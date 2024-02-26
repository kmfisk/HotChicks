package com.github.kmfisk.hotchicks.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.config.HotChicksConfig;
import com.github.kmfisk.hotchicks.entity.base.RabbitBreeds;
import com.github.kmfisk.hotchicks.entity.base.Temperature;
import com.github.kmfisk.hotchicks.entity.goal.DestroyCropsGoal;
import com.github.kmfisk.hotchicks.entity.goal.LivestockBirthGoal;
import com.github.kmfisk.hotchicks.entity.goal.LowStatsAvoidEntityGoal;
import com.github.kmfisk.hotchicks.entity.goal.WildAvoidEntityGoal;
import com.github.kmfisk.hotchicks.entity.stats.RabbitStats;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

import javax.annotation.Nullable;

public class HotRabbitEntity extends LivestockEntity {
    public static final TagKey<Item> RABBIT_FOODS = ItemTags.create(new ResourceLocation(HotChicks.MOD_ID, "rabbit_foods"));
    private WildAvoidEntityGoal<Player> wildAvoidPlayersGoal;
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int jumpDelayTicks;

    public HotRabbitEntity(EntityType<? extends Animal> type, Level world) {
        super(type, world);
        this.jumpControl = new JumpHelperController(this);
        this.moveControl = new MoveHelperController(this);
        this.setSpeedModifier(0.0D);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new LivestockBirthGoal(this));
        this.goalSelector.addGoal(4, new LowStatsAvoidEntityGoal<>(this, Player.class, 16.0F, 0.8D, 1.33D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, Ingredient.of(RABBIT_FOODS), false));
        this.goalSelector.addGoal(5, new DestroyCropsGoal(this, 0.7F, 16));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected void reassessDomesticGoals() {
        if (wildAvoidPlayersGoal == null)
            wildAvoidPlayersGoal = new WildAvoidEntityGoal<>(this, Player.class, 16.0F, 0.8D, 1.33D);

        goalSelector.removeGoal(wildAvoidPlayersGoal);
        if (!isCareRequired()) goalSelector.addGoal(4, wildAvoidPlayersGoal);
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HIDE_QUALITY, 3);
        this.entityData.define(LITTER_SIZE, 2);
        this.entityData.define(GESTATION_TIMER, 0);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficultyInstance, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag nbt) {
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

    @Override
    public Temperature getBreedTemperature() {
        return getBreedFromVariant().getTemperature();
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
                setVariant(random.nextInt(RabbitBreeds.AMERICAN_CHINCHILLA.getVariantCountOfBreed()) + 1);
                break;
            case CALIFORNIA:
                setVariant(3);
                break;
            case DUTCH:
                setVariant(random.nextInt(RabbitBreeds.DUTCH.getVariantCountOfBreed()) + 4);
                break;
            case FLEMISH_GIANT:
                setVariant(random.nextInt(RabbitBreeds.FLEMISH_GIANT.getVariantCountOfBreed()) + 10);
                break;
            case NEW_ZEALAND:
                setVariant(random.nextInt(RabbitBreeds.NEW_ZEALAND.getVariantCountOfBreed()) + 15);
                break;
            case REX:
                setVariant(random.nextInt(RabbitBreeds.REX.getVariantCountOfBreed()) + 18);
                break;
        }

        setStats(rabbitBreeds.getStats());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("HideQuality", getHideQuality());
        nbt.putInt("LitterSize", getLitterSize());
        if (!getSex().toBool()) nbt.putInt("Gestation", getGestationTimer());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
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
        if (getVariant() <= 14) return RabbitBreeds.FLEMISH_GIANT;
        if (getVariant() <= 17) return RabbitBreeds.NEW_ZEALAND;
        if (getVariant() <= 26) return RabbitBreeds.REX;

        return RabbitBreeds.COTTONTAIL;
    }

    @Override
    public float getScale() {
        switch (getBreedFromVariant()) {
            default:
            case COTTONTAIL:
            case CALIFORNIA:
            case DUTCH:
            case REX:
                return super.getScale();
            case AMERICAN_CHINCHILLA:
            case NEW_ZEALAND:
                return isBaby() ? 0.6F : 1.2F;
            case FLEMISH_GIANT:
                return isBaby() ? 0.65F : 1.3F;
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.65F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.CARROT;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return HotEntities.RABBIT.get().create(world);
    }

    @Override
    protected void onOffspringSpawnedFromEgg(Player player, Mob entity) {
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
    public void createChild(ServerLevel level, LivestockEntity livestockEntity) {
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

                CompoundTag childNBT = new CompoundTag();
                child.save(childNBT);

                children.add(childNBT);
                setGestationTimer(HotChicksConfig.gestationSpeed.get());

                ServerPlayer serverplayerentity = getLoveCause();
                if (serverplayerentity == null && father.getLoveCause() != null)
                    serverplayerentity = father.getLoveCause();
                if (serverplayerentity != null) {
                    serverplayerentity.awardStat(Stats.ANIMALS_BRED);
                    CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this, father, child);
                }

                level.broadcastEntityEvent(this, (byte) 18);
                if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT))
                    level.addFreshEntity(new ExperienceOrb(level, getX(), getY(), getZ(), random.nextInt(7) + 1));
            }
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    protected float getJumpPower() {
        if (!horizontalCollision && (!moveControl.hasWanted() || !(moveControl.getWantedY() > getY() + 0.5D))) {
            Path path = navigation.getPath();
            if (path != null && !path.isDone()) {
                Vec3 vector3d = path.getNextEntityPos(this);
                if (vector3d.y > getY() + 0.5D) return 0.5F;
            }
            return moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;

        } else return 0.5F;
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        double d0 = moveControl.getSpeedModifier();
        if (d0 > 0.0D) {
            double d1 = getDeltaMovement().horizontalDistanceSqr();
            if (d1 < 0.01D) moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
        }
        if (!level.isClientSide) level.broadcastEntityEvent(this, (byte) 1);
    }

    @OnlyIn(Dist.CLIENT)
    public float getJumpCompletion(float partialTick) {
        return jumpDuration == 0 ? 0.0F : ((float) jumpTicks + partialTick) / (float) jumpDuration;
    }

    public void setSpeedModifier(double speedModifier) {
        getNavigation().setSpeedModifier(speedModifier);
        moveControl.setWantedPosition(moveControl.getWantedX(), moveControl.getWantedY(), moveControl.getWantedZ(), speedModifier);
    }

    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping)
            playSound(getJumpSound(), getSoundVolume(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
    }

    public void startJumping() {
        setJumping(true);
        jumpDuration = 10;
        jumpTicks = 0;
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (jumpDelayTicks > 0) --jumpDelayTicks;

        if (onGround) {
            if (!wasOnGround) {
                setJumping(false);
                checkLandingDelay();
            }

            JumpHelperController jumpHelperController = (JumpHelperController) jumpControl;
            if (!jumpHelperController.wantJump()) {
                if (moveControl.hasWanted() && jumpDelayTicks == 0) {
                    Path path = navigation.getPath();
                    Vec3 vector3d = new Vec3(moveControl.getWantedX(), moveControl.getWantedY(), moveControl.getWantedZ());
                    if (path != null && !path.isDone()) vector3d = path.getNextEntityPos(this);

                    facePoint(vector3d.x, vector3d.z);
                    startJumping();
                }
            } else if (!jumpHelperController.canJump()) enableJumpControl();
        }

        wasOnGround = onGround;
    }

    private void facePoint(double x, double z) {
        this.setYRot((float) (Mth.atan2(z - this.getZ(), x - this.getX()) * (double) (180F / (float) Math.PI)) - 90.0F);
    }

    private void enableJumpControl() {
        ((JumpHelperController) jumpControl).setCanJump(true);
    }

    private void disableJumpControl() {
        ((JumpHelperController) jumpControl).setCanJump(false);
    }

    private void setLandingDelay() {
        if (moveControl.getSpeedModifier() < 2.2D) jumpDelayTicks = 10;
        else jumpDelayTicks = 1;
    }

    private void checkLandingDelay() {
        setLandingDelay();
        disableJumpControl();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (jumpTicks != jumpDuration) ++jumpTicks;
        else if (jumpDuration != 0) {
            jumpTicks = 0;
            jumpDuration = 0;
            setJumping(false);
        }
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.RABBIT_JUMP;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.RABBIT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.RABBIT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RABBIT_DEATH;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 1) {
            spawnSprintParticle();
            jumpDuration = 10;
            jumpTicks = 0;

        } else super.handleEntityEvent(id);
    }

    public static class JumpHelperController extends JumpControl {
        private final HotRabbitEntity rabbit;
        private boolean canJump;

        public JumpHelperController(HotRabbitEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public boolean wantJump() {
            return jump;
        }

        public boolean canJump() {
            return canJump;
        }

        public void setCanJump(boolean pCanJump) {
            canJump = pCanJump;
        }

        public void tick() {
            if (jump) {
                rabbit.startJumping();
                jump = false;
            }
        }
    }

    static class MoveHelperController extends MoveControl {
        private final HotRabbitEntity rabbit;
        private double nextJumpSpeed;

        public MoveHelperController(HotRabbitEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public void tick() {
            if (rabbit.onGround && !rabbit.jumping && !((HotRabbitEntity.JumpHelperController) rabbit.jumpControl).wantJump())
                rabbit.setSpeedModifier(0.0D);
            else if (hasWanted()) rabbit.setSpeedModifier(nextJumpSpeed);

            super.tick();
        }

        public void setWantedPosition(double x, double y, double z, double speed) {
            if (rabbit.isInWater()) speed = 1.5D;

            super.setWantedPosition(x, y, z, speed);
            if (speed > 0.0D) nextJumpSpeed = speed;
        }
    }
}
