package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class TroughContainer extends Container {
    private IInventory inventory;

    public static TroughContainer createGenericSingle(int id, PlayerInventory player, IInventory blockEntity) {
        return new TroughContainer(HotContainerTypes.TROUGH_SINGLE.get(), id, blockEntity, player, 3);
    }

    public static TroughContainer createGenericSingle(int id, PlayerInventory player) {
        return new TroughContainer(HotContainerTypes.TROUGH_SINGLE.get(), id, new Inventory(3), player, 3);
    }


    public static TroughContainer createGenericDouble(int id, PlayerInventory player, IInventory blockEntity) {
        return new TroughContainer(HotContainerTypes.TROUGH_DOUBLE.get(), id, blockEntity, player, 6);
    }

    public static TroughContainer createGenericDouble(int id, PlayerInventory player) {
        return new TroughContainer(HotContainerTypes.TROUGH_DOUBLE.get(), id, new Inventory(6), player, 6);
    }

    public static TroughContainer createGenericDoubleMetal(int id, PlayerInventory player) {
        return new TroughContainer(HotContainerTypes.TROUGH_DOUBLE_METAL.get(), id, new Inventory(12), player, 12);
    }

    public static TroughContainer createGenericDoubleMetal(int id, PlayerInventory player, IInventory blockEntity) {
        return new TroughContainer(HotContainerTypes.TROUGH_DOUBLE_METAL.get(), id, blockEntity, player, 12);
    }

    private TroughContainer(ContainerType<?> type, int id, IInventory blockEntity, PlayerInventory player, int slot) {
        this(type, id, player, blockEntity, slot);
    }

    // public TroughContainer(int id, PlayerInventory playerInventory, int slots) {
    //     this(id, playerInventory, new Inventory(slots), slots);
    // }

    public int slot = 0;

    private TroughContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory, int slot) {
        super(type, id);
        this.slot = slot;
        this.inventory = inventory;
        checkContainerSize(inventory, slot);
        inventory.startOpen(playerInventory.player);
        int i = 51;

        double offset = 0;
        offset = slot == 3 ? 1 : -0.5;
        if (slot == 12) {
            for (int j = 0; j < slot / 2; j += 1) {
                this.addSlot(new TroughSlot(inventory, j, (int) (44 + j * 18 + offset * 18), 20));
            }
            for (int j = slot / 2; j < slot; j += 1) {
                this.addSlot(new TroughSlot(inventory, j, (int) (44 + (j - 6) * 18 + offset * 18), 20 + 18));
            }
        } else {
            for (int j = 0; j < slot; j += 1) {
                this.addSlot(new TroughSlot(inventory, j, (int) (44 + j * 18 + offset * 18), 20));
            }
        }
        int mod = slot == 12 ? 14 : 0;
        for (int l = 0; l < 3; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51 + mod));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109 + mod));
        }

    }


    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(PlayerEntity playerIn) {
        return this.inventory.stillValid(playerIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.slot) {
                if (!this.moveItemStackTo(itemstack1, this.slot, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.slot, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.inventory.stopOpen(playerIn);
    }

}
