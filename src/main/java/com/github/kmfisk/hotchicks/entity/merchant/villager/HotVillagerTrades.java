package com.github.kmfisk.hotchicks.entity.merchant.villager;

import com.github.kmfisk.hotchicks.item.HotItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class HotVillagerTrades {
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.CABBAGE.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.CUCUMBER.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.COTTON.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.GARLIC.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.LETTUCE.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeraldsTrade(HotItems.OKRA.get(), 1, 4, 16, 1)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItemsTrade(Items.APPLE, 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.CITRON.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.FIG.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.GRAPEFRUIT.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.LEMON.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.LIME.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.MANDARIN.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.MANGO.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.ORANGE.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.PAPEDA.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.PEACH.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.POMEGRANATE.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.POMELO.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.YUZU.get(), 16, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.CUCUMBER.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.GRAPES.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.KIWI.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.PEAS.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.TOMATO.get(), 16, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.BANANA.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.BLUEBERRIES.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.COTTON.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.OKRA.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.PEPPERS.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.STRAWBERRY.get(), 20, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.CABBAGE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.GARLIC.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.KALE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.LETTUCE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.OATS.get(), 22, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.CORN.get(), 28, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.MILLET.get(), 28, 16, 2),
                    new VillagerTrades.EmeraldForItemsTrade(HotItems.RICE.get(), 28, 16, 2)
            ));
        }
    }
}
