/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.commons.Remapper
 */
package org.spongepowered.asm.bridge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.commons.Remapper;
import org.spongepowered.asm.mixin.extensibility.IRemapper;
import org.spongepowered.asm.util.ObfuscationUtil;

public abstract class RemapperAdapter
implements IRemapper,
ObfuscationUtil.IClassRemapper {
    protected final Logger logger = LogManager.getLogger((String)"mixin");
    protected final Remapper remapper;

    public RemapperAdapter(Remapper remapper) {
        this.remapper = remapper;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String mapMethodName(String string, String string2, String string3) {
        this.logger.debug("{} is remapping method {}{} for {}", new Object[]{this, string2, string3, string});
        String string4 = this.remapper.mapMethodName(string, string2, string3);
        if (!string4.equals(string2)) {
            return string4;
        }
        String string5 = this.unmap(string);
        String string6 = this.unmapDesc(string3);
        this.logger.debug("{} is remapping obfuscated method {}{} for {}", new Object[]{this, string2, string6, string5});
        return this.remapper.mapMethodName(string5, string2, string6);
    }

    @Override
    public String mapFieldName(String string, String string2, String string3) {
        this.logger.debug("{} is remapping field {}{} for {}", new Object[]{this, string2, string3, string});
        String string4 = this.remapper.mapFieldName(string, string2, string3);
        if (!string4.equals(string2)) {
            return string4;
        }
        String string5 = this.unmap(string);
        String string6 = this.unmapDesc(string3);
        this.logger.debug("{} is remapping obfuscated field {}{} for {}", new Object[]{this, string2, string6, string5});
        return this.remapper.mapFieldName(string5, string2, string6);
    }

    @Override
    public String map(String string) {
        this.logger.debug("{} is remapping class {}", new Object[]{this, string});
        return this.remapper.map(string);
    }

    @Override
    public String unmap(String string) {
        return string;
    }

    @Override
    public String mapDesc(String string) {
        return this.remapper.mapDesc(string);
    }

    @Override
    public String unmapDesc(String string) {
        return ObfuscationUtil.unmapDescriptor(string, this);
    }
}

