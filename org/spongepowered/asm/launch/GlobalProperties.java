/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.launch;

import java.util.ServiceLoader;
import org.spongepowered.asm.service.IGlobalPropertyService;

public final class GlobalProperties {
    private static IGlobalPropertyService service;

    private GlobalProperties() {
    }

    private static IGlobalPropertyService getService() {
        if (service == null) {
            ServiceLoader<IGlobalPropertyService> serviceLoader = ServiceLoader.load(IGlobalPropertyService.class, GlobalProperties.class.getClassLoader());
            service = serviceLoader.iterator().next();
        }
        return service;
    }

    public static <T> T get(String string) {
        return GlobalProperties.getService().getProperty(string);
    }

    public static void put(String string, Object object) {
        GlobalProperties.getService().setProperty(string, object);
    }

    public static <T> T get(String string, T t2) {
        return GlobalProperties.getService().getProperty(string, t2);
    }

    public static String getString(String string, String string2) {
        return GlobalProperties.getService().getPropertyString(string, string2);
    }

    public static final class Keys {
        public static final String INIT = "mixin.initialised";
        public static final String AGENTS = "mixin.agents";
        public static final String CONFIGS = "mixin.configs";
        public static final String TRANSFORMER = "mixin.transformer";
        public static final String PLATFORM_MANAGER = "mixin.platform";
        public static final String FML_LOAD_CORE_MOD = "mixin.launch.fml.loadcoremodmethod";
        public static final String FML_GET_REPARSEABLE_COREMODS = "mixin.launch.fml.reparseablecoremodsmethod";
        public static final String FML_CORE_MOD_MANAGER = "mixin.launch.fml.coremodmanagerclass";
        public static final String FML_GET_IGNORED_MODS = "mixin.launch.fml.ignoredmodsmethod";

        private Keys() {
        }
    }
}

