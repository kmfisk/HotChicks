package com.github.kmfisk.hotchicks.entity.merchant.villager;

import com.github.kmfisk.hotchicks.item.HotItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class HotVillagerTrades {
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.ItemsForEmeralds(HotItems.CABBAGE.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeralds(HotItems.CUCUMBER.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeralds(HotItems.COTTON.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeralds(HotItems.GARLIC.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeralds(HotItems.LETTUCE.get(), 1, 4, 16, 1),
                    new VillagerTrades.ItemsForEmeralds(HotItems.OKRA.get(), 1, 4, 16, 1)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItems(Items.APPLE, 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.CITRON.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.FIG.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.GRAPEFRUIT.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.LEMON.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.LIME.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.MANDARIN.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.MANGO.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.ORANGE.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.PAPEDA.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.PEACH.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.POMEGRANATE.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.POMELO.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.YUZU.get(), 16, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItems(HotItems.CUCUMBER.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.GRAPES.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.KIWI.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.PEAS.get(), 16, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.TOMATO.get(), 16, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItems(HotItems.BANANA.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.BLUEBERRIES.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.COTTON.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.OKRA.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.PEPPERS.get(), 20, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.STRAWBERRY.get(), 20, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItems(HotItems.CABBAGE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.GARLIC.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.KALE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.LETTUCE.get(), 22, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.OATS.get(), 22, 16, 2)
            ));

            event.getTrades().get(1).addAll(ImmutableList.of(
                    new VillagerTrades.EmeraldForItems(HotItems.CORN.get(), 28, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.MILLET.get(), 28, 16, 2),
                    new VillagerTrades.EmeraldForItems(HotItems.RICE.get(), 28, 16, 2)
            ));
        }
    }
}
