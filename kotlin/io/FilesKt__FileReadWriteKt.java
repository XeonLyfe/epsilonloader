/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.ExposingBufferByteArrayOutputStream;
import kotlin.io.FilesKt;
import kotlin.io.FilesKt__FilePathComponentsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001aB\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010,\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u0082\u0002\u000f\n\u0006\b\u0011(+0\u0001\n\u0005\b\u009920\u0001\u00a8\u00061"}, d2={"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, xs="kotlin/io/FilesKt")
class FilesKt__FileReadWriteKt
extends FilesKt__FilePathComponentsKt {
    @InlineOnly
    private static final InputStreamReader reader(File file, Charset charset) {
        int n2 = 0;
        Object object = file;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        return new InputStreamReader((InputStream)object, charset);
    }

    static /* synthetic */ InputStreamReader reader$default(File file, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = file;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        return new InputStreamReader((InputStream)object, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(File file, Charset charset, int n2) {
        int n3 = 0;
        Object object = file;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileInputStream((File)object2);
        bl2 = false;
        object = new InputStreamReader((InputStream)object2, charset);
        bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, n2);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(File file, Charset charset, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        object = file;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileInputStream((File)object2);
        bl2 = false;
        object = new InputStreamReader((InputStream)object2, charset);
        bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, n2);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(File file, Charset charset) {
        int n2 = 0;
        Object object = file;
        boolean bl = false;
        object = new FileOutputStream((File)object);
        bl = false;
        return new OutputStreamWriter((OutputStream)object, charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(File file, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = file;
        boolean bl = false;
        object = new FileOutputStream((File)object);
        bl = false;
        return new OutputStreamWriter((OutputStream)object, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(File file, Charset charset, int n2) {
        int n3 = 0;
        Object object = file;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileOutputStream((File)object2);
        bl2 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n2);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(File file, Charset charset, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        object = file;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        object2 = new FileOutputStream((File)object2);
        bl2 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n2);
    }

    @InlineOnly
    private static final PrintWriter printWriter(File file, Charset charset) {
        int n2 = 0;
        File file2 = file;
        int n3 = 8192;
        boolean bl = false;
        Object object = file2;
        boolean bl2 = false;
        Object object2 = object;
        boolean bl3 = false;
        object2 = new FileOutputStream((File)object2);
        bl3 = false;
        object = new OutputStreamWriter((OutputStream)object2, charset);
        bl2 = false;
        return new PrintWriter(object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n3));
    }

    static /* synthetic */ PrintWriter printWriter$default(File file, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = file;
        int n3 = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileOutputStream((File)object3);
        bl3 = false;
        object2 = new OutputStreamWriter((OutputStream)object3, charset);
        bl2 = false;
        return new PrintWriter(object2 instanceof BufferedWriter ? (BufferedWriter)object2 : new BufferedWriter((Writer)object2, n3));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final byte[] readBytes(@NotNull File file) {
        Object object;
        Intrinsics.checkNotNullParameter(file, "$this$readBytes");
        Object object2 = file;
        boolean bl = false;
        object2 = new FileInputStream((File)object2);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            byte[] arrby;
            object = (FileInputStream)object2;
            boolean bl3 = false;
            int n2 = 0;
            long l2 = file.length();
            int n3 = 0;
            boolean bl4 = false;
            long l3 = l2;
            boolean bl5 = false;
            if (l3 > (long)Integer.MAX_VALUE) {
                throw (Throwable)new OutOfMemoryError("File " + file + " is too big (" + l3 + " bytes) to fit in memory.");
            }
            int n4 = (int)l2;
            byte[] arrby2 = new byte[n4];
            while (n4 > 0 && (n3 = ((FileInputStream)object).read(arrby2, n2, n4)) >= 0) {
                n4 -= n3;
                n2 += n3;
            }
            if (n4 > 0) {
                byte[] arrby3 = arrby2;
                bl4 = false;
                byte[] arrby4 = Arrays.copyOf(arrby3, n2);
                arrby = arrby4;
                Intrinsics.checkNotNullExpressionValue(arrby4, "java.util.Arrays.copyOf(this, newSize)");
            } else {
                n3 = ((FileInputStream)object).read();
                if (n3 == -1) {
                    arrby = arrby2;
                } else {
                    ExposingBufferByteArrayOutputStream exposingBufferByteArrayOutputStream = new ExposingBufferByteArrayOutputStream(8193);
                    exposingBufferByteArrayOutputStream.write(n3);
                    ByteStreamsKt.copyTo$default((InputStream)object, exposingBufferByteArrayOutputStream, 0, 2, null);
                    int n5 = arrby2.length + exposingBufferByteArrayOutputStream.size();
                    if (n5 < 0) {
                        throw (Throwable)new OutOfMemoryError("File " + file + " is too big to fit in memory.");
                    }
                    byte[] arrby5 = exposingBufferByteArrayOutputStream.getBuffer();
                    byte[] arrby6 = arrby2;
                    bl5 = false;
                    byte[] arrby7 = Arrays.copyOf(arrby6, n5);
                    Intrinsics.checkNotNullExpressionValue(arrby7, "java.util.Arrays.copyOf(this, newSize)");
                    arrby = ArraysKt.copyInto(arrby5, arrby7, arrby2.length, 0, exposingBufferByteArrayOutputStream.size());
                }
            }
            object = arrby;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally((Closeable)object2, throwable);
        }
        return object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void writeBytes(@NotNull File file, @NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(file, "$this$writeBytes");
        Intrinsics.checkNotNullParameter(arrby, "array");
        Closeable closeable = new FileOutputStream(file);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Object object = (FileOutputStream)closeable;
            boolean bl3 = false;
            ((FileOutputStream)object).write(arrby);
            object = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void appendBytes(@NotNull File file, @NotNull byte[] arrby) {
        Intrinsics.checkNotNullParameter(file, "$this$appendBytes");
        Intrinsics.checkNotNullParameter(arrby, "array");
        Closeable closeable = new FileOutputStream(file, true);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Object object = (FileOutputStream)closeable;
            boolean bl3 = false;
            ((FileOutputStream)object).write(arrby);
            object = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final String readText(@NotNull File file, @NotNull Charset charset) {
        Object object;
        Intrinsics.checkNotNullParameter(file, "$this$readText");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Object object2 = file;
        boolean bl = false;
        Object object3 = object2;
        boolean bl2 = false;
        object3 = new FileInputStream((File)object3);
        bl2 = false;
        object2 = new InputStreamReader((InputStream)object3, charset);
        bl = false;
        boolean bl3 = false;
        Throwable throwable = null;
        try {
            object = (InputStreamReader)object2;
            boolean bl4 = false;
            object = TextStreamsKt.readText((Reader)object);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally((Closeable)object2, throwable);
        }
        return object;
    }

    public static /* synthetic */ String readText$default(File file, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readText(file, charset);
    }

    public static final void writeText(@NotNull File file, @NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(file, "$this$writeText");
        Intrinsics.checkNotNullParameter(string, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String string2 = string;
        boolean bl = false;
        byte[] arrby = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        FilesKt.writeBytes(file, arrby);
    }

    public static /* synthetic */ void writeText$default(File file, String string, Charset charset, int n2, Object object) {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.writeText(file, string, charset);
    }

    public static final void appendText(@NotNull File file, @NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(file, "$this$appendText");
        Intrinsics.checkNotNullParameter(string, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String string2 = string;
        boolean bl = false;
        byte[] arrby = string2.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        FilesKt.appendBytes(file, arrby);
    }

    public static /* synthetic */ void appendText$default(File file, String string, Charset charset, int n2, Object object) {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.appendText(file, string, charset);
    }

    public static final void forEachBlock(@NotNull File file, @NotNull Function2<? super byte[], ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(file, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(function2, "action");
        FilesKt.forEachBlock(file, 4096, function2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void forEachBlock(@NotNull File file, int n2, @NotNull Function2<? super byte[], ? super Integer, Unit> function2) {
        Intrinsics.checkNotNullParameter(file, "$this$forEachBlock");
        Intrinsics.checkNotNullParameter(function2, "action");
        byte[] arrby = new byte[RangesKt.coerceAtLeast(n2, 512)];
        Object object = file;
        boolean bl = false;
        object = new FileInputStream((File)object);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            int n3;
            Object object2 = (FileInputStream)object;
            boolean bl3 = false;
            while ((n3 = ((FileInputStream)object2).read(arrby)) > 0) {
                function2.invoke((byte[])arrby, (Integer)n3);
            }
            object2 = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally((Closeable)object, throwable);
        }
    }

    public static final void forEachLine(@NotNull File file, @NotNull Charset charset, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(file, "$this$forEachLine");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(function1, "action");
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(file), charset)), function1);
    }

    public static /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        FilesKt.forEachLine(file, charset, function1);
    }

    @InlineOnly
    private static final FileInputStream inputStream(File file) {
        int n2 = 0;
        return new FileInputStream(file);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(File file) {
        int n2 = 0;
        return new FileOutputStream(file);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File file, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(file, "$this$readLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ArrayList arrayList = new ArrayList();
        FilesKt.forEachLine(file, charset, (Function1<? super String, Unit>)new Function1<String, Unit>(arrayList){
            final /* synthetic */ ArrayList $result;

            public final void invoke(@NotNull String string) {
                Intrinsics.checkNotNullParameter(string, "it");
                this.$result.add(string);
            }
            {
                this.$result = arrayList;
                super(1);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ List readLines$default(File file, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return FilesKt.readLines(file, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <T> T useLines(@NotNull File file, @NotNull Charset charset, @NotNull Function1<? super Sequence<String>, ? extends T> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(file, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(function1, "block");
        Object object = file;
        int n3 = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileInputStream((File)object3);
        bl3 = false;
        object2 = new InputStreamReader((InputStream)object3, charset);
        bl2 = false;
        object = object2 instanceof BufferedReader ? (BufferedReader)object2 : new BufferedReader((Reader)object2, n3);
        n3 = 0;
        bl = false;
        Throwable throwable = null;
        try {
            object2 = (BufferedReader)object;
            bl2 = false;
            object2 = function1.invoke(TextStreamsKt.lineSequence((BufferedReader)object2));
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally((Closeable)object, throwable);
                } else if (throwable == null) {
                    object.close();
                } else {
                    try {
                        object.close();
                    }
                    catch (Throwable throwable4) {
                        // empty catch block
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable3;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally((Closeable)object, throwable);
        } else {
            object.close();
        }
        InlineMarker.finallyEnd(1);
        return (T)object2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static /* synthetic */ Object useLines$default(File file, Charset charset, Function1 function1, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        Intrinsics.checkNotNullParameter(file, "$this$useLines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(function1, "block");
        object = file;
        int n3 = 8192;
        boolean bl = false;
        Object object2 = object;
        boolean bl2 = false;
        Object object3 = object2;
        boolean bl3 = false;
        object3 = new FileInputStream((File)object3);
        bl3 = false;
        object2 = new InputStreamReader((InputStream)object3, charset);
        bl2 = false;
        object = object2 instanceof BufferedReader ? (BufferedReader)object2 : new BufferedReader((Reader)object2, n3);
        n3 = 0;
        bl = false;
        Throwable throwable = null;
        try {
            object2 = (BufferedReader)object;
            bl2 = false;
            object2 = function1.invoke(TextStreamsKt.lineSequence((BufferedReader)object2));
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally((Closeable)object, throwable);
                } else if (throwable == null) {
                    object.close();
                } else {
                    try {
                        object.close();
                    }
                    catch (Throwable throwable4) {
                        // empty catch block
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable3;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally((Closeable)object, throwable);
        } else {
            object.close();
        }
        InlineMarker.finallyEnd(1);
        return object2;
    }
}

