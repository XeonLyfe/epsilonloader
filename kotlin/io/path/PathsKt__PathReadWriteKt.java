/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.io.path.ExperimentalPathApi;
import kotlin.io.path.PathsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a:\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010\u0015\u001a:\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010\u0018\u001a=\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u001c\u00a2\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\u0087\b\u00f8\u0001\u0000\u001a&\u0010 \u001a\u00020!*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010\"\u001a&\u0010#\u001a\u00020$*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010%\u001a\r\u0010&\u001a\u00020\u0004*\u00020\u0002H\u0087\b\u001a\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0016\u0010)\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a0\u0010*\u001a\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010,\u001a?\u0010-\u001a\u0002H.\"\u0004\b\u0000\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b\u0012\u0004\u0012\u0002H.0\u001bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100\u001a.\u00101\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u00104\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u00105\u001a7\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007\u00a2\u0006\u0002\u00107\u001a0\u00108\u001a\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b\u00a2\u0006\u0002\u0010:\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006;"}, d2={"appendBytes", "", "Ljava/nio/file/Path;", "array", "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", "T", "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"}, xs="kotlin/io/path/PathsKt")
class PathsKt__PathReadWriteKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final InputStreamReader reader(Path path, Charset charset, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        return new InputStreamReader(Files.newInputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset);
    }

    static /* synthetic */ InputStreamReader reader$default(Path path, Charset charset, OpenOption[] arropenOption, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        return new InputStreamReader(Files.newInputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final BufferedReader bufferedReader(Path path, Charset charset, int n2, OpenOption ... arropenOption) throws IOException {
        int n3 = 0;
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset), n2);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(Path path, Charset charset, int n2, OpenOption[] arropenOption, int n3, Object object) throws IOException {
        if ((n3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return new BufferedReader(new InputStreamReader(Files.newInputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset), n2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final OutputStreamWriter writer(Path path, Charset charset, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        return new OutputStreamWriter(Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(Path path, Charset charset, OpenOption[] arropenOption, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        return new OutputStreamWriter(Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final BufferedWriter bufferedWriter(Path path, Charset charset, int n2, OpenOption ... arropenOption) throws IOException {
        int n3 = 0;
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset), n2);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(Path path, Charset charset, int n2, OpenOption[] arropenOption, int n3, Object object) throws IOException {
        if ((n3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length)), charset), n2);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final byte[] readBytes(Path path) throws IOException {
        int n2 = 0;
        byte[] arrby = Files.readAllBytes(path);
        Intrinsics.checkNotNullExpressionValue(arrby, "Files.readAllBytes(this)");
        return arrby;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final void writeBytes(Path path, byte[] arrby, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        Files.write(path, arrby, Arrays.copyOf(arropenOption, arropenOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final void appendBytes(Path path, byte[] arrby) throws IOException {
        int n2 = 0;
        Files.write(path, arrby, StandardOpenOption.APPEND);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final String readText(@NotNull Path path, @NotNull Charset charset) throws IOException {
        Object object;
        Intrinsics.checkNotNullParameter(path, "$this$readText");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Object object2 = path;
        OpenOption[] arropenOption = new OpenOption[]{};
        boolean bl = false;
        object2 = new InputStreamReader(Files.newInputStream((Path)object2, Arrays.copyOf(arropenOption, arropenOption.length)), charset);
        boolean bl2 = false;
        bl = false;
        Throwable throwable = null;
        try {
            object = (InputStreamReader)object2;
            boolean bl3 = false;
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

    public static /* synthetic */ String readText$default(Path path, Charset charset, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return PathsKt.readText(path, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static final void writeText(@NotNull Path path, @NotNull CharSequence charSequence, @NotNull Charset charset, OpenOption ... arropenOption) throws IOException {
        Intrinsics.checkNotNullParameter(path, "$this$writeText");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(arropenOption, "options");
        OutputStream outputStream = Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(outputStream, "Files.newOutputStream(this, *options)");
        Closeable closeable = outputStream;
        boolean bl = false;
        closeable = new OutputStreamWriter((OutputStream)closeable, charset);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Writer writer = (OutputStreamWriter)closeable;
            boolean bl3 = false;
            writer = writer.append(charSequence);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    public static /* synthetic */ void writeText$default(Path path, CharSequence charSequence, Charset charset, OpenOption[] arropenOption, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.writeText(path, charSequence, charset, arropenOption);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static final void appendText(@NotNull Path path, @NotNull CharSequence charSequence, @NotNull Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(path, "$this$appendText");
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(outputStream, "Files.newOutputStream(th\u2026tandardOpenOption.APPEND)");
        Closeable closeable = outputStream;
        boolean bl = false;
        closeable = new OutputStreamWriter((OutputStream)closeable, charset);
        bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Writer writer = (OutputStreamWriter)closeable;
            boolean bl3 = false;
            writer = writer.append(charSequence);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    public static /* synthetic */ void appendText$default(Path path, CharSequence charSequence, Charset charset, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.appendText(path, charSequence, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final void forEachLine(Path path, Charset charset, Function1<? super String, Unit> function1) throws IOException {
        int n2 = 0;
        BufferedReader bufferedReader = Files.newBufferedReader(path, charset);
        Intrinsics.checkNotNullExpressionValue(bufferedReader, "Files.newBufferedReader(this, charset)");
        Reader reader = bufferedReader;
        boolean bl = false;
        Closeable closeable = reader;
        int n3 = 8192;
        boolean bl2 = false;
        closeable = closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n3);
        n3 = 0;
        bl2 = false;
        Throwable throwable = null;
        try {
            Object object = (BufferedReader)closeable;
            boolean bl3 = false;
            Sequence<String> sequence = TextStreamsKt.lineSequence((BufferedReader)object);
            boolean bl4 = false;
            Sequence<String> sequence2 = sequence;
            boolean bl5 = false;
            Iterator<String> iterator2 = sequence2.iterator();
            while (iterator2.hasNext()) {
                String string = iterator2.next();
                function1.invoke(string);
            }
            object = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(closeable, throwable);
                } else if (throwable == null) {
                    closeable.close();
                } else {
                    try {
                        closeable.close();
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
            CloseableKt.closeFinally(closeable, throwable);
        } else {
            closeable.close();
        }
        InlineMarker.finallyEnd(1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static /* synthetic */ void forEachLine$default(Path path, Charset charset, Function1 function1, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        BufferedReader bufferedReader = Files.newBufferedReader(path, charset);
        Intrinsics.checkNotNullExpressionValue(bufferedReader, "Files.newBufferedReader(this, charset)");
        object = bufferedReader;
        boolean bl = false;
        Object object2 = object;
        int n3 = 8192;
        boolean bl2 = false;
        object2 = object2 instanceof BufferedReader ? (BufferedReader)object2 : new BufferedReader((Reader)object2, n3);
        n3 = 0;
        bl2 = false;
        Throwable throwable = null;
        try {
            Object object3 = (BufferedReader)object2;
            boolean bl3 = false;
            Sequence<String> sequence = TextStreamsKt.lineSequence((BufferedReader)object3);
            boolean bl4 = false;
            Sequence<String> sequence2 = sequence;
            boolean bl5 = false;
            Iterator<String> iterator2 = sequence2.iterator();
            while (iterator2.hasNext()) {
                String string = iterator2.next();
                function1.invoke(string);
            }
            object3 = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally((Closeable)object2, throwable);
                } else if (throwable == null) {
                    object2.close();
                } else {
                    try {
                        object2.close();
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
            CloseableKt.closeFinally((Closeable)object2, throwable);
        } else {
            object2.close();
        }
        InlineMarker.finallyEnd(1);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final InputStream inputStream(Path path, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        InputStream inputStream = Files.newInputStream(path, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(inputStream, "Files.newInputStream(this, *options)");
        return inputStream;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final OutputStream outputStream(Path path, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        OutputStream outputStream = Files.newOutputStream(path, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(outputStream, "Files.newOutputStream(this, *options)");
        return outputStream;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final List<String> readLines(Path path, Charset charset) throws IOException {
        int n2 = 0;
        List<String> list = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(list, "Files.readAllLines(this, charset)");
        return list;
    }

    static /* synthetic */ List readLines$default(Path path, Charset charset, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        List<String> list = Files.readAllLines(path, charset);
        Intrinsics.checkNotNullExpressionValue(list, "Files.readAllLines(this, charset)");
        return list;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final <T> T useLines(Path path, Charset charset, Function1<? super Sequence<String>, ? extends T> function1) throws IOException {
        BufferedReader bufferedReader;
        int n2 = 0;
        Closeable closeable = Files.newBufferedReader(path, charset);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            bufferedReader = (BufferedReader)closeable;
            boolean bl3 = false;
            BufferedReader bufferedReader2 = bufferedReader;
            Intrinsics.checkNotNullExpressionValue(bufferedReader2, "it");
            bufferedReader = function1.invoke(TextStreamsKt.lineSequence(bufferedReader2));
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(closeable, throwable);
                } else if (closeable != null) {
                    if (throwable == null) {
                        closeable.close();
                    } else {
                        try {
                            closeable.close();
                        }
                        catch (Throwable throwable4) {
                            // empty catch block
                        }
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable3;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally(closeable, throwable);
        } else if (closeable != null) {
            closeable.close();
        }
        InlineMarker.finallyEnd(1);
        return (T)bufferedReader;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static /* synthetic */ Object useLines$default(Path path, Charset charset, Function1 function1, int n2, Object object) throws IOException {
        BufferedReader bufferedReader;
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = Files.newBufferedReader(path, charset);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            bufferedReader = (BufferedReader)object;
            boolean bl3 = false;
            BufferedReader bufferedReader2 = bufferedReader;
            Intrinsics.checkNotNullExpressionValue(bufferedReader2, "it");
            bufferedReader = function1.invoke(TextStreamsKt.lineSequence(bufferedReader2));
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
                } else if (object != null) {
                    if (throwable == null) {
                        object.close();
                    } else {
                        try {
                            object.close();
                        }
                        catch (Throwable throwable4) {
                            // empty catch block
                        }
                    }
                }
                InlineMarker.finallyEnd(1);
                throw throwable3;
            }
        }
        InlineMarker.finallyStart(1);
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
            CloseableKt.closeFinally((Closeable)object, throwable);
        } else if (object != null) {
            object.close();
        }
        InlineMarker.finallyEnd(1);
        return bufferedReader;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path writeLines(Path path, Iterable<? extends CharSequence> iterable, Charset charset, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        Path path2 = Files.write(path, iterable, charset, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines, charset, *options)");
        return path2;
    }

    static /* synthetic */ Path writeLines$default(Path path, Iterable iterable, Charset charset, OpenOption[] arropenOption, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        Path path2 = Files.write(path, (Iterable<? extends CharSequence>)iterable, charset, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines, charset, *options)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path writeLines(Path path, Sequence<? extends CharSequence> sequence, Charset charset, OpenOption ... arropenOption) throws IOException {
        int n2 = 0;
        Path path2 = Files.write(path, SequencesKt.asIterable(sequence), charset, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines.\u2026ble(), charset, *options)");
        return path2;
    }

    static /* synthetic */ Path writeLines$default(Path path, Sequence sequence, Charset charset, OpenOption[] arropenOption, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        Path path2 = Files.write(path, SequencesKt.asIterable(sequence), charset, Arrays.copyOf(arropenOption, arropenOption.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines.\u2026ble(), charset, *options)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path appendLines(Path path, Iterable<? extends CharSequence> iterable, Charset charset) throws IOException {
        int n2 = 0;
        Path path2 = Files.write(path, iterable, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines,\u2026tandardOpenOption.APPEND)");
        return path2;
    }

    static /* synthetic */ Path appendLines$default(Path path, Iterable iterable, Charset charset, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        Path path2 = Files.write(path, (Iterable<? extends CharSequence>)iterable, charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines,\u2026tandardOpenOption.APPEND)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path appendLines(Path path, Sequence<? extends CharSequence> sequence, Charset charset) throws IOException {
        int n2 = 0;
        Path path2 = Files.write(path, SequencesKt.asIterable(sequence), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines.\u2026tandardOpenOption.APPEND)");
        return path2;
    }

    static /* synthetic */ Path appendLines$default(Path path, Sequence sequence, Charset charset, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        Path path2 = Files.write(path, SequencesKt.asIterable(sequence), charset, StandardOpenOption.APPEND);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.write(this, lines.\u2026tandardOpenOption.APPEND)");
        return path2;
    }
}

