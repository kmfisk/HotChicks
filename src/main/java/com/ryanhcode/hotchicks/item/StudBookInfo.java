package com.ryanhcode.hotchicks.item;


import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class StudBookInfo implements ReadBookScreen.IBookInfo {
    private final List<String> pages;

    public StudBookInfo(HotChickenEntity entity) {
        this.pages = readPages(entity);
    }

    private static List<String> readPages(HotChickenEntity entity) {
        ChickenStats stats = entity.getStats();

        String page = "";

        page += "" + "" + TextFormatting.BOLD + "Breed: " + ChickenBreed.valueOf(entity.getBreed().toString().toUpperCase()) + TextFormatting.RESET;
        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Variant: " + entity.getVariant();
        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Child Type: " + entity.getChickType();
        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Sex: " + entity.getSex().toString() + TextFormatting.RESET;

        page += "\n\n" + TextFormatting.RESET + "" + "Carcass Quality: " + stats.carcass_quality;
        page += "\n" + TextFormatting.RESET + "" + "Growth Rate: " + stats.growth_rate;
        page += "\n" + TextFormatting.RESET + "" + "Egg Speed: " + stats.egg_speed;
        page += "\n" + TextFormatting.RESET + "" + "Tameness: " + entity.getTameness();
        page += "\n" + TextFormatting.RESET + "" + "Holding Egg: " + !entity.getMainHandItem().isEmpty();

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

    public ITextProperties getPageRaw(int p_230456_1_) {
        String s = this.pages.get(p_230456_1_);

        try {
            ITextProperties itextproperties = ITextComponent.Serializer.fromJson(s);
            if (itextproperties != null) {
                return itextproperties;
            }
        } catch (Exception exception) {
        }

        return ITextProperties.of(s);
    }
}