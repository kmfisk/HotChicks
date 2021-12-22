package com.ryanhcode.hotchicks.registry;

import com.ryanhcode.hotchicks.HotChickens;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class HotSounds {
    public static SoundEvent CHICKEN_AMBIENT = registerSound("chicken_ambient");
    public static SoundEvent CHICKEN_HURT = registerSound("chicken_hurt");
    public static SoundEvent CHICKEN_ATTACK = registerSound("chicken_attack");
    public static SoundEvent ROOSTER_ATTACK = registerSound("rooster_attack");
    public static SoundEvent ROOSTER_CROW = registerSound("rooster_crow");

    private static SoundEvent registerSound(final String name) {
        final ResourceLocation resourceLocation = new ResourceLocation(HotChickens.MOD_ID, name);
        return new SoundEvent(resourceLocation).setRegistryName(resourceLocation);
    }
}
