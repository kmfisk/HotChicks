package com.ryanhcode.hotchicks;

import com.ryanhcode.hotchicks.block.NestContainer;
import com.ryanhcode.hotchicks.block.NestScreen;
import com.ryanhcode.hotchicks.block.TroughScreen;
import com.ryanhcode.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.ryanhcode.hotchicks.registry.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.HopperScreen;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
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

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GlobalEntityTypeAttributes.put(EntityRegistry.HOT_CHICKEN.get(), MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25).create());
        });
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
        },  BlockRegistry.TROUGH_BLOCK.get());
    }
}