/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.throwables.InvalidAccessorException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.InterfaceInfo;
import org.spongepowered.asm.mixin.transformer.MethodMapper;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.meta.MixinRenamed;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.perf.Profiler;
import org.spongepowered.asm.util.throwables.SyntheticBridgeException;

class MixinPreProcessorStandard {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    protected final MixinInfo mixin;
    protected final MixinInfo.MixinClassNode classNode;
    protected final MixinEnvironment env;
    protected final Profiler profiler = MixinEnvironment.getProfiler();
    private final boolean verboseLogging;
    private final boolean strictUnique;
    private boolean prepared;
    private boolean attached;

    MixinPreProcessorStandard(MixinInfo mixinInfo, MixinInfo.MixinClassNode mixinClassNode) {
        this.mixin = mixinInfo;
        this.classNode = mixinClassNode;
        this.env = mixinInfo.getParent().getEnvironment();
        this.verboseLogging = this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
        this.strictUnique = this.env.getOption(MixinEnvironment.Option.DEBUG_UNIQUE);
    }

    final MixinPreProcessorStandard prepare() {
        if (this.prepared) {
            return this;
        }
        this.prepared = true;
        Profiler.Section section = this.profiler.begin("prepare");
        for (MixinInfo.MixinMethodNode object : this.classNode.mixinMethods) {
            ClassInfo.Method method = this.mixin.getClassInfo().findMethod(object);
            this.prepareMethod(object, method);
        }
        for (FieldNode fieldNode : this.classNode.fields) {
            this.prepareField(fieldNode);
        }
        section.end();
        return this;
    }

    protected void prepareMethod(MixinInfo.MixinMethodNode mixinMethodNode, ClassInfo.Method method) {
        this.prepareShadow(mixinMethodNode, method);
        this.prepareSoftImplements(mixinMethodNode, method);
    }

    protected void prepareShadow(MixinInfo.MixinMethodNode mixinMethodNode, ClassInfo.Method method) {
        AnnotationNode annotationNode = Annotations.getVisible(mixinMethodNode, Shadow.class);
        if (annotationNode == null) {
            return;
        }
        String string = (String)Annotations.getValue(annotationNode, "prefix", Shadow.class);
        if (mixinMethodNode.name.startsWith(string)) {
            Annotations.setVisible(mixinMethodNode, MixinRenamed.class, "originalName", mixinMethodNode.name);
            String string2 = mixinMethodNode.name.substring(string.length());
            mixinMethodNode.name = method.renameTo(string2);
        }
    }

    protected void prepareSoftImplements(MixinInfo.MixinMethodNode mixinMethodNode, ClassInfo.Method method) {
        for (InterfaceInfo interfaceInfo : this.mixin.getSoftImplements()) {
            if (!interfaceInfo.renameMethod(mixinMethodNode)) continue;
            method.renameTo(mixinMethodNode.name);
        }
    }

    protected void prepareField(FieldNode fieldNode) {
    }

    final MixinPreProcessorStandard conform(TargetClassContext targetClassContext) {
        return this.conform(targetClassContext.getClassInfo());
    }

    final MixinPreProcessorStandard conform(ClassInfo classInfo) {
        Profiler.Section section = this.profiler.begin("conform");
        for (MixinInfo.MixinMethodNode mixinMethodNode : this.classNode.mixinMethods) {
            if (!mixinMethodNode.isInjector()) continue;
            ClassInfo.Method method = this.mixin.getClassInfo().findMethod(mixinMethodNode, 10);
            this.conformInjector(classInfo, mixinMethodNode, method);
        }
        section.end();
        return this;
    }

    private void conformInjector(ClassInfo classInfo, MixinInfo.MixinMethodNode mixinMethodNode, ClassInfo.Method method) {
        MethodMapper methodMapper = classInfo.getMethodMapper();
        methodMapper.remapHandlerMethod(this.mixin, mixinMethodNode, method);
    }

    MixinTargetContext createContextFor(TargetClassContext targetClassContext) {
        MixinTargetContext mixinTargetContext = new MixinTargetContext(this.mixin, this.classNode, targetClassContext);
        this.conform(targetClassContext);
        this.attach(mixinTargetContext);
        return mixinTargetContext;
    }

    final MixinPreProcessorStandard attach(MixinTargetContext mixinTargetContext) {
        if (this.attached) {
            throw new IllegalStateException("Preprocessor was already attached");
        }
        this.attached = true;
        Profiler.Section section = this.profiler.begin("attach");
        Profiler.Section section2 = this.profiler.begin("methods");
        this.attachMethods(mixinTargetContext);
        section2 = section2.next("fields");
        this.attachFields(mixinTargetContext);
        section2 = section2.next("transform");
        this.transform(mixinTargetContext);
        section2.end();
        section.end();
        return this;
    }

    protected void attachMethods(MixinTargetContext mixinTargetContext) {
        Iterator<MixinInfo.MixinMethodNode> iterator2 = this.classNode.mixinMethods.iterator();
        while (iterator2.hasNext()) {
            MixinInfo.MixinMethodNode mixinMethodNode = iterator2.next();
            if (!this.validateMethod(mixinTargetContext, mixinMethodNode)) {
                iterator2.remove();
                continue;
            }
            if (this.attachInjectorMethod(mixinTargetContext, mixinMethodNode)) {
                mixinTargetContext.addMixinMethod(mixinMethodNode);
                continue;
            }
            if (this.attachAccessorMethod(mixinTargetContext, mixinMethodNode)) {
                iterator2.remove();
                continue;
            }
            if (this.attachShadowMethod(mixinTargetContext, mixinMethodNode)) {
                mixinTargetContext.addShadowMethod(mixinMethodNode);
                iterator2.remove();
                continue;
            }
            if (this.attachOverwriteMethod(mixinTargetContext, mixinMethodNode)) {
                mixinTargetContext.addMixinMethod(mixinMethodNode);
                continue;
            }
            if (this.attachUniqueMethod(mixinTargetContext, mixinMethodNode)) {
                iterator2.remove();
                continue;
            }
            this.attachMethod(mixinTargetContext, mixinMethodNode);
            mixinTargetContext.addMixinMethod(mixinMethodNode);
        }
    }

    protected boolean validateMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        return true;
    }

    protected boolean attachInjectorMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        return mixinMethodNode.isInjector();
    }

    protected boolean attachAccessorMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        return this.attachAccessorMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.ACCESSOR) || this.attachAccessorMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.INVOKER);
    }

    protected boolean attachAccessorMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode, SpecialMethod specialMethod) {
        AnnotationNode annotationNode = mixinMethodNode.getVisibleAnnotation(specialMethod.annotation);
        if (annotationNode == null) {
            return false;
        }
        String string = (Object)((Object)specialMethod) + " method " + mixinMethodNode.name;
        ClassInfo.Method method = this.getSpecialMethod(mixinMethodNode, specialMethod);
        if (MixinEnvironment.getCompatibilityLevel().isAtLeast(MixinEnvironment.CompatibilityLevel.JAVA_8) && method.isStatic()) {
            if (this.mixin.getTargets().size() > 1) {
                throw new InvalidAccessorException((IMixinContext)mixinTargetContext, string + " in multi-target mixin is invalid. Mixin must have exactly 1 target.");
            }
            String string2 = mixinTargetContext.getUniqueName(mixinMethodNode, true);
            logger.log(this.mixin.getLoggingLevel(), "Renaming @Unique method {}{} to {} in {}", new Object[]{mixinMethodNode.name, mixinMethodNode.desc, string2, this.mixin});
            mixinMethodNode.name = method.renameTo(string2);
        } else {
            if (!method.isAbstract()) {
                throw new InvalidAccessorException((IMixinContext)mixinTargetContext, string + " is not abstract");
            }
            if (method.isStatic()) {
                throw new InvalidAccessorException((IMixinContext)mixinTargetContext, string + " cannot be static");
            }
        }
        mixinTargetContext.addAccessorMethod(mixinMethodNode, specialMethod.annotation);
        return true;
    }

    protected boolean attachShadowMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        return this.attachSpecialMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.SHADOW);
    }

    protected boolean attachOverwriteMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        return this.attachSpecialMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.OVERWRITE);
    }

    protected boolean attachSpecialMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode, SpecialMethod specialMethod) {
        AnnotationNode annotationNode = mixinMethodNode.getVisibleAnnotation(specialMethod.annotation);
        if (annotationNode == null) {
            return false;
        }
        if (specialMethod.isOverwrite) {
            this.checkMixinNotUnique(mixinMethodNode, specialMethod);
        }
        ClassInfo.Method method = this.getSpecialMethod(mixinMethodNode, specialMethod);
        MethodNode methodNode = mixinTargetContext.findMethod(mixinMethodNode, annotationNode);
        if (methodNode == null) {
            if (specialMethod.isOverwrite) {
                return false;
            }
            methodNode = mixinTargetContext.findRemappedMethod(mixinMethodNode);
            if (methodNode == null) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s in %s was not located in the target class %s. %s%s", new Object[]{specialMethod, mixinMethodNode.name, this.mixin, mixinTargetContext.getTarget(), mixinTargetContext.getReferenceMapper().getStatus(), MixinPreProcessorStandard.getDynamicInfo(mixinMethodNode)}));
            }
            mixinMethodNode.name = method.renameTo(methodNode.name);
        }
        if ("<init>".equals(methodNode.name)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Nice try! %s in %s cannot alias a constructor", mixinMethodNode.name, this.mixin));
        }
        if (!Bytecode.compareFlags(mixinMethodNode, methodNode, 8)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("STATIC modifier of %s method %s in %s does not match the target", new Object[]{specialMethod, mixinMethodNode.name, this.mixin}));
        }
        this.conformVisibility(mixinTargetContext, mixinMethodNode, specialMethod, methodNode);
        if (!methodNode.name.equals(mixinMethodNode.name)) {
            if (specialMethod.isOverwrite && (methodNode.access & 2) == 0) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "Non-private method cannot be aliased. Found " + methodNode.name);
            }
            mixinMethodNode.name = method.renameTo(methodNode.name);
        }
        return true;
    }

    private void conformVisibility(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode, SpecialMethod specialMethod, MethodNode methodNode) {
        Bytecode.Visibility visibility = Bytecode.getVisibility(methodNode);
        Bytecode.Visibility visibility2 = Bytecode.getVisibility(mixinMethodNode);
        if (visibility2.ordinal() >= visibility.ordinal()) {
            if (visibility == Bytecode.Visibility.PRIVATE && visibility2.ordinal() > Bytecode.Visibility.PRIVATE.ordinal()) {
                mixinTargetContext.getTarget().addUpgradedMethod(methodNode);
            }
            return;
        }
        String string = String.format("%s %s method %s in %s cannot reduce visibiliy of %s target method", new Object[]{visibility2, specialMethod, mixinMethodNode.name, this.mixin, visibility});
        if (specialMethod.isOverwrite && !this.mixin.getParent().conformOverwriteVisibility()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, string);
        }
        if (visibility2 == Bytecode.Visibility.PRIVATE) {
            if (specialMethod.isOverwrite) {
                logger.warn("Static binding violation: {}, visibility will be upgraded.", new Object[]{string});
            }
            mixinTargetContext.addUpgradedMethod(mixinMethodNode);
            Bytecode.setVisibility((MethodNode)mixinMethodNode, visibility);
        }
    }

    protected ClassInfo.Method getSpecialMethod(MixinInfo.MixinMethodNode mixinMethodNode, SpecialMethod specialMethod) {
        ClassInfo.Method method = this.mixin.getClassInfo().findMethod(mixinMethodNode, 10);
        this.checkMethodNotUnique(method, specialMethod);
        return method;
    }

    protected void checkMethodNotUnique(ClassInfo.Method method, SpecialMethod specialMethod) {
        if (method.isUnique()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s in %s cannot be @Unique", new Object[]{specialMethod, method.getName(), this.mixin}));
        }
    }

    protected void checkMixinNotUnique(MixinInfo.MixinMethodNode mixinMethodNode, SpecialMethod specialMethod) {
        if (this.mixin.isUnique()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s found in a @Unique mixin %s", new Object[]{specialMethod, mixinMethodNode.name, this.mixin}));
        }
    }

    protected boolean attachUniqueMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        String string;
        MethodNode methodNode;
        ClassInfo.Method method = this.mixin.getClassInfo().findMethod(mixinMethodNode, 10);
        if (method == null || !method.isUnique() && !this.mixin.isUnique() && !method.isSynthetic()) {
            return false;
        }
        if (method.isSynthetic()) {
            mixinTargetContext.transformDescriptor(mixinMethodNode);
            method.remapTo(mixinMethodNode.desc);
        }
        if ((methodNode = mixinTargetContext.findMethod(mixinMethodNode, null)) == null) {
            return false;
        }
        String string2 = string = method.isSynthetic() ? "synthetic" : "@Unique";
        if (Bytecode.getVisibility(mixinMethodNode).ordinal() < Bytecode.Visibility.PUBLIC.ordinal()) {
            String string3 = mixinTargetContext.getUniqueName(mixinMethodNode, false);
            logger.log(this.mixin.getLoggingLevel(), "Renaming {} method {}{} to {} in {}", new Object[]{string, mixinMethodNode.name, mixinMethodNode.desc, string3, this.mixin});
            mixinMethodNode.name = method.renameTo(string3);
            return false;
        }
        if (this.strictUnique) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Method conflict, %s method %s in %s cannot overwrite %s%s in %s", string, mixinMethodNode.name, this.mixin, methodNode.name, methodNode.desc, mixinTargetContext.getTarget()));
        }
        AnnotationNode annotationNode = Annotations.getVisible(mixinMethodNode, Unique.class);
        if (annotationNode == null || !Annotations.getValue(annotationNode, "silent", Boolean.FALSE).booleanValue()) {
            if (Bytecode.hasFlag(mixinMethodNode, 64)) {
                try {
                    Bytecode.compareBridgeMethods(methodNode, mixinMethodNode);
                    logger.debug("Discarding sythetic bridge method {} in {} because existing method in {} is compatible", new Object[]{string, mixinMethodNode.name, this.mixin, mixinTargetContext.getTarget()});
                    return true;
                }
                catch (SyntheticBridgeException syntheticBridgeException) {
                    if (this.verboseLogging || this.env.getOption(MixinEnvironment.Option.DEBUG_VERIFY)) {
                        syntheticBridgeException.printAnalysis(mixinTargetContext, methodNode, mixinMethodNode);
                    }
                    throw new InvalidMixinException((IMixinInfo)this.mixin, syntheticBridgeException.getMessage());
                }
            }
            logger.warn("Discarding {} public method {} in {} because it already exists in {}", new Object[]{string, mixinMethodNode.name, this.mixin, mixinTargetContext.getTarget()});
            return true;
        }
        mixinTargetContext.addMixinMethod(mixinMethodNode);
        return true;
    }

    protected void attachMethod(MixinTargetContext mixinTargetContext, MixinInfo.MixinMethodNode mixinMethodNode) {
        MethodNode methodNode;
        ClassInfo.Method method = this.mixin.getClassInfo().findMethod(mixinMethodNode);
        if (method == null) {
            return;
        }
        ClassInfo.Method method2 = this.mixin.getClassInfo().findMethodInHierarchy(mixinMethodNode, ClassInfo.SearchType.SUPER_CLASSES_ONLY);
        if (method2 != null && method2.isRenamed()) {
            mixinMethodNode.name = method.renameTo(method2.getName());
        }
        if ((methodNode = mixinTargetContext.findMethod(mixinMethodNode, null)) != null) {
            this.conformVisibility(mixinTargetContext, mixinMethodNode, SpecialMethod.MERGE, methodNode);
        }
    }

    protected void attachFields(MixinTargetContext mixinTargetContext) {
        Iterator iterator2 = this.classNode.fields.iterator();
        while (iterator2.hasNext()) {
            boolean bl;
            FieldNode fieldNode = (FieldNode)iterator2.next();
            AnnotationNode annotationNode = Annotations.getVisible(fieldNode, Shadow.class);
            boolean bl2 = bl = annotationNode != null;
            if (!this.validateField(mixinTargetContext, fieldNode, annotationNode)) {
                iterator2.remove();
                continue;
            }
            ClassInfo.Field field = this.mixin.getClassInfo().findField(fieldNode);
            mixinTargetContext.transformDescriptor(fieldNode);
            field.remapTo(fieldNode.desc);
            if (field.isUnique() && bl) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("@Shadow field %s cannot be @Unique", fieldNode.name));
            }
            FieldNode fieldNode2 = mixinTargetContext.findField(fieldNode, annotationNode);
            if (fieldNode2 == null) {
                if (annotationNode == null) continue;
                fieldNode2 = mixinTargetContext.findRemappedField(fieldNode);
                if (fieldNode2 == null) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Shadow field %s was not located in the target class %s. %s%s", fieldNode.name, mixinTargetContext.getTarget(), mixinTargetContext.getReferenceMapper().getStatus(), MixinPreProcessorStandard.getDynamicInfo(fieldNode)));
                }
                fieldNode.name = field.renameTo(fieldNode2.name);
            }
            if (!Bytecode.compareFlags(fieldNode, fieldNode2, 8)) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("STATIC modifier of @Shadow field %s in %s does not match the target", fieldNode.name, this.mixin));
            }
            if (field.isUnique()) {
                if ((fieldNode.access & 6) != 0) {
                    String string = mixinTargetContext.getUniqueName(fieldNode);
                    logger.log(this.mixin.getLoggingLevel(), "Renaming @Unique field {}{} to {} in {}", new Object[]{fieldNode.name, fieldNode.desc, string, this.mixin});
                    fieldNode.name = field.renameTo(string);
                    continue;
                }
                if (this.strictUnique) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Field conflict, @Unique field %s in %s cannot overwrite %s%s in %s", fieldNode.name, this.mixin, fieldNode2.name, fieldNode2.desc, mixinTargetContext.getTarget()));
                }
                logger.warn("Discarding @Unique public field {} in {} because it already exists in {}. Note that declared FIELD INITIALISERS will NOT be removed!", new Object[]{fieldNode.name, this.mixin, mixinTargetContext.getTarget()});
                iterator2.remove();
                continue;
            }
            if (!fieldNode2.desc.equals(fieldNode.desc)) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("The field %s in the target class has a conflicting signature", fieldNode.name));
            }
            if (!fieldNode2.name.equals(fieldNode.name)) {
                if ((fieldNode2.access & 2) == 0 && (fieldNode2.access & 0x1000) == 0) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Non-private field cannot be aliased. Found " + fieldNode2.name);
                }
                fieldNode.name = field.renameTo(fieldNode2.name);
            }
            iterator2.remove();
            if (!bl) continue;
            boolean bl3 = field.isDecoratedFinal();
            if (this.verboseLogging && Bytecode.hasFlag(fieldNode2, 16) != bl3) {
                String string = bl3 ? "@Shadow field {}::{} is decorated with @Final but target is not final" : "@Shadow target {}::{} is final but shadow is not decorated with @Final";
                logger.warn(string, new Object[]{this.mixin, fieldNode.name});
            }
            mixinTargetContext.addShadowField(fieldNode, field);
        }
    }

    protected boolean validateField(MixinTargetContext mixinTargetContext, FieldNode fieldNode, AnnotationNode annotationNode) {
        if (Bytecode.hasFlag(fieldNode, 8) && !Bytecode.hasFlag(fieldNode, 2) && !Bytecode.hasFlag(fieldNode, 4096) && annotationNode == null) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("Mixin %s contains non-private static field %s:%s", mixinTargetContext, fieldNode.name, fieldNode.desc));
        }
        String string = (String)Annotations.getValue(annotationNode, "prefix", Shadow.class);
        if (fieldNode.name.startsWith(string)) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("@Shadow field %s.%s has a shadow prefix. This is not allowed.", mixinTargetContext, fieldNode.name));
        }
        if ("super$".equals(fieldNode.name)) {
            if (fieldNode.access != 2) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Imaginary super field %s.%s must be private and non-final", mixinTargetContext, fieldNode.name));
            }
            if (!fieldNode.desc.equals("L" + this.mixin.getClassRef() + ";")) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Imaginary super field %s.%s must have the same type as the parent mixin (%s)", mixinTargetContext, fieldNode.name, this.mixin.getClassName()));
            }
            return false;
        }
        return true;
    }

    protected void transform(MixinTargetContext mixinTargetContext) {
        for (MethodNode methodNode : this.classNode.methods) {
            ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
            while (listIterator.hasNext()) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
                if (abstractInsnNode instanceof MethodInsnNode) {
                    this.transformMethod((MethodInsnNode)abstractInsnNode);
                    continue;
                }
                if (!(abstractInsnNode instanceof FieldInsnNode)) continue;
                this.transformField((FieldInsnNode)abstractInsnNode);
            }
        }
    }

    protected void transformMethod(MethodInsnNode methodInsnNode) {
        Profiler.Section section = this.profiler.begin("meta");
        ClassInfo classInfo = ClassInfo.forName(methodInsnNode.owner);
        if (classInfo == null) {
            throw new RuntimeException(new ClassNotFoundException(methodInsnNode.owner.replace('/', '.')));
        }
        ClassInfo.Method method = classInfo.findMethodInHierarchy(methodInsnNode, ClassInfo.SearchType.ALL_CLASSES, 2);
        section.end();
        if (method != null && method.isRenamed()) {
            methodInsnNode.name = method.getName();
        }
    }

    protected void transformField(FieldInsnNode fieldInsnNode) {
        Profiler.Section section = this.profiler.begin("meta");
        ClassInfo classInfo = ClassInfo.forName(fieldInsnNode.owner);
        if (classInfo == null) {
            throw new RuntimeException(new ClassNotFoundException(fieldInsnNode.owner.replace('/', '.')));
        }
        ClassInfo.Field field = classInfo.findField(fieldInsnNode, 2);
        section.end();
        if (field != null && field.isRenamed()) {
            fieldInsnNode.name = field.getName();
        }
    }

    protected static String getDynamicInfo(MethodNode methodNode) {
        return MixinPreProcessorStandard.getDynamicInfo("Method", Annotations.getInvisible(methodNode, Dynamic.class));
    }

    protected static String getDynamicInfo(FieldNode fieldNode) {
        return MixinPreProcessorStandard.getDynamicInfo("Field", Annotations.getInvisible(fieldNode, Dynamic.class));
    }

    private static String getDynamicInfo(String string, AnnotationNode annotationNode) {
        String string2 = Strings.nullToEmpty((String)Annotations.getValue(annotationNode));
        Type type = (Type)Annotations.getValue(annotationNode, "mixin");
        if (type != null) {
            string2 = String.format("{%s} %s", type.getClassName(), string2).trim();
        }
        return string2.length() > 0 ? String.format(" %s is @Dynamic(%s)", string, string2) : "";
    }

    static enum SpecialMethod {
        MERGE(true),
        OVERWRITE(true, Overwrite.class),
        SHADOW(false, Shadow.class),
        ACCESSOR(false, Accessor.class),
        INVOKER(false, Invoker.class);

        final boolean isOverwrite;
        final Class<? extends Annotation> annotation;
        final String description;

        private SpecialMethod(boolean bl, Class<? extends Annotation> class_) {
            this.isOverwrite = bl;
            this.annotation = class_;
            this.description = "@" + Bytecode.getSimpleName(class_);
        }

        private SpecialMethod(boolean bl) {
            this.isOverwrite = bl;
            this.annotation = null;
            this.description = "overwrite";
        }

        public String toString() {
            return this.description;
        }
    }
}

