package com.github.kmfisk.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class HotWaterCropFeature extends Feature<BlockClusterFeatureConfig> {
    public HotWaterCropFeature(Codec<BlockClusterFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(ISeedReader level, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockClusterFeatureConfig config) {
        int i = random.nextInt(8) - random.nextInt(8);
        int j = random.nextInt(8) - random.nextInt(8);
        int k = level.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
        int l = 0;
        BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
        if (level.getBlockState(blockpos).is(Blocks.WATER)) {
            BlockState blockstate = config.stateProvider.getState(random, pos);
            if (blockstate.canSurvive(level, blockpos)) {
                BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
                for(int m = 0; m < config.tries; ++m) {
                    blockpos$mutable.setWithOffset(blockpos, random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));
                    BlockPos blockpos1 = blockpos$mutable.below();
                    BlockState blockstate1 = level.getBlockState(blockpos1);
                    if (level.getBlockState(blockpos$mutable).is(Blocks.WATER) && blockstate.canSurvive(level, blockpos$mutable)
                            && (config.whitelist.isEmpty() || config.whitelist.contains(blockstate1.getBlock()))
                            && !config.blacklist.contains(blockstate1)
                            && (config.needWater ? level.getFluidState(blockpos$mutable.above()).is(FluidTags.WATER) :
                            level.getFluidState(blockpos$mutable.above()).isEmpty())) {
                        config.blockPlacer.place(level, blockpos$mutable, blockstate, random);
                        ++l;
                    }
                }
            }
        }

        return l > 0;
    }
}
