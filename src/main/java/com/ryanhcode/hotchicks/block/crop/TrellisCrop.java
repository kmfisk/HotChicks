package com.ryanhcode.hotchicks.block.crop;

import net.minecraft.util.IStringSerializable;

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
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }
}

