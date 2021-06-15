/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\u0087\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0086\u0002\u001a\f\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0007\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u00a8\u0006\u001f"}, d2={"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"})
@JvmName(name="ByteStreamsKt")
public final class ByteStreamsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull BufferedInputStream bufferedInputStream) {
        Intrinsics.checkNotNullParameter(bufferedInputStream, "$this$iterator");
        return new ByteIterator(bufferedInputStream){
            private int nextByte;
            private boolean nextPrepared;
            private boolean finished;
            final /* synthetic */ BufferedInputStream $this_iterator;

            public final int getNextByte() {
                return this.nextByte;
            }

            public final void setNextByte(int n2) {
                this.nextByte = n2;
            }

            public final boolean getNextPrepared() {
                return this.nextPrepared;
            }

            public final void setNextPrepared(boolean bl) {
                this.nextPrepared = bl;
            }

            public final boolean getFinished() {
                return this.finished;
            }

            public final void setFinished(boolean bl) {
                this.finished = bl;
            }

            private final void prepareNext() {
                if (!this.nextPrepared && !this.finished) {
                    this.nextByte = this.$this_iterator.read();
                    this.nextPrepared = true;
                    this.finished = this.nextByte == -1;
                }
            }

            public boolean hasNext() {
                this.prepareNext();
                return !this.finished;
            }

            public byte nextByte() {
                this.prepareNext();
                if (this.finished) {
                    throw (Throwable)new NoSuchElementException("Input stream is over.");
                }
                byte by = (byte)this.nextByte;
                this.nextPrepared = false;
                return by;
            }
            {
                this.$this_iterator = bufferedInputStream;
                this.nextByte = -1;
            }
        };
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(String string, Charset charset) {
        int n2 = 0;
        String string2 = string;
        boolean bl = false;
        String string3 = string2;
        if (string3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] arrby = string3.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(arrby);
    }

    static /* synthetic */ ByteArrayInputStream byteInputStream$default(String string, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = string;
        boolean bl = false;
        Object object2 = object;
        if (object2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] arrby = ((String)object2).getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(arrby, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(arrby);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] arrby) {
        int n2 = 0;
        return new ByteArrayInputStream(arrby);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(byte[] arrby, int n2, int n3) {
        int n4 = 0;
        return new ByteArrayInputStream(arrby, n2, n3);
    }

    @InlineOnly
    private static final BufferedInputStream buffered(InputStream inputStream, int n2) {
        int n3 = 0;
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, n2);
    }

    static /* synthetic */ BufferedInputStream buffered$default(InputStream inputStream, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, n2);
    }

    @InlineOnly
    private static final InputStreamReader reader(InputStream inputStream, Charset charset) {
        int n2 = 0;
        return new InputStreamReader(inputStream, charset);
    }

    static /* synthetic */ InputStreamReader reader$default(InputStream inputStream, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        return new InputStreamReader(inputStream, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(InputStream inputStream, Charset charset) {
        int n2 = 0;
        Closeable closeable = inputStream;
        int n3 = 0;
        closeable = new InputStreamReader((InputStream)closeable, charset);
        n3 = 8192;
        boolean bl = false;
        return closeable instanceof BufferedReader ? (BufferedReader)closeable : new BufferedReader((Reader)closeable, n3);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(InputStream inputStream, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = inputStream;
        int n3 = 0;
        object = new InputStreamReader((InputStream)object, charset);
        n3 = 8192;
        boolean bl = false;
        return object instanceof BufferedReader ? (BufferedReader)object : new BufferedReader((Reader)object, n3);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(OutputStream outputStream, int n2) {
        int n3 = 0;
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, n2);
    }

    static /* synthetic */ BufferedOutputStream buffered$default(OutputStream outputStream, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 8192;
        }
        n3 = 0;
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, n2);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(OutputStream outputStream, Charset charset) {
        int n2 = 0;
        return new OutputStreamWriter(outputStream, charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(OutputStream outputStream, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        return new OutputStreamWriter(outputStream, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(OutputStream outputStream, Charset charset) {
        int n2 = 0;
        Closeable closeable = outputStream;
        int n3 = 0;
        closeable = new OutputStreamWriter((OutputStream)closeable, charset);
        n3 = 8192;
        boolean bl = false;
        return closeable instanceof BufferedWriter ? (BufferedWriter)closeable : new BufferedWriter((Writer)closeable, n3);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream outputStream, Charset charset, int n2, Object object) {
        if ((n2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        n2 = 0;
        object = outputStream;
        int n3 = 0;
        object = new OutputStreamWriter((OutputStream)object, charset);
        n3 = 8192;
        boolean bl = false;
        return object instanceof BufferedWriter ? (BufferedWriter)object : new BufferedWriter((Writer)object, n3);
    }

    public static final long copyTo(@NotNull InputStream inputStream, @NotNull OutputStream outputStream, int n2) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$copyTo");
        Intrinsics.checkNotNullParameter(outputStream, "out");
        long l2 = 0L;
        byte[] arrby = new byte[n2];
        int n3 = inputStream.read(arrby);
        while (n3 >= 0) {
            outputStream.write(arrby, 0, n3);
            l2 += (long)n3;
            n3 = inputStream.read(arrby);
        }
        return l2;
    }

    public static /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 8192;
        }
        return ByteStreamsKt.copyTo(inputStream, outputStream, n2);
    }

    @Deprecated(message="Use readBytes() overload without estimatedSize parameter", replaceWith=@ReplaceWith(imports={}, expression="readBytes()"))
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.5")
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream inputStream, int n2) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$readBytes");
        int n3 = inputStream.available();
        boolean bl = false;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(n2, n3));
        ByteStreamsKt.copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] arrby = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arrby, "buffer.toByteArray()");
        return arrby;
    }

    public static /* synthetic */ byte[] readBytes$default(InputStream inputStream, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n2 = 8192;
        }
        return ByteStreamsKt.readBytes(inputStream, n2);
    }

    @SinceKotlin(version="1.3")
    @NotNull
    public static final byte[] readBytes(@NotNull InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "$this$readBytes");
        int n2 = 8192;
        int n3 = inputStream.available();
        boolean bl = false;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(n2, n3));
        ByteStreamsKt.copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] arrby = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(arrby, "buffer.toByteArray()");
        return arrby;
    }
}

