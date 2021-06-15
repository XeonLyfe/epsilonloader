/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InvokeDynamicInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.BasicInterpreter;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BasicVerifier
extends BasicInterpreter {
    public BasicVerifier() {
        super(327680);
    }

    protected BasicVerifier(int n2) {
        super(n2);
    }

    @Override
    public BasicValue copyOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue) throws AnalyzerException {
        BasicValue basicValue2;
        switch (abstractInsnNode.getOpcode()) {
            case 21: 
            case 54: {
                basicValue2 = BasicValue.INT_VALUE;
                break;
            }
            case 23: 
            case 56: {
                basicValue2 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 22: 
            case 55: {
                basicValue2 = BasicValue.LONG_VALUE;
                break;
            }
            case 24: 
            case 57: {
                basicValue2 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 25: {
                if (!basicValue.isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", basicValue);
                }
                return basicValue;
            }
            case 58: {
                if (!basicValue.isReference() && !BasicValue.RETURNADDRESS_VALUE.equals(basicValue)) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference or a return address", basicValue);
                }
                return basicValue;
            }
            default: {
                return basicValue;
            }
        }
        if (!((Object)basicValue2).equals(basicValue)) {
            throw new AnalyzerException(abstractInsnNode, null, basicValue2, basicValue);
        }
        return basicValue;
    }

    @Override
    public BasicValue unaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue) throws AnalyzerException {
        BasicValue basicValue2;
        switch (abstractInsnNode.getOpcode()) {
            case 116: 
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 145: 
            case 146: 
            case 147: 
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: 
            case 170: 
            case 171: 
            case 172: 
            case 188: 
            case 189: {
                basicValue2 = BasicValue.INT_VALUE;
                break;
            }
            case 118: 
            case 139: 
            case 140: 
            case 141: 
            case 174: {
                basicValue2 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 117: 
            case 136: 
            case 137: 
            case 138: 
            case 173: {
                basicValue2 = BasicValue.LONG_VALUE;
                break;
            }
            case 119: 
            case 142: 
            case 143: 
            case 144: 
            case 175: {
                basicValue2 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 180: {
                basicValue2 = this.newValue(Type.getObjectType(((FieldInsnNode)abstractInsnNode).owner));
                break;
            }
            case 192: {
                if (!basicValue.isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", basicValue);
                }
                return super.unaryOperation(abstractInsnNode, basicValue);
            }
            case 190: {
                if (!this.isArrayValue(basicValue)) {
                    throw new AnalyzerException(abstractInsnNode, null, "an array reference", basicValue);
                }
                return super.unaryOperation(abstractInsnNode, basicValue);
            }
            case 176: 
            case 191: 
            case 193: 
            case 194: 
            case 195: 
            case 198: 
            case 199: {
                if (!basicValue.isReference()) {
                    throw new AnalyzerException(abstractInsnNode, null, "an object reference", basicValue);
                }
                return super.unaryOperation(abstractInsnNode, basicValue);
            }
            case 179: {
                basicValue2 = this.newValue(Type.getType(((FieldInsnNode)abstractInsnNode).desc));
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(basicValue, basicValue2)) {
            throw new AnalyzerException(abstractInsnNode, null, basicValue2, basicValue);
        }
        return super.unaryOperation(abstractInsnNode, basicValue);
    }

    @Override
    public BasicValue binaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2) throws AnalyzerException {
        BasicValue basicValue3;
        BasicValue basicValue4;
        switch (abstractInsnNode.getOpcode()) {
            case 46: {
                basicValue4 = this.newValue(Type.getType("[I"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 51: {
                basicValue4 = this.isSubTypeOf(basicValue, this.newValue(Type.getType("[Z"))) ? this.newValue(Type.getType("[Z")) : this.newValue(Type.getType("[B"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 52: {
                basicValue4 = this.newValue(Type.getType("[C"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 53: {
                basicValue4 = this.newValue(Type.getType("[S"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 47: {
                basicValue4 = this.newValue(Type.getType("[J"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 48: {
                basicValue4 = this.newValue(Type.getType("[F"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 49: {
                basicValue4 = this.newValue(Type.getType("[D"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 50: {
                basicValue4 = this.newValue(Type.getType("[Ljava/lang/Object;"));
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 96: 
            case 100: 
            case 104: 
            case 108: 
            case 112: 
            case 120: 
            case 122: 
            case 124: 
            case 126: 
            case 128: 
            case 130: 
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: {
                basicValue4 = BasicValue.INT_VALUE;
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 98: 
            case 102: 
            case 106: 
            case 110: 
            case 114: 
            case 149: 
            case 150: {
                basicValue4 = BasicValue.FLOAT_VALUE;
                basicValue3 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 97: 
            case 101: 
            case 105: 
            case 109: 
            case 113: 
            case 127: 
            case 129: 
            case 131: 
            case 148: {
                basicValue4 = BasicValue.LONG_VALUE;
                basicValue3 = BasicValue.LONG_VALUE;
                break;
            }
            case 121: 
            case 123: 
            case 125: {
                basicValue4 = BasicValue.LONG_VALUE;
                basicValue3 = BasicValue.INT_VALUE;
                break;
            }
            case 99: 
            case 103: 
            case 107: 
            case 111: 
            case 115: 
            case 151: 
            case 152: {
                basicValue4 = BasicValue.DOUBLE_VALUE;
                basicValue3 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 165: 
            case 166: {
                basicValue4 = BasicValue.REFERENCE_VALUE;
                basicValue3 = BasicValue.REFERENCE_VALUE;
                break;
            }
            case 181: {
                FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
                basicValue4 = this.newValue(Type.getObjectType(fieldInsnNode.owner));
                basicValue3 = this.newValue(Type.getType(fieldInsnNode.desc));
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(basicValue, basicValue4)) {
            throw new AnalyzerException(abstractInsnNode, "First argument", basicValue4, basicValue);
        }
        if (!this.isSubTypeOf(basicValue2, basicValue3)) {
            throw new AnalyzerException(abstractInsnNode, "Second argument", basicValue3, basicValue2);
        }
        if (abstractInsnNode.getOpcode() == 50) {
            return this.getElementValue(basicValue);
        }
        return super.binaryOperation(abstractInsnNode, basicValue, basicValue2);
    }

    @Override
    public BasicValue ternaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2, BasicValue basicValue3) throws AnalyzerException {
        BasicValue basicValue4;
        BasicValue basicValue5;
        switch (abstractInsnNode.getOpcode()) {
            case 79: {
                basicValue5 = this.newValue(Type.getType("[I"));
                basicValue4 = BasicValue.INT_VALUE;
                break;
            }
            case 84: {
                basicValue5 = this.isSubTypeOf(basicValue, this.newValue(Type.getType("[Z"))) ? this.newValue(Type.getType("[Z")) : this.newValue(Type.getType("[B"));
                basicValue4 = BasicValue.INT_VALUE;
                break;
            }
            case 85: {
                basicValue5 = this.newValue(Type.getType("[C"));
                basicValue4 = BasicValue.INT_VALUE;
                break;
            }
            case 86: {
                basicValue5 = this.newValue(Type.getType("[S"));
                basicValue4 = BasicValue.INT_VALUE;
                break;
            }
            case 80: {
                basicValue5 = this.newValue(Type.getType("[J"));
                basicValue4 = BasicValue.LONG_VALUE;
                break;
            }
            case 81: {
                basicValue5 = this.newValue(Type.getType("[F"));
                basicValue4 = BasicValue.FLOAT_VALUE;
                break;
            }
            case 82: {
                basicValue5 = this.newValue(Type.getType("[D"));
                basicValue4 = BasicValue.DOUBLE_VALUE;
                break;
            }
            case 83: {
                basicValue5 = basicValue;
                basicValue4 = BasicValue.REFERENCE_VALUE;
                break;
            }
            default: {
                throw new Error("Internal error.");
            }
        }
        if (!this.isSubTypeOf(basicValue, basicValue5)) {
            throw new AnalyzerException(abstractInsnNode, "First argument", "a " + basicValue5 + " array reference", basicValue);
        }
        if (!BasicValue.INT_VALUE.equals(basicValue2)) {
            throw new AnalyzerException(abstractInsnNode, "Second argument", BasicValue.INT_VALUE, basicValue2);
        }
        if (!this.isSubTypeOf(basicValue3, basicValue4)) {
            throw new AnalyzerException(abstractInsnNode, "Third argument", basicValue4, basicValue3);
        }
        return null;
    }

    @Override
    public BasicValue naryOperation(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) throws AnalyzerException {
        int n2 = abstractInsnNode.getOpcode();
        if (n2 == 197) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                if (BasicValue.INT_VALUE.equals(list.get(i2))) continue;
                throw new AnalyzerException(abstractInsnNode, null, BasicValue.INT_VALUE, list.get(i2));
            }
        } else {
            Object object;
            int n3 = 0;
            int n4 = 0;
            if (n2 != 184 && n2 != 186) {
                object = Type.getObjectType(((MethodInsnNode)abstractInsnNode).owner);
                if (!this.isSubTypeOf(list.get(n3++), this.newValue((Type)object))) {
                    throw new AnalyzerException(abstractInsnNode, "Method owner", this.newValue((Type)object), list.get(0));
                }
            }
            object = n2 == 186 ? ((InvokeDynamicInsnNode)abstractInsnNode).desc : ((MethodInsnNode)abstractInsnNode).desc;
            Type[] arrtype = Type.getArgumentTypes((String)object);
            while (n3 < list.size()) {
                BasicValue basicValue;
                BasicValue basicValue2 = this.newValue(arrtype[n4++]);
                if (this.isSubTypeOf(basicValue = list.get(n3++), basicValue2)) continue;
                throw new AnalyzerException(abstractInsnNode, "Argument " + n4, basicValue2, basicValue);
            }
        }
        return super.naryOperation(abstractInsnNode, (List)list);
    }

    @Override
    public void returnOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2) throws AnalyzerException {
        if (!this.isSubTypeOf(basicValue, basicValue2)) {
            throw new AnalyzerException(abstractInsnNode, "Incompatible return type", basicValue2, basicValue);
        }
    }

    protected boolean isArrayValue(BasicValue basicValue) {
        return basicValue.isReference();
    }

    protected BasicValue getElementValue(BasicValue basicValue) throws AnalyzerException {
        return BasicValue.REFERENCE_VALUE;
    }

    protected boolean isSubTypeOf(BasicValue basicValue, BasicValue basicValue2) {
        return basicValue.equals(basicValue2);
    }
}

