package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class WildCropBlock extends BushBlock {
    protected final Supplier<? extends Item> item;

    public WildCropBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties);
        this.item = item;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader level, BlockPos pos, BlockState state) {
        return item.get().getDefaultInstance();
    }
}
