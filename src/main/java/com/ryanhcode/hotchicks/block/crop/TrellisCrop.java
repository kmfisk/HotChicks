package com.ryanhcode.hotchicks.block.crop;

import net.minecraft.util.IStringSerializable;

import java.util.Random;

public enum TrellisCrop implements IStringSerializable {
    NONE("none"),
    GRAPES("grapes"),
    KIWI("kiwi"),
    PEAS("peas"),
    TOMATO("tomato");

    public final String name;

    private TrellisCrop(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getString();
    }

    public String getString() {
        return this.name;
    }
}

