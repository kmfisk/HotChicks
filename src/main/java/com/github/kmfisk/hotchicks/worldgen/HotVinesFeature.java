package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.block.CropVineBlock;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Random;

public class HotVinesFeature extends Feature<RandomPatchConfiguration> {
    public HotVinesFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, Random random, BlockPos pos, RandomPatchConfiguration config) {
        BlockPos pos1;
        if (config.project) pos1 = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
        else pos1 = pos;

        int i = 0;
        BlockPos.MutableBlockPos blockPos = pos1.mutable();

        for (Direction direction : Direction.values()) {
            for (int j = 0; j < config.tries; ++j) {
                blockPos.set(pos1);
                blockPos.move(random.nextInt(config.xspread + 1) - random.nextInt(config.xspread + 1), random.nextInt(config.yspread + 1) - random.nextInt(config.yspread + 1), random.nextInt(config.zspread + 1) - random.nextInt(config.zspread + 1));

                BlockState blockState = level.getBlockState(blockPos);
                BlockState facingState = level.getBlockState(blockPos.relative(direction));

                if ((level.isEmptyBlock(blockPos) || config.canReplace && blockState.getMaterial().isReplaceable()) && (config.whitelist.isEmpty() || config.whitelist.contains(facingState.getBlock())) && !config.blacklist.contains(facingState)) {
                    if (direction != Direction.DOWN /*&& CropVineBlock.isAcceptableNeighbour(level, blockPos, direction.getOpposite())*/) {
                        BlockState vine = config.stateProvider.getState(random, pos).setValue(CropVineBlock.getPropertyForFace(direction), true);
                        if (vine.canSurvive(level, blockPos)) {
                            config.blockPlacer.place(level, blockPos, vine, random);
//                            System.out.println(vine.getBlock() + direction.getName() + blockPos + facingState.getBlock());
                            ++i;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
