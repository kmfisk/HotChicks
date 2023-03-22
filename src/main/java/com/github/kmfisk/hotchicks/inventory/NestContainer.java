package com.github.kmfisk.hotchicks.inventory;

import com.github.kmfisk.hotchicks.item.HotEggItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class NestContainer extends Container {
    private final IInventory inventory;

    public NestContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(5));
    }

    public NestContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(HotContainerTypes.NEST.get(), id);
        this.inventory = inventory;

        for (int j = 0; j < 5; ++j) {
            this.addSlot(new NestSlot(inventory, j, 44 + j * 18, 20));
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

            if (slotStack.getItem() instanceof HotEggItem) {
                slotStack.setTag(new CompoundNBT());
            }

            if (index < 5) {
                if (!this.moveItemStackTo(slotStack, 5, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return resultStack;
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        inventory.stopOpen(player);
    }
}
