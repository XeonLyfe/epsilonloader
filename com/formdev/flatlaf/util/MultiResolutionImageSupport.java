/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MultiResolutionImageSupport {
    public static boolean isAvailable() {
        return (int)((long)-1979958154 ^ (long)-1979958154) != 0;
    }

    public static boolean isMultiResolutionImage(Image image) {
        return ((int)-865018644L ^ 0xCC70DCEC) != 0;
    }

    public static Image create(int n2, Image ... arrimage) {
        return arrimage[n2];
    }

    public static Image create(int n2, Dimension[] arrdimension, Function<Dimension, Image> function) {
        return function.apply(arrdimension[n2]);
    }

    public static Image map(Image image, Function<Image, Image> function) {
        return function.apply(image);
    }

    public static Image getResolutionVariant(Image image, int n2, int n3) {
        return image;
    }

    public static List<Image> getResolutionVariants(Image image) {
        return Collections.singletonList(image);
    }
}

