package com.ryanhcode.hotchicks.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class FruitLeavesBlock extends LeavesBlock {
    public FruitLeavesBlock() {
        super(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(FruitLeavesBlock::ocelotOrParrot).isSuffocating(FruitLeavesBlock::never).isViewBlocking(FruitLeavesBlock::never));
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState p_235441_0_, IBlockReader p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
        return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
    }
}
