package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.ContainerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
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
        super(ContainerRegistry.NEST.get(), id);
        this.hopperInventory = inventory;
        assertInventorySize(inventory, 5);
        inventory.openInventory(playerInventory.player);
        int i = 51;

        for(int j = 0; j < 5; ++j) {
            this.addSlot(new NestSlot(inventory, j, 44 + j * 18, 20));
        }

        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
        }

    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.hopperInventory.isUsableByPlayer(playerIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        slot.onSlotChanged();
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();


            if(itemstack1.getItem() instanceof HotEggItem) {
                itemstack1.setTag(new CompoundNBT());
                itemstack1.getOrCreateTag().putBoolean("infertile", true);
            }

            if (index < this.hopperInventory.getSizeInventory()) {
                int size = this.inventorySlots.size();
                if (!this.mergeItemStack(itemstack1, this.hopperInventory.getSizeInventory(), size, true)) {
                    return ItemStack.EMPTY;

                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.hopperInventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
                slot.onSlotChanged();
            } else {
                slot.onSlotChanged();
            }
        }

        for(int i = 0; i < inventorySlots.size()-1; i++){
            inventorySlots.get(i).onSlotChanged();
        }
        slot.onSlotChanged();
        return itemstack;

        //return super.transferStackInSlot(playerIn, index);
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.hopperInventory.closeInventory(playerIn);
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
