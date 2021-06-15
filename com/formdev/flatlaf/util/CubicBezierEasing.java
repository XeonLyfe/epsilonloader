/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.Animator;

public class CubicBezierEasing
implements Animator.Interpolator {
    public static final CubicBezierEasing STANDARD_EASING = new CubicBezierEasing(Float.intBitsToFloat(0x466F14D5 ^ 0x78A3D818), 0.0f, Float.intBitsToFloat((int)((long)1951713447 ^ (long)1243087978)), 1.0f);
    public static final CubicBezierEasing EASE = new CubicBezierEasing(Float.intBitsToFloat((int)((long)517587406 ^ (long)542753230)), Float.intBitsToFloat((int)1132771905L ^ 0x7E48768C), Float.intBitsToFloat((int)225252402L ^ 0x33ED1432), 1.0f);
    public static final CubicBezierEasing EASE_IN = new CubicBezierEasing(Float.intBitsToFloat(0x3486D016 ^ 0xA51DA2B), 0.0f, 1.0f, 1.0f);
    public static final CubicBezierEasing EASE_IN_OUT = new CubicBezierEasing(Float.intBitsToFloat((int)((long)709579267 ^ (long)345790526)), 0.0f, Float.intBitsToFloat(0x324A3FC4 ^ 0xD5E4525), 1.0f);
    public static final CubicBezierEasing EASE_OUT = new CubicBezierEasing(0.0f, 0.0f, Float.intBitsToFloat((int)1064661694L ^ 0x61085F), 1.0f);
    private final float x1;
    private final float y1;
    private final float x2;
    private final float y2;

    public CubicBezierEasing(float f2, float f3, float f4, float f5) {
        if (f2 < 0.0f || f2 > 1.0f || f3 < 0.0f || f3 > 1.0f || f4 < 0.0f || f4 > 1.0f || f5 < 0.0f || f5 > 1.0f) {
            throw new IllegalArgumentException("control points must be in range [0, 1]");
        }
        this.x1 = f2;
        this.y1 = f3;
        this.x2 = f4;
        this.y2 = f5;
    }

    @Override
    public float interpolate(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            return f2;
        }
        float f3 = 0.0f;
        float f4 = 1.0f;
        float f5;
        float f6;
        while (!(Math.abs(f2 - (f6 = CubicBezierEasing.cubicBezier(f5 = (f3 + f4) / 2.0f, this.x1, this.x2))) < Float.intBitsToFloat((int)((long)433502675 ^ (long)601205692)))) {
            if (f6 < f2) {
                f3 = f5;
                continue;
            }
            f4 = f5;
        }
        return CubicBezierEasing.cubicBezier(f5, this.y1, this.y2);
    }

    private static float cubicBezier(float f2, float f3, float f4) {
        float f5 = 1.0f - f2;
        float f6 = Float.intBitsToFloat((int)474200027L ^ 0x5C03B7DB) * f2 * (f5 * f5);
        float f7 = Float.intBitsToFloat((int)((long)492851220 ^ (long)1562398740)) * (f2 * f2) * f5;
        float f8 = f2 * f2 * f2;
        return f6 * f3 + f7 * f4 + f8;
    }
}

