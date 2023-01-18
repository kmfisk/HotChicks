package com.github.kmfisk.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.Random;

public class LowLightPlacer extends BlockPlacer {
    public static final Codec<LowLightPlacer> CODEC;
    public static final LowLightPlacer INSTANCE = new LowLightPlacer();

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.SIMPLE_BLOCK_PLACER;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (world.getRawBrightness(pos, 0) < 13)
            world.setBlock(pos, state, 2);
    }

    static {
        CODEC = Codec.unit(() -> INSTANCE);
    }
}
