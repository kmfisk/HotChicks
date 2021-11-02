package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.entity.base.ChickenStats;
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

    public static void setStats(ItemStack stack, ChickenStats chickenStats) {
        setStats(stack, chickenStats.carcass_quality, chickenStats.growth_rate, chickenStats.egg_speed, 50);
    }

    public static void setStats(ItemStack stack, int carcass_quality, int growth_rate, int egg_speed, int tameness) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putInt("carcass_quality", carcass_quality);
        compoundnbt.putInt("growth_rate", growth_rate);
        compoundnbt.putInt("egg_speed", egg_speed);
        compoundnbt.putInt("tameness", tameness);
    }


    public static void setBreed(ItemStack stack, String breed) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putString("breed", breed);
    }

    public static void setVariant(ItemStack stack, String variant) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putString("variant", variant);
    }

    public static void setTameness(ItemStack stack, int v) {
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putInt("tameness", v);
    }

    public static int getTameness(ItemStack item) {
        CompoundNBT compoundnbt = item.getTag();
        return compoundnbt.getInt("tameness");
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getOrCreateTag();

        TextFormatting gray = TextFormatting.GRAY;
        if (tag.getBoolean("infertile")) {
            tooltip.add(new StringTextComponent(gray + "Infertile"));
            super.appendHoverText(stack, worldIn, tooltip, flagIn);
            return;
        }
        tooltip.add(new StringTextComponent(gray + "Carcass quality: " + tag.getInt("carcass_quality")));
        tooltip.add(new StringTextComponent(gray + "Growth rate: " + tag.getInt("growth_rate")));
        tooltip.add(new StringTextComponent(gray + "Egg speed: " + tag.getInt("egg_speed")));
        tooltip.add(new StringTextComponent(gray + "Tameness: " + tag.getInt("tameness")));
        tooltip.add(new StringTextComponent(gray + "Breed: " + tag.getString("breed")));
        tooltip.add(new StringTextComponent(gray + "Time Left: " + tag.getInt("time_left") / 20));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (!tag.contains("carcass_quality")) {
            //setBreed(stack, "Junglefowl");
            setStats(stack, new ChickenStats(0, 0, 0));
            tag.putInt("time_left", 200);
        }

        if (!tag.contains("variant")) {
            tag.putString("variant", "not_set");
        }
        return super.initCapabilities(stack, nbt);
    }

    public static void tick(ItemStack stack) {

    }

    public static ChickenStats getStats(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        return new ChickenStats(
                tag.getInt("carcass_quality"),
                tag.getInt("growth_rate"),
                tag.getInt("egg_speed")
        );
    }
}
