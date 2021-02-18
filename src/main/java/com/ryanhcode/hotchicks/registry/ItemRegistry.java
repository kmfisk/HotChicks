package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.NestBlock;
import com.ryanhcode.hotchicks.item.Foods;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.item.StudBookItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HotChickens.MODID);


    public static final RegistryObject<Item> WHITE_EGG = ITEMS.register("white_egg", () -> new HotEggItem((new Item.Properties()).maxStackSize(16).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> STUD_BOOK = ITEMS.register("stud_book", () -> new StudBookItem((new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)));

    public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberries",
            () -> new BlockNamedItem(BlockRegistry.BLUEBERRY_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.BLUEBERRY)));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new BlockNamedItem(BlockRegistry.CORN.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.CORN)));

    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton",
            () -> new BlockNamedItem(BlockRegistry.COTTON_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic",
            () -> new BlockNamedItem(BlockRegistry.GARLIC_BLOCK.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.GARLIC)));

    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.GRAPES)));

    public static final RegistryObject<Item> KIWI = ITEMS.register("kiwi", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.KIWI)));

    public static final RegistryObject<Item> LETTUCE = ITEMS.register("lettuce",
            () -> new BlockNamedItem(BlockRegistry.LETTUCE_BLOCK.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.LETTUCE)));
    public static final RegistryObject<Item> MILLET = ITEMS.register("millet",
            () -> new BlockNamedItem(BlockRegistry.MILLET.get(), (new Item.Properties()).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> OATS = ITEMS.register("oats",
            () -> new BlockNamedItem(BlockRegistry.OATS_BLOCK.get(), (new Item.Properties()).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> OKRA = ITEMS.register("okra",
            () -> new BlockNamedItem(BlockRegistry.OKRA_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.OKRA)));

    public static final RegistryObject<Item> PEAS = ITEMS.register("peas", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.PEAS)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new BlockNamedItem(BlockRegistry.STRAWBERRY_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.STRAWBERRY)));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.TOMATOES)));

    public static final RegistryObject<Item> PEPPERS = ITEMS.register("peppers",
            () -> new BlockNamedItem(BlockRegistry.PEPPER_BUSH.get(), (new Item.Properties()).group(ItemGroup.FOOD)));

}
