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
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.InnerClassNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;

public class ClassNode
extends ClassVisitor {
    public int version;
    public int access;
    public String name;
    public String signature;
    public String superName;
    public List<String> interfaces = new ArrayList<String>();
    public String sourceFile;
    public String sourceDebug;
    public String outerClass;
    public String outerMethod;
    public String outerMethodDesc;
    public List<AnnotationNode> visibleAnnotations;
    public List<AnnotationNode> invisibleAnnotations;
    public List<TypeAnnotationNode> visibleTypeAnnotations;
    public List<TypeAnnotationNode> invisibleTypeAnnotations;
    public List<Attribute> attrs;
    public List<InnerClassNode> innerClasses = new ArrayList<InnerClassNode>();
    public List<FieldNode> fields = new ArrayList<FieldNode>();
    public List<MethodNode> methods = new ArrayList<MethodNode>();

    public ClassNode() {
        this(327680);
        if (this.getClass() != ClassNode.class) {
            throw new IllegalStateException();
        }
    }

    public ClassNode(int n2) {
        super(n2);
    }

    public void visit(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
        this.version = n2;
        this.access = n3;
        this.name = string;
        this.signature = string2;
        this.superName = string3;
        if (arrstring != null) {
            this.interfaces.addAll(Arrays.asList(arrstring));
        }
    }

    public void visitSource(String string, String string2) {
        this.sourceFile = string;
        this.sourceDebug = string2;
    }

    public void visitOuterClass(String string, String string2, String string3) {
        this.outerClass = string;
        this.outerMethod = string2;
        this.outerMethodDesc = string3;
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

    public void visitInnerClass(String string, String string2, String string3, int n2) {
        InnerClassNode innerClassNode = new InnerClassNode(string, string2, string3, n2);
        this.innerClasses.add(innerClassNode);
    }

    public FieldVisitor visitField(int n2, String string, String string2, String string3, Object object) {
        FieldNode fieldNode = new FieldNode(n2, string, string2, string3, object);
        this.fields.add(fieldNode);
        return fieldNode;
    }

    public MethodVisitor visitMethod(int n2, String string, String string2, String string3, String[] arrstring) {
        MethodNode methodNode = new MethodNode(n2, string, string2, string3, arrstring);
        this.methods.add(methodNode);
        return methodNode;
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
            for (FieldNode object : this.fields) {
                object.check(n2);
            }
            for (MethodNode methodNode : this.methods) {
                methodNode.check(n2);
            }
        }
    }

    public void accept(ClassVisitor classVisitor) {
        AnnotationNode annotationNode;
        int n2;
        String[] arrstring = new String[this.interfaces.size()];
        this.interfaces.toArray(arrstring);
        classVisitor.visit(this.version, this.access, this.name, this.signature, this.superName, arrstring);
        if (this.sourceFile != null || this.sourceDebug != null) {
            classVisitor.visitSource(this.sourceFile, this.sourceDebug);
        }
        if (this.outerClass != null) {
            classVisitor.visitOuterClass(this.outerClass, this.outerMethod, this.outerMethodDesc);
        }
        int n3 = this.visibleAnnotations == null ? 0 : this.visibleAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.visibleAnnotations.get(n2);
            annotationNode.accept(classVisitor.visitAnnotation(annotationNode.desc, true));
        }
        n3 = this.invisibleAnnotations == null ? 0 : this.invisibleAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.invisibleAnnotations.get(n2);
            annotationNode.accept(classVisitor.visitAnnotation(annotationNode.desc, false));
        }
        n3 = this.visibleTypeAnnotations == null ? 0 : this.visibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.visibleTypeAnnotations.get(n2);
            annotationNode.accept(classVisitor.visitTypeAnnotation(((TypeAnnotationNode)annotationNode).typeRef, ((TypeAnnotationNode)annotationNode).typePath, ((TypeAnnotationNode)annotationNode).desc, true));
        }
        n3 = this.invisibleTypeAnnotations == null ? 0 : this.invisibleTypeAnnotations.size();
        for (n2 = 0; n2 < n3; ++n2) {
            annotationNode = this.invisibleTypeAnnotations.get(n2);
            annotationNode.accept(classVisitor.visitTypeAnnotation(((TypeAnnotationNode)annotationNode).typeRef, ((TypeAnnotationNode)annotationNode).typePath, ((TypeAnnotationNode)annotationNode).desc, false));
        }
        n3 = this.attrs == null ? 0 : this.attrs.size();
        for (n2 = 0; n2 < n3; ++n2) {
            classVisitor.visitAttribute(this.attrs.get(n2));
        }
        for (n2 = 0; n2 < this.innerClasses.size(); ++n2) {
            this.innerClasses.get(n2).accept(classVisitor);
        }
        for (n2 = 0; n2 < this.fields.size(); ++n2) {
            this.fields.get(n2).accept(classVisitor);
        }
        for (n2 = 0; n2 < this.methods.size(); ++n2) {
            this.methods.get(n2).accept(classVisitor);
        }
        classVisitor.visitEnd();
    }
}

