package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import java.util.function.Supplier;

public abstract class HotCropBlock extends CropBlock {
    private final Supplier<? extends Item> item;

    public HotCropBlock(BlockBehaviour.Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.item = item;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return item.get();
    }

    @Override
    public abstract VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);

    @Override
    public abstract IntegerProperty getAgeProperty();

    @Override
    public abstract int getMaxAge();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }
}
