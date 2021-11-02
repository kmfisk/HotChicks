package com.ryanhcode.hotchicks;

import com.google.common.collect.ImmutableSet;
import com.ryanhcode.hotchicks.block.NestScreen;
import com.ryanhcode.hotchicks.block.TroughScreen;
import com.ryanhcode.hotchicks.block.trellis.TrellisModelLoader;
import com.ryanhcode.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.ryanhcode.hotchicks.registry.*;
import com.ryanhcode.hotchicks.worldgen.LowLightPlacer;
import com.ryanhcode.hotchicks.worldgen.MilletPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalInt;

@Mod(HotChickens.MODID)
public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    public static ItemGroup HOT_CHICKS_GROUP;

    public Main() {
        // Register the setup method for modloading
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityRegistry.ENTITIES.register(bus);
        TileEntityRegistry.ENTITIES.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        ContainerRegistry.CONTAINERS.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::registerRenderers);
        bus.addListener(this::registerColorHandlerBlocks);
        bus.addListener(this::modelLoad);

        HOT_CHICKS_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), "hot_chicks_group") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ItemRegistry.WHITE_EGG.get());
            }
        };


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ConfiguredFeature<?, ?> CORN;
    public static ConfiguredFeature<?, ?> MILLET;
    public static ConfiguredFeature<?, ?> BLUEBERRY_PATCHES;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MANDARIN_TREES =
            Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()),
                    new SimpleBlockStateProvider(Blocks.SPONGE.defaultBlockState()),
                    new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                    new FancyTrunkPlacer(2
                            , 10, 4),
                    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
                    .ignoreVines()
                    .heightmap(Heightmap.Type.MOTION_BLOCKING).build());


    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {//FEATURES


            CORN = Feature.RANDOM_PATCH.configured(

                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.CORN.get().defaultBlockState()),
                            new MilletPlacer(0, 0))).tries(30).xspread(4).yspread(0).zspread(4).noProjection().needWater().build()

            ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

            MILLET = Feature.RANDOM_PATCH.configured(

                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.MILLET.get().defaultBlockState()),
                            new MilletPlacer(0, 0))).tries(30).xspread(10).yspread(0).zspread(10).noProjection().needWater().build()

            ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

            BLUEBERRY_PATCHES = Feature.RANDOM_PATCH.configured(

                    (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.BLUEBERRY_BUSH.get().defaultBlockState()), new LowLightPlacer())).tries(200).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()

            );
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "mandarin_trees"), MANDARIN_TREES);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "millet_patches"), MILLET);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "corn_patches"), CORN);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "blueberry_patches"), BLUEBERRY_PATCHES);


            RenderTypeLookup.setRenderLayer(BlockRegistry.TRELLIS_BLOCK.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(BlockRegistry.STRAWBERRY_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLUEBERRY_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.COTTON_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.PEPPER_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.OKRA_BUSH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.OATS_BLOCK.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.GARLIC_BLOCK.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.LETTUCE_BLOCK.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.CORN.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.MILLET.get(), RenderType.cutout());
            GlobalEntityTypeAttributes.put(EntityRegistry.HOT_CHICKEN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25).build());
        });
    }

    @SubscribeEvent
    public void biomeLoad(BiomeLoadingEvent event) {

        ResourceLocation biomeName = event.getName();
        if (biomeName.equals(Biomes.SAVANNA.location()) || biomeName.equals(Biomes.SHATTERED_SAVANNA.location())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.LAKES).add(() -> MILLET);
        }
        if (biomeName.equals(Biomes.PLAINS.location()) || biomeName.equals(Biomes.SUNFLOWER_PLAINS.location())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN_TREES);
        }

        if (biomeName.equals(Biomes.TAIGA_HILLS.location()) || biomeName.equals(Biomes.TAIGA.location()) || biomeName.equals(Biomes.TAIGA_MOUNTAINS.location())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_PATCHES);
        }
    }

    public void modelLoad(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(HotChickens.MODID, "trellisloader"), new TrellisModelLoader());
    }

    public void registerRenderers(final FMLClientSetupEvent event) {

        ScreenManager.register(ContainerRegistry.NEST.get(), NestScreen::new);
        ScreenManager.register(ContainerRegistry.TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        ScreenManager.register(ContainerRegistry.TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.register(ContainerRegistry.TROUGH_SINGLE.get(), TroughScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HOT_CHICKEN.get(), HotChickenRenderer::new);
    }

    public void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockcolors = event.getBlockColors();
        blockcolors.register((state, reader, pos, color) -> {
            return reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1;
        }, BlockRegistry.TROUGH_BLOCK.get(), BlockRegistry.METAL_TROUGH_BLOCK.get());
    }
}