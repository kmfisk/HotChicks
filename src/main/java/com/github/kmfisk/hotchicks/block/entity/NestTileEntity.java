package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.block.NestBlock;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.inventory.NestContainer;
import com.github.kmfisk.hotchicks.item.HotEggItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class NestTileEntity extends BaseContainerBlockEntity implements TickableBlockEntity, WorldlyContainer {
    private final NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    private final SidedInvWrapper sideHandler = new SidedInvWrapper(this, Direction.UP);

    public NestTileEntity(BlockEntityType<?> type) {
        super(type);
    }

    public NestTileEntity() {
        super(HotTileEntities.NEST.get());
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player) {
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
        ItemStack stack = ContainerHelper.removeItem(this.items, index, count);
        if (stack.getItem() instanceof HotEggItem) stack.setTag(new CompoundTag());
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        setChanged();
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index >= 0 && index < items.size()) {
            items.set(index, stack);
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
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
    protected Component getDefaultName() {
        return new TranslatableComponent(Util.makeDescriptionId("container", ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock())));
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
                if (item.hasTag() && item.getTag().contains("TimeLeft")) {
                    CompoundTag itemTag = item.getTag();
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
