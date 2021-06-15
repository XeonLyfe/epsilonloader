/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.refmap;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;

public final class RemappingReferenceMapper
implements IReferenceMapper {
    private static final String DEFAULT_RESOURCE_PATH_PROPERTY = "net.minecraftforge.gradle.GradleStart.srg.srg-mcp";
    private static final String DEFAULT_MAPPING_ENV = "searge";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static final Map<String, Map<String, String>> srgs = new HashMap<String, Map<String, String>>();
    private final IReferenceMapper refMap;
    private final Map<String, String> mappings;
    private final Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

    private RemappingReferenceMapper(MixinEnvironment mixinEnvironment, IReferenceMapper iReferenceMapper) {
        this.refMap = iReferenceMapper;
        this.refMap.setContext(RemappingReferenceMapper.getMappingEnv(mixinEnvironment));
        String string = RemappingReferenceMapper.getResource(mixinEnvironment);
        this.mappings = RemappingReferenceMapper.loadSrgs(string);
        logger.info("Remapping refMap {} using {}", new Object[]{iReferenceMapper.getResourceName(), string});
    }

    @Override
    public boolean isDefault() {
        return this.refMap.isDefault();
    }

    @Override
    public String getResourceName() {
        return this.refMap.getResourceName();
    }

    @Override
    public String getStatus() {
        return this.refMap.getStatus();
    }

    @Override
    public String getContext() {
        return this.refMap.getContext();
    }

    @Override
    public void setContext(String string) {
    }

    @Override
    public String remap(String string, String string2) {
        Map<String, String> map = this.getCache(string);
        String string3 = map.get(string2);
        if (string3 == null) {
            string3 = this.refMap.remap(string, string2);
            for (Map.Entry<String, String> entry : this.mappings.entrySet()) {
                string3 = string3.replace(entry.getKey(), entry.getValue());
            }
            map.put(string2, string3);
        }
        return string3;
    }

    private Map<String, String> getCache(String string) {
        Map<String, String> map = this.cache.get(string);
        if (map == null) {
            map = new HashMap<String, String>();
            this.cache.put(string, map);
        }
        return map;
    }

    @Override
    public String remapWithContext(String string, String string2, String string3) {
        return this.refMap.remapWithContext(string, string2, string3);
    }

    private static Map<String, String> loadSrgs(String string) {
        if (srgs.containsKey(string)) {
            return srgs.get(string);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        srgs.put(string, hashMap);
        File file = new File(string);
        if (!file.isFile()) {
            return hashMap;
        }
        try {
            Files.readLines(file, Charsets.UTF_8, new LineProcessor<Object>(){

                @Override
                public Object getResult() {
                    return null;
                }

                @Override
                public boolean processLine(String string) throws IOException {
                    if (Strings.isNullOrEmpty(string) || string.startsWith("#")) {
                        return true;
                    }
                    int n2 = 0;
                    int n3 = 0;
                    if ((string.startsWith("MD: ") ? 2 : (n3 = string.startsWith("FD: ") ? 1 : 0)) > 0) {
                        String[] arrstring = string.substring(4).split(" ", 4);
                        hashMap.put(arrstring[n2].substring(arrstring[n2].lastIndexOf(47) + 1), arrstring[n3].substring(arrstring[n3].lastIndexOf(47) + 1));
                    }
                    return true;
                }
            });
        }
        catch (IOException iOException) {
            logger.warn("Could not read input SRG file: {}", new Object[]{string});
            logger.catching((Throwable)iOException);
        }
        return hashMap;
    }

    public static IReferenceMapper of(MixinEnvironment mixinEnvironment, IReferenceMapper iReferenceMapper) {
        if (!iReferenceMapper.isDefault() && RemappingReferenceMapper.hasData(mixinEnvironment)) {
            return new RemappingReferenceMapper(mixinEnvironment, iReferenceMapper);
        }
        return iReferenceMapper;
    }

    private static boolean hasData(MixinEnvironment mixinEnvironment) {
        String string = RemappingReferenceMapper.getResource(mixinEnvironment);
        return string != null && new File(string).exists();
    }

    private static String getResource(MixinEnvironment mixinEnvironment) {
        String string = mixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_RESOURCE);
        return Strings.isNullOrEmpty(string) ? System.getProperty(DEFAULT_RESOURCE_PATH_PROPERTY) : string;
    }

    private static String getMappingEnv(MixinEnvironment mixinEnvironment) {
        String string = mixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_SOURCE_ENV);
        return Strings.isNullOrEmpty(string) ? DEFAULT_MAPPING_ENV : string;
    }
}

