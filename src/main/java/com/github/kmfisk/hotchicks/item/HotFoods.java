package com.github.kmfisk.hotchicks.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HotFoods {
    // ENTITY DROPS
    public static final Food BEEF_STEAK = new Food.Builder().nutrition(3).saturationMod(0.3F).meat().build();
    public static final Food COOKED_BEEF_STEAK = new Food.Builder().nutrition(8).saturationMod(0.8F).meat().build();
    public static final Food CHICKEN_QUARTER = new Food.Builder().nutrition(2).saturationMod(0.3F).effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final Food COOKED_CHICKEN_QUARTER = new Food.Builder().nutrition(6).saturationMod(0.6F).meat().build();
    public static final Food RABBIT_QUARTER = new Food.Builder().nutrition(3).saturationMod(0.3F).meat().build();
    public static final Food COOKED_RABBIT_QUARTER = new Food.Builder().nutrition(5).saturationMod(0.6F).meat().build();

    // CROPS
    public static final Food BANANA = new Food.Builder().nutrition(2).saturationMod(0.5F).build();
    public static final Food BLUEBERRY = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final Food CABBAGE = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final Food CORN = new Food.Builder().nutrition(3).saturationMod(0.5F).build();
    public static final Food CUCUMBER = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final Food GARLIC = new Food.Builder().nutrition(1).saturationMod(0.1F).build();
    public static final Food GRAPES = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final Food KALE = new Food.Builder().nutrition(1).saturationMod(0.5F).build();
    public static final Food KIWI = new Food.Builder().nutrition(1).saturationMod(0.3F).build();
    public static final Food LETTUCE = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final Food OKRA = new Food.Builder().nutrition(1).saturationMod(0.1F).build();
    public static final Food PEAS = new Food.Builder().nutrition(1).saturationMod(0.2F).build();
    public static final Food PEPPERS = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final Food STRAWBERRY = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final Food TOMATOES = new Food.Builder().nutrition(2).saturationMod(0.2F).build();

    // TREE FRUIT
    public static final Food CITRON = new Food.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final Food FIG = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static final Food GRAPEFRUIT = new Food.Builder().nutrition(6).saturationMod(0.3F).build();
    public static final Food LEMON = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final Food LIME = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static final Food MANDARIN = new Food.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final Food MANGO = new Food.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final Food ORANGE = new Food.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final Food PAPEDA = new Food.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final Food PEACH = new Food.Builder().nutrition(3).saturationMod(0.3F).build();
    public static final Food POMEGRANATE = new Food.Builder().nutrition(5).saturationMod(0.3F).build();
    public static final Food POMELO = new Food.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final Food YUZU = new Food.Builder().nutrition(3).saturationMod(0.2F).build();

    // MILKS AND DAIRY
    public static final Food BOTTLED_MILK = new Food.Builder().nutrition(2).saturationMod(0.2F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food PLANT_MILK = new Food.Builder().nutrition(2).saturationMod(0.2F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food SOFT_CHEESE = new Food.Builder().nutrition(4).saturationMod(0.5F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food HARD_CHEESE = new Food.Builder().nutrition(5).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();

    // PROTEIN SHAKES
    public static final Food BERRY_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.DIG_SPEED, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food CREAMSICLE_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food TROPICAL_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.LUCK, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food CHOCOLATE_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEALTH_BOOST, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food PEACH_MANGO_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.JUMP, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food SUPERFOOD_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 1200, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food KEY_LIME_PROTEIN_SHAKE = new Food.Builder().nutrition(6).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.REGENERATION, 300, 1), 1.0F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();

    // SMOOTHIE AND COOLER
    public static final Food SMOOTHIE = new Food.Builder().nutrition(6).saturationMod(0.3F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food CITRUS_COOLER = new Food.Builder().nutrition(6).saturationMod(0.3F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();

    // SOUPS
    public static final Food BEEF_CHILI_SOUP = new Food.Builder().nutrition(5).saturationMod(0.6F).build();
    public static final Food BEEF_STROGANOFF_SOUP = new Food.Builder().nutrition(6).saturationMod(0.8F).build();
    public static final Food BRAISED_RABBIT_SOUP = new Food.Builder().nutrition(5).saturationMod(0.8F).build();
    public static final Food CHICKEN_NOODLE_SOUP = new Food.Builder().nutrition(3).saturationMod(0.6F).build();
    public static final Food GUMBO_SOUP = new Food.Builder().nutrition(6).saturationMod(0.8F).build();
    public static final Food POTATO_SOUP = new Food.Builder().nutrition(5).saturationMod(0.8F).build();
    public static final Food SPLIT_PEA_SOUP = new Food.Builder().nutrition(6).saturationMod(0.7F).build();
    public static final Food VEGGIE_SOUP = new Food.Builder().nutrition(5).saturationMod(0.4F).build();
    public static final Food WILD_MUSHROOM_SOUP = new Food.Builder().nutrition(5).saturationMod(0.5F).build();

    // SALADS
    public static final Food CHICKEN_SALAD = new Food.Builder().nutrition(9).saturationMod(0.6F).build();
    public static final Food CHINESE_CHICKEN_SALAD = new Food.Builder().nutrition(10).saturationMod(0.5F).build();
    public static final Food DANDELION_SALAD = new Food.Builder().nutrition(8).saturationMod(0.5F).build();
    public static final Food GARDEN_SALAD = new Food.Builder().nutrition(10).saturationMod(0.8F).build();
    public static final Food POWER_SALAD = new Food.Builder().nutrition(12).saturationMod(0.8F).build();

    // PICKLES AND KIMCHI
    public static final Food PICKLES = new Food.Builder().nutrition(3).saturationMod(0.4F).build();
    public static final Food KIMCHI = new Food.Builder().nutrition(4).saturationMod(0.4F).build();

    // MEALS
    public static final Food CABBAGE_ROLLS = new Food.Builder().nutrition(13).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food CHEESE_PLATE = new Food.Builder().nutrition(8).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food FARMERS_BREAKFAST = new Food.Builder().nutrition(12).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food FRUIT_BOWL = new Food.Builder().nutrition(9).saturationMod(0.5F).build();
    public static final Food GLAZED_PORK_CHOP = new Food.Builder().nutrition(12).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food LETTUCE_WRAP = new Food.Builder().nutrition(12).saturationMod(0.7F).build();
    public static final Food OATMEAL = new Food.Builder().nutrition(7).saturationMod(0.7F).build();
    public static final Food PAVLOVA = new Food.Builder().nutrition(4).saturationMod(0.5F).build();
    public static final Food PORK_CUTLET = new Food.Builder().nutrition(12).saturationMod(0.8F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food RABBIT_TACOS = new Food.Builder().nutrition(7).saturationMod(0.8F).build();
    public static final Food SALMON_RICE_BOWL = new Food.Builder().nutrition(12).saturationMod(0.7F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food SHAKSHUKA = new Food.Builder().nutrition(10).saturationMod(0.7F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
    public static final Food SHORT_RIB_BBQ = new Food.Builder().nutrition(15).saturationMod(0.9F).effect(() -> new EffectInstance(Effects.HEAL, 1, 1), 1.0F).build();
}
