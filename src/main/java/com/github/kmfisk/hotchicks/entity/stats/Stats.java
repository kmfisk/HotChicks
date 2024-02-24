package com.github.kmfisk.hotchicks.entity.stats;

import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import com.github.kmfisk.hotchicks.entity.base.Temperature;
import net.minecraft.util.Mth;

import java.util.Random;

public class Stats {
    public Random rand = new Random(System.currentTimeMillis());
    public int tameness;
    public int carcassQuality;
    public int hideQuality;
    public int growthRate;
    public int eggSpeed;
    public int litterSize;
    public int milkYield;

    public Stats(int tameness, int carcassQuality, int growthRate) {
        this.tameness = tameness;
        this.carcassQuality = carcassQuality;
        this.growthRate = growthRate;
    }

    public Stats copy() {
        return new Stats(tameness, carcassQuality, growthRate);
    }

    public Stats average(Stats parent2Stats, boolean includeTameness) {
        return new Stats(
                includeTameness ? (int) Mth.average(new long[]{parent2Stats.tameness, tameness}) : tameness,
                (int) Mth.average(new long[]{parent2Stats.carcassQuality, carcassQuality}),
                (int) Mth.average(new long[]{parent2Stats.growthRate, growthRate})
        );
    }

    public Stats mutate(double chance) {
        Stats stats = copy();

        if (this.rand.nextFloat() <= chance)
            stats.tameness = this.rand.nextFloat() <= 0.8 ? Math.min(StatType.TAMENESS.getMax(), stats.tameness + 1 + this.rand.nextInt(10)) : Math.max(StatType.TAMENESS.getMin(), stats.tameness - this.rand.nextInt(5) + 1);
        stats.carcassQuality = mutate(StatType.CARCASS_QUALITY, stats.carcassQuality, chance);
        stats.growthRate = mutate(StatType.GROWTH_RATE, stats.growthRate, chance);

        return stats;
    }

    protected int mutate(StatType statType, int stat, double chance) {
        if (this.rand.nextFloat() <= chance)
            stat = this.rand.nextFloat() <= 0.8 ? Math.min(statType.getMax(), stat + 1) : Math.max(statType.getMin(), stat - 1);
        return stat;
    }

    public int randomLitterSize() {
        int chance = rand.nextInt(100);
        if (chance < 70) return litterSize;
        else if (chance < 95) return litterSize == 0 ? 0 : rand.nextInt(litterSize);
        else return Math.min(StatType.LITTER_SIZE.getMax(), litterSize + 1);
    }

    public float getDebuff(LivestockEntity entity, float mild, float extreme) {
        if (entity.getHunger().isLow() || entity.getThirst().isLow()) return extreme;
        Temperature breedTemp = entity.getBreedTemperature();
        float temperature = entity.level.getBiome(entity.blockPosition()).getBaseTemperature();
        if (breedTemp.isOutsideComfortLevel(temperature)) return breedTemp.isExtreme(temperature) ? extreme : mild;
        else return 1.0F;
    }

    public int getGrowthRateForStat(LivestockEntity entity) {
        int rate = HotChicksConfig.growthSpeed.get() * (5 - growthRate);
        return (int) (rate * getDebuff(entity, 1.5F, 2.0F));
    }

    public int getEggSpeedForStat(LivestockEntity entity) {
        int speed = HotChicksConfig.eggSpeed.get() * (5 - eggSpeed);
        return (int) (speed * getDebuff(entity, 1.5F, 2.0F));
    }

    public int getMilkYieldForStat(LivestockEntity entity) {
        int yield = (int) Math.pow(2, milkYield);
        return (int) (yield * getDebuff(entity, 0.25F, 0.5F));
    }

    public enum StatType {
        TAMENESS(0, 100),
        CARCASS_QUALITY(0, 4),
        HIDE_QUALITY(0, 4),
        GROWTH_RATE(0, 4),
        EGG_SPEED(0, 4),
        LITTER_SIZE(0, 4),
        MILK_YIELD(0, 4);

        private final int min;
        private final int max;

        StatType(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }
}
