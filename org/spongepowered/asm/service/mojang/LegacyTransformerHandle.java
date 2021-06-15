/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 */
package org.spongepowered.asm.service.mojang;

import javax.annotation.Resource;
import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.service.ILegacyClassTransformer;

class LegacyTransformerHandle
implements ILegacyClassTransformer {
    private final IClassTransformer transformer;

    LegacyTransformerHandle(IClassTransformer iClassTransformer) {
        this.transformer = iClassTransformer;
    }

    @Override
    public String getName() {
        return this.transformer.getClass().getName();
    }

    @Override
    public boolean isDelegationExcluded() {
        return this.transformer.getClass().getAnnotation(Resource.class) != null;
    }

    @Override
    public byte[] transformClassBytes(String string, String string2, byte[] arrby) {
        return this.transformer.transform(string, string2, arrby);
    }
}

