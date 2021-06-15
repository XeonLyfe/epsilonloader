/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.TypeElement;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeReference;

public final class TargetMap
extends HashMap<TypeReference, Set<TypeReference>> {
    private static final long serialVersionUID = 1L;
    private final String sessionId;

    private TargetMap() {
        this(String.valueOf(System.currentTimeMillis()));
    }

    private TargetMap(String string) {
        this.sessionId = string;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void registerTargets(AnnotatedMixin annotatedMixin) {
        this.registerTargets(annotatedMixin.getTargets(), annotatedMixin.getHandle());
    }

    public void registerTargets(List<TypeHandle> list, TypeHandle typeHandle) {
        for (TypeHandle typeHandle2 : list) {
            this.addMixin(typeHandle2, typeHandle);
        }
    }

    public void addMixin(TypeHandle typeHandle, TypeHandle typeHandle2) {
        this.addMixin(typeHandle.getReference(), typeHandle2.getReference());
    }

    public void addMixin(String string, String string2) {
        this.addMixin(new TypeReference(string), new TypeReference(string2));
    }

    public void addMixin(TypeReference typeReference, TypeReference typeReference2) {
        Set<TypeReference> set = this.getMixinsFor(typeReference);
        set.add(typeReference2);
    }

    public Collection<TypeReference> getMixinsTargeting(TypeElement typeElement) {
        return this.getMixinsTargeting(new TypeHandle(typeElement));
    }

    public Collection<TypeReference> getMixinsTargeting(TypeHandle typeHandle) {
        return this.getMixinsTargeting(typeHandle.getReference());
    }

    public Collection<TypeReference> getMixinsTargeting(TypeReference typeReference) {
        return Collections.unmodifiableCollection(this.getMixinsFor(typeReference));
    }

    private Set<TypeReference> getMixinsFor(TypeReference typeReference) {
        HashSet hashSet = (HashSet)this.get(typeReference);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.put(typeReference, hashSet);
        }
        return hashSet;
    }

    public void readImports(File file) throws IOException {
        if (!file.isFile()) {
            return;
        }
        for (String string : Files.readLines(file, Charset.defaultCharset())) {
            String[] arrstring = string.split("\t");
            if (arrstring.length != 2) continue;
            this.addMixin(arrstring[1], arrstring[0]);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void write(boolean bl) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = TargetMap.getSessionFile(this.sessionId);
            if (bl) {
                file.deleteOnExit();
            }
            fileOutputStream = new FileOutputStream(file, true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static TargetMap read(File file) {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            TargetMap targetMap = (TargetMap)objectInputStream.readObject();
            return targetMap;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TargetMap create(String string) {
        TargetMap targetMap;
        File file;
        if (string != null && (file = TargetMap.getSessionFile(string)).exists() && (targetMap = TargetMap.read(file)) != null) {
            return targetMap;
        }
        return new TargetMap();
    }

    private static File getSessionFile(String string) {
        File file = new File(System.getProperty("java.io.tmpdir"));
        return new File(file, String.format("mixin-targetdb-%s.tmp", string));
    }
}

