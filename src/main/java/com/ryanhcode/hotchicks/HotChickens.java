package com.ryanhcode.hotchicks;

import com.ryanhcode.hotchicks.block.HotBlocks;
import com.ryanhcode.hotchicks.block.NestScreen;
import com.ryanhcode.hotchicks.block.TroughScreen;
import com.ryanhcode.hotchicks.block.trellis.TrellisModelLoader;
import com.ryanhcode.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.ryanhcode.hotchicks.entity.HotEntities;
import com.ryanhcode.hotchicks.item.HotItems;
import com.ryanhcode.hotchicks.registry.HotContainers;
import com.ryanhcode.hotchicks.registry.HotTileEntities;
import com.ryanhcode.hotchicks.worldgen.HotFeatures;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HotChickens.MOD_ID)
public class HotChickens {
    public static final String MOD_ID = "hotchicks";

    public static ItemGroup HOT_CHICKS_GROUP = new ItemGroup(HotChickens.MOD_ID + ".hot_chicks_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HotItems.WHITE_EGG.get());
        }
    };

    public HotChickens() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        HotEntities.ENTITIES.register(modBus);
        HotTileEntities.ENTITIES.register(modBus);
        HotBlocks.REGISTRAR.register(modBus);
        HotItems.ITEMS.register(modBus);
        HotContainers.CONTAINERS.register(modBus);

        modBus.addListener(this::setup);
        modBus.addListener(this::registerAttributes);
        modBus.addListener(this::registerRenderers);
        modBus.addListener(this::registerColorHandlerBlocks);
        modBus.addListener(this::modelLoad);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(HotFeatures::registerFeatures);
    }

    private void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(HotEntities.HOT_CHICKEN.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25).build());
    }

    private void modelLoad(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(HotChickens.MOD_ID, "trellisloader"), new TrellisModelLoader());
    }

    private void registerRenderers(final FMLClientSetupEvent event) {
        ScreenManager.register(HotContainers.NEST.get(), NestScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.register(HotContainers.TROUGH_SINGLE.get(), TroughScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(HotEntities.HOT_CHICKEN.get(), HotChickenRenderer::new);

        HotBlocks.setRenderLayers();
    }

    private void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockcolors = event.getBlockColors();
        blockcolors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1,
                HotBlocks.TROUGH_BLOCK.get(), HotBlocks.METAL_TROUGH_BLOCK.get());
        blockcolors.register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColors.getDefaultColor(),
                HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.PEACH_LEAVES.get(), HotBlocks.MANGO_LEAVES.get(), HotBlocks.POMEGRANATE_LEAVES.get(),
                HotBlocks.FIG_LEAVES.get(), HotBlocks.CITRON_LEAVES.get(), HotBlocks.POMELO_LEAVES.get(), HotBlocks.MANDARIN_LEAVES.get(),
                HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.ORANGE_LEAVES.get(), HotBlocks.LEMON_LEAVES.get(), HotBlocks.GRAPEFRUIT_LEAVES.get(),
                HotBlocks.LIME_LEAVES.get(), HotBlocks.YUZU_LEAVES.get());
    }
}