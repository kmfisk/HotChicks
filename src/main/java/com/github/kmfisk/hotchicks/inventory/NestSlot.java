package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
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
        this.setChanged();
        return stack;
    }

    @Override
    public boolean mayPickup(PlayerEntity playerIn) {
        this.setChanged();
        return super.mayPickup(playerIn);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }
}
