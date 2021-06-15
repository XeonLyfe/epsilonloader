/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.commons;

import java.util.Collections;
import java.util.Map;
import org.spongepowered.asm.lib.commons.Remapper;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimpleRemapper
extends Remapper {
    private final Map<String, String> mapping;

    public SimpleRemapper(Map<String, String> map) {
        this.mapping = map;
    }

    public SimpleRemapper(String string, String string2) {
        this.mapping = Collections.singletonMap(string, string2);
    }

    @Override
    public String mapMethodName(String string, String string2, String string3) {
        String string4 = this.map(string + '.' + string2 + string3);
        return string4 == null ? string2 : string4;
    }

    @Override
    public String mapInvokeDynamicMethodName(String string, String string2) {
        String string3 = this.map('.' + string + string2);
        return string3 == null ? string : string3;
    }

    @Override
    public String mapFieldName(String string, String string2, String string3) {
        String string4 = this.map(string + '.' + string2);
        return string4 == null ? string2 : string4;
    }

    @Override
    public String map(String string) {
        return this.mapping.get(string);
    }
}

