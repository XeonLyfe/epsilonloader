/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.awt.Color;

public class HSLColor {
    private final Color rgb;
    private final float[] hsl;
    private final float alpha;

    public HSLColor(Color color) {
        this.rgb = color;
        this.hsl = HSLColor.fromRGB(color);
        this.alpha = (float)color.getAlpha() / Float.intBitsToFloat((int)((long)1436735965 ^ (long)383637981));
    }

    public HSLColor(float f2, float f3, float f4) {
        this(f2, f3, f4, 1.0f);
    }

    public HSLColor(float f2, float f3, float f4, float f5) {
        float[] arrf = new float[(int)((long)1890105040 ^ (long)1890105043)];
        arrf[(int)61187995L ^ 61187995] = f2;
        arrf[(int)((long)-1361972454 ^ (long)-1361972453)] = f3;
        arrf[((int)150768336L ^ 150768337) << 1] = f4;
        this.hsl = arrf;
        this.alpha = f5;
        this.rgb = HSLColor.toRGB(this.hsl, f5);
    }

    public HSLColor(float[] arrf) {
        this(arrf, 1.0f);
    }

    public HSLColor(float[] arrf, float f2) {
        this.hsl = arrf;
        this.alpha = f2;
        this.rgb = HSLColor.toRGB(arrf, f2);
    }

    public Color adjustHue(float f2) {
        return HSLColor.toRGB(f2, this.hsl[(int)-1201400069L ^ 0xB86416FA], this.hsl[(int)((long)1539855998 ^ (long)1539855999) << 1], this.alpha);
    }

    public Color adjustLuminance(float f2) {
        return HSLColor.toRGB(this.hsl[(int)((long)-1441435664 ^ (long)-1441435664)], this.hsl[(int)174957770L ^ 0xA6DA4CB], f2, this.alpha);
    }

    public Color adjustSaturation(float f2) {
        return HSLColor.toRGB(this.hsl[(int)((long)472979809 ^ (long)472979809)], f2, this.hsl[(int)((long)769382681 ^ (long)769382680) << 1], this.alpha);
    }

    public Color adjustShade(float f2) {
        float f3 = (Float.intBitsToFloat(0x76D1FDE0 ^ 0x3419FDE0) - f2) / Float.intBitsToFloat((int)466989835L ^ 0x591DB30B);
        float f4 = Math.max(0.0f, this.hsl[((int)1582471186L ^ 0x5E529813) << 1] * f3);
        return HSLColor.toRGB(this.hsl[(int)-211870021L ^ 0xF35F1EBB], this.hsl[(int)((long)365347042 ^ (long)365347043)], f4, this.alpha);
    }

    public Color adjustTone(float f2) {
        float f3 = (Float.intBitsToFloat(0x3A73D95B ^ 0x78BBD95B) + f2) / Float.intBitsToFloat((int)((long)1166718919 ^ (long)121812935));
        float f4 = Math.min(Float.intBitsToFloat((int)((long)879065358 ^ (long)1991080206)), this.hsl[((int)577560715L ^ 0x226CE08A) << 1] * f3);
        return HSLColor.toRGB(this.hsl[(int)-341199863L ^ 0xEBA9B409], this.hsl[(int)1740413486L ^ 0x67BC9A2F], f4, this.alpha);
    }

    public float getAlpha() {
        return this.alpha;
    }

    public Color getComplementary() {
        float f2 = (this.hsl[(int)-241636314L ^ 0xF198EC26] + Float.intBitsToFloat(0x2108C886 ^ 0x623CC886)) % Float.intBitsToFloat((int)1683868391L ^ 0x27E9CAE7);
        return HSLColor.toRGB(f2, this.hsl[(int)-1267806028L ^ 0xB46ED0B5], this.hsl[(int)((long)778449018 ^ (long)778449019) << 1]);
    }

    public float getHue() {
        return this.hsl[(int)103181067L ^ 0x6266B0B];
    }

    public float[] getHSL() {
        return this.hsl;
    }

    public float getLuminance() {
        return this.hsl[(int)((long)-1935470021 ^ (long)-1935470022) << 1];
    }

    public Color getRGB() {
        return this.rgb;
    }

    public float getSaturation() {
        return this.hsl[(int)-271148628L ^ 0xEFD699AD];
    }

    public String toString() {
        String string = "HSLColor[h=" + this.hsl[(int)((long)-85709107 ^ (long)-85709107)] + ",s=" + this.hsl[(int)-1240319673L ^ 0xB6123946] + ",l=" + this.hsl[(int)((long)-217210450 ^ (long)-217210449) << 1] + ",alpha=" + this.alpha + "]";
        return string;
    }

    public static float[] fromRGB(Color color) {
        float[] arrf = color.getRGBColorComponents(null);
        float f2 = arrf[(int)((long)133072836 ^ (long)133072836)];
        float f3 = arrf[(int)220369293L ^ 0xD22918C];
        float f4 = arrf[((int)1051895679L ^ 0x3EB2A77E) << 1];
        float f5 = Math.min(f2, Math.min(f3, f4));
        float f6 = Math.max(f2, Math.max(f3, f4));
        float f7 = 0.0f;
        if (f6 == f5) {
            f7 = 0.0f;
        } else if (f6 == f2) {
            f7 = (Float.intBitsToFloat((int)1313250834L ^ 0xC369E12) * (f3 - f4) / (f6 - f5) + Float.intBitsToFloat((int)2113276971L ^ 0x3E420C2B)) % Float.intBitsToFloat((int)((long)902502279 ^ (long)1988040583));
        } else if (f6 == f3) {
            f7 = Float.intBitsToFloat((int)((long)1706565412 ^ (long)667426596)) * (f4 - f2) / (f6 - f5) + Float.intBitsToFloat((int)((long)1583952058 ^ (long)479801530));
        } else if (f6 == f4) {
            f7 = Float.intBitsToFloat(0x3E534E23 ^ 0x7C234E23) * (f2 - f3) / (f6 - f5) + Float.intBitsToFloat((int)1466767565L ^ 0x141D18CD);
        }
        float f8 = (f6 + f5) / 2.0f;
        float f9 = 0.0f;
        f9 = f6 == f5 ? 0.0f : (f8 <= Float.intBitsToFloat(0x3C064D02 ^ 0x3064D02) ? (f6 - f5) / (f6 + f5) : (f6 - f5) / (2.0f - f6 - f5));
        float[] arrf2 = new float[(int)((long)-1750996589 ^ (long)-1750996592)];
        arrf2[(int)((long)586108330 ^ (long)586108330)] = f7;
        arrf2[(int)-1656896357L ^ -1656896358] = f9 * Float.intBitsToFloat((int)((long)442940082 ^ (long)1487846066));
        arrf2[((int)-1472962328L ^ -1472962327) << 1] = f8 * Float.intBitsToFloat((int)1793751066L ^ 0x2822781A);
        return arrf2;
    }

    public static Color toRGB(float[] arrf) {
        return HSLColor.toRGB(arrf, 1.0f);
    }

    public static Color toRGB(float[] arrf, float f2) {
        return HSLColor.toRGB(arrf[(int)727187104L ^ 0x2B57FEA0], arrf[(int)-1498538694L ^ 0xA6AE1D3B], arrf[((int)462351513L ^ 0x1B8EEC98) << 1], f2);
    }

    public static Color toRGB(float f2, float f3, float f4) {
        return HSLColor.toRGB(f2, f3, f4, 1.0f);
    }

    public static Color toRGB(float f2, float f3, float f4, float f5) {
        if (f3 < 0.0f || f3 > Float.intBitsToFloat((int)((long)1928073058 ^ (long)807669602))) {
            String string = "Color parameter outside of expected range - Saturation";
            throw new IllegalArgumentException(string);
        }
        if (f4 < 0.0f || f4 > Float.intBitsToFloat((int)1542271541L ^ 0x19253235)) {
            String string = "Color parameter outside of expected range - Luminance";
            throw new IllegalArgumentException(string);
        }
        if (f5 < 0.0f || f5 > 1.0f) {
            String string = "Color parameter outside of expected range - Alpha";
            throw new IllegalArgumentException(string);
        }
        f2 %= Float.intBitsToFloat((int)1253436793L ^ 0x901ED79);
        float f6 = 0.0f;
        f6 = (double)f4 < Double.longBitsToDouble((long)2108011257 ^ 0x3FE000007DA5B2F9L) ? f4 * (1.0f + f3) : (f4 /= Float.intBitsToFloat((int)172479291L ^ 0x488FD33B)) + (f3 /= Float.intBitsToFloat((int)600045741L ^ 0x610BF8AD)) - f3 * f4;
        float f7 = 2.0f * f4 - f6;
        float f8 = Math.max(0.0f, HSLColor.HueToRGB(f7, f6, (f2 /= Float.intBitsToFloat((int)((long)1565506497 ^ (long)519814081))) + Float.intBitsToFloat((int)((long)1208305228 ^ (long)1991240935))));
        float f9 = Math.max(0.0f, HSLColor.HueToRGB(f7, f6, f2));
        float f10 = Math.max(0.0f, HSLColor.HueToRGB(f7, f6, f2 - Float.intBitsToFloat((int)((long)1036288020 ^ (long)57551551))));
        f8 = Math.min(f8, 1.0f);
        f9 = Math.min(f9, 1.0f);
        f10 = Math.min(f10, 1.0f);
        return new Color(f8, f9, f10, f5);
    }

    private static float HueToRGB(float f2, float f3, float f4) {
        if (f4 < 0.0f) {
            f4 += 1.0f;
        }
        if (f4 > 1.0f) {
            f4 -= 1.0f;
        }
        if (Float.intBitsToFloat((int)1773518256L ^ 0x2975BDB0) * f4 < 1.0f) {
            return f2 + (f3 - f2) * Float.intBitsToFloat(0x15C29475 ^ 0x55029475) * f4;
        }
        if (2.0f * f4 < 1.0f) {
            return f3;
        }
        if (Float.intBitsToFloat(0x4F1BB6C4 ^ 0xF5BB6C4) * f4 < 2.0f) {
            return f2 + (f3 - f2) * Float.intBitsToFloat((int)((long)1873652071 ^ (long)795715943)) * (Float.intBitsToFloat(0x46DFC462 ^ 0x79F56EC9) - f4);
        }
        return f2;
    }
}

