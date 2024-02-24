package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NestSlot extends Slot {
    public NestSlot(Container inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        stack.setTag(new CompoundTag());
        super.onTake(player, stack);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        this.setChanged();
        return super.mayPickup(playerIn);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }
}
