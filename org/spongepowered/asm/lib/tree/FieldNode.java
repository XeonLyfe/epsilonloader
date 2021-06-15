/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;

public class FieldNode
extends FieldVisitor {
    public int access;
    public String name;
    public String desc;
    public String signature;
    public Object value;
    public List<AnnotationNode> visibleAnnotations;
    public List<AnnotationNode> invisibleAnnotations;
    public List<TypeAnnotationNode> visibleTypeAnnotations;
    public List<TypeAnnotationNode> invisibleTypeAnnotations;
    public List<Attribute> attrs;

    public FieldNode(int n2, String string, String string2, String string3, Object object) {
        this(327680, n2, string, string2, string3, object);
        if (this.getClass() != FieldNode.class) {
            throw new IllegalStateException();
        }
    }

    public FieldNode(int n2, int n3, String string, String string2, String string3, Object object) {
        super(n2);
        this.access = n3;
        this.name = string;
        this.desc = string2;
        this.signature = string3;
        this.value = object;
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

    public void visitAttribute(Attribute attribute) {
        if (this.attrs == null) {
            this.attrs = new ArrayList<Attribute>(1);
        }
        this.attrs.add(attribute);
    }

    public void visitEnd() {
    }

    public void check(int n2) {
        if (n2 == 262144) {
            if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
            if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0) {
                throw new RuntimeException();
            }
        }
    }

    public void accept(ClassVisitor classVisitor) {
        AnnotationNode annotationNode;
        int n2;
        FieldVisitor fieldVisitor = classVisitor.visitField(this.access, this.name, this.desc, this.signature, this.value);
        if (fieldVisitor == null) {
            return;
        }
        int n3 = this.visibleAnnotations == null ? 0 : this.visibleAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.visibleAnnotations.get(n2);
            annotationNode.accept(fieldVisitor.visitAnnotation(annotationNode.desc, true));
        }
        n3 = this.invisibleAnnotations == null ? 0 : this.invisibleAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.invisibleAnnotations.get(n2);
            annotationNode.accept(fieldVisitor.visitAnnotation(annotationNode.desc, false));
        }
        n3 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.visibleTypeAnnotations.get(n2);
            annotationNode.accept(fieldVisitor.visitTypeAnnotation(((TypeAnnotationNode)annotationNode).typeRef, ((TypeAnnotationNode)annotationNode).typePath, ((TypeAnnotationNode)annotationNode).desc, true));
        }
        n3 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.invisibleTypeAnnotations.get(n2);
            annotationNode.accept(fieldVisitor.visitTypeAnnotation(((TypeAnnotationNode)annotationNode).typeRef, ((TypeAnnotationNode)annotationNode).typePath, ((TypeAnnotationNode)annotationNode).desc, false));
        }
        n3 = this.attrs == null ? 0 : this.attrs.size();
        for (n2 = 0; n2 < n3; ++n2) {
            fieldVisitor.visitAttribute(this.attrs.get(n2));
        }
        fieldVisitor.visitEnd();
    }
}

