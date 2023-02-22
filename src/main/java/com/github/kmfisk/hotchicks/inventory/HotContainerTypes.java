package com.github.kmfisk.hotchicks.inventory;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.gui.FoodCrockScreen;
import com.github.kmfisk.hotchicks.client.gui.MillScreen;
import com.github.kmfisk.hotchicks.client.gui.NestScreen;
import com.github.kmfisk.hotchicks.client.gui.TroughScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotContainerTypes {
    public static final DeferredRegister<ContainerType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.CONTAINERS, HotChicks.MOD_ID);

    public static final RegistryObject<ContainerType<MillContainer>> MILL = REGISTRAR.register("mill", () -> new ContainerType<>(MillContainer::new));
    public static final RegistryObject<ContainerType<NestContainer>> NEST = REGISTRAR.register("nest", () -> new ContainerType<>(NestContainer::new));
    public static final RegistryObject<ContainerType<FoodCrockContainer>> FOOD_CROCK = REGISTRAR.register("food_crock", () -> new ContainerType<>(FoodCrockContainer::new));
    public static final RegistryObject<ContainerType<TroughContainer>> TROUGH_SINGLE = REGISTRAR.register("trough_single", () -> new ContainerType<>(TroughContainer::createGenericSingle));
    public static final RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE = REGISTRAR.register("trough_double", () -> new ContainerType<>(TroughContainer::createGenericDouble));
    public static final RegistryObject<ContainerType<TroughContainer>> TROUGH_DOUBLE_METAL = REGISTRAR.register("trough_double_metal", () -> new ContainerType<>(TroughContainer::createGenericDoubleMetal));

    @OnlyIn(Dist.CLIENT)
    public static void registerFactories() {
        ScreenManager.register(MILL.get(), MillScreen::new);
        ScreenManager.register(NEST.get(), NestScreen::new);
        ScreenManager.register(FOOD_CROCK.get(), FoodCrockScreen::new);
        ScreenManager.register(TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        ScreenManager.register(TROUGH_DOUBLE.get(), TroughScreen::new);
        ScreenManager.register(TROUGH_SINGLE.get(), TroughScreen::new);
    }
}