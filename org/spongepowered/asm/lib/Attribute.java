/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.Label;

public class Attribute {
    public final String type;
    byte[] value;
    Attribute next;

    protected Attribute(String string) {
        this.type = string;
    }

    public boolean isUnknown() {
        return true;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    protected Label[] getLabels() {
        return null;
    }

    protected Attribute read(ClassReader classReader, int n2, int n3, char[] arrc, int n4, Label[] arrlabel) {
        Attribute attribute = new Attribute(this.type);
        attribute.value = new byte[n3];
        System.arraycopy(classReader.b, n2, attribute.value, 0, n3);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] arrby, int n2, int n3, int n4) {
        ByteVector byteVector = new ByteVector();
        byteVector.data = this.value;
        byteVector.length = this.value.length;
        return byteVector;
    }

    final int getCount() {
        int n2 = 0;
        Attribute attribute = this;
        while (attribute != null) {
            ++n2;
            attribute = attribute.next;
        }
        return n2;
    }

    final int getSize(ClassWriter classWriter, byte[] arrby, int n2, int n3, int n4) {
        Attribute attribute = this;
        int n5 = 0;
        while (attribute != null) {
            classWriter.newUTF8(attribute.type);
            n5 += attribute.write((ClassWriter)classWriter, (byte[])arrby, (int)n2, (int)n3, (int)n4).length + 6;
            attribute = attribute.next;
        }
        return n5;
    }

    final void put(ClassWriter classWriter, byte[] arrby, int n2, int n3, int n4, ByteVector byteVector) {
        Attribute attribute = this;
        while (attribute != null) {
            ByteVector byteVector2 = attribute.write(classWriter, arrby, n2, n3, n4);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(byteVector2.length);
            byteVector.putByteArray(byteVector2.data, 0, byteVector2.length);
            attribute = attribute.next;
        }
    }
}

