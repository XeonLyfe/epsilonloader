/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatFileViewDirectoryIcon
extends FlatAbstractIcon {
    public FlatFileViewDirectoryIcon() {
        super((int)((long)-1488955394 ^ (long)-1488955393) << 4, (int)((long)-1143721688 ^ (long)-1143721687) << 4, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        double[] arrd = new double[(int)((long)-1602386707 ^ (long)-1602386706) << 2];
        arrd[(int)((long)793455198 ^ (long)793455198)] = 1.0;
        arrd[(int)-505871267L ^ -505871268] = Double.longBitsToDouble((long)108263428 ^ 0x400000000673F804L);
        arrd[(int)((long)510889584 ^ (long)510889585) << 1] = Double.longBitsToDouble((long)758930255 ^ 0x401800002D3C5B4FL);
        arrd[(int)1554002857L ^ 1554002858] = Double.longBitsToDouble((long)1824039665 ^ 0x400000006CB8A2F1L);
        arrd[((int)222001774L ^ 222001775) << 2] = Double.longBitsToDouble((long)63523168 ^ 0x4020000003C94960L);
        arrd[(int)((long)-2128159901 ^ (long)-2128159898)] = Double.longBitsToDouble((long)2033467604 ^ 0x40100000793440D4L);
        arrd[(int)((long)-1681420851 ^ (long)-1681420850) << 1] = Double.longBitsToDouble((long)1789295745 ^ 0x402E00006AA67C81L);
        arrd[(int)((long)39228251 ^ (long)39228252)] = Double.longBitsToDouble((long)1136378417 ^ 0x4010000043BBC231L);
        arrd[(int)((long)-1993567484 ^ (long)-1993567483) << 3] = Double.longBitsToDouble(0xC1B1B4A8C00A95ACL ^ 0x819FB4A8C00A95ACL);
        arrd[(int)2115815640L ^ 2115815633] = Double.longBitsToDouble((long)1267824400 ^ 0x402A00004B917710L);
        arrd[(int)((long)302800681 ^ (long)302800684) << 1] = 1.0;
        arrd[(int)-10321503L ^ -10321494] = Double.longBitsToDouble((long)455539931 ^ 0x402A00001B26FCDBL);
        graphics2D.fill(FlatUIUtils.createPath(arrd));
    }
}

