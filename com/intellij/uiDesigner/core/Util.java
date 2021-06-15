/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

public final class Util {
    private static final Dimension MAX_SIZE = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    public static final int DEFAULT_INDENT = 10;

    public static Dimension getMinimumSize(Component component, GridConstraints gridConstraints, boolean bl) {
        try {
            Dimension dimension = Util.getSize(gridConstraints.myMinimumSize, component.getMinimumSize());
            if (bl) {
                dimension.width += 10 * gridConstraints.getIndent();
            }
            return dimension;
        }
        catch (NullPointerException nullPointerException) {
            return new Dimension(0, 0);
        }
    }

    public static Dimension getMaximumSize(GridConstraints gridConstraints, boolean bl) {
        try {
            Dimension dimension = Util.getSize(gridConstraints.myMaximumSize, MAX_SIZE);
            if (bl && dimension.width < Util.MAX_SIZE.width) {
                dimension.width += 10 * gridConstraints.getIndent();
            }
            return dimension;
        }
        catch (NullPointerException nullPointerException) {
            return new Dimension(0, 0);
        }
    }

    public static Dimension getPreferredSize(Component component, GridConstraints gridConstraints, boolean bl) {
        try {
            Dimension dimension = Util.getSize(gridConstraints.myPreferredSize, component.getPreferredSize());
            if (bl) {
                dimension.width += 10 * gridConstraints.getIndent();
            }
            return dimension;
        }
        catch (NullPointerException nullPointerException) {
            return new Dimension(0, 0);
        }
    }

    private static Dimension getSize(Dimension dimension, Dimension dimension2) {
        int n2 = dimension.width >= 0 ? dimension.width : dimension2.width;
        int n3 = dimension.height >= 0 ? dimension.height : dimension2.height;
        return new Dimension(n2, n3);
    }

    public static void adjustSize(Component component, GridConstraints gridConstraints, Dimension dimension) {
        Dimension dimension2 = Util.getMinimumSize(component, gridConstraints, false);
        Dimension dimension3 = Util.getMaximumSize(gridConstraints, false);
        dimension.width = Math.max(dimension.width, dimension2.width);
        dimension.height = Math.max(dimension.height, dimension2.height);
        dimension.width = Math.min(dimension.width, dimension3.width);
        dimension.height = Math.min(dimension.height, dimension3.height);
    }

    public static int eliminate(int[] arrn, int[] arrn2, ArrayList arrayList) {
        int n2;
        int n3 = arrn.length;
        if (n3 != arrn2.length) {
            throw new IllegalArgumentException("size mismatch: " + n3 + ", " + arrn2.length);
        }
        if (arrayList != null && arrayList.size() != 0) {
            throw new IllegalArgumentException("eliminated must be empty");
        }
        int n4 = 0;
        for (n2 = 0; n2 < n3; ++n2) {
            n4 = Math.max(n4, arrn[n2] + arrn2[n2]);
        }
        for (n2 = n4 - 1; n2 >= 0; --n2) {
            int n5;
            boolean bl = false;
            boolean bl2 = false;
            for (n5 = 0; n5 < n3; ++n5) {
                if (arrn[n5] == n2) {
                    bl = true;
                }
                if (arrn[n5] + arrn2[n5] - 1 != n2) continue;
                bl2 = true;
            }
            if (bl && bl2) continue;
            if (arrayList != null) {
                arrayList.add(new Integer(n2));
            }
            for (n5 = 0; n5 < n3; ++n5) {
                boolean bl3;
                boolean bl4 = arrn[n5] <= n2 && n2 < arrn[n5] + arrn2[n5];
                boolean bl5 = bl3 = arrn[n5] > n2;
                if (bl4) {
                    int n6 = n5;
                    arrn2[n6] = arrn2[n6] - 1;
                }
                if (!bl3) continue;
                int n7 = n5;
                arrn[n7] = arrn[n7] - 1;
            }
            --n4;
        }
        return n4;
    }
}

