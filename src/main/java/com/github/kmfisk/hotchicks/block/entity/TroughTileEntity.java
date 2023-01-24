package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.TroughBlock;
import com.github.kmfisk.hotchicks.block.TroughFillType;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    public int size = 3;
    public NonNullList<ItemStack> contents = NonNullList.withSize(3, ItemStack.EMPTY);
    private int numPlayersUsing;


    public TroughTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public TroughTileEntity() {
        this(HotTileEntities.TROUGH.get());
    }

    public TroughTileEntity(int size) {
        this();
        contents = NonNullList.withSize(size, ItemStack.EMPTY);
        this.size = size;
    }

    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.contents);
        }

        return compound;
    }

    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.contents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.contents);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getContainerSize() {
        return size;
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
        if (size == 6) {
            return TroughContainer.createGenericDouble(id, player, this);
        }
        return TroughContainer.createGenericSingle(id, player, this);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return level.getBlockState(getBlockPos()).getValue(TroughBlock.CONTAINS) != TroughFillType.WATER /*&& stack.getItem() == Items.WHEAT*/;
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
            if (!(blockstate.is(HotBlocks.TROUGH_BLOCK.get()) || blockstate.is(HotBlocks.METAL_TROUGH_BLOCK.get()))) {
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

    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.getValue(TroughBlock.FACING).getNormal();
        double d0 = (double) this.worldPosition.getX() + 0.5D + (double) vector3i.getX() / 2.0D;
        double d1 = (double) this.worldPosition.getY() + 0.5D + (double) vector3i.getY() / 2.0D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D + (double) vector3i.getZ() / 2.0D;
        this.level.playSound((PlayerEntity) null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }


    public static void swapContents(TroughTileEntity chest, TroughTileEntity otherChest) {
        NonNullList<ItemStack> nonnulllist = chest.getItems();
        chest.setItems(otherChest.getItems());
        otherChest.setItems(nonnulllist);
    }

    private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

    @Override
    public void clearCache() {
        super.clearCache();
        if (this.chestHandler != null) {
            this.chestHandler.invalidate();
            this.chestHandler = null;
        }
    }


    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
        if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
        IInventory inv = TroughBlock.getChestInventory((TroughBlock) state.getBlock(), state, getLevel(), getBlockPos(), true);
        return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        if (chestHandler != null)
            chestHandler.invalidate();
    }


    public boolean hasItems() {
        boolean has = false;
        for (ItemStack i : getItems()) {
            if (!i.isEmpty()) {
                has = true;
            }
        }
        return has;
    }

    @Override
    public void tick() {
        //NonNullList<ItemStack> items = getItems();
        //int counter = 0;
        //world.setBlockState(getPos(), world.getBlockState(getPos()).with(NestBlock.eggs,
        //        hasEgg
        //));
        if (level == null) return;
        if (level.isClientSide()) return;

        BlockState state = level.getBlockState(getBlockPos());

        TroughFillType troughFillType = state.getValue(TroughBlock.CONTAINS);

        // if(troughFillType != TroughFillType.WATER) {
        TroughFillType setState = state.getValue(TroughBlock.CONTAINS);


        if (state.getValue(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER) {
            BlockPos connectedSlot = worldPosition.offset(new BlockPos(state.getValue(TroughBlock.FACING).getNormal()).rotate(state.getValue(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

            BlockState connectedState = level.getBlockState(connectedSlot);
            level.setBlockAndUpdate(connectedSlot, connectedState.setValue(TroughBlock.CONTAINS, TroughFillType.WATER));
        }


        if (state.getValue(TroughBlock.TYPE) != ChestType.SINGLE) {
            BlockPos connectedSlot = worldPosition.offset(new BlockPos(state.getValue(TroughBlock.FACING).getNormal()).rotate(state.getValue(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

            BlockState connectedState = level.getBlockState(connectedSlot);
            if (state.getValue(TroughBlock.CONTAINS) != TroughFillType.WATER) {
                TroughFillType value = isEmpty() && ((TroughTileEntity) (level.getBlockEntity(connectedSlot))).isEmpty() ? TroughFillType.NONE : TroughFillType.FEED;
                level.setBlockAndUpdate(worldPosition, state.setValue(TroughBlock.CONTAINS, value));
            }

        } else {
            if (state.getValue(TroughBlock.CONTAINS) != TroughFillType.WATER) {
                TroughFillType value = isEmpty() ? TroughFillType.NONE : TroughFillType.FEED;
                level.setBlockAndUpdate(worldPosition, state.setValue(TroughBlock.CONTAINS, value));
            }
        }

    }

}
