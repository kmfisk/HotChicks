package com.ryanhcode.hotchicks.entity.base;

public class ChickenStats {

    public final int carcass_quality;
    public final int growth_rate;
    public final int egg_speed;

    public ChickenStats(int carcass_quality, int growth_rate, int egg_speed) {
        this.carcass_quality = carcass_quality;
        this.growth_rate = growth_rate;
        this.egg_speed = egg_speed;
    }

    public ChickenStats copy(){
        return new ChickenStats(carcass_quality, growth_rate, egg_speed);
    }
}
