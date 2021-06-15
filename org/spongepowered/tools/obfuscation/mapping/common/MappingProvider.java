/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mapping.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mapping.IMappingProvider;

public abstract class MappingProvider
implements IMappingProvider {
    protected final Messager messager;
    protected final Filer filer;
    protected final BiMap<String, String> packageMap = HashBiMap.create();
    protected final BiMap<String, String> classMap = HashBiMap.create();
    protected final BiMap<MappingField, MappingField> fieldMap = HashBiMap.create();
    protected final BiMap<MappingMethod, MappingMethod> methodMap = HashBiMap.create();

    public MappingProvider(Messager messager, Filer filer) {
        this.messager = messager;
        this.filer = filer;
    }

    @Override
    public void clear() {
        this.packageMap.clear();
        this.classMap.clear();
        this.fieldMap.clear();
        this.methodMap.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.packageMap.isEmpty() && this.classMap.isEmpty() && this.fieldMap.isEmpty() && this.methodMap.isEmpty();
    }

    @Override
    public MappingMethod getMethodMapping(MappingMethod mappingMethod) {
        return (MappingMethod)this.methodMap.get(mappingMethod);
    }

    @Override
    public MappingField getFieldMapping(MappingField mappingField) {
        return (MappingField)this.fieldMap.get(mappingField);
    }

    @Override
    public String getClassMapping(String string) {
        return (String)this.classMap.get(string);
    }

    @Override
    public String getPackageMapping(String string) {
        return (String)this.packageMap.get(string);
    }
}

