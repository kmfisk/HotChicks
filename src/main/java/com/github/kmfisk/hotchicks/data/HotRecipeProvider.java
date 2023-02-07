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
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HotRecipeProvider extends RecipeProvider {
    public HotRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(shapedRecipeResult(HotBlocks.WATER_BOTTLE.get(), 2,
                ImmutableList.of("B", "I", "T"),
                ImmutableMap.<Character, Ingredient>builder()
                        .put('B', Ingredient.of(Items.GLASS_BOTTLE))
                        .put('I', Ingredient.of(Blocks.CLAY))
                        .put('T', Ingredient.of(Items.IRON_INGOT))
                        .build()
        ));

        consumer.accept(shapelessRecipeResult(HotBlocks.RED_APPLE_SAPLING.get(), 1,
                ImmutableList.of(
                        Ingredient.of(Items.APPLE),
                        Ingredient.of(Items.APPLE)
                )
        ));

        cookRecipes(consumer, HotItems.COOKED_BEEF_STEAK.get(), Ingredient.of(HotItems.BEEF_STEAK.get()), 0.35F);
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
        return new ShapelessRecipeBuilder.Result(
                iItemProvider.asItem().getRegistryName(),
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
