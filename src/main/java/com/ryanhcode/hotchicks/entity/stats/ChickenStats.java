package com.ryanhcode.hotchicks.entity.stats;

public class ChickenStats extends Stats {
    public int eggSpeed;

    public ChickenStats(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        super(tameness, carcassQuality, growthRate);
        this.eggSpeed = eggSpeed;
    }

    @Override
    public Stats copy() {
        return new ChickenStats(this.tameness, this.carcassQuality, this.growthRate, this.eggSpeed);
    }

    @Override
    public Stats average(Stats parent2Stats, boolean includeTameness) {
        Stats stats = super.average(parent2Stats, includeTameness);
        ChickenStats parent2Stats1 = (ChickenStats) parent2Stats;

        return new ChickenStats(
                stats.tameness, stats.carcassQuality, stats.growthRate,
                average(parent2Stats1.eggSpeed, this.eggSpeed)
        );
    }

    @Override
    public Stats mutate(double chance) {
        Stats stats = super.mutate(chance);
        ChickenStats chickenStats = new ChickenStats(stats.tameness, stats.carcassQuality, stats.growthRate, this.eggSpeed);

        if (this.rand.nextFloat() <= chance)
            chickenStats.eggSpeed = this.rand.nextFloat() <= 0.8 ? Math.min(4, chickenStats.eggSpeed + 1) : Math.max(0, chickenStats.eggSpeed - 1);

        return chickenStats;
    }
}
