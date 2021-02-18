package com.ryanhcode.hotchicks.block.crop;

import net.minecraft.util.IStringSerializable;

import java.util.Random;

public enum PepperType implements IStringSerializable {
    ORANGE("orange"),
    YELLOW("yellow"),
    RED("red"),
    DEFAULT("default");

    public static PepperType getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    private final String name;

    private PepperType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getString();
    }

    public String getString() {
        return this.name;
    }
}

