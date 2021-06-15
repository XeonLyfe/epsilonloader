/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.Scrollable;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public class FlatScrollPaneUI
extends BasicScrollPaneUI {
    private Handler handler;

    public static ComponentUI createUI(JComponent jComponent) {
        return new FlatScrollPaneUI();
    }

    @Override
    public void installUI(JComponent jComponent) {
        super.installUI(jComponent);
        int n2 = UIManager.getInt("Component.focusWidth");
        LookAndFeel.installProperty(jComponent, "opaque", (n2 == 0 ? (int)((long)-1930190732 ^ (long)-1930190731) : (int)((long)-68866209 ^ (long)-68866209)) != 0);
        MigLayoutVisualPadding.install(this.scrollpane);
    }

    @Override
    public void uninstallUI(JComponent jComponent) {
        MigLayoutVisualPadding.uninstall(this.scrollpane);
        super.uninstallUI(jComponent);
    }

    @Override
    protected void installListeners(JScrollPane jScrollPane) {
        super.installListeners(jScrollPane);
        this.addViewportListeners(this.scrollpane.getViewport());
    }

    @Override
    protected void uninstallListeners(JComponent jComponent) {
        super.uninstallListeners(jComponent);
        this.removeViewportListeners(this.scrollpane.getViewport());
        this.handler = null;
    }

    @Override
    protected MouseWheelListener createMouseWheelListener() {
        MouseWheelListener mouseWheelListener = super.createMouseWheelListener();
        return mouseWheelEvent -> {
            if (this.isSmoothScrollingEnabled() && this.scrollpane.isWheelScrollingEnabled() && mouseWheelEvent.getScrollType() == 0 && mouseWheelEvent.getPreciseWheelRotation() != 0.0 && mouseWheelEvent.getPreciseWheelRotation() != (double)mouseWheelEvent.getWheelRotation()) {
                this.mouseWheelMovedSmooth(mouseWheelEvent);
            } else {
                mouseWheelListener.mouseWheelMoved(mouseWheelEvent);
            }
        };
    }

    protected boolean isSmoothScrollingEnabled() {
        Object object = this.scrollpane.getClientProperty("JScrollPane.smoothScrolling");
        if (object instanceof Boolean) {
            return (Boolean)object;
        }
        return UIManager.getBoolean("ScrollPane.smoothScrolling");
    }

    private void mouseWheelMovedSmooth(MouseWheelEvent mouseWheelEvent) {
        int n2;
        int n3;
        JViewport jViewport = this.scrollpane.getViewport();
        if (jViewport == null) {
            return;
        }
        JScrollBar jScrollBar = this.scrollpane.getVerticalScrollBar();
        if (!(jScrollBar != null && jScrollBar.isVisible() && !mouseWheelEvent.isShiftDown() || (jScrollBar = this.scrollpane.getHorizontalScrollBar()) != null && jScrollBar.isVisible())) {
            return;
        }
        mouseWheelEvent.consume();
        double d2 = mouseWheelEvent.getPreciseWheelRotation();
        int n4 = jScrollBar.getOrientation();
        Component component = jViewport.getView();
        if (component instanceof Scrollable) {
            Scrollable scrollable = (Scrollable)((Object)component);
            Rectangle rectangle = new Rectangle(jViewport.getViewSize());
            n3 = scrollable.getScrollableUnitIncrement(rectangle, n4, (int)-1830192837L ^ 0x92E9793A);
            if (n3 > 0) {
                if (n4 == (int)((long)-1440100171 ^ (long)-1440100172)) {
                    rectangle.y += n3;
                    rectangle.height -= n3;
                } else {
                    rectangle.x += n3;
                    rectangle.width -= n3;
                }
                int n5 = scrollable.getScrollableUnitIncrement(rectangle, n4, (int)((long)209243223 ^ (long)209243222));
                if (n5 > 0) {
                    n3 = Math.min(n3, n5);
                }
            }
        } else {
            int n6 = d2 < 0.0 ? (int)((long)-1292225728 ^ (long)1292225727) : (int)((long)-1900074581 ^ (long)-1900074582);
            n3 = jScrollBar.getUnitIncrement(n6);
        }
        int n7 = n4 == (int)((long)-934381186 ^ (long)-934381185) ? jViewport.getHeight() : jViewport.getWidth();
        int n8 = Math.min(n3 * mouseWheelEvent.getScrollAmount(), n7);
        double d3 = d2 * (double)n8;
        int n9 = (int)Math.round(d3);
        if (n9 == 0) {
            if (d2 > 0.0) {
                n9 = (int)511018358L ^ 0x1E758577;
            } else if (d2 < 0.0) {
                n9 = (int)1419119005L ^ 0xAB69F662;
            }
        }
        int n10 = jScrollBar.getValue();
        int n11 = jScrollBar.getMinimum();
        int n12 = Math.max(n11, Math.min(n10 + n9, n2 = jScrollBar.getMaximum() - jScrollBar.getModel().getExtent()));
        if (n12 != n10) {
            jScrollBar.setValue(n12);
        }
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

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    @Override
    protected void updateViewport(PropertyChangeEvent propertyChangeEvent) {
        super.updateViewport(propertyChangeEvent);
        JViewport jViewport = (JViewport)propertyChangeEvent.getOldValue();
        JViewport jViewport2 = (JViewport)propertyChangeEvent.getNewValue();
        this.removeViewportListeners(jViewport);
        this.addViewportListeners(jViewport2);
    }

    private void addViewportListeners(JViewport jViewport) {
        if (jViewport == null) {
            return;
        }
        jViewport.addContainerListener(this.getHandler());
        Component component = jViewport.getView();
        if (component != null) {
            component.addFocusListener(this.getHandler());
        }
    }

    private void removeViewportListeners(JViewport jViewport) {
        if (jViewport == null) {
            return;
        }
        jViewport.removeContainerListener(this.getHandler());
        Component component = jViewport.getView();
        if (component != null) {
            component.removeFocusListener(this.getHandler());
        }
    }

    @Override
    public void update(Graphics graphics, JComponent jComponent) {
        if (jComponent.isOpaque()) {
            FlatUIUtils.paintParentBackground(graphics, jComponent);
            Insets insets = jComponent.getInsets();
            graphics.setColor(jComponent.getBackground());
            graphics.fillRect(insets.left, insets.top, jComponent.getWidth() - insets.left - insets.right, jComponent.getHeight() - insets.top - insets.bottom);
        }
        this.paint(graphics, jComponent);
    }

    private class Handler
    implements ContainerListener,
    FocusListener {
        private Handler() {
        }

        @Override
        public void componentAdded(ContainerEvent containerEvent) {
            containerEvent.getChild().addFocusListener(this);
        }

        @Override
        public void componentRemoved(ContainerEvent containerEvent) {
            containerEvent.getChild().removeFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent focusEvent) {
            FlatScrollPaneUI.this.scrollpane.repaint();
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            FlatScrollPaneUI.this.scrollpane.repaint();
        }
    }
}

