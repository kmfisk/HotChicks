package com.ryanhcode.hotchicks.block;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.crop.BerryBush;
import com.ryanhcode.hotchicks.block.crop.CornBlock;
import com.ryanhcode.hotchicks.block.crop.PepperBerryBush;
import com.ryanhcode.hotchicks.block.crop.StandardCropBlock;
import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HotBlocks {
    public static final DeferredRegister<Block> REGISTRAR = DeferredRegister.create(ForgeRegistries.BLOCKS, HotChickens.MOD_ID);

    public static final RegistryObject<Block> NEST_BOX = registerWithItem("nest_box", () -> new NestBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> NEST = registerWithItem("nest", () -> new StickNestBlock(AbstractBlock.Properties.of(Material.GRASS).strength(0.5F).sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> TROUGH_BLOCK = registerWithItem("trough_block", () -> new TroughBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> METAL_TROUGH_BLOCK = registerWithItem("metal_trough_block", () -> new MetalTroughBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STRAWBERRY_BUSH = registerWithItem("strawberry_bush", () -> new BerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.STRAWBERRY.get()));
    public static final RegistryObject<Block> BLUEBERRY_BUSH = registerWithItem("blueberry_bush", () -> new BerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.BLUEBERRIES.get()));
    public static final RegistryObject<Block> COTTON_BUSH = registerWithItem("cotton_bush", () -> new BerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.COTTON.get()));
    public static final RegistryObject<Block> OKRA_BUSH = registerWithItem("okra_bush", () -> new BerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.OKRA.get()));
    public static final RegistryObject<Block> PEPPER_BUSH = registerWithItem("pepper_bush", () -> new PepperBerryBush(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.PEPPERS.get()));

    public static final RegistryObject<Block> RED_APPLE_LEAVES = registerWithItem("red_apple_leaves", () -> new FruitLeavesBlock(Items.APPLE));
    public static final RegistryObject<Block> PEACH_LEAVES = registerWithItem("peach_leaves", () -> new FruitLeavesBlock(HotItems.PEACH.get()));
    public static final RegistryObject<Block> MANGO_LEAVES = registerWithItem("mango_leaves", () -> new FruitLeavesBlock(HotItems.MANGO.get()));
    public static final RegistryObject<Block> POMEGRANATE_LEAVES = registerWithItem("pomegranate_leaves", () -> new FruitLeavesBlock(HotItems.POMEGRANATE.get()));
    public static final RegistryObject<Block> FIG_LEAVES = registerWithItem("fig_leaves", () -> new FruitLeavesBlock(HotItems.FIG.get()));
    public static final RegistryObject<Block> CITRON_LEAVES = registerWithItem("citron_leaves", () -> new FruitLeavesBlock(HotItems.CITRON.get()));
    public static final RegistryObject<Block> POMELO_LEAVES = registerWithItem("pomelo_leaves", () -> new FruitLeavesBlock(HotItems.POMELO.get()));
    public static final RegistryObject<Block> MANDARIN_LEAVES = registerWithItem("mandarin_leaves", () -> new FruitLeavesBlock(HotItems.MANDARIN.get()));
    public static final RegistryObject<Block> PAPEDA_LEAVES = registerWithItem("papeda_leaves", () -> new FruitLeavesBlock(HotItems.PAPEDA.get()));
    public static final RegistryObject<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new FruitLeavesBlock(HotItems.ORANGE.get()));
    public static final RegistryObject<Block> LEMON_LEAVES = registerWithItem("lemon_leaves", () -> new FruitLeavesBlock(HotItems.LEMON.get()));
    public static final RegistryObject<Block> GRAPEFRUIT_LEAVES = registerWithItem("grapefruit_leaves", () -> new FruitLeavesBlock(HotItems.GRAPEFRUIT.get()));
    public static final RegistryObject<Block> LIME_LEAVES = registerWithItem("lime_leaves", () -> new FruitLeavesBlock(HotItems.LIME.get()));
    public static final RegistryObject<Block> YUZU_LEAVES = registerWithItem("yuzu_leaves", () -> new FruitLeavesBlock(HotItems.YUZU.get()));

    public static final RegistryObject<Block> CORN_CROP = REGISTRAR.register("corn", () -> new CornBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.CORN.get()));
    public static final RegistryObject<Block> MILLET_CROP = REGISTRAR.register("millet", () -> new CornBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH), HotItems.MILLET.get()));
    public static final RegistryObject<Block> OATS_CROP = REGISTRAR.register("oats", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.OATS.get()));
    public static final RegistryObject<Block> LETTUCE_CROP = REGISTRAR.register("lettuce", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.LETTUCE.get()));
    public static final RegistryObject<Block> GARLIC_CROP = REGISTRAR.register("garlic", () -> new StandardCropBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), HotItems.GARLIC.get()));

    public static final RegistryObject<Block> TRELLIS_BLOCK = registerWithItem("trellis", () -> new TrellisBlock(AbstractBlock.Properties.of(Material.WOOD).strength(0.8F).sound(SoundType.WOOD).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = REGISTRAR.register(name, block);
        HotItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP)));
        return registryObject;
    }

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            RenderTypeLookup.setRenderLayer(TRELLIS_BLOCK.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(STRAWBERRY_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BLUEBERRY_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(COTTON_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(PEPPER_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(OKRA_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(OATS_CROP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(GARLIC_CROP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(LETTUCE_CROP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(CORN_CROP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(MILLET_CROP.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(RED_APPLE_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(PEACH_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(MANGO_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(POMEGRANATE_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(FIG_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(CITRON_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(POMELO_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(MANDARIN_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(PAPEDA_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ORANGE_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(LEMON_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(GRAPEFRUIT_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(LIME_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(YUZU_LEAVES.get(), RenderType.cutoutMipped());
        }
    }
}
