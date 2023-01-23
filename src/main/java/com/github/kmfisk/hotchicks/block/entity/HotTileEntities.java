package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotTileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, HotChicks.MOD_ID);

    public static RegistryObject<TileEntityType<NestTileEntity>> NEST = REGISTRAR.register("nest", () -> TileEntityType.Builder.of(NestTileEntity::new, HotBlocks.NEST_BOX.get(), HotBlocks.NEST.get()).build(null));
    public static RegistryObject<TileEntityType<TroughTileEntity>> TROUGH = REGISTRAR.register("trough", () -> TileEntityType.Builder.of(TroughTileEntity::new, HotBlocks.TROUGH_BLOCK.get(), HotBlocks.METAL_TROUGH_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<MetalTroughTileEntity>> METAL_TROUGH = REGISTRAR.register("metal_trough", () -> TileEntityType.Builder.of(MetalTroughTileEntity::new, HotBlocks.METAL_TROUGH_BLOCK.get()).build(null));
}
