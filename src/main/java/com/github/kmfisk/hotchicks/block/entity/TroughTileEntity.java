package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.block.TroughBlock;
import com.github.kmfisk.hotchicks.block.TroughFillType;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class TroughTileEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    private final NonNullList<ItemStack> items;
    private final SidedInvWrapper sideHandler = new SidedInvWrapper(this, Direction.UP);

    public TroughTileEntity(boolean large) {
        super(large ? HotTileEntities.METAL_TROUGH.get() : HotTileEntities.TROUGH.get());
        this.items = NonNullList.withSize(large ? 6 : 3, ItemStack.EMPTY);
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        if (getContainerSize() == 6) return TroughContainer.createGenericDouble(id, playerInventory, this);
        else return TroughContainer.createGenericSingle(id, playerInventory, this);
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
        return ContainerHelper.removeItem(items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        setChanged();
        return ContainerHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < items.size()) {
            items.set(index, stack);
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    public void load(BlockState state, CompoundTag nbt) {
        super.load(state, nbt);
        clearContent();
        ContainerHelper.loadAllItems(nbt, items);
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        super.save(nbt);
        ContainerHelper.saveAllItems(nbt, items);
        return nbt;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(getBlockPos(), 1, getUpdateTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(getBlockState(), pkt.getTag());
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
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
    public void setChanged() { // todo
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

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent(Util.makeDescriptionId("container", ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock())));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing == Direction.UP && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> sideHandler).cast();
        }
        return super.getCapability(capability, facing);
    }
}
