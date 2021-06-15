/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.AnnotationWriter;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ByteVector;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.CurrentFrame;
import org.spongepowered.asm.lib.Edge;
import org.spongepowered.asm.lib.Frame;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Handler;
import org.spongepowered.asm.lib.Item;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;

class MethodWriter
extends MethodVisitor {
    static final int ACC_CONSTRUCTOR = 524288;
    static final int SAME_FRAME = 0;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int RESERVED = 128;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    static final int CHOP_FRAME = 248;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int APPEND_FRAME = 252;
    static final int FULL_FRAME = 255;
    static final int FRAMES = 0;
    static final int INSERTED_FRAMES = 1;
    static final int MAXS = 2;
    static final int NOTHING = 3;
    final ClassWriter cw;
    private int access;
    private final int name;
    private final int desc;
    private final String descriptor;
    String signature;
    int classReaderOffset;
    int classReaderLength;
    int exceptionCount;
    int[] exceptions;
    private ByteVector annd;
    private AnnotationWriter anns;
    private AnnotationWriter ianns;
    private AnnotationWriter tanns;
    private AnnotationWriter itanns;
    private AnnotationWriter[] panns;
    private AnnotationWriter[] ipanns;
    private int synthetics;
    private Attribute attrs;
    private ByteVector code = new ByteVector();
    private int maxStack;
    private int maxLocals;
    private int currentLocals;
    private int frameCount;
    private ByteVector stackMap;
    private int previousFrameOffset;
    private int[] previousFrame;
    private int[] frame;
    private int handlerCount;
    private Handler firstHandler;
    private Handler lastHandler;
    private int methodParametersCount;
    private ByteVector methodParameters;
    private int localVarCount;
    private ByteVector localVar;
    private int localVarTypeCount;
    private ByteVector localVarType;
    private int lineNumberCount;
    private ByteVector lineNumber;
    private int lastCodeOffset;
    private AnnotationWriter ctanns;
    private AnnotationWriter ictanns;
    private Attribute cattrs;
    private int subroutines;
    private final int compute;
    private Label labels;
    private Label previousBlock;
    private Label currentBlock;
    private int stackSize;
    private int maxStackSize;

    MethodWriter(ClassWriter classWriter, int n2, String string, String string2, String string3, String[] arrstring, int n3) {
        super(327680);
        int n4;
        if (classWriter.firstMethod == null) {
            classWriter.firstMethod = this;
        } else {
            classWriter.lastMethod.mv = this;
        }
        classWriter.lastMethod = this;
        this.cw = classWriter;
        this.access = n2;
        if ("<init>".equals(string)) {
            this.access |= 0x80000;
        }
        this.name = classWriter.newUTF8(string);
        this.desc = classWriter.newUTF8(string2);
        this.descriptor = string2;
        this.signature = string3;
        if (arrstring != null && arrstring.length > 0) {
            this.exceptionCount = arrstring.length;
            this.exceptions = new int[this.exceptionCount];
            for (n4 = 0; n4 < this.exceptionCount; ++n4) {
                this.exceptions[n4] = classWriter.newClass(arrstring[n4]);
            }
        }
        this.compute = n3;
        if (n3 != 3) {
            n4 = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
            if ((n2 & 8) != 0) {
                --n4;
            }
            this.maxLocals = n4;
            this.currentLocals = n4;
            this.labels = new Label();
            this.labels.status |= 8;
            this.visitLabel(this.labels);
        }
    }

    public void visitParameter(String string, int n2) {
        if (this.methodParameters == null) {
            this.methodParameters = new ByteVector();
        }
        ++this.methodParametersCount;
        this.methodParameters.putShort(string == null ? 0 : this.cw.newUTF8(string)).putShort(n2);
    }

    public AnnotationVisitor visitAnnotationDefault() {
        this.annd = new ByteVector();
        return new AnnotationWriter(this.cw, false, this.annd, null, 0);
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
        if (bl) {
            annotationWriter.next = this.anns;
            this.anns = annotationWriter;
        } else {
            annotationWriter.next = this.ianns;
            this.ianns = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(n2, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (bl) {
            annotationWriter.next = this.tanns;
            this.tanns = annotationWriter;
        } else {
            annotationWriter.next = this.itanns;
            this.itanns = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor visitParameterAnnotation(int n2, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(string)) {
            this.synthetics = Math.max(this.synthetics, n2 + 1);
            return new AnnotationWriter(this.cw, false, byteVector, null, 0);
        }
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
        if (bl) {
            if (this.panns == null) {
                this.panns = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
            }
            annotationWriter.next = this.panns[n2];
            this.panns[n2] = annotationWriter;
        } else {
            if (this.ipanns == null) {
                this.ipanns = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
            }
            annotationWriter.next = this.ipanns[n2];
            this.ipanns[n2] = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.next = this.cattrs;
            this.cattrs = attribute;
        } else {
            attribute.next = this.attrs;
            this.attrs = attribute;
        }
    }

    public void visitCode() {
    }

    public void visitFrame(int n2, int n3, Object[] arrobject, int n4, Object[] arrobject2) {
        if (this.compute == 0) {
            return;
        }
        if (this.compute == 1) {
            if (this.currentBlock.frame == null) {
                this.currentBlock.frame = new CurrentFrame();
                this.currentBlock.frame.owner = this.currentBlock;
                this.currentBlock.frame.initInputFrame(this.cw, this.access, Type.getArgumentTypes(this.descriptor), n3);
                this.visitImplicitFirstFrame();
            } else {
                if (n2 == -1) {
                    this.currentBlock.frame.set(this.cw, n3, arrobject, n4, arrobject2);
                }
                this.visitFrame(this.currentBlock.frame);
            }
        } else if (n2 == -1) {
            int n5;
            if (this.previousFrame == null) {
                this.visitImplicitFirstFrame();
            }
            this.currentLocals = n3;
            int n6 = this.startFrame(this.code.length, n3, n4);
            for (n5 = 0; n5 < n3; ++n5) {
                this.frame[n6++] = arrobject[n5] instanceof String ? 0x1700000 | this.cw.addType((String)arrobject[n5]) : (arrobject[n5] instanceof Integer ? (Integer)arrobject[n5] : 0x1800000 | this.cw.addUninitializedType("", ((Label)arrobject[n5]).position));
            }
            for (n5 = 0; n5 < n4; ++n5) {
                this.frame[n6++] = arrobject2[n5] instanceof String ? 0x1700000 | this.cw.addType((String)arrobject2[n5]) : (arrobject2[n5] instanceof Integer ? (Integer)arrobject2[n5] : 0x1800000 | this.cw.addUninitializedType("", ((Label)arrobject2[n5]).position));
            }
            this.endFrame();
        } else {
            int n7;
            if (this.stackMap == null) {
                this.stackMap = new ByteVector();
                n7 = this.code.length;
            } else {
                n7 = this.code.length - this.previousFrameOffset - 1;
                if (n7 < 0) {
                    if (n2 == 3) {
                        return;
                    }
                    throw new IllegalStateException();
                }
            }
            switch (n2) {
                case 0: {
                    int n8;
                    this.currentLocals = n3;
                    this.stackMap.putByte(255).putShort(n7).putShort(n3);
                    for (n8 = 0; n8 < n3; ++n8) {
                        this.writeFrameType(arrobject[n8]);
                    }
                    this.stackMap.putShort(n4);
                    for (n8 = 0; n8 < n4; ++n8) {
                        this.writeFrameType(arrobject2[n8]);
                    }
                    break;
                }
                case 1: {
                    this.currentLocals += n3;
                    this.stackMap.putByte(251 + n3).putShort(n7);
                    for (int i2 = 0; i2 < n3; ++i2) {
                        this.writeFrameType(arrobject[i2]);
                    }
                    break;
                }
                case 2: {
                    this.currentLocals -= n3;
                    this.stackMap.putByte(251 - n3).putShort(n7);
                    break;
                }
                case 3: {
                    if (n7 < 64) {
                        this.stackMap.putByte(n7);
                        break;
                    }
                    this.stackMap.putByte(251).putShort(n7);
                    break;
                }
                case 4: {
                    if (n7 < 64) {
                        this.stackMap.putByte(64 + n7);
                    } else {
                        this.stackMap.putByte(247).putShort(n7);
                    }
                    this.writeFrameType(arrobject2[0]);
                }
            }
            this.previousFrameOffset = this.code.length;
            ++this.frameCount;
        }
        this.maxStack = Math.max(this.maxStack, n4);
        this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
    }

    public void visitInsn(int n2) {
        this.lastCodeOffset = this.code.length;
        this.code.putByte(n2);
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, 0, null, null);
            } else {
                int n3 = this.stackSize + Frame.SIZE[n2];
                if (n3 > this.maxStackSize) {
                    this.maxStackSize = n3;
                }
                this.stackSize = n3;
            }
            if (n2 >= 172 && n2 <= 177 || n2 == 191) {
                this.noSuccessor();
            }
        }
    }

    public void visitIntInsn(int n2, int n3) {
        this.lastCodeOffset = this.code.length;
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, n3, null, null);
            } else if (n2 != 188) {
                int n4 = this.stackSize + 1;
                if (n4 > this.maxStackSize) {
                    this.maxStackSize = n4;
                }
                this.stackSize = n4;
            }
        }
        if (n2 == 17) {
            this.code.put12(n2, n3);
        } else {
            this.code.put11(n2, n3);
        }
    }

    public void visitVarInsn(int n2, int n3) {
        int n4;
        this.lastCodeOffset = this.code.length;
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, n3, null, null);
            } else if (n2 == 169) {
                this.currentBlock.status |= 0x100;
                this.currentBlock.inputStackTop = this.stackSize;
                this.noSuccessor();
            } else {
                n4 = this.stackSize + Frame.SIZE[n2];
                if (n4 > this.maxStackSize) {
                    this.maxStackSize = n4;
                }
                this.stackSize = n4;
            }
        }
        if (this.compute != 3 && (n4 = n2 == 22 || n2 == 24 || n2 == 55 || n2 == 57 ? n3 + 2 : n3 + 1) > this.maxLocals) {
            this.maxLocals = n4;
        }
        if (n3 < 4 && n2 != 169) {
            n4 = n2 < 54 ? 26 + (n2 - 21 << 2) + n3 : 59 + (n2 - 54 << 2) + n3;
            this.code.putByte(n4);
        } else if (n3 >= 256) {
            this.code.putByte(196).put12(n2, n3);
        } else {
            this.code.put11(n2, n3);
        }
        if (n2 >= 54 && this.compute == 0 && this.handlerCount > 0) {
            this.visitLabel(new Label());
        }
    }

    public void visitTypeInsn(int n2, String string) {
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newClassItem(string);
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, this.code.length, this.cw, item);
            } else if (n2 == 187) {
                int n3 = this.stackSize + 1;
                if (n3 > this.maxStackSize) {
                    this.maxStackSize = n3;
                }
                this.stackSize = n3;
            }
        }
        this.code.put12(n2, item.index);
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newFieldItem(string, string2, string3);
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, 0, this.cw, item);
            } else {
                int n3;
                char c2 = string3.charAt(0);
                switch (n2) {
                    case 178: {
                        n3 = this.stackSize + (c2 == 'D' || c2 == 'J' ? 2 : 1);
                        break;
                    }
                    case 179: {
                        n3 = this.stackSize + (c2 == 'D' || c2 == 'J' ? -2 : -1);
                        break;
                    }
                    case 180: {
                        n3 = this.stackSize + (c2 == 'D' || c2 == 'J' ? 1 : 0);
                        break;
                    }
                    default: {
                        n3 = this.stackSize + (c2 == 'D' || c2 == 'J' ? -3 : -2);
                    }
                }
                if (n3 > this.maxStackSize) {
                    this.maxStackSize = n3;
                }
                this.stackSize = n3;
            }
        }
        this.code.put12(n2, item.index);
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3, boolean bl) {
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newMethodItem(string, string2, string3, bl);
        int n3 = item.intVal;
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(n2, 0, this.cw, item);
            } else {
                int n4;
                if (n3 == 0) {
                    item.intVal = n3 = Type.getArgumentsAndReturnSizes(string3);
                }
                if ((n4 = n2 == 184 ? this.stackSize - (n3 >> 2) + (n3 & 3) + 1 : this.stackSize - (n3 >> 2) + (n3 & 3)) > this.maxStackSize) {
                    this.maxStackSize = n4;
                }
                this.stackSize = n4;
            }
        }
        if (n2 == 185) {
            if (n3 == 0) {
                item.intVal = n3 = Type.getArgumentsAndReturnSizes(string3);
            }
            this.code.put12(185, item.index).put11(n3 >> 2, 0);
        } else {
            this.code.put12(n2, item.index);
        }
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... arrobject) {
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newInvokeDynamicItem(string, string2, handle, arrobject);
        int n2 = item.intVal;
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(186, 0, this.cw, item);
            } else {
                int n3;
                if (n2 == 0) {
                    item.intVal = n2 = Type.getArgumentsAndReturnSizes(string2);
                }
                if ((n3 = this.stackSize - (n2 >> 2) + (n2 & 3) + 1) > this.maxStackSize) {
                    this.maxStackSize = n3;
                }
                this.stackSize = n3;
            }
        }
        this.code.put12(186, item.index);
        this.code.putShort(0);
    }

    public void visitJumpInsn(int n2, Label label) {
        boolean bl = n2 >= 200;
        n2 = bl ? n2 - 33 : n2;
        this.lastCodeOffset = this.code.length;
        Label label2 = null;
        if (this.currentBlock != null) {
            if (this.compute == 0) {
                this.currentBlock.frame.execute(n2, 0, null, null);
                label.getFirst().status |= 0x10;
                this.addSuccessor(0, label);
                if (n2 != 167) {
                    label2 = new Label();
                }
            } else if (this.compute == 1) {
                this.currentBlock.frame.execute(n2, 0, null, null);
            } else if (n2 == 168) {
                if ((label.status & 0x200) == 0) {
                    label.status |= 0x200;
                    ++this.subroutines;
                }
                this.currentBlock.status |= 0x80;
                this.addSuccessor(this.stackSize + 1, label);
                label2 = new Label();
            } else {
                this.stackSize += Frame.SIZE[n2];
                this.addSuccessor(this.stackSize, label);
            }
        }
        if ((label.status & 2) != 0 && label.position - this.code.length < -32768) {
            if (n2 == 167) {
                this.code.putByte(200);
            } else if (n2 == 168) {
                this.code.putByte(201);
            } else {
                if (label2 != null) {
                    label2.status |= 0x10;
                }
                this.code.putByte(n2 <= 166 ? (n2 + 1 ^ 1) - 1 : n2 ^ 1);
                this.code.putShort(8);
                this.code.putByte(200);
            }
            label.put(this, this.code, this.code.length - 1, true);
        } else if (bl) {
            this.code.putByte(n2 + 33);
            label.put(this, this.code, this.code.length - 1, true);
        } else {
            this.code.putByte(n2);
            label.put(this, this.code, this.code.length - 1, false);
        }
        if (this.currentBlock != null) {
            if (label2 != null) {
                this.visitLabel(label2);
            }
            if (n2 == 167) {
                this.noSuccessor();
            }
        }
    }

    public void visitLabel(Label label) {
        this.cw.hasAsmInsns |= label.resolve(this, this.code.length, this.code.data);
        if ((label.status & 1) != 0) {
            return;
        }
        if (this.compute == 0) {
            if (this.currentBlock != null) {
                if (label.position == this.currentBlock.position) {
                    this.currentBlock.status |= label.status & 0x10;
                    label.frame = this.currentBlock.frame;
                    return;
                }
                this.addSuccessor(0, label);
            }
            this.currentBlock = label;
            if (label.frame == null) {
                label.frame = new Frame();
                label.frame.owner = label;
            }
            if (this.previousBlock != null) {
                if (label.position == this.previousBlock.position) {
                    this.previousBlock.status |= label.status & 0x10;
                    label.frame = this.previousBlock.frame;
                    this.currentBlock = this.previousBlock;
                    return;
                }
                this.previousBlock.successor = label;
            }
            this.previousBlock = label;
        } else if (this.compute == 1) {
            if (this.currentBlock == null) {
                this.currentBlock = label;
            } else {
                this.currentBlock.frame.owner = label;
            }
        } else if (this.compute == 2) {
            if (this.currentBlock != null) {
                this.currentBlock.outputStackMax = this.maxStackSize;
                this.addSuccessor(this.stackSize, label);
            }
            this.currentBlock = label;
            this.stackSize = 0;
            this.maxStackSize = 0;
            if (this.previousBlock != null) {
                this.previousBlock.successor = label;
            }
            this.previousBlock = label;
        }
    }

    public void visitLdcInsn(Object object) {
        int n2;
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newConstItem(object);
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(18, 0, this.cw, item);
            } else {
                n2 = item.type == 5 || item.type == 6 ? this.stackSize + 2 : this.stackSize + 1;
                if (n2 > this.maxStackSize) {
                    this.maxStackSize = n2;
                }
                this.stackSize = n2;
            }
        }
        n2 = item.index;
        if (item.type == 5 || item.type == 6) {
            this.code.put12(20, n2);
        } else if (n2 >= 256) {
            this.code.put12(19, n2);
        } else {
            this.code.put11(18, n2);
        }
    }

    public void visitIincInsn(int n2, int n3) {
        int n4;
        this.lastCodeOffset = this.code.length;
        if (this.currentBlock != null && (this.compute == 0 || this.compute == 1)) {
            this.currentBlock.frame.execute(132, n2, null, null);
        }
        if (this.compute != 3 && (n4 = n2 + 1) > this.maxLocals) {
            this.maxLocals = n4;
        }
        if (n2 > 255 || n3 > 127 || n3 < -128) {
            this.code.putByte(196).put12(132, n2).putShort(n3);
        } else {
            this.code.putByte(132).put11(n2, n3);
        }
    }

    public void visitTableSwitchInsn(int n2, int n3, Label label, Label ... arrlabel) {
        this.lastCodeOffset = this.code.length;
        int n4 = this.code.length;
        this.code.putByte(170);
        this.code.putByteArray(null, 0, (4 - this.code.length % 4) % 4);
        label.put(this, this.code, n4, true);
        this.code.putInt(n2).putInt(n3);
        for (int i2 = 0; i2 < arrlabel.length; ++i2) {
            arrlabel[i2].put(this, this.code, n4, true);
        }
        this.visitSwitchInsn(label, arrlabel);
    }

    public void visitLookupSwitchInsn(Label label, int[] arrn, Label[] arrlabel) {
        this.lastCodeOffset = this.code.length;
        int n2 = this.code.length;
        this.code.putByte(171);
        this.code.putByteArray(null, 0, (4 - this.code.length % 4) % 4);
        label.put(this, this.code, n2, true);
        this.code.putInt(arrlabel.length);
        for (int i2 = 0; i2 < arrlabel.length; ++i2) {
            this.code.putInt(arrn[i2]);
            arrlabel[i2].put(this, this.code, n2, true);
        }
        this.visitSwitchInsn(label, arrlabel);
    }

    private void visitSwitchInsn(Label label, Label[] arrlabel) {
        if (this.currentBlock != null) {
            if (this.compute == 0) {
                this.currentBlock.frame.execute(171, 0, null, null);
                this.addSuccessor(0, label);
                label.getFirst().status |= 0x10;
                for (int i2 = 0; i2 < arrlabel.length; ++i2) {
                    this.addSuccessor(0, arrlabel[i2]);
                    arrlabel[i2].getFirst().status |= 0x10;
                }
            } else {
                --this.stackSize;
                this.addSuccessor(this.stackSize, label);
                for (int i3 = 0; i3 < arrlabel.length; ++i3) {
                    this.addSuccessor(this.stackSize, arrlabel[i3]);
                }
            }
            this.noSuccessor();
        }
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        this.lastCodeOffset = this.code.length;
        Item item = this.cw.newClassItem(string);
        if (this.currentBlock != null) {
            if (this.compute == 0 || this.compute == 1) {
                this.currentBlock.frame.execute(197, n2, this.cw, item);
            } else {
                this.stackSize += 1 - n2;
            }
        }
        this.code.put12(197, item.index).putByte(n2);
    }

    public AnnotationVisitor visitInsnAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        n2 = n2 & 0xFF0000FF | this.lastCodeOffset << 8;
        AnnotationWriter.putTarget(n2, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (bl) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        ++this.handlerCount;
        Handler handler = new Handler();
        handler.start = label;
        handler.end = label2;
        handler.handler = label3;
        handler.desc = string;
        int n2 = handler.type = string != null ? this.cw.newClass(string) : 0;
        if (this.lastHandler == null) {
            this.firstHandler = handler;
        } else {
            this.lastHandler.next = handler;
        }
        this.lastHandler = handler;
    }

    public AnnotationVisitor visitTryCatchAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(n2, typePath, byteVector);
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (bl) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        char c2;
        int n3;
        if (string3 != null) {
            if (this.localVarType == null) {
                this.localVarType = new ByteVector();
            }
            ++this.localVarTypeCount;
            this.localVarType.putShort(label.position).putShort(label2.position - label.position).putShort(this.cw.newUTF8(string)).putShort(this.cw.newUTF8(string3)).putShort(n2);
        }
        if (this.localVar == null) {
            this.localVar = new ByteVector();
        }
        ++this.localVarCount;
        this.localVar.putShort(label.position).putShort(label2.position - label.position).putShort(this.cw.newUTF8(string)).putShort(this.cw.newUTF8(string2)).putShort(n2);
        if (this.compute != 3 && (n3 = n2 + ((c2 = string2.charAt(0)) == 'J' || c2 == 'D' ? 2 : 1)) > this.maxLocals) {
            this.maxLocals = n3;
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int n2, TypePath typePath, Label[] arrlabel, Label[] arrlabel2, int[] arrn, String string, boolean bl) {
        int n3;
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(n2 >>> 24).putShort(arrlabel.length);
        for (n3 = 0; n3 < arrlabel.length; ++n3) {
            byteVector.putShort(arrlabel[n3].position).putShort(arrlabel2[n3].position - arrlabel[n3].position).putShort(arrn[n3]);
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            n3 = typePath.b[typePath.offset] * 2 + 1;
            byteVector.putByteArray(typePath.b, typePath.offset, n3);
        }
        byteVector.putShort(this.cw.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
        if (bl) {
            annotationWriter.next = this.ctanns;
            this.ctanns = annotationWriter;
        } else {
            annotationWriter.next = this.ictanns;
            this.ictanns = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitLineNumber(int n2, Label label) {
        if (this.lineNumber == null) {
            this.lineNumber = new ByteVector();
        }
        ++this.lineNumberCount;
        this.lineNumber.putShort(label.position);
        this.lineNumber.putShort(n2);
    }

    public void visitMaxs(int n2, int n3) {
        if (this.compute == 0) {
            int n4;
            Edge edge;
            int n5;
            Object object;
            Label label;
            Object object2;
            Handler handler = this.firstHandler;
            while (handler != null) {
                object2 = handler.start.getFirst();
                Label label2 = handler.handler.getFirst();
                label = handler.end.getFirst();
                object = handler.desc == null ? "java/lang/Throwable" : handler.desc;
                n5 = 0x1700000 | this.cw.addType((String)object);
                label2.status |= 0x10;
                while (object2 != label) {
                    edge = new Edge();
                    edge.info = n5;
                    edge.successor = label2;
                    edge.next = ((Label)object2).successors;
                    ((Label)object2).successors = edge;
                    object2 = ((Label)object2).successor;
                }
                handler = handler.next;
            }
            object2 = this.labels.frame;
            ((Frame)object2).initInputFrame(this.cw, this.access, Type.getArgumentTypes(this.descriptor), this.maxLocals);
            this.visitFrame((Frame)object2);
            int n6 = 0;
            label = this.labels;
            while (label != null) {
                object = label;
                label = label.next;
                ((Label)object).next = null;
                object2 = ((Label)object).frame;
                if ((((Label)object).status & 0x10) != 0) {
                    ((Label)object).status |= 0x20;
                }
                ((Label)object).status |= 0x40;
                n5 = ((Frame)object2).inputStack.length + ((Label)object).outputStackMax;
                if (n5 > n6) {
                    n6 = n5;
                }
                edge = ((Label)object).successors;
                while (edge != null) {
                    Label label3 = edge.successor.getFirst();
                    n4 = ((Frame)object2).merge(this.cw, label3.frame, edge.info) ? 1 : 0;
                    if (n4 != 0 && label3.next == null) {
                        label3.next = label;
                        label = label3;
                    }
                    edge = edge.next;
                }
            }
            object = this.labels;
            while (object != null) {
                int n7;
                Label label4;
                int n8;
                object2 = ((Label)object).frame;
                if ((((Label)object).status & 0x20) != 0) {
                    this.visitFrame((Frame)object2);
                }
                if ((((Label)object).status & 0x40) == 0 && (n8 = ((label4 = ((Label)object).successor) == null ? this.code.length : label4.position) - 1) >= (n7 = ((Label)object).position)) {
                    n6 = Math.max(n6, 1);
                    for (n4 = n7; n4 < n8; ++n4) {
                        this.code.data[n4] = 0;
                    }
                    this.code.data[n8] = -65;
                    n4 = this.startFrame(n7, 0, 1);
                    this.frame[n4] = 0x1700000 | this.cw.addType("java/lang/Throwable");
                    this.endFrame();
                    this.firstHandler = Handler.remove(this.firstHandler, (Label)object, label4);
                }
                object = ((Label)object).successor;
            }
            handler = this.firstHandler;
            this.handlerCount = 0;
            while (handler != null) {
                ++this.handlerCount;
                handler = handler.next;
            }
            this.maxStack = n6;
        } else if (this.compute == 2) {
            Object object;
            Label label;
            Label label5;
            Handler handler = this.firstHandler;
            while (handler != null) {
                Label label6 = handler.start;
                label5 = handler.handler;
                label = handler.end;
                while (label6 != label) {
                    object = new Edge();
                    ((Edge)object).info = Integer.MAX_VALUE;
                    ((Edge)object).successor = label5;
                    if ((label6.status & 0x80) == 0) {
                        ((Edge)object).next = label6.successors;
                        label6.successors = object;
                    } else {
                        ((Edge)object).next = label6.successors.next.next;
                        label6.successors.next.next = object;
                    }
                    label6 = label6.successor;
                }
                handler = handler.next;
            }
            if (this.subroutines > 0) {
                int n9 = 0;
                this.labels.visitSubroutine(null, 1L, this.subroutines);
                label5 = this.labels;
                while (label5 != null) {
                    if ((label5.status & 0x80) != 0) {
                        label = label5.successors.next.successor;
                        if ((label.status & 0x400) == 0) {
                            label.visitSubroutine(null, (long)(++n9) / 32L << 32 | 1L << n9 % 32, this.subroutines);
                        }
                    }
                    label5 = label5.successor;
                }
                label5 = this.labels;
                while (label5 != null) {
                    if ((label5.status & 0x80) != 0) {
                        label = this.labels;
                        while (label != null) {
                            label.status &= 0xFFFFF7FF;
                            label = label.successor;
                        }
                        object = label5.successors.next.successor;
                        ((Label)object).visitSubroutine(label5, 0L, this.subroutines);
                    }
                    label5 = label5.successor;
                }
            }
            int n10 = 0;
            label5 = this.labels;
            while (label5 != null) {
                label = label5;
                label5 = label5.next;
                int n11 = label.inputStackTop;
                int n12 = n11 + label.outputStackMax;
                if (n12 > n10) {
                    n10 = n12;
                }
                Edge edge = label.successors;
                if ((label.status & 0x80) != 0) {
                    edge = edge.next;
                }
                while (edge != null) {
                    label = edge.successor;
                    if ((label.status & 8) == 0) {
                        label.inputStackTop = edge.info == Integer.MAX_VALUE ? 1 : n11 + edge.info;
                        label.status |= 8;
                        label.next = label5;
                        label5 = label;
                    }
                    edge = edge.next;
                }
            }
            this.maxStack = Math.max(n2, n10);
        } else {
            this.maxStack = n2;
            this.maxLocals = n3;
        }
    }

    public void visitEnd() {
    }

    private void addSuccessor(int n2, Label label) {
        Edge edge = new Edge();
        edge.info = n2;
        edge.successor = label;
        edge.next = this.currentBlock.successors;
        this.currentBlock.successors = edge;
    }

    private void noSuccessor() {
        if (this.compute == 0) {
            Label label = new Label();
            label.frame = new Frame();
            label.frame.owner = label;
            label.resolve(this, this.code.length, this.code.data);
            this.previousBlock.successor = label;
            this.previousBlock = label;
        } else {
            this.currentBlock.outputStackMax = this.maxStackSize;
        }
        if (this.compute != 1) {
            this.currentBlock = null;
        }
    }

    private void visitFrame(Frame frame) {
        int n2;
        int n3;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int[] arrn = frame.inputLocals;
        int[] arrn2 = frame.inputStack;
        for (n3 = 0; n3 < arrn.length; ++n3) {
            n2 = arrn[n3];
            if (n2 == 0x1000000) {
                ++n4;
            } else {
                n5 += n4 + 1;
                n4 = 0;
            }
            if (n2 != 0x1000004 && n2 != 0x1000003) continue;
            ++n3;
        }
        for (n3 = 0; n3 < arrn2.length; ++n3) {
            n2 = arrn2[n3];
            ++n6;
            if (n2 != 0x1000004 && n2 != 0x1000003) continue;
            ++n3;
        }
        int n7 = this.startFrame(frame.owner.position, n5, n6);
        n3 = 0;
        while (n5 > 0) {
            n2 = arrn[n3];
            this.frame[n7++] = n2;
            if (n2 == 0x1000004 || n2 == 0x1000003) {
                ++n3;
            }
            ++n3;
            --n5;
        }
        for (n3 = 0; n3 < arrn2.length; ++n3) {
            n2 = arrn2[n3];
            this.frame[n7++] = n2;
            if (n2 != 0x1000004 && n2 != 0x1000003) continue;
            ++n3;
        }
        this.endFrame();
    }

    private void visitImplicitFirstFrame() {
        int n2 = this.startFrame(0, this.descriptor.length() + 1, 0);
        if ((this.access & 8) == 0) {
            this.frame[n2++] = (this.access & 0x80000) == 0 ? 0x1700000 | this.cw.addType(this.cw.thisName) : 6;
        }
        int n3 = 1;
        block8: while (true) {
            int n4 = n3;
            switch (this.descriptor.charAt(n3++)) {
                case 'B': 
                case 'C': 
                case 'I': 
                case 'S': 
                case 'Z': {
                    this.frame[n2++] = 1;
                    continue block8;
                }
                case 'F': {
                    this.frame[n2++] = 2;
                    continue block8;
                }
                case 'J': {
                    this.frame[n2++] = 4;
                    continue block8;
                }
                case 'D': {
                    this.frame[n2++] = 3;
                    continue block8;
                }
                case '[': {
                    while (this.descriptor.charAt(n3) == '[') {
                        ++n3;
                    }
                    if (this.descriptor.charAt(n3) == 'L') {
                        ++n3;
                        while (this.descriptor.charAt(n3) != ';') {
                            ++n3;
                        }
                    }
                    this.frame[n2++] = 0x1700000 | this.cw.addType(this.descriptor.substring(n4, ++n3));
                    continue block8;
                }
                case 'L': {
                    while (this.descriptor.charAt(n3) != ';') {
                        ++n3;
                    }
                    this.frame[n2++] = 0x1700000 | this.cw.addType(this.descriptor.substring(n4 + 1, n3++));
                    continue block8;
                }
            }
            break;
        }
        this.frame[1] = n2 - 3;
        this.endFrame();
    }

    private int startFrame(int n2, int n3, int n4) {
        int n5 = 3 + n3 + n4;
        if (this.frame == null || this.frame.length < n5) {
            this.frame = new int[n5];
        }
        this.frame[0] = n2;
        this.frame[1] = n3;
        this.frame[2] = n4;
        return 3;
    }

    private void endFrame() {
        if (this.previousFrame != null) {
            if (this.stackMap == null) {
                this.stackMap = new ByteVector();
            }
            this.writeFrame();
            ++this.frameCount;
        }
        this.previousFrame = this.frame;
        this.frame = null;
    }

    private void writeFrame() {
        int n2 = this.frame[1];
        int n3 = this.frame[2];
        if ((this.cw.version & 0xFFFF) < 50) {
            this.stackMap.putShort(this.frame[0]).putShort(n2);
            this.writeFrameTypes(3, 3 + n2);
            this.stackMap.putShort(n3);
            this.writeFrameTypes(3 + n2, 3 + n2 + n3);
            return;
        }
        int n4 = this.previousFrame[1];
        int n5 = 255;
        int n6 = 0;
        int n7 = this.frameCount == 0 ? this.frame[0] : this.frame[0] - this.previousFrame[0] - 1;
        if (n3 == 0) {
            n6 = n2 - n4;
            switch (n6) {
                case -3: 
                case -2: 
                case -1: {
                    n5 = 248;
                    n4 = n2;
                    break;
                }
                case 0: {
                    n5 = n7 < 64 ? 0 : 251;
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    n5 = 252;
                }
            }
        } else if (n2 == n4 && n3 == 1) {
            int n8 = n5 = n7 < 63 ? 64 : 247;
        }
        if (n5 != 255) {
            int n9 = 3;
            for (int i2 = 0; i2 < n4; ++i2) {
                if (this.frame[n9] != this.previousFrame[n9]) {
                    n5 = 255;
                    break;
                }
                ++n9;
            }
        }
        switch (n5) {
            case 0: {
                this.stackMap.putByte(n7);
                break;
            }
            case 64: {
                this.stackMap.putByte(64 + n7);
                this.writeFrameTypes(3 + n2, 4 + n2);
                break;
            }
            case 247: {
                this.stackMap.putByte(247).putShort(n7);
                this.writeFrameTypes(3 + n2, 4 + n2);
                break;
            }
            case 251: {
                this.stackMap.putByte(251).putShort(n7);
                break;
            }
            case 248: {
                this.stackMap.putByte(251 + n6).putShort(n7);
                break;
            }
            case 252: {
                this.stackMap.putByte(251 + n6).putShort(n7);
                this.writeFrameTypes(3 + n4, 3 + n2);
                break;
            }
            default: {
                this.stackMap.putByte(255).putShort(n7).putShort(n2);
                this.writeFrameTypes(3, 3 + n2);
                this.stackMap.putShort(n3);
                this.writeFrameTypes(3 + n2, 3 + n2 + n3);
            }
        }
    }

    private void writeFrameTypes(int n2, int n3) {
        for (int i2 = n2; i2 < n3; ++i2) {
            int n4 = this.frame[i2];
            int n5 = n4 & 0xF0000000;
            if (n5 == 0) {
                int n6 = n4 & 0xFFFFF;
                switch (n4 & 0xFF00000) {
                    case 0x1700000: {
                        this.stackMap.putByte(7).putShort(this.cw.newClass(this.cw.typeTable[n6].strVal1));
                        break;
                    }
                    case 0x1800000: {
                        this.stackMap.putByte(8).putShort(this.cw.typeTable[n6].intVal);
                        break;
                    }
                    default: {
                        this.stackMap.putByte(n6);
                        break;
                    }
                }
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            n5 >>= 28;
            while (n5-- > 0) {
                stringBuilder.append('[');
            }
            if ((n4 & 0xFF00000) == 0x1700000) {
                stringBuilder.append('L');
                stringBuilder.append(this.cw.typeTable[n4 & 1048575].strVal1);
                stringBuilder.append(';');
            } else {
                switch (n4 & 0xF) {
                    case 1: {
                        stringBuilder.append('I');
                        break;
                    }
                    case 2: {
                        stringBuilder.append('F');
                        break;
                    }
                    case 3: {
                        stringBuilder.append('D');
                        break;
                    }
                    case 9: {
                        stringBuilder.append('Z');
                        break;
                    }
                    case 10: {
                        stringBuilder.append('B');
                        break;
                    }
                    case 11: {
                        stringBuilder.append('C');
                        break;
                    }
                    case 12: {
                        stringBuilder.append('S');
                        break;
                    }
                    default: {
                        stringBuilder.append('J');
                    }
                }
            }
            this.stackMap.putByte(7).putShort(this.cw.newClass(stringBuilder.toString()));
        }
    }

    private void writeFrameType(Object object) {
        if (object instanceof String) {
            this.stackMap.putByte(7).putShort(this.cw.newClass((String)object));
        } else if (object instanceof Integer) {
            this.stackMap.putByte((Integer)object);
        } else {
            this.stackMap.putByte(8).putShort(((Label)object).position);
        }
    }

    final int getSize() {
        int n2;
        if (this.classReaderOffset != 0) {
            return 6 + this.classReaderLength;
        }
        int n3 = 8;
        if (this.code.length > 0) {
            if (this.code.length > 65535) {
                throw new RuntimeException("Method code too large!");
            }
            this.cw.newUTF8("Code");
            n3 += 18 + this.code.length + 8 * this.handlerCount;
            if (this.localVar != null) {
                this.cw.newUTF8("LocalVariableTable");
                n3 += 8 + this.localVar.length;
            }
            if (this.localVarType != null) {
                this.cw.newUTF8("LocalVariableTypeTable");
                n3 += 8 + this.localVarType.length;
            }
            if (this.lineNumber != null) {
                this.cw.newUTF8("LineNumberTable");
                n3 += 8 + this.lineNumber.length;
            }
            if (this.stackMap != null) {
                n2 = (this.cw.version & 0xFFFF) >= 50 ? 1 : 0;
                this.cw.newUTF8(n2 != 0 ? "StackMapTable" : "StackMap");
                n3 += 8 + this.stackMap.length;
            }
            if (this.ctanns != null) {
                this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
                n3 += 8 + this.ctanns.getSize();
            }
            if (this.ictanns != null) {
                this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
                n3 += 8 + this.ictanns.getSize();
            }
            if (this.cattrs != null) {
                n3 += this.cattrs.getSize(this.cw, this.code.data, this.code.length, this.maxStack, this.maxLocals);
            }
        }
        if (this.exceptionCount > 0) {
            this.cw.newUTF8("Exceptions");
            n3 += 8 + 2 * this.exceptionCount;
        }
        if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
            this.cw.newUTF8("Synthetic");
            n3 += 6;
        }
        if ((this.access & 0x20000) != 0) {
            this.cw.newUTF8("Deprecated");
            n3 += 6;
        }
        if (this.signature != null) {
            this.cw.newUTF8("Signature");
            this.cw.newUTF8(this.signature);
            n3 += 8;
        }
        if (this.methodParameters != null) {
            this.cw.newUTF8("MethodParameters");
            n3 += 7 + this.methodParameters.length;
        }
        if (this.annd != null) {
            this.cw.newUTF8("AnnotationDefault");
            n3 += 6 + this.annd.length;
        }
        if (this.anns != null) {
            this.cw.newUTF8("RuntimeVisibleAnnotations");
            n3 += 8 + this.anns.getSize();
        }
        if (this.ianns != null) {
            this.cw.newUTF8("RuntimeInvisibleAnnotations");
            n3 += 8 + this.ianns.getSize();
        }
        if (this.tanns != null) {
            this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
            n3 += 8 + this.tanns.getSize();
        }
        if (this.itanns != null) {
            this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
            n3 += 8 + this.itanns.getSize();
        }
        if (this.panns != null) {
            this.cw.newUTF8("RuntimeVisibleParameterAnnotations");
            n3 += 7 + 2 * (this.panns.length - this.synthetics);
            for (n2 = this.panns.length - 1; n2 >= this.synthetics; --n2) {
                n3 += this.panns[n2] == null ? 0 : this.panns[n2].getSize();
            }
        }
        if (this.ipanns != null) {
            this.cw.newUTF8("RuntimeInvisibleParameterAnnotations");
            n3 += 7 + 2 * (this.ipanns.length - this.synthetics);
            for (n2 = this.ipanns.length - 1; n2 >= this.synthetics; --n2) {
                n3 += this.ipanns[n2] == null ? 0 : this.ipanns[n2].getSize();
            }
        }
        if (this.attrs != null) {
            n3 += this.attrs.getSize(this.cw, null, 0, -1, -1);
        }
        return n3;
    }

    final void put(ByteVector byteVector) {
        int n2;
        int n3 = 64;
        int n4 = 0xE0000 | (this.access & 0x40000) / 64;
        byteVector.putShort(this.access & ~n4).putShort(this.name).putShort(this.desc);
        if (this.classReaderOffset != 0) {
            byteVector.putByteArray(this.cw.cr.b, this.classReaderOffset, this.classReaderLength);
            return;
        }
        int n5 = 0;
        if (this.code.length > 0) {
            ++n5;
        }
        if (this.exceptionCount > 0) {
            ++n5;
        }
        if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
            ++n5;
        }
        if ((this.access & 0x20000) != 0) {
            ++n5;
        }
        if (this.signature != null) {
            ++n5;
        }
        if (this.methodParameters != null) {
            ++n5;
        }
        if (this.annd != null) {
            ++n5;
        }
        if (this.anns != null) {
            ++n5;
        }
        if (this.ianns != null) {
            ++n5;
        }
        if (this.tanns != null) {
            ++n5;
        }
        if (this.itanns != null) {
            ++n5;
        }
        if (this.panns != null) {
            ++n5;
        }
        if (this.ipanns != null) {
            ++n5;
        }
        if (this.attrs != null) {
            n5 += this.attrs.getCount();
        }
        byteVector.putShort(n5);
        if (this.code.length > 0) {
            n2 = 12 + this.code.length + 8 * this.handlerCount;
            if (this.localVar != null) {
                n2 += 8 + this.localVar.length;
            }
            if (this.localVarType != null) {
                n2 += 8 + this.localVarType.length;
            }
            if (this.lineNumber != null) {
                n2 += 8 + this.lineNumber.length;
            }
            if (this.stackMap != null) {
                n2 += 8 + this.stackMap.length;
            }
            if (this.ctanns != null) {
                n2 += 8 + this.ctanns.getSize();
            }
            if (this.ictanns != null) {
                n2 += 8 + this.ictanns.getSize();
            }
            if (this.cattrs != null) {
                n2 += this.cattrs.getSize(this.cw, this.code.data, this.code.length, this.maxStack, this.maxLocals);
            }
            byteVector.putShort(this.cw.newUTF8("Code")).putInt(n2);
            byteVector.putShort(this.maxStack).putShort(this.maxLocals);
            byteVector.putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
            byteVector.putShort(this.handlerCount);
            if (this.handlerCount > 0) {
                Handler handler = this.firstHandler;
                while (handler != null) {
                    byteVector.putShort(handler.start.position).putShort(handler.end.position).putShort(handler.handler.position).putShort(handler.type);
                    handler = handler.next;
                }
            }
            n5 = 0;
            if (this.localVar != null) {
                ++n5;
            }
            if (this.localVarType != null) {
                ++n5;
            }
            if (this.lineNumber != null) {
                ++n5;
            }
            if (this.stackMap != null) {
                ++n5;
            }
            if (this.ctanns != null) {
                ++n5;
            }
            if (this.ictanns != null) {
                ++n5;
            }
            if (this.cattrs != null) {
                n5 += this.cattrs.getCount();
            }
            byteVector.putShort(n5);
            if (this.localVar != null) {
                byteVector.putShort(this.cw.newUTF8("LocalVariableTable"));
                byteVector.putInt(this.localVar.length + 2).putShort(this.localVarCount);
                byteVector.putByteArray(this.localVar.data, 0, this.localVar.length);
            }
            if (this.localVarType != null) {
                byteVector.putShort(this.cw.newUTF8("LocalVariableTypeTable"));
                byteVector.putInt(this.localVarType.length + 2).putShort(this.localVarTypeCount);
                byteVector.putByteArray(this.localVarType.data, 0, this.localVarType.length);
            }
            if (this.lineNumber != null) {
                byteVector.putShort(this.cw.newUTF8("LineNumberTable"));
                byteVector.putInt(this.lineNumber.length + 2).putShort(this.lineNumberCount);
                byteVector.putByteArray(this.lineNumber.data, 0, this.lineNumber.length);
            }
            if (this.stackMap != null) {
                boolean bl = (this.cw.version & 0xFFFF) >= 50;
                byteVector.putShort(this.cw.newUTF8(bl ? "StackMapTable" : "StackMap"));
                byteVector.putInt(this.stackMap.length + 2).putShort(this.frameCount);
                byteVector.putByteArray(this.stackMap.data, 0, this.stackMap.length);
            }
            if (this.ctanns != null) {
                byteVector.putShort(this.cw.newUTF8("RuntimeVisibleTypeAnnotations"));
                this.ctanns.put(byteVector);
            }
            if (this.ictanns != null) {
                byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleTypeAnnotations"));
                this.ictanns.put(byteVector);
            }
            if (this.cattrs != null) {
                this.cattrs.put(this.cw, this.code.data, this.code.length, this.maxLocals, this.maxStack, byteVector);
            }
        }
        if (this.exceptionCount > 0) {
            byteVector.putShort(this.cw.newUTF8("Exceptions")).putInt(2 * this.exceptionCount + 2);
            byteVector.putShort(this.exceptionCount);
            for (n2 = 0; n2 < this.exceptionCount; ++n2) {
                byteVector.putShort(this.exceptions[n2]);
            }
        }
        if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
            byteVector.putShort(this.cw.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.access & 0x20000) != 0) {
            byteVector.putShort(this.cw.newUTF8("Deprecated")).putInt(0);
        }
        if (this.signature != null) {
            byteVector.putShort(this.cw.newUTF8("Signature")).putInt(2).putShort(this.cw.newUTF8(this.signature));
        }
        if (this.methodParameters != null) {
            byteVector.putShort(this.cw.newUTF8("MethodParameters"));
            byteVector.putInt(this.methodParameters.length + 1).putByte(this.methodParametersCount);
            byteVector.putByteArray(this.methodParameters.data, 0, this.methodParameters.length);
        }
        if (this.annd != null) {
            byteVector.putShort(this.cw.newUTF8("AnnotationDefault"));
            byteVector.putInt(this.annd.length);
            byteVector.putByteArray(this.annd.data, 0, this.annd.length);
        }
        if (this.anns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeVisibleAnnotations"));
            this.anns.put(byteVector);
        }
        if (this.ianns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleAnnotations"));
            this.ianns.put(byteVector);
        }
        if (this.tanns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.tanns.put(byteVector);
        }
        if (this.itanns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.itanns.put(byteVector);
        }
        if (this.panns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.put(this.panns, this.synthetics, byteVector);
        }
        if (this.ipanns != null) {
            byteVector.putShort(this.cw.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.put(this.ipanns, this.synthetics, byteVector);
        }
        if (this.attrs != null) {
            this.attrs.put(this.cw, null, 0, -1, -1, byteVector);
        }
    }
}

