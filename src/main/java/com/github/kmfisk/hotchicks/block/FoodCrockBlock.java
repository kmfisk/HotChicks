package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class FoodCrockBlock extends ContainerBlock {
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public FoodCrockBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
