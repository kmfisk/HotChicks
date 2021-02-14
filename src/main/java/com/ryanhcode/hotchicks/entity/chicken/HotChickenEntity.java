package com.ryanhcode.hotchicks.entity.chicken;

import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.ChickenStats;
import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import com.ryanhcode.hotchicks.registry.EntityRegistry;
import net.minecraft.block.BarrelBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

public class HotChickenEntity extends LivestockEntity {

    public ChickenBreed breed = ChickenBreed.JUNGLEFOWL;

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    private static final DataParameter<Integer> carcass_quality = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> growth_rate = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> egg_speed = makeStat(DataSerializers.VARINT);

    private static final DataParameter<String> breed_data = makeStat(DataSerializers.STRING);
    private static final DataParameter<String> variant = makeStat(DataSerializers.STRING);


    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn, 0.333);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(
                "Sex: " + getSex().toString() + " | " + breed.name
        );
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }


    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
        return EntityRegistry.HOT_CHICKEN.get().create(world);
        //return EntityType.CHICKEN.create(world);
        //ChickenEntity
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
            setVariant(isChild() ? "child" : "default");
        }
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


    public void setVariant(String v){
        dataManager.set(this.variant, v);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("carcass_quality", dataManager.get(carcass_quality));
        compound.putInt("growth_rate", dataManager.get(growth_rate));
        compound.putInt("egg_speed", dataManager.get(egg_speed));
        compound.putString("breed", breed.toString());
        compound.putString("variant", dataManager.get(variant));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if(compound.contains("carcass_quality")) {
            dataManager.set(carcass_quality, compound.getInt("carcass_quality"));
            dataManager.set(growth_rate, compound.getInt("growth_rate"));
            dataManager.set(egg_speed, compound.getInt("egg_speed"));
            dataManager.set(variant, compound.getString("variant"));
        }
        if(compound.contains("breed")){
            String breed = compound.getString("breed");
            this.breed = ChickenBreed.valueOf(breed);
            dataManager.set(breed_data, breed);
        }
    }


    public void registerData() {
        super.registerData();
        this.dataManager.register(carcass_quality, 6);
        this.dataManager.register(growth_rate, 9);
        this.dataManager.register(egg_speed, 6);
        this.dataManager.register(variant, "default");
        this.dataManager.register(breed_data, "not_set");
    }


    public String getVariant() {
        return dataManager.get(variant);
    }
}
