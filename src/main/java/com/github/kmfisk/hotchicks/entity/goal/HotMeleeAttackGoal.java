package com.github.kmfisk.hotchicks.entity.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class HotMeleeAttackGoal extends MeleeAttackGoal {
    private final double attackReach;

    public HotMeleeAttackGoal(PathfinderMob entity, double attackReach, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(entity, speedModifier, followingTargetEvenIfNotSeen);
        this.attackReach = attackReach;
    }

    @Override
    protected double getAttackReachSqr(LivingEntity target) {
        return (double) (this.mob.getBbWidth() * this.mob.getBbWidth() + target.getBbWidth()) + attackReach;
    }
}
