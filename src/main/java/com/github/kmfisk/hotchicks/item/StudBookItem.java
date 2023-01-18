package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StudBookItem extends Item {

    public StudBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (target instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) target;
            if (player.level.isClientSide)
                this.openStudBook(chicken);
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }

    @OnlyIn(Dist.CLIENT)
    public void openStudBook(HotChickenEntity chicken) {
        Minecraft.getInstance().setScreen(new ReadBookScreen(new StudBookInfo(chicken)));
    }
}