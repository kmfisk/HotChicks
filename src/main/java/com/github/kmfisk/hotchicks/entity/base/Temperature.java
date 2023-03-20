package com.github.kmfisk.hotchicks.entity.base;

public enum Temperature {
    HOT(new float[]{0.90F, 1.23F}, new float[]{0.50F, 2.00F}),
    WARM(new float[]{0.50F, 0.8F}, new float[]{0.20F, 1.23F}),
    COLD(new float[]{0.20F, 0.30F}, new float[]{-0.50F, 0.80F});

    private final float mildMin, mildMax, extremeMin, extremeMax;

    Temperature(float[] mild, float[] extreme) {
        this.mildMin = mild[0];
        this.mildMax = mild[1];
        this.extremeMin = extreme[0];
        this.extremeMax = extreme[1];
    }

    public boolean isOutsideComfortLevel(float temperature) {
        return temperature > mildMax || temperature < mildMin;
    }

    public boolean isExtreme(float temperature) {
        return temperature > extremeMax || temperature < extremeMin;
    }

    public float getExtremeMin() {
        return extremeMin;
    }

    public float getExtremeMax() {
        return extremeMax;
    }
}
