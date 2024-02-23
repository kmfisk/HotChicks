package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class HutchGateBlock extends FenceGateBlock {
    protected static final VoxelShape NORTH_SHAPE_OPEN = Shapes.or(Block.box(0.0D, 0.0D, 7.0D, 2.0D, 16.0D, 16.0D), Block.box(14.0D, 0.0D, 7.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape SOUTH_SHAPE_OPEN = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 9.0D), Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 9.0D));
    protected static final VoxelShape EAST_SHAPE_OPEN = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 9.0D, 16.0D, 2.0D), Block.box(0.0D, 0.0D, 14.0D, 9.0D, 16.0D, 16.0D));
    protected static final VoxelShape WEST_SHAPE_OPEN = Shapes.or(Block.box(7.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D), Block.box(7.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D));
    protected static final VoxelShape Z_OCCLUSION_SHAPE = Shapes.or(Block.box(0.0D, 0.0D, 7.0D, 2.0D, 16.0D, 9.0D), Block.box(14.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D));
    protected static final VoxelShape X_OCCLUSION_SHAPE = Shapes.or(Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 2.0D), Block.box(7.0D, 0.0D, 14.0D, 9.0D, 16.0D, 16.0D));

    public HutchGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        level.setBlock(pos, state.cycle(OPEN), 10);

        level.levelEvent(entity, state.getValue(OPEN) ? 1008 : 1014, pos, 0);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_OCCLUSION_SHAPE : Z_OCCLUSION_SHAPE;
    }
}
