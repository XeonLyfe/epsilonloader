/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

public final class LayoutState {
    private final Component[] myComponents;
    private final GridConstraints[] myConstraints;
    private final int myColumnCount;
    private final int myRowCount;
    final Dimension[] myPreferredSizes;
    final Dimension[] myMinimumSizes;

    public LayoutState(GridLayoutManager gridLayoutManager, boolean bl) {
        ArrayList<Component> arrayList = new ArrayList<Component>(gridLayoutManager.getComponentCount());
        ArrayList<GridConstraints> arrayList2 = new ArrayList<GridConstraints>(gridLayoutManager.getComponentCount());
        for (int i2 = 0; i2 < gridLayoutManager.getComponentCount(); ++i2) {
            Component component = gridLayoutManager.getComponent(i2);
            if (bl && !component.isVisible()) continue;
            arrayList.add(component);
            GridConstraints gridConstraints = gridLayoutManager.getConstraints(i2);
            arrayList2.add(gridConstraints);
        }
        this.myComponents = arrayList.toArray(new Component[0]);
        this.myConstraints = arrayList2.toArray(GridConstraints.EMPTY_ARRAY);
        this.myMinimumSizes = new Dimension[this.myComponents.length];
        this.myPreferredSizes = new Dimension[this.myComponents.length];
        this.myColumnCount = gridLayoutManager.getColumnCount();
        this.myRowCount = gridLayoutManager.getRowCount();
    }

    public int getComponentCount() {
        return this.myComponents.length;
    }

    public Component getComponent(int n2) {
        return this.myComponents[n2];
    }

    public GridConstraints getConstraints(int n2) {
        return this.myConstraints[n2];
    }

    public int getColumnCount() {
        return this.myColumnCount;
    }

    public int getRowCount() {
        return this.myRowCount;
    }
}

