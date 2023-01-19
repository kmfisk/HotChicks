package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.base.LivestockEntity;
import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class HotRabbitEntity extends LivestockEntity {
    private static final DataParameter<Integer> HIDE_QUALITY = EntityDataManager.defineId(HotRabbitEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> LITTER_SIZE = EntityDataManager.defineId(HotRabbitEntity.class, DataSerializers.INT);

    public final int maxVariants = 26;

    public HotRabbitEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        // todo
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_QUALITY, 3);
        this.entityData.define(LITTER_SIZE, 2);
    }

    public void setHideQuality(int hideQuality) {
        this.entityData.set(HIDE_QUALITY, hideQuality);
    }

    public int getHideQuality() {
        return this.entityData.get(HIDE_QUALITY);
    }

    public void setLitterSize(int litterSize) {
        this.entityData.set(LITTER_SIZE, litterSize);
    }

    public int getLitterSize() {
        return this.entityData.get(LITTER_SIZE);
    }

    public void setStats(RabbitStats stats) {
        this.setTameness(stats.tameness);
        this.setCarcassQuality(stats.carcassQuality);
        this.setHideQuality(stats.hideQuality);
        this.setGrowthRate(stats.growthRate);
        this.setLitterSize(stats.litterSize);
    }

    public RabbitStats getStats() {
        return new RabbitStats(
                this.getTameness(),
                this.getCarcassQuality(),
                this.getHideQuality(),
                this.getGrowthRate(),
                this.getLitterSize()
        );
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HideQuality", this.getHideQuality());
        compound.putInt("LitterSize", this.getLitterSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setHideQuality(compound.getInt("HideQuality"));
        this.setLitterSize(compound.getInt("LitterSize"));
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
        this.setSex(random.nextBoolean());
        this.setVariant(0);
        this.setStats(new RabbitStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)));
        return entityData;
    }

    public RabbitBreeds getBreedFromVariant(int variant) {
        switch (variant) {
            default:
            case 0:
                return RabbitBreeds.COTTONTAIL;
            case 1:
            case 2:
                return RabbitBreeds.AMERICAN_CHINCHILLA;
            case 3:
                return RabbitBreeds.CALIFORNIA;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return RabbitBreeds.DUTCH;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return RabbitBreeds.FLEMISH_GIANT;
            case 15:
            case 16:
            case 17:
                return RabbitBreeds.NEW_ZEALAND;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return RabbitBreeds.REX;
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return super.getStandingEyeHeight(pose, size); // todo
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.CARROT;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return null;
    }

    @Override
    public boolean canMate(AnimalEntity mate) {
        if (!(mate instanceof HotRabbitEntity)) return false;
        else {
            HotRabbitEntity rabbit = (HotRabbitEntity) mate;
            if (rabbit == this) return false;
            else return this.isInLove() && rabbit.isInLove() && rabbit.getSex() != this.getSex();
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity entity) { // todo
        super.spawnChildFromBreeding(world, entity);
    }
}
