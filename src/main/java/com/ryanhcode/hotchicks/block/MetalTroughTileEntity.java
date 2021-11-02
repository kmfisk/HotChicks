package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.registry.TileEntityRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MetalTroughTileEntity extends TroughTileEntity implements ITickableTileEntity {
    private MetalTroughTileEntity(TileEntityType<?> barrelType) {
        super(barrelType);
    }

    public MetalTroughTileEntity() {
        this(TileEntityRegistry.METAL_TROUGH.get());
    }
}
