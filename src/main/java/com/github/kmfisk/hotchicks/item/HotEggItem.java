package com.github.kmfisk.hotchicks.item;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class HotEggItem extends Item {
    public HotEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, itemSlot, isSelected); // todo
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getOrCreateTag();
        ChatFormatting gray = ChatFormatting.GRAY;
        if (tag.contains("CarcassQuality")) {
            tooltip.add(new TextComponent(gray + "Carcass Quality: " + tag.getInt("CarcassQuality")));
            tooltip.add(new TextComponent(gray + "Growth Rate: " + tag.getInt("GrowthRate")));
            tooltip.add(new TextComponent(gray + "Egg Speed: " + tag.getInt("EggSpeed")));
            tooltip.add(new TextComponent(gray + "Tameness: " + tag.getInt("Tameness")));
            tooltip.add(new TextComponent(gray + tag.getString("Breed")));
            tooltip.add(new TextComponent(gray + "Time Left: " + tag.getInt("TimeLeft") / 20));
        }
    }
}
