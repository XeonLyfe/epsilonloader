/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.HSLColor;
import java.awt.Color;

public class ColorFunctions {
    public static Color applyFunctions(Color color, ColorFunction ... arrcolorFunction) {
        float[] arrf = HSLColor.fromRGB(color);
        float f2 = (float)color.getAlpha() / Float.intBitsToFloat(0x4E0669B2 ^ 0xD7969B2);
        float[] arrf2 = new float[(int)((long)-1156611285 ^ (long)-1156611286) << 2];
        arrf2[(int)((long)1544770499 ^ (long)1544770499)] = arrf[(int)401229122L ^ 0x17EA4542];
        arrf2[(int)((long)1660973165 ^ (long)1660973164)] = arrf[(int)1227893283L ^ 0x49302A22];
        arrf2[((int)-271258550L ^ -271258549) << 1] = arrf[((int)-2013103604L ^ 0x88027A0D) << 1];
        arrf2[(int)((long)-119315704 ^ (long)-119315701)] = f2 * Float.intBitsToFloat((int)((long)1005914194 ^ (long)2034042962));
        float[] arrf3 = arrf2;
        ColorFunction[] arrcolorFunction2 = arrcolorFunction;
        int n2 = arrcolorFunction2.length;
        for (int i2 = (int)((long)-303914357 ^ (long)-303914357); i2 < n2; ++i2) {
            ColorFunction colorFunction = arrcolorFunction2[i2];
            colorFunction.apply(arrf3);
        }
        return HSLColor.toRGB(arrf3[(int)1671680013L ^ 0x63A3D00D], arrf3[(int)((long)-1282276488 ^ (long)-1282276487)], arrf3[(int)((long)-2031979265 ^ (long)-2031979266) << 1], arrf3[(int)-310724363L ^ 0xED7AB8F6] / Float.intBitsToFloat(0x6A98577F ^ 0x2850577F));
    }

    public static float clamp(float f2) {
        return f2 < 0.0f ? 0.0f : (f2 > Float.intBitsToFloat((int)((long)911274939 ^ (long)1956180923)) ? Float.intBitsToFloat((int)343739323L ^ 0x56B50BBB) : f2);
    }

    public static Color mix(Color color, Color color2, float f2) {
        if (f2 >= 1.0f) {
            return color;
        }
        if (f2 <= 0.0f) {
            return color2;
        }
        int n2 = color.getRed();
        int n3 = color.getGreen();
        int n4 = color.getBlue();
        int n5 = color.getAlpha();
        int n6 = color2.getRed();
        int n7 = color2.getGreen();
        int n8 = color2.getBlue();
        int n9 = color2.getAlpha();
        return new Color(Math.round((float)n6 + (float)(n2 - n6) * f2), Math.round((float)n7 + (float)(n3 - n7) * f2), Math.round((float)n8 + (float)(n4 - n8) * f2), Math.round((float)n9 + (float)(n5 - n9) * f2));
    }

    public static class Fade
    implements ColorFunction {
        public final float amount;

        public Fade(float f2) {
            this.amount = f2;
        }

        @Override
        public void apply(float[] arrf) {
            arrf[(int)1352780844L ^ 1352780847] = ColorFunctions.clamp(this.amount);
        }

        public String toString() {
            Object[] arrobject = new Object[(int)((long)1583468587 ^ (long)1583468586)];
            arrobject[(int)1044930338L ^ 1044930338] = Float.valueOf(this.amount);
            return String.format("fade(%.0f%%)", arrobject);
        }
    }

    public static class HSLIncreaseDecrease
    implements ColorFunction {
        public final int hslIndex;
        public final boolean increase;
        public final float amount;
        public final boolean relative;
        public final boolean autoInverse;

        public HSLIncreaseDecrease(int n2, boolean bl, float f2, boolean bl2, boolean bl3) {
            this.hslIndex = n2;
            this.increase = bl;
            this.amount = f2;
            this.relative = bl2;
            this.autoInverse = bl3;
        }

        @Override
        public void apply(float[] arrf) {
            float f2;
            float f3 = f2 = this.increase ? this.amount : -this.amount;
            if (this.hslIndex == 0) {
                arrf[(int)-656673278L ^ -656673278] = (arrf[(int)((long)-506060342 ^ (long)-506060342)] + f2) % Float.intBitsToFloat((int)((long)1326889684 ^ (long)211991252));
                return;
            }
            f2 = this.autoInverse && this.shouldInverse(arrf) ? -f2 : f2;
            arrf[this.hslIndex] = ColorFunctions.clamp(this.relative ? arrf[this.hslIndex] * ((Float.intBitsToFloat((int)((long)1463887795 ^ (long)361310131)) + f2) / Float.intBitsToFloat(0x35662264 ^ 0x77AE2264)) : arrf[this.hslIndex] + f2);
        }

        protected boolean shouldInverse(float[] arrf) {
            return (this.increase ? (arrf[this.hslIndex] > Float.intBitsToFloat((int)((long)1955326014 ^ (long)906618942)) ? (int)1932412210L ^ 0x732E4533 : (int)((long)2139041034 ^ (long)2139041034)) : (arrf[this.hslIndex] < Float.intBitsToFloat((int)((long)1304393495 ^ (long)263419671)) ? (int)((long)1864839825 ^ (long)1864839824) : (int)((long)1433680087 ^ (long)1433680087))) != 0;
        }

        public String toString() {
            String string;
            switch (this.hslIndex) {
                case 0: {
                    string = "spin";
                    break;
                }
                case 1: {
                    string = this.increase ? "saturate" : "desaturate";
                    break;
                }
                case 2: {
                    string = this.increase ? "lighten" : "darken";
                    break;
                }
                case 3: {
                    string = this.increase ? "fadein" : "fadeout";
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            Object[] arrobject = new Object[(int)((long)-1279847409 ^ (long)-1279847410) << 2];
            arrobject[(int)-1571975709L ^ -1571975709] = string;
            arrobject[(int)-404980237L ^ -404980238] = Float.valueOf(this.amount);
            arrobject[((int)-739020753L ^ -739020754) << 1] = this.relative ? " relative" : "";
            arrobject[(int)((long)71638152 ^ (long)71638155)] = this.autoInverse ? " autoInverse" : "";
            return String.format("%s(%.0f%%%s%s)", arrobject);
        }
    }

    public static interface ColorFunction {
        public void apply(float[] var1);
    }
}

