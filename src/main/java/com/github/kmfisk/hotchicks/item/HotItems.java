package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.entity.HotEntities;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotItems {
    public static final DeferredRegister<Item> REGISTRAR = DeferredRegister.create(ForgeRegistries.ITEMS, HotChicks.MOD_ID);

    public static final RegistryObject<Item> STUD_BOOK = REGISTRAR.register("stud_book", () -> new StudBookItem(new Item.Properties().stacksTo(1).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> LIVESTOCK_CRATE = REGISTRAR.register("livestock_crate", () -> new LivestockCrateItem(new Item.Properties().stacksTo(1).tab(HotChicks.HOT_CHICKS_GROUP)));

    // SPAWN EGGS
    public static final RegistryObject<Item> JUNGLEFOWL_SPAWN_EGG = REGISTRAR.register("junglefowl_chicken_spawn_egg", () -> new HotSpawnEggItem("junglefowl", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> AMERAUCANA_SPAWN_EGG = REGISTRAR.register("ameraucana_chicken_spawn_egg", () -> new HotSpawnEggItem("ameraucana", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> BARRED_ROCK_SPAWN_EGG = REGISTRAR.register("barred_rock_chicken_spawn_egg", () -> new HotSpawnEggItem("barred_rock", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> LEGHORN_SPAWN_EGG = REGISTRAR.register("leghorn_chicken_spawn_egg", () -> new HotSpawnEggItem("leghorn", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> MARANS_SPAWN_EGG = REGISTRAR.register("marans_chicken_spawn_egg", () -> new HotSpawnEggItem("marans", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OLIVE_EGGER_SPAWN_EGG = REGISTRAR.register("olive_egger_chicken_spawn_egg", () -> new HotSpawnEggItem("olive_egger", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> ORPINGTON_SPAWN_EGG = REGISTRAR.register("orpington_chicken_spawn_egg", () -> new HotSpawnEggItem("orpington", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> RHODE_ISLAND_SPAWN_EGG = REGISTRAR.register("rhode_island_red_chicken_spawn_egg", () -> new HotSpawnEggItem("rhode_island_red", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> SILKIE_SPAWN_EGG = REGISTRAR.register("silkie_chicken_spawn_egg", () -> new HotSpawnEggItem("silkie", HotEntities.CHICKEN, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> COTTONTAIL_SPAWN_EGG = REGISTRAR.register("cottontail_rabbit_spawn_egg", () -> new HotSpawnEggItem("cottontail", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> AMERICAN_CHINCHILLA_SPAWN_EGG = REGISTRAR.register("american_chinchilla_rabbit_spawn_egg", () -> new HotSpawnEggItem("american_chinchilla", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> CALIFORNIA_SPAWN_EGG = REGISTRAR.register("california_rabbit_spawn_egg", () -> new HotSpawnEggItem("california", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> DUTCH_SPAWN_EGG = REGISTRAR.register("dutch_rabbit_spawn_egg", () -> new HotSpawnEggItem("dutch", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> FLEMISH_GIANT_SPAWN_EGG = REGISTRAR.register("flemish_giant_rabbit_spawn_egg", () -> new HotSpawnEggItem("flemish_giant", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> NEW_ZEALAND_SPAWN_EGG = REGISTRAR.register("new_zealand_rabbit_spawn_egg", () -> new HotSpawnEggItem("new_zealand", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> REX_SPAWN_EGG = REGISTRAR.register("rex_rabbit_spawn_egg", () -> new HotSpawnEggItem("rex", HotEntities.RABBIT, new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));

    // ENTITY ITEMS
    public static final RegistryObject<Item> BLUE_EGG = REGISTRAR.register("blue_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> BROWN_EGG = REGISTRAR.register("brown_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> CHOCOLATE_EGG = REGISTRAR.register("chocolate_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GREEN_EGG = REGISTRAR.register("green_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> WHITE_EGG = REGISTRAR.register("white_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> BEEF_STEAK = REGISTRAR.register("beef_steak", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BEEF_STEAK)));
    public static final RegistryObject<Item> COOKED_BEEF_STEAK = REGISTRAR.register("cooked_beef_steak", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.COOKED_BEEF_STEAK)));
    public static final RegistryObject<Item> CHOICE_CHICKEN_CARCASS = REGISTRAR.register("choice_chicken_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> PRIME_CHICKEN_CARCASS = REGISTRAR.register("prime_chicken_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GOOD_CHICKEN_CARCASS = REGISTRAR.register("good_chicken_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> FAIR_CHICKEN_CARCASS = REGISTRAR.register("fair_chicken_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> POOR_CHICKEN_CARCASS = REGISTRAR.register("poor_chicken_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> CHICKEN_QUARTER = REGISTRAR.register("chicken_quarter", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHICKEN_QUARTER)));
    public static final RegistryObject<Item> COOKED_CHICKEN_QUARTER = REGISTRAR.register("cooked_chicken_quarter", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.COOKED_CHICKEN_QUARTER)));
    public static final RegistryObject<Item> CHOICE_RABBIT_CARCASS = REGISTRAR.register("choice_rabbit_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> PRIME_RABBIT_CARCASS = REGISTRAR.register("prime_rabbit_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GOOD_RABBIT_CARCASS = REGISTRAR.register("good_rabbit_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> FAIR_RABBIT_CARCASS = REGISTRAR.register("fair_rabbit_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> POOR_RABBIT_CARCASS = REGISTRAR.register("poor_rabbit_carcass", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> RABBIT_QUARTER = REGISTRAR.register("rabbit_quarter", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.RABBIT_QUARTER)));
    public static final RegistryObject<Item> COOKED_RABBIT_QUARTER = REGISTRAR.register("cooked_rabbit_quarter", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.COOKED_RABBIT_QUARTER)));
    public static final RegistryObject<Item> CHOICE_RABBIT_HIDE = REGISTRAR.register("choice_rabbit_hide", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> PRIME_RABBIT_HIDE = REGISTRAR.register("prime_rabbit_hide", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GOOD_RABBIT_HIDE = REGISTRAR.register("good_rabbit_hide", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> FAIR_RABBIT_HIDE = REGISTRAR.register("fair_rabbit_hide", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> POOR_RABBIT_HIDE = REGISTRAR.register("poor_rabbit_hide", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GELATIN = REGISTRAR.register("gelatin", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));

    // CROP BLOCK ITEMS
    public static final RegistryObject<Item> BANANA = REGISTRAR.register("banana", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BANANA)));
    public static final RegistryObject<Item> BLUEBERRIES = REGISTRAR.register("blueberries", () -> new BlockNamedItem(HotBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BLUEBERRY)));
    public static final RegistryObject<Item> CABBAGE = REGISTRAR.register("cabbage", () -> new BlockNamedItem(HotBlocks.CABBAGE_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CABBAGE)));
    public static final RegistryObject<Item> CORN = REGISTRAR.register("corn", () -> new BlockNamedItem(HotBlocks.CORN_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CORN)));
    public static final RegistryObject<Item> COTTON = REGISTRAR.register("cotton", () -> new BlockNamedItem(HotBlocks.COTTON_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GARLIC = REGISTRAR.register("garlic", () -> new BlockNamedItem(HotBlocks.GARLIC_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GARLIC)));
    public static final RegistryObject<Item> KALE = REGISTRAR.register("kale", () -> new BlockNamedItem(HotBlocks.KALE_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.KALE)));
    public static final RegistryObject<Item> LETTUCE = REGISTRAR.register("lettuce", () -> new BlockNamedItem(HotBlocks.LETTUCE_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = REGISTRAR.register("millet", () -> new BlockNamedItem(HotBlocks.MILLET_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OATS = REGISTRAR.register("oats", () -> new BlockNamedItem(HotBlocks.OATS_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OKRA = REGISTRAR.register("okra", () -> new BlockNamedItem(HotBlocks.OKRA_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.OKRA)));
    public static final RegistryObject<Item> PEPPERS = REGISTRAR.register("peppers", () -> new BlockNamedItem(HotBlocks.PEPPER_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PEPPERS)));
    public static final RegistryObject<Item> RICE = REGISTRAR.register("rice", () -> new BlockNamedItem(HotBlocks.RICE_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> STRAWBERRY = REGISTRAR.register("strawberry", () -> new BlockNamedItem(HotBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.STRAWBERRY)));

    // TRELLIS CROPS
    public static final RegistryObject<Item> CUCUMBER = REGISTRAR.register("cucumber", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CUCUMBER)));
    public static final RegistryObject<Item> GRAPES = REGISTRAR.register("grapes", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GRAPES)));
    public static final RegistryObject<Item> KIWI = REGISTRAR.register("kiwi", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.KIWI)));
    public static final RegistryObject<Item> PEAS = REGISTRAR.register("peas", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PEAS)));
    public static final RegistryObject<Item> TOMATO = REGISTRAR.register("tomato", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.TOMATOES)));

    // TREE FRUITS
    public static final RegistryObject<Item> CITRON = REGISTRAR.register("citron", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CITRON)));
    public static final RegistryObject<Item> FIG = REGISTRAR.register("fig", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.FIG)));
    public static final RegistryObject<Item> GRAPEFRUIT = REGISTRAR.register("grapefruit", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GRAPEFRUIT)));
    public static final RegistryObject<Item> LEMON = REGISTRAR.register("lemon", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.LEMON)));
    public static final RegistryObject<Item> LIME = REGISTRAR.register("lime", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.LIME)));
    public static final RegistryObject<Item> MANDARIN = REGISTRAR.register("mandarin", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.MANDARIN)));
    public static final RegistryObject<Item> MANGO = REGISTRAR.register("mango", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.MANGO)));
    public static final RegistryObject<Item> ORANGE = REGISTRAR.register("orange", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.ORANGE)));
    public static final RegistryObject<Item> PAPEDA = REGISTRAR.register("papeda", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PAPEDA)));
    public static final RegistryObject<Item> PEACH = REGISTRAR.register("peach", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PEACH)));
    public static final RegistryObject<Item> POMEGRANATE = REGISTRAR.register("pomegranate", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.POMEGRANATE)));
    public static final RegistryObject<Item> POMELO = REGISTRAR.register("pomelo", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.POMELO)));
    public static final RegistryObject<Item> YUZU = REGISTRAR.register("yuzu", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.YUZU)));

    // FOODS
    public static final RegistryObject<Item> BUTTER = REGISTRAR.register("butter", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> HARD_CHEESE = REGISTRAR.register("hard_cheese", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.HARD_CHEESE)));
    public static final RegistryObject<Item> SOFT_CHEESE = REGISTRAR.register("soft_cheese", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SOFT_CHEESE)));
    public static final RegistryObject<Item> BOTTLED_MILK = REGISTRAR.register("bottled_milk", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BOTTLED_MILK)));
    public static final RegistryObject<Item> PLANT_MILK = REGISTRAR.register("plant_milk", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PLANT_MILK)));
    public static final RegistryObject<Item> CITRUS_COOLER = REGISTRAR.register("citrus_cooler", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CITRUS_COOLER)));
    public static final RegistryObject<Item> BERRY_PROTEIN_SHAKE = REGISTRAR.register("berry_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BERRY_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> CHOCOLATE_PROTEIN_SHAKE = REGISTRAR.register("chocolate_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHOCOLATE_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> CREAMSICLE_PROTEIN_SHAKE = REGISTRAR.register("creamsicle_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CREAMSICLE_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> KEY_LIME_PROTEIN_SHAKE = REGISTRAR.register("key_lime_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.KEY_LIME_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> PEACH_MANGO_PROTEIN_SHAKE = REGISTRAR.register("peach_mango_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PEACH_MANGO_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> SUPERFOOD_PROTEIN_SHAKE = REGISTRAR.register("superfood_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SUPERFOOD_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> TROPICAL_PROTEIN_SHAKE = REGISTRAR.register("tropical_protein_shake", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.TROPICAL_PROTEIN_SHAKE)));
    public static final RegistryObject<Item> SMOOTHIE = REGISTRAR.register("smoothie", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SMOOTHIE)));
    public static final RegistryObject<Item> CABBAGE_ROLLS = REGISTRAR.register("cabbage_rolls", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CABBAGE_ROLLS)));
    public static final RegistryObject<Item> CHEESE_PLATE = REGISTRAR.register("cheese_plate", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHEESE_PLATE)));
    public static final RegistryObject<Item> FARMERS_BREAKFAST = REGISTRAR.register("farmers_breakfast", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.FARMERS_BREAKFAST)));
    public static final RegistryObject<Item> FRUIT_BOWL = REGISTRAR.register("fruit_bowl", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.FRUIT_BOWL)));
    public static final RegistryObject<Item> GLAZED_PORK_CHOP = REGISTRAR.register("glazed_pork_chop", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GLAZED_PORK_CHOP)));
    public static final RegistryObject<Item> KIMCHI = REGISTRAR.register("kimchi", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.KIMCHI)));
    public static final RegistryObject<Item> LETTUCE_WRAP = REGISTRAR.register("lettuce_wrap", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.LETTUCE_WRAP)));
    public static final RegistryObject<Item> OATMEAL = REGISTRAR.register("oatmeal", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.OATMEAL)));
    public static final RegistryObject<Item> PAVLOVA = REGISTRAR.register("pavlova", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PAVLOVA)));
    public static final RegistryObject<Item> PICKLES = REGISTRAR.register("pickles", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PICKLES)));
    public static final RegistryObject<Item> PORK_CUTLET = REGISTRAR.register("pork_cutlet", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.PORK_CUTLET)));
    public static final RegistryObject<Item> RABBIT_TACOS = REGISTRAR.register("rabbit_tacos", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.RABBIT_TACOS)));
    public static final RegistryObject<Item> CHICKEN_SALAD = REGISTRAR.register("chicken_salad", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHICKEN_SALAD)));
    public static final RegistryObject<Item> CHINESE_CHICKEN_SALAD = REGISTRAR.register("chinese_chicken_salad", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHINESE_CHICKEN_SALAD)));
    public static final RegistryObject<Item> DANDELION_SALAD = REGISTRAR.register("dandelion_salad", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.DANDELION_SALAD)));
    public static final RegistryObject<Item> GARDEN_SALAD = REGISTRAR.register("garden_salad", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GARDEN_SALAD)));
    public static final RegistryObject<Item> POWER_SALAD = REGISTRAR.register("power_salad", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.POWER_SALAD)));
    public static final RegistryObject<Item> SALMON_RICE_BOWL = REGISTRAR.register("salmon_rice_bowl", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SALMON_RICE_BOWL)));
    public static final RegistryObject<Item> SHAKSHUKA = REGISTRAR.register("shakshuka", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SHAKSHUKA)));
    public static final RegistryObject<Item> SHORT_RIB_BBQ = REGISTRAR.register("short_rib_bbq", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SHORT_RIB_BBQ)));
    public static final RegistryObject<Item> BEEF_CHILI_SOUP = REGISTRAR.register("beef_chili_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BEEF_CHILI_SOUP)));
    public static final RegistryObject<Item> BEEF_STROGANOFF_SOUP = REGISTRAR.register("beef_stroganoff_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BEEF_STROGANOFF_SOUP)));
    public static final RegistryObject<Item> BRAISED_RABBIT_SOUP = REGISTRAR.register("braised_rabbit_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BRAISED_RABBIT_SOUP)));
    public static final RegistryObject<Item> CHICKEN_NOODLE_SOUP = REGISTRAR.register("chicken_noodle_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CHICKEN_NOODLE_SOUP)));
    public static final RegistryObject<Item> GUMBO_SOUP = REGISTRAR.register("gumbo_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GUMBO_SOUP)));
    public static final RegistryObject<Item> POTATO_SOUP = REGISTRAR.register("potato_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.POTATO_SOUP)));
    public static final RegistryObject<Item> SPLIT_PEA_SOUP = REGISTRAR.register("split_pea_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.SPLIT_PEA_SOUP)));
    public static final RegistryObject<Item> VEGGIE_SOUP = REGISTRAR.register("veggie_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.VEGGIE_SOUP)));
    public static final RegistryObject<Item> WILD_MUSHROOM_SOUP = REGISTRAR.register("wild_mushroom_soup", () -> new Item(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.WILD_MUSHROOM_SOUP)));
}
