package com.github.kmfisk.hotchicks.block;

import net.minecraft.util.StringRepresentable;

public enum TroughFillType implements StringRepresentable {
    NONE("none"),
    WATER("water"),
    FEED("feed");

    private final String name;

    private TroughFillType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }
}
