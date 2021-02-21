package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.block.crop.PepperType;
import com.ryanhcode.hotchicks.block.crop.TrellisCrop;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class TrellisBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    public static final EnumProperty<TrellisCrop> CROP = EnumProperty.create("crop", TrellisCrop.class);
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape LADDER_EAST_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
    protected static final VoxelShape LADDER_WEST_AABB = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LADDER_SOUTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape LADDER_NORTH_AABB = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);

    public TrellisBlock(AbstractBlock.Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(AGE, 0).with(CROP, TrellisCrop.NONE));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.get(FACING)) {
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




    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (!context.replacingClickedOnBlock()) {
            BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite()));
            if (blockstate.isIn(this) && blockstate.get(FACING) == context.getFace()) {
                return null;
            }
        }

        BlockState blockstate1 = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.with(FACING, direction.getOpposite());
                if (blockstate1.isValidPosition(iworldreader, blockpos)) {
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
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE, CROP);
    }

}
