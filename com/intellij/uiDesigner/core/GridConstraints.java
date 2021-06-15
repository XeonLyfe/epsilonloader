/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import java.awt.Dimension;

public final class GridConstraints
implements Cloneable {
    public static final GridConstraints[] EMPTY_ARRAY = new GridConstraints[0];
    public static final int FILL_NONE = 0;
    public static final int FILL_HORIZONTAL = 1;
    public static final int FILL_VERTICAL = 2;
    public static final int FILL_BOTH = 3;
    public static final int ANCHOR_CENTER = 0;
    public static final int ANCHOR_NORTH = 1;
    public static final int ANCHOR_SOUTH = 2;
    public static final int ANCHOR_EAST = 4;
    public static final int ANCHOR_WEST = 8;
    public static final int ANCHOR_NORTHEAST = 5;
    public static final int ANCHOR_SOUTHEAST = 6;
    public static final int ANCHOR_SOUTHWEST = 10;
    public static final int ANCHOR_NORTHWEST = 9;
    public static final int SIZEPOLICY_FIXED = 0;
    public static final int SIZEPOLICY_CAN_SHRINK = 1;
    public static final int SIZEPOLICY_CAN_GROW = 2;
    public static final int SIZEPOLICY_WANT_GROW = 4;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_FILL = 3;
    private int myRow;
    private int myColumn;
    private int myRowSpan;
    private int myColSpan;
    private int myVSizePolicy;
    private int myHSizePolicy;
    private int myFill;
    private int myAnchor;
    public final Dimension myMinimumSize;
    public final Dimension myPreferredSize;
    public final Dimension myMaximumSize;
    private int myIndent;
    private boolean myUseParentLayout;

    public GridConstraints() {
        this.myRowSpan = 1;
        this.myColSpan = 1;
        this.myVSizePolicy = 3;
        this.myHSizePolicy = 3;
        this.myFill = 0;
        this.myAnchor = 0;
        this.myMinimumSize = new Dimension(-1, -1);
        this.myPreferredSize = new Dimension(-1, -1);
        this.myMaximumSize = new Dimension(-1, -1);
        this.myIndent = 0;
    }

    public GridConstraints(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, Dimension dimension, Dimension dimension2, Dimension dimension3) {
        this.myRow = n2;
        this.myColumn = n3;
        this.myRowSpan = n4;
        this.myColSpan = n5;
        this.myAnchor = n6;
        this.myFill = n7;
        this.myHSizePolicy = n8;
        this.myVSizePolicy = n9;
        this.myMinimumSize = dimension != null ? new Dimension(dimension) : new Dimension(-1, -1);
        this.myPreferredSize = dimension2 != null ? new Dimension(dimension2) : new Dimension(-1, -1);
        this.myMaximumSize = dimension3 != null ? new Dimension(dimension3) : new Dimension(-1, -1);
        this.myIndent = 0;
    }

    public GridConstraints(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, Dimension dimension, Dimension dimension2, Dimension dimension3, int n10) {
        this(n2, n3, n4, n5, n6, n7, n8, n9, dimension, dimension2, dimension3);
        this.myIndent = n10;
    }

    public GridConstraints(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, Dimension dimension, Dimension dimension2, Dimension dimension3, int n10, boolean bl) {
        this(n2, n3, n4, n5, n6, n7, n8, n9, dimension, dimension2, dimension3, n10);
        this.myUseParentLayout = bl;
    }

    public Object clone() {
        return new GridConstraints(this.myRow, this.myColumn, this.myRowSpan, this.myColSpan, this.myAnchor, this.myFill, this.myHSizePolicy, this.myVSizePolicy, new Dimension(this.myMinimumSize), new Dimension(this.myPreferredSize), new Dimension(this.myMaximumSize), this.myIndent, this.myUseParentLayout);
    }

    public int getColumn() {
        return this.myColumn;
    }

    public void setColumn(int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("wrong column: " + n2);
        }
        this.myColumn = n2;
    }

    public int getRow() {
        return this.myRow;
    }

    public void setRow(int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("wrong row: " + n2);
        }
        this.myRow = n2;
    }

    public int getRowSpan() {
        return this.myRowSpan;
    }

    public void setRowSpan(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("wrong rowSpan: " + n2);
        }
        this.myRowSpan = n2;
    }

    public int getColSpan() {
        return this.myColSpan;
    }

    public void setColSpan(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("wrong colSpan: " + n2);
        }
        this.myColSpan = n2;
    }

    public int getHSizePolicy() {
        return this.myHSizePolicy;
    }

    public void setHSizePolicy(int n2) {
        if (n2 < 0 || n2 > 7) {
            throw new IllegalArgumentException("invalid sizePolicy: " + n2);
        }
        this.myHSizePolicy = n2;
    }

    public int getVSizePolicy() {
        return this.myVSizePolicy;
    }

    public void setVSizePolicy(int n2) {
        if (n2 < 0 || n2 > 7) {
            throw new IllegalArgumentException("invalid sizePolicy: " + n2);
        }
        this.myVSizePolicy = n2;
    }

    public int getAnchor() {
        return this.myAnchor;
    }

    public void setAnchor(int n2) {
        if (n2 < 0 || n2 > 15) {
            throw new IllegalArgumentException("invalid anchor: " + n2);
        }
        this.myAnchor = n2;
    }

    public int getFill() {
        return this.myFill;
    }

    public void setFill(int n2) {
        if (n2 != 0 && n2 != 1 && n2 != 2 && n2 != 3) {
            throw new IllegalArgumentException("invalid fill: " + n2);
        }
        this.myFill = n2;
    }

    public int getIndent() {
        return this.myIndent;
    }

    public void setIndent(int n2) {
        this.myIndent = n2;
    }

    public boolean isUseParentLayout() {
        return this.myUseParentLayout;
    }

    public void setUseParentLayout(boolean bl) {
        this.myUseParentLayout = bl;
    }

    public GridConstraints store() {
        GridConstraints gridConstraints = new GridConstraints();
        gridConstraints.setRow(this.myRow);
        gridConstraints.setColumn(this.myColumn);
        gridConstraints.setColSpan(this.myColSpan);
        gridConstraints.setRowSpan(this.myRowSpan);
        gridConstraints.setVSizePolicy(this.myVSizePolicy);
        gridConstraints.setHSizePolicy(this.myHSizePolicy);
        gridConstraints.setFill(this.myFill);
        gridConstraints.setAnchor(this.myAnchor);
        gridConstraints.setIndent(this.myIndent);
        gridConstraints.setUseParentLayout(this.myUseParentLayout);
        gridConstraints.myMinimumSize.setSize(this.myMinimumSize);
        gridConstraints.myPreferredSize.setSize(this.myPreferredSize);
        gridConstraints.myMaximumSize.setSize(this.myMaximumSize);
        return gridConstraints;
    }

    public void restore(GridConstraints gridConstraints) {
        this.myRow = gridConstraints.myRow;
        this.myColumn = gridConstraints.myColumn;
        this.myRowSpan = gridConstraints.myRowSpan;
        this.myColSpan = gridConstraints.myColSpan;
        this.myHSizePolicy = gridConstraints.myHSizePolicy;
        this.myVSizePolicy = gridConstraints.myVSizePolicy;
        this.myFill = gridConstraints.myFill;
        this.myAnchor = gridConstraints.myAnchor;
        this.myIndent = gridConstraints.myIndent;
        this.myUseParentLayout = gridConstraints.myUseParentLayout;
        this.myMinimumSize.setSize(gridConstraints.myMinimumSize);
        this.myPreferredSize.setSize(gridConstraints.myPreferredSize);
        this.myMaximumSize.setSize(gridConstraints.myMaximumSize);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof GridConstraints)) {
            return false;
        }
        GridConstraints gridConstraints = (GridConstraints)object;
        if (this.myAnchor != gridConstraints.myAnchor) {
            return false;
        }
        if (this.myColSpan != gridConstraints.myColSpan) {
            return false;
        }
        if (this.myColumn != gridConstraints.myColumn) {
            return false;
        }
        if (this.myFill != gridConstraints.myFill) {
            return false;
        }
        if (this.myHSizePolicy != gridConstraints.myHSizePolicy) {
            return false;
        }
        if (this.myRow != gridConstraints.myRow) {
            return false;
        }
        if (this.myRowSpan != gridConstraints.myRowSpan) {
            return false;
        }
        if (this.myVSizePolicy != gridConstraints.myVSizePolicy) {
            return false;
        }
        if (this.myMaximumSize != null ? !this.myMaximumSize.equals(gridConstraints.myMaximumSize) : gridConstraints.myMaximumSize != null) {
            return false;
        }
        if (this.myMinimumSize != null ? !this.myMinimumSize.equals(gridConstraints.myMinimumSize) : gridConstraints.myMinimumSize != null) {
            return false;
        }
        if (this.myPreferredSize != null ? !this.myPreferredSize.equals(gridConstraints.myPreferredSize) : gridConstraints.myPreferredSize != null) {
            return false;
        }
        if (this.myIndent != gridConstraints.myIndent) {
            return false;
        }
        return this.myUseParentLayout == gridConstraints.myUseParentLayout;
    }

    public int hashCode() {
        int n2 = this.myRow;
        n2 = 29 * n2 + this.myColumn;
        n2 = 29 * n2 + this.myRowSpan;
        n2 = 29 * n2 + this.myColSpan;
        n2 = 29 * n2 + this.myVSizePolicy;
        n2 = 29 * n2 + this.myHSizePolicy;
        n2 = 29 * n2 + this.myFill;
        n2 = 29 * n2 + this.myAnchor;
        n2 = 29 * n2 + (this.myMinimumSize != null ? this.myMinimumSize.hashCode() : 0);
        n2 = 29 * n2 + (this.myPreferredSize != null ? this.myPreferredSize.hashCode() : 0);
        n2 = 29 * n2 + (this.myMaximumSize != null ? this.myMaximumSize.hashCode() : 0);
        n2 = 29 * n2 + this.myIndent;
        n2 = 29 * n2 + (this.myUseParentLayout ? 1 : 0);
        return n2;
    }

    public int getCell(boolean bl) {
        return bl ? this.getRow() : this.getColumn();
    }

    public void setCell(boolean bl, int n2) {
        if (bl) {
            this.setRow(n2);
        } else {
            this.setColumn(n2);
        }
    }

    public int getSpan(boolean bl) {
        return bl ? this.getRowSpan() : this.getColSpan();
    }

    public void setSpan(boolean bl, int n2) {
        if (bl) {
            this.setRowSpan(n2);
        } else {
            this.setColSpan(n2);
        }
    }

    public boolean contains(boolean bl, int n2) {
        if (bl) {
            return n2 >= this.myRow && n2 < this.myRow + this.myRowSpan;
        }
        return n2 >= this.myColumn && n2 < this.myColumn + this.myColSpan;
    }

    public String toString() {
        return "GridConstraints (row=" + this.myRow + ", col=" + this.myColumn + ", rowspan=" + this.myRowSpan + ", colspan=" + this.myColSpan + ")";
    }
}

