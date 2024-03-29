package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class BerryBushBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape BUSHLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape GROWING_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    protected final Supplier<? extends Item> item;

    public BerryBushBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
        this.item = item;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader level, BlockPos pos, BlockState state) {
        return item.get().getDefaultInstance();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        if (state.getValue(AGE) == 0)
            return BUSHLING_SHAPE;
        else
            return state.getValue(AGE) < 3 ? GROWING_SHAPE : super.getShape(state, level, pos, context);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 3 && level.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }

    @Override
    public void entityInside(BlockState state, World level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE)
            entity.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(hand).getItem() == Items.BONE_MEAL)
            return ActionResultType.PASS;
        else if (i > 2) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(item.get(), j));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE, 2), 2);
            return ActionResultType.sidedSuccess(level.isClientSide);
        } else
            return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader level, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(World level, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld level, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, i), 2);
    }
}
