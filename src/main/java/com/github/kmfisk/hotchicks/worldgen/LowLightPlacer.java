package com.github.kmfisk.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.blockplacers.BlockPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.BlockPlacerType;

import java.util.Random;

public class LowLightPlacer extends BlockPlacer {
    public static final Codec<LowLightPlacer> CODEC;
    public static final LowLightPlacer INSTANCE = new LowLightPlacer();

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.SIMPLE_BLOCK_PLACER;
    }

    public void place(LevelAccessor world, BlockPos pos, BlockState state, Random random) {
        if (world.getRawBrightness(pos, 0) < 13)
            world.setBlock(pos, state, 2);
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}
