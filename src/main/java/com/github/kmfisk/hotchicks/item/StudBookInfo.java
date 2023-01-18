package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
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

        page += "" + "" + TextFormatting.BOLD + "Breed: " + ChickenBreeds.valueOf(entity.getBreedFromVariant(entity.getVariant()).toString().toUpperCase()) + TextFormatting.RESET;
        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Variant: " + entity.getVariant();
//        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Child Type: " + entity.getChickType();
        page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Sex: " + entity.getSex().toString() + TextFormatting.RESET;

        page += "\n\n" + TextFormatting.RESET + "" + "Carcass Quality: " + stats.carcassQuality;
        page += "\n" + TextFormatting.RESET + "" + "Growth Rate: " + stats.growthRate;
        page += "\n" + TextFormatting.RESET + "" + "Egg Speed: " + stats.eggSpeed;
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