package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.MetalTroughBlock;
import com.ryanhcode.hotchicks.block.NestBlock;
import com.ryanhcode.hotchicks.block.TrellisBlock;
import com.ryanhcode.hotchicks.block.TroughBlock;
import com.ryanhcode.hotchicks.block.crop.BerryBush;
import com.ryanhcode.hotchicks.block.crop.CornBlock;
import com.ryanhcode.hotchicks.block.crop.PepperBerryBush;
import com.ryanhcode.hotchicks.block.crop.StandardCropBlock;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HotChickens.MODID);
    //public static final DeferredRegister<BlockPlacerType<?>> BLOCK_PLACER_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_PLACER_TYPES, HotChickens.MODID);



    public static final RegistryObject<Block> NEST_BOX = BLOCKS.register("nest_box", () -> new NestBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> NEST_BOX_ITEM = ItemRegistry.ITEMS.register("nest_box", () -> new BlockItem(NEST_BOX.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static final RegistryObject<Block> STRAWBERRY_BUSH = BLOCKS.register("strawberry_bush", () -> new BerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.STRAWBERRY));
    public static final RegistryObject<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush", () -> new BerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.BLUEBERRIES));
    public static final RegistryObject<Block> COTTON_BUSH = BLOCKS.register("cotton_bush", () -> new BerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.COTTON));
    public static final RegistryObject<Block> OKRA_BUSH = BLOCKS.register("okra_bush", () -> new BerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.OKRA));
    public static final RegistryObject<Block> PEPPER_BUSH = BLOCKS.register("pepper_bush", () -> new PepperBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.PEPPERS));

    public static final RegistryObject<Block> CORN = BLOCKS.register("corn", () -> new CornBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.CORN));
    public static final RegistryObject<Block> MILLET = BLOCKS.register("millet", () -> new CornBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemRegistry.MILLET));

    public static final RegistryObject<Block> OATS_BLOCK = BLOCKS.register("oats", () -> new StandardCropBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP), ItemRegistry.OATS));
    public static final RegistryObject<Block> LETTUCE_BLOCK = BLOCKS.register("lettuce", () -> new StandardCropBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP), ItemRegistry.LETTUCE));
    public static final RegistryObject<Block> GARLIC_BLOCK = BLOCKS.register("garlic", () -> new StandardCropBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP), ItemRegistry.GARLIC));

    public static final RegistryObject<Block> TROUGH_BLOCK = BLOCKS.register("trough_block", () -> new TroughBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> TROUGH_BLOCK_ITEM = ItemRegistry.ITEMS.register("trough_block", () -> new BlockItem(TROUGH_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static final RegistryObject<Block> TRELLIS_BLOCK = BLOCKS.register("trellis", () -> new TrellisBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> TRELLIS_BLOCK_ITEM = ItemRegistry.ITEMS.register("trellis", () -> new BlockItem(TRELLIS_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    public static final RegistryObject<Block> METAL_TROUGH_BLOCK = BLOCKS.register("metal_trough_block", () -> new MetalTroughBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Item> METAL_TROUGH_BLOCK_ITEM = ItemRegistry.ITEMS.register("metal_trough_block", () -> new BlockItem(METAL_TROUGH_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));


    //public static final RegistryObject<Block> METAL_TROUGH_BLOCK = BLOCKS.register("metal_trough_block", () -> new TroughBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
    //public static final RegistryObject<Item> METAL_TROUGH_BLOCK_ITEM = ItemRegistry.ITEMS.register("metal_trough_block", () -> new BlockItem(METAL_TROUGH_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

}
