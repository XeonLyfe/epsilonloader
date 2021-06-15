/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI;
import javax.swing.plaf.metal.MetalFileChooserUI;
import javax.swing.table.TableCellRenderer;

public class FlatFileChooserUI
extends MetalFileChooserUI {
    private final FlatFileView fileView = new FlatFileView();

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatFileChooserUI((JFileChooser)jComponent);
    }

    public FlatFileChooserUI(JFileChooser jFileChooser) {
        super(jFileChooser);
    }

    @Override
    public void installComponents(JFileChooser jFileChooser) {
        super.installComponents(jFileChooser);
        this.patchUI(jFileChooser);
    }

    private void patchUI(JFileChooser jFileChooser) {
        Component component;
        Component component2 = jFileChooser.getComponent((int)-1609661854L ^ 0xA00E8262);
        if (component2 instanceof JPanel && ((JPanel)component2).getLayout() instanceof BorderLayout && (component = ((JPanel)component2).getComponent((int)((long)-142472182 ^ (long)-142472182))) instanceof JPanel && ((JPanel)component).getLayout() instanceof BoxLayout) {
            Insets insets = UIManager.getInsets("Button.margin");
            Component[] arrcomponent = ((JPanel)component).getComponents();
            for (int i2 = arrcomponent.length - ((int)-1034875786L ^ 0xC2510C77); i2 >= 0; --i2) {
                Component component3 = arrcomponent[i2];
                if (component3 instanceof JButton || component3 instanceof JToggleButton) {
                    AbstractButton abstractButton = (AbstractButton)component3;
                    abstractButton.putClientProperty("JButton.buttonType", "toolBarButton");
                    abstractButton.setMargin(insets);
                    abstractButton.setFocusable((boolean)((long)-516215937 ^ (long)-516215937));
                    continue;
                }
                if (!(component3 instanceof Box.Filler)) continue;
                ((JPanel)component).remove(i2);
            }
        }
        try {
            int n2;
            component = ((JPanel)component2).getComponent((int)((long)1570176553 ^ (long)1570176552) << 1);
            if (component instanceof JComboBox && (n2 = UIManager.getInt("ComboBox.maximumRowCount")) > 0) {
                ((JComboBox)component).setMaximumRowCount(n2);
            }
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
    }

    @Override
    protected JPanel createDetailsView(JFileChooser jFileChooser) {
        JPanel jPanel = super.createDetailsView(jFileChooser);
        if (!SystemInfo.isWindows) {
            return jPanel;
        }
        JScrollPane jScrollPane = null;
        Object object = jPanel.getComponents();
        int n2 = ((Component[])object).length;
        for (int i2 = (int)((long)2022115324 ^ (long)2022115324); i2 < n2; ++i2) {
            Component component = object[i2];
            if (!(component instanceof JScrollPane)) continue;
            jScrollPane = (JScrollPane)component;
            break;
        }
        if (jScrollPane == null) {
            return jPanel;
        }
        object = jScrollPane.getViewport().getView();
        if (!(object instanceof JTable)) {
            return jPanel;
        }
        JTable jTable = (JTable)object;
        final TableCellRenderer tableCellRenderer = jTable.getDefaultRenderer(Object.class);
        jTable.setDefaultRenderer(Object.class, new TableCellRenderer(){

            @Override
            public Component getTableCellRendererComponent(JTable jTable, Object object, boolean bl, boolean bl2, int n2, int n3) {
                if (object instanceof String && ((String)object).startsWith("\u200e")) {
                    String string = (String)object;
                    char[] arrc = new char[string.length()];
                    int n4 = (int)((long)1781735644 ^ (long)1781735644);
                    for (int i2 = (int)-1417837384L ^ 0xAB7D84B8; i2 < arrc.length; ++i2) {
                        char c2 = string.charAt(i2);
                        if (c2 == ((int)-54111587L ^ 0xFCC6429A) << 1 || c2 == (int)((long)1669321583 ^ (long)1669329760)) continue;
                        arrc[n4++] = c2;
                    }
                    object = new String(arrc, (int)((long)5427099 ^ (long)5427099), n4);
                }
                return tableCellRenderer.getTableCellRendererComponent(jTable, object, bl, bl2, n2, n3);
            }
        });
        return jPanel;
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return UIScale.scale(super.getPreferredSize(jComponent));
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return UIScale.scale(super.getMinimumSize(jComponent));
    }

    @Override
    public FileView getFileView(JFileChooser jFileChooser) {
        return this.fileView;
    }

    @Override
    public void clearIconCache() {
        this.fileView.clearIconCache();
    }

    private class FlatFileView
    extends BasicFileChooserUI.BasicFileView {
        private FlatFileView() {
            super(FlatFileChooserUI.this);
        }

        @Override
        public Icon getIcon(File file) {
            Icon icon = this.getCachedIcon(file);
            if (icon != null) {
                return icon;
            }
            if (file != null && (icon = FlatFileChooserUI.this.getFileChooser().getFileSystemView().getSystemIcon(file)) != null) {
                if (icon instanceof ImageIcon) {
                    icon = new ScaledImageIcon((ImageIcon)icon);
                }
                this.cacheIcon(file, icon);
                return icon;
            }
            icon = super.getIcon(file);
            if (icon instanceof ImageIcon) {
                icon = new ScaledImageIcon((ImageIcon)icon);
                this.cacheIcon(file, icon);
            }
            return icon;
        }
    }
}

