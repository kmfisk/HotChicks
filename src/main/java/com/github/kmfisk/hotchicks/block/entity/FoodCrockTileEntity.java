package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.block.FoodCrockBlock;
import com.github.kmfisk.hotchicks.inventory.FoodCrockContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class FoodCrockTileEntity extends LockableTileEntity implements ISidedInventory {
    private final NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private final SidedInvWrapper sideHandler = new SidedInvWrapper(this, Direction.UP);

    public FoodCrockTileEntity() {
        super(HotTileEntities.FOOD_CROCK.get());
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new FoodCrockContainer(id, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int index) {
        return index >= 0 && index < items.size() ? items.get(index) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        setChanged();
        return ItemStackHelper.removeItem(items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        setChanged();
        return ItemStackHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < items.size()) {
            items.set(index, stack);
            setChanged();
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        clearContent();
        ItemStackHelper.loadAllItems(tag, items);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        ItemStackHelper.saveAllItems(tag, items);
        return tag;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), 1, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(getBlockState(), pkt.getTag());
        super.onDataPacket(net, pkt);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, items.size()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return direction == null || direction == Direction.UP;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public void setChanged() {
        if (level != null) {
            BlockState blockState = level.getBlockState(getBlockPos());
            if (!level.isClientSide) {
                if (blockState.getBlock() instanceof FoodCrockBlock)
                    blockState = blockState.setValue(FoodCrockBlock.FILLED, !isEmpty());

                level.setBlock(worldPosition, blockState, 2);
            }

            level.blockEntityChanged(worldPosition, this);
            if (!blockState.isAir(level, worldPosition))
                level.updateNeighbourForOutputSignal(worldPosition, blockState.getBlock());

            level.sendBlockUpdated(getBlockPos(), blockState, blockState, 3);
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction side) {
        if (!this.remove && side == Direction.UP && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> sideHandler).cast();
        }
        return super.getCapability(capability, side);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(Util.makeDescriptionId("container", ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock())));
    }
}
