package com.ryanhcode.hotchicks.entity;

import com.ryanhcode.hotchicks.HotChickens;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Tuple;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = HotChickens.MOD_ID)
public class HotEntities {
    private static final List<Tuple<RegistryObject<EntityType<?>>, List<SpawnInfo>>> SPAWNS = new ArrayList<>();
    public static final DeferredRegister<EntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.ENTITIES, HotChickens.MOD_ID);

    public static RegistryObject<EntityType<HotChickenEntity>> CHICKEN = new Builder<>(HotChickenEntity::new, EntityClassification.CREATURE)
            .size(0.4f, 0.7f)
            .spawn(new SpawnInfo((type) ->
                    type.contains(BiomeDictionary.Type.JUNGLE) || (type.contains(BiomeDictionary.Type.SPOOKY) && type.contains(BiomeDictionary.Type.FOREST)),
                    2, 4, 10))
            .build(REGISTRAR, "chicken");

    @SubscribeEvent
    public static void addBiomeSpawns(final BiomeLoadingEvent event) {
        for (Tuple<RegistryObject<EntityType<?>>, List<SpawnInfo>> spawn : SPAWNS) {
            EntityType<?> type = spawn.getA().get();
            for (SpawnInfo spawnInfo : spawn.getB()) {
                if (spawnInfo.predicate.invoke(BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, event.getName())))) {
                    event.getSpawns().getSpawner(type.getCategory()).add(new MobSpawnInfo.Spawners(type, spawnInfo.weight, spawnInfo.groupMinimum, spawnInfo.groupMaximum));
                }
            }
        }
        SPAWNS.clear();
    }

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
            final EntityType<T> builtType = entityBuilder.build(HotChickens.MOD_ID + "." + name);
            final RegistryObject<EntityType<T>> type = registrar.register(name, () -> builtType);

            if (!spawnInfos.isEmpty()) {
                SPAWNS.add(new Tuple<>(cast(type), spawnInfos));
            }

            return type;
        }
    }

    public static class SpawnInfo {
        private final SpawnPredicate predicate;
        private final int groupMinimum, groupMaximum, weight;

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
