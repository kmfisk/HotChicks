package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
import com.github.kmfisk.hotchicks.item.HotItems;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public enum ChickenBreeds {
    JUNGLEFOWL(50, 0, 0, 0, HotItems.WHITE_EGG, Temperature.WARM, 1),
    AMERAUCANA(85, 2, 1, 2, HotItems.BLUE_EGG, Temperature.WARM, 7),
    BARRED_ROCK(85, 3, 3, 3, HotItems.WHITE_EGG, Temperature.COLD, 1),
    LEGHORN(85, 1, 2, 4, HotItems.WHITE_EGG, Temperature.HOT, 1),
    MARANS(85, 3, 1, 2, HotItems.CHOCOLATE_EGG, Temperature.HOT, 4),
    OLIVE_EGGER(85, 2, 1, 2, HotItems.GREEN_EGG, Temperature.WARM, 7),
    ORPINGTON(85, 3, 3, 1, HotItems.BROWN_EGG, Temperature.COLD, 4),
    RHODE_ISLAND_RED(85, 3, 2, 2, HotItems.BROWN_EGG, Temperature.WARM, 3),
    SILKIE(85, 3, 1, 1, HotItems.WHITE_EGG, Temperature.COLD, 5);

    public static final int MAX_VARIANTS = 32;
    private final ChickenStats stats;
    private final Supplier<Item> eggColor;
    private final Temperature temperature;
    private final int variants;

    ChickenBreeds(int tameness, int carcassQuality, int growthRate, int eggSpeed, Supplier<Item> eggColor, Temperature temperature, int variants) {
        this.stats = new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
        this.eggColor = eggColor;
        this.temperature = temperature;
        this.variants = variants;
    }

    public ChickenStats getStats() {
        return stats;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public int getVariantCountOfBreed() {
        return variants;
    }

    public Item getEggColor() {
        return eggColor.get();
    }

    public static int randomFromBreed(Random random, ChickenBreeds breed) {
        switch (breed) {
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
        List<Integer> possibleVariants = Lists.newArrayList();

        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()));
        if (biomeTypes.contains(BiomeDictionary.Type.PLAINS) && !biomeTypes.contains(BiomeDictionary.Type.HOT) && !biomeTypes.contains(BiomeDictionary.Type.COLD))
            possibleVariants.add(randomFromBreed(random, LEGHORN));
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && !biomeTypes.contains(BiomeDictionary.Type.SAVANNA) && !biomeTypes.contains(BiomeDictionary.Type.JUNGLE) && !biomeTypes.contains(BiomeDictionary.Type.WET) && !biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS))
            possibleVariants.add(randomFromBreed(random, RHODE_ISLAND_RED));
        if (biomeTypes.contains(BiomeDictionary.Type.FOREST) && biomeTypes.contains(BiomeDictionary.Type.DENSE))
            possibleVariants.add(randomFromBreed(random, BARRED_ROCK));
        if (biomeTypes.contains(BiomeDictionary.Type.CONIFEROUS) && !biomeTypes.contains(BiomeDictionary.Type.SNOWY))
            possibleVariants.add(randomFromBreed(random, ORPINGTON));
        if (biomeTypes.contains(BiomeDictionary.Type.MOUNTAIN) && !biomeTypes.contains(BiomeDictionary.Type.HOT))
            possibleVariants.addAll(ImmutableList.of(randomFromBreed(random, AMERAUCANA), randomFromBreed(random, OLIVE_EGGER)));
        if (biomeTypes.contains(BiomeDictionary.Type.SWAMP))
            possibleVariants.add(randomFromBreed(random, MARANS));
        if (biomeTypes.contains(BiomeDictionary.Type.SNOWY))
            possibleVariants.add(randomFromBreed(random, SILKIE));

        if (possibleVariants.isEmpty()) return random.nextInt(MAX_VARIANTS) + 1;
        else return possibleVariants.get(random.nextInt(possibleVariants.size()));
    }

    public BaseComponent getLocalizedName() {
        return new TranslatableComponent("breed." + HotChicks.MOD_ID + ".chicken." + name().toLowerCase(Locale.ROOT));
    }
}
