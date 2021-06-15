/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.core.Appender
 *  org.apache.logging.log4j.core.LogEvent
 *  org.apache.logging.log4j.core.Logger
 *  org.apache.logging.log4j.core.appender.AbstractAppender
 */
package org.spongepowered.asm.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.obfuscation.RemapperChain;
import org.spongepowered.asm.service.ILegacyClassTransformer;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.ITransformer;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.ITokenProvider;
import org.spongepowered.asm.util.JavaVersion;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.perf.Profiler;

public final class MixinEnvironment
implements ITokenProvider {
    private static final Set<String> excludeTransformers = Sets.newHashSet("net.minecraftforge.fml.common.asm.transformers.EventSubscriptionTransformer", "cpw.mods.fml.common.asm.transformers.EventSubscriptionTransformer", "net.minecraftforge.fml.common.asm.transformers.TerminalTransformer", "cpw.mods.fml.common.asm.transformers.TerminalTransformer");
    private static MixinEnvironment currentEnvironment;
    private static Phase currentPhase;
    private static CompatibilityLevel compatibility;
    private static boolean showHeader;
    private static final Logger logger;
    private static final Profiler profiler;
    private final IMixinService service;
    private final Phase phase;
    private final String configsKey;
    private final boolean[] options;
    private final Set<String> tokenProviderClasses = new HashSet<String>();
    private final List<TokenProviderWrapper> tokenProviders = new ArrayList<TokenProviderWrapper>();
    private final Map<String, Integer> internalTokens = new HashMap<String, Integer>();
    private final RemapperChain remappers = new RemapperChain();
    private Side side;
    private List<ILegacyClassTransformer> transformers;
    private String obfuscationContext = null;

    MixinEnvironment(Phase phase) {
        this.service = MixinService.getService();
        this.phase = phase;
        this.configsKey = "mixin.configs." + this.phase.name.toLowerCase();
        String string = this.getVersion();
        if (string == null || !"0.7.11".equals(string)) {
            throw new MixinException("Environment conflict, mismatched versions or you didn't call MixinBootstrap.init()");
        }
        this.service.checkEnv(this);
        this.options = new boolean[Option.values().length];
        for (Option option : Option.values()) {
            this.options[option.ordinal()] = option.getBooleanValue();
        }
        if (showHeader) {
            showHeader = false;
            this.printHeader(string);
        }
    }

    private void printHeader(Object object) {
        String string = this.getCodeSource();
        String string2 = this.service.getName();
        Side side = this.getSide();
        logger.info("SpongePowered MIXIN Subsystem Version={} Source={} Service={} Env={}", new Object[]{object, string, string2, side});
        boolean bl = this.getOption(Option.DEBUG_VERBOSE);
        if (bl || this.getOption(Option.DEBUG_EXPORT) || this.getOption(Option.DEBUG_PROFILER)) {
            PrettyPrinter prettyPrinter = new PrettyPrinter(32);
            prettyPrinter.add("SpongePowered MIXIN%s", bl ? " (Verbose debugging enabled)" : "").centre().hr();
            prettyPrinter.kv("Code source", string);
            prettyPrinter.kv("Internal Version", object);
            prettyPrinter.kv("Java 8 Supported", CompatibilityLevel.JAVA_8.isSupported()).hr();
            prettyPrinter.kv("Service Name", string2);
            prettyPrinter.kv("Service Class", this.service.getClass().getName()).hr();
            for (Option option : Option.values()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i2 = 0; i2 < option.depth; ++i2) {
                    stringBuilder.append("- ");
                }
                prettyPrinter.kv(option.property, "%s<%s>", new Object[]{stringBuilder, option});
            }
            prettyPrinter.hr().kv("Detected Side", (Object)side);
            prettyPrinter.print(System.err);
        }
    }

    private String getCodeSource() {
        try {
            return this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        }
        catch (Throwable throwable) {
            return "Unknown";
        }
    }

    public Phase getPhase() {
        return this.phase;
    }

    @Deprecated
    public List<String> getMixinConfigs() {
        ArrayList arrayList = (ArrayList)GlobalProperties.get(this.configsKey);
        if (arrayList == null) {
            arrayList = new ArrayList();
            GlobalProperties.put(this.configsKey, arrayList);
        }
        return arrayList;
    }

    @Deprecated
    public MixinEnvironment addConfiguration(String string) {
        logger.warn("MixinEnvironment::addConfiguration is deprecated and will be removed. Use Mixins::addConfiguration instead!");
        Mixins.addConfiguration(string, this);
        return this;
    }

    void registerConfig(String string) {
        List<String> list = this.getMixinConfigs();
        if (!list.contains(string)) {
            list.add(string);
        }
    }

    @Deprecated
    public MixinEnvironment registerErrorHandlerClass(String string) {
        Mixins.registerErrorHandlerClass(string);
        return this;
    }

    public MixinEnvironment registerTokenProviderClass(String string) {
        if (!this.tokenProviderClasses.contains(string)) {
            try {
                Class<?> class_ = this.service.getClassProvider().findClass(string, true);
                IEnvironmentTokenProvider iEnvironmentTokenProvider = (IEnvironmentTokenProvider)class_.newInstance();
                this.registerTokenProvider(iEnvironmentTokenProvider);
            }
            catch (Throwable throwable) {
                logger.error("Error instantiating " + string, throwable);
            }
        }
        return this;
    }

    public MixinEnvironment registerTokenProvider(IEnvironmentTokenProvider iEnvironmentTokenProvider) {
        if (iEnvironmentTokenProvider != null && !this.tokenProviderClasses.contains(iEnvironmentTokenProvider.getClass().getName())) {
            String string = iEnvironmentTokenProvider.getClass().getName();
            TokenProviderWrapper tokenProviderWrapper = new TokenProviderWrapper(iEnvironmentTokenProvider, this);
            logger.info("Adding new token provider {} to {}", new Object[]{string, this});
            this.tokenProviders.add(tokenProviderWrapper);
            this.tokenProviderClasses.add(string);
            Collections.sort(this.tokenProviders);
        }
        return this;
    }

    @Override
    public Integer getToken(String string) {
        string = string.toUpperCase();
        for (TokenProviderWrapper tokenProviderWrapper : this.tokenProviders) {
            Integer n2 = tokenProviderWrapper.getToken(string);
            if (n2 == null) continue;
            return n2;
        }
        return this.internalTokens.get(string);
    }

    @Deprecated
    public Set<String> getErrorHandlerClasses() {
        return Mixins.getErrorHandlerClasses();
    }

    public Object getActiveTransformer() {
        return GlobalProperties.get("mixin.transformer");
    }

    public void setActiveTransformer(ITransformer iTransformer) {
        if (iTransformer != null) {
            GlobalProperties.put("mixin.transformer", iTransformer);
        }
    }

    public MixinEnvironment setSide(Side side) {
        if (side != null && this.getSide() == Side.UNKNOWN && side != Side.UNKNOWN) {
            this.side = side;
        }
        return this;
    }

    public Side getSide() {
        if (this.side == null) {
            for (Side side : Side.values()) {
                if (!side.detect()) continue;
                this.side = side;
                break;
            }
        }
        return this.side != null ? this.side : Side.UNKNOWN;
    }

    public String getVersion() {
        return (String)GlobalProperties.get("mixin.initialised");
    }

    public boolean getOption(Option option) {
        return this.options[option.ordinal()];
    }

    public void setOption(Option option, boolean bl) {
        this.options[option.ordinal()] = bl;
    }

    public String getOptionValue(Option option) {
        return option.getStringValue();
    }

    public <E extends Enum<E>> E getOption(Option option, E e2) {
        return option.getEnumValue(e2);
    }

    public void setObfuscationContext(String string) {
        this.obfuscationContext = string;
    }

    public String getObfuscationContext() {
        return this.obfuscationContext;
    }

    public String getRefmapObfuscationContext() {
        String string = Option.OBFUSCATION_TYPE.getStringValue();
        if (string != null) {
            return string;
        }
        return this.obfuscationContext;
    }

    public RemapperChain getRemappers() {
        return this.remappers;
    }

    public void audit() {
        Object object = this.getActiveTransformer();
        if (object instanceof MixinTransformer) {
            MixinTransformer mixinTransformer = (MixinTransformer)object;
            mixinTransformer.audit(this);
        }
    }

    public List<ILegacyClassTransformer> getTransformers() {
        if (this.transformers == null) {
            this.buildTransformerDelegationList();
        }
        return Collections.unmodifiableList(this.transformers);
    }

    public void addTransformerExclusion(String string) {
        excludeTransformers.add(string);
        this.transformers = null;
    }

    private void buildTransformerDelegationList() {
        logger.debug("Rebuilding transformer delegation list:");
        this.transformers = new ArrayList<ILegacyClassTransformer>();
        for (ITransformer iTransformer : this.service.getTransformers()) {
            if (!(iTransformer instanceof ILegacyClassTransformer)) continue;
            ILegacyClassTransformer iLegacyClassTransformer = (ILegacyClassTransformer)iTransformer;
            String string = iLegacyClassTransformer.getName();
            boolean bl = true;
            for (String string2 : excludeTransformers) {
                if (!string.contains(string2)) continue;
                bl = false;
                break;
            }
            if (bl && !iLegacyClassTransformer.isDelegationExcluded()) {
                logger.debug("  Adding:    {}", new Object[]{string});
                this.transformers.add(iLegacyClassTransformer);
                continue;
            }
            logger.debug("  Excluding: {}", new Object[]{string});
        }
        logger.debug("Transformer delegation list created with {} entries", new Object[]{this.transformers.size()});
    }

    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.phase);
    }

    private static Phase getCurrentPhase() {
        if (currentPhase == Phase.NOT_INITIALISED) {
            MixinEnvironment.init(Phase.PREINIT);
        }
        return currentPhase;
    }

    public static void init(Phase phase) {
        if (currentPhase == Phase.NOT_INITIALISED) {
            currentPhase = phase;
            MixinEnvironment mixinEnvironment = MixinEnvironment.getEnvironment(phase);
            MixinEnvironment.getProfiler().setActive(mixinEnvironment.getOption(Option.DEBUG_PROFILER));
            MixinLogWatcher.begin();
        }
    }

    public static MixinEnvironment getEnvironment(Phase phase) {
        if (phase == null) {
            return Phase.DEFAULT.getEnvironment();
        }
        return phase.getEnvironment();
    }

    public static MixinEnvironment getDefaultEnvironment() {
        return MixinEnvironment.getEnvironment(Phase.DEFAULT);
    }

    public static MixinEnvironment getCurrentEnvironment() {
        if (currentEnvironment == null) {
            currentEnvironment = MixinEnvironment.getEnvironment(MixinEnvironment.getCurrentPhase());
        }
        return currentEnvironment;
    }

    public static CompatibilityLevel getCompatibilityLevel() {
        return compatibility;
    }

    @Deprecated
    public static void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) throws IllegalArgumentException {
        StackTraceElement[] arrstackTraceElement = Thread.currentThread().getStackTrace();
        if (!"org.spongepowered.asm.mixin.transformer.MixinConfig".equals(arrstackTraceElement[2].getClassName())) {
            logger.warn("MixinEnvironment::setCompatibilityLevel is deprecated and will be removed. Set level via config instead!");
        }
        if (compatibilityLevel != compatibility && compatibilityLevel.isAtLeast(compatibility)) {
            if (!compatibilityLevel.isSupported()) {
                throw new IllegalArgumentException("The requested compatibility level " + (Object)((Object)compatibilityLevel) + " could not be set. Level is not supported");
            }
            compatibility = compatibilityLevel;
            logger.info("Compatibility level set to {}", new Object[]{compatibilityLevel});
        }
    }

    public static Profiler getProfiler() {
        return profiler;
    }

    static void gotoPhase(Phase phase) {
        if (phase == null || phase.ordinal < 0) {
            throw new IllegalArgumentException("Cannot go to the specified phase, phase is null or invalid");
        }
        if (phase.ordinal > MixinEnvironment.getCurrentPhase().ordinal) {
            MixinService.getService().beginPhase();
        }
        if (phase == Phase.DEFAULT) {
            MixinLogWatcher.end();
        }
        currentPhase = phase;
        currentEnvironment = MixinEnvironment.getEnvironment(MixinEnvironment.getCurrentPhase());
    }

    static {
        currentPhase = Phase.NOT_INITIALISED;
        compatibility = Option.DEFAULT_COMPATIBILITY_LEVEL.getEnumValue(CompatibilityLevel.JAVA_6);
        showHeader = true;
        logger = LogManager.getLogger((String)"mixin");
        profiler = new Profiler();
    }

    static class MixinLogWatcher {
        static MixinAppender appender = new MixinAppender();
        static org.apache.logging.log4j.core.Logger log;
        static Level oldLevel;

        MixinLogWatcher() {
        }

        static void begin() {
            Logger logger = LogManager.getLogger((String)"FML");
            if (!(logger instanceof org.apache.logging.log4j.core.Logger)) {
                return;
            }
            log = (org.apache.logging.log4j.core.Logger)logger;
            oldLevel = log.getLevel();
            appender.start();
            log.addAppender((Appender)appender);
            log.setLevel(Level.ALL);
        }

        static void end() {
            if (log != null) {
                log.removeAppender((Appender)appender);
            }
        }

        static {
            oldLevel = null;
        }

        static class MixinAppender
        extends AbstractAppender {
            MixinAppender() {
                super("MixinLogWatcherAppender", null, null);
            }

            public void append(LogEvent logEvent) {
                if (logEvent.getLevel() != Level.DEBUG || !"Validating minecraft".equals(logEvent.getMessage().getFormattedMessage())) {
                    return;
                }
                MixinEnvironment.gotoPhase(Phase.INIT);
                if (log.getLevel() == Level.ALL) {
                    log.setLevel(oldLevel);
                }
            }
        }
    }

    static class TokenProviderWrapper
    implements Comparable<TokenProviderWrapper> {
        private static int nextOrder = 0;
        private final int priority;
        private final int order;
        private final IEnvironmentTokenProvider provider;
        private final MixinEnvironment environment;

        public TokenProviderWrapper(IEnvironmentTokenProvider iEnvironmentTokenProvider, MixinEnvironment mixinEnvironment) {
            this.provider = iEnvironmentTokenProvider;
            this.environment = mixinEnvironment;
            this.order = nextOrder++;
            this.priority = iEnvironmentTokenProvider.getPriority();
        }

        @Override
        public int compareTo(TokenProviderWrapper tokenProviderWrapper) {
            if (tokenProviderWrapper == null) {
                return 0;
            }
            if (tokenProviderWrapper.priority == this.priority) {
                return tokenProviderWrapper.order - this.order;
            }
            return tokenProviderWrapper.priority - this.priority;
        }

        public IEnvironmentTokenProvider getProvider() {
            return this.provider;
        }

        Integer getToken(String string) {
            return this.provider.getToken(string, this.environment);
        }
    }

    public static enum CompatibilityLevel {
        JAVA_6(6, 50, false),
        JAVA_7(7, 51, false){

            @Override
            boolean isSupported() {
                return JavaVersion.current() >= 1.7;
            }
        }
        ,
        JAVA_8(8, 52, true){

            @Override
            boolean isSupported() {
                return JavaVersion.current() >= 1.8;
            }
        }
        ,
        JAVA_9(9, 53, true){

            @Override
            boolean isSupported() {
                return false;
            }
        };

        private static final int CLASS_V1_9 = 53;
        private final int ver;
        private final int classVersion;
        private final boolean supportsMethodsInInterfaces;
        private CompatibilityLevel maxCompatibleLevel;

        private CompatibilityLevel(int n3, int n4, boolean bl) {
            this.ver = n3;
            this.classVersion = n4;
            this.supportsMethodsInInterfaces = bl;
        }

        private void setMaxCompatibleLevel(CompatibilityLevel compatibilityLevel) {
            this.maxCompatibleLevel = compatibilityLevel;
        }

        boolean isSupported() {
            return true;
        }

        public int classVersion() {
            return this.classVersion;
        }

        public boolean supportsMethodsInInterfaces() {
            return this.supportsMethodsInInterfaces;
        }

        public boolean isAtLeast(CompatibilityLevel compatibilityLevel) {
            return compatibilityLevel == null || this.ver >= compatibilityLevel.ver;
        }

        public boolean canElevateTo(CompatibilityLevel compatibilityLevel) {
            if (compatibilityLevel == null || this.maxCompatibleLevel == null) {
                return true;
            }
            return compatibilityLevel.ver <= this.maxCompatibleLevel.ver;
        }

        public boolean canSupport(CompatibilityLevel compatibilityLevel) {
            if (compatibilityLevel == null) {
                return true;
            }
            return compatibilityLevel.canElevateTo(this);
        }
    }

    public static enum Option {
        DEBUG_ALL("debug"),
        DEBUG_EXPORT(DEBUG_ALL, "export"),
        DEBUG_EXPORT_FILTER(DEBUG_EXPORT, "filter", false),
        DEBUG_EXPORT_DECOMPILE(DEBUG_EXPORT, Inherit.ALLOW_OVERRIDE, "decompile"),
        DEBUG_EXPORT_DECOMPILE_THREADED(DEBUG_EXPORT_DECOMPILE, Inherit.ALLOW_OVERRIDE, "async"),
        DEBUG_EXPORT_DECOMPILE_MERGESIGNATURES(DEBUG_EXPORT_DECOMPILE, Inherit.ALLOW_OVERRIDE, "mergeGenericSignatures"),
        DEBUG_VERIFY(DEBUG_ALL, "verify"),
        DEBUG_VERBOSE(DEBUG_ALL, "verbose"),
        DEBUG_INJECTORS(DEBUG_ALL, "countInjections"),
        DEBUG_STRICT(DEBUG_ALL, Inherit.INDEPENDENT, "strict"),
        DEBUG_UNIQUE(DEBUG_STRICT, "unique"),
        DEBUG_TARGETS(DEBUG_STRICT, "targets"),
        DEBUG_PROFILER(DEBUG_ALL, Inherit.ALLOW_OVERRIDE, "profiler"),
        DUMP_TARGET_ON_FAILURE("dumpTargetOnFailure"),
        CHECK_ALL("checks"),
        CHECK_IMPLEMENTS(CHECK_ALL, "interfaces"),
        CHECK_IMPLEMENTS_STRICT(CHECK_IMPLEMENTS, Inherit.ALLOW_OVERRIDE, "strict"),
        IGNORE_CONSTRAINTS("ignoreConstraints"),
        HOT_SWAP("hotSwap"),
        ENVIRONMENT(Inherit.ALWAYS_FALSE, "env"),
        OBFUSCATION_TYPE(ENVIRONMENT, Inherit.ALWAYS_FALSE, "obf"),
        DISABLE_REFMAP(ENVIRONMENT, Inherit.INDEPENDENT, "disableRefMap"),
        REFMAP_REMAP(ENVIRONMENT, Inherit.INDEPENDENT, "remapRefMap"),
        REFMAP_REMAP_RESOURCE(ENVIRONMENT, Inherit.INDEPENDENT, "refMapRemappingFile", ""),
        REFMAP_REMAP_SOURCE_ENV(ENVIRONMENT, Inherit.INDEPENDENT, "refMapRemappingEnv", "searge"),
        REFMAP_REMAP_ALLOW_PERMISSIVE(ENVIRONMENT, Inherit.INDEPENDENT, "allowPermissiveMatch", true, "true"),
        IGNORE_REQUIRED(ENVIRONMENT, Inherit.INDEPENDENT, "ignoreRequired"),
        DEFAULT_COMPATIBILITY_LEVEL(ENVIRONMENT, Inherit.INDEPENDENT, "compatLevel"),
        SHIFT_BY_VIOLATION_BEHAVIOUR(ENVIRONMENT, Inherit.INDEPENDENT, "shiftByViolation", "warn"),
        INITIALISER_INJECTION_MODE("initialiserInjectionMode", "default");

        private static final String PREFIX = "mixin";
        final Option parent;
        final Inherit inheritance;
        final String property;
        final String defaultValue;
        final boolean isFlag;
        final int depth;

        private Option(String string2) {
            this(null, string2, true);
        }

        private Option(Inherit inherit, String string2) {
            this(null, inherit, string2, true);
        }

        private Option(String string2, boolean bl) {
            this(null, string2, bl);
        }

        private Option(String string2, String string3) {
            this(null, Inherit.INDEPENDENT, string2, false, string3);
        }

        private Option(Option option, String string2) {
            this(option, Inherit.INHERIT, string2, true);
        }

        private Option(Option option, Inherit inherit, String string2) {
            this(option, inherit, string2, true);
        }

        private Option(Option option, String string2, boolean bl) {
            this(option, Inherit.INHERIT, string2, bl, null);
        }

        private Option(Option option, Inherit inherit, String string2, boolean bl) {
            this(option, inherit, string2, bl, null);
        }

        private Option(Option option, String string2, String string3) {
            this(option, Inherit.INHERIT, string2, false, string3);
        }

        private Option(Option option, Inherit inherit, String string2, String string3) {
            this(option, inherit, string2, false, string3);
        }

        private Option(Option option, Inherit inherit, String string2, boolean bl, String string3) {
            this.parent = option;
            this.inheritance = inherit;
            this.property = (option != null ? option.property : PREFIX) + "." + string2;
            this.defaultValue = string3;
            this.isFlag = bl;
            int n3 = 0;
            while (option != null) {
                option = option.parent;
                ++n3;
            }
            this.depth = n3;
        }

        Option getParent() {
            return this.parent;
        }

        String getProperty() {
            return this.property;
        }

        public String toString() {
            return this.isFlag ? String.valueOf(this.getBooleanValue()) : this.getStringValue();
        }

        private boolean getLocalBooleanValue(boolean bl) {
            return Boolean.parseBoolean(System.getProperty(this.property, Boolean.toString(bl)));
        }

        private boolean getInheritedBooleanValue() {
            return this.parent != null && this.parent.getBooleanValue();
        }

        final boolean getBooleanValue() {
            if (this.inheritance == Inherit.ALWAYS_FALSE) {
                return false;
            }
            boolean bl = this.getLocalBooleanValue(false);
            if (this.inheritance == Inherit.INDEPENDENT) {
                return bl;
            }
            boolean bl2 = bl || this.getInheritedBooleanValue();
            return this.inheritance == Inherit.INHERIT ? bl2 : this.getLocalBooleanValue(bl2);
        }

        final String getStringValue() {
            return this.inheritance == Inherit.INDEPENDENT || this.parent == null || this.parent.getBooleanValue() ? System.getProperty(this.property, this.defaultValue) : this.defaultValue;
        }

        <E extends Enum<E>> E getEnumValue(E e2) {
            String string = System.getProperty(this.property, e2.name());
            try {
                return (E)Enum.valueOf(e2.getClass(), string.toUpperCase());
            }
            catch (IllegalArgumentException illegalArgumentException) {
                return e2;
            }
        }

        private static enum Inherit {
            INHERIT,
            ALLOW_OVERRIDE,
            INDEPENDENT,
            ALWAYS_FALSE;

        }
    }

    public static enum Side {
        UNKNOWN{

            @Override
            protected boolean detect() {
                return false;
            }
        }
        ,
        CLIENT{

            @Override
            protected boolean detect() {
                String string = MixinService.getService().getSideName();
                return "CLIENT".equals(string);
            }
        }
        ,
        SERVER{

            @Override
            protected boolean detect() {
                String string = MixinService.getService().getSideName();
                return "SERVER".equals(string) || "DEDICATEDSERVER".equals(string);
            }
        };


        protected abstract boolean detect();
    }

    public static final class Phase {
        static final Phase NOT_INITIALISED = new Phase(-1, "NOT_INITIALISED");
        public static final Phase PREINIT = new Phase(0, "PREINIT");
        public static final Phase INIT = new Phase(1, "INIT");
        public static final Phase DEFAULT = new Phase(2, "DEFAULT");
        static final List<Phase> phases = ImmutableList.of(PREINIT, INIT, DEFAULT);
        final int ordinal;
        final String name;
        private MixinEnvironment environment;

        private Phase(int n2, String string) {
            this.ordinal = n2;
            this.name = string;
        }

        public String toString() {
            return this.name;
        }

        public static Phase forName(String string) {
            for (Phase phase : phases) {
                if (!phase.name.equals(string)) continue;
                return phase;
            }
            return null;
        }

        MixinEnvironment getEnvironment() {
            if (this.ordinal < 0) {
                throw new IllegalArgumentException("Cannot access the NOT_INITIALISED environment");
            }
            if (this.environment == null) {
                this.environment = new MixinEnvironment(this);
            }
            return this.environment;
        }
    }
}

