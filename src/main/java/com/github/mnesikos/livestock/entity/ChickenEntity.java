package com.github.mnesikos.livestock.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ChickenEntity extends LivestockEntityBase {
    protected Identifier location;

    public ChickenEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public Identifier getTextureLocation() {
        if (location == null) {
            Identifier key = Registry.ENTITY_TYPE.getId(getType());
            location = new Identifier(key.getNamespace(), "textures/entity/chicken/" + key.getPath());
        }
        return location;
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
