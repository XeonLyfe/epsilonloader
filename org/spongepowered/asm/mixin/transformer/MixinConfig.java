/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.annotations.SerializedName
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinInitialisationError;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.mixin.refmap.RemappingReferenceMapper;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.VersionNumber;

final class MixinConfig
implements Comparable<MixinConfig>,
IMixinConfig {
    private static int configOrder = 0;
    private static final Set<String> globalMixinList = new HashSet<String>();
    private final Logger logger = LogManager.getLogger((String)"mixin");
    private final transient Map<String, List<MixinInfo>> mixinMapping = new HashMap<String, List<MixinInfo>>();
    private final transient Set<String> unhandledTargets = new HashSet<String>();
    private final transient List<MixinInfo> mixins = new ArrayList<MixinInfo>();
    private transient Config handle;
    @SerializedName(value="target")
    private String selector;
    @SerializedName(value="minVersion")
    private String version;
    @SerializedName(value="compatibilityLevel")
    private String compatibility;
    @SerializedName(value="required")
    private boolean required;
    @SerializedName(value="priority")
    private int priority = 1000;
    @SerializedName(value="mixinPriority")
    private int mixinPriority = 1000;
    @SerializedName(value="package")
    private String mixinPackage;
    @SerializedName(value="mixins")
    private List<String> mixinClasses;
    @SerializedName(value="client")
    private List<String> mixinClassesClient;
    @SerializedName(value="server")
    private List<String> mixinClassesServer;
    @SerializedName(value="setSourceFile")
    private boolean setSourceFile = false;
    @SerializedName(value="refmap")
    private String refMapperConfig;
    @SerializedName(value="verbose")
    private boolean verboseLogging;
    private final transient int order = configOrder++;
    private final transient List<IListener> listeners = new ArrayList<IListener>();
    private transient IMixinService service;
    private transient MixinEnvironment env;
    private transient String name;
    @SerializedName(value="plugin")
    private String pluginClassName;
    @SerializedName(value="injectors")
    private InjectorOptions injectorOptions = new InjectorOptions();
    @SerializedName(value="overwrites")
    private OverwriteOptions overwriteOptions = new OverwriteOptions();
    private transient IMixinConfigPlugin plugin;
    private transient IReferenceMapper refMapper;
    private transient boolean prepared = false;
    private transient boolean visited = false;

    private MixinConfig() {
    }

    private boolean onLoad(IMixinService iMixinService, String string, MixinEnvironment mixinEnvironment) {
        this.service = iMixinService;
        this.name = string;
        this.env = this.parseSelector(this.selector, mixinEnvironment);
        this.required &= !this.env.getOption(MixinEnvironment.Option.IGNORE_REQUIRED);
        this.initCompatibilityLevel();
        this.initInjectionPoints();
        return this.checkVersion();
    }

    private void initCompatibilityLevel() {
        MixinEnvironment.CompatibilityLevel compatibilityLevel;
        if (this.compatibility == null) {
            return;
        }
        MixinEnvironment.CompatibilityLevel compatibilityLevel2 = MixinEnvironment.CompatibilityLevel.valueOf(this.compatibility.trim().toUpperCase());
        if (compatibilityLevel2 == (compatibilityLevel = MixinEnvironment.getCompatibilityLevel())) {
            return;
        }
        if (compatibilityLevel.isAtLeast(compatibilityLevel2) && !compatibilityLevel.canSupport(compatibilityLevel2)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + (Object)((Object)compatibilityLevel2) + " which is too old");
        }
        if (!compatibilityLevel.canElevateTo(compatibilityLevel2)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + (Object)((Object)compatibilityLevel2) + " which is prohibited by " + (Object)((Object)compatibilityLevel));
        }
        MixinEnvironment.setCompatibilityLevel(compatibilityLevel2);
    }

    private MixinEnvironment parseSelector(String string, MixinEnvironment mixinEnvironment) {
        if (string != null) {
            String[] arrstring;
            for (String string2 : arrstring = string.split("[&\\| ]")) {
                string2 = string2.trim();
                Pattern pattern = Pattern.compile("^@env(?:ironment)?\\(([A-Z]+)\\)$");
                Matcher matcher = pattern.matcher(string2);
                if (!matcher.matches()) continue;
                return MixinEnvironment.getEnvironment(MixinEnvironment.Phase.forName(matcher.group(1)));
            }
            MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(string);
            if (phase != null) {
                return MixinEnvironment.getEnvironment(phase);
            }
        }
        return mixinEnvironment;
    }

    private void initInjectionPoints() {
        if (this.injectorOptions.injectionPoints == null) {
            return;
        }
        for (String string : this.injectorOptions.injectionPoints) {
            try {
                Class<?> class_ = this.service.getClassProvider().findClass(string, true);
                if (InjectionPoint.class.isAssignableFrom(class_)) {
                    InjectionPoint.register(class_);
                    continue;
                }
                this.logger.error("Unable to register injection point {} for {}, class must extend InjectionPoint", new Object[]{class_, this});
            }
            catch (Throwable throwable) {
                this.logger.catching(throwable);
            }
        }
    }

    private boolean checkVersion() throws MixinInitialisationError {
        VersionNumber versionNumber;
        VersionNumber versionNumber2;
        if (this.version == null) {
            this.logger.error("Mixin config {} does not specify \"minVersion\" property", new Object[]{this.name});
        }
        if ((versionNumber2 = VersionNumber.parse(this.version)).compareTo(versionNumber = VersionNumber.parse(this.env.getVersion())) > 0) {
            this.logger.warn("Mixin config {} requires mixin subsystem version {} but {} was found. The mixin config will not be applied.", new Object[]{this.name, versionNumber2, versionNumber});
            if (this.required) {
                throw new MixinInitialisationError("Required mixin config " + this.name + " requires mixin subsystem version " + versionNumber2);
            }
            return false;
        }
        return true;
    }

    void addListener(IListener iListener) {
        this.listeners.add(iListener);
    }

    void onSelect() {
        if (this.pluginClassName != null) {
            try {
                Class<?> class_ = this.service.getClassProvider().findClass(this.pluginClassName, true);
                this.plugin = (IMixinConfigPlugin)class_.newInstance();
                if (this.plugin != null) {
                    this.plugin.onLoad(this.mixinPackage);
                }
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
                this.plugin = null;
            }
        }
        if (!this.mixinPackage.endsWith(".")) {
            this.mixinPackage = this.mixinPackage + ".";
        }
        boolean bl = false;
        if (this.refMapperConfig == null) {
            if (this.plugin != null) {
                this.refMapperConfig = this.plugin.getRefMapperConfig();
            }
            if (this.refMapperConfig == null) {
                bl = true;
                this.refMapperConfig = "mixin.refmap.json";
            }
        }
        this.refMapper = ReferenceMapper.read(this.refMapperConfig);
        this.verboseLogging |= this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
        if (!bl && this.refMapper.isDefault() && !this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP)) {
            this.logger.warn("Reference map '{}' for {} could not be read. If this is a development environment you can ignore this message", new Object[]{this.refMapperConfig, this});
        }
        if (this.env.getOption(MixinEnvironment.Option.REFMAP_REMAP)) {
            this.refMapper = RemappingReferenceMapper.of(this.env, this.refMapper);
        }
    }

    void prepare() {
        if (this.prepared) {
            return;
        }
        this.prepared = true;
        this.prepareMixins(this.mixinClasses, false);
        switch (this.env.getSide()) {
            case CLIENT: {
                this.prepareMixins(this.mixinClassesClient, false);
                break;
            }
            case SERVER: {
                this.prepareMixins(this.mixinClassesServer, false);
                break;
            }
            default: {
                this.logger.warn("Mixin environment was unable to detect the current side, sided mixins will not be applied");
            }
        }
    }

    void postInitialise() {
        Object object;
        if (this.plugin != null) {
            object = this.plugin.getMixins();
            this.prepareMixins((List<String>)object, true);
        }
        object = this.mixins.iterator();
        while (object.hasNext()) {
            MixinInfo mixinInfo = (MixinInfo)object.next();
            try {
                mixinInfo.validate();
                for (IListener iListener : this.listeners) {
                    iListener.onInit(mixinInfo);
                }
            }
            catch (InvalidMixinException invalidMixinException) {
                this.logger.error(invalidMixinException.getMixin() + ": " + invalidMixinException.getMessage(), (Throwable)invalidMixinException);
                this.removeMixin(mixinInfo);
                object.remove();
            }
            catch (Exception exception) {
                this.logger.error(exception.getMessage(), (Throwable)exception);
                this.removeMixin(mixinInfo);
                object.remove();
            }
        }
    }

    private void removeMixin(MixinInfo mixinInfo) {
        for (List<MixinInfo> list : this.mixinMapping.values()) {
            Iterator<MixinInfo> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                if (mixinInfo != iterator2.next()) continue;
                iterator2.remove();
            }
        }
    }

    private void prepareMixins(List<String> list, boolean bl) {
        if (list == null) {
            return;
        }
        for (String string : list) {
            String string2 = this.mixinPackage + string;
            if (string == null || globalMixinList.contains(string2)) continue;
            MixinInfo mixinInfo = null;
            try {
                mixinInfo = new MixinInfo(this.service, this, string, true, this.plugin, bl);
                if (mixinInfo.getTargetClasses().size() <= 0) continue;
                globalMixinList.add(string2);
                for (String string3 : mixinInfo.getTargetClasses()) {
                    String string4 = string3.replace('/', '.');
                    this.mixinsFor(string4).add(mixinInfo);
                    this.unhandledTargets.add(string4);
                }
                for (IListener iListener : this.listeners) {
                    iListener.onPrepare(mixinInfo);
                }
                this.mixins.add(mixinInfo);
            }
            catch (InvalidMixinException invalidMixinException) {
                if (this.required) {
                    throw invalidMixinException;
                }
                this.logger.error(invalidMixinException.getMessage(), (Throwable)invalidMixinException);
            }
            catch (Exception exception) {
                if (this.required) {
                    throw new InvalidMixinException(mixinInfo, "Error initialising mixin " + mixinInfo + " - " + exception.getClass() + ": " + exception.getMessage(), (Throwable)exception);
                }
                this.logger.error(exception.getMessage(), (Throwable)exception);
            }
        }
    }

    void postApply(String string, ClassNode classNode) {
        this.unhandledTargets.remove(string);
    }

    public Config getHandle() {
        if (this.handle == null) {
            this.handle = new Config(this);
        }
        return this.handle;
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }

    @Override
    public MixinEnvironment getEnvironment() {
        return this.env;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMixinPackage() {
        return this.mixinPackage;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    public int getDefaultMixinPriority() {
        return this.mixinPriority;
    }

    public int getDefaultRequiredInjections() {
        return this.injectorOptions.defaultRequireValue;
    }

    public String getDefaultInjectorGroup() {
        String string = this.injectorOptions.defaultGroup;
        return string != null && !string.isEmpty() ? string : "default";
    }

    public boolean conformOverwriteVisibility() {
        return this.overwriteOptions.conformAccessModifiers;
    }

    public boolean requireOverwriteAnnotations() {
        return this.overwriteOptions.requireOverwriteAnnotations;
    }

    public int getMaxShiftByValue() {
        return Math.min(Math.max(this.injectorOptions.maxShiftBy, 0), 5);
    }

    public boolean select(MixinEnvironment mixinEnvironment) {
        this.visited = true;
        return this.env == mixinEnvironment;
    }

    boolean isVisited() {
        return this.visited;
    }

    int getDeclaredMixinCount() {
        return MixinConfig.getCollectionSize(this.mixinClasses, this.mixinClassesClient, this.mixinClassesServer);
    }

    int getMixinCount() {
        return this.mixins.size();
    }

    public List<String> getClasses() {
        return Collections.unmodifiableList(this.mixinClasses);
    }

    public boolean shouldSetSourceFile() {
        return this.setSourceFile;
    }

    public IReferenceMapper getReferenceMapper() {
        if (this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP)) {
            return ReferenceMapper.DEFAULT_MAPPER;
        }
        this.refMapper.setContext(this.env.getRefmapObfuscationContext());
        return this.refMapper;
    }

    String remapClassName(String string, String string2) {
        return this.getReferenceMapper().remap(string, string2);
    }

    @Override
    public IMixinConfigPlugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Set<String> getTargets() {
        return Collections.unmodifiableSet(this.mixinMapping.keySet());
    }

    public Set<String> getUnhandledTargets() {
        return Collections.unmodifiableSet(this.unhandledTargets);
    }

    public Level getLoggingLevel() {
        return this.verboseLogging ? Level.INFO : Level.DEBUG;
    }

    public boolean packageMatch(String string) {
        return string.startsWith(this.mixinPackage);
    }

    public boolean hasMixinsFor(String string) {
        return this.mixinMapping.containsKey(string);
    }

    public List<MixinInfo> getMixinsFor(String string) {
        return this.mixinsFor(string);
    }

    private List<MixinInfo> mixinsFor(String string) {
        List<MixinInfo> list = this.mixinMapping.get(string);
        if (list == null) {
            list = new ArrayList<MixinInfo>();
            this.mixinMapping.put(string, list);
        }
        return list;
    }

    public List<String> reloadMixin(String string, byte[] arrby) {
        for (MixinInfo mixinInfo : this.mixins) {
            if (!mixinInfo.getClassName().equals(string)) continue;
            mixinInfo.reloadMixin(arrby);
            return mixinInfo.getTargetClasses();
        }
        return Collections.emptyList();
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(MixinConfig mixinConfig) {
        if (mixinConfig == null) {
            return 0;
        }
        if (mixinConfig.priority == this.priority) {
            return this.order - mixinConfig.order;
        }
        return this.priority - mixinConfig.priority;
    }

    static Config create(String string, MixinEnvironment mixinEnvironment) {
        try {
            IMixinService iMixinService = MixinService.getService();
            MixinConfig mixinConfig = (MixinConfig)new Gson().fromJson((Reader)new InputStreamReader(iMixinService.getResourceAsStream(string)), MixinConfig.class);
            if (mixinConfig.onLoad(iMixinService, string, mixinEnvironment)) {
                return mixinConfig.getHandle();
            }
            return null;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new IllegalArgumentException(String.format("The specified resource '%s' was invalid or could not be read", string), exception);
        }
    }

    private static int getCollectionSize(Collection<?> ... arrcollection) {
        int n2 = 0;
        for (Collection<?> collection : arrcollection) {
            if (collection == null) continue;
            n2 += collection.size();
        }
        return n2;
    }

    static interface IListener {
        public void onPrepare(MixinInfo var1);

        public void onInit(MixinInfo var1);
    }

    static class OverwriteOptions {
        @SerializedName(value="conformVisibility")
        boolean conformAccessModifiers;
        @SerializedName(value="requireAnnotations")
        boolean requireOverwriteAnnotations;

        OverwriteOptions() {
        }
    }

    static class InjectorOptions {
        @SerializedName(value="defaultRequire")
        int defaultRequireValue = 0;
        @SerializedName(value="defaultGroup")
        String defaultGroup = "default";
        @SerializedName(value="injectionPoints")
        List<String> injectionPoints;
        @SerializedName(value="maxShiftBy")
        int maxShiftBy = 0;

        InjectorOptions() {
        }
    }
}

