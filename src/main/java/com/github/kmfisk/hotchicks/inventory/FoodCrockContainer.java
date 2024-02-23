package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FoodCrockContainer extends AbstractContainerMenu {
    private final Container inventory;

    public FoodCrockContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(36));
    }

    public FoodCrockContainer(int containerId, Inventory playerInventory, Container inventory) {
        super(HotContainerTypes.FOOD_CROCK.get(), containerId);
        this.inventory = inventory;

        for (int j = 0; j < 3; ++j) {
            this.addSlot(new Slot(inventory, j, 62 + j * 18, 20));
        }

        for (int l = 0; l < 3; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            resultStack = slotStack.copy();
            if (index < 3) {
                if (!this.moveItemStackTo(slotStack, 3, this.slots.size(), true)) return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(slotStack, 0, 3, false)) return ItemStack.EMPTY;

            if (slotStack.isEmpty())
                slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }

        return resultStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        inventory.stopOpen(player);
    }
}
