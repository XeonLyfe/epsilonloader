/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.JavaCompatibility;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import javax.swing.JComponent;

public class HiDPIUtils {
    private static Boolean useTextYCorrection;

    public static void paintAtScale1x(Graphics2D graphics2D, JComponent jComponent, Painter painter) {
        HiDPIUtils.paintAtScale1x(graphics2D, (int)-836148181L ^ 0xCE29642B, (int)((long)-327871134 ^ (long)-327871134), jComponent.getWidth(), jComponent.getHeight(), painter);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void paintAtScale1x(Graphics2D graphics2D, int n2, int n3, int n4, int n5, Painter painter) {
        AffineTransform affineTransform = graphics2D.getTransform();
        if (affineTransform.getScaleX() == 1.0 && affineTransform.getScaleY() == 1.0) {
            painter.paint(graphics2D, n2, n3, n4, n5, 1.0);
            return;
        }
        Rectangle2D.Double double_ = HiDPIUtils.scale(affineTransform, n2, n3, n4, n5);
        try {
            graphics2D.setTransform(new AffineTransform(1.0, 0.0, 0.0, 1.0, Math.floor(double_.x), Math.floor(double_.y)));
            int n6 = (int)double_.width;
            int n7 = (int)double_.height;
            painter.paint(graphics2D, (int)1989005104L ^ 0x768DCF30, (int)176631739L ^ 0xA872FBB, n6, n7, affineTransform.getScaleX());
        }
        finally {
            graphics2D.setTransform(affineTransform);
        }
    }

    private static Rectangle2D.Double scale(AffineTransform affineTransform, int n2, int n3, int n4, int n5) {
        double d2 = affineTransform.getScaleX();
        double d3 = affineTransform.getScaleY();
        double d4 = (double)n2 * d2 + affineTransform.getTranslateX();
        double d5 = (double)n3 * d3 + affineTransform.getTranslateY();
        d2 *= (double)n4;
        d3 *= (double)n5;
        double d6 = HiDPIUtils.normalize(d4);
        double d7 = HiDPIUtils.normalize(d5);
        d2 = HiDPIUtils.normalize(d4 + d2) - d6;
        d3 = HiDPIUtils.normalize(d5 + d3) - d7;
        return new Rectangle2D.Double(d6, d7, d2, d3);
    }

    private static double normalize(double d2) {
        return Math.floor(d2 + Double.longBitsToDouble(0x6614A819A359D861L ^ 0x59C4A819A359D861L)) + Double.longBitsToDouble(0x1499B2FDF884020DL ^ 0x2B49B2FDF884020DL);
    }

    private static boolean useTextYCorrection() {
        if (useTextYCorrection == null) {
            useTextYCorrection = FlatSystemProperties.getBoolean("flatlaf.useTextYCorrection", (boolean)((long)-2017256054 ^ (long)-2017256053));
        }
        return useTextYCorrection;
    }

    public static float computeTextYCorrection(Graphics2D graphics2D) {
        if (!HiDPIUtils.useTextYCorrection() || !SystemInfo.isWindows) {
            return 0.0f;
        }
        if (!SystemInfo.isJava_9_orLater) {
            return UIScale.getUserScaleFactor() > 1.0f ? -UIScale.scale(Float.intBitsToFloat(0x4FAB6A20 ^ 0x708B6A20)) : 0.0f;
        }
        AffineTransform affineTransform = graphics2D.getTransform();
        double d2 = affineTransform.getScaleY();
        if (d2 < Double.longBitsToDouble((long)814767676 ^ 0x3FF4000030905E3CL)) {
            return 0.0f;
        }
        if (d2 <= Double.longBitsToDouble(0x95CF02B533DF3FDCL ^ 0xAA3B02B533DF3FDCL)) {
            return Float.intBitsToFloat((int)1401228579L ^ 0xECE50D23);
        }
        if (d2 <= Double.longBitsToDouble(0xC709AFEEE9857AB6L ^ 0xF8F1AFEEE9857AB6L)) {
            return Float.intBitsToFloat((int)((long)1783570105 ^ (long)-714137927));
        }
        if (d2 <= Double.longBitsToDouble((long)1775287285 ^ 0x3FFC000069D0BBF5L)) {
            return Float.intBitsToFloat((int)1673048862L ^ 0xDCD8B31E);
        }
        if (d2 <= Double.longBitsToDouble(0xA51DCCCB862AF89FL ^ 0xE51DCCCB862AF89FL)) {
            return Float.intBitsToFloat((int)((long)898531582 ^ (long)-1966178050));
        }
        if (d2 <= Double.longBitsToDouble(0x29D07E6CE89E1C6DL ^ 0x69D27E6CE89E1C6DL)) {
            return Float.intBitsToFloat((int)1555012374L ^ 0xE3CF9B16);
        }
        if (d2 <= Double.longBitsToDouble(0x903B16668594A3A1L ^ 0xD03716668594A3A1L)) {
            return Float.intBitsToFloat((int)((long)666157185 ^ (long)-1728790399));
        }
        return Float.intBitsToFloat(0x650CD357 ^ 0xDA6CD357);
    }

    public static void drawStringWithYCorrection(JComponent jComponent, Graphics2D graphics2D, String string, int n2, int n3) {
        HiDPIUtils.drawStringUnderlineCharAtWithYCorrection(jComponent, graphics2D, string, (int)((long)316667467 ^ (long)-316667468), n2, n3);
    }

    public static void drawStringUnderlineCharAtWithYCorrection(JComponent jComponent, Graphics2D graphics2D, String string, int n2, int n3, int n4) {
        float f2 = HiDPIUtils.computeTextYCorrection(graphics2D);
        if (f2 != 0.0f) {
            graphics2D.translate(0.0, f2);
            JavaCompatibility.drawStringUnderlineCharAt(jComponent, graphics2D, string, n2, n3, n4);
            graphics2D.translate(0.0, -f2);
        } else {
            JavaCompatibility.drawStringUnderlineCharAt(jComponent, graphics2D, string, n2, n3, n4);
        }
    }

    public static Graphics2D createGraphicsTextYCorrection(Graphics2D graphics2D) {
        final float f2 = HiDPIUtils.computeTextYCorrection(graphics2D);
        if (f2 == 0.0f) {
            return graphics2D;
        }
        return new Graphics2DProxy(graphics2D){

            @Override
            public void drawString(String string, int n2, int n3) {
                super.drawString(string, (float)n2, (float)n3 + f2);
            }

            @Override
            public void drawString(String string, float f22, float f3) {
                super.drawString(string, f22, f3 + f2);
            }

            @Override
            public void drawString(AttributedCharacterIterator attributedCharacterIterator, int n2, int n3) {
                super.drawString(attributedCharacterIterator, (float)n2, (float)n3 + f2);
            }

            @Override
            public void drawString(AttributedCharacterIterator attributedCharacterIterator, float f22, float f3) {
                super.drawString(attributedCharacterIterator, f22, f3 + f2);
            }

            @Override
            public void drawChars(char[] arrc, int n2, int n3, int n4, int n5) {
                super.drawChars(arrc, n2, n3, n4, Math.round((float)n5 + f2));
            }

            @Override
            public void drawBytes(byte[] arrby, int n2, int n3, int n4, int n5) {
                super.drawBytes(arrby, n2, n3, n4, Math.round((float)n5 + f2));
            }

            @Override
            public void drawGlyphVector(GlyphVector glyphVector, float f22, float f3) {
                super.drawGlyphVector(glyphVector, f22, f3 + f2);
            }
        };
    }

    public static interface Painter {
        public void paint(Graphics2D var1, int var2, int var3, int var4, int var5, double var6);
    }
}

