package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.FoodCrockTileEntity;
import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FoodCrockBlock extends BaseEntityBlock {
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");
    private static final VoxelShape SHAPE = Block.box(4.5D, 0.0D, 4.5D, 11.5D, 3.0D, 11.5D);

    public FoodCrockBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FILLED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        else {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof FoodCrockTileEntity) {
                FoodCrockTileEntity foodCrockTileEntity = (FoodCrockTileEntity) entity;
                player.openMenu(foodCrockTileEntity);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof Container) {
                Containers.dropContents(level, pos, (Container) tileEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter reader) {
        return HotTileEntities.FOOD_CROCK.get().create();
    }
}
