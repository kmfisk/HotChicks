package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.NestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {

    public static final DeferredRegister<TileEntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, HotChickens.MODID);

    public static RegistryObject<TileEntityType<NestTileEntity>> NEST = ENTITIES.register("nest_tile_entity",()->TileEntityType.Builder.create(NestTileEntity::new, BlockRegistry.NEST_BOX.get()).build(null));
}
