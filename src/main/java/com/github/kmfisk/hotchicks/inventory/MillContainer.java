package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MillContainer extends Container {
    private final IInventory inventory;
    private final IIntArray data;

    public MillContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(4), new IntArray(3));
    }

    public MillContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray data) {
        super(HotContainerTypes.MILL.get(), id);
        this.inventory = inventory;
        this.data = data;

        for (int j = 0; j < 3; ++j) {
            this.addSlot(new Slot(inventory, j, 23 + j * 22, 35));
        }

        this.addSlot(new MillResultSlot(inventory, 3, 130, 35));

        for (int l = 0; l < 3; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 84));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            resultStack = slotStack.copy();
            if (index < 4) {
                if (!moveItemStackTo(slotStack, 4, slots.size(), true)) return ItemStack.EMPTY;
            } else if (!moveItemStackTo(slotStack, 0, 4, false)) return ItemStack.EMPTY;

            if (slotStack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }

        return resultStack;
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        inventory.stopOpen(player);
    }

    @OnlyIn(Dist.CLIENT)
    public int getChurnProgress() {
        int i = data.get(1);
        int j = data.get(2);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }
}
