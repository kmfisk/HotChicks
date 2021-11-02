package com.ryanhcode.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import com.ryanhcode.hotchicks.block.crop.BerryBush;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.feature.Features;

import java.util.Random;

public class LowLightPlacer extends BlockPlacer {



    public static final Codec<net.minecraft.world.gen.blockplacer.SimpleBlockPlacer> CODEC;
    public static final net.minecraft.world.gen.blockplacer.SimpleBlockPlacer PLACER = new net.minecraft.world.gen.blockplacer.SimpleBlockPlacer();

    protected BlockPlacerType<?> getBlockPlacerType() {
        return BlockPlacerType.SIMPLE_BLOCK;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        world.setBlockState(pos, state.with(BerryBush.AGE, 3), 2);
    }

    static {
        CODEC = Codec.unit(() -> {
            return PLACER;
        });
    }
}
