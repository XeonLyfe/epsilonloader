/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatCaret;
import com.formdev.flatlaf.ui.FlatTextFieldUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

public class FlatPasswordFieldUI
extends BasicPasswordFieldUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    protected Color placeholderForeground;
    protected boolean showCapsLock;
    protected Icon capsLockIcon;
    private FocusListener focusListener;
    private KeyListener capsLockListener;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatPasswordFieldUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        String string = this.getPropertyPrefix();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.placeholderForeground = UIManager.getColor(string + ".placeholderForeground");
        this.showCapsLock = UIManager.getBoolean("PasswordField.showCapsLock");
        this.capsLockIcon = UIManager.getIcon("PasswordField.capsLockIcon");
        LookAndFeel.installProperty(this.getComponent(), "opaque", (boolean)((long)-47937832 ^ (long)-47937832));
        MigLayoutVisualPadding.install(this.getComponent());
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.placeholderForeground = null;
        this.capsLockIcon = null;
        MigLayoutVisualPadding.uninstall(this.getComponent());
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.focusListener = new FlatUIUtils.RepaintFocusListener(this.getComponent());
        this.capsLockListener = new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                this.repaint(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                this.repaint(keyEvent);
            }

            private void repaint(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == (int)((long)474921468 ^ (long)474921465) << 2) {
                    keyEvent.getComponent().repaint();
                }
            }
        };
        this.getComponent().addFocusListener(this.focusListener);
        this.getComponent().addKeyListener(this.capsLockListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.getComponent().removeFocusListener(this.focusListener);
        this.getComponent().removeKeyListener(this.capsLockListener);
        this.focusListener = null;
        this.capsLockListener = null;
    }

    @Override
    protected Caret createCaret() {
        return new FlatCaret(UIManager.getString("TextComponent.selectAllOnFocusPolicy"), UIManager.getBoolean("TextComponent.selectAllOnMouseClick"));
    }

    @Override
    protected void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        super.propertyChange(propertyChangeEvent);
        FlatTextFieldUI.propertyChange(this.getComponent(), propertyChangeEvent);
    }

    @Override
    protected void paintSafely(Graphics graphics) {
        FlatTextFieldUI.paintBackground(graphics, this.getComponent(), this.isIntelliJTheme);
        FlatTextFieldUI.paintPlaceholder(graphics, this.getComponent(), this.placeholderForeground);
        this.paintCapsLock(graphics);
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)graphics));
    }

    protected void paintCapsLock(Graphics graphics) {
        if (!this.showCapsLock) {
            return;
        }
        JTextComponent jTextComponent = this.getComponent();
        if (!FlatUIUtils.isPermanentFocusOwner(jTextComponent) || !Toolkit.getDefaultToolkit().getLockingKeyState(((int)1098125848L ^ 0x4174121D) << 2)) {
            return;
        }
        int n2 = (jTextComponent.getHeight() - this.capsLockIcon.getIconHeight()) / (((int)-1598368329L ^ 0xA0BAD5B6) << 1);
        int n3 = jTextComponent.getWidth() - this.capsLockIcon.getIconWidth() - n2;
        this.capsLockIcon.paintIcon(jTextComponent, graphics, n3, n2);
    }

    @Override
    protected void paintBackground(Graphics graphics) {
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return FlatTextFieldUI.applyMinimumWidth(jComponent, super.getPreferredSize(jComponent), this.minimumWidth);
    }

    @Override
    public Dimension getMinimumSize(JComponent jComponent) {
        return FlatTextFieldUI.applyMinimumWidth(jComponent, super.getMinimumSize(jComponent), this.minimumWidth);
    }
}

