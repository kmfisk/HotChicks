package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import com.github.kmfisk.hotchicks.item.HotItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

import javax.annotation.Nullable;

public class HotRabbitEntity extends LivestockEntity {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT, Items.WHEAT, HotItems.CORN.get(), HotItems.OATS.get(), HotItems.LETTUCE.get());

    public HotRabbitEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
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

    public void setStats(RabbitStats stats) {
        setTameness(stats.tameness);
        setCarcassQuality(stats.carcassQuality);
        setHideQuality(stats.hideQuality);
        setGrowthRate(stats.growthRate);
        setLitterSize(stats.litterSize);
    }

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
        return HotEntities.RABBIT.get().create(world);
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
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity entity) {
        if (entity instanceof HotRabbitEntity) {
            HotRabbitEntity partner = (HotRabbitEntity) entity;
            int babyCount = getStats().randomLitterSize();
            if (babyCount > 0) for (int i = 0; i < babyCount; i++) createChild(world, partner);
            else {
                setAge(6000);
                partner.setAge(6000);
                resetLove();
                partner.resetLove();
                level.broadcastEntityEvent(this, (byte) 6);
            }
        }
    }

    public void createChild(ServerWorld world, HotRabbitEntity parent) {
        HotRabbitEntity child = (HotRabbitEntity) getBreedOffspring(world, parent);
        final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, parent, child);
        final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
        child = (HotRabbitEntity) event.getChild();
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

            setAge(6000);
            parent.setAge(6000);
            resetLove();
            parent.resetLove();

            boolean inheritMotherGenes = random.nextFloat() <= 0.6;
            boolean colorMorph = random.nextFloat() <= 0.1;
            RabbitBreeds breed1 = getBreedFromVariant(getVariant());
            RabbitBreeds breed2 = parent.getBreedFromVariant(parent.getVariant());
            RabbitStats stats = (RabbitStats) getStats().average(parent.getStats(), true).mutate(0.2);

            if (stats.tameness < 95) child.setVariant(0);
            else {
                int childVariant;
                RabbitBreeds childBreed;

                if (breed1 != RabbitBreeds.COTTONTAIL && breed2 != RabbitBreeds.COTTONTAIL) {
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
                    if (colorMorph) childVariant = RabbitBreeds.randomFromBreed(random, childBreed);

                } else {
                    if (breed1 != RabbitBreeds.COTTONTAIL && inheritMotherGenes) {
                        childBreed = breed1;
                        childVariant = colorMorph ? RabbitBreeds.randomFromBreed(random, childBreed) : getVariant();
                    } else if (breed2 != RabbitBreeds.COTTONTAIL && !inheritMotherGenes) {
                        childBreed = breed2;
                        childVariant = colorMorph ? RabbitBreeds.randomFromBreed(random, childBreed) : parent.getVariant();
                    } else {
                        if (random.nextFloat() <= 0.8F) childVariant = RabbitBreeds.randomBasedOnBiome(random, getBiome());
                        else childVariant = random.nextInt(getMaxVariants()) + 1;
                        childBreed = getBreedFromVariant(childVariant);
                    }
                }

                if (childBreed != RabbitBreeds.COTTONTAIL && random.nextFloat() <= 0.8F)
                    stats = (RabbitStats) stats.average(childBreed.stats, false);

                child.setVariant(childVariant);
            }

            child.setBaby(true);
            child.setStats(stats);
            child.setSex(Sex.fromBool(random.nextBoolean()));

            child.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F); // todo: pregnancy
            world.addFreshEntityWithPassengers(child);

            world.broadcastEntityEvent(this, (byte)18);
            if (world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                world.addFreshEntity(new ExperienceOrbEntity(world, getX(), getY(), getZ(), random.nextInt(7) + 1));
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }
}
