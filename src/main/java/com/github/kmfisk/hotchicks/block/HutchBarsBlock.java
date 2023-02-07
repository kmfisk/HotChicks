package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class HutchBarsBlock extends PaneBlock {
    public HutchBarsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader iblockreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.south();
        BlockPos blockpos3 = blockpos.west();
        BlockPos blockpos4 = blockpos.east();
        BlockState blockstate = iblockreader.getBlockState(blockpos1);
        BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
        BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
        BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
        return defaultBlockState().setValue(NORTH, canAttachTo(blockstate, blockstate.isFaceSturdy(iblockreader, blockpos1, Direction.SOUTH), Direction.SOUTH)).setValue(SOUTH, canAttachTo(blockstate1, blockstate1.isFaceSturdy(iblockreader, blockpos2, Direction.NORTH), Direction.NORTH)).setValue(WEST, canAttachTo(blockstate2, blockstate2.isFaceSturdy(iblockreader, blockpos3, Direction.EAST), Direction.EAST)).setValue(EAST, canAttachTo(blockstate3, blockstate3.isFaceSturdy(iblockreader, blockpos4, Direction.WEST), Direction.WEST)).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED))
            level.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return facing.getAxis().isHorizontal() ? state.setValue(PROPERTY_BY_DIRECTION.get(facing), canAttachTo(facingState, facingState.isFaceSturdy(level, facingPos, facing.getOpposite()), facing.getOpposite())) : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public boolean canAttachTo(BlockState state, boolean solidSide, Direction direction) {
        Block block = state.getBlock();
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
        return !isExceptionForConnection(block) && solidSide || block instanceof PaneBlock || block instanceof FenceBlock || flag1 || block.is(BlockTags.WALLS);
    }
}
