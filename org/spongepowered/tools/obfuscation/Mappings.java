/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

class Mappings
implements IMappingConsumer {
    private final Map<ObfuscationType, IMappingConsumer.MappingSet<MappingField>> fieldMappings = new HashMap<ObfuscationType, IMappingConsumer.MappingSet<MappingField>>();
    private final Map<ObfuscationType, IMappingConsumer.MappingSet<MappingMethod>> methodMappings = new HashMap<ObfuscationType, IMappingConsumer.MappingSet<MappingMethod>>();
    private UniqueMappings unique;

    public Mappings() {
        this.init();
    }

    private void init() {
        for (ObfuscationType obfuscationType : ObfuscationType.types()) {
            this.fieldMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
            this.methodMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
        }
    }

    public IMappingConsumer asUnique() {
        if (this.unique == null) {
            this.unique = new UniqueMappings(this);
        }
        return this.unique;
    }

    @Override
    public IMappingConsumer.MappingSet<MappingField> getFieldMappings(ObfuscationType obfuscationType) {
        IMappingConsumer.MappingSet mappingSet = this.fieldMappings.get(obfuscationType);
        return mappingSet != null ? mappingSet : new IMappingConsumer.MappingSet();
    }

    @Override
    public IMappingConsumer.MappingSet<MappingMethod> getMethodMappings(ObfuscationType obfuscationType) {
        IMappingConsumer.MappingSet mappingSet = this.methodMappings.get(obfuscationType);
        return mappingSet != null ? mappingSet : new IMappingConsumer.MappingSet();
    }

    @Override
    public void clear() {
        this.fieldMappings.clear();
        this.methodMappings.clear();
        if (this.unique != null) {
            this.unique.clearMaps();
        }
        this.init();
    }

    @Override
    public void addFieldMapping(ObfuscationType obfuscationType, MappingField mappingField, MappingField mappingField2) {
        IMappingConsumer.MappingSet<MappingField> mappingSet = this.fieldMappings.get(obfuscationType);
        if (mappingSet == null) {
            mappingSet = new IMappingConsumer.MappingSet();
            this.fieldMappings.put(obfuscationType, mappingSet);
        }
        mappingSet.add(new IMappingConsumer.MappingSet.Pair<MappingField>(mappingField, mappingField2));
    }

    @Override
    public void addMethodMapping(ObfuscationType obfuscationType, MappingMethod mappingMethod, MappingMethod mappingMethod2) {
        IMappingConsumer.MappingSet<MappingMethod> mappingSet = this.methodMappings.get(obfuscationType);
        if (mappingSet == null) {
            mappingSet = new IMappingConsumer.MappingSet();
            this.methodMappings.put(obfuscationType, mappingSet);
        }
        mappingSet.add(new IMappingConsumer.MappingSet.Pair<MappingMethod>(mappingMethod, mappingMethod2));
    }

    static class UniqueMappings
    implements IMappingConsumer {
        private final IMappingConsumer mappings;
        private final Map<ObfuscationType, Map<MappingField, MappingField>> fields = new HashMap<ObfuscationType, Map<MappingField, MappingField>>();
        private final Map<ObfuscationType, Map<MappingMethod, MappingMethod>> methods = new HashMap<ObfuscationType, Map<MappingMethod, MappingMethod>>();

        public UniqueMappings(IMappingConsumer iMappingConsumer) {
            this.mappings = iMappingConsumer;
        }

        @Override
        public void clear() {
            this.clearMaps();
            this.mappings.clear();
        }

        protected void clearMaps() {
            this.fields.clear();
            this.methods.clear();
        }

        @Override
        public void addFieldMapping(ObfuscationType obfuscationType, MappingField mappingField, MappingField mappingField2) {
            if (!this.checkForExistingMapping(obfuscationType, mappingField, mappingField2, this.fields)) {
                this.mappings.addFieldMapping(obfuscationType, mappingField, mappingField2);
            }
        }

        @Override
        public void addMethodMapping(ObfuscationType obfuscationType, MappingMethod mappingMethod, MappingMethod mappingMethod2) {
            if (!this.checkForExistingMapping(obfuscationType, mappingMethod, mappingMethod2, this.methods)) {
                this.mappings.addMethodMapping(obfuscationType, mappingMethod, mappingMethod2);
            }
        }

        private <TMapping extends IMapping<TMapping>> boolean checkForExistingMapping(ObfuscationType obfuscationType, TMapping TMapping, TMapping TMapping2, Map<ObfuscationType, Map<TMapping, TMapping>> map) throws MappingConflictException {
            IMapping iMapping;
            Map<TMapping, TMapping> map2 = map.get(obfuscationType);
            if (map2 == null) {
                map2 = new HashMap<TMapping, TMapping>();
                map.put(obfuscationType, map2);
            }
            if ((iMapping = (IMapping)map2.get(TMapping)) != null) {
                if (iMapping.equals(TMapping2)) {
                    return true;
                }
                throw new MappingConflictException(iMapping, TMapping2);
            }
            map2.put(TMapping, TMapping2);
            return false;
        }

        @Override
        public IMappingConsumer.MappingSet<MappingField> getFieldMappings(ObfuscationType obfuscationType) {
            return this.mappings.getFieldMappings(obfuscationType);
        }

        @Override
        public IMappingConsumer.MappingSet<MappingMethod> getMethodMappings(ObfuscationType obfuscationType) {
            return this.mappings.getMethodMappings(obfuscationType);
        }
    }

    public static class MappingConflictException
    extends RuntimeException {
        private static final long serialVersionUID = 1L;
        private final IMapping<?> oldMapping;
        private final IMapping<?> newMapping;

        public MappingConflictException(IMapping<?> iMapping, IMapping<?> iMapping2) {
            this.oldMapping = iMapping;
            this.newMapping = iMapping2;
        }

        public IMapping<?> getOld() {
            return this.oldMapping;
        }

        public IMapping<?> getNew() {
            return this.newMapping;
        }
    }
}

