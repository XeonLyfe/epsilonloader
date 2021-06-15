/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.service;

import org.spongepowered.tools.obfuscation.ObfuscationEnvironment;

public class ObfuscationTypeDescriptor {
    private final String key;
    private final String inputFileArgName;
    private final String extraInputFilesArgName;
    private final String outFileArgName;
    private final Class<? extends ObfuscationEnvironment> environmentType;

    public ObfuscationTypeDescriptor(String string, String string2, String string3, Class<? extends ObfuscationEnvironment> class_) {
        this(string, string2, null, string3, class_);
    }

    public ObfuscationTypeDescriptor(String string, String string2, String string3, String string4, Class<? extends ObfuscationEnvironment> class_) {
        this.key = string;
        this.inputFileArgName = string2;
        this.extraInputFilesArgName = string3;
        this.outFileArgName = string4;
        this.environmentType = class_;
    }

    public final String getKey() {
        return this.key;
    }

    public String getInputFileOption() {
        return this.inputFileArgName;
    }

    public String getExtraInputFilesOption() {
        return this.extraInputFilesArgName;
    }

    public String getOutputFileOption() {
        return this.outFileArgName;
    }

    public Class<? extends ObfuscationEnvironment> getEnvironmentType() {
        return this.environmentType;
    }
}

