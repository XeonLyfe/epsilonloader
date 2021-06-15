/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.ClassVisitor;

public class InnerClassNode {
    public String name;
    public String outerName;
    public String innerName;
    public int access;

    public InnerClassNode(String string, String string2, String string3, int n2) {
        this.name = string;
        this.outerName = string2;
        this.innerName = string3;
        this.access = n2;
    }

    public void accept(ClassVisitor classVisitor) {
        classVisitor.visitInnerClass(this.name, this.outerName, this.innerName, this.access);
    }
}

