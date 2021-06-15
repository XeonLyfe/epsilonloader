/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.transformers;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.service.ILegacyClassTransformer;
import org.spongepowered.asm.transformers.MixinClassWriter;

public abstract class TreeTransformer
implements ILegacyClassTransformer {
    private ClassReader classReader;
    private ClassNode classNode;

    protected final ClassNode readClass(byte[] arrby) {
        return this.readClass(arrby, true);
    }

    protected final ClassNode readClass(byte[] arrby, boolean bl) {
        ClassReader classReader = new ClassReader(arrby);
        if (bl) {
            this.classReader = classReader;
        }
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 8);
        return classNode;
    }

    protected final byte[] writeClass(ClassNode classNode) {
        if (this.classReader != null && this.classNode == classNode) {
            this.classNode = null;
            MixinClassWriter mixinClassWriter = new MixinClassWriter(this.classReader, 3);
            this.classReader = null;
            classNode.accept(mixinClassWriter);
            return mixinClassWriter.toByteArray();
        }
        this.classNode = null;
        MixinClassWriter mixinClassWriter = new MixinClassWriter(3);
        classNode.accept(mixinClassWriter);
        return mixinClassWriter.toByteArray();
    }
}

