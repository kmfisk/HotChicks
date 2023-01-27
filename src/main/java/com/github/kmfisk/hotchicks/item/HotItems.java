package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
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
    public static final RegistryObject<Item> SILKIE_SPAWN_EGG = REGISTRAR.register("silkie_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.SILKIE));
    public static final RegistryObject<Item> RHODE_ISLAND_SPAWN_EGG = REGISTRAR.register("rhode_island_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.RHODE_ISLAND_RED));
    public static final RegistryObject<Item> ORPINGTON_SPAWN_EGG = REGISTRAR.register("orpington_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.ORPINGTON));
    public static final RegistryObject<Item> OLIVE_EGGER_SPAWN_EGG = REGISTRAR.register("olive_egger_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.OLIVE_EGGER));
    public static final RegistryObject<Item> MARANS_SPAWN_EGG = REGISTRAR.register("marans_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.MARANS));
    public static final RegistryObject<Item> LEGHORN_SPAWN_EGG = REGISTRAR.register("leghorn_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.LEGHORN));
    public static final RegistryObject<Item> JUNGLEFOWL_SPAWN_EGG = REGISTRAR.register("junglefowl_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.JUNGLEFOWL));
    public static final RegistryObject<Item> BARRED_ROCK_SPAWN_EGG = REGISTRAR.register("barred_rock_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.BARRED_ROCK));
    public static final RegistryObject<Item> AMERAUCANA_SPAWN_EGG = REGISTRAR.register("ameraucana_spawn_egg", () -> new ChickenSpawnEggItem(ChickenBreeds.AMERAUCANA));
    public static final RegistryObject<Item> COTTONTAIL_SPAWN_EGG = REGISTRAR.register("cottontail_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.COTTONTAIL));
    public static final RegistryObject<Item> AMERICAN_CHINCHILLA_SPAWN_EGG = REGISTRAR.register("american_chinchilla_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.AMERICAN_CHINCHILLA));
    public static final RegistryObject<Item> CALIFORNIA_SPAWN_EGG = REGISTRAR.register("california_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.CALIFORNIA));
    public static final RegistryObject<Item> DUTCH_SPAWN_EGG = REGISTRAR.register("dutch_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.DUTCH));
    public static final RegistryObject<Item> FLEMISH_GIANT_SPAWN_EGG = REGISTRAR.register("flemish_giant_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.FLEMISH_GIANT));
    public static final RegistryObject<Item> NEW_ZEALAND_SPAWN_EGG = REGISTRAR.register("new_zealand_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.NEW_ZEALAND));
    public static final RegistryObject<Item> REX_SPAWN_EGG = REGISTRAR.register("rex_spawn_egg", () -> new RabbitSpawnEggItem(RabbitBreeds.REX));

    // ENTITY ITEMS
    public static final RegistryObject<Item> BLUE_EGG = REGISTRAR.register("blue_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> BROWN_EGG = REGISTRAR.register("brown_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> CHOCOLATE_EGG = REGISTRAR.register("chocolate_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GREEN_EGG = REGISTRAR.register("green_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> WHITE_EGG = REGISTRAR.register("white_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChicks.HOT_CHICKS_GROUP)));
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

    // CROP BLOCK ITEMS
    public static final RegistryObject<Item> BLUEBERRIES = REGISTRAR.register("blueberries", () -> new BlockNamedItem(HotBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.BLUEBERRY)));
    public static final RegistryObject<Item> CORN = REGISTRAR.register("corn", () -> new BlockNamedItem(HotBlocks.CORN_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.CORN)));
    public static final RegistryObject<Item> COTTON = REGISTRAR.register("cotton", () -> new BlockNamedItem(HotBlocks.COTTON_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GARLIC = REGISTRAR.register("garlic", () -> new BlockNamedItem(HotBlocks.GARLIC_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.GARLIC)));
    public static final RegistryObject<Item> LETTUCE = REGISTRAR.register("lettuce", () -> new BlockNamedItem(HotBlocks.LETTUCE_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = REGISTRAR.register("millet", () -> new BlockNamedItem(HotBlocks.MILLET_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OATS = REGISTRAR.register("oats", () -> new BlockNamedItem(HotBlocks.OATS_CROP.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OKRA = REGISTRAR.register("okra", () -> new BlockNamedItem(HotBlocks.OKRA_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.OKRA)));
    public static final RegistryObject<Item> PEPPERS = REGISTRAR.register("peppers", () -> new BlockNamedItem(HotBlocks.PEPPER_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> STRAWBERRY = REGISTRAR.register("strawberry", () -> new BlockNamedItem(HotBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP).food(HotFoods.STRAWBERRY)));

    // TRELLIS CROPS
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
}
