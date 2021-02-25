package com.ryanhcode.hotchicks.entity.base;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public enum ChickenBreed {
    //JUNGLEFOWL("Junglefowl", "junglefowl", "junglefowl/junglefowl_rooster", BaseChickenStats.JUNGLEFOWL),

    JUNGLEFOWL("Junglefowl", "junglefowl",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }}, BaseChickenStats.JUNGLEFOWL),
    LEGHORN("Leghorn", "leghorn",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("leghorns/leghorn_rooster", "leghorns/leghorn_hen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_1");
            }}, BaseChickenStats.LEGHORN),
    RHODE_ISLAND_RED("Rhode Island Red", "rhode_island_red",
            new HashMap<String, ChickenVariant>() {{
                put("deep_red", new ChickenVariant("rhode_islands/rhodeisland_deepred", true));
                put("light_red", new ChickenVariant("rhode_islands/rhodeisland_lightred", true));
                put("red", new ChickenVariant("rhode_islands/rhodeisland_red", true));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_2");
                add("chicks/chick_3");
                add("chicks/chick_4");
            }}, BaseChickenStats.RHODE_ISLAND_RED),
    BARRED_ROCK("Barred Rock", "barred_rock",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("barred_rocks/barredrock_rooster", "barred_rocks/barredrock_hen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
            }}, BaseChickenStats.BARRED_ROCK),
    ORPINGTON("Orpington", "orpington",
            new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("orpingtons/orpington_black_rooster", "orpingtons/orpington_black_hen"));
                put("blue", new ChickenVariant("orpingtons/orpington_blue_rooster", "orpingtons/orpington_blue_hen"));
                put("buff", new ChickenVariant("orpingtons/orpington_buff_rooster", "orpingtons/orpington_buff_hen"));
                put("white", new ChickenVariant("orpingtons/orpington_white_rooster", "orpingtons/orpington_white_hen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
                add("chicks/chick_3");
                add("chicks/chick_1");
            }}, BaseChickenStats.OPRINGTON),
    AMERAUCANA("Amerucana", "ameraucana",
            new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("ameraucanas/ameraucana_black", true));
                put("blue", new ChickenVariant("ameraucanas/ameraucana_blue", true));
                put("bluewheaten", new ChickenVariant("ameraucanas/ameraucana_bluewheaten", true));
                put("brown", new ChickenVariant("ameraucanas/ameraucana_brown", true));
                put("buff", new ChickenVariant("ameraucanas/ameraucana_buff", true));
                put("lavender", new ChickenVariant("ameraucanas/ameraucana_lavender", true));
                put("lightbrown", new ChickenVariant("ameraucanas/ameraucana_lightbrown", true));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }}, BaseChickenStats.AMERAUCANA),
    OLIVE_EGGER("Olive Egger", "olive_egger",
            new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("ameraucanas/ameraucana_black", true));
                put("blue", new ChickenVariant("ameraucanas/ameraucana_blue", true));
                put("bluewheaten", new ChickenVariant("ameraucanas/ameraucana_bluewheaten", true));
                put("brown", new ChickenVariant("ameraucanas/ameraucana_brown", true));
                put("buff", new ChickenVariant("ameraucanas/ameraucana_buff", true));
                put("lavender", new ChickenVariant("ameraucanas/ameraucana_lavender", true));
                put("lightbrown", new ChickenVariant("ameraucanas/ameraucana_lightbrown", true));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_6");
            }}, BaseChickenStats.OLIVE_EGGER),
    MARANS("Marans", "marans",
            new HashMap<String, ChickenVariant>() {{
                put("gold_cuckoo", new ChickenVariant("marans/marans_goldcuckoo_rooster", "marans/marans_goldcuckoo_hen"));
                put("black_copper", new ChickenVariant("marans/marans_blackcopper_rooster", "marans/marans_blackcopper_hen"));
                put("cuckoo", new ChickenVariant("marans/marans_cuckoo_rooster", "marans/marans_cuckoo_hen"));
                put("black_birchen", new ChickenVariant("marans/marans_blackbirchen_rooster", "marans/marans_blackbirchen_hen"));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_8");
                add("chicks/chick_6");
                add("chicks/chick_8");
                add("chicks/chick_4");
            }}, BaseChickenStats.MARANS),
    SILKIE("Silkie", "silkie",
            new HashMap<String, ChickenVariant>() {{
                put("black", new ChickenVariant("silkies/silkie_black", true));
                put("blue", new ChickenVariant("silkies/silkie_blue", true));
                put("buff", new ChickenVariant("silkies/silkie_buff", true));
                put("patridge", new ChickenVariant("silkies/silkie_patridge", true));
                put("white", new ChickenVariant("silkies/silkie_white", true));
            }},
            new ArrayList<String>() {{
                add("chicks/chick_7");
                add("chicks/chick_5");
                add("chicks/chick_4");
                add("chicks/chick_2");
                add("chicks/chick_1");
            }},
            BaseChickenStats.SILKIE);

    public final String name;
    public final String id;
    public final Map<String, ChickenVariant> textureMap;
    public final ArrayList<String> childTextures;
    public final ChickenStats stats;

    ChickenBreed(String name, String id, Map<String, ChickenVariant> textureMap, ArrayList<String> childTextures, ChickenStats stats){
        this.name = name;
        this.id = id;
        this.textureMap = textureMap;
        this.childTextures = childTextures;
        this.stats = stats;
    }


    public static ChickenBreed breedFromBiome(Biome biome){
        String path = biome.getRegistryName().getPath();
        if(path.equals(Biomes.PLAINS.getRegistryName().getPath())){
            return LEGHORN;
        }
        if(path.contains("forest") && !path.contains("dark") && !path.contains("roofed")){
            return RHODE_ISLAND_RED;
        }

        if(path.equals(Biomes.DARK_FOREST.getRegistryName().getPath()) || path.equals(Biomes.DARK_FOREST_HILLS.getRegistryName().getPath())){
            return BARRED_ROCK;
        }
        if(path.equals(Biomes.DESERT.getRegistryName().getPath())){
            return ORPINGTON;
        }
        if(path.equals(Biomes.TAIGA_MOUNTAINS.getRegistryName().getPath()) || path.equals(Biomes.MOUNTAINS.getRegistryName().getPath()) || path.equals(Biomes.MOUNTAIN_EDGE.getRegistryName().getPath())){
            return AMERAUCANA;
        }
        if(path.contains("jungle")){
            return OLIVE_EGGER;
        }
        if(path.equals(Biomes.SWAMP.getRegistryName().getPath())){
            return MARANS;
        }
        if(path.contains("snow")){
            return SILKIE;
        }

        return random(JUNGLEFOWL);
    }

    public static ChickenBreed randomBasedOnBiome(Biome biome) {
        Random rand = new Random();
        if(rand.nextFloat() < 0.8){
            return breedFromBiome(biome);
        }else{
            return random(JUNGLEFOWL);
        }
    }

    public static ChickenBreed random(ChickenBreed... not){
        Random random = new Random();
        ChickenBreed value = values()[random.nextInt(values().length)];
        for(ChickenBreed c : not){
            if(value == c){
                return random(not);
            }
        }
        return value;
    }

    public String randomChick(){
        return childTextures.get(randomChickIndex());
    }

    public int randomChickIndex(){
        return new Random(System.currentTimeMillis()).nextInt(childTextures.size());
    }

    public String randomVariant(){
        Object[] textures = textureMap.keySet().toArray();
        return (String) textures[new Random().nextInt(textures.length)];
    }


    public static class ChickenVariant {
        private final String male, female;

        public String get(Sex sex){
            return sex == Sex.MALE ? male : female;
        }

        public ChickenVariant(String male, String female){
            this.male = male;
            this.female = female;
        }

        public ChickenVariant(String all){
            this.male = all;
            this.female = all;
        }

        public ChickenVariant(String all, boolean rh){
            this.male = all + "_rooster";
            this.female = all + "_hen";
        }
    }
}
