package com.ryanhcode.hotchicks.entity;

/*
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.base.NEWLivestockEntity;
import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class NEWHotChickenEntity extends NEWLivestockEntity {
    private static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(NEWHotChickenEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(NEWHotChickenEntity.class, DataSerializers.INT);
    public final int maxVariants = 26;

    public NEWHotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {}

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EGG_TIMER, 0);
        this.entityData.define(EGG_SPEED, 0);
    }

    public void setStats(int carcassQuality, int growthRate, int eggSpeed) {
        this.entityData.set(CARCASS_QUALITY, carcassQuality);
        this.entityData.set(GROWTH_RATE, growthRate);
        this.entityData.set(EGG_SPEED, eggSpeed);
    }

    public void setStats(ChickenStats stats) {
        this.entityData.set(CARCASS_QUALITY, stats.carcass_quality);
        this.entityData.set(GROWTH_RATE, stats.growth_rate);
        this.entityData.set(EGG_SPEED, stats.egg_speed);
    }

    public ChickenStats getStats() {
        return new ChickenStats(
                this.entityData.get(CARCASS_QUALITY),
                this.entityData.get(GROWTH_RATE),
                this.entityData.get(EGG_SPEED)
        );
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setSex(random.nextInt(3) == 0);
        this.setVariant(0);
        return spawnDataIn;
    }

    @Override
    public ChickenBreed getBreedFromVariant(int variant) {
        switch (variant) {
            default: case 0:
                return ChickenBreed.JUNGLEFOWL;
            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                return ChickenBreed.AMERAUCANA;
            case 8:
                return ChickenBreed.BARRED_ROCK;
            case 9:
                return ChickenBreed.LEGHORN;
            case 10: case 11: case 12: case 13:
                return ChickenBreed.MARANS;
            case 14: case 15: case 16: case 17:
                return ChickenBreed.ORPINGTON;
            case 18: case 19: case 20:
                return ChickenBreed.RHODE_ISLAND_RED;
            case 21: case 22: case 23: case 24: case 25:
                return ChickenBreed.SILKIE;
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack item) {
        return item.getItem() == HotItems.CORN.get();
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }
}
*/
