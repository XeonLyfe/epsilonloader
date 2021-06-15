/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilePathComponents;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002\u00a2\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2={"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, xs="kotlin/io/FilesKt")
class FilesKt__FilePathComponentsKt {
    private static final int getRootLength$FilesKt__FilePathComponentsKt(String string) {
        int n2 = StringsKt.indexOf$default((CharSequence)string, File.separatorChar, 0, false, 4, null);
        if (n2 == 0) {
            if (string.length() > 1 && string.charAt(1) == File.separatorChar && (n2 = StringsKt.indexOf$default((CharSequence)string, File.separatorChar, 2, false, 4, null)) >= 0) {
                if ((n2 = StringsKt.indexOf$default((CharSequence)string, File.separatorChar, n2 + 1, false, 4, null)) >= 0) {
                    return n2 + 1;
                }
                return string.length();
            }
            return 1;
        }
        if (n2 > 0 && string.charAt(n2 - 1) == ':') {
            return ++n2;
        }
        if (n2 == -1 && StringsKt.endsWith$default((CharSequence)string, ':', false, 2, null)) {
            return string.length();
        }
        return 0;
    }

    @NotNull
    public static final String getRootName(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$rootName");
        String string = file.getPath();
        Intrinsics.checkNotNullExpressionValue(string, "path");
        String string2 = string;
        int n2 = 0;
        String string3 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(string3, "path");
        int n3 = FilesKt__FilePathComponentsKt.getRootLength$FilesKt__FilePathComponentsKt(string3);
        boolean bl = false;
        String string4 = string2;
        if (string4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String string5 = string4.substring(n2, n3);
        Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string5;
    }

    @NotNull
    public static final File getRoot(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$root");
        return new File(FilesKt.getRootName(file));
    }

    public static final boolean isRooted(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "$this$isRooted");
        String string = file.getPath();
        Intrinsics.checkNotNullExpressionValue(string, "path");
        return FilesKt__FilePathComponentsKt.getRootLength$FilesKt__FilePathComponentsKt(string) > 0;
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File file) {
        List list;
        String string;
        Intrinsics.checkNotNullParameter(file, "$this$toComponents");
        String string2 = string = file.getPath();
        Intrinsics.checkNotNullExpressionValue(string2, "path");
        int n2 = FilesKt__FilePathComponentsKt.getRootLength$FilesKt__FilePathComponentsKt(string2);
        String string3 = string;
        int n3 = 0;
        boolean bl = false;
        String string4 = string3.substring(n3, n2);
        Intrinsics.checkNotNullExpressionValue(string4, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        String string5 = string4;
        Object object = string;
        bl = false;
        String string6 = ((String)object).substring(n2);
        Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.String).substring(startIndex)");
        string3 = string6;
        Object object2 = string3;
        boolean bl2 = false;
        if (object2.length() == 0) {
            boolean bl3 = false;
            list = CollectionsKt.emptyList();
        } else {
            object2 = StringsKt.split$default((CharSequence)string3, new char[]{File.separatorChar}, false, 0, 6, null);
            bl2 = false;
            Object object3 = object2;
            Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object2, 10));
            boolean bl4 = false;
            Iterator iterator2 = object3.iterator();
            while (iterator2.hasNext()) {
                Object t2 = iterator2.next();
                String string7 = (String)t2;
                Collection collection2 = collection;
                boolean bl5 = false;
                File file2 = new File(string7);
                collection2.add(file2);
            }
            list = (List)collection;
        }
        object = list;
        return new FilePathComponents(new File(string5), (List<? extends File>)object);
    }

    @NotNull
    public static final File subPath(@NotNull File file, int n2, int n3) {
        Intrinsics.checkNotNullParameter(file, "$this$subPath");
        return FilesKt.toComponents(file).subPath(n2, n3);
    }
}

