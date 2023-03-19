package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class HutchGateBlock extends FenceGateBlock {
    protected static final VoxelShape NORTH_SHAPE_OPEN = VoxelShapes.or(Block.box(0.0D, 0.0D, 7.0D, 2.0D, 16.0D, 16.0D), Block.box(14.0D, 0.0D, 7.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape SOUTH_SHAPE_OPEN = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 9.0D), Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 9.0D));
    protected static final VoxelShape EAST_SHAPE_OPEN = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 9.0D, 16.0D, 2.0D), Block.box(0.0D, 0.0D, 14.0D, 9.0D, 16.0D, 16.0D));
    protected static final VoxelShape WEST_SHAPE_OPEN = VoxelShapes.or(Block.box(7.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D), Block.box(7.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape Z_OCCLUSION_SHAPE = VoxelShapes.or(Block.box(0.0D, 0.0D, 7.0D, 2.0D, 16.0D, 9.0D), Block.box(14.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D));
    protected static final VoxelShape X_OCCLUSION_SHAPE = VoxelShapes.or(Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 2.0D), Block.box(7.0D, 0.0D, 14.0D, 9.0D, 16.0D, 16.0D));

    public HutchGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult hit) {
        level.setBlock(pos, state.cycle(OPEN), 10);

        level.levelEvent(entity, state.getValue(OPEN) ? 1008 : 1014, pos, 0);
        return ActionResultType.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        if (state.getValue(OPEN)) {
            switch (state.getValue(FACING)) {
                case NORTH:
                    return NORTH_SHAPE_OPEN;
                case SOUTH:
                    return SOUTH_SHAPE_OPEN;
                case WEST:
                    return WEST_SHAPE_OPEN;
                case EAST:
                    return EAST_SHAPE_OPEN;
            }
        }
        return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, IBlockReader level, BlockPos pos) {
        return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_OCCLUSION_SHAPE : Z_OCCLUSION_SHAPE;
    }
}
