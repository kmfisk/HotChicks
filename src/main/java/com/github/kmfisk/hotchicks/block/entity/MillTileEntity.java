package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.inventory.MillContainer;
import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class MillTileEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible {
    protected final NonNullList<ItemStack> items = NonNullList.withSize(7, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[] sideHandlers = SidedInvWrapper.create(this, Direction.DOWN, Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    protected int activeTime;
    protected int churningProgress;
    protected int churningTotalTime;
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int index) {
            switch (index) {
                case 0:
                    return MillTileEntity.this.activeTime;
                case 1:
                    return MillTileEntity.this.churningProgress;
                case 2:
                    return MillTileEntity.this.churningTotalTime;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case 0:
                    MillTileEntity.this.activeTime = value;
                    break;
                case 1:
                    MillTileEntity.this.churningProgress = value;
                    break;
                case 2:
                    MillTileEntity.this.churningTotalTime = value;
            }
        }

        public int getCount() {
            return 3;
        }
    };

    public MillTileEntity(BlockPos pos, BlockState state) {
        super(HotTileEntities.MILL.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + HotChicks.MOD_ID + ".mill");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return new MillContainer(id, playerInventory, this, dataAccess);
    }

    protected boolean isActive() {
        return activeTime > 0;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        clearContent();
        ContainerHelper.loadAllItems(tag, items);
        activeTime = tag.getInt("ActiveTime");
        churningProgress = tag.getInt("CookTime");
        churningTotalTime = tag.getInt("CookTimeTotal");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("ActiveTime", activeTime);
        tag.putInt("CookTime", churningProgress);
        tag.putInt("CookTimeTotal", churningTotalTime);
        ContainerHelper.saveAllItems(tag, items);
    }

    private boolean readyToChurn() {
        return !items.get(0).isEmpty() && !items.get(1).isEmpty() && !items.get(2).isEmpty();
    }

    public void serverTick() {
        boolean flag = isActive();
        boolean flag1 = false;
        if (isActive()) --activeTime;

        if (!level.isClientSide) {
            if (isActive() || readyToChurn()) {
                if (!isActive() && canChurn()) {
                    activeTime = getTotalChurnTime();
                    if (isActive()) flag1 = true;
                }

                if (isActive() && canChurn()) {
                    ++churningProgress;
                    if (churningProgress == churningTotalTime) {
                        churningProgress = 0;
                        churningTotalTime = getTotalChurnTime();
                        churn();
                        flag1 = true;
                    }
                } else churningProgress = 0;

            } else if (!isActive() && churningProgress > 0)
                churningProgress = Mth.clamp(churningProgress - 2, 0, churningTotalTime);

            if (flag != isActive()) flag1 = true;
        }

        if (flag1) setChanged();
    }

    protected boolean canChurn() {
        if (readyToChurn()) {
            ItemStack resultStack = ItemStack.EMPTY;

            boolean isMilk0 = items.get(0).getItem() == HotItems.BOTTLED_MILK.get();
            boolean isMilk1 = items.get(1).getItem() == HotItems.BOTTLED_MILK.get();
            boolean isMilk2 = items.get(2).getItem() == HotItems.BOTTLED_MILK.get();
            if (isMilk0 && isMilk1 && isMilk2) resultStack = new ItemStack(HotItems.BUTTER.get());

            if (resultStack.isEmpty()) {
                return false;
            } else {
                ItemStack resultSlot = items.get(3);
                if (resultSlot.isEmpty()) return true;
                else if (!resultSlot.sameItem(resultStack)) return false;
                else if (resultSlot.getCount() + resultStack.getCount() <= getMaxStackSize() && resultSlot.getCount() + resultStack.getCount() <= resultSlot.getMaxStackSize())
                    return true;
                else return resultSlot.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
            }

        } else return false;
    }

    private void churn() {
        if (canChurn()) {
            ItemStack slot0 = items.get(0);
            ItemStack slot1 = items.get(1);
            ItemStack slot2 = items.get(2);
            ItemStack resultStack = new ItemStack(HotItems.BUTTER.get(), 3); // todo: recipes, for now just butter
            ItemStack resultSlot = items.get(3);

            if (resultSlot.isEmpty()) items.set(3, resultStack.copy());
            else if (resultSlot.getItem() == resultStack.getItem()) resultSlot.grow(resultStack.getCount());

            slot0.shrink(1);
            slot1.shrink(1);
            slot2.shrink(1);
        }
    }

    protected int getTotalChurnTime() {
        return 4800;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        Direction blockFacing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (side == Direction.DOWN) return new int[]{3};
        else if (side == blockFacing.getClockWise()) return new int[]{0};
        else if (side == blockFacing.getCounterClockWise()) return new int[]{2};
        else return new int[]{1};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == 3;
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
        ItemStack itemStack = items.get(index);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemStack) && ItemStack.tagMatches(stack, itemStack);
        if (index >= 0 && index < items.size()) {
            items.set(index, stack);
            if (stack.getCount() > getMaxStackSize()) stack.setCount(getMaxStackSize());
        }

        if (index < 3 && !flag) {
            churningTotalTime = getTotalChurnTime();
            churningProgress = 0;
            setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack pStack) {
        return index != 3;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            switch (facing) {
                case DOWN:
                    return sideHandlers[0].cast();
                case UP:
                    return sideHandlers[1].cast();
                case NORTH:
                    return sideHandlers[2].cast();
                case EAST:
                    return sideHandlers[3].cast();
                case SOUTH:
                    return sideHandlers[4].cast();
                case WEST:
                    return sideHandlers[5].cast();
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        for (ItemStack itemstack : items) {
            stackedContents.accountStack(itemstack);
        }
    }
}
