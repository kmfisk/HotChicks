package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class HotRabbitEntity extends LivestockEntity {
    public HotRabbitEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
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

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
        setStats(new RabbitStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)));
        return entityData;
    }

    @Override
    public float getMaleRatio() {
        return 0.5F;
    }

    @Override
    public int getMaxVariants() {
        return 26;
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideQuality", this.getHideQuality());
        nbt.putInt("LitterSize", this.getLitterSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHideQuality(nbt.getInt("HideQuality"));
        this.setLitterSize(nbt.getInt("LitterSize"));
    }

    public RabbitBreeds getBreedFromVariant(int variant) {
        if (variant == 0) return RabbitBreeds.COTTONTAIL;
        if (variant <= 2) return RabbitBreeds.AMERICAN_CHINCHILLA;
        if (variant == 3) return RabbitBreeds.CALIFORNIA;
        if (variant <= 9) return RabbitBreeds.DUTCH;
        if (variant <= 14) return RabbitBreeds.FLEMISH_GIANT;
        if (variant <= 17) return RabbitBreeds.NEW_ZEALAND;
        if (variant <= 26) return RabbitBreeds.REX;

        return RabbitBreeds.COTTONTAIL;
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

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }
}
