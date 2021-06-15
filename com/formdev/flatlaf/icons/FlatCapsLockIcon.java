/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.UIManager;

public class FlatCapsLockIcon
extends FlatAbstractIcon {
    public FlatCapsLockIcon() {
        super(((int)-888196670L ^ 0xCB0F31C3) << 4, ((int)1971147264L ^ 0x757D5201) << 4, UIManager.getColor("PasswordField.capsLockIconColor"));
    }

    @Override
    protected void paintIcon(Component component, Graphics2D graphics2D) {
        Path2D.Float float_ = new Path2D.Float((int)((long)489230944 ^ (long)489230944));
        float_.append(new RoundRectangle2D.Float(0.0f, 0.0f, Float.intBitsToFloat((int)((long)662307262 ^ (long)1727660478)), Float.intBitsToFloat((int)((long)1046967617 ^ (long)2145875265)), Float.intBitsToFloat(0x784C7B0E ^ 0x388C7B0E), Float.intBitsToFloat((int)((long)1953710092 ^ (long)884162572))), ((int)-1276444484L ^ 0xB3EB00BC) != 0);
        float_.append(new Rectangle2D.Float(Float.intBitsToFloat(0x7BAEE7CA ^ 0x3B0EE7CA), Float.intBitsToFloat((int)301960580L ^ 0x50BF8D84), Float.intBitsToFloat((int)815265620L ^ 0x7057F754), 2.0f), (boolean)((long)1550651157 ^ (long)1550651157));
        double[] arrd = new double[(int)((long)1759685088 ^ (long)1759685095) << 1];
        arrd[(int)1287421622L ^ 1287421622] = Double.longBitsToDouble((long)677296664 ^ 0x40000000285EBA18L);
        arrd[(int)((long)-195668965 ^ (long)-195668966)] = Double.longBitsToDouble(0xF11FFF23BF4FC98FL ^ 0xB13FFF23BF4FC98FL);
        arrd[(int)((long)-453221567 ^ (long)-453221568) << 1] = Double.longBitsToDouble(0x81A0470FC312574FL ^ 0xC180470FC312574FL);
        arrd[(int)((long)-437377520 ^ (long)-437377517)] = Double.longBitsToDouble(0x8763CD14E9355BF4L ^ 0xC763CD14E9355BF4L);
        arrd[((int)-1841670303L ^ -1841670304) << 2] = Double.longBitsToDouble(0x67CD583BEDF05A58L ^ 0x27E1583BEDF05A58L);
        arrd[(int)963601246L ^ 963601243] = Double.longBitsToDouble(0x71E150999DE176A7L ^ 0x31C150999DE176A7L);
        arrd[((int)299564888L ^ 299564891) << 1] = Double.longBitsToDouble((long)1402825548 ^ 0x40260000539D6B4CL);
        arrd[(int)1351089253L ^ 1351089250] = Double.longBitsToDouble(0xF7B27E98C81CE975L ^ 0xB7927E98C81CE975L);
        arrd[((int)-1372456016L ^ -1372456015) << 3] = Double.longBitsToDouble(0x9E428843DAD262D5L ^ 0xDE648843DAD262D5L);
        arrd[(int)((long)-564656585 ^ (long)-564656578)] = Double.longBitsToDouble((long)1146591399 ^ 0x40240000445798A7L);
        arrd[((int)-2143049785L ^ -2143049790) << 1] = Double.longBitsToDouble((long)654192136 ^ 0x4014000026FE2E08L);
        arrd[(int)200700393L ^ 200700386] = Double.longBitsToDouble(0x2B6068416CDD2B9FL ^ 0x6B4468416CDD2B9FL);
        arrd[((int)-1388039635L ^ -1388039634) << 2] = Double.longBitsToDouble((long)2086999107 ^ 0x401400007C651443L);
        arrd[(int)1928852167L ^ 1928852170] = Double.longBitsToDouble(0x5204C8CDA2D266D6L ^ 0x1224C8CDA2D266D6L);
        float_.append(FlatUIUtils.createPath(arrd), ((int)-758593898L ^ 0xD2C8C696) != 0);
        graphics2D.fill(float_);
    }
}

