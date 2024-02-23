package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotChickenRenderer;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotCowRenderer;
import com.github.kmfisk.hotchicks.client.renderer.entity.HotRabbitRenderer;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;

public class HotEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.ENTITIES, HotChicks.MOD_ID);
    private static final List<Tuple<Supplier<EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>>> ATTRIBUTES = new ArrayList<>();
    private static final List<Tuple<Supplier<EntityType<?>>, Supplier<IRenderFactory<?>>>> RENDERERS = new ArrayList<>();
//    public static final List<Tuple<RegistryObject<EntityType<?>>, List<SpawnInfo>>> SPAWNS = new ArrayList<>();

    public static RegistryObject<EntityType<HotChickenEntity>> CHICKEN = new Builder<>(HotChickenEntity::new, MobCategory.CREATURE)
            .attributes(HotChickenEntity::registerAttributes)
            .renderer(() -> HotChickenRenderer::new)
            /*.spawn(new SpawnInfo((type) -> type.contains(BiomeDictionary.Type.JUNGLE) || (type.contains(BiomeDictionary.Type.SPOOKY) && type.contains(BiomeDictionary.Type.FOREST)),
                    2, 4, 10))*/
            .data(hotChickenEntityBuilder -> hotChickenEntityBuilder.sized(0.4f, 0.7f).clientTrackingRange(10))
            .build(REGISTRAR, "chicken");

    public static RegistryObject<EntityType<HotRabbitEntity>> RABBIT = new Builder<>(HotRabbitEntity::new, MobCategory.CREATURE)
            .attributes(HotRabbitEntity::registerAttributes)
            .renderer(() -> HotRabbitRenderer::new)
            /*.spawn(new SpawnInfo((type) -> type.contains(BiomeDictionary.Type.PLAINS) || (type.contains(BiomeDictionary.Type.CONIFEROUS) && !type.contains(BiomeDictionary.Type.MOUNTAIN) && !type.contains(BiomeDictionary.Type.SNOWY)),
                    2, 4, 10))*/
            .data(hotRabbitEntityBuilder -> hotRabbitEntityBuilder.sized(0.6f, 0.5f).clientTrackingRange(10))
            .build(REGISTRAR, "rabbit");

    public static RegistryObject<EntityType<HotCowEntity>> COW = new Builder<>(HotCowEntity::new, MobCategory.CREATURE)
            .attributes(HotCowEntity::registerAttributes)
            .renderer(() -> HotCowRenderer::new)
            .data(hotCowEntityBuilder -> hotCowEntityBuilder.sized(1.6f, 2.2f).clientTrackingRange(10))
            .build(REGISTRAR, "cow");

    public static void registerSpawnPlacements() {
        SpawnPlacements.register(CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, LivestockEntity::checkLivestockSpawnRules);
        SpawnPlacements.register(RABBIT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LivestockEntity::checkLivestockSpawnRules);
        SpawnPlacements.register(COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LivestockEntity::checkLivestockSpawnRules);
    }

    public static void registerAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> register) {
        for (Tuple<Supplier<EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>> attribute : ATTRIBUTES) {
            register.accept(attribute.getA().get(), attribute.getB().get());
        }
        ATTRIBUTES.clear();
    }

    public static void registerRenderers() {
        for (Tuple<Supplier<EntityType<?>>, Supplier<IRenderFactory<?>>> renderer : RENDERERS) {
            RenderingRegistry.registerEntityRenderingHandler(renderer.getA().get(), cast(renderer.getB().get()));
        }
        RENDERERS.clear();
    }

    @SuppressWarnings("unchecked")
    private static <T, F> T cast(F from) {
        return (T) from;
    }

    public static class Builder<T extends Entity> {
        private final EntityType.EntityFactory<T> factory;
        private final MobCategory category;
        private Supplier<AttributeSupplier.Builder> attributes;
        private Supplier<IRenderFactory<? super T>> renderer;
        private Consumer<EntityType.Builder<T>> builderConsumer;
//        private final List<SpawnInfo> spawnInfos = new ArrayList<>();

        public Builder(EntityType.EntityFactory<T> factory, MobCategory category) {
            this.factory = factory;
            this.category = category;
        }

        public Builder<T> attributes(Supplier<AttributeSupplier.Builder> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder<T> renderer(Supplier<IRenderFactory<? super T>> renderer) {
            this.renderer = renderer;
            return this;
        }

        /*public Builder<T> spawn(SpawnInfo spawnInfo) {
            spawnInfos.add(spawnInfo);
            return this;
        }*/

        public Builder<T> data(Consumer<EntityType.Builder<T>> consumer) {
            builderConsumer = consumer;
            return this;
        }

        public RegistryObject<EntityType<T>> build(DeferredRegister<EntityType<?>> registrar, String name) {
            final RegistryObject<EntityType<T>> type = registrar.register(name, () -> {
                final EntityType.Builder<T> entityBuilder = EntityType.Builder.of(factory, category);
                if (builderConsumer != null) builderConsumer.accept(entityBuilder);
                return entityBuilder.build(HotChicks.MOD_ID + "." + name);
            });

            if (attributes != null) {
                ATTRIBUTES.add(new Tuple<>(cast(type), attributes));
            }

            if (EffectiveSide.get().isClient() && renderer != null) {
                RENDERERS.add(new Tuple<>(cast(type), cast(renderer)));
            }

            /*if (!spawnInfos.isEmpty()) {
                SPAWNS.add(new Tuple<>(cast(type), spawnInfos));
            }*/

            return type;
        }
    }

    /*public static class SpawnInfo {
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
    }*/
}
