package com.github.kmfisk.hotchicks.block.trees;

import com.github.kmfisk.hotchicks.worldgen.HotFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class MandarinTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean bees) {
        return HotFeatures.MANDARIN;
    }
}
