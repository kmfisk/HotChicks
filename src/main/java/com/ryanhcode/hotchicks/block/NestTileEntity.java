package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.BlockRegistry;
import com.ryanhcode.hotchicks.registry.EntityRegistry;
import com.ryanhcode.hotchicks.registry.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.HopperContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.stream.IntStream;

public class NestTileEntity extends LockableLootTileEntity implements ITickableTileEntity, ISidedInventory {
    public NonNullList<ItemStack> barrelContents = NonNullList.withSize(5, ItemStack.EMPTY);
    private int numPlayersUsing;


    private NestTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public NestTileEntity() {
        this(TileEntityRegistry.NEST.get());
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.barrelContents);
        }

        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.barrelContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.barrelContents);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
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

    public void openInventory(PlayerEntity player) {
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

        this.world.getPendingBlockTicks().scheduleTick(this.getPos(), this.getBlockState().getBlock(), 5);
    }

    public void barrelTick() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        this.numPlayersUsing = ChestTileEntity.calculatePlayersUsing(this.world, this, i, j, k);
        if (this.numPlayersUsing > 0) {
            this.scheduleTick();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!(blockstate.isIn(BlockRegistry.NEST_BOX.get()) || blockstate.isIn(BlockRegistry.NEST.get()))) {
                this.remove();
                return;
            }

        }


    }

    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
        }



    }



    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }



    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.get(NestBlock.PROPERTY_FACING).getDirectionVec();
        double d0 = (double)this.pos.getX() + 0.5D + (double)vector3i.getX() / 2.0D;
        double d1 = (double)this.pos.getY() + 0.5D + (double)vector3i.getY() / 2.0D;
        double d2 = (double)this.pos.getZ() + 0.5D + (double)vector3i.getZ() / 2.0D;
        this.world.playSound((PlayerEntity)null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void tick() {
        if(world == null) return;
        if(world.isRemote()) return;

        NonNullList<ItemStack> items = getItems();
        int counter = 0;

        boolean hasEgg = false;
        for(ItemStack item : items){
            if (item.getItem() instanceof HotEggItem && !item.isEmpty()) {
                hasEgg = true;
                CompoundNBT tag = item.getOrCreateTag();
                int time_left = tag.getInt("time_left") - 1;
                if(time_left <= 0){

                    HotChickenEntity chicken = new HotChickenEntity(EntityRegistry.HOT_CHICKEN.get(), world);
                    chicken.setPosition(getPos().getX()+0.5, getPos().getY()+0.2, getPos().getZ()+0.5);
                    world.addEntity(
                            chicken
                    );

                    chicken.setChild(true);
                    chicken.setGrowingAge(-100);
                    chicken.setTameness(HotEggItem.getTameness(item));
                    chicken.setStats(HotEggItem.getStats(item));
                    chicken.setBreed(ChickenBreed.valueOf(tag.getString("breed").toUpperCase()));

                    barrelContents.set(counter, ItemStack.EMPTY);
                }else {
                    tag.putInt("time_left", time_left);
                }
            }
            counter+=1;
        }

        /*boolean hasItems = false;
        for(ItemStack i : items){
            if(i!=ItemStack.EMPTY){
                hasItems = true;
            }else{

            }
        }*/
        world.setBlockState(getPos(), world.getBlockState(getPos()).with(NestBlock.eggs,
                hasEgg
        ));
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, 3).toArray();
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return false;
    }

    @Override
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler() {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
    }
}
