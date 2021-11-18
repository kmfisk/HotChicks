package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.crop.*;
import com.ryanhcode.hotchicks.block.trees.*;
import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class HotBlocks {
    public static final DeferredRegister<Block> REGISTRAR = DeferredRegister.create(ForgeRegistries.BLOCKS, HotChickens.MOD_ID);

    public static final RegistryObject<Block> NEST_BOX = registerWithItem("nest_box", () -> new NestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NEST = registerWithItem("nest", () -> new StickNestBlock(AbstractBlock.Properties.of(Material.GRASS).strength(0.5F).sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> TROUGH_BLOCK = registerWithItem("trough_block", () -> new TroughBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> METAL_TROUGH_BLOCK = registerWithItem("metal_trough_block", () -> new MetalTroughBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
//    public static final RegistryObject<Block> FOOD_CROCK = registerWithItem("food_crock", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
//    public static final RegistryObject<Block> WATER_BOTTLE = registerWithItem("water_bottle", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
    public static final RegistryObject<Block> TRELLIS_BLOCK = registerWithItem("trellis", () -> new TrellisBlock(AbstractBlock.Properties.of(Material.WOOD).strength(0.8F).sound(SoundType.WOOD).noOcclusion()));
//    public static final RegistryObject<Block> RABBIT_WIRE = registerWithItem("rabbit_wire", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
//    public static final RegistryObject<Block> RABBIT_FLOORING = registerWithItem("rabbit_flooring", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
//    public static final RegistryObject<Block> RABBIT_WIRE_DOOR = registerWithItem("rabbit_wire_door", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
//    public static final RegistryObject<Block> FRUIT_CRATE = registerWithItem("fruit_crate", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));

    /*public static final Map<String, RegistryObject<Block>> PLANTERS = new HashMap<>();

    static {
        String[] woodTypes = new String[]{"oak", "spruce", "birch", "acacia", "jungle", "dark_oak", "crimson", "warped"};
        for (String woodType : woodTypes)
            PLANTERS.put(woodType, registerWithItem(woodType + "_planter", () -> new Block(AbstractBlock.Properties.of(Material.WOOD))));
    }*/

    public static final RegistryObject<Block> STRAWBERRY_BUSH = registerWithItem("strawberry_bush", () -> new BerryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.STRAWBERRY));
    public static final RegistryObject<Block> BLUEBERRY_BUSH = registerWithItem("blueberry_bush", () -> new BerryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.BLUEBERRIES));
    public static final RegistryObject<Block> COTTON_BUSH = registerWithItem("cotton_bush", () -> new BerryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.COTTON));
    public static final RegistryObject<Block> OKRA_BUSH = registerWithItem("okra_bush", () -> new BerryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.OKRA));
    public static final RegistryObject<Block> PEPPER_BUSH = registerWithItem("pepper_bush", () -> new PepperBerryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.PEPPERS));

    public static final RegistryObject<Block> FRUIT_LEAVES = registerWithItem("fruit_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> CITRUS_LEAVES = registerWithItem("citrus_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> FICUS_LEAVES = registerWithItem("ficus_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> TROPICAL_FRUIT_LEAVES = registerWithItem("tropical_fruit_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));

    public static final RegistryObject<Block> RED_APPLE_LEAVES = registerWithItem("red_apple_leaves", () -> new FruitLeavesBlock(() -> Items.APPLE));
    public static final RegistryObject<Block> PEACH_LEAVES = registerWithItem("peach_leaves", () -> new FruitLeavesBlock(HotItems.PEACH));
    //public static final RegistryObject<Block> MANGO_LEAVES = registerWithItem("mango_leaves", () -> new FruitLeavesBlock(HotItems.MANGO)); todo
    //public static final RegistryObject<Block> POMEGRANATE_LEAVES = registerWithItem("pomegranate_leaves", () -> new FruitLeavesBlock(HotItems.POMEGRANATE));
    public static final RegistryObject<Block> FIG_LEAVES = registerWithItem("fig_leaves", () -> new FruitLeavesBlock(HotItems.FIG));
    public static final RegistryObject<Block> CITRON_LEAVES = registerWithItem("citron_leaves", () -> new FruitLeavesBlock(HotItems.CITRON));
    public static final RegistryObject<Block> POMELO_LEAVES = registerWithItem("pomelo_leaves", () -> new FruitLeavesBlock(HotItems.POMELO));
    public static final RegistryObject<Block> MANDARIN_LEAVES = registerWithItem("mandarin_leaves", () -> new FruitLeavesBlock(HotItems.MANDARIN));
    public static final RegistryObject<Block> PAPEDA_LEAVES = registerWithItem("papeda_leaves", () -> new FruitLeavesBlock(HotItems.PAPEDA));
    public static final RegistryObject<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new FruitLeavesBlock(HotItems.ORANGE));
    public static final RegistryObject<Block> LEMON_LEAVES = registerWithItem("lemon_leaves", () -> new FruitLeavesBlock(HotItems.LEMON));
    public static final RegistryObject<Block> GRAPEFRUIT_LEAVES = registerWithItem("grapefruit_leaves", () -> new FruitLeavesBlock(HotItems.GRAPEFRUIT));
    public static final RegistryObject<Block> LIME_LEAVES = registerWithItem("lime_leaves", () -> new FruitLeavesBlock(HotItems.LIME));
    public static final RegistryObject<Block> YUZU_LEAVES = registerWithItem("yuzu_leaves", () -> new FruitLeavesBlock(HotItems.YUZU));

    public static final RegistryObject<Block> RED_APPLE_SAPLING = registerWithItem("red_apple_seed", () -> new SaplingBlock(new RedAppleTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PEACH_SAPLING = registerWithItem("peach_seed", () -> new SaplingBlock(new PeachTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    //public static final RegistryObject<Block> MANGO_SAPLING = registerWithItem("mango_seed", () -> new SaplingBlock(new MangoTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS))); todo
    //public static final RegistryObject<Block> POMEGRANATE_SAPLING = registerWithItem("pomegranate_seed", () -> new SaplingBlock(new PomegranateTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> FIG_SAPLING = registerWithItem("fig_seed", () -> new SaplingBlock(new FigTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> CITRON_SAPLING = registerWithItem("citron_seed", () -> new SaplingBlock(new CitronTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POMELO_SAPLING = registerWithItem("pomelo_seed", () -> new SaplingBlock(new PomeloTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MANDARIN_SAPLING = registerWithItem("mandarin_seed", () -> new SaplingBlock(new MandarinTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PAPEDA_SAPLING = registerWithItem("papeda_seed", () -> new SaplingBlock(new PapedaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ORANGE_SAPLING = registerWithItem("orange_seed", () -> new SaplingBlock(new OrangeTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LEMON_SAPLING = registerWithItem("lemon_seed", () -> new SaplingBlock(new LemonTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> GRAPEFRUIT_SAPLING = registerWithItem("grapefruit_seed", () -> new SaplingBlock(new GrapefruitTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LIME_SAPLING = registerWithItem("lime_seed", () -> new SaplingBlock(new LimeTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> YUZU_SAPLING = registerWithItem("yuzu_seed", () -> new SaplingBlock(new YuzuTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> CORN_CROP = REGISTRAR.register("corn", () -> new CornBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.CORN));
    public static final RegistryObject<Block> MILLET_CROP = REGISTRAR.register("millet", () -> new CornBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.MILLET));
    public static final RegistryObject<Block> OATS_CROP = REGISTRAR.register("oats", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.OATS));
    public static final RegistryObject<Block> LETTUCE_CROP = REGISTRAR.register("lettuce", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.LETTUCE));
    public static final RegistryObject<Block> GARLIC_CROP = REGISTRAR.register("garlic", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.GARLIC));

    public static final RegistryObject<Block> WILD_GRAPE = registerWithItem("wild_grape", CropVineBlock::new);
    public static final RegistryObject<Block> WILD_KIWI = registerWithItem("wild_kiwi", CropVineBlock::new);
    public static final RegistryObject<Block> WILD_TOMATO = registerWithItem("wild_tomato", () -> new BushBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().strength(0.2F).sound(SoundType.VINE)));
    public static final RegistryObject<Block> WILD_PEA = registerWithItem("wild_pea", CropVineBlock::new);

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = REGISTRAR.register(name, block);
        HotItems.REGISTRAR.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));
        return registryObject;
    }

    public static void setRenderLayers() {
        RenderType rendertype1 = RenderType.cutoutMipped();
        RenderTypeLookup.setRenderLayer(TRELLIS_BLOCK.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(FRUIT_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(CITRUS_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(FICUS_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(TROPICAL_FRUIT_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(RED_APPLE_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(PEACH_LEAVES.get(), rendertype1);
        //RenderTypeLookup.setRenderLayer(MANGO_LEAVES.get(), rendertype1); todo
        //RenderTypeLookup.setRenderLayer(POMEGRANATE_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(FIG_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(CITRON_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(POMELO_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(MANDARIN_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(PAPEDA_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(ORANGE_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(LEMON_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(GRAPEFRUIT_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(LIME_LEAVES.get(), rendertype1);
        RenderTypeLookup.setRenderLayer(YUZU_LEAVES.get(), rendertype1);
        RenderType rendertype2 = RenderType.cutout();
        RenderTypeLookup.setRenderLayer(STRAWBERRY_BUSH.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(BLUEBERRY_BUSH.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(COTTON_BUSH.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(PEPPER_BUSH.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(OKRA_BUSH.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(OATS_CROP.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(GARLIC_CROP.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(LETTUCE_CROP.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(CORN_CROP.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(MILLET_CROP.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(RED_APPLE_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(PEACH_SAPLING.get(), rendertype2);
        //RenderTypeLookup.setRenderLayer(MANGO_SAPLING.get(), rendertype2); todo
        //RenderTypeLookup.setRenderLayer(POMEGRANATE_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(FIG_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(CITRON_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(POMELO_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(MANDARIN_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(PAPEDA_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(ORANGE_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(LEMON_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(GRAPEFRUIT_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(LIME_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(YUZU_SAPLING.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(WILD_GRAPE.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(WILD_KIWI.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(WILD_TOMATO.get(), rendertype2);
        RenderTypeLookup.setRenderLayer(WILD_PEA.get(), rendertype2);
    }
}
