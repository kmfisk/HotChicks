package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.Main;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.item.Foods;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.item.HotSpawnEggItem;
import com.ryanhcode.hotchicks.item.StudBookItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HotChickens.MODID);

    public static final RegistryObject<Item> WHITE_EGG = ITEMS.register("white_egg", () -> new HotEggItem((new Item.Properties()).stacksTo(16).tab(ItemGroup.TAB_MISC)));
    public static final RegistryObject<Item> STUD_BOOK = ITEMS.register("stud_book", () -> new StudBookItem((new Item.Properties()).stacksTo(1).tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberries",
            () -> new BlockNamedItem(BlockRegistry.BLUEBERRY_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.BLUEBERRY)));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new BlockNamedItem(BlockRegistry.CORN.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.CORN)));

    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton",
            () -> new BlockNamedItem(BlockRegistry.COTTON_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic",
            () -> new BlockNamedItem(BlockRegistry.GARLIC_BLOCK.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.GARLIC)));

    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.GRAPES)));

    public static final RegistryObject<Item> KIWI = ITEMS.register("kiwi", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.KIWI)));

    public static final RegistryObject<Item> LETTUCE = ITEMS.register("lettuce",
            () -> new BlockNamedItem(BlockRegistry.LETTUCE_BLOCK.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = ITEMS.register("millet",
            () -> new BlockNamedItem(BlockRegistry.MILLET.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> OATS = ITEMS.register("oats",
            () -> new BlockNamedItem(BlockRegistry.OATS_BLOCK.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> OKRA = ITEMS.register("okra",
            () -> new BlockNamedItem(BlockRegistry.OKRA_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.OKRA)));

    public static final RegistryObject<Item> PEAS = ITEMS.register("peas", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.PEAS)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new BlockNamedItem(BlockRegistry.STRAWBERRY_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.STRAWBERRY)));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item((new Item.Properties()).tab(ItemGroup.TAB_FOOD).food(Foods.TOMATOES)));

    public static final RegistryObject<Item> PEPPERS = ITEMS.register("peppers",
            () -> new BlockNamedItem(BlockRegistry.PEPPER_BUSH.get(), (new Item.Properties()).tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> SILKIE_SPAWN_EGG = ITEMS.register("silkie_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.SILKIE));
    public static final RegistryObject<Item> RHODE_ISLAND_SPAWN_EGG = ITEMS.register("rhode_island_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.RHODE_ISLAND_RED));
    public static final RegistryObject<Item> ORPINGTON_SPAWN_EGG = ITEMS.register("orpington_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.ORPINGTON));
    public static final RegistryObject<Item> OLIVE_EGGER_SPAWN_EGG = ITEMS.register("olive_egger_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.OLIVE_EGGER));
    public static final RegistryObject<Item> MARANS_SPAWN_EGG = ITEMS.register("marans_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.MARANS));
    public static final RegistryObject<Item> LEGHORN_SPAWN_EGG = ITEMS.register("leghorn_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.LEGHORN));
    public static final RegistryObject<Item> JUNGLEFOWL_SPAWN_EGG = ITEMS.register("junglefowl_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.JUNGLEFOWL));
    public static final RegistryObject<Item> BARRED_ROCK_SPAWN_EGG = ITEMS.register("barred_rock_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.BARRED_ROCK));
    public static final RegistryObject<Item> AMERAUCANA_SPAWN_EGG = ITEMS.register("ameraucana_spawn_egg", () -> new HotSpawnEggItem(new Item.Properties().tab(Main.HOT_CHICKS_GROUP), ChickenBreed.AMERAUCANA));
}
