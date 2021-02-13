package com.ryanhcode.hotchicks.entity.base;

import net.minecraft.entity.passive.PigEntity;

public enum Sex {
    MALE,
    FEMALE;

    public static Sex getSex(boolean sex) {
        return sex ? MALE : FEMALE;
    }

    public static boolean getSex(Sex sex) {
        return sex == MALE;
    }
}