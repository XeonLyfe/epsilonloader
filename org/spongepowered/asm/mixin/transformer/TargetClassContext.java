/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import java.io.OutputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.struct.SourceMap;
import org.spongepowered.asm.mixin.transformer.ClassContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinApplicatorInterface;
import org.spongepowered.asm.mixin.transformer.MixinApplicatorStandard;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.ClassSignature;

class TargetClassContext
extends ClassContext
implements ITargetClassContext {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final MixinEnvironment env;
    private final Extensions extensions;
    private final String sessionId;
    private final String className;
    private final ClassNode classNode;
    private final ClassInfo classInfo;
    private final SourceMap sourceMap;
    private final ClassSignature signature;
    private final SortedSet<MixinInfo> mixins;
    private final Map<String, Target> targetMethods = new HashMap<String, Target>();
    private final Set<MethodNode> mixinMethods = new HashSet<MethodNode>();
    private int nextUniqueMethodIndex;
    private int nextUniqueFieldIndex;
    private boolean applied;
    private boolean forceExport;

    TargetClassContext(MixinEnvironment mixinEnvironment, Extensions extensions, String string, String string2, ClassNode classNode, SortedSet<MixinInfo> sortedSet) {
        this.env = mixinEnvironment;
        this.extensions = extensions;
        this.sessionId = string;
        this.className = string2;
        this.classNode = classNode;
        this.classInfo = ClassInfo.fromClassNode(classNode);
        this.signature = this.classInfo.getSignature();
        this.mixins = sortedSet;
        this.sourceMap = new SourceMap(classNode.sourceFile);
        this.sourceMap.addFile(this.classNode);
    }

    public String toString() {
        return this.className;
    }

    boolean isApplied() {
        return this.applied;
    }

    boolean isExportForced() {
        return this.forceExport;
    }

    Extensions getExtensions() {
        return this.extensions;
    }

    String getSessionId() {
        return this.sessionId;
    }

    @Override
    String getClassRef() {
        return this.classNode.name;
    }

    String getClassName() {
        return this.className;
    }

    @Override
    public ClassNode getClassNode() {
        return this.classNode;
    }

    List<MethodNode> getMethods() {
        return this.classNode.methods;
    }

    List<FieldNode> getFields() {
        return this.classNode.fields;
    }

    @Override
    public ClassInfo getClassInfo() {
        return this.classInfo;
    }

    SortedSet<MixinInfo> getMixins() {
        return this.mixins;
    }

    SourceMap getSourceMap() {
        return this.sourceMap;
    }

    void mergeSignature(ClassSignature classSignature) {
        this.signature.merge(classSignature);
    }

    void addMixinMethod(MethodNode methodNode) {
        this.mixinMethods.add(methodNode);
    }

    void methodMerged(MethodNode methodNode) {
        if (!this.mixinMethods.remove(methodNode)) {
            logger.debug("Unexpected: Merged unregistered method {}{} in {}", new Object[]{methodNode.name, methodNode.desc, this});
        }
    }

    MethodNode findMethod(Deque<String> deque, String string) {
        return this.findAliasedMethod(deque, string, true);
    }

    MethodNode findAliasedMethod(Deque<String> deque, String string) {
        return this.findAliasedMethod(deque, string, false);
    }

    private MethodNode findAliasedMethod(Deque<String> deque, String string, boolean bl) {
        String string2 = deque.poll();
        if (string2 == null) {
            return null;
        }
        for (MethodNode methodNode : this.classNode.methods) {
            if (!methodNode.name.equals(string2) || !methodNode.desc.equals(string)) continue;
            return methodNode;
        }
        if (bl) {
            for (MethodNode methodNode : this.mixinMethods) {
                if (!methodNode.name.equals(string2) || !methodNode.desc.equals(string)) continue;
                return methodNode;
            }
        }
        return this.findAliasedMethod(deque, string);
    }

    FieldNode findAliasedField(Deque<String> deque, String string) {
        String string2 = deque.poll();
        if (string2 == null) {
            return null;
        }
        for (FieldNode fieldNode : this.classNode.fields) {
            if (!fieldNode.name.equals(string2) || !fieldNode.desc.equals(string)) continue;
            return fieldNode;
        }
        return this.findAliasedField(deque, string);
    }

    Target getTargetMethod(MethodNode methodNode) {
        if (!this.classNode.methods.contains(methodNode)) {
            throw new IllegalArgumentException("Invalid target method supplied to getTargetMethod()");
        }
        String string = methodNode.name + methodNode.desc;
        Target target = this.targetMethods.get(string);
        if (target == null) {
            target = new Target(this.classNode, methodNode);
            this.targetMethods.put(string, target);
        }
        return target;
    }

    String getUniqueName(MethodNode methodNode, boolean bl) {
        String string = Integer.toHexString(this.nextUniqueMethodIndex++);
        String string2 = bl ? "%2$s_$md$%1$s$%3$s" : "md%s$%s$%s";
        return String.format(string2, this.sessionId.substring(30), methodNode.name, string);
    }

    String getUniqueName(FieldNode fieldNode) {
        String string = Integer.toHexString(this.nextUniqueFieldIndex++);
        return String.format("fd%s$%s$%s", this.sessionId.substring(30), fieldNode.name, string);
    }

    void applyMixins() {
        if (this.applied) {
            throw new IllegalStateException("Mixins already applied to target class " + this.className);
        }
        this.applied = true;
        MixinApplicatorStandard mixinApplicatorStandard = this.createApplicator();
        mixinApplicatorStandard.apply(this.mixins);
        this.applySignature();
        this.upgradeMethods();
        this.checkMerges();
    }

    private MixinApplicatorStandard createApplicator() {
        if (this.classInfo.isInterface()) {
            return new MixinApplicatorInterface(this);
        }
        return new MixinApplicatorStandard(this);
    }

    private void applySignature() {
        this.getClassNode().signature = this.signature.toString();
    }

    private void checkMerges() {
        for (MethodNode methodNode : this.mixinMethods) {
            if (methodNode.name.startsWith("<")) continue;
            logger.debug("Unexpected: Registered method {}{} in {} was not merged", new Object[]{methodNode.name, methodNode.desc, this});
        }
    }

    void processDebugTasks() {
        if (!this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            return;
        }
        AnnotationNode annotationNode = Annotations.getVisible(this.classNode, Debug.class);
        if (annotationNode != null) {
            this.forceExport = Boolean.TRUE.equals(Annotations.getValue(annotationNode, "export"));
            if (Boolean.TRUE.equals(Annotations.getValue(annotationNode, "print"))) {
                Bytecode.textify(this.classNode, (OutputStream)System.err);
            }
        }
        for (MethodNode methodNode : this.classNode.methods) {
            AnnotationNode annotationNode2 = Annotations.getVisible(methodNode, Debug.class);
            if (annotationNode2 == null || !Boolean.TRUE.equals(Annotations.getValue(annotationNode2, "print"))) continue;
            Bytecode.textify(methodNode, (OutputStream)System.err);
        }
    }
}

