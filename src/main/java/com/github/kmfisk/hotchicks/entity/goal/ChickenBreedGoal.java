package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.base.Sex;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ChickenBreedGoal extends Goal {
    private static final EntityPredicate PARTNER_TARGETING = (new EntityPredicate()).range(8.0D).allowInvulnerable().allowSameTeam().allowUnseeable();
    protected final HotChickenEntity animal;
    private final Class<? extends HotChickenEntity> mateClass;
    protected final World world;
    protected HotChickenEntity targetMate;
    private int loveTime;
    private final double moveSpeed;

    public ChickenBreedGoal(HotChickenEntity animal, double speedIn) {
        this(animal, speedIn, animal.getClass());
    }

    public ChickenBreedGoal(HotChickenEntity animal, double moveSpeed, Class<? extends HotChickenEntity> mateClass) {
        this.animal = animal;
        this.world = animal.level;
        this.mateClass = mateClass;
        this.moveSpeed = moveSpeed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.animal.isInLove() || this.animal.getSex() != Sex.FEMALE)
            return false;
        else {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null && this.targetMate.getSex() == Sex.MALE;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetMate.isAlive() && this.targetMate.isInLove() && this.loveTime < 60;
    }

    @Override
    public void stop() {
        this.targetMate = null;
        this.loveTime = 0;
    }

    @Override
    public void tick() {
        this.animal.getLookControl().setLookAt(this.targetMate, 10.0F, (float) this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.targetMate, this.moveSpeed);
        ++this.loveTime;
        if (this.loveTime >= 60 && this.animal.distanceToSqr(this.targetMate) < 9.0D)
            this.breed();
    }

    @Nullable
    private HotChickenEntity getNearbyMate() {
        List<HotChickenEntity> list = this.world.getNearbyEntities(this.mateClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        HotChickenEntity animalentity = null;

        for (HotChickenEntity animalentity1 : list) {
            if (this.animal.canMate(animalentity1) && this.animal.distanceToSqr(animalentity1) < d0) {
                animalentity = animalentity1;
                d0 = this.animal.distanceToSqr(animalentity1);
            }
        }

        return animalentity;
    }

    protected void breed() {
        this.animal.spawnChildFromBreeding((ServerWorld) this.world, this.targetMate);
    }
}
