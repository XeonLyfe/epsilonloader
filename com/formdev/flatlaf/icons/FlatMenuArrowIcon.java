/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JMenu;
import javax.swing.UIManager;

public class FlatMenuArrowIcon
extends FlatAbstractIcon {
    protected final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    protected final Color arrowColor = UIManager.getColor("Menu.icon.arrowColor");
    protected final Color disabledArrowColor = UIManager.getColor("Menu.icon.disabledArrowColor");
    protected final Color selectionForeground = UIManager.getColor("Menu.selectionForeground");

    public FlatMenuArrowIcon() {
        super((int)((long)-1060986584 ^ (long)-1060986581) << 1, ((int)-607191207L ^ 0xDBCEFF5C) << 1, null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        if (!component.getComponentOrientation().isLeftToRight()) {
            graphics2D.rotate(Math.toRadians(Double.longBitsToDouble((long)595500061 ^ 0x40668000237E9C1DL)), (double)this.width / Double.longBitsToDouble(0x33319B95F5A556FL ^ 0x433319B95F5A556FL), (double)this.height / Double.longBitsToDouble(0xDCDCE2FBFAF9899L ^ 0x4DCDCE2FBFAF9899L));
        }
        graphics2D.setColor(this.getArrowColor(component));
        if (this.chevron) {
            double[] arrd = new double[(int)((long)-901026665 ^ (long)-901026668) << 1];
            arrd[(int)((long)-1700244589 ^ (long)-1700244589)] = 1.0;
            arrd[(int)((long)420321067 ^ (long)420321066)] = 1.0;
            arrd[(int)((long)1219281513 ^ (long)1219281512) << 1] = Double.longBitsToDouble(0x58CAA5B6A681A0B5L ^ 0x18DEA5B6A681A0B5L);
            arrd[(int)((long)-2027990839 ^ (long)-2027990838)] = Double.longBitsToDouble(0x2D62D8AC96A4A122L ^ 0x6D76D8AC96A4A122L);
            arrd[(int)((long)1293467498 ^ (long)1293467499) << 2] = 1.0;
            arrd[(int)((long)-502376542 ^ (long)-502376537)] = Double.longBitsToDouble((long)958889864 ^ 0x4022000039277F88L);
            Path2D path2D = FlatUIUtils.createPath(((int)-1257366111L ^ 0xB50E1DA1) != 0, arrd);
            graphics2D.setStroke(new BasicStroke(1.0f));
            graphics2D.draw(path2D);
        } else {
            double[] arrd = new double[((int)290795624L ^ 0x1155306B) << 1];
            arrd[(int)1732612704L ^ 1732612704] = 0.0;
            arrd[(int)445011045L ^ 445011044] = Double.longBitsToDouble((long)2103306269 ^ 0x3FE000007D5DE81DL);
            arrd[((int)-621832312L ^ -621832311) << 1] = Double.longBitsToDouble((long)1354518598 ^ 0x4014000050BC5046L);
            arrd[(int)-1162240393L ^ -1162240396] = Double.longBitsToDouble((long)1583073170 ^ 0x401400005E5BC792L);
            arrd[((int)-1493854450L ^ -1493854449) << 2] = 0.0;
            arrd[(int)((long)-2001694133 ^ (long)-2001694130)] = Double.longBitsToDouble((long)371293677 ^ 0x4023000016217DEDL);
            graphics2D.fill(FlatUIUtils.createPath(arrd));
        }
    }

    protected Color getArrowColor(Component component) {
        if (component instanceof JMenu && ((JMenu)component).isSelected() && !this.isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return component.isEnabled() ? this.arrowColor : this.disabledArrowColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}

