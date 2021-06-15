/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 *  org.apache.logging.log4j.Level
 */
package org.spongepowered.asm.launch.platform;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.platform.MixinPlatformAgentAbstract;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public class MixinPlatformAgentFML
extends MixinPlatformAgentAbstract {
    private static final String LOAD_CORE_MOD_METHOD = "loadCoreMod";
    private static final String GET_REPARSEABLE_COREMODS_METHOD = "getReparseableCoremods";
    private static final String CORE_MOD_MANAGER_CLASS = "net.minecraftforge.fml.relauncher.CoreModManager";
    private static final String CORE_MOD_MANAGER_CLASS_LEGACY = "cpw.mods.fml.relauncher.CoreModManager";
    private static final String GET_IGNORED_MODS_METHOD = "getIgnoredMods";
    private static final String GET_IGNORED_MODS_METHOD_LEGACY = "getLoadedCoremods";
    private static final String FML_REMAPPER_ADAPTER_CLASS = "org.spongepowered.asm.bridge.RemapperAdapterFML";
    private static final String FML_CMDLINE_COREMODS = "fml.coreMods.load";
    private static final String FML_PLUGIN_WRAPPER_CLASS = "FMLPluginWrapper";
    private static final String FML_CORE_MOD_INSTANCE_FIELD = "coreModInstance";
    private static final String MFATT_FORCELOADASMOD = "ForceLoadAsMod";
    private static final String MFATT_FMLCOREPLUGIN = "FMLCorePlugin";
    private static final String MFATT_COREMODCONTAINSMOD = "FMLCorePluginContainsFMLMod";
    private static final String FML_TWEAKER_DEOBF = "FMLDeobfTweaker";
    private static final String FML_TWEAKER_INJECTION = "FMLInjectionAndSortingTweaker";
    private static final String FML_TWEAKER_TERMINAL = "TerminalTweaker";
    private static final Set<String> loadedCoreMods = new HashSet<String>();
    private final ITweaker coreModWrapper;
    private final String fileName;
    private Class<?> clCoreModManager;
    private boolean initInjectionState;

    public MixinPlatformAgentFML(MixinPlatformManager mixinPlatformManager, URI uRI) {
        super(mixinPlatformManager, uRI);
        this.fileName = this.container.getName();
        this.coreModWrapper = this.initFMLCoreMod();
    }

    private ITweaker initFMLCoreMod() {
        try {
            try {
                this.clCoreModManager = MixinPlatformAgentFML.getCoreModManagerClass();
            }
            catch (ClassNotFoundException classNotFoundException) {
                MixinPlatformAgentAbstract.logger.info("FML platform manager could not load class {}. Proceeding without FML support.", new Object[]{classNotFoundException.getMessage()});
                return null;
            }
            if ("true".equalsIgnoreCase(this.attributes.get(MFATT_FORCELOADASMOD))) {
                MixinPlatformAgentAbstract.logger.debug("ForceLoadAsMod was specified for {}, attempting force-load", new Object[]{this.fileName});
                this.loadAsMod();
            }
            return this.injectCorePlugin();
        }
        catch (Exception exception) {
            MixinPlatformAgentAbstract.logger.catching((Throwable)exception);
            return null;
        }
    }

    private void loadAsMod() {
        try {
            MixinPlatformAgentFML.getIgnoredMods(this.clCoreModManager).remove(this.fileName);
        }
        catch (Exception exception) {
            MixinPlatformAgentAbstract.logger.catching((Throwable)exception);
        }
        if (this.attributes.get(MFATT_COREMODCONTAINSMOD) != null) {
            if (this.isIgnoredReparseable()) {
                MixinPlatformAgentAbstract.logger.debug("Ignoring request to add {} to reparseable coremod collection - it is a deobfuscated dependency", new Object[]{this.fileName});
                return;
            }
            this.addReparseableJar();
        }
    }

    private boolean isIgnoredReparseable() {
        return this.container.toString().contains("deobfedDeps");
    }

    private void addReparseableJar() {
        try {
            Method method = this.clCoreModManager.getDeclaredMethod(GlobalProperties.getString("mixin.launch.fml.reparseablecoremodsmethod", GET_REPARSEABLE_COREMODS_METHOD), new Class[0]);
            List list = (List)method.invoke(null, new Object[0]);
            if (!list.contains(this.fileName)) {
                MixinPlatformAgentAbstract.logger.debug("Adding {} to reparseable coremod collection", new Object[]{this.fileName});
                list.add(this.fileName);
            }
        }
        catch (Exception exception) {
            MixinPlatformAgentAbstract.logger.catching((Throwable)exception);
        }
    }

    private ITweaker injectCorePlugin() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String string = this.attributes.get(MFATT_FMLCOREPLUGIN);
        if (string == null) {
            return null;
        }
        if (this.isAlreadyInjected(string)) {
            MixinPlatformAgentAbstract.logger.debug("{} has core plugin {}. Skipping because it was already injected.", new Object[]{this.fileName, string});
            return null;
        }
        MixinPlatformAgentAbstract.logger.debug("{} has core plugin {}. Injecting it into FML for co-initialisation:", new Object[]{this.fileName, string});
        Method method = this.clCoreModManager.getDeclaredMethod(GlobalProperties.getString("mixin.launch.fml.loadcoremodmethod", LOAD_CORE_MOD_METHOD), LaunchClassLoader.class, String.class, File.class);
        method.setAccessible(true);
        ITweaker iTweaker = (ITweaker)method.invoke(null, new Object[]{Launch.classLoader, string, this.container});
        if (iTweaker == null) {
            MixinPlatformAgentAbstract.logger.debug("Core plugin {} could not be loaded.", new Object[]{string});
            return null;
        }
        this.initInjectionState = MixinPlatformAgentFML.isTweakerQueued(FML_TWEAKER_INJECTION);
        loadedCoreMods.add(string);
        return iTweaker;
    }

    private boolean isAlreadyInjected(String string) {
        if (loadedCoreMods.contains(string)) {
            return true;
        }
        try {
            List list = (List)GlobalProperties.get("Tweaks");
            if (list == null) {
                return false;
            }
            for (ITweaker iTweaker : list) {
                Class<?> class_ = iTweaker.getClass();
                if (!FML_PLUGIN_WRAPPER_CLASS.equals(class_.getSimpleName())) continue;
                Field field = class_.getField(FML_CORE_MOD_INSTANCE_FIELD);
                field.setAccessible(true);
                Object object = field.get((Object)iTweaker);
                if (!string.equals(object.getClass().getName())) continue;
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    @Override
    public String getPhaseProvider() {
        return MixinPlatformAgentFML.class.getName() + "$PhaseProvider";
    }

    @Override
    public void prepare() {
        this.initInjectionState |= MixinPlatformAgentFML.isTweakerQueued(FML_TWEAKER_INJECTION);
    }

    @Override
    public void initPrimaryContainer() {
        if (this.clCoreModManager != null) {
            this.injectRemapper();
        }
    }

    private void injectRemapper() {
        try {
            MixinPlatformAgentAbstract.logger.debug("Creating FML remapper adapter: {}", new Object[]{FML_REMAPPER_ADAPTER_CLASS});
            Class<?> class_ = Class.forName(FML_REMAPPER_ADAPTER_CLASS, true, (ClassLoader)Launch.classLoader);
            Method method = class_.getDeclaredMethod("create", new Class[0]);
            IRemapper iRemapper = (IRemapper)method.invoke(null, new Object[0]);
            MixinEnvironment.getDefaultEnvironment().getRemappers().add(iRemapper);
        }
        catch (Exception exception) {
            MixinPlatformAgentAbstract.logger.debug("Failed instancing FML remapper adapter, things will probably go horribly for notch-obf'd mods!");
        }
    }

    @Override
    public void inject() {
        if (this.coreModWrapper != null && this.checkForCoInitialisation()) {
            MixinPlatformAgentAbstract.logger.debug("FML agent is co-initiralising coremod instance {} for {}", new Object[]{this.coreModWrapper, this.uri});
            this.coreModWrapper.injectIntoClassLoader(Launch.classLoader);
        }
    }

    @Override
    public String getLaunchTarget() {
        return null;
    }

    protected final boolean checkForCoInitialisation() {
        boolean bl = MixinPlatformAgentFML.isTweakerQueued(FML_TWEAKER_INJECTION);
        boolean bl2 = MixinPlatformAgentFML.isTweakerQueued(FML_TWEAKER_TERMINAL);
        if (this.initInjectionState && bl2 || bl) {
            MixinPlatformAgentAbstract.logger.debug("FML agent is skipping co-init for {} because FML will inject it normally", new Object[]{this.coreModWrapper});
            return false;
        }
        return !MixinPlatformAgentFML.isTweakerQueued(FML_TWEAKER_DEOBF);
    }

    private static boolean isTweakerQueued(String string) {
        for (String string2 : (List)GlobalProperties.get("TweakClasses")) {
            if (!string2.endsWith(string)) continue;
            return true;
        }
        return false;
    }

    private static Class<?> getCoreModManagerClass() throws ClassNotFoundException {
        try {
            return Class.forName(GlobalProperties.getString("mixin.launch.fml.coremodmanagerclass", CORE_MOD_MANAGER_CLASS));
        }
        catch (ClassNotFoundException classNotFoundException) {
            return Class.forName(CORE_MOD_MANAGER_CLASS_LEGACY);
        }
    }

    private static List<String> getIgnoredMods(Class<?> class_) throws IllegalAccessException, InvocationTargetException {
        Method method = null;
        try {
            method = class_.getDeclaredMethod(GlobalProperties.getString("mixin.launch.fml.ignoredmodsmethod", GET_IGNORED_MODS_METHOD), new Class[0]);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            try {
                method = class_.getDeclaredMethod(GET_IGNORED_MODS_METHOD_LEGACY, new Class[0]);
            }
            catch (NoSuchMethodException noSuchMethodException2) {
                MixinPlatformAgentAbstract.logger.catching(Level.DEBUG, (Throwable)noSuchMethodException2);
                return Collections.emptyList();
            }
        }
        return (List)method.invoke(null, new Object[0]);
    }

    static {
        for (String string : System.getProperty(FML_CMDLINE_COREMODS, "").split(",")) {
            if (string.isEmpty()) continue;
            MixinPlatformAgentAbstract.logger.debug("FML platform agent will ignore coremod {} specified on the command line", new Object[]{string});
            loadedCoreMods.add(string);
        }
    }
}

