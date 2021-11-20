package com.ryanhcode.hotchicks.event;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.entity.HotEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Tuple;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

import static com.ryanhcode.hotchicks.entity.HotEntities.SPAWNS;
import static com.ryanhcode.hotchicks.worldgen.HotFeatures.*;

@Mod.EventBusSubscriber(modid = HotChickens.MOD_ID)
public class HotEvents {
    @SubscribeEvent
    public static void biomeLoad(final BiomeLoadingEvent event) {
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
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> STRAWBERRY_BUSH);

                if (biomeTypes.contains(BiomeDictionary.Type.SPOOKY))
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(HotEntities.CHICKEN.get(), 10, 2, 4));

                if (!biomeTypes.contains(BiomeDictionary.Type.COLD) && !biomeTypes.contains(BiomeDictionary.Type.DENSE) && !biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN)) {
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_GRAPE);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_KIWI);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_PEA);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> RED_APPLE);
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH);
                }
            }

            if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.DRY)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANDARIN);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.LAKES).add(() -> MILLET);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> OAT);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> GARLIC);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEACH);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CITRON);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> MANGO);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMEGRANATE);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> FIG);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> POMELO);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PAPEDA);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> PEPPER_BUSH);

                event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(HotEntities.CHICKEN.get(), 10, 2, 4));
            }

            if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CORN);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> OAT);
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WILD_TOMATO);
            }

            if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY))
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> BLUEBERRY_BUSH);
        }
    }
}
