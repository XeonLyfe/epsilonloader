/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class FlatDropShadowBorder
extends FlatEmptyBorder {
    private final Color shadowColor;
    private final Insets shadowInsets;
    private final float shadowOpacity;
    private final int shadowSize;
    private Image shadowImage;
    private Color lastShadowColor;
    private double lastSystemScaleFactor;
    private float lastUserScaleFactor;

    public FlatDropShadowBorder() {
        this((Color)null);
    }

    public FlatDropShadowBorder(Color color) {
        this(color, (int)((long)-1799967861 ^ (long)-1799967862) << 2, Float.intBitsToFloat((int)((long)724324422 ^ (long)338448454)));
    }

    public FlatDropShadowBorder(Color color, int n2, float f2) {
        this(color, new Insets(-n2, -n2, n2, n2), f2);
    }

    public FlatDropShadowBorder(Color color, Insets insets, float f2) {
        super(Math.max(insets.top, (int)800837841L ^ 0x2FBBD0D1), Math.max(insets.left, (int)((long)853037145 ^ (long)853037145)), Math.max(insets.bottom, (int)((long)859386407 ^ (long)859386407)), Math.max(insets.right, (int)-1537957989L ^ 0xA4549F9B));
        this.shadowColor = color;
        this.shadowInsets = insets;
        this.shadowOpacity = f2;
        this.shadowSize = Math.max(Math.max(insets.left, insets.right), Math.max(insets.top, insets.bottom));
    }

    @Override
    public void paintBorder(Component component, Graphics graphics, int n2, int n3, int n4, int n5) {
        if (this.shadowSize <= 0) {
            return;
        }
        HiDPIUtils.paintAtScale1x((Graphics2D)graphics, n2, n3, n4, n5, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5) -> this.paintImpl(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5));
    }

    private void paintImpl(Graphics2D graphics2D, int n2, int n3, int n4, int n5, double d2) {
        Color color = this.shadowColor != null ? this.shadowColor : graphics2D.getColor();
        int n6 = this.scale(this.shadowSize, d2);
        float f2 = UIScale.getUserScaleFactor();
        if (this.shadowImage == null || !color.equals(this.lastShadowColor) || this.lastSystemScaleFactor != d2 || this.lastUserScaleFactor != f2) {
            this.shadowImage = FlatDropShadowBorder.createShadowImage(color, n6, this.shadowOpacity, (float)(d2 * (double)f2));
            this.lastShadowColor = color;
            this.lastSystemScaleFactor = d2;
            this.lastUserScaleFactor = f2;
        }
        int n7 = this.scale(this.shadowInsets.left, d2);
        int n8 = this.scale(this.shadowInsets.right, d2);
        int n9 = this.scale(this.shadowInsets.top, d2);
        int n10 = this.scale(this.shadowInsets.bottom, d2);
        int n11 = n2 - Math.min(n7, (int)-791854370L ^ 0xD0CD42DE);
        int n12 = n3 - Math.min(n9, (int)((long)1985102128 ^ (long)1985102128));
        int n13 = n2 + n4 + Math.min(n8, (int)((long)-263646111 ^ (long)-263646111));
        int n14 = n3 + n5 + Math.min(n10, (int)((long)-327854205 ^ (long)-327854205));
        int n15 = n11 + n6;
        int n16 = n12 + n6;
        int n17 = n13 - n6;
        int n18 = n14 - n6;
        int n19 = n6 * ((int)((long)1555882606 ^ (long)1555882607) << 1) - ((int)-959093308L ^ 0xC6D565C5);
        int n20 = n6 - (int)((long)-1609939718 ^ (long)-1609939717);
        if (n7 > 0 || n9 > 0) {
            graphics2D.drawImage(this.shadowImage, n11, n12, n15, n16, (int)((long)50283711 ^ (long)50283711), (int)((long)-2097870080 ^ (long)-2097870080), n20, n20, null);
        }
        if (n9 > 0) {
            graphics2D.drawImage(this.shadowImage, n15, n12, n17, n16, n20, (int)((long)-1486950074 ^ (long)-1486950074), n20 + ((int)130098588L ^ 0x7C1259D), n20, null);
        }
        if (n8 > 0 || n9 > 0) {
            graphics2D.drawImage(this.shadowImage, n17, n12, n13, n16, n20, (int)-763276476L ^ 0xD2815344, n19, n20, null);
        }
        if (n7 > 0) {
            graphics2D.drawImage(this.shadowImage, n11, n16, n15, n18, (int)1004185636L ^ 0x3BDAA824, n20, n20, n20 + (int)((long)-748111464 ^ (long)-748111463), null);
        }
        if (n8 > 0) {
            graphics2D.drawImage(this.shadowImage, n17, n16, n13, n18, n20, n20, n19, n20 + ((int)-367680731L ^ 0xEA15A324), null);
        }
        if (n7 > 0 || n10 > 0) {
            graphics2D.drawImage(this.shadowImage, n11, n18, n15, n14, (int)((long)481907042 ^ (long)481907042), n20, n20, n19, null);
        }
        if (n10 > 0) {
            graphics2D.drawImage(this.shadowImage, n15, n18, n17, n14, n20, n20, n20 + ((int)711292475L ^ 0x2A65763A), n19, null);
        }
        if (n8 > 0 || n10 > 0) {
            graphics2D.drawImage(this.shadowImage, n17, n18, n13, n14, n20, n20, n19, n19, null);
        }
    }

    private int scale(int n2, double d2) {
        return (int)Math.ceil((double)UIScale.scale(n2) * d2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static BufferedImage createShadowImage(Color color, int n2, float f2, float f3) {
        int n3 = color.getRGB() & ((int)496859242L ^ 0x1D628795);
        int n4 = (int)(Float.intBitsToFloat((int)((long)1064285577 ^ (long)2081469833)) * f2);
        Color color2 = new Color(n3 | (n4 & ((int)1669427715L ^ 0x638172FC)) << (((int)-711994349L ^ 0xD58FD410) << 3), (boolean)((long)673307863 ^ (long)673307862));
        Color color3 = new Color(n3 | (n4 / (((int)1819111017L ^ 0x6C6D6E68) << 1) & (int)((long)2097031976 ^ (long)2097032151)) << ((int)((long)-1832594327 ^ (long)-1832594326) << 3), (boolean)((long)-643256905 ^ (long)-643256906));
        Color color4 = new Color(n3, (boolean)((long)-1758341828 ^ (long)-1758341827));
        int n5 = n2 * ((int)((long)203172695 ^ (long)203172694) << 1) - ((int)2131069554L ^ 0x7F058A73);
        int n6 = n2 - (int)((long)-1827584014 ^ (long)-1827584013);
        float[] arrf = new float[(int)((long)1708246646 ^ (long)1708246645)];
        arrf[(int)((long)-1958284600 ^ (long)-1958284600)] = 0.0f;
        arrf[(int)((long)855767871 ^ (long)855767870)] = Float.intBitsToFloat((int)((long)1116689346 ^ (long)2084331761));
        arrf[((int)1447896471L ^ 1447896470) << 1] = 1.0f;
        Color[] arrcolor = new Color[(int)652643476L ^ 0x26E68C97];
        arrcolor[(int)-343465192L ^ -343465192] = color2;
        arrcolor[(int)((long)-578756031 ^ (long)-578756032)] = color3;
        arrcolor[((int)-2082719608L ^ -2082719607) << 1] = color4;
        RadialGradientPaint radialGradientPaint = new RadialGradientPaint(n6, (float)n6, (float)n2 - Float.intBitsToFloat(0x1F37AC99 ^ 0x2077AC99) * f3, arrf, arrcolor);
        BufferedImage bufferedImage = new BufferedImage(n5, n5, (int)((long)-1614099114 ^ (long)-1614099113) << 1);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        try {
            graphics2D.setPaint(radialGradientPaint);
            graphics2D.fillRect((int)((long)-549613949 ^ (long)-549613949), (int)((long)1475440511 ^ (long)1475440511), n5, n5);
        }
        finally {
            graphics2D.dispose();
        }
        return bufferedImage;
    }
}

