package com.github.kmfisk.hotchicks.worldgen;


import com.github.kmfisk.hotchicks.block.TallCropsBlock;
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


        world.setBlock(blockpos$mutable, state.setValue(TallCropsBlock.AGE, 5).setValue(TallCropsBlock.HEIGHT, 0), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlock(blockpos$mutable, state.setValue(TallCropsBlock.AGE, 5).setValue(TallCropsBlock.HEIGHT, 1), 2);
        blockpos$mutable.move(Direction.UP);

        world.setBlock(blockpos$mutable, state.setValue(TallCropsBlock.AGE, 5).setValue(TallCropsBlock.HEIGHT, 2), 2);
        blockpos$mutable.move(Direction.UP);

    }
}
