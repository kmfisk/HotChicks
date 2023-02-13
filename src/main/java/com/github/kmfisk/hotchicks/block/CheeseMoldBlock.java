package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class CheeseMoldBlock extends Block {
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
}
