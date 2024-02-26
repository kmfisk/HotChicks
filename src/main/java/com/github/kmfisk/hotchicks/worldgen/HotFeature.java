package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotFeature {
    public static final DeferredRegister<Feature<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.FEATURES, HotChicks.MOD_ID);
//    public static final RegistryObject<Feature<RandomPatchConfiguration>> CROP_VINES = REGISTRAR.register("crop_vines", () -> new HotVinesFeature(RandomPatchConfiguration.CODEC));
//    public static final RegistryObject<Feature<RandomPatchConfiguration>> WATER_CROPS = REGISTRAR.register("water_crops", () -> new HotWaterCropFeature(RandomPatchConfiguration.CODEC));
}
