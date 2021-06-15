/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.tools.agent;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;
import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.tools.agent.MixinAgentClassLoader;

public class MixinAgent
implements IHotSwap {
    public static final byte[] ERROR_BYTECODE = new byte[]{1};
    static final MixinAgentClassLoader classLoader = new MixinAgentClassLoader();
    static final Logger logger = LogManager.getLogger((String)"mixin.agent");
    static Instrumentation instrumentation = null;
    private static List<MixinAgent> agents = new ArrayList<MixinAgent>();
    final MixinTransformer classTransformer;

    public MixinAgent(MixinTransformer mixinTransformer) {
        this.classTransformer = mixinTransformer;
        agents.add(this);
        if (instrumentation != null) {
            this.initTransformer();
        }
    }

    private void initTransformer() {
        instrumentation.addTransformer(new Transformer(), true);
    }

    @Override
    public void registerMixinClass(String string) {
        classLoader.addMixinClass(string);
    }

    @Override
    public void registerTargetClass(String string, byte[] arrby) {
        classLoader.addTargetClass(string, arrby);
    }

    public static void init(Instrumentation instrumentation) {
        MixinAgent.instrumentation = instrumentation;
        if (!MixinAgent.instrumentation.isRedefineClassesSupported()) {
            logger.error("The instrumentation doesn't support re-definition of classes");
        }
        for (MixinAgent mixinAgent : agents) {
            mixinAgent.initTransformer();
        }
    }

    public static void premain(String string, Instrumentation instrumentation) {
        System.setProperty("mixin.hotSwap", "true");
        MixinAgent.init(instrumentation);
    }

    public static void agentmain(String string, Instrumentation instrumentation) {
        MixinAgent.init(instrumentation);
    }

    class Transformer
    implements ClassFileTransformer {
        Transformer() {
        }

        @Override
        public byte[] transform(ClassLoader classLoader, String string, Class<?> class_, ProtectionDomain protectionDomain, byte[] arrby) throws IllegalClassFormatException {
            if (class_ == null) {
                return null;
            }
            byte[] arrby2 = MixinAgent.classLoader.getFakeMixinBytecode(class_);
            if (arrby2 != null) {
                List<String> list = this.reloadMixin(string, arrby);
                if (list == null || !this.reApplyMixins(list)) {
                    return ERROR_BYTECODE;
                }
                return arrby2;
            }
            try {
                logger.info("Redefining class " + string);
                return MixinAgent.this.classTransformer.transformClassBytes(null, string, arrby);
            }
            catch (Throwable throwable) {
                logger.error("Error while re-transforming class " + string, throwable);
                return ERROR_BYTECODE;
            }
        }

        private List<String> reloadMixin(String string, byte[] arrby) {
            logger.info("Redefining mixin {}", new Object[]{string});
            try {
                return MixinAgent.this.classTransformer.reload(string.replace('/', '.'), arrby);
            }
            catch (MixinReloadException mixinReloadException) {
                logger.error("Mixin {} cannot be reloaded, needs a restart to be applied: {} ", new Object[]{mixinReloadException.getMixinInfo(), mixinReloadException.getMessage()});
            }
            catch (Throwable throwable) {
                logger.error("Error while finding targets for mixin " + string, throwable);
            }
            return null;
        }

        private boolean reApplyMixins(List<String> list) {
            IMixinService iMixinService = MixinService.getService();
            for (String string : list) {
                String string2 = string.replace('/', '.');
                logger.debug("Re-transforming target class {}", new Object[]{string});
                try {
                    Class<?> class_ = iMixinService.getClassProvider().findClass(string2);
                    byte[] arrby = classLoader.getOriginalTargetBytecode(string2);
                    if (arrby == null) {
                        logger.error("Target class {} bytecode is not registered", new Object[]{string2});
                        return false;
                    }
                    arrby = MixinAgent.this.classTransformer.transformClassBytes(null, string2, arrby);
                    instrumentation.redefineClasses(new ClassDefinition(class_, arrby));
                }
                catch (Throwable throwable) {
                    logger.error("Error while re-transforming target class " + string, throwable);
                    return false;
                }
            }
            return true;
        }
    }
}

