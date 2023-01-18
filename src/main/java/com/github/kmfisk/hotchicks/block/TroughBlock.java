package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import com.github.kmfisk.hotchicks.registry.HotTileEntities;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiPredicate;

public class TroughBlock extends ContainerBlock {
    public static final EnumProperty<TroughFillType> CONTAINS = EnumProperty.create("contains", TroughFillType.class);

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;

    private static final VoxelShape INSIDE_SINGLE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_NORTH = box(2.0D, 4.0D, 0.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_SOUTH = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 16.0D);
    private static final VoxelShape INSIDE_WEST = box(0.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_EAST = box(2.0D, 4.0D, 2.0D, 16.0D, 16.0D, 14.0D);

    protected static final VoxelShape SHAPE_SINGLE =
            VoxelShapes.join(
                    box(0, 0, 0, 16, 12, 16),
                    VoxelShapes.or(
                            box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_SINGLE),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_NORTH =
            VoxelShapes.join(
                    box(0, 0, 0, 16, 12, 16),
                    VoxelShapes.or(
                            box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_NORTH),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_SOUTH =
            VoxelShapes.join(
                    box(0, 0, 0, 16, 12, 16),
                    VoxelShapes.or(
                            box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_SOUTH),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_WEST =
            VoxelShapes.join(
                    box(0, 0, 0, 16, 12, 16),
                    VoxelShapes.or(
                            box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_WEST),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_EAST =
            VoxelShapes.join(
                    box(0, 0, 0, 16, 12, 16),
                    VoxelShapes.or(
                            box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_EAST),
                    IBooleanFunction.ONLY_FIRST);


    //MERGERS
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>> INVENTORY_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>>() {
        public Optional<IInventory> acceptDouble(TroughTileEntity p_225539_1_, TroughTileEntity p_225539_2_) {
            return Optional.of(new DoubleSidedInventory(p_225539_1_, p_225539_2_));
        }

        public Optional<IInventory> acceptSingle(TroughTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<IInventory> acceptNone() {
            return Optional.empty();
        }
    };
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>>() {
        public Optional<INamedContainerProvider> acceptDouble(final TroughTileEntity p_225539_1_, final TroughTileEntity p_225539_2_) {
            final IInventory iinventory = new DoubleSidedInventory(p_225539_1_, p_225539_2_);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                    if (p_225539_1_.canOpen(p_createMenu_3_) && p_225539_2_.canOpen(p_createMenu_3_)) {
                        p_225539_1_.unpackLootTable(p_createMenu_2_.player);
                        p_225539_2_.unpackLootTable(p_createMenu_2_.player);
                        return TroughContainer.createGenericDouble(p_createMenu_1_, p_createMenu_2_, iinventory);
                    } else {
                        return null;
                    }
                }

                public ITextComponent getDisplayName() {
                    if (p_225539_1_.hasCustomName()) {
                        return p_225539_1_.getDisplayName();
                    } else {
                        return (ITextComponent) (p_225539_2_.hasCustomName() ? p_225539_2_.getDisplayName() : new StringTextComponent("Large Trough"));
                    }
                }
            });
        }

        public Optional<INamedContainerProvider> acceptSingle(TroughTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<INamedContainerProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public static TileEntityMerger.Type getChestMergerType(BlockState state) {
        ChestType chesttype = state.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return TileEntityMerger.Type.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
        }
    }

    @Nullable
    public static IInventory getChestInventory(TroughBlock chest, BlockState state, World world, BlockPos pos, boolean override) {
        return chest.combine(state, world, pos, override).<Optional<IInventory>>apply(INVENTORY_MERGER).orElse((IInventory) null);
    }

    public TileEntityMerger.ICallbackWrapper<? extends TroughTileEntity> combine(BlockState state, World world, BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) {
            bipredicate = (worldIn, posIn) -> {
                return false;
            };
        } else {
            bipredicate = ChestBlock::isChestBlockedAt;
        }

        return TileEntityMerger.combineWithNeigbour(HotTileEntities.TROUGH.get(), TroughBlock::getChestMergerType, TroughBlock::getDirectionToAttached, FACING, state, world, pos, bipredicate);
    }


    public TroughBlock(Properties properties) {
        super(properties);
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        if (facingState.is(this) && facing.getAxis().isHorizontal()) {
            ChestType chesttype = facingState.getValue(TYPE);
            if (stateIn.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && stateIn.getValue(FACING) == facingState.getValue(FACING) && getDirectionToAttached(facingState) == facing.getOpposite()) {
                return stateIn.setValue(TYPE, chesttype.getOpposite());
            }
        } else if (getDirectionToAttached(stateIn) == facing) {
            return stateIn.setValue(TYPE, ChestType.SINGLE);
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.getValue(TYPE) == ChestType.SINGLE) {
            return SHAPE_SINGLE;
        } else {
            switch (getDirectionToAttached(state)) {
                case NORTH:
                default:
                    return SHAPE_NORTH;
                case SOUTH:
                    return SHAPE_SOUTH;
                case WEST:
                    return SHAPE_WEST;
                case EAST:
                    return SHAPE_EAST;
            }
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof TroughTileEntity) {
            TroughTileEntity te = ((TroughTileEntity) tileentity);
            te.barrelTick();

        }
    }


    /**
     * Returns a facing pointing from the given state to its attached double chest
     */
    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            if (direction == this.getDirectionToAttach(context, direction.getClockWise())) {
                chesttype = ChestType.LEFT;
            } else if (direction == this.getDirectionToAttach(context, direction.getCounterClockWise())) {
                chesttype = ChestType.RIGHT;
            }
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(CONTAINS, TroughFillType.NONE);
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(BlockItemUseContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack
            stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof TroughTileEntity) {
                ((TroughTileEntity) tileentity).setCustomName(stack.getHoverName());
            }
        }

    }

    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }


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


    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return this.combine(state, worldIn, pos, false).<Optional<INamedContainerProvider>>apply(CONTAINER_MERGER).orElse((INamedContainerProvider) null);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, CONTAINS);
    }

    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }


    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand
            handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(handIn);

        Item item = itemstack.getItem();
        TroughFillType contains = state.getValue(CONTAINS);
        if (item == Items.WATER_BUCKET && contains == TroughFillType.NONE) {
            if (!player.abilities.instabuild) {
                player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
            }

            worldIn.setBlockAndUpdate(pos, state.setValue(CONTAINS, TroughFillType.WATER));
            if (state.getValue(TYPE) != ChestType.SINGLE) {
                BlockPos connectedSlot = pos.offset(new BlockPos(state.getValue(FACING).getNormal()).rotate(state.getValue(TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                BlockState connectedState = worldIn.getBlockState(connectedSlot);
                worldIn.setBlockAndUpdate(connectedSlot, connectedState.setValue(CONTAINS, TroughFillType.WATER));
            }

            worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else if (item == Items.BUCKET && contains == TroughFillType.WATER) {
            player.setItemInHand(handIn, new ItemStack(Items.WATER_BUCKET));
            worldIn.setBlockAndUpdate(pos, state.setValue(CONTAINS, TroughFillType.NONE));

            TroughFillType troughFillType = state.getValue(TroughBlock.CONTAINS);


            if (state.getValue(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER) {
                BlockPos connectedSlot = pos.offset(new BlockPos(state.getValue(TroughBlock.FACING).getNormal()).rotate(state.getValue(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                BlockState connectedState = worldIn.getBlockState(connectedSlot);
                worldIn.setBlockAndUpdate(connectedSlot, connectedState.setValue(TroughBlock.CONTAINS, TroughFillType.NONE));
            }

            worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else if (contains != TroughFillType.WATER) {
            if (worldIn.isClientSide) {
                return ActionResultType.SUCCESS;
            } else {
                INamedContainerProvider inamedcontainerprovider = this.getMenuProvider(state, worldIn, pos);
                if (inamedcontainerprovider != null) {
                    player.openMenu(inamedcontainerprovider);
                }

                return ActionResultType.CONSUME;
            }

        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }

    @Nullable
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new TroughTileEntity();
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

    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
