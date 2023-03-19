package com.github.kmfisk.hotchicks.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MilkDrinkItem extends DrinkItem {
    public MilkDrinkItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World level, LivingEntity livingEntity) {
        if (!level.isClientSide) livingEntity.curePotionEffects(stack);
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
