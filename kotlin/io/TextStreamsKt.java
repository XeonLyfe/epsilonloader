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
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.LinesSequence;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\u0087\b\u001a8\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\u0086\b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0002\u0010\u001f\u0082\u0002\u000f\n\u0006\b\u0011(\u001e0\u0001\n\u0005\b\u009920\u0001\u00a8\u0006 "}, d2={"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name="TextStreamsKt")
public final class TextStreamsKt {
    @InlineOnly
    private static final BufferedReader buffered(Reader reader, int n2) {
        int n3 = 0;
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n2);
    }

    static /* synthetic */ BufferedReader buffered$default(Reader reader, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, n2);
    }

    @InlineOnly
    private static final BufferedWriter buffered(Writer writer, int n2) {
        int n3 = 0;
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer, n2);
    }

    static /* synthetic */ BufferedWriter buffered$default(Writer writer, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer, n2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void forEachLine(@NotNull Reader reader, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(reader, "$this$forEachLine");
        Intrinsics.checkNotNullParameter(function1, "action");
        Reader reader2 = reader;
        boolean bl = false;
        Closeable closeable = reader2;
        int n2 = 8192;
        boolean bl2 = false;
        closeable = closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n2);
        n2 = 0;
        bl2 = false;
        Throwable throwable = null;
        try {
            Object object = (BufferedReader)closeable;
            boolean bl3 = false;
            Sequence<String> sequence = TextStreamsKt.lineSequence((BufferedReader)object);
            boolean bl4 = false;
            Sequence<String> sequence2 = sequence;
            Function1<? super String, Unit> function12 = function1;
            boolean bl5 = false;
            Iterator<String> iterator2 = sequence2.iterator();
            while (iterator2.hasNext()) {
                String string = iterator2.next();
                function12.invoke(string);
            }
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

    @NotNull
    public static final List<String> readLines(@NotNull Reader reader) {
        Intrinsics.checkNotNullParameter(reader, "$this$readLines");
        boolean bl = false;
        ArrayList arrayList = new ArrayList();
        TextStreamsKt.forEachLine(reader, (Function1<? super String, Unit>)new Function1<String, Unit>(arrayList){
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final <T> T useLines(@NotNull Reader reader, @NotNull Function1<? super Sequence<String>, ? extends T> function1) {
        BufferedReader bufferedReader;
        int n2 = 0;
        Intrinsics.checkNotNullParameter(reader, "$this$useLines");
        Intrinsics.checkNotNullParameter(function1, "block");
        Closeable closeable = reader;
        int n3 = 8192;
        boolean bl = false;
        closeable = closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n3);
        n3 = 0;
        bl = false;
        Throwable throwable = null;
        try {
            bufferedReader = (BufferedReader)closeable;
            boolean bl2 = false;
            bufferedReader = function1.invoke(TextStreamsKt.lineSequence(bufferedReader));
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
        return (T)bufferedReader;
    }

    @InlineOnly
    private static final StringReader reader(String string) {
        int n2 = 0;
        return new StringReader(string);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader bufferedReader) {
        Intrinsics.checkNotNullParameter(bufferedReader, "$this$lineSequence");
        return SequencesKt.constrainOnce(new LinesSequence(bufferedReader));
    }

    @NotNull
    public static final String readText(@NotNull Reader reader) {
        Intrinsics.checkNotNullParameter(reader, "$this$readText");
        StringWriter stringWriter = new StringWriter();
        TextStreamsKt.copyTo$default(reader, stringWriter, 0, 2, null);
        String string = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(string, "buffer.toString()");
        return string;
    }

    public static final long copyTo(@NotNull Reader reader, @NotNull Writer writer, int n2) {
        Intrinsics.checkNotNullParameter(reader, "$this$copyTo");
        Intrinsics.checkNotNullParameter(writer, "out");
        long l2 = 0L;
        char[] arrc = new char[n2];
        int n3 = reader.read(arrc);
        while (n3 >= 0) {
            writer.write(arrc, 0, n3);
            l2 += (long)n3;
            n3 = reader.read(arrc);
        }
        return l2;
    }

    public static /* synthetic */ long copyTo$default(Reader reader, Writer writer, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        return TextStreamsKt.copyTo(reader, writer, n2);
    }

    @InlineOnly
    private static final String readText(URL uRL, Charset charset) {
        int n2 = 0;
        byte[] arrby = TextStreamsKt.readBytes(uRL);
        boolean bl = false;
        boolean bl2 = false;
        return new String(arrby, charset);
    }

    static /* synthetic */ String readText$default(URL uRL, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = TextStreamsKt.readBytes(uRL);
        boolean bl = false;
        boolean bl2 = false;
        return new String((byte[])object, charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public static final byte[] readBytes(@NotNull URL uRL) {
        Object object;
        Intrinsics.checkNotNullParameter(uRL, "$this$readBytes");
        Closeable closeable = uRL.openStream();
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            object = (InputStream)closeable;
            boolean bl3 = false;
            InputStream inputStream = object;
            Intrinsics.checkNotNullExpressionValue(inputStream, "it");
            object = ByteStreamsKt.readBytes(inputStream);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        return object;
    }
}

