package com.ryanhcode.hotchicks.entity.base;

import java.util.HashMap;
import java.util.Map;


public enum ChickenBreed {
    //JUNGLEFOWL("Junglefowl", "junglefowl", "junglefowl/junglefowl_rooster", BaseChickenStats.JUNGLEFOWL),

    JUNGLEFOWL("Junglefowl", "junglefowl",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1"));
            }}, BaseChickenStats.JUNGLEFOWL),
    LEGHORN("Leghorn", "leghorn",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1"));
            }}, BaseChickenStats.LEGHORN),
    RHODE_ISLAND_RED("Rhode Island Red", "rhode_island_red",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
    }}, BaseChickenStats.RHODE_ISLAND_RED),
    BARRED_ROCK("Barred Rock", "barred_rock",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
    }}, BaseChickenStats.BARRED_ROCK),
    OPRINGTON("Oprington", "oprington",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.OPRINGTON),
    AMERAUCANA("Amerucana", "ameraucana",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.AMERAUCANA),
    OLIVE_EGGER("Olive Egger", "olive_egger",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.OLIVE_EGGER),
    MARANS("Marans", "marans",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.MARANS),
    WYANDOTTE("Wyandotte", "wyandotte",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.WYANDOTTE),
    SILKIE("Silkie", "silkie",
            new HashMap<String, ChickenVariant>() {{
                put("default", new ChickenVariant("junglefowl/junglefowl_rooster", "junglefowl/junglefowl_hen"));
                put("child", new ChickenVariant("chicks/chick_1", "chicks/chick_1"));
            }}, BaseChickenStats.SILKIE);

    public final String name;
    public final String id;
    public final Map<String, ChickenVariant> textureMap;
    public final ChickenStats stats;

    ChickenBreed(String name, String id, Map<String, ChickenVariant> textureMap, ChickenStats stats){
        this.name = name;
        this.id = id;
        this.textureMap = textureMap;
        this.stats = stats;
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
    }
}
