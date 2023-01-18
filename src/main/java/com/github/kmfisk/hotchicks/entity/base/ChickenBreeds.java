package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;
import java.util.Set;

public enum ChickenBreeds {
    JUNGLEFOWL(50, 0, 0, 0),
    LEGHORN(85, 1, 2, 7),
    RHODE_ISLAND_RED(85, 3, 2, 4),
    BARRED_ROCK(85, 3, 3, 5),
    ORPINGTON(85, 3, 3, 3),
    AMERAUCANA(85, 2, 1, 4),
    OLIVE_EGGER(85, 2, 1, 4),
    MARANS(85, 3, 1, 4),
    SILKIE(85, 3, 1, 3);

    public final ChickenStats stats;

    ChickenBreeds(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        this.stats = new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
    }

    public static int randomFromBreed(Random random, ChickenBreeds breeds) {
        switch (breeds) {
            default: case JUNGLEFOWL:
                return 0;
            case AMERAUCANA:
                return random.nextInt(7) + 1;
            case BARRED_ROCK:
                return 8;
            case LEGHORN:
                return 9;
            case MARANS:
                return random.nextInt(4) + 10;
            case OLIVE_EGGER:
                return random.nextInt(7) + 14;
            case ORPINGTON:
                return random.nextInt(4) + 21;
            case RHODE_ISLAND_RED:
                return random.nextInt(3) + 25;
            case SILKIE:
                return random.nextInt(5) + 28;
        }
    }

    public static int randomBasedOnBiome(Random random, Biome biome) {
        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()));
        if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD))
            return 9; // LEGHORN;
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.COLD) && !biomeTypes.contains(BiomeDictionary.Type.DENSE) && !biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN))
            return random.nextInt(3) + 25; // RHODE_ISLAND_RED;
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && biomeTypes.contains(BiomeDictionary.Type.SPOOKY))
            return 8; // BARRED_ROCK;
        if (biomeTypes.contains(BiomeDictionary.Type.HOT) && biomeTypes.contains(BiomeDictionary.Type.SANDY))
            return random.nextInt(4) + 21; // ORPINGTON;
        if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.DRY))
            return random.nextInt(7) + 1; // AMERAUCANA;
        if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE))
            return random.nextInt(7) + 14; // OLIVE_EGGER;
        if (biomeTypes.contains(BiomeDictionary.Type.SWAMP))
            return random.nextInt(4) + 10; // MARANS;
        if (biomeTypes.contains(BiomeDictionary.Type.SNOWY))
            return random.nextInt(5) + 28; // SILKIE;
        return random.nextInt(32) + 1;
    }
}