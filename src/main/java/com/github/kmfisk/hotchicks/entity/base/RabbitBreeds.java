package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;
import java.util.Set;

public enum RabbitBreeds {
    COTTONTAIL(50, 0, 0, 0, 0),
    AMERICAN_CHINCHILLA(95, 2, 3, 2, 1),
    CALIFORNIA(95, 2, 1, 2, 2),
    DUTCH(95, 1, 1, 1, 4),
    FLEMISH_GIANT(95, 4, 1, 0, 1),
    NEW_ZEALAND(95, 3, 1, 3, 3),
    REX(95, 1, 4, 1, 2);

    public final RabbitStats stats;

    RabbitBreeds(int tameness, int carcassQuality, int hideQuality, int growthRate, int litterSize) {
        this.stats = new RabbitStats(tameness, carcassQuality, hideQuality, growthRate, litterSize);
    }

    public static int randomFromBreed(Random random, RabbitBreeds breeds) {
        switch (breeds) {
            default: case COTTONTAIL:
                return 0;
            case AMERICAN_CHINCHILLA:
                return random.nextInt(2) + 1;
            case CALIFORNIA:
                return 3;
            case DUTCH:
                return random.nextInt(6) + 4;
            case FLEMISH_GIANT:
                return random.nextInt(5) + 10;
            case NEW_ZEALAND:
                return random.nextInt(3) + 15;
            case REX:
                return random.nextInt(9) + 18;
        }
    }

    public static int randomBasedOnBiome(Random random, Biome biome) { // todo
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
        return random.nextInt(26) + 1;
    }
}
