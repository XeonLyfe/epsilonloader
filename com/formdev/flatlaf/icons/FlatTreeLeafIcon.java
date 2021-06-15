/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeLeafIcon
extends FlatAbstractIcon {
    public FlatTreeLeafIcon() {
        super(((int)-2138837832L ^ 0x8083ECB9) << 4, (int)((long)-309952503 ^ (long)-309952504) << 4, UIManager.getColor("Tree.icon.leafColor"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        double[] arrd = new double[(int)((long)1732538341 ^ (long)1732538342) << 2];
        arrd[(int)((long)487431498 ^ (long)487431498)] = Double.longBitsToDouble(0xD92267A23169B811L ^ 0x990267A23169B811L);
        arrd[(int)500706899L ^ 500706898] = Double.longBitsToDouble(0x17EA849EC034A438L ^ 0x57F2849EC034A438L);
        arrd[((int)1734720687L ^ 1734720686) << 1] = Double.longBitsToDouble(0xD326286AC0B94306L ^ 0x9306286AC0B94306L);
        arrd[(int)1037480130L ^ 1037480129] = 1.0;
        arrd[((int)-1463450144L ^ -1463450143) << 2] = Double.longBitsToDouble(0xB7AC029B439DAE43L ^ 0xF786029B439DAE43L);
        arrd[(int)1057391232L ^ 1057391237] = 1.0;
        arrd[((int)-1698036965L ^ -1698036968) << 1] = Double.longBitsToDouble(0x5F082F8946A9D725L ^ 0x1F222F8946A9D725L);
        arrd[(int)1221742863L ^ 1221742856] = Double.longBitsToDouble(0x25C1522AC1471632L ^ 0x65EF522AC1471632L);
        arrd[((int)-354099728L ^ -354099727) << 3] = Double.longBitsToDouble(0xE4B66AEE636D7D3EL ^ 0xA4BE6AEE636D7D3EL);
        arrd[(int)((long)318142705 ^ (long)318142712)] = Double.longBitsToDouble(0x13B2F14461D2B27BL ^ 0x539CF14461D2B27BL);
        arrd[(int)((long)-751780961 ^ (long)-751780966) << 1] = Double.longBitsToDouble((long)1660796455 ^ 0x4008000062FDBE27L);
        arrd[(int)965873667L ^ 965873672] = Double.longBitsToDouble((long)589344225 ^ 0x401800002320ADE1L);
        graphics2D.fill(FlatUIUtils.createPath(arrd));
        double[] arrd2 = new double[(int)((long)504077120 ^ (long)504077123) << 1];
        arrd2[(int)815442828L ^ 815442828] = Double.longBitsToDouble(0x85724C478974913DL ^ 0xC57A4C478974913DL);
        arrd2[(int)-117240619L ^ -117240620] = Double.longBitsToDouble(0x8B1C3EBE38273252L ^ 0xCB083EBE38273252L);
        arrd2[((int)-1405668349L ^ -1405668350) << 1] = Double.longBitsToDouble((long)1959176946 ^ 0x401C000074C6AAF2L);
        arrd2[(int)-1593602515L ^ -1593602514] = Double.longBitsToDouble(0x80D9198F005C6BD3L ^ 0xC0CD198F005C6BD3L);
        arrd2[((int)1982850963L ^ 1982850962) << 2] = Double.longBitsToDouble((long)1175501180 ^ 0x401C00004610B97CL);
        arrd2[(int)-181325655L ^ -181325652] = 1.0;
        graphics2D.fill(FlatUIUtils.createPath(arrd2));
    }
}

