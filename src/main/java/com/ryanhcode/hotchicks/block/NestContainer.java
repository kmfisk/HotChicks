package com.ryanhcode.hotchicks.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class NestContainer extends Container {
    private final IInventory hopperInventory;

    public NestContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new Inventory(5));
    }

    public NestContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(ContainerType.HOPPER, id);
        this.hopperInventory = inventory;
        assertInventorySize(inventory, 5);
        inventory.openInventory(playerInventory.player);
        int i = 51;

        for(int j = 0; j < 5; ++j) {
            this.addSlot(new NestSlot(inventory, j, 44 + j * 18, 20));
        }

        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new NestSlot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new NestSlot(playerInventory, i1, 8 + i1 * 18, 109));
        }

    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.hopperInventory.isUsableByPlayer(playerIn);
    }


    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.hopperInventory.closeInventory(playerIn);
    }
}

