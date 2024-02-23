package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.client.gui.StudBookInfo;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class StudBookItem extends Item {

    public StudBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof LivestockEntity) {
            LivestockEntity livestock = (LivestockEntity) target;
            if (player.level.isClientSide)
                this.openStudBook(livestock);
        }
        return super.interactLivingEntity(stack, player, target, hand);
    }

    @OnlyIn(Dist.CLIENT)
    public void openStudBook(LivestockEntity livestock) {
        Minecraft.getInstance().setScreen(new BookViewScreen(new StudBookInfo(livestock)));
    }
}
