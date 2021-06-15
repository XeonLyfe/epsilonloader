/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonParseException
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.refmap;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;

public final class ReferenceMapper
implements IReferenceMapper,
Serializable {
    private static final long serialVersionUID = 2L;
    public static final String DEFAULT_RESOURCE = "mixin.refmap.json";
    public static final ReferenceMapper DEFAULT_MAPPER = new ReferenceMapper(true, "invalid");
    private final Map<String, Map<String, String>> mappings = Maps.newHashMap();
    private final Map<String, Map<String, Map<String, String>>> data = Maps.newHashMap();
    private final transient boolean readOnly;
    private transient String context = null;
    private transient String resource;

    public ReferenceMapper() {
        this(false, DEFAULT_RESOURCE);
    }

    private ReferenceMapper(boolean bl, String string) {
        this.readOnly = bl;
        this.resource = string;
    }

    @Override
    public boolean isDefault() {
        return this.readOnly;
    }

    private void setResourceName(String string) {
        if (!this.readOnly) {
            this.resource = string != null ? string : "<unknown resource>";
        }
    }

    @Override
    public String getResourceName() {
        return this.resource;
    }

    @Override
    public String getStatus() {
        return this.isDefault() ? "No refMap loaded." : "Using refmap " + this.getResourceName();
    }

    @Override
    public String getContext() {
        return this.context;
    }

    @Override
    public void setContext(String string) {
        this.context = string;
    }

    @Override
    public String remap(String string, String string2) {
        return this.remapWithContext(this.context, string, string2);
    }

    @Override
    public String remapWithContext(String string, String string2, String string3) {
        Map<String, Map<String, String>> map = this.mappings;
        if (string != null && (map = this.data.get(string)) == null) {
            map = this.mappings;
        }
        return this.remap(map, string2, string3);
    }

    private String remap(Map<String, Map<String, String>> map, String string, String string2) {
        Object object;
        Object object2;
        if (string == null) {
            object2 = map.values().iterator();
            while (object2.hasNext()) {
                object = (Map)object2.next();
                if (!object.containsKey(string2)) continue;
                return (String)object.get(string2);
            }
        }
        if ((object2 = map.get(string)) == null) {
            return string2;
        }
        object = (String)object2.get(string2);
        return object != null ? object : string2;
    }

    public String addMapping(String string, String string2, String string3, String string4) {
        Map<String, String> map;
        if (this.readOnly || string3 == null || string4 == null || string3.equals(string4)) {
            return null;
        }
        Map<String, Map<String, String>> map2 = this.mappings;
        if (string != null && (map2 = this.data.get(string)) == null) {
            map2 = Maps.newHashMap();
            this.data.put(string, map2);
        }
        if ((map = map2.get(string2)) == null) {
            map = new HashMap<String, String>();
            map2.put(string2, map);
        }
        return map.put(string3, string4);
    }

    public void write(Appendable appendable) {
        new GsonBuilder().setPrettyPrinting().create().toJson((Object)this, appendable);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static ReferenceMapper read(String string) {
        InputStreamReader inputStreamReader;
        block6: {
            ReferenceMapper referenceMapper;
            Logger logger = LogManager.getLogger((String)"mixin");
            inputStreamReader = null;
            try {
                IMixinService iMixinService = MixinService.getService();
                InputStream inputStream = iMixinService.getResourceAsStream(string);
                if (inputStream == null) break block6;
                inputStreamReader = new InputStreamReader(inputStream);
                ReferenceMapper referenceMapper2 = ReferenceMapper.readJson(inputStreamReader);
                referenceMapper2.setResourceName(string);
                referenceMapper = referenceMapper2;
            }
            catch (JsonParseException jsonParseException) {
                logger.error("Invalid REFMAP JSON in " + string + ": " + ((Object)((Object)jsonParseException)).getClass().getName() + " " + jsonParseException.getMessage());
                {
                    catch (Throwable throwable) {
                        IOUtils.closeQuietly(inputStreamReader);
                        throw throwable;
                    }
                }
                IOUtils.closeQuietly((Reader)inputStreamReader);
                return DEFAULT_MAPPER;
                catch (Exception exception) {
                    logger.error("Failed reading REFMAP JSON from " + string + ": " + exception.getClass().getName() + " " + exception.getMessage());
                    IOUtils.closeQuietly((Reader)inputStreamReader);
                    return DEFAULT_MAPPER;
                }
            }
            IOUtils.closeQuietly((Reader)inputStreamReader);
            return referenceMapper;
        }
        IOUtils.closeQuietly(inputStreamReader);
        return DEFAULT_MAPPER;
    }

    public static ReferenceMapper read(Reader reader, String string) {
        try {
            ReferenceMapper referenceMapper = ReferenceMapper.readJson(reader);
            referenceMapper.setResourceName(string);
            return referenceMapper;
        }
        catch (Exception exception) {
            return DEFAULT_MAPPER;
        }
    }

    private static ReferenceMapper readJson(Reader reader) {
        return (ReferenceMapper)new Gson().fromJson(reader, ReferenceMapper.class);
    }
}

