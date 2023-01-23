package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;

public class LivestockAvoidPlayerGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final LivestockEntity livestock;

    public LivestockAvoidPlayerGoal(LivestockEntity livestockEntity, Class<T> classToAvoid, float maxDist, double walkSpeedMod, double sprintSpeedMod) {
        super(livestockEntity, classToAvoid, maxDist, walkSpeedMod, sprintSpeedMod);
        this.livestock = livestockEntity;
    }

    @Override
    public boolean canUse() {
        return /*todo: HUNGER livestock.isHungry() &&*/ super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return /*livestock.isHungry() &&*/ super.canContinueToUse();
    }
}
