/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatWindowAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Graphics2D;

public class FlatWindowMaximizeIcon
extends FlatWindowAbstractIcon {
    @Override
    protected void paintIconAt1x(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
        int n6 = (int)(Double.longBitsToDouble(0x81D4C21D6A09DD81L ^ 0xC1F0C21D6A09DD81L) * d2);
        int n7 = n2 + (n4 - n6) / (((int)-1973503708L ^ 0x8A5EB925) << 1);
        int n8 = n3 + (n5 - n6) / (((int)-1137627160L ^ 0xBC312FE9) << 1);
        int n9 = (int)d2;
        graphics2D.fill(FlatUIUtils.createRectangle(n7, n8, n6, n6, n9));
    }
}

