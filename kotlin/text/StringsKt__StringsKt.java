/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.DelimitedRangesSequence;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\n\u001a\u0018\u0010\u0014\u001a\u00020\r*\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u0018\u0010\u0015\u001a\u00020\r*\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u001c\u0010\u0016\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0016\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002\u00a2\u0006\u0002\b\u001e\u001a:\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010 \u001a\u00020\r*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a7\u0010\"\u001a\u0002H#\"\f\b\u0000\u0010$*\u00020\u0002*\u0002H#\"\u0004\b\u0001\u0010#*\u0002H$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H#0&H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001a7\u0010(\u001a\u0002H#\"\f\b\u0000\u0010$*\u00020\u0002*\u0002H#\"\u0004\b\u0001\u0010#*\u0002H$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H#0&H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010'\u001a&\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\rH\u0002\u00a2\u0006\u0002\b+\u001a&\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010-\u001a\u00020\u0006*\u00020\u00022\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010-\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00101\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00102\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a \u00103\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00104\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00105\u001a\u000206*\u00020\u0002H\u0086\u0002\u001a&\u00107\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00107\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00108\u001a\u00020\u0006*\u00020\u00022\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00108\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0010\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u0002\u001a\u0015\u0010=\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\f\u001a\u000f\u0010>\u001a\u00020\n*\u0004\u0018\u00010\nH\u0087\b\u001a\u001c\u0010?\u001a\u00020\u0002*\u00020\u00022\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010?\u001a\u00020\n*\u00020\n2\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010B\u001a\u00020\u0002*\u00020\u00022\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010B\u001a\u00020\n*\u00020\n2\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001aG\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00010:*\u00020\u00022\u000e\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bG\u0010H\u001a=\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00010:*\u00020\u00022\u0006\u0010D\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\bG\u001a4\u0010I\u001a\u00020\r*\u00020\u00022\u0006\u0010J\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0002\u001a\u0012\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0002\u001a\u001a\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u001d\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010P\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010Q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010R\u001a\u00020\u0002\u001a\u001a\u0010Q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u0002\u001a\u001a\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a.\u0010S\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010T\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020\u00020UH\u0087\b\u00f8\u0001\u0000\u001a\u001d\u0010S\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010W\u001a\u00020\nH\u0087\b\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010[\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010[\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001d\u0010]\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010W\u001a\u00020\nH\u0087\b\u001a)\u0010^\u001a\u00020\n*\u00020\n2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110UH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b_\u001a)\u0010^\u001a\u00020\n*\u00020\n2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00020UH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\b`\u001a\"\u0010a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u0002\u001a\u001a\u0010a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u00012\u0006\u0010W\u001a\u00020\u0002\u001a%\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010W\u001a\u00020\u0002H\u0087\b\u001a=\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0012\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u00a2\u0006\u0002\u0010c\u001a0\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\n\u0010D\u001a\u00020/\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u001a/\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0006\u0010R\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\bd\u001a%\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010F\u001a\u00020\u0006H\u0087\b\u001a=\u0010e\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u00a2\u0006\u0002\u0010f\u001a0\u0010e\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010D\u001a\u00020/\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u001a\u001c\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u001d\u0010h\u001a\u00020\u0002*\u00020\n2\u0006\u0010i\u001a\u00020\u00062\u0006\u0010j\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010k\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010k\u001a\u00020\n*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u0012\u0010k\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u0001\u001a\u001c\u0010l\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010l\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010m\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010m\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010n\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010n\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010o\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010o\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\f\u0010p\u001a\u00020\r*\u00020\nH\u0007\u001a\u0013\u0010q\u001a\u0004\u0018\u00010\r*\u00020\nH\u0007\u00a2\u0006\u0002\u0010r\u001a\n\u0010s\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010s\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010s\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010s\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010s\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010s\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\n\u0010u\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010u\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010u\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010u\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\n\u0010v\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010v\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010v\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010v\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010v\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\b\u00f8\u0001\u0000\u001a\u0016\u0010v\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006w"}, d2={"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "contentEqualsIgnoreCaseImpl", "contentEqualsImpl", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceFirstChar", "replaceFirstCharWithChar", "replaceFirstCharWithCharSequence", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "toBooleanStrict", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, xs="kotlin/text/StringsKt")
class StringsKt__StringsKt
extends StringsKt__StringsJVMKt {
    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(charSequence, "$this$trim");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        int n3 = 0;
        int n4 = charSequence.length() - 1;
        boolean bl = false;
        while (n3 <= n4) {
            int n5 = !bl ? n3 : n4;
            boolean bl2 = function1.invoke(Character.valueOf(charSequence.charAt(n5)));
            if (!bl) {
                if (!bl2) {
                    bl = true;
                    continue;
                }
                ++n3;
                continue;
            }
            if (!bl2) break;
            --n4;
        }
        return charSequence.subSequence(n3, n4 + 1);
    }

    @NotNull
    public static final String trim(@NotNull String string, @NotNull Function1<? super Character, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(string, "$this$trim");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        CharSequence charSequence = string;
        boolean bl = false;
        int n3 = 0;
        int n4 = charSequence.length() - 1;
        boolean bl2 = false;
        while (n3 <= n4) {
            int n5 = !bl2 ? n3 : n4;
            boolean bl3 = function1.invoke(Character.valueOf(charSequence.charAt(n5)));
            if (!bl2) {
                if (!bl3) {
                    bl2 = true;
                    continue;
                }
                ++n3;
                continue;
            }
            if (!bl3) break;
            --n4;
        }
        return ((Object)charSequence.subSequence(n3, n4 + 1)).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(charSequence, "$this$trimStart");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        int n3 = charSequence.length();
        for (int i2 = 0; i2 < n3; ++i2) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i2))).booleanValue()) continue;
            return charSequence.subSequence(i2, charSequence.length());
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String string, @NotNull Function1<? super Character, Boolean> function1) {
        CharSequence charSequence;
        block1: {
            int n2 = 0;
            Intrinsics.checkNotNullParameter(string, "$this$trimStart");
            Intrinsics.checkNotNullParameter(function1, "predicate");
            CharSequence charSequence2 = string;
            boolean bl = false;
            int n3 = charSequence2.length();
            for (int i2 = 0; i2 < n3; ++i2) {
                if (function1.invoke(Character.valueOf(charSequence2.charAt(i2))).booleanValue()) continue;
                charSequence = charSequence2.subSequence(i2, charSequence2.length());
                break block1;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(charSequence, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        int n3 = charSequence.length();
        --n3;
        boolean bl = false;
        while (n3 >= 0) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(n3))).booleanValue()) {
                return charSequence.subSequence(0, n3 + 1);
            }
            --n3;
        }
        return "";
    }

    @NotNull
    public static final String trimEnd(@NotNull String string, @NotNull Function1<? super Character, Boolean> function1) {
        CharSequence charSequence;
        block2: {
            int n2 = 0;
            Intrinsics.checkNotNullParameter(string, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(function1, "predicate");
            CharSequence charSequence2 = string;
            boolean bl = false;
            int n3 = charSequence2.length();
            --n3;
            boolean bl2 = false;
            while (n3 >= 0) {
                if (!function1.invoke(Character.valueOf(charSequence2.charAt(n3))).booleanValue()) {
                    charSequence = charSequence2.subSequence(0, n3 + 1);
                    break block2;
                }
                --n3;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence, char ... arrc) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$trim");
        Intrinsics.checkNotNullParameter(arrc, "chars");
        CharSequence charSequence2 = charSequence;
        boolean bl = false;
        int n2 = 0;
        int n3 = charSequence2.length() - 1;
        boolean bl2 = false;
        while (n2 <= n3) {
            int n4 = !bl2 ? n2 : n3;
            char c2 = charSequence2.charAt(n4);
            boolean bl3 = false;
            c2 = (char)(ArraysKt.contains(arrc, c2) ? 1 : 0);
            if (!bl2) {
                if (c2 == '\u0000') {
                    bl2 = true;
                    continue;
                }
                ++n2;
                continue;
            }
            if (c2 == '\u0000') break;
            --n3;
        }
        return charSequence2.subSequence(n2, n3 + 1);
    }

    @NotNull
    public static final String trim(@NotNull String string, char ... arrc) {
        Intrinsics.checkNotNullParameter(string, "$this$trim");
        Intrinsics.checkNotNullParameter(arrc, "chars");
        String string2 = string;
        boolean bl = false;
        CharSequence charSequence = string2;
        boolean bl2 = false;
        int n2 = 0;
        int n3 = charSequence.length() - 1;
        boolean bl3 = false;
        while (n2 <= n3) {
            int n4 = !bl3 ? n2 : n3;
            char c2 = charSequence.charAt(n4);
            boolean bl4 = false;
            c2 = (char)(ArraysKt.contains(arrc, c2) ? 1 : 0);
            if (!bl3) {
                if (c2 == '\u0000') {
                    bl3 = true;
                    continue;
                }
                ++n2;
                continue;
            }
            if (c2 == '\u0000') break;
            --n3;
        }
        return ((Object)charSequence.subSequence(n2, n3 + 1)).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence, char ... arrc) {
        CharSequence charSequence2;
        block1: {
            Intrinsics.checkNotNullParameter(charSequence, "$this$trimStart");
            Intrinsics.checkNotNullParameter(arrc, "chars");
            CharSequence charSequence3 = charSequence;
            boolean bl = false;
            int n2 = charSequence3.length();
            for (int i2 = 0; i2 < n2; ++i2) {
                char c2 = charSequence3.charAt(i2);
                boolean bl2 = false;
                if (ArraysKt.contains(arrc, c2)) continue;
                charSequence2 = charSequence3.subSequence(i2, charSequence3.length());
                break block1;
            }
            charSequence2 = "";
        }
        return charSequence2;
    }

    @NotNull
    public static final String trimStart(@NotNull String string, char ... arrc) {
        CharSequence charSequence;
        block1: {
            Intrinsics.checkNotNullParameter(string, "$this$trimStart");
            Intrinsics.checkNotNullParameter(arrc, "chars");
            String string2 = string;
            boolean bl = false;
            CharSequence charSequence2 = string2;
            boolean bl2 = false;
            int n2 = charSequence2.length();
            for (int i2 = 0; i2 < n2; ++i2) {
                char c2 = charSequence2.charAt(i2);
                boolean bl3 = false;
                if (ArraysKt.contains(arrc, c2)) continue;
                charSequence = charSequence2.subSequence(i2, charSequence2.length());
                break block1;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence, char ... arrc) {
        CharSequence charSequence2;
        block2: {
            Intrinsics.checkNotNullParameter(charSequence, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(arrc, "chars");
            CharSequence charSequence3 = charSequence;
            boolean bl = false;
            int n2 = charSequence3.length();
            --n2;
            boolean bl2 = false;
            while (n2 >= 0) {
                char c2 = charSequence3.charAt(n2);
                boolean bl3 = false;
                if (!ArraysKt.contains(arrc, c2)) {
                    charSequence2 = charSequence3.subSequence(0, n2 + 1);
                    break block2;
                }
                --n2;
            }
            charSequence2 = "";
        }
        return charSequence2;
    }

    @NotNull
    public static final String trimEnd(@NotNull String string, char ... arrc) {
        CharSequence charSequence;
        block2: {
            Intrinsics.checkNotNullParameter(string, "$this$trimEnd");
            Intrinsics.checkNotNullParameter(arrc, "chars");
            String string2 = string;
            boolean bl = false;
            CharSequence charSequence2 = string2;
            boolean bl2 = false;
            int n2 = charSequence2.length();
            --n2;
            boolean bl3 = false;
            while (n2 >= 0) {
                char c2 = charSequence2.charAt(n2);
                boolean bl4 = false;
                if (!ArraysKt.contains(arrc, c2)) {
                    charSequence = charSequence2.subSequence(0, n2 + 1);
                    break block2;
                }
                --n2;
            }
            charSequence = "";
        }
        return ((Object)charSequence).toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$trim");
        CharSequence charSequence2 = charSequence;
        boolean bl = false;
        int n2 = 0;
        int n3 = charSequence2.length() - 1;
        boolean bl2 = false;
        while (n2 <= n3) {
            int n4 = !bl2 ? n2 : n3;
            char c2 = charSequence2.charAt(n4);
            boolean bl3 = false;
            c2 = (char)(CharsKt.isWhitespace(c2) ? 1 : 0);
            if (!bl2) {
                if (c2 == '\u0000') {
                    bl2 = true;
                    continue;
                }
                ++n2;
                continue;
            }
            if (c2 == '\u0000') break;
            --n3;
        }
        return charSequence2.subSequence(n2, n3 + 1);
    }

    @InlineOnly
    private static final String trim(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trim((CharSequence)string2)).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence charSequence) {
        CharSequence charSequence2;
        block1: {
            Intrinsics.checkNotNullParameter(charSequence, "$this$trimStart");
            CharSequence charSequence3 = charSequence;
            boolean bl = false;
            int n2 = charSequence3.length();
            for (int i2 = 0; i2 < n2; ++i2) {
                char c2 = charSequence3.charAt(i2);
                boolean bl2 = false;
                if (CharsKt.isWhitespace(c2)) continue;
                charSequence2 = charSequence3.subSequence(i2, charSequence3.length());
                break block1;
            }
            charSequence2 = "";
        }
        return charSequence2;
    }

    @InlineOnly
    private static final String trimStart(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trimStart((CharSequence)string2)).toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence charSequence) {
        CharSequence charSequence2;
        block2: {
            Intrinsics.checkNotNullParameter(charSequence, "$this$trimEnd");
            CharSequence charSequence3 = charSequence;
            boolean bl = false;
            int n2 = charSequence3.length();
            --n2;
            boolean bl2 = false;
            while (n2 >= 0) {
                char c2 = charSequence3.charAt(n2);
                boolean bl3 = false;
                if (!CharsKt.isWhitespace(c2)) {
                    charSequence2 = charSequence3.subSequence(0, n2 + 1);
                    break block2;
                }
                --n2;
            }
            charSequence2 = "";
        }
        return charSequence2;
    }

    @InlineOnly
    private static final String trimEnd(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.trimEnd((CharSequence)string2)).toString();
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence charSequence, int n2, char c2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$padStart");
        if (n2 < 0) {
            throw (Throwable)new IllegalArgumentException("Desired length " + n2 + " is less than zero.");
        }
        if (n2 <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder stringBuilder = new StringBuilder(n2);
        int n3 = 1;
        int n4 = n2 - charSequence.length();
        if (n3 <= n4) {
            while (true) {
                stringBuilder.append(c2);
                if (n3 == n4) break;
                ++n3;
            }
        }
        stringBuilder.append(charSequence);
        return stringBuilder;
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int n2, char c2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            c2 = (char)32;
        }
        return StringsKt.padStart(charSequence, n2, c2);
    }

    @NotNull
    public static final String padStart(@NotNull String string, int n2, char c2) {
        Intrinsics.checkNotNullParameter(string, "$this$padStart");
        return ((Object)StringsKt.padStart((CharSequence)string, n2, c2)).toString();
    }

    public static /* synthetic */ String padStart$default(String string, int n2, char c2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            c2 = (char)32;
        }
        return StringsKt.padStart(string, n2, c2);
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence charSequence, int n2, char c2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$padEnd");
        if (n2 < 0) {
            throw (Throwable)new IllegalArgumentException("Desired length " + n2 + " is less than zero.");
        }
        if (n2 <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder stringBuilder = new StringBuilder(n2);
        stringBuilder.append(charSequence);
        int n3 = 1;
        int n4 = n2 - charSequence.length();
        if (n3 <= n4) {
            while (true) {
                stringBuilder.append(c2);
                if (n3 == n4) break;
                ++n3;
            }
        }
        return stringBuilder;
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int n2, char c2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            c2 = (char)32;
        }
        return StringsKt.padEnd(charSequence, n2, c2);
    }

    @NotNull
    public static final String padEnd(@NotNull String string, int n2, char c2) {
        Intrinsics.checkNotNullParameter(string, "$this$padEnd");
        return ((Object)StringsKt.padEnd((CharSequence)string, n2, c2)).toString();
    }

    public static /* synthetic */ String padEnd$default(String string, int n2, char c2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            c2 = (char)32;
        }
        return StringsKt.padEnd(string, n2, c2);
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(CharSequence charSequence) {
        int n2 = 0;
        boolean bl = false;
        return charSequence == null || charSequence.length() == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(CharSequence charSequence) {
        int n2 = 0;
        return charSequence.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(CharSequence charSequence) {
        int n2 = 0;
        return charSequence.length() > 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(CharSequence charSequence) {
        int n2 = 0;
        return !StringsKt.isBlank(charSequence);
    }

    @InlineOnly
    private static final boolean isNullOrBlank(CharSequence charSequence) {
        int n2 = 0;
        boolean bl = false;
        return charSequence == null || StringsKt.isBlank(charSequence);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$iterator");
        return new CharIterator(charSequence){
            private int index;
            final /* synthetic */ CharSequence $this_iterator;

            public char nextChar() {
                int n2 = this.index;
                this.index = n2 + 1;
                return this.$this_iterator.charAt(n2);
            }

            public boolean hasNext() {
                return this.index < this.$this_iterator.length();
            }
            {
                this.$this_iterator = charSequence;
            }
        };
    }

    @InlineOnly
    private static final String orEmpty(String string) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            string2 = "";
        }
        return string2;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C c2, Function0<? extends R> function0) {
        int n2 = 0;
        C c3 = c2;
        boolean bl = false;
        return (R)(c3.length() == 0 ? function0.invoke() : c2);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C c2, Function0<? extends R> function0) {
        int n2 = 0;
        return (R)(StringsKt.isBlank(c2) ? function0.invoke() : c2);
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$indices");
        int n2 = 0;
        return new IntRange(n2, charSequence.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean hasSurrogatePairAt(@NotNull CharSequence charSequence, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$hasSurrogatePairAt");
        int n3 = n2;
        if (0 > n3) return false;
        if (charSequence.length() - 2 < n3) return false;
        char c2 = charSequence.charAt(n2);
        boolean bl = false;
        if (!Character.isHighSurrogate(c2)) return false;
        char c3 = charSequence.charAt(n2 + 1);
        bl = false;
        if (!Character.isLowSurrogate(c3)) return false;
        return true;
    }

    @NotNull
    public static final String substring(@NotNull String string, @NotNull IntRange intRange) {
        Intrinsics.checkNotNullParameter(string, "$this$substring");
        Intrinsics.checkNotNullParameter(intRange, "range");
        String string2 = string;
        int n2 = intRange.getStart();
        int n3 = intRange.getEndInclusive() + 1;
        boolean bl = false;
        String string3 = string2.substring(n2, n3);
        Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string3;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$subSequence");
        Intrinsics.checkNotNullParameter(intRange, "range");
        return charSequence.subSequence(intRange.getStart(), intRange.getEndInclusive() + 1);
    }

    @Deprecated(message="Use parameters named startIndex and endIndex.", replaceWith=@ReplaceWith(imports={}, expression="subSequence(startIndex = start, endIndex = end)"))
    @InlineOnly
    private static final CharSequence subSequence(String string, int n2, int n3) {
        int n4 = 0;
        return string.subSequence(n2, n3);
    }

    @InlineOnly
    private static final String substring(CharSequence charSequence, int n2, int n3) {
        int n4 = 0;
        return ((Object)charSequence.subSequence(n2, n3)).toString();
    }

    static /* synthetic */ String substring$default(CharSequence charSequence, int n2, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n3 = charSequence.length();
        }
        n4 = 0;
        return ((Object)charSequence.subSequence(n2, n3)).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$substring");
        Intrinsics.checkNotNullParameter(intRange, "range");
        return ((Object)charSequence.subSequence(intRange.getStart(), intRange.getEndInclusive() + 1)).toString();
    }

    @NotNull
    public static final String substringBefore(@NotNull String string, char c2, @NotNull String string2) {
        String string3;
        Intrinsics.checkNotNullParameter(string, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(string2, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string3 = string2;
        } else {
            String string4 = string;
            int n3 = 0;
            boolean bl = false;
            String string5 = string4.substring(n3, n2);
            string3 = string5;
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string3;
    }

    public static /* synthetic */ String substringBefore$default(String string, char c2, String string2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringBefore(string, c2, string2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = 0;
            boolean bl = false;
            String string6 = string5.substring(n3, n2);
            string4 = string6;
            Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string4;
    }

    public static /* synthetic */ String substringBefore$default(String string, String string2, String string3, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringBefore(string, string2, string3);
    }

    @NotNull
    public static final String substringAfter(@NotNull String string, char c2, @NotNull String string2) {
        String string3;
        Intrinsics.checkNotNullParameter(string, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(string2, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string3 = string2;
        } else {
            String string4 = string;
            int n3 = n2 + 1;
            int n4 = string.length();
            boolean bl = false;
            String string5 = string4.substring(n3, n4);
            string3 = string5;
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string3;
    }

    public static /* synthetic */ String substringAfter$default(String string, char c2, String string2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringAfter(string, c2, string2);
    }

    @NotNull
    public static final String substringAfter(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = n2 + string2.length();
            int n4 = string.length();
            boolean bl = false;
            String string6 = string5.substring(n3, n4);
            string4 = string6;
            Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string4;
    }

    public static /* synthetic */ String substringAfter$default(String string, String string2, String string3, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringAfter(string, string2, string3);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String string, char c2, @NotNull String string2) {
        String string3;
        Intrinsics.checkNotNullParameter(string, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(string2, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string3 = string2;
        } else {
            String string4 = string;
            int n3 = 0;
            boolean bl = false;
            String string5 = string4.substring(n3, n2);
            string3 = string5;
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string3;
    }

    public static /* synthetic */ String substringBeforeLast$default(String string, char c2, String string2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringBeforeLast(string, c2, string2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = 0;
            boolean bl = false;
            String string6 = string5.substring(n3, n2);
            string4 = string6;
            Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string4;
    }

    public static /* synthetic */ String substringBeforeLast$default(String string, String string2, String string3, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringBeforeLast(string, string2, string3);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String string, char c2, @NotNull String string2) {
        String string3;
        Intrinsics.checkNotNullParameter(string, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(string2, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string3 = string2;
        } else {
            String string4 = string;
            int n3 = n2 + 1;
            int n4 = string.length();
            boolean bl = false;
            String string5 = string4.substring(n3, n4);
            string3 = string5;
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string3;
    }

    public static /* synthetic */ String substringAfterLast$default(String string, char c2, String string2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string2 = string;
        }
        return StringsKt.substringAfterLast(string, c2, string2);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = n2 + string2.length();
            int n4 = string.length();
            boolean bl = false;
            String string6 = string5.substring(n3, n4);
            string4 = string6;
            Intrinsics.checkNotNullExpressionValue(string6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        }
        return string4;
    }

    public static /* synthetic */ String substringAfterLast$default(String string, String string2, String string3, int n2, Object object) {
        if ((n2 & 2) != 0) {
            string3 = string;
        }
        return StringsKt.substringAfterLast(string, string2, string3);
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, int n2, int n3, @NotNull CharSequence charSequence2) {
        StringBuilder stringBuilder;
        Intrinsics.checkNotNullParameter(charSequence, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(charSequence2, "replacement");
        if (n3 < n2) {
            throw (Throwable)new IndexOutOfBoundsException("End index (" + n3 + ") is less than start index (" + n2 + ").");
        }
        StringBuilder stringBuilder2 = stringBuilder = new StringBuilder();
        int n4 = 0;
        boolean bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder2.append(charSequence, n4, n2), "this.append(value, startIndex, endIndex)");
        stringBuilder.append(charSequence2);
        stringBuilder2 = stringBuilder;
        n4 = charSequence.length();
        bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder2.append(charSequence, n3, n4), "this.append(value, startIndex, endIndex)");
        return stringBuilder;
    }

    @InlineOnly
    private static final String replaceRange(String string, int n2, int n3, CharSequence charSequence) {
        int n4 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.replaceRange((CharSequence)string2, n2, n3, charSequence)).toString();
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence charSequence, @NotNull IntRange intRange, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(intRange, "range");
        Intrinsics.checkNotNullParameter(charSequence2, "replacement");
        return StringsKt.replaceRange(charSequence, (int)intRange.getStart(), intRange.getEndInclusive() + 1, charSequence2);
    }

    @InlineOnly
    private static final String replaceRange(String string, IntRange intRange, CharSequence charSequence) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.replaceRange((CharSequence)string2, intRange, charSequence)).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, int n2, int n3) {
        StringBuilder stringBuilder;
        Intrinsics.checkNotNullParameter(charSequence, "$this$removeRange");
        if (n3 < n2) {
            throw (Throwable)new IndexOutOfBoundsException("End index (" + n3 + ") is less than start index (" + n2 + ").");
        }
        if (n3 == n2) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder stringBuilder2 = stringBuilder = new StringBuilder(charSequence.length() - (n3 - n2));
        int n4 = 0;
        boolean bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder2.append(charSequence, n4, n2), "this.append(value, startIndex, endIndex)");
        stringBuilder2 = stringBuilder;
        n4 = charSequence.length();
        bl = false;
        Intrinsics.checkNotNullExpressionValue(stringBuilder2.append(charSequence, n3, n4), "this.append(value, startIndex, endIndex)");
        return stringBuilder;
    }

    @InlineOnly
    private static final String removeRange(String string, int n2, int n3) {
        int n4 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.removeRange((CharSequence)string2, n2, n3)).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$removeRange");
        Intrinsics.checkNotNullParameter(intRange, "range");
        return StringsKt.removeRange(charSequence, (int)intRange.getStart(), intRange.getEndInclusive() + 1);
    }

    @InlineOnly
    private static final String removeRange(String string, IntRange intRange) {
        int n2 = 0;
        String string2 = string;
        if (string2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return ((Object)StringsKt.removeRange((CharSequence)string2, intRange)).toString();
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        if (StringsKt.startsWith$default(charSequence, charSequence2, false, 2, null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String string, @NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(string, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(charSequence, "prefix");
        if (StringsKt.startsWith$default((CharSequence)string, charSequence, false, 2, null)) {
            String string2 = string;
            int n2 = charSequence.length();
            boolean bl = false;
            String string3 = string2.substring(n2);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
            return string3;
        }
        return string;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(charSequence2, "suffix");
        if (StringsKt.endsWith$default(charSequence, charSequence2, false, 2, null)) {
            return charSequence.subSequence(0, charSequence.length() - charSequence2.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String string, @NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(string, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(charSequence, "suffix");
        if (StringsKt.endsWith$default((CharSequence)string, charSequence, false, 2, null)) {
            String string2 = string;
            int n2 = 0;
            int n3 = string.length() - charSequence.length();
            boolean bl = false;
            String string3 = string2.substring(n2, n3);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string3;
        }
        return string;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        Intrinsics.checkNotNullParameter(charSequence3, "suffix");
        if (charSequence.length() >= charSequence2.length() + charSequence3.length() && StringsKt.startsWith$default(charSequence, charSequence2, false, 2, null) && StringsKt.endsWith$default(charSequence, charSequence3, false, 2, null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length() - charSequence3.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String string, @NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(string, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(charSequence, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "suffix");
        if (string.length() >= charSequence.length() + charSequence2.length() && StringsKt.startsWith$default((CharSequence)string, charSequence, false, 2, null) && StringsKt.endsWith$default((CharSequence)string, charSequence2, false, 2, null)) {
            String string2 = string;
            int n2 = charSequence.length();
            int n3 = string.length() - charSequence2.length();
            boolean bl = false;
            String string3 = string2.substring(n2, n3);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return string3;
        }
        return string;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(charSequence2, "delimiter");
        return StringsKt.removeSurrounding(charSequence, charSequence2, charSequence2);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String string, @NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(string, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(charSequence, "delimiter");
        return StringsKt.removeSurrounding(string, charSequence, charSequence);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String string, char c2, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(string2, "replacement");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = 0;
            boolean bl = false;
            string4 = ((Object)StringsKt.replaceRange((CharSequence)string5, n3, n2, (CharSequence)string2)).toString();
        }
        return string4;
    }

    public static /* synthetic */ String replaceBefore$default(String string, char c2, String string2, String string3, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceBefore(string, c2, string2, string3);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4) {
        String string5;
        Intrinsics.checkNotNullParameter(string, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "replacement");
        Intrinsics.checkNotNullParameter(string4, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string5 = string4;
        } else {
            String string6 = string;
            int n3 = 0;
            boolean bl = false;
            string5 = ((Object)StringsKt.replaceRange((CharSequence)string6, n3, n2, (CharSequence)string3)).toString();
        }
        return string5;
    }

    public static /* synthetic */ String replaceBefore$default(String string, String string2, String string3, String string4, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceBefore(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String string, char c2, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(string2, "replacement");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = n2 + 1;
            int n4 = string.length();
            boolean bl = false;
            string4 = ((Object)StringsKt.replaceRange((CharSequence)string5, n3, n4, (CharSequence)string2)).toString();
        }
        return string4;
    }

    public static /* synthetic */ String replaceAfter$default(String string, char c2, String string2, String string3, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceAfter(string, c2, string2, string3);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4) {
        String string5;
        Intrinsics.checkNotNullParameter(string, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "replacement");
        Intrinsics.checkNotNullParameter(string4, "missingDelimiterValue");
        int n2 = StringsKt.indexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string5 = string4;
        } else {
            String string6 = string;
            int n3 = n2 + string2.length();
            int n4 = string.length();
            boolean bl = false;
            string5 = ((Object)StringsKt.replaceRange((CharSequence)string6, n3, n4, (CharSequence)string3)).toString();
        }
        return string5;
    }

    public static /* synthetic */ String replaceAfter$default(String string, String string2, String string3, String string4, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceAfter(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4) {
        String string5;
        Intrinsics.checkNotNullParameter(string, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "replacement");
        Intrinsics.checkNotNullParameter(string4, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string5 = string4;
        } else {
            String string6 = string;
            int n3 = n2 + string2.length();
            int n4 = string.length();
            boolean bl = false;
            string5 = ((Object)StringsKt.replaceRange((CharSequence)string6, n3, n4, (CharSequence)string3)).toString();
        }
        return string5;
    }

    public static /* synthetic */ String replaceAfterLast$default(String string, String string2, String string3, String string4, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceAfterLast(string, string2, string3, string4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String string, char c2, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(string2, "replacement");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = n2 + 1;
            int n4 = string.length();
            boolean bl = false;
            string4 = ((Object)StringsKt.replaceRange((CharSequence)string5, n3, n4, (CharSequence)string2)).toString();
        }
        return string4;
    }

    public static /* synthetic */ String replaceAfterLast$default(String string, char c2, String string2, String string3, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceAfterLast(string, c2, string2, string3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String string, char c2, @NotNull String string2, @NotNull String string3) {
        String string4;
        Intrinsics.checkNotNullParameter(string, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(string2, "replacement");
        Intrinsics.checkNotNullParameter(string3, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, c2, 0, false, 6, null);
        if (n2 == -1) {
            string4 = string3;
        } else {
            String string5 = string;
            int n3 = 0;
            boolean bl = false;
            string4 = ((Object)StringsKt.replaceRange((CharSequence)string5, n3, n2, (CharSequence)string2)).toString();
        }
        return string4;
    }

    public static /* synthetic */ String replaceBeforeLast$default(String string, char c2, String string2, String string3, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string3 = string;
        }
        return StringsKt.replaceBeforeLast(string, c2, string2, string3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4) {
        String string5;
        Intrinsics.checkNotNullParameter(string, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(string2, "delimiter");
        Intrinsics.checkNotNullParameter(string3, "replacement");
        Intrinsics.checkNotNullParameter(string4, "missingDelimiterValue");
        int n2 = StringsKt.lastIndexOf$default((CharSequence)string, string2, 0, false, 6, null);
        if (n2 == -1) {
            string5 = string4;
        } else {
            String string6 = string;
            int n3 = 0;
            boolean bl = false;
            string5 = ((Object)StringsKt.replaceRange((CharSequence)string6, n3, n2, (CharSequence)string3)).toString();
        }
        return string5;
    }

    public static /* synthetic */ String replaceBeforeLast$default(String string, String string2, String string3, String string4, int n2, Object object) {
        if ((n2 & 4) != 0) {
            string4 = string;
        }
        return StringsKt.replaceBeforeLast(string, string2, string3, string4);
    }

    @InlineOnly
    private static final String replace(CharSequence charSequence, Regex regex, String string) {
        int n2 = 0;
        return regex.replace(charSequence, string);
    }

    @InlineOnly
    private static final String replace(CharSequence charSequence, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        int n2 = 0;
        return regex.replace(charSequence, function1);
    }

    @InlineOnly
    private static final String replaceFirst(CharSequence charSequence, Regex regex, String string) {
        int n2 = 0;
        return regex.replaceFirst(charSequence, string);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="replaceFirstCharWithChar")
    @InlineOnly
    private static final String replaceFirstCharWithChar(String string, Function1<? super Character, Character> function1) {
        String string2;
        int n2 = 0;
        CharSequence charSequence = string;
        boolean bl = false;
        if (charSequence.length() > 0) {
            char c2 = function1.invoke(Character.valueOf(string.charAt(0))).charValue();
            String string3 = string;
            int n3 = 1;
            boolean bl2 = false;
            String string4 = string3;
            if (string4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string5 = string4.substring(n3);
            Intrinsics.checkNotNullExpressionValue(string5, "(this as java.lang.String).substring(startIndex)");
            string3 = string5;
            n3 = 0;
            string2 = String.valueOf(c2) + string3;
        } else {
            string2 = string;
        }
        return string2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @OverloadResolutionByLambdaReturnType
    @JvmName(name="replaceFirstCharWithCharSequence")
    @InlineOnly
    private static final String replaceFirstCharWithCharSequence(String string, Function1<? super Character, ? extends CharSequence> function1) {
        String string2;
        int n2 = 0;
        CharSequence charSequence = string;
        int n3 = 0;
        if (charSequence.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder().append(((Object)function1.invoke(Character.valueOf(string.charAt(0)))).toString());
            charSequence = string;
            n3 = 1;
            boolean bl = false;
            CharSequence charSequence2 = charSequence;
            if (charSequence2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String string3 = ((String)charSequence2).substring(n3);
            Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.String).substring(startIndex)");
            string2 = stringBuilder.append(string3).toString();
        } else {
            string2 = string;
        }
        return string2;
    }

    @InlineOnly
    private static final boolean matches(CharSequence charSequence, Regex regex) {
        int n2 = 0;
        return regex.matches(charSequence);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence charSequence, int n2, @NotNull CharSequence charSequence2, int n3, int n4, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$regionMatchesImpl");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        if (n3 < 0 || n2 < 0 || n2 > charSequence.length() - n4 || n3 > charSequence2.length() - n4) {
            return false;
        }
        int n5 = n4;
        for (int i2 = 0; i2 < n5; ++i2) {
            if (CharsKt.equals(charSequence.charAt(n2 + i2), charSequence2.charAt(n3 + i2), bl)) continue;
            return false;
        }
        return true;
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, char c2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$startsWith");
        return charSequence.length() > 0 && CharsKt.equals(charSequence.charAt(0), c2, bl);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, c2, bl);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, char c2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$endsWith");
        return charSequence.length() > 0 && CharsKt.equals(charSequence.charAt(StringsKt.getLastIndex(charSequence)), c2, bl);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(charSequence, c2, bl);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$startsWith");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        if (!bl && charSequence instanceof String && charSequence2 instanceof String) {
            return StringsKt.startsWith$default((String)charSequence, (String)charSequence2, false, 2, null);
        }
        return StringsKt.regionMatchesImpl(charSequence, 0, charSequence2, 0, charSequence2.length(), bl);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, bl);
    }

    public static final boolean startsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$startsWith");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        if (!bl && charSequence instanceof String && charSequence2 instanceof String) {
            return StringsKt.startsWith$default((String)charSequence, (String)charSequence2, n2, false, 4, null);
        }
        return StringsKt.regionMatchesImpl(charSequence, n2, charSequence2, 0, charSequence2.length(), bl);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, n2, bl);
    }

    public static final boolean endsWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$endsWith");
        Intrinsics.checkNotNullParameter(charSequence2, "suffix");
        if (!bl && charSequence instanceof String && charSequence2 instanceof String) {
            return StringsKt.endsWith$default((String)charSequence, (String)charSequence2, false, 2, null);
        }
        return StringsKt.regionMatchesImpl(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), bl);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.endsWith(charSequence, charSequence2, bl);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$commonPrefixWith");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        int n2 = charSequence.length();
        int n3 = charSequence2.length();
        boolean bl2 = false;
        int n4 = Math.min(n2, n3);
        for (n2 = 0; n2 < n4 && CharsKt.equals(charSequence.charAt(n2), charSequence2.charAt(n2), bl); ++n2) {
        }
        if (StringsKt.hasSurrogatePairAt(charSequence, n2 - 1) || StringsKt.hasSurrogatePairAt(charSequence2, n2 - 1)) {
            --n2;
        }
        return ((Object)charSequence.subSequence(0, n2)).toString();
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.commonPrefixWith(charSequence, charSequence2, bl);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$commonSuffixWith");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        int n2 = charSequence.length();
        int n3 = charSequence2.length();
        int n4 = 0;
        int n5 = Math.min(n2, n3);
        for (n4 = 0; n4 < n5 && CharsKt.equals(charSequence.charAt(n2 - n4 - 1), charSequence2.charAt(n3 - n4 - 1), bl); ++n4) {
        }
        if (StringsKt.hasSurrogatePairAt(charSequence, n2 - n4 - 1) || StringsKt.hasSurrogatePairAt(charSequence2, n3 - n4 - 1)) {
            --n4;
        }
        return ((Object)charSequence.subSequence(n2 - n4, n2)).toString();
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.commonSuffixWith(charSequence, charSequence2, bl);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull char[] arrc, int n2, boolean bl) {
        int n3;
        Intrinsics.checkNotNullParameter(charSequence, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(arrc, "chars");
        if (!bl && arrc.length == 1 && charSequence instanceof String) {
            char c2 = ArraysKt.single(arrc);
            String string = (String)charSequence;
            boolean bl2 = false;
            char c3 = c2;
            boolean bl3 = false;
            return string.indexOf(c3, n2);
        }
        int n4 = RangesKt.coerceAtLeast(n2, 0);
        if (n4 <= (n3 = StringsKt.getLastIndex(charSequence))) {
            while (true) {
                boolean bl4;
                block5: {
                    char c4 = charSequence.charAt(n4);
                    char[] arrc2 = arrc;
                    boolean bl5 = false;
                    char[] arrc3 = arrc2;
                    int n5 = arrc3.length;
                    for (int i2 = 0; i2 < n5; ++i2) {
                        char c5;
                        char c6 = c5 = arrc3[i2];
                        boolean bl6 = false;
                        if (!CharsKt.equals(c6, c4, bl)) continue;
                        bl4 = true;
                        break block5;
                    }
                    bl4 = false;
                }
                if (bl4) {
                    return n4;
                }
                if (n4 == n3) break;
                ++n4;
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] arrc, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOfAny(charSequence, arrc, n2, bl);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull char[] arrc, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(arrc, "chars");
        if (!bl && arrc.length == 1 && charSequence instanceof String) {
            char c2 = ArraysKt.single(arrc);
            String string = (String)charSequence;
            boolean bl2 = false;
            char c3 = c2;
            boolean bl3 = false;
            return string.lastIndexOf(c3, n2);
        }
        boolean bl4 = false;
        for (int i2 = RangesKt.coerceAtMost(n2, StringsKt.getLastIndex(charSequence)); i2 >= 0; --i2) {
            boolean bl5;
            block3: {
                char c4 = charSequence.charAt(i2);
                char[] arrc2 = arrc;
                boolean bl6 = false;
                char[] arrc3 = arrc2;
                int n3 = arrc3.length;
                for (int i3 = 0; i3 < n3; ++i3) {
                    char c5;
                    char c6 = c5 = arrc3[i3];
                    boolean bl7 = false;
                    if (!CharsKt.equals(c6, c4, bl)) continue;
                    bl5 = true;
                    break block3;
                }
                bl5 = false;
            }
            if (!bl5) continue;
            return i2;
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] arrc, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = StringsKt.getLastIndex(charSequence);
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, arrc, n2, bl);
    }

    private static final int indexOf$StringsKt__StringsKt(CharSequence charSequence, CharSequence charSequence2, int n2, int n3, boolean bl, boolean bl2) {
        IntProgression intProgression;
        IntProgression intProgression2;
        int n4;
        if (!bl2) {
            n4 = RangesKt.coerceAtLeast(n2, 0);
            intProgression2 = new IntRange(n4, RangesKt.coerceAtMost(n3, charSequence.length()));
        } else {
            intProgression2 = intProgression = RangesKt.downTo(RangesKt.coerceAtMost(n2, StringsKt.getLastIndex(charSequence)), RangesKt.coerceAtLeast(n3, 0));
        }
        if (charSequence instanceof String && charSequence2 instanceof String) {
            IntProgression intProgression3 = intProgression;
            n4 = intProgression3.getFirst();
            int n5 = intProgression3.getLast();
            int n6 = intProgression3.getStep();
            int n7 = n4;
            int n8 = n5;
            if (n6 >= 0 ? n7 <= n8 : n7 >= n8) {
                while (true) {
                    if (StringsKt.regionMatches((String)charSequence2, 0, (String)charSequence, n4, charSequence2.length(), bl)) {
                        return n4;
                    }
                    if (n4 != n5) {
                        n4 += n6;
                        continue;
                    }
                    break;
                }
            }
        } else {
            IntProgression intProgression4 = intProgression;
            n4 = intProgression4.getFirst();
            int n9 = intProgression4.getLast();
            int n10 = intProgression4.getStep();
            int n11 = n4;
            int n12 = n9;
            if (n10 >= 0 ? n11 <= n12 : n11 >= n12) {
                while (true) {
                    if (StringsKt.regionMatchesImpl(charSequence2, 0, charSequence, n4, charSequence2.length(), bl)) {
                        return n4;
                    }
                    if (n4 == n9) break;
                    n4 += n10;
                }
            }
        }
        return -1;
    }

    static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int n2, int n3, boolean bl, boolean bl2, int n4, Object object) {
        if ((n4 & 0x10) != 0) {
            bl2 = false;
        }
        return StringsKt__StringsKt.indexOf$StringsKt__StringsKt(charSequence, charSequence2, n2, n3, bl, bl2);
    }

    private static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(CharSequence charSequence, Collection<String> collection, int n2, boolean bl, boolean bl2) {
        IntProgression intProgression;
        IntProgression intProgression2;
        int n3;
        if (!bl && collection.size() == 1) {
            String string = (String)CollectionsKt.single((Iterable)collection);
            int n4 = !bl2 ? StringsKt.indexOf$default(charSequence, string, n2, false, 4, null) : StringsKt.lastIndexOf$default(charSequence, string, n2, false, 4, null);
            return n4 < 0 ? null : TuplesKt.to(n4, string);
        }
        if (!bl2) {
            n3 = RangesKt.coerceAtLeast(n2, 0);
            intProgression2 = new IntRange(n3, charSequence.length());
        } else {
            intProgression2 = intProgression = RangesKt.downTo(RangesKt.coerceAtMost(n2, StringsKt.getLastIndex(charSequence)), 0);
        }
        if (charSequence instanceof String) {
            IntProgression intProgression3 = intProgression;
            n3 = intProgression3.getFirst();
            int n5 = intProgression3.getLast();
            int n6 = intProgression3.getStep();
            int n7 = n3;
            int n8 = n5;
            if (n6 >= 0 ? n7 <= n8 : n7 >= n8) {
                while (true) {
                    Object v4;
                    block14: {
                        Iterable iterable = collection;
                        boolean bl3 = false;
                        for (Object t2 : iterable) {
                            String string = (String)t2;
                            boolean bl4 = false;
                            if (!StringsKt.regionMatches(string, 0, (String)charSequence, n3, string.length(), bl)) continue;
                            v4 = t2;
                            break block14;
                        }
                        v4 = null;
                    }
                    String string = v4;
                    if (string != null) {
                        return TuplesKt.to(n3, string);
                    }
                    if (n3 != n5) {
                        n3 += n6;
                        continue;
                    }
                    break;
                }
            }
        } else {
            IntProgression intProgression4 = intProgression;
            n3 = intProgression4.getFirst();
            int n9 = intProgression4.getLast();
            int n10 = intProgression4.getStep();
            int n11 = n3;
            int n12 = n9;
            if (n10 >= 0 ? n11 <= n12 : n11 >= n12) {
                while (true) {
                    Object v8;
                    block16: {
                        Iterable iterable = collection;
                        boolean bl5 = false;
                        for (Object t3 : iterable) {
                            String string = (String)t3;
                            boolean bl6 = false;
                            if (!StringsKt.regionMatchesImpl(string, 0, charSequence, n3, string.length(), bl)) continue;
                            v8 = t3;
                            break block16;
                        }
                        v8 = null;
                    }
                    String string = v8;
                    if (string != null) {
                        return TuplesKt.to(n3, string);
                    }
                    if (n3 == n9) break;
                    n3 += n10;
                }
            }
        }
        return null;
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$findAnyOf");
        Intrinsics.checkNotNullParameter(collection, "strings");
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, collection, n2, bl, false);
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.findAnyOf(charSequence, collection, n2, bl);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$findLastAnyOf");
        Intrinsics.checkNotNullParameter(collection, "strings");
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, collection, n2, bl, true);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = StringsKt.getLastIndex(charSequence);
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.findLastAnyOf(charSequence, collection, n2, bl);
    }

    public static final int indexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(collection, "strings");
        Serializable serializable = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, collection, n2, bl, false);
        return serializable != null && (serializable = ((Pair)serializable).getFirst()) != null ? (Integer)serializable : -1;
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOfAny(charSequence, collection, n2, bl);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int n2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(collection, "strings");
        Serializable serializable = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, collection, n2, bl, true);
        return serializable != null && (serializable = ((Pair)serializable).getFirst()) != null ? (Integer)serializable : -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = StringsKt.getLastIndex(charSequence);
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, collection, n2, bl);
    }

    public static final int indexOf(@NotNull CharSequence charSequence, char c2, int n2, boolean bl) {
        int n3;
        Intrinsics.checkNotNullParameter(charSequence, "$this$indexOf");
        if (bl || !(charSequence instanceof String)) {
            n3 = StringsKt.indexOfAny(charSequence, new char[]{c2}, n2, bl);
        } else {
            String string = (String)charSequence;
            boolean bl2 = false;
            char c3 = c2;
            boolean bl3 = false;
            n3 = string.indexOf(c3, n2);
        }
        return n3;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c2, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOf(charSequence, c2, n2, bl);
    }

    public static final int indexOf(@NotNull CharSequence charSequence, @NotNull String string, int n2, boolean bl) {
        int n3;
        Intrinsics.checkNotNullParameter(charSequence, "$this$indexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (bl || !(charSequence instanceof String)) {
            n3 = StringsKt__StringsKt.indexOf$StringsKt__StringsKt$default(charSequence, string, n2, charSequence.length(), bl, false, 16, null);
        } else {
            String string2 = (String)charSequence;
            boolean bl2 = false;
            n3 = string2.indexOf(string, n2);
        }
        return n3;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String string, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.indexOf(charSequence, string, n2, bl);
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, char c2, int n2, boolean bl) {
        int n3;
        Intrinsics.checkNotNullParameter(charSequence, "$this$lastIndexOf");
        if (bl || !(charSequence instanceof String)) {
            n3 = StringsKt.lastIndexOfAny(charSequence, new char[]{c2}, n2, bl);
        } else {
            String string = (String)charSequence;
            boolean bl2 = false;
            char c3 = c2;
            boolean bl3 = false;
            n3 = string.lastIndexOf(c3, n2);
        }
        return n3;
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c2, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = StringsKt.getLastIndex(charSequence);
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOf(charSequence, c2, n2, bl);
    }

    public static final int lastIndexOf(@NotNull CharSequence charSequence, @NotNull String string, int n2, boolean bl) {
        int n3;
        Intrinsics.checkNotNullParameter(charSequence, "$this$lastIndexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (bl || !(charSequence instanceof String)) {
            n3 = StringsKt__StringsKt.indexOf$StringsKt__StringsKt(charSequence, string, n2, 0, bl, true);
        } else {
            String string2 = (String)charSequence;
            boolean bl2 = false;
            n3 = string2.lastIndexOf(string, n2);
        }
        return n3;
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String string, int n2, boolean bl, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = StringsKt.getLastIndex(charSequence);
        }
        if ((n3 & 4) != 0) {
            bl = false;
        }
        return StringsKt.lastIndexOf(charSequence, string, n2, bl);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$contains");
        Intrinsics.checkNotNullParameter(charSequence2, "other");
        return charSequence2 instanceof String ? StringsKt.indexOf$default(charSequence, (String)charSequence2, 0, bl, 2, null) >= 0 : StringsKt__StringsKt.indexOf$StringsKt__StringsKt$default(charSequence, charSequence2, 0, charSequence.length(), bl, false, 16, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.contains(charSequence, charSequence2, bl);
    }

    public static final boolean contains(@NotNull CharSequence charSequence, char c2, boolean bl) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$contains");
        return StringsKt.indexOf$default(charSequence, c2, 0, bl, 2, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c2, boolean bl, int n2, Object object) {
        if ((n2 & 2) != 0) {
            bl = false;
        }
        return StringsKt.contains(charSequence, c2, bl);
    }

    @InlineOnly
    private static final boolean contains(CharSequence charSequence, Regex regex) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(charSequence, "$this$contains");
        return regex.containsMatchIn(charSequence);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence charSequence, char[] arrc, int n2, boolean bl, int n3) {
        boolean bl2 = n3 >= 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl2) {
            boolean bl5 = false;
            String string = "Limit must be non-negative, but was " + n3 + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        return new DelimitedRangesSequence(charSequence, n2, n3, (Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>>)new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>(arrc, bl){
            final /* synthetic */ char[] $delimiters;
            final /* synthetic */ boolean $ignoreCase;

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence charSequence, int n2) {
                Intrinsics.checkNotNullParameter(charSequence, "$receiver");
                int n3 = StringsKt.indexOfAny(charSequence, this.$delimiters, n2, this.$ignoreCase);
                boolean bl = false;
                boolean bl2 = false;
                int n4 = n3;
                boolean bl3 = false;
                return n4 < 0 ? null : TuplesKt.to(n4, 1);
            }
            {
                this.$delimiters = arrc;
                this.$ignoreCase = bl;
                super(2);
            }
        });
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] arrc, int n2, boolean bl, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            bl = false;
        }
        if ((n4 & 8) != 0) {
            n3 = 0;
        }
        return StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(charSequence, arrc, n2, bl, n3);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence charSequence, String[] arrstring, int n2, boolean bl, int n3) {
        boolean bl2 = n3 >= 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl2) {
            boolean bl5 = false;
            String string = "Limit must be non-negative, but was " + n3 + '.';
            throw (Throwable)new IllegalArgumentException(string.toString());
        }
        List<String> list = ArraysKt.asList(arrstring);
        return new DelimitedRangesSequence(charSequence, n2, n3, (Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>>)new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>(list, bl){
            final /* synthetic */ List $delimitersList;
            final /* synthetic */ boolean $ignoreCase;

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence charSequence, int n2) {
                Pair<A, Integer> pair;
                Intrinsics.checkNotNullParameter(charSequence, "$receiver");
                Pair pair2 = StringsKt__StringsKt.access$findAnyOf(charSequence, this.$delimitersList, n2, this.$ignoreCase, false);
                if (pair2 != null) {
                    Pair pair3 = pair2;
                    boolean bl = false;
                    boolean bl2 = false;
                    Pair pair4 = pair3;
                    boolean bl3 = false;
                    pair = TuplesKt.to(pair4.getFirst(), ((String)pair4.getSecond()).length());
                } else {
                    pair = null;
                }
                return pair;
            }
            {
                this.$delimitersList = list;
                this.$ignoreCase = bl;
                super(2);
            }
        });
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] arrstring, int n2, boolean bl, int n3, int n4, Object object) {
        if ((n4 & 2) != 0) {
            n2 = 0;
        }
        if ((n4 & 4) != 0) {
            bl = false;
        }
        if ((n4 & 8) != 0) {
            n3 = 0;
        }
        return StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt(charSequence, arrstring, n2, bl, n3);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence charSequence, @NotNull String[] arrstring, boolean bl, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(arrstring, "delimiters");
        return SequencesKt.map(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, arrstring, 0, bl, n2, 2, null), (Function1)new Function1<IntRange, String>(charSequence){
            final /* synthetic */ CharSequence $this_splitToSequence;

            @NotNull
            public final String invoke(@NotNull IntRange intRange) {
                Intrinsics.checkNotNullParameter(intRange, "it");
                return StringsKt.substring(this.$this_splitToSequence, intRange);
            }
            {
                this.$this_splitToSequence = charSequence;
                super(1);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] arrstring, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            bl = false;
        }
        if ((n3 & 4) != 0) {
            n2 = 0;
        }
        return StringsKt.splitToSequence(charSequence, arrstring, bl, n2);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull String[] arrstring, boolean bl, int n2) {
        Object object;
        Intrinsics.checkNotNullParameter(charSequence, "$this$split");
        Intrinsics.checkNotNullParameter(arrstring, "delimiters");
        if (arrstring.length == 1) {
            object = arrstring[0];
            CharSequence charSequence2 = (CharSequence)object;
            boolean bl2 = false;
            if (!(charSequence2.length() == 0)) {
                return StringsKt__StringsKt.split$StringsKt__StringsKt(charSequence, (String)object, bl, n2);
            }
        }
        object = SequencesKt.asIterable(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, arrstring, 0, bl, n2, 2, null));
        boolean bl3 = false;
        Object object2 = object;
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object, 10));
        boolean bl4 = false;
        Iterator iterator2 = object2.iterator();
        while (iterator2.hasNext()) {
            Object t2 = iterator2.next();
            IntRange intRange = (IntRange)t2;
            Collection collection2 = collection;
            boolean bl5 = false;
            String string = StringsKt.substring(charSequence, intRange);
            collection2.add(string);
        }
        return (List)collection;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, String[] arrstring, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            bl = false;
        }
        if ((n3 & 4) != 0) {
            n2 = 0;
        }
        return StringsKt.split(charSequence, arrstring, bl, n2);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull CharSequence charSequence, @NotNull char[] arrc, boolean bl, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(arrc, "delimiters");
        return SequencesKt.map(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, arrc, 0, bl, n2, 2, null), (Function1)new Function1<IntRange, String>(charSequence){
            final /* synthetic */ CharSequence $this_splitToSequence;

            @NotNull
            public final String invoke(@NotNull IntRange intRange) {
                Intrinsics.checkNotNullParameter(intRange, "it");
                return StringsKt.substring(this.$this_splitToSequence, intRange);
            }
            {
                this.$this_splitToSequence = charSequence;
                super(1);
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] arrc, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            bl = false;
        }
        if ((n3 & 4) != 0) {
            n2 = 0;
        }
        return StringsKt.splitToSequence(charSequence, arrc, bl, n2);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence charSequence, @NotNull char[] arrc, boolean bl, int n2) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$split");
        Intrinsics.checkNotNullParameter(arrc, "delimiters");
        if (arrc.length == 1) {
            return StringsKt__StringsKt.split$StringsKt__StringsKt(charSequence, String.valueOf(arrc[0]), bl, n2);
        }
        Iterable iterable = SequencesKt.asIterable(StringsKt__StringsKt.rangesDelimitedBy$StringsKt__StringsKt$default(charSequence, arrc, 0, bl, n2, 2, null));
        boolean bl2 = false;
        Iterable iterable2 = iterable;
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        boolean bl3 = false;
        for (Object t2 : iterable2) {
            IntRange intRange = (IntRange)t2;
            Collection collection2 = collection;
            boolean bl4 = false;
            String string = StringsKt.substring(charSequence, intRange);
            collection2.add(string);
        }
        return (List)collection;
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, char[] arrc, boolean bl, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            bl = false;
        }
        if ((n3 & 4) != 0) {
            n2 = 0;
        }
        return StringsKt.split(charSequence, arrc, bl, n2);
    }

    private static final List<String> split$StringsKt__StringsKt(CharSequence charSequence, String string, boolean bl, int n2) {
        int n3;
        CharSequence charSequence2;
        int n4 = n2 >= 0 ? 1 : 0;
        int n5 = 0;
        boolean bl2 = false;
        if (n4 == 0) {
            boolean bl3 = false;
            String string2 = "Limit must be non-negative, but was " + n2 + '.';
            throw (Throwable)new IllegalArgumentException(string2.toString());
        }
        n4 = 0;
        n5 = StringsKt.indexOf(charSequence, string, n4, bl);
        if (n5 == -1 || n2 == 1) {
            return CollectionsKt.listOf(((Object)charSequence).toString());
        }
        bl2 = n2 > 0;
        ArrayList<String> arrayList = new ArrayList<String>(bl2 ? RangesKt.coerceAtMost(n2, 10) : 10);
        do {
            charSequence2 = charSequence;
            n3 = 0;
            arrayList.add(((Object)charSequence2.subSequence(n4, n5)).toString());
            n4 = n5 + string.length();
        } while ((!bl2 || arrayList.size() != n2 - 1) && (n5 = StringsKt.indexOf(charSequence, string, n4, bl)) != -1);
        charSequence2 = charSequence;
        n3 = charSequence.length();
        boolean bl4 = false;
        arrayList.add(((Object)charSequence2.subSequence(n4, n3)).toString());
        return arrayList;
    }

    @InlineOnly
    private static final List<String> split(CharSequence charSequence, Regex regex, int n2) {
        int n3 = 0;
        return regex.split(charSequence, n2);
    }

    static /* synthetic */ List split$default(CharSequence charSequence, Regex regex, int n2, int n3, Object object) {
        if ((n3 & 2) != 0) {
            n2 = 0;
        }
        n3 = 0;
        return regex.split(charSequence, n2);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$lineSequence");
        return StringsKt.splitToSequence$default(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "$this$lines");
        return SequencesKt.toList(StringsKt.lineSequence(charSequence));
    }

    public static final boolean contentEqualsIgnoreCaseImpl(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        if (charSequence instanceof String && charSequence2 instanceof String) {
            return StringsKt.equals((String)charSequence, (String)charSequence2, true);
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || charSequence.length() != charSequence2.length()) {
            return false;
        }
        int n2 = charSequence.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (CharsKt.equals(charSequence.charAt(i2), charSequence2.charAt(i2), true)) continue;
            return false;
        }
        return true;
    }

    public static final boolean contentEqualsImpl(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        if (charSequence instanceof String && charSequence2 instanceof String) {
            return Intrinsics.areEqual(charSequence, charSequence2);
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || charSequence.length() != charSequence2.length()) {
            return false;
        }
        int n2 = charSequence.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (charSequence.charAt(i2) == charSequence2.charAt(i2)) continue;
            return false;
        }
        return true;
    }

    @SinceKotlin(version="1.5")
    public static final boolean toBooleanStrict(@NotNull String string) {
        boolean bl;
        Intrinsics.checkNotNullParameter(string, "$this$toBooleanStrict");
        switch (string) {
            case "true": {
                bl = true;
                break;
            }
            case "false": {
                bl = false;
                break;
            }
            default: {
                throw (Throwable)new IllegalArgumentException("The string doesn't represent a boolean value: " + string);
            }
        }
        return bl;
    }

    @SinceKotlin(version="1.5")
    @Nullable
    public static final Boolean toBooleanStrictOrNull(@NotNull String string) {
        Boolean bl;
        Intrinsics.checkNotNullParameter(string, "$this$toBooleanStrictOrNull");
        switch (string) {
            case "true": {
                bl = true;
                break;
            }
            case "false": {
                bl = false;
                break;
            }
            default: {
                bl = null;
            }
        }
        return bl;
    }

    public static final /* synthetic */ Pair access$findAnyOf(CharSequence charSequence, Collection collection, int n2, boolean bl, boolean bl2) {
        return StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(charSequence, collection, n2, bl, bl2);
    }
}

