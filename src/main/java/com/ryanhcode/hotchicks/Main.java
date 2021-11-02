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
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
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

@Mod(HotChickens.MODID)
public class Main {
    public static ItemGroup HOT_CHICKS_GROUP = new ItemGroup(HotChickens.MODID + ".hot_chicks_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HotItems.WHITE_EGG.get());
        }
    };

    public Main() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        HotEntities.ENTITIES.register(modBus);
        HotTileEntities.ENTITIES.register(modBus);
        HotBlocks.REGISTRAR.register(modBus);
        HotItems.ITEMS.register(modBus);
        HotContainers.CONTAINERS.register(modBus);

        modBus.addListener(this::setup);
        modBus.addListener(this::registerRenderers);
        modBus.addListener(this::registerColorHandlerBlocks);
        modBus.addListener(this::modelLoad);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ConfiguredFeature<?, ?> CORN;
    public static ConfiguredFeature<?, ?> MILLET;
    public static ConfiguredFeature<?, ?> BLUEBERRY_PATCHES;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> MANDARIN_TREES;

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CORN = Feature.RANDOM_PATCH.configured(
                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(HotBlocks.CORN_CROP.get().defaultBlockState()),
                            new MilletPlacer(0, 0))).tries(30).xspread(4).yspread(0).zspread(4).noProjection().needWater().build()
            ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

            MILLET = Feature.RANDOM_PATCH.configured(
                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(HotBlocks.MILLET_CROP.get().defaultBlockState()),
                            new MilletPlacer(0, 0))).tries(30).xspread(10).yspread(0).zspread(10).noProjection().needWater().build()
            ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(20);

            BLUEBERRY_PATCHES = Feature.RANDOM_PATCH.configured(
                    (new BlockClusterFeatureConfig.Builder(
                            new SimpleBlockStateProvider(HotBlocks.BLUEBERRY_BUSH.get().defaultBlockState()),
                            new LowLightPlacer())).tries(200).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build()
            );

            MANDARIN_TREES = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()),
                    new WeightedBlockStateProvider().add(HotBlocks.MANDARIN_LEAVES.get().defaultBlockState(), 2).add(Blocks.OAK_LEAVES.defaultBlockState(), 5),
                    new AcaciaFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1)),
                    new ForkyTrunkPlacer(3, 2, 2),
                    new TwoLayerFeature(1, 0, 2)))
                    .ignoreVines()
                    .heightmap(Heightmap.Type.MOTION_BLOCKING).build()
            );

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "corn_patches"), CORN);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "millet_patches"), MILLET);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "blueberry_patches"), BLUEBERRY_PATCHES);
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(HotChickens.MODID, "mandarin_trees"), MANDARIN_TREES);

            GlobalEntityTypeAttributes.put(HotEntities.HOT_CHICKEN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25).build());
        });
    }

    @SubscribeEvent
    public void biomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();
        if (biomeName.equals(Biomes.SAVANNA.location()) || biomeName.equals(Biomes.SHATTERED_SAVANNA.location()))
            event.getGeneration().getFeatures(GenerationStage.Decoration.LAKES).add(() -> MILLET);

        if (biomeName.equals(Biomes.PLAINS.location()) || biomeName.equals(Biomes.SUNFLOWER_PLAINS.location()))
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);

        if (biomeName.equals(Biomes.MOUNTAINS.location()))
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN_TREES);

        if (biomeName.equals(Biomes.TAIGA_HILLS.location()) || biomeName.equals(Biomes.TAIGA.location()) || biomeName.equals(Biomes.TAIGA_MOUNTAINS.location()))
            event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_PATCHES);
    }

    public void modelLoad(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(HotChickens.MODID, "trellisloader"), new TrellisModelLoader());
    }

    public void registerRenderers(final FMLClientSetupEvent event) {
        ScreenManager.register(HotContainers.NEST.get(), NestScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_SINGLE.get(), TroughScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(HotEntities.HOT_CHICKEN.get(), HotChickenRenderer::new);
    }

    public void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockcolors = event.getBlockColors();
        blockcolors.register((state, reader, pos, color) -> {
            return reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1;
        }, HotBlocks.TROUGH_BLOCK.get(), HotBlocks.METAL_TROUGH_BLOCK.get());

        blockcolors.register((state, reader, pos, color) -> {
            return reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColors.getDefaultColor();
        }, HotBlocks.MANDARIN_LEAVES.get());
    }
}