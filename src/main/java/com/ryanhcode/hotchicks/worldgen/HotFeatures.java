package com.ryanhcode.hotchicks.worldgen;

import com.google.common.collect.ImmutableSet;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.HotBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = HotChickens.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HotFeatures {
    public static ConfiguredFeature<?, ?> CORN;
    public static ConfiguredFeature<?, ?> MILLET;
    public static ConfiguredFeature<?, ?> BLUEBERRY_BUSH;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> RED_APPLE;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> PEACH;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MANGO;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> POMEGRANATE;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> FIG;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> CITRON;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> POMELO;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MANDARIN;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> PAPEDA;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> ORANGE;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LEMON;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> GRAPEFRUIT;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> LIME;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> YUZU;

    public static void registerFeatures() {
        CORN = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HotBlocks.CORN_CROP.get().defaultBlockState()),
                        new MilletPlacer(0, 0))).tries(30).xspread(4).yspread(0).zspread(4).noProjection().needWater().build()
        ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

        MILLET = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HotBlocks.MILLET_CROP.get().defaultBlockState()),
                        new MilletPlacer(0, 0))).tries(30).xspread(10).yspread(0).zspread(10).noProjection().needWater().build()
        ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

        BLUEBERRY_BUSH = Feature.RANDOM_PATCH.configured(
                (new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HotBlocks.BLUEBERRY_BUSH.get().defaultBlockState()),
                        new LowLightPlacer())).tries(200).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()
        );

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "corn_patches"), CORN);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "millet_patches"), MILLET);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "blueberry_patches"), BLUEBERRY_BUSH);

        RED_APPLE = configureFruitTree(Blocks.OAK_LOG, HotBlocks.RED_APPLE_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        PEACH = configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.PEACH_LEAVES.get(), Blocks.DARK_OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        MANGO = configureFruitTree(Blocks.ACACIA_LOG, HotBlocks.MANGO_LEAVES.get(), Blocks.ACACIA_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        POMEGRANATE = configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.POMEGRANATE_LEAVES.get(), Blocks.DARK_OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        FIG = configureFruitTree(Blocks.OAK_LOG, HotBlocks.FIG_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        CITRON = configureFruitTree(Blocks.OAK_LOG, HotBlocks.CITRON_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        POMELO = configureFruitTree(Blocks.OAK_LOG, HotBlocks.POMELO_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        MANDARIN = configureFruitTree(Blocks.OAK_LOG, HotBlocks.MANDARIN_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        PAPEDA = configureFruitTree(Blocks.OAK_LOG, HotBlocks.PAPEDA_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        ORANGE = configureFruitTree(Blocks.OAK_LOG, HotBlocks.ORANGE_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        LEMON = configureFruitTree(Blocks.OAK_LOG, HotBlocks.LEMON_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        GRAPEFRUIT = configureFruitTree(Blocks.OAK_LOG, HotBlocks.GRAPEFRUIT_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        LIME = configureFruitTree(Blocks.OAK_LOG, HotBlocks.LIME_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);
        YUZU = configureFruitTree(Blocks.OAK_LOG, HotBlocks.YUZU_LEAVES.get(), Blocks.OAK_LEAVES,
                new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                new ForkyTrunkPlacer(3, 2, 2),
                1, 0, 2);

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "red_apple_trees"), RED_APPLE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "peach_trees"), PEACH);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "mango_trees"), MANGO);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "pomegranate_trees"), POMEGRANATE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "fig_trees"), FIG);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "citron_trees"), CITRON);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "pomelo_trees"), POMELO);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "mandarin_trees"), MANDARIN);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "papeda_trees"), PAPEDA);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "orange_trees"), ORANGE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "lemon_trees"), LEMON);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "grapefruit_trees"), GRAPEFRUIT);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "lime_trees"), LIME);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MOD_ID, "yuzu_trees"), YUZU);
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> configureFruitTree(Block logs, Block fruitLeaves, Block baseLeaves, FoliagePlacer foliagePlacer, AbstractTrunkPlacer trunkPlacer, int limit, int lowerSize, int upperSize) {
        return Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(logs.defaultBlockState()),
                new WeightedBlockStateProvider().add(fruitLeaves.defaultBlockState(), 2).add(baseLeaves.defaultBlockState(), 5),
                foliagePlacer, trunkPlacer,
                new TwoLayerFeature(limit, lowerSize, upperSize)))
                .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()
        );
    }

    @SubscribeEvent
    public void biomeLoad(BiomeLoadingEvent event) {
        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, event.getName()));
        if (biomeTypes.contains(BiomeDictionary.Type.OVERWORLD)) {
            if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.COLD) && !biomeTypes.contains(BiomeDictionary.Type.DENSE) && !biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RED_APPLE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.DRY))
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN);

            if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.LAKES).add(() -> MILLET);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CITRON);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANGO);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMELO);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PAPEDA);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT))
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);

            if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY))
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
        }
    }
}
