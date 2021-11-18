package com.ryanhcode.hotchicks.entity.base;

import com.ryanhcode.hotchicks.entity.stats.ChickenStats;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum ChickenBreeds {
    JUNGLEFOWL(new HashMap<String, Variant>() {{
                put("default", new Variant("junglefowl/junglefowl"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }},
            50, 1, 2, 3),
    LEGHORN(new HashMap<String, Variant>() {{
                put("default", new Variant("leghorns/leghorn"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }},
            80, 1, 2, 7),
    RHODE_ISLAND_RED(new HashMap<String, Variant>() {{
                put("deep_red", new Variant("rhode_islands/rhodeisland_deepred"));
                put("light_red", new Variant("rhode_islands/rhodeisland_lightred"));
                put("red", new Variant("rhode_islands/rhodeisland_red"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_2");
                add("chicks/chick_3");
                add("chicks/chick_4");
            }},
            80, 3, 2, 4),
    BARRED_ROCK(new HashMap<String, Variant>() {{
                put("default", new Variant("barred_rocks/barredrock"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
            }},
            80, 3, 3, 5),
    ORPINGTON(new HashMap<String, Variant>() {{
                put("black", new Variant("orpingtons/orpington_black"));
                put("blue", new Variant("orpingtons/orpington_blue"));
                put("buff", new Variant("orpingtons/orpington_buff"));
                put("white", new Variant("orpingtons/orpington_white"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
                add("chicks/chick_3");
                add("chicks/chick_1");
            }},
            80, 3, 3, 3),
    AMERAUCANA(new HashMap<String, Variant>() {{
                put("black", new Variant("ameraucanas/ameraucana_black"));
                put("blue", new Variant("ameraucanas/ameraucana_blue"));
                put("bluewheaten", new Variant("ameraucanas/ameraucana_bluewheaten"));
                put("brown", new Variant("ameraucanas/ameraucana_brown"));
                put("buff", new Variant("ameraucanas/ameraucana_buff"));
                put("lavender", new Variant("ameraucanas/ameraucana_lavender"));
                put("lightbrown", new Variant("ameraucanas/ameraucana_lightbrown"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }},
            80, 2, 1, 4),
    OLIVE_EGGER(new HashMap<String, Variant>() {{
                put("black", new Variant("ameraucanas/ameraucana_black"));
                put("blue", new Variant("ameraucanas/ameraucana_blue"));
                put("bluewheaten", new Variant("ameraucanas/ameraucana_bluewheaten"));
                put("brown", new Variant("ameraucanas/ameraucana_brown"));
                put("buff", new Variant("ameraucanas/ameraucana_buff"));
                put("lavender", new Variant("ameraucanas/ameraucana_lavender"));
                put("lightbrown", new Variant("ameraucanas/ameraucana_lightbrown"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }},
            80, 2, 1, 4),
    MARANS(new HashMap<String, Variant>() {{
                put("gold_cuckoo", new Variant("marans/marans_goldcuckoo"));
                put("black_copper", new Variant("marans/marans_blackcopper"));
                put("cuckoo", new Variant("marans/marans_cuckoo"));
                put("black_birchen", new Variant("marans/marans_blackbirchen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
                add("chicks/chick_6");
                add("chicks/chick_8");
                add("chicks/chick_4");
            }},
            80, 3, 1, 4),
    SILKIE(new HashMap<String, Variant>() {{
                put("black", new Variant("silkies/silkie_black"));
                put("blue", new Variant("silkies/silkie_blue"));
                put("buff", new Variant("silkies/silkie_buff"));
                put("patridge", new Variant("silkies/silkie_patridge"));
                put("white", new Variant("silkies/silkie_white"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_5");
                add("chicks/chick_4");
                add("chicks/chick_2");
                add("chicks/chick_1");
            }},
            80, 3, 1, 3);

    public final Map<String, Variant> textureMap;
    public final ArrayList<String> childTextures;
    public final ChickenStats stats;

    ChickenBreeds(Map<String, Variant> textureMap, ArrayList<String> childTextures, int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        this.textureMap = textureMap;
        this.childTextures = childTextures;
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

    public String randomChick() {
        return childTextures.get(randomChickIndex());
    }

    public int randomChickIndex() {
        return new Random(System.currentTimeMillis()).nextInt(childTextures.size());
    }

    public String randomVariant() {
        Object[] textures = textureMap.keySet().toArray();
        return (String) textures[new Random().nextInt(textures.length)];
    }

    public static class Variant {
        private final String male, female;

        public Variant(String path) {
            this.male = path + "_rooster";
            this.female = path + "_hen";
        }

        public String getTexture(Sex sex) {
            return sex == Sex.MALE ? male : female;
        }
    }
}
