package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

public class CheeseMoldBlock extends Block implements WorldlyContainerHolder {
    public static final Tags.IOptionalNamedTag<Item> MILKS = ItemTags.createOptional(new ResourceLocation("forge", "milks"));
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    private static final VoxelShape INSIDE_1 = box(3.0D, 3.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape SHAPE_1 = Shapes.join(Shapes.block(), INSIDE_1, BooleanOp.ONLY_FIRST);

    public CheeseMoldBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(AGE) < 6 ? SHAPE_1 : Shapes.block();
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(AGE) < 6 ? INSIDE_1 : Shapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        int age = state.getValue(AGE);
        return age > 0 && age != 15;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (random.nextBoolean()) {
            int age = state.getValue(AGE);
            if (age == 5 || age == 14)
                level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, state.cycle(AGE), 2);
            level.updateNeighbourForOutputSignal(pos, this);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        int age = state.getValue(AGE);
        if (age == 0 && Ingredient.of(MILKS).test(itemStack)) {
            if (!level.isClientSide) {
                if (!player.abilities.instabuild) itemStack.shrink(1);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, state.setValue(AGE, 1), 2);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);

        } else if (age > 2) {
            ItemStack cheese;
            if (age != 15) {
                cheese = new ItemStack(HotItems.SOFT_CHEESE.get());
            } else cheese = new ItemStack(HotItems.HARD_CHEESE.get());

            if (!player.inventory.add(cheese)) player.drop(cheese, false);
            level.playSound(null, pos, SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            level.updateNeighbourForOutputSignal(pos, this);

            return InteractionResult.sidedSuccess(level.isClientSide);

        } else return InteractionResult.PASS;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(AGE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor level, BlockPos pos) {
        int age = state.getValue(AGE);
        if (age == 15) return new CheeseInventory(state, level, pos, new ItemStack(HotItems.HARD_CHEESE.get()));
        else if (age > 2) return new CheeseInventory(state, level, pos, new ItemStack(HotItems.SOFT_CHEESE.get()));
        else return age > 0 ? new MilkInventory() : new EmptyInventory(state, level, pos);
    }

    static class EmptyInventory extends SimpleContainer implements WorldlyContainer {
        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public EmptyInventory(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
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

    static class MilkInventory extends SimpleContainer implements WorldlyContainer {
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

    static class CheeseInventory extends SimpleContainer implements WorldlyContainer {
        private final BlockState state;
        private final LevelAccessor level;
        private final BlockPos pos;
        private boolean changed;

        public CheeseInventory(BlockState state, LevelAccessor level, BlockPos pos, ItemStack stack) {
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
