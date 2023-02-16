package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LivestockBirthGoal extends Goal {
    protected final LivestockEntity mother;
    protected final World level;

    public LivestockBirthGoal(LivestockEntity livestockEntity) {
        this.mother = livestockEntity;
        this.level = livestockEntity.level;
    }

    @Override
    public boolean canUse() {
        return mother.hasChildrenToSpawn() && mother.getGestationTimer() > 0;
    }

    @Override
    public boolean canContinueToUse() {
        return mother.getGestationTimer() > 0 && mother.hasChildrenToSpawn();
    }

    @Override
    public void tick() {
        int gestationTimer = mother.getGestationTimer();
        --gestationTimer;
        mother.setGestationTimer(gestationTimer);

        if (mother.getGestationTimer() <= 0) mother.spawnChildrenFromPregnancy((ServerWorld) level);
    }
}
