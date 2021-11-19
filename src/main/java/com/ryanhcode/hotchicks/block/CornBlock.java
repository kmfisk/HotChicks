package com.ryanhcode.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class CornBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);

    private final Supplier<? extends Item> item;

    public CornBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(TYPE, 0));
        this.item = item;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos().below());
        if (context.getPlayer() != null && !(state.is(Blocks.FARMLAND)))
            return null;
        return super.getStateForPlacement(context);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.FARMLAND) || state.is(this);
    }

    public void setAge(ServerWorld world, BlockPos pos, int age) {
        BlockState state = world.getBlockState(pos);
        world.setBlock(pos, state.setValue(AGE, age), 2);
    }

    private void growTo(BlockState state, ServerWorld world, BlockPos pos, boolean boned) {
        if (!boned)
            if (getType(state) != 0) return;
        int newAge = state.getValue(AGE) + 1;
        if (newAge == 2) {
            setAge(world, pos, newAge);
            addPlant(world, pos, pos.above(), 1);
        } else if (newAge > 2) {
            setAge(world, pos, newAge);
            addPlant(world, pos, pos.above(), 1);
            addPlant(world, pos, pos.above().above(), 2);
        } else {
            setAge(world, pos, newAge);
            //setAge(world, pos.up(), newAge);
            //setAge(world, pos.up().up(), newAge);
        }
    }

    public void setType(ServerWorld world, BlockPos pos, int type) {
        world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(TYPE, type));
    }

    private void addPlant(ServerWorld w, BlockPos thisPos, BlockPos newPos, int type) {
        w.setBlockAndUpdate(newPos, w.getBlockState(thisPos).setValue(TYPE, type));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    private int getType(BlockState state) {
        return state.getValue(TYPE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return isRandomlyTicking(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 5 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
            growTo(state, worldIn, pos, false);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE)
            entityIn.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(TYPE);
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
        //return isValidToGrow(worldIn.getBlockState(pos.up())) && isValidToGrow(worldIn.getBlockState(pos.up().up()));
    }

    private boolean isValidToGrow(BlockState blockState) {
        return blockState.getBlock() instanceof CornBlock || blockState.isAir();
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int type = getType(state);
        int age = state.getValue(AGE);
        if (type == 0) {
            boolean canGrow = (isValidToGrow(worldIn.getBlockState(pos.above())) && isValidToGrow(worldIn.getBlockState(pos.above().above())));
            if (canGrow && age < 5)
                growTo(state, worldIn, pos, true);
        } else if (type == 1) {
            boolean canGrow = (isValidToGrow(worldIn.getBlockState(pos.above())));
            if (canGrow && age < 5)
                growTo(state, worldIn, pos.below(), true);
        } else if (type == 2) {
            if (age < 5)
                growTo(state, worldIn, pos.below().below(), true);
        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() instanceof CornBlock) {
            super.onRemove(state, world, pos, newState, isMoving);
            return;
        }
        int type = getType(state);
        int age = state.getValue(AGE);
        if (type == 1) {
            if (age > 2)
                world.destroyBlock(pos.above(), false);
            world.destroyBlock(pos.below(), false);
        }
        if (type == 2) {
            world.destroyBlock(pos.below(), false);
            world.destroyBlock(pos.below().below(), false);
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }
}
