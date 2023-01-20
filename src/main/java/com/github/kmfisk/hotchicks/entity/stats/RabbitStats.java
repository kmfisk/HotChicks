package com.github.kmfisk.hotchicks.entity.stats;

public class RabbitStats extends Stats {
    public RabbitStats(int tameness, int carcassQuality, int hideQuality, int growthRate, int litterSize) {
        super(tameness, carcassQuality, hideQuality, growthRate, litterSize);
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
        Stats rabbitStats = super.mutate(chance);
//        RabbitStats rabbitStats = new RabbitStats(stats.tameness, stats.carcassQuality, stats.hideQuality, stats.growthRate, litterSize);

        rabbitStats.hideQuality = mutate(StatType.HIDE_QUALITY, rabbitStats.hideQuality, chance);
        rabbitStats.litterSize = mutate(StatType.LITTER_SIZE, rabbitStats.litterSize, chance);

        return rabbitStats;
    }
}
