package com.ryanhcode.hotchicks.entity;

import com.ryanhcode.hotchicks.entity.base.ChickenBreeds;
import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import com.ryanhcode.hotchicks.entity.goal.ChickenBreedGoal;
import com.ryanhcode.hotchicks.entity.goal.LayEggsGoal;
import com.ryanhcode.hotchicks.entity.stats.ChickenStats;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.item.HotItems;
import com.ryanhcode.hotchicks.registry.HotSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
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
    private static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, HotItems.CORN.get());

    public final int maxVariants = 33;

    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new LayEggsGoal(this));
        this.goalSelector.addGoal(3, new ChickenBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new MeleeAttackGoal(this, 1.4F, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EGG_SPEED, 3);
        this.entityData.define(EGG_TIMER, 0);
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

    public void setStats(ChickenStats stats) {
        this.setTameness(stats.tameness);
        this.setCarcassQuality(stats.carcassQuality);
        this.setGrowthRate(stats.growthRate);
        this.setEggSpeed(stats.eggSpeed);
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
        compound.putInt("EggSpeed", this.getEggSpeed());
        compound.putInt("EggTimer", this.getEggTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setEggSpeed(compound.getInt("EggSpeed"));
        this.setEggTimer(compound.getInt("EggTimer"));
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setSex(random.nextInt(3) == 0);
        this.setVariant(0);
        this.setStats(new ChickenStats(random.nextInt(25) + random.nextInt(35), random.nextInt(4), random.nextInt(4), random.nextInt(8)));
        return spawnDataIn;
    }

    public ChickenBreeds getBreedFromVariant(int variant) {
        switch (variant) {
            default:
            case 0:
                return ChickenBreeds.JUNGLEFOWL;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return ChickenBreeds.AMERAUCANA;
            case 8:
                return ChickenBreeds.BARRED_ROCK;
            case 9:
                return ChickenBreeds.LEGHORN;
            case 10:
            case 11:
            case 12:
            case 13:
                return ChickenBreeds.MARANS;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return ChickenBreeds.OLIVE_EGGER;
            case 21:
            case 22:
            case 23:
            case 24:
                return ChickenBreeds.ORPINGTON;
            case 25:
            case 26:
            case 27:
                return ChickenBreeds.RHODE_ISLAND_RED;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return ChickenBreeds.SILKIE;
        }
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
    public void baseTick() {
        super.baseTick();

        if (!getMainHandItem().isEmpty()) {
            int timer = this.getEggTimer();
            if (timer < this.getMaxEggTimer()) {
                ++timer;
                this.setEggTimer(timer);
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return null;
    }

    @Override
    public boolean canMate(AnimalEntity otherAnimal) {
        if (!(otherAnimal instanceof HotChickenEntity)) return false;
        else {
            HotChickenEntity chicken = (HotChickenEntity) otherAnimal;
            if (chicken == this)
                return false;
            else
                return this.isInLove() && otherAnimal.isInLove() && chicken.getSex() != this.getSex();
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity animal) {
        HotChickenEntity parent2 = (HotChickenEntity) animal;
        boolean inheritMotherGenes = this.random.nextFloat() <= 0.6;

        if (!this.getMainHandItem().isEmpty()) {
            world.broadcastEntityEvent(this, (byte) 19);
            return;
        }

        ItemStack stack = new ItemStack(HotItems.WHITE_EGG.get());
        ChickenBreeds breed1 = this.getBreedFromVariant(this.getVariant());
        ChickenBreeds breed2 = parent2.getBreedFromVariant(parent2.getVariant());
        ChickenStats stats = (ChickenStats) this.getStats().average(parent2.getStats()).mutate(0.2);
        HotEggItem.setChickenStats(stack, stats);
        if (stats.tameness < 85) {
            HotEggItem.setBreed(stack, ChickenBreeds.JUNGLEFOWL.toString());
            HotEggItem.setVariant(stack, 0);

        } else {
            if (breed1 != ChickenBreeds.JUNGLEFOWL && breed2 != ChickenBreeds.JUNGLEFOWL) {
                if (breed1.equals(breed2)) {
                    HotEggItem.setBreed(stack, breed1.toString());
                    HotEggItem.setVariant(stack, inheritMotherGenes ? this.getVariant() : parent2.getVariant());
                } else {
                    if (inheritMotherGenes) {
                        HotEggItem.setBreed(stack, breed1.toString());
                        HotEggItem.setVariant(stack, this.getVariant());
                    } else {
                        HotEggItem.setBreed(stack, breed2.toString());
                        HotEggItem.setVariant(stack, parent2.getVariant());
                    }
                }

            } else {
                if (breed1 != ChickenBreeds.JUNGLEFOWL && inheritMotherGenes) {
                    HotEggItem.setBreed(stack, breed1.toString());
                    HotEggItem.setVariant(stack, this.getVariant());
                } else if (breed2 != ChickenBreeds.JUNGLEFOWL && !inheritMotherGenes) {
                    HotEggItem.setBreed(stack, breed2.toString());
                    HotEggItem.setVariant(stack, parent2.getVariant());
                } else {
                    Biome biome = this.getBiome();
                    System.out.println("biome = " + biome);
                    int variant = ChickenBreeds.randomBasedOnBiome(random, biome);
                    ChickenBreeds breed = this.getBreedFromVariant(variant);
                    HotEggItem.setBreed(stack, breed.toString());
                    HotEggItem.setVariant(stack, variant);
                }
            }
        }

        this.setItemInHand(Hand.MAIN_HAND, stack);

        this.setAge(6000); // todo mate timers
        animal.setAge(6000);
        this.resetLove();
        animal.resetLove();

        world.broadcastEntityEvent(this, (byte) 18);
        world.addFreshEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.random.nextInt(7) + 1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 19) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else
            super.handleEntityEvent(id);
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getSex() == Sex.MALE && this.random.nextInt(4) == 0)
            return HotSounds.ROOSTER_CROW;
        return HotSounds.CHICKEN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return HotSounds.CHICKEN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        boolean flag = entity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, entity);
            this.playSound(this.getSex() == Sex.MALE ? HotSounds.ROOSTER_ATTACK : HotSounds.CHICKEN_ATTACK, 1.0F, 1.0F);
        }

        return flag;
    }
}
