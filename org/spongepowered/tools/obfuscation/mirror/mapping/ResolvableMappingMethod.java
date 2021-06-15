/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror.mapping;

import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

public final class ResolvableMappingMethod
extends MappingMethod {
    private final TypeHandle ownerHandle;

    public ResolvableMappingMethod(TypeHandle typeHandle, String string, String string2) {
        super(typeHandle.getName(), string, string2);
        this.ownerHandle = typeHandle;
    }

    @Override
    public MappingMethod getSuper() {
        if (this.ownerHandle == null) {
            return super.getSuper();
        }
        String string = this.getSimpleName();
        String string2 = this.getDesc();
        String string3 = TypeUtils.getJavaSignature(string2);
        TypeHandle typeHandle = this.ownerHandle.getSuperclass();
        if (typeHandle != null && typeHandle.findMethod(string, string3) != null) {
            return typeHandle.getMappingMethod(string, string2);
        }
        for (TypeHandle typeHandle2 : this.ownerHandle.getInterfaces()) {
            if (typeHandle2.findMethod(string, string3) == null) continue;
            return typeHandle2.getMappingMethod(string, string2);
        }
        if (typeHandle != null) {
            return typeHandle.getMappingMethod(string, string2).getSuper();
        }
        return super.getSuper();
    }

    public MappingMethod move(TypeHandle typeHandle) {
        return new ResolvableMappingMethod(typeHandle, this.getSimpleName(), this.getDesc());
    }

    @Override
    public MappingMethod remap(String string) {
        return new ResolvableMappingMethod(this.ownerHandle, string, this.getDesc());
    }

    @Override
    public MappingMethod transform(String string) {
        return new ResolvableMappingMethod(this.ownerHandle, this.getSimpleName(), string);
    }

    @Override
    public MappingMethod copy() {
        return new ResolvableMappingMethod(this.ownerHandle, this.getSimpleName(), this.getDesc());
    }
}

