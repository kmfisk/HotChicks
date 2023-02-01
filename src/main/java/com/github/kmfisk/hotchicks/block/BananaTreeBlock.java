package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class BananaTreeBlock extends DoublePlantBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public BananaTreeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 1 && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 1 && state.getValue(HALF) == DoubleBlockHalf.UPPER && level.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
            if (level.getBlockState(pos.below()).getBlock() instanceof BananaTreeBlock) {
                level.setBlock(pos.below(), level.getBlockState(pos.below()).setValue(AGE, i + 1), 2);
                ForgeHooks.onCropsGrowPost(level, pos.below(), state);
            }
        }
    }

    public void dropHarvestItem(World level, BlockPos pos) {
        int j = 1 + level.random.nextInt(3);
        popResource(level, pos, new ItemStack(HotItems.BANANA.get(), j));
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        int i = state.getValue(AGE);
        if (i != 1 && player.getItemInHand(hand).getItem() == Items.BONE_MEAL)
            return ActionResultType.PASS;
        else if (i > 0) {
            BlockPos pos1 = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
            BlockState state1 = level.getBlockState(pos1);
            dropHarvestItem(level, pos);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            if (state1.getBlock() instanceof BananaTreeBlock)
                level.setBlock(pos1, state1.setValue(AGE, 0), 2);
            return ActionResultType.sidedSuccess(level.isClientSide);
        } else
            return super.use(state, level, pos, player, hand, rayTraceResult);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, AGE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader level, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 1;
    }

    @Override
    public boolean isBonemealSuccess(World level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld level, Random random, BlockPos pos, BlockState state) {
        BlockPos pos1 = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
        BlockState state1 = level.getBlockState(pos1);
        int i = Math.min(1, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, i), 2);
        level.setBlock(pos1, state1.setValue(AGE, i), 2);
    }

    @Override
    public PlantType getPlantType(IBlockReader level, BlockPos pos) {
        return PlantType.PLAINS;
    }
}
