/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.plaf.UIResource;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class FlatCaret
extends DefaultCaret
implements UIResource {
    private final String selectAllOnFocusPolicy;
    private final boolean selectAllOnMouseClick;
    private boolean wasFocused;
    private boolean wasTemporaryLost;
    private boolean isMousePressed;

    public FlatCaret(String string, boolean bl) {
        this.selectAllOnFocusPolicy = string;
        this.selectAllOnMouseClick = bl;
    }

    @Override
    public void install(JTextComponent jTextComponent) {
        int n2;
        super.install(jTextComponent);
        Document document = jTextComponent.getDocument();
        if (document != null && this.getDot() == 0 && this.getMark() == 0 && (n2 = document.getLength()) > 0) {
            this.setDot(n2);
        }
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (!(this.wasTemporaryLost || this.isMousePressed && !this.selectAllOnMouseClick)) {
            this.selectAllOnFocusGained();
        }
        this.wasTemporaryLost = (int)((long)1363979686 ^ (long)1363979686);
        this.wasFocused = (int)551039109L ^ 0x20D83084;
        super.focusGained(focusEvent);
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        this.wasTemporaryLost = focusEvent.isTemporary();
        super.focusLost(focusEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.isMousePressed = (int)((long)1041209077 ^ (long)1041209076);
        super.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        this.isMousePressed = (int)((long)-240388399 ^ (long)-240388399);
        super.mouseReleased(mouseEvent);
    }

    protected void selectAllOnFocusGained() {
        JTextComponent jTextComponent = this.getComponent();
        Document document = jTextComponent.getDocument();
        if (document == null || !jTextComponent.isEnabled() || !jTextComponent.isEditable()) {
            return;
        }
        Object object = jTextComponent.getClientProperty("JTextField.selectAllOnFocusPolicy");
        if (object == null) {
            object = this.selectAllOnFocusPolicy;
        }
        if ("never".equals(object)) {
            return;
        }
        if (!"always".equals(object)) {
            int n2;
            if (this.wasFocused) {
                return;
            }
            int n3 = this.getDot();
            if (n3 != (n2 = this.getMark()) || n3 != document.getLength()) {
                return;
            }
        }
        if (jTextComponent instanceof JFormattedTextField) {
            EventQueue.invokeLater(() -> {
                this.setDot((int)-1111178751L ^ 0xBDC4C201);
                this.moveDot(document.getLength());
            });
        } else {
            this.setDot((int)-471766283L ^ 0xE3E16AF5);
            this.moveDot(document.getLength());
        }
    }
}

