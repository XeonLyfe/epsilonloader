/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=1, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2={"Lkotlin/io/LineReader;", "", "()V", "BUFFER_SIZE", "", "byteBuf", "Ljava/nio/ByteBuffer;", "bytes", "", "charBuf", "Ljava/nio/CharBuffer;", "chars", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "compactBytes", "decode", "endOfInput", "decodeEndOfInput", "nBytes", "nChars", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "resetAll", "", "trimStringBuilder", "updateCharset", "kotlin-stdlib"})
public final class LineReader {
    private static final int BUFFER_SIZE = 32;
    private static CharsetDecoder decoder;
    private static boolean directEOL;
    private static final byte[] bytes;
    private static final char[] chars;
    private static final ByteBuffer byteBuf;
    private static final CharBuffer charBuf;
    private static final StringBuilder sb;
    @NotNull
    public static final LineReader INSTANCE;

    @Nullable
    public final synchronized String readLine(@NotNull InputStream inputStream, @NotNull Charset charset) {
        boolean bl;
        block12: {
            block11: {
                Intrinsics.checkNotNullParameter(inputStream, "inputStream");
                Intrinsics.checkNotNullParameter(charset, "charset");
                if (decoder == null) break block11;
                CharsetDecoder charsetDecoder = decoder;
                if (charsetDecoder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("decoder");
                }
                if (!(Intrinsics.areEqual(charsetDecoder.charset(), charset) ^ true)) break block12;
            }
            this.updateCharset(charset);
        }
        int n2 = 0;
        int n3 = 0;
        while (true) {
            int n4;
            if ((n4 = inputStream.read()) == -1) {
                CharSequence charSequence = sb;
                bl = false;
                if (charSequence.length() == 0 && n2 == 0 && n3 == 0) {
                    return null;
                }
                n3 = this.decodeEndOfInput(n2, n3);
                break;
            }
            LineReader.bytes[n2++] = (byte)n4;
            int n5 = 10;
            bl = false;
            if (n4 != n5 && n2 != 32 && directEOL) continue;
            byteBuf.limit(n2);
            charBuf.position(n3);
            n3 = this.decode(false);
            if (n3 > 0 && chars[n3 - 1] == '\n') {
                byteBuf.position(0);
                break;
            }
            n2 = this.compactBytes();
        }
        if (n3 > 0 && chars[n3 - 1] == '\n' && --n3 > 0 && chars[n3 - 1] == '\r') {
            --n3;
        }
        Object object = sb;
        int n6 = 0;
        if (object.length() == 0) {
            object = chars;
            n6 = 0;
            bl = false;
            return new String((char[])object, n6, n3);
        }
        sb.append(chars, 0, n3);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        object = string;
        if (sb.length() > 32) {
            this.trimStringBuilder();
        }
        sb.setLength(0);
        return object;
    }

    private final int decode(boolean bl) {
        while (true) {
            CoderResult coderResult;
            CharsetDecoder charsetDecoder = decoder;
            if (charsetDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
            }
            Intrinsics.checkNotNullExpressionValue(charsetDecoder.decode(byteBuf, charBuf, bl), "decoder.decode(byteBuf, charBuf, endOfInput)");
            if (coderResult.isError()) {
                this.resetAll();
                coderResult.throwException();
            }
            int n2 = charBuf.position();
            if (!coderResult.isOverflow()) {
                return n2;
            }
            sb.append(chars, 0, n2 - 1);
            charBuf.position(0);
            charBuf.limit(32);
            charBuf.put(chars[n2 - 1]);
        }
    }

    private final int compactBytes() {
        ByteBuffer byteBuffer = byteBuf;
        boolean bl = false;
        boolean bl2 = false;
        ByteBuffer byteBuffer2 = byteBuffer;
        boolean bl3 = false;
        byteBuffer2.compact();
        int n2 = byteBuffer2.position();
        boolean bl4 = false;
        boolean bl5 = false;
        int n3 = n2;
        boolean bl6 = false;
        byteBuffer2.position(0);
        return n2;
    }

    private final int decodeEndOfInput(int n2, int n3) {
        byteBuf.limit(n2);
        charBuf.position(n3);
        int n4 = this.decode(true);
        boolean bl = false;
        boolean bl2 = false;
        int n5 = n4;
        boolean bl3 = false;
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        return n4;
    }

    private final void updateCharset(Charset charset) {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(charsetDecoder, "charset.newDecoder()");
        decoder = charsetDecoder;
        byteBuf.clear();
        charBuf.clear();
        int n2 = 10;
        boolean bl = false;
        byteBuf.put((byte)n2);
        byteBuf.flip();
        CharsetDecoder charsetDecoder2 = decoder;
        if (charsetDecoder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder2.decode(byteBuf, charBuf, false);
        directEOL = charBuf.position() == 1 && charBuf.get(0) == '\n';
        this.resetAll();
    }

    private final void resetAll() {
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        sb.setLength(0);
    }

    private final void trimStringBuilder() {
        sb.setLength(32);
        sb.trimToSize();
    }

    private LineReader() {
    }

    static {
        LineReader lineReader;
        INSTANCE = lineReader = new LineReader();
        bytes = new byte[32];
        chars = new char[32];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "ByteBuffer.wrap(bytes)");
        byteBuf = byteBuffer;
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        Intrinsics.checkNotNullExpressionValue(charBuffer, "CharBuffer.wrap(chars)");
        charBuf = charBuffer;
        sb = new StringBuilder();
    }

    public static final /* synthetic */ CharsetDecoder access$getDecoder$p(LineReader lineReader) {
        LineReader lineReader2 = lineReader;
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
        }
        return charsetDecoder;
    }

    public static final /* synthetic */ void access$setDecoder$p(LineReader lineReader, CharsetDecoder charsetDecoder) {
        LineReader lineReader2 = lineReader;
        decoder = charsetDecoder;
    }
}

