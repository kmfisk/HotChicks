package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import com.ryanhcode.hotchicks.entity.HotEntities;
import com.ryanhcode.hotchicks.inventory.NestContainer;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.HotTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class NestTileEntity extends LockableLootTileEntity implements ITickableTileEntity, ISidedInventory {
    public NonNullList<ItemStack> barrelContents = NonNullList.withSize(5, ItemStack.EMPTY);
    private int numPlayersUsing;


    private NestTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public NestTileEntity() {
        this(HotTileEntities.NEST.get());
    }

    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.barrelContents);
        }

        return compound;
    }

    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.barrelContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.barrelContents);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getContainerSize() {
        return 5;
    }

    public NonNullList<ItemStack> getItems() {
        return this.barrelContents;
    }

    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.barrelContents = itemsIn;
    }

    protected ITextComponent getDefaultName() {
        return new StringTextComponent("Nest");
    }

    public Container createMenu(int id, PlayerInventory player) {
        return new NestContainer(id, player, this);
        //return ChestContainer.createGeneric9X1(id, player);
    }

    public void startOpen(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            BlockState blockstate = this.getBlockState();
            this.scheduleTick();
        }

    }

    private void scheduleTick() {

        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }

    public void barrelTick() {
        int i = this.worldPosition.getX();
        int j = this.worldPosition.getY();
        int k = this.worldPosition.getZ();
        this.numPlayersUsing = ChestTileEntity.getOpenCount(this.level, this, i, j, k);
        if (this.numPlayersUsing > 0) {
            this.scheduleTick();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!(blockstate.is(HotBlocks.NEST_BOX.get()) || blockstate.is(HotBlocks.NEST.get()))) {
                this.setRemoved();
                return;
            }

        }


    }

    public void stopOpen(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
        }


    }


    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return false;
    }


    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.getValue(NestBlock.PROPERTY_FACING).getNormal();
        double d0 = (double) this.worldPosition.getX() + 0.5D + (double) vector3i.getX() / 2.0D;
        double d1 = (double) this.worldPosition.getY() + 0.5D + (double) vector3i.getY() / 2.0D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D + (double) vector3i.getZ() / 2.0D;
        this.level.playSound((PlayerEntity) null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void tick() {
        if (level == null) return;
        if (level.isClientSide()) return;

        NonNullList<ItemStack> items = getItems();
        int counter = 0;

        boolean hasEgg = false;
        for (ItemStack item : items) {
            if (item.getItem() instanceof HotEggItem && !item.isEmpty()) {
                hasEgg = true;
                CompoundNBT tag = item.getOrCreateTag();
                int time_left = tag.getInt("time_left") - 1;
                if (time_left <= 0) {

                    HotChickenEntity chicken = new HotChickenEntity(HotEntities.CHICKEN.get(), level);
                    chicken.setPos(getBlockPos().getX() + 0.5, getBlockPos().getY() + 0.2, getBlockPos().getZ() + 0.5);
                    level.addFreshEntity(
                            chicken
                    );

                    chicken.setBaby(true);
                    chicken.setAge(-100);
                    chicken.setTameness(HotEggItem.getTameness(item));
                    chicken.setStats(HotEggItem.getStats(item));

                    barrelContents.set(counter, ItemStack.EMPTY);
                } else {
                    tag.putInt("time_left", time_left);
                }
            }
            counter += 1;
        }

        /*boolean hasItems = false;
        for(ItemStack i : items){
            if(i!=ItemStack.EMPTY){
                hasItems = true;
            }else{

            }
        }*/
        level.setBlockAndUpdate(getBlockPos(), level.getBlockState(getBlockPos()).setValue(NestBlock.eggs,
                hasEgg
        ));
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, 3).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return false;
    }

    @Override
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
    }
}
