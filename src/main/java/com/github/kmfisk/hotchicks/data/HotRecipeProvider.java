package com.github.kmfisk.hotchicks.data;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.item.HotItems;
import com.github.kmfisk.hotchicks.tags.HotItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class HotRecipeProvider extends RecipeProvider {
    public HotRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.CHEESE_MOLD.get(), 1).pattern("NBN").define('N', Items.IRON_NUGGET).define('B', Blocks.BARREL).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.FOOD_CROCK.get(), 2).pattern("FCF").pattern(" F ").define('F', Items.CLAY_BALL).define('C', Items.BOWL).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.HUTCH_GATE.get(), 2).pattern("SCS").pattern("SFS").pattern("SCS").define('F', Items.IRON_NUGGET).define('S', Items.STICK).define('C', Items.IRON_INGOT).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.HUTCH_FLOOR.get(), 4).pattern("FFF").pattern("FCF").pattern("FFF").define('F', Items.STICK).define('C', HotBlocks.HUTCH_BARS.get()).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.HUTCH_BARS.get(), 16).pattern("FCF").pattern("FCF").pattern("FCF").define('F', Items.IRON_NUGGET).define('C', Items.IRON_INGOT).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotItems.LIVESTOCK_CRATE.get(), 1).pattern("TTT").pattern("PCP").pattern("PPP").define('T', ItemTags.WOODEN_TRAPDOORS).define('C', Blocks.CHEST).define('P', ItemTags.PLANKS).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.NEST.get(), 2).pattern("S S").pattern("SFS").define('S', Items.WHEAT).define('F', Items.FEATHER).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.NEST_BOX.get(), 4).pattern("WWW").pattern("WSW").pattern("WWW").define('W', ItemTags.PLANKS).define('S', Blocks.HAY_BLOCK).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.METAL_TROUGH.get(), 2).pattern("S S").pattern("SSS").pattern("N N").define('S', Items.IRON_INGOT).define('N', Items.IRON_NUGGET).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.WOODEN_TROUGH.get(), 2).pattern("S S").pattern("SSS").pattern("N N").define('S', ItemTags.PLANKS).define('N', Items.IRON_NUGGET).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.TRELLIS.get(), 8).pattern("NSN").pattern("SNS").pattern("NSN").define('S', Items.STRING).define('N', Items.STICK).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotBlocks.WATER_BOTTLE.get(), 2).pattern("B").pattern("I").pattern("T").define('B', Items.GLASS_BOTTLE).define('I', Blocks.CLAY).define('T', Items.IRON_INGOT).save(consumer);

        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("blue").get(), Ingredient.of(HotItems.BLUEBERRIES.get()), true, HotBlocks.STAIRS.get("blue").get(), HotBlocks.SLABS.get("blue").get(), HotBlocks.PRESSURE_PLATES.get("blue").get(), HotBlocks.BUTTONS.get("blue").get(), HotBlocks.FENCES.get("blue").get(), HotBlocks.FENCE_GATES.get("blue").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("green").get(), Ingredient.of(HotItems.KALE.get()), true, HotBlocks.STAIRS.get("green").get(), HotBlocks.SLABS.get("green").get(), HotBlocks.PRESSURE_PLATES.get("green").get(), HotBlocks.BUTTONS.get("green").get(), HotBlocks.FENCES.get("green").get(), HotBlocks.FENCE_GATES.get("green").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("red").get(), Ingredient.of(HotItems.STRAWBERRY.get()), true, HotBlocks.STAIRS.get("red").get(), HotBlocks.SLABS.get("red").get(), HotBlocks.PRESSURE_PLATES.get("red").get(), HotBlocks.BUTTONS.get("red").get(), HotBlocks.FENCES.get("red").get(), HotBlocks.FENCE_GATES.get("red").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("white").get(), Ingredient.of(Tags.Items.DYES_WHITE), true, HotBlocks.STAIRS.get("white").get(), HotBlocks.SLABS.get("white").get(), HotBlocks.PRESSURE_PLATES.get("white").get(), HotBlocks.BUTTONS.get("white").get(), HotBlocks.FENCES.get("white").get(), HotBlocks.FENCE_GATES.get("white").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("dark").get(), Ingredient.of(Tags.Items.DYES_BLACK), false, HotBlocks.STAIRS.get("dark").get(), HotBlocks.SLABS.get("dark").get(), HotBlocks.PRESSURE_PLATES.get("dark").get(), HotBlocks.BUTTONS.get("dark").get(), HotBlocks.FENCES.get("dark").get(), HotBlocks.FENCE_GATES.get("dark").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("gray").get(), Ingredient.of(Tags.Items.DYES_GRAY), false, HotBlocks.STAIRS.get("gray").get(), HotBlocks.SLABS.get("gray").get(), HotBlocks.PRESSURE_PLATES.get("gray").get(), HotBlocks.BUTTONS.get("gray").get(), HotBlocks.FENCES.get("gray").get(), HotBlocks.FENCE_GATES.get("gray").get());
        woodVariantBlocksRecipes(consumer, HotBlocks.PLANKS.get("tan").get(), Ingredient.of(Tags.Items.DYES_LIGHT_GRAY), false, HotBlocks.STAIRS.get("tan").get(), HotBlocks.SLABS.get("tan").get(), HotBlocks.PRESSURE_PLATES.get("tan").get(), HotBlocks.BUTTONS.get("tan").get(), HotBlocks.FENCES.get("tan").get(), HotBlocks.FENCE_GATES.get("tan").get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.STUD_BOOK.get(), 1).requires(Items.BOOK).requires(Items.WHEAT_SEEDS).requires(Items.GREEN_DYE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.GLUE.get(), 4).requires(Items.WHEAT).requires(HotItems.GELATIN.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ROTTEN_FLESH, 2).requires(HotItems.POOR_CHICKEN_CARCASS.get()).save(consumer, "chicken_quarter_0");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_QUARTER.get(), 1).requires(HotItems.FAIR_CHICKEN_CARCASS.get()).save(consumer, "chicken_quarter_1");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_QUARTER.get(), 2).requires(HotItems.GOOD_CHICKEN_CARCASS.get()).save(consumer, "chicken_quarter_2");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_QUARTER.get(), 3).requires(HotItems.CHOICE_CHICKEN_CARCASS.get()).save(consumer, "chicken_quarter_3");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_QUARTER.get(), 4).requires(HotItems.PRIME_CHICKEN_CARCASS.get()).save(consumer, "chicken_quarter_4");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CHICKEN, 1).requires(HotItems.CHICKEN_QUARTER.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COOKED_CHICKEN, 1).requires(HotItems.COOKED_CHICKEN_QUARTER.get());
        cookRecipes(consumer, HotItems.COOKED_CHICKEN_QUARTER.get(), HotItems.CHICKEN_QUARTER.get(), 0.35F);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ROTTEN_FLESH, 2).requires(HotItems.POOR_RABBIT_CARCASS.get()).save(consumer, "rabbit_quarter_0");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.RABBIT_QUARTER.get(), 1).requires(HotItems.FAIR_RABBIT_CARCASS.get()).save(consumer, "rabbit_quarter_1");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.RABBIT_QUARTER.get(), 2).requires(HotItems.GOOD_RABBIT_CARCASS.get()).save(consumer, "rabbit_quarter_2");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.RABBIT_QUARTER.get(), 3).requires(HotItems.CHOICE_RABBIT_CARCASS.get()).save(consumer, "rabbit_quarter_3");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.RABBIT_QUARTER.get(), 4).requires(HotItems.PRIME_RABBIT_CARCASS.get()).save(consumer, "rabbit_quarter_4");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RABBIT, 1).requires(HotItems.RABBIT_QUARTER.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COOKED_RABBIT, 1).requires(HotItems.COOKED_RABBIT_QUARTER.get());
        cookRecipes(consumer, HotItems.COOKED_RABBIT_QUARTER.get(), HotItems.RABBIT_QUARTER.get(), 0.35F);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ROTTEN_FLESH, 4).requires(HotItems.POOR_BEEF_PRIMAL.get()).save(consumer, "beef_steak_0");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_STEAK.get(), 2).requires(HotItems.FAIR_BEEF_PRIMAL.get()).save(consumer, "beef_steak_1");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_STEAK.get(), 4).requires(HotItems.GOOD_BEEF_PRIMAL.get()).save(consumer, "beef_steak_2");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_STEAK.get(), 6).requires(HotItems.CHOICE_BEEF_PRIMAL.get()).save(consumer, "beef_steak_3");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_STEAK.get(), 8).requires(HotItems.PRIME_BEEF_PRIMAL.get()).save(consumer, "beef_steak_4");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BEEF, 1).requires(HotItems.BEEF_STEAK.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COOKED_BEEF, 1).requires(HotItems.COOKED_BEEF_STEAK.get());
        cookRecipes(consumer, HotItems.COOKED_BEEF_STEAK.get(), HotItems.BEEF_STEAK.get(), 0.35F);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RABBIT_HIDE, 1).requires(HotItems.FAIR_RABBIT_HIDE.get()).save(consumer, "rabbit_hide_1");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RABBIT_HIDE, 2).requires(HotItems.GOOD_RABBIT_HIDE.get()).save(consumer, "rabbit_hide_2");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RABBIT_HIDE, 3).requires(HotItems.CHOICE_RABBIT_HIDE.get()).save(consumer, "rabbit_hide_3");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RABBIT_HIDE, 4).requires(HotItems.PRIME_RABBIT_HIDE.get()).save(consumer, "rabbit_hide_4");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LEATHER, 2).requires(HotItems.FAIR_COWHIDE.get()).save(consumer, "cowhide_1");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LEATHER, 4).requires(HotItems.GOOD_COWHIDE.get()).save(consumer, "cowhide_2");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LEATHER, 6).requires(HotItems.CHOICE_COWHIDE.get()).save(consumer, "cowhide_3");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LEATHER, 8).requires(HotItems.PRIME_COWHIDE.get()).save(consumer, "cowhide_4");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.CITRON_SAPLING.get(), 1).requires(HotItems.CITRON.get()).requires(HotItems.CITRON.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.FIG_SAPLING.get(), 1).requires(HotItems.FIG.get()).requires(HotItems.FIG.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.GRAPEFRUIT_SAPLING.get(), 1).requires(HotItems.GRAPEFRUIT.get()).requires(HotItems.GRAPEFRUIT.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.LEMON_SAPLING.get(), 1).requires(HotItems.LEMON.get()).requires(HotItems.LEMON.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.LIME_SAPLING.get(), 1).requires(HotItems.LIME.get()).requires(HotItems.LIME.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.MANDARIN_SAPLING.get(), 1).requires(HotItems.MANDARIN.get()).requires(HotItems.MANDARIN.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.MANGO_SAPLING.get(), 1).requires(HotItems.MANGO.get()).requires(HotItems.MANGO.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.ORANGE_SAPLING.get(), 1).requires(HotItems.ORANGE.get()).requires(HotItems.ORANGE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.PAPEDA_SAPLING.get(), 1).requires(HotItems.PAPEDA.get()).requires(HotItems.PAPEDA.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.PEACH_SAPLING.get(), 1).requires(HotItems.PEACH.get()).requires(HotItems.PEACH.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.POMEGRANATE_SAPLING.get(), 1).requires(HotItems.POMEGRANATE.get()).requires(HotItems.POMEGRANATE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.POMELO_SAPLING.get(), 1).requires(HotItems.POMELO.get()).requires(HotItems.POMELO.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.RED_APPLE_SAPLING.get(), 1).requires(Items.APPLE).requires(Items.APPLE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.YUZU_SAPLING.get(), 1).requires(HotItems.YUZU.get()).requires(HotItems.YUZU.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.GRAPEFRUIT_SAPLING.get(), 2).requires(HotBlocks.ORANGE_SAPLING.get()).requires(HotBlocks.POMELO_SAPLING.get()).save(consumer, "grapefruit_hybrid_seed");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.LEMON_SAPLING.get(), 2).requires(HotBlocks.CITRON_SAPLING.get()).requires(HotBlocks.ORANGE_SAPLING.get()).save(consumer, "lemon_hybrid_seed");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.LIME_SAPLING.get(), 2).requires(HotBlocks.CITRON_SAPLING.get()).requires(HotBlocks.PAPEDA_SAPLING.get()).save(consumer, "lime_hybrid_seed");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.ORANGE_SAPLING.get(), 2).requires(HotBlocks.CITRON_SAPLING.get()).requires(HotBlocks.POMELO_SAPLING.get()).save(consumer, "orange_hybrid_seed");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotBlocks.YUZU_SAPLING.get(), 2).requires(HotBlocks.PAPEDA_SAPLING.get()).requires(HotBlocks.MANDARIN_SAPLING.get()).save(consumer, "yuzu_hybrid_seed");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BOTTLED_MILK.get(), 4).requires(Items.MILK_BUCKET);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HotItems.PLANT_MILK.get(), 4).pattern("PPP").pattern("PWP").pattern("PPP").define('P', HotItemTags.PLANT_MILK_INGREDIENTS).define('W', Items.WATER_BUCKET).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CITRUS_COOLER.get(), 2).requires(HotItemTags.CITRUS_FRUITS).requires(HotItemTags.CITRUS_FRUITS).requires(HotItemTags.CITRUS_FRUITS).requires(Items.SUGAR).requires(Blocks.ICE);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BERRY_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.STRAWBERRY.get()).requires(HotItems.BLUEBERRIES.get()).requires(Items.SWEET_BERRIES);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHOCOLATE_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(Items.COCOA_BEANS).requires(Items.COCOA_BEANS).requires(HotItems.BANANA.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CREAMSICLE_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.ORANGE.get()).requires(HotItems.GRAPEFRUIT.get()).requires(HotItems.MANDARIN.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.KEY_LIME_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.LEMON.get()).requires(HotItems.LIME.get()).requires(HotItems.CITRON.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.PEACH_MANGO_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.LEMON.get()).requires(HotItems.PEACH.get()).requires(HotItems.MANGO.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SUPERFOOD_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.BLUEBERRIES.get()).requires(HotItems.KALE.get()).requires(HotItems.OATS.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.TROPICAL_PROTEIN_SHAKE.get(), 2).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItemTags.MILKS).requires(HotItems.BANANA.get()).requires(HotItems.MANGO.get()).requires(HotItems.KIWI.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SMOOTHIE.get(), 2).requires(HotItemTags.FRUITS).requires(HotItemTags.FRUITS).requires(HotItemTags.FRUITS).requires(Items.SUGAR).requires(Blocks.SNOW_BLOCK);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CABBAGE_ROLLS.get(), 1).requires(HotItemTags.RAW_PORKCHOPS).requires(HotItems.RICE.get()).requires(HotItems.RICE.get()).requires(Tags.Items.MUSHROOMS).requires(Items.CARROT).requires(HotItems.CABBAGE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHEESE_PLATE.get(), 2).requires(HotItems.HARD_CHEESE.get()).requires(HotItems.SOFT_CHEESE.get()).requires(HotItems.GRAPES.get()).requires(HotItems.POMEGRANATE.get()).requires(HotItems.FIG.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.FARMERS_BREAKFAST.get(), 1).requires(HotItemTags.RAW_PORKCHOPS).requires(Items.BREAD).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItemTags.FRUITS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.FRUIT_BOWL.get(), 1).requires(Items.BOWL).requires(HotItemTags.FRUIT_SALAD_INGREDIENTS).requires(HotItemTags.FRUIT_SALAD_INGREDIENTS).requires(HotItemTags.FRUIT_SALAD_INGREDIENTS).requires(HotItemTags.FRUIT_SALAD_INGREDIENTS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.GLAZED_PORK_CHOP.get(), 1).requires(HotItemTags.RAW_PORKCHOPS).requires(Items.HONEY_BOTTLE).requires(HotItems.PEACH.get()).requires(Items.POTATO).requires(HotItems.PEPPERS.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.KIMCHI.get(), 3).requires(HotItems.CABBAGE.get()).requires(HotItems.CABBAGE.get()).requires(HotItems.PEPPERS.get()).requires(HotItems.GARLIC.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.LETTUCE_WRAP.get(), 2).requires(HotItemTags.COOKED_MEATS).requires(Tags.Items.MUSHROOMS).requires(Items.CARROT).requires(HotItems.PEPPERS.get()).requires(HotItems.LETTUCE.get()).requires(HotItems.LETTUCE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.OATMEAL.get(), 1).requires(Items.BOWL).requires(HotItemTags.FRUITS).requires(HotItemTags.MILKS).requires(HotItems.OATS.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.PAVLOVA.get(), 4).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItems.STRAWBERRY.get()).requires(HotItems.KIWI.get()).requires(Items.SUGAR).requires(HotItemTags.MILKS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.PICKLES.get(), 3).requires(HotItems.CUCUMBER.get()).requires(HotItems.CUCUMBER.get()).requires(Items.WHEAT_SEEDS).requires(HotItems.GARLIC.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.PORK_CUTLET.get(), 1).requires(HotItemTags.RAW_PORKCHOPS).requires(Items.WHEAT).requires(Tags.Items.EGGS).requires(HotItems.RICE.get()).requires(HotItems.CABBAGE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.RABBIT_TACOS.get(), 2).requires(HotItemTags.RAW_RABBITS).requires(HotItems.CORN.get()).requires(HotItems.CORN.get()).requires(HotItems.LETTUCE.get()).requires(Items.CARROT).requires(HotItems.LIME.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_SALAD.get(), 1).requires(Items.BOWL).requires(HotItems.LETTUCE.get()).requires(HotItemTags.COOKED_CHICKENS).requires(Items.APPLE).requires(HotItems.GRAPES.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHINESE_CHICKEN_SALAD.get(), 1).requires(Items.BOWL).requires(HotItemTags.COOKED_CHICKENS).requires(HotItems.CABBAGE.get()).requires(Items.CARROT).requires(HotItems.MANDARIN.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.DANDELION_SALAD.get(), 1).requires(Items.BOWL).requires(HotItems.LETTUCE.get()).requires(HotItems.LETTUCE.get()).requires(Items.DANDELION).requires(HotItems.GARLIC.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.GARDEN_SALAD.get(), 1).requires(Items.BOWL).requires(HotItems.LETTUCE.get()).requires(HotItems.CORN.get()).requires(HotItems.TOMATO.get()).requires(HotItems.PEPPERS.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.POWER_SALAD.get(), 1).requires(Items.BOWL).requires(HotItems.LETTUCE.get()).requires(HotItems.KALE.get()).requires(HotItems.CABBAGE.get()).requires(HotItems.PEPPERS.get()).requires(Tags.Items.EGGS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SALMON_RICE_BOWL.get(), 1).requires(HotItemTags.RAW_SALMONS).requires(HotItems.RICE.get()).requires(Items.CARROT).requires(HotItems.YUZU.get()).requires(Items.KELP);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SHAKSHUKA.get(), 1).requires(Tags.Items.EGGS).requires(Tags.Items.EGGS).requires(HotItems.TOMATO.get()).requires(HotItems.PEPPERS.get()).requires(HotItems.GARLIC.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SHORT_RIB_BBQ.get(), 1).requires(HotItemTags.RAW_BEEFS).requires(Items.HONEY_BOTTLE).requires(Items.APPLE).requires(HotItems.PEPPERS.get()).requires(HotItems.GARLIC.get()).requires(HotItems.RICE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_CHILI.get(), 4).requires(Items.WATER_BUCKET).requires(HotItemTags.RAW_BEEFS).requires(HotItemTags.RAW_BEEFS).requires(HotItems.GARLIC.get()).requires(HotItems.PEPPERS.get()).requires(HotItems.TOMATO.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BEEF_STROGANOFF.get(), 4).requires(Items.WATER_BUCKET).requires(HotItemTags.RAW_BEEFS).requires(HotItemTags.RAW_BEEFS).requires(HotItemTags.MILKS).requires(Tags.Items.MUSHROOMS).requires(Tags.Items.MUSHROOMS).requires(HotItems.GARLIC.get()).requires(HotItems.BUTTER.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.BRAISED_RABBIT.get(), 4).requires(Items.WATER_BUCKET).requires(HotItemTags.RAW_RABBITS).requires(Items.CARROT).requires(Tags.Items.MUSHROOMS).requires(HotItems.PEPPERS.get()).requires(HotItemTags.MILKS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.CHICKEN_NOODLE_SOUP.get(), 4).requires(Items.WATER_BUCKET).requires(HotItemTags.RAW_CHICKENS).requires(Items.CARROT).requires(HotItems.PEAS.get()).requires(HotItems.GARLIC.get()).requires(Items.WHEAT);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.GUMBO.get(), 4).requires(Items.WATER_BUCKET).requires(HotItemTags.COOKED_CHICKENS).requires(HotItemTags.COOKED_PORKCHOPS).requires(HotItems.GARLIC.get()).requires(HotItems.PEPPERS.get()).requires(HotItems.OKRA.get()).requires(HotItems.RICE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.POTATO_SOUP.get(), 4).requires(Items.WATER_BUCKET).requires(Items.POTATO).requires(Items.CARROT).requires(HotItems.GARLIC.get()).requires(HotItemTags.MILKS).requires(HotItems.SOFT_CHEESE.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.SPLIT_PEA_SOUP.get(), 4).requires(Items.WATER_BUCKET).requires(Items.CARROT).requires(HotItems.PEAS.get()).requires(HotItems.GARLIC.get()).requires(HotItemTags.COOKED_PORKCHOPS);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.VEGGIE_SOUP.get(), 4).requires(Items.WATER_BUCKET).requires(Items.CARROT).requires(Items.POTATO).requires(HotItems.GARLIC.get()).requires(HotItems.PEAS.get()).requires(HotItems.TOMATO.get()).requires(HotItems.CORN.get());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HotItems.WILD_MUSHROOM_SOUP.get(), 4).requires(Items.WATER_BUCKET).requires(Tags.Items.MUSHROOMS).requires(Tags.Items.MUSHROOMS).requires(Tags.Items.MUSHROOMS).requires(Items.CARROT).requires(Items.POTATO).requires(HotItems.GARLIC.get());
    }

    private static void woodVariantBlocksRecipes(Consumer<FinishedRecipe> consumer, ItemLike planks, Ingredient dye, boolean basic, ItemLike stairs, ItemLike slab, ItemLike pressurePlate, ItemLike button, ItemLike fence, ItemLike fenceGate) {
        if (basic)
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, planks, 4).requires(dye).requires(Ingredient.of(ItemTags.PLANKS), 4);
        else
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, planks, 8).requires(dye).requires(Ingredient.of(ItemTags.PLANKS), 4).requires(Ingredient.of(Items.IRON_NUGGET), 4);
        stairBuilder(stairs, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
        slab(consumer, RecipeCategory.MISC, slab, planks);
        pressurePlate(consumer, pressurePlate, planks);
        buttonBuilder(button, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
        fenceBuilder(fence, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
        fenceGateBuilder(fenceGate, Ingredient.of(planks)).unlockedBy(getHasName(planks), has(planks)).save(consumer);
    }

    private static void cookRecipes(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike recipe, float experience) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(recipe), RecipeCategory.FOOD, result, experience, 200).unlockedBy(getHasName(recipe), has(recipe)).save(consumer, BuiltInRegistries.ITEM.getKey(result.asItem()));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(recipe), RecipeCategory.FOOD, result, experience, 100).unlockedBy(getHasName(recipe), has(recipe)).save(consumer, BuiltInRegistries.ITEM.getKey(result.asItem()).withSuffix("_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(recipe), RecipeCategory.FOOD, result, experience, 600).unlockedBy(getHasName(recipe), has(recipe)).save(consumer, BuiltInRegistries.ITEM.getKey(result.asItem()).withSuffix("_campfire"));
    }
}
