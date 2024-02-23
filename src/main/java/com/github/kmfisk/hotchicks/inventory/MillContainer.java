package com.github.kmfisk.hotchicks.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MillContainer extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData data;

    public MillContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(4), new SimpleContainerData(3));
    }

    public MillContainer(int id, Inventory playerInventory, Container inventory, ContainerData data) {
        super(HotContainerTypes.MILL.get(), id);
        this.inventory = inventory;
        this.data = data;

        for (int j = 0; j < 3; ++j) {
            this.addSlot(new Slot(inventory, j, 23 + j * 22, 35));
        }

        this.addSlot(new MillResultSlot(inventory, 3, 130, 35));

        for (int l = 0; l < 3; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 84));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }

        this.addDataSlots(data);
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
            if (index < 4) {
                if (!moveItemStackTo(slotStack, 4, slots.size(), true)) return ItemStack.EMPTY;
            } else if (!moveItemStackTo(slotStack, 0, 4, false)) return ItemStack.EMPTY;

            if (slotStack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }

        return resultStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        inventory.stopOpen(player);
    }

    @OnlyIn(Dist.CLIENT)
    public int getChurnProgress() {
        int progress = this.data.get(1);
        int total = this.data.get(2);
        return total != 0 && progress != 0 ? progress * 24 / total : 0;
    }
}
