/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import java.awt.Component;
import java.awt.Graphics2D;

public class FlatInternalFrameIconifyIcon
extends FlatInternalFrameAbstractIcon {
    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.paintBackground(component, graphics2D);
        graphics2D.setColor(component.getForeground());
        graphics2D.fillRect(this.width / ((int)((long)695208235 ^ (long)695208234) << 1) - (((int)-413720711L ^ 0xE7571F78) << 2), this.height / ((int)((long)2111646231 ^ (long)2111646230) << 1), ((int)-43891989L ^ 0xFD6242EA) << 3, (int)((long)-724924601 ^ (long)-724924602));
    }
}

