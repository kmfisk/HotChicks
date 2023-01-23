package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.block.NestBlock;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.inventory.NestContainer;
import com.github.kmfisk.hotchicks.item.HotEggItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
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

public class NestTileEntity extends LockableTileEntity implements ITickableTileEntity, ISidedInventory {
    private final NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    private final SidedInvWrapper sideHandler = new SidedInvWrapper(this, Direction.UP);

    public NestTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public NestTileEntity() {
        super(HotTileEntities.NEST.get());
    }

    @Override
    public Container createMenu(int id, PlayerInventory player) {
        return new NestContainer(id, player, this);
    }

    public NonNullList<ItemStack> getItems() {
        return items;
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
        return ItemStackHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        setChanged();
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < items.size()) {
            items.set(index, stack);
            setChanged();
        }
    }

    @Override
    public boolean stillValid(PlayerEntity pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        clearContent();
        ItemStackHelper.loadAllItems(nbt, items);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        ItemStackHelper.saveAllItems(nbt, items);
        return nbt;
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
            super.setChanged(); // todo: show egg models
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(Util.makeDescriptionId("container", ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock())));
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return false;
    }

    @Override
    public void tick() {
        if (level == null) return;
        if (level.isClientSide()) return;

        int counter = 0;

        boolean hasEgg = false;
        for (ItemStack item : items) {
            if (item.getItem() instanceof HotEggItem && !item.isEmpty()) {
                hasEgg = true;
                CompoundNBT itemTag = item.getTag();
                int timeLeft = itemTag.getInt("TimeLeft") - 1;
                if (timeLeft <= 0) {
                    HotChickenEntity chicken = (HotChickenEntity) EntityType.loadEntityRecursive(itemTag, level, entity -> entity);
                    if (chicken != null) {
                        chicken.absMoveTo(getBlockPos().getX() + 0.5, getBlockPos().getY() + 0.2, getBlockPos().getZ() + 0.5);
                        level.addFreshEntity(chicken);
                    }

                    this.items.set(counter, ItemStack.EMPTY);

                } else itemTag.putInt("TimeLeft", timeLeft);
            }

            counter += 1;
        }

        level.setBlockAndUpdate(getBlockPos(), level.getBlockState(getBlockPos()).setValue(NestBlock.PROPERTY_EGGS, hasEgg));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing == Direction.UP && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> sideHandler).cast();
        }
        return super.getCapability(capability, facing);
    }
}
