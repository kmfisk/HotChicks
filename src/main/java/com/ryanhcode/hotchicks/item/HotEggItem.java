package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.entity.stats.ChickenStats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class HotEggItem extends Item {
    public HotEggItem(Properties properties) {
        super(properties);
    }

    public static void setChickenStats(ItemStack stack, ChickenStats chickenStats) {
        setChickenStats(stack, chickenStats.tameness, chickenStats.carcassQuality, chickenStats.growthRate, chickenStats.eggSpeed);
    }

    public static void setChickenStats(ItemStack stack, int tameness, int carcass_quality, int growth_rate, int egg_speed) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putInt("Tameness", tameness);
        compoundnbt.putInt("CarcassQuality", carcass_quality);
        compoundnbt.putInt("GrowthRate", growth_rate);
        compoundnbt.putInt("EggSpeed", egg_speed);
    }

    public static void setBreed(ItemStack stack, String breed) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putString("Breed", breed);
    }

    public static void setVariant(ItemStack stack, int variant) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putInt("Variant", variant);
    }

    public static int getVariant(ItemStack item) {
        CompoundNBT compoundnbt = item.getTag();
        return compoundnbt.getInt("Variant");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getOrCreateTag();

        TextFormatting gray = TextFormatting.GRAY;
        if (tag.getBoolean("Infertile")) {
            tooltip.add(new StringTextComponent(gray + "Infertile"));
            super.appendHoverText(stack, worldIn, tooltip, flagIn);
            return;
        }
        tooltip.add(new StringTextComponent(gray + "Carcass Quality: " + tag.getInt("CarcassQuality")));
        tooltip.add(new StringTextComponent(gray + "Growth Rate: " + tag.getInt("GrowthRate")));
        tooltip.add(new StringTextComponent(gray + "Egg Speed: " + tag.getInt("EggSpeed")));
        tooltip.add(new StringTextComponent(gray + "Tameness: " + tag.getInt("Tameness")));
        tooltip.add(new StringTextComponent(gray + "Breed: " + tag.getString("Breed")));
        tooltip.add(new StringTextComponent(gray + "Time Left: " + tag.getInt("TimeLeft") / 20));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (!tag.contains("CarcassQuality")) {
            setChickenStats(stack, new ChickenStats(50, 0, 0, 0));
            tag.putInt("TimeLeft", 200);
        }

        if (!tag.contains("Variant")) {
            tag.putInt("Variant", 0);
        }
        return super.initCapabilities(stack, nbt);
    }

    public static ChickenStats getStats(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return new ChickenStats(
                tag.getInt("Tameness"),
                tag.getInt("CarcassQuality"),
                tag.getInt("GrowthRate"),
                tag.getInt("EggSpeed")
        );
    }
}
