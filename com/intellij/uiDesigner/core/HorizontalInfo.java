/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.LayoutState;

final class HorizontalInfo
extends DimensionInfo {
    HorizontalInfo(LayoutState layoutState, int n2) {
        super(layoutState, n2);
    }

    @Override
    protected int getOriginalCell(GridConstraints gridConstraints) {
        return gridConstraints.getColumn();
    }

    @Override
    protected int getOriginalSpan(GridConstraints gridConstraints) {
        return gridConstraints.getColSpan();
    }

    @Override
    int getSizePolicy(int n2) {
        return this.myLayoutState.getConstraints(n2).getHSizePolicy();
    }

    @Override
    int getChildLayoutCellCount(GridLayoutManager gridLayoutManager) {
        return gridLayoutManager.getColumnCount();
    }

    @Override
    public int getMinimumWidth(int n2) {
        return this.getMinimumSize((int)n2).width;
    }

    @Override
    public DimensionInfo getDimensionInfo(GridLayoutManager gridLayoutManager) {
        return gridLayoutManager.myHorizontalInfo;
    }

    @Override
    public int getCellCount() {
        return this.myLayoutState.getColumnCount();
    }

    @Override
    public int getPreferredWidth(int n2) {
        return this.getPreferredSize((int)n2).width;
    }
}

