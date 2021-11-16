package com.ryanhcode.hotchicks.entity.base;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum ChickenBreed {
    JUNGLEFOWL(new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }}, BaseChickenStats.JUNGLEFOWL),
    LEGHORN(new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("leghorns/leghorn"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }}, BaseChickenStats.LEGHORN),
    RHODE_ISLAND_RED(new HashMap<String, ChickenVariant>() {{
                put("deep_red", new ChickenVariant("rhode_islands/rhodeisland_deepred"));
                put("light_red", new ChickenVariant("rhode_islands/rhodeisland_lightred"));
                put("red", new ChickenVariant("rhode_islands/rhodeisland_red"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_2");
                add("chicks/chick_3");
                add("chicks/chick_4");
            }}, BaseChickenStats.RHODE_ISLAND_RED),
    BARRED_ROCK(new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("barred_rocks/barredrock"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
            }}, BaseChickenStats.BARRED_ROCK),
    ORPINGTON(new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("orpingtons/orpington_black"));
                put("blue", new ChickenVariant("orpingtons/orpington_blue"));
                put("buff", new ChickenVariant("orpingtons/orpington_buff"));
                put("white", new ChickenVariant("orpingtons/orpington_white"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
                add("chicks/chick_3");
                add("chicks/chick_1");
            }}, BaseChickenStats.OPRINGTON),
    AMERAUCANA(new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("ameraucanas/ameraucana_black"));
                put("blue", new ChickenVariant("ameraucanas/ameraucana_blue"));
                put("bluewheaten", new ChickenVariant("ameraucanas/ameraucana_bluewheaten"));
                put("brown", new ChickenVariant("ameraucanas/ameraucana_brown"));
                put("buff", new ChickenVariant("ameraucanas/ameraucana_buff"));
                put("lavender", new ChickenVariant("ameraucanas/ameraucana_lavender"));
                put("lightbrown", new ChickenVariant("ameraucanas/ameraucana_lightbrown"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }}, BaseChickenStats.AMERAUCANA),
    OLIVE_EGGER(new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("ameraucanas/ameraucana_black"));
                put("blue", new ChickenVariant("ameraucanas/ameraucana_blue"));
                put("bluewheaten", new ChickenVariant("ameraucanas/ameraucana_bluewheaten"));
                put("brown", new ChickenVariant("ameraucanas/ameraucana_brown"));
                put("buff", new ChickenVariant("ameraucanas/ameraucana_buff"));
                put("lavender", new ChickenVariant("ameraucanas/ameraucana_lavender"));
                put("lightbrown", new ChickenVariant("ameraucanas/ameraucana_lightbrown"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }}, BaseChickenStats.OLIVE_EGGER),
    MARANS(new HashMap<String, ChickenVariant>() {{
                put("gold_cuckoo", new ChickenVariant("marans/marans_goldcuckoo"));
                put("black_copper", new ChickenVariant("marans/marans_blackcopper"));
                put("cuckoo", new ChickenVariant("marans/marans_cuckoo"));
                put("black_birchen", new ChickenVariant("marans/marans_blackbirchen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
                add("chicks/chick_6");
                add("chicks/chick_8");
                add("chicks/chick_4");
            }}, BaseChickenStats.MARANS),
    SILKIE(new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("silkies/silkie_black"));
                put("blue", new ChickenVariant("silkies/silkie_blue"));
                put("buff", new ChickenVariant("silkies/silkie_buff"));
                put("patridge", new ChickenVariant("silkies/silkie_patridge"));
                put("white", new ChickenVariant("silkies/silkie_white"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_5");
                add("chicks/chick_4");
                add("chicks/chick_2");
                add("chicks/chick_1");
            }},
            BaseChickenStats.SILKIE);

    public final Map<String, ChickenVariant> textureMap;
    public final ArrayList<String> childTextures;
    public final ChickenStats stats;

    ChickenBreed(Map<String, ChickenVariant> textureMap, ArrayList<String> childTextures, ChickenStats stats) {
        this.textureMap = textureMap;
        this.childTextures = childTextures;
        this.stats = stats;
    }

    public static ChickenBreed breedFromBiome(Biome biome) {
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

    public static ChickenBreed randomBasedOnBiome(Biome biome) {
        Random rand = new Random(System.currentTimeMillis()
        );
        if (rand.nextFloat() < 0.8) {
            System.out.println("from biome");
            ChickenBreed chickenBreed = breedFromBiome(biome);
            System.out.println("chickenBreed from biome = " + chickenBreed);
            return chickenBreed;
        } else {
            return random(JUNGLEFOWL);
        }
    }

    public static ChickenBreed random(ChickenBreed... not) {
        Random random = new Random();
        ChickenBreed value = values()[random.nextInt(values().length)];
        for (ChickenBreed c : not) {
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

    public static class ChickenVariant {
        private final String male, female;

        public ChickenVariant(String path) {
            this.male = path + "_rooster";
            this.female = path + "_hen";
        }

        public String getTexture(Sex sex) {
            return sex == Sex.MALE ? male : female;
        }
    }
}
