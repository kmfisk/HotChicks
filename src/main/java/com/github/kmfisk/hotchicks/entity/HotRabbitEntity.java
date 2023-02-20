package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.goal.LivestockAvoidPlayerGoal;
import com.github.kmfisk.hotchicks.entity.goal.LivestockBirthGoal;
import com.github.kmfisk.hotchicks.entity.goal.LivestockBreedGoal;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import net.minecraft.advancements.CriteriaTriggers;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

import javax.annotation.Nullable;

import static com.github.kmfisk.hotchicks.entity.base.RabbitBreeds.*;

public class HotRabbitEntity extends LivestockEntity {
    public static final Tags.IOptionalNamedTag<Item> RABBIT_FOODS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "rabbit_foods"));

    public HotRabbitEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new LivestockBirthGoal(this));
        this.goalSelector.addGoal(3, new LivestockBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LivestockAvoidPlayerGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, Ingredient.of(RABBIT_FOODS)));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_QUALITY, 3);
        this.entityData.define(LITTER_SIZE, 2);
        this.entityData.define(GESTATION_TIMER, 0);
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
        return RabbitBreeds.MAX_VARIANTS;
    }

    @Override
    public int getMaxCareStat() {
        return 6;
    }

    @Override
    public int getHungerDepletion() {
        return HotChicksConfig.hungerDepletion.get();
    }

    @Override
    public int getThirstDepletion() {
        return HotChicksConfig.thirstDepletion.get();
    }

    @Override
    public boolean isEdibleFood(ItemStack stack) {
        return Ingredient.of(RABBIT_FOODS).test(stack);
    }

    @Override
    public String getReadableBreed() {
        return getBreedFromVariant().getLocalizedName().getString();
    }

    public void setStats(RabbitStats stats) {
        setTameness(stats.tameness);
        setCarcassQuality(stats.carcassQuality);
        setHideQuality(stats.hideQuality);
        setGrowthRate(stats.growthRate);
        setLitterSize(stats.litterSize);
    }

    @Override
    public RabbitStats getStats() {
        return new RabbitStats(
                getTameness(),
                getCarcassQuality(),
                getHideQuality(),
                getGrowthRate(),
                getLitterSize()
        );
    }

    @Override
    public void initByBreed(String breed) {
        RabbitBreeds rabbitBreeds = RabbitBreeds.valueOf(breed.toUpperCase());
        switch (rabbitBreeds) {
            default:
            case COTTONTAIL:
                setVariant(0);
                break;
            case AMERICAN_CHINCHILLA:
                setVariant(random.nextInt(AMERICAN_CHINCHILLA.getVariantCountOfBreed()) + 1);
                break;
            case CALIFORNIA:
                setVariant(3);
                break;
            case DUTCH:
                setVariant(random.nextInt(DUTCH.getVariantCountOfBreed()) + 4);
                break;
            case FLEMISH_GIANT:
                setVariant(random.nextInt(FLEMISH_GIANT.getVariantCountOfBreed()) + 10);
                break;
            case NEW_ZEALAND:
                setVariant(random.nextInt(NEW_ZEALAND.getVariantCountOfBreed()) + 15);
                break;
            case REX:
                setVariant(random.nextInt(REX.getVariantCountOfBreed()) + 18);
                break;
        }

        setStats(rabbitBreeds.getStats());
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideQuality", getHideQuality());
        nbt.putInt("LitterSize", getLitterSize());
        if (!getSex().toBool()) nbt.putInt("Gestation", getGestationTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        setHideQuality(nbt.getInt("HideQuality"));
        setLitterSize(nbt.getInt("LitterSize"));
        if (!getSex().toBool()) setGestationTimer(nbt.getInt("Gestation"));
    }

    public RabbitBreeds getBreedFromVariant() {
        if (getVariant() == 0) return RabbitBreeds.COTTONTAIL;
        if (getVariant() <= 2) return RabbitBreeds.AMERICAN_CHINCHILLA;
        if (getVariant() == 3) return RabbitBreeds.CALIFORNIA;
        if (getVariant() <= 9) return RabbitBreeds.DUTCH;
        if (getVariant() <= 14) return FLEMISH_GIANT;
        if (getVariant() <= 17) return NEW_ZEALAND;
        if (getVariant() <= 26) return REX;

        return RabbitBreeds.COTTONTAIL;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return size.height * 0.65F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.CARROT;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
        return HotEntities.RABBIT.get().create(world);
    }

    @Override
    protected void onOffspringSpawnedFromEgg(PlayerEntity player, MobEntity entity) {
        if (entity instanceof HotRabbitEntity) {
            HotRabbitEntity child = (HotRabbitEntity) entity;

            boolean colorMorph = random.nextFloat() <= 0.1;
            RabbitBreeds breed1 = getBreedFromVariant();
            RabbitStats stats = (RabbitStats) getStats().average(getStats(), true).mutate(0.2);

            if (stats.tameness < 95) child.setVariant(0);
            else {
                int childVariant;
                RabbitBreeds childBreed;

                if (breed1 != RabbitBreeds.COTTONTAIL)
                    childVariant = colorMorph ? RabbitBreeds.randomFromBreed(random, breed1) : getVariant();
                else {
                    if (random.nextFloat() <= 0.8F)
                        childVariant = RabbitBreeds.randomBasedOnBiome(random, getBiome());
                    else childVariant = random.nextInt(getMaxVariants()) + 1;
                }

                child.setVariant(childVariant);
                childBreed = child.getBreedFromVariant();

                if (childBreed != RabbitBreeds.COTTONTAIL && random.nextFloat() <= 0.8F)
                    stats = (RabbitStats) stats.average(childBreed.getStats(), false);

            }

            child.setBaby(true);
            child.setStats(stats);
            child.setSex(Sex.fromBool(random.nextBoolean()));
        }
    }

    @Override
    public void createChild(ServerWorld level, LivestockEntity livestockEntity) {
        if (livestockEntity instanceof HotRabbitEntity) {
            HotRabbitEntity father = (HotRabbitEntity) livestockEntity;
            HotRabbitEntity child = (HotRabbitEntity) getBreedOffspring(level, father);
            final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, father, child);
            final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
            child = (HotRabbitEntity) event.getChild();
            if (cancelled) {
                setAge(6000);
                father.setAge(6000);
                resetLove();
                father.resetLove();
                return;
            }
            if (child != null) {
                ServerPlayerEntity serverplayerentity = getLoveCause();
                if (serverplayerentity == null && father.getLoveCause() != null)
                    serverplayerentity = father.getLoveCause();
                if (serverplayerentity != null) {
                    serverplayerentity.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this, father, child);
                }

                int breedingCooldown = HotChicksConfig.breedingCooldown.get();
                setAge(breedingCooldown);
                father.setAge(6000);
                resetLove();
                father.resetLove();
                child.setBaby(true);

                boolean inheritMotherGenes = random.nextFloat() <= 0.6;
                boolean colorMorph = random.nextFloat() <= 0.1;
                RabbitBreeds breed1 = getBreedFromVariant();
                RabbitBreeds breed2 = father.getBreedFromVariant();
                RabbitStats stats = (RabbitStats) getStats().average(father.getStats(), true).mutate(0.2);

                if (stats.tameness < 95) child.setVariant(0);
                else {
                    int childVariant;
                    RabbitBreeds childBreed;

                    if (breed1 != RabbitBreeds.COTTONTAIL && breed2 != RabbitBreeds.COTTONTAIL) {
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
                        if (colorMorph) childVariant = RabbitBreeds.randomFromBreed(random, childBreed);

                    } else {
                        if (breed1 != RabbitBreeds.COTTONTAIL && inheritMotherGenes) {
                            childBreed = breed1;
                            childVariant = colorMorph ? RabbitBreeds.randomFromBreed(random, childBreed) : getVariant();
                        } else if (breed2 != RabbitBreeds.COTTONTAIL && !inheritMotherGenes) {
                            childBreed = breed2;
                            childVariant = colorMorph ? RabbitBreeds.randomFromBreed(random, childBreed) : father.getVariant();
                        } else {
                            if (random.nextFloat() <= 0.8F)
                                childVariant = RabbitBreeds.randomBasedOnBiome(random, getBiome());
                            else childVariant = random.nextInt(getMaxVariants()) + 1;
                        }
                    }

                    child.setVariant(childVariant);
                    childBreed = child.getBreedFromVariant();

                    if (childBreed != RabbitBreeds.COTTONTAIL && random.nextFloat() <= 0.8F)
                        stats = (RabbitStats) stats.average(childBreed.getStats(), false);

                }

                child.setStats(stats);
                child.setSex(Sex.fromBool(random.nextBoolean()));

                CompoundNBT childNBT = new CompoundNBT();
                child.save(childNBT);

                children.add(childNBT);
                setGestationTimer(HotChicksConfig.gestationSpeed.get());

                level.broadcastEntityEvent(this, (byte) 18);
                if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                    level.addFreshEntity(new ExperienceOrbEntity(level, getX(), getY(), getZ(), random.nextInt(7) + 1));
            }
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }
}
