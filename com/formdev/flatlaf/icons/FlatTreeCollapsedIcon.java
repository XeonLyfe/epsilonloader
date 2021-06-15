/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeCollapsedIcon
extends FlatAbstractIcon {
    private final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));

    public FlatTreeCollapsedIcon() {
        this(UIManager.getColor("Tree.icon.collapsedColor"));
    }

    FlatTreeCollapsedIcon(Color color) {
        super((int)-104636006L ^ 0xF9C36191, (int)102054744L ^ 0x6153B53, color);
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        this.rotate(component, graphics2D);
        if (this.chevron) {
            double[] arrd = new double[(int)((long)273144831 ^ (long)273144830) << 4];
            arrd[(int)((long)644720374 ^ (long)644720374)] = Double.longBitsToDouble((long)1783692267 ^ 0x400800006A50FBEBL);
            arrd[(int)260659722L ^ 260659723] = 1.0;
            arrd[(int)((long)1866948441 ^ (long)1866948440) << 1] = Double.longBitsToDouble(0x4CDF5E79522B5A3EL ^ 0xCD75E79522B5A3EL);
            arrd[(int)((long)1969773893 ^ (long)1969773894)] = Double.longBitsToDouble((long)1227923108 ^ 0x4004000049309EA4L);
            arrd[(int)((long)-87073442 ^ (long)-87073441) << 2] = Double.longBitsToDouble(0x40EA2DCCB5601214L ^ 0xF22DCCB5601214L);
            arrd[(int)((long)-919374320 ^ (long)-919374315)] = Double.longBitsToDouble(0xC96E9FBB2C408C18L ^ 0x89789FBB2C408C18L);
            arrd[(int)((long)898462506 ^ (long)898462505) << 1] = Double.longBitsToDouble(0xB4FF3C656B6C6AF0L ^ 0xF4F73C656B6C6AF0L);
            arrd[(int)((long)1175495664 ^ (long)1175495671)] = Double.longBitsToDouble((long)1742428963 ^ 0x4021000067DB5B23L);
            arrd[((int)-159099759L ^ -159099760) << 3] = Double.longBitsToDouble((long)1385542435 ^ 0x400800005295B323L);
            arrd[(int)-1840568397L ^ -1840568390] = Double.longBitsToDouble((long)1908171613 ^ 0x4024000071BC635DL);
            arrd[(int)((long)-1445691851 ^ (long)-1445691856) << 1] = Double.longBitsToDouble(0x32CB9CCE15ECCL ^ 0x40112CB9CCE15ECCL);
            arrd[(int)1800307816L ^ 1800307811] = Double.longBitsToDouble((long)1841813134 ^ 0x402400006DC7D68EL);
            arrd[((int)258414066L ^ 258414065) << 2] = Double.longBitsToDouble((long)2046479143 ^ 0x4022000079FACB27L);
            arrd[(int)-164349972L ^ -164349983] = Double.longBitsToDouble(0x5EBCF71B794952AAL ^ 0x1EAAF71B794952AAL);
            arrd[((int)-299373479L ^ -299373474) << 1] = Double.longBitsToDouble(0x5F9EBF1E2B2F81BAL ^ 0x1F8CBF1E2B2F81BAL);
            arrd[(int)2037023793L ^ 2037023806] = 1.0;
            graphics2D.fill(FlatUIUtils.createPath(arrd));
        } else {
            double[] arrd = new double[(int)((long)-1779591931 ^ (long)-1779591930) << 1];
            arrd[(int)-1331395681L ^ -1331395681] = Double.longBitsToDouble(0xDF8771A5EFE26F9FL ^ 0x9F8771A5EFE26F9FL);
            arrd[(int)1317691101L ^ 1317691100] = 1.0;
            arrd[(int)((long)-1300707561 ^ (long)-1300707562) << 1] = Double.longBitsToDouble(0x411A170A15A7A657L ^ 0x11A170A15A7A657L);
            arrd[(int)-431615499L ^ -431615498] = Double.longBitsToDouble((long)587142805 ^ 0x4024000022FF1695L);
            arrd[(int)((long)-1584997190 ^ (long)-1584997189) << 2] = Double.longBitsToDouble(0xD0AA198D1A7154C9L ^ 0x908E198D1A7154C9L);
            arrd[(int)((long)-2037708191 ^ (long)-2037708188)] = Double.longBitsToDouble(0x6385A0725BB359C3L ^ 0x2393A0725BB359C3L);
            graphics2D.fill(FlatUIUtils.createPath(arrd));
        }
    }

    void rotate(Component component, Graphics2D graphics2D) {
        if (!component.getComponentOrientation().isLeftToRight()) {
            graphics2D.rotate(Math.toRadians(Double.longBitsToDouble(0x3FC2F2EDB9875B4CL ^ 0x7FA472EDB9875B4CL)), (double)this.width / Double.longBitsToDouble((long)368446109 ^ 0x4000000015F60A9DL), (double)this.height / Double.longBitsToDouble((long)1352416708 ^ 0x40000000509C3DC4L));
        }
    }
}

