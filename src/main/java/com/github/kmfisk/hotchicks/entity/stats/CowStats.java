package com.github.kmfisk.hotchicks.entity.stats;

import net.minecraft.util.Mth;

public class CowStats extends Stats {
    public CowStats(int tameness, int carcassQuality, int hideQuality, int growthRate, int milkYield) {
        super(tameness, carcassQuality, growthRate);
        this.hideQuality = hideQuality;
        this.milkYield = milkYield;
    }

    @Override
    public Stats copy() {
        return new CowStats(tameness, carcassQuality, hideQuality, growthRate, milkYield);
    }

    @Override
    public Stats average(Stats parent2Stats, boolean includeTameness) {
        Stats stats = super.average(parent2Stats, includeTameness);
        CowStats parent2CowStats = (CowStats) parent2Stats;

        return new CowStats(
                stats.tameness,
                stats.carcassQuality,
                (int) Mth.average(new long[]{parent2CowStats.hideQuality, hideQuality}),
                stats.growthRate,
                (int) Mth.average(new long[]{parent2CowStats.milkYield, milkYield})
        );
    }

    @Override
    public Stats mutate(double chance) {
        Stats cowStats = super.mutate(chance);

        cowStats.hideQuality = mutate(StatType.HIDE_QUALITY, cowStats.hideQuality, chance);
        cowStats.milkYield = mutate(StatType.MILK_YIELD, cowStats.milkYield, chance);

        return cowStats;
    }
}
