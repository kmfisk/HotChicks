package com.ryanhcode.hotchicks.block;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class NEWTrellisBlock extends Block implements IGrowable {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    private final Map<ResourceLocation, Supplier<? extends Block>> fullTrellises;
    private final Supplier<NEWTrellisBlock> emptyTrellis;
    private final Supplier<? extends Item> cropItem;

    public NEWTrellisBlock(Supplier<NEWTrellisBlock> emptyTrellis, Supplier<? extends Item> cropItem) {
        super(AbstractBlock.Properties.of(Material.WOOD).strength(0.4F).sound(SoundType.WOOD).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(FACING, Direction.NORTH));
        this.cropItem = cropItem;
        if (emptyTrellis == null) {
            this.fullTrellises = Maps.newHashMap();
            this.emptyTrellis = null;
        } else {
            this.fullTrellises = Collections.emptyMap();
            this.emptyTrellis = emptyTrellis;
            emptyTrellis.get().fullTrellises.put(cropItem.get().getRegistryName(), () -> this);
        }
    }

    public NEWTrellisBlock getEmptyTrellis() {
        return emptyTrellis == null ? this : emptyTrellis.get();
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader blockReader, BlockPos blockPos, BlockState state) {
        return this.cropItem == null ? super.getCloneItemStack(blockReader, blockPos, state) : this.cropItem.get().getDefaultInstance();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.cropItem != null && state.getValue(AGE) < 5;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 5 && world.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, random.nextInt(5) == 0)) {
            world.setBlock(pos, state.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        Block block = getEmptyTrellis().fullTrellises.getOrDefault(item.getRegistryName(), Blocks.AIR.delegate).get();
        boolean notHoldingCrop = block == Blocks.AIR;
        boolean isTrellisEmpty = this.cropItem == null;
        if (notHoldingCrop != isTrellisEmpty) {
            if (isTrellisEmpty) {
                world.setBlock(blockPos, block.defaultBlockState(), 3);
                if (!player.abilities.instabuild)
                    itemStack.shrink(1);

            } else {
                int i = state.getValue(AGE);
                boolean flag2 = i == 5;
                if (!flag2 && item == Items.BONE_MEAL)
                    return ActionResultType.PASS;
                else if (i > 4) {
                    int j = 1 + world.random.nextInt(2);
                    popResource(world, blockPos, new ItemStack(this.cropItem.get(), j));
                    world.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
                    world.setBlock(blockPos, state.setValue(AGE, 0), 2);
                    return ActionResultType.sidedSuccess(world.isClientSide);
                }
            }
            return ActionResultType.sidedSuccess(world.isClientSide);

        } else
            return super.use(state, world, blockPos, player, hand, rayTraceResult);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction enumFacing = context.getHorizontalDirection().getOpposite();
        return super.getStateForPlacement(context).setValue(FACING, enumFacing);
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos blockPos, ISelectionContext context) {
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
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos blockPos, BlockState state, boolean isClient) {
        return this.cropItem != null && state.getValue(AGE) < 5;
    }

    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState state) {
        return this.cropItem != null;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random random, BlockPos blockPos, BlockState state) {
        int i = Math.min(5, state.getValue(AGE) + 1);
        world.setBlock(blockPos, state.setValue(AGE, i), 2);
    }
}
