package com.github.kmfisk.hotchicks.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class HotPlacements {
    public static final Holder<PlacedFeature> RED_APPLE = PlacementUtils.register("red_apple", HotFeatures.RED_APPLE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1)));
    public static final Holder<PlacedFeature> PEACH = PlacementUtils.register("peach", HotFeatures.PEACH, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1)));
    public static final Holder<PlacedFeature> MANGO = PlacementUtils.register("mango", HotFeatures.MANGO, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1)));
    public static final Holder<PlacedFeature> POMEGRANATE = PlacementUtils.register("pomegranate", HotFeatures.POMEGRANATE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1)));
    public static final Holder<PlacedFeature> FIG = PlacementUtils.register("fig", HotFeatures.FIG, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1)));
    public static final Holder<PlacedFeature> CITRON = PlacementUtils.register("citron", HotFeatures.CITRON, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1)));
    public static final Holder<PlacedFeature> POMELO = PlacementUtils.register("pomelo", HotFeatures.POMELO, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1)));
    public static final Holder<PlacedFeature> MANDARIN = PlacementUtils.register("mandarin", HotFeatures.MANDARIN, VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1)));
    public static final Holder<PlacedFeature> PAPEDA = PlacementUtils.register("papeda", HotFeatures.PAPEDA, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1)));
}
