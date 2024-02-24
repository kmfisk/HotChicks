package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TroughSlot extends Slot {
    public TroughSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        this.setChanged();
        return super.mayPickup(playerIn);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(ItemStack stack) {
//        if (stack.getItem() == Items.WHEAT) {
        return true;
//        }
//        return false;
    }

}
