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
        shapedRecipeResult(consumer, HotBlocks.CHEESE_MOLD.get(), 1, ImmutableList.of("NBN"), ImmutableMap.<Character, Ingredient>builder().put('N', Ingredient.of(Items.IRON_NUGGET)).put('B', Ingredient.of(Blocks.BARREL)).build());
        shapedRecipeResult(consumer, HotBlocks.FOOD_CROCK.get(), 2, ImmutableList.of("FCF", " F "), ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.CLAY_BALL)).put('C', Ingredient.of(Items.BOWL)).build());
        shapedRecipeResult(consumer, HotBlocks.HUTCH_GATE.get(), 2, ImmutableList.of("SCS", "SFS", "SCS"), ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.IRON_NUGGET)).put('S', Ingredient.of(Items.STICK)).put('C', Ingredient.of(Items.IRON_INGOT)).build());
        shapedRecipeResult(consumer, HotBlocks.HUTCH_FLOOR.get(), 4, ImmutableList.of("FFF", "FCF", "FFF"), ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.STICK)).put('C', Ingredient.of(HotBlocks.HUTCH_BARS.get())).build());
        shapedRecipeResult(consumer, HotBlocks.HUTCH_BARS.get(), 16, ImmutableList.of("FCF", "FCF", "FCF"), ImmutableMap.<Character, Ingredient>builder().put('F', Ingredient.of(Items.IRON_NUGGET)).put('C', Ingredient.of(Items.IRON_INGOT)).build());
        shapedRecipeResult(consumer, HotItems.LIVESTOCK_CRATE.get(), 1, ImmutableList.of("TTT", "PCP", "PPP"), ImmutableMap.<Character, Ingredient>builder().put('T', Ingredient.of(ItemTags.WOODEN_TRAPDOORS)).put('C', Ingredient.of(Blocks.CHEST)).put('P', Ingredient.of(ItemTags.PLANKS)).build());
        shapedRecipeResult(consumer, HotBlocks.NEST.get(), 2, ImmutableList.of("S S", "SFS"), ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.WHEAT)).put('F', Ingredient.of(Items.FEATHER)).build());
        shapedRecipeResult(consumer, HotBlocks.NEST_BOX.get(), 4, ImmutableList.of("WWW", "WSW", "WWW"), ImmutableMap.<Character, Ingredient>builder().put('W', Ingredient.of(ItemTags.PLANKS)).put('S', Ingredient.of(Blocks.HAY_BLOCK)).build());
        shapedRecipeResult(consumer, HotBlocks.METAL_TROUGH.get(), 2, ImmutableList.of("S S", "SSS", "N N"), ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.IRON_INGOT)).put('N', Ingredient.of(Items.IRON_NUGGET)).build());
        shapedRecipeResult(consumer, HotBlocks.WOODEN_TROUGH.get(), 2, ImmutableList.of("S S", "SSS", "N N"), ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(ItemTags.PLANKS)).put('N', Ingredient.of(Items.IRON_NUGGET)).build());
        shapedRecipeResult(consumer, HotBlocks.TRELLIS.get(), 8, ImmutableList.of("NSN", "SNS", "NSN"), ImmutableMap.<Character, Ingredient>builder().put('S', Ingredient.of(Items.STRING)).put('N', Ingredient.of(Items.STICK)).build());
        shapedRecipeResult(consumer, HotBlocks.WATER_BOTTLE.get(), 2, ImmutableList.of("B", "I", "T"), ImmutableMap.<Character, Ingredient>builder().put('B', Ingredient.of(Items.GLASS_BOTTLE)).put('I', Ingredient.of(Blocks.CLAY)).put('T', Ingredient.of(Items.IRON_INGOT)).build());

        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("blue").get(), Ingredient.of(HotItems.BLUEBERRIES.get()), true, HotBlocks.STAIRS.get("blue").get(), HotBlocks.SLABS.get("blue").get(), HotBlocks.PRESSURE_PLATES.get("blue").get(), HotBlocks.BUTTONS.get("blue").get(), HotBlocks.FENCES.get("blue").get(), HotBlocks.FENCE_GATES.get("blue").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("green").get(), Ingredient.of(HotItems.KALE.get()), true, HotBlocks.STAIRS.get("green").get(), HotBlocks.SLABS.get("green").get(), HotBlocks.PRESSURE_PLATES.get("green").get(), HotBlocks.BUTTONS.get("green").get(), HotBlocks.FENCES.get("green").get(), HotBlocks.FENCE_GATES.get("green").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("red").get(), Ingredient.of(HotItems.STRAWBERRY.get()), true, HotBlocks.STAIRS.get("red").get(), HotBlocks.SLABS.get("red").get(), HotBlocks.PRESSURE_PLATES.get("red").get(), HotBlocks.BUTTONS.get("red").get(), HotBlocks.FENCES.get("red").get(), HotBlocks.FENCE_GATES.get("red").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("white").get(), Ingredient.of(Tags.Items.DYES_WHITE), true, HotBlocks.STAIRS.get("white").get(), HotBlocks.SLABS.get("white").get(), HotBlocks.PRESSURE_PLATES.get("white").get(), HotBlocks.BUTTONS.get("white").get(), HotBlocks.FENCES.get("white").get(), HotBlocks.FENCE_GATES.get("white").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("dark").get(), Ingredient.of(Tags.Items.DYES_BLACK), false, HotBlocks.STAIRS.get("dark").get(), HotBlocks.SLABS.get("dark").get(), HotBlocks.PRESSURE_PLATES.get("dark").get(), HotBlocks.BUTTONS.get("dark").get(), HotBlocks.FENCES.get("dark").get(), HotBlocks.FENCE_GATES.get("dark").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("gray").get(), Ingredient.of(Tags.Items.DYES_GRAY), false, HotBlocks.STAIRS.get("gray").get(), HotBlocks.SLABS.get("gray").get(), HotBlocks.PRESSURE_PLATES.get("gray").get(), HotBlocks.BUTTONS.get("gray").get(), HotBlocks.FENCES.get("gray").get(), HotBlocks.FENCE_GATES.get("gray").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("tan").get(), Ingredient.of(Tags.Items.DYES_LIGHT_GRAY), false, HotBlocks.STAIRS.get("tan").get(), HotBlocks.SLABS.get("tan").get(), HotBlocks.PRESSURE_PLATES.get("tan").get(), HotBlocks.BUTTONS.get("tan").get(), HotBlocks.FENCES.get("tan").get(), HotBlocks.FENCE_GATES.get("tan").get());

        shapelessRecipeResult(consumer, HotItems.STUD_BOOK.get(), 1, ImmutableList.of(Ingredient.of(Items.BOOK), Ingredient.of(Items.WHEAT_SEEDS), Ingredient.of(Items.GREEN_DYE)));
        shapelessRecipeResult(consumer, HotItems.GLUE.get(), 4, ImmutableList.of(Ingredient.of(Items.WHEAT), Ingredient.of(HotItems.GELATIN.get())));

        namedShapelessRecipeResult(consumer, "chicken_quarter_0", Items.ROTTEN_FLESH, 2, ImmutableList.of(Ingredient.of(HotItems.POOR_CHICKEN_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "chicken_quarter_1", HotItems.CHICKEN_QUARTER.get(), 1, ImmutableList.of(Ingredient.of(HotItems.FAIR_CHICKEN_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "chicken_quarter_2", HotItems.CHICKEN_QUARTER.get(), 2, ImmutableList.of(Ingredient.of(HotItems.GOOD_CHICKEN_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "chicken_quarter_3", HotItems.CHICKEN_QUARTER.get(), 3, ImmutableList.of(Ingredient.of(HotItems.CHOICE_CHICKEN_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "chicken_quarter_4", HotItems.CHICKEN_QUARTER.get(), 4, ImmutableList.of(Ingredient.of(HotItems.PRIME_CHICKEN_CARCASS.get())));
        shapelessRecipeResult(consumer, Items.CHICKEN, 1, ImmutableList.of(Ingredient.of(HotItems.CHICKEN_QUARTER.get())));
        shapelessRecipeResult(consumer, Items.COOKED_CHICKEN, 1, ImmutableList.of(Ingredient.of(HotItems.COOKED_CHICKEN_QUARTER.get())));
        cookRecipes(consumer, HotItems.COOKED_CHICKEN_QUARTER.get(), Ingredient.of(HotItems.CHICKEN_QUARTER.get()), 0.35F);
        namedShapelessRecipeResult(consumer, "rabbit_quarter_0", Items.ROTTEN_FLESH, 2, ImmutableList.of(Ingredient.of(HotItems.POOR_RABBIT_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "rabbit_quarter_1", HotItems.RABBIT_QUARTER.get(), 1, ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "rabbit_quarter_2", HotItems.RABBIT_QUARTER.get(), 2, ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "rabbit_quarter_3", HotItems.RABBIT_QUARTER.get(), 3, ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_CARCASS.get())));
        namedShapelessRecipeResult(consumer, "rabbit_quarter_4", HotItems.RABBIT_QUARTER.get(), 4, ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_CARCASS.get())));
        shapelessRecipeResult(consumer, Items.RABBIT, 1, ImmutableList.of(Ingredient.of(HotItems.RABBIT_QUARTER.get())));
        shapelessRecipeResult(consumer, Items.COOKED_RABBIT, 1, ImmutableList.of(Ingredient.of(HotItems.COOKED_RABBIT_QUARTER.get())));
        cookRecipes(consumer, HotItems.COOKED_RABBIT_QUARTER.get(), Ingredient.of(HotItems.RABBIT_QUARTER.get()), 0.35F);
        namedShapelessRecipeResult(consumer, "beef_steak_0", Items.ROTTEN_FLESH, 4, ImmutableList.of(Ingredient.of(HotItems.POOR_BEEF_PRIMAL.get())));
        namedShapelessRecipeResult(consumer, "beef_steak_1", HotItems.BEEF_STEAK.get(), 2, ImmutableList.of(Ingredient.of(HotItems.FAIR_BEEF_PRIMAL.get())));
        namedShapelessRecipeResult(consumer, "beef_steak_2", HotItems.BEEF_STEAK.get(), 4, ImmutableList.of(Ingredient.of(HotItems.GOOD_BEEF_PRIMAL.get())));
        namedShapelessRecipeResult(consumer, "beef_steak_3", HotItems.BEEF_STEAK.get(), 6, ImmutableList.of(Ingredient.of(HotItems.CHOICE_BEEF_PRIMAL.get())));
        namedShapelessRecipeResult(consumer, "beef_steak_4", HotItems.BEEF_STEAK.get(), 8, ImmutableList.of(Ingredient.of(HotItems.PRIME_BEEF_PRIMAL.get())));
        shapelessRecipeResult(consumer, Items.BEEF, 1, ImmutableList.of(Ingredient.of(HotItems.BEEF_STEAK.get())));
        shapelessRecipeResult(consumer, Items.COOKED_BEEF, 1, ImmutableList.of(Ingredient.of(HotItems.COOKED_BEEF_STEAK.get())));
        cookRecipes(consumer, HotItems.COOKED_BEEF_STEAK.get(), Ingredient.of(HotItems.BEEF_STEAK.get()), 0.35F);

        namedShapelessRecipeResult(consumer, "rabbit_hide_1", Items.RABBIT_HIDE, 1, ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_HIDE.get())));
        namedShapelessRecipeResult(consumer, "rabbit_hide_2", Items.RABBIT_HIDE, 2, ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_HIDE.get())));
        namedShapelessRecipeResult(consumer, "rabbit_hide_3", Items.RABBIT_HIDE, 3, ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_HIDE.get())));
        namedShapelessRecipeResult(consumer, "rabbit_hide_4", Items.RABBIT_HIDE, 4, ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_HIDE.get())));
        namedShapelessRecipeResult(consumer, "cowhide_1", Items.LEATHER, 2, ImmutableList.of(Ingredient.of(HotItems.FAIR_COWHIDE.get())));
        namedShapelessRecipeResult(consumer, "cowhide_2", Items.LEATHER, 4, ImmutableList.of(Ingredient.of(HotItems.GOOD_COWHIDE.get())));
        namedShapelessRecipeResult(consumer, "cowhide_3", Items.LEATHER, 6, ImmutableList.of(Ingredient.of(HotItems.CHOICE_COWHIDE.get())));
        namedShapelessRecipeResult(consumer, "cowhide_4", Items.LEATHER, 8, ImmutableList.of(Ingredient.of(HotItems.PRIME_COWHIDE.get())));

        shapelessRecipeResult(consumer, HotBlocks.CITRON_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.CITRON.get()), Ingredient.of(HotItems.CITRON.get())));
        shapelessRecipeResult(consumer, HotBlocks.FIG_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.FIG.get()), Ingredient.of(HotItems.FIG.get())));
        shapelessRecipeResult(consumer, HotBlocks.GRAPEFRUIT_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.GRAPEFRUIT.get()), Ingredient.of(HotItems.GRAPEFRUIT.get())));
        shapelessRecipeResult(consumer, HotBlocks.LEMON_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.LEMON.get())));
        shapelessRecipeResult(consumer, HotBlocks.LIME_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.LIME.get()), Ingredient.of(HotItems.LIME.get())));
        shapelessRecipeResult(consumer, HotBlocks.MANDARIN_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.MANDARIN.get()), Ingredient.of(HotItems.MANDARIN.get())));
        shapelessRecipeResult(consumer, HotBlocks.MANGO_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.MANGO.get()), Ingredient.of(HotItems.MANGO.get())));
        shapelessRecipeResult(consumer, HotBlocks.ORANGE_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.ORANGE.get()), Ingredient.of(HotItems.ORANGE.get())));
        shapelessRecipeResult(consumer, HotBlocks.PAPEDA_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.PAPEDA.get()), Ingredient.of(HotItems.PAPEDA.get())));
        shapelessRecipeResult(consumer, HotBlocks.PEACH_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.PEACH.get()), Ingredient.of(HotItems.PEACH.get())));
        shapelessRecipeResult(consumer, HotBlocks.POMEGRANATE_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.POMEGRANATE.get()), Ingredient.of(HotItems.POMEGRANATE.get())));
        shapelessRecipeResult(consumer, HotBlocks.POMELO_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.POMELO.get()), Ingredient.of(HotItems.POMELO.get())));
        shapelessRecipeResult(consumer, HotBlocks.RED_APPLE_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(Items.APPLE), Ingredient.of(Items.APPLE)));
        shapelessRecipeResult(consumer, HotBlocks.YUZU_SAPLING.get(), 1, ImmutableList.of(Ingredient.of(HotItems.YUZU.get()), Ingredient.of(HotItems.YUZU.get())));

        namedShapelessRecipeResult(consumer, "grapefruit_hybrid_seed", HotBlocks.GRAPEFRUIT_SAPLING.get(), 2, ImmutableList.of(Ingredient.of(HotBlocks.ORANGE_SAPLING.get()), Ingredient.of(HotBlocks.POMELO_SAPLING.get())));
        namedShapelessRecipeResult(consumer, "lemon_hybrid_seed", HotBlocks.LEMON_SAPLING.get(), 2, ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.ORANGE_SAPLING.get())));
        namedShapelessRecipeResult(consumer, "lime_hybrid_seed", HotBlocks.LIME_SAPLING.get(), 2, ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.PAPEDA_SAPLING.get())));
        namedShapelessRecipeResult(consumer, "orange_hybrid_seed", HotBlocks.ORANGE_SAPLING.get(), 2, ImmutableList.of(Ingredient.of(HotBlocks.CITRON_SAPLING.get()), Ingredient.of(HotBlocks.POMELO_SAPLING.get())));
        namedShapelessRecipeResult(consumer, "yuzu_hybrid_seed", HotBlocks.YUZU_SAPLING.get(), 2, ImmutableList.of(Ingredient.of(HotBlocks.PAPEDA_SAPLING.get()), Ingredient.of(HotBlocks.MANDARIN_SAPLING.get())));

        shapelessRecipeResult(consumer, HotItems.BOTTLED_MILK.get(), 4, ImmutableList.of(Ingredient.of(Items.MILK_BUCKET)));
        shapedRecipeResult(consumer, HotItems.PLANT_MILK.get(), 4, ImmutableList.of("PPP", "PWP", "PPP"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(HotItemTags.PLANT_MILK_INGREDIENTS)).put('W', Ingredient.of(Items.WATER_BUCKET)).build());
        shapelessRecipeResult(consumer, HotItems.CITRUS_COOLER.get(), 2, ImmutableList.of(Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(HotItemTags.CITRUS_FRUITS), Ingredient.of(Items.SUGAR), Ingredient.of(Blocks.ICE)));
        shapelessRecipeResult(consumer, HotItems.BERRY_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.STRAWBERRY.get()), Ingredient.of(HotItems.BLUEBERRIES.get()), Ingredient.of(Items.SWEET_BERRIES)));
        shapelessRecipeResult(consumer, HotItems.CHOCOLATE_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(Items.COCOA_BEANS), Ingredient.of(Items.COCOA_BEANS), Ingredient.of(HotItems.BANANA.get())));
        shapelessRecipeResult(consumer, HotItems.CREAMSICLE_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.ORANGE.get()), Ingredient.of(HotItems.GRAPEFRUIT.get()), Ingredient.of(HotItems.MANDARIN.get())));
        shapelessRecipeResult(consumer, HotItems.KEY_LIME_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.LIME.get()), Ingredient.of(HotItems.CITRON.get())));
        shapelessRecipeResult(consumer, HotItems.PEACH_MANGO_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.LEMON.get()), Ingredient.of(HotItems.PEACH.get()), Ingredient.of(HotItems.MANGO.get())));
        shapelessRecipeResult(consumer, HotItems.SUPERFOOD_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.BLUEBERRIES.get()), Ingredient.of(HotItems.KALE.get()), Ingredient.of(HotItems.OATS.get())));
        shapelessRecipeResult(consumer, HotItems.TROPICAL_PROTEIN_SHAKE.get(), 2, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.BANANA.get()), Ingredient.of(HotItems.MANGO.get()), Ingredient.of(HotItems.KIWI.get())));
        shapelessRecipeResult(consumer, HotItems.SMOOTHIE.get(), 2, ImmutableList.of(Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(Items.SUGAR), Ingredient.of(Blocks.SNOW_BLOCK)));
        shapelessRecipeResult(consumer, HotItems.CABBAGE_ROLLS.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(HotItems.RICE.get()), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.CABBAGE.get())));
        shapelessRecipeResult(consumer, HotItems.CHEESE_PLATE.get(), 2, ImmutableList.of(Ingredient.of(HotItems.HARD_CHEESE.get()), Ingredient.of(HotItems.SOFT_CHEESE.get()), Ingredient.of(HotItems.GRAPES.get()), Ingredient.of(HotItems.POMEGRANATE.get()), Ingredient.of(HotItems.FIG.get())));
        shapelessRecipeResult(consumer, HotItems.FARMERS_BREAKFAST.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.BREAD), Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItemTags.FRUITS)));
        shapelessRecipeResult(consumer, HotItems.FRUIT_BOWL.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS), Ingredient.of(HotItemTags.FRUIT_SALAD_INGREDIENTS)));
        shapelessRecipeResult(consumer, HotItems.GLAZED_PORK_CHOP.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(HotItems.PEACH.get()), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.PEPPERS.get())));
        shapelessRecipeResult(consumer, HotItems.KIMCHI.get(), 3, ImmutableList.of(Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get())));
        shapelessRecipeResult(consumer, HotItems.LETTUCE_WRAP.get(), 2, ImmutableList.of(Ingredient.of(HotItemTags.COOKED_MEATS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.LETTUCE.get())));
        shapelessRecipeResult(consumer, HotItems.OATMEAL.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.FRUITS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.OATS.get())));
        shapelessRecipeResult(consumer, HotItems.PAVLOVA.get(), 4, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.STRAWBERRY.get()), Ingredient.of(HotItems.KIWI.get()), Ingredient.of(Items.SUGAR), Ingredient.of(HotItemTags.MILKS)));
        shapelessRecipeResult(consumer, HotItems.PICKLES.get(), 3, ImmutableList.of(Ingredient.of(HotItems.CUCUMBER.get()), Ingredient.of(HotItems.CUCUMBER.get()), Ingredient.of(Items.WHEAT_SEEDS), Ingredient.of(HotItems.GARLIC.get())));
        shapelessRecipeResult(consumer, HotItems.PORK_CUTLET.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_PORKCHOPS), Ingredient.of(Items.WHEAT), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(HotItems.CABBAGE.get())));
        shapelessRecipeResult(consumer, HotItems.RABBIT_TACOS.get(), 2, ImmutableList.of(Ingredient.of(HotItemTags.RAW_RABBITS), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.LIME.get())));
        shapelessRecipeResult(consumer, HotItems.CHICKEN_SALAD.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(Items.APPLE), Ingredient.of(HotItems.GRAPES.get())));
        shapelessRecipeResult(consumer, HotItems.CHINESE_CHICKEN_SALAD.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.MANDARIN.get())));
        shapelessRecipeResult(consumer, HotItems.DANDELION_SALAD.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(Items.DANDELION), Ingredient.of(HotItems.GARLIC.get())));
        shapelessRecipeResult(consumer, HotItems.GARDEN_SALAD.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.CORN.get()), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.PEPPERS.get())));
        shapelessRecipeResult(consumer, HotItems.POWER_SALAD.get(), 1, ImmutableList.of(Ingredient.of(Items.BOWL), Ingredient.of(HotItems.LETTUCE.get()), Ingredient.of(HotItems.KALE.get()), Ingredient.of(HotItems.CABBAGE.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(Tags.Items.EGGS)));
        shapelessRecipeResult(consumer, HotItems.SALMON_RICE_BOWL.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_SALMONS), Ingredient.of(HotItems.RICE.get()), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.YUZU.get()), Ingredient.of(Items.KELP)));
        shapelessRecipeResult(consumer, HotItems.SHAKSHUKA.get(), 1, ImmutableList.of(Ingredient.of(Tags.Items.EGGS), Ingredient.of(Tags.Items.EGGS), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get())));
        shapelessRecipeResult(consumer, HotItems.SHORT_RIB_BBQ.get(), 1, ImmutableList.of(Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.APPLE), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.RICE.get())));
        shapelessRecipeResult(consumer, HotItems.BEEF_CHILI.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.TOMATO.get())));
        shapelessRecipeResult(consumer, HotItems.BEEF_STROGANOFF.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.RAW_BEEFS), Ingredient.of(HotItemTags.MILKS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.BUTTER.get())));
        shapelessRecipeResult(consumer, HotItems.BRAISED_RABBIT.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_RABBITS), Ingredient.of(Items.CARROT), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItemTags.MILKS)));
        shapelessRecipeResult(consumer, HotItems.CHICKEN_NOODLE_SOUP.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.RAW_CHICKENS), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(Items.WHEAT)));
        shapelessRecipeResult(consumer, HotItems.GUMBO.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(HotItemTags.COOKED_CHICKENS), Ingredient.of(HotItemTags.COOKED_PORKCHOPS), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEPPERS.get()), Ingredient.of(HotItems.OKRA.get()), Ingredient.of(HotItems.RICE.get())));
        shapelessRecipeResult(consumer, HotItems.POTATO_SOUP.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.POTATO), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItemTags.MILKS), Ingredient.of(HotItems.SOFT_CHEESE.get())));
        shapelessRecipeResult(consumer, HotItems.SPLIT_PEA_SOUP.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.CARROT), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItemTags.COOKED_PORKCHOPS)));
        shapelessRecipeResult(consumer, HotItems.VEGGIE_SOUP.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Items.CARROT), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.GARLIC.get()), Ingredient.of(HotItems.PEAS.get()), Ingredient.of(HotItems.TOMATO.get()), Ingredient.of(HotItems.CORN.get())));
        shapelessRecipeResult(consumer, HotItems.WILD_MUSHROOM_SOUP.get(), 4, ImmutableList.of(Ingredient.of(Items.WATER_BUCKET), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Tags.Items.MUSHROOMS), Ingredient.of(Items.CARROT), Ingredient.of(Items.POTATO), Ingredient.of(HotItems.GARLIC.get())));
    }

    private static void woodVariantBlocksRecipes(Consumer<IFinishedRecipe> consumer, IItemProvider planks, Ingredient dye, boolean basic, IItemProvider stairs, IItemProvider slab, IItemProvider pressurePlate, IItemProvider button, IItemProvider fence, IItemProvider fenceGate) {
        if (basic) shapelessRecipeResult(consumer, planks, 4, ImmutableList.of(dye, Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.PLANKS)));
        else shapelessRecipeResult(consumer, planks, 8, ImmutableList.of(dye, Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.PLANKS), Ingredient.of(Items.IRON_NUGGET), Ingredient.of(Items.IRON_NUGGET), Ingredient.of(ItemTags.PLANKS), Ingredient.of(ItemTags.PLANKS), Ingredient.of(Items.IRON_NUGGET), Ingredient.of(Items.IRON_NUGGET)));
        shapedRecipeResult(consumer, stairs, 4, ImmutableList.of("P  ", "PP ", "PPP"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(planks)).build());
        shapedRecipeResult(consumer, slab, 6, ImmutableList.of("PPP"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(planks)).build());
        shapedRecipeResult(consumer, pressurePlate, 1, ImmutableList.of("PP"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(planks)).build());
        shapelessRecipeResult(consumer, button, 1, ImmutableList.of(Ingredient.of(planks)));
        shapedRecipeResult(consumer, fence, 3, ImmutableList.of("P#P", "P#P"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(planks)).put('#', Ingredient.of(Items.STICK)).build());
        shapedRecipeResult(consumer, fenceGate, 1, ImmutableList.of("#P#", "#P#"), ImmutableMap.<Character, Ingredient>builder().put('P', Ingredient.of(planks)).put('#', Ingredient.of(Items.STICK)).build());
    }

    private static void cookRecipes(Consumer<IFinishedRecipe> consumer, IItemProvider result, Ingredient recipe, float experience) {
        consumer.accept(smeltingRecipeResult(result, recipe, experience, 200));
        consumer.accept(cookingRecipeResult("smoking", result, recipe, experience, 100, IRecipeSerializer.SMOKING_RECIPE));
        consumer.accept(cookingRecipeResult("campfire", result, recipe, experience, 600, IRecipeSerializer.CAMPFIRE_COOKING_RECIPE));
    }

    public static void shapedRecipeResult(Consumer<IFinishedRecipe> consumer, IItemProvider iItemProvider, int outputNum, List<String> recipe, Map<Character, Ingredient> recipeMapKey) {
        consumer.accept(new ShapedRecipeBuilder(iItemProvider, outputNum).new Result(
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
        });
    }

    public static void shapelessRecipeResult(Consumer<IFinishedRecipe> consumer, IItemProvider iItemProvider, int outputNum, List<Ingredient> recipe) {
        namedShapelessRecipeResult(consumer, iItemProvider.asItem().getRegistryName().getPath(), iItemProvider, outputNum, recipe);
    }

    public static void namedShapelessRecipeResult(Consumer<IFinishedRecipe> consumer, String id, IItemProvider iItemProvider, int outputNum, List<Ingredient> recipe) {
        consumer.accept(new ShapelessRecipeBuilder.Result(
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
        });
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
