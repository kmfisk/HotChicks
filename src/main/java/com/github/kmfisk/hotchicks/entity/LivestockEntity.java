package com.github.kmfisk.hotchicks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class LivestockEntity extends AnimalEntity {
    public static final DataParameter<Boolean> SEX = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> TAMENESS = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> CARCASS_QUALITY = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> HIDE_QUALITY = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> GROWTH_RATE = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> LITTER_SIZE = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(TAMENESS, 50);
        this.entityData.define(CARCASS_QUALITY, 0);
        this.entityData.define(GROWTH_RATE, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        setSex(Sex.fromBool(random.nextFloat() <= this.getMaleRatio()));
        setVariant(0);
        return entityData;
    }

    public abstract float getMaleRatio();

    public abstract int getMaxVariants();

    public Sex getSex() {
        return Sex.fromBool(entityData.get(SEX));
    }

    public void setSex(Sex sex) {
        this.entityData.set(SEX, sex.toBool());
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

    public void setHideQuality(int hideQuality) {
        this.entityData.set(HIDE_QUALITY, hideQuality);
    }

    public int getHideQuality() {
        return this.entityData.get(HIDE_QUALITY);
    }

    public void setGrowthRate(int growthRate) {
        this.entityData.set(GROWTH_RATE, growthRate);
    }

    public int getGrowthRate() {
        return this.entityData.get(GROWTH_RATE);
    }

    public void setLitterSize(int litterSize) {
        this.entityData.set(LITTER_SIZE, litterSize);
    }

    public int getLitterSize() {
        return this.entityData.get(LITTER_SIZE);
    }

    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sex", this.getSex().toBool());
        nbt.putInt("Variant", this.getVariant());
        nbt.putInt("Tameness", this.getTameness());
        nbt.putInt("CarcassQuality", this.getCarcassQuality());
        nbt.putInt("GrowthRate", this.getGrowthRate());
    }

    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSex(Sex.fromBool(nbt.getBoolean("Sex")));
        this.setVariant(nbt.getInt("Variant"));
        this.setTameness(nbt.getInt("Tameness"));
        this.setCarcassQuality(nbt.getInt("CarcassQuality"));
        this.setGrowthRate(nbt.getInt("GrowthRate"));
    }

    public static boolean checkLivestockSpawnRules(EntityType<? extends LivestockEntity> entityType, IServerWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockState blockState = world.getBlockState(pos.below());
        return (blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.SNOW) || blockState.is(BlockTags.ICE)
                || Tags.Blocks.SAND.contains(blockState.getBlock()) || Tags.Blocks.DIRT.contains(blockState.getBlock()))
                && world.getRawBrightness(pos, 0) > 8;
    }

    protected Biome getBiome() {
        int x = MathHelper.floor(this.getX());
        int z = MathHelper.floor(this.getZ());
        return this.level.getBiome(new BlockPos(x, 0, z));
    }

    public enum Sex {
        MALE,
        FEMALE;

        public static Sex fromBool(boolean value) {
            return value ? MALE : FEMALE;
        }

        public boolean toBool() {
            return this == MALE;
        }
    }
}