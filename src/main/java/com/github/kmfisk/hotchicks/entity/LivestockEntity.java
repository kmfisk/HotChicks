package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.CareStat;
import com.github.kmfisk.hotchicks.entity.base.Temperature;
import com.github.kmfisk.hotchicks.entity.goal.FindFoodGoal;
import com.github.kmfisk.hotchicks.entity.goal.FindWaterGoal;
import com.github.kmfisk.hotchicks.entity.goal.LivestockBreedGoal;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import com.github.kmfisk.hotchicks.entity.stats.Stats;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public abstract class LivestockEntity extends Animal {
    public static final EntityDataAccessor<Boolean> SEX = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Byte> TAG_COLOR = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Integer> TAMENESS = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> CARCASS_QUALITY = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HIDE_QUALITY = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GROWTH_RATE = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EGG_SPEED = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> EGG_TIMER = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LITTER_SIZE = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> MILK_YIELD = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> AVAILABLE_MILK = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> THIRST = SynchedEntityData.defineId(LivestockEntity.class, EntityDataSerializers.INT);
    protected final ListTag children = new ListTag();
    private final CareStat hunger;
    private final CareStat thirst;
    private boolean careRequired;

    public LivestockEntity(EntityType<? extends Animal> type, Level world) {
        super(type, world);
        hunger = new CareStat(this, HUNGER, getMaxCareStat(), getHungerDepletion());
        thirst = new CareStat(this, THIRST, getMaxCareStat(), getThirstDepletion());
        reassessDomesticGoals();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FindFoodGoal(this, 16, 2));
        this.goalSelector.addGoal(2, new FindWaterGoal(this, 16, 1));
        this.goalSelector.addGoal(3, new LivestockBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(TAG_COLOR, (byte) 0);
        this.entityData.define(TAMENESS, 50);
        this.entityData.define(CARCASS_QUALITY, 0);
        this.entityData.define(GROWTH_RATE, 0);
        this.entityData.define(HUNGER, 0);
        this.entityData.define(THIRST, 0);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficultyInstance, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag nbt) {
        setSex(Sex.fromBool(random.nextFloat() <= getMaleRatio()));
        setVariant(0);
        return super.finalizeSpawn(world, difficultyInstance, spawnReason, entityData, nbt);
    }

    public void initFromVillageSpawn() {

    }

    public abstract float getMaleRatio();

    public abstract int getMaxVariants();

    public abstract int getMaxCareStat();

    public abstract int getHungerDepletion();

    public abstract int getThirstDepletion();

    public abstract boolean isEdibleFood(ItemStack stack);

    public boolean canUseSmallDishes() {
        return true;
    }

    public abstract Stats getStats();

    public abstract void initByBreed(String breed);

    public abstract String getReadableBreed();

    public abstract Temperature getBreedTemperature();

    public Sex getSex() {
        return Sex.fromBool(entityData.get(SEX));
    }

    public void setSex(Sex sex) {
        entityData.set(SEX, sex.toBool());
    }

    public void setVariant(int variant) {
        entityData.set(VARIANT, variant);
    }

    public int getVariant() {
        return entityData.get(VARIANT);
    }

    public void setTagged(boolean tagged) {
        byte b0 = entityData.get(TAG_COLOR);
        if (tagged) entityData.set(TAG_COLOR, (byte) (b0 | 16));
        else entityData.set(TAG_COLOR, (byte) (b0 & -17));
    }

    public boolean isTagged() {
        return (entityData.get(TAG_COLOR) & 16) != 0;
    }

    public void setTagColor(DyeColor color) {
        byte b0 = entityData.get(TAG_COLOR);
        entityData.set(TAG_COLOR, (byte) (b0 & 240 | color.getId() & 15));
    }

    public DyeColor getTagColor() {
        return DyeColor.byId(entityData.get(TAG_COLOR) & 15);
    }

    public void setTameness(int tameness) {
        entityData.set(TAMENESS, tameness);
    }

    public int getTameness() {
        return entityData.get(TAMENESS);
    }

    public void setCarcassQuality(int carcassQuality) {
        entityData.set(CARCASS_QUALITY, carcassQuality);
    }

    public int getCarcassQuality() {
        return entityData.get(CARCASS_QUALITY);
    }

    public void setHideQuality(int hideQuality) {
        entityData.set(HIDE_QUALITY, hideQuality);
    }

    public int getHideQuality() {
        return entityData.get(HIDE_QUALITY);
    }

    public void setGrowthRate(int growthRate) {
        entityData.set(GROWTH_RATE, growthRate);
    }

    public int getGrowthRate() {
        return entityData.get(GROWTH_RATE);
    }

    public void setEggSpeed(int eggSpeed) {
        entityData.set(EGG_SPEED, eggSpeed);
    }

    public int getEggSpeed() {
        return entityData.get(EGG_SPEED);
    }

    public void setEggTimer(int i) {
        entityData.set(EGG_TIMER, i);
    }

    public int getEggTimer() {
        return entityData.get(EGG_TIMER);
    }

    public void setLitterSize(int litterSize) {
        entityData.set(LITTER_SIZE, litterSize);
    }

    public int getLitterSize() {
        return entityData.get(LITTER_SIZE);
    }

    public void setMilkYield(int milkYield) {
        entityData.set(MILK_YIELD, milkYield);
    }

    public int getMilkYield() {
        return entityData.get(MILK_YIELD);
    }

    public void setAvailableMilk(int availableMilk) {
        entityData.set(AVAILABLE_MILK, availableMilk);
    }

    public int getAvailableMilk() {
        return entityData.get(AVAILABLE_MILK);
    }

    public ListTag getChildren() {
        return children;
    }

    public boolean hasChildrenToSpawn() {
        return !children.isEmpty(); // todo client sync for stud book visuals
    }

    public int getGestationTimer() {
        return entityData.get(GESTATION_TIMER);
    }

    public void setGestationTimer(int maxGestationTime) {
        entityData.set(GESTATION_TIMER, maxGestationTime);
    }

    public CareStat getHunger() {
        return hunger;
    }

    public CareStat getThirst() {
        return thirst;
    }

    public boolean isCareRequired() {
        return careRequired;
    }

    public void setCareRequired() {
        careRequired = true;
        reassessDomesticGoals();
    }

    protected void reassessDomesticGoals() {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Sex", getSex().toBool());
        nbt.putInt("Variant", getVariant());
        nbt.putBoolean("Tagged", isTagged());
        nbt.putByte("TagColor", (byte) getTagColor().getId());
        nbt.putInt("Tameness", getTameness());
        nbt.putInt("CarcassQuality", getCarcassQuality());
        nbt.putInt("GrowthRate", getGrowthRate());
        if (!getSex().toBool()) if (hasChildrenToSpawn()) nbt.put("Children", children);
        nbt.put("Hunger", hunger.toTag());
        nbt.put("Thirst", thirst.toTag());
        nbt.putBoolean("CareRequired", careRequired);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        setSex(Sex.fromBool(nbt.getBoolean("Sex")));
        setVariant(nbt.getInt("Variant"));
        setTagged(nbt.getBoolean("Tagged"));
        setTagColor(DyeColor.byId(nbt.getByte("TagColor")));
        setTameness(nbt.getInt("Tameness"));
        setCarcassQuality(nbt.getInt("CarcassQuality"));
        setGrowthRate(nbt.getInt("GrowthRate"));
        if (!getSex().toBool()) {
            if (nbt.contains("Children")) {
                children.clear();
                children.addAll(nbt.getList("Children", 10));
            }
        }
        hunger.fromTag(nbt.getCompound("Hunger"));
        thirst.fromTag(nbt.getCompound("Thirst"));
        careRequired = nbt.getBoolean("CareRequired");
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (VARIANT.equals(key)) refreshDimensions();
        super.onSyncedDataUpdated(key);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        boolean doHunger = HotChicksConfig.hunger.get();
        boolean doThirst = HotChicksConfig.thirst.get();
        if (isAlive() && isCareRequired() && !level.isClientSide) {
            if (doHunger && getHunger().getValue() > 0) hunger.tick();
            if (doThirst && getThirst().getValue() > 0) thirst.tick();
            if (getHealth() < getMaxHealth() && tickCount % 20 == 0 && getHunger().getValue() == getHunger().getMax() && getThirst().getValue() == getThirst().getMax())
                heal(1.0F);
        }

        if (level.isClientSide && tickCount % 10 == 0) {
            if (((doHunger && getHunger().isLow()) || (doThirst && getThirst().isLow())))
                level.addParticle(ParticleTypes.SMOKE, getRandomX(1.0D), getRandomY() + 0.5D, getRandomZ(1.0D), random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D);
        }
    }

    @Override
    public void ate() {
        if (getHunger().getValue() < getHunger().getMax()) hunger.increment(1);
        if (getHealth() < getMaxHealth()) heal(1.0F);
    }

    @Override
    public boolean canFallInLove() {
        boolean lowHealth = getHealth() < getMaxHealth();
        boolean unhappy = getHunger().isLow() || getThirst().isLow();
        boolean alreadyBred = getSex() == Sex.FEMALE && hasChildrenToSpawn();
        return !lowHealth && !unhappy && !alreadyBred && super.canFallInLove();
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal.getType() != getType()) return false;
        else {
            LivestockEntity livestockEntity = (LivestockEntity) otherAnimal;
            if (livestockEntity.getSex() == getSex()) return false;
            return super.canMate(otherAnimal);
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
        if (mate instanceof LivestockEntity && ((LivestockEntity) mate).getSex() == Sex.MALE) {
            LivestockEntity partner = (LivestockEntity) mate;
            if (getStats() instanceof RabbitStats) {
                int babyCount = getStats().randomLitterSize();
                if (babyCount > 0) for (int i = 0; i < babyCount; i++) createChild(level, partner);
                else {
                    setAge(6000);
                    partner.setAge(6000);
                    resetLove();
                    partner.resetLove();
                    level.broadcastEntityEvent(this, (byte) 6);
                }
            } else createChild(level, partner);
        }
    }

    public abstract void createChild(ServerLevel level, LivestockEntity parent);

    public ItemStack addChildrenDataToItem(ItemStack stack) {
        if (hasChildrenToSpawn()) {
            stack.setTag(children.getCompound(0));
            children.clear();
        }

        setEggTimer(getStats().getEggSpeedForStat(this));
        return stack;
    }

    public void spawnChildrenFromPregnancy(ServerLevel level) {
        for (int i = 0; i < children.size(); i++) {
            CompoundTag childNBT = children.getCompound(i);
            Entity child = EntityType.loadEntityRecursive(childNBT, level, entity -> entity);
            if (child != null) {
                child.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F);
                level.addFreshEntity(child);
                level.broadcastEntityEvent(this, (byte) 18);
            }
        }

        children.clear();
        setGestationTimer(0);
    }

    @Override
    public void setBaby(boolean baby) {
        setAge(baby ? -(getStats().getGrowthRateForStat(this)) : 0);
        setCareRequired();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        InteractionResult actionResultType = super.mobInteract(player, hand);
        if (actionResultType.consumesAction()) setCareRequired();

        ItemStack stack = player.getItemInHand(hand);
        if (isEdibleFood(stack)) {
            if (getHealth() < getMaxHealth() || getHunger().getValue() < getHunger().getMax() || !isCareRequired()) {
                usePlayerItem(player, hand, stack);
                ate();
                if (!isCareRequired()) setCareRequired();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        } else if (getThirst().getValue() < getThirst().getMax()) {
            if (stack.getItem() == Items.WATER_BUCKET) {
                if (!player.getAbilities().instabuild) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                thirst.increment(3);
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (stack.getItem() == Items.POTION && PotionUtils.getPotion(stack) == Potions.WATER) {
                if (!player.getAbilities().instabuild) player.setItemInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                thirst.increment(1);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        if (stack.getItem() instanceof DyeItem) {
            DyeColor dyeColor = ((DyeItem) stack.getItem()).getDyeColor();
            if (!isTagged() || dyeColor != getTagColor()) {
                setTagged(true);
                setTagColor(dyeColor);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                setCareRequired();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        } else if (isTagged() && stack.getItem() == Items.SHEARS) {
            setTagged(false);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return actionResultType;
    }

    @Override
    protected boolean shouldDropLoot() {
        boolean extremeTemps = getBreedTemperature().isExtreme(level.getBiome(blockPosition()).value().getBaseTemperature());
        return !getHunger().isLow() && !getThirst().isLow() && !extremeTemps && super.shouldDropLoot();
    }

    public static boolean checkLivestockSpawnRules(EntityType<? extends LivestockEntity> entityType, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random random) {
        BlockState blockState = world.getBlockState(pos.below());
        return (blockState.is(Blocks.GRASS_BLOCK) || blockState.is(BlockTags.SNOW) || blockState.is(BlockTags.ICE)
                || blockState.is(BlockTags.SAND) || blockState.is(BlockTags.DIRT))
                && world.getRawBrightness(pos, 0) > 8;
    }

    protected Biome getBiome() {
        int x = Mth.floor(getX());
        int z = Mth.floor(getZ());
        return level.getBiome(new BlockPos(x, 0, z)).value();
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

        public BaseComponent getLocalizedName() {
            return new TranslatableComponent("data." + HotChicks.MOD_ID + ".sex." + name().toLowerCase(Locale.ROOT));
        }
    }
}