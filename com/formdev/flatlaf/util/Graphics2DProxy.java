/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Graphics2DProxy
extends Graphics2D {
    private final Graphics2D delegate;

    public Graphics2DProxy(Graphics2D graphics2D) {
        this.delegate = graphics2D;
    }

    @Override
    public Graphics create() {
        return this.delegate.create();
    }

    @Override
    public Graphics create(int n2, int n3, int n4, int n5) {
        return this.delegate.create(n2, n3, n4, n5);
    }

    @Override
    public Color getColor() {
        return this.delegate.getColor();
    }

    @Override
    public void setColor(Color color) {
        this.delegate.setColor(color);
    }

    @Override
    public void setPaintMode() {
        this.delegate.setPaintMode();
    }

    @Override
    public void setXORMode(Color color) {
        this.delegate.setXORMode(color);
    }

    @Override
    public Font getFont() {
        return this.delegate.getFont();
    }

    @Override
    public void setFont(Font font) {
        this.delegate.setFont(font);
    }

    @Override
    public FontMetrics getFontMetrics() {
        return this.delegate.getFontMetrics();
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return this.delegate.getFontMetrics(font);
    }

    @Override
    public Rectangle getClipBounds() {
        return this.delegate.getClipBounds();
    }

    @Override
    public void clipRect(int n2, int n3, int n4, int n5) {
        this.delegate.clipRect(n2, n3, n4, n5);
    }

    @Override
    public void setClip(int n2, int n3, int n4, int n5) {
        this.delegate.setClip(n2, n3, n4, n5);
    }

    @Override
    public Shape getClip() {
        return this.delegate.getClip();
    }

    @Override
    public void setClip(Shape shape) {
        this.delegate.setClip(shape);
    }

    @Override
    public void copyArea(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.delegate.copyArea(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void drawLine(int n2, int n3, int n4, int n5) {
        this.delegate.drawLine(n2, n3, n4, n5);
    }

    @Override
    public void fillRect(int n2, int n3, int n4, int n5) {
        this.delegate.fillRect(n2, n3, n4, n5);
    }

    @Override
    public void drawRect(int n2, int n3, int n4, int n5) {
        this.delegate.drawRect(n2, n3, n4, n5);
    }

    @Override
    public void clearRect(int n2, int n3, int n4, int n5) {
        this.delegate.clearRect(n2, n3, n4, n5);
    }

    @Override
    public void drawRoundRect(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.delegate.drawRoundRect(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void fillRoundRect(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.delegate.fillRoundRect(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void drawOval(int n2, int n3, int n4, int n5) {
        this.delegate.drawOval(n2, n3, n4, n5);
    }

    @Override
    public void fillOval(int n2, int n3, int n4, int n5) {
        this.delegate.fillOval(n2, n3, n4, n5);
    }

    @Override
    public void drawArc(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.delegate.drawArc(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void fillArc(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.delegate.fillArc(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void drawPolyline(int[] arrn, int[] arrn2, int n2) {
        this.delegate.drawPolyline(arrn, arrn2, n2);
    }

    @Override
    public void drawPolygon(int[] arrn, int[] arrn2, int n2) {
        this.delegate.drawPolygon(arrn, arrn2, n2);
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        this.delegate.drawPolygon(polygon);
    }

    @Override
    public void fillPolygon(int[] arrn, int[] arrn2, int n2) {
        this.delegate.fillPolygon(arrn, arrn2, n2);
    }

    @Override
    public void fillPolygon(Polygon polygon) {
        this.delegate.fillPolygon(polygon);
    }

    @Override
    public void drawChars(char[] arrc, int n2, int n3, int n4, int n5) {
        this.delegate.drawChars(arrc, n2, n3, n4, n5);
    }

    @Override
    public void drawBytes(byte[] arrby, int n2, int n3, int n4, int n5) {
        this.delegate.drawBytes(arrby, n2, n3, n4, n5);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, int n4, int n5, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, n4, n5, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, Color color, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, color, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, int n4, int n5, Color color, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, n4, n5, color, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, n4, n5, n6, n7, n8, n9, imageObserver);
    }

    @Override
    public boolean drawImage(Image image, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, Color color, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, n2, n3, n4, n5, n6, n7, n8, n9, color, imageObserver);
    }

    @Override
    public void dispose() {
        this.delegate.dispose();
    }

    @Override
    public void finalize() {
        this.delegate.finalize();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public Rectangle getClipRect() {
        return this.delegate.getClipRect();
    }

    @Override
    public boolean hitClip(int n2, int n3, int n4, int n5) {
        return this.delegate.hitClip(n2, n3, n4, n5);
    }

    @Override
    public Rectangle getClipBounds(Rectangle rectangle) {
        return this.delegate.getClipBounds(rectangle);
    }

    @Override
    public void draw3DRect(int n2, int n3, int n4, int n5, boolean bl) {
        this.delegate.draw3DRect(n2, n3, n4, n5, bl);
    }

    @Override
    public void fill3DRect(int n2, int n3, int n4, int n5, boolean bl) {
        this.delegate.fill3DRect(n2, n3, n4, n5, bl);
    }

    @Override
    public void draw(Shape shape) {
        this.delegate.draw(shape);
    }

    @Override
    public boolean drawImage(Image image, AffineTransform affineTransform, ImageObserver imageObserver) {
        return this.delegate.drawImage(image, affineTransform, imageObserver);
    }

    @Override
    public void drawImage(BufferedImage bufferedImage, BufferedImageOp bufferedImageOp, int n2, int n3) {
        this.delegate.drawImage(bufferedImage, bufferedImageOp, n2, n3);
    }

    @Override
    public void drawRenderedImage(RenderedImage renderedImage, AffineTransform affineTransform) {
        this.delegate.drawRenderedImage(renderedImage, affineTransform);
    }

    @Override
    public void drawRenderableImage(RenderableImage renderableImage, AffineTransform affineTransform) {
        this.delegate.drawRenderableImage(renderableImage, affineTransform);
    }

    @Override
    public void drawString(String string, int n2, int n3) {
        this.delegate.drawString(string, n2, n3);
    }

    @Override
    public void drawString(String string, float f2, float f3) {
        this.delegate.drawString(string, f2, f3);
    }

    @Override
    public void drawString(AttributedCharacterIterator attributedCharacterIterator, int n2, int n3) {
        this.delegate.drawString(attributedCharacterIterator, n2, n3);
    }

    @Override
    public void drawString(AttributedCharacterIterator attributedCharacterIterator, float f2, float f3) {
        this.delegate.drawString(attributedCharacterIterator, f2, f3);
    }

    @Override
    public void drawGlyphVector(GlyphVector glyphVector, float f2, float f3) {
        this.delegate.drawGlyphVector(glyphVector, f2, f3);
    }

    @Override
    public void fill(Shape shape) {
        this.delegate.fill(shape);
    }

    @Override
    public boolean hit(Rectangle rectangle, Shape shape, boolean bl) {
        return this.delegate.hit(rectangle, shape, bl);
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        return this.delegate.getDeviceConfiguration();
    }

    @Override
    public void setComposite(Composite composite) {
        this.delegate.setComposite(composite);
    }

    @Override
    public void setPaint(Paint paint) {
        this.delegate.setPaint(paint);
    }

    @Override
    public void setStroke(Stroke stroke) {
        this.delegate.setStroke(stroke);
    }

    @Override
    public void setRenderingHint(RenderingHints.Key key, Object object) {
        this.delegate.setRenderingHint(key, object);
    }

    @Override
    public Object getRenderingHint(RenderingHints.Key key) {
        return this.delegate.getRenderingHint(key);
    }

    @Override
    public void setRenderingHints(Map<?, ?> map) {
        this.delegate.setRenderingHints(map);
    }

    @Override
    public void addRenderingHints(Map<?, ?> map) {
        this.delegate.addRenderingHints(map);
    }

    @Override
    public RenderingHints getRenderingHints() {
        return this.delegate.getRenderingHints();
    }

    @Override
    public void translate(int n2, int n3) {
        this.delegate.translate(n2, n3);
    }

    @Override
    public void translate(double d2, double d3) {
        this.delegate.translate(d2, d3);
    }

    @Override
    public void rotate(double d2) {
        this.delegate.rotate(d2);
    }

    @Override
    public void rotate(double d2, double d3, double d4) {
        this.delegate.rotate(d2, d3, d4);
    }

    @Override
    public void scale(double d2, double d3) {
        this.delegate.scale(d2, d3);
    }

    @Override
    public void shear(double d2, double d3) {
        this.delegate.shear(d2, d3);
    }

    @Override
    public void transform(AffineTransform affineTransform) {
        this.delegate.transform(affineTransform);
    }

    @Override
    public void setTransform(AffineTransform affineTransform) {
        this.delegate.setTransform(affineTransform);
    }

    @Override
    public AffineTransform getTransform() {
        return this.delegate.getTransform();
    }

    @Override
    public Paint getPaint() {
        return this.delegate.getPaint();
    }

    @Override
    public Composite getComposite() {
        return this.delegate.getComposite();
    }

    @Override
    public void setBackground(Color color) {
        this.delegate.setBackground(color);
    }

    @Override
    public Color getBackground() {
        return this.delegate.getBackground();
    }

    @Override
    public Stroke getStroke() {
        return this.delegate.getStroke();
    }

    @Override
    public void clip(Shape shape) {
        this.delegate.clip(shape);
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        return this.delegate.getFontRenderContext();
    }
}

