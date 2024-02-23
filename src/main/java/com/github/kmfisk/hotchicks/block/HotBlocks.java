package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.trees.*;
import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HotBlocks {
    public static final DeferredRegister<Block> REGISTRAR = DeferredRegister.create(ForgeRegistries.BLOCKS, HotChicks.MOD_ID);

    public static final RegistryObject<Block> CHEESE_MOLD = registerWithItem("cheese_mold", () -> new CheeseMoldBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FOOD_CROCK = registerWithItem("food_crock", () -> new FoodCrockBlock(BlockBehaviour.Properties.of(Material.CLAY).noOcclusion()));
//    public static final RegistryObject<Block> FRUIT_CRATE = registerWithItem("fruit_crate", () -> new Block(AbstractBlock.Properties.of(Material.CLAY)));
    public static final RegistryObject<Block> MILL = registerWithItem("mill", () -> new MillBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> NEST_BOX = registerWithItem("nest_box", () -> new NestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NEST = registerWithItem("nest", () -> new NestBoxBlock(BlockBehaviour.Properties.of(Material.GRASS).strength(0.5F).sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> HUTCH_BARS = registerWithItem("hutch_bars", () -> new HutchBarsBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.NONE).strength(2.0F, 3.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> HUTCH_FLOOR = registerWithItem("hutch_floor", () -> new HutchFloorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(2.0F, 3.0F).sound(SoundType.METAL).noOcclusion().isRedstoneConductor(HotBlocks::never).isSuffocating(HotBlocks::never).isViewBlocking(HotBlocks::never)));
    public static final RegistryObject<Block> HUTCH_GATE = registerWithItem("hutch_gate", () -> new HutchGateBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.NONE).strength(2.0F, 3.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> TRELLIS = registerWithItem("trellis", () -> new TrellisBlock(null));
    public static final RegistryObject<Block> WOODEN_TROUGH = registerWithItem("wooden_trough", () -> new TroughBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> METAL_TROUGH = registerWithItem("metal_trough", () -> new MetalTroughBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2.5F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> WATER_BOTTLE = registerWithItem("water_bottle", () -> new WaterBottleBlock(BlockBehaviour.Properties.of(Material.CLAY).noOcclusion()));

    //    public static final Map<String, RegistryObject<Block>> PLANTERS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> PLANKS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> STAIRS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> SLABS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> PRESSURE_PLATES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> BUTTONS = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> FENCES = new HashMap<>();
    public static final Map<String, RegistryObject<Block>> FENCE_GATES = new HashMap<>();

    static {
        /*for (String woodType : new String[]{"oak", "spruce", "birch", "acacia", "jungle", "dark_oak", "crimson", "warped"})
            PLANTERS.put(woodType, registerWithItem(woodType + "_planter", () -> new Block(AbstractBlock.Properties.of(Material.WOOD))));*/

        for (String color : new String[]{"blue", "green", "red", "white"}) {
            PLANKS.put(color, registerWithItem(color + "_barn_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            STAIRS.put(color, registerWithItem(color + "_barn_stairs", () -> new StairBlock(Blocks.OAK_PLANKS::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
            SLABS.put(color, registerWithItem(color + "_barn_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            PRESSURE_PLATES.put(color, registerWithItem(color + "_barn_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD))));
            BUTTONS.put(color, registerWithItem(color + "_barn_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD))));
            FENCES.put(color, registerWithItem(color + "_barn_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            FENCE_GATES.put(color, registerWithItem(color + "_barn_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
        }

        for (String color : new String[]{"dark", "gray", "tan"}) {
            PLANKS.put(color, registerWithItem(color + "_siding", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            STAIRS.put(color, registerWithItem(color + "_siding_stairs", () -> new StairBlock(Blocks.OAK_PLANKS::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))));
            SLABS.put(color, registerWithItem(color + "_siding_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            PRESSURE_PLATES.put(color, registerWithItem(color + "_siding_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD))));
            BUTTONS.put(color, registerWithItem(color + "_siding_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD))));
            FENCES.put(color, registerWithItem(color + "_siding_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
            FENCE_GATES.put(color, registerWithItem(color + "_siding_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD))));
        }
    }

    // WILD CROPS
    public static final RegistryObject<Block> WILD_GRAPE = REGISTRAR.register("wild_grape", () -> new CropVineBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.VINE), HotItems.GRAPES));
    public static final RegistryObject<Block> WILD_KIWI = REGISTRAR.register("wild_kiwi", () -> new CropVineBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.VINE), HotItems.KIWI));
    public static final RegistryObject<Block> WILD_PEA = REGISTRAR.register("wild_pea", () -> new CropVineBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().sound(SoundType.VINE), HotItems.PEAS));
    public static final RegistryObject<Block> WILD_TOMATO = REGISTRAR.register("wild_tomato", () -> new WildCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS), HotItems.TOMATO));
    public static final RegistryObject<Block> WILD_OATS = REGISTRAR.register("wild_oats", () -> new WildCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS), HotItems.OATS));
    public static final RegistryObject<Block> WILD_GARLIC = REGISTRAR.register("wild_garlic", () -> new WildCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS), HotItems.GARLIC));
    public static final RegistryObject<Block> WILD_KALE = REGISTRAR.register("wild_kale", () -> new WildCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS), HotItems.KALE));

    // CROP BLOCKS
    public static final RegistryObject<Block> BANANA_TREE = REGISTRAR.register("banana_tree", () -> new TripleCropBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.CROP), HotItems.BANANA, 1, 2));
    public static final RegistryObject<Block> BLUEBERRY_BUSH = REGISTRAR.register("blueberry_bush", () -> new BerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.BLUEBERRIES));
    public static final RegistryObject<Block> CABBAGE_CROP = REGISTRAR.register("cabbage", () -> new StandardCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.CABBAGE));
    public static final RegistryObject<Block> CORN_CROP = REGISTRAR.register("corn", () -> new TripleCropBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.CROP), HotItems.CORN, 2, 3));
    public static final RegistryObject<Block> COTTON_BUSH = REGISTRAR.register("cotton_bush", () -> new BerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.COTTON));
    public static final RegistryObject<Block> GARLIC_CROP = REGISTRAR.register("garlic", () -> new StandardCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.GARLIC));
    public static final RegistryObject<Block> KALE_CROP = REGISTRAR.register("kale", () -> new StandardCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.KALE));
    public static final RegistryObject<Block> LETTUCE_CROP = REGISTRAR.register("lettuce", () -> new StandardCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.LETTUCE));
    public static final RegistryObject<Block> MILLET_CROP = REGISTRAR.register("millet", () -> new TripleCropBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.CROP), HotItems.MILLET, 2, 3));
    public static final RegistryObject<Block> OATS_CROP = REGISTRAR.register("oats", () -> new StandardCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.OATS));
    public static final RegistryObject<Block> OKRA_BUSH = REGISTRAR.register("okra_bush", () -> new BerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.OKRA));
    public static final RegistryObject<Block> PEPPER_BUSH = REGISTRAR.register("pepper_bush", () -> new PepperBerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.PEPPERS));
    public static final RegistryObject<Block> RICE_CROP = REGISTRAR.register("rice", () -> new DoubleWaterCropBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.RICE, 2));
    public static final RegistryObject<Block> STRAWBERRY_BUSH = REGISTRAR.register("strawberry_bush", () -> new BerryBushBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.STRAWBERRY));

    // TRELLIS CROPS
    public static final RegistryObject<Block> CUCUMBER_TRELLIS = REGISTRAR.register("cucumber_trellis", () -> new TrellisBlock(HotItems.CUCUMBER));
    public static final RegistryObject<Block> GRAPE_TRELLIS = REGISTRAR.register("grape_trellis", () -> new TrellisBlock(HotItems.GRAPES));
    public static final RegistryObject<Block> KIWI_TRELLIS = REGISTRAR.register("kiwi_trellis", () -> new TrellisBlock(HotItems.KIWI));
    public static final RegistryObject<Block> PEA_TRELLIS = REGISTRAR.register("pea_trellis", () -> new TrellisBlock(HotItems.PEAS));
    public static final RegistryObject<Block> TOMATO_TRELLIS = REGISTRAR.register("tomato_trellis", () -> new TrellisBlock(HotItems.TOMATO));

    // FRUIT TREES
    public static final RegistryObject<Block> FRUIT_LEAVES = registerWithItem("fruit_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> CITRUS_LEAVES = registerWithItem("citrus_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> FICUS_LEAVES = registerWithItem("ficus_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> TROPICAL_FRUIT_LEAVES = registerWithItem("tropical_fruit_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, reader, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final RegistryObject<Block> CITRON_SAPLING = registerWithItem("citron_seed", () -> new SaplingBlock(new CitronTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> CITRON_LEAVES = registerWithItem("citron_leaves", () -> new FruitLeavesBlock(HotItems.CITRON));
    public static final RegistryObject<Block> FIG_SAPLING = registerWithItem("fig_seed", () -> new SaplingBlock(new FigTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> FIG_LEAVES = registerWithItem("fig_leaves", () -> new FruitLeavesBlock(HotItems.FIG));
    public static final RegistryObject<Block> GRAPEFRUIT_SAPLING = registerWithItem("grapefruit_seed", () -> new SaplingBlock(new GrapefruitTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> GRAPEFRUIT_LEAVES = registerWithItem("grapefruit_leaves", () -> new FruitLeavesBlock(HotItems.GRAPEFRUIT));
    public static final RegistryObject<Block> LEMON_SAPLING = registerWithItem("lemon_seed", () -> new SaplingBlock(new LemonTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LEMON_LEAVES = registerWithItem("lemon_leaves", () -> new FruitLeavesBlock(HotItems.LEMON));
    public static final RegistryObject<Block> LIME_SAPLING = registerWithItem("lime_seed", () -> new SaplingBlock(new LimeTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LIME_LEAVES = registerWithItem("lime_leaves", () -> new FruitLeavesBlock(HotItems.LIME));
    public static final RegistryObject<Block> MANDARIN_SAPLING = registerWithItem("mandarin_seed", () -> new SaplingBlock(new MandarinTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MANDARIN_LEAVES = registerWithItem("mandarin_leaves", () -> new FruitLeavesBlock(HotItems.MANDARIN));
    public static final RegistryObject<Block> MANGO_SAPLING = registerWithItem("mango_seed", () -> new SaplingBlock(new MangoTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MANGO_LEAVES = registerWithItem("mango_leaves", () -> new FruitLeavesBlock(HotItems.MANGO));
    public static final RegistryObject<Block> ORANGE_SAPLING = registerWithItem("orange_seed", () -> new SaplingBlock(new OrangeTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new FruitLeavesBlock(HotItems.ORANGE));
    public static final RegistryObject<Block> PAPEDA_SAPLING = registerWithItem("papeda_seed", () -> new SaplingBlock(new PapedaTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PAPEDA_LEAVES = registerWithItem("papeda_leaves", () -> new FruitLeavesBlock(HotItems.PAPEDA));
    public static final RegistryObject<Block> PEACH_SAPLING = registerWithItem("peach_seed", () -> new SaplingBlock(new PeachTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PEACH_LEAVES = registerWithItem("peach_leaves", () -> new FruitLeavesBlock(HotItems.PEACH));
    public static final RegistryObject<Block> POMEGRANATE_SAPLING = registerWithItem("pomegranate_seed", () -> new SaplingBlock(new PomegranateTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POMEGRANATE_LEAVES = registerWithItem("pomegranate_leaves", () -> new FruitLeavesBlock(HotItems.POMEGRANATE));
    public static final RegistryObject<Block> POMELO_SAPLING = registerWithItem("pomelo_seed", () -> new SaplingBlock(new PomeloTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POMELO_LEAVES = registerWithItem("pomelo_leaves", () -> new FruitLeavesBlock(HotItems.POMELO));
    public static final RegistryObject<Block> RED_APPLE_SAPLING = registerWithItem("red_apple_seed", () -> new SaplingBlock(new RedAppleTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> RED_APPLE_LEAVES = registerWithItem("red_apple_leaves", () -> new FruitLeavesBlock(() -> Items.APPLE));
    public static final RegistryObject<Block> YUZU_SAPLING = registerWithItem("yuzu_seed", () -> new SaplingBlock(new YuzuTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> YUZU_LEAVES = registerWithItem("yuzu_leaves", () -> new FruitLeavesBlock(HotItems.YUZU));

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = REGISTRAR.register(name, block);
        HotItems.REGISTRAR.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP)));
        return registryObject;
    }

    private static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

    public static void setRenderLayers() {
        RenderType cutoutMipped = RenderType.cutoutMipped();
        ItemBlockRenderTypes.setRenderLayer(HUTCH_BARS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(HUTCH_FLOOR.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(HUTCH_GATE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(CUCUMBER_TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(GRAPE_TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(KIWI_TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TOMATO_TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(PEA_TRELLIS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(FRUIT_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(CITRUS_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(FICUS_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TROPICAL_FRUIT_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(RED_APPLE_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(PEACH_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(MANGO_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(POMEGRANATE_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(FIG_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(CITRON_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(POMELO_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(MANDARIN_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(PAPEDA_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(ORANGE_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(LEMON_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(GRAPEFRUIT_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(LIME_LEAVES.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(YUZU_LEAVES.get(), cutoutMipped);
        RenderType cutout = RenderType.cutout();
        ItemBlockRenderTypes.setRenderLayer(BANANA_TREE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(CABBAGE_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(KALE_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(RICE_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(STRAWBERRY_BUSH.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLUEBERRY_BUSH.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(COTTON_BUSH.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(PEPPER_BUSH.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(OKRA_BUSH.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(OATS_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(GARLIC_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(LETTUCE_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(CORN_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(MILLET_CROP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(RED_APPLE_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(PEACH_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(MANGO_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(POMEGRANATE_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(FIG_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(CITRON_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(POMELO_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(MANDARIN_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(PAPEDA_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(ORANGE_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(LEMON_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(GRAPEFRUIT_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(LIME_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(YUZU_SAPLING.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_OATS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_GARLIC.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_KALE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_GRAPE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_KIWI.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_TOMATO.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(WILD_PEA.get(), cutout);
    }
}
