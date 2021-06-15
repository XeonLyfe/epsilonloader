/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer.debug;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.jar.Manifest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import org.jetbrains.java.decompiler.util.InterpreterUtil;
import org.spongepowered.asm.mixin.transformer.ext.IDecompiler;

public class RuntimeDecompiler
extends IFernflowerLogger
implements IDecompiler,
IResultSaver {
    private static final Level[] SEVERITY_LEVELS = new Level[]{Level.TRACE, Level.INFO, Level.WARN, Level.ERROR};
    private final Map<String, Object> options = ImmutableMap.builder().put("din", "0").put("rbr", "0").put("dgs", "1").put("asc", "1").put("den", "1").put("hdc", "1").put("ind", "    ").build();
    private final File outputPath;
    protected final Logger logger = LogManager.getLogger((String)"fernflower");

    public RuntimeDecompiler(File file) {
        this.outputPath = file;
        if (this.outputPath.exists()) {
            try {
                FileUtils.deleteDirectory((File)this.outputPath);
            }
            catch (IOException iOException) {
                this.logger.warn("Error cleaning output directory: {}", new Object[]{iOException.getMessage()});
            }
        }
    }

    @Override
    public void decompile(File file) {
        try {
            Fernflower fernflower = new Fernflower(new IBytecodeProvider(){
                private byte[] byteCode;

                @Override
                public byte[] getBytecode(String string, String string2) throws IOException {
                    if (this.byteCode == null) {
                        this.byteCode = InterpreterUtil.getBytes(new File(string));
                    }
                    return this.byteCode;
                }
            }, this, this.options, this);
            fernflower.getStructContext().addSpace(file, true);
            fernflower.decompileContext();
        }
        catch (Throwable throwable) {
            this.logger.warn("Decompilation error while processing {}", new Object[]{file.getName()});
        }
    }

    @Override
    public void saveFolder(String string) {
    }

    @Override
    public void saveClassFile(String string, String string2, String string3, String string4, int[] arrn) {
        File file = new File(this.outputPath, string2 + ".java");
        file.getParentFile().mkdirs();
        try {
            this.logger.info("Writing {}", new Object[]{file.getAbsolutePath()});
            Files.write(string4, file, Charsets.UTF_8);
        }
        catch (IOException iOException) {
            this.writeMessage("Cannot write source file " + file, iOException);
        }
    }

    @Override
    public void startReadingClass(String string) {
        this.logger.info("Decompiling {}", new Object[]{string});
    }

    @Override
    public void writeMessage(String string, IFernflowerLogger.Severity severity) {
        this.logger.log(SEVERITY_LEVELS[severity.ordinal()], string);
    }

    @Override
    public void writeMessage(String string, Throwable throwable) {
        this.logger.warn("{} {}: {}", new Object[]{string, throwable.getClass().getSimpleName(), throwable.getMessage()});
    }

    @Override
    public void writeMessage(String string, IFernflowerLogger.Severity severity, Throwable throwable) {
        this.logger.log(SEVERITY_LEVELS[severity.ordinal()], string, throwable);
    }

    @Override
    public void copyFile(String string, String string2, String string3) {
    }

    @Override
    public void createArchive(String string, String string2, Manifest manifest) {
    }

    @Override
    public void saveDirEntry(String string, String string2, String string3) {
    }

    @Override
    public void copyEntry(String string, String string2, String string3, String string4) {
    }

    @Override
    public void saveClassEntry(String string, String string2, String string3, String string4, String string5) {
    }

    @Override
    public void closeArchive(String string, String string2) {
    }
}

