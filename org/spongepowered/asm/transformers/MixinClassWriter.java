/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.transformers;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

public class MixinClassWriter
extends ClassWriter {
    public MixinClassWriter(int n2) {
        super(n2);
    }

    public MixinClassWriter(ClassReader classReader, int n2) {
        super(classReader, n2);
    }

    @Override
    protected String getCommonSuperClass(String string, String string2) {
        return ClassInfo.getCommonSuperClass(string, string2).getName();
    }
}

