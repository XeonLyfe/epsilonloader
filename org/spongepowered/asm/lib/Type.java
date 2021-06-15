/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Type {
    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    public static final Type VOID_TYPE = new Type(0, null, 0x56050000, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, null, 1509950721, 1);
    public static final Type CHAR_TYPE = new Type(2, null, 1124075009, 1);
    public static final Type BYTE_TYPE = new Type(3, null, 1107297537, 1);
    public static final Type SHORT_TYPE = new Type(4, null, 1392510721, 1);
    public static final Type INT_TYPE = new Type(5, null, 1224736769, 1);
    public static final Type FLOAT_TYPE = new Type(6, null, 1174536705, 1);
    public static final Type LONG_TYPE = new Type(7, null, 1241579778, 1);
    public static final Type DOUBLE_TYPE = new Type(8, null, 1141048066, 1);
    private final int sort;
    private final char[] buf;
    private final int off;
    private final int len;

    private Type(int n2, char[] arrc, int n3, int n4) {
        this.sort = n2;
        this.buf = arrc;
        this.off = n3;
        this.len = n4;
    }

    public static Type getType(String string) {
        return Type.getType(string.toCharArray(), 0);
    }

    public static Type getObjectType(String string) {
        char[] arrc = string.toCharArray();
        return new Type(arrc[0] == '[' ? 9 : 10, arrc, 0, arrc.length);
    }

    public static Type getMethodType(String string) {
        return Type.getType(string.toCharArray(), 0);
    }

    public static Type getMethodType(Type type, Type ... arrtype) {
        return Type.getType(Type.getMethodDescriptor(type, arrtype));
    }

    public static Type getType(Class<?> class_) {
        if (class_.isPrimitive()) {
            if (class_ == Integer.TYPE) {
                return INT_TYPE;
            }
            if (class_ == Void.TYPE) {
                return VOID_TYPE;
            }
            if (class_ == Boolean.TYPE) {
                return BOOLEAN_TYPE;
            }
            if (class_ == Byte.TYPE) {
                return BYTE_TYPE;
            }
            if (class_ == Character.TYPE) {
                return CHAR_TYPE;
            }
            if (class_ == Short.TYPE) {
                return SHORT_TYPE;
            }
            if (class_ == Double.TYPE) {
                return DOUBLE_TYPE;
            }
            if (class_ == Float.TYPE) {
                return FLOAT_TYPE;
            }
            return LONG_TYPE;
        }
        return Type.getType(Type.getDescriptor(class_));
    }

    public static Type getType(Constructor<?> constructor) {
        return Type.getType(Type.getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method) {
        return Type.getType(Type.getMethodDescriptor(method));
    }

    public static Type[] getArgumentTypes(String string) {
        char c2;
        char[] arrc = string.toCharArray();
        int n2 = 1;
        int n3 = 0;
        while ((c2 = arrc[n2++]) != ')') {
            if (c2 == 'L') {
                while (arrc[n2++] != ';') {
                }
                ++n3;
                continue;
            }
            if (c2 == '[') continue;
            ++n3;
        }
        Type[] arrtype = new Type[n3];
        n2 = 1;
        n3 = 0;
        while (arrc[n2] != ')') {
            arrtype[n3] = Type.getType(arrc, n2);
            n2 += arrtype[n3].len + (arrtype[n3].sort == 10 ? 2 : 0);
            ++n3;
        }
        return arrtype;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] arrclass = method.getParameterTypes();
        Type[] arrtype = new Type[arrclass.length];
        for (int i2 = arrclass.length - 1; i2 >= 0; --i2) {
            arrtype[i2] = Type.getType(arrclass[i2]);
        }
        return arrtype;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Type getReturnType(String var0) {
        var1_1 = var0.toCharArray();
        var2_2 = 1;
        block0: while (true) lbl-1000:
        // 3 sources

        {
            if ((var3_3 = var1_1[var2_2++]) == ')') {
                return Type.getType(var1_1, var2_2);
            }
            if (var3_3 != 'L') ** GOTO lbl-1000
            while (true) {
                if (var1_1[var2_2++] == ';') continue block0;
            }
            break;
        }
    }

    public static Type getReturnType(Method method) {
        return Type.getType(method.getReturnType());
    }

    public static int getArgumentsAndReturnSizes(String string) {
        int n2 = 1;
        int n3 = 1;
        while (true) {
            char c2;
            if ((c2 = string.charAt(n3++)) == ')') {
                c2 = string.charAt(n3);
                return n2 << 2 | (c2 == 'V' ? 0 : (c2 == 'D' || c2 == 'J' ? 2 : 1));
            }
            if (c2 == 'L') {
                while (string.charAt(n3++) != ';') {
                }
                ++n2;
                continue;
            }
            if (c2 == '[') {
                while ((c2 = string.charAt(n3)) == '[') {
                    ++n3;
                }
                if (c2 != 'D' && c2 != 'J') continue;
                --n2;
                continue;
            }
            if (c2 == 'D' || c2 == 'J') {
                n2 += 2;
                continue;
            }
            ++n2;
        }
    }

    private static Type getType(char[] arrc, int n2) {
        switch (arrc[n2]) {
            case 'V': {
                return VOID_TYPE;
            }
            case 'Z': {
                return BOOLEAN_TYPE;
            }
            case 'C': {
                return CHAR_TYPE;
            }
            case 'B': {
                return BYTE_TYPE;
            }
            case 'S': {
                return SHORT_TYPE;
            }
            case 'I': {
                return INT_TYPE;
            }
            case 'F': {
                return FLOAT_TYPE;
            }
            case 'J': {
                return LONG_TYPE;
            }
            case 'D': {
                return DOUBLE_TYPE;
            }
            case '[': {
                int n3 = 1;
                while (arrc[n2 + n3] == '[') {
                    ++n3;
                }
                if (arrc[n2 + n3] == 'L') {
                    ++n3;
                    while (arrc[n2 + n3] != ';') {
                        ++n3;
                    }
                }
                return new Type(9, arrc, n2, n3 + 1);
            }
            case 'L': {
                int n4 = 1;
                while (arrc[n2 + n4] != ';') {
                    ++n4;
                }
                return new Type(10, arrc, n2 + 1, n4 - 1);
            }
        }
        return new Type(11, arrc, n2, arrc.length - n2);
    }

    public int getSort() {
        return this.sort;
    }

    public int getDimensions() {
        int n2 = 1;
        while (this.buf[this.off + n2] == '[') {
            ++n2;
        }
        return n2;
    }

    public Type getElementType() {
        return Type.getType(this.buf, this.off + this.getDimensions());
    }

    public String getClassName() {
        switch (this.sort) {
            case 0: {
                return "void";
            }
            case 1: {
                return "boolean";
            }
            case 2: {
                return "char";
            }
            case 3: {
                return "byte";
            }
            case 4: {
                return "short";
            }
            case 5: {
                return "int";
            }
            case 6: {
                return "float";
            }
            case 7: {
                return "long";
            }
            case 8: {
                return "double";
            }
            case 9: {
                StringBuilder stringBuilder = new StringBuilder(this.getElementType().getClassName());
                for (int i2 = this.getDimensions(); i2 > 0; --i2) {
                    stringBuilder.append("[]");
                }
                return stringBuilder.toString();
            }
            case 10: {
                return new String(this.buf, this.off, this.len).replace('/', '.');
            }
        }
        return null;
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.getDescriptor());
    }

    public Type getReturnType() {
        return Type.getReturnType(this.getDescriptor());
    }

    public int getArgumentsAndReturnSizes() {
        return Type.getArgumentsAndReturnSizes(this.getDescriptor());
    }

    public String getDescriptor() {
        StringBuilder stringBuilder = new StringBuilder();
        this.getDescriptor(stringBuilder);
        return stringBuilder.toString();
    }

    public static String getMethodDescriptor(Type type, Type ... arrtype) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (int i2 = 0; i2 < arrtype.length; ++i2) {
            arrtype[i2].getDescriptor(stringBuilder);
        }
        stringBuilder.append(')');
        type.getDescriptor(stringBuilder);
        return stringBuilder.toString();
    }

    private void getDescriptor(StringBuilder stringBuilder) {
        if (this.buf == null) {
            stringBuilder.append((char)((this.off & 0xFF000000) >>> 24));
        } else if (this.sort == 10) {
            stringBuilder.append('L');
            stringBuilder.append(this.buf, this.off, this.len);
            stringBuilder.append(';');
        } else {
            stringBuilder.append(this.buf, this.off, this.len);
        }
    }

    public static String getInternalName(Class<?> class_) {
        return class_.getName().replace('.', '/');
    }

    public static String getDescriptor(Class<?> class_) {
        StringBuilder stringBuilder = new StringBuilder();
        Type.getDescriptor(stringBuilder, class_);
        return stringBuilder.toString();
    }

    public static String getConstructorDescriptor(Constructor<?> constructor) {
        Class<?>[] arrclass = constructor.getParameterTypes();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (int i2 = 0; i2 < arrclass.length; ++i2) {
            Type.getDescriptor(stringBuilder, arrclass[i2]);
        }
        return stringBuilder.append(")V").toString();
    }

    public static String getMethodDescriptor(Method method) {
        Class<?>[] arrclass = method.getParameterTypes();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (int i2 = 0; i2 < arrclass.length; ++i2) {
            Type.getDescriptor(stringBuilder, arrclass[i2]);
        }
        stringBuilder.append(')');
        Type.getDescriptor(stringBuilder, method.getReturnType());
        return stringBuilder.toString();
    }

    private static void getDescriptor(StringBuilder stringBuilder, Class<?> class_) {
        Class<?> class_2 = class_;
        while (true) {
            if (class_2.isPrimitive()) {
                int n2 = class_2 == Integer.TYPE ? 73 : (class_2 == Void.TYPE ? 86 : (class_2 == Boolean.TYPE ? 90 : (class_2 == Byte.TYPE ? 66 : (class_2 == Character.TYPE ? 67 : (class_2 == Short.TYPE ? 83 : (class_2 == Double.TYPE ? 68 : (class_2 == Float.TYPE ? 70 : 74)))))));
                stringBuilder.append((char)n2);
                return;
            }
            if (!class_2.isArray()) break;
            stringBuilder.append('[');
            class_2 = class_2.getComponentType();
        }
        stringBuilder.append('L');
        String string = class_2.getName();
        int n3 = string.length();
        for (int i2 = 0; i2 < n3; ++i2) {
            char c2 = string.charAt(i2);
            stringBuilder.append(c2 == '.' ? (char)'/' : (char)c2);
        }
        stringBuilder.append(';');
    }

    public int getSize() {
        return this.buf == null ? this.off & 0xFF : 1;
    }

    public int getOpcode(int n2) {
        if (n2 == 46 || n2 == 79) {
            return n2 + (this.buf == null ? (this.off & 0xFF00) >> 8 : 4);
        }
        return n2 + (this.buf == null ? (this.off & 0xFF0000) >> 16 : 4);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Type)) {
            return false;
        }
        Type type = (Type)object;
        if (this.sort != type.sort) {
            return false;
        }
        if (this.sort >= 9) {
            if (this.len != type.len) {
                return false;
            }
            int n2 = this.off;
            int n3 = type.off;
            int n4 = n2 + this.len;
            while (n2 < n4) {
                if (this.buf[n2] != type.buf[n3]) {
                    return false;
                }
                ++n2;
                ++n3;
            }
        }
        return true;
    }

    public int hashCode() {
        int n2 = 13 * this.sort;
        if (this.sort >= 9) {
            int n3;
            int n4 = n3 + this.len;
            for (n3 = this.off; n3 < n4; ++n3) {
                n2 = 17 * (n2 + this.buf[n3]);
            }
        }
        return n2;
    }

    public String toString() {
        return this.getDescriptor();
    }
}

