/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.signature;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

public class SignatureReader {
    private final String signature;

    public SignatureReader(String string) {
        this.signature = string;
    }

    public void accept(SignatureVisitor signatureVisitor) {
        int n2;
        String string = this.signature;
        int n3 = string.length();
        if (string.charAt(0) == '<') {
            char c2;
            n2 = 2;
            do {
                int n4 = string.indexOf(58, n2);
                signatureVisitor.visitFormalTypeParameter(string.substring(n2 - 1, n4));
                n2 = n4 + 1;
                c2 = string.charAt(n2);
                if (c2 == 'L' || c2 == '[' || c2 == 'T') {
                    n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitClassBound());
                }
                while ((c2 = string.charAt(n2++)) == ':') {
                    n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitInterfaceBound());
                }
            } while (c2 != '>');
        } else {
            n2 = 0;
        }
        if (string.charAt(n2) == '(') {
            ++n2;
            while (string.charAt(n2) != ')') {
                n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitParameterType());
            }
            n2 = SignatureReader.parseType(string, n2 + 1, signatureVisitor.visitReturnType());
            while (n2 < n3) {
                n2 = SignatureReader.parseType(string, n2 + 1, signatureVisitor.visitExceptionType());
            }
        } else {
            n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitSuperclass());
            while (n2 < n3) {
                n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitInterface());
            }
        }
    }

    public void acceptType(SignatureVisitor signatureVisitor) {
        SignatureReader.parseType(this.signature, 0, signatureVisitor);
    }

    private static int parseType(String string, int n2, SignatureVisitor signatureVisitor) {
        char c2 = string.charAt(n2++);
        switch (c2) {
            case 'B': 
            case 'C': 
            case 'D': 
            case 'F': 
            case 'I': 
            case 'J': 
            case 'S': 
            case 'V': 
            case 'Z': {
                signatureVisitor.visitBaseType(c2);
                return n2;
            }
            case '[': {
                return SignatureReader.parseType(string, n2, signatureVisitor.visitArrayType());
            }
            case 'T': {
                int n3 = string.indexOf(59, n2);
                signatureVisitor.visitTypeVariable(string.substring(n2, n3));
                return n3 + 1;
            }
        }
        int n4 = n2;
        boolean bl = false;
        boolean bl2 = false;
        while (true) {
            c2 = string.charAt(n2++);
            block5 : switch (c2) {
                case '.': 
                case ';': {
                    String string2;
                    if (!bl) {
                        string2 = string.substring(n4, n2 - 1);
                        if (bl2) {
                            signatureVisitor.visitInnerClassType(string2);
                        } else {
                            signatureVisitor.visitClassType(string2);
                        }
                    }
                    if (c2 == ';') {
                        signatureVisitor.visitEnd();
                        return n2;
                    }
                    n4 = n2;
                    bl = false;
                    bl2 = true;
                    break;
                }
                case '<': {
                    String string2 = string.substring(n4, n2 - 1);
                    if (bl2) {
                        signatureVisitor.visitInnerClassType(string2);
                    } else {
                        signatureVisitor.visitClassType(string2);
                    }
                    bl = true;
                    block15: while (true) {
                        c2 = string.charAt(n2);
                        switch (c2) {
                            case '>': {
                                break block5;
                            }
                            case '*': {
                                ++n2;
                                signatureVisitor.visitTypeArgument();
                                continue block15;
                            }
                            case '+': 
                            case '-': {
                                n2 = SignatureReader.parseType(string, n2 + 1, signatureVisitor.visitTypeArgument(c2));
                                continue block15;
                            }
                        }
                        n2 = SignatureReader.parseType(string, n2, signatureVisitor.visitTypeArgument('='));
                    }
                }
            }
        }
    }
}

