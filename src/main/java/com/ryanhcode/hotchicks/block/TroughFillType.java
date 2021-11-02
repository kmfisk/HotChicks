package com.ryanhcode.hotchicks.block;

import net.minecraft.util.IStringSerializable;

public enum TroughFillType implements IStringSerializable {
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
