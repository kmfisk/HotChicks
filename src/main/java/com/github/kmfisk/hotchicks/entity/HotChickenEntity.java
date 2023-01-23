package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.block.entity.NestTileEntity;
import com.github.kmfisk.hotchicks.client.HotSounds;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.entity.goal.*;
import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {
    public static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(HotItems.CORN.get(), Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public int remainingCooldownBeforeLocatingNewNest = 0;
    private BlockPos nestPos = null;
    public FindNestGoal goToNestGoal;

    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new LayEggsGoal(this));
        this.goalSelector.addGoal(3, new ChickenBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LivestockAvoidPlayerGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new UpdateNestGoal(this));
        this.goToNestGoal = new FindNestGoal(this, 16);
        this.goalSelector.addGoal(5, this.goToNestGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EGG_SPEED, 3);
        this.entityData.define(EGG_TIMER, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
        setStats(new ChickenStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(5)));
        return entityData;
    }

    @Override
    public float getMaleRatio() {
        return 0.33F;
    }

    @Override
    public int getMaxVariants() {
        return ChickenBreeds.MAX_VARIANTS;
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
        setTameness(stats.tameness);
        setCarcassQuality(stats.carcassQuality);
        setGrowthRate(stats.growthRate);
        setEggSpeed(stats.eggSpeed);
    }

    public ChickenStats getStats() {
        return new ChickenStats(
                getTameness(),
                getCarcassQuality(),
                getGrowthRate(),
                getEggSpeed()
        );
    }

    public int getMaxEggTimer() {
        return getStats().getMaxEggSpeed();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (hasNest()) nbt.put("NestPos", NBTUtil.writeBlockPos(getNestPos()));
        nbt.putInt("EggSpeed", this.getEggSpeed());
        nbt.putInt("EggTimer", this.getEggTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        nestPos = null;
        if (nbt.contains("NestPos")) nestPos = NBTUtil.readBlockPos(nbt.getCompound("NestPos"));
        super.readAdditionalSaveData(nbt);
        this.setEggSpeed(nbt.getInt("EggSpeed"));
        this.setEggTimer(nbt.getInt("EggTimer"));
    }

    public ChickenBreeds getBreedFromVariant(int variant) {
        if (variant == 0) return ChickenBreeds.JUNGLEFOWL;
        if (variant <= 7) return ChickenBreeds.AMERAUCANA;
        if (variant == 8) return ChickenBreeds.BARRED_ROCK;
        if (variant == 9) return ChickenBreeds.LEGHORN;
        if (variant <= 13) return ChickenBreeds.MARANS;
        if (variant <= 20) return ChickenBreeds.OLIVE_EGGER;
        if (variant <= 24) return ChickenBreeds.ORPINGTON;
        if (variant <= 27) return ChickenBreeds.RHODE_ISLAND_RED;
        if (variant <= 32) return ChickenBreeds.SILKIE;

        return ChickenBreeds.JUNGLEFOWL;
    }

    public boolean wantsToLayEggs() {
        return getSex() == Sex.FEMALE && getMainHandItem() != ItemStack.EMPTY && getEggTimer() >= getMaxEggTimer();
    }

    public boolean doesNestHaveSpace(BlockPos nestPos) {
        TileEntity tileEntity = level.getBlockEntity(nestPos);
        if (tileEntity instanceof NestTileEntity)
            return ((NestTileEntity) tileEntity).getItems().contains(ItemStack.EMPTY);
            //return ((NestTileEntity) tileEntity).getItems().size() < 5; // todo
        else return false;
    }

    public boolean hasNest() {
        return nestPos != null;
    }

    public void setNestPos(@Nullable BlockPos pos) {
        nestPos = pos;
    }

    @Nullable
    public BlockPos getNestPos() {
        return nestPos;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level.isClientSide) {
            if (remainingCooldownBeforeLocatingNewNest > 0) --remainingCooldownBeforeLocatingNewNest;
            if (tickCount % 20 == 0 && !isNestValid())
                nestPos = null;
        }
    }

    private boolean isNestValid() {
        if (!hasNest()) return false;
        else {
            TileEntity tileEntity = level.getBlockEntity(nestPos);
            return tileEntity instanceof NestTileEntity;
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return this.isBaby() ? size.height * 0.85F : size.height * 0.92F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == HotItems.CORN.get();
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if (!getMainHandItem().isEmpty() && getSex() == Sex.FEMALE) {
            int eggTimer = this.getEggTimer();
            if (eggTimer < this.getMaxEggTimer()) {
                ++eggTimer;
                setEggTimer(eggTimer);
            } /*else setEggTimer(0);*/
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return null;
    }

    @Override
    public boolean canMate(AnimalEntity mate) {
        if (!(mate instanceof HotChickenEntity)) return false;
        else {
            HotChickenEntity chicken = (HotChickenEntity) mate;
            if (chicken == this)
                return false;
            else
                return this.isInLove() && chicken.isInLove() && chicken.getSex() != this.getSex();
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity animal) {
        HotChickenEntity parent2 = (HotChickenEntity) animal;
        boolean inheritMotherGenes = this.random.nextFloat() <= 0.6;
        boolean colorMorph = this.random.nextFloat() <= 0.1;

        if (!this.getMainHandItem().isEmpty()) {
            world.broadcastEntityEvent(this, (byte) 19);
            return;
        }

        ItemStack stack = HotItems.WHITE_EGG.get().getDefaultInstance();
        CompoundNBT stackTag = stack.getOrCreateTag();
        ChickenBreeds breed1 = this.getBreedFromVariant(this.getVariant());
        ChickenBreeds breed2 = parent2.getBreedFromVariant(parent2.getVariant());
        ChickenStats stats = (ChickenStats) this.getStats().average(parent2.getStats(), true).mutate(0.2);
        if (stats.tameness < 85) {
            stackTag.putString("Breed", ChickenBreeds.JUNGLEFOWL.toString());
            stackTag.putInt("Variant", 0);

        } else {
            int chickVariant;
            ChickenBreeds chickBreed;

            if (breed1 != ChickenBreeds.JUNGLEFOWL && breed2 != ChickenBreeds.JUNGLEFOWL) {
                if (breed1.equals(breed2)) {
                    chickBreed = breed1;
                    chickVariant = inheritMotherGenes ? this.getVariant() : parent2.getVariant();
                } else {
                    if (inheritMotherGenes) {
                        chickBreed = breed1;
                        chickVariant = this.getVariant();
                    } else {
                        chickBreed = breed2;
                        chickVariant = parent2.getVariant();
                    }
                }
                if (colorMorph)
                    chickVariant = ChickenBreeds.randomFromBreed(this.random, chickBreed);

            } else {
                if (breed1 != ChickenBreeds.JUNGLEFOWL && inheritMotherGenes) {
                    chickBreed = breed1;
                    chickVariant = colorMorph ? ChickenBreeds.randomFromBreed(this.random, chickBreed) : this.getVariant();
                } else if (breed2 != ChickenBreeds.JUNGLEFOWL && !inheritMotherGenes) {
                    chickBreed = breed2;
                    chickVariant = colorMorph ? ChickenBreeds.randomFromBreed(this.random, chickBreed) : parent2.getVariant();
                } else {
                    if (this.random.nextFloat() <= 0.8) {
                        Biome biome = this.getBiome();
                        chickVariant = ChickenBreeds.randomBasedOnBiome(this.random, biome);
                    } else
                        chickVariant = this.random.nextInt(this.getMaxVariants()) + 1;
                    chickBreed = this.getBreedFromVariant(chickVariant);
                }
            }

            if (chickBreed != ChickenBreeds.JUNGLEFOWL && this.random.nextFloat() <= 0.8)
                stats = (ChickenStats) stats.average(chickBreed.stats, false);

            stackTag.putString("Breed", chickBreed.toString());
            stackTag.putInt("Variant", chickVariant);
            stackTag.putInt("Tameness", stats.tameness);
            stackTag.putInt("CarcassQuality", stats.carcassQuality);
            stackTag.putInt("GrowthRate", stats.growthRate);
            stackTag.putInt("EggSpeed", stats.eggSpeed);
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
    public boolean causeFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 480;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getSex() == Sex.MALE && this.random.nextInt(10) == 0)
            return HotSounds.ROOSTER_CROW.get();
        return HotSounds.CHICKEN_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return HotSounds.CHICKEN_HURT.get();
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
        boolean flag = entity.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, entity);
            this.playSound(this.getSex() == Sex.MALE ? HotSounds.ROOSTER_ATTACK.get() : HotSounds.CHICKEN_ATTACK.get(), 1.0F, 1.0F);
        }

        return flag;
    }
}
