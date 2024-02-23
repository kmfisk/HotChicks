package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WildCropBlock extends BushBlock {
    protected final Supplier<? extends Item> item;

    public WildCropBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.item = item;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return item.get().getDefaultInstance();
    }
}
