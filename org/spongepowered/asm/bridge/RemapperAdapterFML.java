/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.commons.Remapper
 */
package org.spongepowered.asm.bridge;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.objectweb.asm.commons.Remapper;
import org.spongepowered.asm.bridge.RemapperAdapter;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public final class RemapperAdapterFML
extends RemapperAdapter {
    private static final String DEOBFUSCATING_REMAPPER_CLASS = "fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper";
    private static final String DEOBFUSCATING_REMAPPER_CLASS_FORGE = "net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper";
    private static final String DEOBFUSCATING_REMAPPER_CLASS_LEGACY = "cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper";
    private static final String INSTANCE_FIELD = "INSTANCE";
    private static final String UNMAP_METHOD = "unmap";
    private final Method mdUnmap;

    private RemapperAdapterFML(Remapper remapper, Method method) {
        super(remapper);
        this.logger.info("Initialised Mixin FML Remapper Adapter with {}", new Object[]{remapper});
        this.mdUnmap = method;
    }

    @Override
    public String unmap(String string) {
        try {
            return this.mdUnmap.invoke((Object)this.remapper, string).toString();
        }
        catch (Exception exception) {
            return string;
        }
    }

    public static IRemapper create() {
        try {
            Class<?> class_ = RemapperAdapterFML.getFMLDeobfuscatingRemapper();
            Field field = class_.getDeclaredField(INSTANCE_FIELD);
            Method method = class_.getDeclaredMethod(UNMAP_METHOD, String.class);
            Remapper remapper = (Remapper)field.get(null);
            return new RemapperAdapterFML(remapper, method);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static Class<?> getFMLDeobfuscatingRemapper() throws ClassNotFoundException {
        try {
            return Class.forName(DEOBFUSCATING_REMAPPER_CLASS_FORGE);
        }
        catch (ClassNotFoundException classNotFoundException) {
            return Class.forName(DEOBFUSCATING_REMAPPER_CLASS_LEGACY);
        }
    }
}

