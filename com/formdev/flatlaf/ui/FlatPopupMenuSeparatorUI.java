/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatSeparatorUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class FlatPopupMenuSeparatorUI
extends FlatSeparatorUI {
    public static ComponentUI createUI(JComponent jComponent) {
        return FlatUIUtils.createSharedUI(FlatPopupMenuSeparatorUI.class, FlatPopupMenuSeparatorUI::new);
    }

    @Override
    protected String getPropertyPrefix() {
        return "PopupMenuSeparator";
    }
}

