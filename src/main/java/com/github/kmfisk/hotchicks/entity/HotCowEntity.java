package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.CowBreeds;
import com.github.kmfisk.hotchicks.entity.goal.LivestockAvoidPlayerGoal;
import com.github.kmfisk.hotchicks.entity.stats.CowStats;
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

import static com.github.kmfisk.hotchicks.entity.base.CowBreeds.*;

public class HotCowEntity extends LivestockEntity {
    public static final Tags.IOptionalNamedTag<Item> COW_FOODS = ItemTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "cow_foods"));

    public HotCowEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() { // todo
        return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() { // todo
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LivestockAvoidPlayerGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, Ingredient.of(COW_FOODS)));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_QUALITY, 3);
        this.entityData.define(MILK_YIELD, 2);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
        entityData = super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
        setStats(new CowStats(random.nextInt(25) + random.nextInt(35), random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)));
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
        return 6;
    } // todo

    @Override
    public boolean isEdibleFood(ItemStack stack) {
        return Ingredient.of(COW_FOODS).test(stack);
    }

    @Override
    public String getReadableBreed() {
        return getBreedFromVariant().toString();
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
    public void setupStats(String breed) {
        CowBreeds cowBreeds = CowBreeds.valueOf(breed.toUpperCase());
        switch (cowBreeds) {
            default:
            case AUROCHS:
                setVariant(0);
                break;
            case ANGUS:
                setVariant(random.nextInt(ANGUS.getVariantCountOfBreed()) + 1);
                break;
            case BRAHMA:
                setVariant(random.nextInt(BRAHMA.getVariantCountOfBreed()) + 3);
                break;
            case BROWN_SWISS:
                setVariant(8);
                break;
            case GUERNSEY:
                setVariant(random.nextInt(GUERNSEY.getVariantCountOfBreed()) + 9);
                break;
            case HEREFORD:
                setVariant(random.nextInt(HEREFORD.getVariantCountOfBreed()) + 11);
                break;
            case HIGHLAND:
                setVariant(random.nextInt(HIGHLAND.getVariantCountOfBreed()) + 15);
                break;
            case HOLSTEIN:
                setVariant(random.nextInt(HOLSTEIN.getVariantCountOfBreed()) + 19);
                break;
            case JERSEY:
                setVariant(23);
                break;
            case LAKENVELDER:
                setVariant(random.nextInt(LAKENVELDER.getVariantCountOfBreed()) + 24);
                break;
            case LONGHORN:
                setVariant(random.nextInt(LONGHORN.getVariantCountOfBreed()) + 26);
                break;
        }

        setStats(cowBreeds.getStats());
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideQuality", this.getHideQuality());
        nbt.putInt("MilkYield", this.getMilkYield());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setHideQuality(nbt.getInt("HideQuality"));
        this.setMilkYield(nbt.getInt("MilkYield"));
    }

    public CowBreeds getBreedFromVariant() {
        if (getVariant() == 0) return CowBreeds.AUROCHS;
        if (getVariant() <= 2) return ANGUS;
        if (getVariant() <= 7) return BRAHMA;
        if (getVariant() == 8) return CowBreeds.BROWN_SWISS;
        if (getVariant() <= 10) return GUERNSEY;
        if (getVariant() <= 14) return CowBreeds.HEREFORD;
        if (getVariant() <= 18) return CowBreeds.HIGHLAND;
        if (getVariant() <= 22) return CowBreeds.HOLSTEIN;
        if (getVariant() == 23) return CowBreeds.JERSEY;
        if (getVariant() <= 25) return CowBreeds.LAKENVELDER;
        if (getVariant() <= 30) return CowBreeds.LONGHORN;

        return CowBreeds.AUROCHS;
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
    public boolean canMate(AnimalEntity mate) {
        if (!(mate instanceof HotCowEntity)) return false;
        else {
            HotCowEntity cow = (HotCowEntity) mate;
            if (cow == this) return false;
            else return this.isInLove() && cow.isInLove() && cow.getSex() != this.getSex();
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerWorld world, AnimalEntity entity) {
        if (entity instanceof HotCowEntity) {
            HotCowEntity partner = (HotCowEntity) entity;
            createChild(world, partner);
        }
    }

    public void createChild(ServerWorld world, HotCowEntity parent) {
        HotCowEntity child = (HotCowEntity) getBreedOffspring(world, parent);
        final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, parent, child);
        final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
        child = (HotCowEntity) event.getChild();
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

            boolean inheritMotherGenes = random.nextFloat() <= 0.6;
            boolean colorMorph = random.nextFloat() <= 0.1;
            CowBreeds breed1 = getBreedFromVariant();
            CowBreeds breed2 = parent.getBreedFromVariant();
            CowStats stats = (CowStats) getStats().average(parent.getStats(), true).mutate(0.2);

            if (stats.tameness < 75) child.setVariant(0);
            else {
                int childVariant;
                CowBreeds childBreed;

                if (breed1 != CowBreeds.AUROCHS && breed2 != CowBreeds.AUROCHS) {
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
                    if (colorMorph) childVariant = CowBreeds.randomFromBreed(random, childBreed);

                } else {
                    if (breed1 != CowBreeds.AUROCHS && inheritMotherGenes) {
                        childBreed = breed1;
                        childVariant = colorMorph ? CowBreeds.randomFromBreed(random, childBreed) : getVariant();
                    } else if (breed2 != CowBreeds.AUROCHS && !inheritMotherGenes) {
                        childBreed = breed2;
                        childVariant = colorMorph ? CowBreeds.randomFromBreed(random, childBreed) : parent.getVariant();
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

            child.setBaby(true);
            child.setStats(stats);
            child.setSex(Sex.fromBool(random.nextBoolean()));

            child.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F); // todo: pregnancy
            world.addFreshEntityWithPassengers(child);

            world.broadcastEntityEvent(this, (byte) 18);
            if (world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                world.addFreshEntity(new ExperienceOrbEntity(world, getX(), getY(), getZ(), random.nextInt(7) + 1));
        }
    }
}
