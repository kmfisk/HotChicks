package com.github.kmfisk.hotchicks.tags;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class HotItemTags {
    public static final TagKey<Item> FRUIT_SALAD_INGREDIENTS = ItemTags.create(new ResourceLocation(HotChicks.MOD_ID, "fruit_salad_ingredients"));
    public static final TagKey<Item> PLANT_MILK_INGREDIENTS = ItemTags.create(new ResourceLocation(HotChicks.MOD_ID, "plant_milk_ingredients"));

    public static final TagKey<Item> CITRUS_FRUITS = ItemTags.create(new ResourceLocation("forge", "fruits/citrus"));
    public static final TagKey<Item> FRUITS = ItemTags.create(new ResourceLocation("forge", "fruits"));
    public static final TagKey<Item> MILKS = ItemTags.create(new ResourceLocation("forge", "milks"));

    public static final TagKey<Item> COOKED_MEATS = ItemTags.create(new ResourceLocation("forge", "cooked_meats"));
    public static final TagKey<Item> COOKED_CHICKENS = ItemTags.create(new ResourceLocation("forge", "cooked_meats/cooked_chicken"));
    public static final TagKey<Item> COOKED_PORKCHOPS = ItemTags.create(new ResourceLocation("forge", "cooked_meats/cooked_porkchop"));
    public static final TagKey<Item> RAW_BEEFS = ItemTags.create(new ResourceLocation("forge", "raw_meats/beef"));
    public static final TagKey<Item> RAW_CHICKENS = ItemTags.create(new ResourceLocation("forge", "raw_meats/chicken"));
    public static final TagKey<Item> RAW_PORKCHOPS = ItemTags.create(new ResourceLocation("forge", "raw_meats/porkchop"));
    public static final TagKey<Item> RAW_RABBITS = ItemTags.create(new ResourceLocation("forge", "raw_meats/rabbit"));
    public static final TagKey<Item> RAW_SALMONS = ItemTags.create(new ResourceLocation("forge", "raw_fishes/salmon"));
}
