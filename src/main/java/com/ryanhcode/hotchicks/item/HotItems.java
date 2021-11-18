package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.HotBlocks;
import com.ryanhcode.hotchicks.entity.base.ChickenBreeds;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotItems {
    public static final DeferredRegister<Item> REGISTRAR = DeferredRegister.create(ForgeRegistries.ITEMS, HotChickens.MOD_ID);

    public static final RegistryObject<Item> WHITE_EGG = REGISTRAR.register("white_egg", () -> new HotEggItem(new Item.Properties().stacksTo(16).tab(HotChickens.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> STUD_BOOK = REGISTRAR.register("stud_book", () -> new StudBookItem(new Item.Properties().stacksTo(1).tab(HotChickens.HOT_CHICKS_GROUP)));
//    public static final RegistryObject<Item> LIVESTOCK_CRATE = REGISTRAR.register("livestock_crate", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));

    // CROP BLOCK ITEMS
    public static final RegistryObject<Item> BLUEBERRIES = REGISTRAR.register("blueberries", () -> new BlockNamedItem(HotBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.BLUEBERRY)));
    public static final RegistryObject<Item> CORN = REGISTRAR.register("corn", () -> new BlockNamedItem(HotBlocks.CORN_CROP.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.CORN)));
    public static final RegistryObject<Item> COTTON = REGISTRAR.register("cotton", () -> new BlockNamedItem(HotBlocks.COTTON_BUSH.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> GARLIC = REGISTRAR.register("garlic", () -> new BlockNamedItem(HotBlocks.GARLIC_CROP.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.GARLIC)));
    public static final RegistryObject<Item> LETTUCE = REGISTRAR.register("lettuce", () -> new BlockNamedItem(HotBlocks.LETTUCE_CROP.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = REGISTRAR.register("millet", () -> new BlockNamedItem(HotBlocks.MILLET_CROP.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OATS = REGISTRAR.register("oats", () -> new BlockNamedItem(HotBlocks.OATS_CROP.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));
    public static final RegistryObject<Item> OKRA = REGISTRAR.register("okra", () -> new BlockNamedItem(HotBlocks.OKRA_BUSH.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.OKRA)));
    public static final RegistryObject<Item> STRAWBERRY = REGISTRAR.register("strawberry", () -> new BlockNamedItem(HotBlocks.STRAWBERRY_BUSH.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.STRAWBERRY)));
    public static final RegistryObject<Item> PEPPERS = REGISTRAR.register("peppers", () -> new BlockNamedItem(HotBlocks.PEPPER_BUSH.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));

    // TRELLIS CROPS
    public static final RegistryObject<Item> GRAPES = REGISTRAR.register("grapes", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.GRAPES)));
    public static final RegistryObject<Item> KIWI = REGISTRAR.register("kiwi", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.KIWI)));
    public static final RegistryObject<Item> PEAS = REGISTRAR.register("peas", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.PEAS)));
    public static final RegistryObject<Item> TOMATO = REGISTRAR.register("tomato", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.TOMATOES)));

    // TREE FRUITS
    public static final RegistryObject<Item> PEACH = REGISTRAR.register("peach", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.PEACH)));
    public static final RegistryObject<Item> MANGO = REGISTRAR.register("mango", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.MANGO)));
    public static final RegistryObject<Item> POMEGRANATE = REGISTRAR.register("pomegranate", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.POMEGRANATE)));
    public static final RegistryObject<Item> FIG = REGISTRAR.register("fig", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.FIG)));
    public static final RegistryObject<Item> CITRON = REGISTRAR.register("citron", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.CITRON)));
    public static final RegistryObject<Item> POMELO = REGISTRAR.register("pomelo", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.POMELO)));
    public static final RegistryObject<Item> MANDARIN = REGISTRAR.register("mandarin", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.MANDARIN)));
    public static final RegistryObject<Item> PAPEDA = REGISTRAR.register("papeda", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.PAPEDA)));
    public static final RegistryObject<Item> ORANGE = REGISTRAR.register("orange", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.ORANGE)));
    public static final RegistryObject<Item> LEMON = REGISTRAR.register("lemon", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.LEMON)));
    public static final RegistryObject<Item> GRAPEFRUIT = REGISTRAR.register("grapefruit", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.GRAPEFRUIT)));
    public static final RegistryObject<Item> LIME = REGISTRAR.register("lime", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.LIME)));
    public static final RegistryObject<Item> YUZU = REGISTRAR.register("yuzu", () -> new Item(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP).food(HotFoods.YUZU)));

    // SPAWN EGGS
    public static final RegistryObject<Item> SILKIE_SPAWN_EGG = REGISTRAR.register("silkie_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.SILKIE));
    public static final RegistryObject<Item> RHODE_ISLAND_SPAWN_EGG = REGISTRAR.register("rhode_island_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.RHODE_ISLAND_RED));
    public static final RegistryObject<Item> ORPINGTON_SPAWN_EGG = REGISTRAR.register("orpington_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.ORPINGTON));
    public static final RegistryObject<Item> OLIVE_EGGER_SPAWN_EGG = REGISTRAR.register("olive_egger_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.OLIVE_EGGER));
    public static final RegistryObject<Item> MARANS_SPAWN_EGG = REGISTRAR.register("marans_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.MARANS));
    public static final RegistryObject<Item> LEGHORN_SPAWN_EGG = REGISTRAR.register("leghorn_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.LEGHORN));
    public static final RegistryObject<Item> JUNGLEFOWL_SPAWN_EGG = REGISTRAR.register("junglefowl_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.JUNGLEFOWL));
    public static final RegistryObject<Item> BARRED_ROCK_SPAWN_EGG = REGISTRAR.register("barred_rock_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.BARRED_ROCK));
    public static final RegistryObject<Item> AMERAUCANA_SPAWN_EGG = REGISTRAR.register("ameraucana_spawn_egg", () -> new HotSpawnEggItem(ChickenBreeds.AMERAUCANA));
}
