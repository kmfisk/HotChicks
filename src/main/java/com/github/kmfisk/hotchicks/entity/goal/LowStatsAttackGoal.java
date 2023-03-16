package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

public class LowStatsAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    protected final LivestockEntity livestock;
    private final int additionalAttackTime;
    private int cooldown = 0;

    public LowStatsAttackGoal(LivestockEntity livestockEntity, Class<T> targetType, int additionalAttackTime) {
        super(livestockEntity, targetType, 500, true, true, null);
        this.livestock = livestockEntity;
        this.additionalAttackTime = additionalAttackTime;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void decrementCooldown() {
        --cooldown;
    }

    @Override
    public boolean canUse() {
        return cooldown <= 0 && (livestock.getHunger().isLow() || livestock.getThirst().isLow()) && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return cooldown > 0 && (livestock.getHunger().isLow() || livestock.getThirst().isLow()) && super.canContinueToUse();
    }

    @Override
    public void start() {
        cooldown = livestock.getRandom().nextInt(additionalAttackTime) + 40;
        super.start();
    }
}
