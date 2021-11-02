package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.item.HotFoods;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.item.HotSpawnEggItem;
import com.ryanhcode.hotchicks.item.StudBookItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HotChickens.MODID);

    public static final RegistryObject<Item> WHITE_EGG = ITEMS.register("white_egg", () -> new HotEggItem((new Item.Properties()).stacksTo(16).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> STUD_BOOK = ITEMS.register("stud_book", () -> new StudBookItem((new Item.Properties()).stacksTo(1).tab(ItemGroup.TAB_MISC)));

    // CROP BLOCK ITEMS
    public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberries", () -> new BlockNamedItem(HotBlocks.BLUEBERRY_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.BLUEBERRY)));
    public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new BlockNamedItem(HotBlocks.CORN_CROP.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.CORN)));
    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton", () -> new BlockNamedItem(HotBlocks.COTTON_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic", () -> new BlockNamedItem(HotBlocks.GARLIC_CROP.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.GARLIC)));
    public static final RegistryObject<Item> LETTUCE = ITEMS.register("lettuce", () -> new BlockNamedItem(HotBlocks.LETTUCE_CROP.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = ITEMS.register("millet", () -> new BlockNamedItem(HotBlocks.MILLET_CROP.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> OATS = ITEMS.register("oats", () -> new BlockNamedItem(HotBlocks.OATS_CROP.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));
    public static final RegistryObject<Item> OKRA = ITEMS.register("okra", () -> new BlockNamedItem(HotBlocks.OKRA_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.OKRA)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry", () -> new BlockNamedItem(HotBlocks.STRAWBERRY_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.STRAWBERRY)));
    public static final RegistryObject<Item> PEPPERS = ITEMS.register("peppers", () -> new BlockNamedItem(HotBlocks.PEPPER_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));

    // TRELLIS CROPS?
    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.GRAPES)));
    public static final RegistryObject<Item> KIWI = ITEMS.register("kiwi", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.KIWI)));
    public static final RegistryObject<Item> PEAS = ITEMS.register("peas", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.PEAS)));
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(HotFoods.TOMATOES)));

    // SPAWN EGGS
    public static final RegistryObject<Item> SILKIE_SPAWN_EGG = ITEMS.register("silkie_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.SILKIE));
    public static final RegistryObject<Item> RHODE_ISLAND_SPAWN_EGG = ITEMS.register("rhode_island_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.RHODE_ISLAND_RED));
    public static final RegistryObject<Item> ORPINGTON_SPAWN_EGG = ITEMS.register("orpington_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.ORPINGTON));
    public static final RegistryObject<Item> OLIVE_EGGER_SPAWN_EGG = ITEMS.register("olive_egger_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.OLIVE_EGGER));
    public static final RegistryObject<Item> MARANS_SPAWN_EGG = ITEMS.register("marans_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.MARANS));
    public static final RegistryObject<Item> LEGHORN_SPAWN_EGG = ITEMS.register("leghorn_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.LEGHORN));
    public static final RegistryObject<Item> JUNGLEFOWL_SPAWN_EGG = ITEMS.register("junglefowl_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.JUNGLEFOWL));
    public static final RegistryObject<Item> BARRED_ROCK_SPAWN_EGG = ITEMS.register("barred_rock_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.BARRED_ROCK));
    public static final RegistryObject<Item> AMERAUCANA_SPAWN_EGG = ITEMS.register("ameraucana_spawn_egg", () -> new HotSpawnEggItem(ChickenBreed.AMERAUCANA));
}
