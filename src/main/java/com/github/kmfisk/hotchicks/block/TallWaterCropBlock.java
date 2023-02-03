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
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TallWaterCropBlock extends TallCropsBlock implements ILiquidContainer {
    public TallWaterCropBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties, item);
    }

    @Override
    public boolean hasThirdBlock() {
        return false;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader level, BlockPos pos) {
        return state.is(this) || (state.isFaceSturdy(level, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK));
    }

    @Override
    public IntegerProperty getHeightProperty() {
        return IntegerProperty.create("height", 0, 1);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockState blockstate = super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        if (!blockstate.isAir())
            pLevel.getLiquidTicks().scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));

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
        if (state.getValue(getHeightProperty()) > 0) {
            BlockState belowState = level.getBlockState(pos.below());
            return belowState.is(this) && belowState.getValue(getHeightProperty()) == 0;
        } else {
            FluidState fluidstate = level.getFluidState(pos);
            return super.canSurvive(state, level, pos) && fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.getValue(getHeightProperty()) > 0) return super.getFluidState(state);
        else return Fluids.WATER.getSource(false);
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
