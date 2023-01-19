package com.github.kmfisk.hotchicks.entity.stats;

public class RabbitStats extends Stats {
    public int litterSize;

    public RabbitStats(int tameness, int carcassQuality, int hideQuality, int growthRate, int litterSize) {
        super(tameness, carcassQuality, hideQuality, growthRate);
        this.litterSize = litterSize;
    }

    @Override
    public Stats copy() {
        return new RabbitStats(tameness, carcassQuality, hideQuality, growthRate, litterSize);
    }

    @Override
    public Stats average(Stats parent2Stats, boolean includeTameness) {
        Stats stats = super.average(parent2Stats, includeTameness);
        RabbitStats parent2RabbitStats = (RabbitStats) parent2Stats;

        return new RabbitStats(
                stats.tameness,
                stats.carcassQuality,
                average(parent2RabbitStats.hideQuality, hideQuality),
                stats.growthRate,
                average(parent2RabbitStats.litterSize, litterSize)
        );
    }

    @Override
    public Stats mutate(double chance) {
        Stats stats = super.mutate(chance);
        RabbitStats rabbitStats = new RabbitStats(stats.tameness, stats.carcassQuality, stats.hideQuality, stats.growthRate, litterSize);

        if (this.rand.nextFloat() <= chance)
            rabbitStats.litterSize = this.rand.nextFloat() <= 0.8 ? Math.min(4, rabbitStats.litterSize + 1) : Math.max(0, rabbitStats.litterSize - 1);

        return rabbitStats;
    }
}
