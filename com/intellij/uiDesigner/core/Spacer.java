/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import java.awt.Dimension;
import javax.swing.JComponent;

public class Spacer
extends JComponent {
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }

    @Override
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
}

