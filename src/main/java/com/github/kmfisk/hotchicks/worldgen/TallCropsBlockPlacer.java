package com.github.kmfisk.hotchicks.worldgen;

import com.github.kmfisk.hotchicks.block.DoubleCropBlock;
import com.github.kmfisk.hotchicks.block.TripleCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.blockplacers.BlockPlacerType;
import net.minecraft.world.level.levelgen.feature.blockplacers.ColumnPlacer;

import java.util.Random;

public class TallCropsBlockPlacer extends ColumnPlacer {
    public TallCropsBlockPlacer() {
        super(0, 0);
    }

    protected BlockPlacerType<?> type() {
        return BlockPlacerType.COLUMN_PLACER;
    }

    public void place(LevelAccessor world, BlockPos pos, BlockState state, Random random) {
        if (state.getBlock() instanceof TripleCropBlock) {
            TripleCropBlock tripleCropBlock = (TripleCropBlock) state.getBlock();
            world.setBlock(pos, state.setValue(tripleCropBlock.getAgeProperty(), tripleCropBlock.getMaxAge()).setValue(TripleCropBlock.SEGMENT, TripleCropBlock.TripleBlockSegment.BOTTOM), 2);
            world.setBlock(pos.above(), state.setValue(tripleCropBlock.getAgeProperty(), tripleCropBlock.getMaxAge()).setValue(TripleCropBlock.SEGMENT, TripleCropBlock.TripleBlockSegment.MIDDLE), 2);
            world.setBlock(pos.above().above(), state.setValue(tripleCropBlock.getAgeProperty(), tripleCropBlock.getMaxAge()).setValue(TripleCropBlock.SEGMENT, TripleCropBlock.TripleBlockSegment.TOP), 2);

        } else if (state.getBlock() instanceof DoubleCropBlock) {
            DoubleCropBlock doubleCropBlock = (DoubleCropBlock) state.getBlock();
            world.setBlock(pos, state.setValue(doubleCropBlock.getAgeProperty(), doubleCropBlock.getMaxAge()).setValue(DoubleCropBlock.SEGMENT, DoubleBlockHalf.LOWER), 2);
            world.setBlock(pos.above(), state.setValue(doubleCropBlock.getAgeProperty(), doubleCropBlock.getMaxAge()).setValue(DoubleCropBlock.SEGMENT, DoubleBlockHalf.UPPER), 2);

        }
    }
}
