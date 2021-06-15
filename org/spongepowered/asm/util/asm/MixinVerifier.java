/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.asm;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.analysis.SimpleVerifier;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

public class MixinVerifier
extends SimpleVerifier {
    private Type currentClass;
    private Type currentSuperClass;
    private List<Type> currentClassInterfaces;
    private boolean isInterface;

    public MixinVerifier(Type type, Type type2, List<Type> list, boolean bl) {
        super(type, type2, list, bl);
        this.currentClass = type;
        this.currentSuperClass = type2;
        this.currentClassInterfaces = list;
        this.isInterface = bl;
    }

    @Override
    protected boolean isInterface(Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.isInterface;
        }
        return ClassInfo.forType(type).isInterface();
    }

    @Override
    protected Type getSuperClass(Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.currentSuperClass;
        }
        ClassInfo classInfo = ClassInfo.forType(type).getSuperClass();
        return classInfo == null ? null : Type.getType("L" + classInfo.getName() + ";");
    }

    @Override
    protected boolean isAssignableFrom(Type type, Type type2) {
        if (type.equals(type2)) {
            return true;
        }
        if (this.currentClass != null && type.equals(this.currentClass)) {
            if (this.getSuperClass(type2) == null) {
                return false;
            }
            if (this.isInterface) {
                return type2.getSort() == 10 || type2.getSort() == 9;
            }
            return this.isAssignableFrom(type, this.getSuperClass(type2));
        }
        if (this.currentClass != null && type2.equals(this.currentClass)) {
            if (this.isAssignableFrom(type, this.currentSuperClass)) {
                return true;
            }
            if (this.currentClassInterfaces != null) {
                for (int i2 = 0; i2 < this.currentClassInterfaces.size(); ++i2) {
                    Type type3 = this.currentClassInterfaces.get(i2);
                    if (!this.isAssignableFrom(type, type3)) continue;
                    return true;
                }
            }
            return false;
        }
        ClassInfo classInfo = ClassInfo.forType(type);
        if (classInfo == null) {
            return false;
        }
        if (classInfo.isInterface()) {
            classInfo = ClassInfo.forName("java/lang/Object");
        }
        return ClassInfo.forType(type2).hasSuperClass(classInfo);
    }
}

