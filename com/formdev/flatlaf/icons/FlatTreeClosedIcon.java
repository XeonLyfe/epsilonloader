/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeClosedIcon
extends FlatAbstractIcon {
    public FlatTreeClosedIcon() {
        super(((int)476389149L ^ 0x1C651F1C) << 4, ((int)1844351631L ^ 0x6DEE928E) << 4, UIManager.getColor("Tree.icon.closedColor"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        double[] arrd = new double[(int)((long)-1467943340 ^ (long)-1467943337) << 2];
        arrd[(int)((long)1861847015 ^ (long)1861847015)] = 1.0;
        arrd[(int)((long)0x2DDD2DD6 ^ (long)0x2DDD2DD7)] = Double.longBitsToDouble(0x283227249B418E9BL ^ 0x683227249B418E9BL);
        arrd[(int)((long)-931657419 ^ (long)-931657420) << 1] = Double.longBitsToDouble((long)1943740701 ^ 0x4018000073DB211DL);
        arrd[(int)((long)464139376 ^ (long)464139379)] = Double.longBitsToDouble((long)1810708116 ^ 0x400000006BED3694L);
        arrd[((int)-1044492527L ^ -1044492528) << 2] = Double.longBitsToDouble((long)2086487528 ^ 0x402000007C5D45E8L);
        arrd[(int)1282510584L ^ 1282510589] = Double.longBitsToDouble(0xF7BBDFE2A27A2DCL ^ 0x4F6BBDFE2A27A2DCL);
        arrd[(int)((long)642279366 ^ (long)642279365) << 1] = Double.longBitsToDouble(0xE72E81D15F1D62C0L ^ 0xA70081D15F1D62C0L);
        arrd[(int)((long)691653376 ^ (long)691653383)] = Double.longBitsToDouble((long)1753434001 ^ 0x4010000068834791L);
        arrd[(int)((long)-1769091479 ^ (long)-1769091480) << 3] = Double.longBitsToDouble((long)1694466936 ^ 0x402E000064FF8378L);
        arrd[(int)-1864103455L ^ -1864103448] = Double.longBitsToDouble((long)598877463 ^ 0x402A000023B22517L);
        arrd[(int)((long)718259595 ^ (long)718259598) << 1] = 1.0;
        arrd[(int)((long)450948658 ^ (long)450948665)] = Double.longBitsToDouble(0xF4D1C62B9BD2A59AL ^ 0xB4FBC62B9BD2A59AL);
        graphics2D.fill(FlatUIUtils.createPath(arrd));
    }
}

