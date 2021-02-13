package com.ryanhcode.hotchicks.entity.chicken;

import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class HotChickenEntity extends LivestockEntity {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    private static final DataParameter<Integer> carcass_quality = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> growth_rate = makeStat(DataSerializers.VARINT);
    private static final DataParameter<Integer> egg_speed = makeStat(DataSerializers.VARINT);


    public HotChickenEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn, 0.333);
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Sex: " + getSex().toString());
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

    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
        return EntityType.CHICKEN.create(world);
    }

    @Override
    public double getMaleToFemaleChance() { return 0.333; }

    private Biome getBiome() {
        int x = MathHelper.floor(this.getPosX());
        int y = MathHelper.floor(this.getPosY());
        int z = MathHelper.floor(this.getPosZ());
        return this.world.getBiome(new BlockPos(x, 0, z));
    }

    public static DataParameter makeStat(IDataSerializer dataSerializer){
        return EntityDataManager.createKey(HotChickenEntity.class, dataSerializer);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("carcass_quality", dataManager.get(carcass_quality));
        compound.putInt("growth_rate", dataManager.get(growth_rate));
        compound.putInt("egg_speed", dataManager.get(egg_speed));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if(compound.contains("carcass_quality")) {
            dataManager.set(carcass_quality, compound.getInt("carcass_quality"));
            dataManager.set(growth_rate, compound.getInt("growth_rate"));
            dataManager.set(egg_speed, compound.getInt("egg_speed"));
        }
    }

    public void registerData() {
        super.registerData();
        this.dataManager.register(carcass_quality, 6);
        this.dataManager.register(growth_rate, 9);
        this.dataManager.register(egg_speed, 6);
    }
}
