/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.commons.SignatureRemapper;
import org.spongepowered.asm.lib.signature.SignatureReader;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import org.spongepowered.asm.lib.signature.SignatureWriter;

public abstract class Remapper {
    public String mapDesc(String string) {
        Type type = Type.getType(string);
        switch (type.getSort()) {
            case 9: {
                String string2 = this.mapDesc(type.getElementType().getDescriptor());
                for (int i2 = 0; i2 < type.getDimensions(); ++i2) {
                    string2 = '[' + string2;
                }
                return string2;
            }
            case 10: {
                String string3 = this.map(type.getInternalName());
                if (string3 == null) break;
                return 'L' + string3 + ';';
            }
        }
        return string;
    }

    private Type mapType(Type type) {
        switch (type.getSort()) {
            case 9: {
                String string = this.mapDesc(type.getElementType().getDescriptor());
                for (int i2 = 0; i2 < type.getDimensions(); ++i2) {
                    string = '[' + string;
                }
                return Type.getType(string);
            }
            case 10: {
                String string = this.map(type.getInternalName());
                return string != null ? Type.getObjectType(string) : type;
            }
            case 11: {
                return Type.getMethodType(this.mapMethodDesc(type.getDescriptor()));
            }
        }
        return type;
    }

    public String mapType(String string) {
        if (string == null) {
            return null;
        }
        return this.mapType(Type.getObjectType(string)).getInternalName();
    }

    public String[] mapTypes(String[] arrstring) {
        String[] arrstring2 = null;
        boolean bl = false;
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            String string = arrstring[i2];
            String string2 = this.map(string);
            if (string2 != null && arrstring2 == null) {
                arrstring2 = new String[arrstring.length];
                if (i2 > 0) {
                    System.arraycopy(arrstring, 0, arrstring2, 0, i2);
                }
                bl = true;
            }
            if (!bl) continue;
            arrstring2[i2] = string2 == null ? string : string2;
        }
        return bl ? arrstring2 : arrstring;
    }

    public String mapMethodDesc(String string) {
        if ("()V".equals(string)) {
            return string;
        }
        Type[] arrtype = Type.getArgumentTypes(string);
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i2 = 0; i2 < arrtype.length; ++i2) {
            stringBuilder.append(this.mapDesc(arrtype[i2].getDescriptor()));
        }
        Type type = Type.getReturnType(string);
        if (type == Type.VOID_TYPE) {
            stringBuilder.append(")V");
            return stringBuilder.toString();
        }
        stringBuilder.append(')').append(this.mapDesc(type.getDescriptor()));
        return stringBuilder.toString();
    }

    public Object mapValue(Object object) {
        if (object instanceof Type) {
            return this.mapType((Type)object);
        }
        if (object instanceof Handle) {
            Handle handle = (Handle)object;
            return new Handle(handle.getTag(), this.mapType(handle.getOwner()), this.mapMethodName(handle.getOwner(), handle.getName(), handle.getDesc()), this.mapMethodDesc(handle.getDesc()), handle.isInterface());
        }
        return object;
    }

    public String mapSignature(String string, boolean bl) {
        if (string == null) {
            return null;
        }
        SignatureReader signatureReader = new SignatureReader(string);
        SignatureWriter signatureWriter = new SignatureWriter();
        SignatureVisitor signatureVisitor = this.createSignatureRemapper(signatureWriter);
        if (bl) {
            signatureReader.acceptType(signatureVisitor);
        } else {
            signatureReader.accept(signatureVisitor);
        }
        return signatureWriter.toString();
    }

    @Deprecated
    protected SignatureVisitor createRemappingSignatureAdapter(SignatureVisitor signatureVisitor) {
        return new SignatureRemapper(signatureVisitor, this);
    }

    protected SignatureVisitor createSignatureRemapper(SignatureVisitor signatureVisitor) {
        return this.createRemappingSignatureAdapter(signatureVisitor);
    }

    public String mapMethodName(String string, String string2, String string3) {
        return string2;
    }

    public String mapInvokeDynamicMethodName(String string, String string2) {
        return string;
    }

    public String mapFieldName(String string, String string2, String string3) {
        return string2;
    }

    public String map(String string) {
        return string;
    }
}

