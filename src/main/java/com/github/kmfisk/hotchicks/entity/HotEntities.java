package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HotEntities {
    public static final List<Tuple<RegistryObject<EntityType<?>>, List<SpawnInfo>>> SPAWNS = new ArrayList<>();
    public static final DeferredRegister<EntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.ENTITIES, HotChicks.MOD_ID);

    public static RegistryObject<EntityType<HotChickenEntity>> CHICKEN = new Builder<>(HotChickenEntity::new, EntityClassification.CREATURE)
            .size(0.4f, 0.7f)
            .spawn(new SpawnInfo((type) -> type.contains(BiomeDictionary.Type.JUNGLE) || (type.contains(BiomeDictionary.Type.SPOOKY) && type.contains(BiomeDictionary.Type.FOREST)),
                    2, 4, 10))
            .build(REGISTRAR, "chicken");

    @SuppressWarnings("unchecked")
    private static <T, F> T cast(F from) {
        return (T) from;
    }

    public static class Builder<T extends Entity> {
        private final EntityType.IFactory<T> factory;
        private final EntityClassification category;
        private float width, height;
        private final List<SpawnInfo> spawnInfos = new ArrayList<>();

        public Builder(EntityType.IFactory<T> factory, EntityClassification category) {
            this.factory = factory;
            this.category = category;
        }

        public Builder<T> size(float width, float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder<T> spawn(SpawnInfo spawnInfo) {
            spawnInfos.add(spawnInfo);
            return this;
        }

        public RegistryObject<EntityType<T>> build(DeferredRegister<EntityType<?>> registrar, String name) {
            final EntityType.Builder<T> entityBuilder = EntityType.Builder.of(factory, category).sized(width, height);
            final EntityType<T> builtType = entityBuilder.build(HotChicks.MOD_ID + "." + name);
            final RegistryObject<EntityType<T>> type = registrar.register(name, () -> builtType);

            if (!spawnInfos.isEmpty()) {
                SPAWNS.add(new Tuple<>(cast(type), spawnInfos));
            }

            return type;
        }
    }

    public static class SpawnInfo {
        public final SpawnPredicate predicate;
        public final int groupMinimum, groupMaximum, weight;

        public SpawnInfo(SpawnPredicate predicate, int groupMinimum, int groupMaximum, int weight) {
            this.predicate = predicate;
            this.groupMinimum = groupMinimum;
            this.groupMaximum = groupMaximum;
            this.weight = weight;
        }
    }

    @FunctionalInterface
    public interface SpawnPredicate {
        boolean invoke(Set<BiomeDictionary.Type> type);
    }
}
