package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import com.google.common.collect.Lists;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public enum RabbitBreeds {
    COTTONTAIL(50, 0, 0, 0, 1, 1),
    AMERICAN_CHINCHILLA(95, 2, 3, 2, 1, 2),
    CALIFORNIA(95, 2, 1, 2, 2, 1),
    DUTCH(95, 1, 1, 1, 4, 6),
    FLEMISH_GIANT(95, 4, 1, 0, 1, 5),
    NEW_ZEALAND(95, 3, 1, 3, 3, 3),
    REX(95, 1, 4, 1, 2, 9);

    public static final int MAX_VARIANTS = 26;
    private final RabbitStats stats;
    private final int variants;

    RabbitBreeds(int tameness, int carcassQuality, int hideQuality, int growthRate, int litterSize, int variants) {
        this.stats = new RabbitStats(tameness, carcassQuality, hideQuality, growthRate, litterSize);
        this.variants = variants;
    }

    public RabbitStats getStats() {
        return stats;
    }

    public int getVariantCountOfBreed() {
        return variants;
    }

    public static int randomFromBreed(Random random, RabbitBreeds breed) {
        switch (breed) {
            default:
            case COTTONTAIL:
                return 0;
            case AMERICAN_CHINCHILLA:
                return random.nextInt(AMERICAN_CHINCHILLA.variants) + 1;
            case CALIFORNIA:
                return 3;
            case DUTCH:
                return random.nextInt(DUTCH.variants) + 4;
            case FLEMISH_GIANT:
                return random.nextInt(FLEMISH_GIANT.variants) + 10;
            case NEW_ZEALAND:
                return random.nextInt(NEW_ZEALAND.variants) + 15;
            case REX:
                return random.nextInt(REX.variants) + 18;
        }
    }

    public static int randomBasedOnBiome(Random random, Biome biome) {
        List<Integer> possibleVariants = Lists.newArrayList();

        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()));
        if (biomeTypes.contains(BiomeDictionary.Type.COLD))
            possibleVariants.add(randomFromBreed(random, AMERICAN_CHINCHILLA));
        if (biomeTypes.contains(BiomeDictionary.Type.JUNGLE))
            possibleVariants.add(randomFromBreed(random, CALIFORNIA));
        if (biomeTypes.contains(BiomeDictionary.Type.SAVANNA) || (biomeTypes.contains(BiomeDictionary.Type.HOT) && biomeTypes.contains(BiomeDictionary.Type.SANDY)))
            possibleVariants.add(randomFromBreed(random, DUTCH));
        if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.HOT))
            possibleVariants.add(randomFromBreed(random, FLEMISH_GIANT));
        if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD))
            possibleVariants.add(randomFromBreed(random, NEW_ZEALAND));
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.SAVANNA) && !biomeTypes.contains(BiomeDictionary.Type.JUNGLE) && !biomeTypes.contains(BiomeDictionary.Type.WET) && !biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS))
            possibleVariants.add(randomFromBreed(random, REX));

        if (possibleVariants.isEmpty()) return random.nextInt(MAX_VARIANTS) + 1;
        else return possibleVariants.get(random.nextInt(possibleVariants.size()));
    }

    public TextComponent getLocalizedName() {
        return new TranslationTextComponent("breed." + HotChicks.MOD_ID + ".rabbit." + name().toLowerCase(Locale.ROOT));
    }
}
