package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.NestBlock;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
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


}
