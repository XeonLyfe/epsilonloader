/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import javax.swing.UIManager;

public class FlatTextBorder
extends FlatBorder {
    protected final int arc = UIManager.getInt("TextComponent.arc");

    @Override
    protected int getArc(Component component) {
        if (this.isCellEditor(component)) {
            return (int)((long)40592634 ^ (long)40592634);
        }
        Boolean bl = FlatUIUtils.isRoundRect(component);
        return bl != null ? (bl.booleanValue() ? (int)-1907897858L ^ 0x8E47B601 : (int)619532375L ^ 0x24ED5057) : this.arc;
    }
}

