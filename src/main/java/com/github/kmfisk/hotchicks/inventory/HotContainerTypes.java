package com.github.kmfisk.hotchicks.inventory;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.gui.FoodCrockScreen;
import com.github.kmfisk.hotchicks.client.gui.MillScreen;
import com.github.kmfisk.hotchicks.client.gui.NestScreen;
import com.github.kmfisk.hotchicks.client.gui.TroughScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotContainerTypes {
    public static final DeferredRegister<MenuType<?>> REGISTRAR = DeferredRegister.create(ForgeRegistries.CONTAINERS, HotChicks.MOD_ID);

    public static final RegistryObject<MenuType<MillContainer>> MILL = REGISTRAR.register("mill", () -> new MenuType<>(MillContainer::new));
    public static final RegistryObject<MenuType<NestContainer>> NEST = REGISTRAR.register("nest", () -> new MenuType<>(NestContainer::new));
    public static final RegistryObject<MenuType<FoodCrockContainer>> FOOD_CROCK = REGISTRAR.register("food_crock", () -> new MenuType<>(FoodCrockContainer::new));
    public static final RegistryObject<MenuType<TroughContainer>> TROUGH_SINGLE = REGISTRAR.register("trough_single", () -> new MenuType<>(TroughContainer::createGenericSingle));
    public static final RegistryObject<MenuType<TroughContainer>> TROUGH_DOUBLE = REGISTRAR.register("trough_double", () -> new MenuType<>(TroughContainer::createGenericDouble));
    public static final RegistryObject<MenuType<TroughContainer>> TROUGH_DOUBLE_METAL = REGISTRAR.register("trough_double_metal", () -> new MenuType<>(TroughContainer::createGenericDoubleMetal));

    @OnlyIn(Dist.CLIENT)
    public static void registerFactories() {
        MenuScreens.register(MILL.get(), MillScreen::new);
        MenuScreens.register(NEST.get(), NestScreen::new);
        MenuScreens.register(FOOD_CROCK.get(), FoodCrockScreen::new);
        MenuScreens.register(TROUGH_DOUBLE_METAL.get(), TroughScreen::new);
        MenuScreens.register(TROUGH_DOUBLE.get(), TroughScreen::new);
        MenuScreens.register(TROUGH_SINGLE.get(), TroughScreen::new);
    }
}