package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.CowBreeds;
import com.github.kmfisk.hotchicks.entity.goal.LivestockBirthGoal;
import com.github.kmfisk.hotchicks.entity.goal.LowStatsAttackGoal;
import com.github.kmfisk.hotchicks.entity.stats.CowStats;
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
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

import javax.annotation.Nullable;

public class HotCowEntity extends LivestockEntity {
    public static final Tags.IOptionalNamedTag<Item> COW_FOODS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "cow_foods"));
    private LowStatsAttackGoal<PlayerEntity> lowStatsAttackGoal;

    public HotCowEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.lowStatsAttackGoal = new LowStatsAttackGoal<>(this, PlayerEntity.class, 40);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new LivestockBirthGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, Ingredient.of(COW_FOODS)));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0F, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(7, lowStatsAttackGoal);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_QUALITY, 3);
        this.entityData.define(MILK_YIELD, 2);
        this.entityData.define(AVAILABLE_MILK, 0);
        this.entityData.define(GESTATION_TIMER, 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        if (nbt != null && nbt.contains("VillageSpawn") && nbt.getInt("VillageSpawn") != 0) initFromVillageSpawn();
        else {
            entityData = super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
            setStats(new CowStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)));
        }
        return entityData;
    }

    @Override
    public float getMaleRatio() {
        return 0.25F;
    }

    @Override
    public int getMaxVariants() {
        return CowBreeds.MAX_VARIANTS;
    }

    @Override
    public int getMaxCareStat() {
        return 16;
    }

    @Override
    public int getHungerDepletion() {
        return HotChicksConfig.hungerDepletion.get() / 4;
    }

    @Override
    public int getThirstDepletion() {
        return HotChicksConfig.thirstDepletion.get() / 4;
    }

    @Override
    public boolean isEdibleFood(ItemStack stack) {
        return Ingredient.of(COW_FOODS).test(stack);
    }

    @Override
    public String getReadableBreed() {
        return getBreedFromVariant().getLocalizedName().getString();
    }

    public void setStats(CowStats stats) {
        setTameness(stats.tameness);
        setCarcassQuality(stats.carcassQuality);
        setHideQuality(stats.hideQuality);
        setGrowthRate(stats.growthRate);
        setMilkYield(stats.milkYield);
    }

    @Override
    public CowStats getStats() {
        return new CowStats(
                getTameness(),
                getCarcassQuality(),
                getHideQuality(),
                getGrowthRate(),
                getMilkYield()
        );
    }

    @Override
    public void initFromVillageSpawn() {
        setSex(Sex.fromBool(random.nextFloat() <= getMaleRatio()));
        setVariant(CowBreeds.randomBasedOnBiome(random, getBiome()));
        setStats(getBreedFromVariant().getStats());
    }

    @Override
    public void initByBreed(String breed) {
        CowBreeds cowBreeds = CowBreeds.valueOf(breed.toUpperCase());
        switch (cowBreeds) {
            default:
            case AUROCHS:
                setVariant(0);
                break;
            case ANGUS:
                setVariant(random.nextInt(CowBreeds.ANGUS.getVariantCountOfBreed()) + 1);
                break;
            case BRAHMA:
                setVariant(random.nextInt(CowBreeds.BRAHMA.getVariantCountOfBreed()) + 3);
                break;
            case BROWN_SWISS:
                setVariant(8);
                break;
            case GUERNSEY:
                setVariant(random.nextInt(CowBreeds.GUERNSEY.getVariantCountOfBreed()) + 9);
                break;
            case HEREFORD:
                setVariant(random.nextInt(CowBreeds.HEREFORD.getVariantCountOfBreed()) + 11);
                break;
            case HIGHLAND:
                setVariant(random.nextInt(CowBreeds.HIGHLAND.getVariantCountOfBreed()) + 15);
                break;
            case HOLSTEIN:
                setVariant(random.nextInt(CowBreeds.HOLSTEIN.getVariantCountOfBreed()) + 19);
                break;
            case JERSEY:
                setVariant(23);
                break;
            case LAKENVELDER:
                setVariant(random.nextInt(CowBreeds.LAKENVELDER.getVariantCountOfBreed()) + 24);
                break;
            case LONGHORN:
                setVariant(random.nextInt(CowBreeds.LONGHORN.getVariantCountOfBreed()) + 26);
                break;
        }

        setStats(cowBreeds.getStats());
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideQuality", getHideQuality());
        nbt.putInt("MilkYield", getMilkYield());
        nbt.putInt("AvailableMilk", getAvailableMilk());
        if (!getSex().toBool()) nbt.putInt("Gestation", getGestationTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        setHideQuality(nbt.getInt("HideQuality"));
        setMilkYield(nbt.getInt("MilkYield"));
        setAvailableMilk(nbt.getInt("AvailableMilk"));
        if (!getSex().toBool()) setGestationTimer(nbt.getInt("Gestation"));
    }

    public CowBreeds getBreedFromVariant() {
        if (getVariant() == 0) return CowBreeds.AUROCHS;
        if (getVariant() <= 2) return CowBreeds.ANGUS;
        if (getVariant() <= 7) return CowBreeds.BRAHMA;
        if (getVariant() == 8) return CowBreeds.BROWN_SWISS;
        if (getVariant() <= 10) return CowBreeds.GUERNSEY;
        if (getVariant() <= 14) return CowBreeds.HEREFORD;
        if (getVariant() <= 18) return CowBreeds.HIGHLAND;
        if (getVariant() <= 22) return CowBreeds.HOLSTEIN;
        if (getVariant() == 23) return CowBreeds.JERSEY;
        if (getVariant() <= 25) return CowBreeds.LAKENVELDER;
        if (getVariant() <= 30) return CowBreeds.LONGHORN;

        return CowBreeds.AUROCHS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level.isClientSide && isAlive()) {
            if (lowStatsAttackGoal.getCooldown() > 0) lowStatsAttackGoal.decrementCooldown();
        }
    }

    @Override
    public float getScale() {
        switch (getBreedFromVariant()) {
            case AUROCHS:
                return isBaby() ? 0.7F : 1.4F;
            case HEREFORD:
            case HOLSTEIN:
            case LONGHORN:
                return isBaby() ? 0.6F : 1.2F;
            default:
            case ANGUS:
            case BRAHMA:
            case BROWN_SWISS:
            case HIGHLAND:
            case LAKENVELDER:
                return super.getScale();
            case GUERNSEY:
                return isBaby() ? 0.45F : 0.9F;
            case JERSEY:
                return isBaby() ? 0.35F : 0.7F;
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) { // todo
        return super.getStandingEyeHeight(pose, size);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.WHEAT;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return HotEntities.COW.get().create(world);
    }

    @Override
    protected void onOffspringSpawnedFromEgg(PlayerEntity player, MobEntity entity) {
        if (entity instanceof HotCowEntity) {
            HotCowEntity child = (HotCowEntity) entity;

            boolean colorMorph = random.nextFloat() <= 0.1;
            CowBreeds breed1 = getBreedFromVariant();
            CowStats stats = (CowStats) getStats().average(getStats(), true).mutate(0.2);

            if (stats.tameness < 75) child.setVariant(0);
            else {
                int childVariant;
                CowBreeds childBreed;

                if (breed1 != CowBreeds.AUROCHS)
                    childVariant = colorMorph ? CowBreeds.randomFromBreed(random, breed1) : getVariant();
                else {
                    if (random.nextFloat() <= 0.8F)
                        childVariant = CowBreeds.randomBasedOnBiome(random, getBiome());
                    else childVariant = random.nextInt(getMaxVariants()) + 1;
                }

                child.setVariant(childVariant);
                childBreed = child.getBreedFromVariant();

                if (childBreed != CowBreeds.AUROCHS && random.nextFloat() <= 0.8F)
                    stats = (CowStats) stats.average(childBreed.getStats(), false);

            }

            child.setBaby(true);
            child.setStats(stats);
            child.setSex(Sex.fromBool(random.nextBoolean()));
        }
    }

    @Override
    public void createChild(ServerWorld level, LivestockEntity livestockEntity) {
        if (livestockEntity instanceof HotCowEntity) {
            HotCowEntity father = (HotCowEntity) livestockEntity;
            HotCowEntity child = (HotCowEntity) getBreedOffspring(level, father);
            final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, father, child);
            final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
            child = (HotCowEntity) event.getChild();
            if (cancelled) {
                setAge(6000);
                father.setAge(6000);
                resetLove();
                father.resetLove();
                return;
            }
            if (child != null) {
                int breedingCooldown = HotChicksConfig.breedingCooldown.get();
                setAge(breedingCooldown);
                father.setAge(6000);
                resetLove();
                father.resetLove();
                child.setBaby(true);

                boolean inheritMotherGenes = random.nextFloat() <= 0.6;
                boolean colorMorph = random.nextFloat() <= 0.1;
                CowBreeds breed1 = getBreedFromVariant();
                CowBreeds breed2 = father.getBreedFromVariant();
                CowStats stats = (CowStats) getStats().average(father.getStats(), true).mutate(0.2);

                if (stats.tameness < 75) child.setVariant(0);
                else {
                    int childVariant;
                    CowBreeds childBreed;

                    if (breed1 != CowBreeds.AUROCHS && breed2 != CowBreeds.AUROCHS) {
                        if (breed1.equals(breed2)) {
                            childBreed = breed1;
                            childVariant = inheritMotherGenes ? getVariant() : father.getVariant();
                        } else {
                            if (inheritMotherGenes) {
                                childBreed = breed1;
                                childVariant = getVariant();
                            } else {
                                childBreed = breed2;
                                childVariant = father.getVariant();
                            }
                        }
                        if (colorMorph) childVariant = CowBreeds.randomFromBreed(random, childBreed);

                    } else {
                        if (breed1 != CowBreeds.AUROCHS && inheritMotherGenes) {
                            childBreed = breed1;
                            childVariant = colorMorph ? CowBreeds.randomFromBreed(random, childBreed) : getVariant();
                        } else if (breed2 != CowBreeds.AUROCHS && !inheritMotherGenes) {
                            childBreed = breed2;
                            childVariant = colorMorph ? CowBreeds.randomFromBreed(random, childBreed) : father.getVariant();
                        } else {
                            if (random.nextFloat() <= 0.8F)
                                childVariant = CowBreeds.randomBasedOnBiome(random, getBiome());
                            else childVariant = random.nextInt(getMaxVariants()) + 1;
                        }
                    }

                    child.setVariant(childVariant);
                    childBreed = child.getBreedFromVariant();

                    if (childBreed != CowBreeds.AUROCHS && random.nextFloat() <= 0.8F)
                        stats = (CowStats) stats.average(childBreed.getStats(), false);

                }

                child.setStats(stats);
                child.setSex(Sex.fromBool(random.nextBoolean()));

                CompoundNBT childNBT = new CompoundNBT();
                child.save(childNBT);

                children.add(childNBT);
                setGestationTimer(HotChicksConfig.gestationSpeed.get());

                ServerPlayerEntity serverplayerentity = getLoveCause();
                if (serverplayerentity == null && father.getLoveCause() != null)
                    serverplayerentity = father.getLoveCause();
                if (serverplayerentity != null) {
                    serverplayerentity.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this, father, child);
                }

                level.broadcastEntityEvent(this, (byte) 18);
                if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                    level.addFreshEntity(new ExperienceOrbEntity(level, getX(), getY(), getZ(), random.nextInt(7) + 1));
            }
        }
    }

    @Override
    public void spawnChildrenFromPregnancy(ServerWorld level) {
        super.spawnChildrenFromPregnancy(level);
        setAvailableMilk(getStats().getMilkYieldForStat());
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!isBaby() && stack.getItem() == Items.BUCKET) {
            if (getSex() == Sex.FEMALE && getAvailableMilk() > 0) {
                player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                ItemStack filledResult = DrinkHelper.createFilledResult(stack, player, Items.MILK_BUCKET.getDefaultInstance());
                player.setItemInHand(hand, filledResult);
                setAvailableMilk(Math.max(0, getAvailableMilk() - 1));
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }
}
