package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
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
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class CheeseMoldBlock extends Block implements ISidedInventoryProvider {
    public static final Tags.IOptionalNamedTag<Item> MILKS = ItemTags.createOptional(new ResourceLocation("forge", "milks"));
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    private static final VoxelShape INSIDE_1 = box(3.0D, 3.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape SHAPE_1 = VoxelShapes.join(VoxelShapes.block(), INSIDE_1, IBooleanFunction.ONLY_FIRST);

    public CheeseMoldBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return state.getValue(AGE) < 6 ? SHAPE_1 : VoxelShapes.block();
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, IBlockReader level, BlockPos pos) {
        return state.getValue(AGE) < 6 ? INSIDE_1 : VoxelShapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        int age = state.getValue(AGE);
        return age > 0 && age != 15;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        if (random.nextBoolean()) {
            int age = state.getValue(AGE);
            if (age == 5 || age == 14)
                level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, state.cycle(AGE), 2);
            level.updateNeighbourForOutputSignal(pos, this);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        int age = state.getValue(AGE);
        if (age == 0 && Ingredient.of(MILKS).test(itemStack)) {
            if (!level.isClientSide) {
                if (!player.abilities.instabuild) itemStack.shrink(1);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, state.setValue(AGE, 1), 2);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            return ActionResultType.sidedSuccess(level.isClientSide);

        } else if (age > 2) {
            ItemStack cheese;
            if (age != 15) {
                cheese = new ItemStack(HotItems.SOFT_CHEESE.get());
            } else cheese = new ItemStack(HotItems.HARD_CHEESE.get());

            if (!player.inventory.add(cheese)) player.drop(cheese, false);
            level.playSound(null, pos, SoundEvents.COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            level.updateNeighbourForOutputSignal(pos, this);

            return ActionResultType.sidedSuccess(level.isClientSide);

        } else return ActionResultType.PASS;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, World level, BlockPos pos) {
        return state.getValue(AGE);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isPathfindable(BlockState state, IBlockReader level, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public ISidedInventory getContainer(BlockState state, IWorld level, BlockPos pos) {
        int age = state.getValue(AGE);
        if (age == 15) return new CheeseInventory(state, level, pos, new ItemStack(HotItems.HARD_CHEESE.get()));
        else if (age > 2) return new CheeseInventory(state, level, pos, new ItemStack(HotItems.SOFT_CHEESE.get()));
        else return age > 0 ? new MilkInventory() : new EmptyInventory(state, level, pos);
    }

    static class EmptyInventory extends Inventory implements ISidedInventory {
        private final BlockState state;
        private final IWorld level;
        private final BlockPos pos;
        private boolean changed;

        public EmptyInventory(BlockState pState, IWorld pLevel, BlockPos pPos) {
            super(1);
            this.state = pState;
            this.level = pLevel;
            this.pos = pPos;
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int[] getSlotsForFace(Direction side) {
            return side != Direction.DOWN ? new int[]{0} : new int[0];
        }

        @Override
        public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
            return !changed && pDirection != Direction.DOWN && Ingredient.of(MILKS).test(pItemStack);
        }

        @Override
        public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
            return false;
        }

        @Override
        public void setChanged() {
            ItemStack stack = getItem(0);
            if (!stack.isEmpty()) {
                changed = true;
                level.setBlock(pos, state.setValue(AGE, 1), 3);
                removeItemNoUpdate(0);
            }
        }
    }

    static class MilkInventory extends Inventory implements ISidedInventory {
        public MilkInventory() {
            super(1);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int[] getSlotsForFace(Direction pSide) {
            return new int[0];
        }

        @Override
        public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
            return false;
        }

        @Override
        public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
            return false;
        }
    }

    static class CheeseInventory extends Inventory implements ISidedInventory {
        private final BlockState state;
        private final IWorld level;
        private final BlockPos pos;
        private boolean changed;

        public CheeseInventory(BlockState state, IWorld level, BlockPos pos, ItemStack stack) {
            super(stack);
            this.state = state;
            this.level = level;
            this.pos = pos;
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int[] getSlotsForFace(Direction side) {
            return side == Direction.DOWN ? new int[]{0} : new int[0];
        }

        @Override
        public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
            return false;
        }

        @Override
        public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
            return !changed && direction == Direction.DOWN && (stack.getItem() == HotItems.SOFT_CHEESE.get() || stack.getItem() == HotItems.HARD_CHEESE.get());
        }

        @Override
        public void setChanged() {
            level.setBlock(pos, state.setValue(AGE, 0), 3);
            changed = true;
        }
    }
}
