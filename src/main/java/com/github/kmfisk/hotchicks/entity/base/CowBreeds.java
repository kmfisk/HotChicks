package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.stats.CowStats;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public enum CowBreeds {
    AUROCHS(50, 0, 0, 0, 1, Temperature.WARM, 1),
    ANGUS(75, 3, 4, 3, 1, Temperature.WARM, 2),
    BRAHMA(75, 3, 1, 0, 2, Temperature.HOT, 5),
    BROWN_SWISS(75, 1, 3, 2, 4, Temperature.WARM, 1),
    GUERNSEY(75, 1, 2, 4, 3, Temperature.WARM, 2),
    HEREFORD(75, 3, 3, 2, 0, Temperature.WARM, 4),
    HIGHLAND(75, 3, 3, 0, 0, Temperature.COLD, 4),
    HOLSTEIN(75, 2, 2, 0, 4, Temperature.WARM, 4),
    JERSEY(75, 1, 3, 2, 3, Temperature.WARM, 1),
    LAKENVELDER(75, 1, 2, 3, 4, Temperature.COLD, 2),
    LONGHORN(75, 2, 4, 4, 1, Temperature.HOT, 5);

    public static final int MAX_VARIANTS = 30;
    private final CowStats stats;
    private final Temperature temperature;
    private final int variants;

    CowBreeds(int tameness, int carcassQuality, int hideQuality, int growthRate, int milkYield, Temperature temperature, int variants) {
        this.stats = new CowStats(tameness, carcassQuality, hideQuality, growthRate, milkYield);
        this.temperature = temperature;
        this.variants = variants;
    }

    public CowStats getStats() {
        return stats;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public int getVariantCountOfBreed() {
        return variants;
    }

    public static int randomFromBreed(Random random, CowBreeds breed) {
        switch (breed) {
            default:
            case AUROCHS:
                return 0;
            case ANGUS:
                return random.nextInt(ANGUS.variants) + 1;
            case BRAHMA:
                return random.nextInt(BRAHMA.variants) + 3;
            case BROWN_SWISS:
                return 8;
            case GUERNSEY:
                return random.nextInt(GUERNSEY.variants) + 9;
            case HEREFORD:
                return random.nextInt(HEREFORD.variants) + 11;
            case HIGHLAND:
                return random.nextInt(HIGHLAND.variants) + 15;
            case HOLSTEIN:
                return random.nextInt(HOLSTEIN.variants) + 19;
            case JERSEY:
                return 23;
            case LAKENVELDER:
                return random.nextInt(LAKENVELDER.variants) + 24;
            case LONGHORN:
                return random.nextInt(LONGHORN.variants) + 26;
        }
    }

    public static int randomBasedOnBiome(Random random, Biome biome) {
        List<Integer> possibleVariants = Lists.newArrayList();

        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()));
        if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD))
            possibleVariants.add(randomFromBreed(random, HOLSTEIN));
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.SAVANNA) && !biomeTypes.contains(BiomeDictionary.Type.JUNGLE) && !biomeTypes.contains(BiomeDictionary.Type.WET) && !biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS))
            possibleVariants.addAll(ImmutableList.of(randomFromBreed(random, JERSEY), randomFromBreed(random, GUERNSEY)));
        if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.HOT))
            possibleVariants.add(randomFromBreed(random, LAKENVELDER));
        if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY))
            possibleVariants.add(randomFromBreed(random, BROWN_SWISS));
        if (biomeTypes.contains(BiomeDictionary.Type.SWAMP))
            possibleVariants.add(randomFromBreed(random, HEREFORD));
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && biomeTypes.contains(BiomeDictionary.Type.DENSE))
            possibleVariants.add(randomFromBreed(random, ANGUS));
        if (biomeTypes.contains(BiomeDictionary.Type.SNOWY) && !biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN))
            possibleVariants.add(randomFromBreed(random, HIGHLAND));
        if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA))
            possibleVariants.add(randomFromBreed(random, LONGHORN));
        if (biomeTypes.contains(BiomeDictionary.Type.HOT) && biomeTypes.contains(BiomeDictionary.Type.SANDY))
            possibleVariants.add(randomFromBreed(random, BRAHMA));

        if (possibleVariants.isEmpty()) return random.nextInt(MAX_VARIANTS) + 1;
        else return possibleVariants.get(random.nextInt(possibleVariants.size()));
    }

    public BaseComponent getLocalizedName() {
        return new TranslatableComponent("breed." + HotChicks.MOD_ID + ".cow." + name().toLowerCase(Locale.ROOT));
    }
}
