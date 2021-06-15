/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.launch.platform;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

final class MainAttributes {
    private static final Map<URI, MainAttributes> instances = new HashMap<URI, MainAttributes>();
    protected final Attributes attributes;

    private MainAttributes() {
        this.attributes = new Attributes();
    }

    private MainAttributes(File file) {
        this.attributes = MainAttributes.getAttributes(file);
    }

    public final String get(String string) {
        if (this.attributes != null) {
            return this.attributes.getValue(string);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Attributes getAttributes(File file) {
        if (file == null) {
            return null;
        }
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
            Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                Attributes attributes = manifest.getMainAttributes();
                return attributes;
            }
        }
        catch (IOException iOException) {
        }
        finally {
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
            }
            catch (IOException iOException) {}
        }
        return new Attributes();
    }

    public static MainAttributes of(File file) {
        return MainAttributes.of(file.toURI());
    }

    public static MainAttributes of(URI uRI) {
        MainAttributes mainAttributes = instances.get(uRI);
        if (mainAttributes == null) {
            mainAttributes = new MainAttributes(new File(uRI));
            instances.put(uRI, mainAttributes);
        }
        return mainAttributes;
    }
}

