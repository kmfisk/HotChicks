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
        this.onSlotChanged();
        stack.setTag(new CompoundNBT());
        stack.getOrCreateTag().putBoolean("infertile", true);
        return stack;
    }
}
