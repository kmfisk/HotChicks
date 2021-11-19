package com.ryanhcode.hotchicks.inventory;

import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.HotContainers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class NestContainer extends Container {
    private final IInventory hopperInventory;

    public NestContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(5));
    }

    public NestContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(HotContainers.NEST.get(), id);
        this.hopperInventory = inventory;
        checkContainerSize(inventory, 5);
        inventory.startOpen(playerInventory.player);
        int i = 51;

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

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(PlayerEntity playerIn) {
        return this.hopperInventory.stillValid(playerIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        slot.setChanged();
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();


            if (itemstack1.getItem() instanceof HotEggItem) {
                itemstack1.setTag(new CompoundNBT());
                itemstack1.getOrCreateTag().putBoolean("infertile", true);
            }

            if (index < this.hopperInventory.getContainerSize()) {
                int size = this.slots.size();
                if (!this.moveItemStackTo(itemstack1, this.hopperInventory.getContainerSize(), size, true)) {
                    return ItemStack.EMPTY;

                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.hopperInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
                slot.setChanged();
            } else {
                slot.setChanged();
            }
        }

        for (int i = 0; i < slots.size() - 1; i++) {
            slots.get(i).setChanged();
        }
        slot.setChanged();
        return itemstack;

        //return super.transferStackInSlot(playerIn, index);
    }

    /**
     * Called when the container is closed.
     */
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.hopperInventory.stopOpen(playerIn);
    }

    public static ContainerType<NestContainer> createCraftingContainer() {
        return IForgeContainerType.create((windowId, inv, data) -> {
            //PlayerEntity player = McJtyLib.proxy.getClientPlayer();
            ClientPlayerEntity player = Minecraft.getInstance().player;
            NestContainer container = new NestContainer(windowId, player.inventory);
            return container;
        });
    }
}
