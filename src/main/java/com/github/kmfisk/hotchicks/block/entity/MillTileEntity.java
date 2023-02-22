package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.inventory.MillContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class MillTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
    protected final NonNullList<ItemStack> items = NonNullList.withSize(7, ItemStack.EMPTY);
    private final SidedInvWrapper sideHandler = new SidedInvWrapper(this, Direction.UP);
    protected Item resultItem = Items.AIR;
    protected int activeTime;
    protected int breedingProgress;
    protected int breedingTotalTime;

    public MillTileEntity() {
        super(HotTileEntities.MILL.get());
    }

    public MillTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + HotChicks.MOD_ID + ".mill");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MillContainer(id, playerInventory, this);
    }

    protected boolean isActive() {
        return activeTime > 0;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        clearContent();
        ItemStackHelper.loadAllItems(tag, items);
        activeTime = tag.getInt("ActiveTime");
        breedingProgress = tag.getInt("CookTime");
        breedingTotalTime = tag.getInt("CookTimeTotal");
        resultItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("ResultItem")));
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        tag.putInt("ActiveTime", activeTime);
        tag.putInt("CookTime", breedingProgress);
        tag.putInt("CookTimeTotal", breedingTotalTime);
        tag.putString("ResultItem", resultItem.getRegistryName().toString());
        ItemStackHelper.saveAllItems(tag, items);
        return tag;
    }

    @Override
    public void tick() {
        boolean flag1 = false;
        if (isActive()) {
            --activeTime;
        }

        if (!level.isClientSide) {
            // todo
        }

        if (flag1) {
            setChanged();
        }
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
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return pIndex <= 2;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
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
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        load(state, tag);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!remove && facing == Direction.UP && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> sideHandler).cast();
        }
        return super.getCapability(capability, facing);
    }
}
