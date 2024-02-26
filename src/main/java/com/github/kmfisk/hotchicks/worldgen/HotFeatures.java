package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.BerryBushBlock;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.PepperBerryBushBlock;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.OptionalInt;

public class HotFeatures {
    /*public static final Holder<PlacedFeature> WILD_RICE_PF = PlacementUtils.register("wild_rice", HotFeatures.WILD_RICE_CF, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final ConfiguredFeature<?, ?> CORN = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.CORN_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> MILLET = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.MILLET_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).tries(64).xspread(4).yspread(0).zspread(4).noProjection().needWater().build())
            .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(20);
    public static final Holder<ConfiguredFeature<?, ?>> WILD_RICE_CF = FeatureUtils.register("wild_rice", HotFeature.WATER_CROPS.get(), new RandomPatchConfiguration(20, 4, 0, new SimpleStateProvider(HotBlocks.RICE_CROP.get().defaultBlockState()), new TallCropsBlockPlacer()).blacklist(ImmutableSet.of(Blocks.WATER.defaultBlockState())).tries(20).xspread(4).yspread(0).zspread(4).noProjection().build()
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> BANANA_TREE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.BANANA_TREE.get().defaultBlockState()), new TallCropsBlockPlacer()).tries(64).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE);

    public static final ConfiguredFeature<?, ?> BLUEBERRY_BUSH = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BerryBushBlock.AGE, 3)), LowLightPlacer.INSTANCE).tries(64).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE);
    public static final ConfiguredFeature<?, ?> PEPPER_BUSH = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.PEPPER_BUSH.get().defaultBlockState().setValue(PepperBerryBushBlock.AGE, 3)), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE);
    public static final ConfiguredFeature<?, ?> STRAWBERRY_BUSH = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.STRAWBERRY_BUSH.get().defaultBlockState().setValue(BerryBushBlock.AGE, 3)), LowLightPlacer.INSTANCE).tries(64).xspread(4).zspread(4).noProjection().build())
            .decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE);

    public static final ConfiguredFeature<?, ?> WILD_OATS = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_OATS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_GARLIC = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_GARLIC.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_KALE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_KALE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);
    public static final ConfiguredFeature<?, ?> WILD_TOMATO = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_TOMATO.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).tries(20).xspread(4).zspread(4).noProjection().build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(1);

    public static final ConfiguredFeature<?, ?> WILD_GRAPE = HotFeature.CROP_VINES.get().configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_GRAPE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LEAVES.defaultBlockState().getBlock(), Blocks.BIRCH_LEAVES.defaultBlockState().getBlock())).noProjection().tries(64).build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(2);
    public static final ConfiguredFeature<?, ?> WILD_KIWI = HotFeature.CROP_VINES.get().configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_KIWI.get().defaultBlockState()), LowLightPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LOG.defaultBlockState().getBlock(), Blocks.JUNGLE_LOG.defaultBlockState().getBlock(), Blocks.DARK_OAK_LOG.defaultBlockState().getBlock())).noProjection().tries(64).build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(5);
    public static final ConfiguredFeature<?, ?> WILD_PEA = HotFeature.CROP_VINES.get().configured(new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(HotBlocks.WILD_PEA.get().defaultBlockState()), LowLightPlacer.INSTANCE).xspread(2).zspread(2).whitelist(ImmutableSet.of(Blocks.OAK_LOG.defaultBlockState().getBlock(), Blocks.BIRCH_LOG.defaultBlockState().getBlock())).noProjection().tries(64).build())
            .count(UniformInt.of(-3, 4)).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(5);*/

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RED_APPLE = FeatureUtils.register("red_apple", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PEACH = FeatureUtils.register("peach", Feature.TREE, configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.PEACH_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new StraightTrunkPlacer(4, 2, 0), 1, 0, 1, OptionalInt.empty()).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MANGO = FeatureUtils.register("mango", Feature.TREE, configureFruitTree(Blocks.ACACIA_LOG, HotBlocks.MANGO_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new FancyTrunkPlacer(8, 2, 6), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> POMEGRANATE = FeatureUtils.register("pomegranate", Feature.TREE, configureFruitTree(Blocks.DARK_OAK_LOG, HotBlocks.POMEGRANATE_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(5), 4), new FancyTrunkPlacer(3, 2, 0), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FIG = FeatureUtils.register("fig", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.FIG_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), new StraightTrunkPlacer(3, 2, 0), 1, 0, 1, OptionalInt.empty()).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CITRON = FeatureUtils.register("citron", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.CITRON_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> POMELO = FeatureUtils.register("pomelo", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.POMELO_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new StraightTrunkPlacer(5, 1, 9), 1, 1, 2, OptionalInt.empty()).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MANDARIN = FeatureUtils.register("mandarin", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.MANDARIN_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), new ForkingTrunkPlacer(3, 2, 2), 1, 0, 2, OptionalInt.empty()).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PAPEDA = FeatureUtils.register("papeda", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ORANGE = FeatureUtils.register("orange", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.ORANGE_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LEMON = FeatureUtils.register("lemon", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.LEMON_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> GRAPEFRUIT = FeatureUtils.register("grapefruit", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.GRAPEFRUIT_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new StraightTrunkPlacer(5, 1, 9), 1, 1, 2, OptionalInt.empty()).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LIME = FeatureUtils.register("lime", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.LIME_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new FancyTrunkPlacer(3, 11, 0), 0, 0, 0, OptionalInt.of(4)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YUZU = FeatureUtils.register("yuzu", Feature.TREE, configureFruitTree(Blocks.OAK_LOG, HotBlocks.YUZU_LEAVES.get(), HotBlocks.CITRUS_LEAVES.get(), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)), new StraightTrunkPlacer(5, 2, 1), 0, 0, 0, OptionalInt.of(4)).build());

    /*public static void registerFeatures() {
        register("corn_patches", CORN);
        register("millet_patches", MILLET);
        register("rice_patches", WILD_RICE_CF, WILD_RICE_PF);
        register("banana_tree", BANANA_TREE);

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
    }*/

    private static TreeConfiguration.TreeConfigurationBuilder configureFruitTree(Block logs, Block fruitLeaves, Block baseLeaves, FoliagePlacer foliagePlacer, TrunkPlacer trunkPlacer, int limit, int lowerSize, int upperSize, OptionalInt minClippedHeight) {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(logs.defaultBlockState()),
                trunkPlacer,
                new WeightedStateProvider(weightedBlockStateBuilder().add(fruitLeaves.defaultBlockState(), 2).add(baseLeaves.defaultBlockState(), 5)),
                foliagePlacer,
                new TwoLayersFeatureSize(limit, lowerSize, upperSize, minClippedHeight)))
                .ignoreVines();
    }

    static SimpleWeightedRandomList.Builder<BlockState> weightedBlockStateBuilder() {
        return SimpleWeightedRandomList.builder();
    }

    /*private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature, Holder<PlacedFeature> placedFeatureHolder) {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(HotChicks.MOD_ID, key + "_pf"), placedFeatureHolder.value());
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChicks.MOD_ID, key), configuredFeature);
    }*/
}
