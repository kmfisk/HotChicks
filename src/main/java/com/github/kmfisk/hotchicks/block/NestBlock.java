package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.block.entity.NestTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class NestBlock extends ContainerBlock {
    public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PROPERTY_EGGS = BooleanProperty.create("eggs");
    protected static final VoxelShape WEST_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(1.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(0.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 16.0D), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.join(VoxelShapes.block(), box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 15.0D), IBooleanFunction.ONLY_FIRST);

    public NestBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PROPERTY_FACING, Direction.NORTH).setValue(PROPERTY_EGGS, false));
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return shape(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return shape(state);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof NestTileEntity) player.openMenu((NestTileEntity) tileentity);

            return ActionResultType.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(world, pos, (IInventory) tileentity);
                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_FACING, PROPERTY_EGGS);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return HotTileEntities.NEST.get().create();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(PROPERTY_FACING, context.getHorizontalDirection()).setValue(PROPERTY_EGGS, false);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(PROPERTY_FACING, rotation.rotate(state.getValue(PROPERTY_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(PROPERTY_FACING)));
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World world, BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
    }

    private VoxelShape shape(BlockState state) {
        switch (state.getValue(PROPERTY_FACING)) {
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
}
