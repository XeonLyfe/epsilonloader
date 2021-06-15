/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

public class TypeReference {
    public static final int CLASS_TYPE_PARAMETER = 0;
    public static final int METHOD_TYPE_PARAMETER = 1;
    public static final int CLASS_EXTENDS = 16;
    public static final int CLASS_TYPE_PARAMETER_BOUND = 17;
    public static final int METHOD_TYPE_PARAMETER_BOUND = 18;
    public static final int FIELD = 19;
    public static final int METHOD_RETURN = 20;
    public static final int METHOD_RECEIVER = 21;
    public static final int METHOD_FORMAL_PARAMETER = 22;
    public static final int THROWS = 23;
    public static final int LOCAL_VARIABLE = 64;
    public static final int RESOURCE_VARIABLE = 65;
    public static final int EXCEPTION_PARAMETER = 66;
    public static final int INSTANCEOF = 67;
    public static final int NEW = 68;
    public static final int CONSTRUCTOR_REFERENCE = 69;
    public static final int METHOD_REFERENCE = 70;
    public static final int CAST = 71;
    public static final int CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = 72;
    public static final int METHOD_INVOCATION_TYPE_ARGUMENT = 73;
    public static final int CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = 74;
    public static final int METHOD_REFERENCE_TYPE_ARGUMENT = 75;
    private int value;

    public TypeReference(int n2) {
        this.value = n2;
    }

    public static TypeReference newTypeReference(int n2) {
        return new TypeReference(n2 << 24);
    }

    public static TypeReference newTypeParameterReference(int n2, int n3) {
        return new TypeReference(n2 << 24 | n3 << 16);
    }

    public static TypeReference newTypeParameterBoundReference(int n2, int n3, int n4) {
        return new TypeReference(n2 << 24 | n3 << 16 | n4 << 8);
    }

    public static TypeReference newSuperTypeReference(int n2) {
        return new TypeReference(0x10000000 | (n2 &= 0xFFFF) << 8);
    }

    public static TypeReference newFormalParameterReference(int n2) {
        return new TypeReference(0x16000000 | n2 << 16);
    }

    public static TypeReference newExceptionReference(int n2) {
        return new TypeReference(0x17000000 | n2 << 8);
    }

    public static TypeReference newTryCatchReference(int n2) {
        return new TypeReference(0x42000000 | n2 << 8);
    }

    public static TypeReference newTypeArgumentReference(int n2, int n3) {
        return new TypeReference(n2 << 24 | n3);
    }

    public int getSort() {
        return this.value >>> 24;
    }

    public int getTypeParameterIndex() {
        return (this.value & 0xFF0000) >> 16;
    }

    public int getTypeParameterBoundIndex() {
        return (this.value & 0xFF00) >> 8;
    }

    public int getSuperTypeIndex() {
        return (short)((this.value & 0xFFFF00) >> 8);
    }

    public int getFormalParameterIndex() {
        return (this.value & 0xFF0000) >> 16;
    }

    public int getExceptionIndex() {
        return (this.value & 0xFFFF00) >> 8;
    }

    public int getTryCatchBlockIndex() {
        return (this.value & 0xFFFF00) >> 8;
    }

    public int getTypeArgumentIndex() {
        return this.value & 0xFF;
    }

    public int getValue() {
        return this.value;
    }
}

