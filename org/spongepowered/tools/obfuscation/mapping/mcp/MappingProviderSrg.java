/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mapping.mcp;

import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import org.spongepowered.tools.obfuscation.mapping.common.MappingProvider;

public class MappingProviderSrg
extends MappingProvider {
    public MappingProviderSrg(Messager messager, Filer filer) {
        super(messager, filer);
    }

    @Override
    public void read(final File file) throws IOException {
        final BiMap biMap = this.packageMap;
        final BiMap biMap2 = this.classMap;
        final BiMap biMap3 = this.fieldMap;
        final BiMap biMap4 = this.methodMap;
        Files.readLines(file, Charset.defaultCharset(), new LineProcessor<String>(){

            @Override
            public String getResult() {
                return null;
            }

            @Override
            public boolean processLine(String string) throws IOException {
                if (Strings.isNullOrEmpty(string) || string.startsWith("#")) {
                    return true;
                }
                String string2 = string.substring(0, 2);
                String[] arrstring = string.substring(4).split(" ");
                if (string2.equals("PK")) {
                    biMap.forcePut(arrstring[0], arrstring[1]);
                } else if (string2.equals("CL")) {
                    biMap2.forcePut(arrstring[0], arrstring[1]);
                } else if (string2.equals("FD")) {
                    biMap3.forcePut(new MappingFieldSrg(arrstring[0]).copy(), new MappingFieldSrg(arrstring[1]).copy());
                } else if (string2.equals("MD")) {
                    biMap4.forcePut(new MappingMethod(arrstring[0], arrstring[1]), new MappingMethod(arrstring[2], arrstring[3]));
                } else {
                    throw new MixinException("Invalid SRG file: " + file);
                }
                return true;
            }
        });
    }

    @Override
    public MappingField getFieldMapping(MappingField mappingField) {
        if (mappingField.getDesc() != null) {
            mappingField = new MappingFieldSrg(mappingField);
        }
        return (MappingField)this.fieldMap.get(mappingField);
    }
}

