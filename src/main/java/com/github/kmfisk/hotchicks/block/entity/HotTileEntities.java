package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotTileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, HotChicks.MOD_ID);

    public static final RegistryObject<TileEntityType<NestTileEntity>> NEST = REGISTRAR.register("nest", () -> TileEntityType.Builder.of(NestTileEntity::new, HotBlocks.NEST_BOX.get(), HotBlocks.NEST.get()).build(null));
    public static final RegistryObject<TileEntityType<FoodCrockTileEntity>> FOOD_CROCK = REGISTRAR.register("food_crock", () -> TileEntityType.Builder.of(FoodCrockTileEntity::new, HotBlocks.FOOD_CROCK.get()).build(null));
    public static final RegistryObject<TileEntityType<TroughTileEntity>> TROUGH = REGISTRAR.register("trough", () -> TileEntityType.Builder.of(() -> new TroughTileEntity(false), HotBlocks.WOODEN_TROUGH.get()).build(null));
    public static final RegistryObject<TileEntityType<TroughTileEntity>> METAL_TROUGH = REGISTRAR.register("metal_trough", () -> TileEntityType.Builder.of(() -> new TroughTileEntity(true), HotBlocks.METAL_TROUGH.get()).build(null));
}
