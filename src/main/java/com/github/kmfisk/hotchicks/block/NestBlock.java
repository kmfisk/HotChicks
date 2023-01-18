package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class NestBlock extends ContainerBlock {
    public static final BooleanProperty eggs = BooleanProperty.create("eggs");

    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PROPERTY_EGGS = eggs;


    protected static final VoxelShape WEST_SHAPE = VoxelShapes.join(
            VoxelShapes.block(),
            box(1.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.join(
            VoxelShapes.block(),
            box(0.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.join(
            VoxelShapes.block(),
            box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 16.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.join(
            VoxelShapes.block(),
            box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );

    public VoxelShape shape(BlockState state) {
        switch ((Direction) state.getValue(PROPERTY_FACING)) {
            case UP:
            case DOWN:
            case SOUTH:
            default:
                return SOUTH_SHAPE;
            case NORTH:
                return NORTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
        }
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return shape(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape(state);
    }

    public NestBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PROPERTY_FACING, Direction.NORTH).setValue(PROPERTY_EGGS, false));
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand
            handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof NestTileEntity) {
                player.openMenu((NestTileEntity) tileentity);
                PiglinTasks.angerNearbyPiglins(player, true);
            }

            return ActionResultType.CONSUME;
        }
    }


    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                //InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof NestTileEntity) {
            ((NestTileEntity) tileentity).barrelTick();
        }

        NonNullList<ItemStack> items = ((NestTileEntity) tileentity).getItems();


    }

    @Nullable
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new NestTileEntity();
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack
            stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof NestTileEntity) {
                ((NestTileEntity) tileentity).setCustomName(stack.getHoverName());
            }
        }

    }

    /**
     * is fine.
     */
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    /**
     * Implementing/overriding is fine.
     */
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(PROPERTY_FACING, rot.rotate(state.getValue(PROPERTY_FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(PROPERTY_FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_FACING);
        builder.add(PROPERTY_EGGS);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorld iworld = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(PROPERTY_FACING, context.getHorizontalDirection()).setValue(PROPERTY_EGGS, false);
    }

}
