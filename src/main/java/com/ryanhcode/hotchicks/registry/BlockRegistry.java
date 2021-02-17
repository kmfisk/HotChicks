package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.NestBlock;
import com.ryanhcode.hotchicks.block.TroughBlock;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HotChickens.MODID);

    public static final RegistryObject<Block> NEST_BOX = BLOCKS.register("nest_box", () -> new NestBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> NEST_BOX_ITEM = ItemRegistry.ITEMS.register("nest_box", () -> new BlockItem(NEST_BOX.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));


    public static final RegistryObject<Block> TROUGH_BLOCK = BLOCKS.register("trough_block", () -> new TroughBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> TROUGH_BLOCK_ITEM = ItemRegistry.ITEMS.register("trough_block", () -> new BlockItem(TROUGH_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    //public static final RegistryObject<Block> METAL_TROUGH_BLOCK = BLOCKS.register("metal_trough_block", () -> new TroughBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    //public static final RegistryObject<Item> METAL_TROUGH_BLOCK_ITEM = ItemRegistry.ITEMS.register("metal_trough_block", () -> new BlockItem(METAL_TROUGH_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

}
