package com.github.kmfisk.hotchicks.entity.stats;

import java.util.Random;

public class Stats {
    public Random rand = new Random(System.currentTimeMillis());
    public int tameness;
    public int carcassQuality;
    public int hideQuality;
    public int growthRate;

    public Stats(int tameness, int carcassQuality, int growthRate) {
        this.tameness = tameness;
        this.carcassQuality = carcassQuality;
        this.growthRate = growthRate;
    }

    public Stats(int tameness, int carcassQuality, int hideQuality, int growthRate) {
        this.tameness = tameness;
        this.carcassQuality = carcassQuality;
        this.hideQuality = hideQuality;
        this.growthRate = growthRate;
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

        if (this.rand.nextFloat() <= chance)
            stats.tameness = this.rand.nextFloat() <= 0.8 ? Math.min(100, stats.tameness + 1 + this.rand.nextInt(10)) : Math.max(0, stats.tameness - this.rand.nextInt(5) + 1);

        if (this.rand.nextFloat() <= chance)
            stats.carcassQuality = this.rand.nextFloat() <= 0.8 ? Math.min(4, stats.carcassQuality + 1) : Math.max(0, stats.carcassQuality - 1);

        if (this.rand.nextFloat() <= chance)
            stats.growthRate = this.rand.nextFloat() <= 0.8 ? Math.min(4, stats.growthRate + 1) : Math.max(0, stats.growthRate - 1);

        return stats;
    }
}
