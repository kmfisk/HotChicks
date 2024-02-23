package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class DoubleWaterCropBlock extends DoubleCropBlock implements LiquidBlockContainer {
    public DoubleWaterCropBlock(Properties properties, Supplier<? extends Item> item, int upperSegmentAge) {
        super(properties, item, upperSegmentAge);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(this) || (state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block(); // todo
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
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        BlockState blockstate = super.updateShape(state, facing, facingState, level, pos, facingPos);
        if (!blockstate.isAir())
            level.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return blockstate;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = super.getStateForPlacement(context);
        if (blockState != null) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos().above());
            if (fluidstate.getAmount() < 8) return blockState;
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
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
    public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.WATER;
    }
}
