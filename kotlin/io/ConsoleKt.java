/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.io.LineReader;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0013\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\t\u0010\r\u001a\u00020\u0001H\u0087\b\u001a\u0013\u0010\r\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a8\u0006\u0010"}, d2={"print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"})
@JvmName(name="ConsoleKt")
public final class ConsoleKt {
    @InlineOnly
    private static final void print(Object object) {
        int n2 = 0;
        System.out.print(object);
    }

    @InlineOnly
    private static final void print(int n2) {
        int n3 = 0;
        System.out.print(n2);
    }

    @InlineOnly
    private static final void print(long l2) {
        int n2 = 0;
        System.out.print(l2);
    }

    @InlineOnly
    private static final void print(byte by) {
        int n2 = 0;
        System.out.print((Object)by);
    }

    @InlineOnly
    private static final void print(short s2) {
        int n2 = 0;
        System.out.print((Object)s2);
    }

    @InlineOnly
    private static final void print(char c2) {
        int n2 = 0;
        System.out.print(c2);
    }

    @InlineOnly
    private static final void print(boolean bl) {
        int n2 = 0;
        System.out.print(bl);
    }

    @InlineOnly
    private static final void print(float f2) {
        int n2 = 0;
        System.out.print(f2);
    }

    @InlineOnly
    private static final void print(double d2) {
        int n2 = 0;
        System.out.print(d2);
    }

    @InlineOnly
    private static final void print(char[] arrc) {
        int n2 = 0;
        System.out.print(arrc);
    }

    @InlineOnly
    private static final void println(Object object) {
        int n2 = 0;
        System.out.println(object);
    }

    @InlineOnly
    private static final void println(int n2) {
        int n3 = 0;
        System.out.println(n2);
    }

    @InlineOnly
    private static final void println(long l2) {
        int n2 = 0;
        System.out.println(l2);
    }

    @InlineOnly
    private static final void println(byte by) {
        int n2 = 0;
        System.out.println((Object)by);
    }

    @InlineOnly
    private static final void println(short s2) {
        int n2 = 0;
        System.out.println((Object)s2);
    }

    @InlineOnly
    private static final void println(char c2) {
        int n2 = 0;
        System.out.println(c2);
    }

    @InlineOnly
    private static final void println(boolean bl) {
        int n2 = 0;
        System.out.println(bl);
    }

    @InlineOnly
    private static final void println(float f2) {
        int n2 = 0;
        System.out.println(f2);
    }

    @InlineOnly
    private static final void println(double d2) {
        int n2 = 0;
        System.out.println(d2);
    }

    @InlineOnly
    private static final void println(char[] arrc) {
        int n2 = 0;
        System.out.println(arrc);
    }

    @InlineOnly
    private static final void println() {
        int n2 = 0;
        System.out.println();
    }

    @Nullable
    public static final String readLine() {
        InputStream inputStream = System.in;
        Intrinsics.checkNotNullExpressionValue(inputStream, "System.`in`");
        Charset charset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charset, "Charset.defaultCharset()");
        return LineReader.INSTANCE.readLine(inputStream, charset);
    }
}

