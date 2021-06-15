/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.List;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;

public class TryCatchBlockNode {
    public LabelNode start;
    public LabelNode end;
    public LabelNode handler;
    public String type;
    public List<TypeAnnotationNode> visibleTypeAnnotations;
    public List<TypeAnnotationNode> invisibleTypeAnnotations;

    public TryCatchBlockNode(LabelNode labelNode, LabelNode labelNode2, LabelNode labelNode3, String string) {
        this.start = labelNode;
        this.end = labelNode2;
        this.handler = labelNode3;
        this.type = string;
    }

    public void updateIndex(int n2) {
        int n3 = 0x42000000 | n2 << 8;
        if (this.visibleTypeAnnotations != null) {
            for (TypeAnnotationNode typeAnnotationNode : this.visibleTypeAnnotations) {
                typeAnnotationNode.typeRef = n3;
            }
        }
        if (this.invisibleTypeAnnotations != null) {
            for (TypeAnnotationNode typeAnnotationNode : this.invisibleTypeAnnotations) {
                typeAnnotationNode.typeRef = n3;
            }
        }
    }

    public void accept(MethodVisitor methodVisitor) {
        TypeAnnotationNode typeAnnotationNode;
        int n2;
        methodVisitor.visitTryCatchBlock(this.start.getLabel(), this.end.getLabel(), this.handler == null ? null : this.handler.getLabel(), this.type);
        int n3 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            typeAnnotationNode = this.visibleTypeAnnotations.get(n2);
            typeAnnotationNode.accept(methodVisitor.visitTryCatchAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
        }
        n3 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            typeAnnotationNode = this.invisibleTypeAnnotations.get(n2);
            typeAnnotationNode.accept(methodVisitor.visitTryCatchAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
        }
    }
}

