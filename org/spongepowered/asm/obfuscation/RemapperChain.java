/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.obfuscation;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public class RemapperChain
implements IRemapper {
    private final List<IRemapper> remappers = new ArrayList<IRemapper>();

    public String toString() {
        return String.format("RemapperChain[%d]", this.remappers.size());
    }

    public RemapperChain add(IRemapper iRemapper) {
        this.remappers.add(iRemapper);
        return this;
    }

    @Override
    public String mapMethodName(String string, String string2, String string3) {
        for (IRemapper iRemapper : this.remappers) {
            String string4 = iRemapper.mapMethodName(string, string2, string3);
            if (string4 == null || string4.equals(string2)) continue;
            string2 = string4;
        }
        return string2;
    }

    @Override
    public String mapFieldName(String string, String string2, String string3) {
        for (IRemapper iRemapper : this.remappers) {
            String string4 = iRemapper.mapFieldName(string, string2, string3);
            if (string4 == null || string4.equals(string2)) continue;
            string2 = string4;
        }
        return string2;
    }

    @Override
    public String map(String string) {
        for (IRemapper iRemapper : this.remappers) {
            String string2 = iRemapper.map(string);
            if (string2 == null || string2.equals(string)) continue;
            string = string2;
        }
        return string;
    }

    @Override
    public String unmap(String string) {
        for (IRemapper iRemapper : this.remappers) {
            String string2 = iRemapper.unmap(string);
            if (string2 == null || string2.equals(string)) continue;
            string = string2;
        }
        return string;
    }

    @Override
    public String mapDesc(String string) {
        for (IRemapper iRemapper : this.remappers) {
            String string2 = iRemapper.mapDesc(string);
            if (string2 == null || string2.equals(string)) continue;
            string = string2;
        }
        return string;
    }

    @Override
    public String unmapDesc(String string) {
        for (IRemapper iRemapper : this.remappers) {
            String string2 = iRemapper.unmapDesc(string);
            if (string2 == null || string2.equals(string)) continue;
            string = string2;
        }
        return string;
    }
}

