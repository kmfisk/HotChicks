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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public abstract class LivestockEntity extends AnimalEntity {
    public static final DataParameter<Boolean> SEX = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Byte> TAG_COLOR = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.BYTE);
    public static final DataParameter<Integer> TAMENESS = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> CARCASS_QUALITY = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> HIDE_QUALITY = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> GROWTH_RATE = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> EGG_SPEED = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> EGG_TIMER = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> LITTER_SIZE = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> MILK_YIELD = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> AVAILABLE_MILK = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> THIRST = EntityDataManager.defineId(LivestockEntity.class, DataSerializers.INT);
    protected final ListNBT children = new ListNBT();
    private final CareStat hunger;
    private final CareStat thirst;
    private boolean careRequired;

    public LivestockEntity(EntityType<? extends AnimalEntity> type, World world) {
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
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT nbt) {
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

    public ListNBT getChildren() {
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
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
    public void readAdditionalSaveData(CompoundNBT nbt) {
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
    public void onSyncedDataUpdated(DataParameter<?> key) {
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
    public boolean canFallInLove() {
        boolean lowHealth = getHealth() < getMaxHealth();
        boolean unhappy = getHunger().isLow() || getThirst().isLow();
        boolean alreadyBred = getSex() == Sex.FEMALE && hasChildrenToSpawn();
        return !lowHealth && !unhappy && !alreadyBred && super.canFallInLove();
    }

    @Override
    public boolean canMate(AnimalEntity otherAnimal) {
        if (otherAnimal.getType() != getType()) return false;
        else {
            LivestockEntity livestockEntity = (LivestockEntity) otherAnimal;
            if (livestockEntity.getSex() == getSex()) return false;
            return super.canMate(otherAnimal);
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerWorld level, AnimalEntity mate) {
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

    public abstract void createChild(ServerWorld level, LivestockEntity parent);

    public ItemStack addChildrenDataToItem(ItemStack stack) {
        if (hasChildrenToSpawn()) {
            stack.setTag(children.getCompound(0));
            children.clear();
        }

        setEggTimer(getStats().getEggSpeedForStat(this));
        return stack;
    }

    public void spawnChildrenFromPregnancy(ServerWorld level) {
        for (int i = 0; i < children.size(); i++) {
            CompoundNBT childNBT = children.getCompound(i);
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
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isEdibleFood(stack)) {
            if (getHunger().getValue() < getHunger().getMax()) {
                usePlayerItem(player, stack);
                hunger.increment(1);
                return ActionResultType.sidedSuccess(level.isClientSide);
            }
        } else if (getThirst().getValue() < getThirst().getMax()) {
            if (stack.getItem() == Items.WATER_BUCKET) {
                if (!player.abilities.instabuild) player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                thirst.increment(3);
                return ActionResultType.sidedSuccess(level.isClientSide);
            } else if (stack.getItem() == Items.POTION && PotionUtils.getPotion(stack) == Potions.WATER) {
                if (!player.abilities.instabuild) player.setItemInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                thirst.increment(1);
                return ActionResultType.sidedSuccess(level.isClientSide);
            }
        }

        if (stack.getItem() instanceof DyeItem) {
            DyeColor dyeColor = ((DyeItem) stack.getItem()).getDyeColor();
            if (!isTagged() || dyeColor != getTagColor()) {
                setTagged(true);
                setTagColor(dyeColor);
                if (!player.abilities.instabuild) stack.shrink(1);
                setCareRequired();
                return ActionResultType.sidedSuccess(level.isClientSide);
            }
        } else if (isTagged() && stack.getItem() == Items.SHEARS) {
            setTagged(false);
            return ActionResultType.sidedSuccess(level.isClientSide);
        }

        ActionResultType actionResultType = super.mobInteract(player, hand);
        if (actionResultType.consumesAction()) setCareRequired();

        return actionResultType;
    }

    @Override
    protected boolean shouldDropLoot() {
        boolean extremeTemps = getBreedTemperature().isExtreme(level.getBiome(blockPosition()).getBaseTemperature());
        return !getHunger().isLow() && !getThirst().isLow() && !extremeTemps && super.shouldDropLoot();
    }

    public static boolean checkLivestockSpawnRules(EntityType<? extends LivestockEntity> entityType, IServerWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockState blockState = world.getBlockState(pos.below());
        return (blockState.is(Blocks.GRASS_BLOCK) || blockState.is(Blocks.SNOW) || blockState.is(BlockTags.ICE)
                || Tags.Blocks.SAND.contains(blockState.getBlock()) || Tags.Blocks.DIRT.contains(blockState.getBlock()))
                && world.getRawBrightness(pos, 0) > 8;
    }

    protected Biome getBiome() {
        int x = MathHelper.floor(getX());
        int z = MathHelper.floor(getZ());
        return level.getBiome(new BlockPos(x, 0, z));
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

        public TextComponent getLocalizedName() {
            return new TranslationTextComponent("data." + HotChicks.MOD_ID + ".sex." + name().toLowerCase(Locale.ROOT));
        }
    }
}