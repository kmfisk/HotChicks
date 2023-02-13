package com.github.kmfisk.hotchicks.data;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.item.HotItems;
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

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HotRecipeProvider extends RecipeProvider {
    public HotRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
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
                ImmutableList.of(Ingredient.of(HotItems.PRIME_CHICKEN_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("chicken_quarter_4", HotItems.CHICKEN_QUARTER.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_CHICKEN_CARCASS.get()))));
        cookRecipes(consumer, HotItems.COOKED_CHICKEN_QUARTER.get(), Ingredient.of(HotItems.CHICKEN_QUARTER.get()), 0.35F);
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_0", Items.ROTTEN_FLESH, 2,
                ImmutableList.of(Ingredient.of(HotItems.POOR_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_1", HotItems.RABBIT_QUARTER.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_2", HotItems.RABBIT_QUARTER.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_3", HotItems.RABBIT_QUARTER.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_CARCASS.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_quarter_4", HotItems.RABBIT_QUARTER.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_CARCASS.get()))));
        cookRecipes(consumer, HotItems.COOKED_RABBIT_QUARTER.get(), Ingredient.of(HotItems.RABBIT_QUARTER.get()), 0.35F);
        consumer.accept(namedShapelessRecipeResult("beef_steak_0", Items.ROTTEN_FLESH, 2,
                ImmutableList.of(Ingredient.of(HotItems.POOR_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_1", HotItems.BEEF_STEAK.get(), 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_2", HotItems.BEEF_STEAK.get(), 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_3", HotItems.BEEF_STEAK.get(), 3,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_BEEF_PRIMAL.get()))));
        consumer.accept(namedShapelessRecipeResult("beef_steak_4", HotItems.BEEF_STEAK.get(), 4,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_BEEF_PRIMAL.get()))));
        cookRecipes(consumer, HotItems.COOKED_BEEF_STEAK.get(), Ingredient.of(HotItems.BEEF_STEAK.get()), 0.35F);

        consumer.accept(namedShapelessRecipeResult("rabbit_hide_1", Items.LEATHER, 1,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_2", Items.LEATHER, 2,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_3", Items.LEATHER, 3,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("rabbit_hide_4", Items.LEATHER, 4,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_RABBIT_HIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_1", Items.LEATHER, 2,
                ImmutableList.of(Ingredient.of(HotItems.FAIR_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_2", Items.LEATHER, 4,
                ImmutableList.of(Ingredient.of(HotItems.GOOD_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_3", Items.LEATHER, 6,
                ImmutableList.of(Ingredient.of(HotItems.PRIME_COWHIDE.get()))));
        consumer.accept(namedShapelessRecipeResult("cowhide_4", Items.LEATHER, 8,
                ImmutableList.of(Ingredient.of(HotItems.CHOICE_COWHIDE.get()))));

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
