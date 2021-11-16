package com.ryanhcode.hotchicks.entity.base;

public enum Sex {
    MALE,
    FEMALE;

    public static Sex getSex(boolean sex) {
        return sex ? MALE : FEMALE;
    }

    public boolean toBool() {
        return this == MALE;
    }
}