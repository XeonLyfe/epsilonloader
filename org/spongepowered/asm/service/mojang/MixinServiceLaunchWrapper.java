/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassNameTransformer
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.service.mojang;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.launchwrapper.IClassNameTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.IClassProvider;
import org.spongepowered.asm.service.ILegacyClassTransformer;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.ITransformer;
import org.spongepowered.asm.service.mojang.LaunchClassLoaderUtil;
import org.spongepowered.asm.service.mojang.LegacyTransformerHandle;
import org.spongepowered.asm.util.ReEntranceLock;
import org.spongepowered.asm.util.perf.Profiler;

public class MixinServiceLaunchWrapper
implements IMixinService,
IClassProvider,
IClassBytecodeProvider {
    public static final String BLACKBOARD_KEY_TWEAKCLASSES = "TweakClasses";
    public static final String BLACKBOARD_KEY_TWEAKS = "Tweaks";
    private static final String LAUNCH_PACKAGE = "org.spongepowered.asm.launch.";
    private static final String MIXIN_PACKAGE = "org.spongepowered.asm.mixin.";
    private static final String STATE_TWEAKER = "org.spongepowered.asm.mixin.EnvironmentStateTweaker";
    private static final String TRANSFORMER_PROXY_CLASS = "org.spongepowered.asm.mixin.transformer.Proxy";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final LaunchClassLoaderUtil classLoaderUtil = new LaunchClassLoaderUtil(Launch.classLoader);
    private final ReEntranceLock lock = new ReEntranceLock(1);
    private IClassNameTransformer nameTransformer;

    @Override
    public String getName() {
        return "LaunchWrapper";
    }

    @Override
    public boolean isValid() {
        try {
            Launch.classLoader.hashCode();
        }
        catch (Throwable throwable) {
            return false;
        }
        return true;
    }

    @Override
    public void prepare() {
        Launch.classLoader.addClassLoaderExclusion(LAUNCH_PACKAGE);
    }

    @Override
    public MixinEnvironment.Phase getInitialPhase() {
        if (MixinServiceLaunchWrapper.findInStackTrace("net.minecraft.launchwrapper.Launch", "launch") > 132) {
            return MixinEnvironment.Phase.DEFAULT;
        }
        return MixinEnvironment.Phase.PREINIT;
    }

    @Override
    public void init() {
        List list;
        if (MixinServiceLaunchWrapper.findInStackTrace("net.minecraft.launchwrapper.Launch", "launch") < 4) {
            logger.error("MixinBootstrap.doInit() called during a tweak constructor!");
        }
        if ((list = (List)GlobalProperties.get(BLACKBOARD_KEY_TWEAKCLASSES)) != null) {
            list.add(STATE_TWEAKER);
        }
    }

    @Override
    public ReEntranceLock getReEntranceLock() {
        return this.lock;
    }

    @Override
    public Collection<String> getPlatformAgents() {
        return ImmutableList.of("org.spongepowered.asm.launch.platform.MixinPlatformAgentFML");
    }

    @Override
    public IClassProvider getClassProvider() {
        return this;
    }

    @Override
    public IClassBytecodeProvider getBytecodeProvider() {
        return this;
    }

    @Override
    public Class<?> findClass(String string) throws ClassNotFoundException {
        return Launch.classLoader.findClass(string);
    }

    @Override
    public Class<?> findClass(String string, boolean bl) throws ClassNotFoundException {
        return Class.forName(string, bl, (ClassLoader)Launch.classLoader);
    }

    @Override
    public Class<?> findAgentClass(String string, boolean bl) throws ClassNotFoundException {
        return Class.forName(string, bl, Launch.class.getClassLoader());
    }

    @Override
    public void beginPhase() {
        Launch.classLoader.registerTransformer(TRANSFORMER_PROXY_CLASS);
    }

    @Override
    public void checkEnv(Object object) {
        if (object.getClass().getClassLoader() != Launch.class.getClassLoader()) {
            throw new MixinException("Attempted to init the mixin environment in the wrong classloader");
        }
    }

    @Override
    public InputStream getResourceAsStream(String string) {
        return Launch.classLoader.getResourceAsStream(string);
    }

    @Override
    public void registerInvalidClass(String string) {
        this.classLoaderUtil.registerInvalidClass(string);
    }

    @Override
    public boolean isClassLoaded(String string) {
        return this.classLoaderUtil.isClassLoaded(string);
    }

    @Override
    public String getClassRestrictions(String string) {
        String string2 = "";
        if (this.classLoaderUtil.isClassClassLoaderExcluded(string, null)) {
            string2 = "PACKAGE_CLASSLOADER_EXCLUSION";
        }
        if (this.classLoaderUtil.isClassTransformerExcluded(string, null)) {
            string2 = (string2.length() > 0 ? string2 + "," : "") + "PACKAGE_TRANSFORMER_EXCLUSION";
        }
        return string2;
    }

    @Override
    public URL[] getClassPath() {
        return Launch.classLoader.getSources().toArray(new URL[0]);
    }

    @Override
    public Collection<ITransformer> getTransformers() {
        List list = Launch.classLoader.getTransformers();
        ArrayList<ITransformer> arrayList = new ArrayList<ITransformer>(list.size());
        for (IClassTransformer iClassTransformer : list) {
            if (iClassTransformer instanceof ITransformer) {
                arrayList.add((ITransformer)iClassTransformer);
            } else {
                arrayList.add(new LegacyTransformerHandle(iClassTransformer));
            }
            if (!(iClassTransformer instanceof IClassNameTransformer)) continue;
            logger.debug("Found name transformer: {}", new Object[]{iClassTransformer.getClass().getName()});
            this.nameTransformer = (IClassNameTransformer)iClassTransformer;
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getClassBytes(String string, String string2) throws IOException {
        byte[] arrby = Launch.classLoader.getClassBytes(string);
        if (arrby != null) {
            return arrby;
        }
        URLClassLoader uRLClassLoader = (URLClassLoader)Launch.class.getClassLoader();
        InputStream inputStream = null;
        try {
            String string3 = string2.replace('.', '/').concat(".class");
            inputStream = uRLClassLoader.getResourceAsStream(string3);
            byte[] arrby2 = IOUtils.toByteArray((InputStream)inputStream);
            IOUtils.closeQuietly((InputStream)inputStream);
            return arrby2;
        }
        catch (Exception exception) {
            byte[] arrby3 = null;
            return arrby3;
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Override
    public byte[] getClassBytes(String string, boolean bl) throws ClassNotFoundException, IOException {
        String string2 = string.replace('/', '.');
        String string3 = this.unmapClassName(string2);
        Profiler profiler = MixinEnvironment.getProfiler();
        Profiler.Section section = profiler.begin(1, "class.load");
        byte[] arrby = this.getClassBytes(string3, string2);
        section.end();
        if (bl) {
            Profiler.Section section2 = profiler.begin(1, "class.transform");
            arrby = this.applyTransformers(string3, string2, arrby, profiler);
            section2.end();
        }
        if (arrby == null) {
            throw new ClassNotFoundException(String.format("The specified class '%s' was not found", string2));
        }
        return arrby;
    }

    private byte[] applyTransformers(String string, String string2, byte[] arrby, Profiler profiler) {
        if (this.classLoaderUtil.isClassExcluded(string, string2)) {
            return arrby;
        }
        MixinEnvironment mixinEnvironment = MixinEnvironment.getCurrentEnvironment();
        for (ILegacyClassTransformer iLegacyClassTransformer : mixinEnvironment.getTransformers()) {
            this.lock.clear();
            int n2 = iLegacyClassTransformer.getName().lastIndexOf(46);
            String string3 = iLegacyClassTransformer.getName().substring(n2 + 1);
            Profiler.Section section = profiler.begin(2, string3.toLowerCase());
            section.setInfo(iLegacyClassTransformer.getName());
            arrby = iLegacyClassTransformer.transformClassBytes(string, string2, arrby);
            section.end();
            if (!this.lock.isSet()) continue;
            mixinEnvironment.addTransformerExclusion(iLegacyClassTransformer.getName());
            this.lock.clear();
            logger.info("A re-entrant transformer '{}' was detected and will no longer process meta class data", new Object[]{iLegacyClassTransformer.getName()});
        }
        return arrby;
    }

    private String unmapClassName(String string) {
        if (this.nameTransformer == null) {
            this.findNameTransformer();
        }
        if (this.nameTransformer != null) {
            return this.nameTransformer.unmapClassName(string);
        }
        return string;
    }

    private void findNameTransformer() {
        List list = Launch.classLoader.getTransformers();
        for (IClassTransformer iClassTransformer : list) {
            if (!(iClassTransformer instanceof IClassNameTransformer)) continue;
            logger.debug("Found name transformer: {}", new Object[]{iClassTransformer.getClass().getName()});
            this.nameTransformer = (IClassNameTransformer)iClassTransformer;
        }
    }

    @Override
    public ClassNode getClassNode(String string) throws ClassNotFoundException, IOException {
        return this.getClassNode(this.getClassBytes(string, true), 0);
    }

    private ClassNode getClassNode(byte[] arrby, int n2) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(arrby);
        classReader.accept(classNode, n2);
        return classNode;
    }

    @Override
    public final String getSideName() {
        for (ITweaker iTweaker : (List)GlobalProperties.get(BLACKBOARD_KEY_TWEAKS)) {
            if (iTweaker.getClass().getName().endsWith(".common.launcher.FMLServerTweaker")) {
                return "SERVER";
            }
            if (!iTweaker.getClass().getName().endsWith(".common.launcher.FMLTweaker")) continue;
            return "CLIENT";
        }
        Object object = this.getSideName("net.minecraftforge.fml.relauncher.FMLLaunchHandler", "side");
        if (object != null) {
            return object;
        }
        object = this.getSideName("cpw.mods.fml.relauncher.FMLLaunchHandler", "side");
        if (object != null) {
            return object;
        }
        object = this.getSideName("com.mumfrey.liteloader.launch.LiteLoaderTweaker", "getEnvironmentType");
        if (object != null) {
            return object;
        }
        return "UNKNOWN";
    }

    private String getSideName(String string, String string2) {
        try {
            Class<?> class_ = Class.forName(string, false, (ClassLoader)Launch.classLoader);
            Method method = class_.getDeclaredMethod(string2, new Class[0]);
            return ((Enum)method.invoke(null, new Object[0])).name();
        }
        catch (Exception exception) {
            return null;
        }
    }

    private static int findInStackTrace(String string, String string2) {
        StackTraceElement[] arrstackTraceElement;
        Thread thread2 = Thread.currentThread();
        if (!"main".equals(thread2.getName())) {
            return 0;
        }
        for (StackTraceElement stackTraceElement : arrstackTraceElement = thread2.getStackTrace()) {
            if (!string.equals(stackTraceElement.getClassName()) || !string2.equals(stackTraceElement.getMethodName())) continue;
            return stackTraceElement.getLineNumber();
        }
        return 0;
    }
}

