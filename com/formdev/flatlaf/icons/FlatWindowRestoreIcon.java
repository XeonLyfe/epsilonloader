/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatWindowAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatWindowRestoreIcon
extends FlatWindowAbstractIcon {
    @Override
    protected void paintIconAt1x(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
        int n6 = (int)(Double.longBitsToDouble(0xCD621E95264481DBL ^ 0x8D461E95264481DBL) * d2);
        int n7 = n2 + (n4 - n6) / (((int)-1133282886L ^ 0xBC7379BB) << 1);
        int n8 = n3 + (n5 - n6) / (((int)713388667L ^ 0x2A85727A) << 1);
        int n9 = (int)d2;
        int n10 = (int)(Double.longBitsToDouble(0xFA69681B3739189CL ^ 0xBA49681B3739189CL) * d2);
        int n11 = n6 - n10;
        Path2D path2D = FlatUIUtils.createRectangle(n7 + n11, n8, n10, n10, n9);
        Path2D path2D2 = FlatUIUtils.createRectangle(n7, n8 + n11, n10, n10, n9);
        Area area = new Area(path2D);
        area.subtract(new Area(new Rectangle2D.Float(n7, n8 + n11, n10, n10)));
        graphics2D.fill(area);
        graphics2D.fill(path2D2);
    }
}

