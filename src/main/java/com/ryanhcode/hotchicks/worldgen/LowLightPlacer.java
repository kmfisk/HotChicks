package com.ryanhcode.hotchicks.worldgen;

import com.mojang.serialization.Codec;
import com.ryanhcode.hotchicks.block.crop.BerryBush;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.Random;

public class LowLightPlacer extends BlockPlacer {


    public static final Codec<net.minecraft.world.gen.blockplacer.SimpleBlockPlacer> CODEC;
    public static final net.minecraft.world.gen.blockplacer.SimpleBlockPlacer PLACER = new net.minecraft.world.gen.blockplacer.SimpleBlockPlacer();

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.SIMPLE_BLOCK_PLACER;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        world.setBlock(pos, state.setValue(BerryBush.AGE, 3), 2);
    }

    static {
        CODEC = Codec.unit(() -> {
            return PLACER;
        });
    }
}
