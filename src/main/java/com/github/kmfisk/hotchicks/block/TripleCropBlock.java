package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class TripleCropBlock extends HotCropBlock {
    public static final EnumProperty<TripleBlockSegment> SEGMENT = EnumProperty.create("segment", TripleBlockSegment.class);
    protected final int middleSegmentAge, topSegmentAge;

    public TripleCropBlock(Properties properties, Supplier<? extends Item> item, int middleSegmentAge, int topSegmentAge) {
        super(properties, item);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(SEGMENT, TripleBlockSegment.BOTTOM));
        this.middleSegmentAge = middleSegmentAge;
        this.topSegmentAge = topSegmentAge;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.FARMLAND);
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
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        TripleBlockSegment stateSegment = state.getValue(SEGMENT);
        if (facing.getAxis() != Direction.Axis.Y || stateSegment == TripleBlockSegment.BOTTOM != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(SEGMENT) != stateSegment)
            return stateSegment == TripleBlockSegment.BOTTOM && facing == Direction.DOWN && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
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
        if (state.getValue(SEGMENT) == TripleBlockSegment.BOTTOM) return super.canSurvive(state, level, pos);
        else {
            BlockState bottomMostState = level.getBlockState(pos.below(state.getValue(SEGMENT) == TripleBlockSegment.MIDDLE ? 1 : 2));
            if (state.getBlock() != this) return super.canSurvive(state, level, pos);

            boolean bottomCheck = bottomMostState.is(this) && bottomMostState.getValue(SEGMENT) == TripleBlockSegment.BOTTOM;
            if (state.getValue(SEGMENT) == TripleBlockSegment.TOP) {
                BlockState middleState = level.getBlockState(pos.below());
                return bottomCheck && middleState.is(this) && middleState.getValue(SEGMENT) == TripleBlockSegment.MIDDLE;

            } if (state.getValue(getAgeProperty()) >= topSegmentAge && state.getValue(SEGMENT) == TripleBlockSegment.MIDDLE) {
                BlockState topState = level.getBlockState(pos.above());
                return bottomCheck && topState.is(this) && topState.getValue(SEGMENT) == TripleBlockSegment.TOP;
            }

            return bottomCheck;
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        int age = state.getValue(getAgeProperty());
        if (age < getMaxAge() && state.getValue(SEGMENT) == TripleBlockSegment.BOTTOM && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            int growthAge = age + 1;
            level.setBlock(pos, state.setValue(getAgeProperty(), growthAge), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
            if (growthAge >= middleSegmentAge) {
                level.setBlockAndUpdate(pos.above(), defaultBlockState().setValue(getAgeProperty(), growthAge).setValue(SEGMENT, TripleBlockSegment.MIDDLE));
                ForgeHooks.onCropsGrowPost(level, pos.above(), state);
                if (growthAge >= topSegmentAge) {
                    level.setBlockAndUpdate(pos.above().above(), defaultBlockState().setValue(getAgeProperty(), growthAge).setValue(SEGMENT, TripleBlockSegment.TOP));
                    ForgeHooks.onCropsGrowPost(level, pos.above().above(), state);
                }
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
        TripleBlockSegment tripleBlockSegment = state.getValue(SEGMENT);
        if (tripleBlockSegment == TripleBlockSegment.MIDDLE) {
            BlockPos bottomPos = pos.below();
            BlockState bottomState = level.getBlockState(bottomPos);
            if (bottomState.getBlock() == state.getBlock() && bottomState.getValue(SEGMENT) == TripleBlockSegment.BOTTOM) {
                level.setBlock(bottomPos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, bottomPos, Block.getId(bottomState));
            }
            BlockPos topPos = pos.above();
            BlockState topState = level.getBlockState(topPos);
            if (topState.getBlock() == state.getBlock() && topState.getValue(SEGMENT) == TripleBlockSegment.TOP) {
                level.setBlock(topPos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, topPos, Block.getId(topState));
            }

        } else if (tripleBlockSegment == TripleBlockSegment.TOP) {
            BlockPos bottomPos = pos.below(2);
            BlockState bottomState = level.getBlockState(bottomPos);
            if (bottomState.getBlock() == state.getBlock() && bottomState.getValue(SEGMENT) == TripleBlockSegment.BOTTOM) {
                level.setBlock(bottomPos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, bottomPos, Block.getId(bottomState));
            }
            BlockPos middlePos = pos.below();
            BlockState middleState = level.getBlockState(middlePos);
            if (middleState.getBlock() == state.getBlock() && middleState.getValue(SEGMENT) == TripleBlockSegment.MIDDLE) {
                level.setBlock(middlePos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, middlePos, Block.getId(middleState));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty(), SEGMENT);
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(SEGMENT) == TripleBlockSegment.BOTTOM ? 0 : state.getValue(SEGMENT) == TripleBlockSegment.MIDDLE ? 1 : 2).getY(), pos.getZ());
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.PLAINS;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        int age = Math.min(getMaxAge(), state.getValue(getAgeProperty()) + Mth.nextInt(level.random, 1, 3));
        if (state.canSurvive(level, pos) && state.getValue(getAgeProperty()) < getMaxAge()) {
            level.setBlock(pos, state.setValue(getAgeProperty(), age), 2);
            if (age >= middleSegmentAge) {
                BlockPos pos1, pos2;
                TripleBlockSegment segment1, segment2;
                if (state.getValue(SEGMENT) == TripleBlockSegment.BOTTOM) {
                    pos1 = pos.above();
                    segment1 = TripleBlockSegment.MIDDLE;
                    pos2 = pos.above().above();
                    segment2 = TripleBlockSegment.TOP;

                } else if (state.getValue(SEGMENT) == TripleBlockSegment.MIDDLE) {
                    pos1 = pos.below();
                    segment1 = TripleBlockSegment.BOTTOM;
                    pos2 = pos.above();
                    segment2 = TripleBlockSegment.TOP;

                } else {
                    pos1 = pos.below();
                    segment1 = TripleBlockSegment.MIDDLE;
                    pos2 = pos.below().below();
                    segment2 = TripleBlockSegment.BOTTOM;
                }

                level.setBlock(pos1, defaultBlockState().setValue(getAgeProperty(), age).setValue(SEGMENT, segment1), 2);
                if (age >= topSegmentAge)
                    level.setBlock(pos2, defaultBlockState().setValue(getAgeProperty(), age).setValue(SEGMENT, segment2), 2);
            }
        }
    }

    public enum TripleBlockSegment implements StringRepresentable {
        TOP,
        MIDDLE,
        BOTTOM;

        public String toString() {
            return this.getSerializedName();
        }

        @Override
        public String getSerializedName() {
            return this == TOP ? "top" : this == MIDDLE ? "middle" : "bottom";
        }
    }
}
