package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.HotChicks;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TrellisBlock extends Block implements BonemealableBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 6.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(10.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 6.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 10.0D, 16.0D, 16.0D, 16.0D);

    private static final Map<ResourceLocation, Supplier<? extends Block>> fullTrellises = Maps.newHashMap();
    private final Supplier<? extends Item> cropItem;

    public TrellisBlock(Supplier<? extends Item> cropItem) {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(0.4F).sound(SoundType.WOOD).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(FACING, Direction.NORTH));
        this.cropItem = cropItem;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockReader, BlockPos blockPos, BlockState state) {
        return this.cropItem == null ? super.getCloneItemStack(blockReader, blockPos, state) : this.cropItem.get().getDefaultInstance();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.cropItem != null && state.getValue(AGE) < 5;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 5 && world.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, random.nextInt(5) == 0)) {
            world.setBlock(pos, state.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        Block block = fullTrellises.getOrDefault(item.getRegistryName(), Blocks.AIR.delegate).get();
        boolean notHoldingCrop = block == Blocks.AIR;
        boolean isTrellisEmpty = this.cropItem == null;
        if (notHoldingCrop != isTrellisEmpty) {
            if (isTrellisEmpty) {
                world.setBlock(blockPos, block.defaultBlockState().setValue(FACING, state.getValue(FACING)), 3);
                if (!player.abilities.instabuild)
                    itemStack.shrink(1);

            } else {
                int i = state.getValue(AGE);
                boolean flag2 = i == 5;
                if (!flag2 && item == Items.BONE_MEAL)
                    return InteractionResult.PASS;
                else if (i > 4) {
                    int j = 1 + world.random.nextInt(2);
                    popResource(world, blockPos, new ItemStack(this.cropItem.get(), j));
                    world.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
                    world.setBlock(blockPos, state.setValue(AGE, 0), 2);
                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide);

        } else
            return super.use(state, world, blockPos, player, hand, rayTraceResult);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction enumFacing = context.getHorizontalDirection().getOpposite();
        return super.getStateForPlacement(context).setValue(FACING, enumFacing);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos blockPos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            default:
                return NORTH_AABB;
            case EAST:
                return EAST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter reader, BlockPos blockPos, BlockState state, boolean isClient) {
        return this.cropItem != null && state.getValue(AGE) < 5;
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos blockPos, BlockState state) {
        return this.cropItem != null;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos blockPos, BlockState state) {
        int i = Math.min(5, state.getValue(AGE) + 1);
        world.setBlock(blockPos, state.setValue(AGE, i), 2);
    }

    static {
        fullTrellises.put(new ResourceLocation(HotChicks.MOD_ID, "cucumber"), HotBlocks.CUCUMBER_TRELLIS);
        fullTrellises.put(new ResourceLocation(HotChicks.MOD_ID, "grapes"), HotBlocks.GRAPE_TRELLIS);
        fullTrellises.put(new ResourceLocation(HotChicks.MOD_ID, "kiwi"), HotBlocks.KIWI_TRELLIS);
        fullTrellises.put(new ResourceLocation(HotChicks.MOD_ID, "tomato"), HotBlocks.TOMATO_TRELLIS);
        fullTrellises.put(new ResourceLocation(HotChicks.MOD_ID, "peas"), HotBlocks.PEA_TRELLIS);
    }
}
