package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.block.CropVineBlock;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.Random;

public class HotVinesFeature /*extends Feature<RandomPatchConfiguration>*/ {
    /*public HotVinesFeature(Codec<RandomPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        Random random = context.random();
        context.config();
        BlockPos pos1;
        if (context.config().project) pos1 = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
        else pos1 = pos;

        int i = 0;
        BlockPos.MutableBlockPos blockPos = pos1.mutable();

        for (Direction direction : Direction.values()) {
            for (int j = 0; j < context.config().tries(); ++j) {
                blockPos.set(pos1);
                blockPos.move(random.nextInt(context.config().xzSpread() + 1) - random.nextInt(context.config().xzSpread() + 1), random.nextInt(context.config().ySpread() + 1) - random.nextInt(context.config().ySpread() + 1), random.nextInt(context.config().xzSpread() + 1) - random.nextInt(context.config().xzSpread() + 1));

                BlockState blockState = level.getBlockState(blockPos);
                BlockState facingState = level.getBlockState(blockPos.relative(direction));

                if ((level.isEmptyBlock(blockPos) || context.config().canReplace && blockState.getMaterial().isReplaceable()) && (context.config().whitelist.isEmpty() || context.config().whitelist.contains(facingState.getBlock())) && !context.config().blacklist.contains(facingState)) {
                    if (direction != Direction.DOWN *//*&& CropVineBlock.isAcceptableNeighbour(level, blockPos, direction.getOpposite())*//*) {
                        BlockState vine = context.config().stateProvider.getState(random, pos).setValue(CropVineBlock.getPropertyForFace(direction), true);
                        if (vine.canSurvive(level, blockPos)) {
                            context.config().blockPlacer.place(level, blockPos, vine, random);
//                            System.out.println(vine.getBlock() + direction.getName() + blockPos + facingState.getBlock());
                            ++i;
                        }
                    }
                }
            }
        }

        return i > 0;
    }*/
}
