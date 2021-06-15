/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.awt.image.RGBImageFilter;

public class GrayFilter
extends RGBImageFilter {
    private final float brightness;
    private final float contrast;
    private final int alpha;
    private final int origContrast;
    private final int origBrightness;

    public static GrayFilter createDisabledIconFilter(boolean bl) {
        return bl ? new GrayFilter((int)((long)865075223 ^ (long)-865075205), (int)((long)2122740210 ^ (long)-2122740152), ((int)555047244L ^ 0x21155955) << 2) : new GrayFilter((int)((long)1582958232 ^ (long)1582958209), (int)1142448584L ^ 0xBBE79E2F, (int)((long)-884226782 ^ (long)-884226757) << 2);
    }

    public GrayFilter(int n2, int n3, int n4) {
        this.origBrightness = Math.max((int)((long)1434487085 ^ (long)-1434487119), Math.min(((int)-683043187L ^ 0xD7499694) << 2, n2));
        this.origContrast = Math.max((int)-2047017672L ^ 0x7A0302A4, Math.min(((int)1202637634L ^ 0x47AECB5B) << 2, n3));
        this.alpha = Math.max((int)439725548L ^ 0x1A35ADEC, Math.min((int)((long)1328528615 ^ (long)1328528638) << 2, n4));
        this.brightness = (float)(Math.pow(this.origBrightness, Double.longBitsToDouble((long)1039759126 ^ 0x400800003DF97716L)) / Double.longBitsToDouble((long)2082304890 ^ 0x40C388007C1D737AL));
        this.contrast = (float)this.origContrast / Float.intBitsToFloat((int)1999473221L ^ 0x35E58A45);
        this.canFilterIndexColorModel = (int)251255312L ^ 0xEF9DA11;
    }

    public GrayFilter() {
        this((int)219608414L ^ 0xD16F55E, (int)71122612L ^ 0x43D3EB4, (int)((long)-174298472 ^ (long)-174298495) << 2);
    }

    public int getBrightness() {
        return this.origBrightness;
    }

    public int getContrast() {
        return this.origContrast;
    }

    public int getAlpha() {
        return this.alpha;
    }

    @Override
    public int filterRGB(int n2, int n3, int n4) {
        int n5 = (int)(Double.longBitsToDouble(0xBBC1151E251D1E99L ^ 0x8412262D162E2DAAL) * (double)(n4 >> ((int)((long)-1964652915 ^ (long)-1964652916) << 4) & (int)((long)-1181287648 ^ (long)-1181287457)) + Double.longBitsToDouble(0x55D7A3C1E2F648D4L ^ 0x6A3542864CE23235L) * (double)(n4 >> ((int)((long)-1525609776 ^ (long)-1525609775) << 3) & (int)((long)107666837 ^ (long)0x66ADD6A)) + Double.longBitsToDouble(0xE97BF119F2D72A5EL ^ 0xD6C7D9EC30587677L) * (double)(n4 & (int)((long)-1902918519 ^ (long)-1902918538)));
        n5 = this.brightness >= 0.0f ? (int)(((float)n5 + this.brightness * Float.intBitsToFloat((int)((long)1797491517 ^ (long)677153597))) / (1.0f + this.brightness)) : (int)((float)n5 / (1.0f - this.brightness));
        n5 = this.contrast >= 0.0f ? (n5 >= ((int)60008541L ^ 0x393A822) ? (int)((float)n5 + (float)(((int)743962477L ^ 0x2C57F792) - n5) * this.contrast) : (int)((float)n5 - (float)n5 * this.contrast)) : (int)(Float.intBitsToFloat((int)((long)1221873300 ^ (long)170544788)) + (float)(n5 - (int)((long)365553892 ^ (long)365553819)) * (this.contrast + 1.0f));
        int n6 = this.alpha != (int)((long)-368128317 ^ (long)-368128294) << 2 ? (n4 >> ((int)((long)1415892947 ^ (long)1415892944) << 3) & (int)((long)-1777196599 ^ (long)-1777196746)) * this.alpha / ((int)((long)-449502139 ^ (long)-449502116) << 2) << ((int)((long)1496906314 ^ (long)1496906313) << 3) : n4 & (int)((long)1771456237 ^ (long)-1768536339);
        return n6 | n5 << (((int)-827077950L ^ 0xCEB3CAC3) << 4) | n5 << ((int)((long)61835152 ^ (long)61835153) << 3) | n5;
    }
}

