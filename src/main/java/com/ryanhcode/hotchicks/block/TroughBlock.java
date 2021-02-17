package com.ryanhcode.hotchicks.block;

import com.mojang.serialization.Codec;
import com.ryanhcode.hotchicks.registry.TileEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TroughBlock extends ContainerBlock {
    public static final EnumProperty<TroughFillType> CONTAINS = EnumProperty.create("contains", TroughFillType.class);

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;

    private static final VoxelShape INSIDE_SINGLE = makeCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_NORTH = makeCuboidShape(2.0D, 4.0D, 0.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_SOUTH = makeCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 16.0D);
    private static final VoxelShape INSIDE_WEST = makeCuboidShape(0.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape INSIDE_EAST = makeCuboidShape(2.0D, 4.0D, 2.0D, 16.0D, 16.0D, 14.0D);

    protected static final VoxelShape SHAPE_SINGLE =
            VoxelShapes.combineAndSimplify(
                    makeCuboidShape(0,0,0,16,12,16),
                    VoxelShapes.or(
                            makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_SINGLE),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_NORTH =
            VoxelShapes.combineAndSimplify(
                    makeCuboidShape(0,0,0,16,12,16),
                    VoxelShapes.or(
                            makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_NORTH),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_SOUTH =
            VoxelShapes.combineAndSimplify(
                    makeCuboidShape(0,0,0,16,12,16),
                    VoxelShapes.or(
                            makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_SOUTH),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_WEST =
            VoxelShapes.combineAndSimplify(
                    makeCuboidShape(0,0,0,16,12,16),
                    VoxelShapes.or(
                            makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_WEST),
                    IBooleanFunction.ONLY_FIRST);

    protected static final VoxelShape SHAPE_EAST =
            VoxelShapes.combineAndSimplify(
                    makeCuboidShape(0,0,0,16,12,16),
                    VoxelShapes.or(
                            makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D),
                            makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D),
                            makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
                            INSIDE_EAST),
                    IBooleanFunction.ONLY_FIRST);



    //MERGERS
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>> INVENTORY_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<IInventory>>() {
        public Optional<IInventory> func_225539_a_(TroughTileEntity p_225539_1_, TroughTileEntity p_225539_2_) {
            return Optional.of(new DoubleSidedInventory(p_225539_1_, p_225539_2_));
        }

        public Optional<IInventory> func_225538_a_(TroughTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<IInventory> func_225537_b_() {
            return Optional.empty();
        }
    };
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>> CONTAINER_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>>() {
        public Optional<INamedContainerProvider> func_225539_a_(final TroughTileEntity p_225539_1_, final TroughTileEntity p_225539_2_) {
            final IInventory iinventory = new DoubleSidedInventory(p_225539_1_, p_225539_2_);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                    if (p_225539_1_.canOpen(p_createMenu_3_) && p_225539_2_.canOpen(p_createMenu_3_)) {
                        p_225539_1_.fillWithLoot(p_createMenu_2_.player);
                        p_225539_2_.fillWithLoot(p_createMenu_2_.player);
                        return TroughContainer.createGenericDouble(p_createMenu_1_, p_createMenu_2_, iinventory);
                    } else {
                        return null;
                    }
                }

                public ITextComponent getDisplayName() {
                    if (p_225539_1_.hasCustomName()) {
                        return p_225539_1_.getDisplayName();
                    } else {
                        return (ITextComponent)(p_225539_2_.hasCustomName() ? p_225539_2_.getDisplayName() : new StringTextComponent("Large Trough"));
                    }
                }
            });
        }

        public Optional<INamedContainerProvider> func_225538_a_(TroughTileEntity p_225538_1_) {
            return Optional.of(p_225538_1_);
        }

        public Optional<INamedContainerProvider> func_225537_b_() {
            return Optional.empty();
        }
    };
    public static TileEntityMerger.Type getChestMergerType(BlockState state) {
        ChestType chesttype = state.get(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return TileEntityMerger.Type.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
        }
    }

    @Nullable
    public static IInventory getChestInventory(TroughBlock chest, BlockState state, World world, BlockPos pos, boolean override) {
        return chest.combine(state, world, pos, override).<Optional<IInventory>>apply(INVENTORY_MERGER).orElse((IInventory)null);
    }

    public TileEntityMerger.ICallbackWrapper<? extends TroughTileEntity> combine(BlockState state, World world, BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) {
            bipredicate = (worldIn, posIn) -> {
                return false;
            };
        } else {
            bipredicate = ChestBlock::isBlocked;
        }

        return TileEntityMerger.func_226924_a_(TileEntityRegistry.TROUGH.get(), TroughBlock::getChestMergerType, TroughBlock::getDirectionToAttached, FACING, state, world, pos, bipredicate);
    }





    public TroughBlock(Properties properties) {
        super(properties);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        if (facingState.isIn(this) && facing.getAxis().isHorizontal()) {
            ChestType chesttype = facingState.get(TYPE);
            if (stateIn.get(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && stateIn.get(FACING) == facingState.get(FACING) && getDirectionToAttached(facingState) == facing.getOpposite()) {
                return stateIn.with(TYPE, chesttype.opposite());
            }
        } else if (getDirectionToAttached(stateIn) == facing) {
            return stateIn.with(TYPE, ChestType.SINGLE);
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(TYPE) == ChestType.SINGLE) {
            return SHAPE_SINGLE;
        } else {
            switch(getDirectionToAttached(state)) {
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
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TroughTileEntity) {
            TroughTileEntity te = ((TroughTileEntity)tileentity);
            te.barrelTick();
        }

    }

    /**
     * Returns a facing pointing from the given state to its attached double chest
     */
    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(TYPE) == ChestType.LEFT ? direction.rotateY() : direction.rotateYCCW();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getPlacementHorizontalFacing().getOpposite();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        boolean flag = context.hasSecondaryUseForPlayer();
        Direction direction1 = context.getFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.rotateYCCW() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            if (direction == this.getDirectionToAttach(context, direction.rotateY())) {
                chesttype = ChestType.LEFT;
            } else if (direction == this.getDirectionToAttach(context, direction.rotateYCCW())) {
                chesttype = ChestType.RIGHT;
            }
        }

        return this.getDefaultState().with(FACING, direction).with(TYPE, chesttype).with(CONTAINS, TroughFillType.NONE);
    }

    /**
     * Returns facing pointing to a chest to form a double chest with, null otherwise
     */
    @Nullable
    private Direction getDirectionToAttach(BlockItemUseContext context, Direction direction) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(direction));
        return blockstate.isIn(this) && blockstate.get(TYPE) == ChestType.SINGLE ? blockstate.get(FACING) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack
            stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TroughTileEntity) {
                ((TroughTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof IInventory) {
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }


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


    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return this.combine(state, worldIn, pos, false).<Optional<INamedContainerProvider>>apply(CONTAINER_MERGER).orElse((INamedContainerProvider)null);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, CONTAINS);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.isEmpty()) {
            return ActionResultType.PASS;
        } else {
            Item item = itemstack.getItem();
            TroughFillType contains = state.get(CONTAINS);
            if (item == Items.WATER_BUCKET && contains == TroughFillType.NONE) {
                if (!player.abilities.isCreativeMode) {
                    player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                }

                worldIn.setBlockState(pos, state.with(CONTAINS, TroughFillType.WATER));
                if(state.get(TYPE) != ChestType.SINGLE){
                    BlockPos connectedSlot = pos.add(new BlockPos(state.get(FACING).getDirectionVec()).rotate(state.get(TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                    BlockState connectedState = worldIn.getBlockState(connectedSlot);
                    worldIn.setBlockState(connectedSlot, connectedState.with(CONTAINS, TroughFillType.WATER));
                }

                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (item == Items.BUCKET && contains == TroughFillType.WATER) {
                player.setHeldItem(handIn, new ItemStack(Items.WATER_BUCKET));
                worldIn.setBlockState(pos, state.with(CONTAINS, TroughFillType.NONE));

                TroughFillType troughFillType = state.get(TroughBlock.CONTAINS);


                if(state.get(TroughBlock.TYPE) != ChestType.SINGLE && troughFillType == TroughFillType.WATER){
                    BlockPos connectedSlot = pos.add(new BlockPos(state.get(TroughBlock.FACING).getDirectionVec()).rotate(state.get(TroughBlock.TYPE) == ChestType.LEFT ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90));

                    BlockState connectedState = worldIn.getBlockState(connectedSlot);
                    worldIn.setBlockState(connectedSlot, connectedState.with(TroughBlock.CONTAINS, TroughFillType.NONE));
                }

                worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (contains != TroughFillType.WATER) {
                if (worldIn.isRemote) {
                    return ActionResultType.SUCCESS;
                } else {
                    INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
                    if (inamedcontainerprovider != null) {
                        player.openContainer(inamedcontainerprovider);
                    }

                    return ActionResultType.CONSUME;
                }
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }
    }

    @Nullable
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TroughTileEntity();
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
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


}
