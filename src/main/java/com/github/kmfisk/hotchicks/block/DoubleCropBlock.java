package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public abstract class DoubleCropBlock extends HotCropBlock {
    public static final EnumProperty<DoubleBlockHalf> SEGMENT = BlockStateProperties.DOUBLE_BLOCK_HALF;
    protected final int upperSegmentAge;

    public DoubleCropBlock(Properties properties, Supplier<? extends Item> item, int upperSegmentAge) {
        super(properties, item);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(SEGMENT, DoubleBlockHalf.LOWER));
        this.upperSegmentAge = upperSegmentAge;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        DoubleBlockHalf stateSegment = state.getValue(SEGMENT);
        if (facing.getAxis() != Direction.Axis.Y || stateSegment == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(SEGMENT) != stateSegment)
            return stateSegment == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
        else return Blocks.AIR.defaultBlockState();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        return pos.getY() < 255 ? super.getStateForPlacement(context) : null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(SEGMENT) == DoubleBlockHalf.LOWER) return super.canSurvive(state, level, pos);
        else {
            BlockState lowerState = level.getBlockState(pos.below());
            if (state.getBlock() != this) return super.canSurvive(state, level, pos);
            return lowerState.is(this) && lowerState.getValue(SEGMENT) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        int age = state.getValue(getAgeProperty());
        if (age < getMaxAge() && state.getValue(SEGMENT) == DoubleBlockHalf.LOWER && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            int growthAge = age + 1;
            level.setBlock(pos, state.setValue(getAgeProperty(), growthAge), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
            if (growthAge >= upperSegmentAge) {
                level.setBlockAndUpdate(pos.above(), defaultBlockState().setValue(getAgeProperty(), growthAge).setValue(SEGMENT, DoubleBlockHalf.UPPER));
                ForgeHooks.onCropsGrowPost(level, pos.above(), state);
            }
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) preventCreativeDropFromBottomPart(level, pos, state, player);
            else dropResources(state, level, pos, null, player, player.getMainHandItem());
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity tileEntity, ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), tileEntity, stack);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(SEGMENT);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
            BlockPos lowerPos = pos.below();
            BlockState lowerState = level.getBlockState(lowerPos);
            if (lowerState.getBlock() == state.getBlock() && lowerState.getValue(SEGMENT) == DoubleBlockHalf.LOWER) {
                level.setBlock(lowerPos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, lowerPos, Block.getId(lowerState));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty(), SEGMENT);
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(SEGMENT) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        int age = Math.min(getMaxAge(), state.getValue(getAgeProperty()) + Mth.nextInt(level.random, 1, 3));
        if (state.canSurvive(level, pos) && state.getValue(getAgeProperty()) < getMaxAge()) {
            level.setBlock(pos, state.setValue(getAgeProperty(), age), 2);
            if (age >= upperSegmentAge) {
                BlockPos pos1;
                DoubleBlockHalf segment1;
                if (state.getValue(SEGMENT) == DoubleBlockHalf.LOWER) {
                    pos1 = pos.above();
                    segment1 = DoubleBlockHalf.UPPER;

                } else {
                    pos1 = pos.below();
                    segment1 = DoubleBlockHalf.LOWER;
                }

                level.setBlock(pos1, defaultBlockState().setValue(getAgeProperty(), age).setValue(SEGMENT, segment1), 2);
            }
        }
    }
}
