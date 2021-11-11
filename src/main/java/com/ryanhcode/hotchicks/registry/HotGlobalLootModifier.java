package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotGlobalLootModifier {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, HotChickens.MOD_ID);
    public static final RegistryObject<HotLootImporter.Serializer> HOT_LOOT_IMPORTER = REGISTRAR.register("zombie_crops", HotLootImporter.Serializer::new);
}
