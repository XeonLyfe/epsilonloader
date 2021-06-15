/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;

public class FlatInternalFrameMaximizeIcon
extends FlatInternalFrameAbstractIcon {
    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.paintBackground(component, graphics2D);
        graphics2D.setColor(component.getForeground());
        graphics2D.fill(FlatUIUtils.createRectangle(this.width / (((int)-1934894338L ^ 0x8CABDAFF) << 1) - ((int)((long)633580888 ^ (long)633580889) << 2), this.height / (((int)1945661159L ^ 0x73F86EE6) << 1) - (((int)632711512L ^ 0x25B66959) << 2), Float.intBitsToFloat((int)((long)978033453 ^ (long)2068552493)), Float.intBitsToFloat((int)((long)952941534 ^ (long)2043460574)), 1.0f));
    }
}

