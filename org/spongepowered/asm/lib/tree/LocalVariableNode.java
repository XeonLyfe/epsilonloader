/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.LabelNode;

public class LocalVariableNode {
    public String name;
    public String desc;
    public String signature;
    public LabelNode start;
    public LabelNode end;
    public int index;

    public LocalVariableNode(String string, String string2, String string3, LabelNode labelNode, LabelNode labelNode2, int n2) {
        this.name = string;
        this.desc = string2;
        this.signature = string3;
        this.start = labelNode;
        this.end = labelNode2;
        this.index = n2;
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLocalVariable(this.name, this.desc, this.signature, this.start.getLabel(), this.end.getLabel(), this.index);
    }
}

