package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class PepperBerryBushBlock extends BerryBushBlock {
    public static final EnumProperty<PepperType> VARIANT = EnumProperty.create("variant", PepperType.class);

    public PepperBerryBushBlock(Properties properties, Supplier<? extends Item> item) {
        super(properties, item);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(VARIANT, PepperType.DEFAULT));
    }

    @Override
    public void setPlacedBy(World pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) pLevel.setBlock(pPos, pState.setValue(VARIANT, PepperType.getRandom()), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, VARIANT);
    }
}
