package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class HotVillagerTrades {
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            event.getTrades().get(1).add(new VillagerTrades.ItemsForEmeraldsTrade(HotItems.LETTUCE.get(), 1, 4, 16, 1));
            event.getTrades().get(1).add(new VillagerTrades.ItemsForEmeraldsTrade(HotItems.GARLIC.get(), 1, 4, 16, 1));
            event.getTrades().get(1).add(new VillagerTrades.ItemsForEmeraldsTrade(HotItems.OKRA.get(), 1, 4, 16, 1));
            event.getTrades().get(1).add(new VillagerTrades.ItemsForEmeraldsTrade(HotItems.COTTON.get(), 1, 4, 16, 1));
        }
    }
}
