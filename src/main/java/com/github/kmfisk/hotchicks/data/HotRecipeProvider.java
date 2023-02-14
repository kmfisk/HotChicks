package com.github.kmfisk.hotchicks.data;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.item.HotItems;
import com.github.kmfisk.hotchicks.tags.HotItemTags;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HotRecipeProvider extends RecipeProvider {
    public HotRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(shapedRecipeResult(HotBlocks.FOOD_CROCK.get(), 2, ImmutableList.of("FCF", " F "),
                ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.CLAY_BALL)).put('C', Ingredient.of(Items.BOWL)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.HUTCH_GATE.get(), 2, ImmutableList.of("SCS", "SFS", "SCS"),
                ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.IRON_NUGGET)).put('S', Ingredient.of(Items.STICK)).put('C', Ingredient.of(Items.IRON_INGOT)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.HUTCH_FLOOR.get(), 4, ImmutableList.of("FFF", "FCF", "FFF"),
                ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.STICK)).put('C', Ingredient.of(HotBlocks.HUTCH_BARS.get())).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.HUTCH_BARS.get(), 16, ImmutableList.of("FCF", "FCF", "FCF"),
                ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.IRON_NUGGET)).put('C', Ingredient.of(Items.IRON_INGOT)).build()));
        consumer.accept(shapedRecipeResult(HotItems.LIVESTOCK_CRATE.get(), 1, ImmutableList.of("TTT", "PCP", "PPP"),
                ImmutableMap.<Character, Ingredient>builder().put('T', Ingredient.of(ItemTags.WOODEN_TRAPDOORS)).put('C', Ingredient.of(Blocks.CHEST)).put('P', Ingredient.of(ItemTags.PLANKS)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.NEST.get(), 2, ImmutableList.of("S S", "SFS"),
                ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.WHEAT)).put('F', Ingredient.of(Items.FEATHER)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.NEST_BOX.get(), 4, ImmutableList.of("WWW", "WSW", "WWW"),
                ImmutableMap.<Character, Ingredient>builder().put('W', Ingredient.of(ItemTags.PLANKS)).put('S', Ingredient.of(Blocks.HAY_BLOCK)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.METAL_TROUGH.get(), 2, ImmutableList.of("S S", "SSS", "N N"),
                ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.IRON_INGOT)).put('N', Ingredient.of(Items.IRON_NUGGET)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.WOODEN_TROUGH.get(), 2, ImmutableList.of("S S", "SSS", "N N"),
                ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(ItemTags.PLANKS)).put('N', Ingredient.of(Items.IRON_NUGGET)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.TRELLIS.get(), 8, ImmutableList.of("NSN", "SNS", "NSN"),
                ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.STRING)).put('N', Ingredient.of(Items.STICK)).build()));
        consumer.accept(shapedRecipeResult(HotBlocks.WATER_BOTTLE.get(), 2, ImmutableList.of("B", "I", "T"),
                ImmutableMap.<Character, Ingredient>builder().put('B', Ingredient.of(Items.GLASS_BOTTLE)).put('I', Ingredient.of(Blocks.CLAY)).put('T', Ingredient.of(Items.IRON_INGOT)).build()));

        consumer.accept(shapelessRecipeResult(HotItems.STUD_BOOK.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOOK), Ingredient.of(Items.WHEAT_SEEDS), Ingredient.of(Items.GREEN_DYE))));
        consumer.accept(shapelessRecipeResult(HotItems.GLUE.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WHEAT), Ingredient.of(HotItems.GELATIN.get()))));

        consumer.accept(namedShapelessRecipeResult("chicken_quarter_0", Items.ROTTEN_FLESH, 2,
                ImmutableList.of(Ingredient.of(HotItems.POOR_CHICKEN_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("chicken_quarter_1", HotItems.CHICKEN_QUARTER.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_CHICKEN_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("chicken_quarter_2", HotItems.CHICKEN_QUARTER.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_CHICKEN_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("chicken_quarter_3", HotItems.CHICKEN_QUARTER.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_CHICKEN_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("chicken_quarter_4", HotItems.CHICKEN_QUARTER.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_CHICKEN_CARCASS.get()))));
        cookRecipes(consumer, HotItems.COOKED_CHICKEN_QUARTER.get(), Ingredient.of(HotItems.CHICKEN_QUARTER.get()), 0.35F);
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_0", Items.ROTTEN_FLESH, 2,
                ImmutableList.of(Ingredient.of(HotItems.POOR_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_1", HotItems.RABBIT_QUARTER.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_2", HotItems.RABBIT_QUARTER.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_3", HotItems.RABBIT_QUARTER.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_4", HotItems.RABBIT_QUARTER.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_CARCASS.get()))));
        cookRecipes(consumer, HotItems.COOKED_RABBIT_QUARTER.get(), Ingredient.of(HotItems.RABBIT_QUARTER.get()), 0.35F);
        consumer.accept(namedShapelessRecipeResult("beef_steak_0", Items.ROTTEN_FLESH, 2,
                ImmutableList.of(Ingredient.of(HotItems.POOR_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_1", HotItems.BEEF_STEAK.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_2", HotItems.BEEF_STEAK.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_3", HotItems.BEEF_STEAK.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_4", HotItems.BEEF_STEAK.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_BEEF_PRIMAL.get()))));
        cookRecipes(consumer, HotItems.COOKED_BEEF_STEAK.get(), Ingredient.of(HotItems.BEEF_STEAK.get()), 0.35F);

        consumer.accept(namedShapelessRecipeResult("rabbit_hide_1", Items.LEATHER, 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_2", Items.LEATHER, 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_3", Items.LEATHER, 3,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_4", Items.LEATHER, 4,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_1", Items.LEATHER, 2,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_2", Items.LEATHER, 4,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_3", Items.LEATHER, 6,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_4", Items.LEATHER, 8,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_COWHIDE.get()))));

        consumer.accept(shapelessRecipeResult(HotBlocks.CITRON_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.CITRON.get()), Ingredient.of(HotItems.CITRON.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.FIG_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FIG.get()), Ingredient.of(HotItems.FIG.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.GRAPEFRUIT_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.GRAPEFRUIT.get()), Ingredient.of(HotItems.GRAPEFRUIT.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.LEMON_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.LEMON.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.LIME_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.LIME.get()), Ingredient.of(HotItems.LIME.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.MANDARIN_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.MANDARIN.get()), Ingredient.of(HotItems.MANDARIN.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.MANGO_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.MANGO.get()), Ingredient.of(HotItems.MANGO.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.ORANGE_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.ORANGE.get()), Ingredient.of(HotItems.ORANGE.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.PAPEDA_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.PAPEDA.get()), Ingredient.of(HotItems.PAPEDA.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.PEACH_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.PEACH.get()), Ingredient.of(HotItems.PEACH.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.POMEGRANATE_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.POMEGRANATE.get()), Ingredient.of(HotItems.POMEGRANATE.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.POMELO_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.POMELO.get()), Ingredient.of(HotItems.POMELO.get()))));
        consumer.accept(shapelessRecipeResult(HotBlocks.RED_APPLE_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(Items.APPLE), Ingredient.of(Items.APPLE))));
        consumer.accept(shapelessRecipeResult(HotBlocks.YUZU_SAPLING.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.YUZU.get()), Ingredient.of(HotItems.YUZU.get()))));

        consumer.accept(namedShapelessRecipeResult("grapefruit_hybrid_seed", HotBlocks.GRAPEFRUIT_SAPLING.get(), 2,
                ImmutableList.of(Ingredient.of(HotBlocks.ORANGE_SAPLING.get()), Ingredient.of(HotBlocks.POMELO_SAPLING.get()))));
        consumer.accept(namedShapelessRecipeResult("lemon_hybrid_seed", HotBlocks.LEMON_SAPLING.get(), 2,
                ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.ORANGE_SAPLING.get()))));
        consumer.accept(namedShapelessRecipeResult("lime_hybrid_seed", HotBlocks.LIME_SAPLING.get(), 2,
                ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.PAPEDA_SAPLING.get()))));
        consumer.accept(namedShapelessRecipeResult("orange_hybrid_seed", HotBlocks.ORANGE_SAPLING.get(), 2,
                ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.POMELO_SAPLING.get()))));
        consumer.accept(namedShapelessRecipeResult("yuzu_hybrid_seed", HotBlocks.YUZU_SAPLING.get(), 2,
                ImmutableList.of(Ingredient.of(HotBlocks.PAPEDA_SAPLING.get()), Ingredient.of(HotBlocks.MANDARIN_SAPLING.get()))));

        consumer.accept(smeltingRecipeResult(HotBlocks.BUTTER_BLOCK.get(), Ingredient.of(Items.MILK_BUCKET), 0.35F, 200));
        consumer.accept(shapelessRecipeResult(HotItems.BUTTER.get(), 4, ImmutableList.of(Ingredient.of(HotBlocks.BUTTER_BLOCK.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.BOTTLED_MILK.get(), 4, ImmutableList.of(Ingredient.of(Items.MILK_BUCKET))));
        consumer.accept(shapedRecipeResult(HotItems.PLANT_MILK.get(), 4, ImmutableList.of("PPP", "PWP", "PPP"),
                ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(HotItemTags.PLANT_MILK_INGREDIENTS)).put('W', Ingredient.of(Items.WATER_BUCKET)).build()));
        consumer.accept(shapelessRecipeResult(HotItems.CITRUS_COOLER.get(), 2,
                ImmutableList.of(Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(Items.SUGAR), Ingredient.of(Blocks.ICE))));
        consumer.accept(shapelessRecipeResult(HotItems.BERRY_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.STRAWBERRY.get()), Ingredient.of(HotItems.BLUEBERRIES.get()), Ingredient.of(Items.SWEET_BERRIES))));
        consumer.accept(shapelessRecipeResult(HotItems.CHOCOLATE_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(Items.COCOA_BEANS), Ingredient.of(Items.COCOA_BEANS), Ingredient.of(HotItems.BANANA.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.CREAMSICLE_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.ORANGE.get()), Ingredient.of(HotItems.GRAPEFRUIT.get()), Ingredient.of(HotItems.MANDARIN.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.KEY_LIME_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.LIME.get()), Ingredient.of(HotItems.CITRON.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.PEACH_MANGO_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.PEACH.get()), Ingredient.of(HotItems.MANGO.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.SUPERFOOD_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.BLUEBERRIES.get()), Ingredient.of(HotItems.KALE.get()), Ingredient.of(HotItems.OATS.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.TROPICAL_PROTEIN_SHAKE.get(), 2,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.BANANA.get()), Ingredient.of(HotItems.MANGO.get()), Ingredient.of(HotItems.KIWI.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.SMOOTHIE.get(), 2,
                ImmutableList.of(Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(Items.SUGAR), Ingredient.of(Blocks.SNOW_BLOCK))));
        consumer.accept(shapelessRecipeResult(HotItems.CABBAGE_ROLLS.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(HotItems.RICE.get()), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.CABBAGE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.CHEESE_PLATE.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.HARD_CHEESE.get()), Ingredient.of(HotItems.SOFT_CHEESE.get()), Ingredient.of(HotItems.GRAPES.get()), Ingredient.of(HotItems.POMEGRANATE.get()), Ingredient.of(HotItems.FIG.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.FARMERS_BREAKFAST.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.BREAD), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.FRUITS))));
        consumer.accept(shapelessRecipeResult(HotItems.FRUIT_BOWL.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS))));
        consumer.accept(shapelessRecipeResult(HotItems.GLAZED_PORK_CHOP.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(HotItems.PEACH.get()), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.PEPPERS.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.KIMCHI.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.LETTUCE_WRAP.get(), 2,
                ImmutableList.of(Ingredient.of(HotItemTags.COOKED_MEATS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.LETTUCE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.OATMEAL.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.OATS.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.PAVLOVA.get(), 4,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.STRAWBERRY.get()), Ingredient.of(HotItems.KIWI.get()), Ingredient.of(Items.SUGAR), Ingredient.of(HotItemTags.MILKS))));
        consumer.accept(shapelessRecipeResult(HotItems.PICKLES.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.CUCUMBER.get()), Ingredient.of(HotItems.CUCUMBER.get()), Ingredient.of(Tags.Items.SEEDS), Ingredient.of(HotItems.GARLIC.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.PORK_CUTLET.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.WHEAT), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(HotItems.CABBAGE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.RABBIT_TACOS.get(), 2,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_RABBITS), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.LIME.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.CHICKEN_SALAD.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(Items.APPLE), Ingredient.of(HotItems.GRAPES.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.CHINESE_CHICKEN_SALAD.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.MANDARIN.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.DANDELION_SALAD.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(Items.DANDELION), Ingredient.of(HotItems.GARLIC.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.GARDEN_SALAD.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.PEPPERS.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.POWER_SALAD.get(), 1,
                ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.KALE.get()), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(Tags.Items.EGGS))));
        consumer.accept(shapelessRecipeResult(HotItems.SALMON_RICE_BOWL.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_SALMONS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.YUZU.get()), Ingredient.of(Items.KELP))));
        consumer.accept(shapelessRecipeResult(HotItems.SHAKSHUKA.get(), 1,
                ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.SHORT_RIB_BBQ.get(), 1,
                ImmutableList.of(Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.APPLE), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.RICE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.BEEF_CHILI_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.TOMATO.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.BEEF_STROGANOFF_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.BUTTER.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.BRAISED_RABBIT_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_RABBITS), Ingredient.of(Items.CARROT), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItemTags.MILKS))));
        consumer.accept(shapelessRecipeResult(HotItems.CHICKEN_NOODLE_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_CHICKENS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(Items.WHEAT))));
        consumer.accept(shapelessRecipeResult(HotItems.GUMBO_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(HotItemTags.COOKED_PORKCHOPS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.OKRA.get()), Ingredient.of(HotItems.RICE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.POTATO_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.POTATO), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.SOFT_CHEESE.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.SPLIT_PEA_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItemTags.COOKED_PORKCHOPS))));
        consumer.accept(shapelessRecipeResult(HotItems.VEGGIE_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.CARROT), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.CORN.get()))));
        consumer.accept(shapelessRecipeResult(HotItems.WILD_MUSHROOM_SOUP.get(), 4,
                ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.GARLIC.get()))));
    }

    private static void cookRecipes(Consumer<IFinishedRecipe> consumer, IItemProvider iItemProvider, Ingredient recipe, float experience) {
        consumer.accept(smeltingRecipeResult(iItemProvider, recipe, experience, 200));
        consumer.accept(cookingRecipeResult("smoking", iItemProvider, recipe, experience, 100, IRecipeSerializer.SMOKING_RECIPE));
        consumer.accept(cookingRecipeResult("campfire", iItemProvider, recipe, experience, 600, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE));
    }

    public static ShapedRecipeBuilder.Result shapedRecipeResult(IItemProvider iItemProvider, int outputNum, List<String> recipe, Map<Character, Ingredient> recipeMapKey) {
        return new ShapedRecipeBuilder(iItemProvider, outputNum).new Result(
                iItemProvider.asItem().getRegistryName(),
                iItemProvider.asItem(),
                outputNum,
                HotChicks.MOD_ID,
                recipe,
                recipeMapKey,
                null,
                null
        ) {
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }
        };
    }

    public static ShapelessRecipeBuilder.Result shapelessRecipeResult(IItemProvider iItemProvider, int outputNum, List<Ingredient> recipe) {
        return namedShapelessRecipeResult(iItemProvider.asItem().getRegistryName().getPath(), iItemProvider, outputNum, recipe);
    }

    public static ShapelessRecipeBuilder.Result namedShapelessRecipeResult(String id, IItemProvider iItemProvider, int outputNum, List<Ingredient> recipe) {
        return new ShapelessRecipeBuilder.Result(
                new ResourceLocation(HotChicks.MOD_ID, id),
                iItemProvider.asItem(),
                outputNum,
                HotChicks.MOD_ID,
                recipe,
                null,
                null
        ) {
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }
        };
    }

    public static CookingRecipeBuilder.Result smeltingRecipeResult(IItemProvider iItemProvider, Ingredient recipe, float experience, int cookingTime) {
        return new CookingRecipeBuilder.Result(
                iItemProvider.asItem().getRegistryName(),
                HotChicks.MOD_ID,
                recipe,
                iItemProvider.asItem(),
                experience,
                cookingTime,
                null,
                null,
                IRecipeSerializer.SMELTING_RECIPE
        ) {
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }
        };
    }

    public static CookingRecipeBuilder.Result cookingRecipeResult(String cookingMethod, IItemProvider iItemProvider, Ingredient recipe, float experience, int cookingTime, CookingRecipeSerializer<?> cookingSerializer) {
        return new CookingRecipeBuilder.Result(
                new ResourceLocation(HotChicks.MOD_ID, iItemProvider.asItem().getRegistryName().getPath() + "_" + cookingMethod),
                HotChicks.MOD_ID,
                recipe,
                iItemProvider.asItem(),
                experience,
                cookingTime,
                null,
                null,
                cookingSerializer
        ) {
            @Override
            public JsonObject serializeAdvancement() {
                return null;
            }
        };
    }
}
