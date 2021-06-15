/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ScaledImageIcon
implements Icon {
    private final ImageIcon imageIcon;
    private final int iconWidth;
    private final int iconHeight;
    private double lastSystemScaleFactor;
    private float lastUserScaleFactor;
    private Image lastImage;

    public ScaledImageIcon(ImageIcon imageIcon) {
        this(imageIcon, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    public ScaledImageIcon(ImageIcon imageIcon, int n2, int n3) {
        this.imageIcon = imageIcon;
        this.iconWidth = n2;
        this.iconHeight = n3;
    }

    @Override
    public int getIconWidth() {
        return UIScale.scale(this.iconWidth);
    }

    @Override
    public int getIconHeight() {
        return UIScale.scale(this.iconHeight);
    }

    @Override
    public void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        float f2;
        double d2 = UIScale.getSystemScaleFactor((Graphics2D)graphics);
        double d3 = d2 * (double)(f2 = UIScale.getUserScaleFactor());
        if (d3 == 1.0 && this.iconWidth == this.imageIcon.getIconWidth() && this.iconHeight == this.imageIcon.getIconHeight()) {
            this.imageIcon.paintIcon(component, graphics, n2, n3);
            return;
        }
        if (d2 == this.lastSystemScaleFactor && f2 == this.lastUserScaleFactor && this.lastImage != null) {
            this.paintLastImage(graphics, n2, n3);
            return;
        }
        int n4 = (int)Math.round((double)this.iconWidth * d3);
        int n5 = (int)Math.round((double)this.iconHeight * d3);
        Image image = this.getResolutionVariant(n4, n5);
        int n6 = image.getWidth(null);
        int n7 = image.getHeight(null);
        if (n6 != n4 || n7 != n5) {
            Object object = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
            float f3 = (float)n4 / (float)n6;
            if ((float)((int)f3) == f3 && f3 > 1.0f && n6 <= (int)((long)-409637679 ^ (long)-409637680) << 4 && n7 <= ((int)-1833914415L ^ 0x92B0AFD0) << 4) {
                object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
            }
            BufferedImage bufferedImage = this.image2bufferedImage(image);
            image = this.scaleImage(bufferedImage, n4, n5, object);
        }
        this.lastSystemScaleFactor = d2;
        this.lastUserScaleFactor = f2;
        this.lastImage = image;
        this.paintLastImage(graphics, n2, n3);
    }

    protected Image getResolutionVariant(int n2, int n3) {
        return MultiResolutionImageSupport.getResolutionVariant(this.imageIcon.getImage(), n2, n3);
    }

    private void paintLastImage(Graphics graphics, int n6, int n7) {
        if (this.lastSystemScaleFactor > 1.0) {
            HiDPIUtils.paintAtScale1x((Graphics2D)graphics, n6, n7, ((int)-2029468407L ^ 0x8708C510) << 2, (int)((long)1738396764 ^ (long)1738396741) << 2, (graphics2D, n2, n3, n4, n5, d2) -> graphics2D.drawImage(this.lastImage, n2, n3, null));
        } else {
            graphics.drawImage(this.lastImage, n6, n7, null);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private BufferedImage scaleImage(BufferedImage bufferedImage, int n2, int n3, Object object) {
        BufferedImage bufferedImage2 = new BufferedImage(n2, n3, ((int)-2123544989L ^ 0x816D4662) << 1);
        Graphics2D graphics2D = bufferedImage2.createGraphics();
        try {
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
            graphics2D.drawImage(bufferedImage, (int)712810098L ^ 0x2A7C9E72, (int)-1106094507L ^ 0xBE125655, n2, n3, null);
        }
        finally {
            graphics2D.dispose();
        }
        return bufferedImage2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private BufferedImage image2bufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), ((int)-1785966843L ^ 0x958C4F04) << 1);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        try {
            graphics2D.drawImage(image, (int)-16736121L ^ 0xFF00A087, (int)1368136993L ^ 0x518C1D21, null);
        }
        finally {
            graphics2D.dispose();
        }
        return bufferedImage;
    }
}

