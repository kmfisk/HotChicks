package com.github.kmfisk.hotchicks.registry;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.inventory.NestContainer;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotContainers {
    public static final DeferredRegister<ContainerType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.CONTAINERS, HotChicks.MOD_ID);

    public static RegistryObject<ContainerType<NestContainer>> NEST = REGISTRAR.register("nest_container", NestContainer::createCraftingContainer);
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_SINGLE = REGISTRAR.register("trough_single", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericSingle);
    });
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE = REGISTRAR.register("trough_double", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericDouble);
    });
    public static RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE_METAL = REGISTRAR.register("trough_double_metal", () -> {
        return new ContainerType<TroughContainer>(TroughContainer::createGenericDoubleMetal);
    });
}