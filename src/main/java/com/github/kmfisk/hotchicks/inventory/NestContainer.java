package com.github.kmfisk.hotchicks.inventory;

import com.github.kmfisk.hotchicks.item.HotEggItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class NestContainer extends AbstractContainerMenu {
    private final Container inventory;

    public NestContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(5));
    }

    public NestContainer(int id, Inventory playerInventory, Container inventory) {
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
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack resultStack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            resultStack = slotStack.copy();

            if (slotStack.getItem() instanceof HotEggItem) {
                slotStack.setTag(new CompoundTag());
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
    public void removed(Player player) {
        super.removed(player);
        inventory.stopOpen(player);
    }
}
