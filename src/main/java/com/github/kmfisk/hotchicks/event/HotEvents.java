package com.github.kmfisk.hotchicks.event;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.HotEntities;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.SinglePoolElement;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.github.kmfisk.hotchicks.worldgen.HotFeatures.*;

@Mod.EventBusSubscriber(modid = HotChicks.MOD_ID)
public class HotEvents {
    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        if (HotChicksConfig.addCowsToVillages.get()) {
            changeVillageCows(new ResourceLocation("village/common/animals"), 7);
            changeVillageCows(new ResourceLocation("village/common/butcher_animals"), 3);
        }
    }

    @SubscribeEvent
    public static void joinWorldEvent(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide) {
            boolean chickens = event.getEntity().getClass() == Chicken.class && HotChicksConfig.removeVanillaChickens.get();
            boolean cows = event.getEntity().getClass() == Cow.class && HotChicksConfig.removeVanillaCows.get();
            boolean rabbits = event.getEntity().getClass() == Rabbit.class && HotChicksConfig.removeVanillaRabbits.get();
            if (chickens || cows || rabbits) {
                LivingEntity entity = (LivingEntity) event.getEntity();
                if (!entity.getPersistentData().contains("HotChicksSpawn")) {
                    entity.getPersistentData().putBoolean("HotChicksSpawn", true);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void biomeLoad(final BiomeLoadingEvent event) {
        MobSpawnSettings.SpawnerData chickenSpawner = new MobSpawnSettings.SpawnerData(HotEntities.CHICKEN.get(), HotChicksConfig.chickenSpawnChance.get(), HotChicksConfig.chickenMinGroup.get(), HotChicksConfig.chickenMaxGroup.get());
        MobSpawnSettings.SpawnerData cowSpawner = new MobSpawnSettings.SpawnerData(HotEntities.COW.get(), HotChicksConfig.cowSpawnChance.get(), HotChicksConfig.cowMinGroup.get(), HotChicksConfig.cowMaxGroup.get());
        MobSpawnSettings.SpawnerData rabbitSpawner = new MobSpawnSettings.SpawnerData(HotEntities.RABBIT.get(), HotChicksConfig.rabbitSpawnChance.get(), HotChicksConfig.rabbitMinGroup.get(), HotChicksConfig.rabbitMaxGroup.get());

        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(ResourceKey.create(Registry.BIOME_REGISTRY, event.getName()));

        if (biomeTypes.contains(BiomeDictionary.Type.OVERWORLD)) {
            /*for (Tuple<RegistryObject<EntityType<?>>, List<HotEntities.SpawnInfo>> spawn : SPAWNS) {
                EntityType<?> type = spawn.getA().get();
                for (HotEntities.SpawnInfo spawnInfo : spawn.getB()) {
                    if (spawnInfo.predicate.invoke(biomeTypes)) {
                        event.getSpawns().getSpawner(type.getCategory()).add(new MobSpawnInfo.Spawners(type, spawnInfo.weight, spawnInfo.groupMinimum, spawnInfo.groupMaximum));
                    }
                }
            }
            SPAWNS.clear();*/

            if (biomeTypes.contains(BiomeDictionary.Type.FOREST)) {
                if (!biomeTypes.contains(BiomeDictionary.Type.SAVANNA) && !biomeTypes.contains(BiomeDictionary.Type.JUNGLE) && !biomeTypes.contains(BiomeDictionary.Type.WET) && !biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS)) {
                    event.getSpawns().getSpawner(MobCategory.CREATURE).add(chickenSpawner);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> RED_APPLE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1f, 1))));
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PEACH.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05f, 1))));
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> STRAWBERRY_BUSH);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_GRAPE);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_PEA);
                }
                if (biomeTypes.contains(BiomeDictionary.Type.DENSE)) {
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_KIWI);
                }
            }

            if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.HOT)) {
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.05f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA)) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(cowSpawner);
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(rabbitSpawner);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PEACH.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.02f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.02f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> FIG.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> CITRON.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_OATS);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_GARLIC);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> MILLET);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE)) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(chickenSpawner);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> MANGO.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> FIG.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> POMELO.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PAPEDA.decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> BANANA_TREE);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_KIWI);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.PLAINS)) {
                if (!biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD)) {
                    event.getSpawns().getSpawner(MobCategory.CREATURE).add(cowSpawner);
                    event.getSpawns().getSpawner(MobCategory.CREATURE).add(rabbitSpawner);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> CORN);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_OATS);
                    event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_TOMATO);
                }
            }

            if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY)) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(chickenSpawner);
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(rabbitSpawner);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SANDY) && biomeTypes.contains(BiomeDictionary.Type.HOT)) {
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> MILLET);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SWAMP)) {
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_KALE);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.RIVER)) {
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
            }
        }
    }

    private static boolean isVanillaVillageCowPiece(SinglePoolElement piece) {
        return piece.toString().contains("minecraft:village/common/animals/cows");
    }

    private static boolean keepJigsawPair(Pair<StructurePoolElement, Integer> pair) {
        if (!HotChicksConfig.removeVanillaCows.get()) return true;
        StructurePoolElement piece = pair.getFirst();
        if (piece instanceof SinglePoolElement) return !isVanillaVillageCowPiece((SinglePoolElement) piece);
        return true;
    }

    public static void changeVillageCows(ResourceLocation poolLocation, int i) {
        PlainVillagePools.bootstrap();
        java.util.Optional<StructureTemplatePool> poolOptional = BuiltinRegistries.TEMPLATE_POOL.getOptional(poolLocation);
        if (!poolOptional.isPresent()) {
            System.err.println("Trying to overwrite village spawns too soon");
            return;
        }
        StructureTemplatePool pool = poolOptional.get();
        List<Pair<StructurePoolElement, Integer>> vanillaList = ObfuscationReflectionHelper.getPrivateValue(StructureTemplatePool.class, pool, "rawTemplates");
        List<Pair<StructurePoolElement, Integer>> keeperList = new ArrayList<>();
        for (Pair<StructurePoolElement, Integer> p : vanillaList)
            if (keepJigsawPair(p)) keeperList.add(p);

        List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> customPieces = new ArrayList<>();
        customPieces.add(new Pair<>(StructurePoolElement.legacy(HotChicks.MOD_ID + ":village/common/animals/cows_1"), i));

        StructureTemplatePool.Projection placementBehaviour = StructureTemplatePool.Projection.RIGID;
        for (Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer> pair : customPieces) {
            StructurePoolElement jigsawPiece = pair.getFirst().apply(placementBehaviour);
            keeperList.add(new Pair<>(jigsawPiece, pair.getSecond()));
        }

        Pools.register(new StructureTemplatePool(poolLocation, new ResourceLocation("empty"), keeperList));
    }
}
