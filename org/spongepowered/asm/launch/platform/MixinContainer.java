/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch.platform;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.platform.IMixinPlatformAgent;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.service.MixinService;

public class MixinContainer {
    private static final List<String> agentClasses = new ArrayList<String>();
    private final Logger logger = LogManager.getLogger((String)"mixin");
    private final URI uri;
    private final List<IMixinPlatformAgent> agents = new ArrayList<IMixinPlatformAgent>();

    public MixinContainer(MixinPlatformManager mixinPlatformManager, URI uRI) {
        this.uri = uRI;
        for (String string : agentClasses) {
            try {
                Class<?> class_ = Class.forName(string);
                Constructor<?> constructor = class_.getDeclaredConstructor(MixinPlatformManager.class, URI.class);
                this.logger.debug("Instancing new {} for {}", new Object[]{class_.getSimpleName(), this.uri});
                IMixinPlatformAgent iMixinPlatformAgent = (IMixinPlatformAgent)constructor.newInstance(mixinPlatformManager, uRI);
                this.agents.add(iMixinPlatformAgent);
            }
            catch (Exception exception) {
                this.logger.catching((Throwable)exception);
            }
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public Collection<String> getPhaseProviders() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (IMixinPlatformAgent iMixinPlatformAgent : this.agents) {
            String string = iMixinPlatformAgent.getPhaseProvider();
            if (string == null) continue;
            arrayList.add(string);
        }
        return arrayList;
    }

    public void prepare() {
        for (IMixinPlatformAgent iMixinPlatformAgent : this.agents) {
            this.logger.debug("Processing prepare() for {}", new Object[]{iMixinPlatformAgent});
            iMixinPlatformAgent.prepare();
        }
    }

    public void initPrimaryContainer() {
        for (IMixinPlatformAgent iMixinPlatformAgent : this.agents) {
            this.logger.debug("Processing launch tasks for {}", new Object[]{iMixinPlatformAgent});
            iMixinPlatformAgent.initPrimaryContainer();
        }
    }

    public void inject() {
        for (IMixinPlatformAgent iMixinPlatformAgent : this.agents) {
            this.logger.debug("Processing inject() for {}", new Object[]{iMixinPlatformAgent});
            iMixinPlatformAgent.inject();
        }
    }

    public String getLaunchTarget() {
        for (IMixinPlatformAgent iMixinPlatformAgent : this.agents) {
            String string = iMixinPlatformAgent.getLaunchTarget();
            if (string == null) continue;
            return string;
        }
        return null;
    }

    static {
        GlobalProperties.put("mixin.agents", agentClasses);
        for (String string : MixinService.getService().getPlatformAgents()) {
            agentClasses.add(string);
        }
        agentClasses.add("org.spongepowered.asm.launch.platform.MixinPlatformAgentDefault");
    }
}

