package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class StudBookItem extends Item {

    public StudBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (target instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) target;
            Minecraft.getInstance().setScreen(new ReadBookScreen(new StudBookInfo(chicken)));
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }
}
