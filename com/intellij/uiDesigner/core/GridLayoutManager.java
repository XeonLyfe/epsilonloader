/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.HorizontalInfo;
import com.intellij.uiDesigner.core.LayoutState;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.uiDesigner.core.Util;
import com.intellij.uiDesigner.core.VerticalInfo;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.JComponent;

public final class GridLayoutManager
extends AbstractLayout {
    private int myMinCellSize = 20;
    private final int[] myRowStretches;
    private final int[] myColumnStretches;
    private final int[] myYs;
    private final int[] myHeights;
    private final int[] myXs;
    private final int[] myWidths;
    private LayoutState myLayoutState;
    DimensionInfo myHorizontalInfo;
    DimensionInfo myVerticalInfo;
    private boolean mySameSizeHorizontally;
    private boolean mySameSizeVertically;
    public static final Object DESIGN_TIME_INSETS = new Object();
    private static final int SKIP_ROW = 1;
    private static final int SKIP_COL = 2;

    public GridLayoutManager(int n2, int n3) {
        int n4;
        if (n3 < 1) {
            throw new IllegalArgumentException("wrong columnCount: " + n3);
        }
        if (n2 < 1) {
            throw new IllegalArgumentException("wrong rowCount: " + n2);
        }
        this.myRowStretches = new int[n2];
        for (n4 = 0; n4 < n2; ++n4) {
            this.myRowStretches[n4] = 1;
        }
        this.myColumnStretches = new int[n3];
        for (n4 = 0; n4 < n3; ++n4) {
            this.myColumnStretches[n4] = 1;
        }
        this.myXs = new int[n3];
        this.myWidths = new int[n3];
        this.myYs = new int[n2];
        this.myHeights = new int[n2];
    }

    public GridLayoutManager(int n2, int n3, Insets insets, int n4, int n5) {
        this(n2, n3);
        this.setMargin(insets);
        this.setHGap(n4);
        this.setVGap(n5);
        this.myMinCellSize = 0;
    }

    public GridLayoutManager(int n2, int n3, Insets insets, int n4, int n5, boolean bl, boolean bl2) {
        this(n2, n3, insets, n4, n5);
        this.mySameSizeHorizontally = bl;
        this.mySameSizeVertically = bl2;
    }

    @Override
    public void addLayoutComponent(Component component, Object object) {
        GridConstraints gridConstraints = (GridConstraints)object;
        int n2 = gridConstraints.getRow();
        int n3 = gridConstraints.getRowSpan();
        int n4 = this.getRowCount();
        if (n2 < 0 || n2 >= n4) {
            throw new IllegalArgumentException("wrong row: " + n2);
        }
        if (n2 + n3 - 1 >= n4) {
            throw new IllegalArgumentException("wrong row span: " + n3 + "; row=" + n2 + " rowCount=" + n4);
        }
        int n5 = gridConstraints.getColumn();
        int n6 = gridConstraints.getColSpan();
        int n7 = this.getColumnCount();
        if (n5 < 0 || n5 >= n7) {
            throw new IllegalArgumentException("wrong column: " + n5);
        }
        if (n5 + n6 - 1 >= n7) {
            throw new IllegalArgumentException("wrong col span: " + n6 + "; column=" + n5 + " columnCount=" + n7);
        }
        super.addLayoutComponent(component, object);
    }

    public int getRowCount() {
        return this.myRowStretches.length;
    }

    public int getColumnCount() {
        return this.myColumnStretches.length;
    }

    public int getRowStretch(int n2) {
        return this.myRowStretches[n2];
    }

    public void setRowStretch(int n2, int n3) {
        if (n3 < 1) {
            throw new IllegalArgumentException("wrong stretch: " + n3);
        }
        this.myRowStretches[n2] = n3;
    }

    public int getColumnStretch(int n2) {
        return this.myColumnStretches[n2];
    }

    public void setColumnStretch(int n2, int n3) {
        if (n3 < 1) {
            throw new IllegalArgumentException("wrong stretch: " + n3);
        }
        this.myColumnStretches[n2] = n3;
    }

    @Override
    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Dimension minimumLayoutSize(Container container) {
        this.validateInfos(container);
        DimensionInfo dimensionInfo = this.myHorizontalInfo;
        DimensionInfo dimensionInfo2 = this.myVerticalInfo;
        Dimension dimension = this.getTotalGap(container, dimensionInfo, dimensionInfo2);
        int[] arrn = this.getMinSizes(dimensionInfo);
        if (this.mySameSizeHorizontally) {
            GridLayoutManager.makeSameSizes(arrn);
        }
        dimension.width += GridLayoutManager.sum(arrn);
        int[] arrn2 = this.getMinSizes(dimensionInfo2);
        if (this.mySameSizeVertically) {
            GridLayoutManager.makeSameSizes(arrn2);
        }
        dimension.height += GridLayoutManager.sum(arrn2);
        return dimension;
    }

    private static void makeSameSizes(int[] arrn) {
        int n2 = arrn[0];
        for (int n3 : arrn) {
            n2 = Math.max(n3, n2);
        }
        Arrays.fill(arrn, n2);
    }

    private static int[] getSameSizes(DimensionInfo dimensionInfo, int n2) {
        int[] arrn = new int[dimensionInfo.getCellCount()];
        int n3 = n2 / arrn.length;
        int n4 = n2 % arrn.length;
        for (int i2 = 0; i2 < arrn.length; ++i2) {
            arrn[i2] = n3;
            if (n4 <= 0) continue;
            int n5 = i2;
            arrn[n5] = arrn[n5] + 1;
            --n4;
        }
        return arrn;
    }

    @Override
    public Dimension preferredLayoutSize(Container container) {
        this.validateInfos(container);
        DimensionInfo dimensionInfo = this.myHorizontalInfo;
        DimensionInfo dimensionInfo2 = this.myVerticalInfo;
        Dimension dimension = this.getTotalGap(container, dimensionInfo, dimensionInfo2);
        int[] arrn = this.getPrefSizes(dimensionInfo);
        if (this.mySameSizeHorizontally) {
            GridLayoutManager.makeSameSizes(arrn);
        }
        dimension.width += GridLayoutManager.sum(arrn);
        int[] arrn2 = this.getPrefSizes(dimensionInfo2);
        if (this.mySameSizeVertically) {
            GridLayoutManager.makeSameSizes(arrn2);
        }
        dimension.height += GridLayoutManager.sum(arrn2);
        return dimension;
    }

    private static int sum(int[] arrn) {
        int n2 = 0;
        for (int i2 = arrn.length - 1; i2 >= 0; --i2) {
            n2 += arrn[i2];
        }
        return n2;
    }

    private Dimension getTotalGap(Container container, DimensionInfo dimensionInfo, DimensionInfo dimensionInfo2) {
        Insets insets = GridLayoutManager.getInsets(container);
        return new Dimension(insets.left + insets.right + GridLayoutManager.countGap(dimensionInfo, 0, dimensionInfo.getCellCount()) + this.myMargin.left + this.myMargin.right, insets.top + insets.bottom + GridLayoutManager.countGap(dimensionInfo2, 0, dimensionInfo2.getCellCount()) + this.myMargin.top + this.myMargin.bottom);
    }

    private static int getDesignTimeInsets(Container container) {
        while (container != null) {
            Integer n2;
            if (container instanceof JComponent && (n2 = (Integer)((JComponent)container).getClientProperty(DESIGN_TIME_INSETS)) != null) {
                return n2;
            }
            container = container.getParent();
        }
        return 0;
    }

    private static Insets getInsets(Container container) {
        Insets insets = container.getInsets();
        int n2 = GridLayoutManager.getDesignTimeInsets(container);
        if (n2 != 0) {
            return new Insets(insets.top + n2, insets.left + n2, insets.bottom + n2, insets.right + n2);
        }
        return insets;
    }

    private static int countGap(DimensionInfo dimensionInfo, int n2, int n3) {
        int n4 = 0;
        for (int i2 = n2 + n3 - 2; i2 >= n2; --i2) {
            if (!GridLayoutManager.shouldAddGapAfterCell(dimensionInfo, i2)) continue;
            ++n4;
        }
        return n4 * dimensionInfo.getGap();
    }

    private static boolean shouldAddGapAfterCell(DimensionInfo dimensionInfo, int n2) {
        int n3;
        if (n2 < 0 || n2 >= dimensionInfo.getCellCount()) {
            throw new IllegalArgumentException("wrong cellIndex: " + n2 + "; cellCount=" + dimensionInfo.getCellCount());
        }
        boolean bl = false;
        boolean bl2 = false;
        int n4 = -1;
        for (n3 = n2 + 1; n3 < dimensionInfo.getCellCount(); ++n3) {
            if (GridLayoutManager.isCellEmpty(dimensionInfo, n3)) continue;
            n4 = n3;
            break;
        }
        for (n3 = 0; n3 < dimensionInfo.getComponentCount(); ++n3) {
            Component component = dimensionInfo.getComponent(n3);
            if (component instanceof Spacer) continue;
            if (dimensionInfo.componentBelongsCell(n3, n2) && DimensionInfo.findAlignedChild(component, dimensionInfo.getConstraints(n3)) != null) {
                return true;
            }
            if (dimensionInfo.getCell(n3) == n4) {
                bl2 = true;
            }
            if (dimensionInfo.getCell(n3) + dimensionInfo.getSpan(n3) - 1 != n2) continue;
            bl = true;
        }
        return bl2 && bl;
    }

    private static boolean isCellEmpty(DimensionInfo dimensionInfo, int n2) {
        if (n2 < 0 || n2 >= dimensionInfo.getCellCount()) {
            throw new IllegalArgumentException("wrong cellIndex: " + n2 + "; cellCount=" + dimensionInfo.getCellCount());
        }
        for (int i2 = 0; i2 < dimensionInfo.getComponentCount(); ++i2) {
            Component component = dimensionInfo.getComponent(i2);
            if (dimensionInfo.getCell(i2) != n2 || component instanceof Spacer) continue;
            return false;
        }
        return true;
    }

    @Override
    public void layoutContainer(Container container) {
        int n2;
        int n3;
        int[] arrn;
        this.validateInfos(container);
        LayoutState layoutState = this.myLayoutState;
        DimensionInfo dimensionInfo = this.myHorizontalInfo;
        DimensionInfo dimensionInfo2 = this.myVerticalInfo;
        Insets insets = GridLayoutManager.getInsets(container);
        int n4 = this.checkSetSizesFromParent(container, insets);
        Dimension dimension = this.getTotalGap(container, dimensionInfo, dimensionInfo2);
        Dimension dimension2 = container.getSize();
        dimension2.width -= dimension.width;
        dimension2.height -= dimension.height;
        Dimension dimension3 = this.preferredLayoutSize(container);
        dimension3.width -= dimension.width;
        dimension3.height -= dimension.height;
        Dimension dimension4 = this.minimumLayoutSize(container);
        dimension4.width -= dimension.width;
        dimension4.height -= dimension.height;
        if ((n4 & 1) == 0) {
            if (this.mySameSizeVertically) {
                arrn = GridLayoutManager.getSameSizes(dimensionInfo2, Math.max(dimension2.height, dimension4.height));
            } else if (dimension2.height < dimension3.height) {
                arrn = this.getMinSizes(dimensionInfo2);
                this.new_doIt(arrn, 0, dimensionInfo2.getCellCount(), dimension2.height, dimensionInfo2, true);
            } else {
                arrn = this.getPrefSizes(dimensionInfo2);
                this.new_doIt(arrn, 0, dimensionInfo2.getCellCount(), dimension2.height, dimensionInfo2, false);
            }
            n3 = insets.top + this.myMargin.top;
            for (n2 = 0; n2 < arrn.length; ++n2) {
                this.myYs[n2] = n3;
                this.myHeights[n2] = arrn[n2];
                n3 += arrn[n2];
                if (!GridLayoutManager.shouldAddGapAfterCell(dimensionInfo2, n2)) continue;
                n3 += dimensionInfo2.getGap();
            }
        }
        if ((n4 & 2) == 0) {
            if (this.mySameSizeHorizontally) {
                arrn = GridLayoutManager.getSameSizes(dimensionInfo, Math.max(dimension2.width, dimension4.width));
            } else if (dimension2.width < dimension3.width) {
                arrn = this.getMinSizes(dimensionInfo);
                this.new_doIt(arrn, 0, dimensionInfo.getCellCount(), dimension2.width, dimensionInfo, true);
            } else {
                arrn = this.getPrefSizes(dimensionInfo);
                this.new_doIt(arrn, 0, dimensionInfo.getCellCount(), dimension2.width, dimensionInfo, false);
            }
            n3 = insets.left + this.myMargin.left;
            for (n2 = 0; n2 < arrn.length; ++n2) {
                this.myXs[n2] = n3;
                this.myWidths[n2] = arrn[n2];
                n3 += arrn[n2];
                if (!GridLayoutManager.shouldAddGapAfterCell(dimensionInfo, n2)) continue;
                n3 += dimensionInfo.getGap();
            }
        }
        for (int i2 = 0; i2 < layoutState.getComponentCount(); ++i2) {
            GridConstraints gridConstraints = layoutState.getConstraints(i2);
            Component component = layoutState.getComponent(i2);
            int n5 = dimensionInfo.getCell(i2);
            int n6 = dimensionInfo.getSpan(i2);
            int n7 = dimensionInfo2.getCell(i2);
            int n8 = dimensionInfo2.getSpan(i2);
            int n9 = this.myXs[n5 + n6 - 1] + this.myWidths[n5 + n6 - 1] - this.myXs[n5];
            int n10 = this.myYs[n7 + n8 - 1] + this.myHeights[n7 + n8 - 1] - this.myYs[n7];
            Dimension dimension5 = new Dimension(n9, n10);
            if ((gridConstraints.getFill() & 1) == 0) {
                dimension5.width = Math.min(dimension5.width, dimensionInfo.getPreferredWidth(i2));
            }
            if ((gridConstraints.getFill() & 2) == 0) {
                dimension5.height = Math.min(dimension5.height, dimensionInfo2.getPreferredWidth(i2));
            }
            Util.adjustSize(component, gridConstraints, dimension5);
            int n11 = 0;
            int n12 = 0;
            if ((gridConstraints.getAnchor() & 4) != 0) {
                n11 = n9 - dimension5.width;
            } else if ((gridConstraints.getAnchor() & 8) == 0) {
                n11 = (n9 - dimension5.width) / 2;
            }
            if ((gridConstraints.getAnchor() & 2) != 0) {
                n12 = n10 - dimension5.height;
            } else if ((gridConstraints.getAnchor() & 1) == 0) {
                n12 = (n10 - dimension5.height) / 2;
            }
            int n13 = 10 * gridConstraints.getIndent();
            dimension5.width -= n13;
            component.setBounds(this.myXs[n5] + (n11 += n13), this.myYs[n7] + n12, dimension5.width, dimension5.height);
        }
    }

    private int checkSetSizesFromParent(Container container, Insets insets) {
        int n2 = 0;
        GridLayoutManager gridLayoutManager = null;
        GridConstraints gridConstraints = null;
        Container container2 = container.getParent();
        if (container2 != null) {
            if (container2.getLayout() instanceof GridLayoutManager) {
                gridLayoutManager = (GridLayoutManager)container2.getLayout();
                gridConstraints = gridLayoutManager.getConstraintsForComponent(container);
            } else {
                Container container3 = container2.getParent();
                if (container3 != null && container3.getLayout() instanceof GridLayoutManager) {
                    gridLayoutManager = (GridLayoutManager)container3.getLayout();
                    gridConstraints = gridLayoutManager.getConstraintsForComponent(container2);
                }
            }
        }
        if (gridLayoutManager != null && gridConstraints.isUseParentLayout()) {
            int n3;
            if (this.myRowStretches.length == gridConstraints.getRowSpan()) {
                int n4 = gridConstraints.getRow();
                this.myYs[0] = insets.top + this.myMargin.top;
                this.myHeights[0] = gridLayoutManager.myHeights[n4] - this.myYs[0];
                for (n3 = 1; n3 < this.myRowStretches.length; ++n3) {
                    this.myYs[n3] = gridLayoutManager.myYs[n3 + n4] - gridLayoutManager.myYs[n4];
                    this.myHeights[n3] = gridLayoutManager.myHeights[n3 + n4];
                }
                int n5 = this.myRowStretches.length - 1;
                this.myHeights[n5] = this.myHeights[n5] - (insets.bottom + this.myMargin.bottom);
                n2 |= 1;
            }
            if (this.myColumnStretches.length == gridConstraints.getColSpan()) {
                int n6 = gridConstraints.getColumn();
                this.myXs[0] = insets.left + this.myMargin.left;
                this.myWidths[0] = gridLayoutManager.myWidths[n6] - this.myXs[0];
                for (n3 = 1; n3 < this.myColumnStretches.length; ++n3) {
                    this.myXs[n3] = gridLayoutManager.myXs[n3 + n6] - gridLayoutManager.myXs[n6];
                    this.myWidths[n3] = gridLayoutManager.myWidths[n3 + n6];
                }
                int n7 = this.myColumnStretches.length - 1;
                this.myWidths[n7] = this.myWidths[n7] - (insets.right + this.myMargin.right);
                n2 |= 2;
            }
        }
        return n2;
    }

    @Override
    public void invalidateLayout(Container container) {
        this.myLayoutState = null;
        this.myHorizontalInfo = null;
        this.myVerticalInfo = null;
    }

    void validateInfos(Container container) {
        if (this.myLayoutState == null) {
            this.myLayoutState = new LayoutState(this, GridLayoutManager.getDesignTimeInsets(container) == 0);
            this.myHorizontalInfo = new HorizontalInfo(this.myLayoutState, GridLayoutManager.getHGapImpl(container));
            this.myVerticalInfo = new VerticalInfo(this.myLayoutState, GridLayoutManager.getVGapImpl(container));
        }
    }

    public int[] getXs() {
        return this.myXs;
    }

    public int[] getWidths() {
        return this.myWidths;
    }

    public int[] getYs() {
        return this.myYs;
    }

    public int[] getHeights() {
        return this.myHeights;
    }

    public int[] getCoords(boolean bl) {
        return bl ? this.myYs : this.myXs;
    }

    public int[] getSizes(boolean bl) {
        return bl ? this.myHeights : this.myWidths;
    }

    private int[] getMinSizes(DimensionInfo dimensionInfo) {
        return this.getMinOrPrefSizes(dimensionInfo, true);
    }

    private int[] getPrefSizes(DimensionInfo dimensionInfo) {
        return this.getMinOrPrefSizes(dimensionInfo, false);
    }

    private int[] getMinOrPrefSizes(DimensionInfo dimensionInfo, boolean bl) {
        int n2;
        int n3;
        int n4;
        int[] arrn = new int[dimensionInfo.getCellCount()];
        Arrays.fill(arrn, this.myMinCellSize);
        for (int i2 = dimensionInfo.getComponentCount() - 1; i2 >= 0; --i2) {
            if (dimensionInfo.getSpan(i2) != 1) continue;
            n4 = bl ? GridLayoutManager.getMin2(dimensionInfo, i2) : Math.max(dimensionInfo.getMinimumWidth(i2), dimensionInfo.getPreferredWidth(i2));
            n3 = dimensionInfo.getCell(i2);
            n2 = GridLayoutManager.countGap(dimensionInfo, n3, dimensionInfo.getSpan(i2));
            n4 = Math.max(n4 - n2, 0);
            arrn[n3] = Math.max(arrn[n3], n4);
        }
        GridLayoutManager.updateSizesFromChildren(dimensionInfo, bl, arrn);
        boolean[] arrbl = new boolean[dimensionInfo.getCellCount()];
        for (n4 = dimensionInfo.getComponentCount() - 1; n4 >= 0; --n4) {
            n3 = bl ? GridLayoutManager.getMin2(dimensionInfo, n4) : Math.max(dimensionInfo.getMinimumWidth(n4), dimensionInfo.getPreferredWidth(n4));
            n2 = dimensionInfo.getSpan(n4);
            int n5 = dimensionInfo.getCell(n4);
            int n6 = GridLayoutManager.countGap(dimensionInfo, n5, n2);
            n3 = Math.max(n3 - n6, 0);
            Arrays.fill(arrbl, false);
            int n7 = 0;
            for (int i3 = 0; i3 < n2; ++i3) {
                n7 += arrn[i3 + n5];
                arrbl[i3 + n5] = true;
            }
            if (n7 >= n3) continue;
            boolean[] arrbl2 = new boolean[arrbl.length];
            this.getCellsWithHigherPriorities(dimensionInfo, arrbl, arrbl2, false, arrn);
            GridLayoutManager.distribute(arrbl2, dimensionInfo, n3 - n7, arrn);
        }
        return arrn;
    }

    private static void updateSizesFromChildren(DimensionInfo dimensionInfo, boolean bl, int[] arrn) {
        for (int i2 = dimensionInfo.getComponentCount() - 1; i2 >= 0; --i2) {
            Container container;
            Component component = dimensionInfo.getComponent(i2);
            GridConstraints gridConstraints = dimensionInfo.getConstraints(i2);
            if (!gridConstraints.isUseParentLayout() || !(component instanceof Container)) continue;
            Container container2 = (Container)component;
            if (container2.getLayout() instanceof GridLayoutManager) {
                GridLayoutManager.updateSizesFromChild(dimensionInfo, bl, arrn, container2, i2);
                continue;
            }
            if (container2.getComponentCount() != 1 || !(container2.getComponent(0) instanceof Container) || !((container = (Container)container2.getComponent(0)).getLayout() instanceof GridLayoutManager)) continue;
            GridLayoutManager.updateSizesFromChild(dimensionInfo, bl, arrn, container, i2);
        }
    }

    private static void updateSizesFromChild(DimensionInfo dimensionInfo, boolean bl, int[] arrn, Container container, int n2) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager)container.getLayout();
        if (dimensionInfo.getSpan(n2) == dimensionInfo.getChildLayoutCellCount(gridLayoutManager)) {
            gridLayoutManager.validateInfos(container);
            DimensionInfo dimensionInfo2 = dimensionInfo instanceof HorizontalInfo ? gridLayoutManager.myHorizontalInfo : gridLayoutManager.myVerticalInfo;
            int[] arrn2 = gridLayoutManager.getMinOrPrefSizes(dimensionInfo2, bl);
            int n3 = dimensionInfo.getCell(n2);
            for (int i2 = 0; i2 < arrn2.length; ++i2) {
                arrn[n3 + i2] = Math.max(arrn[n3 + i2], arrn2[i2]);
            }
        }
    }

    private static int getMin2(DimensionInfo dimensionInfo, int n2) {
        int n3 = (dimensionInfo.getSizePolicy(n2) & 1) != 0 ? dimensionInfo.getMinimumWidth(n2) : Math.max(dimensionInfo.getMinimumWidth(n2), dimensionInfo.getPreferredWidth(n2));
        return n3;
    }

    private void new_doIt(int[] arrn, int n2, int n3, int n4, DimensionInfo dimensionInfo, boolean bl) {
        int n5 = n4;
        for (int i2 = n2; i2 < n2 + n3; ++i2) {
            n5 -= arrn[i2];
        }
        if (n5 <= 0) {
            return;
        }
        boolean[] arrbl = new boolean[dimensionInfo.getCellCount()];
        for (int i3 = n2; i3 < n2 + n3; ++i3) {
            arrbl[i3] = true;
        }
        boolean[] arrbl2 = new boolean[dimensionInfo.getCellCount()];
        this.getCellsWithHigherPriorities(dimensionInfo, arrbl, arrbl2, bl, arrn);
        GridLayoutManager.distribute(arrbl2, dimensionInfo, n5, arrn);
    }

    private static void distribute(boolean[] arrbl, DimensionInfo dimensionInfo, int n2, int[] arrn) {
        int n3;
        int n4 = 0;
        for (n3 = 0; n3 < dimensionInfo.getCellCount(); ++n3) {
            if (!arrbl[n3]) continue;
            n4 += dimensionInfo.getStretch(n3);
        }
        n3 = n2;
        for (int i2 = 0; i2 < dimensionInfo.getCellCount(); ++i2) {
            if (!arrbl[i2]) continue;
            int n5 = n3 * dimensionInfo.getStretch(i2) / n4;
            int n6 = i2;
            arrn[n6] = arrn[n6] + n5;
            n2 -= n5;
        }
        if (n2 != 0) {
            for (n3 = 0; n3 < dimensionInfo.getCellCount(); ++n3) {
                if (!arrbl[n3]) continue;
                int n7 = n3;
                arrn[n7] = arrn[n7] + 1;
                if (--n2 == 0) break;
            }
        }
        if (n2 != 0) {
            throw new IllegalStateException("toDistribute = " + n2);
        }
    }

    private void getCellsWithHigherPriorities(DimensionInfo dimensionInfo, boolean[] arrbl, boolean[] arrbl2, boolean bl, int[] arrn) {
        int n2;
        Arrays.fill(arrbl2, false);
        int n3 = 0;
        if (bl) {
            int[] arrn2 = this.getMinOrPrefSizes(dimensionInfo, false);
            for (int i2 = 0; i2 < arrbl.length; ++i2) {
                if (!arrbl[i2] || GridLayoutManager.isCellEmpty(dimensionInfo, i2) || arrn2[i2] <= arrn[i2]) continue;
                arrbl2[i2] = true;
                ++n3;
            }
            if (n3 > 0) {
                return;
            }
        }
        for (n2 = 0; n2 < arrbl.length; ++n2) {
            if (!arrbl[n2] || (dimensionInfo.getCellSizePolicy(n2) & 4) == 0) continue;
            arrbl2[n2] = true;
            ++n3;
        }
        if (n3 > 0) {
            return;
        }
        for (n2 = 0; n2 < arrbl.length; ++n2) {
            if (!arrbl[n2] || (dimensionInfo.getCellSizePolicy(n2) & 2) == 0) continue;
            arrbl2[n2] = true;
            ++n3;
        }
        if (n3 > 0) {
            return;
        }
        for (n2 = 0; n2 < arrbl.length; ++n2) {
            if (!arrbl[n2] || GridLayoutManager.isCellEmpty(dimensionInfo, n2)) continue;
            arrbl2[n2] = true;
            ++n3;
        }
        if (n3 > 0) {
            return;
        }
        for (n2 = 0; n2 < arrbl.length; ++n2) {
            if (!arrbl[n2]) continue;
            arrbl2[n2] = true;
        }
    }

    public boolean isSameSizeHorizontally() {
        return this.mySameSizeHorizontally;
    }

    public boolean isSameSizeVertically() {
        return this.mySameSizeVertically;
    }

    public void setSameSizeHorizontally(boolean bl) {
        this.mySameSizeHorizontally = bl;
    }

    public void setSameSizeVertically(boolean bl) {
        this.mySameSizeVertically = bl;
    }

    public int[] getHorizontalGridLines() {
        return GridLayoutManager.getGridLines(this.myYs, this.myHeights);
    }

    public int[] getVerticalGridLines() {
        return GridLayoutManager.getGridLines(this.myXs, this.myWidths);
    }

    private static int[] getGridLines(int[] arrn, int[] arrn2) {
        int[] arrn3 = new int[arrn.length + 1];
        arrn3[0] = arrn[0];
        for (int i2 = 0; i2 < arrn.length - 1; ++i2) {
            arrn3[i2 + 1] = (arrn[i2] + arrn2[i2] + arrn[i2 + 1]) / 2;
        }
        arrn3[arrn.length] = arrn[arrn.length - 1] + arrn2[arrn.length - 1];
        return arrn3;
    }

    public int getCellCount(boolean bl) {
        return bl ? this.getRowCount() : this.getColumnCount();
    }

    public int getCellSizePolicy(boolean bl, int n2) {
        DimensionInfo dimensionInfo;
        DimensionInfo dimensionInfo2 = dimensionInfo = bl ? this.myVerticalInfo : this.myHorizontalInfo;
        if (dimensionInfo == null) {
            return 0;
        }
        return dimensionInfo.getCellSizePolicy(n2);
    }
}

