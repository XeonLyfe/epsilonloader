/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.IincInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.IntInsnNode;
import org.spongepowered.asm.lib.tree.InvokeDynamicInsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.LocalVariableAnnotationNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.LookupSwitchInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MultiANewArrayInsnNode;
import org.spongepowered.asm.lib.tree.ParameterNode;
import org.spongepowered.asm.lib.tree.TableSwitchInsnNode;
import org.spongepowered.asm.lib.tree.TryCatchBlockNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;

public class MethodNode
extends MethodVisitor {
    public int access;
    public String name;
    public String desc;
    public String signature;
    public List<String> exceptions;
    public List<ParameterNode> parameters;
    public List<AnnotationNode> visibleAnnotations;
    public List<AnnotationNode> invisibleAnnotations;
    public List<TypeAnnotationNode> visibleTypeAnnotations;
    public List<TypeAnnotationNode> invisibleTypeAnnotations;
    public List<Attribute> attrs;
    public Object annotationDefault;
    public List<AnnotationNode>[] visibleParameterAnnotations;
    public List<AnnotationNode>[] invisibleParameterAnnotations;
    public InsnList instructions;
    public List<TryCatchBlockNode> tryCatchBlocks;
    public int maxStack;
    public int maxLocals;
    public List<LocalVariableNode> localVariables;
    public List<LocalVariableAnnotationNode> visibleLocalVariableAnnotations;
    public List<LocalVariableAnnotationNode> invisibleLocalVariableAnnotations;
    private boolean visited;

    public MethodNode() {
        this(327680);
        if (this.getClass() != MethodNode.class) {
            throw new IllegalStateException();
        }
    }

    public MethodNode(int n2) {
        super(n2);
        this.instructions = new InsnList();
    }

    public MethodNode(int n2, String string, String string2, String string3, String[] arrstring) {
        this(327680, n2, string, string2, string3, arrstring);
        if (this.getClass() != MethodNode.class) {
            throw new IllegalStateException();
        }
    }

    public MethodNode(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
        super(n2);
        boolean bl;
        this.access = n3;
        this.name = string;
        this.desc = string2;
        this.signature = string3;
        this.exceptions = new ArrayList<String>(arrstring == null ? 0 : arrstring.length);
        boolean bl2 = bl = (n3 & 0x400) != 0;
        if (!bl) {
            this.localVariables = new ArrayList<LocalVariableNode>(5);
        }
        this.tryCatchBlocks = new ArrayList<TryCatchBlockNode>();
        if (arrstring != null) {
            this.exceptions.addAll(Arrays.asList(arrstring));
        }
        this.instructions = new InsnList();
    }

    public void visitParameter(String string, int n2) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<ParameterNode>(5);
        }
        this.parameters.add(new ParameterNode(string, n2));
    }

    public AnnotationVisitor visitAnnotationDefault() {
        return new AnnotationNode((List<Object>)new ArrayList<Object>(0){

            @Override
            public boolean add(Object object) {
                MethodNode.this.annotationDefault = object;
                return super.add(object);
            }
        });
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        AnnotationNode annotationNode = new AnnotationNode(string);
        if (bl) {
            if (this.visibleAnnotations == null) {
                this.visibleAnnotations = new ArrayList<AnnotationNode>(1);
            }
            this.visibleAnnotations.add(annotationNode);
        } else {
            if (this.invisibleAnnotations == null) {
                this.invisibleAnnotations = new ArrayList<AnnotationNode>(1);
            }
            this.invisibleAnnotations.add(annotationNode);
        }
        return annotationNode;
    }

    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n2, typePath, string);
        if (bl) {
            if (this.visibleTypeAnnotations == null) {
                this.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            this.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (this.invisibleTypeAnnotations == null) {
                this.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            this.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public AnnotationVisitor visitParameterAnnotation(int n2, String string, boolean bl) {
        AnnotationNode annotationNode = new AnnotationNode(string);
        if (bl) {
            if (this.visibleParameterAnnotations == null) {
                int n3 = Type.getArgumentTypes(this.desc).length;
                this.visibleParameterAnnotations = new List[n3];
            }
            if (this.visibleParameterAnnotations[n2] == null) {
                this.visibleParameterAnnotations[n2] = new ArrayList<AnnotationNode>(1);
            }
            this.visibleParameterAnnotations[n2].add(annotationNode);
        } else {
            if (this.invisibleParameterAnnotations == null) {
                int n4 = Type.getArgumentTypes(this.desc).length;
                this.invisibleParameterAnnotations = new List[n4];
            }
            if (this.invisibleParameterAnnotations[n2] == null) {
                this.invisibleParameterAnnotations[n2] = new ArrayList<AnnotationNode>(1);
            }
            this.invisibleParameterAnnotations[n2].add(annotationNode);
        }
        return annotationNode;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.attrs == null) {
            this.attrs = new ArrayList<Attribute>(1);
        }
        this.attrs.add(attribute);
    }

    public void visitCode() {
    }

    public void visitFrame(int n2, int n3, Object[] arrobject, int n4, Object[] arrobject2) {
        this.instructions.add(new FrameNode(n2, n3, arrobject == null ? null : this.getLabelNodes(arrobject), n4, arrobject2 == null ? null : this.getLabelNodes(arrobject2)));
    }

    public void visitInsn(int n2) {
        this.instructions.add(new InsnNode(n2));
    }

    public void visitIntInsn(int n2, int n3) {
        this.instructions.add(new IntInsnNode(n2, n3));
    }

    public void visitVarInsn(int n2, int n3) {
        this.instructions.add(new VarInsnNode(n2, n3));
    }

    public void visitTypeInsn(int n2, String string) {
        this.instructions.add(new TypeInsnNode(n2, string));
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        this.instructions.add(new FieldInsnNode(n2, string, string2, string3));
    }

    @Deprecated
    public void visitMethodInsn(int n2, String string, String string2, String string3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(n2, string, string2, string3);
            return;
        }
        this.instructions.add(new MethodInsnNode(n2, string, string2, string3));
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        if (this.api < 327680) {
            super.visitMethodInsn(n2, string, string2, string3, bl);
            return;
        }
        this.instructions.add(new MethodInsnNode(n2, string, string2, string3, bl));
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        this.instructions.add(new InvokeDynamicInsnNode(string, string2, handle, arrobject));
    }

    public void visitJumpInsn(int n2, Label label) {
        this.instructions.add(new JumpInsnNode(n2, this.getLabelNode(label)));
    }

    public void visitLabel(Label label) {
        this.instructions.add(this.getLabelNode(label));
    }

    public void visitLdcInsn(Object object) {
        this.instructions.add(new LdcInsnNode(object));
    }

    public void visitIincInsn(int n2, int n3) {
        this.instructions.add(new IincInsnNode(n2, n3));
    }

    public void visitTableSwitchInsn(int n2, int n3, Label label, Label ... arrlabel) {
        this.instructions.add(new TableSwitchInsnNode(n2, n3, this.getLabelNode(label), this.getLabelNodes(arrlabel)));
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        this.instructions.add(new LookupSwitchInsnNode(this.getLabelNode(label), arrn, this.getLabelNodes(arrlabel)));
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        this.instructions.add(new MultiANewArrayInsnNode(string, n2));
    }

    public AnnotationVisitor visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        AbstractInsnNode abstractInsnNode = this.instructions.getLast();
        while (abstractInsnNode.getOpcode() == -1) {
            abstractInsnNode = abstractInsnNode.getPrevious();
        }
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n2, typePath, string);
        if (bl) {
            if (abstractInsnNode.visibleTypeAnnotations == null) {
                abstractInsnNode.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            abstractInsnNode.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (abstractInsnNode.invisibleTypeAnnotations == null) {
                abstractInsnNode.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            abstractInsnNode.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        this.tryCatchBlocks.add(new TryCatchBlockNode(this.getLabelNode(label), this.getLabelNode(label2), this.getLabelNode(label3), string));
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        TryCatchBlockNode tryCatchBlockNode = this.tryCatchBlocks.get((n2 & 0xFFFF00) >> 8);
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(n2, typePath, string);
        if (bl) {
            if (tryCatchBlockNode.visibleTypeAnnotations == null) {
                tryCatchBlockNode.visibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            tryCatchBlockNode.visibleTypeAnnotations.add(typeAnnotationNode);
        } else {
            if (tryCatchBlockNode.invisibleTypeAnnotations == null) {
                tryCatchBlockNode.invisibleTypeAnnotations = new ArrayList<TypeAnnotationNode>(1);
            }
            tryCatchBlockNode.invisibleTypeAnnotations.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        this.localVariables.add(new LocalVariableNode(string, string2, string3, this.getLabelNode(label), this.getLabelNode(label2), n2));
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        LocalVariableAnnotationNode localVariableAnnotationNode = new LocalVariableAnnotationNode(n2, typePath, this.getLabelNodes(arrlabel), this.getLabelNodes(arrlabel2), arrn, string);
        if (bl) {
            if (this.visibleLocalVariableAnnotations == null) {
                this.visibleLocalVariableAnnotations = new ArrayList<LocalVariableAnnotationNode>(1);
            }
            this.visibleLocalVariableAnnotations.add(localVariableAnnotationNode);
        } else {
            if (this.invisibleLocalVariableAnnotations == null) {
                this.invisibleLocalVariableAnnotations = new ArrayList<LocalVariableAnnotationNode>(1);
            }
            this.invisibleLocalVariableAnnotations.add(localVariableAnnotationNode);
        }
        return localVariableAnnotationNode;
    }

    public void visitLineNumber(int n2, Label label) {
        this.instructions.add(new LineNumberNode(n2, this.getLabelNode(label)));
    }

    public void visitMaxs(int n2, int n3) {
        this.maxStack = n2;
        this.maxLocals = n3;
    }

    public void visitEnd() {
    }

    protected LabelNode getLabelNode(Label label) {
        if (!(label.info instanceof LabelNode)) {
            label.info = new LabelNode();
        }
        return (LabelNode)label.info;
    }

    private LabelNode[] getLabelNodes(Label[] arrlabel) {
        LabelNode[] arrlabelNode = new LabelNode[arrlabel.length];
        for (int i2 = 0; i2 < arrlabel.length; ++i2) {
            arrlabelNode[i2] = this.getLabelNode(arrlabel[i2]);
        }
        return arrlabelNode;
    }

    private Object[] getLabelNodes(Object[] arrobject) {
        Object[] arrobject2 = new Object[arrobject.length];
        for (int i2 = 0; i2 < arrobject.length; ++i2) {
            Object object = arrobject[i2];
            if (object instanceof Label) {
                object = this.getLabelNode((Label)object);
            }
            arrobject2[i2] = object;
        }
        return arrobject2;
    }

    public void check(int n2) {
        if (n2 == 262144) {
            Object object;
            int n3;
            if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            int n4 = this.tryCatchBlocks == null ? 0 : this.tryCatchBlocks.size();
            for (n3 = 0; n3 < n4; ++n3) {
                object = this.tryCatchBlocks.get(n3);
                if (((TryCatchBlockNode)object).visibleTypeAnnotations != null && ((TryCatchBlockNode)object).visibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (((TryCatchBlockNode)object).invisibleTypeAnnotations == null || ((TryCatchBlockNode)object).invisibleTypeAnnotations.size() <= 0) continue;
                throw new RuntimeException();
            }
            for (n3 = 0; n3 < this.instructions.size(); ++n3) {
                boolean bl;
                object = this.instructions.get(n3);
                if (((AbstractInsnNode)object).visibleTypeAnnotations != null && ((AbstractInsnNode)object).visibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (((AbstractInsnNode)object).invisibleTypeAnnotations != null && ((AbstractInsnNode)object).invisibleTypeAnnotations.size() > 0) {
                    throw new RuntimeException();
                }
                if (!(object instanceof MethodInsnNode) || (bl = ((MethodInsnNode)object).itf) == (((AbstractInsnNode)object).opcode == 185)) continue;
                throw new RuntimeException();
            }
            if (this.visibleLocalVariableAnnotations != null && this.visibleLocalVariableAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleLocalVariableAnnotations != null && this.invisibleLocalVariableAnnotations.size() > 0) {
                throw new RuntimeException();
            }
        }
    }

    public void accept(ClassVisitor classVisitor) {
        String[] arrstring = new String[this.exceptions.size()];
        this.exceptions.toArray(arrstring);
        MethodVisitor methodVisitor = classVisitor.visitMethod(this.access, this.name, this.desc, this.signature, arrstring);
        if (methodVisitor != null) {
            this.accept(methodVisitor);
        }
    }

    public void accept(MethodVisitor methodVisitor) {
        AnnotationNode annotationNode;
        int n2;
        List<AnnotationNode> list;
        int n3;
        int n4 = this.parameters == null ? 0 : this.parameters.size();
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.parameters.get(n3);
            methodVisitor.visitParameter(((ParameterNode)list).name, ((ParameterNode)list).access);
        }
        if (this.annotationDefault != null) {
            list = methodVisitor.visitAnnotationDefault();
            AnnotationNode.accept((AnnotationVisitor)((Object)list), null, this.annotationDefault);
            if (list != null) {
                ((AnnotationVisitor)((Object)list)).visitEnd();
            }
        }
        n4 = this.visibleAnnotations == null ? 0 : this.visibleAnnotations.size();
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.visibleAnnotations.get(n3);
            ((AnnotationNode)((Object)list)).accept(methodVisitor.visitAnnotation(((AnnotationNode)list).desc, true));
        }
        n4 = this.invisibleAnnotations == null ? 0 : this.invisibleAnnotations.size();
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.invisibleAnnotations.get(n3);
            ((AnnotationNode)((Object)list)).accept(methodVisitor.visitAnnotation(((AnnotationNode)list).desc, false));
        }
        n4 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.visibleTypeAnnotations.get(n3);
            ((AnnotationNode)((Object)list)).accept(methodVisitor.visitTypeAnnotation(((TypeAnnotationNode)list).typeRef, ((TypeAnnotationNode)list).typePath, ((TypeAnnotationNode)list).desc, true));
        }
        n4 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.invisibleTypeAnnotations.get(n3);
            ((AnnotationNode)((Object)list)).accept(methodVisitor.visitTypeAnnotation(((TypeAnnotationNode)list).typeRef, ((TypeAnnotationNode)list).typePath, ((TypeAnnotationNode)list).desc, false));
        }
        n4 = this.visibleParameterAnnotations == null ? 0 : this.visibleParameterAnnotations.length;
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.visibleParameterAnnotations[n3];
            if (list == null) continue;
            for (n2 = 0; n2 < list.size(); ++n2) {
                annotationNode = list.get(n2);
                annotationNode.accept(methodVisitor.visitParameterAnnotation(n3, annotationNode.desc, true));
            }
        }
        n4 = this.invisibleParameterAnnotations == null ? 0 : this.invisibleParameterAnnotations.length;
        for (n3 = 0; n3 < n4; ++n3) {
            list = this.invisibleParameterAnnotations[n3];
            if (list == null) continue;
            for (n2 = 0; n2 < list.size(); ++n2) {
                annotationNode = list.get(n2);
                annotationNode.accept(methodVisitor.visitParameterAnnotation(n3, annotationNode.desc, false));
            }
        }
        if (this.visited) {
            this.instructions.resetLabels();
        }
        n4 = this.attrs == null ? 0 : this.attrs.size();
        for (n3 = 0; n3 < n4; ++n3) {
            methodVisitor.visitAttribute(this.attrs.get(n3));
        }
        if (this.instructions.size() > 0) {
            methodVisitor.visitCode();
            n4 = this.tryCatchBlocks == null ? 0 : this.tryCatchBlocks.size();
            for (n3 = 0; n3 < n4; ++n3) {
                this.tryCatchBlocks.get(n3).updateIndex(n3);
                this.tryCatchBlocks.get(n3).accept(methodVisitor);
            }
            this.instructions.accept(methodVisitor);
            n4 = this.localVariables == null ? 0 : this.localVariables.size();
            for (n3 = 0; n3 < n4; ++n3) {
                this.localVariables.get(n3).accept(methodVisitor);
            }
            n4 = this.visibleLocalVariableAnnotations == null ? 0 : this.visibleLocalVariableAnnotations.size();
            for (n3 = 0; n3 < n4; ++n3) {
                this.visibleLocalVariableAnnotations.get(n3).accept(methodVisitor, true);
            }
            n4 = this.invisibleLocalVariableAnnotations == null ? 0 : this.invisibleLocalVariableAnnotations.size();
            for (n3 = 0; n3 < n4; ++n3) {
                this.invisibleLocalVariableAnnotations.get(n3).accept(methodVisitor, false);
            }
            methodVisitor.visitMaxs(this.maxStack, this.maxLocals);
            this.visited = true;
        }
        methodVisitor.visitEnd();
    }
}

