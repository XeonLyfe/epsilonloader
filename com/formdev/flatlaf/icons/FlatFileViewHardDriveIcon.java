/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.UIManager;

public class FlatFileViewHardDriveIcon
extends FlatAbstractIcon {
    public FlatFileViewHardDriveIcon() {
        super(((int)-1738201704L ^ 0x98652599) << 4, (int)((long)622580797 ^ (long)622580796) << 4, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        Path2D.Float float_ = new Path2D.Float((int)((long)-1195614285 ^ (long)-1195614285));
        float_.append(new Rectangle2D.Float(2.0f, Float.intBitsToFloat(0x5A7C31EB ^ 0x1ABC31EB), Float.intBitsToFloat((int)158483956L ^ 0x483245F4), Float.intBitsToFloat((int)((long)1065350751 ^ (long)2147481183))), (boolean)((long)-703874906 ^ (long)-703874906));
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)992396993 ^ (long)2053555905)), Float.intBitsToFloat((int)((long)354867983 ^ (long)1411832591)), 1.0f, 1.0f), ((int)-774609994L ^ 0xD1D463B6) != 0);
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)238445484 ^ (long)1326867372)), Float.intBitsToFloat(0x477EAF86 ^ 0x67EAF86), 1.0f, 1.0f), ((int)1753491988L ^ 0x68842A14) != 0);
        graphics2D.fill(float_);
    }
}

