/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class FlatCheckBoxIcon
extends FlatAbstractIcon {
    protected final String style = UIManager.getString("CheckBox.icon.style");
    public final int focusWidth = FlatCheckBoxIcon.getUIInt("CheckBox.icon.focusWidth", UIManager.getInt("Component.focusWidth"), this.style);
    protected final Color focusColor = FlatUIUtils.getUIColor("CheckBox.icon.focusColor", UIManager.getColor("Component.focusColor"));
    protected final int arc = FlatUIUtils.getUIInt("CheckBox.arc", (int)((long)1917114848 ^ (long)1917114849) << 1);
    protected final Color borderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.borderColor", this.style);
    protected final Color background = FlatCheckBoxIcon.getUIColor("CheckBox.icon.background", this.style);
    protected final Color selectedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedBorderColor", this.style);
    protected final Color selectedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedBackground", this.style);
    protected final Color checkmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.checkmarkColor", this.style);
    protected final Color disabledBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledBorderColor", this.style);
    protected final Color disabledBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledBackground", this.style);
    protected final Color disabledCheckmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledCheckmarkColor", this.style);
    protected final Color focusedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.focusedBorderColor", this.style);
    protected final Color focusedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.focusedBackground", this.style);
    protected final Color selectedFocusedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedBorderColor", this.style);
    protected final Color selectedFocusedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedBackground", this.style);
    protected final Color selectedFocusedCheckmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedCheckmarkColor", this.style);
    protected final Color hoverBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.hoverBorderColor", this.style);
    protected final Color hoverBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.hoverBackground", this.style);
    protected final Color selectedHoverBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedHoverBackground", this.style);
    protected final Color pressedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.pressedBackground", this.style);
    protected final Color selectedPressedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedPressedBackground", this.style);
    static final int ICON_SIZE = 15;

    protected static Color getUIColor(String string, String string2) {
        Color color;
        if (string2 != null && (color = UIManager.getColor(FlatCheckBoxIcon.styleKey(string, string2))) != null) {
            return color;
        }
        return UIManager.getColor(string);
    }

    protected static int getUIInt(String string, int n2, String string2) {
        Object object;
        if (string2 != null && (object = UIManager.get(FlatCheckBoxIcon.styleKey(string, string2))) instanceof Integer) {
            return (Integer)object;
        }
        return FlatUIUtils.getUIInt(string, n2);
    }

    private static String styleKey(String string, String string2) {
        return string.replace(".icon.", ".icon[" + string2 + "].");
    }

    public FlatCheckBoxIcon() {
        super((int)((long)106589479 ^ (long)106589480), (int)((long)-308684643 ^ (long)-308684654), null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        boolean bl = this.isIndeterminate(component);
        boolean bl2 = bl || this.isSelected(component) ? (int)-1689656729L ^ 0x9B49E266 : (int)((long)-1690660183 ^ (long)-1690660183);
        boolean bl3 = FlatUIUtils.isPermanentFocusOwner(component);
        if (bl3 && this.focusWidth > 0 && FlatButtonUI.isFocusPainted(component)) {
            graphics2D.setColor(this.getFocusColor(component));
            this.paintFocusBorder(component, graphics2D);
        }
        graphics2D.setColor(this.getBorderColor(component, bl2));
        this.paintBorder(component, graphics2D);
        Color color = FlatUIUtils.deriveColor(this.getBackground(component, bl2), bl2 ? this.selectedBackground : this.background);
        if (color.getAlpha() < ((int)921052395L ^ 0x36E62414)) {
            graphics2D.setColor(bl2 ? this.selectedBackground : this.background);
            this.paintBackground(component, graphics2D);
        }
        graphics2D.setColor(color);
        this.paintBackground(component, graphics2D);
        if (bl2 || bl) {
            graphics2D.setColor(this.getCheckmarkColor(component, bl2, bl3));
            if (bl) {
                this.paintIndeterminate(component, graphics2D);
            } else {
                this.paintCheckmark(component, graphics2D);
            }
        }
    }

    protected void paintFocusBorder(Component component, Graphics2D graphics2D) {
        int n2 = (((int)523552093L ^ 0x1F34C55A) << 1) + this.focusWidth * (((int)1835677568L ^ 0x6D6A3781) << 1);
        int n3 = this.arc + this.focusWidth * ((int)((long)-343154744 ^ (long)-343154743) << 1);
        graphics2D.fillRoundRect(-this.focusWidth + (int)((long)-738470793 ^ (long)-738470794), -this.focusWidth, n2, n2, n3, n3);
    }

    protected void paintBorder(Component component, Graphics2D graphics2D) {
        int n2 = this.arc;
        graphics2D.fillRoundRect((int)((long)-183144130 ^ (long)-183144129), (int)-774468009L ^ 0xD1D68E57, ((int)-177301110L ^ 0xF56E998D) << 1, ((int)-2097370505L ^ 0x82FCAA70) << 1, n2, n2);
    }

    protected void paintBackground(Component component, Graphics2D graphics2D) {
        int n2 = this.arc - (int)((long)-1249604763 ^ (long)-1249604764);
        graphics2D.fillRoundRect((int)((long)143418533 ^ (long)143418532) << 1, (int)1499091237L ^ 0x595A5124, ((int)-94721575L ^ 0xFA5AA9DA) << 2, (int)((long)-1396642949 ^ (long)-1396642952) << 2, n2, n2);
    }

    protected void paintCheckmark(Component component, Graphics2D graphics2D) {
        Path2D.Float float_ = new Path2D.Float();
        float_.moveTo(Float.intBitsToFloat((int)((long)1345029038 ^ (long)280724398)), Float.intBitsToFloat(0x34CEC581 ^ 0x743EC581));
        float_.lineTo(Float.intBitsToFloat((int)((long)804071125 ^ (long)1866339814)), Float.intBitsToFloat((int)2038110652L ^ 0x385B19BC));
        float_.lineTo(Float.intBitsToFloat(0x4728FA56 ^ 0x61CFA56), Float.intBitsToFloat((int)((long)1021105705 ^ (long)2092750377)));
        graphics2D.setStroke(new BasicStroke(Float.intBitsToFloat((int)1065848155L ^ 0x74BE68), (int)269480526L ^ 0x100FF24F, (int)((long)1057494559 ^ (long)1057494558)));
        graphics2D.draw(float_);
    }

    protected void paintIndeterminate(Component component, Graphics2D graphics2D) {
        graphics2D.fill(new RoundRectangle2D.Float(Float.intBitsToFloat((int)283278936L ^ 0x50927E58), Float.intBitsToFloat(0x72CF4A13 ^ 0x32774A13), Float.intBitsToFloat((int)((long)1407917729 ^ (long)316874401)), Float.intBitsToFloat(0x34046BC8 ^ 0x74246BC8), 2.0f, 2.0f));
    }

    protected boolean isIndeterminate(Component component) {
        return (component instanceof JComponent && FlatClientProperties.clientPropertyEquals((JComponent)component, "JButton.selectedState", "indeterminate") ? (int)((long)1352640755 ^ (long)1352640754) : (int)((long)213146805 ^ (long)213146805)) != 0;
    }

    public boolean isSelected(Component component) {
        return (component instanceof AbstractButton && ((AbstractButton)component).isSelected() ? (int)((long)263622743 ^ (long)263622742) : (int)((long)-1295589305 ^ (long)-1295589305)) != 0;
    }

    protected Color getFocusColor(Component component) {
        return this.focusColor;
    }

    protected Color getBorderColor(Component component, boolean bl) {
        return FlatButtonUI.buttonStateColor(component, bl ? this.selectedBorderColor : this.borderColor, this.disabledBorderColor, bl && this.selectedFocusedBorderColor != null ? this.selectedFocusedBorderColor : this.focusedBorderColor, this.hoverBorderColor, null);
    }

    protected Color getBackground(Component component, boolean bl) {
        return FlatButtonUI.buttonStateColor(component, bl ? this.selectedBackground : this.background, this.disabledBackground, bl && this.selectedFocusedBackground != null ? this.selectedFocusedBackground : this.focusedBackground, bl && this.selectedHoverBackground != null ? this.selectedHoverBackground : this.hoverBackground, bl && this.selectedPressedBackground != null ? this.selectedPressedBackground : this.pressedBackground);
    }

    protected Color getCheckmarkColor(Component component, boolean bl, boolean bl2) {
        return component.isEnabled() ? (bl && bl2 && this.selectedFocusedCheckmarkColor != null ? this.selectedFocusedCheckmarkColor : this.checkmarkColor) : this.disabledCheckmarkColor;
    }
}

