package com.github.kmfisk.hotchicks.block.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, HotChicks.MOD_ID);

    public static final RegistryObject<BlockEntityType<MillTileEntity>> MILL = REGISTRAR.register("mill", () -> BlockEntityType.Builder.of(MillTileEntity::new, HotBlocks.MILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<NestTileEntity>> NEST = REGISTRAR.register("nest", () -> BlockEntityType.Builder.of(NestTileEntity::new, HotBlocks.NEST_BOX.get(), HotBlocks.NEST.get()).build(null));
    public static final RegistryObject<BlockEntityType<FoodCrockTileEntity>> FOOD_CROCK = REGISTRAR.register("food_crock", () -> BlockEntityType.Builder.of(FoodCrockTileEntity::new, HotBlocks.FOOD_CROCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<TroughTileEntity>> TROUGH = REGISTRAR.register("trough", () -> BlockEntityType.Builder.of(() -> new TroughTileEntity(false), HotBlocks.WOODEN_TROUGH.get()).build(null));
    public static final RegistryObject<BlockEntityType<TroughTileEntity>> METAL_TROUGH = REGISTRAR.register("metal_trough", () -> BlockEntityType.Builder.of(() -> new TroughTileEntity(true), HotBlocks.METAL_TROUGH.get()).build(null));
}
