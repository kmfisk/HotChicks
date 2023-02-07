package com.github.kmfisk.hotchicks.data;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

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
}
