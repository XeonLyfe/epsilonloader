/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package org.spongepowered.asm.service.mojang;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.LaunchClassLoader;

final class LaunchClassLoaderUtil {
    private static final String CACHED_CLASSES_FIELD = "cachedClasses";
    private static final String INVALID_CLASSES_FIELD = "invalidClasses";
    private static final String CLASS_LOADER_EXCEPTIONS_FIELD = "classLoaderExceptions";
    private static final String TRANSFORMER_EXCEPTIONS_FIELD = "transformerExceptions";
    private final LaunchClassLoader classLoader;
    private final Map<String, Class<?>> cachedClasses;
    private final Set<String> invalidClasses;
    private final Set<String> classLoaderExceptions;
    private final Set<String> transformerExceptions;

    LaunchClassLoaderUtil(LaunchClassLoader launchClassLoader) {
        this.classLoader = launchClassLoader;
        this.cachedClasses = (Map)LaunchClassLoaderUtil.getField(launchClassLoader, CACHED_CLASSES_FIELD);
        this.invalidClasses = (Set)LaunchClassLoaderUtil.getField(launchClassLoader, INVALID_CLASSES_FIELD);
        this.classLoaderExceptions = (Set)LaunchClassLoaderUtil.getField(launchClassLoader, CLASS_LOADER_EXCEPTIONS_FIELD);
        this.transformerExceptions = (Set)LaunchClassLoaderUtil.getField(launchClassLoader, TRANSFORMER_EXCEPTIONS_FIELD);
    }

    LaunchClassLoader getClassLoader() {
        return this.classLoader;
    }

    boolean isClassLoaded(String string) {
        return this.cachedClasses.containsKey(string);
    }

    boolean isClassExcluded(String string, String string2) {
        return this.isClassClassLoaderExcluded(string, string2) || this.isClassTransformerExcluded(string, string2);
    }

    boolean isClassClassLoaderExcluded(String string, String string2) {
        for (String string3 : this.getClassLoaderExceptions()) {
            if ((string2 == null || !string2.startsWith(string3)) && !string.startsWith(string3)) continue;
            return true;
        }
        return false;
    }

    boolean isClassTransformerExcluded(String string, String string2) {
        for (String string3 : this.getTransformerExceptions()) {
            if ((string2 == null || !string2.startsWith(string3)) && !string.startsWith(string3)) continue;
            return true;
        }
        return false;
    }

    void registerInvalidClass(String string) {
        if (this.invalidClasses != null) {
            this.invalidClasses.add(string);
        }
    }

    Set<String> getClassLoaderExceptions() {
        if (this.classLoaderExceptions != null) {
            return this.classLoaderExceptions;
        }
        return Collections.emptySet();
    }

    Set<String> getTransformerExceptions() {
        if (this.transformerExceptions != null) {
            return this.transformerExceptions;
        }
        return Collections.emptySet();
    }

    private static <T> T getField(LaunchClassLoader launchClassLoader, String string) {
        try {
            Field field = LaunchClassLoader.class.getDeclaredField(string);
            field.setAccessible(true);
            return (T)field.get((Object)launchClassLoader);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}

