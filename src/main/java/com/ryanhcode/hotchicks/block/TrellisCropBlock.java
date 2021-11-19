package com.ryanhcode.hotchicks.block;

import net.minecraft.util.IStringSerializable;

public enum TrellisCropBlock implements IStringSerializable {
    NONE("none"),
    GRAPES("grapes"),
    KIWI("kiwi"),
    PEAS("peas"),
    TOMATO("tomato");

    public final String name;

    private TrellisCropBlock(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }
}

