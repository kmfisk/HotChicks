package com.ryanhcode.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class FruitLeavesBlock extends LeavesBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    Item fruitItem;

    public FruitLeavesBlock(Item fruit) {
        super(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(FruitLeavesBlock::ocelotOrParrot).isSuffocating(FruitLeavesBlock::never).isViewBlocking(FruitLeavesBlock::never));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
        this.fruitItem = fruit;
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState p_235441_0_, IBlockReader p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
        return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 3 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
            worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL)
            return ActionResultType.PASS;
        else if (i > 2) {
            int j = 1 + worldIn.random.nextInt(2);
            popResource(worldIn, pos, new ItemStack(fruitItem, j));
            worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
            worldIn.setBlock(pos, state.setValue(AGE, 0), 2);
            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else
            return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        worldIn.setBlock(pos, state.setValue(AGE, i), 2);
    }
}
