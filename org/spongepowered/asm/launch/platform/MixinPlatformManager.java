/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch.platform;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.platform.MainAttributes;
import org.spongepowered.asm.launch.platform.MixinContainer;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.service.MixinService;

public class MixinPlatformManager {
    private static final String DEFAULT_MAIN_CLASS = "net.minecraft.client.main.Main";
    private static final String MIXIN_TWEAKER_CLASS = "org.spongepowered.asm.launch.MixinTweaker";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final Map<URI, MixinContainer> containers = new LinkedHashMap<URI, MixinContainer>();
    private MixinContainer primaryContainer;
    private boolean prepared = false;
    private boolean injected;

    public void init() {
        logger.debug("Initialising Mixin Platform Manager");
        URI uRI = null;
        try {
            uRI = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
            if (uRI != null) {
                logger.debug("Mixin platform: primary container is {}", new Object[]{uRI});
                this.primaryContainer = this.addContainer(uRI);
            }
        }
        catch (URISyntaxException uRISyntaxException) {
            uRISyntaxException.printStackTrace();
        }
        this.scanClasspath();
    }

    public Collection<String> getPhaseProviderClasses() {
        Collection<String> collection = this.primaryContainer.getPhaseProviders();
        if (collection != null) {
            return Collections.unmodifiableCollection(collection);
        }
        return Collections.emptyList();
    }

    public final MixinContainer addContainer(URI uRI) {
        MixinContainer mixinContainer = this.containers.get(uRI);
        if (mixinContainer != null) {
            return mixinContainer;
        }
        logger.debug("Adding mixin platform agents for container {}", new Object[]{uRI});
        MixinContainer mixinContainer2 = new MixinContainer(this, uRI);
        this.containers.put(uRI, mixinContainer2);
        if (this.prepared) {
            mixinContainer2.prepare();
        }
        return mixinContainer2;
    }

    public final void prepare(List<String> list) {
        this.prepared = true;
        for (MixinContainer mixinContainer : this.containers.values()) {
            mixinContainer.prepare();
        }
        if (list != null) {
            this.parseArgs(list);
        } else {
            String string = System.getProperty("sun.java.command");
            if (string != null) {
                this.parseArgs(Arrays.asList(string.split(" ")));
            }
        }
    }

    private void parseArgs(List<String> list) {
        boolean bl = false;
        for (String string : list) {
            if (bl) {
                this.addConfig(string);
            }
            bl = "--mixin".equals(string);
        }
    }

    public final void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        if (this.primaryContainer != null) {
            this.primaryContainer.initPrimaryContainer();
        }
        this.scanClasspath();
        logger.debug("inject() running with {} agents", new Object[]{this.containers.size()});
        for (MixinContainer mixinContainer : this.containers.values()) {
            try {
                mixinContainer.inject();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void scanClasspath() {
        URL[] arruRL;
        for (URL uRL : arruRL = MixinService.getService().getClassProvider().getClassPath()) {
            try {
                MainAttributes mainAttributes;
                String string;
                URI uRI = uRL.toURI();
                if (this.containers.containsKey(uRI)) continue;
                logger.debug("Scanning {} for mixin tweaker", new Object[]{uRI});
                if (!"file".equals(uRI.getScheme()) || !new File(uRI).exists() || !MIXIN_TWEAKER_CLASS.equals(string = (mainAttributes = MainAttributes.of(uRI)).get("TweakClass"))) continue;
                logger.debug("{} contains a mixin tweaker, adding agents", new Object[]{uRI});
                this.addContainer(uRI);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public String getLaunchTarget() {
        for (MixinContainer mixinContainer : this.containers.values()) {
            String string = mixinContainer.getLaunchTarget();
            if (string == null) continue;
            return string;
        }
        return DEFAULT_MAIN_CLASS;
    }

    final void setCompatibilityLevel(String string) {
        try {
            MixinEnvironment.CompatibilityLevel compatibilityLevel = MixinEnvironment.CompatibilityLevel.valueOf(string.toUpperCase());
            logger.debug("Setting mixin compatibility level: {}", new Object[]{compatibilityLevel});
            MixinEnvironment.setCompatibilityLevel(compatibilityLevel);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            logger.warn("Invalid compatibility level specified: {}", new Object[]{string});
        }
    }

    final void addConfig(String string) {
        if (string.endsWith(".json")) {
            logger.debug("Registering mixin config: {}", new Object[]{string});
            Mixins.addConfiguration(string);
        } else if (string.contains(".json@")) {
            int n2 = string.indexOf(".json@");
            String string2 = string.substring(n2 + 6);
            string = string.substring(0, n2 + 5);
            MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(string2);
            if (phase != null) {
                logger.warn("Setting config phase via manifest is deprecated: {}. Specify target in config instead", new Object[]{string});
                logger.debug("Registering mixin config: {}", new Object[]{string});
                MixinEnvironment.getEnvironment(phase).addConfiguration(string);
            }
        }
    }

    final void addTokenProvider(String string) {
        if (string.contains("@")) {
            String[] arrstring = string.split("@", 2);
            MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(arrstring[1]);
            if (phase != null) {
                logger.debug("Registering token provider class: {}", new Object[]{arrstring[0]});
                MixinEnvironment.getEnvironment(phase).registerTokenProviderClass(arrstring[0]);
            }
            return;
        }
        MixinEnvironment.getDefaultEnvironment().registerTokenProviderClass(string);
    }
}

