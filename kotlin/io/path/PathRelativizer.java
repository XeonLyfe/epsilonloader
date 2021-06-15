/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.io.path;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lkotlin/io/path/PathRelativizer;", "", "()V", "emptyPath", "Ljava/nio/file/Path;", "kotlin.jvm.PlatformType", "parentPath", "tryRelativeTo", "path", "base", "kotlin-stdlib-jdk7"})
final class PathRelativizer {
    private static final Path emptyPath;
    private static final Path parentPath;
    @NotNull
    public static final PathRelativizer INSTANCE;

    @NotNull
    public final Path tryRelativeTo(@NotNull Path path, @NotNull Path path2) {
        Path path3;
        Path path4;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(path2, "base");
        Path path5 = path2.normalize();
        Path path6 = path.normalize();
        Path path7 = path5.relativize(path6);
        Path path8 = path5;
        Intrinsics.checkNotNullExpressionValue(path8, "bn");
        int n2 = path8.getNameCount();
        Path path9 = path6;
        Intrinsics.checkNotNullExpressionValue(path9, "pn");
        int n3 = path9.getNameCount();
        boolean bl = false;
        int n4 = Math.min(n2, n3);
        for (int i2 = 0; i2 < n4 && !(Intrinsics.areEqual(path5.getName(i2), parentPath) ^ true); ++i2) {
            if (!(Intrinsics.areEqual(path6.getName(i2), parentPath) ^ true)) continue;
            throw (Throwable)new IllegalArgumentException("Unable to compute relative path");
        }
        if (Intrinsics.areEqual(path6, path5) ^ true && Intrinsics.areEqual(path5, emptyPath)) {
            path4 = path6;
        } else {
            String string = ((Object)path7).toString();
            Path path10 = path7;
            Intrinsics.checkNotNullExpressionValue(path10, "rn");
            FileSystem fileSystem = path10.getFileSystem();
            Intrinsics.checkNotNullExpressionValue(fileSystem, "rn.fileSystem");
            String string2 = fileSystem.getSeparator();
            Intrinsics.checkNotNullExpressionValue(string2, "rn.fileSystem.separator");
            if (StringsKt.endsWith$default(string, string2, false, 2, null)) {
                FileSystem fileSystem2 = path7.getFileSystem();
                FileSystem fileSystem3 = path7.getFileSystem();
                Intrinsics.checkNotNullExpressionValue(fileSystem3, "rn.fileSystem");
                path4 = fileSystem2.getPath(StringsKt.dropLast(string, fileSystem3.getSeparator().length()), new String[0]);
            } else {
                path4 = path7;
            }
        }
        Path path11 = path3 = path4;
        Intrinsics.checkNotNullExpressionValue(path11, "r");
        return path11;
    }

    private PathRelativizer() {
    }

    static {
        PathRelativizer pathRelativizer;
        INSTANCE = pathRelativizer = new PathRelativizer();
        emptyPath = Paths.get("", new String[0]);
        parentPath = Paths.get("..", new String[0]);
    }
}

