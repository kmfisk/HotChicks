package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.entity.NestTileEntity;
import com.github.kmfisk.hotchicks.client.HotSounds;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.entity.goal.*;
import com.github.kmfisk.hotchicks.entity.stats.ChickenStats;
import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {
    public static final Tags.IOptionalNamedTag<Item> CHICKEN_FOODS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "chicken_foods"));
    public static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(HotChickenEntity.class, DataSerializers.INT);
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
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, Ingredient.of(CHICKEN_FOODS)));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new UpdateNestGoal(this, 8, 2));
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
        setStats(new ChickenStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(3)));
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

    @Override
    public int getMaxHunger() {
        return 6;
    }

    @Override
    public boolean isEdibleFood(ItemStack stack) {
        return Ingredient.of(CHICKEN_FOODS).test(stack);
    }

    @Override
    public String getReadableBreed() {
        return getBreedFromVariant().toString();
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

    @Override
    public ChickenStats getStats() {
        return new ChickenStats(
                getTameness(),
                getCarcassQuality(),
                getGrowthRate(),
                getEggSpeed()
        );
    }

    public int getMaxEggTimer() {
        return getStats().getEggSpeedForStat();
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

    public ChickenBreeds getBreedFromVariant() {
        if (getVariant() == 0) return ChickenBreeds.JUNGLEFOWL;
        if (getVariant() <= 7) return ChickenBreeds.AMERAUCANA;
        if (getVariant() == 8) return ChickenBreeds.BARRED_ROCK;
        if (getVariant() == 9) return ChickenBreeds.LEGHORN;
        if (getVariant() <= 13) return ChickenBreeds.MARANS;
        if (getVariant() <= 20) return ChickenBreeds.OLIVE_EGGER;
        if (getVariant() <= 24) return ChickenBreeds.ORPINGTON;
        if (getVariant() <= 27) return ChickenBreeds.RHODE_ISLAND_RED;
        if (getVariant() <= 32) return ChickenBreeds.SILKIE;

        return ChickenBreeds.JUNGLEFOWL;
    }

    public boolean wantsToLayEggs() {
        return getSex() == Sex.FEMALE && getMainHandItem() != ItemStack.EMPTY && getEggTimer() >= getMaxEggTimer();
    }

    public boolean doesNestHaveSpace(BlockPos nestPos) {
        TileEntity tileEntity = level.getBlockEntity(nestPos);
        if (tileEntity instanceof NestTileEntity)
            return ((NestTileEntity) tileEntity).getItems().stream().anyMatch(ItemStack::isEmpty);
        else return false;
    }

    public boolean isValidNestBlock(BlockPos pos) {
        if (level.getBlockState(pos).is(HotBlocks.NEST_BOX.get())) return doesNestHaveSpace(pos);
        return level.getBlockState(pos).is(HotBlocks.NEST.get()) && doesNestHaveSpace(pos) && level.isEmptyBlock(pos.above());
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
            if (tickCount % 20 == 0 && !isValidNestBlock())
                nestPos = null;
        }
    }

    private boolean isValidNestBlock() {
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
        return HotEntities.CHICKEN.get().create(world);
    }

    @Override
    public boolean canFallInLove() {
        return getMainHandItem().isEmpty() && super.canFallInLove();
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
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity entity) {
        if (entity instanceof HotChickenEntity) createChild(world, (HotChickenEntity) entity);
    }

    public void createChild(ServerWorld world, HotChickenEntity parent) {
        HotChickenEntity child = (HotChickenEntity) getBreedOffspring(world, parent);
        final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, parent, child);
        final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
        child = (HotChickenEntity) event.getChild();
        if (cancelled) {
            setAge(6000);
            parent.setAge(6000);
            resetLove();
            parent.resetLove();
            return;
        }

        if (child != null) {
            ServerPlayerEntity serverplayerentity = getLoveCause();
            if (serverplayerentity == null && parent.getLoveCause() != null) serverplayerentity = parent.getLoveCause();
            if (serverplayerentity != null) {
                serverplayerentity.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this, parent, child);
            }

            int breedingCooldown = HotChicksConfig.breedingCooldown.get();
            setAge(getSex() == Sex.MALE ? 6000 : breedingCooldown);
            parent.setAge(parent.getSex() == Sex.MALE ? 6000 : breedingCooldown);
            resetLove();
            parent.resetLove();

            if (!getMainHandItem().isEmpty()) {
                world.broadcastEntityEvent(this, (byte) 19);
                return;
            }

            boolean inheritMotherGenes = random.nextFloat() <= 0.6;
            boolean colorMorph = random.nextFloat() <= 0.1;
            ChickenBreeds breed1 = getBreedFromVariant();
            ChickenBreeds breed2 = parent.getBreedFromVariant();
            ChickenStats stats = (ChickenStats) getStats().average(parent.getStats(), true).mutate(0.2);

            if (stats.tameness < 85) child.setVariant(0);
            else {
                int childVariant;
                ChickenBreeds childBreed;

                if (breed1 != ChickenBreeds.JUNGLEFOWL && breed2 != ChickenBreeds.JUNGLEFOWL) {
                    if (breed1.equals(breed2)) {
                        childBreed = breed1;
                        childVariant = inheritMotherGenes ? getVariant() : parent.getVariant();
                    } else {
                        if (inheritMotherGenes) {
                            childBreed = breed1;
                            childVariant = getVariant();
                        } else {
                            childBreed = breed2;
                            childVariant = parent.getVariant();
                        }
                    }
                    if (colorMorph) childVariant = ChickenBreeds.randomFromBreed(random, childBreed);

                } else {
                    if (breed1 != ChickenBreeds.JUNGLEFOWL && inheritMotherGenes) {
                        childBreed = breed1;
                        childVariant = colorMorph ? ChickenBreeds.randomFromBreed(random, childBreed) : getVariant();
                    } else if (breed2 != ChickenBreeds.JUNGLEFOWL && !inheritMotherGenes) {
                        childBreed = breed2;
                        childVariant = colorMorph ? ChickenBreeds.randomFromBreed(random, childBreed) : parent.getVariant();
                    } else {
                        if (this.random.nextFloat() <= 0.8)
                            childVariant = ChickenBreeds.randomBasedOnBiome(random, getBiome());
                        else childVariant = random.nextInt(getMaxVariants()) + 1;
                    }
                }

                child.setVariant(childVariant);
                childBreed = child.getBreedFromVariant();

                if (childBreed != ChickenBreeds.JUNGLEFOWL && random.nextFloat() <= 0.8)
                    stats = (ChickenStats) stats.average(childBreed.getStats(), false);

            }

            child.setBaby(true);
            child.setStats(stats);
            child.setSex(Sex.fromBool(random.nextBoolean()));

            ItemStack stack = getBreedFromVariant().getEggColor().getDefaultInstance();
            CompoundNBT tags = stack.getOrCreateTag();
            child.save(tags);
            ResourceLocation key = EntityType.getKey(child.getType());
            tags.putString("id", key.toString());
            tags.putString("Breed", getReadableBreed());
            tags.putInt("TimeLeft", HotChicksConfig.hatchSpeed.get());
            stack.setTag(tags);
            setItemInHand(Hand.MAIN_HAND, stack);

            world.broadcastEntityEvent(this, (byte) 18);
            if (world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                world.addFreshEntity(new ExperienceOrbEntity(world, getX(), getY(), getZ(), random.nextInt(7) + 1));
        }
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
