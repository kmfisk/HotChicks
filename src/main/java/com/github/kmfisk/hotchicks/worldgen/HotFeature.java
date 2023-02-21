package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotFeature {
    public static final DeferredRegister<Feature<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.FEATURES, HotChicks.MOD_ID);
    public static final RegistryObject<Feature<BlockClusterFeatureConfig>> CROP_VINES = REGISTRAR.register("crop_vines", () -> new HotVinesFeature(BlockClusterFeatureConfig.CODEC));
}
