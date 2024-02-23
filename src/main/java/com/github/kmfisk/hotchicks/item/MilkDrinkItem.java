package com.github.kmfisk.hotchicks.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class MilkDrinkItem extends DrinkItem {
    public MilkDrinkItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) livingEntity.removeAllEffects();
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
