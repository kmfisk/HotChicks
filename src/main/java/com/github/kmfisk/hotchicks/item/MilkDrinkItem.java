package com.github.kmfisk.hotchicks.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.Iterator;

public class MilkDrinkItem extends DrinkItem {
    public MilkDrinkItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if (!level.isClientSide) livingEntity.removeAllEffects();
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
