/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.CharProgression;
import kotlin.ranges.CharRange;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongProgression;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.ranges.RangesKt__RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000n\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001\u00a2\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007\u001a\u0012\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0012\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n\u001a'\u0010\u000b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001\u00a2\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u0012\u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0012\u0010\u000b\u001a\u00020\n*\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a3\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\f\u001a\u0004\u0018\u0001H\u0001\u00a2\u0006\u0002\u0010\u000e\u001a/\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0007\u00a2\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012\u00a2\u0006\u0002\u0010\u0013\u001a\u001a\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u001a\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u001a\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0018\u0010\r\u001a\u00020\b*\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u001a\u001a\u0010\r\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0018\u0010\r\u001a\u00020\t*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0087\n\u00a2\u0006\u0002\u0010\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002\u00a2\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002\u00a2\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002\u00a2\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002\u00a2\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002\u00a2\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002\u00a2\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002\u00a2\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002\u00a2\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002\u00a2\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002\u00a2\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002\u00a2\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002\u00a2\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002\u00a2\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002\u00a2\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002\u00a2\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002\u00a2\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002\u00a2\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002\u00a2\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002\u00a2\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002\u00a2\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002\u00a2\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002\u00a2\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002\u00a2\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002\u00a2\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002\u00a2\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002\u00a2\u0006\u0002\b \u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020!2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0087\n\u00a2\u0006\u0002\u0010\"\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020#2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0087\n\u00a2\u0006\u0002\u0010$\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020)*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\r\u0010*\u001a\u00020\u0018*\u00020\u0016H\u0087\b\u001a\u0014\u0010*\u001a\u00020\u0018*\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\b*\u00020!H\u0087\b\u001a\u0014\u0010*\u001a\u00020\b*\u00020!2\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\t*\u00020#H\u0087\b\u001a\u0014\u0010*\u001a\u00020\t*\u00020#2\u0006\u0010*\u001a\u00020+H\u0007\u001a\u0014\u0010,\u001a\u0004\u0018\u00010\u0018*\u00020\u0016H\u0087\b\u00a2\u0006\u0002\u0010-\u001a\u001b\u0010,\u001a\u0004\u0018\u00010\u0018*\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0007\u00a2\u0006\u0002\u0010.\u001a\u0014\u0010,\u001a\u0004\u0018\u00010\b*\u00020!H\u0087\b\u00a2\u0006\u0002\u0010/\u001a\u001b\u0010,\u001a\u0004\u0018\u00010\b*\u00020!2\u0006\u0010*\u001a\u00020+H\u0007\u00a2\u0006\u0002\u00100\u001a\u0014\u0010,\u001a\u0004\u0018\u00010\t*\u00020#H\u0087\b\u00a2\u0006\u0002\u00101\u001a\u001b\u0010,\u001a\u0004\u0018\u00010\t*\u00020#2\u0006\u0010*\u001a\u00020+H\u0007\u00a2\u0006\u0002\u00102\u001a\n\u00103\u001a\u00020)*\u00020)\u001a\n\u00103\u001a\u00020&*\u00020&\u001a\n\u00103\u001a\u00020(*\u00020(\u001a\u0015\u00104\u001a\u00020)*\u00020)2\u0006\u00104\u001a\u00020\bH\u0086\u0004\u001a\u0015\u00104\u001a\u00020&*\u00020&2\u0006\u00104\u001a\u00020\bH\u0086\u0004\u001a\u0015\u00104\u001a\u00020(*\u00020(2\u0006\u00104\u001a\u00020\tH\u0086\u0004\u001a\u0013\u00105\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0000\u00a2\u0006\u0002\u00106\u001a\u0013\u00105\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000\u00a2\u0006\u0002\u00107\u001a\u0013\u00105\u001a\u0004\u0018\u00010\u0005*\u00020\bH\u0000\u00a2\u0006\u0002\u00108\u001a\u0013\u00105\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000\u00a2\u0006\u0002\u00109\u001a\u0013\u00105\u001a\u0004\u0018\u00010\u0005*\u00020\nH\u0000\u00a2\u0006\u0002\u0010:\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0000\u00a2\u0006\u0002\u0010<\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0000\u00a2\u0006\u0002\u0010=\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000\u00a2\u0006\u0002\u0010>\u001a\u0013\u0010?\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000\u00a2\u0006\u0002\u0010@\u001a\u0013\u0010?\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0000\u00a2\u0006\u0002\u0010A\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\n*\u00020\u0006H\u0000\u00a2\u0006\u0002\u0010C\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\n*\u00020\u0007H\u0000\u00a2\u0006\u0002\u0010D\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\n*\u00020\bH\u0000\u00a2\u0006\u0002\u0010E\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\n*\u00020\tH\u0000\u00a2\u0006\u0002\u0010F\u001a\u0015\u0010G\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020\u0016*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020#*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010G\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u00a8\u0006H"}, d2={"coerceAtLeast", "T", "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "Lkotlin/ranges/CharRange;", "element", "", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "value", "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "Lkotlin/ranges/LongRange;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "random", "Lkotlin/random/Random;", "randomOrNull", "(Lkotlin/ranges/CharRange;)Ljava/lang/Character;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)Ljava/lang/Character;", "(Lkotlin/ranges/IntRange;)Ljava/lang/Integer;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;)Ljava/lang/Long;", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)Ljava/lang/Long;", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "kotlin-stdlib"}, xs="kotlin/ranges/RangesKt")
class RangesKt___RangesKt
extends RangesKt__RangesKt {
    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final int random(IntRange intRange) {
        int n2 = 0;
        return RangesKt.random(intRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final long random(LongRange longRange) {
        int n2 = 0;
        return RangesKt.random(longRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final char random(CharRange charRange) {
        int n2 = 0;
        return RangesKt.random(charRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.3")
    public static final int random(@NotNull IntRange intRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(intRange, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return RandomKt.nextInt(random, intRange);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new NoSuchElementException(illegalArgumentException.getMessage());
        }
    }

    @SinceKotlin(version="1.3")
    public static final long random(@NotNull LongRange longRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(longRange, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            return RandomKt.nextLong(random, longRange);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new NoSuchElementException(illegalArgumentException.getMessage());
        }
    }

    @SinceKotlin(version="1.3")
    public static final char random(@NotNull CharRange charRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(charRange, "$this$random");
        Intrinsics.checkNotNullParameter(random, "random");
        try {
            char c2 = charRange.getFirst();
            boolean bl = false;
            char c3 = c2;
            c2 = charRange.getLast();
            bl = false;
            return (char)random.nextInt(c3, c2 + '\u0001');
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new NoSuchElementException(illegalArgumentException.getMessage());
        }
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Integer randomOrNull(IntRange intRange) {
        int n2 = 0;
        return RangesKt.randomOrNull(intRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Long randomOrNull(LongRange longRange) {
        int n2 = 0;
        return RangesKt.randomOrNull(longRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @InlineOnly
    private static final Character randomOrNull(CharRange charRange) {
        int n2 = 0;
        return RangesKt.randomOrNull(charRange, (Random)Random.Default);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @Nullable
    public static final Integer randomOrNull(@NotNull IntRange intRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(intRange, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (intRange.isEmpty()) {
            return null;
        }
        return RandomKt.nextInt(random, intRange);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @Nullable
    public static final Long randomOrNull(@NotNull LongRange longRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(longRange, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (longRange.isEmpty()) {
            return null;
        }
        return RandomKt.nextLong(random, longRange);
    }

    @SinceKotlin(version="1.4")
    @WasExperimental(markerClass={ExperimentalStdlibApi.class})
    @Nullable
    public static final Character randomOrNull(@NotNull CharRange charRange, @NotNull Random random) {
        Intrinsics.checkNotNullParameter(charRange, "$this$randomOrNull");
        Intrinsics.checkNotNullParameter(random, "random");
        if (charRange.isEmpty()) {
            return null;
        }
        char c2 = charRange.getFirst();
        boolean bl = false;
        char c3 = c2;
        c2 = charRange.getLast();
        bl = false;
        return Character.valueOf((char)random.nextInt(c3, c2 + '\u0001'));
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final boolean contains(IntRange intRange, Integer n2) {
        int n3 = 0;
        Intrinsics.checkNotNullParameter(intRange, "$this$contains");
        return n2 != null && intRange.contains(n2);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final boolean contains(LongRange longRange, Long l2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(longRange, "$this$contains");
        return l2 != null && longRange.contains(l2);
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final boolean contains(CharRange charRange, Character c2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(charRange, "$this$contains");
        return c2 != null && charRange.contains(c2.charValue());
    }

    @JvmName(name="intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, byte by) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Integer)((Comparable)Integer.valueOf(by)));
    }

    @JvmName(name="longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, byte by) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Long)((Comparable)Long.valueOf(by)));
    }

    @JvmName(name="shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, byte by) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Short)((Comparable)Short.valueOf(by)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange<Double> closedRange, byte by) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Double)((Comparable)Double.valueOf(by)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange<Float> closedRange, byte by) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Float)((Comparable)Float.valueOf(by)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="intRangeContains")
    public static final /* synthetic */ boolean intRangeContains(ClosedRange<Integer> closedRange, double d2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Integer n2 = RangesKt.toIntExactOrNull(d2);
        boolean bl = false;
        boolean bl2 = false;
        Integer n3 = n2;
        boolean bl3 = false;
        return n3 != null ? closedRange.contains((Integer)((Comparable)n3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="longRangeContains")
    public static final /* synthetic */ boolean longRangeContains(ClosedRange<Long> closedRange, double d2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Long l2 = RangesKt.toLongExactOrNull(d2);
        boolean bl = false;
        boolean bl2 = false;
        Long l3 = l2;
        boolean bl3 = false;
        return l3 != null ? closedRange.contains((Long)((Comparable)l3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="byteRangeContains")
    public static final /* synthetic */ boolean byteRangeContains(ClosedRange<Byte> closedRange, double d2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Byte by = RangesKt.toByteExactOrNull(d2);
        boolean bl = false;
        boolean bl2 = false;
        Byte by2 = by;
        boolean bl3 = false;
        return by2 != null ? closedRange.contains((Byte)((Comparable)by2)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="shortRangeContains")
    public static final /* synthetic */ boolean shortRangeContains(ClosedRange<Short> closedRange, double d2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Short s2 = RangesKt.toShortExactOrNull(d2);
        boolean bl = false;
        boolean bl2 = false;
        Short s3 = s2;
        boolean bl3 = false;
        return s3 != null ? closedRange.contains((Short)((Comparable)s3)) : false;
    }

    @JvmName(name="floatRangeContains")
    public static final boolean floatRangeContains(@NotNull ClosedRange<Float> closedRange, double d2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Float)((Comparable)Float.valueOf((float)d2)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="intRangeContains")
    public static final /* synthetic */ boolean intRangeContains(ClosedRange<Integer> closedRange, float f2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Integer n2 = RangesKt.toIntExactOrNull(f2);
        boolean bl = false;
        boolean bl2 = false;
        Integer n3 = n2;
        boolean bl3 = false;
        return n3 != null ? closedRange.contains((Integer)((Comparable)n3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="longRangeContains")
    public static final /* synthetic */ boolean longRangeContains(ClosedRange<Long> closedRange, float f2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Long l2 = RangesKt.toLongExactOrNull(f2);
        boolean bl = false;
        boolean bl2 = false;
        Long l3 = l2;
        boolean bl3 = false;
        return l3 != null ? closedRange.contains((Long)((Comparable)l3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="byteRangeContains")
    public static final /* synthetic */ boolean byteRangeContains(ClosedRange<Byte> closedRange, float f2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Byte by = RangesKt.toByteExactOrNull(f2);
        boolean bl = false;
        boolean bl2 = false;
        Byte by2 = by;
        boolean bl3 = false;
        return by2 != null ? closedRange.contains((Byte)((Comparable)by2)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="shortRangeContains")
    public static final /* synthetic */ boolean shortRangeContains(ClosedRange<Short> closedRange, float f2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Short s2 = RangesKt.toShortExactOrNull(f2);
        boolean bl = false;
        boolean bl2 = false;
        Short s3 = s2;
        boolean bl3 = false;
        return s3 != null ? closedRange.contains((Short)((Comparable)s3)) : false;
    }

    @JvmName(name="doubleRangeContains")
    public static final boolean doubleRangeContains(@NotNull ClosedRange<Double> closedRange, float f2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Double)((Comparable)Double.valueOf(f2)));
    }

    @JvmName(name="longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, int n2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Long)((Comparable)Long.valueOf(n2)));
    }

    @JvmName(name="byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, int n2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Byte by = RangesKt.toByteExactOrNull(n2);
        boolean bl = false;
        boolean bl2 = false;
        Byte by2 = by;
        boolean bl3 = false;
        return by2 != null ? closedRange.contains((Byte)((Comparable)by2)) : false;
    }

    @JvmName(name="shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, int n2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Short s2 = RangesKt.toShortExactOrNull(n2);
        boolean bl = false;
        boolean bl2 = false;
        Short s3 = s2;
        boolean bl3 = false;
        return s3 != null ? closedRange.contains((Short)((Comparable)s3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange<Double> closedRange, int n2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Double)((Comparable)Double.valueOf(n2)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange<Float> closedRange, int n2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Float)((Comparable)Float.valueOf(n2)));
    }

    @JvmName(name="intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, long l2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Integer n2 = RangesKt.toIntExactOrNull(l2);
        boolean bl = false;
        boolean bl2 = false;
        Integer n3 = n2;
        boolean bl3 = false;
        return n3 != null ? closedRange.contains((Integer)((Comparable)n3)) : false;
    }

    @JvmName(name="byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, long l2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Byte by = RangesKt.toByteExactOrNull(l2);
        boolean bl = false;
        boolean bl2 = false;
        Byte by2 = by;
        boolean bl3 = false;
        return by2 != null ? closedRange.contains((Byte)((Comparable)by2)) : false;
    }

    @JvmName(name="shortRangeContains")
    public static final boolean shortRangeContains(@NotNull ClosedRange<Short> closedRange, long l2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Short s2 = RangesKt.toShortExactOrNull(l2);
        boolean bl = false;
        boolean bl2 = false;
        Short s3 = s2;
        boolean bl3 = false;
        return s3 != null ? closedRange.contains((Short)((Comparable)s3)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange<Double> closedRange, long l2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Double)((Comparable)Double.valueOf(l2)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange<Float> closedRange, long l2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Float)((Comparable)Float.valueOf(l2)));
    }

    @JvmName(name="intRangeContains")
    public static final boolean intRangeContains(@NotNull ClosedRange<Integer> closedRange, short s2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Integer)((Comparable)Integer.valueOf(s2)));
    }

    @JvmName(name="longRangeContains")
    public static final boolean longRangeContains(@NotNull ClosedRange<Long> closedRange, short s2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Long)((Comparable)Long.valueOf(s2)));
    }

    @JvmName(name="byteRangeContains")
    public static final boolean byteRangeContains(@NotNull ClosedRange<Byte> closedRange, short s2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        Byte by = RangesKt.toByteExactOrNull(s2);
        boolean bl = false;
        boolean bl2 = false;
        Byte by2 = by;
        boolean bl3 = false;
        return by2 != null ? closedRange.contains((Byte)((Comparable)by2)) : false;
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange<Double> closedRange, short s2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Double)((Comparable)Double.valueOf(s2)));
    }

    @Deprecated(message="This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(warningSince="1.3", errorSince="1.4", hiddenSince="1.5")
    @JvmName(name="floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange<Float> closedRange, short s2) {
        Intrinsics.checkNotNullParameter(closedRange, "$this$contains");
        return closedRange.contains((Float)((Comparable)Float.valueOf(s2)));
    }

    @NotNull
    public static final IntProgression downTo(int n2, byte by) {
        return IntProgression.Companion.fromClosedRange(n2, by, -1);
    }

    @NotNull
    public static final LongProgression downTo(long l2, byte by) {
        return LongProgression.Companion.fromClosedRange(l2, by, -1L);
    }

    @NotNull
    public static final IntProgression downTo(byte by, byte by2) {
        return IntProgression.Companion.fromClosedRange(by, by2, -1);
    }

    @NotNull
    public static final IntProgression downTo(short s2, byte by) {
        return IntProgression.Companion.fromClosedRange(s2, by, -1);
    }

    @NotNull
    public static final CharProgression downTo(char c2, char c3) {
        return CharProgression.Companion.fromClosedRange(c2, c3, -1);
    }

    @NotNull
    public static final IntProgression downTo(int n2, int n3) {
        return IntProgression.Companion.fromClosedRange(n2, n3, -1);
    }

    @NotNull
    public static final LongProgression downTo(long l2, int n2) {
        return LongProgression.Companion.fromClosedRange(l2, n2, -1L);
    }

    @NotNull
    public static final IntProgression downTo(byte by, int n2) {
        return IntProgression.Companion.fromClosedRange(by, n2, -1);
    }

    @NotNull
    public static final IntProgression downTo(short s2, int n2) {
        return IntProgression.Companion.fromClosedRange(s2, n2, -1);
    }

    @NotNull
    public static final LongProgression downTo(int n2, long l2) {
        return LongProgression.Companion.fromClosedRange(n2, l2, -1L);
    }

    @NotNull
    public static final LongProgression downTo(long l2, long l3) {
        return LongProgression.Companion.fromClosedRange(l2, l3, -1L);
    }

    @NotNull
    public static final LongProgression downTo(byte by, long l2) {
        return LongProgression.Companion.fromClosedRange(by, l2, -1L);
    }

    @NotNull
    public static final LongProgression downTo(short s2, long l2) {
        return LongProgression.Companion.fromClosedRange(s2, l2, -1L);
    }

    @NotNull
    public static final IntProgression downTo(int n2, short s2) {
        return IntProgression.Companion.fromClosedRange(n2, s2, -1);
    }

    @NotNull
    public static final LongProgression downTo(long l2, short s2) {
        return LongProgression.Companion.fromClosedRange(l2, s2, -1L);
    }

    @NotNull
    public static final IntProgression downTo(byte by, short s2) {
        return IntProgression.Companion.fromClosedRange(by, s2, -1);
    }

    @NotNull
    public static final IntProgression downTo(short s2, short s3) {
        return IntProgression.Companion.fromClosedRange(s2, s3, -1);
    }

    @NotNull
    public static final IntProgression reversed(@NotNull IntProgression intProgression) {
        Intrinsics.checkNotNullParameter(intProgression, "$this$reversed");
        return IntProgression.Companion.fromClosedRange(intProgression.getLast(), intProgression.getFirst(), -intProgression.getStep());
    }

    @NotNull
    public static final LongProgression reversed(@NotNull LongProgression longProgression) {
        Intrinsics.checkNotNullParameter(longProgression, "$this$reversed");
        return LongProgression.Companion.fromClosedRange(longProgression.getLast(), longProgression.getFirst(), -longProgression.getStep());
    }

    @NotNull
    public static final CharProgression reversed(@NotNull CharProgression charProgression) {
        Intrinsics.checkNotNullParameter(charProgression, "$this$reversed");
        return CharProgression.Companion.fromClosedRange(charProgression.getLast(), charProgression.getFirst(), -charProgression.getStep());
    }

    @NotNull
    public static final IntProgression step(@NotNull IntProgression intProgression, int n2) {
        Intrinsics.checkNotNullParameter(intProgression, "$this$step");
        RangesKt.checkStepIsPositive(n2 > 0, n2);
        return IntProgression.Companion.fromClosedRange(intProgression.getFirst(), intProgression.getLast(), intProgression.getStep() > 0 ? n2 : -n2);
    }

    @NotNull
    public static final LongProgression step(@NotNull LongProgression longProgression, long l2) {
        Intrinsics.checkNotNullParameter(longProgression, "$this$step");
        RangesKt.checkStepIsPositive(l2 > 0L, l2);
        return LongProgression.Companion.fromClosedRange(longProgression.getFirst(), longProgression.getLast(), longProgression.getStep() > 0L ? l2 : -l2);
    }

    @NotNull
    public static final CharProgression step(@NotNull CharProgression charProgression, int n2) {
        Intrinsics.checkNotNullParameter(charProgression, "$this$step");
        RangesKt.checkStepIsPositive(n2 > 0, n2);
        return CharProgression.Companion.fromClosedRange(charProgression.getFirst(), charProgression.getLast(), charProgression.getStep() > 0 ? n2 : -n2);
    }

    @Nullable
    public static final Byte toByteExactOrNull(int n2) {
        int n3 = n2;
        return -128 <= n3 && 127 >= n3 ? Byte.valueOf((byte)n2) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(long l2) {
        long l3 = l2;
        return (long)-128 <= l3 && (long)127 >= l3 ? Byte.valueOf((byte)l2) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(short s2) {
        short s3 = s2;
        return (short)-128 <= s3 && (short)127 >= s3 ? Byte.valueOf((byte)s2) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(double d2) {
        double d3 = -128;
        double d4 = 127;
        double d5 = d2;
        return d5 >= d3 && d5 <= d4 ? Byte.valueOf((byte)d2) : null;
    }

    @Nullable
    public static final Byte toByteExactOrNull(float f2) {
        float f3 = -128;
        float f4 = 127;
        float f5 = f2;
        return f5 >= f3 && f5 <= f4 ? Byte.valueOf((byte)f2) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(long l2) {
        long l3 = l2;
        return (long)Integer.MIN_VALUE <= l3 && (long)Integer.MAX_VALUE >= l3 ? Integer.valueOf((int)l2) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(double d2) {
        double d3 = Integer.MIN_VALUE;
        double d4 = Integer.MAX_VALUE;
        double d5 = d2;
        return d5 >= d3 && d5 <= d4 ? Integer.valueOf((int)d2) : null;
    }

    @Nullable
    public static final Integer toIntExactOrNull(float f2) {
        float f3 = Integer.MIN_VALUE;
        float f4 = Integer.MAX_VALUE;
        float f5 = f2;
        return f5 >= f3 && f5 <= f4 ? Integer.valueOf((int)f2) : null;
    }

    @Nullable
    public static final Long toLongExactOrNull(double d2) {
        double d3 = Long.MIN_VALUE;
        double d4 = Long.MAX_VALUE;
        double d5 = d2;
        return d5 >= d3 && d5 <= d4 ? Long.valueOf((long)d2) : null;
    }

    @Nullable
    public static final Long toLongExactOrNull(float f2) {
        float f3 = Long.MIN_VALUE;
        float f4 = Long.MAX_VALUE;
        float f5 = f2;
        return f5 >= f3 && f5 <= f4 ? Long.valueOf((long)f2) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(int n2) {
        int n3 = n2;
        return -32768 <= n3 && 32767 >= n3 ? Short.valueOf((short)n2) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(long l2) {
        long l3 = l2;
        return (long)-32768 <= l3 && (long)32767 >= l3 ? Short.valueOf((short)l2) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(double d2) {
        double d3 = -32768;
        double d4 = 32767;
        double d5 = d2;
        return d5 >= d3 && d5 <= d4 ? Short.valueOf((short)d2) : null;
    }

    @Nullable
    public static final Short toShortExactOrNull(float f2) {
        float f3 = -32768;
        float f4 = 32767;
        float f5 = f2;
        return f5 >= f3 && f5 <= f4 ? Short.valueOf((short)f2) : null;
    }

    @NotNull
    public static final IntRange until(int n2, byte by) {
        int n3 = n2;
        return new IntRange(n3, by - 1);
    }

    @NotNull
    public static final LongRange until(long l2, byte by) {
        long l3 = l2;
        return new LongRange(l3, (long)by - 1L);
    }

    @NotNull
    public static final IntRange until(byte by, byte by2) {
        byte by3 = by;
        return new IntRange(by3, by2 - 1);
    }

    @NotNull
    public static final IntRange until(short s2, byte by) {
        short s3 = s2;
        return new IntRange(s3, by - 1);
    }

    @NotNull
    public static final CharRange until(char c2, char c3) {
        if (Intrinsics.compare(c3, 0) <= 0) {
            return CharRange.Companion.getEMPTY();
        }
        char c4 = c2;
        return new CharRange(c4, (char)(c3 - '\u0001'));
    }

    @NotNull
    public static final IntRange until(int n2, int n3) {
        if (n3 <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        int n4 = n2;
        return new IntRange(n4, n3 - 1);
    }

    @NotNull
    public static final LongRange until(long l2, int n2) {
        long l3 = l2;
        return new LongRange(l3, (long)n2 - 1L);
    }

    @NotNull
    public static final IntRange until(byte by, int n2) {
        if (n2 <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        byte by2 = by;
        return new IntRange(by2, n2 - 1);
    }

    @NotNull
    public static final IntRange until(short s2, int n2) {
        if (n2 <= Integer.MIN_VALUE) {
            return IntRange.Companion.getEMPTY();
        }
        short s3 = s2;
        return new IntRange(s3, n2 - 1);
    }

    @NotNull
    public static final LongRange until(int n2, long l2) {
        if (l2 <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        long l3 = n2;
        return new LongRange(l3, l2 - 1L);
    }

    @NotNull
    public static final LongRange until(long l2, long l3) {
        if (l3 <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        long l4 = l2;
        return new LongRange(l4, l3 - 1L);
    }

    @NotNull
    public static final LongRange until(byte by, long l2) {
        if (l2 <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        long l3 = by;
        return new LongRange(l3, l2 - 1L);
    }

    @NotNull
    public static final LongRange until(short s2, long l2) {
        if (l2 <= Long.MIN_VALUE) {
            return LongRange.Companion.getEMPTY();
        }
        long l3 = s2;
        return new LongRange(l3, l2 - 1L);
    }

    @NotNull
    public static final IntRange until(int n2, short s2) {
        int n3 = n2;
        return new IntRange(n3, s2 - 1);
    }

    @NotNull
    public static final LongRange until(long l2, short s2) {
        long l3 = l2;
        return new LongRange(l3, (long)s2 - 1L);
    }

    @NotNull
    public static final IntRange until(byte by, short s2) {
        byte by2 = by;
        return new IntRange(by2, s2 - 1);
    }

    @NotNull
    public static final IntRange until(short s2, short s3) {
        short s4 = s2;
        return new IntRange(s4, s3 - 1);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtLeast(@NotNull T t2, @NotNull T t3) {
        Intrinsics.checkNotNullParameter(t2, "$this$coerceAtLeast");
        Intrinsics.checkNotNullParameter(t3, "minimumValue");
        return t2.compareTo(t3) < 0 ? t3 : t2;
    }

    public static final byte coerceAtLeast(byte by, byte by2) {
        return by < by2 ? by2 : by;
    }

    public static final short coerceAtLeast(short s2, short s3) {
        return s2 < s3 ? s3 : s2;
    }

    public static final int coerceAtLeast(int n2, int n3) {
        return n2 < n3 ? n3 : n2;
    }

    public static final long coerceAtLeast(long l2, long l3) {
        return l2 < l3 ? l3 : l2;
    }

    public static final float coerceAtLeast(float f2, float f3) {
        return f2 < f3 ? f3 : f2;
    }

    public static final double coerceAtLeast(double d2, double d3) {
        return d2 < d3 ? d3 : d2;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceAtMost(@NotNull T t2, @NotNull T t3) {
        Intrinsics.checkNotNullParameter(t2, "$this$coerceAtMost");
        Intrinsics.checkNotNullParameter(t3, "maximumValue");
        return t2.compareTo(t3) > 0 ? t3 : t2;
    }

    public static final byte coerceAtMost(byte by, byte by2) {
        return by > by2 ? by2 : by;
    }

    public static final short coerceAtMost(short s2, short s3) {
        return s2 > s3 ? s3 : s2;
    }

    public static final int coerceAtMost(int n2, int n3) {
        return n2 > n3 ? n3 : n2;
    }

    public static final long coerceAtMost(long l2, long l3) {
        return l2 > l3 ? l3 : l2;
    }

    public static final float coerceAtMost(float f2, float f3) {
        return f2 > f3 ? f3 : f2;
    }

    public static final double coerceAtMost(double d2, double d3) {
        return d2 > d3 ? d3 : d2;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T t2, @Nullable T t3, @Nullable T t4) {
        Intrinsics.checkNotNullParameter(t2, "$this$coerceIn");
        if (t3 != null && t4 != null) {
            if (t3.compareTo(t4) > 0) {
                throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + t4 + " is less than minimum " + t3 + '.');
            }
            if (t2.compareTo(t3) < 0) {
                return t3;
            }
            if (t2.compareTo(t4) > 0) {
                return t4;
            }
        } else {
            if (t3 != null && t2.compareTo(t3) < 0) {
                return t3;
            }
            if (t4 != null && t2.compareTo(t4) > 0) {
                return t4;
            }
        }
        return t2;
    }

    public static final byte coerceIn(byte by, byte by2, byte by3) {
        if (by2 > by3) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + by3 + " is less than minimum " + by2 + '.');
        }
        if (by < by2) {
            return by2;
        }
        if (by > by3) {
            return by3;
        }
        return by;
    }

    public static final short coerceIn(short s2, short s3, short s4) {
        if (s3 > s4) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + s4 + " is less than minimum " + s3 + '.');
        }
        if (s2 < s3) {
            return s3;
        }
        if (s2 > s4) {
            return s4;
        }
        return s2;
    }

    public static final int coerceIn(int n2, int n3, int n4) {
        if (n3 > n4) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + n4 + " is less than minimum " + n3 + '.');
        }
        if (n2 < n3) {
            return n3;
        }
        if (n2 > n4) {
            return n4;
        }
        return n2;
    }

    public static final long coerceIn(long l2, long l3, long l4) {
        if (l3 > l4) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + l4 + " is less than minimum " + l3 + '.');
        }
        if (l2 < l3) {
            return l3;
        }
        if (l2 > l4) {
            return l4;
        }
        return l2;
    }

    public static final float coerceIn(float f2, float f3, float f4) {
        if (f3 > f4) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f4 + " is less than minimum " + f3 + '.');
        }
        if (f2 < f3) {
            return f3;
        }
        if (f2 > f4) {
            return f4;
        }
        return f2;
    }

    public static final double coerceIn(double d2, double d3, double d4) {
        if (d3 > d4) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d4 + " is less than minimum " + d3 + '.');
        }
        if (d2 < d3) {
            return d3;
        }
        if (d2 > d4) {
            return d4;
        }
        return d2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T t2, @NotNull ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.checkNotNullParameter(t2, "$this$coerceIn");
        Intrinsics.checkNotNullParameter(closedFloatingPointRange, "range");
        if (closedFloatingPointRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedFloatingPointRange + '.');
        }
        return closedFloatingPointRange.lessThanOrEquals(t2, (T)closedFloatingPointRange.getStart()) && !closedFloatingPointRange.lessThanOrEquals((T)closedFloatingPointRange.getStart(), t2) ? closedFloatingPointRange.getStart() : (closedFloatingPointRange.lessThanOrEquals((T)closedFloatingPointRange.getEndInclusive(), t2) && !closedFloatingPointRange.lessThanOrEquals(t2, (T)closedFloatingPointRange.getEndInclusive()) ? closedFloatingPointRange.getEndInclusive() : t2);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T coerceIn(@NotNull T object, @NotNull ClosedRange<T> closedRange) {
        Intrinsics.checkNotNullParameter(object, "$this$coerceIn");
        Intrinsics.checkNotNullParameter(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return RangesKt.coerceIn(object, (ClosedFloatingPointRange)closedRange);
        }
        if (closedRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        }
        return (T)(object.compareTo(closedRange.getStart()) < 0 ? closedRange.getStart() : (object.compareTo(closedRange.getEndInclusive()) > 0 ? closedRange.getEndInclusive() : object));
    }

    public static final int coerceIn(int n2, @NotNull ClosedRange<Integer> closedRange) {
        Intrinsics.checkNotNullParameter(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number)((Object)RangesKt.coerceIn((Comparable)Integer.valueOf(n2), (ClosedFloatingPointRange)closedRange))).intValue();
        }
        if (closedRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        }
        return n2 < ((Number)closedRange.getStart()).intValue() ? ((Number)closedRange.getStart()).intValue() : (n2 > ((Number)closedRange.getEndInclusive()).intValue() ? ((Number)closedRange.getEndInclusive()).intValue() : n2);
    }

    public static final long coerceIn(long l2, @NotNull ClosedRange<Long> closedRange) {
        Intrinsics.checkNotNullParameter(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number)((Object)RangesKt.coerceIn((Comparable)Long.valueOf(l2), (ClosedFloatingPointRange)closedRange))).longValue();
        }
        if (closedRange.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        }
        return l2 < ((Number)closedRange.getStart()).longValue() ? ((Number)closedRange.getStart()).longValue() : (l2 > ((Number)closedRange.getEndInclusive()).longValue() ? ((Number)closedRange.getEndInclusive()).longValue() : l2);
    }
}

