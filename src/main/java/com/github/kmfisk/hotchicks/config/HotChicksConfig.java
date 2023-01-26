package com.github.kmfisk.hotchicks.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HotChicksConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static ForgeConfigSpec.ConfigValue<Integer> growthSpeed;
    public static ForgeConfigSpec.ConfigValue<Integer> eggSpeed;
    public static ForgeConfigSpec.ConfigValue<Integer> hatchSpeed;
    public static ForgeConfigSpec.ConfigValue<Integer> breedingCooldown;
    public static ForgeConfigSpec.BooleanValue hunger;
    public static ForgeConfigSpec.ConfigValue<Integer> hungerDepletion;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        CONFIG_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Timers");
        growthSpeed = builder
                .comment(" Growth speed is in minecraft ticks, and will be multiplied by the total of 5 minus the entity's stat.",
                        " For example, by default, a chick with a 2 in growth rate will have a growth timer of 36,000 ticks; that is (12000 * (5 - 2)).",
                        " Default: 12000")
                .define("growth_speed", 12000);
        eggSpeed = builder
                .comment(" Egg speed is in minecraft ticks, and will be multiplied by the total of 5 minus the entity's stat.",
                        " For example, by default, a hen with a 2 in egg speed will have an egg laying timer of 18,000 ticks; that is (6000 * (5 - 2)).",
                        " Default: 6000")
                .define("egg_speed", 6000);
        hatchSpeed = builder
                .comment(" Hatch speed is in minecraft ticks, and is the same for all eggs.",
                        " By default, an egg will have a hatch timer of 24,000 ticks.",
                        " Default: 24000")
                .define("hatch_speed", 24000);
        breedingCooldown = builder
                .comment(" Breeding cooldown is in minecraft ticks, and will be multiplied by the total of 5 minus the entity's growth rate stat.",
                        " For example, by default, a hen with a 2 in growth rate will have a breeding cooldown of 54,000 ticks; that is (18000 * (5 - 2)).",
                        " This only applies to the female on a successful breeding, unsuccessful attempts and males are always set to minecraft's default of 6000 ticks.",
                        " Default: 12000")
                .define("breeding_cooldown", 18000);
        builder.pop();

        builder.push("Hunger");
        hunger = builder.define("enabled", true);
        hungerDepletion = builder
                .comment(" Depletion rate is in minecraft ticks.",
                        " By default, animals will lose 1 hunger point every 12000 ticks.",
                        " Default: 12000")
                .define("depletion_rate", 12000);
        builder.pop();
    }
}
