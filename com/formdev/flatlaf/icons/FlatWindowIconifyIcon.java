/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatWindowAbstractIcon;
import java.awt.Graphics2D;

public class FlatWindowIconifyIcon
extends FlatWindowAbstractIcon {
    @Override
    protected void paintIconAt1x(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
        int n6 = (int)(Double.longBitsToDouble(0x9843A6CEA4D62B51L ^ 0xD867A6CEA4D62B51L) * d2);
        int n7 = (int)d2;
        int n8 = n2 + (n4 - n6) / (((int)990256699L ^ 0x3B061E3A) << 1);
        int n9 = n3 + (n5 - n7) / (((int)-1832324116L ^ 0x92C8F3ED) << 1);
        graphics2D.fillRect(n8, n9, n6, n7);
    }
}

