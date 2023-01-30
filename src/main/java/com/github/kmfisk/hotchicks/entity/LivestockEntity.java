package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.HungerStat;
import com.github.kmfisk.hotchicks.entity.goal.FindFoodGoal;
import com.github.kmfisk.hotchicks.entity.stats.Stats;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
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
    public static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    private final HungerStat hunger;

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
        hunger = new HungerStat(this, HUNGER, getMaxHunger(), HotChicksConfig.hungerDepletion.get());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FindFoodGoal(this, 16, 2));
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(TAMENESS, 50);
        this.entityData.define(CARCASS_QUALITY, 0);
        this.entityData.define(GROWTH_RATE, 0);
        this.entityData.define(HUNGER, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        setSex(Sex.fromBool(random.nextFloat() <= this.getMaleRatio()));
        setVariant(0);
        return super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
    }

    public abstract float getMaleRatio();

    public abstract int getMaxVariants();

    public abstract int getMaxHunger();

    public abstract boolean isEdibleFood(ItemStack stack);

    public abstract Stats getStats();

    public abstract void setupStats(String breed);

    public abstract String getReadableBreed();

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

    public HungerStat getHunger() {
        return hunger;
    }

    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sex", this.getSex().toBool());
        nbt.putInt("Variant", this.getVariant());
        nbt.putInt("Tameness", this.getTameness());
        nbt.putInt("CarcassQuality", this.getCarcassQuality());
        nbt.putInt("GrowthRate", this.getGrowthRate());
        nbt.put("Hunger", hunger.toTag());
    }

    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setSex(Sex.fromBool(nbt.getBoolean("Sex")));
        this.setVariant(nbt.getInt("Variant"));
        this.setTameness(nbt.getInt("Tameness"));
        this.setCarcassQuality(nbt.getInt("CarcassQuality"));
        this.setGrowthRate(nbt.getInt("GrowthRate"));
        hunger.fromTag(nbt.getCompound("Hunger"));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (isAlive() && !level.isClientSide) {
            if (HotChicksConfig.hunger.get() && getHunger().getValue() > 0) hunger.tick();
            if (getHealth() < getMaxHealth() && tickCount % 20 == 0 && getHunger().getValue() == getHunger().getMax())
                heal(1.0F);
        }

        if (level.isClientSide && HotChicksConfig.hunger.get() && getHunger().isLow()) {
            if (tickCount % 10 == 0)
                level.addParticle(ParticleTypes.SMOKE, getRandomX(1.0D), getRandomY() + 0.5D, getRandomZ(1.0D), random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D);
        }
    }

    @Override
    public boolean canFallInLove() {
        return !getHunger().isLow() && super.canFallInLove();
    }

    @Override
    public void setBaby(boolean baby) {
        setAge(baby ? -(getStats().getGrowthRateForStat()) : 0);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isEdibleFood(stack)) {
            if (getHunger().getValue() < getHunger().getMax()) {
                usePlayerItem(player, stack);
                hunger.increment(1);
                return ActionResultType.sidedSuccess(level.isClientSide);
            }
        }
        if (stack.getItem() == Items.STICK && !level.isClientSide) {
            player.displayClientMessage(new StringTextComponent("Hunger: " +
                    getHunger().getValue() + " / " + getHunger().getMax() +
                    " -> " + getHunger().getTicks()), true);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    protected boolean shouldDropLoot() {
        return !getHunger().isLow() && super.shouldDropLoot();
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