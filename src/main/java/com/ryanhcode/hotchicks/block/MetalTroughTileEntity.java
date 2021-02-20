package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.registry.BlockRegistry;
import com.ryanhcode.hotchicks.registry.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MetalTroughTileEntity extends TroughTileEntity implements ITickableTileEntity {
    private MetalTroughTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public MetalTroughTileEntity() {
        this(TileEntityRegistry.METAL_TROUGH.get());
    }
}
