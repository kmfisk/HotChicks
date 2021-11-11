package com.ryanhcode.hotchicks.registry;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class HotLootImporter extends LootModifier {
    public HotLootImporter(final ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.addAll(ImmutableList.of(
                HotItems.OATS.get().getDefaultInstance(),
                HotItems.LETTUCE.get().getDefaultInstance(),
                HotItems.GARLIC.get().getDefaultInstance(),
                HotItems.OKRA.get().getDefaultInstance(),
                HotItems.COTTON.get().getDefaultInstance()));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<HotLootImporter> {
        @Override
        public HotLootImporter read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new HotLootImporter(ailootcondition);
        }

        @Override
        public JsonObject write(HotLootImporter instance) {
            return this.makeConditions(instance.conditions);
        }
    }
}
