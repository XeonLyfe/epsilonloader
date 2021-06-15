/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.Frame;
import org.spongepowered.asm.lib.Item;

class CurrentFrame
extends Frame {
    CurrentFrame() {
    }

    void execute(int n2, int n3, ClassWriter classWriter, Item item) {
        super.execute(n2, n3, classWriter, item);
        Frame frame = new Frame();
        this.merge(classWriter, frame, 0);
        this.set(frame);
        this.owner.inputStackTop = 0;
    }
}

