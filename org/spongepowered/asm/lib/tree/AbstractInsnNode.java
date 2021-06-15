/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractInsnNode {
    public static final int INSN = 0;
    public static final int INT_INSN = 1;
    public static final int VAR_INSN = 2;
    public static final int TYPE_INSN = 3;
    public static final int FIELD_INSN = 4;
    public static final int METHOD_INSN = 5;
    public static final int INVOKE_DYNAMIC_INSN = 6;
    public static final int JUMP_INSN = 7;
    public static final int LABEL = 8;
    public static final int LDC_INSN = 9;
    public static final int IINC_INSN = 10;
    public static final int TABLESWITCH_INSN = 11;
    public static final int LOOKUPSWITCH_INSN = 12;
    public static final int MULTIANEWARRAY_INSN = 13;
    public static final int FRAME = 14;
    public static final int LINE = 15;
    protected int opcode;
    public List<TypeAnnotationNode> visibleTypeAnnotations;
    public List<TypeAnnotationNode> invisibleTypeAnnotations;
    AbstractInsnNode prev;
    AbstractInsnNode next;
    int index;

    protected AbstractInsnNode(int n2) {
        this.opcode = n2;
        this.index = -1;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public abstract int getType();

    public AbstractInsnNode getPrevious() {
        return this.prev;
    }

    public AbstractInsnNode getNext() {
        return this.next;
    }

    public abstract void accept(MethodVisitor var1);

    protected final void acceptAnnotations(MethodVisitor methodVisitor) {
        TypeAnnotationNode typeAnnotationNode;
        int n2;
        int n3 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            typeAnnotationNode = this.visibleTypeAnnotations.get(n2);
            typeAnnotationNode.accept(methodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
        }
        n3 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            typeAnnotationNode = this.invisibleTypeAnnotations.get(n2);
            typeAnnotationNode.accept(methodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
        }
    }

    public abstract AbstractInsnNode clone(Map<LabelNode, LabelNode> var1);

    static LabelNode clone(LabelNode labelNode, Map<LabelNode, LabelNode> map) {
        return map.get(labelNode);
    }

    static LabelNode[] clone(List<LabelNode> list, Map<LabelNode, LabelNode> map) {
        LabelNode[] arrlabelNode = new LabelNode[list.size()];
        for (int i2 = 0; i2 < arrlabelNode.length; ++i2) {
            arrlabelNode[i2] = map.get(list.get(i2));
        }
        return arrlabelNode;
    }

    protected final AbstractInsnNode cloneAnnotations(AbstractInsnNode abstractInsnNode) {
        TypeAnnotationNode typeAnnotationNode;
        TypeAnnotationNode typeAnnotationNode2;
        int n2;
        if (abstractInsnNode.visibleTypeAnnotations != null) {
            this.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
            for (n2 = 0; n2 < abstractInsnNode.visibleTypeAnnotations.size(); ++n2) {
                typeAnnotationNode2 = abstractInsnNode.visibleTypeAnnotations.get(n2);
                typeAnnotationNode = new TypeAnnotationNode(typeAnnotationNode2.typeRef, typeAnnotationNode2.typePath, typeAnnotationNode2.desc);
                typeAnnotationNode2.accept(typeAnnotationNode);
                this.visibleTypeAnnotations.add(typeAnnotationNode);
            }
        }
        if (abstractInsnNode.invisibleTypeAnnotations != null) {
            this.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>();
            for (n2 = 0; n2 < abstractInsnNode.invisibleTypeAnnotations.size(); ++n2) {
                typeAnnotationNode2 = abstractInsnNode.invisibleTypeAnnotations.get(n2);
                typeAnnotationNode = new TypeAnnotationNode(typeAnnotationNode2.typeRef, typeAnnotationNode2.typePath, typeAnnotationNode2.desc);
                typeAnnotationNode2.accept(typeAnnotationNode);
                this.invisibleTypeAnnotations.add(typeAnnotationNode);
            }
        }
        return this;
    }
}

