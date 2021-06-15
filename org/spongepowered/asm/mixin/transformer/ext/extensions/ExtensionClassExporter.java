/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer.ext.extensions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.IDecompiler;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.util.Constants;
import org.spongepowered.asm.util.perf.Profiler;

public class ExtensionClassExporter
implements IExtension {
    private static final String DECOMPILER_CLASS = "org.spongepowered.asm.mixin.transformer.debug.RuntimeDecompiler";
    private static final String EXPORT_CLASS_DIR = "class";
    private static final String EXPORT_JAVA_DIR = "java";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final File classExportDir = new File(Constants.DEBUG_OUTPUT_DIR, "class");
    private final IDecompiler decompiler;

    public ExtensionClassExporter(MixinEnvironment mixinEnvironment) {
        this.decompiler = this.initDecompiler(mixinEnvironment, new File(Constants.DEBUG_OUTPUT_DIR, EXPORT_JAVA_DIR));
        try {
            FileUtils.deleteDirectory((File)this.classExportDir);
        }
        catch (IOException iOException) {
            logger.warn("Error cleaning class output directory: {}", new Object[]{iOException.getMessage()});
        }
    }

    public boolean isDecompilerActive() {
        return this.decompiler != null;
    }

    private IDecompiler initDecompiler(MixinEnvironment mixinEnvironment, File file) {
        if (!mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE)) {
            return null;
        }
        try {
            boolean bl = mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE_THREADED);
            logger.info("Attempting to load Fernflower decompiler{}", new Object[]{bl ? " (Threaded mode)" : ""});
            String string = DECOMPILER_CLASS + (bl ? "Async" : "");
            Class<?> class_ = Class.forName(string);
            Constructor<?> constructor = class_.getDeclaredConstructor(File.class);
            IDecompiler iDecompiler = (IDecompiler)constructor.newInstance(file);
            logger.info("Fernflower decompiler was successfully initialised, exported classes will be decompiled{}", new Object[]{bl ? " in a separate thread" : ""});
            return iDecompiler;
        }
        catch (Throwable throwable) {
            logger.info("Fernflower could not be loaded, exported classes will not be decompiled. {}: {}", new Object[]{throwable.getClass().getSimpleName(), throwable.getMessage()});
            return null;
        }
    }

    private String prepareFilter(String string) {
        string = "^\\Q" + string.replace("**", "\u0081").replace("*", "\u0082").replace("?", "\u0083") + "\\E$";
        return string.replace("\u0081", "\\E.*\\Q").replace("\u0082", "\\E[^\\.]+\\Q").replace("\u0083", "\\E.\\Q").replace("\\Q\\E", "");
    }

    private boolean applyFilter(String string, String string2) {
        return Pattern.compile(this.prepareFilter(string), 2).matcher(string2).matches();
    }

    @Override
    public boolean checkActive(MixinEnvironment mixinEnvironment) {
        return true;
    }

    @Override
    public void preApply(ITargetClassContext iTargetClassContext) {
    }

    @Override
    public void postApply(ITargetClassContext iTargetClassContext) {
    }

    @Override
    public void export(MixinEnvironment mixinEnvironment, String string, boolean bl, byte[] arrby) {
        if (bl || mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_EXPORT)) {
            String string2 = mixinEnvironment.getOptionValue(MixinEnvironment.Option.DEBUG_EXPORT_FILTER);
            if (bl || string2 == null || this.applyFilter(string2, string)) {
                Profiler.Section section = MixinEnvironment.getProfiler().begin("debug.export");
                File file = this.dumpClass(string.replace('.', '/'), arrby);
                if (this.decompiler != null) {
                    this.decompiler.decompile(file);
                }
                section.end();
            }
        }
    }

    public File dumpClass(String string, byte[] arrby) {
        File file = new File(this.classExportDir, string + ".class");
        try {
            FileUtils.writeByteArrayToFile((File)file, (byte[])arrby);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return file;
    }
}

