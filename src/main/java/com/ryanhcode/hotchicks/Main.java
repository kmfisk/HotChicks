package com.ryanhcode.hotchicks;

import com.google.common.collect.ImmutableSet;
import com.ryanhcode.hotchicks.block.NestContainer;
import com.ryanhcode.hotchicks.block.NestScreen;
import com.ryanhcode.hotchicks.block.TroughScreen;
import com.ryanhcode.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.ryanhcode.hotchicks.registry.*;
import com.ryanhcode.hotchicks.worldgen.LowLightPlacer;
import com.ryanhcode.hotchicks.worldgen.MilletPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
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

@Mod(HotChickens.MODID)
public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

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


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ConfiguredFeature<?,?> CORN;
    public static ConfiguredFeature<?,?> MILLET;
    public static ConfiguredFeature<?,?> BLUEBERRY_PATCHES;


    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            //FEATURES

            CORN = Feature.RANDOM_PATCH.withConfiguration(

                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.CORN.get().getDefaultState()),
                            new MilletPlacer(0,0))).tries(30).xSpread(4).ySpread(0).zSpread(4).func_227317_b_().requiresWater().build()

            ).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(20);

            MILLET = Feature.RANDOM_PATCH.withConfiguration(

                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.MILLET.get().getDefaultState()),
                            new MilletPlacer(0,0))).tries(30).xSpread(10).ySpread(0).zSpread(10).func_227317_b_().requiresWater().build()

            ).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(20);

            BLUEBERRY_PATCHES = Feature.RANDOM_PATCH.withConfiguration(

                    (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.BLUEBERRY_BUSH.get().getDefaultState()), new LowLightPlacer())).tries(200).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).func_227317_b_().build()

            );
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "millet_patches"), MILLET);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "corn_patches"), CORN);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "blueberry_patches"), BLUEBERRY_PATCHES);


            RenderTypeLookup.setRenderLayer(BlockRegistry.STRAWBERRY_BUSH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLUEBERRY_BUSH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.COTTON_BUSH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.PEPPER_BUSH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.OKRA_BUSH.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.OATS_BLOCK.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.GARLIC_BLOCK.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.LETTUCE_BLOCK.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.CORN.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(BlockRegistry.MILLET.get(), RenderType.getCutout());
            GlobalEntityTypeAttributes.put(EntityRegistry.HOT_CHICKEN.get(), MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25).create());
        });
    }

    @SubscribeEvent
    public void biomeLoad(BiomeLoadingEvent event) {

        ResourceLocation biomeName = event.getName();
        if (biomeName.equals(Biomes.SAVANNA.getLocation()) || biomeName.equals(Biomes.SHATTERED_SAVANNA.getLocation())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.LAKES).add(() -> MILLET);
        }
        if (biomeName.equals(Biomes.PLAINS.getLocation()) || biomeName.equals(Biomes.SUNFLOWER_PLAINS.getLocation())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);
        }

        if (biomeName.equals(Biomes.TAIGA_HILLS.getLocation()) || biomeName.equals(Biomes.TAIGA.getLocation()) || biomeName.equals(Biomes.TAIGA_MOUNTAINS.getLocation())) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_PATCHES);
        }
    }
    public void registerRenderers(final FMLClientSetupEvent event) {

        ScreenManager.registerFactory(ContainerRegistry.NEST.get(), NestScreen::new);
        ScreenManager.registerFactory(ContainerRegistry.TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.registerFactory(ContainerRegistry.TROUGH_SINGLE.get(), TroughScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HOT_CHICKEN.get(), HotChickenRenderer::new);
    }

    public void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockcolors = event.getBlockColors();
        blockcolors.register((state, reader, pos, color) -> {
            return reader != null && pos != null ? BiomeColors.getWaterColor(reader, pos) : -1;
        },  BlockRegistry.TROUGH_BLOCK.get(), BlockRegistry.METAL_TROUGH_BLOCK.get());
    }
}