package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class DoubleWaterCropBlock extends DoubleCropBlock implements ILiquidContainer {
    public DoubleWaterCropBlock(Properties properties, Supplier<? extends Item> item, int upperSegmentAge) {
        super(properties, item, upperSegmentAge);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader level, BlockPos pos) {
        return state.is(this) || (state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.block(); // todo
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return BlockStateProperties.AGE_5;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld level, BlockPos pos, BlockPos facingPos) {
        BlockState blockstate = super.updateShape(state, facing, facingState, level, pos, facingPos);
        if (!blockstate.isAir())
            level.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return blockstate;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockState = super.getStateForPlacement(context);
        if (blockState != null) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos().above());
            if (fluidstate.getAmount() < 8) return blockState;
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader level, BlockPos pos) {
        if (state.getValue(SEGMENT) == DoubleBlockHalf.UPPER) {
            BlockState belowState = level.getBlockState(pos.below());
            return belowState.is(this) && belowState.getValue(SEGMENT) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidstate = level.getFluidState(pos);
            return super.canSurvive(state, level, pos) && fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(SEGMENT) == DoubleBlockHalf.UPPER ? super.getFluidState(state) : Fluids.WATER.getSource(false);
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader level, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(IWorld level, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.WATER;
    }
}
