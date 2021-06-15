/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Supplier;
import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;

public abstract class FlatWindowResizer
implements PropertyChangeListener,
ComponentListener {
    protected static final Integer WINDOW_RESIZER_LAYER = JLayeredPane.DRAG_LAYER + ((int)1419033911L ^ 0x5494BD36);
    protected final JComponent resizeComp;
    protected final int borderDragThickness = FlatUIUtils.getUIInt("RootPane.borderDragThickness", (int)2111953842L ^ 0x7DE1DBB7);
    protected final int cornerDragWidth = FlatUIUtils.getUIInt("RootPane.cornerDragWidth", ((int)250381245L ^ 0xEEC83BC) << 4);
    protected final boolean honorFrameMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorFrameMinimumSizeOnResize");
    protected final boolean honorDialogMinimumSizeOnResize = UIManager.getBoolean("RootPane.honorDialogMinimumSizeOnResize");
    protected final DragBorderComponent topDragComp;
    protected final DragBorderComponent bottomDragComp;
    protected final DragBorderComponent leftDragComp;
    protected final DragBorderComponent rightDragComp;

    protected FlatWindowResizer(JComponent jComponent) {
        this.resizeComp = jComponent;
        this.topDragComp = this.createDragBorderComponent(((int)1599554381L ^ 0x5F57434E) << 1, ((int)2115112901L ^ 0x7E120FC4) << 3, (int)341508398L ^ 0x145B0129);
        this.bottomDragComp = this.createDragBorderComponent(((int)1974724934L ^ 0x75B3E947) << 2, (int)181858768L ^ 0xAD6F1D9, (int)((long)1706185076 ^ (long)1706185073));
        this.leftDragComp = this.createDragBorderComponent((int)((long)-1695439763 ^ (long)-1695439762) << 1, (int)((long)-1256407596 ^ (long)-1256407599) << 1, (int)((long)913973106 ^ (long)913973107) << 2);
        this.rightDragComp = this.createDragBorderComponent((int)((long)-885868106 ^ (long)-885868111), (int)((long)1790436999 ^ (long)1790437004), (int)-1553014845L ^ 0xA36EDFC6);
        JComponent jComponent2 = jComponent instanceof JRootPane ? ((JRootPane)jComponent).getLayeredPane() : jComponent;
        Integer n2 = jComponent2 instanceof JLayeredPane ? WINDOW_RESIZER_LAYER : null;
        jComponent2.add(this.topDragComp, n2, (int)((long)-1624004271 ^ (long)-1624004271));
        jComponent2.add(this.bottomDragComp, n2, (int)-1561021895L ^ 0xA2F4B238);
        jComponent2.add(this.leftDragComp, n2, (int)((long)-1750625418 ^ (long)-1750625417) << 1);
        jComponent2.add(this.rightDragComp, n2, (int)((long)579986959 ^ (long)579986956));
        jComponent.addComponentListener(this);
        jComponent.addPropertyChangeListener("ancestor", this);
        if (jComponent.isDisplayable()) {
            this.addNotify();
        }
    }

    protected DragBorderComponent createDragBorderComponent(int n2, int n3, int n4) {
        return new DragBorderComponent(n2, n3, n4);
    }

    public void uninstall() {
        this.removeNotify();
        this.resizeComp.removeComponentListener(this);
        this.resizeComp.removePropertyChangeListener("ancestor", this);
        Container container = this.topDragComp.getParent();
        container.remove(this.topDragComp);
        container.remove(this.bottomDragComp);
        container.remove(this.leftDragComp);
        container.remove(this.rightDragComp);
    }

    public void doLayout() {
        if (!this.topDragComp.isVisible()) {
            return;
        }
        int n2 = (int)-1253999770L ^ 0xB5417B66;
        int n3 = (int)840188536L ^ 0x32144278;
        int n4 = this.resizeComp.getWidth();
        int n5 = this.resizeComp.getHeight();
        if (n4 == 0 || n5 == 0) {
            return;
        }
        Insets insets = this.getResizeInsets();
        int n6 = UIScale.scale(this.borderDragThickness);
        int n7 = Math.max(insets.top, n6);
        int n8 = Math.max(insets.bottom, n6);
        int n9 = Math.max(insets.left, n6);
        int n10 = Math.max(insets.right, n6);
        int n11 = n3 + n7;
        int n12 = n5 - n7 - n8;
        this.topDragComp.setBounds(n2, n3, n4, n7);
        this.bottomDragComp.setBounds(n2, n3 + n5 - n8, n4, n8);
        this.leftDragComp.setBounds(n2, n11, n9, n12);
        this.rightDragComp.setBounds(n2 + n4 - n10, n11, n10, n12);
        int n13 = UIScale.scale(this.cornerDragWidth - this.borderDragThickness);
        this.topDragComp.setCornerDragWidths(n9 + n13, n10 + n13);
        this.bottomDragComp.setCornerDragWidths(n9 + n13, n10 + n13);
        this.leftDragComp.setCornerDragWidths(n13, n13);
        this.rightDragComp.setCornerDragWidths(n13, n13);
    }

    protected Insets getResizeInsets() {
        return new Insets((int)((long)2143398519 ^ (long)2143398519), (int)1419001922L ^ 0x54944042, (int)((long)-968127786 ^ (long)-968127786), (int)((long)-1470191913 ^ (long)-1470191913));
    }

    protected void addNotify() {
        this.updateVisibility();
    }

    protected void removeNotify() {
        this.updateVisibility();
    }

    protected void updateVisibility() {
        boolean bl = this.isWindowResizable();
        if (bl == this.topDragComp.isVisible()) {
            return;
        }
        this.topDragComp.setVisible(bl);
        this.bottomDragComp.setVisible(bl);
        this.leftDragComp.setVisible(bl);
        this.rightDragComp.setEnabled(bl);
        if (bl) {
            this.rightDragComp.setVisible(((int)1300683250L ^ 0x4D86D9F3) != 0);
            this.doLayout();
        } else {
            this.rightDragComp.setBounds((int)-1630780838L ^ 0x9ECC425A, (int)155743863L ^ 0x9487677, (int)((long)-680339777 ^ (long)-680339778), (int)1757358782L ^ 0x68BF2ABF);
        }
    }

    boolean isDialog() {
        return ((int)537478037L ^ 0x20094395) != 0;
    }

    protected abstract boolean isWindowResizable();

    protected abstract Rectangle getWindowBounds();

    protected abstract void setWindowBounds(Rectangle var1);

    protected abstract boolean honorMinimumSizeOnResize();

    protected abstract Dimension getWindowMinimumSize();

    protected void beginResizing(int n2) {
    }

    protected void endResizing() {
    }

    /*
     * Exception decompiling
     */
    @Override
    public void propertyChange(PropertyChangeEvent var1_1) {
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
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
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
    }

    @Override
    public void componentResized(ComponentEvent componentEvent) {
        this.doLayout();
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {
    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {
    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {
    }

    protected class DragBorderComponent
    extends JComponent
    implements MouseListener,
    MouseMotionListener {
        private final int leadingResizeDir;
        private final int centerResizeDir;
        private final int trailingResizeDir;
        private int resizeDir = (int)-2010779495L ^ 0x77DA0F66;
        private int leadingCornerDragWidth;
        private int trailingCornerDragWidth;
        private int dragLeftOffset;
        private int dragRightOffset;
        private int dragTopOffset;
        private int dragBottomOffset;

        protected DragBorderComponent(int n2, int n3, int n4) {
            this.leadingResizeDir = n2;
            this.centerResizeDir = n3;
            this.trailingResizeDir = n4;
            this.setResizeDir(n3);
            this.setVisible((boolean)((long)1969217230 ^ (long)1969217230));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        void setCornerDragWidths(int n2, int n3) {
            this.leadingCornerDragWidth = n2;
            this.trailingCornerDragWidth = n3;
        }

        protected void setResizeDir(int n2) {
            if (this.resizeDir == n2) {
                return;
            }
            this.resizeDir = n2;
            this.setCursor(Cursor.getPredefinedCursor(n2));
        }

        @Override
        public Dimension getPreferredSize() {
            int n2 = UIScale.scale(FlatWindowResizer.this.borderDragThickness);
            return new Dimension(n2, n2);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintChildren(graphics);
            FlatWindowResizer.this.updateVisibility();
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (!FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            int n2 = mouseEvent.getXOnScreen();
            int n3 = mouseEvent.getYOnScreen();
            Rectangle rectangle = FlatWindowResizer.this.getWindowBounds();
            this.dragLeftOffset = n2 - rectangle.x;
            this.dragTopOffset = n3 - rectangle.y;
            this.dragRightOffset = rectangle.x + rectangle.width - n2;
            this.dragBottomOffset = rectangle.y + rectangle.height - n3;
            int n4 = (int)((long)-530700084 ^ (long)-530700084);
            switch (this.resizeDir) {
                case 8: {
                    n4 = (int)((long)2141965365 ^ (long)2141965364);
                    break;
                }
                case 9: {
                    n4 = (int)466566007L ^ 0x1BCF3B72;
                    break;
                }
                case 10: {
                    n4 = (int)((long)2079222323 ^ (long)2079222324);
                    break;
                }
                case 11: {
                    n4 = (int)((long)534375791 ^ (long)534375788);
                    break;
                }
                case 6: {
                    n4 = ((int)-2095809753L ^ 0x83147B26) << 3;
                    break;
                }
                case 7: {
                    n4 = (int)((long)-1756499886 ^ (long)-1756499885) << 1;
                    break;
                }
                case 4: {
                    n4 = (int)((long)94014373 ^ (long)94014374) << 1;
                    break;
                }
                case 5: {
                    n4 = ((int)549961442L ^ 0x20C7BEE3) << 2;
                }
            }
            FlatWindowResizer.this.beginResizing(n4);
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if (!FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            this.dragTopOffset = this.dragBottomOffset = (int)-413467697L ^ 0xE75AFBCF;
            this.dragRightOffset = this.dragBottomOffset;
            this.dragLeftOffset = this.dragBottomOffset;
            FlatWindowResizer.this.endResizing();
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            int n2;
            int n3 = this.centerResizeDir == (int)((long)-1998994737 ^ (long)-1998994738) << 3 || this.centerResizeDir == ((int)-20413642L ^ 0xFEC8833F) ? (int)1027760110L ^ 0x3D425FEF : (int)((long)323766135 ^ (long)323766135);
            int n4 = n3 != 0 ? mouseEvent.getX() : mouseEvent.getY();
            int n5 = n2 = n3 != 0 ? this.getWidth() : this.getHeight();
            this.setResizeDir(n4 <= this.leadingCornerDragWidth ? this.leadingResizeDir : (n4 >= n2 - this.trailingCornerDragWidth ? this.trailingResizeDir : this.centerResizeDir));
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            Dimension dimension;
            if (!FlatWindowResizer.this.isWindowResizable()) {
                return;
            }
            int n2 = mouseEvent.getXOnScreen();
            int n3 = mouseEvent.getYOnScreen();
            Rectangle rectangle = FlatWindowResizer.this.getWindowBounds();
            Rectangle rectangle2 = new Rectangle(rectangle);
            if (this.resizeDir == (int)((long)173621743 ^ (long)173621742) << 3 || this.resizeDir == ((int)857922619L ^ 0x3322DC38) << 1 || this.resizeDir == ((int)-565938136L ^ 0xDE44782F)) {
                rectangle2.y = n3 - this.dragTopOffset;
                rectangle2.height += rectangle.y - rectangle2.y;
            }
            if (this.resizeDir == (int)((long)-496413436 ^ (long)-496413427) || this.resizeDir == ((int)799767594L ^ 0x2FAB7C2B) << 2 || this.resizeDir == (int)((long)1800521622 ^ (long)1800521619)) {
                rectangle2.height = n3 + this.dragBottomOffset - rectangle2.y;
            }
            if (this.resizeDir == ((int)-168523965L ^ 0xF5F48746) << 1 || this.resizeDir == ((int)-584409420L ^ 0xDD2A9EB7) << 1 || this.resizeDir == ((int)1068286993L ^ 0x3FACC410) << 2) {
                rectangle2.x = n2 - this.dragLeftOffset;
                rectangle2.width += rectangle.x - rectangle2.x;
            }
            if (this.resizeDir == (int)((long)1532775165 ^ (long)1532775158) || this.resizeDir == (int)((long)-85539894 ^ (long)-85539891) || this.resizeDir == ((int)-478452086L ^ 0xE37B668F)) {
                rectangle2.width = n2 + this.dragRightOffset - rectangle2.x;
            }
            Dimension dimension2 = dimension = FlatWindowResizer.this.honorMinimumSizeOnResize() ? FlatWindowResizer.this.getWindowMinimumSize() : null;
            if (dimension == null) {
                dimension = UIScale.scale(new Dimension((int)((long)873443774 ^ (long)873443829) << 1, ((int)871957129L ^ 0x33F90290) << 1));
            }
            if (rectangle2.width < dimension.width) {
                if (rectangle2.x != rectangle.x) {
                    rectangle2.x -= dimension.width - rectangle2.width;
                }
                rectangle2.width = dimension.width;
            }
            if (rectangle2.height < dimension.height) {
                if (rectangle2.y != rectangle.y) {
                    rectangle2.y -= dimension.height - rectangle2.height;
                }
                rectangle2.height = dimension.height;
            }
            if (!rectangle2.equals(rectangle)) {
                FlatWindowResizer.this.setWindowBounds(rectangle2);
            }
        }
    }

    public static class InternalFrameResizer
    extends FlatWindowResizer {
        protected final Supplier<DesktopManager> desktopManager;

        public InternalFrameResizer(JInternalFrame jInternalFrame, Supplier<DesktopManager> supplier) {
            super(jInternalFrame);
            this.desktopManager = supplier;
            jInternalFrame.addPropertyChangeListener("resizable", this);
        }

        @Override
        public void uninstall() {
            this.getFrame().removePropertyChangeListener("resizable", this);
            super.uninstall();
        }

        private JInternalFrame getFrame() {
            return (JInternalFrame)this.resizeComp;
        }

        @Override
        protected Insets getResizeInsets() {
            return this.getFrame().getInsets();
        }

        @Override
        protected boolean isWindowResizable() {
            return this.getFrame().isResizable();
        }

        @Override
        protected Rectangle getWindowBounds() {
            return this.getFrame().getBounds();
        }

        @Override
        protected void setWindowBounds(Rectangle rectangle) {
            this.desktopManager.get().resizeFrame(this.getFrame(), rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }

        @Override
        protected boolean honorMinimumSizeOnResize() {
            return ((int)-432170112L ^ 0xE63D9B81) != 0;
        }

        @Override
        protected Dimension getWindowMinimumSize() {
            return this.getFrame().getMinimumSize();
        }

        @Override
        protected void beginResizing(int n2) {
            this.desktopManager.get().beginResizingFrame(this.getFrame(), n2);
        }

        @Override
        protected void endResizing() {
            this.desktopManager.get().endResizingFrame(this.getFrame());
        }
    }

    public static class WindowResizer
    extends FlatWindowResizer
    implements WindowStateListener {
        protected Window window;

        public WindowResizer(JRootPane jRootPane) {
            super(jRootPane);
        }

        @Override
        protected void addNotify() {
            Container container = this.resizeComp.getParent();
            Window window = this.window = container instanceof Window ? (Window)container : null;
            if (this.window instanceof Frame) {
                this.window.addPropertyChangeListener("resizable", this);
                this.window.addWindowStateListener(this);
            }
            super.addNotify();
        }

        @Override
        protected void removeNotify() {
            if (this.window instanceof Frame) {
                this.window.removePropertyChangeListener("resizable", this);
                this.window.removeWindowStateListener(this);
            }
            this.window = null;
            super.removeNotify();
        }

        @Override
        protected boolean isWindowResizable() {
            if (FlatUIUtils.isFullScreen(this.resizeComp)) {
                return ((int)-1519113787L ^ 0xA57429C5) != 0;
            }
            if (this.window instanceof Frame) {
                return (((Frame)this.window).isResizable() && (((Frame)this.window).getExtendedState() & (int)((long)-1803540438 ^ (long)-1803540439) << 1) == 0 ? (int)1183750333L ^ 0x468E98BC : (int)206648706L ^ 0xC513582) != 0;
            }
            if (this.window instanceof Dialog) {
                return ((Dialog)this.window).isResizable();
            }
            return (int)((long)337941376 ^ (long)337941376) != 0;
        }

        @Override
        protected Rectangle getWindowBounds() {
            return this.window.getBounds();
        }

        @Override
        protected void setWindowBounds(Rectangle rectangle) {
            this.window.setBounds(rectangle);
            this.doLayout();
            if (Toolkit.getDefaultToolkit().isDynamicLayoutActive()) {
                this.window.validate();
                this.resizeComp.repaint();
            }
        }

        @Override
        protected boolean honorMinimumSizeOnResize() {
            return (this.honorFrameMinimumSizeOnResize && this.window instanceof Frame || this.honorDialogMinimumSizeOnResize && this.window instanceof Dialog ? (int)971292566L ^ 0x39E4BF97 : (int)((long)1162489022 ^ (long)1162489022)) != 0;
        }

        @Override
        protected Dimension getWindowMinimumSize() {
            return this.window.getMinimumSize();
        }

        @Override
        boolean isDialog() {
            return this.window instanceof Dialog;
        }

        @Override
        public void windowStateChanged(WindowEvent windowEvent) {
            this.updateVisibility();
        }
    }
}

