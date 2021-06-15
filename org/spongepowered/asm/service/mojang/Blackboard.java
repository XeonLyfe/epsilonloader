/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 */
package org.spongepowered.asm.service.mojang;

import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.service.IGlobalPropertyService;

public class Blackboard
implements IGlobalPropertyService {
    @Override
    public final <T> T getProperty(String string) {
        return (T)Launch.blackboard.get(string);
    }

    @Override
    public final void setProperty(String string, Object object) {
        Launch.blackboard.put(string, object);
    }

    @Override
    public final <T> T getProperty(String string, T t2) {
        Object v2 = Launch.blackboard.get(string);
        return (T)(v2 != null ? v2 : t2);
    }

    @Override
    public final String getPropertyString(String string, String string2) {
        Object v2 = Launch.blackboard.get(string);
        return v2 != null ? v2.toString() : string2;
    }
}

