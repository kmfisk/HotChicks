package com.github.kmfisk.hotchicks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HotEggItem extends Item {
    public HotEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, itemSlot, isSelected); // todo
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT tag = stack.getOrCreateTag();
        TextFormatting gray = TextFormatting.GRAY;
        if (tag.contains("CarcassQuality")) {
            tooltip.add(new StringTextComponent(gray + "Carcass Quality: " + tag.getInt("CarcassQuality")));
            tooltip.add(new StringTextComponent(gray + "Growth Rate: " + tag.getInt("GrowthRate")));
            tooltip.add(new StringTextComponent(gray + "Egg Speed: " + tag.getInt("EggSpeed")));
            tooltip.add(new StringTextComponent(gray + "Tameness: " + tag.getInt("Tameness")));
            tooltip.add(new StringTextComponent(gray + tag.getString("Breed")));
            tooltip.add(new StringTextComponent(gray + "Time Left: " + tag.getInt("TimeLeft") / 20));
        }
    }
}
