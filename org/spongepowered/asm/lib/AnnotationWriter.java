/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.Item;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;

final class AnnotationWriter
extends AnnotationVisitor {
    private final ClassWriter cw;
    private int size;
    private final boolean named;
    private final ByteVector bv;
    private final ByteVector parent;
    private final int offset;
    AnnotationWriter next;
    AnnotationWriter prev;

    AnnotationWriter(ClassWriter classWriter, boolean bl, ByteVector byteVector, ByteVector byteVector2, int n2) {
        super(327680);
        this.cw = classWriter;
        this.named = bl;
        this.bv = byteVector;
        this.parent = byteVector2;
        this.offset = n2;
    }

    public void visit(String string, Object object) {
        ++this.size;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(string));
        }
        if (object instanceof String) {
            this.bv.put12(115, this.cw.newUTF8((String)object));
        } else if (object instanceof Byte) {
            this.bv.put12(66, this.cw.newInteger((int)((Byte)object).byteValue()).index);
        } else if (object instanceof Boolean) {
            int n2 = (Boolean)object != false ? 1 : 0;
            this.bv.put12(90, this.cw.newInteger((int)n2).index);
        } else if (object instanceof Character) {
            this.bv.put12(67, this.cw.newInteger((int)((Character)object).charValue()).index);
        } else if (object instanceof Short) {
            this.bv.put12(83, this.cw.newInteger((int)((Short)object).shortValue()).index);
        } else if (object instanceof Type) {
            this.bv.put12(99, this.cw.newUTF8(((Type)object).getDescriptor()));
        } else if (object instanceof byte[]) {
            byte[] arrby = (byte[])object;
            this.bv.put12(91, arrby.length);
            for (int i2 = 0; i2 < arrby.length; ++i2) {
                this.bv.put12(66, this.cw.newInteger((int)arrby[i2]).index);
            }
        } else if (object instanceof boolean[]) {
            boolean[] arrbl = (boolean[])object;
            this.bv.put12(91, arrbl.length);
            for (int i3 = 0; i3 < arrbl.length; ++i3) {
                this.bv.put12(90, this.cw.newInteger((int)(arrbl[i3] ? 1 : 0)).index);
            }
        } else if (object instanceof short[]) {
            short[] arrs = (short[])object;
            this.bv.put12(91, arrs.length);
            for (int i4 = 0; i4 < arrs.length; ++i4) {
                this.bv.put12(83, this.cw.newInteger((int)arrs[i4]).index);
            }
        } else if (object instanceof char[]) {
            char[] arrc = (char[])object;
            this.bv.put12(91, arrc.length);
            for (int i5 = 0; i5 < arrc.length; ++i5) {
                this.bv.put12(67, this.cw.newInteger((int)arrc[i5]).index);
            }
        } else if (object instanceof int[]) {
            int[] arrn = (int[])object;
            this.bv.put12(91, arrn.length);
            for (int i6 = 0; i6 < arrn.length; ++i6) {
                this.bv.put12(73, this.cw.newInteger((int)arrn[i6]).index);
            }
        } else if (object instanceof long[]) {
            long[] arrl = (long[])object;
            this.bv.put12(91, arrl.length);
            for (int i7 = 0; i7 < arrl.length; ++i7) {
                this.bv.put12(74, this.cw.newLong((long)arrl[i7]).index);
            }
        } else if (object instanceof float[]) {
            float[] arrf = (float[])object;
            this.bv.put12(91, arrf.length);
            for (int i8 = 0; i8 < arrf.length; ++i8) {
                this.bv.put12(70, this.cw.newFloat((float)arrf[i8]).index);
            }
        } else if (object instanceof double[]) {
            double[] arrd = (double[])object;
            this.bv.put12(91, arrd.length);
            for (int i9 = 0; i9 < arrd.length; ++i9) {
                this.bv.put12(68, this.cw.newDouble((double)arrd[i9]).index);
            }
        } else {
            Item item = this.cw.newConstItem(object);
            this.bv.put12(".s.IFJDCS".charAt(item.type), item.index);
        }
    }

    public void visitEnum(String string, String string2, String string3) {
        ++this.size;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(string));
        }
        this.bv.put12(101, this.cw.newUTF8(string2)).putShort(this.cw.newUTF8(string3));
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        ++this.size;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(string));
        }
        this.bv.put12(64, this.cw.newUTF8(string2)).putShort(0);
        return new AnnotationWriter(this.cw, true, this.bv, this.bv, this.bv.length - 2);
    }

    public AnnotationVisitor visitArray(String string) {
        ++this.size;
        if (this.named) {
            this.bv.putShort(this.cw.newUTF8(string));
        }
        this.bv.put12(91, 0);
        return new AnnotationWriter(this.cw, false, this.bv, this.bv, this.bv.length - 2);
    }

    public void visitEnd() {
        if (this.parent != null) {
            byte[] arrby = this.parent.data;
            arrby[this.offset] = (byte)(this.size >>> 8);
            arrby[this.offset + 1] = (byte)this.size;
        }
    }

    int getSize() {
        int n2 = 0;
        AnnotationWriter annotationWriter = this;
        while (annotationWriter != null) {
            n2 += annotationWriter.bv.length;
            annotationWriter = annotationWriter.next;
        }
        return n2;
    }

    void put(ByteVector byteVector) {
        int n2 = 0;
        int n3 = 2;
        AnnotationWriter annotationWriter = this;
        AnnotationWriter annotationWriter2 = null;
        while (annotationWriter != null) {
            ++n2;
            n3 += annotationWriter.bv.length;
            annotationWriter.visitEnd();
            annotationWriter.prev = annotationWriter2;
            annotationWriter2 = annotationWriter;
            annotationWriter = annotationWriter.next;
        }
        byteVector.putInt(n3);
        byteVector.putShort(n2);
        annotationWriter = annotationWriter2;
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.bv.data, 0, annotationWriter.bv.length);
            annotationWriter = annotationWriter.prev;
        }
    }

    static void put(AnnotationWriter[] arrannotationWriter, int n2, ByteVector byteVector) {
        int n3;
        int n4 = 1 + 2 * (arrannotationWriter.length - n2);
        for (n3 = n2; n3 < arrannotationWriter.length; ++n3) {
            n4 += arrannotationWriter[n3] == null ? 0 : arrannotationWriter[n3].getSize();
        }
        byteVector.putInt(n4).putByte(arrannotationWriter.length - n2);
        for (n3 = n2; n3 < arrannotationWriter.length; ++n3) {
            AnnotationWriter annotationWriter = arrannotationWriter[n3];
            AnnotationWriter annotationWriter2 = null;
            int n5 = 0;
            while (annotationWriter != null) {
                ++n5;
                annotationWriter.visitEnd();
                annotationWriter.prev = annotationWriter2;
                annotationWriter2 = annotationWriter;
                annotationWriter = annotationWriter.next;
            }
            byteVector.putShort(n5);
            annotationWriter = annotationWriter2;
            while (annotationWriter != null) {
                byteVector.putByteArray(annotationWriter.bv.data, 0, annotationWriter.bv.length);
                annotationWriter = annotationWriter.prev;
            }
        }
    }

    static void putTarget(int n2, TypePath typePath, ByteVector byteVector) {
        switch (n2 >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                byteVector.putShort(n2 >>> 16);
                break;
            }
            case 19: 
            case 20: 
            case 21: {
                byteVector.putByte(n2 >>> 24);
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                byteVector.putInt(n2);
                break;
            }
            default: {
                byteVector.put12(n2 >>> 24, (n2 & 0xFFFF00) >> 8);
            }
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            int n3 = typePath.b[typePath.offset] * 2 + 1;
            byteVector.putByteArray(typePath.b, typePath.offset, n3);
        }
    }
}

