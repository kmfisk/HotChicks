package com.ryanhcode.hotchicks.entity.chicken;

import com.ryanhcode.hotchicks.entity.base.*;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.ItemRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
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
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {

    public ChickenBreed breed = ChickenBreed.JUNGLEFOWL;

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    private static final DataParameter<Integer> egg_timer = makeStat(DataSerializers.INT);

    private static final DataParameter<Integer> chick_type = makeStat(DataSerializers.INT);
    private static final DataParameter<Integer> tameness = makeStat(DataSerializers.INT);
    private static final DataParameter<Integer> carcass_quality = makeStat(DataSerializers.INT);
    private static final DataParameter<Integer> growth_rate = makeStat(DataSerializers.INT);
    private static final DataParameter<Integer> egg_speed = makeStat(DataSerializers.INT);

    private static final DataParameter<String> breed_data = makeStat(DataSerializers.STRING);
    private static final DataParameter<String> variant = makeStat(DataSerializers.STRING);

    public int getTameness() {
        return entityData.get(tameness);
    }

    public void setTameness(int tameness) {
        entityData.set(this.tameness, tameness);
    }

    @Override
    public void setBaby(boolean childZombie) {
        super.setBaby(childZombie);
    }

    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn, 0.333);
    }


    public String getBreed() {
        return entityData.get(breed_data);
    }

    protected void registerGoals() {
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

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isBaby() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
    }

    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    @Override
    public double getMaleToFemaleChance() {
        return 0.333;
    }

    private Biome getBiome() {
        int x = MathHelper.floor(this.getX());
        int z = MathHelper.floor(this.getZ());
        return this.level.getBiome(new BlockPos(x, 0, z));
    }

    public static DataParameter makeStat(IDataSerializer dataSerializer) {
        return EntityDataManager.defineId(HotChickenEntity.class, dataSerializer);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (entityData.get(breed_data).equals("not_set")) {
            ChickenBreed breed = ChickenBreed.JUNGLEFOWL;
            this.breed = breed;

            entityData.set(breed_data, breed.toString());
            entityData.set(chick_type, breed.randomChickIndex());
        }
        if (entityData.get(variant).equals("not_set")) {
            setVariant(breed.randomVariant());
        }

        if (!getMainHandItem().isEmpty()) {
            int timer = entityData.get(egg_timer) + 1;
            if (timer < getMaxEggTimer() + 1) {
                entityData.set(egg_timer, timer);
            }
        }
    }

    public int getMaxEggTimer() {
        return 200;
    }


    public int getEggTimer() {
        return entityData.get(egg_timer);
    }

    public ChickenStats getStats() {
        return new ChickenStats(
                entityData.get(carcass_quality),
                entityData.get(growth_rate),
                entityData.get(egg_speed)
        );
    }

    public void setStats(int carcass_quality, int growth_rate, int egg_speed) {
        entityData.set(this.carcass_quality, carcass_quality);
        entityData.set(this.growth_rate, growth_rate);
        entityData.set(this.egg_speed, egg_speed);
    }

    public void setStats(ChickenStats stats) {
        entityData.set(this.carcass_quality, stats.carcass_quality);
        entityData.set(this.growth_rate, stats.growth_rate);
        entityData.set(this.egg_speed, stats.egg_speed);
    }


    public void setVariant(String v) {
        entityData.set(this.variant, v);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("egg_timer", entityData.get(egg_timer));
        compound.putInt("tameness", entityData.get(tameness));
        compound.putInt("carcass_quality", entityData.get(carcass_quality));
        compound.putInt("growth_rate", entityData.get(growth_rate));
        compound.putInt("egg_speed", entityData.get(egg_speed));
        compound.putString("breed", breed.toString());
        compound.putString("variant", entityData.get(variant));
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("carcass_quality")) {
            entityData.set(egg_timer, compound.getInt("egg_timer"));
            entityData.set(tameness, compound.getInt("tameness"));
            entityData.set(carcass_quality, compound.getInt("carcass_quality"));
            entityData.set(growth_rate, compound.getInt("growth_rate"));
            entityData.set(egg_speed, compound.getInt("egg_speed"));
            entityData.set(variant, compound.getString("variant"));
            entityData.set(chick_type, compound.getInt("chick_type"));
        }
        if (compound.contains("breed")) {
            String breed = compound.getString("breed");
            this.breed = ChickenBreed.valueOf(breed);
            entityData.set(breed_data, this.breed.toString());
        }
    }


    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(egg_timer, 0);
        this.entityData.define(chick_type, 0);
        this.entityData.define(carcass_quality, 1);
        this.entityData.define(growth_rate, 2);
        this.entityData.define(egg_speed, 3);
        this.entityData.define(variant, "not_set");
        this.entityData.define(breed_data, "not_set");
        this.entityData.define(tameness, 50);
    }


    public String getVariant() {
        return entityData.get(variant);
    }

    public int getChildType() {
        return entityData.get(chick_type);
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

    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity animal) {


        HotChickenEntity other = (HotChickenEntity) animal;

        if (!this.getMainHandItem().isEmpty()) {
            world.broadcastEntityEvent(this, (byte) 19);
            return;
        }

        ItemStack stack = new ItemStack(ItemRegistry.WHITE_EGG.get());

        if (other.breed == this.breed) {
            HotEggItem.setBreed(stack, this.breed.toString());
            boolean isMotherOrFathers = getRandom().nextFloat() < 0.6;
            if (isMotherOrFathers) {
                HotEggItem.setVariant(stack, isMotherOrFathers ? this.getVariant() : other.getVariant());
            }
        } else {
            HotEggItem.setBreed(stack, getRandom().nextFloat() < 0.5 ? this.breed.toString() : other.breed.toString());
        }

        ChickenStats stats = this.getStats().average(other.getStats()).mutate(0.15);
        HotEggItem.setStats(stack, stats);
        int avgtmness = ((getTameness() + other.getTameness())) / 2;
        int tameness = (int) (avgtmness * 1.1);
        if (tameness < 80) {
            HotEggItem.setBreed(stack, ChickenBreed.JUNGLEFOWL.toString());
        }
        if (tameness > 80 && avgtmness <= 80) {
            Biome biome = getBiome();
            System.out.println("biome = " + biome);
            ChickenBreed breed = ChickenBreed.randomBasedOnBiome(biome);
            HotEggItem.setStats(stack, breed.stats);
            HotEggItem.setBreed(stack, breed.toString());
        }
        HotEggItem.setTameness(stack, tameness);


        this.setItemInHand(Hand.MAIN_HAND, stack);

        this.setAge(6000);
        animal.setAge(6000);
        this.resetLove();
        animal.resetLove();

        world.broadcastEntityEvent(this, (byte) 18);
        world.addFreshEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
    }


    public void setEggTimer(int i) {
        entityData.set(egg_timer, i);
    }

    public void setBreed(ChickenBreed breed) {
        this.breed = breed;
        entityData.set(breed_data, this.breed.toString());
    }
}
