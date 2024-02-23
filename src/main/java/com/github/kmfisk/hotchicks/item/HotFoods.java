package com.github.kmfisk.hotchicks.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class HotFoods {
    // ENTITY DROPS
    public static final FoodProperties BEEF_STEAK = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_BEEF_STEAK = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build();
    public static final FoodProperties CHICKEN_QUARTER = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final FoodProperties COOKED_CHICKEN_QUARTER = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).meat().build();
    public static final FoodProperties RABBIT_QUARTER = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_RABBIT_QUARTER = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).meat().build();

    // CROPS
    public static final FoodProperties BANANA = new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).build();
    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties CABBAGE = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(3).saturationMod(0.5F).build();
    public static final FoodProperties CUCUMBER = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties GARLIC = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties GRAPES = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties KALE = new FoodProperties.Builder().nutrition(1).saturationMod(0.5F).build();
    public static final FoodProperties KIWI = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F).build();
    public static final FoodProperties LETTUCE = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties OKRA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties PEAS = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build();
    public static final FoodProperties PEPPERS = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties TOMATOES = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();

    // TREE FRUIT
    public static final FoodProperties CITRON = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties FIG = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final FoodProperties GRAPEFRUIT = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).build();
    public static final FoodProperties LEMON = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final FoodProperties MANDARIN = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties ORANGE = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties PAPEDA = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties PEACH = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties POMEGRANATE = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).build();
    public static final FoodProperties POMELO = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final FoodProperties YUZU = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();

    // MILKS AND DAIRY
    public static final FoodProperties BOTTLED_MILK = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties PLANT_MILK = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties SOFT_CHEESE = new FoodProperties.Builder().nutrition(4).saturationMod(0.5F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties HARD_CHEESE = new FoodProperties.Builder().nutrition(5).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();

    // PROTEIN SHAKES
    public static final FoodProperties BERRY_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties CREAMSICLE_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties TROPICAL_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.LUCK, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties CHOCOLATE_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties PEACH_MANGO_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.JUMP, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties SUPERFOOD_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties KEY_LIME_PROTEIN_SHAKE = new FoodProperties.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();

    // SMOOTHIE AND COOLER
    public static final FoodProperties SMOOTHIE = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties CITRUS_COOLER = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();

    // SOUPS
    public static final FoodProperties BEEF_CHILI = new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build();
    public static final FoodProperties BEEF_STROGANOFF = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
    public static final FoodProperties BRAISED_RABBIT = new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).build();
    public static final FoodProperties CHICKEN_NOODLE_SOUP = new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).build();
    public static final FoodProperties GUMBO = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
    public static final FoodProperties POTATO_SOUP = new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).build();
    public static final FoodProperties SPLIT_PEA_SOUP = new FoodProperties.Builder().nutrition(6).saturationMod(0.7F).build();
    public static final FoodProperties VEGGIE_SOUP = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
    public static final FoodProperties WILD_MUSHROOM_SOUP = new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).build();

    // SALADS
    public static final FoodProperties CHICKEN_SALAD = new FoodProperties.Builder().nutrition(9).saturationMod(0.6F).build();
    public static final FoodProperties CHINESE_CHICKEN_SALAD = new FoodProperties.Builder().nutrition(10).saturationMod(0.5F).build();
    public static final FoodProperties DANDELION_SALAD = new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).build();
    public static final FoodProperties GARDEN_SALAD = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F).build();
    public static final FoodProperties POWER_SALAD = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).build();

    // PICKLES AND KIMCHI
    public static final FoodProperties PICKLES = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build();
    public static final FoodProperties KIMCHI = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build();

    // MEALS
    public static final FoodProperties CABBAGE_ROLLS = new FoodProperties.Builder().nutrition(13).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties CHEESE_PLATE = new FoodProperties.Builder().nutrition(8).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties FARMERS_BREAKFAST = new FoodProperties.Builder().nutrition(12).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties FRUIT_BOWL = new FoodProperties.Builder().nutrition(9).saturationMod(0.5F).build();
    public static final FoodProperties GLAZED_PORK_CHOP = new FoodProperties.Builder().nutrition(12).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties LETTUCE_WRAP = new FoodProperties.Builder().nutrition(12).saturationMod(0.7F).build();
    public static final FoodProperties OATMEAL = new FoodProperties.Builder().nutrition(7).saturationMod(0.7F).build();
    public static final FoodProperties PAVLOVA = new FoodProperties.Builder().nutrition(4).saturationMod(0.5F).build();
    public static final FoodProperties PORK_CUTLET = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties RABBIT_TACOS = new FoodProperties.Builder().nutrition(7).saturationMod(0.8F).build();
    public static final FoodProperties SALMON_RICE_BOWL = new FoodProperties.Builder().nutrition(12).saturationMod(0.7F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties SHAKSHUKA = new FoodProperties.Builder().nutrition(10).saturationMod(0.7F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
    public static final FoodProperties SHORT_RIB_BBQ = new FoodProperties.Builder().nutrition(15).saturationMod(0.9F).effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F).build();
}
