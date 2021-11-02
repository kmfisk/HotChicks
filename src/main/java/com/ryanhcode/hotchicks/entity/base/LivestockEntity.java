package com.ryanhcode.hotchicks.entity.base;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class LivestockEntity extends AnimalEntity {
    private static final DataParameter<Boolean> SEX = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.BOOLEAN);

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World worldIn, double m2f) {
        super(type, worldIn);
    }

    public abstract double getMaleToFemaleChance();

    public void defineSynchedData() {
        super.defineSynchedData();
        double random = getRandom().nextFloat();
        this.entityData.define(SEX, random <= getMaleToFemaleChance());
    }

    public void setSex(Sex sex) {
        entityData.set(SEX, Sex.getSex(sex));
    }

    public void setSex(boolean sex) {
        setSex(Sex.getSex(sex));
    }

    public Sex getSex() {
        return Sex.getSex(entityData.get(SEX));
    }


    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("sex", Sex.getSex(getSex()));
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("sex")) {
            setSex(compound.getBoolean("sex"));
        }
    }

}