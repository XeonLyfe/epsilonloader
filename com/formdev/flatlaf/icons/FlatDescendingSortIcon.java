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
import javax.swing.UIManager;

public class FlatDescendingSortIcon
extends FlatAbstractIcon {
    protected final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    protected final Color sortIconColor = UIManager.getColor("Table.sortIconColor");

    public FlatDescendingSortIcon() {
        super(((int)-528443855L ^ 0xE0809634) << 1, (int)((long)1463859974 ^ (long)1463859971), null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        graphics2D.setColor(this.sortIconColor);
        if (this.chevron) {
            double[] arrd = new double[(int)((long)-1665785860 ^ (long)-1665785857) << 1];
            arrd[(int)((long)2051394148 ^ (long)2051394148)] = 1.0;
            arrd[(int)((long)-494997070 ^ (long)-494997069)] = 0.0;
            arrd[(int)((long)-1762282282 ^ (long)-1762282281) << 1] = Double.longBitsToDouble((long)1242462079 ^ 0x401400004A0E777FL);
            arrd[(int)((long)-302635667 ^ (long)-302635666)] = Double.longBitsToDouble((long)20393750 ^ 0x4010000001372F16L);
            arrd[(int)((long)1828212772 ^ (long)1828212773) << 2] = Double.longBitsToDouble((long)1307961417 ^ 0x402200004DF5E849L);
            arrd[(int)((long)422434437 ^ (long)422434432)] = 0.0;
            Path2D path2D = FlatUIUtils.createPath((boolean)((long)-2005613510 ^ (long)-2005613510), arrd);
            graphics2D.setStroke(new BasicStroke(1.0f));
            graphics2D.draw(path2D);
        } else {
            double[] arrd = new double[((int)355103616L ^ 0x152A7383) << 1];
            arrd[(int)516732605L ^ 516732605] = Double.longBitsToDouble((long)782603839 ^ 0x3FE000002EA5963FL);
            arrd[(int)-318335515L ^ -318335516] = 0.0;
            arrd[((int)-1561309152L ^ -1561309151) << 1] = Double.longBitsToDouble(0x3F94F0FF7213CF7EL ^ 0x7F80F0FF7213CF7EL);
            arrd[(int)((long)1457036999 ^ (long)1457036996)] = Double.longBitsToDouble(0x7FCBB7D409D71038L ^ 0x3FDFB7D409D71038L);
            arrd[((int)-317597764L ^ -317597763) << 2] = Double.longBitsToDouble(0x4B40CF71ACDBD78L ^ 0x44970CF71ACDBD78L);
            arrd[(int)331190896L ^ 331190901] = 0.0;
            graphics2D.fill(FlatUIUtils.createPath(arrd));
        }
    }
}

