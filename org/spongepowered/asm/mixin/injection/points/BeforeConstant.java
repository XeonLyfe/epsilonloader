/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.injection.points;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

@InjectionPoint.AtCode(value="CONSTANT")
public class BeforeConstant
extends InjectionPoint {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final int ordinal;
    private final boolean nullValue;
    private final Integer intValue;
    private final Float floatValue;
    private final Long longValue;
    private final Double doubleValue;
    private final String stringValue;
    private final Type typeValue;
    private final int[] expandOpcodes;
    private final boolean expand;
    private final String matchByType;
    private final boolean log;

    public BeforeConstant(IMixinContext iMixinContext, AnnotationNode annotationNode, String string) {
        super(Annotations.getValue(annotationNode, "slice", ""), InjectionPoint.Selector.DEFAULT, null);
        Boolean bl = Annotations.getValue(annotationNode, "nullValue", (Boolean)null);
        this.ordinal = Annotations.getValue(annotationNode, "ordinal", -1);
        this.nullValue = bl != null && bl != false;
        this.intValue = Annotations.getValue(annotationNode, "intValue", (Integer)null);
        this.floatValue = Annotations.getValue(annotationNode, "floatValue", (Float)null);
        this.longValue = Annotations.getValue(annotationNode, "longValue", (Long)null);
        this.doubleValue = Annotations.getValue(annotationNode, "doubleValue", (Double)null);
        this.stringValue = Annotations.getValue(annotationNode, "stringValue", (String)null);
        this.typeValue = Annotations.getValue(annotationNode, "classValue", (Type)null);
        this.matchByType = this.validateDiscriminator(iMixinContext, string, bl, "on @Constant annotation");
        this.expandOpcodes = this.parseExpandOpcodes(Annotations.getValue(annotationNode, "expandZeroConditions", true, Constant.Condition.class));
        this.expand = this.expandOpcodes.length > 0;
        this.log = Annotations.getValue(annotationNode, "log", Boolean.FALSE);
    }

    public BeforeConstant(InjectionPointData injectionPointData) {
        super(injectionPointData);
        String string = injectionPointData.get("nullValue", null);
        Boolean bl = string != null ? Boolean.valueOf(Boolean.parseBoolean(string)) : null;
        this.ordinal = injectionPointData.getOrdinal();
        this.nullValue = bl != null && bl != false;
        this.intValue = Ints.tryParse(injectionPointData.get("intValue", ""));
        this.floatValue = Floats.tryParse(injectionPointData.get("floatValue", ""));
        this.longValue = Longs.tryParse(injectionPointData.get("longValue", ""));
        this.doubleValue = Doubles.tryParse(injectionPointData.get("doubleValue", ""));
        this.stringValue = injectionPointData.get("stringValue", null);
        String string2 = injectionPointData.get("classValue", null);
        this.typeValue = string2 != null ? Type.getObjectType(string2.replace('.', '/')) : null;
        this.matchByType = this.validateDiscriminator(injectionPointData.getContext(), "V", bl, "in @At(\"CONSTANT\") args");
        if ("V".equals(this.matchByType)) {
            throw new InvalidInjectionException(injectionPointData.getContext(), "No constant discriminator could be parsed in @At(\"CONSTANT\") args");
        }
        ArrayList<Constant.Condition> arrayList = new ArrayList<Constant.Condition>();
        String string3 = injectionPointData.get("expandZeroConditions", "").toLowerCase();
        for (Constant.Condition condition : Constant.Condition.values()) {
            if (!string3.contains(condition.name().toLowerCase())) continue;
            arrayList.add(condition);
        }
        this.expandOpcodes = this.parseExpandOpcodes(arrayList);
        this.expand = this.expandOpcodes.length > 0;
        this.log = injectionPointData.get("log", false);
    }

    private String validateDiscriminator(IMixinContext iMixinContext, String string, Boolean bl, String string2) {
        int n2 = BeforeConstant.count(bl, this.intValue, this.floatValue, this.longValue, this.doubleValue, this.stringValue, this.typeValue);
        if (n2 == 1) {
            string = null;
        } else if (n2 > 1) {
            throw new InvalidInjectionException(iMixinContext, "Conflicting constant discriminators specified " + string2 + " for " + iMixinContext);
        }
        return string;
    }

    private int[] parseExpandOpcodes(List<Constant.Condition> list) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (Constant.Condition condition : list) {
            Constant.Condition condition2 = condition.getEquivalentCondition();
            for (int n2 : condition2.getOpcodes()) {
                hashSet.add(n2);
            }
        }
        return Ints.toArray(hashSet);
    }

    @Override
    public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
        boolean bl = false;
        this.log("BeforeConstant is searching for constants in method with descriptor {}", string);
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        int n2 = 0;
        int n3 = 0;
        while (listIterator.hasNext()) {
            boolean bl2;
            AbstractInsnNode abstractInsnNode = listIterator.next();
            boolean bl3 = bl2 = this.expand ? this.matchesConditionalInsn(n3, abstractInsnNode) : this.matchesConstantInsn(abstractInsnNode);
            if (bl2) {
                this.log("    BeforeConstant found a matching constant{} at ordinal {}", this.matchByType != null ? " TYPE" : " value", n2);
                if (this.ordinal == -1 || this.ordinal == n2) {
                    this.log("      BeforeConstant found {}", Bytecode.describeNode(abstractInsnNode).trim());
                    collection.add(abstractInsnNode);
                    bl = true;
                }
                ++n2;
            }
            if (abstractInsnNode instanceof LabelNode || abstractInsnNode instanceof FrameNode) continue;
            n3 = abstractInsnNode.getOpcode();
        }
        return bl;
    }

    private boolean matchesConditionalInsn(int n2, AbstractInsnNode abstractInsnNode) {
        for (int n3 : this.expandOpcodes) {
            int n4 = abstractInsnNode.getOpcode();
            if (n4 != n3) continue;
            if (n2 == 148 || n2 == 149 || n2 == 150 || n2 == 151 || n2 == 152) {
                this.log("  BeforeConstant is ignoring {} following {}", Bytecode.getOpcodeName(n4), Bytecode.getOpcodeName(n2));
                return false;
            }
            this.log("  BeforeConstant found {} instruction", Bytecode.getOpcodeName(n4));
            return true;
        }
        if (this.intValue != null && this.intValue == 0 && Bytecode.isConstant(abstractInsnNode)) {
            Object object = Bytecode.getConstant(abstractInsnNode);
            this.log("  BeforeConstant found INTEGER constant: value = {}", object);
            return object instanceof Integer && (Integer)object == 0;
        }
        return false;
    }

    private boolean matchesConstantInsn(AbstractInsnNode abstractInsnNode) {
        if (!Bytecode.isConstant(abstractInsnNode)) {
            return false;
        }
        Object object = Bytecode.getConstant(abstractInsnNode);
        if (object == null) {
            this.log("  BeforeConstant found NULL constant: nullValue = {}", this.nullValue);
            return this.nullValue || "Ljava/lang/Object;".equals(this.matchByType);
        }
        if (object instanceof Integer) {
            this.log("  BeforeConstant found INTEGER constant: value = {}, intValue = {}", object, this.intValue);
            return object.equals(this.intValue) || "I".equals(this.matchByType);
        }
        if (object instanceof Float) {
            this.log("  BeforeConstant found FLOAT constant: value = {}, floatValue = {}", object, this.floatValue);
            return object.equals(this.floatValue) || "F".equals(this.matchByType);
        }
        if (object instanceof Long) {
            this.log("  BeforeConstant found LONG constant: value = {}, longValue = {}", object, this.longValue);
            return object.equals(this.longValue) || "J".equals(this.matchByType);
        }
        if (object instanceof Double) {
            this.log("  BeforeConstant found DOUBLE constant: value = {}, doubleValue = {}", object, this.doubleValue);
            return object.equals(this.doubleValue) || "D".equals(this.matchByType);
        }
        if (object instanceof String) {
            this.log("  BeforeConstant found STRING constant: value = {}, stringValue = {}", object, this.stringValue);
            return object.equals(this.stringValue) || "Ljava/lang/String;".equals(this.matchByType);
        }
        if (object instanceof Type) {
            this.log("  BeforeConstant found CLASS constant: value = {}, typeValue = {}", object, this.typeValue);
            return object.equals(this.typeValue) || "Ljava/lang/Class;".equals(this.matchByType);
        }
        return false;
    }

    protected void log(String string, Object ... arrobject) {
        if (this.log) {
            logger.info(string, arrobject);
        }
    }

    private static int count(Object ... arrobject) {
        int n2 = 0;
        for (Object object : arrobject) {
            if (object == null) continue;
            ++n2;
        }
        return n2;
    }
}

