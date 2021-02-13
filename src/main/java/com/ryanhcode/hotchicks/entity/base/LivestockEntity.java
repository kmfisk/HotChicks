package com.ryanhcode.hotchicks.entity.base;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.Random;

public abstract class LivestockEntity extends AnimalEntity {
    private static final DataParameter<Boolean> SEX = EntityDataManager.createKey(LivestockEntity.class, DataSerializers.BOOLEAN);

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World worldIn, double m2f) {
        super(type, worldIn);
    }

    public abstract double getMaleToFemaleChance();

    public void registerData() {
        super.registerData();
        double random = getRNG().nextFloat();
        this.dataManager.register(SEX, random <= getMaleToFemaleChance());
    }

    public void setSex(Sex sex){
        dataManager.set(SEX, Sex.getSex(sex));
    }

    public void setSex(boolean sex){
        setSex(Sex.getSex(sex));
    }

    public Sex getSex(){
        return Sex.getSex(dataManager.get(SEX));
    }


    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("sex", Sex.getSex(getSex()));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setSex(compound.getBoolean("sex"));
    }
}