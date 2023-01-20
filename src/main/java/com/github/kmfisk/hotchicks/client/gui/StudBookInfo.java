package com.github.kmfisk.hotchicks.client.gui;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.stats.Stats;
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

    public StudBookInfo(LivestockEntity entity) {
        this.pages = readPages(entity);
    }

    private static List<String> readPages(LivestockEntity entity) {
        Stats stats;
        String page = "";

        if (entity instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) entity;
            stats = chicken.getStats();

            page += "" + "" + TextFormatting.BOLD + "Breed: " + ChickenBreeds.valueOf(chicken.getBreedFromVariant(chicken.getVariant()).toString().toUpperCase()) + TextFormatting.RESET;
            page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Variant: " + chicken.getVariant();
//            page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Child Type: " + chicken.getChickType();
            page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Sex: " + chicken.getSex().toString() + TextFormatting.RESET;

            page += "\n\n" + TextFormatting.RESET + "" + "Carcass Quality: " + stats.carcassQuality;
            page += "\n" + TextFormatting.RESET + "" + "Growth Rate: " + stats.growthRate;
            page += "\n" + TextFormatting.RESET + "" + "Egg Speed: " + stats.eggSpeed;
            page += "\n" + TextFormatting.RESET + "" + "Tameness: " + chicken.getTameness();
            page += "\n" + TextFormatting.RESET + "" + "Holding Egg: " + !chicken.getMainHandItem().isEmpty();
        }

        if (entity instanceof HotRabbitEntity) {
            HotRabbitEntity rabbit = (HotRabbitEntity) entity;
            stats = rabbit.getStats();

            page += "" + "" + TextFormatting.BOLD + "Breed: " + RabbitBreeds.valueOf(rabbit.getBreedFromVariant(rabbit.getVariant()).toString().toUpperCase()) + TextFormatting.RESET;
            page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Variant: " + rabbit.getVariant();
            page += "\n" + TextFormatting.RESET + "" + TextFormatting.BOLD + "" + "Sex: " + rabbit.getSex().toString() + TextFormatting.RESET;

            page += "\n\n" + TextFormatting.RESET + "" + "Carcass Quality: " + stats.carcassQuality;
            page += "\n\n" + TextFormatting.RESET + "" + "Hide Quality: " + stats.hideQuality;
            page += "\n" + TextFormatting.RESET + "" + "Growth Rate: " + stats.growthRate;
            page += "\n" + TextFormatting.RESET + "" + "Litter Size: " + stats.litterSize;
            page += "\n" + TextFormatting.RESET + "" + "Tameness: " + rabbit.getTameness();
        }

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