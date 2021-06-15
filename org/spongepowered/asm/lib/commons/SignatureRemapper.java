/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.commons;

import java.util.Stack;
import org.spongepowered.asm.lib.commons.Remapper;
import org.spongepowered.asm.lib.signature.SignatureVisitor;

public class SignatureRemapper
extends SignatureVisitor {
    private final SignatureVisitor v;
    private final Remapper remapper;
    private Stack<String> classNames = new Stack();

    public SignatureRemapper(SignatureVisitor signatureVisitor, Remapper remapper) {
        this(327680, signatureVisitor, remapper);
    }

    protected SignatureRemapper(int n2, SignatureVisitor signatureVisitor, Remapper remapper) {
        super(n2);
        this.v = signatureVisitor;
        this.remapper = remapper;
    }

    public void visitClassType(String string) {
        this.classNames.push(string);
        this.v.visitClassType(this.remapper.mapType(string));
    }

    public void visitInnerClassType(String string) {
        String string2 = this.classNames.pop();
        String string3 = string2 + '$' + string;
        this.classNames.push(string3);
        String string4 = this.remapper.mapType(string2) + '$';
        String string5 = this.remapper.mapType(string3);
        int n2 = string5.startsWith(string4) ? string4.length() : string5.lastIndexOf(36) + 1;
        this.v.visitInnerClassType(string5.substring(n2));
    }

    public void visitFormalTypeParameter(String string) {
        this.v.visitFormalTypeParameter(string);
    }

    public void visitTypeVariable(String string) {
        this.v.visitTypeVariable(string);
    }

    public SignatureVisitor visitArrayType() {
        this.v.visitArrayType();
        return this;
    }

    public void visitBaseType(char c2) {
        this.v.visitBaseType(c2);
    }

    public SignatureVisitor visitClassBound() {
        this.v.visitClassBound();
        return this;
    }

    public SignatureVisitor visitExceptionType() {
        this.v.visitExceptionType();
        return this;
    }

    public SignatureVisitor visitInterface() {
        this.v.visitInterface();
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        this.v.visitInterfaceBound();
        return this;
    }

    public SignatureVisitor visitParameterType() {
        this.v.visitParameterType();
        return this;
    }

    public SignatureVisitor visitReturnType() {
        this.v.visitReturnType();
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        this.v.visitSuperclass();
        return this;
    }

    public void visitTypeArgument() {
        this.v.visitTypeArgument();
    }

    public SignatureVisitor visitTypeArgument(char c2) {
        this.v.visitTypeArgument(c2);
        return this;
    }

    public void visitEnd() {
        this.v.visitEnd();
        this.classNames.pop();
    }
}

