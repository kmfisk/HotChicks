package com.github.kmfisk.hotchicks.entity.goal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class HotMeleeAttackGoal extends MeleeAttackGoal {
    private final double attackReach;

    public HotMeleeAttackGoal(CreatureEntity entity, double attackReach, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(entity, speedModifier, followingTargetEvenIfNotSeen);
        this.attackReach = attackReach;
    }

    @Override
    protected double getAttackReachSqr(LivingEntity target) {
        return (double) (this.mob.getBbWidth() * this.mob.getBbWidth() + target.getBbWidth()) + attackReach;
    }
}
