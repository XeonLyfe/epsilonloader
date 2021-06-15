/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatInternalFrameRestoreIcon
extends FlatInternalFrameAbstractIcon {
    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.paintBackground(component, graphics2D);
        graphics2D.setColor(component.getForeground());
        int n2 = this.width / ((int)((long)581209291 ^ (long)581209290) << 1) - ((int)((long)1500136433 ^ (long)1500136432) << 2);
        int n3 = this.height / ((int)((long)-1218450087 ^ (long)-1218450088) << 1) - (((int)2064901996L ^ 0x7B13E76D) << 2);
        Path2D path2D = FlatUIUtils.createRectangle(n2 + ((int)1156006726L ^ 0x44E74347), n3 - (int)((long)-1314918512 ^ (long)-1314918511), Float.intBitsToFloat((int)((long)705439193 ^ (long)1795958233)), Float.intBitsToFloat((int)((long)151600635 ^ (long)1208565243)), 1.0f);
        Path2D path2D2 = FlatUIUtils.createRectangle(n2 - (int)((long)504950769 ^ (long)504950768), n3 + ((int)1564634781L ^ 0x5D426E9C), Float.intBitsToFloat((int)950002148L ^ 0x799FE1E4), Float.intBitsToFloat(0x2C64C1C1 ^ 0x6D64C1C1), 1.0f);
        Area area = new Area(path2D);
        area.subtract(new Area(new Rectangle2D.Float(n2 - (int)((long)1050101842 ^ (long)1050101843), n3 + ((int)783736952L ^ 0x2EB6E079), Float.intBitsToFloat((int)((long)941941610 ^ (long)2032460650)), Float.intBitsToFloat((int)((long)1827732046 ^ (long)770767438)))));
        graphics2D.fill(area);
        graphics2D.fill(path2D2);
    }
}

