/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneInformationIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneInformationIcon() {
        super("OptionPane.icon.informationColor", "Actions.Blue");
    }

    @Override
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, Float.intBitsToFloat((int)905957481L ^ 0x741FD069), Float.intBitsToFloat((int)987108561L ^ 0x7B3614D1));
    }

    @Override
    protected Shape createInside() {
        Path2D.Float float_ = new Path2D.Float((int)2059867507L ^ 0x7AC71573);
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)434315506 ^ (long)1484988658)), Float.intBitsToFloat((int)((long)1722432875 ^ (long)667565419)), Float.intBitsToFloat((int)((long)120962659 ^ (long)1203093091)), Float.intBitsToFloat(0x53E8B6EB ^ 0x12D8B6EB)), (boolean)((long)1120669433 ^ (long)1120669433));
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat(0x368C32CA ^ 0x77EC32CA), Float.intBitsToFloat(0x2FE2403B ^ 0x6F02403B), Float.intBitsToFloat((int)1549315006L ^ 0x1CD8ABBE), Float.intBitsToFloat(0x237E4B4C ^ 0x63FE4B4C)), (boolean)((long)-1928113767 ^ (long)-1928113767));
        return float_;
    }
}

