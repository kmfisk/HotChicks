package com.ryanhcode.hotchicks.item;


import com.google.common.collect.ImmutableList;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class StudBookInfo implements ReadBookScreen.IBookInfo {
    private final List<String> pages;

    public StudBookInfo(HotChickenEntity entity) {
        this.pages = func_216921_b(entity);
    }

    private static List<String> func_216921_b(HotChickenEntity entity) {
        ChickenStats stats = entity.getStats();

        String page = "";

        page+=""  + "" + TextFormatting.BOLD + "Breed: " + ChickenBreed.valueOf(entity.getBreed().toUpperCase()) + TextFormatting.RESET;
        page+="\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Variant: " + entity.getVariant();
        page+="\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Child Type: " + entity.getChildType();
        page+="\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Sex: " + entity.getSex().toString() + TextFormatting.RESET;

        page+="\n\n" + TextFormatting.RESET + "" + "Carcass Quality: " + stats.carcass_quality;
        page+="\n" + TextFormatting.RESET + "" + "Growth Rate: " + stats.growth_rate;
        page+="\n" + TextFormatting.RESET + "" + "Egg Speed: " + stats.egg_speed;
        page+="\n" + TextFormatting.RESET + "" + "Tameness: " + entity.getTameness();
        page+="\n" + TextFormatting.RESET + "" + "Holding Egg: " + !entity.getHeldItemMainhand().isEmpty();

        ArrayList<String> pages = new ArrayList<>();
        pages.add(page);
        return pages;
    }

    /**
     * Returns the size of the book
     */
    public int getPageCount() {
        return this.pages.size();
    }

    public ITextProperties func_230456_a_(int p_230456_1_) {
        String s = this.pages.get(p_230456_1_);

        try {
            ITextProperties itextproperties = ITextComponent.Serializer.getComponentFromJson(s);
            if (itextproperties != null) {
                return itextproperties;
            }
        } catch (Exception exception) {
        }

        return ITextProperties.func_240652_a_(s);
    }
}