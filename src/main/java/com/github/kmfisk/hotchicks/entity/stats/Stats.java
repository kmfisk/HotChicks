package com.github.kmfisk.hotchicks.entity.stats;

import java.util.Random;

public class Stats {
    public Random rand = new Random(System.currentTimeMillis());
    public int tameness;
    public int carcassQuality;
    public int hideQuality;
    public int growthRate;
    public int eggSpeed;
    public int litterSize;

    public Stats(int tameness, int carcassQuality, int growthRate) {
        this.tameness = tameness;
        this.carcassQuality = carcassQuality;
        this.growthRate = growthRate;
    }

    public Stats(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        this(tameness, carcassQuality, growthRate);
        this.eggSpeed = eggSpeed;
    }

    public Stats(int tameness, int carcassQuality, int hideQuality, int growthRate, int litterSize) {
        this(tameness, carcassQuality, growthRate);
        this.hideQuality = hideQuality;
        this.litterSize = litterSize;
    }

    public Stats copy() {
        return new Stats(tameness, carcassQuality, growthRate);
    }

    public Stats average(Stats parent2Stats, boolean includeTameness) {
        return new Stats(
                includeTameness ? average(parent2Stats.tameness, tameness) : tameness,
                average(parent2Stats.carcassQuality, carcassQuality),
                average(parent2Stats.growthRate, growthRate)
        );
    }

    protected int average(int stat1, int stat2) {
        return (stat1 + stat2) / 2;
    }

    public Stats mutate(double chance) {
        Stats stats = copy();

        if (this.rand.nextFloat() <= chance) stats.tameness = this.rand.nextFloat() <= 0.8 ? Math.min(StatType.TAMENESS.max, stats.tameness + 1 + this.rand.nextInt(10)) : Math.max(StatType.TAMENESS.min, stats.tameness - this.rand.nextInt(5) + 1);
        stats.carcassQuality = mutate(StatType.CARCASS_QUALITY, stats.carcassQuality, chance);
        stats.growthRate = mutate(StatType.GROWTH_RATE, stats.growthRate, chance);

        return stats;
    }

    protected int mutate(StatType statType, int stat, double chance) {
        if (this.rand.nextFloat() <= chance) stat = this.rand.nextFloat() <= 0.8 ? Math.min(statType.max, stat + 1) : Math.max(statType.min, stat - 1);
        return stat;
    }

    public enum StatType {
        TAMENESS(0, 100),
        CARCASS_QUALITY(0, 4),
        HIDE_QUALITY(0, 4),
        GROWTH_RATE(0, 4),
        EGG_SPEED(0, 8),
        LITTER_SIZE(0, 4);

        public final int min;
        public final int max;

        StatType(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
