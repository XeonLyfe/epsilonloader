/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

import com.google.common.base.Strings;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;

public class SignaturePrinter {
    private final String name;
    private final Type returnType;
    private final Type[] argTypes;
    private final String[] argNames;
    private String modifiers = "private void";
    private boolean fullyQualified;

    public SignaturePrinter(MethodNode methodNode) {
        this(methodNode.name, Type.VOID_TYPE, Type.getArgumentTypes(methodNode.desc));
        this.setModifiers(methodNode);
    }

    public SignaturePrinter(MethodNode methodNode, String[] arrstring) {
        this(methodNode.name, Type.VOID_TYPE, Type.getArgumentTypes(methodNode.desc), arrstring);
        this.setModifiers(methodNode);
    }

    public SignaturePrinter(MemberInfo memberInfo) {
        this(memberInfo.name, memberInfo.desc);
    }

    public SignaturePrinter(String string, String string2) {
        this(string, Type.getReturnType(string2), Type.getArgumentTypes(string2));
    }

    public SignaturePrinter(String string, Type type, Type[] arrtype) {
        this.name = string;
        this.returnType = type;
        this.argTypes = new Type[arrtype.length];
        this.argNames = new String[arrtype.length];
        int n2 = 0;
        for (int i2 = 0; i2 < arrtype.length; ++i2) {
            if (arrtype[i2] == null) continue;
            this.argTypes[i2] = arrtype[i2];
            this.argNames[i2] = "var" + n2++;
        }
    }

    public SignaturePrinter(String string, Type type, LocalVariableNode[] arrlocalVariableNode) {
        this.name = string;
        this.returnType = type;
        this.argTypes = new Type[arrlocalVariableNode.length];
        this.argNames = new String[arrlocalVariableNode.length];
        for (int i2 = 0; i2 < arrlocalVariableNode.length; ++i2) {
            if (arrlocalVariableNode[i2] == null) continue;
            this.argTypes[i2] = Type.getType(arrlocalVariableNode[i2].desc);
            this.argNames[i2] = arrlocalVariableNode[i2].name;
        }
    }

    public SignaturePrinter(String string, Type type, Type[] arrtype, String[] arrstring) {
        this.name = string;
        this.returnType = type;
        this.argTypes = arrtype;
        this.argNames = arrstring;
        if (this.argTypes.length > this.argNames.length) {
            throw new IllegalArgumentException(String.format("Types array length must not exceed names array length! (names=%d, types=%d)", this.argNames.length, this.argTypes.length));
        }
    }

    public String getFormattedArgs() {
        return this.appendArgs(new StringBuilder(), true, true).toString();
    }

    public String getReturnType() {
        return SignaturePrinter.getTypeName(this.returnType, false, this.fullyQualified);
    }

    public void setModifiers(MethodNode methodNode) {
        String string = SignaturePrinter.getTypeName(Type.getReturnType(methodNode.desc), false, this.fullyQualified);
        if ((methodNode.access & 1) != 0) {
            this.setModifiers("public " + string);
        } else if ((methodNode.access & 4) != 0) {
            this.setModifiers("protected " + string);
        } else if ((methodNode.access & 2) != 0) {
            this.setModifiers("private " + string);
        } else {
            this.setModifiers(string);
        }
    }

    public SignaturePrinter setModifiers(String string) {
        this.modifiers = string.replace("${returnType}", this.getReturnType());
        return this;
    }

    public SignaturePrinter setFullyQualified(boolean bl) {
        this.fullyQualified = bl;
        return this;
    }

    public boolean isFullyQualified() {
        return this.fullyQualified;
    }

    public String toString() {
        return this.appendArgs(new StringBuilder().append(this.modifiers).append(" ").append(this.name), false, true).toString();
    }

    public String toDescriptor() {
        StringBuilder stringBuilder = this.appendArgs(new StringBuilder(), true, false);
        return stringBuilder.append(SignaturePrinter.getTypeName(this.returnType, false, this.fullyQualified)).toString();
    }

    private StringBuilder appendArgs(StringBuilder stringBuilder, boolean bl, boolean bl2) {
        stringBuilder.append('(');
        for (int i2 = 0; i2 < this.argTypes.length; ++i2) {
            if (this.argTypes[i2] == null) continue;
            if (i2 > 0) {
                stringBuilder.append(',');
                if (bl2) {
                    stringBuilder.append(' ');
                }
            }
            try {
                String string = bl ? null : (Strings.isNullOrEmpty(this.argNames[i2]) ? "unnamed" + i2 : this.argNames[i2]);
                this.appendType(stringBuilder, this.argTypes[i2], string);
                continue;
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        return stringBuilder.append(")");
    }

    private StringBuilder appendType(StringBuilder stringBuilder, Type type, String string) {
        switch (type.getSort()) {
            case 9: {
                return SignaturePrinter.appendArraySuffix(this.appendType(stringBuilder, type.getElementType(), string), type);
            }
            case 10: {
                return this.appendType(stringBuilder, type.getClassName(), string);
            }
        }
        stringBuilder.append(SignaturePrinter.getTypeName(type, false, this.fullyQualified));
        if (string != null) {
            stringBuilder.append(' ').append(string);
        }
        return stringBuilder;
    }

    private StringBuilder appendType(StringBuilder stringBuilder, String string, String string2) {
        if (!this.fullyQualified) {
            string = string.substring(string.lastIndexOf(46) + 1);
        }
        stringBuilder.append(string);
        if (string.endsWith("CallbackInfoReturnable")) {
            stringBuilder.append('<').append(SignaturePrinter.getTypeName(this.returnType, true, this.fullyQualified)).append('>');
        }
        if (string2 != null) {
            stringBuilder.append(' ').append(string2);
        }
        return stringBuilder;
    }

    public static String getTypeName(Type type, boolean bl) {
        return SignaturePrinter.getTypeName(type, bl, false);
    }

    public static String getTypeName(Type type, boolean bl, boolean bl2) {
        switch (type.getSort()) {
            case 0: {
                return bl ? "Void" : "void";
            }
            case 1: {
                return bl ? "Boolean" : "boolean";
            }
            case 2: {
                return bl ? "Character" : "char";
            }
            case 3: {
                return bl ? "Byte" : "byte";
            }
            case 4: {
                return bl ? "Short" : "short";
            }
            case 5: {
                return bl ? "Integer" : "int";
            }
            case 6: {
                return bl ? "Float" : "float";
            }
            case 7: {
                return bl ? "Long" : "long";
            }
            case 8: {
                return bl ? "Double" : "double";
            }
            case 9: {
                return SignaturePrinter.getTypeName(type.getElementType(), bl, bl2) + SignaturePrinter.arraySuffix(type);
            }
            case 10: {
                String string = type.getClassName();
                if (!bl2) {
                    string = string.substring(string.lastIndexOf(46) + 1);
                }
                return string;
            }
        }
        return "Object";
    }

    private static String arraySuffix(Type type) {
        return Strings.repeat("[]", type.getDimensions());
    }

    private static StringBuilder appendArraySuffix(StringBuilder stringBuilder, Type type) {
        for (int i2 = 0; i2 < type.getDimensions(); ++i2) {
            stringBuilder.append("[]");
        }
        return stringBuilder;
    }
}

