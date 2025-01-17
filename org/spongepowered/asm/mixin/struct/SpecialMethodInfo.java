/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.IInjectionPointContext;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

public abstract class SpecialMethodInfo
implements IInjectionPointContext {
    protected final AnnotationNode annotation;
    protected final ClassNode classNode;
    protected final MethodNode method;
    protected final MixinTargetContext mixin;

    public SpecialMethodInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode, AnnotationNode annotationNode) {
        this.mixin = mixinTargetContext;
        this.method = methodNode;
        this.annotation = annotationNode;
        this.classNode = mixinTargetContext.getTargetClassNode();
    }

    @Override
    public final IMixinContext getContext() {
        return this.mixin;
    }

    @Override
    public final AnnotationNode getAnnotation() {
        return this.annotation;
    }

    public final ClassNode getClassNode() {
        return this.classNode;
    }

    @Override
    public final MethodNode getMethod() {
        return this.method;
    }
}

