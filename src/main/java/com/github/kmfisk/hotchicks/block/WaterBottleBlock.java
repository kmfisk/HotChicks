package com.github.kmfisk.hotchicks.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WaterBottleBlock extends HorizontalDirectionalBlock {
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
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pPos, CollisionContext pContext) {
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos oppositePos = pos.relative(direction.getOpposite());
        BlockState oppositeState = level.getBlockState(oppositePos);
        return oppositeState.isFaceSturdy(level, oppositePos, direction);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = defaultBlockState();
        LevelReader level = context.getLevel();
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
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
