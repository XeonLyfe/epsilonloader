/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class FlatScrollBarUI
extends BasicScrollBarUI {
    protected Insets trackInsets;
    protected Insets thumbInsets;
    protected int trackArc;
    protected int thumbArc;
    protected Color hoverTrackColor;
    protected Color hoverThumbColor;
    protected boolean hoverThumbWithTrack;
    protected Color pressedTrackColor;
    protected Color pressedThumbColor;
    protected boolean pressedThumbWithTrack;
    protected boolean showButtons;
    protected String arrowType;
    protected Color buttonArrowColor;
    protected Color buttonDisabledArrowColor;
    protected Color hoverButtonBackground;
    protected Color pressedButtonBackground;
    private MouseAdapter hoverListener;
    protected boolean hoverTrack;
    protected boolean hoverThumb;
    private static boolean isPressed;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatScrollBarUI();
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.hoverListener = new ScrollBarHoverListener();
        this.scrollbar.addMouseListener(this.hoverListener);
        this.scrollbar.addMouseMotionListener(this.hoverListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.scrollbar.removeMouseListener(this.hoverListener);
        this.scrollbar.removeMouseMotionListener(this.hoverListener);
        this.hoverListener = null;
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.trackInsets = UIManager.getInsets("ScrollBar.trackInsets");
        this.thumbInsets = UIManager.getInsets("ScrollBar.thumbInsets");
        this.trackArc = UIManager.getInt("ScrollBar.trackArc");
        this.thumbArc = UIManager.getInt("ScrollBar.thumbArc");
        this.hoverTrackColor = UIManager.getColor("ScrollBar.hoverTrackColor");
        this.hoverThumbColor = UIManager.getColor("ScrollBar.hoverThumbColor");
        this.hoverThumbWithTrack = UIManager.getBoolean("ScrollBar.hoverThumbWithTrack");
        this.pressedTrackColor = UIManager.getColor("ScrollBar.pressedTrackColor");
        this.pressedThumbColor = UIManager.getColor("ScrollBar.pressedThumbColor");
        this.pressedThumbWithTrack = UIManager.getBoolean("ScrollBar.pressedThumbWithTrack");
        this.showButtons = UIManager.getBoolean("ScrollBar.showButtons");
        this.arrowType = UIManager.getString("Component.arrowType");
        this.buttonArrowColor = UIManager.getColor("ScrollBar.buttonArrowColor");
        this.buttonDisabledArrowColor = UIManager.getColor("ScrollBar.buttonDisabledArrowColor");
        this.hoverButtonBackground = UIManager.getColor("ScrollBar.hoverButtonBackground");
        this.pressedButtonBackground = UIManager.getColor("ScrollBar.pressedButtonBackground");
        if (this.trackInsets == null) {
            this.trackInsets = new Insets((int)((long)-625844421 ^ (long)-625844421), (int)-1899819705L ^ 0x8EC30D47, (int)1573305065L ^ 0x5DC6BAE9, (int)1247894889L ^ 0x4A615D69);
        }
        if (this.thumbInsets == null) {
            this.thumbInsets = new Insets((int)((long)-263054945 ^ (long)-263054945), (int)((long)1415037800 ^ (long)1415037800), (int)1381982378L ^ 0x525F60AA, (int)((long)-467608192 ^ (long)-467608192));
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.trackInsets = null;
        this.thumbInsets = null;
        this.hoverTrackColor = null;
        this.hoverThumbColor = null;
        this.pressedTrackColor = null;
        this.pressedThumbColor = null;
        this.buttonArrowColor = null;
        this.buttonDisabledArrowColor = null;
        this.hoverButtonBackground = null;
        this.pressedButtonBackground = null;
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        PropertyChangeListener propertyChangeListener = super.createPropertyChangeListener();
        return var2_2 -> {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$TooOptimisticMatchException
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.getInt(SwitchStringRewriter.java:392)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.access$300(SwitchStringRewriter.java:50)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter$SwitchStringMatchResultCollector.collectMatches(SwitchStringRewriter.java:343)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(ResetAfterTest.java:24)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.KleeneN.match(KleeneN.java:24)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.MatchSequence.match(MatchSequence.java:26)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.matchutil.ResetAfterTest.match(ResetAfterTest.java:23)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewriteComplex(SwitchStringRewriter.java:197)
             * org.benf.cfr.reader.bytecode.analysis.opgraph.op4rewriters.SwitchStringRewriter.rewrite(SwitchStringRewriter.java:70)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
             * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
             * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
             * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1030)
             * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
             * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
             * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
             * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
             * org.benf.cfr.reader.Main.main(Main.java:49)
             * async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:259)
             * async.DecompilerRunnable.call(DecompilerRunnable.java:220)
             * async.DecompilerRunnable.call(DecompilerRunnable.java:26)
             * java.util.concurrent.FutureTask.run(FutureTask.java:266)
             * java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
             * java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
             * java.lang.Thread.run(Thread.java:748)
             */
            throw new IllegalStateException(Decompilation failed);
        };
    }

    @Override
    public Dimension getPreferredSize(JComponent jComponent) {
        return UIScale.scale(super.getPreferredSize(jComponent));
    }

    @Override
    protected JButton createDecreaseButton(int n2) {
        return new FlatScrollBarButton(n2);
    }

    @Override
    protected JButton createIncreaseButton(int n2) {
        return new FlatScrollBarButton(n2);
    }

    protected boolean isShowButtons() {
        Object object = this.scrollbar.getClientProperty("JScrollBar.showButtons");
        if (object == null && this.scrollbar.getParent() instanceof JScrollPane) {
            object = ((JScrollPane)this.scrollbar.getParent()).getClientProperty("JScrollBar.showButtons");
        }
        return object != null ? Objects.equals(object, ((int)2043042944L ^ 0x79C65C81) != 0) : this.showButtons;
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        Object[] arrobject = FlatUIUtils.setRenderingHints(graphics);
        super.paint(graphics, jComponent);
        FlatUIUtils.resetRenderingHints(graphics, arrobject);
    }

    @Override
    protected void paintTrack(Graphics graphics, JComponent jComponent, Rectangle rectangle) {
        graphics.setColor(this.getTrackColor(jComponent, this.hoverTrack, (isPressed && this.hoverTrack && !this.hoverThumb ? (int)-7000594L ^ 0xFF952DEF : (int)-1744125621L ^ 0x980AC14B) != 0));
        this.paintTrackOrThumb(graphics, jComponent, rectangle, this.trackInsets, this.trackArc);
    }

    @Override
    protected void paintThumb(Graphics graphics, JComponent jComponent, Rectangle rectangle) {
        if (rectangle.isEmpty() || !this.scrollbar.isEnabled()) {
            return;
        }
        graphics.setColor(this.getThumbColor(jComponent, (this.hoverThumb || this.hoverThumbWithTrack && this.hoverTrack ? (int)-412428114L ^ 0xE76AD8AF : (int)-306527681L ^ 0xEDBAC23F) != 0, (isPressed && (this.hoverThumb || this.pressedThumbWithTrack && this.hoverTrack) ? (int)((long)-1645473637 ^ (long)-1645473638) : (int)((long)-691274375 ^ (long)-691274375)) != 0));
        this.paintTrackOrThumb(graphics, jComponent, rectangle, this.thumbInsets, this.thumbArc);
    }

    protected void paintTrackOrThumb(Graphics graphics, JComponent jComponent, Rectangle rectangle, Insets insets, int n2) {
        if (this.scrollbar.getOrientation() == 0) {
            insets = new Insets(insets.right, insets.top, insets.left, insets.bottom);
        }
        rectangle = FlatUIUtils.subtractInsets(rectangle, UIScale.scale(insets));
        if (n2 <= 0) {
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            n2 = Math.min(UIScale.scale(n2), Math.min(rectangle.width, rectangle.height));
            graphics.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, n2, n2);
        }
    }

    @Override
    protected void paintDecreaseHighlight(Graphics graphics) {
    }

    @Override
    protected void paintIncreaseHighlight(Graphics graphics) {
    }

    protected Color getTrackColor(JComponent jComponent, boolean bl, boolean bl2) {
        Color color = FlatUIUtils.deriveColor(this.trackColor, jComponent.getBackground());
        return bl2 && this.pressedTrackColor != null ? FlatUIUtils.deriveColor(this.pressedTrackColor, color) : (bl && this.hoverTrackColor != null ? FlatUIUtils.deriveColor(this.hoverTrackColor, color) : color);
    }

    protected Color getThumbColor(JComponent jComponent, boolean bl, boolean bl2) {
        Color color = FlatUIUtils.deriveColor(this.trackColor, jComponent.getBackground());
        Color color2 = FlatUIUtils.deriveColor(this.thumbColor, color);
        return bl2 && this.pressedThumbColor != null ? FlatUIUtils.deriveColor(this.pressedThumbColor, color2) : (bl && this.hoverThumbColor != null ? FlatUIUtils.deriveColor(this.hoverThumbColor, color2) : color2);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return UIScale.scale(FlatUIUtils.addInsets(super.getMinimumThumbSize(), this.thumbInsets));
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return UIScale.scale(FlatUIUtils.addInsets(super.getMaximumThumbSize(), this.thumbInsets));
    }

    protected class FlatScrollBarButton
    extends FlatArrowButton {
        protected FlatScrollBarButton(int n2) {
            this(n2, flatScrollBarUI.arrowType, flatScrollBarUI.buttonArrowColor, flatScrollBarUI.buttonDisabledArrowColor, null, flatScrollBarUI.hoverButtonBackground, null, flatScrollBarUI.pressedButtonBackground);
        }

        protected FlatScrollBarButton(int n2, String string, Color color, Color color2, Color color3, Color color4, Color color5, Color color6) {
            super(n2, string, color, color2, color3, color4, color5, color6);
            this.setArrowWidth(((int)-1545869118L ^ 0xA3DBE8C1) << 1);
            this.setFocusable((boolean)((long)939944841 ^ (long)939944841));
            this.setRequestFocusEnabled((boolean)((long)-1013948685 ^ (long)-1013948685));
        }

        @Override
        protected Color deriveBackground(Color color) {
            return FlatUIUtils.deriveColor(color, FlatScrollBarUI.this.scrollbar.getBackground());
        }

        @Override
        public Dimension getPreferredSize() {
            if (FlatScrollBarUI.this.isShowButtons()) {
                int n2 = UIScale.scale(FlatScrollBarUI.this.scrollBarWidth);
                return new Dimension(n2, n2);
            }
            return new Dimension();
        }

        @Override
        public Dimension getMinimumSize() {
            return FlatScrollBarUI.this.isShowButtons() ? super.getMinimumSize() : new Dimension();
        }

        @Override
        public Dimension getMaximumSize() {
            return FlatScrollBarUI.this.isShowButtons() ? super.getMaximumSize() : new Dimension();
        }
    }

    private class ScrollBarHoverListener
    extends MouseAdapter {
        private ScrollBarHoverListener() {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (!isPressed) {
                FlatScrollBarUI.this.hoverTrack = FlatScrollBarUI.this.hoverThumb = (int)((long)1831700337 ^ (long)1831700337);
                this.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if (!isPressed) {
                this.update(mouseEvent.getX(), mouseEvent.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            isPressed = ((int)-2116423729L ^ 0x81D9EFCE) != 0;
            this.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            isPressed = ((int)1225112567L ^ 0x4905BBF7) != 0;
            this.repaint();
            this.update(mouseEvent.getX(), mouseEvent.getY());
        }

        private void update(int n2, int n3) {
            boolean bl = FlatScrollBarUI.this.getTrackBounds().contains(n2, n3);
            boolean bl2 = FlatScrollBarUI.this.getThumbBounds().contains(n2, n3);
            if (bl != FlatScrollBarUI.this.hoverTrack || bl2 != FlatScrollBarUI.this.hoverThumb) {
                FlatScrollBarUI.this.hoverTrack = bl;
                FlatScrollBarUI.this.hoverThumb = bl2;
                this.repaint();
            }
        }

        private void repaint() {
            if (FlatScrollBarUI.this.scrollbar.isEnabled()) {
                FlatScrollBarUI.this.scrollbar.repaint();
            }
        }
    }
}

