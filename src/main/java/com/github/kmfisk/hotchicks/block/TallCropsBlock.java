package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class TallCropsBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final IntegerProperty HEIGHT = IntegerProperty.create("height", 0, 2);

    private final Supplier<? extends Item> item;

    public TallCropsBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(this.getHeightProperty(), 0));
        this.item = item;
    }

    public boolean hasThirdBlock() {
        return true;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader level, BlockPos pos, BlockState state) {
        return new ItemStack(item.get());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader level, BlockPos pos) {
        return state.is(this) || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL);
    }

    public IntegerProperty getHeightProperty() {
        return HEIGHT;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        return blockpos.getY() < 255 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        int age = state.getValue(AGE);
        if (age < 5 && state.getValue(getHeightProperty()) == 0 && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
            if (age + 1 == 2) {
                level.setBlockAndUpdate(pos.above(), defaultBlockState().setValue(AGE, age + 1).setValue(getHeightProperty(), 1));
                ForgeHooks.onCropsGrowPost(level, pos.above(), state);
            } else if (age + 1 > 2) {
                level.setBlockAndUpdate(pos.above(), defaultBlockState().setValue(AGE, age + 1).setValue(getHeightProperty(), 1));
                ForgeHooks.onCropsGrowPost(level, pos.above(), state);
                if (hasThirdBlock()) {
                    level.setBlockAndUpdate(pos.above().above(), defaultBlockState().setValue(AGE, age + 1).setValue(getHeightProperty(), 2));
                    ForgeHooks.onCropsGrowPost(level, pos.above().above(), state);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, getHeightProperty());
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader level, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 5;
    }

    @Override
    public boolean isBonemealSuccess(World level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld level, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(5, state.getValue(AGE) + 1);
        if (state.canSurvive(level, pos) && state.getValue(AGE) < 5) {
            level.setBlock(pos, state.setValue(AGE, i), 2);
            if (i > 1) {
                BlockPos pos1, pos2;
                int type1, type2;
                if (state.getValue(getHeightProperty()) == 0) {
                    pos1 = pos.above();
                    type1 = 1;
                    pos2 = pos.above().above();
                    type2 = 2;

                } else if (state.getValue(getHeightProperty()) == 1) {
                    pos1 = pos.below();
                    type1 = 0;
                    pos2 = pos.above();
                    type2 = 2;

                } else /*if (state.getValue(TYPE) == 2)*/ {
                    pos1 = pos.below();
                    type1 = 1;
                    pos2 = pos.below().below();
                    type2 = 0;
                }

                level.setBlock(pos1, defaultBlockState().setValue(AGE, i).setValue(getHeightProperty(), type1), 2);
                if (i > 2 && hasThirdBlock())
                    level.setBlock(pos2, defaultBlockState().setValue(AGE, i).setValue(getHeightProperty(), type2), 2);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() instanceof TallCropsBlock) {
            super.onRemove(state, level, pos, newState, isMoving);
            return;
        }
        int height = state.getValue(getHeightProperty());
        int age = state.getValue(AGE);
        if (height == 1) {
            if (age > 2 && hasThirdBlock()) level.destroyBlock(pos.above(), false);
            level.destroyBlock(pos.below(), false);
        }
        if (hasThirdBlock() && height == 2) {
            level.destroyBlock(pos.below(), false);
            level.destroyBlock(pos.below().below(), false);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.PLAINS;
    }
}
