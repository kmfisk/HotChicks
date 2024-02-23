package com.github.kmfisk.hotchicks.block.trees;

import com.github.kmfisk.hotchicks.worldgen.HotFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class PapedaTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean bees) {
        return HotFeatures.PAPEDA;
    }
}
