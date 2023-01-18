package com.github.kmfisk.hotchicks.registry;

import com.github.kmfisk.hotchicks.HotChicks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HotSounds {
    public static final DeferredRegister<SoundEvent> REGISTRAR = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HotChicks.MOD_ID);

    public static RegistryObject<SoundEvent> CHICKEN_AMBIENT = registerSound("chicken_ambient");
    public static RegistryObject<SoundEvent> CHICKEN_HURT = registerSound("chicken_hurt");
    public static RegistryObject<SoundEvent> CHICKEN_ATTACK = registerSound("chicken_attack");
    public static RegistryObject<SoundEvent> ROOSTER_ATTACK = registerSound("rooster_attack");
    public static RegistryObject<SoundEvent> ROOSTER_CROW = registerSound("rooster_crow");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRAR.register(name, () -> new SoundEvent(new ResourceLocation(HotChicks.MOD_ID, name)));
    }
}
