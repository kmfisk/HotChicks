package com.ryanhcode.hotchicks.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HotFoods {
    // ENTITY DROPS
    public static final Food CHICKEN_QUARTER = (new Food.Builder()).nutrition(2).saturationMod(0.3F).effect(() -> new EffectInstance(Effects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final Food COOKED_CHICKEN_QUARTER = (new Food.Builder()).nutrition(6).saturationMod(0.6F).meat().build();

    // CROPS
    public static final Food LETTUCE = (new Food.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final Food GARLIC = (new Food.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final Food GRAPES = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final Food KIWI = (new Food.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final Food TOMATOES = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final Food PEAS = (new Food.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final Food STRAWBERRY = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final Food BLUEBERRY = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final Food PEPPERS = (new Food.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final Food OKRA = (new Food.Builder()).nutrition(1).saturationMod(0.3F).build();
    public static final Food CORN = (new Food.Builder()).nutrition(3).saturationMod(0.3F).build();

    // TREE FRUIT
    public static final Food PEACH = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food MANGO = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food POMEGRANATE = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food FIG = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food CITRON = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food POMELO = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food MANDARIN = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food PAPEDA = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food ORANGE = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food LEMON = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food GRAPEFRUIT = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food LIME = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food YUZU = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
}
