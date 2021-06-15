/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneWarningIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneWarningIcon() {
        super("OptionPane.icon.warningColor", "Actions.Yellow");
    }

    @Override
    protected Shape createOutside() {
        double[] arrd = new double[((int)75794902L ^ 0x48489D5) << 1];
        arrd[(int)((long)-1381673863 ^ (long)-1381673863)] = Double.longBitsToDouble(0xF67F65CDCEA3531DL ^ 0xB64F65CDCEA3531DL);
        arrd[(int)((long)-549641067 ^ (long)-549641068)] = Double.longBitsToDouble((long)246821258 ^ 0x400000000EB6318AL);
        arrd[((int)-746748171L ^ -746748172) << 1] = Double.longBitsToDouble(0xD78E6F098934C5D6L ^ 0x97B16F098934C5D6L);
        arrd[(int)((long)-1932524738 ^ (long)-1932524739)] = Double.longBitsToDouble(0x6B2A48D304EDDBF4L ^ 0x2B1648D304EDDBF4L);
        arrd[(int)((long)-955783798 ^ (long)-955783797) << 2] = 1.0;
        arrd[(int)((long)-1469885070 ^ (long)-1469885065)] = Double.longBitsToDouble((long)1121582197 ^ 0x403C000042D9FC75L);
        return FlatUIUtils.createPath(arrd);
    }

    @Override
    protected Shape createInside() {
        Path2D.Float float_ = new Path2D.Float((int)488816241L ^ 0x1D22BE71);
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)729409027 ^ (long)1780082179)), Float.intBitsToFloat((int)2122538817L ^ 0x3FA35F41), Float.intBitsToFloat((int)((long)771875441 ^ (long)1854005873)), Float.intBitsToFloat((int)((long)2043114244 ^ (long)952595204))), (boolean)((long)-971810324 ^ (long)-971810324));
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)1629147213 ^ (long)544919629)), Float.intBitsToFloat((int)1959886855L ^ 0x35798007), Float.intBitsToFloat((int)1979580547L ^ 0x357E0083), Float.intBitsToFloat(0x6318EE7D ^ 0x2398EE7D)), ((int)578062221L ^ 0x2274878D) != 0);
        return float_;
    }
}

