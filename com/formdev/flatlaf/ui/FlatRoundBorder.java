/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import javax.swing.UIManager;

public class FlatRoundBorder
extends FlatBorder {
    protected final int arc = UIManager.getInt("Component.arc");

    @Override
    protected int getArc(Component component) {
        if (this.isCellEditor(component)) {
            return (int)424428933L ^ 0x194C4585;
        }
        Boolean bl = FlatUIUtils.isRoundRect(component);
        return bl != null ? (bl.booleanValue() ? (int)-111737552L ^ 0xF9577ACF : (int)444122375L ^ 0x1A78C507) : this.arc;
    }
}

