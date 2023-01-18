package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CropVineBlock extends VineBlock {
    public CropVineBlock() {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().strength(0.2F).sound(SoundType.VINE));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    }
}
