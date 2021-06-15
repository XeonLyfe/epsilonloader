/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.ColorFunctions;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;

public class DerivedColor
extends ColorUIResource {
    private final ColorFunctions.ColorFunction[] functions;
    private boolean hasBaseOfDefaultColor;
    private int baseOfDefaultColorRGB;

    public DerivedColor(Color color, ColorFunctions.ColorFunction ... arrcolorFunction) {
        super(color != null ? color : Color.red);
        this.functions = arrcolorFunction;
    }

    public Color derive(Color color) {
        if (this.hasBaseOfDefaultColor && this.baseOfDefaultColorRGB == color.getRGB() || color == this) {
            return this;
        }
        Color color2 = ColorFunctions.applyFunctions(color, this.functions);
        if (!this.hasBaseOfDefaultColor && color2.getRGB() == this.getRGB()) {
            this.hasBaseOfDefaultColor = (int)825786257L ^ 0x31387F90;
            this.baseOfDefaultColorRGB = color.getRGB();
        }
        return color2;
    }

    public ColorFunctions.ColorFunction[] getFunctions() {
        return this.functions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        ColorFunctions.ColorFunction[] arrcolorFunction = this.functions;
        int n2 = arrcolorFunction.length;
        for (int i2 = (int)((long)-1117014568 ^ (long)-1117014568); i2 < n2; ++i2) {
            ColorFunctions.ColorFunction colorFunction = arrcolorFunction[i2];
            stringBuilder.append(((int)-104106851L ^ 0xF9CB7498) << 1);
            stringBuilder.append(colorFunction.toString());
        }
        return stringBuilder.toString();
    }
}

