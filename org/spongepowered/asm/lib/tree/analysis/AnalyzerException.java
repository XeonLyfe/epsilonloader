/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.analysis.Value;

public class AnalyzerException
extends Exception {
    public final AbstractInsnNode node;

    public AnalyzerException(AbstractInsnNode abstractInsnNode, String string) {
        super(string);
        this.node = abstractInsnNode;
    }

    public AnalyzerException(AbstractInsnNode abstractInsnNode, String string, Throwable throwable) {
        super(string, throwable);
        this.node = abstractInsnNode;
    }

    public AnalyzerException(AbstractInsnNode abstractInsnNode, String string, Object object, Value value) {
        super((string == null ? "Expected " : string + ": expected ") + object + ", but found " + value);
        this.node = abstractInsnNode;
    }
}

