/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;

public interface AnimatedIcon
extends Icon {
    @Override
    default public void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        AnimationSupport.paintIcon(this, component, graphics, n2, n3);
    }

    public void paintIconAnimated(Component var1, Graphics var2, int var3, int var4, float var5);

    public float getValue(Component var1);

    default public boolean isAnimationEnabled() {
        return (int)((long)385923935 ^ (long)385923934) != 0;
    }

    default public int getAnimationDuration() {
        return ((int)1746651450L ^ 0x681BC971) << 1;
    }

    default public int getAnimationResolution() {
        return ((int)1775751627L ^ 0x69D7D1CE) << 1;
    }

    default public Animator.Interpolator getAnimationInterpolator() {
        return CubicBezierEasing.STANDARD_EASING;
    }

    default public Object getClientPropertyKey() {
        return this.getClass();
    }

    public static class AnimationSupport {
        private float startValue;
        private float targetValue;
        private float animatedValue;
        private float fraction;
        private Animator animator;
        private int x;
        private int y;

        public static void paintIcon(AnimatedIcon animatedIcon, Component component, Graphics graphics, int n2, int n3) {
            if (!AnimationSupport.isAnimationEnabled(animatedIcon, component)) {
                AnimationSupport.paintIconImpl(animatedIcon, component, graphics, n2, n3, null);
                return;
            }
            JComponent jComponent = (JComponent)component;
            Object object = animatedIcon.getClientPropertyKey();
            AnimationSupport animationSupport = (AnimationSupport)jComponent.getClientProperty(object);
            if (animationSupport == null) {
                animationSupport = new AnimationSupport();
                animationSupport.targetValue = animationSupport.animatedValue = animatedIcon.getValue(component);
                animationSupport.startValue = animationSupport.animatedValue;
                animationSupport.x = n2;
                animationSupport.y = n3;
                jComponent.putClientProperty(object, animationSupport);
            } else {
                float f3 = animatedIcon.getValue(component);
                if (f3 != animationSupport.targetValue) {
                    if (animationSupport.animator == null) {
                        AnimationSupport animationSupport2 = animationSupport;
                        animationSupport.animator = new Animator(animatedIcon.getAnimationDuration(), f2 -> {
                            if (!component.isDisplayable()) {
                                animationSupport.animator.stop();
                                return;
                            }
                            animationSupport.animatedValue = animationSupport.startValue + (animationSupport.targetValue - animationSupport.startValue) * f2;
                            animationSupport.fraction = f2;
                            component.repaint(animationSupport.x, animationSupport.y, animatedIcon.getIconWidth(), animatedIcon.getIconHeight());
                        }, () -> {
                            animationSupport.startValue = animationSupport.animatedValue = animationSupport.targetValue;
                            animationSupport.animator = null;
                        });
                    }
                    if (animationSupport.animator.isRunning()) {
                        animationSupport.animator.cancel();
                        int n4 = (int)((float)animatedIcon.getAnimationDuration() * animationSupport.fraction);
                        if (n4 > 0) {
                            animationSupport.animator.setDuration(n4);
                        }
                        animationSupport.startValue = animationSupport.animatedValue;
                    } else {
                        animationSupport.animator.setDuration(animatedIcon.getAnimationDuration());
                        animationSupport.animator.setResolution(animatedIcon.getAnimationResolution());
                        animationSupport.animator.setInterpolator(animatedIcon.getAnimationInterpolator());
                        animationSupport.animatedValue = animationSupport.startValue;
                    }
                    animationSupport.targetValue = f3;
                    animationSupport.animator.start();
                }
                animationSupport.x = n2;
                animationSupport.y = n3;
            }
            AnimationSupport.paintIconImpl(animatedIcon, component, graphics, n2, n3, animationSupport);
        }

        private static void paintIconImpl(AnimatedIcon animatedIcon, Component component, Graphics graphics, int n2, int n3, AnimationSupport animationSupport) {
            float f2 = animationSupport != null ? animationSupport.animatedValue : animatedIcon.getValue(component);
            animatedIcon.paintIconAnimated(component, graphics, n2, n3, f2);
        }

        private static boolean isAnimationEnabled(AnimatedIcon animatedIcon, Component component) {
            return (Animator.useAnimation() && animatedIcon.isAnimationEnabled() && component instanceof JComponent ? (int)((long)-723097516 ^ (long)-723097515) : (int)677510712L ^ 0x2861FE38) != 0;
        }

        public static void saveIconLocation(AnimatedIcon animatedIcon, Component component, int n2, int n3) {
            if (!AnimationSupport.isAnimationEnabled(animatedIcon, component)) {
                return;
            }
            AnimationSupport animationSupport = (AnimationSupport)((JComponent)component).getClientProperty(animatedIcon.getClientPropertyKey());
            if (animationSupport != null) {
                animationSupport.x = n2;
                animationSupport.y = n3;
            }
        }
    }
}

