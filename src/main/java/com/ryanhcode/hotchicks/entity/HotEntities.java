package com.ryanhcode.hotchicks.entity;

import com.ryanhcode.hotchicks.HotChickens;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.ENTITIES, HotChickens.MOD_ID);

    public static RegistryObject<EntityType<HotChickenEntity>> HOT_CHICKEN = buildEntity(HotChickenEntity::new, HotChickenEntity.class, 0.4f, 0.7f, "chicken");


    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height, String name) {
        return REGISTRAR.register(name, () -> {
            return EntityType.Builder.of(entity, EntityClassification.CREATURE).sized(width, height).build(name);
        });
    }
}
