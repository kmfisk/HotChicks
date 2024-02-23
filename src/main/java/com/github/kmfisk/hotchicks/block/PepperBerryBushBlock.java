package com.github.kmfisk.hotchicks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PepperBerryBushBlock extends BerryBushBlock {
    public static final EnumProperty<PepperType> VARIANT = EnumProperty.create("variant", PepperType.class);

    public PepperBerryBushBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties, item);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(VARIANT, PepperType.DEFAULT));
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) pLevel.setBlock(pPos, pState.setValue(VARIANT, PepperType.getRandom()), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, VARIANT);
    }
}
