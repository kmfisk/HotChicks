package com.github.kmfisk.hotchicks.entity.stats;

import net.minecraft.util.Mth;

public class ChickenStats extends Stats {
    public ChickenStats(int tameness, int carcassQuality, int growthRate, int eggSpeed) {
        super(tameness, carcassQuality, growthRate);
        this.eggSpeed = eggSpeed;
    }

    @Override
    public Stats copy() {
        return new ChickenStats(tameness, carcassQuality, growthRate, eggSpeed);
    }

    @Override
    public Stats average(Stats parent2Stats, boolean includeTameness) {
        Stats stats = super.average(parent2Stats, includeTameness);
        ChickenStats parent2ChickenStats = (ChickenStats) parent2Stats;

        return new ChickenStats(
                stats.tameness, stats.carcassQuality, stats.growthRate,
                (int) Mth.average(new long[]{parent2ChickenStats.eggSpeed, eggSpeed})
        );
    }

    @Override
    public Stats mutate(double chance) {
        Stats chickenStats = super.mutate(chance);
        chickenStats.eggSpeed = mutate(StatType.EGG_SPEED, chickenStats.eggSpeed, chance);
        return chickenStats;
    }
}
