package com.github.mnesikos.livestock.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.UUID;

public abstract class LivestockEntityBase extends AnimalEntity {
    private static final TrackedData<Boolean> SEX = DataTracker.registerData(ChickenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public LivestockEntityBase(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        if (getClass().isInstance(entity)) {
            EntityType<?> type;
            if (getType() != entity.getType()) {
                getRandom().nextBoolean() ? type = getType() : type = entity.getType();
            }
            LivestockEntityBase child = (LivestockEntityBase) getType().create(world);
            return child;
        }
        return null;
    }

    public Sex getSex() {
        return Sex.getSex(this.getDataTracker().get(SEX));
    }

    public void setSex(Sex sex) {
        this.getDataTracker().set(SEX, Sex.getSex(sex));
    }

    enum Sex {
        MALE,
        FEMALE;

        public static Sex getSex(boolean sex) {
            return sex ? MALE : FEMALE;
        }

        public static boolean getSex(Sex sex) {
            return sex == MALE;
        }
    }
}
