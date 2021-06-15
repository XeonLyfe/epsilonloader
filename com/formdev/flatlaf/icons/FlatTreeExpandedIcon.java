/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatTreeCollapsedIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeExpandedIcon
extends FlatTreeCollapsedIcon {
    public FlatTreeExpandedIcon() {
        super(UIManager.getColor("Tree.icon.expandedColor"));
    }

    @Override
    void rotate(Component component, Graphics2D graphics2D) {
        graphics2D.rotate(Math.toRadians(Double.longBitsToDouble(0xCE32C5AB47B77ABCL ^ 0x8E6445AB47B77ABCL)), (double)this.width / Double.longBitsToDouble(0xA99C942C1D83F882L ^ 0xE99C942C1D83F882L), (double)this.height / Double.longBitsToDouble(0xB06C845CDCC12AD0L ^ 0xF06C845CDCC12AD0L));
    }
}

