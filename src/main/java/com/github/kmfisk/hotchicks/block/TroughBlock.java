package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.block.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

public class TroughBlock extends BaseEntityBlock {
    public static final EnumProperty<TroughFillType> CONTAINS = EnumProperty.create("contains", TroughFillType.class);

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;

    private static final VoxelShape INSIDE_SINGLE = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_NORTH = box(2.0D, 4.0D, 0.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_SOUTH = box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 16.0D);
    private static final VoxelShape INSIDE_WEST = box(0.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_EAST = box(2.0D, 4.0D, 2.0D, 16.0D, 16.0D, 14.0D);

    protected static final VoxelShape SHAPE_SINGLE = Shapes.join(box(0, 0, 0, 16, 12, 16), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_SINGLE), BooleanOp.ONLY_FIRST);
    protected static final VoxelShape SHAPE_NORTH = Shapes.join(box(0, 0, 0, 16, 12, 16), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_NORTH), BooleanOp.ONLY_FIRST);
    protected static final VoxelShape SHAPE_SOUTH = Shapes.join(box(0, 0, 0, 16, 12, 16), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_SOUTH), BooleanOp.ONLY_FIRST);
    protected static final VoxelShape SHAPE_WEST = Shapes.join(box(0, 0, 0, 16, 12, 16), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_WEST), BooleanOp.ONLY_FIRST);
    protected static final VoxelShape SHAPE_EAST = Shapes.join(box(0, 0, 0, 16, 12, 16), Shapes.or(box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D), box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D), box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D), INSIDE_EAST), BooleanOp.ONLY_FIRST);

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
    private static final DoubleBlockCombiner.Combiner<TroughTileEntity, Optional<MenuProvider>> CONTAINER_MERGER = new DoubleBlockCombiner.Combiner<TroughTileEntity, Optional<MenuProvider>>() {
        @Override
        public Optional<MenuProvider> acceptDouble(final TroughTileEntity tileEntity, final TroughTileEntity tileEntity1) {
            final Container iinventory = new CompoundContainer(tileEntity, tileEntity1);
            return Optional.of(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                    if (tileEntity.canOpen(player) && tileEntity1.canOpen(player))
                        return TroughContainer.createGenericDouble(id, playerInventory, iinventory);
                    else return null;
                }

                @Override
                public Component getDisplayName() {
                    if (tileEntity.hasCustomName()) return tileEntity.getDisplayName();
                    else
                        return new TranslatableComponent(Util.makeDescriptionId("container_double", ForgeRegistries.BLOCKS.getKey(tileEntity.getBlockState().getBlock())));
                }
            });
        }

        @Override
        public Optional<MenuProvider> acceptSingle(TroughTileEntity tileEntity) {
            return Optional.of(tileEntity);
        }

        @Override
        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public TroughBlock(Properties properties) {
        super(properties);
    }

    public static DoubleBlockCombiner.BlockType getChestMergerType(BlockState state) {
        ChestType chesttype = state.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) return DoubleBlockCombiner.BlockType.SINGLE;
        else return chesttype == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
    }

   /* @Nullable
    public static IInventory getChestInventory(TroughBlock chest, BlockState state, World level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(INVENTORY_MERGER).orElse(null);
    }*/

    public DoubleBlockCombiner.NeighborCombineResult<? extends TroughTileEntity> combine(BlockState state, Level level, BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (override) bipredicate = (level1, pos1) -> false;
        else bipredicate = ChestBlock::isChestBlockedAt;

        return DoubleBlockCombiner.combineWithNeigbour(HotTileEntities.TROUGH.get(), TroughBlock::getChestMergerType, TroughBlock::getDirectionToAttached, FACING, state, level, pos, bipredicate);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        if (facingState.is(this) && facing.getAxis().isHorizontal()) {
            ChestType facingChestType = facingState.getValue(TYPE);
            if (state.getValue(TYPE) == ChestType.SINGLE && facingChestType != ChestType.SINGLE && state.getValue(FACING) == facingState.getValue(FACING) && getDirectionToAttached(facingState) == facing.getOpposite())
                return state.setValue(TYPE, facingChestType.getOpposite());

        } else if (getDirectionToAttached(state) == facing) return state.setValue(TYPE, ChestType.SINGLE);

        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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
    private Direction getDirectionToAttach(BlockPlaceContext context, Direction direction) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
        return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof TroughTileEntity)
                ((TroughTileEntity) tileentity).setCustomName(stack.getHoverName());
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof Container) {
                Containers.dropContents(level, pos, (Container) tileentity);
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
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(CONTAINER_MERGER).orElse(null);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, CONTAINS);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
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

            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide);

        } else if (item == Items.BUCKET && contains == TroughFillType.WATER) {
            player.setItemInHand(hand, new ItemStack(Items.WATER_BUCKET));
            level.setBlockAndUpdate(pos, state.setValue(CONTAINS, TroughFillType.NONE));

            TroughFillType troughFillType = state.getValue(TroughBlock.CONTAINS);

            if (state.getValue(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER) {
                BlockPos connectedSlot = pos.offset(new BlockPos(state.getValue(TroughBlock.FACING).getNormal()).rotate(state.getValue(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                BlockState connectedState = level.getBlockState(connectedSlot);
                level.setBlockAndUpdate(connectedSlot, connectedState.setValue(TroughBlock.CONTAINS, TroughFillType.NONE));
            }

            level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide);

        } else if (contains != TroughFillType.WATER) {
            if (level.isClientSide) return InteractionResult.SUCCESS;
            else {
                MenuProvider inamedcontainerprovider = this.getMenuProvider(state, level, pos);
                if (inamedcontainerprovider != null) player.openMenu(inamedcontainerprovider);

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter level) {
        return HotTileEntities.TROUGH.get().create();
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
