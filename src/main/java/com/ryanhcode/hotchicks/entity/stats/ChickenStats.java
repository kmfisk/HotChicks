package com.ryanhcode.hotchicks.entity.stats;

public class ChickenStats extends Stats {
    public int eggSpeed;

    public ChickenStats(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        super(tameness, carcassQuality, growthRate);
        this.eggSpeed = eggSpeed;
    }

    @Override
    public Stats copy() {
        return new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
    }

    @Override
    public Stats average(Stats parent2Stats) {
        Stats stats = super.average(parent2Stats);
        ChickenStats parent2Stats1 = (ChickenStats) parent2Stats;

        return new ChickenStats(
                stats.tameness, stats.carcassQuality, stats.growthRate,
                (parent2Stats1.eggSpeed + eggSpeed) / 2
        );
    }

    @Override
    public Stats mutate(double chance) {
        Stats stats = super.mutate(chance);
        ChickenStats chickenStats = new ChickenStats(stats.tameness, stats.carcassQuality, stats.growthRate, eggSpeed);

        if (this.rand.nextFloat() < chance)
            chickenStats.eggSpeed += this.rand.nextFloat() < 0.5 ? 1 : -1;

        return chickenStats;
    }
}
