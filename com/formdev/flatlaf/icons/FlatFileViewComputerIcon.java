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

public class FlatFileViewComputerIcon
extends FlatAbstractIcon {
    public FlatFileViewComputerIcon() {
        super((int)((long)1100116364 ^ (long)1100116365) << 4, (int)((long)-13945073 ^ (long)-13945074) << 4, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        Path2D.Float float_ = new Path2D.Float((int)((long)-1160553175 ^ (long)-1160553175));
        float_.append(new Rectangle2D.Float(2.0f, Float.intBitsToFloat(0x4B623D7D ^ 0xB223D7D), Float.intBitsToFloat(0x120CB99E ^ 0x534CB99E), Float.intBitsToFloat((int)((long)793374713 ^ (long)1850339321))), (boolean)((long)390851208 ^ (long)390851208));
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat((int)((long)1420893199 ^ (long)338762767)), Float.intBitsToFloat(0x29636714 ^ 0x69C36714), Float.intBitsToFloat((int)975617618L ^ 0x7B26BE52), Float.intBitsToFloat(0x45CE325A ^ 0x54E325A)), ((int)-909923033L ^ 0xC9C3AD27) != 0);
        graphics2D.fill(float_);
        graphics2D.fillRect(((int)-737388035L ^ 0xD40C59FC) << 1, ((int)-1251076423L ^ 0xB56E16BA) << 2, ((int)1459838774L ^ 0x57035F35) << 2, (int)((long)827215111 ^ (long)827215110) << 1);
    }
}

