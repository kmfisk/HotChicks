package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.block.crop.TrellisCrop;
import com.ryanhcode.hotchicks.registry.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.Random;

public class TrellisBlock extends Block implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final EnumProperty<TrellisCrop> CROP = EnumProperty.create("crop", TrellisCrop.class);
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    protected static final VoxelShape LADDER_EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape LADDER_WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LADDER_SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape LADDER_NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    public TrellisBlock(AbstractBlock.Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AGE, 0).setValue(CROP, TrellisCrop.NONE));
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getItemInHand(handIn);
        if (stack.isEmpty()) {

        } else if (stack.getItem() == ItemRegistry.GRAPES.get() && state.getValue(CROP) == TrellisCrop.NONE) {
            worldIn.setBlockAndUpdate(pos, state.setValue(CROP, TrellisCrop.GRAPES));
            stack.setCount(stack.getCount() - 1);
            player.setItemInHand(handIn, stack);
            player.swing(handIn, false);
            return ActionResultType.CONSUME;
        } else if (stack.getItem() == ItemRegistry.KIWI.get() && state.getValue(CROP) == TrellisCrop.NONE) {
            worldIn.setBlockAndUpdate(pos, state.setValue(CROP, TrellisCrop.KIWI));
            stack.setCount(stack.getCount() - 1);
            player.setItemInHand(handIn, stack);
            player.swing(handIn, false);
            return ActionResultType.CONSUME;
        } else if (stack.getItem() == ItemRegistry.PEAS.get() && state.getValue(CROP) == TrellisCrop.NONE) {
            worldIn.setBlockAndUpdate(pos, state.setValue(CROP, TrellisCrop.PEAS));
            stack.setCount(stack.getCount() - 1);
            player.setItemInHand(handIn, stack);
            player.swing(handIn, false);
            return ActionResultType.CONSUME;
        } else if (stack.getItem() == ItemRegistry.TOMATO.get() && state.getValue(CROP) == TrellisCrop.NONE) {
            worldIn.setBlockAndUpdate(pos, state.setValue(CROP, TrellisCrop.TOMATO));
            stack.setCount(stack.getCount() - 1);
            player.setItemInHand(handIn, stack);
            player.swing(handIn, false);
            return ActionResultType.CONSUME;
        }

        if (state.getValue(AGE) >= 5) {
            TrellisCrop crop = state.getValue(CROP);
            player.swing(handIn, false);
            if (crop == TrellisCrop.GRAPES) {
                drop(state, worldIn, pos, ItemRegistry.TOMATO);
                return ActionResultType.CONSUME;
            }
            if (crop == TrellisCrop.KIWI) {
                drop(state, worldIn, pos, ItemRegistry.KIWI);
                return ActionResultType.CONSUME;
            }
            if (crop == TrellisCrop.PEAS) {
                drop(state, worldIn, pos, ItemRegistry.PEAS);
                return ActionResultType.CONSUME;
            }
            if (crop == TrellisCrop.TOMATO) {
                drop(state, worldIn, pos, ItemRegistry.TOMATO);
                return ActionResultType.CONSUME;
            }
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    private void drop(BlockState state, World worldIn, BlockPos pos, RegistryObject<Item> item) {
        popResource(worldIn, pos, new ItemStack(item.get(), 1));
        worldIn.playSound((PlayerEntity) null, pos, SoundEvents.SWEET_BERRY_BUSH_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
        worldIn.setBlock(pos, state.setValue(AGE, 2), 2);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch ((Direction) state.getValue(FACING)) {
            case NORTH:
                return LADDER_NORTH_AABB;
            case SOUTH:
                return LADDER_SOUTH_AABB;
            case WEST:
                return LADDER_WEST_AABB;
            case EAST:
            default:
                return LADDER_EAST_AABB;
        }
    }


    public VoxelShape getVisualShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }


    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (!context.replacingClickedOnBlock()) {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
            if (blockstate.is(this) && blockstate.getValue(FACING) == context.getClickedFace()) {
                return null;
            }
        }

        BlockState blockstate1 = this.defaultBlockState();
        IWorldReader iworldreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
                if (blockstate1.canSurvive(iworldreader, blockpos)) {
                    return blockstate1;
                }
            }
        }

        return null;
    }


    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE, CROP);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.getValue(CROP) != TrellisCrop.NONE;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return state.getValue(CROP) != TrellisCrop.NONE;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(CROP) != TrellisCrop.NONE) {
            worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
        }
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.getValue(AGE);
        if (i < 5 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
            performBonemeal(worldIn, random, pos, state);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }
}
