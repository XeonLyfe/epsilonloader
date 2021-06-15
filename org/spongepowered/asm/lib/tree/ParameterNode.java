/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.MethodVisitor;

public class ParameterNode {
    public String name;
    public int access;

    public ParameterNode(String string, int n2) {
        this.name = string;
        this.access = n2;
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitParameter(this.name, this.access);
    }
}

