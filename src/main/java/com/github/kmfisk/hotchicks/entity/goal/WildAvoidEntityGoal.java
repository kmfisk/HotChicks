package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;

public class WildAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final LivestockEntity livestock;

    public WildAvoidEntityGoal(LivestockEntity livestockEntity, Class<T> classToAvoid, float maxDist, double walkSpeedMod, double sprintSpeedMod) {
        super(livestockEntity, classToAvoid, maxDist, walkSpeedMod, sprintSpeedMod);
        this.livestock = livestockEntity;
    }

    @Override
    public boolean canUse() {
        return !livestock.isCareRequired() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !livestock.isCareRequired() && super.canContinueToUse();
    }
}
