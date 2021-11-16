package com.ryanhcode.hotchicks.entity.base;

import java.util.Random;

public class ChickenStats {
    public int tameness;
    public int carcass_quality;
    public int growth_rate;
    public int egg_speed;

    public ChickenStats(int tameness, int carcass_quality, int growth_rate, int egg_speed) {
        this.tameness = tameness;
        this.carcass_quality = carcass_quality;
        this.growth_rate = growth_rate;
        this.egg_speed = egg_speed;
    }

    public ChickenStats copy() {
        return new ChickenStats(tameness, carcass_quality, growth_rate, egg_speed);
    }

    public ChickenStats average(ChickenStats stats) {
        return new ChickenStats(
                (stats.tameness + tameness) / 2,
                (stats.carcass_quality + carcass_quality) / 2,
                (stats.growth_rate + growth_rate) / 2,
                (stats.egg_speed + egg_speed) / 2
        );
    }

    public ChickenStats mutate(double chance) {
        ChickenStats stats = copy();
        stats.tameness *= 1.1; // todo, mutate tameness?

        Random rand = new Random(System.currentTimeMillis());
        if (rand.nextFloat() < chance)
            stats.carcass_quality += rand.nextFloat() < 0.5 ? 1 : -1;

        if (rand.nextFloat() < chance)
            stats.growth_rate += rand.nextFloat() < 0.5 ? 1 : -1;

        if (rand.nextFloat() < chance)
            stats.egg_speed += rand.nextFloat() < 0.5 ? 1 : -1;

        return stats;
    }
}
