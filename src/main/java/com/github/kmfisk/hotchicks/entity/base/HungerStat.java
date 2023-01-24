package com.github.kmfisk.hotchicks.entity.base;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;

public final class HungerStat {
    private final LivestockEntity entity;
    private final DataParameter<Integer> data;
    private final int max;
    private final int depleteTimer;
    private int ticks;
    private int saturationTimer;

    public HungerStat(LivestockEntity entity, DataParameter<Integer> data, int max, int depleteTimer) {
        this.entity = entity;
        this.data = data;
        this.max = max;
        this.depleteTimer = depleteTimer;
        this.ticks = depleteTimer;
        entity.getEntityData().set(data, max);
    }

    public void tick() {
        if (--saturationTimer > 0) return;
        if (--ticks <= 0) {
            ticks = depleteTimer;
            entity.getEntityData().set(data, entity.getEntityData().get(data) - 1);
        }
    }

    public void increment(int value) {
        entity.getEntityData().set(data, Math.min(entity.getEntityData().get(data) + value, max));
        saturationTimer = depleteTimer / 2;
    }

    public int getTicks() {
        return ticks;
    }

    public int getValue() {
        return entity.getEntityData().get(data);
    }

    public int getMax() {
        return max;
    }

    public boolean isLow() {
        return getValue() <= getMax() / 2; // todo
    }

    public CompoundNBT toTag() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Value", entity.getEntityData().get(data));
        tag.putInt("Saturation", saturationTimer);
        return tag;
    }

    public void fromTag(CompoundNBT tag) {
        entity.getEntityData().set(data, tag.getInt("Value"));
        saturationTimer = tag.getInt("Saturation");
    }
}
