package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class NestBoxBlock extends NestBlock {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PROPERTY_EGGS = BooleanProperty.create("eggs");
    private static final VoxelShape shape = box(3, 0, 3, 13, 3, 13);

    public NestBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PROPERTY_FACING, Direction.NORTH).setValue(PROPERTY_EGGS, false));
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return shape;
    }
}
