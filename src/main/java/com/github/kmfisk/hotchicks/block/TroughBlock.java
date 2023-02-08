package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
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

    protected static final VoxelShape SHAPE_SINGLE = VoxelShapes.join(box(0, 0, 0, 16, 12, 16), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_SINGLE), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape SHAPE_NORTH = VoxelShapes.join(box(0, 0, 0, 16, 12, 16), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_NORTH), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape SHAPE_SOUTH = VoxelShapes.join(box(0, 0, 0, 16, 12, 16), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_SOUTH), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape SHAPE_WEST = VoxelShapes.join(box(0, 0, 0, 16, 12, 16), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_WEST), IBooleanFunction.ONLY_FIRST);
    protected static final VoxelShape SHAPE_EAST = VoxelShapes.join(box(0, 0, 0, 16, 12, 16), VoxelShapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_EAST), IBooleanFunction.ONLY_FIRST);

    //MERGERS
    /*private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>> INVENTORY_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>>() {
        @Override
        public Optional<IInventory> acceptDouble(TroughTileEntity tileEntity, TroughTileEntity tileEntity1) {
            return Optional.of(new DoubleSidedInventory(tileEntity, tileEntity1));
        }

        @Override
        public Optional<IInventory> acceptSingle(TroughTileEntity tileEntity) {
            return Optional.of(tileEntity);
        }

        @Override
        public Optional<IInventory> acceptNone() {
            return Optional.empty();
        }
    };*/
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>>() {
        @Override
        public Optional<INamedContainerProvider> acceptDouble(final TroughTileEntity tileEntity, final TroughTileEntity tileEntity1) {
            final IInventory iinventory = new DoubleSidedInventory(tileEntity, tileEntity1);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                @Override
                public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
                    if (tileEntity.canOpen(player) && tileEntity1.canOpen(player))
                        return TroughContainer.createGenericDouble(id, playerInventory, iinventory);
                    else return null;
                }

                @Override
                public ITextComponent getDisplayName() {
                    if (tileEntity.hasCustomName()) return tileEntity.getDisplayName();
                    else
                        return new TranslationTextComponent(Util.makeDescriptionId("container_double", ForgeRegistries.BLOCKS.getKey(tileEntity.getBlockState().getBlock())));
                }
            });
        }

        @Override
        public Optional<INamedContainerProvider> acceptSingle(TroughTileEntity tileEntity) {
            return Optional.of(tileEntity);
        }

        @Override
        public Optional<INamedContainerProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public TroughBlock(Properties properties) {
        super(properties);
    }

    public static TileEntityMerger.Type getChestMergerType(BlockState state) {
        ChestType chesttype = state.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) return TileEntityMerger.Type.SINGLE;
        else return chesttype == ChestType.RIGHT ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
    }

   /* @Nullable
    public static IInventory getChestInventory(TroughBlock chest, BlockState state, World level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(INVENTORY_MERGER).orElse(null);
    }*/

    public TileEntityMerger.ICallbackWrapper<? extends TroughTileEntity> combine(BlockState state, World level, BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) bipredicate = (level1, pos1) -> false;
        else bipredicate = ChestBlock::isChestBlockedAt;

        return TileEntityMerger.combineWithNeigbour(HotTileEntities.TROUGH.get(), TroughBlock::getChestMergerType, TroughBlock::getDirectionToAttached, FACING, state, level, pos, bipredicate);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld level, BlockPos pos, BlockPos facingPos) {
        if (facingState.is(this) && facing.getAxis().isHorizontal()) {
            ChestType facingChestType = facingState.getValue(TYPE);
            if (state.getValue(TYPE) == ChestType.SINGLE && facingChestType != ChestType.SINGLE && state.getValue(FACING) == facingState.getValue(FACING) && getDirectionToAttached(facingState) == facing.getOpposite())
                return state.setValue(TYPE, facingChestType.getOpposite());

        } else if (getDirectionToAttached(state) == facing) return state.setValue(TYPE, ChestType.SINGLE);

        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
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

    /**
     * Returns a facing pointing from the given state to its attached double chest
     */
    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(BlockItemUseContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
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
            if (direction == this.getDirectionToAttach(context, direction.getClockWise()))
                chesttype = ChestType.LEFT;
            else if (direction == this.getDirectionToAttach(context, direction.getCounterClockWise()))
                chesttype = ChestType.RIGHT;
        }

        return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(CONTAINS, TroughFillType.NONE);
    }

    @Override
    public void setPlacedBy(World level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof TroughTileEntity)
                ((TroughTileEntity) tileentity).setCustomName(stack.getHoverName());
        }
    }

    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(level, pos, (IInventory) tileentity);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(CONTAINER_MERGER).orElse(null);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, CONTAINS);
    }

    @Override
    public boolean isPathfindable(BlockState state, IBlockReader level, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);

        Item item = itemstack.getItem();
        TroughFillType contains = state.getValue(CONTAINS);
        if (item == Items.WATER_BUCKET && contains == TroughFillType.NONE) {
            if (!player.abilities.instabuild) player.setItemInHand(hand, new ItemStack(Items.BUCKET));

            level.setBlockAndUpdate(pos, state.setValue(CONTAINS, TroughFillType.WATER));
            if (state.getValue(TYPE) != ChestType.SINGLE) {
                BlockPos connectedSlot = pos.offset(new BlockPos(state.getValue(FACING).getNormal()).rotate(state.getValue(TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                BlockState connectedState = level.getBlockState(connectedSlot);
                level.setBlockAndUpdate(connectedSlot, connectedState.setValue(CONTAINS, TroughFillType.WATER));
            }

            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.sidedSuccess(level.isClientSide);

        } else if (item == Items.BUCKET && contains == TroughFillType.WATER) {
            player.setItemInHand(hand, new ItemStack(Items.WATER_BUCKET));
            level.setBlockAndUpdate(pos, state.setValue(CONTAINS, TroughFillType.NONE));

            TroughFillType troughFillType = state.getValue(TroughBlock.CONTAINS);

            if (state.getValue(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER) {
                BlockPos connectedSlot = pos.offset(new BlockPos(state.getValue(TroughBlock.FACING).getNormal()).rotate(state.getValue(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                BlockState connectedState = level.getBlockState(connectedSlot);
                level.setBlockAndUpdate(connectedSlot, connectedState.setValue(TroughBlock.CONTAINS, TroughFillType.NONE));
            }

            level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResultType.sidedSuccess(level.isClientSide);

        } else if (contains != TroughFillType.WATER) {
            if (level.isClientSide) return ActionResultType.SUCCESS;
            else {
                INamedContainerProvider inamedcontainerprovider = this.getMenuProvider(state, level, pos);
                if (inamedcontainerprovider != null) player.openMenu(inamedcontainerprovider);

                return ActionResultType.CONSUME;
            }
        }

        return ActionResultType.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader level) {
        return HotTileEntities.TROUGH.get().create();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World level, BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
