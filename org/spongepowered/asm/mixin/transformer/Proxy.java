/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.apache.logging.log4j.LogManager
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.service.ILegacyClassTransformer;

public final class Proxy
implements IClassTransformer,
ILegacyClassTransformer {
    private static List<Proxy> proxies = new ArrayList<Proxy>();
    private static MixinTransformer transformer = new MixinTransformer();
    private boolean isActive = true;

    public Proxy() {
        for (Proxy proxy : proxies) {
            proxy.isActive = false;
        }
        proxies.add(this);
        LogManager.getLogger((String)"mixin").debug("Adding new mixin transformer proxy #{}", new Object[]{proxies.size()});
    }

    public byte[] transform(String string, String string2, byte[] arrby) {
        if (this.isActive) {
            return transformer.transformClassBytes(string, string2, arrby);
        }
        return arrby;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isDelegationExcluded() {
        return true;
    }

    @Override
    public byte[] transformClassBytes(String string, String string2, byte[] arrby) {
        if (this.isActive) {
            return transformer.transformClassBytes(string, string2, arrby);
        }
        return arrby;
    }
}

