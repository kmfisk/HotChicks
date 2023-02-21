package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.block.TallCropsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;

import java.util.Random;

public class TallCropsBlockPlacer extends ColumnBlockPlacer {
    public TallCropsBlockPlacer() {
        super(0, 0);
    }

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.COLUMN_PLACER;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        IntegerProperty height = ((TallCropsBlock) state.getBlock()).getHeightProperty();
        world.setBlock(pos, state.setValue(TallCropsBlock.AGE, 5).setValue(height, 0), 2);
        world.setBlock(pos.above(), state.setValue(TallCropsBlock.AGE, 5).setValue(height, 1), 2);
        if (((TallCropsBlock) state.getBlock()).hasThirdBlock())
            world.setBlock(pos.above().above(), state.setValue(TallCropsBlock.AGE, 5).setValue(height, 2), 2);
    }
}
