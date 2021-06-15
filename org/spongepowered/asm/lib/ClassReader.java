/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import java.io.IOException;
import java.io.InputStream;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.Context;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Item;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.MethodWriter;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;

public class ClassReader {
    static final boolean SIGNATURES = true;
    static final boolean ANNOTATIONS = true;
    static final boolean FRAMES = true;
    static final boolean WRITER = true;
    static final boolean RESIZE = true;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    static final int EXPAND_ASM_INSNS = 256;
    public final byte[] b;
    private final int[] items;
    private final String[] strings;
    private final int maxStringLength;
    public final int header;

    public ClassReader(byte[] arrby) {
        this(arrby, 0, arrby.length);
    }

    public ClassReader(byte[] arrby, int n2, int n3) {
        this.b = arrby;
        if (this.readShort(n2 + 6) > 52) {
            throw new IllegalArgumentException();
        }
        this.items = new int[this.readUnsignedShort(n2 + 8)];
        int n4 = this.items.length;
        this.strings = new String[n4];
        int n5 = 0;
        int n6 = n2 + 10;
        for (int i2 = 1; i2 < n4; ++i2) {
            int n7;
            this.items[i2] = n6 + 1;
            switch (arrby[n6]) {
                case 3: 
                case 4: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 18: {
                    n7 = 5;
                    break;
                }
                case 5: 
                case 6: {
                    n7 = 9;
                    ++i2;
                    break;
                }
                case 1: {
                    n7 = 3 + this.readUnsignedShort(n6 + 1);
                    if (n7 <= n5) break;
                    n5 = n7;
                    break;
                }
                case 15: {
                    n7 = 4;
                    break;
                }
                default: {
                    n7 = 3;
                }
            }
            n6 += n7;
        }
        this.maxStringLength = n5;
        this.header = n6;
    }

    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }

    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public String getSuperName() {
        return this.readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public String[] getInterfaces() {
        int n2 = this.header + 6;
        int n3 = this.readUnsignedShort(n2);
        String[] arrstring = new String[n3];
        if (n3 > 0) {
            char[] arrc = new char[this.maxStringLength];
            for (int i2 = 0; i2 < n3; ++i2) {
                arrstring[i2] = this.readClass(n2 += 2, arrc);
            }
        }
        return arrstring;
    }

    void copyPool(ClassWriter classWriter) {
        int n2;
        char[] arrc = new char[this.maxStringLength];
        int n3 = this.items.length;
        Item[] arritem = new Item[n3];
        for (n2 = 1; n2 < n3; ++n2) {
            int n4;
            int n5 = this.items[n2];
            byte by = this.b[n5 - 1];
            Item item = new Item(n2);
            switch (by) {
                case 9: 
                case 10: 
                case 11: {
                    int n6 = this.items[this.readUnsignedShort(n5 + 2)];
                    item.set(by, this.readClass(n5, arrc), this.readUTF8(n6, arrc), this.readUTF8(n6 + 2, arrc));
                    break;
                }
                case 3: {
                    item.set(this.readInt(n5));
                    break;
                }
                case 4: {
                    item.set(Float.intBitsToFloat(this.readInt(n5)));
                    break;
                }
                case 12: {
                    item.set(by, this.readUTF8(n5, arrc), this.readUTF8(n5 + 2, arrc), null);
                    break;
                }
                case 5: {
                    item.set(this.readLong(n5));
                    ++n2;
                    break;
                }
                case 6: {
                    item.set(Double.longBitsToDouble(this.readLong(n5)));
                    ++n2;
                    break;
                }
                case 1: {
                    String string = this.strings[n2];
                    if (string == null) {
                        n5 = this.items[n2];
                        string = this.strings[n2] = this.readUTF(n5 + 2, this.readUnsignedShort(n5), arrc);
                    }
                    item.set(by, string, null, null);
                    break;
                }
                case 15: {
                    n4 = this.items[this.readUnsignedShort(n5 + 1)];
                    int n6 = this.items[this.readUnsignedShort(n4 + 2)];
                    item.set(20 + this.readByte(n5), this.readClass(n4, arrc), this.readUTF8(n6, arrc), this.readUTF8(n6 + 2, arrc));
                    break;
                }
                case 18: {
                    if (classWriter.bootstrapMethods == null) {
                        this.copyBootstrapMethods(classWriter, arritem, arrc);
                    }
                    int n6 = this.items[this.readUnsignedShort(n5 + 2)];
                    item.set(this.readUTF8(n6, arrc), this.readUTF8(n6 + 2, arrc), this.readUnsignedShort(n5));
                    break;
                }
                default: {
                    item.set(by, this.readUTF8(n5, arrc), null, null);
                }
            }
            n4 = item.hashCode % arritem.length;
            item.next = arritem[n4];
            arritem[n4] = item;
        }
        n2 = this.items[1] - 1;
        classWriter.pool.putByteArray(this.b, n2, this.header - n2);
        classWriter.items = arritem;
        classWriter.threshold = (int)(0.75 * (double)n3);
        classWriter.index = n3;
    }

    private void copyBootstrapMethods(ClassWriter classWriter, Item[] arritem, char[] arrc) {
        int n2;
        int n3;
        int n4 = this.getAttributes();
        boolean bl = false;
        for (n3 = this.readUnsignedShort(n4); n3 > 0; --n3) {
            String string = this.readUTF8(n4 + 2, arrc);
            if ("BootstrapMethods".equals(string)) {
                bl = true;
                break;
            }
            n4 += 6 + this.readInt(n4 + 4);
        }
        if (!bl) {
            return;
        }
        n3 = this.readUnsignedShort(n4 + 8);
        int n5 = n4 + 10;
        for (n2 = 0; n2 < n3; ++n2) {
            int n6 = n5 - n4 - 10;
            int n7 = this.readConst(this.readUnsignedShort(n5), arrc).hashCode();
            for (int i2 = this.readUnsignedShort(n5 + 2); i2 > 0; --i2) {
                n7 ^= this.readConst(this.readUnsignedShort(n5 + 4), arrc).hashCode();
                n5 += 2;
            }
            n5 += 4;
            Item item = new Item(n2);
            item.set(n6, n7 & Integer.MAX_VALUE);
            int n8 = item.hashCode % arritem.length;
            item.next = arritem[n8];
            arritem[n8] = item;
        }
        n2 = this.readInt(n4 + 4);
        ByteVector byteVector = new ByteVector(n2 + 62);
        byteVector.putByteArray(this.b, n4 + 10, n2 - 2);
        classWriter.bootstrapMethodsCount = n3;
        classWriter.bootstrapMethods = byteVector;
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(ClassReader.readClass(inputStream, false));
    }

    public ClassReader(String string) throws IOException {
        this(ClassReader.readClass(ClassLoader.getSystemResourceAsStream(string.replace('.', '/') + ".class"), true));
    }

    private static byte[] readClass(InputStream inputStream, boolean bl) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        try {
            byte[] arrby = new byte[inputStream.available()];
            int n2 = 0;
            while (true) {
                byte[] arrby2;
                int n3;
                if ((n3 = inputStream.read(arrby, n2, arrby.length - n2)) == -1) {
                    byte[] arrby3;
                    if (n2 < arrby.length) {
                        arrby3 = new byte[n2];
                        System.arraycopy(arrby, 0, arrby3, 0, n2);
                        arrby = arrby3;
                    }
                    arrby3 = arrby;
                    return arrby3;
                }
                if ((n2 += n3) != arrby.length) continue;
                int n4 = inputStream.read();
                if (n4 < 0) {
                    arrby2 = arrby;
                    return arrby2;
                }
                arrby2 = new byte[arrby.length + 1000];
                System.arraycopy(arrby, 0, arrby2, 0, n2);
                arrby2[n2++] = (byte)n4;
                arrby = arrby2;
            }
        }
        finally {
            if (bl) {
                inputStream.close();
            }
        }
    }

    public void accept(ClassVisitor classVisitor, int n2) {
        this.accept(classVisitor, new Attribute[0], n2);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] arrattribute, int n2) {
        int n3;
        int n4 = this.header;
        char[] arrc = new char[this.maxStringLength];
        Context context = new Context();
        context.attrs = arrattribute;
        context.flags = n2;
        context.buffer = arrc;
        int n5 = this.readUnsignedShort(n4);
        String string = this.readClass(n4 + 2, arrc);
        String string2 = this.readClass(n4 + 4, arrc);
        String[] arrstring = new String[this.readUnsignedShort(n4 + 6)];
        n4 += 8;
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            arrstring[i2] = this.readClass(n4, arrc);
            n4 += 2;
        }
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        String string7 = null;
        String string8 = null;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        Attribute attribute = null;
        n4 = this.getAttributes();
        for (n3 = this.readUnsignedShort(n4); n3 > 0; --n3) {
            String string9 = this.readUTF8(n4 + 2, arrc);
            if ("SourceFile".equals(string9)) {
                string4 = this.readUTF8(n4 + 8, arrc);
            } else if ("InnerClasses".equals(string9)) {
                n10 = n4 + 8;
            } else if ("EnclosingMethod".equals(string9)) {
                string6 = this.readClass(n4 + 8, arrc);
                int n11 = this.readUnsignedShort(n4 + 10);
                if (n11 != 0) {
                    string7 = this.readUTF8(this.items[n11], arrc);
                    string8 = this.readUTF8(this.items[n11] + 2, arrc);
                }
            } else if ("Signature".equals(string9)) {
                string3 = this.readUTF8(n4 + 8, arrc);
            } else if ("RuntimeVisibleAnnotations".equals(string9)) {
                n6 = n4 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(string9)) {
                n8 = n4 + 8;
            } else if ("Deprecated".equals(string9)) {
                n5 |= 0x20000;
            } else if ("Synthetic".equals(string9)) {
                n5 |= 0x41000;
            } else if ("SourceDebugExtension".equals(string9)) {
                int n12 = this.readInt(n4 + 4);
                string5 = this.readUTF(n4 + 8, n12, new char[n12]);
            } else if ("RuntimeInvisibleAnnotations".equals(string9)) {
                n7 = n4 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string9)) {
                n9 = n4 + 8;
            } else if ("BootstrapMethods".equals(string9)) {
                int[] arrn = new int[this.readUnsignedShort(n4 + 8)];
                int n13 = n4 + 10;
                for (int i3 = 0; i3 < arrn.length; ++i3) {
                    arrn[i3] = n13;
                    n13 += 2 + this.readUnsignedShort(n13 + 2) << 1;
                }
                context.bootstrapMethods = arrn;
            } else {
                Attribute attribute2 = this.readAttribute(arrattribute, string9, n4 + 8, this.readInt(n4 + 4), arrc, -1, null);
                if (attribute2 != null) {
                    attribute2.next = attribute;
                    attribute = attribute2;
                }
            }
            n4 += 6 + this.readInt(n4 + 4);
        }
        classVisitor.visit(this.readInt(this.items[1] - 7), n5, string, string3, string2, arrstring);
        if ((n2 & 2) == 0 && (string4 != null || string5 != null)) {
            classVisitor.visitSource(string4, string5);
        }
        if (string6 != null) {
            classVisitor.visitOuterClass(string6, string7, string8);
        }
        if (n6 != 0) {
            int n14 = n6 + 2;
            for (n3 = this.readUnsignedShort(n6); n3 > 0; --n3) {
                n14 = this.readAnnotationValues(n14 + 2, arrc, true, classVisitor.visitAnnotation(this.readUTF8(n14, arrc), true));
            }
        }
        if (n7 != 0) {
            int n15 = n7 + 2;
            for (n3 = this.readUnsignedShort(n7); n3 > 0; --n3) {
                n15 = this.readAnnotationValues(n15 + 2, arrc, true, classVisitor.visitAnnotation(this.readUTF8(n15, arrc), false));
            }
        }
        if (n8 != 0) {
            int n16 = n8 + 2;
            for (n3 = this.readUnsignedShort(n8); n3 > 0; --n3) {
                n16 = this.readAnnotationTarget(context, n16);
                n16 = this.readAnnotationValues(n16 + 2, arrc, true, classVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n16, arrc), true));
            }
        }
        if (n9 != 0) {
            int n17 = n9 + 2;
            for (n3 = this.readUnsignedShort(n9); n3 > 0; --n3) {
                n17 = this.readAnnotationTarget(context, n17);
                n17 = this.readAnnotationValues(n17 + 2, arrc, true, classVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n17, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.next;
            attribute.next = null;
            classVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        if (n10 != 0) {
            n3 = n10 + 2;
            for (int i4 = this.readUnsignedShort(n10); i4 > 0; --i4) {
                classVisitor.visitInnerClass(this.readClass(n3, arrc), this.readClass(n3 + 2, arrc), this.readUTF8(n3 + 4, arrc), this.readUnsignedShort(n3 + 6));
                n3 += 8;
            }
        }
        n4 = this.header + 10 + 2 * arrstring.length;
        for (n3 = this.readUnsignedShort(n4 - 2); n3 > 0; --n3) {
            n4 = this.readField(classVisitor, context, n4);
        }
        for (n3 = this.readUnsignedShort((n4 += 2) - 2); n3 > 0; --n3) {
            n4 = this.readMethod(classVisitor, context, n4);
        }
        classVisitor.visitEnd();
    }

    private int readField(ClassVisitor classVisitor, Context context, int n2) {
        int n3;
        char[] arrc = context.buffer;
        int n4 = this.readUnsignedShort(n2);
        String string = this.readUTF8(n2 + 2, arrc);
        String string2 = this.readUTF8(n2 + 4, arrc);
        String string3 = null;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        Object object = null;
        Attribute attribute = null;
        for (int i2 = this.readUnsignedShort(n2 += 6); i2 > 0; --i2) {
            String string4 = this.readUTF8(n2 + 2, arrc);
            if ("ConstantValue".equals(string4)) {
                n3 = this.readUnsignedShort(n2 + 8);
                object = n3 == 0 ? null : this.readConst(n3, arrc);
            } else if ("Signature".equals(string4)) {
                string3 = this.readUTF8(n2 + 8, arrc);
            } else if ("Deprecated".equals(string4)) {
                n4 |= 0x20000;
            } else if ("Synthetic".equals(string4)) {
                n4 |= 0x41000;
            } else if ("RuntimeVisibleAnnotations".equals(string4)) {
                n5 = n2 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(string4)) {
                n7 = n2 + 8;
            } else if ("RuntimeInvisibleAnnotations".equals(string4)) {
                n6 = n2 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string4)) {
                n8 = n2 + 8;
            } else {
                Attribute attribute2 = this.readAttribute(context.attrs, string4, n2 + 8, this.readInt(n2 + 4), arrc, -1, null);
                if (attribute2 != null) {
                    attribute2.next = attribute;
                    attribute = attribute2;
                }
            }
            n2 += 6 + this.readInt(n2 + 4);
        }
        n2 += 2;
        FieldVisitor fieldVisitor = classVisitor.visitField(n4, string, string2, string3, object);
        if (fieldVisitor == null) {
            return n2;
        }
        if (n5 != 0) {
            n3 = n5 + 2;
            for (int i3 = this.readUnsignedShort(n5); i3 > 0; --i3) {
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, fieldVisitor.visitAnnotation(this.readUTF8(n3, arrc), true));
            }
        }
        if (n6 != 0) {
            n3 = n6 + 2;
            for (int i4 = this.readUnsignedShort(n6); i4 > 0; --i4) {
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, fieldVisitor.visitAnnotation(this.readUTF8(n3, arrc), false));
            }
        }
        if (n7 != 0) {
            n3 = n7 + 2;
            for (int i5 = this.readUnsignedShort(n7); i5 > 0; --i5) {
                n3 = this.readAnnotationTarget(context, n3);
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, fieldVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n3, arrc), true));
            }
        }
        if (n8 != 0) {
            n3 = n8 + 2;
            for (int i6 = this.readUnsignedShort(n8); i6 > 0; --i6) {
                n3 = this.readAnnotationTarget(context, n3);
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, fieldVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n3, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.next;
            attribute.next = null;
            fieldVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        fieldVisitor.visitEnd();
        return n2;
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int n2) {
        int n3;
        Object object;
        char[] arrc = context.buffer;
        context.access = this.readUnsignedShort(n2);
        context.name = this.readUTF8(n2 + 2, arrc);
        context.desc = this.readUTF8(n2 + 4, arrc);
        int n4 = 0;
        int n5 = 0;
        String[] arrstring = null;
        String string = null;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int n14 = n2 += 6;
        Attribute attribute = null;
        for (int i2 = this.readUnsignedShort(n2); i2 > 0; --i2) {
            object = this.readUTF8(n2 + 2, arrc);
            if ("Code".equals(object)) {
                if ((context.flags & 1) == 0) {
                    n4 = n2 + 8;
                }
            } else if ("Exceptions".equals(object)) {
                arrstring = new String[this.readUnsignedShort(n2 + 8)];
                n5 = n2 + 10;
                for (n3 = 0; n3 < arrstring.length; ++n3) {
                    arrstring[n3] = this.readClass(n5, arrc);
                    n5 += 2;
                }
            } else if ("Signature".equals(object)) {
                string = this.readUTF8(n2 + 8, arrc);
            } else if ("Deprecated".equals(object)) {
                context.access |= 0x20000;
            } else if ("RuntimeVisibleAnnotations".equals(object)) {
                n7 = n2 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(object)) {
                n9 = n2 + 8;
            } else if ("AnnotationDefault".equals(object)) {
                n11 = n2 + 8;
            } else if ("Synthetic".equals(object)) {
                context.access |= 0x41000;
            } else if ("RuntimeInvisibleAnnotations".equals(object)) {
                n8 = n2 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(object)) {
                n10 = n2 + 8;
            } else if ("RuntimeVisibleParameterAnnotations".equals(object)) {
                n12 = n2 + 8;
            } else if ("RuntimeInvisibleParameterAnnotations".equals(object)) {
                n13 = n2 + 8;
            } else if ("MethodParameters".equals(object)) {
                n6 = n2 + 8;
            } else {
                Attribute attribute2 = this.readAttribute(context.attrs, (String)object, n2 + 8, this.readInt(n2 + 4), arrc, -1, null);
                if (attribute2 != null) {
                    attribute2.next = attribute;
                    attribute = attribute2;
                }
            }
            n2 += 6 + this.readInt(n2 + 4);
        }
        n2 += 2;
        MethodVisitor methodVisitor = classVisitor.visitMethod(context.access, context.name, context.desc, string, arrstring);
        if (methodVisitor == null) {
            return n2;
        }
        if (methodVisitor instanceof MethodWriter) {
            object = (MethodWriter)methodVisitor;
            if (object.cw.cr == this && string == ((MethodWriter)object).signature) {
                n3 = 0;
                if (arrstring == null) {
                    n3 = ((MethodWriter)object).exceptionCount == 0 ? 1 : 0;
                } else if (arrstring.length == ((MethodWriter)object).exceptionCount) {
                    n3 = 1;
                    for (int i3 = arrstring.length - 1; i3 >= 0; --i3) {
                        if (((MethodWriter)object).exceptions[i3] == this.readUnsignedShort(n5 -= 2)) continue;
                        n3 = 0;
                        break;
                    }
                }
                if (n3 != 0) {
                    ((MethodWriter)object).classReaderOffset = n14;
                    ((MethodWriter)object).classReaderLength = n2 - n14;
                    return n2;
                }
            }
        }
        if (n6 != 0) {
            int n15 = this.b[n6] & 0xFF;
            n3 = n6 + 1;
            while (n15 > 0) {
                methodVisitor.visitParameter(this.readUTF8(n3, arrc), this.readUnsignedShort(n3 + 2));
                --n15;
                n3 += 4;
            }
        }
        if (n11 != 0) {
            AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
            this.readAnnotationValue(n11, arrc, null, annotationVisitor);
            if (annotationVisitor != null) {
                annotationVisitor.visitEnd();
            }
        }
        if (n7 != 0) {
            n3 = n7 + 2;
            for (int i4 = this.readUnsignedShort(n7); i4 > 0; --i4) {
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, methodVisitor.visitAnnotation(this.readUTF8(n3, arrc), true));
            }
        }
        if (n8 != 0) {
            n3 = n8 + 2;
            for (int i5 = this.readUnsignedShort(n8); i5 > 0; --i5) {
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, methodVisitor.visitAnnotation(this.readUTF8(n3, arrc), false));
            }
        }
        if (n9 != 0) {
            n3 = n9 + 2;
            for (int i6 = this.readUnsignedShort(n9); i6 > 0; --i6) {
                n3 = this.readAnnotationTarget(context, n3);
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, methodVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n3, arrc), true));
            }
        }
        if (n10 != 0) {
            n3 = n10 + 2;
            for (int i7 = this.readUnsignedShort(n10); i7 > 0; --i7) {
                n3 = this.readAnnotationTarget(context, n3);
                n3 = this.readAnnotationValues(n3 + 2, arrc, true, methodVisitor.visitTypeAnnotation(context.typeRef, context.typePath, this.readUTF8(n3, arrc), false));
            }
        }
        if (n12 != 0) {
            this.readParameterAnnotations(methodVisitor, context, n12, true);
        }
        if (n13 != 0) {
            this.readParameterAnnotations(methodVisitor, context, n13, false);
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.next;
            attribute.next = null;
            methodVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        if (n4 != 0) {
            methodVisitor.visitCode();
            this.readCode(methodVisitor, context, n4);
        }
        methodVisitor.visitEnd();
        return n2;
    }

    private void readCode(MethodVisitor methodVisitor, Context context, int n2) {
        int n3;
        int n4;
        int n5;
        byte[] arrby = this.b;
        char[] arrc = context.buffer;
        int n6 = this.readUnsignedShort(n2);
        int n7 = this.readUnsignedShort(n2 + 2);
        int n8 = this.readInt(n2 + 4);
        int n9 = n2 += 8;
        int n10 = n2 + n8;
        context.labels = new Label[n8 + 2];
        Label[] arrlabel = context.labels;
        this.readLabel(n8 + 1, arrlabel);
        block31: while (n2 < n10) {
            n5 = n2 - n9;
            int n11 = arrby[n2] & 0xFF;
            switch (ClassWriter.TYPE[n11]) {
                case 0: 
                case 4: {
                    ++n2;
                    continue block31;
                }
                case 9: {
                    this.readLabel(n5 + this.readShort(n2 + 1), arrlabel);
                    n2 += 3;
                    continue block31;
                }
                case 18: {
                    this.readLabel(n5 + this.readUnsignedShort(n2 + 1), arrlabel);
                    n2 += 3;
                    continue block31;
                }
                case 10: {
                    this.readLabel(n5 + this.readInt(n2 + 1), arrlabel);
                    n2 += 5;
                    continue block31;
                }
                case 17: {
                    n11 = arrby[n2 + 1] & 0xFF;
                    if (n11 == 132) {
                        n2 += 6;
                        continue block31;
                    }
                    n2 += 4;
                    continue block31;
                }
                case 14: {
                    int n12;
                    n2 = n2 + 4 - (n5 & 3);
                    this.readLabel(n5 + this.readInt(n2), arrlabel);
                    for (n12 = this.readInt(n2 + 8) - this.readInt(n2 + 4) + 1; n12 > 0; --n12) {
                        this.readLabel(n5 + this.readInt(n2 + 12), arrlabel);
                        n2 += 4;
                    }
                    n2 += 12;
                    continue block31;
                }
                case 15: {
                    int n12;
                    n2 = n2 + 4 - (n5 & 3);
                    this.readLabel(n5 + this.readInt(n2), arrlabel);
                    for (n12 = this.readInt(n2 + 4); n12 > 0; --n12) {
                        this.readLabel(n5 + this.readInt(n2 + 12), arrlabel);
                        n2 += 8;
                    }
                    n2 += 8;
                    continue block31;
                }
                case 1: 
                case 3: 
                case 11: {
                    n2 += 2;
                    continue block31;
                }
                case 2: 
                case 5: 
                case 6: 
                case 12: 
                case 13: {
                    n2 += 3;
                    continue block31;
                }
                case 7: 
                case 8: {
                    n2 += 5;
                    continue block31;
                }
            }
            n2 += 4;
        }
        for (n5 = this.readUnsignedShort(n2); n5 > 0; --n5) {
            Label label = this.readLabel(this.readUnsignedShort(n2 + 2), arrlabel);
            Label label2 = this.readLabel(this.readUnsignedShort(n2 + 4), arrlabel);
            Label label3 = this.readLabel(this.readUnsignedShort(n2 + 6), arrlabel);
            String string = this.readUTF8(this.items[this.readUnsignedShort(n2 + 8)], arrc);
            methodVisitor.visitTryCatchBlock(label, label2, label3, string);
            n2 += 8;
        }
        int[] arrn = null;
        int[] arrn2 = null;
        int n13 = 0;
        int n14 = 0;
        int n15 = -1;
        int n16 = -1;
        int n17 = 0;
        int n18 = 0;
        boolean bl = true;
        boolean bl2 = (context.flags & 8) != 0;
        int n19 = 0;
        int n20 = 0;
        int n21 = 0;
        Context context2 = null;
        Attribute attribute = null;
        for (n4 = this.readUnsignedShort(n2 += 2); n4 > 0; --n4) {
            int n22;
            int n23;
            String string = this.readUTF8(n2 + 2, arrc);
            if ("LocalVariableTable".equals(string)) {
                if ((context.flags & 2) == 0) {
                    n17 = n2 + 8;
                    n3 = n2;
                    for (n23 = this.readUnsignedShort(n2 + 8); n23 > 0; --n23) {
                        n22 = this.readUnsignedShort(n3 + 10);
                        if (arrlabel[n22] == null) {
                            this.readLabel((int)n22, (Label[])arrlabel).status |= 1;
                        }
                        if (arrlabel[n22 += this.readUnsignedShort(n3 + 12)] == null) {
                            this.readLabel((int)n22, (Label[])arrlabel).status |= 1;
                        }
                        n3 += 10;
                    }
                }
            } else if ("LocalVariableTypeTable".equals(string)) {
                n18 = n2 + 8;
            } else if ("LineNumberTable".equals(string)) {
                if ((context.flags & 2) == 0) {
                    n3 = n2;
                    for (n23 = this.readUnsignedShort(n2 + 8); n23 > 0; --n23) {
                        n22 = this.readUnsignedShort(n3 + 10);
                        if (arrlabel[n22] == null) {
                            this.readLabel((int)n22, (Label[])arrlabel).status |= 1;
                        }
                        Label label = arrlabel[n22];
                        while (label.line > 0) {
                            if (label.next == null) {
                                label.next = new Label();
                            }
                            label = label.next;
                        }
                        label.line = this.readUnsignedShort(n3 + 12);
                        n3 += 4;
                    }
                }
            } else if ("RuntimeVisibleTypeAnnotations".equals(string)) {
                arrn = this.readTypeAnnotations(methodVisitor, context, n2 + 8, true);
                n15 = arrn.length == 0 || this.readByte(arrn[0]) < 67 ? -1 : this.readUnsignedShort(arrn[0] + 1);
            } else if ("RuntimeInvisibleTypeAnnotations".equals(string)) {
                arrn2 = this.readTypeAnnotations(methodVisitor, context, n2 + 8, false);
                n16 = arrn2.length == 0 || this.readByte(arrn2[0]) < 67 ? -1 : this.readUnsignedShort(arrn2[0] + 1);
            } else if ("StackMapTable".equals(string)) {
                if ((context.flags & 4) == 0) {
                    n19 = n2 + 10;
                    n20 = this.readInt(n2 + 4);
                    n21 = this.readUnsignedShort(n2 + 8);
                }
            } else if ("StackMap".equals(string)) {
                if ((context.flags & 4) == 0) {
                    bl = false;
                    n19 = n2 + 10;
                    n20 = this.readInt(n2 + 4);
                    n21 = this.readUnsignedShort(n2 + 8);
                }
            } else {
                for (n23 = 0; n23 < context.attrs.length; ++n23) {
                    Attribute attribute2;
                    if (!context.attrs[n23].type.equals(string) || (attribute2 = context.attrs[n23].read(this, n2 + 8, this.readInt(n2 + 4), arrc, n9 - 8, arrlabel)) == null) continue;
                    attribute2.next = attribute;
                    attribute = attribute2;
                }
            }
            n2 += 6 + this.readInt(n2 + 4);
        }
        n2 += 2;
        if (n19 != 0) {
            context2 = context;
            context2.offset = -1;
            context2.mode = 0;
            context2.localCount = 0;
            context2.localDiff = 0;
            context2.stackCount = 0;
            context2.local = new Object[n7];
            context2.stack = new Object[n6];
            if (bl2) {
                this.getImplicitFrame(context);
            }
            for (n4 = n19; n4 < n19 + n20 - 2; ++n4) {
                int n24;
                if (arrby[n4] != 8 || (n24 = this.readUnsignedShort(n4 + 1)) < 0 || n24 >= n8 || (arrby[n9 + n24] & 0xFF) != 187) continue;
                this.readLabel(n24, arrlabel);
            }
        }
        if ((context.flags & 0x100) != 0) {
            methodVisitor.visitFrame(-1, n7, null, 0, null);
        }
        n4 = (context.flags & 0x100) == 0 ? -33 : 0;
        n2 = n9;
        while (n2 < n10) {
            int n25 = n2 - n9;
            Label label = arrlabel[n25];
            if (label != null) {
                Label label4 = label.next;
                label.next = null;
                methodVisitor.visitLabel(label);
                if ((context.flags & 2) == 0 && label.line > 0) {
                    methodVisitor.visitLineNumber(label.line, label);
                    while (label4 != null) {
                        methodVisitor.visitLineNumber(label4.line, label);
                        label4 = label4.next;
                    }
                }
            }
            while (context2 != null && (context2.offset == n25 || context2.offset == -1)) {
                if (context2.offset != -1) {
                    if (!bl || bl2) {
                        methodVisitor.visitFrame(-1, context2.localCount, context2.local, context2.stackCount, context2.stack);
                    } else {
                        methodVisitor.visitFrame(context2.mode, context2.localDiff, context2.local, context2.stackCount, context2.stack);
                    }
                }
                if (n21 > 0) {
                    n19 = this.readFrame(n19, bl, bl2, context2);
                    --n21;
                    continue;
                }
                context2 = null;
            }
            int n26 = arrby[n2] & 0xFF;
            switch (ClassWriter.TYPE[n26]) {
                case 0: {
                    methodVisitor.visitInsn(n26);
                    ++n2;
                    break;
                }
                case 4: {
                    if (n26 > 54) {
                        methodVisitor.visitVarInsn(54 + ((n26 -= 59) >> 2), n26 & 3);
                    } else {
                        methodVisitor.visitVarInsn(21 + ((n26 -= 26) >> 2), n26 & 3);
                    }
                    ++n2;
                    break;
                }
                case 9: {
                    methodVisitor.visitJumpInsn(n26, arrlabel[n25 + this.readShort(n2 + 1)]);
                    n2 += 3;
                    break;
                }
                case 10: {
                    methodVisitor.visitJumpInsn(n26 + n4, arrlabel[n25 + this.readInt(n2 + 1)]);
                    n2 += 5;
                    break;
                }
                case 18: {
                    n26 = n26 < 218 ? n26 - 49 : n26 - 20;
                    Label label5 = arrlabel[n25 + this.readUnsignedShort(n2 + 1)];
                    if (n26 == 167 || n26 == 168) {
                        methodVisitor.visitJumpInsn(n26 + 33, label5);
                    } else {
                        n26 = n26 <= 166 ? (n26 + 1 ^ 1) - 1 : n26 ^ 1;
                        Label label6 = new Label();
                        methodVisitor.visitJumpInsn(n26, label6);
                        methodVisitor.visitJumpInsn(200, label5);
                        methodVisitor.visitLabel(label6);
                        if (n19 != 0 && (context2 == null || context2.offset != n25 + 3)) {
                            methodVisitor.visitFrame(256, 0, null, 0, null);
                        }
                    }
                    n2 += 3;
                    break;
                }
                case 17: {
                    n26 = arrby[n2 + 1] & 0xFF;
                    if (n26 == 132) {
                        methodVisitor.visitIincInsn(this.readUnsignedShort(n2 + 2), this.readShort(n2 + 4));
                        n2 += 6;
                        break;
                    }
                    methodVisitor.visitVarInsn(n26, this.readUnsignedShort(n2 + 2));
                    n2 += 4;
                    break;
                }
                case 14: {
                    n2 = n2 + 4 - (n25 & 3);
                    int n27 = n25 + this.readInt(n2);
                    int n28 = this.readInt(n2 + 4);
                    int n29 = this.readInt(n2 + 8);
                    Label[] arrlabel2 = new Label[n29 - n28 + 1];
                    n2 += 12;
                    for (int i2 = 0; i2 < arrlabel2.length; ++i2) {
                        arrlabel2[i2] = arrlabel[n25 + this.readInt(n2)];
                        n2 += 4;
                    }
                    methodVisitor.visitTableSwitchInsn(n28, n29, arrlabel[n27], arrlabel2);
                    break;
                }
                case 15: {
                    n2 = n2 + 4 - (n25 & 3);
                    int n30 = n25 + this.readInt(n2);
                    int n31 = this.readInt(n2 + 4);
                    int[] arrn3 = new int[n31];
                    Label[] arrlabel3 = new Label[n31];
                    n2 += 8;
                    for (int i3 = 0; i3 < n31; ++i3) {
                        arrn3[i3] = this.readInt(n2);
                        arrlabel3[i3] = arrlabel[n25 + this.readInt(n2 + 4)];
                        n2 += 8;
                    }
                    methodVisitor.visitLookupSwitchInsn(arrlabel[n30], arrn3, arrlabel3);
                    break;
                }
                case 3: {
                    methodVisitor.visitVarInsn(n26, arrby[n2 + 1] & 0xFF);
                    n2 += 2;
                    break;
                }
                case 1: {
                    methodVisitor.visitIntInsn(n26, arrby[n2 + 1]);
                    n2 += 2;
                    break;
                }
                case 2: {
                    methodVisitor.visitIntInsn(n26, this.readShort(n2 + 1));
                    n2 += 3;
                    break;
                }
                case 11: {
                    methodVisitor.visitLdcInsn(this.readConst(arrby[n2 + 1] & 0xFF, arrc));
                    n2 += 2;
                    break;
                }
                case 12: {
                    methodVisitor.visitLdcInsn(this.readConst(this.readUnsignedShort(n2 + 1), arrc));
                    n2 += 3;
                    break;
                }
                case 6: 
                case 7: {
                    int n32 = this.items[this.readUnsignedShort(n2 + 1)];
                    boolean bl3 = arrby[n32 - 1] == 11;
                    String string = this.readClass(n32, arrc);
                    n32 = this.items[this.readUnsignedShort(n32 + 2)];
                    String string2 = this.readUTF8(n32, arrc);
                    String string3 = this.readUTF8(n32 + 2, arrc);
                    if (n26 < 182) {
                        methodVisitor.visitFieldInsn(n26, string, string2, string3);
                    } else {
                        methodVisitor.visitMethodInsn(n26, string, string2, string3, bl3);
                    }
                    if (n26 == 185) {
                        n2 += 5;
                        break;
                    }
                    n2 += 3;
                    break;
                }
                case 8: {
                    int n33 = this.items[this.readUnsignedShort(n2 + 1)];
                    int n34 = context.bootstrapMethods[this.readUnsignedShort(n33)];
                    Handle handle = (Handle)this.readConst(this.readUnsignedShort(n34), arrc);
                    int n35 = this.readUnsignedShort(n34 + 2);
                    Object[] arrobject = new Object[n35];
                    n34 += 4;
                    for (int i4 = 0; i4 < n35; ++i4) {
                        arrobject[i4] = this.readConst(this.readUnsignedShort(n34), arrc);
                        n34 += 2;
                    }
                    n33 = this.items[this.readUnsignedShort(n33 + 2)];
                    String string = this.readUTF8(n33, arrc);
                    String string4 = this.readUTF8(n33 + 2, arrc);
                    methodVisitor.visitInvokeDynamicInsn(string, string4, handle, arrobject);
                    n2 += 5;
                    break;
                }
                case 5: {
                    methodVisitor.visitTypeInsn(n26, this.readClass(n2 + 1, arrc));
                    n2 += 3;
                    break;
                }
                case 13: {
                    methodVisitor.visitIincInsn(arrby[n2 + 1] & 0xFF, arrby[n2 + 2]);
                    n2 += 3;
                    break;
                }
                default: {
                    methodVisitor.visitMultiANewArrayInsn(this.readClass(n2 + 1, arrc), arrby[n2 + 3] & 0xFF);
                    n2 += 4;
                }
            }
            while (arrn != null && n13 < arrn.length && n15 <= n25) {
                if (n15 == n25) {
                    int n36 = this.readAnnotationTarget(context, arrn[n13]);
                    this.readAnnotationValues(n36 + 2, arrc, true, methodVisitor.visitInsnAnnotation(context.typeRef, context.typePath, this.readUTF8(n36, arrc), true));
                }
                n15 = ++n13 >= arrn.length || this.readByte(arrn[n13]) < 67 ? -1 : this.readUnsignedShort(arrn[n13] + 1);
            }
            while (arrn2 != null && n14 < arrn2.length && n16 <= n25) {
                if (n16 == n25) {
                    int n37 = this.readAnnotationTarget(context, arrn2[n14]);
                    this.readAnnotationValues(n37 + 2, arrc, true, methodVisitor.visitInsnAnnotation(context.typeRef, context.typePath, this.readUTF8(n37, arrc), false));
                }
                n16 = ++n14 >= arrn2.length || this.readByte(arrn2[n14]) < 67 ? -1 : this.readUnsignedShort(arrn2[n14] + 1);
            }
        }
        if (arrlabel[n8] != null) {
            methodVisitor.visitLabel(arrlabel[n8]);
        }
        if ((context.flags & 2) == 0 && n17 != 0) {
            int[] arrn4 = null;
            if (n18 != 0) {
                n2 = n18 + 2;
                arrn4 = new int[this.readUnsignedShort(n18) * 3];
                int n38 = arrn4.length;
                while (n38 > 0) {
                    arrn4[--n38] = n2 + 6;
                    arrn4[--n38] = this.readUnsignedShort(n2 + 8);
                    arrn4[--n38] = this.readUnsignedShort(n2);
                    n2 += 10;
                }
            }
            n2 = n17 + 2;
            for (int i5 = this.readUnsignedShort(n17); i5 > 0; --i5) {
                n3 = this.readUnsignedShort(n2);
                int n39 = this.readUnsignedShort(n2 + 2);
                int n40 = this.readUnsignedShort(n2 + 8);
                String string = null;
                if (arrn4 != null) {
                    for (int i6 = 0; i6 < arrn4.length; i6 += 3) {
                        if (arrn4[i6] != n3 || arrn4[i6 + 1] != n40) continue;
                        string = this.readUTF8(arrn4[i6 + 2], arrc);
                        break;
                    }
                }
                methodVisitor.visitLocalVariable(this.readUTF8(n2 + 4, arrc), this.readUTF8(n2 + 6, arrc), string, arrlabel[n3], arrlabel[n3 + n39], n40);
                n2 += 10;
            }
        }
        if (arrn != null) {
            for (int i7 = 0; i7 < arrn.length; ++i7) {
                if (this.readByte(arrn[i7]) >> 1 != 32) continue;
                int n41 = this.readAnnotationTarget(context, arrn[i7]);
                n41 = this.readAnnotationValues(n41 + 2, arrc, true, methodVisitor.visitLocalVariableAnnotation(context.typeRef, context.typePath, context.start, context.end, context.index, this.readUTF8(n41, arrc), true));
            }
        }
        if (arrn2 != null) {
            for (int i8 = 0; i8 < arrn2.length; ++i8) {
                if (this.readByte(arrn2[i8]) >> 1 != 32) continue;
                int n42 = this.readAnnotationTarget(context, arrn2[i8]);
                n42 = this.readAnnotationValues(n42 + 2, arrc, true, methodVisitor.visitLocalVariableAnnotation(context.typeRef, context.typePath, context.start, context.end, context.index, this.readUTF8(n42, arrc), false));
            }
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.next;
            attribute.next = null;
            methodVisitor.visitAttribute(attribute);
            attribute = attribute3;
        }
        methodVisitor.visitMaxs(n6, n7);
    }

    private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int n2, boolean bl) {
        char[] arrc = context.buffer;
        int[] arrn = new int[this.readUnsignedShort(n2)];
        n2 += 2;
        for (int i2 = 0; i2 < arrn.length; ++i2) {
            int n3;
            arrn[i2] = n2;
            int n4 = this.readInt(n2);
            switch (n4 >>> 24) {
                case 0: 
                case 1: 
                case 22: {
                    n2 += 2;
                    break;
                }
                case 19: 
                case 20: 
                case 21: {
                    ++n2;
                    break;
                }
                case 64: 
                case 65: {
                    for (n3 = this.readUnsignedShort(n2 + 1); n3 > 0; --n3) {
                        int n5 = this.readUnsignedShort(n2 + 3);
                        int n6 = this.readUnsignedShort(n2 + 5);
                        this.readLabel(n5, context.labels);
                        this.readLabel(n5 + n6, context.labels);
                        n2 += 6;
                    }
                    n2 += 3;
                    break;
                }
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: {
                    n2 += 4;
                    break;
                }
                default: {
                    n2 += 3;
                }
            }
            n3 = this.readByte(n2);
            if (n4 >>> 24 == 66) {
                TypePath typePath = n3 == 0 ? null : new TypePath(this.b, n2);
                n2 += 1 + 2 * n3;
                n2 = this.readAnnotationValues(n2 + 2, arrc, true, methodVisitor.visitTryCatchAnnotation(n4, typePath, this.readUTF8(n2, arrc), bl));
                continue;
            }
            n2 = this.readAnnotationValues(n2 + 3 + 2 * n3, arrc, true, null);
        }
        return arrn;
    }

    private int readAnnotationTarget(Context context, int n2) {
        int n3;
        int n4 = this.readInt(n2);
        switch (n4 >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                n4 &= 0xFFFF0000;
                n2 += 2;
                break;
            }
            case 19: 
            case 20: 
            case 21: {
                n4 &= 0xFF000000;
                ++n2;
                break;
            }
            case 64: 
            case 65: {
                n4 &= 0xFF000000;
                n3 = this.readUnsignedShort(n2 + 1);
                context.start = new Label[n3];
                context.end = new Label[n3];
                context.index = new int[n3];
                n2 += 3;
                for (int i2 = 0; i2 < n3; ++i2) {
                    int n5 = this.readUnsignedShort(n2);
                    int n6 = this.readUnsignedShort(n2 + 2);
                    context.start[i2] = this.readLabel(n5, context.labels);
                    context.end[i2] = this.readLabel(n5 + n6, context.labels);
                    context.index[i2] = this.readUnsignedShort(n2 + 4);
                    n2 += 6;
                }
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                n4 &= 0xFF0000FF;
                n2 += 4;
                break;
            }
            default: {
                n4 &= n4 >>> 24 < 67 ? -256 : -16777216;
                n2 += 3;
            }
        }
        n3 = this.readByte(n2);
        context.typeRef = n4;
        context.typePath = n3 == 0 ? null : new TypePath(this.b, n2);
        return n2 + 1 + 2 * n3;
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int n2, boolean bl) {
        AnnotationVisitor annotationVisitor;
        int n3;
        int n4 = this.b[n2++] & 0xFF;
        int n5 = Type.getArgumentTypes(context.desc).length - n4;
        for (n3 = 0; n3 < n5; ++n3) {
            annotationVisitor = methodVisitor.visitParameterAnnotation(n3, "Ljava/lang/Synthetic;", false);
            if (annotationVisitor == null) continue;
            annotationVisitor.visitEnd();
        }
        char[] arrc = context.buffer;
        while (n3 < n4 + n5) {
            int n6 = this.readUnsignedShort(n2);
            n2 += 2;
            while (n6 > 0) {
                annotationVisitor = methodVisitor.visitParameterAnnotation(n3, this.readUTF8(n2, arrc), bl);
                n2 = this.readAnnotationValues(n2 + 2, arrc, true, annotationVisitor);
                --n6;
            }
            ++n3;
        }
    }

    private int readAnnotationValues(int n2, char[] arrc, boolean bl, AnnotationVisitor annotationVisitor) {
        int n3 = this.readUnsignedShort(n2);
        n2 += 2;
        if (bl) {
            while (n3 > 0) {
                n2 = this.readAnnotationValue(n2 + 2, arrc, this.readUTF8(n2, arrc), annotationVisitor);
                --n3;
            }
        } else {
            while (n3 > 0) {
                n2 = this.readAnnotationValue(n2, arrc, null, annotationVisitor);
                --n3;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return n2;
    }

    private int readAnnotationValue(int n2, char[] arrc, String string, AnnotationVisitor annotationVisitor) {
        if (annotationVisitor == null) {
            switch (this.b[n2] & 0xFF) {
                case 101: {
                    return n2 + 5;
                }
                case 64: {
                    return this.readAnnotationValues(n2 + 3, arrc, true, null);
                }
                case 91: {
                    return this.readAnnotationValues(n2 + 1, arrc, false, null);
                }
            }
            return n2 + 3;
        }
        block5 : switch (this.b[n2++] & 0xFF) {
            case 68: 
            case 70: 
            case 73: 
            case 74: {
                annotationVisitor.visit(string, this.readConst(this.readUnsignedShort(n2), arrc));
                n2 += 2;
                break;
            }
            case 66: {
                annotationVisitor.visit(string, (byte)this.readInt(this.items[this.readUnsignedShort(n2)]));
                n2 += 2;
                break;
            }
            case 90: {
                annotationVisitor.visit(string, this.readInt(this.items[this.readUnsignedShort(n2)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                n2 += 2;
                break;
            }
            case 83: {
                annotationVisitor.visit(string, (short)this.readInt(this.items[this.readUnsignedShort(n2)]));
                n2 += 2;
                break;
            }
            case 67: {
                annotationVisitor.visit(string, Character.valueOf((char)this.readInt(this.items[this.readUnsignedShort(n2)])));
                n2 += 2;
                break;
            }
            case 115: {
                annotationVisitor.visit(string, this.readUTF8(n2, arrc));
                n2 += 2;
                break;
            }
            case 101: {
                annotationVisitor.visitEnum(string, this.readUTF8(n2, arrc), this.readUTF8(n2 + 2, arrc));
                n2 += 4;
                break;
            }
            case 99: {
                annotationVisitor.visit(string, Type.getType(this.readUTF8(n2, arrc)));
                n2 += 2;
                break;
            }
            case 64: {
                n2 = this.readAnnotationValues(n2 + 2, arrc, true, annotationVisitor.visitAnnotation(string, this.readUTF8(n2, arrc)));
                break;
            }
            case 91: {
                int n3 = this.readUnsignedShort(n2);
                n2 += 2;
                if (n3 == 0) {
                    return this.readAnnotationValues(n2 - 2, arrc, false, annotationVisitor.visitArray(string));
                }
                switch (this.b[n2++] & 0xFF) {
                    case 66: {
                        byte[] arrby = new byte[n3];
                        for (int i2 = 0; i2 < n3; ++i2) {
                            arrby[i2] = (byte)this.readInt(this.items[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrby);
                        --n2;
                        break block5;
                    }
                    case 90: {
                        boolean[] arrbl = new boolean[n3];
                        for (int i3 = 0; i3 < n3; ++i3) {
                            arrbl[i3] = this.readInt(this.items[this.readUnsignedShort(n2)]) != 0;
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrbl);
                        --n2;
                        break block5;
                    }
                    case 83: {
                        short[] arrs = new short[n3];
                        for (int i4 = 0; i4 < n3; ++i4) {
                            arrs[i4] = (short)this.readInt(this.items[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrs);
                        --n2;
                        break block5;
                    }
                    case 67: {
                        char[] arrc2 = new char[n3];
                        for (int i5 = 0; i5 < n3; ++i5) {
                            arrc2[i5] = (char)this.readInt(this.items[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrc2);
                        --n2;
                        break block5;
                    }
                    case 73: {
                        int[] arrn = new int[n3];
                        for (int i6 = 0; i6 < n3; ++i6) {
                            arrn[i6] = this.readInt(this.items[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrn);
                        --n2;
                        break block5;
                    }
                    case 74: {
                        long[] arrl = new long[n3];
                        for (int i7 = 0; i7 < n3; ++i7) {
                            arrl[i7] = this.readLong(this.items[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrl);
                        --n2;
                        break block5;
                    }
                    case 70: {
                        float[] arrf = new float[n3];
                        for (int i8 = 0; i8 < n3; ++i8) {
                            arrf[i8] = Float.intBitsToFloat(this.readInt(this.items[this.readUnsignedShort(n2)]));
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrf);
                        --n2;
                        break block5;
                    }
                    case 68: {
                        double[] arrd = new double[n3];
                        for (int i9 = 0; i9 < n3; ++i9) {
                            arrd[i9] = Double.longBitsToDouble(this.readLong(this.items[this.readUnsignedShort(n2)]));
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, arrd);
                        --n2;
                        break block5;
                    }
                }
                n2 = this.readAnnotationValues(n2 - 3, arrc, false, annotationVisitor.visitArray(string));
            }
        }
        return n2;
    }

    private void getImplicitFrame(Context context) {
        String string = context.desc;
        Object[] arrobject = context.local;
        int n2 = 0;
        if ((context.access & 8) == 0) {
            arrobject[n2++] = "<init>".equals(context.name) ? Opcodes.UNINITIALIZED_THIS : this.readClass(this.header + 2, context.buffer);
        }
        int n3 = 1;
        block8: while (true) {
            int n4 = n3;
            switch (string.charAt(n3++)) {
                case 'B': 
                case 'C': 
                case 'I': 
                case 'S': 
                case 'Z': {
                    arrobject[n2++] = Opcodes.INTEGER;
                    continue block8;
                }
                case 'F': {
                    arrobject[n2++] = Opcodes.FLOAT;
                    continue block8;
                }
                case 'J': {
                    arrobject[n2++] = Opcodes.LONG;
                    continue block8;
                }
                case 'D': {
                    arrobject[n2++] = Opcodes.DOUBLE;
                    continue block8;
                }
                case '[': {
                    while (string.charAt(n3) == '[') {
                        ++n3;
                    }
                    if (string.charAt(n3) == 'L') {
                        ++n3;
                        while (string.charAt(n3) != ';') {
                            ++n3;
                        }
                    }
                    arrobject[n2++] = string.substring(n4, ++n3);
                    continue block8;
                }
                case 'L': {
                    while (string.charAt(n3) != ';') {
                        ++n3;
                    }
                    arrobject[n2++] = string.substring(n4 + 1, n3++);
                    continue block8;
                }
            }
            break;
        }
        context.localCount = n2;
    }

    private int readFrame(int n2, boolean bl, boolean bl2, Context context) {
        int n3;
        int n4;
        char[] arrc = context.buffer;
        Label[] arrlabel = context.labels;
        if (bl) {
            n4 = this.b[n2++] & 0xFF;
        } else {
            n4 = 255;
            context.offset = -1;
        }
        context.localDiff = 0;
        if (n4 < 64) {
            n3 = n4;
            context.mode = 3;
            context.stackCount = 0;
        } else if (n4 < 128) {
            n3 = n4 - 64;
            n2 = this.readFrameType(context.stack, 0, n2, arrc, arrlabel);
            context.mode = 4;
            context.stackCount = 1;
        } else {
            n3 = this.readUnsignedShort(n2);
            n2 += 2;
            if (n4 == 247) {
                n2 = this.readFrameType(context.stack, 0, n2, arrc, arrlabel);
                context.mode = 4;
                context.stackCount = 1;
            } else if (n4 >= 248 && n4 < 251) {
                context.mode = 2;
                context.localDiff = 251 - n4;
                context.localCount -= context.localDiff;
                context.stackCount = 0;
            } else if (n4 == 251) {
                context.mode = 3;
                context.stackCount = 0;
            } else if (n4 < 255) {
                int n5 = bl2 ? context.localCount : 0;
                for (int i2 = n4 - 251; i2 > 0; --i2) {
                    n2 = this.readFrameType(context.local, n5++, n2, arrc, arrlabel);
                }
                context.mode = 1;
                context.localDiff = n4 - 251;
                context.localCount += context.localDiff;
                context.stackCount = 0;
            } else {
                context.mode = 0;
                int n6 = this.readUnsignedShort(n2);
                n2 += 2;
                context.localDiff = n6;
                context.localCount = n6;
                int n7 = 0;
                while (n6 > 0) {
                    n2 = this.readFrameType(context.local, n7++, n2, arrc, arrlabel);
                    --n6;
                }
                n6 = this.readUnsignedShort(n2);
                n2 += 2;
                context.stackCount = n6;
                n7 = 0;
                while (n6 > 0) {
                    n2 = this.readFrameType(context.stack, n7++, n2, arrc, arrlabel);
                    --n6;
                }
            }
        }
        context.offset += n3 + 1;
        this.readLabel(context.offset, arrlabel);
        return n2;
    }

    private int readFrameType(Object[] arrobject, int n2, int n3, char[] arrc, Label[] arrlabel) {
        int n4 = this.b[n3++] & 0xFF;
        switch (n4) {
            case 0: {
                arrobject[n2] = Opcodes.TOP;
                break;
            }
            case 1: {
                arrobject[n2] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                arrobject[n2] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                arrobject[n2] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                arrobject[n2] = Opcodes.LONG;
                break;
            }
            case 5: {
                arrobject[n2] = Opcodes.NULL;
                break;
            }
            case 6: {
                arrobject[n2] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                arrobject[n2] = this.readClass(n3, arrc);
                n3 += 2;
                break;
            }
            default: {
                arrobject[n2] = this.readLabel(this.readUnsignedShort(n3), arrlabel);
                n3 += 2;
            }
        }
        return n3;
    }

    protected Label readLabel(int n2, Label[] arrlabel) {
        if (arrlabel[n2] == null) {
            arrlabel[n2] = new Label();
        }
        return arrlabel[n2];
    }

    private int getAttributes() {
        int n2;
        int n3;
        int n4 = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;
        for (n3 = this.readUnsignedShort(n4); n3 > 0; --n3) {
            for (n2 = this.readUnsignedShort(n4 + 8); n2 > 0; --n2) {
                n4 += 6 + this.readInt(n4 + 12);
            }
            n4 += 8;
        }
        for (n3 = this.readUnsignedShort(n4 += 2); n3 > 0; --n3) {
            for (n2 = this.readUnsignedShort(n4 + 8); n2 > 0; --n2) {
                n4 += 6 + this.readInt(n4 + 12);
            }
            n4 += 8;
        }
        return n4 + 2;
    }

    private Attribute readAttribute(Attribute[] arrattribute, String string, int n2, int n3, char[] arrc, int n4, Label[] arrlabel) {
        for (int i2 = 0; i2 < arrattribute.length; ++i2) {
            if (!arrattribute[i2].type.equals(string)) continue;
            return arrattribute[i2].read(this, n2, n3, arrc, n4, arrlabel);
        }
        return new Attribute(string).read(this, n2, n3, null, -1, null);
    }

    public int getItemCount() {
        return this.items.length;
    }

    public int getItem(int n2) {
        return this.items[n2];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int n2) {
        return this.b[n2] & 0xFF;
    }

    public int readUnsignedShort(int n2) {
        byte[] arrby = this.b;
        return (arrby[n2] & 0xFF) << 8 | arrby[n2 + 1] & 0xFF;
    }

    public short readShort(int n2) {
        byte[] arrby = this.b;
        return (short)((arrby[n2] & 0xFF) << 8 | arrby[n2 + 1] & 0xFF);
    }

    public int readInt(int n2) {
        byte[] arrby = this.b;
        return (arrby[n2] & 0xFF) << 24 | (arrby[n2 + 1] & 0xFF) << 16 | (arrby[n2 + 2] & 0xFF) << 8 | arrby[n2 + 3] & 0xFF;
    }

    public long readLong(int n2) {
        long l2 = this.readInt(n2);
        long l3 = (long)this.readInt(n2 + 4) & 0xFFFFFFFFL;
        return l2 << 32 | l3;
    }

    public String readUTF8(int n2, char[] arrc) {
        int n3 = this.readUnsignedShort(n2);
        if (n2 == 0 || n3 == 0) {
            return null;
        }
        String string = this.strings[n3];
        if (string != null) {
            return string;
        }
        n2 = this.items[n3];
        this.strings[n3] = this.readUTF(n2 + 2, this.readUnsignedShort(n2), arrc);
        return this.strings[n3];
    }

    private String readUTF(int n2, int n3, char[] arrc) {
        int n4 = n2 + n3;
        byte[] arrby = this.b;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        while (n2 < n4) {
            int n8 = arrby[n2++];
            switch (n6) {
                case 0: {
                    if ((n8 &= 0xFF) < 128) {
                        arrc[n5++] = (char)n8;
                        break;
                    }
                    if (n8 < 224 && n8 > 191) {
                        n7 = (char)(n8 & 0x1F);
                        n6 = 1;
                        break;
                    }
                    n7 = (char)(n8 & 0xF);
                    n6 = 2;
                    break;
                }
                case 1: {
                    arrc[n5++] = (char)(n7 << 6 | n8 & 0x3F);
                    n6 = 0;
                    break;
                }
                case 2: {
                    n7 = (char)(n7 << 6 | n8 & 0x3F);
                    n6 = 1;
                }
            }
        }
        return new String(arrc, 0, n5);
    }

    public String readClass(int n2, char[] arrc) {
        return this.readUTF8(this.items[this.readUnsignedShort(n2)], arrc);
    }

    public Object readConst(int n2, char[] arrc) {
        int n3 = this.items[n2];
        switch (this.b[n3 - 1]) {
            case 3: {
                return this.readInt(n3);
            }
            case 4: {
                return Float.valueOf(Float.intBitsToFloat(this.readInt(n3)));
            }
            case 5: {
                return this.readLong(n3);
            }
            case 6: {
                return Double.longBitsToDouble(this.readLong(n3));
            }
            case 7: {
                return Type.getObjectType(this.readUTF8(n3, arrc));
            }
            case 8: {
                return this.readUTF8(n3, arrc);
            }
            case 16: {
                return Type.getMethodType(this.readUTF8(n3, arrc));
            }
        }
        int n4 = this.readByte(n3);
        int[] arrn = this.items;
        int n5 = arrn[this.readUnsignedShort(n3 + 1)];
        boolean bl = this.b[n5 - 1] == 11;
        String string = this.readClass(n5, arrc);
        n5 = arrn[this.readUnsignedShort(n5 + 2)];
        String string2 = this.readUTF8(n5, arrc);
        String string3 = this.readUTF8(n5 + 2, arrc);
        return new Handle(n4, string, string2, string3, bl);
    }
}

