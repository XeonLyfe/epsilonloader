/*
 * Decompiled with CFR 0.150.
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

public abstract class AbstractLayout
implements LayoutManager2 {
    public static final int DEFAULT_HGAP = 10;
    public static final int DEFAULT_VGAP = 5;
    protected Component[] myComponents = COMPONENT_EMPTY_ARRAY;
    protected GridConstraints[] myConstraints = GridConstraints.EMPTY_ARRAY;
    protected Insets myMargin = new Insets(0, 0, 0, 0);
    private int myHGap = -1;
    private int myVGap = -1;
    private static final Component[] COMPONENT_EMPTY_ARRAY = new Component[0];

    public final Insets getMargin() {
        return (Insets)this.myMargin.clone();
    }

    public final int getHGap() {
        return this.myHGap;
    }

    protected static int getHGapImpl(Container container) {
        if (container == null) {
            throw new IllegalArgumentException("container cannot be null");
        }
        while (container != null) {
            AbstractLayout abstractLayout;
            if (container.getLayout() instanceof AbstractLayout && (abstractLayout = (AbstractLayout)container.getLayout()).getHGap() != -1) {
                return abstractLayout.getHGap();
            }
            container = container.getParent();
        }
        return 10;
    }

    public final void setHGap(int n2) {
        if (n2 < -1) {
            throw new IllegalArgumentException("wrong hGap: " + n2);
        }
        this.myHGap = n2;
    }

    public final int getVGap() {
        return this.myVGap;
    }

    protected static int getVGapImpl(Container container) {
        if (container == null) {
            throw new IllegalArgumentException("container cannot be null");
        }
        while (container != null) {
            AbstractLayout abstractLayout;
            if (container.getLayout() instanceof AbstractLayout && (abstractLayout = (AbstractLayout)container.getLayout()).getVGap() != -1) {
                return abstractLayout.getVGap();
            }
            container = container.getParent();
        }
        return 5;
    }

    public final void setVGap(int n2) {
        if (n2 < -1) {
            throw new IllegalArgumentException("wrong vGap: " + n2);
        }
        this.myVGap = n2;
    }

    public final void setMargin(Insets insets) {
        if (insets == null) {
            throw new IllegalArgumentException("margin cannot be null");
        }
        this.myMargin = (Insets)insets.clone();
    }

    final int getComponentCount() {
        return this.myComponents.length;
    }

    final Component getComponent(int n2) {
        return this.myComponents[n2];
    }

    final GridConstraints getConstraints(int n2) {
        return this.myConstraints[n2];
    }

    @Override
    public void addLayoutComponent(Component component, Object object) {
        if (!(object instanceof GridConstraints)) {
            throw new IllegalArgumentException("constraints: " + object);
        }
        Component[] arrcomponent = new Component[this.myComponents.length + 1];
        System.arraycopy(this.myComponents, 0, arrcomponent, 0, this.myComponents.length);
        arrcomponent[this.myComponents.length] = component;
        this.myComponents = arrcomponent;
        GridConstraints[] arrgridConstraints = new GridConstraints[this.myConstraints.length + 1];
        System.arraycopy(this.myConstraints, 0, arrgridConstraints, 0, this.myConstraints.length);
        arrgridConstraints[this.myConstraints.length] = (GridConstraints)((GridConstraints)object).clone();
        this.myConstraints = arrgridConstraints;
    }

    @Override
    public final void addLayoutComponent(String string, Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void removeLayoutComponent(Component component) {
        Object[] arrobject;
        int n2 = this.getComponentIndex(component);
        if (n2 == -1) {
            throw new IllegalArgumentException("component was not added: " + component);
        }
        if (this.myComponents.length == 1) {
            this.myComponents = COMPONENT_EMPTY_ARRAY;
        } else {
            arrobject = new Component[this.myComponents.length - 1];
            System.arraycopy(this.myComponents, 0, arrobject, 0, n2);
            System.arraycopy(this.myComponents, n2 + 1, arrobject, n2, this.myComponents.length - n2 - 1);
            this.myComponents = arrobject;
        }
        if (this.myConstraints.length == 1) {
            this.myConstraints = GridConstraints.EMPTY_ARRAY;
        } else {
            arrobject = new GridConstraints[this.myConstraints.length - 1];
            System.arraycopy(this.myConstraints, 0, arrobject, 0, n2);
            System.arraycopy(this.myConstraints, n2 + 1, arrobject, n2, this.myConstraints.length - n2 - 1);
            this.myConstraints = arrobject;
        }
    }

    public GridConstraints getConstraintsForComponent(Component component) {
        int n2 = this.getComponentIndex(component);
        if (n2 == -1) {
            throw new IllegalArgumentException("component was not added: " + component);
        }
        return this.myConstraints[n2];
    }

    private int getComponentIndex(Component component) {
        for (int i2 = 0; i2 < this.myComponents.length; ++i2) {
            Component component2 = this.myComponents[i2];
            if (component2 != component) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public final float getLayoutAlignmentX(Container container) {
        return 0.5f;
    }

    @Override
    public final float getLayoutAlignmentY(Container container) {
        return 0.5f;
    }

    @Override
    public abstract Dimension maximumLayoutSize(Container var1);

    @Override
    public abstract void invalidateLayout(Container var1);

    @Override
    public abstract Dimension preferredLayoutSize(Container var1);

    @Override
    public abstract Dimension minimumLayoutSize(Container var1);

    @Override
    public abstract void layoutContainer(Container var1);
}

