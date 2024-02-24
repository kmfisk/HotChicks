package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class LivestockBreedGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    protected final LivestockEntity animal;
    private final Class<? extends LivestockEntity> partnerClass;
    protected final Level level;
    protected LivestockEntity partner;
    private int loveTime;
    private final double speedModifier;

    public LivestockBreedGoal(LivestockEntity livestockEntity, double speedModifier) {
        this(livestockEntity, speedModifier, livestockEntity.getClass());
    }

    public LivestockBreedGoal(LivestockEntity livestockEntity, double speedModifier, Class<? extends LivestockEntity> partnerClass) {
        this.animal = livestockEntity;
        this.level = livestockEntity.level;
        this.partnerClass = partnerClass;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!animal.isInLove() || animal.getSex() == LivestockEntity.Sex.FEMALE) return false;
        else {
            partner = getNearbyMate();
            return partner != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return partner.isAlive() && partner.isInLove() && loveTime < 60;
    }

    @Override
    public void stop() {
        partner = null;
        loveTime = 0;
    }

    @Override
    public void tick() {
        animal.getLookControl().setLookAt(partner, 10.0F, (float) animal.getMaxHeadXRot());
        partner.getLookControl().setLookAt(animal, 10.0F, (float) partner.getMaxHeadXRot());
        animal.getNavigation().moveTo(partner, speedModifier);
        partner.getNavigation().moveTo(animal, speedModifier);
        ++loveTime;
        if (loveTime >= 60 && animal.distanceToSqr(partner) < 9.0D) breed();
    }

    @Nullable
    private LivestockEntity getNearbyMate() {
        List<? extends LivestockEntity> list = level.getNearbyEntities(partnerClass, PARTNER_TARGETING, animal, animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        LivestockEntity animalentity = null;

        for (LivestockEntity animalentity1 : list) {
            if (animal.canMate(animalentity1) && animal.distanceToSqr(animalentity1) < d0) {
                animalentity = animalentity1;
                d0 = animal.distanceToSqr(animalentity1);
            }
        }

        return animalentity;
    }

    protected void breed() {
        partner.spawnChildFromBreeding((ServerLevel) level, animal);
    }
}
