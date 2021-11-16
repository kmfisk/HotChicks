package com.ryanhcode.hotchicks.entity;

import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.goal.ChickenBreedGoal;
import com.ryanhcode.hotchicks.entity.goal.LayEggsGoal;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.item.HotItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {
    private static final DataParameter<String> BREED_DATA = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> VARIANT = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.STRING);

    private static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> CHICK_TYPE = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, HotItems.CORN.get());

    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LayEggsGoal(this));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new ChickenBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BREED_DATA, "not_set");
        this.entityData.define(VARIANT, "not_set");
        this.entityData.define(EGG_SPEED, 3);
        this.entityData.define(EGG_TIMER, 0);
        this.entityData.define(CHICK_TYPE, 0);
    }

    public void setBreed(ChickenBreed breed) {
        this.entityData.set(BREED_DATA, breed.toString());
    }

    public ChickenBreed getBreed() {
        return ChickenBreed.valueOf(this.entityData.get(BREED_DATA));
    }

    public void setVariant(String v) {
        this.entityData.set(VARIANT, v);
    }

    public String getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setEggSpeed(int eggSpeed) {
        this.entityData.set(EGG_SPEED, eggSpeed);
    }

    public int getEggSpeed() {
        return this.entityData.get(EGG_SPEED);
    }

    public void setEggTimer(int i) {
        this.entityData.set(EGG_TIMER, i);
    }

    public int getEggTimer() {
        return this.entityData.get(EGG_TIMER);
    }

    public void setChickType(int chickType) {
        this.entityData.set(CHICK_TYPE, chickType);
    }

    public int getChickType() {
        return this.entityData.get(CHICK_TYPE);
    }

    public void setStats(ChickenStats stats) {
        this.setTameness(stats.tameness);
        this.setCarcassQuality(stats.carcass_quality);
        this.setGrowthRate(stats.growth_rate);
        this.setEggSpeed(stats.egg_speed);
    }

    public ChickenStats getStats() {
        return new ChickenStats(
                this.getTameness(),
                this.getCarcassQuality(),
                this.getGrowthRate(),
                this.getEggSpeed()
        );
    }

    public int getMaxEggTimer() {
        return 200;
    }

    private Biome getBiome() {
        int x = MathHelper.floor(this.getX());
        int z = MathHelper.floor(this.getZ());
        return this.level.getBiome(new BlockPos(x, 0, z));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Breed", this.getBreed().toString());
        compound.putString("Variant", this.getVariant());
        compound.putInt("EggSpeed", this.getEggSpeed());
        compound.putInt("EggTimer", this.getEggTimer());
        compound.putInt("ChickType", this.getChickType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setBreed(ChickenBreed.valueOf(compound.getString("Breed")));
        this.setVariant(compound.getString("Variant"));
        this.setEggSpeed(compound.getInt("EggSpeed"));
        this.setEggTimer(compound.getInt("EggTimer"));
        this.setChickType(compound.getInt("ChickType"));
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setSex(random.nextInt(3) == 0);
//        this.setVariant(0); todo
        return spawnDataIn;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isBaby() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == HotItems.CORN.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.getBreed().toString().equals("not_set")) {
            ChickenBreed breed = ChickenBreed.JUNGLEFOWL;
            this.setBreed(breed);
            this.setChickType(breed.randomChickIndex());
        }
        if (this.getVariant().equals("not_set")) {
            this.setVariant(this.getBreed().randomVariant());
        }

        if (!getMainHandItem().isEmpty()) {
            int timer = this.entityData.get(EGG_TIMER) + 1;
            if (timer < getMaxEggTimer() + 1) {
                this.entityData.set(EGG_TIMER, timer);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    @Override
    public boolean canMate(AnimalEntity otherAnimal) {
        HotChickenEntity other = (HotChickenEntity) otherAnimal;
        if (other == this) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && otherAnimal.isInLove() && other.getSex() != this.getSex();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 18) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else if (id == 19) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else {
            super.handleEntityEvent(id);
        }

    }

    @Override
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity animal) {
        HotChickenEntity other = (HotChickenEntity) animal;

        if (!this.getMainHandItem().isEmpty()) {
            world.broadcastEntityEvent(this, (byte) 19);
            return;
        }

        ItemStack stack = new ItemStack(HotItems.WHITE_EGG.get());

        if (other.getBreed().equals(this.getBreed())) {
            HotEggItem.setBreed(stack, this.getBreed().toString());
            boolean isMotherOrFathers = getRandom().nextFloat() < 0.6;
            if (isMotherOrFathers) {
                HotEggItem.setVariant(stack, isMotherOrFathers ? this.getVariant() : other.getVariant());
            }
        } else {
            HotEggItem.setBreed(stack, getRandom().nextFloat() < 0.5 ? this.getBreed().toString() : other.getBreed().toString());
        }

        ChickenStats stats = this.getStats().average(other.getStats()).mutate(0.15);
        HotEggItem.setStats(stack, stats);
        int avgtmness = (getTameness() + other.getTameness()) / 2;
        if (stats.tameness < 80) {
            HotEggItem.setBreed(stack, ChickenBreed.JUNGLEFOWL.toString());
        }
        if (stats.tameness > 80 && avgtmness <= 80) {
            Biome biome = getBiome();
            System.out.println("biome = " + biome);
            ChickenBreed breed = ChickenBreed.randomBasedOnBiome(biome);
            HotEggItem.setStats(stack, breed.stats);
            HotEggItem.setBreed(stack, breed.toString());
        }
        HotEggItem.setTameness(stack, stats.tameness);


        this.setItemInHand(Hand.MAIN_HAND, stack);

        this.setAge(6000);
        animal.setAge(6000);
        this.resetLove();
        animal.resetLove();

        world.broadcastEntityEvent(this, (byte) 18);
        world.addFreshEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
    }
}
