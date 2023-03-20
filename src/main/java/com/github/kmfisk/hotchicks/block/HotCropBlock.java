package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public abstract class HotCropBlock extends CropsBlock {
    private final Supplier<? extends Item> item;

    public HotCropBlock(AbstractBlock.Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.item = item;
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return item.get();
    }

    @Override
    public abstract VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context);

    @Override
    public abstract IntegerProperty getAgeProperty();

    @Override
    public abstract int getMaxAge();

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }
}
