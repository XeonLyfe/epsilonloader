/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.IincInsnNode;
import org.spongepowered.asm.lib.tree.InvokeDynamicInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MultiANewArrayInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;
import org.spongepowered.asm.lib.tree.analysis.Value;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Frame<V extends Value> {
    private V returnValue;
    private V[] values;
    private int locals;
    private int top;

    public Frame(int n2, int n3) {
        this.values = new Value[n2 + n3];
        this.locals = n2;
    }

    public Frame(Frame<? extends V> frame) {
        this(frame.locals, frame.values.length - frame.locals);
        this.init(frame);
    }

    public Frame<V> init(Frame<? extends V> frame) {
        this.returnValue = frame.returnValue;
        System.arraycopy(frame.values, 0, this.values, 0, this.values.length);
        this.top = frame.top;
        return this;
    }

    public void setReturn(V v2) {
        this.returnValue = v2;
    }

    public int getLocals() {
        return this.locals;
    }

    public int getMaxStackSize() {
        return this.values.length - this.locals;
    }

    public V getLocal(int n2) throws IndexOutOfBoundsException {
        if (n2 >= this.locals) {
            throw new IndexOutOfBoundsException("Trying to access an inexistant local variable");
        }
        return this.values[n2];
    }

    public void setLocal(int n2, V v2) throws IndexOutOfBoundsException {
        if (n2 >= this.locals) {
            throw new IndexOutOfBoundsException("Trying to access an inexistant local variable " + n2);
        }
        this.values[n2] = v2;
    }

    public int getStackSize() {
        return this.top;
    }

    public V getStack(int n2) throws IndexOutOfBoundsException {
        return this.values[n2 + this.locals];
    }

    public void clearStack() {
        this.top = 0;
    }

    public V pop() throws IndexOutOfBoundsException {
        if (this.top == 0) {
            throw new IndexOutOfBoundsException("Cannot pop operand off an empty stack.");
        }
        return this.values[--this.top + this.locals];
    }

    public void push(V v2) throws IndexOutOfBoundsException {
        if (this.top + this.locals >= this.values.length) {
            throw new IndexOutOfBoundsException("Insufficient maximum stack size.");
        }
        this.values[this.top++ + this.locals] = v2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void execute(AbstractInsnNode abstractInsnNode, Interpreter<V> interpreter) throws AnalyzerException {
        switch (abstractInsnNode.getOpcode()) {
            case 0: {
                return;
            }
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: {
                this.push(interpreter.newOperation(abstractInsnNode));
                return;
            }
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: {
                this.push(interpreter.copyOperation(abstractInsnNode, this.getLocal(((VarInsnNode)abstractInsnNode).var)));
                return;
            }
            case 46: 
            case 47: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: {
                V v2 = this.pop();
                V v3 = this.pop();
                this.push(interpreter.binaryOperation(abstractInsnNode, v3, v2));
                return;
            }
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: {
                V v4;
                Object e2 = interpreter.copyOperation(abstractInsnNode, this.pop());
                int n2 = ((VarInsnNode)abstractInsnNode).var;
                this.setLocal(n2, e2);
                if (e2.getSize() == 2) {
                    this.setLocal(n2 + 1, interpreter.newValue(null));
                }
                if (n2 <= 0 || (v4 = this.getLocal(n2 - 1)) == null || v4.getSize() != 2) return;
                this.setLocal(n2 - 1, interpreter.newValue(null));
                return;
            }
            case 79: 
            case 80: 
            case 81: 
            case 82: 
            case 83: 
            case 84: 
            case 85: 
            case 86: {
                V v5 = this.pop();
                V v6 = this.pop();
                V v7 = this.pop();
                interpreter.ternaryOperation(abstractInsnNode, v7, v6, v5);
                return;
            }
            case 87: {
                if (this.pop().getSize() != 2) return;
                throw new AnalyzerException(abstractInsnNode, "Illegal use of POP");
            }
            case 88: {
                if (this.pop().getSize() != 1 || this.pop().getSize() == 1) return;
                throw new AnalyzerException(abstractInsnNode, "Illegal use of POP2");
            }
            case 89: {
                V v8 = this.pop();
                if (v8.getSize() != 1) {
                    throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP");
                }
                this.push(v8);
                this.push(interpreter.copyOperation(abstractInsnNode, v8));
                return;
            }
            case 90: {
                V v9 = this.pop();
                V v10 = this.pop();
                if (v9.getSize() != 1 || v10.getSize() != 1) {
                    throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP_X1");
                }
                this.push(interpreter.copyOperation(abstractInsnNode, v9));
                this.push(v10);
                this.push(v9);
                return;
            }
            case 91: {
                V v11 = this.pop();
                if (v11.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP_X2");
                V v12 = this.pop();
                if (v12.getSize() == 1) {
                    V v13 = this.pop();
                    if (v13.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP_X2");
                    this.push(interpreter.copyOperation(abstractInsnNode, v11));
                    this.push(v13);
                    this.push(v12);
                    this.push(v11);
                    return;
                }
                this.push(interpreter.copyOperation(abstractInsnNode, v11));
                this.push(v12);
                this.push(v11);
                return;
            }
            case 92: {
                V v14 = this.pop();
                if (v14.getSize() == 1) {
                    V v15 = this.pop();
                    if (v15.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2");
                    this.push(v15);
                    this.push(v14);
                    this.push(interpreter.copyOperation(abstractInsnNode, v15));
                    this.push(interpreter.copyOperation(abstractInsnNode, v14));
                    return;
                }
                this.push(v14);
                this.push(interpreter.copyOperation(abstractInsnNode, v14));
                return;
            }
            case 93: {
                V v16 = this.pop();
                if (v16.getSize() == 1) {
                    V v17;
                    V v18 = this.pop();
                    if (v18.getSize() != 1 || (v17 = this.pop()).getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2_X1");
                    this.push(interpreter.copyOperation(abstractInsnNode, v18));
                    this.push(interpreter.copyOperation(abstractInsnNode, v16));
                    this.push(v17);
                    this.push(v18);
                    this.push(v16);
                    return;
                }
                V v19 = this.pop();
                if (v19.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2_X1");
                this.push(interpreter.copyOperation(abstractInsnNode, v16));
                this.push(v19);
                this.push(v16);
                return;
            }
            case 94: {
                V v20 = this.pop();
                if (v20.getSize() == 1) {
                    V v21 = this.pop();
                    if (v21.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2_X2");
                    V v22 = this.pop();
                    if (v22.getSize() == 1) {
                        V v23 = this.pop();
                        if (v23.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2_X2");
                        this.push(interpreter.copyOperation(abstractInsnNode, v21));
                        this.push(interpreter.copyOperation(abstractInsnNode, v20));
                        this.push(v23);
                        this.push(v22);
                        this.push(v21);
                        this.push(v20);
                        return;
                    }
                    this.push(interpreter.copyOperation(abstractInsnNode, v21));
                    this.push(interpreter.copyOperation(abstractInsnNode, v20));
                    this.push(v22);
                    this.push(v21);
                    this.push(v20);
                    return;
                }
                V v24 = this.pop();
                if (v24.getSize() == 1) {
                    V v25 = this.pop();
                    if (v25.getSize() != 1) throw new AnalyzerException(abstractInsnNode, "Illegal use of DUP2_X2");
                    this.push(interpreter.copyOperation(abstractInsnNode, v20));
                    this.push(v25);
                    this.push(v24);
                    this.push(v20);
                    return;
                }
                this.push(interpreter.copyOperation(abstractInsnNode, v20));
                this.push(v24);
                this.push(v20);
                return;
            }
            case 95: {
                V v26 = this.pop();
                V v27 = this.pop();
                if (v27.getSize() != 1 || v26.getSize() != 1) {
                    throw new AnalyzerException(abstractInsnNode, "Illegal use of SWAP");
                }
                this.push(interpreter.copyOperation(abstractInsnNode, v26));
                this.push(interpreter.copyOperation(abstractInsnNode, v27));
                return;
            }
            case 96: 
            case 97: 
            case 98: 
            case 99: 
            case 100: 
            case 101: 
            case 102: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: 
            case 109: 
            case 110: 
            case 111: 
            case 112: 
            case 113: 
            case 114: 
            case 115: {
                V v28 = this.pop();
                V v29 = this.pop();
                this.push(interpreter.binaryOperation(abstractInsnNode, v29, v28));
                return;
            }
            case 116: 
            case 117: 
            case 118: 
            case 119: {
                this.push(interpreter.unaryOperation(abstractInsnNode, this.pop()));
                return;
            }
            case 120: 
            case 121: 
            case 122: 
            case 123: 
            case 124: 
            case 125: 
            case 126: 
            case 127: 
            case 128: 
            case 129: 
            case 130: 
            case 131: {
                V v30 = this.pop();
                V v31 = this.pop();
                this.push(interpreter.binaryOperation(abstractInsnNode, v31, v30));
                return;
            }
            case 132: {
                int n3 = ((IincInsnNode)abstractInsnNode).var;
                this.setLocal(n3, interpreter.unaryOperation(abstractInsnNode, this.getLocal(n3)));
                return;
            }
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 137: 
            case 138: 
            case 139: 
            case 140: 
            case 141: 
            case 142: 
            case 143: 
            case 144: 
            case 145: 
            case 146: 
            case 147: {
                this.push(interpreter.unaryOperation(abstractInsnNode, this.pop()));
                return;
            }
            case 148: 
            case 149: 
            case 150: 
            case 151: 
            case 152: {
                V v32 = this.pop();
                V v33 = this.pop();
                this.push(interpreter.binaryOperation(abstractInsnNode, v33, v32));
                return;
            }
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: {
                V v34 = this.pop();
                V v35 = this.pop();
                interpreter.binaryOperation(abstractInsnNode, v35, v34);
                return;
            }
            case 167: {
                return;
            }
            case 168: {
                this.push(interpreter.newOperation(abstractInsnNode));
                return;
            }
            case 169: {
                return;
            }
            case 170: 
            case 171: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            case 172: 
            case 173: 
            case 174: 
            case 175: 
            case 176: {
                V v36 = this.pop();
                interpreter.unaryOperation(abstractInsnNode, v36);
                interpreter.returnOperation(abstractInsnNode, v36, this.returnValue);
                return;
            }
            case 177: {
                if (this.returnValue == null) return;
                throw new AnalyzerException(abstractInsnNode, "Incompatible return type");
            }
            case 178: {
                this.push(interpreter.newOperation(abstractInsnNode));
                return;
            }
            case 179: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            case 180: {
                this.push(interpreter.unaryOperation(abstractInsnNode, this.pop()));
                return;
            }
            case 181: {
                V v37 = this.pop();
                V v38 = this.pop();
                interpreter.binaryOperation(abstractInsnNode, v38, v37);
                return;
            }
            case 182: 
            case 183: 
            case 184: 
            case 185: {
                ArrayList<V> arrayList = new ArrayList<V>();
                String string = ((MethodInsnNode)abstractInsnNode).desc;
                for (int i2 = Type.getArgumentTypes(string).length; i2 > 0; --i2) {
                    arrayList.add(0, this.pop());
                }
                if (abstractInsnNode.getOpcode() != 184) {
                    arrayList.add(0, this.pop());
                }
                if (Type.getReturnType(string) == Type.VOID_TYPE) {
                    interpreter.naryOperation(abstractInsnNode, arrayList);
                    return;
                }
                this.push(interpreter.naryOperation(abstractInsnNode, arrayList));
                return;
            }
            case 186: {
                ArrayList<V> arrayList = new ArrayList<V>();
                String string = ((InvokeDynamicInsnNode)abstractInsnNode).desc;
                for (int i3 = Type.getArgumentTypes(string).length; i3 > 0; --i3) {
                    arrayList.add(0, this.pop());
                }
                if (Type.getReturnType(string) == Type.VOID_TYPE) {
                    interpreter.naryOperation(abstractInsnNode, arrayList);
                    return;
                }
                this.push(interpreter.naryOperation(abstractInsnNode, arrayList));
                return;
            }
            case 187: {
                this.push(interpreter.newOperation(abstractInsnNode));
                return;
            }
            case 188: 
            case 189: 
            case 190: {
                this.push(interpreter.unaryOperation(abstractInsnNode, this.pop()));
                return;
            }
            case 191: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            case 192: 
            case 193: {
                this.push(interpreter.unaryOperation(abstractInsnNode, this.pop()));
                return;
            }
            case 194: 
            case 195: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            case 197: {
                ArrayList<V> arrayList = new ArrayList<V>();
                for (int i4 = ((MultiANewArrayInsnNode)abstractInsnNode).dims; i4 > 0; --i4) {
                    arrayList.add(0, this.pop());
                }
                this.push(interpreter.naryOperation(abstractInsnNode, arrayList));
                return;
            }
            case 198: 
            case 199: {
                interpreter.unaryOperation(abstractInsnNode, this.pop());
                return;
            }
            default: {
                throw new RuntimeException("Illegal opcode " + abstractInsnNode.getOpcode());
            }
        }
    }

    public boolean merge(Frame<? extends V> frame, Interpreter<V> interpreter) throws AnalyzerException {
        if (this.top != frame.top) {
            throw new AnalyzerException(null, "Incompatible stack heights");
        }
        boolean bl = false;
        for (int i2 = 0; i2 < this.locals + this.top; ++i2) {
            V v2 = interpreter.merge(this.values[i2], frame.values[i2]);
            if (v2.equals(this.values[i2])) continue;
            this.values[i2] = v2;
            bl = true;
        }
        return bl;
    }

    public boolean merge(Frame<? extends V> frame, boolean[] arrbl) {
        boolean bl = false;
        for (int i2 = 0; i2 < this.locals; ++i2) {
            if (arrbl[i2] || this.values[i2].equals(frame.values[i2])) continue;
            this.values[i2] = frame.values[i2];
            bl = true;
        }
        return bl;
    }

    public String toString() {
        int n2;
        StringBuilder stringBuilder = new StringBuilder();
        for (n2 = 0; n2 < this.getLocals(); ++n2) {
            stringBuilder.append(this.getLocal(n2));
        }
        stringBuilder.append(' ');
        for (n2 = 0; n2 < this.getStackSize(); ++n2) {
            stringBuilder.append(this.getStack(n2).toString());
        }
        return stringBuilder.toString();
    }
}

