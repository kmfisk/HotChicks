package com.ryanhcode.hotchicks.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class LivestockEntity extends AnimalEntity {
    public static final DataParameter<Boolean> SEX = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> TAMENESS = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> CARCASS_QUALITY = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> GROWTH_RATE = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(TAMENESS, 50);
        this.entityData.define(CARCASS_QUALITY, 0);
        this.entityData.define(GROWTH_RATE, 0);
    }

    public void setSex(boolean isMale) {
        this.entityData.set(SEX, isMale);
    }

    public Sex getSex() {
        return Sex.getSex(entityData.get(SEX));
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setTameness(int tameness) {
        entityData.set(TAMENESS, tameness);
    }

    public int getTameness() {
        return entityData.get(TAMENESS);
    }

    public void setCarcassQuality(int carcassQuality) {
        this.entityData.set(CARCASS_QUALITY, carcassQuality);
    }

    public int getCarcassQuality() {
        return this.entityData.get(CARCASS_QUALITY);
    }

    public void setGrowthRate(int growthRate) {
        this.entityData.set(GROWTH_RATE, growthRate);
    }

    public int getGrowthRate() {
        return this.entityData.get(GROWTH_RATE);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sex", this.getSex().toBool());
        compound.putInt("Variant", this.getVariant());
        compound.putInt("Tameness", this.getTameness());
        compound.putInt("CarcassQuality", this.getCarcassQuality());
        compound.putInt("GrowthRate", this.getGrowthRate());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setSex(compound.getBoolean("Sex"));
        this.setVariant(compound.getInt("Variant"));
        this.setTameness(compound.getInt("Tameness"));
        this.setCarcassQuality(compound.getInt("CarcassQuality"));
        this.setGrowthRate(compound.getInt("GrowthRate"));
    }
}