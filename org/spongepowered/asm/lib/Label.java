/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.Edge;
import org.spongepowered.asm.lib.Frame;
import org.spongepowered.asm.lib.MethodWriter;

public class Label {
    static final int DEBUG = 1;
    static final int RESOLVED = 2;
    static final int RESIZED = 4;
    static final int PUSHED = 8;
    static final int TARGET = 16;
    static final int STORE = 32;
    static final int REACHABLE = 64;
    static final int JSR = 128;
    static final int RET = 256;
    static final int SUBROUTINE = 512;
    static final int VISITED = 1024;
    static final int VISITED2 = 2048;
    public Object info;
    int status;
    int line;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int inputStackTop;
    int outputStackMax;
    Frame frame;
    Label successor;
    Edge successors;
    Label next;

    public int getOffset() {
        if ((this.status & 2) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.position;
    }

    void put(MethodWriter methodWriter, ByteVector byteVector, int n2, boolean bl) {
        if ((this.status & 2) == 0) {
            if (bl) {
                this.addReference(-1 - n2, byteVector.length);
                byteVector.putInt(-1);
            } else {
                this.addReference(n2, byteVector.length);
                byteVector.putShort(-1);
            }
        } else if (bl) {
            byteVector.putInt(this.position - n2);
        } else {
            byteVector.putShort(this.position - n2);
        }
    }

    private void addReference(int n2, int n3) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            int[] arrn = new int[this.srcAndRefPositions.length + 6];
            System.arraycopy(this.srcAndRefPositions, 0, arrn, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = arrn;
        }
        this.srcAndRefPositions[this.referenceCount++] = n2;
        this.srcAndRefPositions[this.referenceCount++] = n3;
    }

    boolean resolve(MethodWriter methodWriter, int n2, byte[] arrby) {
        boolean bl = false;
        this.status |= 2;
        this.position = n2;
        int n3 = 0;
        while (n3 < this.referenceCount) {
            int n4;
            int n5 = this.srcAndRefPositions[n3++];
            int n6 = this.srcAndRefPositions[n3++];
            if (n5 >= 0) {
                n4 = n2 - n5;
                if (n4 < -32768 || n4 > 32767) {
                    int n7 = arrby[n6 - 1] & 0xFF;
                    arrby[n6 - 1] = n7 <= 168 ? (byte)(n7 + 49) : (byte)(n7 + 20);
                    bl = true;
                }
                arrby[n6++] = (byte)(n4 >>> 8);
                arrby[n6] = (byte)n4;
                continue;
            }
            n4 = n2 + n5 + 1;
            arrby[n6++] = (byte)(n4 >>> 24);
            arrby[n6++] = (byte)(n4 >>> 16);
            arrby[n6++] = (byte)(n4 >>> 8);
            arrby[n6] = (byte)n4;
        }
        return bl;
    }

    Label getFirst() {
        return this.frame == null ? this : this.frame.owner;
    }

    boolean inSubroutine(long l2) {
        if ((this.status & 0x400) != 0) {
            return (this.srcAndRefPositions[(int)(l2 >>> 32)] & (int)l2) != 0;
        }
        return false;
    }

    boolean inSameSubroutine(Label label) {
        if ((this.status & 0x400) == 0 || (label.status & 0x400) == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.srcAndRefPositions.length; ++i2) {
            if ((this.srcAndRefPositions[i2] & label.srcAndRefPositions[i2]) == 0) continue;
            return true;
        }
        return false;
    }

    void addToSubroutine(long l2, int n2) {
        if ((this.status & 0x400) == 0) {
            this.status |= 0x400;
            this.srcAndRefPositions = new int[n2 / 32 + 1];
        }
        int n3 = (int)(l2 >>> 32);
        this.srcAndRefPositions[n3] = this.srcAndRefPositions[n3] | (int)l2;
    }

    void visitSubroutine(Label label, long l2, int n2) {
        Label label2 = this;
        while (label2 != null) {
            Edge edge;
            Label label3 = label2;
            label2 = label3.next;
            label3.next = null;
            if (label != null) {
                if ((label3.status & 0x800) != 0) continue;
                label3.status |= 0x800;
                if ((label3.status & 0x100) != 0 && !label3.inSameSubroutine(label)) {
                    edge = new Edge();
                    edge.info = label3.inputStackTop;
                    edge.successor = label.successors.successor;
                    edge.next = label3.successors;
                    label3.successors = edge;
                }
            } else {
                if (label3.inSubroutine(l2)) continue;
                label3.addToSubroutine(l2, n2);
            }
            edge = label3.successors;
            while (edge != null) {
                if (((label3.status & 0x80) == 0 || edge != label3.successors.next) && edge.successor.next == null) {
                    edge.successor.next = label2;
                    label2 = edge.successor;
                }
                edge = edge.next;
            }
        }
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}

