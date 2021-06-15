/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

public class NativeLibrary {
    private static final String DELETE_SUFFIX = ".delete";
    private static boolean deletedTemporary;
    private final boolean loaded;

    public NativeLibrary(String string, ClassLoader classLoader, boolean bl) {
        this.loaded = (bl ? NativeLibrary.loadLibraryFromJar(string, classLoader) : (int)((long)290461304 ^ (long)290461304)) ? 1 : 0;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    private static boolean loadLibraryFromJar(String string, ClassLoader classLoader) {
        URL uRL;
        string = NativeLibrary.decorateLibraryName(string);
        URL uRL2 = uRL = classLoader != null ? classLoader.getResource(string) : NativeLibrary.class.getResource("/" + string);
        if (uRL == null) {
            NativeLibrary.log("Library '" + string + "' not found", null);
            return ((int)-467635559L ^ 0xE4207299) != 0;
        }
        File file = null;
        try {
            Comparable<Path> comparable;
            if ("file".equals(uRL.getProtocol()) && ((File)(comparable = new File(uRL.getPath()))).isFile()) {
                System.load(((File)comparable).getCanonicalPath());
                return ((int)1719201189L ^ 0x6678EDA4) != 0;
            }
            comparable = NativeLibrary.createTempFile(string);
            file = comparable.toFile();
            try (InputStream inputStream = uRL.openStream();){
                CopyOption[] arrcopyOption = new CopyOption[(int)-643476784L ^ 0xD9A552D1];
                arrcopyOption[(int)136733670L ^ 136733670] = StandardCopyOption.REPLACE_EXISTING;
                Files.copy(inputStream, comparable, arrcopyOption);
            }
            System.load(file.getCanonicalPath());
            NativeLibrary.deleteOrMarkForDeletion(file);
            return (int)((long)-1531728428 ^ (long)-1531728427) != 0;
        }
        catch (Throwable throwable) {
            NativeLibrary.log(null, throwable);
            if (file != null) {
                NativeLibrary.deleteOrMarkForDeletion(file);
            }
            return (int)((long)-2065055907 ^ (long)-2065055907) != 0;
        }
    }

    private static String decorateLibraryName(String string) {
        if (SystemInfo.isWindows) {
            return string.concat(".dll");
        }
        String string2 = SystemInfo.isMacOS ? ".dylib" : ".so";
        int n2 = string.lastIndexOf((int)((long)-1633770814 ^ (long)-1633770771));
        return n2 >= 0 ? string.substring((int)-1296822649L ^ 0xB2B40E87, n2 + ((int)1578900612L ^ 0x5E1C1C85)) + "lib" + string.substring(n2 + (int)((long)-239418704 ^ (long)-239418703)) + string2 : "lib" + string + string2;
    }

    private static void log(String string, Throwable throwable) {
        LoggingFacade.INSTANCE.logSevere(string, throwable);
    }

    private static Path createTempFile(String string) throws IOException {
        int n2 = string.lastIndexOf((int)1638853852L ^ 0x61AEECF3);
        String string2 = n2 >= 0 ? string.substring(n2 + ((int)-536000592L ^ 0xE00D47B1)) : string;
        int n3 = string2.lastIndexOf((int)((long)1208224777 ^ (long)1208224798) << 1);
        String string3 = (n3 >= 0 ? string2.substring((int)((long)-1866933700 ^ (long)-1866933700), n3) : string2) + (char)((long)669183259 ^ (long)669183286);
        String string4 = n3 >= 0 ? string2.substring(n3) : "";
        Path path = NativeLibrary.getTempDir();
        if (path != null) {
            NativeLibrary.deleteTemporaryFiles(path);
            return Files.createTempFile(path, string3, string4, new FileAttribute[(int)-590212157L ^ 0xDCD213C3]);
        }
        return Files.createTempFile(string3, string4, new FileAttribute[(int)((long)1534237627 ^ (long)1534237627)]);
    }

    private static Path getTempDir() throws IOException {
        if (SystemInfo.isWindows) {
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + "/flatlaf.temp", new String[(int)((long)-723189309 ^ (long)-723189309)]);
            Files.createDirectories(path, new FileAttribute[(int)((long)1503604491 ^ (long)1503604491)]);
            return path;
        }
        return null;
    }

    private static void deleteTemporaryFiles(Path path) {
        if (deletedTemporary) {
            return;
        }
        deletedTemporary = (int)((long)765191926 ^ (long)765191927);
        File[] arrfile = path.toFile().listFiles((file, string) -> string.endsWith(DELETE_SUFFIX));
        if (arrfile == null) {
            return;
        }
        File[] arrfile2 = arrfile;
        int n2 = arrfile2.length;
        for (int i2 = (int)-1927563276L ^ 0x8D1BB7F4; i2 < n2; ++i2) {
            File file2 = arrfile2[i2];
            File file3 = new File(file2.getParent(), StringUtils.removeTrailing(file2.getName(), DELETE_SUFFIX));
            if (file3.exists() && !file3.delete()) continue;
            file2.delete();
        }
    }

    private static void deleteOrMarkForDeletion(File file) {
        if (file.delete()) {
            return;
        }
        try {
            File file2 = new File(file.getParent(), file.getName() + DELETE_SUFFIX);
            file2.createNewFile();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}

