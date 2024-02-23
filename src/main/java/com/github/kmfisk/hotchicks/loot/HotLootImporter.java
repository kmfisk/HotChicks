package com.github.kmfisk.hotchicks.loot;

import com.github.kmfisk.hotchicks.item.HotItems;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class HotLootImporter extends LootModifier {
    public HotLootImporter(final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        switch (context.getRandom().nextInt(5)) {
            case 0:
                generatedLoot.add(HotItems.OATS.get().getDefaultInstance());
                break;
            case 1:
                generatedLoot.add(HotItems.LETTUCE.get().getDefaultInstance());
                break;
            case 2:
                generatedLoot.add(HotItems.GARLIC.get().getDefaultInstance());
                break;
            case 3:
                generatedLoot.add(HotItems.OKRA.get().getDefaultInstance());
                break;
            case 4:
                generatedLoot.add(HotItems.COTTON.get().getDefaultInstance());
                break;
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<HotLootImporter> {
        @Override
        public HotLootImporter read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            return new HotLootImporter(ailootcondition);
        }

        @Override
        public JsonObject write(HotLootImporter instance) {
            return this.makeConditions(instance.conditions);
        }
    }
}
