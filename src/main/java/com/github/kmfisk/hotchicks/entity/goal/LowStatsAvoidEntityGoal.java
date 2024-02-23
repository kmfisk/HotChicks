package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class LowStatsAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    protected final LivestockEntity livestock;

    public LowStatsAvoidEntityGoal(LivestockEntity livestockEntity, Class<T> classToAvoid, float maxDist, double walkSpeedMod, double sprintSpeedMod) {
        super(livestockEntity, classToAvoid, maxDist, walkSpeedMod, sprintSpeedMod);
        this.livestock = livestockEntity;
    }

    @Override
    public boolean canUse() {
        return (livestock.getHunger().isLow() || livestock.getThirst().isLow()) && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return (livestock.getHunger().isLow() || livestock.getThirst().isLow()) && super.canContinueToUse();
    }
}
