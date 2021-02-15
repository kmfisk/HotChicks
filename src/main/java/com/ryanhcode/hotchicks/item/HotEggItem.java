package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.potion.Effect;
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

    public static void setStats(ItemStack stack, ChickenStats chickenStats){
        setStats(stack, chickenStats.carcass_quality, chickenStats.growth_rate, chickenStats.egg_speed);
    }

    public static void setStats(ItemStack stack, int carcass_quality, int growth_rate, int egg_speed){
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putInt("carcass_quality", carcass_quality);
        compoundnbt.putInt("growth_rate", growth_rate);
        compoundnbt.putInt("egg_speed", egg_speed);
        compoundnbt.putInt("time_left", 48000);
    }


    public static void setBreed(ItemStack stack, String breed){
        CompoundNBT compoundnbt = stack.getTag();
        compoundnbt.putString("breed", breed);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getOrCreateTag();

        TextFormatting gray = TextFormatting.GRAY;
        if(tag.getBoolean("infertile")) {
            tooltip.add(new StringTextComponent(gray + "Infertile"));
            super.addInformation(stack, worldIn, tooltip, flagIn);
            return;
        }
        tooltip.add(new StringTextComponent(gray + "Carcass quality: " + tag.getInt("carcass_quality")));
        tooltip.add(new StringTextComponent(gray + "Growth rate: " + tag.getInt("growth_rate")));
        tooltip.add(new StringTextComponent(gray + "Egg speed: " + tag.getInt("egg_speed")));
        tooltip.add(new StringTextComponent(gray + "Seconds Left: " + tag.getInt("time_left")/20));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT tag = stack.getOrCreateTag();
        if(!tag.contains("carcass_quality")) {
            setBreed(stack, "Junglefowl");
            setStats(stack, new ChickenStats(0, 0, 0));
        }
        return super.initCapabilities(stack, nbt);
    }

    public static void tick(ItemStack stack){
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt("time_left", tag.getInt("time_left") - 1);
    }
}
