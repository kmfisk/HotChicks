package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.ContainerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
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
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class TroughContainer extends Container {
    private IInventory inventory;

    public static TroughContainer createGenericSingle(int id, PlayerInventory player, IInventory blockEntity) {
        return new TroughContainer(ContainerRegistry.TROUGH_SINGLE.get(), id, blockEntity, player, 3);
    }

    public static TroughContainer createGenericSingle(int id, PlayerInventory player) {
        return new TroughContainer(ContainerRegistry.TROUGH_SINGLE.get(), id, new Inventory(3), player, 3);
    }


    public static TroughContainer createGenericDouble(int id, PlayerInventory player, IInventory blockEntity) {
        return new TroughContainer(ContainerRegistry.TROUGH_DOUBLE.get(), id,blockEntity, player, 6);
    }

    public static TroughContainer createGenericDouble(int id, PlayerInventory player) {
        return new TroughContainer(ContainerRegistry.TROUGH_DOUBLE.get(), id, new Inventory(6), player, 6);
    }

    private TroughContainer(ContainerType<?> type, int id, IInventory blockEntity, PlayerInventory player, int slots) {
        this(type, id, player, blockEntity, slots);
    }

   // public TroughContainer(int id, PlayerInventory playerInventory, int slots) {
   //     this(id, playerInventory, new Inventory(slots), slots);
   // }

    int slots = 0;
    private TroughContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, IInventory inventory,int slots) {
        super(type, id);
        this.slots = slots;
        this.inventory = inventory;
        assertInventorySize(inventory, slots);
        inventory.openInventory(playerInventory.player);
        int i = 51;

        double offset = 0;
        offset = slots == 3 ? 1 : -0.5;
        for(int j = 0; j < slots; j+=1) {
            this.addSlot(new TroughSlot(inventory, j, (int)(44 + j * 18 + offset * 18), 20));
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
        return this.inventory.isUsableByPlayer(playerIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.slots) {
                if (!this.mergeItemStack(itemstack1, this.slots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.slots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }

}
