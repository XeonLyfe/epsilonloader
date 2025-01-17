/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.MixinInitialisationError;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.service.MixinService;

public abstract class MixinBootstrap {
    public static final String VERSION = "0.7.11";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static boolean initialised = false;
    private static boolean initState = true;
    private static MixinPlatformManager platform;

    private MixinBootstrap() {
    }

    @Deprecated
    public static void addProxy() {
        MixinService.getService().beginPhase();
    }

    public static MixinPlatformManager getPlatform() {
        if (platform == null) {
            Object t2 = GlobalProperties.get("mixin.platform");
            if (t2 instanceof MixinPlatformManager) {
                platform = (MixinPlatformManager)t2;
            } else {
                platform = new MixinPlatformManager();
                GlobalProperties.put("mixin.platform", platform);
                platform.init();
            }
        }
        return platform;
    }

    public static void init() {
        if (!MixinBootstrap.start()) {
            return;
        }
        MixinBootstrap.doInit(null);
    }

    static boolean start() {
        if (MixinBootstrap.isSubsystemRegistered()) {
            if (!MixinBootstrap.checkSubsystemVersion()) {
                throw new MixinInitialisationError("Mixin subsystem version " + MixinBootstrap.getActiveSubsystemVersion() + " was already initialised. Cannot bootstrap version " + VERSION);
            }
            return false;
        }
        MixinBootstrap.registerSubsystem(VERSION);
        if (!initialised) {
            MixinEnvironment.Phase phase;
            initialised = true;
            String string = System.getProperty("sun.java.command");
            if (string != null && string.contains("GradleStart")) {
                System.setProperty("mixin.env.remapRefMap", "true");
            }
            if ((phase = MixinService.getService().getInitialPhase()) == MixinEnvironment.Phase.DEFAULT) {
                logger.error("Initialising mixin subsystem after game pre-init phase! Some mixins may be skipped.");
                MixinEnvironment.init(phase);
                MixinBootstrap.getPlatform().prepare(null);
                initState = false;
            } else {
                MixinEnvironment.init(phase);
            }
            MixinService.getService().beginPhase();
        }
        MixinBootstrap.getPlatform();
        return true;
    }

    static void doInit(List<String> list) {
        if (!initialised) {
            if (MixinBootstrap.isSubsystemRegistered()) {
                logger.warn("Multiple Mixin containers present, init suppressed for 0.7.11");
                return;
            }
            throw new IllegalStateException("MixinBootstrap.doInit() called before MixinBootstrap.start()");
        }
        MixinBootstrap.getPlatform().getPhaseProviderClasses();
        if (initState) {
            MixinBootstrap.getPlatform().prepare(list);
            MixinService.getService().init();
        }
    }

    static void inject() {
        MixinBootstrap.getPlatform().inject();
    }

    private static boolean isSubsystemRegistered() {
        return GlobalProperties.get("mixin.initialised") != null;
    }

    private static boolean checkSubsystemVersion() {
        return VERSION.equals(MixinBootstrap.getActiveSubsystemVersion());
    }

    private static Object getActiveSubsystemVersion() {
        Object t2 = GlobalProperties.get("mixin.initialised");
        return t2 != null ? t2 : "";
    }

    private static void registerSubsystem(String string) {
        GlobalProperties.put("mixin.initialised", string);
    }

    static {
        MixinService.boot();
        MixinService.getService().prepare();
    }
}

