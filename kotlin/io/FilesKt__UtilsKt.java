/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FileAlreadyExistsException;
import kotlin.io.FilePathComponents;
import kotlin.io.FileSystemException;
import kotlin.io.FilesKt;
import kotlin.io.FilesKt__FileTreeWalkKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.io.NoSuchFileException;
import kotlin.io.OnErrorAction;
import kotlin.io.TerminateException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a*\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002\u00a2\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002\u00a2\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002\u00a2\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0004\u00a8\u0006+"}, d2={"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, xs="kotlin/io/FilesKt")
class FilesKt__UtilsKt
extends FilesKt__FileTreeWalkKt {
    @Deprecated(message="Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
    @NotNull
    public static final File createTempDir(@NotNull String string, @Nullable String string2, @Nullable File file) {
        Intrinsics.checkNotNullParameter(string, "prefix");
        File file2 = File.createTempFile(string, string2, file);
        file2.delete();
        if (file2.mkdir()) {
            File file3 = file2;
            Intrinsics.checkNotNullExpressionValue(file3, "dir");
            return file3;
        }
        throw (Throwable)new IOException("Unable to create temporary directory " + file2 + '.');
    }

    public static /* synthetic */ File createTempDir$default(String string, String string2, File file, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = "tmp";
        }
        if ((n2 & 2) != 0) {
            string2 = null;
        }
        if ((n2 & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempDir(string, string2, file);
    }

    @Deprecated(message="Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
    @NotNull
    public static final File createTempFile(@NotNull String string, @Nullable String string2, @Nullable File file) {
        Intrinsics.checkNotNullParameter(string, "prefix");
        File file2 = File.createTempFile(string, string2, file);
        Intrinsics.checkNotNullExpressionValue(file2, "File.createTempFile(prefix, suffix, directory)");
        return file2;
    }

    public static /* synthetic */ File createTempFile$default(String string, String string2, File file, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = "tmp";
        }
        if ((n2 & 2) != 0) {
            string2 = null;
        }
        if ((n2 & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempFile(string, string2, file);
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$extension");
        String string = file.getName();
        Intrinsics.checkNotNullExpressionValue(string, "name");
        return StringsKt.substringAfterLast(string, '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        String string;
        Intrinsics.checkNotNullParameter(file, "$this$invariantSeparatorsPath");
        if (File.separatorChar != '/') {
            String string2 = file.getPath();
            Intrinsics.checkNotNullExpressionValue(string2, "path");
            string = StringsKt.replace$default(string2, File.separatorChar, '/', false, 4, null);
        } else {
            String string3 = file.getPath();
            string = string3;
            Intrinsics.checkNotNullExpressionValue(string3, "path");
        }
        return string;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$nameWithoutExtension");
        String string = file.getName();
        Intrinsics.checkNotNullExpressionValue(string, "name");
        return StringsKt.substringBeforeLast$default(string, ".", null, 2, null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$toRelativeString");
        Intrinsics.checkNotNullParameter(file2, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (string == null) {
            throw (Throwable)new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + '.');
        }
        return string;
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$relativeTo");
        Intrinsics.checkNotNullParameter(file2, "base");
        return new File(FilesKt.toRelativeString(file, file2));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        File file3;
        Intrinsics.checkNotNullParameter(file, "$this$relativeToOrSelf");
        Intrinsics.checkNotNullParameter(file2, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (string != null) {
            String string2 = string;
            boolean bl = false;
            boolean bl2 = false;
            String string3 = string2;
            boolean bl3 = false;
            file3 = new File(string3);
        } else {
            file3 = file;
        }
        return file3;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        File file3;
        Intrinsics.checkNotNullParameter(file, "$this$relativeToOrNull");
        Intrinsics.checkNotNullParameter(file2, "base");
        String string = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (string != null) {
            String string2 = string;
            boolean bl = false;
            boolean bl2 = false;
            String string3 = string2;
            boolean bl3 = false;
            file3 = new File(string3);
        } else {
            file3 = null;
        }
        return file3;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File file, File file2) {
        int n2;
        FilePathComponents filePathComponents = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents(file));
        FilePathComponents filePathComponents2 = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents(file2));
        if (Intrinsics.areEqual(filePathComponents.getRoot(), filePathComponents2.getRoot()) ^ true) {
            return null;
        }
        int n3 = filePathComponents2.getSize();
        int n4 = filePathComponents.getSize();
        Serializable serializable = file;
        int n5 = 0;
        int n6 = 0;
        File file3 = serializable;
        boolean bl = false;
        int n7 = n4;
        int n8 = n3;
        boolean bl2 = false;
        int n9 = Math.min(n7, n8);
        for (n2 = 0; n2 < n9 && Intrinsics.areEqual(filePathComponents.getSegments().get(n2), filePathComponents2.getSegments().get(n2)); ++n2) {
        }
        int n10 = n2;
        serializable = new StringBuilder();
        n5 = n3 - 1;
        n6 = n10;
        if (n5 >= n6) {
            while (true) {
                if (Intrinsics.areEqual(filePathComponents2.getSegments().get(n5).getName(), "..")) {
                    return null;
                }
                ((StringBuilder)serializable).append("..");
                if (n5 != n10) {
                    ((StringBuilder)serializable).append(File.separatorChar);
                }
                if (n5 == n6) break;
                --n5;
            }
        }
        if (n10 < n4) {
            if (n10 < n3) {
                ((StringBuilder)serializable).append(File.separatorChar);
            }
            Iterable iterable = CollectionsKt.drop((Iterable)filePathComponents.getSegments(), n10);
            Appendable appendable = (Appendable)((Object)serializable);
            String string = File.separator;
            Intrinsics.checkNotNullExpressionValue(string, "File.separator");
            CollectionsKt.joinTo$default(iterable, appendable, string, null, null, 0, null, null, 124, null);
        }
        return ((StringBuilder)serializable).toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean bl, int n2) {
        Intrinsics.checkNotNullParameter(file, "$this$copyTo");
        Intrinsics.checkNotNullParameter(file2, "target");
        if (!file.exists()) {
            throw (Throwable)new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
        }
        if (file2.exists()) {
            if (!bl) {
                throw (Throwable)new FileAlreadyExistsException(file, file2, "The destination file already exists.");
            }
            if (!file2.delete()) {
                throw (Throwable)new FileAlreadyExistsException(file, file2, "Tried to overwrite the destination, but failed to delete it.");
            }
        }
        if (file.isDirectory()) {
            if (!file2.mkdirs()) {
                throw (Throwable)new FileSystemException(file, file2, "Failed to create target directory.");
            }
        } else {
            File file3 = file2.getParentFile();
            if (file3 != null) {
                file3.mkdirs();
            }
            Object object = file;
            boolean bl2 = false;
            object = new FileInputStream((File)object);
            bl2 = false;
            boolean bl3 = false;
            Throwable throwable = null;
            try {
                long l2;
                FileInputStream fileInputStream = (FileInputStream)object;
                boolean bl4 = false;
                Object object2 = file2;
                boolean bl5 = false;
                object2 = new FileOutputStream((File)object2);
                bl5 = false;
                boolean bl6 = false;
                Throwable throwable2 = null;
                try {
                    FileOutputStream fileOutputStream = (FileOutputStream)object2;
                    boolean bl7 = false;
                    l2 = ByteStreamsKt.copyTo(fileInputStream, fileOutputStream, n2);
                }
                catch (Throwable throwable3) {
                    throwable2 = throwable3;
                    throw throwable3;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)object2, throwable2);
                }
                long l3 = l2;
            }
            catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            }
            finally {
                CloseableKt.closeFinally((Closeable)object, throwable);
            }
        }
        return file2;
    }

    public static /* synthetic */ File copyTo$default(File file, File file2, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            bl = false;
        }
        if ((n3 & 4) != 0) {
            n2 = 8192;
        }
        return FilesKt.copyTo(file, file2, bl, n2);
    }

    public static final boolean copyRecursively(@NotNull File file, @NotNull File file2, boolean bl, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        Intrinsics.checkNotNullParameter(file, "$this$copyRecursively");
        Intrinsics.checkNotNullParameter(file2, "target");
        Intrinsics.checkNotNullParameter(function2, "onError");
        if (!file.exists()) {
            return function2.invoke(file, new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE;
        }
        try {
            Iterator<File> iterator2 = FilesKt.walkTopDown(file).onFail((Function2<? super File, ? super IOException, Unit>)new Function2<File, IOException, Unit>(function2){
                final /* synthetic */ Function2 $onError;

                public final void invoke(@NotNull File file, @NotNull IOException iOException) {
                    Intrinsics.checkNotNullParameter(file, "f");
                    Intrinsics.checkNotNullParameter(iOException, "e");
                    if ((OnErrorAction)((Object)this.$onError.invoke(file, iOException)) == OnErrorAction.TERMINATE) {
                        throw (Throwable)new TerminateException(file);
                    }
                }
                {
                    this.$onError = function2;
                    super(2);
                }
            }).iterator();
            while (iterator2.hasNext()) {
                File file3 = iterator2.next();
                if (!file3.exists()) {
                    if (function2.invoke(file3, new NoSuchFileException(file3, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE) continue;
                    return false;
                }
                String string = FilesKt.toRelativeString(file3, file);
                File file4 = new File(file2, string);
                if (!(!file4.exists() || file3.isDirectory() && file4.isDirectory())) {
                    boolean bl2;
                    boolean bl3 = !bl ? true : (file4.isDirectory() ? !FilesKt.deleteRecursively(file4) : (bl2 = !file4.delete()));
                    if (bl2) {
                        if (function2.invoke(file4, new FileAlreadyExistsException(file3, file4, "The destination file already exists.")) != OnErrorAction.TERMINATE) continue;
                        return false;
                    }
                }
                if (file3.isDirectory()) {
                    file4.mkdirs();
                    continue;
                }
                if (FilesKt.copyTo$default(file3, file4, bl, 0, 4, null).length() == file3.length() || function2.invoke(file3, new IOException("Source file wasn't copied completely, length of destination file differs.")) != OnErrorAction.TERMINATE) continue;
                return false;
            }
            return true;
        }
        catch (TerminateException terminateException) {
            return false;
        }
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean bl, Function2 function2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        if ((n2 & 4) != 0) {
            function2 = copyRecursively.1.INSTANCE;
        }
        return FilesKt.copyRecursively(file, file2, bl, function2);
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$deleteRecursively");
        Sequence sequence = FilesKt.walkBottomUp(file);
        boolean bl = true;
        boolean bl2 = false;
        boolean bl3 = bl;
        Iterator iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            Object t2 = iterator2.next();
            File file2 = (File)t2;
            boolean bl4 = bl3;
            boolean bl5 = false;
            bl3 = (file2.delete() || !file2.exists()) && bl4;
        }
        return bl3;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$startsWith");
        Intrinsics.checkNotNullParameter(file2, "other");
        FilePathComponents filePathComponents = FilesKt.toComponents(file);
        FilePathComponents filePathComponents2 = FilesKt.toComponents(file2);
        if (Intrinsics.areEqual(filePathComponents.getRoot(), filePathComponents2.getRoot()) ^ true) {
            return false;
        }
        return filePathComponents.getSize() < filePathComponents2.getSize() ? false : ((Object)filePathComponents.getSegments().subList(0, filePathComponents2.getSize())).equals(filePathComponents2.getSegments());
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String string) {
        Intrinsics.checkNotNullParameter(file, "$this$startsWith");
        Intrinsics.checkNotNullParameter(string, "other");
        return FilesKt.startsWith(file, new File(string));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$endsWith");
        Intrinsics.checkNotNullParameter(file2, "other");
        FilePathComponents filePathComponents = FilesKt.toComponents(file);
        FilePathComponents filePathComponents2 = FilesKt.toComponents(file2);
        if (filePathComponents2.isRooted()) {
            return Intrinsics.areEqual(file, file2);
        }
        int n2 = filePathComponents.getSize() - filePathComponents2.getSize();
        return n2 < 0 ? false : ((Object)filePathComponents.getSegments().subList(n2, filePathComponents.getSize())).equals(filePathComponents2.getSegments());
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String string) {
        Intrinsics.checkNotNullParameter(file, "$this$endsWith");
        Intrinsics.checkNotNullParameter(string, "other");
        return FilesKt.endsWith(file, new File(string));
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$normalize");
        FilePathComponents filePathComponents = FilesKt.toComponents(file);
        boolean bl = false;
        boolean bl2 = false;
        FilePathComponents filePathComponents2 = filePathComponents;
        boolean bl3 = false;
        File file2 = filePathComponents2.getRoot();
        Iterable iterable = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(filePathComponents2.getSegments());
        String string = File.separator;
        Intrinsics.checkNotNullExpressionValue(string, "File.separator");
        return FilesKt.resolve(file2, CollectionsKt.joinToString$default(iterable, string, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static final List<File> normalize$FilesKt__UtilsKt(List<? extends File> var0) {
        var1_1 = new ArrayList<E>(var0.size());
        var3_2 = var0.iterator();
        block8: while (var3_2.hasNext() != false) {
            var2_3 = var3_2.next();
            if (var2_3.getName() == null) ** GOTO lbl-1000
            tmp = -1;
            switch (var4_4.hashCode()) {
                case 1472: {
                    if (var4_4.equals("..")) {
                        tmp = 1;
                    }
                    break;
                }
                case 46: {
                    if (var4_4.equals(".")) {
                        tmp = 2;
                    }
                    break;
                }
                default: {
                }
            }
            switch (tmp) {
                case 1: {
                    if (!var1_1.isEmpty() && Intrinsics.areEqual(((File)CollectionsKt.last(var1_1)).getName(), "..") ^ true) {
                        var1_1.remove(var1_1.size() - 1);
                        break;
                    }
                    var1_1.add(var2_3);
                    break;
                }
                default: lbl-1000:
                // 2 sources

                {
                    var1_1.add(var2_3);
                    continue block8;
                }
                case 2: 
            }
        }
        return var1_1;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$resolve");
        Intrinsics.checkNotNullParameter(file2, "relative");
        if (FilesKt.isRooted(file2)) {
            return file2;
        }
        String string = file.toString();
        Intrinsics.checkNotNullExpressionValue(string, "this.toString()");
        String string2 = string;
        CharSequence charSequence = string2;
        boolean bl = false;
        return charSequence.length() == 0 || StringsKt.endsWith$default((CharSequence)string2, File.separatorChar, false, 2, null) ? new File(string2 + file2) : new File(string2 + File.separatorChar + file2);
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String string) {
        Intrinsics.checkNotNullParameter(file, "$this$resolve");
        Intrinsics.checkNotNullParameter(string, "relative");
        return FilesKt.resolve(file, new File(string));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkNotNullParameter(file, "$this$resolveSibling");
        Intrinsics.checkNotNullParameter(file2, "relative");
        FilePathComponents filePathComponents = FilesKt.toComponents(file);
        File file3 = filePathComponents.getSize() == 0 ? new File("..") : filePathComponents.subPath(0, filePathComponents.getSize() - 1);
        return FilesKt.resolve(FilesKt.resolve(filePathComponents.getRoot(), file3), file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String string) {
        Intrinsics.checkNotNullParameter(file, "$this$resolveSibling");
        Intrinsics.checkNotNullParameter(string, "relative");
        return FilesKt.resolveSibling(file, new File(string));
    }
}

