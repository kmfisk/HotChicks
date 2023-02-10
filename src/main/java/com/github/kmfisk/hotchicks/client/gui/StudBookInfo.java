package com.github.kmfisk.hotchicks.client.gui;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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
        String page = "";
        String name = entity.getName().getString();

        page += TextFormatting.BOLD + name;
        page += "\n" + entity.getReadableBreed() + " " + entity.getSex().getLocalizedName().getString();
        page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.variant", entity.getVariant())).getString();
        page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.health", entity.getHealth(), entity.getMaxHealth())).getString();
        page += "\n" + TextFormatting.RESET + "";

        page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.tameness", entity.getTameness())).getString();
        page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.carcass_quality", entity.getCarcassQuality())).getString();
        page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.growth_rate", entity.getGrowthRate())).getString();

        if (entity instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) entity;
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.egg_speed", chicken.getEggSpeed())).getString();
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.egg_color", chicken.getBreedFromVariant().getEggColor().getDescription())).getString();
            page += "\n" + TextFormatting.RESET + "" + "Holding Egg: " + !chicken.getMainHandItem().isEmpty();

        } else if (entity instanceof HotRabbitEntity) {
            HotRabbitEntity rabbit = (HotRabbitEntity) entity;
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.hide_quality", rabbit.getHideQuality())).getString();
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.litter_size", rabbit.getLitterSize())).getString();
            page += "\n" + TextFormatting.RESET + "" + "Pregnant: " + rabbit.isPregnant();

        } else if (entity instanceof HotCowEntity) {
            HotCowEntity cow = (HotCowEntity) entity;
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.hide_quality", cow.getHideQuality())).getString();
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.milk_yield", cow.getMilkYield())).getString();
            page += "\n" + TextFormatting.RESET + "" + "Pregnant: " + cow.isPregnant();
        }

        page += "\n";

        if (entity.getHunger().isLow() && entity.getThirst().isLow())
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.unhappy", name)).getString();
        else if (entity.getHunger().isLow())
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.hungry", name)).getString();
        else if (entity.getThirst().isLow())
            page += "\n" + (new TranslationTextComponent("data." + HotChicks.MOD_ID + ".stud_book.thirsty", name)).getString();

        ArrayList<String> pages = new ArrayList<>();
        pages.add(page);
        return pages;
    }

    @Override
    public int getPageCount() {
        return this.pages.size();
    }

    @Override
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