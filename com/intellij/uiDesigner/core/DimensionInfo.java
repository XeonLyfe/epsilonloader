/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.LayoutState;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.uiDesigner.core.Util;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class DimensionInfo {
    private final int[] myCell;
    private final int[] mySpan;
    protected final LayoutState myLayoutState;
    private final int[] myStretches;
    private final int[] mySpansAfterElimination;
    private final int[] myCellSizePolicies;
    private final int myGap;

    public DimensionInfo(LayoutState layoutState, int n2) {
        if (layoutState == null) {
            throw new IllegalArgumentException("layoutState cannot be null");
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("invalid gap: " + n2);
        }
        this.myLayoutState = layoutState;
        this.myGap = n2;
        this.myCell = new int[layoutState.getComponentCount()];
        this.mySpan = new int[layoutState.getComponentCount()];
        for (int i2 = 0; i2 < layoutState.getComponentCount(); ++i2) {
            GridConstraints gridConstraints = layoutState.getConstraints(i2);
            this.myCell[i2] = this.getOriginalCell(gridConstraints);
            this.mySpan[i2] = this.getOriginalSpan(gridConstraints);
        }
        this.myStretches = new int[this.getCellCount()];
        Arrays.fill(this.myStretches, 1);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        this.mySpansAfterElimination = (int[])this.mySpan.clone();
        Util.eliminate((int[])this.myCell.clone(), this.mySpansAfterElimination, arrayList);
        this.myCellSizePolicies = new int[this.getCellCount()];
        for (int i3 = 0; i3 < this.myCellSizePolicies.length; ++i3) {
            this.myCellSizePolicies[i3] = this.getCellSizePolicyImpl(i3, arrayList);
        }
    }

    public final int getComponentCount() {
        return this.myLayoutState.getComponentCount();
    }

    public final Component getComponent(int n2) {
        return this.myLayoutState.getComponent(n2);
    }

    public final GridConstraints getConstraints(int n2) {
        return this.myLayoutState.getConstraints(n2);
    }

    public abstract int getCellCount();

    public abstract int getPreferredWidth(int var1);

    public abstract int getMinimumWidth(int var1);

    public abstract DimensionInfo getDimensionInfo(GridLayoutManager var1);

    public final int getCell(int n2) {
        return this.myCell[n2];
    }

    public final int getSpan(int n2) {
        return this.mySpan[n2];
    }

    public final int getStretch(int n2) {
        return this.myStretches[n2];
    }

    protected abstract int getOriginalCell(GridConstraints var1);

    protected abstract int getOriginalSpan(GridConstraints var1);

    abstract int getSizePolicy(int var1);

    abstract int getChildLayoutCellCount(GridLayoutManager var1);

    public final int getGap() {
        return this.myGap;
    }

    public boolean componentBelongsCell(int n2, int n3) {
        int n4 = this.getCell(n2);
        int n5 = this.getSpan(n2);
        return n4 <= n3 && n3 < n4 + n5;
    }

    public final int getCellSizePolicy(int n2) {
        return this.myCellSizePolicies[n2];
    }

    private int getCellSizePolicyImpl(int n2, ArrayList<Integer> arrayList) {
        int n3 = this.getCellSizePolicyFromInheriting(n2);
        if (n3 != -1) {
            return n3;
        }
        for (int i2 = arrayList.size() - 1; i2 >= 0; --i2) {
            if (n2 != arrayList.get(i2)) continue;
            return 1;
        }
        return this.calcCellSizePolicy(n2);
    }

    private int calcCellSizePolicy(int n2) {
        boolean bl = true;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = true;
        boolean bl5 = true;
        int n3 = 0;
        for (int i2 = 0; i2 < this.getComponentCount(); ++i2) {
            boolean bl6;
            if (!this.componentBelongsCell(i2, n2)) continue;
            ++n3;
            int n4 = this.getSizePolicy(i2);
            boolean bl7 = (n4 & 1) != 0;
            boolean bl8 = (n4 & 2) != 0;
            boolean bl9 = bl6 = (n4 & 4) != 0;
            if (this.getCell(i2) == n2 && this.mySpansAfterElimination[i2] == 1) {
                bl &= bl7;
                bl2 |= bl8;
                bl3 |= bl6;
            }
            if (!bl8) {
                bl4 = false;
            }
            if (bl6) continue;
            bl5 = false;
        }
        return (bl ? 1 : 0) | (bl2 || n3 > 0 && bl4 ? 2 : 0) | (bl3 || n3 > 0 && bl5 ? 4 : 0);
    }

    private int getCellSizePolicyFromInheriting(int n2) {
        int n3 = 0;
        int n4 = -1;
        for (int i2 = this.getComponentCount() - 1; i2 >= 0; --i2) {
            GridConstraints gridConstraints;
            if (!this.componentBelongsCell(i2, n2)) continue;
            Component component = this.getComponent(i2);
            Container container = DimensionInfo.findAlignedChild(component, gridConstraints = this.getConstraints(i2));
            if (container != null) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager)container.getLayout();
                gridLayoutManager.validateInfos(container);
                DimensionInfo dimensionInfo = this.getDimensionInfo(gridLayoutManager);
                int n5 = dimensionInfo.calcCellSizePolicy(n2 - this.getOriginalCell(gridConstraints));
                if (n4 == -1) {
                    n4 = n5;
                    continue;
                }
                n4 |= n5;
                continue;
            }
            if (this.getOriginalCell(gridConstraints) != n2 || this.getOriginalSpan(gridConstraints) != 1 || component instanceof Spacer) continue;
            ++n3;
        }
        if (n3 > 0) {
            return -1;
        }
        return n4;
    }

    public static Container findAlignedChild(Component component, GridConstraints gridConstraints) {
        if (gridConstraints.isUseParentLayout() && component instanceof Container) {
            Container container;
            Container container2 = (Container)component;
            if (container2.getLayout() instanceof GridLayoutManager) {
                return container2;
            }
            if (container2.getComponentCount() == 1 && container2.getComponent(0) instanceof Container && (container = (Container)container2.getComponent(0)).getLayout() instanceof GridLayoutManager) {
                return container;
            }
        }
        return null;
    }

    protected final Dimension getPreferredSize(int n2) {
        Dimension dimension = this.myLayoutState.myPreferredSizes[n2];
        if (dimension == null) {
            this.myLayoutState.myPreferredSizes[n2] = dimension = Util.getPreferredSize(this.myLayoutState.getComponent(n2), this.myLayoutState.getConstraints(n2), true);
        }
        return dimension;
    }

    protected final Dimension getMinimumSize(int n2) {
        Dimension dimension = this.myLayoutState.myMinimumSizes[n2];
        if (dimension == null) {
            this.myLayoutState.myMinimumSizes[n2] = dimension = Util.getMinimumSize(this.myLayoutState.getComponent(n2), this.myLayoutState.getConstraints(n2), true);
        }
        return dimension;
    }
}

