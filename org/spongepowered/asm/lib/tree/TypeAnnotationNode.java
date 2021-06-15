/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.AnnotationNode;

public class TypeAnnotationNode
extends AnnotationNode {
    public int typeRef;
    public TypePath typePath;

    public TypeAnnotationNode(int n2, TypePath typePath, String string) {
        this(327680, n2, typePath, string);
        if (this.getClass() != TypeAnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    public TypeAnnotationNode(int n2, int n3, TypePath typePath, String string) {
        super(n2, string);
        this.typeRef = n3;
        this.typePath = typePath;
    }
}

