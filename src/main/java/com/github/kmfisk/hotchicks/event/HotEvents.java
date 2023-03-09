package com.github.kmfisk.hotchicks.event;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.HotEntities;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
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
        changeVillageCows(new ResourceLocation("village/common/animals"), 7);
        changeVillageCows(new ResourceLocation("village/common/butcher_animals"), 3);
    }

    @SubscribeEvent
    public static void joinWorldEvent(EntityJoinWorldEvent event) {
        if (!event.getWorld().isClientSide) {
            boolean chickens = event.getEntity().getClass() == ChickenEntity.class && HotChicksConfig.removeVanillaChickens.get();
            boolean cows = event.getEntity().getClass() == CowEntity.class && HotChicksConfig.removeVanillaCows.get();
            boolean rabbits = event.getEntity().getClass() == RabbitEntity.class && HotChicksConfig.removeVanillaRabbits.get();
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
        MobSpawnInfo.Spawners chickenSpawner = new MobSpawnInfo.Spawners(HotEntities.CHICKEN.get(), HotChicksConfig.chickenSpawnChance.get(), HotChicksConfig.chickenMinGroup.get(), HotChicksConfig.chickenMaxGroup.get());
        MobSpawnInfo.Spawners cowSpawner = new MobSpawnInfo.Spawners(HotEntities.COW.get(), HotChicksConfig.cowSpawnChance.get(), HotChicksConfig.cowMinGroup.get(), HotChicksConfig.cowMaxGroup.get());
        MobSpawnInfo.Spawners rabbitSpawner = new MobSpawnInfo.Spawners(HotEntities.RABBIT.get(), HotChicksConfig.rabbitSpawnChance.get(), HotChicksConfig.rabbitMinGroup.get(), HotChicksConfig.rabbitMaxGroup.get());

        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, event.getName()));

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
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(chickenSpawner);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RED_APPLE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05f, 1))));
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> STRAWBERRY_BUSH);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_GRAPE);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_PEA);
                }
                if (biomeTypes.contains(BiomeDictionary.Type.DENSE)) {
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_KIWI);
                }
            }

            if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.HOT)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.05f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA)) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(cowSpawner);
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(rabbitSpawner);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.02f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.02f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CITRON.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_OATS);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_GARLIC);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MILLET);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE)) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(chickenSpawner);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANGO.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMELO.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1f, 1))));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PAPEDA.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1f, 1))));
//                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BANANA_TREE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_KIWI);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.PLAINS)) {
                if (!biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD)) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(cowSpawner);
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(rabbitSpawner);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_OATS);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_TOMATO);
                }
            }

            if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY)) {
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(chickenSpawner);
                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(rabbitSpawner);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SANDY) && biomeTypes.contains(BiomeDictionary.Type.HOT)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MILLET);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SWAMP)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_KALE);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.RIVER)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_RICE);
            }
        }
    }

    private static boolean isVanillaVillageCowPiece(SingleJigsawPiece piece) {
        return piece.toString().contains("minecraft:village/common/animals/cows");
    }

    private static boolean keepJigsawPair(Pair<JigsawPiece, Integer> pair) {
        if (!HotChicksConfig.removeVanillaCows.get()) return true;
        JigsawPiece piece = pair.getFirst();
        if (piece instanceof SingleJigsawPiece) return !isVanillaVillageCowPiece((SingleJigsawPiece) piece);
        return true;
    }

    public static void changeVillageCows(ResourceLocation poolLocation, int i) {
        PlainsVillagePools.bootstrap();
        java.util.Optional<JigsawPattern> poolOptional = WorldGenRegistries.TEMPLATE_POOL.getOptional(poolLocation);
        if (!poolOptional.isPresent()) {
            System.err.println("Trying to overwrite village spawns too soon");
            return;
        }
        JigsawPattern pool = poolOptional.get();
        List<Pair<JigsawPiece, Integer>> vanillaList = ObfuscationReflectionHelper.getPrivateValue(JigsawPattern.class, pool, "field_214952_d");
        List<Pair<JigsawPiece, Integer>> keeperList = new ArrayList<>();
        for (Pair<JigsawPiece, Integer> p : vanillaList)
            if (keepJigsawPair(p)) keeperList.add(p);

        List<Pair<Function<JigsawPattern.PlacementBehaviour, ? extends JigsawPiece>, Integer>> customPieces = new ArrayList<>();
        customPieces.add(new Pair<>(JigsawPiece.legacy(HotChicks.MOD_ID + ":village/common/animals/cows_1"), i));

        JigsawPattern.PlacementBehaviour placementBehaviour = JigsawPattern.PlacementBehaviour.RIGID;
        for (Pair<Function<JigsawPattern.PlacementBehaviour, ? extends JigsawPiece>, Integer> pair : customPieces) {
            JigsawPiece jigsawPiece = pair.getFirst().apply(placementBehaviour);
            keeperList.add(new Pair<>(jigsawPiece, pair.getSecond()));
        }

        JigsawPatternRegistry.register(new JigsawPattern(poolLocation, new ResourceLocation("empty"), keeperList));
    }
}
