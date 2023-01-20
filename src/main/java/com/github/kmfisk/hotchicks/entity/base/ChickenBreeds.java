package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;
import java.util.Set;

public enum ChickenBreeds {
    JUNGLEFOWL(50, 0, 0, 0, 1),
    AMERAUCANA(85, 2, 1, 4, 7),
    BARRED_ROCK(85, 3, 3, 5, 1),
    LEGHORN(85, 1, 2, 7, 1),
    MARANS(85, 3, 1, 4, 4),
    OLIVE_EGGER(85, 2, 1, 4, 7),
    ORPINGTON(85, 3, 3, 3, 4),
    RHODE_ISLAND_RED(85, 3, 2, 4, 3),
    SILKIE(85, 3, 1, 3, 5);

    public static final int MAX_VARIANTS = 32;
    public final ChickenStats stats;
    public final int variants;

    ChickenBreeds(int tameness, int carcassQuality, int growthRate, int eggSpeed, int variants) {
        this.stats = new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
        this.variants = variants;
    }

    public static int randomFromBreed(Random random, ChickenBreeds breeds) {
        switch (breeds) {
            default:
            case JUNGLEFOWL:
                return 0;
            case AMERAUCANA:
                return random.nextInt(AMERAUCANA.variants) + 1;
            case BARRED_ROCK:
                return 8;
            case LEGHORN:
                return 9;
            case MARANS:
                return random.nextInt(MARANS.variants) + 10;
            case OLIVE_EGGER:
                return random.nextInt(OLIVE_EGGER.variants) + 14;
            case ORPINGTON:
                return random.nextInt(ORPINGTON.variants) + 21;
            case RHODE_ISLAND_RED:
                return random.nextInt(RHODE_ISLAND_RED.variants) + 25;
            case SILKIE:
                return random.nextInt(SILKIE.variants) + 28;
        }
    }

    public static int randomBasedOnBiome(Random random, Biome biome) {
        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()));
        if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD))
            return randomFromBreed(random, LEGHORN);
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.COLD) && !biomeTypes.contains(BiomeDictionary.Type.DENSE) && !biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN))
            return randomFromBreed(random, RHODE_ISLAND_RED);
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && biomeTypes.contains(BiomeDictionary.Type.SPOOKY))
            return randomFromBreed(random, BARRED_ROCK);
        if (biomeTypes.contains(BiomeDictionary.Type.HOT) && biomeTypes.contains(BiomeDictionary.Type.SANDY))
            return randomFromBreed(random, ORPINGTON);
        if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.DRY))
            return randomFromBreed(random, AMERAUCANA);
        if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE))
            return randomFromBreed(random, OLIVE_EGGER);
        if (biomeTypes.contains(BiomeDictionary.Type.SWAMP))
            return randomFromBreed(random, MARANS);
        if (biomeTypes.contains(BiomeDictionary.Type.SNOWY))
            return randomFromBreed(random, SILKIE);
        return random.nextInt(MAX_VARIANTS) + 1;
    }
}
