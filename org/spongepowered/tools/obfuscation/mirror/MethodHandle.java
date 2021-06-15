/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.base.Strings;
import javax.lang.model.element.ExecutableElement;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.MemberHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.Visibility;
import org.spongepowered.tools.obfuscation.mirror.mapping.ResolvableMappingMethod;

public class MethodHandle
extends MemberHandle<MappingMethod> {
    private final ExecutableElement element;
    private final TypeHandle ownerHandle;

    public MethodHandle(TypeHandle typeHandle, ExecutableElement executableElement) {
        this(typeHandle, executableElement, TypeUtils.getName(executableElement), TypeUtils.getDescriptor(executableElement));
    }

    public MethodHandle(TypeHandle typeHandle, String string, String string2) {
        this(typeHandle, null, string, string2);
    }

    private MethodHandle(TypeHandle typeHandle, ExecutableElement executableElement, String string, String string2) {
        super(typeHandle != null ? typeHandle.getName() : null, string, string2);
        this.element = executableElement;
        this.ownerHandle = typeHandle;
    }

    public boolean isImaginary() {
        return this.element == null;
    }

    public ExecutableElement getElement() {
        return this.element;
    }

    @Override
    public Visibility getVisibility() {
        return TypeUtils.getVisibility(this.element);
    }

    @Override
    public MappingMethod asMapping(boolean bl) {
        if (bl) {
            if (this.ownerHandle != null) {
                return new ResolvableMappingMethod(this.ownerHandle, this.getName(), this.getDesc());
            }
            return new MappingMethod(this.getOwner(), this.getName(), this.getDesc());
        }
        return new MappingMethod(null, this.getName(), this.getDesc());
    }

    public String toString() {
        String string = this.getOwner() != null ? "L" + this.getOwner() + ";" : "";
        String string2 = Strings.nullToEmpty(this.getName());
        String string3 = Strings.nullToEmpty(this.getDesc());
        return String.format("%s%s%s", string, string2, string3);
    }
}

