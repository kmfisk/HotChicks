package com.ryanhcode.hotchicks.item;

import com.google.common.collect.ImmutableList;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class StudBookItem extends Item {

    public StudBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {

        if(target instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) target;
            Minecraft.getInstance().displayGuiScreen(new ReadBookScreen(new StudBookInfo(chicken)));
        }
        return super.itemInteractionForEntity(stack, player, target, hand);
    }
}
