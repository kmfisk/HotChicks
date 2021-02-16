package com.ryanhcode.hotchicks.entity.chicken;

import com.ryanhcode.hotchicks.entity.base.*;
import com.ryanhcode.hotchicks.item.HotEggItem;
import com.ryanhcode.hotchicks.registry.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {

    public ChickenBreed breed = ChickenBreed.JUNGLEFOWL;

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    private static final DataParameter<Integer> egg_timer = makeStat(DataSerializers.VARINT);

    private static final DataParameter<Integer> chick_type = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> tameness = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> carcass_quality = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> growth_rate = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> egg_speed = makeStat(DataSerializers.VARINT);

    private static final DataParameter<String> breed_data = makeStat(DataSerializers.STRING);
    private static final DataParameter<String> variant = makeStat(DataSerializers.STRING);

    public int getTameness(){
        return dataManager.get(tameness);
    }

    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {

        super(type, worldIn, 0.333);
    }


    public String getBreed(){
        return dataManager.get(breed_data);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new LayEggsGoal(this));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new ChickenBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    @Override
    public double getMaleToFemaleChance() { return 0.333; }

    private Biome getBiome() {
        int x = MathHelper.floor(this.getPosX());
        int z = MathHelper.floor(this.getPosZ());
        return this.world.getBiome(new BlockPos(x, 0, z));
    }

    public static DataParameter makeStat(IDataSerializer dataSerializer){
        return EntityDataManager.createKey(HotChickenEntity.class, dataSerializer);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if(dataManager.get(breed_data).equals("not_set")){
            ChickenBreed breed = ChickenBreed.JUNGLEFOWL;
            this.breed = breed;

            dataManager.set(breed_data, breed.toString());
            dataManager.set(chick_type, breed.randomChickIndex());
        }
        if(dataManager.get(variant).equals("not_set")){
            setVariant(breed.randomVariant());
        }

        if(!getHeldItemMainhand().isEmpty()){
            int timer = dataManager.get(egg_timer) + 1;
            if(timer < getMaxEggTimer()+1) {
                dataManager.set(egg_timer, timer);
            }
        }
    }

    public int getMaxEggTimer(){
        return 200;
    }


    public int getEggTimer(){
        return dataManager.get(egg_timer);
    }

    public ChickenStats getStats(){
        return new ChickenStats(
                dataManager.get(carcass_quality),
                dataManager.get(growth_rate),
                dataManager.get(egg_speed)
        );
    }
    public void setStats(int carcass_quality, int growth_rate, int egg_speed){
        dataManager.set(this.carcass_quality, carcass_quality);
        dataManager.set(this.growth_rate, growth_rate);
        dataManager.set(this.egg_speed, egg_speed);
    }

    public void setStats(ChickenStats stats){
        dataManager.set(this.carcass_quality, stats.carcass_quality);
        dataManager.set(this.growth_rate, stats.growth_rate);
        dataManager.set(this.egg_speed, stats.egg_speed);
    }


    public void setVariant(String v){
        dataManager.set(this.variant, v);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("egg_timer", dataManager.get(egg_timer));
        compound.putInt("tameness", dataManager.get(tameness));
        compound.putInt("carcass_quality", dataManager.get(carcass_quality));
        compound.putInt("growth_rate", dataManager.get(growth_rate));
        compound.putInt("egg_speed", dataManager.get(egg_speed));
        compound.putString("breed", breed.toString());
        compound.putString("variant", dataManager.get(variant));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if(compound.contains("carcass_quality")) {
            dataManager.set(egg_timer, compound.getInt("egg_timer"));
            dataManager.set(tameness, compound.getInt("tameness"));
            dataManager.set(carcass_quality, compound.getInt("carcass_quality"));
            dataManager.set(growth_rate, compound.getInt("growth_rate"));
            dataManager.set(egg_speed, compound.getInt("egg_speed"));
            dataManager.set(variant, compound.getString("variant"));
            dataManager.set(chick_type, compound.getInt("chick_type"));
        }
        if(compound.contains("breed")){
            String breed = compound.getString("breed");
            this.breed = ChickenBreed.valueOf(breed);
            dataManager.set(breed_data, this.breed.toString());
        }
    }


    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    public void registerData() {
        super.registerData();
        this.dataManager.register(egg_timer, 0);
        this.dataManager.register(chick_type, 0);
        this.dataManager.register(carcass_quality, 1);
        this.dataManager.register(growth_rate, 2);
        this.dataManager.register(egg_speed, 3);
        this.dataManager.register(variant, "not_set");
        this.dataManager.register(breed_data, "not_set");
        this.dataManager.register(tameness, 50);
    }


    public String getVariant() {
        return dataManager.get(variant);
    }

    public int getChildType() {
        return dataManager.get(chick_type);
    }

    @Override
    public boolean canMateWith(AnimalEntity otherAnimal) {
        HotChickenEntity other = (HotChickenEntity) otherAnimal;
        if (other == this) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && otherAnimal.isInLove() && other.getSex() != this.getSex();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 18) {
            for(int i = 0; i < 7; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
            }
        } else if(id == 19){
            for(int i = 0; i < 7; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
        else {
            super.handleStatusUpdate(id);
        }

    }

    public void func_234177_a_(ServerWorld world, AnimalEntity animal) {



        HotChickenEntity other = (HotChickenEntity) animal;

        if(!this.getHeldItemMainhand().isEmpty()){
            world.setEntityState(this, (byte)19);
            return;
        }

        ItemStack stack = new ItemStack(ItemRegistry.WHITE_EGG.get());

        if(other.breed == this.breed) {
            HotEggItem.setBreed(stack, this.breed.toString());
            boolean isMotherOrFathers = getRNG().nextFloat() < 0.6;
            if(isMotherOrFathers) {
                HotEggItem.setVariant(stack, isMotherOrFathers ? this.getVariant() : other.getVariant());
            }
        }else{
            HotEggItem.setBreed(stack, getRNG().nextFloat() < 0.5 ? this.breed.toString() : other.breed.toString());
        }

        ChickenStats stats = this.getStats().average(other.getStats()).mutate(0.15);
        HotEggItem.setStats(stack, stats);
        HotEggItem.setTameness(stack, (int)(dataManager.get(tameness)*1.1));


        this.setHeldItem(Hand.MAIN_HAND, stack);

        this.setGrowingAge(6000);
        animal.setGrowingAge(6000);
        this.resetInLove();
        animal.resetInLove();

        world.setEntityState(this, (byte)18);
        world.addEntity(new ExperienceOrbEntity(world, this.getPosX(), this.getPosY(), this.getPosZ(), this.getRNG().nextInt(7) + 1));
    }


    public void setEggTimer(int i) {
        dataManager.set(egg_timer, i);
    }
}
