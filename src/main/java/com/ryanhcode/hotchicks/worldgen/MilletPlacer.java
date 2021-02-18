package com.ryanhcode.hotchicks.worldgen;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryanhcode.hotchicks.block.crop.CornBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;

import java.util.Random;

public class MilletPlacer extends ColumnBlockPlacer {


    public MilletPlacer(int minSize, int extraSize) {
        super(minSize, extraSize);
    }

    protected BlockPlacerType<?> getBlockPlacerType() {
        return BlockPlacerType.COLUMN;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        BlockPos.Mutable blockpos$mutable = pos.toMutable();


        world.setBlockState(blockpos$mutable, state.with(CornBlock.AGE, 5).with(CornBlock.TYPE, 0), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlockState(blockpos$mutable, state.with(CornBlock.AGE, 5).with(CornBlock.TYPE, 1), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlockState(blockpos$mutable, state.with(CornBlock.AGE, 5).with(CornBlock.TYPE, 2), 2);
        blockpos$mutable.move(Direction.UP);

    }
}
