/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.asm;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.util.Bytecode;

public class MethodVisitorEx
extends MethodVisitor {
    public MethodVisitorEx(MethodVisitor methodVisitor) {
        super(327680, methodVisitor);
    }

    public void visitConstant(byte by) {
        if (by > -2 && by < 6) {
            this.visitInsn(Bytecode.CONSTANTS_INT[by + 1]);
            return;
        }
        this.visitIntInsn(16, by);
    }
}

