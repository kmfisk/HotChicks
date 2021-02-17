package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.registry.BlockRegistry;
import com.ryanhcode.hotchicks.registry.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TroughTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
    public NonNullList<ItemStack> contents = NonNullList.withSize(3, ItemStack.EMPTY);
    private int numPlayersUsing;


    private TroughTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public TroughTileEntity() {
        this(TileEntityRegistry.TROUGH.get());
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.contents);
        }

        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.contents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.contents);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return 3;
    }

    public NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.contents = itemsIn;
    }

    protected ITextComponent getDefaultName() {
        return new StringTextComponent("Trough");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return TroughContainer.createGenericSingle(id, player, this);
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
            if (!blockstate.isIn(BlockRegistry.TROUGH_BLOCK.get())) {
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

    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.get(TroughBlock.FACING).getDirectionVec();
        double d0 = (double)this.pos.getX() + 0.5D + (double)vector3i.getX() / 2.0D;
        double d1 = (double)this.pos.getY() + 0.5D + (double)vector3i.getY() / 2.0D;
        double d2 = (double)this.pos.getZ() + 0.5D + (double)vector3i.getZ() / 2.0D;
        this.world.playSound((PlayerEntity)null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }


    public static void swapContents(TroughTileEntity chest, TroughTileEntity otherChest) {
        NonNullList<ItemStack> nonnulllist = chest.getItems();
        chest.setItems(otherChest.getItems());
        otherChest.setItems(nonnulllist);
    }
    private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.chestHandler != null) {
            this.chestHandler.invalidate();
            this.chestHandler = null;
        }
    }


    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
        if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (this.chestHandler == null)
                this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
            return this.chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
        BlockState state = this.getBlockState();
        if (!(state.getBlock() instanceof TroughBlock)) {
            return new net.minecraftforge.items.wrapper.InvWrapper(this);
        }
        IInventory inv = TroughBlock.getChestInventory((TroughBlock) state.getBlock(), state, getWorld(), getPos(), true);
        return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        if (chestHandler != null)
            chestHandler.invalidate();
    }


    public boolean hasItems(){
        for(ItemStack i : contents){
            if(!i.isEmpty()){
                return true;
            }
        }
        return false;
    }
    @Override
    public void tick() {
        //NonNullList<ItemStack> items = getItems();
        //int counter = 0;
        //world.setBlockState(getPos(), world.getBlockState(getPos()).with(NestBlock.eggs,
        //        hasEgg
        //));


        BlockState state =  world.getBlockState(getPos());

        TroughFillType troughFillType = state.get(TroughBlock.CONTAINS);


        if(troughFillType != TroughFillType.WATER) {
            if(!hasItems()){
                world.setBlockState(pos, state.with(TroughBlock.CONTAINS, TroughFillType.NONE));
            }else{
                world.setBlockState(pos, state.with(TroughBlock.CONTAINS, TroughFillType.FEED));
            }
        }

        if(state.get(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER){
            BlockPos connectedSlot = pos.add(new BlockPos(state.get(TroughBlock.FACING).getDirectionVec()).rotate(state.get(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

            BlockState connectedState = world.getBlockState(connectedSlot);
            world.setBlockState(connectedSlot, connectedState.with(TroughBlock.CONTAINS, TroughFillType.WATER));
        }




    }

}
