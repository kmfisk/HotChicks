package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.item.HotEggItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class NestSlot extends Slot {
    public NestSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        stack.setTag(new CompoundNBT());
        stack.getOrCreateTag().putBoolean("infertile", true);
        this.onSlotChanged();
        return stack;
    }


    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        this.onSlotChanged();
        return super.canTakeStack(playerIn);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        if(stack.getItem() instanceof HotEggItem) {
            return !stack.getOrCreateTag().getBoolean("infertile");
        }
        return false;
    }

}
