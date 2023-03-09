package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.*;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class HotFeatures {
    public static final ConfiguredFeature<?, ?> CORN = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.CORN_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> MILLET = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.MILLET_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).tries(64).xspread(4).yspread(0).zspread(4).noProjection().needWater().build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);
    public static final ConfiguredFeature<?, ?> WILD_RICE = HotFeature.WATER_CROPS.get().configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.RICE_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).blacklist(ImmutableSet.of(Blocks.WATER.defaultBlockState())).tries(20).xspread(4).yspread(0).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE);
//    public static final ConfiguredFeature<?, ?> BANANA_TREE = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.BANANA_TREE.get().defaultBlockState().setValue(BananaTreeBlock.AGE, 1)), new DoublePlantBlockPlacer()).tries(64).noProjection().build())
//            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE);

    public static final ConfiguredFeature<?, ?> BLUEBERRY_BUSH = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BerryBushBlock.AGE, 3)), LowLightPlacer.INSTANCE).tries(64).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE);
    public static final ConfiguredFeature<?, ?> PEPPER_BUSH = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.PEPPER_BUSH.get().defaultBlockState().setValue(PepperBerryBushBlock.AGE, 3)), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE);
    public static final ConfiguredFeature<?, ?> STRAWBERRY_BUSH = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.STRAWBERRY_BUSH.get().defaultBlockState().setValue(BerryBushBlock.AGE, 3)), LowLightPlacer.INSTANCE).tries(64).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE);

    public static final ConfiguredFeature<?, ?> WILD_OATS = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_OATS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_GARLIC = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_GARLIC.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_KALE = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_KALE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_TOMATO = Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_TOMATO.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);

    public static final ConfiguredFeature<?, ?> WILD_GRAPE = HotFeature.CROP_VINES.get().configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_GRAPE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LEAVES.getBlock(), Blocks.BIRCH_LEAVES.getBlock())).noProjection().tries(64).build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(2);
    public static final ConfiguredFeature<?, ?> WILD_KIWI = HotFeature.CROP_VINES.get().configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_KIWI.get().defaultBlockState()), LowLightPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LOG.getBlock(), Blocks.JUNGLE_LOG.getBlock(), Blocks.DARK_OAK_LOG.getBlock())).noProjection().tries(64).build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(5);
    public static final ConfiguredFeature<?, ?> WILD_PEA = HotFeature.CROP_VINES.get().configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HotBlocks.WILD_PEA.get().defaultBlockState()), LowLightPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LOG.getBlock(), Blocks.BIRCH_LOG.getBlock())).noProjection().tries(64).build())
            .count(FeatureSpread.of(-3, 4)).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(5);

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> RED_APPLE = configureFruitTree(Blocks.OAK_LOG, HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PEACH = configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.PEACH_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), 1, 0, 1, OptionalInt.empty());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MANGO = configureFruitTree(Blocks.ACACIA_LOG, HotBlocks.MANGO_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(8, 2, 6), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> POMEGRANATE = configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.POMEGRANATE_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(5), 4), new FancyTrunkPlacer(3, 2, 0), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> FIG = configureFruitTree(Blocks.OAK_LOG, HotBlocks.FIG_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)), new StraightTrunkPlacer(3, 2, 0), 1, 0, 1, OptionalInt.empty());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CITRON = configureFruitTree(Blocks.OAK_LOG, HotBlocks.CITRON_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> POMELO = configureFruitTree(Blocks.OAK_LOG, HotBlocks.POMELO_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new StraightTrunkPlacer(5, 1, 9), 1, 1, 2, OptionalInt.empty());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MANDARIN = configureFruitTree(Blocks.OAK_LOG, HotBlocks.MANDARIN_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)), new ForkyTrunkPlacer(3, 2, 2), 1, 0, 2, OptionalInt.empty());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PAPEDA = configureFruitTree(Blocks.OAK_LOG, HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ORANGE = configureFruitTree(Blocks.OAK_LOG, HotBlocks.ORANGE_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LEMON = configureFruitTree(Blocks.OAK_LOG, HotBlocks.LEMON_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GRAPEFRUIT = configureFruitTree(Blocks.OAK_LOG, HotBlocks.GRAPEFRUIT_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new JungleFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 2), new StraightTrunkPlacer(5, 1, 9), 1, 1, 2, OptionalInt.empty());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LIME = configureFruitTree(Blocks.OAK_LOG, HotBlocks.LIME_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> YUZU = configureFruitTree(Blocks.OAK_LOG, HotBlocks.YUZU_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4));

    public static void registerFeatures() {
        register("corn_patches", CORN);
        register("millet_patches", MILLET);
        register("rice_patches", WILD_RICE);
//        register("banana_tree", BANANA_TREE);

        register("blueberry_patches", BLUEBERRY_BUSH);
        register("pepper_patches", PEPPER_BUSH);
        register("strawberry_patches", STRAWBERRY_BUSH);

        register("oats_patches", WILD_OATS);
        register("garlic_patches", WILD_GARLIC);
        register("kale_patches", WILD_KALE);
        register("tomato_patches", WILD_TOMATO);

        register("grape_patches", WILD_GRAPE);
        register("kiwi_patches", WILD_KIWI);
        register("pea_patches", WILD_PEA);

        register("red_apple_trees", RED_APPLE);
        register("peach_trees", PEACH);
        register("mango_trees", MANGO);
        register("pomegranate_trees", POMEGRANATE);
        register("fig_trees", FIG);
        register("citron_trees", CITRON);
        register("pomelo_trees", POMELO);
        register("mandarin_trees", MANDARIN);
        register("papeda_trees", PAPEDA);
        register("orange_trees", ORANGE);
        register("lemon_trees", LEMON);
        register("grapefruit_trees", GRAPEFRUIT);
        register("lime_trees", LIME);
        register("yuzu_trees", YUZU);
    }

    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> configureFruitTree(Block logs, Block fruitLeaves, Block baseLeaves, FoliagePlacer foliagePlacer, AbstractTrunkPlacer trunkPlacer, int limit, int lowerSize, int upperSize, OptionalInt minClippedHeight) {
        return Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(logs.defaultBlockState()),
                new WeightedBlockStateProvider().add(fruitLeaves.defaultBlockState(), 2).add(baseLeaves.defaultBlockState(), 5),
                foliagePlacer, trunkPlacer,
                new TwoLayerFeature(limit, lowerSize, upperSize, minClippedHeight)))
                .ignoreVines().build()
        );
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChicks.MOD_ID, key), configuredFeature);
    }
}
