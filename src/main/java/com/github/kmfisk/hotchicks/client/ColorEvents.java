package com.github.kmfisk.hotchicks.client;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class ColorEvents {
    public static void registerColorHandlerBlocks(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1,
                HotBlocks.WOODEN_TROUGH.get(), HotBlocks.METAL_TROUGH.get());
        event.getBlockColors().register((state, reader, pos, color) -> reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColor.getDefaultColor(),
                HotBlocks.CITRUS_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(),
                HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.PEACH_LEAVES.get(), HotBlocks.MANGO_LEAVES.get(), HotBlocks.POMEGRANATE_LEAVES.get(),
                HotBlocks.FIG_LEAVES.get(), HotBlocks.CITRON_LEAVES.get(), HotBlocks.POMELO_LEAVES.get(), HotBlocks.MANDARIN_LEAVES.get(),
                HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.ORANGE_LEAVES.get(), HotBlocks.LEMON_LEAVES.get(), HotBlocks.GRAPEFRUIT_LEAVES.get(),
                HotBlocks.LIME_LEAVES.get(), HotBlocks.YUZU_LEAVES.get());
    }

    public static void registerColorHandlerItems(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, color) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
                    return event.getBlockColors().getColor(blockState, null, null, color);
                }, HotBlocks.CITRUS_LEAVES.get(), HotBlocks.FICUS_LEAVES.get(), HotBlocks.FRUIT_LEAVES.get(), HotBlocks.TROPICAL_FRUIT_LEAVES.get(),
                HotBlocks.RED_APPLE_LEAVES.get(), HotBlocks.PEACH_LEAVES.get(), HotBlocks.MANGO_LEAVES.get(), HotBlocks.POMEGRANATE_LEAVES.get(),
                HotBlocks.FIG_LEAVES.get(), HotBlocks.CITRON_LEAVES.get(), HotBlocks.POMELO_LEAVES.get(), HotBlocks.MANDARIN_LEAVES.get(),
                HotBlocks.PAPEDA_LEAVES.get(), HotBlocks.ORANGE_LEAVES.get(), HotBlocks.LEMON_LEAVES.get(), HotBlocks.GRAPEFRUIT_LEAVES.get(),
                HotBlocks.LIME_LEAVES.get(), HotBlocks.YUZU_LEAVES.get());
    }
}
