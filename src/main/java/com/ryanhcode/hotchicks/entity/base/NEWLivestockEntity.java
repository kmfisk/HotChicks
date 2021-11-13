package com.ryanhcode.hotchicks.entity.base;

/*
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class NEWLivestockEntity extends AnimalEntity {
    public static final DataParameter<Boolean> SEX = EntityDataManager.defineId(NEWLivestockEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(NEWLivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> TAMENESS = EntityDataManager.defineId(NEWLivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> CARCASS_QUALITY = EntityDataManager.defineId(NEWLivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> GROWTH_RATE = EntityDataManager.defineId(NEWLivestockEntity.class, DataSerializers.INT);

    public NEWLivestockEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(TAMENESS, 0);
        this.entityData.define(CARCASS_QUALITY, 0);
        this.entityData.define(GROWTH_RATE, 0);
    }

    public void setSex(boolean isMale) {
        this.entityData.set(SEX, isMale);
    }

    public Sex getSex() {
        return this.entityData.get(SEX) ? Sex.MALE : Sex.FEMALE;
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setTameness(int tameness) {
        this.entityData.set(TAMENESS, tameness);
    }

    public int getTameness() {
        return this.entityData.get(TAMENESS);
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

    public abstract ChickenBreed getBreedFromVariant(int variant);

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sex", this.getSex().toBool());
        compound.putInt("Variant", this.getVariant());
        compound.putInt("Tameness", this.getTameness());
        compound.putInt("CarcassQuality", this.getCarcassQuality());
        compound.putInt("GrowthRate", this.getGrowthRate());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setSex(compound.getBoolean("Sex"));
        this.setVariant(compound.getInt("Variant"));
        this.setTameness(compound.getInt("Tameness"));
        this.setCarcassQuality(compound.getInt("CarcassQuality"));
        this.setGrowthRate(compound.getInt("GrowthRate"));
    }

    @Override
    public boolean canMate(AnimalEntity otherAnimal) {
        if (!(otherAnimal instanceof NEWLivestockEntity)) return false;
        else {
            NEWLivestockEntity livestockEntity = (NEWLivestockEntity) otherAnimal;
            if (livestockEntity.getSex() == this.getSex()) return false;
            return super.canMate(otherAnimal);
        }
    }
}
*/
