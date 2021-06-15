/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.List;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationEnvironment;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public class ObfuscationDataProvider
implements IObfuscationDataProvider {
    private final IMixinAnnotationProcessor ap;
    private final List<ObfuscationEnvironment> environments;

    public ObfuscationDataProvider(IMixinAnnotationProcessor iMixinAnnotationProcessor, List<ObfuscationEnvironment> list) {
        this.ap = iMixinAnnotationProcessor;
        this.environments = list;
    }

    @Override
    public <T> ObfuscationData<T> getObfEntryRecursive(MemberInfo memberInfo) {
        MemberInfo memberInfo2 = memberInfo;
        ObfuscationData<String> obfuscationData = this.getObfClass(memberInfo2.owner);
        ObfuscationData<T> obfuscationData2 = this.getObfEntry(memberInfo2);
        try {
            while (obfuscationData2.isEmpty()) {
                TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(memberInfo2.owner);
                if (typeHandle == null) {
                    return obfuscationData2;
                }
                TypeHandle typeHandle2 = typeHandle.getSuperclass();
                obfuscationData2 = this.getObfEntryUsing(memberInfo2, typeHandle2);
                if (!obfuscationData2.isEmpty()) {
                    return ObfuscationDataProvider.applyParents(obfuscationData, obfuscationData2);
                }
                for (TypeHandle typeHandle3 : typeHandle.getInterfaces()) {
                    obfuscationData2 = this.getObfEntryUsing(memberInfo2, typeHandle3);
                    if (obfuscationData2.isEmpty()) continue;
                    return ObfuscationDataProvider.applyParents(obfuscationData, obfuscationData2);
                }
                if (typeHandle2 != null) {
                    memberInfo2 = memberInfo2.move(typeHandle2.getName());
                    continue;
                }
                break;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return this.getObfEntry(memberInfo);
        }
        return obfuscationData2;
    }

    private <T> ObfuscationData<T> getObfEntryUsing(MemberInfo memberInfo, TypeHandle typeHandle) {
        return typeHandle == null ? new ObfuscationData() : this.getObfEntry(memberInfo.move(typeHandle.getName()));
    }

    @Override
    public <T> ObfuscationData<T> getObfEntry(MemberInfo memberInfo) {
        if (memberInfo.isField()) {
            return this.getObfField(memberInfo);
        }
        return this.getObfMethod(memberInfo.asMethodMapping());
    }

    @Override
    public <T> ObfuscationData<T> getObfEntry(IMapping<T> iMapping) {
        if (iMapping != null) {
            if (iMapping.getType() == IMapping.Type.FIELD) {
                return this.getObfField((MappingField)iMapping);
            }
            if (iMapping.getType() == IMapping.Type.METHOD) {
                return this.getObfMethod((MappingMethod)iMapping);
            }
        }
        return new ObfuscationData();
    }

    @Override
    public ObfuscationData<MappingMethod> getObfMethodRecursive(MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }

    @Override
    public ObfuscationData<MappingMethod> getObfMethod(MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, memberInfo.isConstructor());
    }

    @Override
    public ObfuscationData<MappingMethod> getRemappedMethod(MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, true);
    }

    private ObfuscationData<MappingMethod> getRemappedMethod(MemberInfo memberInfo, boolean bl) {
        ObfuscationData<MappingMethod> obfuscationData = new ObfuscationData<MappingMethod>();
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingMethod mappingMethod = obfuscationEnvironment.getObfMethod(memberInfo);
            if (mappingMethod == null) continue;
            obfuscationData.put(obfuscationEnvironment.getType(), mappingMethod);
        }
        if (!obfuscationData.isEmpty() || !bl) {
            return obfuscationData;
        }
        return this.remapDescriptor(obfuscationData, memberInfo);
    }

    @Override
    public ObfuscationData<MappingMethod> getObfMethod(MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, mappingMethod.isConstructor());
    }

    @Override
    public ObfuscationData<MappingMethod> getRemappedMethod(MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, true);
    }

    private ObfuscationData<MappingMethod> getRemappedMethod(MappingMethod mappingMethod, boolean bl) {
        ObfuscationData<MappingMethod> obfuscationData = new ObfuscationData<MappingMethod>();
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingMethod mappingMethod2 = obfuscationEnvironment.getObfMethod(mappingMethod);
            if (mappingMethod2 == null) continue;
            obfuscationData.put(obfuscationEnvironment.getType(), mappingMethod2);
        }
        if (!obfuscationData.isEmpty() || !bl) {
            return obfuscationData;
        }
        return this.remapDescriptor(obfuscationData, new MemberInfo(mappingMethod));
    }

    public ObfuscationData<MappingMethod> remapDescriptor(ObfuscationData<MappingMethod> obfuscationData, MemberInfo memberInfo) {
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MemberInfo memberInfo2 = obfuscationEnvironment.remapDescriptor(memberInfo);
            if (memberInfo2 == null) continue;
            obfuscationData.put(obfuscationEnvironment.getType(), memberInfo2.asMethodMapping());
        }
        return obfuscationData;
    }

    @Override
    public ObfuscationData<MappingField> getObfFieldRecursive(MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }

    @Override
    public ObfuscationData<MappingField> getObfField(MemberInfo memberInfo) {
        return this.getObfField(memberInfo.asFieldMapping());
    }

    @Override
    public ObfuscationData<MappingField> getObfField(MappingField mappingField) {
        ObfuscationData<MappingField> obfuscationData = new ObfuscationData<MappingField>();
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingField mappingField2 = obfuscationEnvironment.getObfField(mappingField);
            if (mappingField2 == null) continue;
            if (mappingField2.getDesc() == null && mappingField.getDesc() != null) {
                mappingField2 = mappingField2.transform(obfuscationEnvironment.remapDescriptor(mappingField.getDesc()));
            }
            obfuscationData.put(obfuscationEnvironment.getType(), mappingField2);
        }
        return obfuscationData;
    }

    @Override
    public ObfuscationData<String> getObfClass(TypeHandle typeHandle) {
        return this.getObfClass(typeHandle.getName());
    }

    @Override
    public ObfuscationData<String> getObfClass(String string) {
        ObfuscationData<String> obfuscationData = new ObfuscationData<String>(string);
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            String string2 = obfuscationEnvironment.getObfClass(string);
            if (string2 == null) continue;
            obfuscationData.put(obfuscationEnvironment.getType(), string2);
        }
        return obfuscationData;
    }

    private static <T> ObfuscationData<T> applyParents(ObfuscationData<String> obfuscationData, ObfuscationData<T> obfuscationData2) {
        for (ObfuscationType obfuscationType : obfuscationData2) {
            String string = obfuscationData.get(obfuscationType);
            T t2 = obfuscationData2.get(obfuscationType);
            obfuscationData2.put(obfuscationType, MemberInfo.fromMapping((IMapping)t2).move(string).asMapping());
        }
        return obfuscationData2;
    }
}

