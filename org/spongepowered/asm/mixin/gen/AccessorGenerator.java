/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.gen;

import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.gen.AccessorInfo;

public abstract class AccessorGenerator {
    protected final AccessorInfo info;

    public AccessorGenerator(AccessorInfo accessorInfo) {
        this.info = accessorInfo;
    }

    protected final MethodNode createMethod(int n2, int n3) {
        MethodNode methodNode = this.info.getMethod();
        MethodNode methodNode2 = new MethodNode(327680, methodNode.access & 0xFFFFFBFF | 0x1000, methodNode.name, methodNode.desc, null, null);
        methodNode2.visibleAnnotations = new ArrayList<AnnotationNode>();
        methodNode2.visibleAnnotations.add(this.info.getAnnotation());
        methodNode2.maxLocals = n2;
        methodNode2.maxStack = n3;
        return methodNode2;
    }

    public abstract MethodNode generate();
}

