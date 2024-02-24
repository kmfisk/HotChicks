package com.github.kmfisk.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.Random;

public class HotWaterCropFeature extends Feature<RandomPatchConfiguration> {
    public HotWaterCropFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        Random random = context.random();
        context.config();
        int i = random.nextInt(8) - random.nextInt(8);
        int j = random.nextInt(8) - random.nextInt(8);
        int k = level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
        int l = 0;
        BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
        if (level.getBlockState(blockpos).is(Blocks.WATER)) {
            BlockState blockstate = context.config().stateProvider.getState(random, pos);
            if (blockstate.canSurvive(level, blockpos)) {
                BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
                for (int m = 0; m < context.config().tries; ++m) {
                    blockpos$mutable.setWithOffset(blockpos, random.nextInt(context.config().xspread + 1) - random.nextInt(context.config().xspread + 1), random.nextInt(context.config().yspread + 1) - random.nextInt(context.config().yspread + 1), random.nextInt(context.config().zspread + 1) - random.nextInt(context.config().zspread + 1));
                    BlockPos blockpos1 = blockpos$mutable.below();
                    BlockState blockstate1 = level.getBlockState(blockpos1);
                    if (level.getBlockState(blockpos$mutable).is(Blocks.WATER) && blockstate.canSurvive(level, blockpos$mutable)
                            && (context.config().whitelist.isEmpty() || context.config().whitelist.contains(blockstate1.getBlock()))
                            && !context.config().blacklist.contains(blockstate1)
                            && (context.config().needWater ? level.getFluidState(blockpos$mutable.above()).is(FluidTags.WATER) :
                            level.getFluidState(blockpos$mutable.above()).isEmpty())) {
                        context.config().blockPlacer.place(level, blockpos$mutable, blockstate, random);
                        ++l;
                    }
                }
            }
        }

        return l > 0;
    }
}
