/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.base.Strings;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.tools.obfuscation.mirror.MemberHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.Visibility;

public class FieldHandle
extends MemberHandle<MappingField> {
    private final VariableElement element;
    private final boolean rawType;

    public FieldHandle(TypeElement typeElement, VariableElement variableElement) {
        this(TypeUtils.getInternalName(typeElement), variableElement);
    }

    public FieldHandle(String string, VariableElement variableElement) {
        this(string, variableElement, false);
    }

    public FieldHandle(TypeElement typeElement, VariableElement variableElement, boolean bl) {
        this(TypeUtils.getInternalName(typeElement), variableElement, bl);
    }

    public FieldHandle(String string, VariableElement variableElement, boolean bl) {
        this(string, variableElement, bl, TypeUtils.getName(variableElement), TypeUtils.getInternalName(variableElement));
    }

    public FieldHandle(String string, String string2, String string3) {
        this(string, null, false, string2, string3);
    }

    private FieldHandle(String string, VariableElement variableElement, boolean bl, String string2, String string3) {
        super(string, string2, string3);
        this.element = variableElement;
        this.rawType = bl;
    }

    public boolean isImaginary() {
        return this.element == null;
    }

    public VariableElement getElement() {
        return this.element;
    }

    @Override
    public Visibility getVisibility() {
        return TypeUtils.getVisibility(this.element);
    }

    public boolean isRawType() {
        return this.rawType;
    }

    @Override
    public MappingField asMapping(boolean bl) {
        return new MappingField(bl ? this.getOwner() : null, this.getName(), this.getDesc());
    }

    public String toString() {
        String string = this.getOwner() != null ? "L" + this.getOwner() + ";" : "";
        String string2 = Strings.nullToEmpty(this.getName());
        String string3 = Strings.nullToEmpty(this.getDesc());
        return String.format("%s%s:%s", string, string2, string3);
    }
}

