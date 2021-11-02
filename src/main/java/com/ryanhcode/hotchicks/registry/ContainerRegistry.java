package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.NestContainer;
import com.ryanhcode.hotchicks.block.TroughContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, HotChickens.MODID);

    public static RegistryObject<ContainerType<NestContainer>> NEST = CONTAINERS.register("nest_container", NestContainer::createCraftingContainer);
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_SINGLE = CONTAINERS.register("trough_single", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericSingle);
    });
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE = CONTAINERS.register("trough_double", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericDouble);
    });
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE_METAL = CONTAINERS.register("trough_double_metal", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericDoubleMetal);
    });
}