/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.io.path;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.CloseableKt;
import kotlin.io.path.ExperimentalPathApi;
import kotlin.io.path.PathRelativizer;
import kotlin.io.path.PathsKt;
import kotlin.io.path.PathsKt__PathReadWriteKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 5, 1}, k=5, xi=1, d1={"\u0000\u00b2\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0001H\u0087\b\u001a*\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001a\"\u00020\u0001H\u0087\b\u00a2\u0006\u0002\u0010\u001b\u001a?\u0010\u001c\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007\u00a2\u0006\u0002\u0010!\u001a6\u0010\u001c\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u0010\"\u001aK\u0010#\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0007\u00a2\u0006\u0002\u0010%\u001aB\u0010#\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00012\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u0010&\u001a\u001c\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u00022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*H\u0001\u001a\r\u0010+\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010,\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a.\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\u0087\b\u00a2\u0006\u0002\u00101\u001a\u001f\u0010-\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\u0087\b\u001a.\u00104\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u00105\u001a.\u00106\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u00105\u001a.\u00107\u001a\u00020\u0002*\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u00105\u001a\u0015\u00108\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\u0087\b\u001a6\u00109\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u001a\u0010\u001f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030 0\u001a\"\u0006\u0012\u0002\b\u00030 H\u0087\b\u00a2\u0006\u0002\u0010:\u001a\r\u0010;\u001a\u00020<*\u00020\u0002H\u0087\b\u001a\r\u0010=\u001a\u000203*\u00020\u0002H\u0087\b\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\u0087\n\u001a\u0015\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010?\u001a\u00020\u0001H\u0087\n\u001a&\u0010@\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010B\u001a2\u0010C\u001a\u0002HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010F\u001a4\u0010G\u001a\u0004\u0018\u0001HD\"\n\b\u0000\u0010D\u0018\u0001*\u00020E*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010F\u001a\r\u0010H\u001a\u00020I*\u00020\u0002H\u0087\b\u001a\r\u0010J\u001a\u00020K*\u00020\u0002H\u0087\b\u001a.\u0010L\u001a\u00020<*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020<0OH\u0087\b\u00f8\u0001\u0000\u001a0\u0010P\u001a\u0004\u0018\u00010Q*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010S\u001a&\u0010T\u001a\u00020U*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010V\u001a(\u0010W\u001a\u0004\u0018\u00010X*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010Y\u001a,\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010]\u001a&\u0010^\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010B\u001a\r\u0010_\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010`\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010a\u001a\u000203*\u00020\u0002H\u0087\b\u001a&\u0010b\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010B\u001a\u0015\u0010c\u001a\u000203*\u00020\u00022\u0006\u0010?\u001a\u00020\u0002H\u0087\b\u001a\r\u0010d\u001a\u000203*\u00020\u0002H\u0087\b\u001a\r\u0010e\u001a\u000203*\u00020\u0002H\u0087\b\u001a\u001c\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00020g*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u0001H\u0007\u001a.\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u0002000\u001a\"\u000200H\u0087\b\u00a2\u0006\u0002\u00101\u001a\u001f\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u00102\u001a\u000203H\u0087\b\u001a&\u0010i\u001a\u000203*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010B\u001a2\u0010j\u001a\u0002Hk\"\n\b\u0000\u0010k\u0018\u0001*\u00020l*\u00020\u00022\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010m\u001a<\u0010j\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010Q0n*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010o\u001a\r\u0010p\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0014\u0010q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0016\u0010r\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a\u0014\u0010s\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0002H\u0007\u001a8\u0010t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010R\u001a\u00020\u00012\b\u0010u\u001a\u0004\u0018\u00010Q2\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0\u001a\"\u00020AH\u0087\b\u00a2\u0006\u0002\u0010v\u001a\u0015\u0010w\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020UH\u0087\b\u001a\u0015\u0010x\u001a\u00020\u0002*\u00020\u00022\u0006\u0010u\u001a\u00020XH\u0087\b\u001a\u001b\u0010y\u001a\u00020\u0002*\u00020\u00022\f\u0010u\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0087\b\u001a\r\u0010z\u001a\u00020\u0002*\u00020{H\u0087\b\u001a@\u0010|\u001a\u0002H}\"\u0004\b\u0000\u0010}*\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00012\u0018\u0010~\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u007f\u0012\u0004\u0012\u0002H}0OH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0080\u0001\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\u0001*\u00020\u00028\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\"\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"\u001e\u0010\u0010\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006\"\u001f\u0010\u0013\u001a\u00020\u0001*\u00020\u00028\u00c6\u0002X\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0014\u0010\u0004\u001a\u0004\b\u0015\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u0081\u0001"}, d2={"extension", "", "Ljava/nio/file/Path;", "getExtension$annotations", "(Ljava/nio/file/Path;)V", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "getInvariantSeparatorsPath", "invariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "getInvariantSeparatorsPathString", "name", "getName$annotations", "getName", "nameWithoutExtension", "getNameWithoutExtension$annotations", "getNameWithoutExtension", "pathString", "getPathString$annotations", "getPathString", "Path", "path", "base", "subpaths", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "createTempDirectory", "directory", "prefix", "attributes", "Ljava/nio/file/attribute/FileAttribute;", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createTempFile", "suffix", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "fileAttributeViewNotAvailable", "", "attributeViewClass", "Ljava/lang/Class;", "absolute", "absolutePathString", "copyTo", "target", "options", "Ljava/nio/file/CopyOption;", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "overwrite", "", "createDirectories", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectory", "createFile", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "deleteExisting", "", "deleteIfExists", "div", "other", "exists", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "fileAttributesView", "V", "Ljava/nio/file/attribute/FileAttributeView;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesViewOrNull", "fileSize", "", "fileStore", "Ljava/nio/file/FileStore;", "forEachDirectoryEntry", "glob", "action", "Lkotlin/Function1;", "getAttribute", "", "attribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "getLastModifiedTime", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "getOwner", "Ljava/nio/file/attribute/UserPrincipal;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "getPosixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "isDirectory", "isExecutable", "isHidden", "isReadable", "isRegularFile", "isSameFileAs", "isSymbolicLink", "isWritable", "listDirectoryEntries", "", "moveTo", "notExists", "readAttributes", "A", "Ljava/nio/file/attribute/BasicFileAttributes;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "readSymbolicLink", "relativeTo", "relativeToOrNull", "relativeToOrSelf", "setAttribute", "value", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "setLastModifiedTime", "setOwner", "setPosixFilePermissions", "toPath", "Ljava/net/URI;", "useDirectoryEntries", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, xs="kotlin/io/path/PathsKt")
class PathsKt__PathUtilsKt
extends PathsKt__PathReadWriteKt {
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static /* synthetic */ void getName$annotations(Path path) {
    }

    @NotNull
    public static final String getName(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "$this$name");
        Path path2 = path.getFileName();
        String string = path2 != null ? ((Object)path2).toString() : null;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            string2 = "";
        }
        return string2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static /* synthetic */ void getNameWithoutExtension$annotations(Path path) {
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "$this$nameWithoutExtension");
        Object object = path.getFileName();
        if (object == null || (object = object.toString()) == null || (object = StringsKt.substringBeforeLast$default((String)object, ".", null, 2, null)) == null) {
            object = "";
        }
        return object;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static /* synthetic */ void getExtension$annotations(Path path) {
    }

    @NotNull
    public static final String getExtension(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "$this$extension");
        Object object = path.getFileName();
        if (object == null || (object = object.toString()) == null || (object = StringsKt.substringAfterLast((String)object, '.', "")) == null) {
            object = "";
        }
        return object;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    public static /* synthetic */ void getPathString$annotations(Path path) {
    }

    private static final String getPathString(Path path) {
        int n2 = 0;
        return ((Object)path).toString();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path path) {
    }

    @NotNull
    public static final String getInvariantSeparatorsPathString(@NotNull Path path) {
        String string;
        Intrinsics.checkNotNullParameter(path, "$this$invariantSeparatorsPathString");
        FileSystem fileSystem = path.getFileSystem();
        Intrinsics.checkNotNullExpressionValue(fileSystem, "fileSystem");
        String string2 = fileSystem.getSeparator();
        if (Intrinsics.areEqual(string2, "/") ^ true) {
            String string3 = ((Object)path).toString();
            String string4 = string2;
            Intrinsics.checkNotNullExpressionValue(string4, "separator");
            string = StringsKt.replace$default(string3, string4, "/", false, 4, null);
        } else {
            string = ((Object)path).toString();
        }
        return string;
    }

    @Deprecated(message="Use invariantSeparatorsPathString property instead.", replaceWith=@ReplaceWith(imports={}, expression="invariantSeparatorsPathString"), level=DeprecationLevel.ERROR)
    @SinceKotlin(version="1.4")
    @ExperimentalPathApi
    @InlineOnly
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path path) {
    }

    private static final String getInvariantSeparatorsPath(Path path) {
        int n2 = 0;
        return PathsKt.getInvariantSeparatorsPathString(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path absolute(Path path) {
        int n2 = 0;
        Path path2 = path.toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(path2, "toAbsolutePath()");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final String absolutePathString(Path path) {
        int n2 = 0;
        return ((Object)path.toAbsolutePath()).toString();
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final Path relativeTo(@NotNull Path path, @NotNull Path path2) {
        Path path3;
        Intrinsics.checkNotNullParameter(path, "$this$relativeTo");
        Intrinsics.checkNotNullParameter(path2, "base");
        try {
            path3 = PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw (Throwable)new IllegalArgumentException(Intrinsics.stringPlus(illegalArgumentException.getMessage(), "\nthis path: " + path + "\nbase path: " + path2), illegalArgumentException);
        }
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final Path relativeToOrSelf(@NotNull Path path, @NotNull Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$relativeToOrSelf");
        Intrinsics.checkNotNullParameter(path2, "base");
        Path path3 = PathsKt.relativeToOrNull(path, path2);
        if (path3 == null) {
            path3 = path;
        }
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @Nullable
    public static final Path relativeToOrNull(@NotNull Path path, @NotNull Path path2) {
        Path path3;
        Intrinsics.checkNotNullParameter(path, "$this$relativeToOrNull");
        Intrinsics.checkNotNullParameter(path2, "base");
        try {
            path3 = PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            path3 = null;
        }
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path copyTo(Path path, Path path2, boolean bl) throws IOException {
        CopyOption[] arrcopyOption;
        int n2 = 0;
        if (bl) {
            CopyOption[] arrcopyOption2 = new CopyOption[1];
            arrcopyOption = arrcopyOption2;
            arrcopyOption2[0] = StandardCopyOption.REPLACE_EXISTING;
        } else {
            arrcopyOption = new CopyOption[]{};
        }
        CopyOption[] arrcopyOption3 = arrcopyOption;
        Path path3 = Files.copy(path, path2, Arrays.copyOf(arrcopyOption3, arrcopyOption3.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.copy(this, target, *options)");
        return path3;
    }

    static /* synthetic */ Path copyTo$default(Path path, Path path2, boolean bl, int n2, Object arrcopyOption) throws IOException {
        CopyOption[] arrcopyOption2;
        if ((n2 & 2) != 0) {
            bl = false;
        }
        n2 = 0;
        if (bl) {
            CopyOption[] arrcopyOption3 = new CopyOption[1];
            arrcopyOption2 = arrcopyOption3;
            arrcopyOption3[0] = StandardCopyOption.REPLACE_EXISTING;
        } else {
            arrcopyOption2 = new CopyOption[]{};
        }
        arrcopyOption = arrcopyOption2;
        Path path3 = Files.copy(path, path2, Arrays.copyOf(arrcopyOption, arrcopyOption.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.copy(this, target, *options)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path copyTo(Path path, Path path2, CopyOption ... arrcopyOption) throws IOException {
        int n2 = 0;
        Path path3 = Files.copy(path, path2, Arrays.copyOf(arrcopyOption, arrcopyOption.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.copy(this, target, *options)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean exists(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        return Files.exists(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean notExists(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        return Files.notExists(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isRegularFile(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        return Files.isRegularFile(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isDirectory(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        return Files.isDirectory(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isSymbolicLink(Path path) {
        int n2 = 0;
        return Files.isSymbolicLink(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isExecutable(Path path) {
        int n2 = 0;
        return Files.isExecutable(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isHidden(Path path) throws IOException {
        int n2 = 0;
        return Files.isHidden(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isReadable(Path path) {
        int n2 = 0;
        return Files.isReadable(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isWritable(Path path) {
        int n2 = 0;
        return Files.isWritable(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean isSameFileAs(Path path, Path path2) throws IOException {
        int n2 = 0;
        return Files.isSameFile(path, path2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final List<Path> listDirectoryEntries(@NotNull Path path, @NotNull String string) throws IOException {
        Iterable iterable;
        Intrinsics.checkNotNullParameter(path, "$this$listDirectoryEntries");
        Intrinsics.checkNotNullParameter(string, "glob");
        Closeable closeable = Files.newDirectoryStream(path, string);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            iterable = (DirectoryStream)closeable;
            boolean bl3 = false;
            DirectoryStream directoryStream = iterable;
            Intrinsics.checkNotNullExpressionValue(directoryStream, "it");
            iterable = CollectionsKt.toList(directoryStream);
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        return iterable;
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path path, String string, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            string = "*";
        }
        return PathsKt.listDirectoryEntries(path, string);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final <T> T useDirectoryEntries(Path path, String string, Function1<? super Sequence<? extends Path>, ? extends T> function1) throws IOException {
        DirectoryStream directoryStream;
        int n2 = 0;
        Closeable closeable = Files.newDirectoryStream(path, string);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            directoryStream = (DirectoryStream)closeable;
            boolean bl3 = false;
            DirectoryStream directoryStream2 = directoryStream;
            Intrinsics.checkNotNullExpressionValue(directoryStream2, "it");
            directoryStream = function1.invoke(CollectionsKt.asSequence(directoryStream2));
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
        return (T)directoryStream;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static /* synthetic */ Object useDirectoryEntries$default(Path path, String string, Function1 function1, int n2, Object object) throws IOException {
        DirectoryStream<Object> directoryStream;
        if ((n2 & 1) != 0) {
            string = "*";
        }
        n2 = 0;
        object = Files.newDirectoryStream(path, string);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            directoryStream = (DirectoryStream)object;
            boolean bl3 = false;
            DirectoryStream directoryStream2 = directoryStream;
            Intrinsics.checkNotNullExpressionValue(directoryStream2, "it");
            directoryStream = function1.invoke(CollectionsKt.asSequence(directoryStream2));
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
        return directoryStream;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final void forEachDirectoryEntry(Path path, String string, Function1<? super Path, Unit> function1) throws IOException {
        int n2 = 0;
        Closeable closeable = Files.newDirectoryStream(path, string);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Object object = (DirectoryStream)closeable;
            boolean bl3 = false;
            DirectoryStream directoryStream = object;
            Intrinsics.checkNotNullExpressionValue(directoryStream, "it");
            Iterable iterable = directoryStream;
            boolean bl4 = false;
            for (Object t2 : iterable) {
                function1.invoke((Path)t2);
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
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static /* synthetic */ void forEachDirectoryEntry$default(Path path, String string, Function1 function1, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            string = "*";
        }
        n2 = 0;
        object = Files.newDirectoryStream(path, string);
        boolean bl = false;
        boolean bl2 = false;
        Throwable throwable = null;
        try {
            Object object2 = (DirectoryStream)object;
            boolean bl3 = false;
            DirectoryStream directoryStream = object2;
            Intrinsics.checkNotNullExpressionValue(directoryStream, "it");
            Iterable iterable = directoryStream;
            boolean bl4 = false;
            for (Object t2 : iterable) {
                function1.invoke(t2);
            }
            object2 = Unit.INSTANCE;
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
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final long fileSize(Path path) throws IOException {
        int n2 = 0;
        return Files.size(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final void deleteExisting(Path path) throws IOException {
        int n2 = 0;
        Files.delete(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final boolean deleteIfExists(Path path) throws IOException {
        int n2 = 0;
        return Files.deleteIfExists(path);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createDirectory(Path path, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path2 = Files.createDirectory(path, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.createDirectory(this, *attributes)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createDirectories(Path path, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path2 = Files.createDirectories(path, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.createDirectories(this, *attributes)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path moveTo(Path path, Path path2, CopyOption ... arrcopyOption) throws IOException {
        int n2 = 0;
        Path path3 = Files.move(path, path2, Arrays.copyOf(arrcopyOption, arrcopyOption.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.move(this, target, *options)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path moveTo(Path path, Path path2, boolean bl) throws IOException {
        CopyOption[] arrcopyOption;
        int n2 = 0;
        if (bl) {
            CopyOption[] arrcopyOption2 = new CopyOption[1];
            arrcopyOption = arrcopyOption2;
            arrcopyOption2[0] = StandardCopyOption.REPLACE_EXISTING;
        } else {
            arrcopyOption = new CopyOption[]{};
        }
        CopyOption[] arrcopyOption3 = arrcopyOption;
        Path path3 = Files.move(path, path2, Arrays.copyOf(arrcopyOption3, arrcopyOption3.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.move(this, target, *options)");
        return path3;
    }

    static /* synthetic */ Path moveTo$default(Path path, Path path2, boolean bl, int n2, Object arrcopyOption) throws IOException {
        CopyOption[] arrcopyOption2;
        if ((n2 & 2) != 0) {
            bl = false;
        }
        n2 = 0;
        if (bl) {
            CopyOption[] arrcopyOption3 = new CopyOption[1];
            arrcopyOption2 = arrcopyOption3;
            arrcopyOption3[0] = StandardCopyOption.REPLACE_EXISTING;
        } else {
            arrcopyOption2 = new CopyOption[]{};
        }
        arrcopyOption = arrcopyOption2;
        Path path3 = Files.move(path, path2, Arrays.copyOf(arrcopyOption, arrcopyOption.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.move(this, target, *options)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final FileStore fileStore(Path path) throws IOException {
        int n2 = 0;
        FileStore fileStore = Files.getFileStore(path);
        Intrinsics.checkNotNullExpressionValue(fileStore, "Files.getFileStore(this)");
        return fileStore;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Object getAttribute(Path path, String string, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        return Files.getAttribute(path, string, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path setAttribute(Path path, String string, Object object, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        Path path2 = Files.setAttribute(path, string, object, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.setAttribute(this,\u2026tribute, value, *options)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesViewOrNull(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        Intrinsics.reifiedOperationMarker(4, "V");
        return (V)Files.getFileAttributeView(path, FileAttributeView.class, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesView(Path path, LinkOption ... arrlinkOption) {
        int n2 = 0;
        Intrinsics.reifiedOperationMarker(4, "V");
        FileAttributeView fileAttributeView = Files.getFileAttributeView(path, FileAttributeView.class, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        if (fileAttributeView == null) {
            Intrinsics.reifiedOperationMarker(4, "V");
            Void void_ = PathsKt.fileAttributeViewNotAvailable(path, FileAttributeView.class);
            throw new KotlinNothingValueException();
        }
        return (V)fileAttributeView;
    }

    @PublishedApi
    @NotNull
    public static final Void fileAttributeViewNotAvailable(@NotNull Path path, @NotNull Class<?> class_) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(class_, "attributeViewClass");
        throw (Throwable)new UnsupportedOperationException("The desired attribute view type " + class_ + " is not available for the file " + path + '.');
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final /* synthetic */ <A extends BasicFileAttributes> A readAttributes(Path path, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        Intrinsics.reifiedOperationMarker(4, "A");
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        Intrinsics.checkNotNullExpressionValue(basicFileAttributes, "Files.readAttributes(thi\u2026 A::class.java, *options)");
        return (A)basicFileAttributes;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Map<String, Object> readAttributes(Path path, String string, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        Map<String, Object> map = Files.readAttributes(path, string, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        Intrinsics.checkNotNullExpressionValue(map, "Files.readAttributes(this, attributes, *options)");
        return map;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final FileTime getLastModifiedTime(Path path, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        FileTime fileTime = Files.getLastModifiedTime(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        Intrinsics.checkNotNullExpressionValue(fileTime, "Files.getLastModifiedTime(this, *options)");
        return fileTime;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path setLastModifiedTime(Path path, FileTime fileTime) throws IOException {
        int n2 = 0;
        Path path2 = Files.setLastModifiedTime(path, fileTime);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.setLastModifiedTime(this, value)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final UserPrincipal getOwner(Path path, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        return Files.getOwner(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path setOwner(Path path, UserPrincipal userPrincipal) throws IOException {
        int n2 = 0;
        Path path2 = Files.setOwner(path, userPrincipal);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.setOwner(this, value)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption ... arrlinkOption) throws IOException {
        int n2 = 0;
        Set<PosixFilePermission> set = Files.getPosixFilePermissions(path, Arrays.copyOf(arrlinkOption, arrlinkOption.length));
        Intrinsics.checkNotNullExpressionValue(set, "Files.getPosixFilePermissions(this, *options)");
        return set;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path setPosixFilePermissions(Path path, Set<? extends PosixFilePermission> set) throws IOException {
        int n2 = 0;
        Path path2 = Files.setPosixFilePermissions(path, set);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.setPosixFilePermissions(this, value)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createLinkPointingTo(Path path, Path path2) throws IOException {
        int n2 = 0;
        Path path3 = Files.createLink(path, path2);
        Intrinsics.checkNotNullExpressionValue(path3, "Files.createLink(this, target)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createSymbolicLinkPointingTo(Path path, Path path2, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path3 = Files.createSymbolicLink(path, path2, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path3, "Files.createSymbolicLink\u2026his, target, *attributes)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path readSymbolicLink(Path path) throws IOException {
        int n2 = 0;
        Path path2 = Files.readSymbolicLink(path);
        Intrinsics.checkNotNullExpressionValue(path2, "Files.readSymbolicLink(this)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createFile(Path path, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path2 = Files.createFile(path, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path2, "Files.createFile(this, *attributes)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createTempFile(String string, String string2, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path = Files.createTempFile(string, string2, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path, "Files.createTempFile(prefix, suffix, *attributes)");
        return path;
    }

    static /* synthetic */ Path createTempFile$default(String string, String string2, FileAttribute[] arrfileAttribute, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            string = null;
        }
        if ((n2 & 2) != 0) {
            string2 = null;
        }
        n2 = 0;
        Path path = Files.createTempFile(string, string2, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path, "Files.createTempFile(prefix, suffix, *attributes)");
        return path;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final Path createTempFile(@Nullable Path path, @Nullable String string, @Nullable String string2, FileAttribute<?> ... arrfileAttribute) throws IOException {
        Path path2;
        Intrinsics.checkNotNullParameter(arrfileAttribute, "attributes");
        if (path != null) {
            Path path3 = Files.createTempFile(path, string, string2, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
            path2 = path3;
            Intrinsics.checkNotNullExpressionValue(path3, "Files.createTempFile(dir\u2026fix, suffix, *attributes)");
        } else {
            Path path4 = Files.createTempFile(string, string2, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
            path2 = path4;
            Intrinsics.checkNotNullExpressionValue(path4, "Files.createTempFile(prefix, suffix, *attributes)");
        }
        return path2;
    }

    public static /* synthetic */ Path createTempFile$default(Path path, String string, String string2, FileAttribute[] arrfileAttribute, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            string = null;
        }
        if ((n2 & 4) != 0) {
            string2 = null;
        }
        return PathsKt.createTempFile(path, string, string2, arrfileAttribute);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path createTempDirectory(String string, FileAttribute<?> ... arrfileAttribute) throws IOException {
        int n2 = 0;
        Path path = Files.createTempDirectory(string, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path, "Files.createTempDirectory(prefix, *attributes)");
        return path;
    }

    static /* synthetic */ Path createTempDirectory$default(String string, FileAttribute[] arrfileAttribute, int n2, Object object) throws IOException {
        if ((n2 & 1) != 0) {
            string = null;
        }
        n2 = 0;
        Path path = Files.createTempDirectory(string, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
        Intrinsics.checkNotNullExpressionValue(path, "Files.createTempDirectory(prefix, *attributes)");
        return path;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @NotNull
    public static final Path createTempDirectory(@Nullable Path path, @Nullable String string, FileAttribute<?> ... arrfileAttribute) throws IOException {
        Path path2;
        Intrinsics.checkNotNullParameter(arrfileAttribute, "attributes");
        if (path != null) {
            Path path3 = Files.createTempDirectory(path, string, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
            path2 = path3;
            Intrinsics.checkNotNullExpressionValue(path3, "Files.createTempDirector\u2026ory, prefix, *attributes)");
        } else {
            Path path4 = Files.createTempDirectory(string, Arrays.copyOf(arrfileAttribute, arrfileAttribute.length));
            path2 = path4;
            Intrinsics.checkNotNullExpressionValue(path4, "Files.createTempDirectory(prefix, *attributes)");
        }
        return path2;
    }

    public static /* synthetic */ Path createTempDirectory$default(Path path, String string, FileAttribute[] arrfileAttribute, int n2, Object object) throws IOException {
        if ((n2 & 2) != 0) {
            string = null;
        }
        return PathsKt.createTempDirectory(path, string, arrfileAttribute);
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path div(Path path, Path path2) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(path, "$this$div");
        Path path3 = path.resolve(path2);
        Intrinsics.checkNotNullExpressionValue(path3, "this.resolve(other)");
        return path3;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path div(Path path, String string) {
        int n2 = 0;
        Intrinsics.checkNotNullParameter(path, "$this$div");
        Path path2 = path.resolve(string);
        Intrinsics.checkNotNullExpressionValue(path2, "this.resolve(other)");
        return path2;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path Path(String string) {
        int n2 = 0;
        Path path = Paths.get(string, new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(path)");
        return path;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path Path(String string, String ... arrstring) {
        int n2 = 0;
        Path path = Paths.get(string, Arrays.copyOf(arrstring, arrstring.length));
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(base, *subpaths)");
        return path;
    }

    @SinceKotlin(version="1.5")
    @WasExperimental(markerClass={ExperimentalPathApi.class})
    @InlineOnly
    private static final Path toPath(URI uRI) {
        int n2 = 0;
        Path path = Paths.get(uRI);
        Intrinsics.checkNotNullExpressionValue(path, "Paths.get(this)");
        return path;
    }
}

