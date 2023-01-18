package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TroughSlot extends Slot {
    public TroughSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        this.setChanged();
        return stack;
    }


    @Override
    public boolean mayPickup(PlayerEntity playerIn) {
        this.setChanged();
        return super.mayPickup(playerIn);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem() == Items.WHEAT) {
            return true;
        }
        return false;
    }

}
