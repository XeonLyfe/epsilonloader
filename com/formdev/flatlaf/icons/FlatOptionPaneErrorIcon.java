/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneErrorIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneErrorIcon() {
        super("OptionPane.icon.errorColor", "Actions.Red");
    }

    @Override
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, Float.intBitsToFloat((int)((long)404029866 ^ (long)1509228970)), Float.intBitsToFloat((int)((long)639720154 ^ (long)1740724954)));
    }

    @Override
    protected Shape createInside() {
        Path2D.Float float_ = new Path2D.Float((int)-531757137L ^ 0xE04E07AF);
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)205892678L ^ 0x4D25AC46), Float.intBitsToFloat((int)((long)1165535910 ^ (long)93891238)), Float.intBitsToFloat((int)((long)937740147 ^ (long)2003093363)), Float.intBitsToFloat((int)1358556694L ^ 0x11C9EE16)), (boolean)((long)468284924 ^ (long)468284924));
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)1942811639 ^ (long)850195447)), Float.intBitsToFloat((int)((long)1636891183 ^ (long)540604975)), Float.intBitsToFloat((int)1869279258L ^ 0x2FEAF01A), Float.intBitsToFloat((int)1942226977L ^ 0x33440821)), (boolean)((long)1502985415 ^ (long)1502985415));
        return float_;
    }
}

