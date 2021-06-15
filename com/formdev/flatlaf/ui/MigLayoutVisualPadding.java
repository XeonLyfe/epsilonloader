/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import javax.swing.JComponent;

public class MigLayoutVisualPadding {
    public static String VISUAL_PADDING_PROPERTY = "visualPadding";
    private static final FlatMigInsets ZERO = new FlatMigInsets((int)129157390L ^ 0x7B2C90E, (int)((long)-631511314 ^ (long)-631511314), (int)502415668L ^ 0x1DF24134, (int)((long)1930477310 ^ (long)1930477310));
    private static final boolean migLayoutAvailable;

    public static void install(JComponent jComponent, Insets insets) {
        if (!migLayoutAvailable) {
            return;
        }
        MigLayoutVisualPadding.setVisualPadding(jComponent, insets);
    }

    public static void install(JComponent jComponent2) {
        if (!migLayoutAvailable) {
            return;
        }
        String[] arrstring = new String[(int)-378867165L ^ 0xE96AF222];
        arrstring[(int)((long)-321276702 ^ (long)-321276702)] = "border";
        MigLayoutVisualPadding.install(jComponent2, jComponent -> {
            FlatBorder flatBorder = FlatUIUtils.getOutsideFlatBorder(jComponent);
            if (flatBorder != null) {
                int n2 = flatBorder.getFocusWidth((Component)jComponent);
                return new Insets(n2, n2, n2, n2);
            }
            return null;
        }, arrstring);
    }

    public static void install(JComponent jComponent, Function<JComponent, Insets> function, String ... arrstring) {
        if (!migLayoutAvailable) {
            return;
        }
        MigLayoutVisualPadding.setVisualPadding(jComponent, function.apply(jComponent));
        jComponent.addPropertyChangeListener(propertyChangeEvent -> {
            String string = propertyChangeEvent.getPropertyName();
            String[] arrstring2 = arrstring;
            int n2 = arrstring2.length;
            for (int i2 = (int)((long)-1425295905 ^ (long)-1425295905); i2 < n2; ++i2) {
                String string2 = arrstring2[i2];
                if (string2 != string) continue;
                MigLayoutVisualPadding.setVisualPadding(jComponent, (Insets)function.apply(jComponent));
                break;
            }
        });
    }

    private static void setVisualPadding(JComponent jComponent, Insets insets) {
        Object object = jComponent.getClientProperty(VISUAL_PADDING_PROPERTY);
        if (object == null || object instanceof FlatMigInsets) {
            FlatMigInsets flatMigInsets = insets != null ? new FlatMigInsets(UIScale.scale2(insets.top), UIScale.scale2(insets.left), UIScale.scale2(insets.bottom), UIScale.scale2(insets.right)) : ZERO;
            jComponent.putClientProperty(VISUAL_PADDING_PROPERTY, flatMigInsets);
        }
    }

    public static void uninstall(JComponent jComponent) {
        if (!migLayoutAvailable) {
            return;
        }
        PropertyChangeListener[] arrpropertyChangeListener = jComponent.getPropertyChangeListeners();
        int n2 = arrpropertyChangeListener.length;
        for (int i2 = (int)84460605L ^ 0x508C43D; i2 < n2; ++i2) {
            PropertyChangeListener propertyChangeListener = arrpropertyChangeListener[i2];
            if (!(propertyChangeListener instanceof FlatMigListener)) continue;
            jComponent.removePropertyChangeListener(propertyChangeListener);
            break;
        }
        if (jComponent.getClientProperty(VISUAL_PADDING_PROPERTY) instanceof FlatMigInsets) {
            jComponent.putClientProperty(VISUAL_PADDING_PROPERTY, null);
        }
    }

    static {
        int n2 = (int)((long)1263093984 ^ (long)1263093984);
        try {
            Class.forName("net.miginfocom.swing.MigLayout");
            n2 = (int)((long)543203502 ^ (long)543203503);
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        migLayoutAvailable = n2;
    }

    private static interface FlatMigListener
    extends PropertyChangeListener {
    }

    private static class FlatMigInsets
    extends Insets {
        FlatMigInsets(int n2, int n3, int n4, int n5) {
            super(n2, n3, n4, n5);
        }
    }
}

