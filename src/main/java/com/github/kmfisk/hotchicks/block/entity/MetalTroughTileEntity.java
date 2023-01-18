package com.github.kmfisk.hotchicks.block.entity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MetalTroughTileEntity extends TroughTileEntity implements ITickableTileEntity {
    private MetalTroughTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public MetalTroughTileEntity() {
        this(HotTileEntities.METAL_TROUGH.get());
    }
}
