package com.ryanhcode.hotchicks.worldgen;


import com.ryanhcode.hotchicks.block.crop.CornBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;

import java.util.Random;

public class MilletPlacer extends ColumnBlockPlacer {


    public MilletPlacer(int minSize, int extraSize) {
        super(minSize, extraSize);
    }

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.COLUMN_PLACER;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        BlockPos.Mutable blockpos$mutable = pos.mutable();


        world.setBlock(blockpos$mutable, state.setValue(CornBlock.AGE, 5).setValue(CornBlock.TYPE, 0), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlock(blockpos$mutable, state.setValue(CornBlock.AGE, 5).setValue(CornBlock.TYPE, 1), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlock(blockpos$mutable, state.setValue(CornBlock.AGE, 5).setValue(CornBlock.TYPE, 2), 2);
        blockpos$mutable.move(Direction.UP);

    }
}
