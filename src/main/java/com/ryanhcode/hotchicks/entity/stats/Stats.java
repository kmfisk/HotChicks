package com.ryanhcode.hotchicks.entity.stats;

import java.util.Random;

public class Stats {
    public Random rand = new Random(System.currentTimeMillis());
    public int tameness;
    public int carcassQuality;
    public int growthRate;

    public Stats(int tameness, int carcassQuality, int growthRate) {
        this.tameness = tameness;
        this.carcassQuality = carcassQuality;
        this.growthRate = growthRate;
    }

    public Stats copy() {
        return new Stats(tameness, carcassQuality, growthRate);
    }

    public Stats average(Stats stats) {
        return new Stats(
                (stats.tameness + tameness) / 2,
                (stats.carcassQuality + carcassQuality) / 2,
                (stats.growthRate + growthRate) / 2
        );
    }

    public Stats mutate(double chance) {
        Stats stats = copy();
        stats.tameness *= 1.1; // todo, mutate tameness?

        if (this.rand.nextFloat() < chance)
            stats.carcassQuality += this.rand.nextFloat() < 0.5 ? 1 : -1;

        if (this.rand.nextFloat() < chance)
            stats.growthRate += this.rand.nextFloat() < 0.5 ? 1 : -1;

        return stats;
    }
}
