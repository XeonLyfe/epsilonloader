/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatHelpButtonIcon
extends FlatAbstractIcon {
    protected final int focusWidth = UIManager.getInt("Component.focusWidth");
    protected final Color focusColor = UIManager.getColor("Component.focusColor");
    protected final float innerFocusWidth = FlatUIUtils.getUIFloat("HelpButton.innerFocusWidth", FlatUIUtils.getUIFloat("Component.innerFocusWidth", 0.0f));
    protected final int borderWidth = FlatUIUtils.getUIInt("HelpButton.borderWidth", (int)((long)60862024 ^ (long)60862025));
    protected final Color borderColor = UIManager.getColor("HelpButton.borderColor");
    protected final Color disabledBorderColor = UIManager.getColor("HelpButton.disabledBorderColor");
    protected final Color focusedBorderColor = UIManager.getColor("HelpButton.focusedBorderColor");
    protected final Color hoverBorderColor = UIManager.getColor("HelpButton.hoverBorderColor");
    protected final Color background = UIManager.getColor("HelpButton.background");
    protected final Color disabledBackground = UIManager.getColor("HelpButton.disabledBackground");
    protected final Color focusedBackground = UIManager.getColor("HelpButton.focusedBackground");
    protected final Color hoverBackground = UIManager.getColor("HelpButton.hoverBackground");
    protected final Color pressedBackground = UIManager.getColor("HelpButton.pressedBackground");
    protected final Color questionMarkColor = UIManager.getColor("HelpButton.questionMarkColor");
    protected final Color disabledQuestionMarkColor = UIManager.getColor("HelpButton.disabledQuestionMarkColor");
    protected final int iconSize = ((int)((long)1396294817 ^ (long)1396294826) << 1) + this.focusWidth * (((int)-1509296811L ^ 0xA609F554) << 1);

    public FlatHelpButtonIcon() {
        super((int)((long)789055658 ^ (long)789055658), (int)((long)1001662175 ^ (long)1001662175), null);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        boolean bl = component.isEnabled();
        boolean bl2 = FlatUIUtils.isPermanentFocusOwner(component);
        float f2 = Float.intBitsToFloat((int)((long)1938562799 ^ (long)1284251375));
        float f3 = this.iconSize - ((int)-85773789L ^ 0xFAE33222);
        if (bl2 && FlatButtonUI.isFocusPainted(component)) {
            graphics2D.setColor(this.focusColor);
            graphics2D.fill(new Ellipse2D.Float(f2, f2, f3, f3));
        }
        graphics2D.setColor(FlatButtonUI.buttonStateColor(component, this.borderColor, this.disabledBorderColor, this.focusedBorderColor, this.hoverBorderColor, null));
        graphics2D.fill(new Ellipse2D.Float(f2 += (float)this.focusWidth, f2, f3 -= (float)(this.focusWidth * (((int)1443544472L ^ 0x560ABD99) << 1)), f3));
        f2 += (float)this.borderWidth;
        f3 -= (float)(this.borderWidth * (((int)-82002408L ^ 0xFB1CBE19) << 1));
        if (this.innerFocusWidth > 0.0f && bl2 && FlatButtonUI.isFocusPainted(component)) {
            graphics2D.setColor(this.focusColor);
            graphics2D.fill(new Ellipse2D.Float(f2, f2, f3, f3));
            f2 += this.innerFocusWidth;
            f3 -= this.innerFocusWidth * 2.0f;
        }
        graphics2D.setColor(FlatUIUtils.deriveColor(FlatButtonUI.buttonStateColor(component, this.background, this.disabledBackground, this.focusedBackground, this.hoverBackground, this.pressedBackground), this.background));
        graphics2D.fill(new Ellipse2D.Float(f2, f2, f3, f3));
        Path2D.Float float_ = new Path2D.Float();
        ((Path2D)float_).moveTo(Double.longBitsToDouble((long)1382424669 ^ 0x402600005266205DL), Double.longBitsToDouble((long)482029408 ^ 0x401400001CBB2F60L));
        ((Path2D)float_).curveTo(Double.longBitsToDouble(0xE39C3BB537E03B7FL ^ 0xA3BDA22CAE79A2E5L), Double.longBitsToDouble((long)592087995 ^ 0x40140000234A8BBBL), Double.longBitsToDouble(0xA001C4C34BD6674AL ^ 0xE01DC4C34BD6674AL), Double.longBitsToDouble(0x6DAC93954F91064DL ^ 0x2DB7A0A67CA2357EL), Double.longBitsToDouble((long)1470153256 ^ 0x401C000057A0C228L), Double.longBitsToDouble(0xBE26257EA17C6385L ^ 0xFE04257EA17C6385L));
        ((Path2D)float_).lineTo(Double.longBitsToDouble((long)46876625 ^ 0x4022000002CB47D1L), Double.longBitsToDouble(0x3D4408ABAAAA9F65L ^ 0x7D6608ABAAAA9F65L));
        ((Path2D)float_).curveTo(Double.longBitsToDouble(0x16D32869996F1A5AL ^ 0x56F12869996F1A5AL), Double.longBitsToDouble(0x6C6F5E95027D4C7BL ^ 0x2C70C70C9BE4D5E1L), Double.longBitsToDouble(0x1A47461D9C8409EFL ^ 0x5A648AD15048C522L), Double.longBitsToDouble((long)408972291 ^ 0x401C000018606C03L), Double.longBitsToDouble((long)637641443 ^ 0x402600002601A2E3L), Double.longBitsToDouble((long)1923187658 ^ 0x401C000072A183CAL));
        ((Path2D)float_).curveTo(Double.longBitsToDouble((long)1835256602 ^ 0x402833335E50F829L), Double.longBitsToDouble((long)635417623 ^ 0x401C000025DFB417L), Double.longBitsToDouble(0x13D3A6B814C052E6L ^ 0x53F9A6B814C052E6L), Double.longBitsToDouble(0xE573A158D23AAFEFL ^ 0xA56C38C14BA33675L), Double.longBitsToDouble(0x5955C6A77CC03B27L ^ 0x197FC6A77CC03B27L), Double.longBitsToDouble(0x3DE7C970408C5F3EL ^ 0x7DC5C970408C5F3EL));
        ((Path2D)float_).curveTo(Double.longBitsToDouble((long)919388286 ^ 0x402A000036CCC07EL), Double.longBitsToDouble((long)1954600125 ^ 0x402600007480D4BDL), Double.longBitsToDouble(0xC7492ED50B43FF17L ^ 0x876D2ED50B43FF17L), Double.longBitsToDouble(0xDCF8309C67DB75F7L ^ 0x9CDDB09C67DB75F7L), Double.longBitsToDouble(0xAD88EF7CC2E096F2L ^ 0xEDACEF7CC2E096F2L), Double.longBitsToDouble(0xE4561727CAE47661L ^ 0xA47A1727CAE47661L));
        ((Path2D)float_).lineTo(Double.longBitsToDouble((long)714352937 ^ 0x402800002A942929L), Double.longBitsToDouble(0x49F90E4EDEBF29E0L ^ 0x9D50E4EDEBF29E0L));
        ((Path2D)float_).curveTo(Double.longBitsToDouble((long)1677038649 ^ 0x4028000063F59439L), Double.longBitsToDouble(0x8FA6E54DE434AF74L ^ 0xCF81654DE434AF74L), Double.longBitsToDouble((long)1824908432 ^ 0x402E00006CC5E490L), Double.longBitsToDouble(0xF81A869DCF869009L ^ 0xB83D869DCF869009L), Double.longBitsToDouble((long)2016179653 ^ 0x402E0000782C75C5L), Double.longBitsToDouble((long)841681563 ^ 0x40220000322B0A9BL));
        ((Path2D)float_).curveTo(Double.longBitsToDouble(0x5C897E486CEDB842L ^ 0x1CA77E486CEDB842L), Double.longBitsToDouble((long)1045007446 ^ 0x401B33330D7ABF65L), Double.longBitsToDouble(0xA441ABE23F10C499L ^ 0xE46BCD845976A2FFL), Double.longBitsToDouble((long)125558715 ^ 0x40140000077BDFBBL), Double.longBitsToDouble((long)765555668 ^ 0x402600002DA173D4L), Double.longBitsToDouble((long)1468671152 ^ 0x40140000578A24B0L));
        float_.closePath();
        graphics2D.translate(this.focusWidth, this.focusWidth);
        graphics2D.setColor(bl ? this.questionMarkColor : this.disabledQuestionMarkColor);
        graphics2D.fill(float_);
        graphics2D.fillRect((int)((long)-233018602 ^ (long)-233018605) << 1, (int)-1305369905L ^ 0xB231A2C0, (int)((long)-336027039 ^ (long)-336027040) << 1, ((int)1126570479L ^ 0x432619EE) << 1);
    }

    @Override
    public int getIconWidth() {
        return UIScale.scale(this.iconSize);
    }

    @Override
    public int getIconHeight() {
        return UIScale.scale(this.iconSize);
    }
}

