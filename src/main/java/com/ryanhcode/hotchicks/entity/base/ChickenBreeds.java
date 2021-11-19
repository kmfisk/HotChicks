package com.ryanhcode.hotchicks.entity.base;

import com.ryanhcode.hotchicks.entity.stats.ChickenStats;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum ChickenBreeds {
    JUNGLEFOWL(50, 1, 2, 3),
    LEGHORN(80, 1, 2, 7),
    RHODE_ISLAND_RED(80, 3, 2, 4),
    BARRED_ROCK(80, 3, 3, 5),
    ORPINGTON(80, 3, 3, 3),
    AMERAUCANA(80, 2, 1, 4),
    OLIVE_EGGER(80, 2, 1, 4),
    MARANS(80, 3, 1, 4),
    SILKIE(80, 3, 1, 3);

    public final ChickenStats stats;

    ChickenBreeds(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        this.stats = new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
    }

    public static ChickenBreeds breedFromBiome(Biome biome) {
        String path = biome.getRegistryName().getPath();
        System.out.println("bpath: " + path);
        if (path.equals(Biomes.PLAINS.getRegistryName().getPath())) {
            return LEGHORN;
        }
        if (path.contains("forest") && !path.contains("dark") && !path.contains("roofed")) {
            return RHODE_ISLAND_RED;
        }

        if (path.equals(Biomes.DARK_FOREST.getRegistryName().getPath()) || path.equals(Biomes.DARK_FOREST_HILLS.getRegistryName().getPath())) {
            return BARRED_ROCK;
        }
        if (path.equals(Biomes.DESERT.getRegistryName().getPath())) {
            return ORPINGTON;
        }
        if (path.equals(Biomes.TAIGA_MOUNTAINS.getRegistryName().getPath()) || path.equals(Biomes.MOUNTAINS.getRegistryName().getPath()) || path.equals(Biomes.MOUNTAIN_EDGE.getRegistryName().getPath())) {
            return AMERAUCANA;
        }
        if (path.contains("jungle")) {
            return OLIVE_EGGER;
        }
        if (path.equals(Biomes.SWAMP.getRegistryName().getPath())) {
            return MARANS;
        }
        if (path.contains("snow")) {
            return SILKIE;
        }

        System.out.println("made it here@");
        return random(JUNGLEFOWL);
    }

    public static ChickenBreeds randomBasedOnBiome(Biome biome) {
        Random rand = new Random(System.currentTimeMillis()
        );
        if (rand.nextFloat() < 0.8) {
            System.out.println("from biome");
            ChickenBreeds chickenBreed = breedFromBiome(biome);
            System.out.println("chickenBreed from biome = " + chickenBreed);
            return chickenBreed;
        } else {
            return random(JUNGLEFOWL);
        }
    }

    public static ChickenBreeds random(ChickenBreeds... not) {
        Random random = new Random();
        ChickenBreeds value = values()[random.nextInt(values().length)];
        for (ChickenBreeds c : not) {
            if (value == c) {
                return random(not);
            }
        }
        return value;
    }
}
