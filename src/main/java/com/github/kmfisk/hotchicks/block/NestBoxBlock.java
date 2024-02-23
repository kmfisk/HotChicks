package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class NestBoxBlock extends NestBlock {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PROPERTY_EGGS = BooleanProperty.create("eggs");
    private static final VoxelShape shape = box(3, 0, 3, 13, 3, 13);

    public NestBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PROPERTY_FACING, Direction.NORTH).setValue(PROPERTY_EGGS, false));
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shape;
    }
}
