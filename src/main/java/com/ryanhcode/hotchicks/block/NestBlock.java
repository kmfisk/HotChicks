package com.ryanhcode.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BarrelTileEntity;
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


    protected static final VoxelShape WEST_SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.fullCube(),
            makeCuboidShape(1.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.fullCube(),
            makeCuboidShape(0.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.fullCube(),
            makeCuboidShape(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 16.0D),
            IBooleanFunction.ONLY_FIRST
    );
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.fullCube(),
            makeCuboidShape(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 15.0D),
            IBooleanFunction.ONLY_FIRST
    );

    public VoxelShape shape(BlockState state) {
        switch ((Direction) state.get(PROPERTY_FACING)) {
            case UP :
            case DOWN :
            case SOUTH :
            default :
                return SOUTH_SHAPE;
            case NORTH :
                return NORTH_SHAPE;
            case WEST :
                return WEST_SHAPE;
            case EAST :
                return EAST_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return shape(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape(state);
    }

    public NestBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PROPERTY_FACING, Direction.NORTH).with(PROPERTY_EGGS, false));
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand
            handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof NestTileEntity) {
                player.openContainer((NestTileEntity)tileentity);
                PiglinTasks.func_234478_a_(player, true);
            }

            return ActionResultType.CONSUME;
        }
    }



    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof NestTileEntity) {
            ((NestTileEntity)tileentity).barrelTick();
        }

        NonNullList<ItemStack> items = ((NestTileEntity) tileentity).getItems();

        boolean hasItems = false;
        for(ItemStack i : items){
            if(i!=ItemStack.EMPTY){
                hasItems = true;
            }else{

            }
        }
        worldIn.setBlockState(pos, state.with(eggs,
                !hasItems
        ));

    }

    @Nullable
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new NestTileEntity();
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack
            stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof NestTileEntity) {
                ((NestTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }

    /**
     * is fine.
     */
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    /**
     * Implementing/overriding is fine.
     */
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(PROPERTY_FACING, rot.rotate(state.get(PROPERTY_FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(PROPERTY_FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder< Block, BlockState> builder) {
        builder.add(PROPERTY_FACING);
        builder.add(PROPERTY_EGGS);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IWorld iworld = context.getWorld();
        BlockPos blockpos = context.getPos();
        return this.getDefaultState().with(PROPERTY_FACING, context.getPlacementHorizontalFacing()).with(PROPERTY_EGGS, false);
    }
}
