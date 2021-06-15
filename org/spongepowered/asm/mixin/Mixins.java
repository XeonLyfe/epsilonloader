/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.Config;

public final class Mixins {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static final String CONFIGS_KEY = "mixin.configs.queue";
    private static final Set<String> errorHandlers = new LinkedHashSet<String>();

    private Mixins() {
    }

    public static void addConfigurations(String ... arrstring) {
        MixinEnvironment mixinEnvironment = MixinEnvironment.getDefaultEnvironment();
        for (String string : arrstring) {
            Mixins.createConfiguration(string, mixinEnvironment);
        }
    }

    public static void addConfiguration(String string) {
        Mixins.createConfiguration(string, MixinEnvironment.getDefaultEnvironment());
    }

    @Deprecated
    static void addConfiguration(String string, MixinEnvironment mixinEnvironment) {
        Mixins.createConfiguration(string, mixinEnvironment);
    }

    private static void createConfiguration(String string, MixinEnvironment mixinEnvironment) {
        Config config = null;
        try {
            config = Config.create(string, mixinEnvironment);
        }
        catch (Exception exception) {
            logger.error("Error encountered reading mixin config " + string + ": " + exception.getClass().getName() + " " + exception.getMessage(), (Throwable)exception);
        }
        Mixins.registerConfiguration(config);
    }

    private static void registerConfiguration(Config config) {
        if (config == null) {
            return;
        }
        MixinEnvironment mixinEnvironment = config.getEnvironment();
        if (mixinEnvironment != null) {
            mixinEnvironment.registerConfig(config.getName());
        }
        Mixins.getConfigs().add(config);
    }

    public static int getUnvisitedCount() {
        int n2 = 0;
        for (Config config : Mixins.getConfigs()) {
            if (config.isVisited()) continue;
            ++n2;
        }
        return n2;
    }

    public static Set<Config> getConfigs() {
        LinkedHashSet linkedHashSet = (LinkedHashSet)GlobalProperties.get(CONFIGS_KEY);
        if (linkedHashSet == null) {
            linkedHashSet = new LinkedHashSet();
            GlobalProperties.put(CONFIGS_KEY, linkedHashSet);
        }
        return linkedHashSet;
    }

    public static void registerErrorHandlerClass(String string) {
        if (string != null) {
            errorHandlers.add(string);
        }
    }

    public static Set<String> getErrorHandlerClasses() {
        return Collections.unmodifiableSet(errorHandlers);
    }
}

