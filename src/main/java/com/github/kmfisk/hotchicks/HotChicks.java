package com.github.kmfisk.hotchicks;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.client.ColorEvents;
import com.github.kmfisk.hotchicks.client.HotSounds;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotCowRenderer;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotRabbitRenderer;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotCowModel;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotRabbitModel;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.data.HotRecipeProvider;
import com.github.kmfisk.hotchicks.entity.HotEntities;
import com.github.kmfisk.hotchicks.entity.merchant.villager.HotVillagerTrades;
import com.github.kmfisk.hotchicks.event.HotEvents;
import com.github.kmfisk.hotchicks.inventory.HotContainerTypes;
import com.github.kmfisk.hotchicks.item.HotItems;
import com.github.kmfisk.hotchicks.loot.HotGlobalLootModifier;
import com.github.kmfisk.hotchicks.worldgen.HotFeature;
import com.github.kmfisk.hotchicks.worldgen.HotFeatures;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(HotChicks.MOD_ID)
public class HotChicks {
    public static final String MOD_ID = "hotchicks";

    public static final CreativeModeTab HOT_CHICKS_GROUP = new CreativeModeTab(HotChicks.MOD_ID + ".hot_chicks_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HotItems.WHITE_EGG.get());
        }
    };

    public HotChicks() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(HotVillagerTrades::onVillagerTradesEvent);

        HotEntities.REGISTRAR.register(bus);
        HotTileEntities.REGISTRAR.register(bus);
        HotBlocks.REGISTRAR.register(bus);
        HotItems.REGISTRAR.register(bus);
        HotFeature.REGISTRAR.register(bus);
        HotSounds.REGISTRAR.register(bus);
        HotContainerTypes.REGISTRAR.register(bus);
        HotGlobalLootModifier.REGISTRAR.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::registerAttributes);
        bus.addListener(this::gatherData);

        bus.addListener(this::setupClient);
        bus.addListener(HotEvents::onLoadComplete);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            bus.addListener(ColorEvents::registerColorHandlerBlocks);
            bus.addListener(ColorEvents::registerColorHandlerItems);
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HotChicksConfig.CONFIG_SPEC);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
//            HotFeatures.registerFeatures();
//            registerCompostables();
        });
        HotEntities.registerSpawnPlacements();
    }

    private void setupClient(final FMLClientSetupEvent event) {
//        HotEntities.registerRenderers();
        HotBlocks.setRenderLayers();
        HotContainerTypes.registerFactories();
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HotEntities.CHICKEN.get(), HotChickenRenderer::new);
        event.registerEntityRenderer(HotEntities.RABBIT.get(), HotRabbitRenderer::new);
        event.registerEntityRenderer(HotEntities.COW.get(), HotCowRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HotChickenRenderer.HEN, HotChickenModel.Hen::createBodyLayer);
        event.registerLayerDefinition(HotChickenRenderer.HEN_SILKIE, HotChickenModel.HenSilkie::createBodyLayer);
        event.registerLayerDefinition(HotChickenRenderer.ROOSTER, HotChickenModel.Rooster::createBodyLayer);
        event.registerLayerDefinition(HotChickenRenderer.ROOSTER_SILKIE, HotChickenModel.RoosterSilkie::createBodyLayer);
        event.registerLayerDefinition(HotChickenRenderer.CHICK, HotChickenModel.Chick::createBodyLayer);
        event.registerLayerDefinition(HotCowRenderer.COW, HotCowModel.Adult::createBodyLayer);
        event.registerLayerDefinition(HotCowRenderer.CALF, HotCowModel.Calf::createBodyLayer);
        event.registerLayerDefinition(HotRabbitRenderer.RABBIT, HotRabbitModel::createBodyLayer);
    }

    private void registerAttributes(final EntityAttributeCreationEvent event) {
        HotEntities.registerAttributes((type, builder) -> event.put(type, builder.build()));
    }

    private void gatherData(final GatherDataEvent event) {
        System.out.println("Generating hotchicks Data!");
        DataGenerator dataGenerator = event.getGenerator();
        if (event.includeServer()) dataGenerator.addProvider(new HotRecipeProvider(dataGenerator));
    }

    /*private static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(HotItems.CABBAGE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.GARLIC.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.KALE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.LETTUCE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.OATS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.CORN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.MILLET.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.RICE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.CUCUMBER.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.GRAPES.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.KIWI.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.PEAS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.TOMATO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.BANANA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.BLUEBERRIES.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.COTTON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.OKRA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.PEPPERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.STRAWBERRY.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.CITRON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.FIG.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.GRAPEFRUIT.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.LEMON.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.LIME.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.MANDARIN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.MANGO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.ORANGE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.PAPEDA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.PEACH.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.POMEGRANATE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.POMELO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HotItems.YUZU.get(), 0.65F);
    }*/
}