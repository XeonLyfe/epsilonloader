/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.util.ArrayList;
import javax.swing.Timer;

public class Animator {
    private int duration;
    private int resolution = (int)((long)-364079760 ^ (long)-364079755) << 1;
    private Interpolator interpolator;
    private final ArrayList<TimingTarget> targets = new ArrayList();
    private final Runnable endRunnable;
    private boolean running;
    private boolean hasBegun;
    private boolean timeToStop;
    private long startTime;
    private Timer timer;

    public static boolean useAnimation() {
        return FlatSystemProperties.getBoolean("flatlaf.animation", ((int)-2100315435L ^ 0x82CFBAD4) != 0);
    }

    public Animator(int n2) {
        this(n2, null, null);
    }

    public Animator(int n2, TimingTarget timingTarget) {
        this(n2, timingTarget, null);
    }

    public Animator(int n2, TimingTarget timingTarget, Runnable runnable) {
        this.setDuration(n2);
        this.addTarget(timingTarget);
        this.endRunnable = runnable;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int n2) {
        this.throwExceptionIfRunning();
        if (n2 <= 0) {
            throw new IllegalArgumentException();
        }
        this.duration = n2;
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int n2) {
        this.throwExceptionIfRunning();
        if (n2 <= 0) {
            throw new IllegalArgumentException();
        }
        this.resolution = n2;
    }

    public Interpolator getInterpolator() {
        return this.interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.throwExceptionIfRunning();
        this.interpolator = interpolator;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addTarget(TimingTarget timingTarget) {
        if (timingTarget == null) {
            return;
        }
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            if (!this.targets.contains(timingTarget)) {
                this.targets.add(timingTarget);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeTarget(TimingTarget timingTarget) {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            this.targets.remove(timingTarget);
        }
    }

    public void start() {
        this.throwExceptionIfRunning();
        this.running = (int)-587887907L ^ 0xDCF58ADC;
        this.hasBegun = (int)((long)-2051885279 ^ (long)-2051885279);
        this.timeToStop = (int)((long)-1631148041 ^ (long)-1631148041);
        this.startTime = System.nanoTime() / (0xF4250L & 0x331F4240L);
        if (this.timer == null) {
            this.timer = new Timer(this.resolution, actionEvent -> {
                if (!this.hasBegun) {
                    this.begin();
                    this.hasBegun = (int)-1249791499L ^ 0xB581B1F4;
                }
                this.timingEvent(this.getTimingFraction());
            });
        } else {
            this.timer.setDelay(this.resolution);
        }
        this.timer.setInitialDelay((int)484685082L ^ 0x1CE3B51A);
        this.timer.start();
    }

    public void stop() {
        this.stop((boolean)((long)292715373 ^ (long)292715373));
    }

    public void cancel() {
        this.stop((boolean)((long)-1685137882 ^ (long)-1685137881));
    }

    private void stop(boolean bl) {
        if (!this.running) {
            return;
        }
        if (this.timer != null) {
            this.timer.stop();
        }
        if (!bl) {
            this.end();
        }
        this.running = (int)-476468816L ^ 0xE399A9B0;
        this.timeToStop = (int)1766408900L ^ 0x694942C4;
    }

    public void restart() {
        this.cancel();
        this.start();
    }

    public boolean isRunning() {
        return this.running;
    }

    private float getTimingFraction() {
        long l2 = System.nanoTime() / (0x338F5F64L & 0x5FC2C8L);
        long l3 = l2 - this.startTime;
        this.timeToStop = l3 >= (long)this.duration ? (int)-1643187735L ^ 0x9E0EF1E8 : (int)((long)-1769502934 ^ (long)-1769502934);
        float f2 = this.clampFraction((float)l3 / (float)this.duration);
        if (this.interpolator != null) {
            f2 = this.clampFraction(this.interpolator.interpolate(f2));
        }
        return f2;
    }

    private float clampFraction(float f2) {
        if (f2 < 0.0f) {
            return 0.0f;
        }
        if (f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void timingEvent(float f2) {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget timingTarget : this.targets) {
                timingTarget.timingEvent(f2);
            }
        }
        if (this.timeToStop) {
            this.stop();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void begin() {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget timingTarget : this.targets) {
                timingTarget.begin();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void end() {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget timingTarget : this.targets) {
                timingTarget.end();
            }
        }
        if (this.endRunnable != null) {
            this.endRunnable.run();
        }
    }

    private void throwExceptionIfRunning() {
        if (this.isRunning()) {
            throw new IllegalStateException();
        }
    }

    @FunctionalInterface
    public static interface Interpolator {
        public float interpolate(float var1);
    }

    @FunctionalInterface
    public static interface TimingTarget {
        public void timingEvent(float var1);

        default public void begin() {
        }

        default public void end() {
        }
    }
}

