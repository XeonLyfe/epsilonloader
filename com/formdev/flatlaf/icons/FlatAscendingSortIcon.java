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

public class FlatAscendingSortIcon
extends FlatAbstractIcon {
    protected final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    protected final Color sortIconColor = UIManager.getColor("Table.sortIconColor");

    public FlatAscendingSortIcon() {
        super(((int)-664098134L ^ 0xD86AAAAF) << 1, (int)-1131589351L ^ 0xBC8D511C, null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        graphics2D.setColor(this.sortIconColor);
        if (this.chevron) {
            double[] arrd = new double[(int)((long)157699791 ^ (long)157699788) << 1];
            arrd[(int)((long)2142227421 ^ (long)2142227421)] = 1.0;
            arrd[(int)((long)-1501847150 ^ (long)-1501847149)] = Double.longBitsToDouble(0x9757D12B0430D40FL ^ 0xD747D12B0430D40FL);
            arrd[((int)-1213087201L ^ -1213087202) << 1] = Double.longBitsToDouble((long)273099153 ^ 0x4014000010472991L);
            arrd[(int)1698537856L ^ 1698537859] = 0.0;
            arrd[(int)((long)-1903595498 ^ (long)-1903595497) << 2] = Double.longBitsToDouble(0x2295A5C2E5957632L ^ 0x62B7A5C2E5957632L);
            arrd[(int)((long)325687439 ^ (long)325687434)] = Double.longBitsToDouble(0xA82E407B4177844CL ^ 0xE83E407B4177844CL);
            Path2D path2D = FlatUIUtils.createPath(((int)-714141949L ^ 0xD56F0F03) != 0, arrd);
            graphics2D.setStroke(new BasicStroke(1.0f));
            graphics2D.draw(path2D);
        } else {
            double[] arrd = new double[(int)((long)-257017435 ^ (long)-257017434) << 1];
            arrd[(int)39560323L ^ 39560323] = Double.longBitsToDouble((long)955259529 ^ 0x3FE0000038F01A89L);
            arrd[(int)((long)971123062 ^ (long)971123063)] = Double.longBitsToDouble(0x172646EAC1807935L ^ 0x573246EAC1807935L);
            arrd[(int)((long)-223417732 ^ (long)-223417731) << 1] = Double.longBitsToDouble((long)186990359 ^ 0x401400000B253F17L);
            arrd[(int)602071795L ^ 602071792] = 0.0;
            arrd[((int)850972196L ^ 850972197) << 2] = Double.longBitsToDouble(0x6D73921D96B2506AL ^ 0x2D50921D96B2506AL);
            arrd[(int)((long)-1767511566 ^ (long)-1767511561)] = Double.longBitsToDouble(0x657BA2B4B214CDC7L ^ 0x256FA2B4B214CDC7L);
            graphics2D.fill(FlatUIUtils.createPath(arrd));
        }
    }
}

