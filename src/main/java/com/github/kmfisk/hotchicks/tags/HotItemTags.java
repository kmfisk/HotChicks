package com.github.kmfisk.hotchicks.tags;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;

public class HotItemTags {
    public static final Tags.IOptionalNamedTag<Item> FRUIT_SALAD_INGREDIENTS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "fruit_salad_ingredients"));
    public static final Tags.IOptionalNamedTag<Item> PLANT_MILK_INGREDIENTS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "plant_milk_ingredients"));

    public static final Tags.IOptionalNamedTag<Item> CITRUS_FRUITS = ItemTags.createOptional(new ResourceLocation("forge", "fruits/citrus"));
    public static final Tags.IOptionalNamedTag<Item> FRUITS = ItemTags.createOptional(new ResourceLocation("forge", "fruits"));
    public static final Tags.IOptionalNamedTag<Item> MILKS = ItemTags.createOptional(new ResourceLocation("forge", "milks"));

    public static final Tags.IOptionalNamedTag<Item> COOKED_MEATS = ItemTags.createOptional(new ResourceLocation("forge", "cooked_meats"));
    public static final Tags.IOptionalNamedTag<Item> COOKED_CHICKENS = ItemTags.createOptional(new ResourceLocation("forge", "cooked_meats/cooked_chicken"));
    public static final Tags.IOptionalNamedTag<Item> COOKED_PORKCHOPS = ItemTags.createOptional(new ResourceLocation("forge", "cooked_meats/cooked_porkchop"));
    public static final Tags.IOptionalNamedTag<Item> RAW_BEEFS = ItemTags.createOptional(new ResourceLocation("forge", "raw_meats/beef"));
    public static final Tags.IOptionalNamedTag<Item> RAW_CHICKENS = ItemTags.createOptional(new ResourceLocation("forge", "raw_meats/chicken"));
    public static final Tags.IOptionalNamedTag<Item> RAW_PORKCHOPS = ItemTags.createOptional(new ResourceLocation("forge", "raw_meats/porkchop"));
    public static final Tags.IOptionalNamedTag<Item> RAW_RABBITS = ItemTags.createOptional(new ResourceLocation("forge", "raw_meats/rabbit"));
    public static final Tags.IOptionalNamedTag<Item> RAW_SALMONS = ItemTags.createOptional(new ResourceLocation("forge", "raw_fishes/salmon"));
}
