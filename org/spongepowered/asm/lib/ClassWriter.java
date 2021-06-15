/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.AnnotationWriter;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.FieldWriter;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Item;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.MethodWriter;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;

public class ClassWriter
extends ClassVisitor {
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    static final int ACC_SYNTHETIC_ATTRIBUTE = 262144;
    static final int TO_ACC_SYNTHETIC = 64;
    static final int NOARG_INSN = 0;
    static final int SBYTE_INSN = 1;
    static final int SHORT_INSN = 2;
    static final int VAR_INSN = 3;
    static final int IMPLVAR_INSN = 4;
    static final int TYPE_INSN = 5;
    static final int FIELDORMETH_INSN = 6;
    static final int ITFMETH_INSN = 7;
    static final int INDYMETH_INSN = 8;
    static final int LABEL_INSN = 9;
    static final int LABELW_INSN = 10;
    static final int LDC_INSN = 11;
    static final int LDCW_INSN = 12;
    static final int IINC_INSN = 13;
    static final int TABL_INSN = 14;
    static final int LOOK_INSN = 15;
    static final int MANA_INSN = 16;
    static final int WIDE_INSN = 17;
    static final int ASM_LABEL_INSN = 18;
    static final int F_INSERT = 256;
    static final byte[] TYPE;
    static final int CLASS = 7;
    static final int FIELD = 9;
    static final int METH = 10;
    static final int IMETH = 11;
    static final int STR = 8;
    static final int INT = 3;
    static final int FLOAT = 4;
    static final int LONG = 5;
    static final int DOUBLE = 6;
    static final int NAME_TYPE = 12;
    static final int UTF8 = 1;
    static final int MTYPE = 16;
    static final int HANDLE = 15;
    static final int INDY = 18;
    static final int HANDLE_BASE = 20;
    static final int TYPE_NORMAL = 30;
    static final int TYPE_UNINIT = 31;
    static final int TYPE_MERGED = 32;
    static final int BSM = 33;
    ClassReader cr;
    int version;
    int index = 1;
    final ByteVector pool = new ByteVector();
    Item[] items = new Item[256];
    int threshold = (int)(0.75 * (double)this.items.length);
    final Item key = new Item();
    final Item key2 = new Item();
    final Item key3 = new Item();
    final Item key4 = new Item();
    Item[] typeTable;
    private short typeCount;
    private int access;
    private int name;
    String thisName;
    private int signature;
    private int superName;
    private int interfaceCount;
    private int[] interfaces;
    private int sourceFile;
    private ByteVector sourceDebug;
    private int enclosingMethodOwner;
    private int enclosingMethod;
    private AnnotationWriter anns;
    private AnnotationWriter ianns;
    private AnnotationWriter tanns;
    private AnnotationWriter itanns;
    private Attribute attrs;
    private int innerClassesCount;
    private ByteVector innerClasses;
    int bootstrapMethodsCount;
    ByteVector bootstrapMethods;
    FieldWriter firstField;
    FieldWriter lastField;
    MethodWriter firstMethod;
    MethodWriter lastMethod;
    private int compute;
    boolean hasAsmInsns;

    public ClassWriter(int n2) {
        super(327680);
        this.compute = (n2 & 2) != 0 ? 0 : ((n2 & 1) != 0 ? 2 : 3);
    }

    public ClassWriter(ClassReader classReader, int n2) {
        this(n2);
        classReader.copyPool(this);
        this.cr = classReader;
    }

    public final void visit(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
        this.version = n2;
        this.access = n3;
        this.name = this.newClass(string);
        this.thisName = string;
        if (string2 != null) {
            this.signature = this.newUTF8(string2);
        }
        int n4 = this.superName = string3 == null ? 0 : this.newClass(string3);
        if (arrstring != null && arrstring.length > 0) {
            this.interfaceCount = arrstring.length;
            this.interfaces = new int[this.interfaceCount];
            for (int i2 = 0; i2 < this.interfaceCount; ++i2) {
                this.interfaces[i2] = this.newClass(arrstring[i2]);
            }
        }
    }

    public final void visitSource(String string, String string2) {
        if (string != null) {
            this.sourceFile = this.newUTF8(string);
        }
        if (string2 != null) {
            this.sourceDebug = new ByteVector().encodeUTF8(string2, 0, Integer.MAX_VALUE);
        }
    }

    public final void visitOuterClass(String string, String string2, String string3) {
        this.enclosingMethodOwner = this.newClass(string);
        if (string2 != null && string3 != null) {
            this.enclosingMethod = this.newNameType(string2, string3);
        }
    }

    public final AnnotationVisitor visitAnnotation(String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (bl) {
            annotationWriter.next = this.anns;
            this.anns = annotationWriter;
        } else {
            annotationWriter.next = this.ianns;
            this.ianns = annotationWriter;
        }
        return annotationWriter;
    }

    public final AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(n2, typePath, byteVector);
        byteVector.putShort(this.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, byteVector.length - 2);
        if (bl) {
            annotationWriter.next = this.tanns;
            this.tanns = annotationWriter;
        } else {
            annotationWriter.next = this.itanns;
            this.itanns = annotationWriter;
        }
        return annotationWriter;
    }

    public final void visitAttribute(Attribute attribute) {
        attribute.next = this.attrs;
        this.attrs = attribute;
    }

    public final void visitInnerClass(String string, String string2, String string3, int n2) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        Item item = this.newClassItem(string);
        if (item.intVal == 0) {
            ++this.innerClassesCount;
            this.innerClasses.putShort(item.index);
            this.innerClasses.putShort(string2 == null ? 0 : this.newClass(string2));
            this.innerClasses.putShort(string3 == null ? 0 : this.newUTF8(string3));
            this.innerClasses.putShort(n2);
            item.intVal = this.innerClassesCount;
        }
    }

    public final FieldVisitor visitField(int n2, String string, String string2, String string3, Object object) {
        return new FieldWriter(this, n2, string, string2, string3, object);
    }

    public final MethodVisitor visitMethod(int n2, String string, String string2, String string3, String[] arrstring) {
        return new MethodWriter(this, n2, string, string2, string3, arrstring, this.compute);
    }

    public final void visitEnd() {
    }

    public byte[] toByteArray() {
        int n2;
        if (this.index > 65535) {
            throw new RuntimeException("Class file too large!");
        }
        int n3 = 24 + 2 * this.interfaceCount;
        int n4 = 0;
        FieldWriter fieldWriter = this.firstField;
        while (fieldWriter != null) {
            ++n4;
            n3 += fieldWriter.getSize();
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        int n5 = 0;
        MethodWriter methodWriter = this.firstMethod;
        while (methodWriter != null) {
            ++n5;
            n3 += methodWriter.getSize();
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        int n6 = 0;
        if (this.bootstrapMethods != null) {
            ++n6;
            n3 += 8 + this.bootstrapMethods.length;
            this.newUTF8("BootstrapMethods");
        }
        if (this.signature != 0) {
            ++n6;
            n3 += 8;
            this.newUTF8("Signature");
        }
        if (this.sourceFile != 0) {
            ++n6;
            n3 += 8;
            this.newUTF8("SourceFile");
        }
        if (this.sourceDebug != null) {
            ++n6;
            n3 += this.sourceDebug.length + 6;
            this.newUTF8("SourceDebugExtension");
        }
        if (this.enclosingMethodOwner != 0) {
            ++n6;
            n3 += 10;
            this.newUTF8("EnclosingMethod");
        }
        if ((this.access & 0x20000) != 0) {
            ++n6;
            n3 += 6;
            this.newUTF8("Deprecated");
        }
        if ((this.access & 0x1000) != 0 && ((this.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
            ++n6;
            n3 += 6;
            this.newUTF8("Synthetic");
        }
        if (this.innerClasses != null) {
            ++n6;
            n3 += 8 + this.innerClasses.length;
            this.newUTF8("InnerClasses");
        }
        if (this.anns != null) {
            ++n6;
            n3 += 8 + this.anns.getSize();
            this.newUTF8("RuntimeVisibleAnnotations");
        }
        if (this.ianns != null) {
            ++n6;
            n3 += 8 + this.ianns.getSize();
            this.newUTF8("RuntimeInvisibleAnnotations");
        }
        if (this.tanns != null) {
            ++n6;
            n3 += 8 + this.tanns.getSize();
            this.newUTF8("RuntimeVisibleTypeAnnotations");
        }
        if (this.itanns != null) {
            ++n6;
            n3 += 8 + this.itanns.getSize();
            this.newUTF8("RuntimeInvisibleTypeAnnotations");
        }
        if (this.attrs != null) {
            n6 += this.attrs.getCount();
            n3 += this.attrs.getSize(this, null, 0, -1, -1);
        }
        ByteVector byteVector = new ByteVector(n3 += this.pool.length);
        byteVector.putInt(-889275714).putInt(this.version);
        byteVector.putShort(this.index).putByteArray(this.pool.data, 0, this.pool.length);
        int n7 = 0x60000 | (this.access & 0x40000) / 64;
        byteVector.putShort(this.access & ~n7).putShort(this.name).putShort(this.superName);
        byteVector.putShort(this.interfaceCount);
        for (n2 = 0; n2 < this.interfaceCount; ++n2) {
            byteVector.putShort(this.interfaces[n2]);
        }
        byteVector.putShort(n4);
        fieldWriter = this.firstField;
        while (fieldWriter != null) {
            fieldWriter.put(byteVector);
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        byteVector.putShort(n5);
        methodWriter = this.firstMethod;
        while (methodWriter != null) {
            methodWriter.put(byteVector);
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        byteVector.putShort(n6);
        if (this.bootstrapMethods != null) {
            byteVector.putShort(this.newUTF8("BootstrapMethods"));
            byteVector.putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodsCount);
            byteVector.putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
        }
        if (this.signature != 0) {
            byteVector.putShort(this.newUTF8("Signature")).putInt(2).putShort(this.signature);
        }
        if (this.sourceFile != 0) {
            byteVector.putShort(this.newUTF8("SourceFile")).putInt(2).putShort(this.sourceFile);
        }
        if (this.sourceDebug != null) {
            n2 = this.sourceDebug.length;
            byteVector.putShort(this.newUTF8("SourceDebugExtension")).putInt(n2);
            byteVector.putByteArray(this.sourceDebug.data, 0, n2);
        }
        if (this.enclosingMethodOwner != 0) {
            byteVector.putShort(this.newUTF8("EnclosingMethod")).putInt(4);
            byteVector.putShort(this.enclosingMethodOwner).putShort(this.enclosingMethod);
        }
        if ((this.access & 0x20000) != 0) {
            byteVector.putShort(this.newUTF8("Deprecated")).putInt(0);
        }
        if ((this.access & 0x1000) != 0 && ((this.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
            byteVector.putShort(this.newUTF8("Synthetic")).putInt(0);
        }
        if (this.innerClasses != null) {
            byteVector.putShort(this.newUTF8("InnerClasses"));
            byteVector.putInt(this.innerClasses.length + 2).putShort(this.innerClassesCount);
            byteVector.putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
        }
        if (this.anns != null) {
            byteVector.putShort(this.newUTF8("RuntimeVisibleAnnotations"));
            this.anns.put(byteVector);
        }
        if (this.ianns != null) {
            byteVector.putShort(this.newUTF8("RuntimeInvisibleAnnotations"));
            this.ianns.put(byteVector);
        }
        if (this.tanns != null) {
            byteVector.putShort(this.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.tanns.put(byteVector);
        }
        if (this.itanns != null) {
            byteVector.putShort(this.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.itanns.put(byteVector);
        }
        if (this.attrs != null) {
            this.attrs.put(this, null, 0, -1, -1, byteVector);
        }
        if (this.hasAsmInsns) {
            this.anns = null;
            this.ianns = null;
            this.attrs = null;
            this.innerClassesCount = 0;
            this.innerClasses = null;
            this.firstField = null;
            this.lastField = null;
            this.firstMethod = null;
            this.lastMethod = null;
            this.compute = 1;
            this.hasAsmInsns = false;
            new ClassReader(byteVector.data).accept(this, 264);
            return this.toByteArray();
        }
        return byteVector.data;
    }

    Item newConstItem(Object object) {
        if (object instanceof Integer) {
            int n2 = (Integer)object;
            return this.newInteger(n2);
        }
        if (object instanceof Byte) {
            int n3 = ((Byte)object).intValue();
            return this.newInteger(n3);
        }
        if (object instanceof Character) {
            char c2 = ((Character)object).charValue();
            return this.newInteger(c2);
        }
        if (object instanceof Short) {
            int n4 = ((Short)object).intValue();
            return this.newInteger(n4);
        }
        if (object instanceof Boolean) {
            int n5 = (Boolean)object != false ? 1 : 0;
            return this.newInteger(n5);
        }
        if (object instanceof Float) {
            float f2 = ((Float)object).floatValue();
            return this.newFloat(f2);
        }
        if (object instanceof Long) {
            long l2 = (Long)object;
            return this.newLong(l2);
        }
        if (object instanceof Double) {
            double d2 = (Double)object;
            return this.newDouble(d2);
        }
        if (object instanceof String) {
            return this.newString((String)object);
        }
        if (object instanceof Type) {
            Type type = (Type)object;
            int n6 = type.getSort();
            if (n6 == 10) {
                return this.newClassItem(type.getInternalName());
            }
            if (n6 == 11) {
                return this.newMethodTypeItem(type.getDescriptor());
            }
            return this.newClassItem(type.getDescriptor());
        }
        if (object instanceof Handle) {
            Handle handle = (Handle)object;
            return this.newHandleItem(handle.tag, handle.owner, handle.name, handle.desc, handle.itf);
        }
        throw new IllegalArgumentException("value " + object);
    }

    public int newConst(Object object) {
        return this.newConstItem((Object)object).index;
    }

    public int newUTF8(String string) {
        this.key.set(1, string, null, null);
        Item item = this.get(this.key);
        if (item == null) {
            this.pool.putByte(1).putUTF8(string);
            item = new Item(this.index++, this.key);
            this.put(item);
        }
        return item.index;
    }

    Item newClassItem(String string) {
        this.key2.set(7, string, null, null);
        Item item = this.get(this.key2);
        if (item == null) {
            this.pool.put12(7, this.newUTF8(string));
            item = new Item(this.index++, this.key2);
            this.put(item);
        }
        return item;
    }

    public int newClass(String string) {
        return this.newClassItem((String)string).index;
    }

    Item newMethodTypeItem(String string) {
        this.key2.set(16, string, null, null);
        Item item = this.get(this.key2);
        if (item == null) {
            this.pool.put12(16, this.newUTF8(string));
            item = new Item(this.index++, this.key2);
            this.put(item);
        }
        return item;
    }

    public int newMethodType(String string) {
        return this.newMethodTypeItem((String)string).index;
    }

    Item newHandleItem(int n2, String string, String string2, String string3, boolean bl) {
        this.key4.set(20 + n2, string, string2, string3);
        Item item = this.get(this.key4);
        if (item == null) {
            if (n2 <= 4) {
                this.put112(15, n2, this.newField(string, string2, string3));
            } else {
                this.put112(15, n2, this.newMethod(string, string2, string3, bl));
            }
            item = new Item(this.index++, this.key4);
            this.put(item);
        }
        return item;
    }

    @Deprecated
    public int newHandle(int n2, String string, String string2, String string3) {
        return this.newHandle(n2, string, string2, string3, n2 == 9);
    }

    public int newHandle(int n2, String string, String string2, String string3, boolean bl) {
        return this.newHandleItem((int)n2, (String)string, (String)string2, (String)string3, (boolean)bl).index;
    }

    Item newInvokeDynamicItem(String string, String string2, Handle handle, Object ... arrobject) {
        int n2;
        ByteVector byteVector = this.bootstrapMethods;
        if (byteVector == null) {
            byteVector = this.bootstrapMethods = new ByteVector();
        }
        int n3 = byteVector.length;
        int n4 = handle.hashCode();
        byteVector.putShort(this.newHandle(handle.tag, handle.owner, handle.name, handle.desc, handle.isInterface()));
        int n5 = arrobject.length;
        byteVector.putShort(n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            Object object = arrobject[i2];
            n4 ^= object.hashCode();
            byteVector.putShort(this.newConst(object));
        }
        byte[] arrby = byteVector.data;
        int n6 = 2 + n5 << 1;
        Item item = this.items[(n4 &= Integer.MAX_VALUE) % this.items.length];
        block1: while (item != null) {
            if (item.type != 33 || item.hashCode != n4) {
                item = item.next;
                continue;
            }
            n2 = item.intVal;
            for (int i3 = 0; i3 < n6; ++i3) {
                if (arrby[n3 + i3] == arrby[n2 + i3]) continue;
                item = item.next;
                continue block1;
            }
        }
        if (item != null) {
            n2 = item.index;
            byteVector.length = n3;
        } else {
            n2 = this.bootstrapMethodsCount++;
            item = new Item(n2);
            item.set(n3, n4);
            this.put(item);
        }
        this.key3.set(string, string2, n2);
        item = this.get(this.key3);
        if (item == null) {
            this.put122(18, n2, this.newNameType(string, string2));
            item = new Item(this.index++, this.key3);
            this.put(item);
        }
        return item;
    }

    public int newInvokeDynamic(String string, String string2, Handle handle, Object ... arrobject) {
        return this.newInvokeDynamicItem((String)string, (String)string2, (Handle)handle, (Object[])arrobject).index;
    }

    Item newFieldItem(String string, String string2, String string3) {
        this.key3.set(9, string, string2, string3);
        Item item = this.get(this.key3);
        if (item == null) {
            this.put122(9, this.newClass(string), this.newNameType(string2, string3));
            item = new Item(this.index++, this.key3);
            this.put(item);
        }
        return item;
    }

    public int newField(String string, String string2, String string3) {
        return this.newFieldItem((String)string, (String)string2, (String)string3).index;
    }

    Item newMethodItem(String string, String string2, String string3, boolean bl) {
        int n2 = bl ? 11 : 10;
        this.key3.set(n2, string, string2, string3);
        Item item = this.get(this.key3);
        if (item == null) {
            this.put122(n2, this.newClass(string), this.newNameType(string2, string3));
            item = new Item(this.index++, this.key3);
            this.put(item);
        }
        return item;
    }

    public int newMethod(String string, String string2, String string3, boolean bl) {
        return this.newMethodItem((String)string, (String)string2, (String)string3, (boolean)bl).index;
    }

    Item newInteger(int n2) {
        this.key.set(n2);
        Item item = this.get(this.key);
        if (item == null) {
            this.pool.putByte(3).putInt(n2);
            item = new Item(this.index++, this.key);
            this.put(item);
        }
        return item;
    }

    Item newFloat(float f2) {
        this.key.set(f2);
        Item item = this.get(this.key);
        if (item == null) {
            this.pool.putByte(4).putInt(this.key.intVal);
            item = new Item(this.index++, this.key);
            this.put(item);
        }
        return item;
    }

    Item newLong(long l2) {
        this.key.set(l2);
        Item item = this.get(this.key);
        if (item == null) {
            this.pool.putByte(5).putLong(l2);
            item = new Item(this.index, this.key);
            this.index += 2;
            this.put(item);
        }
        return item;
    }

    Item newDouble(double d2) {
        this.key.set(d2);
        Item item = this.get(this.key);
        if (item == null) {
            this.pool.putByte(6).putLong(this.key.longVal);
            item = new Item(this.index, this.key);
            this.index += 2;
            this.put(item);
        }
        return item;
    }

    private Item newString(String string) {
        this.key2.set(8, string, null, null);
        Item item = this.get(this.key2);
        if (item == null) {
            this.pool.put12(8, this.newUTF8(string));
            item = new Item(this.index++, this.key2);
            this.put(item);
        }
        return item;
    }

    public int newNameType(String string, String string2) {
        return this.newNameTypeItem((String)string, (String)string2).index;
    }

    Item newNameTypeItem(String string, String string2) {
        this.key2.set(12, string, string2, null);
        Item item = this.get(this.key2);
        if (item == null) {
            this.put122(12, this.newUTF8(string), this.newUTF8(string2));
            item = new Item(this.index++, this.key2);
            this.put(item);
        }
        return item;
    }

    int addType(String string) {
        this.key.set(30, string, null, null);
        Item item = this.get(this.key);
        if (item == null) {
            item = this.addType(this.key);
        }
        return item.index;
    }

    int addUninitializedType(String string, int n2) {
        this.key.type = 31;
        this.key.intVal = n2;
        this.key.strVal1 = string;
        this.key.hashCode = Integer.MAX_VALUE & 31 + string.hashCode() + n2;
        Item item = this.get(this.key);
        if (item == null) {
            item = this.addType(this.key);
        }
        return item.index;
    }

    private Item addType(Item item) {
        this.typeCount = (short)(this.typeCount + 1);
        Item item2 = new Item(this.typeCount, this.key);
        this.put(item2);
        if (this.typeTable == null) {
            this.typeTable = new Item[16];
        }
        if (this.typeCount == this.typeTable.length) {
            Item[] arritem = new Item[2 * this.typeTable.length];
            System.arraycopy(this.typeTable, 0, arritem, 0, this.typeTable.length);
            this.typeTable = arritem;
        }
        this.typeTable[this.typeCount] = item2;
        return item2;
    }

    int getMergedType(int n2, int n3) {
        this.key2.type = 32;
        this.key2.longVal = (long)n2 | (long)n3 << 32;
        this.key2.hashCode = Integer.MAX_VALUE & 32 + n2 + n3;
        Item item = this.get(this.key2);
        if (item == null) {
            String string = this.typeTable[n2].strVal1;
            String string2 = this.typeTable[n3].strVal1;
            this.key2.intVal = this.addType(this.getCommonSuperClass(string, string2));
            item = new Item(0, this.key2);
            this.put(item);
        }
        return item.intVal;
    }

    protected String getCommonSuperClass(String string, String string2) {
        Class<?> class_;
        Class<?> class_2;
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            class_2 = Class.forName(string.replace('/', '.'), false, classLoader);
            class_ = Class.forName(string2.replace('/', '.'), false, classLoader);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception.toString());
        }
        if (class_2.isAssignableFrom(class_)) {
            return string;
        }
        if (class_.isAssignableFrom(class_2)) {
            return string2;
        }
        if (class_2.isInterface() || class_.isInterface()) {
            return "java/lang/Object";
        }
        while (!(class_2 = class_2.getSuperclass()).isAssignableFrom(class_)) {
        }
        return class_2.getName().replace('.', '/');
    }

    private Item get(Item item) {
        Item item2 = this.items[item.hashCode % this.items.length];
        while (!(item2 == null || item2.type == item.type && item.isEqualTo(item2))) {
            item2 = item2.next;
        }
        return item2;
    }

    private void put(Item item) {
        int n2;
        if (this.index + this.typeCount > this.threshold) {
            n2 = this.items.length;
            int n3 = n2 * 2 + 1;
            Item[] arritem = new Item[n3];
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                Item item2 = this.items[i2];
                while (item2 != null) {
                    int n4 = item2.hashCode % arritem.length;
                    Item item3 = item2.next;
                    item2.next = arritem[n4];
                    arritem[n4] = item2;
                    item2 = item3;
                }
            }
            this.items = arritem;
            this.threshold = (int)((double)n3 * 0.75);
        }
        n2 = item.hashCode % this.items.length;
        item.next = this.items[n2];
        this.items[n2] = item;
    }

    private void put122(int n2, int n3, int n4) {
        this.pool.put12(n2, n3).putShort(n4);
    }

    private void put112(int n2, int n3, int n4) {
        this.pool.put11(n2, n3).putShort(n4);
    }

    static {
        byte[] arrby = new byte[220];
        String string = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKSSSSSSSSSSSSSSSSSS";
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            arrby[i2] = (byte)(string.charAt(i2) - 65);
        }
        TYPE = arrby;
    }
}

