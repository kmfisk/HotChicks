package com.github.kmfisk.hotchicks;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.client.gui.NestScreen;
import com.github.kmfisk.hotchicks.client.gui.TroughScreen;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.github.kmfisk.hotchicks.entity.HotEntities;
import com.github.kmfisk.hotchicks.item.HotItems;
import com.github.kmfisk.hotchicks.registry.*;
import com.github.kmfisk.hotchicks.worldgen.HotFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(HotChicks.MOD_ID)
public class HotChicks {
    public static final String MOD_ID = "hotchicks";

    public static ItemGroup HOT_CHICKS_GROUP = new ItemGroup(HotChicks.MOD_ID + ".hot_chicks_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HotItems.WHITE_EGG.get());
        }
    };

    public HotChicks() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        HotEntities.REGISTRAR.register(modBus);
        HotTileEntities.REGISTRAR.register(modBus);
        HotBlocks.REGISTRAR.register(modBus);
        HotItems.REGISTRAR.register(modBus);
        HotSounds.REGISTRAR.register(modBus);
        HotContainers.REGISTRAR.register(modBus);
        HotGlobalLootModifier.REGISTRAR.register(modBus);

        forgeBus.addListener(HotVillagerTrades::onVillagerTradesEvent);

        modBus.addListener(this::setup);
        modBus.addListener(this::registerAttributes);


        if (FMLEnvironment.dist == Dist.CLIENT) {
            modBus.addListener(this::setupClient);
            modBus.addListener(this::registerColorHandlerBlocks);
            modBus.addListener(this::registerColorHandlerItems);
        }

        forgeBus.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(HotFeatures::registerFeatures);
    }

    private void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(HotEntities.CHICKEN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 1.0D).build());
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ScreenManager.register(HotContainers.NEST.get(), NestScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_SINGLE.get(), TroughScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(HotEntities.CHICKEN.get(), HotChickenRenderer::new);

        HotBlocks.setRenderLayers();
    }

    private void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockcolors = event.getBlockColors();
        blockcolors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1,
                HotBlocks.TROUGH_BLOCK.get(), HotBlocks.METAL_TROUGH_BLOCK.get());
        blockcolors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColors.getDefaultColor(),
                HotBlocks.CITRUS_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(),
                HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.PEACH_LEAVES.get(), HotBlocks.MANGO_LEAVES.get(), HotBlocks.POMEGRANATE_LEAVES.get(),
                HotBlocks.FIG_LEAVES.get(), HotBlocks.CITRON_LEAVES.get(), HotBlocks.POMELO_LEAVES.get(), HotBlocks.MANDARIN_LEAVES.get(),
                HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.ORANGE_LEAVES.get(), HotBlocks.LEMON_LEAVES.get(), HotBlocks.GRAPEFRUIT_LEAVES.get(),
                HotBlocks.LIME_LEAVES.get(), HotBlocks.YUZU_LEAVES.get());
    }

    private void registerColorHandlerItems(final ColorHandlerEvent.Item event) {
        ItemColors itemcolors = event.getItemColors();
        itemcolors.register((color, item) -> {
                    BlockState blockState = ((BlockItem) color.getItem()).getBlock().defaultBlockState();
                    return event.getBlockColors().getColor(blockState, null, null, item);
                }, HotBlocks.CITRUS_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(),
                HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.PEACH_LEAVES.get(), HotBlocks.MANGO_LEAVES.get(), HotBlocks.POMEGRANATE_LEAVES.get(),
                HotBlocks.FIG_LEAVES.get(), HotBlocks.CITRON_LEAVES.get(), HotBlocks.POMELO_LEAVES.get(), HotBlocks.MANDARIN_LEAVES.get(),
                HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.ORANGE_LEAVES.get(), HotBlocks.LEMON_LEAVES.get(), HotBlocks.GRAPEFRUIT_LEAVES.get(),
                HotBlocks.LIME_LEAVES.get(), HotBlocks.YUZU_LEAVES.get());
    }
}