package com.github.kmfisk.hotchicks.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.Map;

public class WaterBottleBlock extends HorizontalBlock {
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(5.0D, 2.0D, 10.5D, 11.0D, 15.5D, 16.0D),
                    Direction.SOUTH, Block.box(5.0D, 2.0D, 0.0D, 11.0D, 15.5D, 5.5D),
                    Direction.WEST, Block.box(10.5D, 2.0D, 5.0D, 16.0D, 15.5D, 11.0D),
                    Direction.EAST, Block.box(0.0D, 2.0D, 5.0D, 5.5D, 15.5D, 11.0D)
            ));

    public WaterBottleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pPos, ISelectionContext pContext) {
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos oppositePos = pos.relative(direction.getOpposite());
        BlockState oppositeState = level.getBlockState(oppositePos);
        return oppositeState.isFaceSturdy(level, oppositePos, direction);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockState = defaultBlockState();
        IWorldReader level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction[] nearestLookingDirections = context.getNearestLookingDirections();

        for(Direction direction : nearestLookingDirections) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockState = blockState.setValue(FACING, direction1);
                if (blockState.canSurvive(level, clickedPos)) return blockState;
            }
        }

        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld level, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
