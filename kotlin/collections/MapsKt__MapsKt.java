/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyMap;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000~\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a`\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00052%\b\u0001\u0010\u0006\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\b\nH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aX\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032%\b\u0001\u0010\u0006\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\b\nH\u0087\b\u00f8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\u001a1\u0010\f\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001a_\u0010\f\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0012\u001a1\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001a_\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0016\u001a!\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001aO\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0018\u001a!\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001aO\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00a2\u0006\u0002\u0010\u0018\u001a*\u0010\u001a\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\n\u00a2\u0006\u0002\u0010\u001c\u001a*\u0010\u001d\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\n\u00a2\u0006\u0002\u0010\u001c\u001a9\u0010\u001e\u001a\u00020\u001f\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u0010\"\u001a1\u0010#\u001a\u00020\u001f\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b *\u000e\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u0010\"\u001a7\u0010$\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\t\b\u0001\u0010\u0003\u00a2\u0006\u0002\b *\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010%\u001a\u0002H\u0003H\u0087\b\u00a2\u0006\u0002\u0010\"\u001aV\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aJ\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aV\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001aq\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001aq\u0010.\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001aJ\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001f0\u0007H\u0086\b\u00f8\u0001\u0000\u001a;\u00100\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u00101\u001aC\u00102\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001aC\u00106\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0080\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001aC\u00107\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u00022\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u000304H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105\u001a1\u00108\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0007\u00a2\u0006\u0002\u00101\u001a?\u00109\u001a\u0002H:\"\u0014\b\u0000\u0010+*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001*\u0002H:\"\u0004\b\u0001\u0010:*\u0002H+2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H:04H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;\u001a'\u0010<\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u001a:\u0010=\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a9\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b0?\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001a<\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030A0@\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0087\n\u00a2\u0006\u0002\bB\u001a\\\u0010C\u001a\u000e\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u001aw\u0010E\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H:\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001a\\\u0010F\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H:0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u001aw\u0010G\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H:0\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+2\u001e\u0010D\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001b\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u001a@\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001a\u0002H\u0002H\u0087\u0002\u00a2\u0006\u0002\u0010I\u001aH\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u000e\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\u0002\u00a2\u0006\u0002\u0010K\u001aA\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\u0002\u001aA\u0010H\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\u0002\u001a2\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u0002H\u0087\n\u00a2\u0006\u0002\u0010O\u001a:\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u000e\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\n\u00a2\u0006\u0002\u0010P\u001a3\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\n\u001a3\u0010N\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\n\u001a0\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0000\u001a3\u0010R\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u001aT\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0086\u0002\u00a2\u0006\u0002\u0010T\u001aG\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0086\u0002\u001aM\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0086\u0002\u001aI\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010V\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0086\u0002\u001aM\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0086\u0002\u001aJ\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0087\n\u00a2\u0006\u0002\u0010X\u001a=\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0087\n\u001aC\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0087\n\u001a=\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001aC\u0010W\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0087\n\u001aG\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001a\u0010\u000f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00a2\u0006\u0002\u0010X\u001a@\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001a@\u0010Y\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001a;\u0010Z\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00a2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u0002H\u0087\b\u00a2\u0006\u0002\u00101\u001a:\u0010[\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001a\u0002H\u00022\u0006\u0010%\u001a\u0002H\u0003H\u0087\n\u00a2\u0006\u0002\u0010\\\u001a;\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00a2\u0006\u0002\u0010\u0018\u001aQ\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u00102\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010^\u001a4\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001aO\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L2\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010_\u001a2\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001aM\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001a\u0002H+H\u0007\u00a2\u0006\u0002\u0010`\u001a4\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001aO\u0010]\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M2\u0006\u0010,\u001a\u0002H+\u00a2\u0006\u0002\u0010a\u001a2\u0010b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001a1\u0010c\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006d"}, d2={"buildMap", "", "K", "V", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptyMap", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "filter", "predicate", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "ifEmpty", "R", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt__MapsKt
extends MapsKt__MapsJVMKt {
    @NotNull
    public static final <K, V> Map<K, V> emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        if (emptyMap == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        return emptyMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> mapOf(Pair<? extends K, ? extends V> ... arrpair) {
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        return arrpair.length > 0 ? MapsKt.toMap(arrpair, (Map)new LinkedHashMap(MapsKt.mapCapacity(arrpair.length))) : MapsKt.emptyMap();
    }

    @InlineOnly
    private static final <K, V> Map<K, V> mapOf() {
        int n2 = 0;
        return MapsKt.emptyMap();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> Map<K, V> mutableMapOf() {
        int n2 = 0;
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> Map<K, V> mutableMapOf(Pair<? extends K, ? extends V> ... arrpair) {
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(arrpair.length));
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)linkedHashMap2, arrpair);
        return linkedHashMap;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> HashMap<K, V> hashMapOf() {
        int n2 = 0;
        return new HashMap();
    }

    @NotNull
    public static final <K, V> HashMap<K, V> hashMapOf(Pair<? extends K, ? extends V> ... arrpair) {
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        HashMap hashMap = new HashMap(MapsKt.mapCapacity(arrpair.length));
        boolean bl = false;
        boolean bl2 = false;
        HashMap hashMap2 = hashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)hashMap2, arrpair);
        return hashMap;
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> LinkedHashMap<K, V> linkedMapOf() {
        int n2 = 0;
        return new LinkedHashMap();
    }

    @NotNull
    public static final <K, V> LinkedHashMap<K, V> linkedMapOf(Pair<? extends K, ? extends V> ... arrpair) {
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        return (LinkedHashMap)MapsKt.toMap(arrpair, (Map)new LinkedHashMap(MapsKt.mapCapacity(arrpair.length)));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(@BuilderInference Function1<? super Map<K, V>, Unit> function1) {
        int n2 = 0;
        boolean bl = false;
        bl = false;
        Map map = MapsKt.createMapBuilder();
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(map);
        return MapsKt.build(map);
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(int n2, @BuilderInference Function1<? super Map<K, V>, Unit> function1) {
        int n3 = 0;
        boolean bl = false;
        bl = false;
        Map map = MapsKt.createMapBuilder(n2);
        boolean bl2 = false;
        boolean bl3 = false;
        function1.invoke(map);
        return MapsKt.build(map);
    }

    @InlineOnly
    private static final <K, V> boolean isNotEmpty(Map<? extends K, ? extends V> map) {
        int n2 = 0;
        return !map.isEmpty();
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <K, V> boolean isNullOrEmpty(Map<? extends K, ? extends V> map) {
        int n2 = 0;
        boolean bl = false;
        return map == null || map.isEmpty();
    }

    @InlineOnly
    private static final <K, V> Map<K, V> orEmpty(Map<K, ? extends V> map) {
        int n2 = 0;
        Map<K, Object> map2 = map;
        if (map2 == null) {
            map2 = MapsKt.emptyMap();
        }
        return map2;
    }

    @SinceKotlin(version="1.3")
    @InlineOnly
    private static final <M extends Map<?, ?> & R, R> R ifEmpty(M m2, Function0<? extends R> function0) {
        int n2 = 0;
        return (R)(m2.isEmpty() ? function0.invoke() : m2);
    }

    @InlineOnly
    private static final <K, V> boolean contains(Map<? extends K, ? extends V> map, K k2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$contains");
        Map<K, V> map2 = map;
        boolean bl = false;
        return map2.containsKey(k2);
    }

    @InlineOnly
    private static final <K, V> V get(Map<? extends K, ? extends V> map, K k2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$get");
        return map.get(k2);
    }

    @InlineOnly
    private static final <K, V> void set(Map<K, V> map, K k2, V v2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$set");
        map.put(k2, v2);
    }

    @InlineOnly
    private static final <K> boolean containsKey(Map<? extends K, ?> map, K k2) {
        int n2 = 0;
        Map<K, ?> map2 = map;
        if (map2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
        }
        return map2.containsKey(k2);
    }

    @InlineOnly
    private static final <K, V> boolean containsValue(Map<K, ? extends V> map, V v2) {
        int n2 = 0;
        return map.containsValue(v2);
    }

    @InlineOnly
    private static final <K, V> V remove(Map<? extends K, V> map, K k2) {
        int n2 = 0;
        Map<? extends K, V> map2 = map;
        if (map2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }
        return TypeIntrinsics.asMutableMap(map2).remove(k2);
    }

    @InlineOnly
    private static final <K, V> K component1(Map.Entry<? extends K, ? extends V> entry) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(entry, "$this$component1");
        return entry.getKey();
    }

    @InlineOnly
    private static final <K, V> V component2(Map.Entry<? extends K, ? extends V> entry) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(entry, "$this$component2");
        return entry.getValue();
    }

    @InlineOnly
    private static final <K, V> Pair<K, V> toPair(Map.Entry<? extends K, ? extends V> entry) {
        int n2 = 0;
        return new Pair<K, V>(entry.getKey(), entry.getValue());
    }

    @InlineOnly
    private static final <K, V> V getOrElse(Map<K, ? extends V> map, K k2, Function0<? extends V> function0) {
        int n2 = 0;
        V v2 = map.get(k2);
        if (v2 == null) {
            v2 = function0.invoke();
        }
        return v2;
    }

    public static final <K, V> V getOrElseNullable(@NotNull Map<K, ? extends V> map, K k2, @NotNull Function0<? extends V> function0) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$getOrElseNullable");
        Intrinsics.checkNotNullParameter(function0, "defaultValue");
        V v2 = map.get(k2);
        if (v2 == null && !map.containsKey(k2)) {
            return function0.invoke();
        }
        return v2;
    }

    @SinceKotlin(version="1.1")
    public static final <K, V> V getValue(@NotNull Map<K, ? extends V> map, K k2) {
        Intrinsics.checkNotNullParameter(map, "$this$getValue");
        return MapsKt.getOrImplicitDefaultNullable(map, k2);
    }

    public static final <K, V> V getOrPut(@NotNull Map<K, V> map, K k2, @NotNull Function0<? extends V> function0) {
        V v2;
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$getOrPut");
        Intrinsics.checkNotNullParameter(function0, "defaultValue");
        V v3 = map.get(k2);
        if (v3 == null) {
            V v4 = function0.invoke();
            map.put(k2, v4);
            v2 = v4;
        } else {
            v2 = v3;
        }
        return v2;
    }

    @InlineOnly
    private static final <K, V> Iterator<Map.Entry<K, V>> iterator(Map<? extends K, ? extends V> map) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$iterator");
        return map.entrySet().iterator();
    }

    @JvmName(name="mutableIterator")
    @InlineOnly
    private static final <K, V> Iterator<Map.Entry<K, V>> mutableIterator(Map<K, V> map) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$iterator");
        return map.entrySet().iterator();
    }

    @NotNull
    public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m2, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$mapValuesTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Iterable iterable = map.entrySet();
        boolean bl = false;
        for (Object t2 : iterable) {
            Map.Entry entry = (Map.Entry)t2;
            M m3 = m2;
            boolean bl2 = false;
            Object k2 = entry.getKey();
            m3.put(k2, function1.invoke((Map.Entry<K, V>)t2));
        }
        return m2;
    }

    @NotNull
    public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m2, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$mapKeysTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Iterable iterable = map.entrySet();
        boolean bl = false;
        for (Object t2 : iterable) {
            Map.Entry entry = (Map.Entry)t2;
            R r2 = function1.invoke((Map.Entry<K, V>)t2);
            M m3 = m2;
            boolean bl2 = false;
            Object v2 = entry.getValue();
            m3.put(r2, v2);
        }
        return m2;
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Pair<? extends K, ? extends V>[] arrpair) {
        Intrinsics.checkNotNullParameter(map, "$this$putAll");
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        for (Pair<K, V> pair : arrpair) {
            K k2 = pair.component1();
            V v2 = pair.component2();
            map.put(k2, v2);
        }
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkNotNullParameter(map, "$this$putAll");
        Intrinsics.checkNotNullParameter(iterable, "pairs");
        for (Pair<K, V> pair : iterable) {
            K k2 = pair.component1();
            V v2 = pair.component2();
            map.put(k2, v2);
        }
    }

    public static final <K, V> void putAll(@NotNull Map<? super K, ? super V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkNotNullParameter(map, "$this$putAll");
        Intrinsics.checkNotNullParameter(sequence, "pairs");
        Iterator<Pair<K, V>> iterator2 = sequence.iterator();
        while (iterator2.hasNext()) {
            Pair<K, V> pair = iterator2.next();
            K k2 = pair.component1();
            V v2 = pair.component2();
            map.put(k2, v2);
        }
    }

    @NotNull
    public static final <K, V, R> Map<K, R> mapValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$mapValues");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Map<K, V> map2 = map;
        Map map3 = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        boolean bl = false;
        Iterable iterable = map2.entrySet();
        boolean bl2 = false;
        for (Object t2 : iterable) {
            Map.Entry entry = (Map.Entry)t2;
            Map map4 = map3;
            boolean bl3 = false;
            Object k2 = entry.getKey();
            map4.put(k2, function1.invoke((Map.Entry<K, V>)t2));
        }
        return map3;
    }

    @NotNull
    public static final <K, V, R> Map<R, V> mapKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$mapKeys");
        Intrinsics.checkNotNullParameter(function1, "transform");
        Map<K, V> map2 = map;
        Map map3 = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        boolean bl = false;
        Iterable iterable = map2.entrySet();
        boolean bl2 = false;
        for (Object t2 : iterable) {
            Map.Entry entry = (Map.Entry)t2;
            R r2 = function1.invoke((Map.Entry<K, V>)t2);
            Map map4 = map3;
            boolean bl3 = false;
            Object v2 = entry.getValue();
            map4.put(r2, v2);
        }
        return map3;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterKeys(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super K, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filterKeys");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<K, V>();
        Map<K, V> map2 = map;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (!function1.invoke(entry.getKey()).booleanValue()) continue;
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterValues(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super V, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filterValues");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<K, V>();
        Map<K, V> map2 = map;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (!function1.invoke(entry.getValue()).booleanValue()) continue;
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m2, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filterTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Map<K, V> map2 = map;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (!function1.invoke(entry).booleanValue()) continue;
            m2.put(entry.getKey(), entry.getValue());
        }
        return m2;
    }

    @NotNull
    public static final <K, V> Map<K, V> filter(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filter");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Map<K, V> map2 = map;
        Map map3 = new LinkedHashMap();
        boolean bl = false;
        Map<K, V> map4 = map2;
        boolean bl2 = false;
        for (Map.Entry<K, V> entry : map4.entrySet()) {
            if (!function1.invoke(entry).booleanValue()) continue;
            map3.put(entry.getKey(), entry.getValue());
        }
        return map3;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(@NotNull Map<? extends K, ? extends V> map, @NotNull M m2, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filterNotTo");
        Intrinsics.checkNotNullParameter(m2, "destination");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Map<K, V> map2 = map;
        boolean bl = false;
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (function1.invoke(entry).booleanValue()) continue;
            m2.put(entry.getKey(), entry.getValue());
        }
        return m2;
    }

    @NotNull
    public static final <K, V> Map<K, V> filterNot(@NotNull Map<? extends K, ? extends V> map, @NotNull Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> function1) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$filterNot");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Map<K, V> map2 = map;
        Map map3 = new LinkedHashMap();
        boolean bl = false;
        Map<K, V> map4 = map2;
        boolean bl2 = false;
        for (Map.Entry<K, V> entry : map4.entrySet()) {
            if (function1.invoke(entry).booleanValue()) continue;
            map3.put(entry.getKey(), entry.getValue());
        }
        return map3;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "$this$toMap");
        if (iterable instanceof Collection) {
            Map map;
            switch (((Collection)iterable).size()) {
                case 0: {
                    map = MapsKt.emptyMap();
                    break;
                }
                case 1: {
                    map = MapsKt.mapOf(iterable instanceof List ? (Pair)((List)iterable).get(0) : iterable.iterator().next());
                    break;
                }
                default: {
                    map = MapsKt.toMap(iterable, (Map)new LinkedHashMap(MapsKt.mapCapacity(((Collection)iterable).size())));
                }
            }
            return map;
        }
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(iterable, (Map)new LinkedHashMap()));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable, @NotNull M m2) {
        Intrinsics.checkNotNullParameter(iterable, "$this$toMap");
        Intrinsics.checkNotNullParameter(m2, "destination");
        M m3 = m2;
        boolean bl = false;
        boolean bl2 = false;
        M m4 = m3;
        boolean bl3 = false;
        MapsKt.putAll(m4, iterable);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Pair<? extends K, ? extends V>[] arrpair) {
        Map<Object, Object> map;
        Intrinsics.checkNotNullParameter(arrpair, "$this$toMap");
        switch (arrpair.length) {
            case 0: {
                map = MapsKt.emptyMap();
                break;
            }
            case 1: {
                map = MapsKt.mapOf(arrpair[0]);
                break;
            }
            default: {
                map = MapsKt.toMap(arrpair, (Map)new LinkedHashMap(MapsKt.mapCapacity(arrpair.length)));
            }
        }
        return map;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Pair<? extends K, ? extends V>[] arrpair, @NotNull M m2) {
        Intrinsics.checkNotNullParameter(arrpair, "$this$toMap");
        Intrinsics.checkNotNullParameter(m2, "destination");
        M m3 = m2;
        boolean bl = false;
        boolean bl2 = false;
        M m4 = m3;
        boolean bl3 = false;
        MapsKt.putAll(m4, arrpair);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "$this$toMap");
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(sequence, (Map)new LinkedHashMap()));
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence, @NotNull M m2) {
        Intrinsics.checkNotNullParameter(sequence, "$this$toMap");
        Intrinsics.checkNotNullParameter(m2, "destination");
        M m3 = m2;
        boolean bl = false;
        boolean bl2 = false;
        M m4 = m3;
        boolean bl3 = false;
        MapsKt.putAll(m4, sequence);
        return m3;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> toMap(@NotNull Map<? extends K, ? extends V> map) {
        Map<Object, Object> map2;
        Intrinsics.checkNotNullParameter(map, "$this$toMap");
        switch (map.size()) {
            case 0: {
                map2 = MapsKt.emptyMap();
                break;
            }
            case 1: {
                map2 = MapsKt.toSingletonMap(map);
                break;
            }
            default: {
                map2 = MapsKt.toMutableMap(map);
            }
        }
        return map2;
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> toMutableMap(@NotNull Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "$this$toMutableMap");
        return new LinkedHashMap<K, V>(map);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(@NotNull Map<? extends K, ? extends V> map, @NotNull M m2) {
        Intrinsics.checkNotNullParameter(map, "$this$toMap");
        Intrinsics.checkNotNullParameter(m2, "destination");
        M m3 = m2;
        boolean bl = false;
        boolean bl2 = false;
        M m4 = m3;
        boolean bl3 = false;
        m4.putAll(map);
        return m3;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V> pair) {
        Map map2;
        Intrinsics.checkNotNullParameter(map, "$this$plus");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if (map.isEmpty()) {
            map2 = MapsKt.mapOf(pair);
        } else {
            LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<K, V>(map);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<K, V> linkedHashMap2 = linkedHashMap;
            boolean bl3 = false;
            linkedHashMap2.put(pair.getFirst(), pair.getSecond());
            map2 = linkedHashMap;
        }
        return map2;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        Map map2;
        Intrinsics.checkNotNullParameter(map, "$this$plus");
        Intrinsics.checkNotNullParameter(iterable, "pairs");
        if (map.isEmpty()) {
            map2 = MapsKt.toMap(iterable);
        } else {
            LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>(map);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<? extends K, ? extends V> linkedHashMap2 = linkedHashMap;
            boolean bl3 = false;
            MapsKt.putAll((Map)linkedHashMap2, iterable);
            map2 = linkedHashMap;
        }
        return map2;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Pair<? extends K, ? extends V>[] arrpair) {
        Map map2;
        Intrinsics.checkNotNullParameter(map, "$this$plus");
        Intrinsics.checkNotNullParameter(arrpair, "pairs");
        if (map.isEmpty()) {
            map2 = MapsKt.toMap(arrpair);
        } else {
            LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>(map);
            boolean bl = false;
            boolean bl2 = false;
            LinkedHashMap<? extends K, ? extends V> linkedHashMap2 = linkedHashMap;
            boolean bl3 = false;
            MapsKt.putAll((Map)linkedHashMap2, arrpair);
            map2 = linkedHashMap;
        }
        return map2;
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        Intrinsics.checkNotNullParameter(map, "$this$plus");
        Intrinsics.checkNotNullParameter(sequence, "pairs");
        LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>(map);
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap<? extends K, ? extends V> linkedHashMap2 = linkedHashMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)linkedHashMap2, sequence);
        return MapsKt.optimizeReadOnlyMap((Map)linkedHashMap);
    }

    @NotNull
    public static final <K, V> Map<K, V> plus(@NotNull Map<? extends K, ? extends V> map, @NotNull Map<? extends K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(map, "$this$plus");
        Intrinsics.checkNotNullParameter(map2, "map");
        LinkedHashMap<? extends K, ? extends V> linkedHashMap = new LinkedHashMap<K, V>(map);
        boolean bl = false;
        boolean bl2 = false;
        LinkedHashMap<? extends K, ? extends V> linkedHashMap2 = linkedHashMap;
        boolean bl3 = false;
        linkedHashMap2.putAll(map2);
        return linkedHashMap;
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> map, Pair<? extends K, ? extends V> pair) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$plusAssign");
        map.put(pair.getFirst(), pair.getSecond());
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> map, Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$plusAssign");
        MapsKt.putAll(map, iterable);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] arrpair) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$plusAssign");
        MapsKt.putAll(map, arrpair);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> map, Sequence<? extends Pair<? extends K, ? extends V>> sequence) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$plusAssign");
        MapsKt.putAll(map, sequence);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> map, Map<K, ? extends V> map2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$plusAssign");
        map.putAll(map2);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, K k2) {
        Intrinsics.checkNotNullParameter(map, "$this$minus");
        Map<K, V> map2 = MapsKt.toMutableMap(map);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> map3 = map2;
        boolean bl3 = false;
        Map<K, V> map4 = map3;
        K k3 = k2;
        boolean bl4 = false;
        map4.remove(k3);
        return MapsKt.optimizeReadOnlyMap(map2);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Iterable<? extends K> iterable) {
        Intrinsics.checkNotNullParameter(map, "$this$minus");
        Intrinsics.checkNotNullParameter(iterable, "keys");
        Map<K, V> map2 = MapsKt.toMutableMap(map);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> map3 = map2;
        boolean bl3 = false;
        Map<K, V> map4 = map3;
        Iterable<? extends K> iterable2 = iterable;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map4.keySet(), iterable2);
        return MapsKt.optimizeReadOnlyMap(map2);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull K[] arrK) {
        Intrinsics.checkNotNullParameter(map, "$this$minus");
        Intrinsics.checkNotNullParameter(arrK, "keys");
        Map<K, V> map2 = MapsKt.toMutableMap(map);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> map3 = map2;
        boolean bl3 = false;
        Map<K, V> map4 = map3;
        K[] arrK2 = arrK;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map4.keySet(), arrK2);
        return MapsKt.optimizeReadOnlyMap(map2);
    }

    @SinceKotlin(version="1.1")
    @NotNull
    public static final <K, V> Map<K, V> minus(@NotNull Map<? extends K, ? extends V> map, @NotNull Sequence<? extends K> sequence) {
        Intrinsics.checkNotNullParameter(map, "$this$minus");
        Intrinsics.checkNotNullParameter(sequence, "keys");
        Map<K, V> map2 = MapsKt.toMutableMap(map);
        boolean bl = false;
        boolean bl2 = false;
        Map<K, V> map3 = map2;
        boolean bl3 = false;
        Map<K, V> map4 = map3;
        Sequence<? extends K> sequence2 = sequence;
        boolean bl4 = false;
        CollectionsKt.removeAll((Collection)map4.keySet(), sequence2);
        return MapsKt.optimizeReadOnlyMap(map2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> map, K k2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$minusAssign");
        map.remove(k2);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> map, Iterable<? extends K> iterable) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)map.keySet(), iterable);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> map, K[] arrK) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)map.keySet(), arrK);
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> map, Sequence<? extends K> sequence) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(map, "$this$minusAssign");
        CollectionsKt.removeAll((Collection)map.keySet(), sequence);
    }

    @NotNull
    public static final <K, V> Map<K, V> optimizeReadOnlyMap(@NotNull Map<K, ? extends V> map) {
        Map<K, Object> map2;
        Intrinsics.checkNotNullParameter(map, "$this$optimizeReadOnlyMap");
        switch (map.size()) {
            case 0: {
                map2 = MapsKt.emptyMap();
                break;
            }
            case 1: {
                Map<K, V> map3 = map;
                boolean bl = false;
                map2 = MapsKt.toSingletonMap(map3);
                break;
            }
            default: {
                map2 = map;
            }
        }
        return map2;
    }
}

