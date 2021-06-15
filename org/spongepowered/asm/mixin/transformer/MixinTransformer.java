/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.throwables.ClassAlreadyLoadedException;
import org.spongepowered.asm.mixin.throwables.MixinApplyError;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.throwables.MixinPrepareError;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.mixin.transformer.InnerClassGenerator;
import org.spongepowered.asm.mixin.transformer.MixinConfig;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinPostProcessor;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionCheckClass;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionCheckInterfaces;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionClassExporter;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.ITransformer;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.transformers.TreeTransformer;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.ReEntranceLock;
import org.spongepowered.asm.util.perf.Profiler;

public class MixinTransformer
extends TreeTransformer {
    private static final String MIXIN_AGENT_CLASS = "org.spongepowered.tools.agent.MixinAgent";
    private static final String METRONOME_AGENT_CLASS = "org.spongepowered.metronome.Agent";
    static final Logger logger = LogManager.getLogger((String)"mixin");
    private final IMixinService service = MixinService.getService();
    private final List<MixinConfig> configs = new ArrayList<MixinConfig>();
    private final List<MixinConfig> pendingConfigs = new ArrayList<MixinConfig>();
    private final ReEntranceLock lock;
    private final String sessionId = UUID.randomUUID().toString();
    private final Extensions extensions;
    private final IHotSwap hotSwapper;
    private final MixinPostProcessor postProcessor;
    private final Profiler profiler;
    private MixinEnvironment currentEnvironment;
    private Level verboseLoggingLevel = Level.DEBUG;
    private boolean errorState = false;
    private int transformedCount = 0;

    MixinTransformer() {
        MixinEnvironment mixinEnvironment = MixinEnvironment.getCurrentEnvironment();
        Object object = mixinEnvironment.getActiveTransformer();
        if (object instanceof ITransformer) {
            throw new MixinException("Terminating MixinTransformer instance " + this);
        }
        mixinEnvironment.setActiveTransformer(this);
        this.lock = this.service.getReEntranceLock();
        this.extensions = new Extensions(this);
        this.hotSwapper = this.initHotSwapper(mixinEnvironment);
        this.postProcessor = new MixinPostProcessor();
        this.extensions.add(new ArgsClassGenerator());
        this.extensions.add(new InnerClassGenerator());
        this.extensions.add(new ExtensionClassExporter(mixinEnvironment));
        this.extensions.add(new ExtensionCheckClass());
        this.extensions.add(new ExtensionCheckInterfaces());
        this.profiler = MixinEnvironment.getProfiler();
    }

    private IHotSwap initHotSwapper(MixinEnvironment mixinEnvironment) {
        if (!mixinEnvironment.getOption(MixinEnvironment.Option.HOT_SWAP)) {
            return null;
        }
        try {
            logger.info("Attempting to load Hot-Swap agent");
            Class<?> class_ = Class.forName(MIXIN_AGENT_CLASS);
            Constructor<?> constructor = class_.getDeclaredConstructor(MixinTransformer.class);
            return (IHotSwap)constructor.newInstance(this);
        }
        catch (Throwable throwable) {
            logger.info("Hot-swap agent could not be loaded, hot swapping of mixins won't work. {}: {}", new Object[]{throwable.getClass().getSimpleName(), throwable.getMessage()});
            return null;
        }
    }

    public void audit(MixinEnvironment mixinEnvironment) {
        HashSet<String> hashSet = new HashSet<String>();
        for (MixinConfig iterator2 : this.configs) {
            hashSet.addAll(iterator2.getUnhandledTargets());
        }
        Logger logger = LogManager.getLogger((String)"mixin/audit");
        for (String string : hashSet) {
            try {
                logger.info("Force-loading class {}", new Object[]{string});
                this.service.getClassProvider().findClass(string, true);
            }
            catch (ClassNotFoundException classNotFoundException) {
                logger.error("Could not force-load " + string, (Throwable)classNotFoundException);
            }
        }
        for (MixinConfig mixinConfig : this.configs) {
            for (String string : mixinConfig.getUnhandledTargets()) {
                ClassAlreadyLoadedException classAlreadyLoadedException = new ClassAlreadyLoadedException(string + " was already classloaded");
                logger.error("Could not force-load " + string, (Throwable)classAlreadyLoadedException);
            }
        }
        if (mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_PROFILER)) {
            this.printProfilerSummary();
        }
    }

    private void printProfilerSummary() {
        DecimalFormat decimalFormat = new DecimalFormat("(###0.000");
        DecimalFormat decimalFormat2 = new DecimalFormat("(###0.0");
        PrettyPrinter prettyPrinter = this.profiler.printer(false, false);
        long l2 = this.profiler.get("mixin.prepare").getTotalTime();
        long l3 = this.profiler.get("mixin.read").getTotalTime();
        long l4 = this.profiler.get("mixin.apply").getTotalTime();
        long l5 = this.profiler.get("mixin.write").getTotalTime();
        long l6 = this.profiler.get("mixin").getTotalTime();
        long l7 = this.profiler.get("class.load").getTotalTime();
        long l8 = this.profiler.get("class.transform").getTotalTime();
        long l9 = this.profiler.get("mixin.debug.export").getTotalTime();
        long l10 = l6 - l7 - l8 - l9;
        double d2 = (double)l10 / (double)l6 * 100.0;
        double d3 = (double)l7 / (double)l6 * 100.0;
        double d4 = (double)l8 / (double)l6 * 100.0;
        double d5 = (double)l9 / (double)l6 * 100.0;
        long l11 = 0L;
        Profiler.Section section = null;
        for (Profiler.Section object : this.profiler.getSections()) {
            long method = object.getName().startsWith("class.transform.") ? object.getTotalTime() : 0L;
            if (method <= l11) continue;
            l11 = method;
            section = object;
        }
        prettyPrinter.hr().add("Summary").hr().add();
        String string = "%9d ms %12s seconds)";
        prettyPrinter.kv("Total mixin time", string, l6, decimalFormat.format((double)l6 * 0.001)).add();
        prettyPrinter.kv("Preparing mixins", string, l2, decimalFormat.format((double)l2 * 0.001));
        prettyPrinter.kv("Reading input", string, l3, decimalFormat.format((double)l3 * 0.001));
        prettyPrinter.kv("Applying mixins", string, l4, decimalFormat.format((double)l4 * 0.001));
        prettyPrinter.kv("Writing output", string, l5, decimalFormat.format((double)l5 * 0.001)).add();
        prettyPrinter.kv("of which", "");
        prettyPrinter.kv("Time spent loading from disk", string, l7, decimalFormat.format((double)l7 * 0.001));
        prettyPrinter.kv("Time spent transforming classes", string, l8, decimalFormat.format((double)l8 * 0.001)).add();
        if (section != null) {
            prettyPrinter.kv("Worst transformer", section.getName());
            prettyPrinter.kv("Class", section.getInfo());
            prettyPrinter.kv("Time spent", "%s seconds", section.getTotalSeconds());
            prettyPrinter.kv("called", "%d times", section.getTotalCount()).add();
        }
        prettyPrinter.kv("   Time allocation:     Processing mixins", "%9d ms %10s%% of total)", l10, decimalFormat2.format(d2));
        prettyPrinter.kv("Loading classes", "%9d ms %10s%% of total)", l7, decimalFormat2.format(d3));
        prettyPrinter.kv("Running transformers", "%9d ms %10s%% of total)", l8, decimalFormat2.format(d4));
        if (l9 > 0L) {
            prettyPrinter.kv("Exporting classes (debug)", "%9d ms %10s%% of total)", l9, decimalFormat2.format(d5));
        }
        prettyPrinter.add();
        try {
            Class<?> throwable = this.service.getClassProvider().findAgentClass(METRONOME_AGENT_CLASS, false);
            Method method = throwable.getDeclaredMethod("getTimes", new Class[0]);
            Map map = (Map)method.invoke(null, new Object[0]);
            prettyPrinter.hr().add("Transformer Times").hr().add();
            int n2 = 10;
            for (Map.Entry entry : map.entrySet()) {
                n2 = Math.max(n2, ((String)entry.getKey()).length());
            }
            for (Map.Entry entry : map.entrySet()) {
                String string2 = (String)entry.getKey();
                long l12 = 0L;
                for (Profiler.Section section2 : this.profiler.getSections()) {
                    if (!string2.equals(section2.getInfo())) continue;
                    l12 = section2.getTotalTime();
                    break;
                }
                if (l12 > 0L) {
                    prettyPrinter.add("%-" + n2 + "s %8s ms %8s ms in mixin)", string2, (Long)entry.getValue() + l12, "(" + l12);
                    continue;
                }
                prettyPrinter.add("%-" + n2 + "s %8s ms", string2, entry.getValue());
            }
            prettyPrinter.add();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        prettyPrinter.print();
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
    public synchronized byte[] transformClassBytes(String string, String string2, byte[] arrby) {
        if (string2 == null || this.errorState) {
            return arrby;
        }
        MixinEnvironment mixinEnvironment = MixinEnvironment.getCurrentEnvironment();
        if (arrby == null) {
            for (IClassGenerator iClassGenerator : this.extensions.getGenerators()) {
                Profiler.Section section = this.profiler.begin("generator", iClassGenerator.getClass().getSimpleName().toLowerCase());
                arrby = iClassGenerator.generate(string2);
                section.end();
                if (arrby == null) continue;
                this.extensions.export(mixinEnvironment, string2.replace('.', '/'), false, arrby);
                return arrby;
            }
            return arrby;
        }
        boolean bl = this.lock.push().check();
        Profiler.Section section = this.profiler.begin("mixin");
        if (!bl) {
            try {
                this.checkSelect(mixinEnvironment);
            }
            catch (Exception exception) {
                this.lock.pop();
                section.end();
                throw new MixinException(exception);
            }
        }
        try {
            Object object;
            if (this.postProcessor.canTransform(string2)) {
                Profiler.Section section2 = this.profiler.begin("postprocessor");
                byte[] arrby2 = this.postProcessor.transformClassBytes(string, string2, arrby);
                section2.end();
                this.extensions.export(mixinEnvironment, string2, false, arrby2);
                byte[] arrby3 = arrby2;
                return arrby3;
            }
            TreeSet<MixinInfo> treeSet = null;
            boolean bl2 = false;
            for (MixinConfig object2 : this.configs) {
                if (object2.packageMatch(string2)) {
                    bl2 = true;
                    continue;
                }
                if (!object2.hasMixinsFor(string2)) continue;
                if (treeSet == null) {
                    treeSet = new TreeSet<MixinInfo>();
                }
                treeSet.addAll(object2.getMixinsFor(string2));
            }
            if (bl2) {
                throw new NoClassDefFoundError(String.format("%s is a mixin class and cannot be referenced directly", string2));
            }
            if (treeSet != null) {
                if (bl) {
                    logger.warn("Re-entrance detected, this will cause serious problems.", (Throwable)new MixinException());
                    throw new MixinApplyError("Re-entrance error.");
                }
                if (this.hotSwapper != null) {
                    this.hotSwapper.registerTargetClass(string2, arrby);
                }
                try {
                    object = this.profiler.begin("read");
                    ClassNode classNode = this.readClass(arrby, true);
                    TargetClassContext targetClassContext = new TargetClassContext(mixinEnvironment, this.extensions, this.sessionId, string2, classNode, treeSet);
                    ((Profiler.Section)object).end();
                    arrby = this.applyMixins(mixinEnvironment, targetClassContext);
                    ++this.transformedCount;
                }
                catch (InvalidMixinException invalidMixinException) {
                    this.dumpClassOnFailure(string2, arrby, mixinEnvironment);
                    this.handleMixinApplyError(string2, invalidMixinException, mixinEnvironment);
                }
            }
            object = arrby;
            return object;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            this.dumpClassOnFailure(string2, arrby, mixinEnvironment);
            throw new MixinTransformerError("An unexpected critical error was encountered", throwable);
        }
        finally {
            this.lock.pop();
            section.end();
        }
    }

    public List<String> reload(String string, byte[] arrby) {
        if (this.lock.getDepth() > 0) {
            throw new MixinApplyError("Cannot reload mixin if re-entrant lock entered");
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        for (MixinConfig mixinConfig : this.configs) {
            arrayList.addAll(mixinConfig.reloadMixin(string, arrby));
        }
        return arrayList;
    }

    private void checkSelect(MixinEnvironment mixinEnvironment) {
        if (this.currentEnvironment != mixinEnvironment) {
            this.select(mixinEnvironment);
            return;
        }
        int n2 = Mixins.getUnvisitedCount();
        if (n2 > 0 && this.transformedCount == 0) {
            this.select(mixinEnvironment);
        }
    }

    private void select(MixinEnvironment mixinEnvironment) {
        Level level = this.verboseLoggingLevel = mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE) ? Level.INFO : Level.DEBUG;
        if (this.transformedCount > 0) {
            logger.log(this.verboseLoggingLevel, "Ending {}, applied {} mixins", new Object[]{this.currentEnvironment, this.transformedCount});
        }
        String string = this.currentEnvironment == mixinEnvironment ? "Checking for additional" : "Preparing";
        logger.log(this.verboseLoggingLevel, "{} mixins for {}", new Object[]{string, mixinEnvironment});
        this.profiler.setActive(true);
        this.profiler.mark(mixinEnvironment.getPhase().toString() + ":prepare");
        Profiler.Section section = this.profiler.begin("prepare");
        this.selectConfigs(mixinEnvironment);
        this.extensions.select(mixinEnvironment);
        int n2 = this.prepareConfigs(mixinEnvironment);
        this.currentEnvironment = mixinEnvironment;
        this.transformedCount = 0;
        section.end();
        long l2 = section.getTime();
        double d2 = section.getSeconds();
        if (d2 > 0.25) {
            long l3 = this.profiler.get("class.load").getTime();
            long l4 = this.profiler.get("class.transform").getTime();
            long l5 = this.profiler.get("mixin.plugin").getTime();
            String string2 = new DecimalFormat("###0.000").format(d2);
            String string3 = new DecimalFormat("###0.0").format((double)l2 / (double)n2);
            logger.log(this.verboseLoggingLevel, "Prepared {} mixins in {} sec ({}ms avg) ({}ms load, {}ms transform, {}ms plugin)", new Object[]{n2, string2, string3, l3, l4, l5});
        }
        this.profiler.mark(mixinEnvironment.getPhase().toString() + ":apply");
        this.profiler.setActive(mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_PROFILER));
    }

    private void selectConfigs(MixinEnvironment mixinEnvironment) {
        Iterator<Config> iterator2 = Mixins.getConfigs().iterator();
        while (iterator2.hasNext()) {
            Config config = iterator2.next();
            try {
                MixinConfig mixinConfig = config.get();
                if (!mixinConfig.select(mixinEnvironment)) continue;
                iterator2.remove();
                logger.log(this.verboseLoggingLevel, "Selecting config {}", new Object[]{mixinConfig});
                mixinConfig.onSelect();
                this.pendingConfigs.add(mixinConfig);
            }
            catch (Exception exception) {
                logger.warn(String.format("Failed to select mixin config: %s", config), (Throwable)exception);
            }
        }
        Collections.sort(this.pendingConfigs);
    }

    private int prepareConfigs(MixinEnvironment mixinEnvironment) {
        Object object;
        int n2 = 0;
        final IHotSwap iHotSwap = this.hotSwapper;
        for (MixinConfig mixinConfig : this.pendingConfigs) {
            mixinConfig.addListener(this.postProcessor);
            if (iHotSwap == null) continue;
            mixinConfig.addListener(new MixinConfig.IListener(){

                @Override
                public void onPrepare(MixinInfo mixinInfo) {
                    iHotSwap.registerMixinClass(mixinInfo.getClassName());
                }

                @Override
                public void onInit(MixinInfo mixinInfo) {
                }
            });
        }
        for (MixinConfig mixinConfig : this.pendingConfigs) {
            try {
                logger.log(this.verboseLoggingLevel, "Preparing {} ({})", new Object[]{mixinConfig, mixinConfig.getDeclaredMixinCount()});
                mixinConfig.prepare();
                n2 += mixinConfig.getMixinCount();
            }
            catch (InvalidMixinException invalidMixinException) {
                this.handleMixinPrepareError(mixinConfig, invalidMixinException, mixinEnvironment);
            }
            catch (Exception exception) {
                object = exception.getMessage();
                logger.error("Error encountered whilst initialising mixin config '" + mixinConfig.getName() + "': " + (String)object, (Throwable)exception);
            }
        }
        for (MixinConfig mixinConfig : this.pendingConfigs) {
            IMixinConfigPlugin iMixinConfigPlugin = mixinConfig.getPlugin();
            if (iMixinConfigPlugin == null) continue;
            object = new HashSet();
            for (MixinConfig mixinConfig2 : this.pendingConfigs) {
                if (mixinConfig2.equals(mixinConfig)) continue;
                object.addAll(mixinConfig2.getTargets());
            }
            iMixinConfigPlugin.acceptTargets(mixinConfig.getTargets(), Collections.unmodifiableSet(object));
        }
        for (MixinConfig mixinConfig : this.pendingConfigs) {
            try {
                mixinConfig.postInitialise();
            }
            catch (InvalidMixinException invalidMixinException) {
                this.handleMixinPrepareError(mixinConfig, invalidMixinException, mixinEnvironment);
            }
            catch (Exception exception) {
                object = exception.getMessage();
                logger.error("Error encountered during mixin config postInit step'" + mixinConfig.getName() + "': " + (String)object, (Throwable)exception);
            }
        }
        this.configs.addAll(this.pendingConfigs);
        Collections.sort(this.configs);
        this.pendingConfigs.clear();
        return n2;
    }

    private byte[] applyMixins(MixinEnvironment mixinEnvironment, TargetClassContext targetClassContext) {
        Profiler.Section section;
        block2: {
            section = this.profiler.begin("preapply");
            this.extensions.preApply(targetClassContext);
            section = section.next("apply");
            this.apply(targetClassContext);
            section = section.next("postapply");
            try {
                this.extensions.postApply(targetClassContext);
            }
            catch (ExtensionCheckClass.ValidationFailedException validationFailedException) {
                logger.info(validationFailedException.getMessage());
                if (!targetClassContext.isExportForced() && !mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_EXPORT)) break block2;
                this.writeClass(targetClassContext);
            }
        }
        section.end();
        return this.writeClass(targetClassContext);
    }

    private void apply(TargetClassContext targetClassContext) {
        targetClassContext.applyMixins();
    }

    private void handleMixinPrepareError(MixinConfig mixinConfig, InvalidMixinException invalidMixinException, MixinEnvironment mixinEnvironment) throws MixinPrepareError {
        this.handleMixinError(mixinConfig.getName(), invalidMixinException, mixinEnvironment, ErrorPhase.PREPARE);
    }

    private void handleMixinApplyError(String string, InvalidMixinException invalidMixinException, MixinEnvironment mixinEnvironment) throws MixinApplyError {
        this.handleMixinError(string, invalidMixinException, mixinEnvironment, ErrorPhase.APPLY);
    }

    private void handleMixinError(String string, InvalidMixinException invalidMixinException, MixinEnvironment mixinEnvironment, ErrorPhase errorPhase) throws Error {
        IMixinErrorHandler.ErrorAction errorAction;
        this.errorState = true;
        IMixinInfo iMixinInfo = invalidMixinException.getMixin();
        if (iMixinInfo == null) {
            logger.error("InvalidMixinException has no mixin!", (Throwable)invalidMixinException);
            throw invalidMixinException;
        }
        IMixinConfig iMixinConfig = iMixinInfo.getConfig();
        MixinEnvironment.Phase phase = iMixinInfo.getPhase();
        IMixinErrorHandler.ErrorAction errorAction2 = errorAction = iMixinConfig.isRequired() ? IMixinErrorHandler.ErrorAction.ERROR : IMixinErrorHandler.ErrorAction.WARN;
        if (mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            new PrettyPrinter().add("Invalid Mixin").centre().hr('-').kvWidth(10).kv("Action", errorPhase.name()).kv("Mixin", iMixinInfo.getClassName()).kv("Config", iMixinConfig.getName()).kv("Phase", phase).hr('-').add("    %s", invalidMixinException.getClass().getName()).hr('-').addWrapped("    %s", invalidMixinException.getMessage()).hr('-').add(invalidMixinException, 8).trace(errorAction.logLevel);
        }
        for (IMixinErrorHandler iMixinErrorHandler : this.getErrorHandlers(iMixinInfo.getPhase())) {
            IMixinErrorHandler.ErrorAction errorAction3 = errorPhase.onError(iMixinErrorHandler, string, invalidMixinException, iMixinInfo, errorAction);
            if (errorAction3 == null) continue;
            errorAction = errorAction3;
        }
        logger.log(errorAction.logLevel, errorPhase.getLogMessage(string, invalidMixinException, iMixinInfo), (Throwable)invalidMixinException);
        this.errorState = false;
        if (errorAction == IMixinErrorHandler.ErrorAction.ERROR) {
            throw new MixinApplyError(errorPhase.getErrorMessage(iMixinInfo, iMixinConfig, phase), invalidMixinException);
        }
    }

    private List<IMixinErrorHandler> getErrorHandlers(MixinEnvironment.Phase phase) {
        ArrayList<IMixinErrorHandler> arrayList = new ArrayList<IMixinErrorHandler>();
        for (String string : Mixins.getErrorHandlerClasses()) {
            try {
                logger.info("Instancing error handler class {}", new Object[]{string});
                Class<?> class_ = this.service.getClassProvider().findClass(string, true);
                IMixinErrorHandler iMixinErrorHandler = (IMixinErrorHandler)class_.newInstance();
                if (iMixinErrorHandler == null) continue;
                arrayList.add(iMixinErrorHandler);
            }
            catch (Throwable throwable) {}
        }
        return arrayList;
    }

    private byte[] writeClass(TargetClassContext targetClassContext) {
        return this.writeClass(targetClassContext.getClassName(), targetClassContext.getClassNode(), targetClassContext.isExportForced());
    }

    private byte[] writeClass(String string, ClassNode classNode, boolean bl) {
        Profiler.Section section = this.profiler.begin("write");
        byte[] arrby = this.writeClass(classNode);
        section.end();
        this.extensions.export(this.currentEnvironment, string, bl, arrby);
        return arrby;
    }

    private void dumpClassOnFailure(String string, byte[] arrby, MixinEnvironment mixinEnvironment) {
        if (mixinEnvironment.getOption(MixinEnvironment.Option.DUMP_TARGET_ON_FAILURE)) {
            ExtensionClassExporter extensionClassExporter = (ExtensionClassExporter)this.extensions.getExtension(ExtensionClassExporter.class);
            extensionClassExporter.dumpClass(string.replace('.', '/') + ".target", arrby);
        }
    }

    static enum ErrorPhase {
        PREPARE{

            @Override
            IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler iMixinErrorHandler, String string, InvalidMixinException invalidMixinException, IMixinInfo iMixinInfo, IMixinErrorHandler.ErrorAction errorAction) {
                try {
                    return iMixinErrorHandler.onPrepareError(iMixinInfo.getConfig(), invalidMixinException, iMixinInfo, errorAction);
                }
                catch (AbstractMethodError abstractMethodError) {
                    return errorAction;
                }
            }

            @Override
            protected String getContext(IMixinInfo iMixinInfo, String string) {
                return String.format("preparing %s in %s", iMixinInfo.getName(), string);
            }
        }
        ,
        APPLY{

            @Override
            IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler iMixinErrorHandler, String string, InvalidMixinException invalidMixinException, IMixinInfo iMixinInfo, IMixinErrorHandler.ErrorAction errorAction) {
                try {
                    return iMixinErrorHandler.onApplyError(string, invalidMixinException, iMixinInfo, errorAction);
                }
                catch (AbstractMethodError abstractMethodError) {
                    return errorAction;
                }
            }

            @Override
            protected String getContext(IMixinInfo iMixinInfo, String string) {
                return String.format("%s -> %s", iMixinInfo, string);
            }
        };

        private final String text = this.name().toLowerCase();

        abstract IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler var1, String var2, InvalidMixinException var3, IMixinInfo var4, IMixinErrorHandler.ErrorAction var5);

        protected abstract String getContext(IMixinInfo var1, String var2);

        public String getLogMessage(String string, InvalidMixinException invalidMixinException, IMixinInfo iMixinInfo) {
            return String.format("Mixin %s failed %s: %s %s", this.text, this.getContext(iMixinInfo, string), invalidMixinException.getClass().getName(), invalidMixinException.getMessage());
        }

        public String getErrorMessage(IMixinInfo iMixinInfo, IMixinConfig iMixinConfig, MixinEnvironment.Phase phase) {
            return String.format("Mixin [%s] from phase [%s] in config [%s] FAILED during %s", iMixinInfo, phase, iMixinConfig, this.name());
        }
    }
}

