/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.signature.SignatureReader;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionClassExporter;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.mixin.transformer.meta.MixinRenamed;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.ConstraintParser;
import org.spongepowered.asm.util.perf.Profiler;
import org.spongepowered.asm.util.throwables.ConstraintViolationException;
import org.spongepowered.asm.util.throwables.InvalidConstraintException;

class MixinApplicatorStandard {
    protected static final List<Class<? extends Annotation>> CONSTRAINED_ANNOTATIONS = ImmutableList.of(Overwrite.class, Inject.class, ModifyArg.class, ModifyArgs.class, Redirect.class, ModifyVariable.class, ModifyConstant.class);
    protected static final int[] INITIALISER_OPCODE_BLACKLIST = new int[]{177, 21, 22, 23, 24, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 79, 80, 81, 82, 83, 84, 85, 86};
    protected final Logger logger = LogManager.getLogger((String)"mixin");
    protected final TargetClassContext context;
    protected final String targetName;
    protected final ClassNode targetClass;
    protected final Profiler profiler = MixinEnvironment.getProfiler();
    protected final boolean mergeSignatures;

    MixinApplicatorStandard(TargetClassContext targetClassContext) {
        this.context = targetClassContext;
        this.targetName = targetClassContext.getClassName();
        this.targetClass = targetClassContext.getClassNode();
        ExtensionClassExporter extensionClassExporter = (ExtensionClassExporter)targetClassContext.getExtensions().getExtension(ExtensionClassExporter.class);
        this.mergeSignatures = extensionClassExporter.isDecompilerActive() && MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE_MERGESIGNATURES);
    }

    void apply(SortedSet<MixinInfo> sortedSet) {
        ArrayList<MixinTargetContext> arrayList = new ArrayList<MixinTargetContext>();
        for (MixinInfo object : sortedSet) {
            this.logger.log(object.getLoggingLevel(), "Mixing {} from {} into {}", new Object[]{object.getName(), object.getParent(), this.targetName});
            arrayList.add(object.createContextFor(this.context));
        }
        Object object = null;
        try {
            for (MixinTargetContext mixinTargetContext : arrayList) {
                object = mixinTargetContext;
                ((MixinTargetContext)object).preApply(this.targetName, this.targetClass);
            }
            for (ApplicatorPass applicatorPass : ApplicatorPass.values()) {
                Profiler.Section section = this.profiler.begin("pass", applicatorPass.name().toLowerCase());
                for (MixinTargetContext mixinTargetContext : arrayList) {
                    object = mixinTargetContext;
                    this.applyMixin((MixinTargetContext)object, applicatorPass);
                }
                section.end();
            }
            for (MixinTargetContext mixinTargetContext : arrayList) {
                object = mixinTargetContext;
                ((MixinTargetContext)object).postApply(this.targetName, this.targetClass);
            }
        }
        catch (InvalidMixinException invalidMixinException) {
            throw invalidMixinException;
        }
        catch (Exception exception) {
            throw new InvalidMixinException((IMixinContext)object, "Unexpecteded " + exception.getClass().getSimpleName() + " whilst applying the mixin class: " + exception.getMessage(), (Throwable)exception);
        }
        this.applySourceMap(this.context);
        this.context.processDebugTasks();
    }

    protected final void applyMixin(MixinTargetContext mixinTargetContext, ApplicatorPass applicatorPass) {
        switch (applicatorPass) {
            case MAIN: {
                this.applySignature(mixinTargetContext);
                this.applyInterfaces(mixinTargetContext);
                this.applyAttributes(mixinTargetContext);
                this.applyAnnotations(mixinTargetContext);
                this.applyFields(mixinTargetContext);
                this.applyMethods(mixinTargetContext);
                this.applyInitialisers(mixinTargetContext);
                break;
            }
            case PREINJECT: {
                this.prepareInjections(mixinTargetContext);
                break;
            }
            case INJECT: {
                this.applyAccessors(mixinTargetContext);
                this.applyInjections(mixinTargetContext);
                break;
            }
            default: {
                throw new IllegalStateException("Invalid pass specified " + (Object)((Object)applicatorPass));
            }
        }
    }

    protected void applySignature(MixinTargetContext mixinTargetContext) {
        if (this.mergeSignatures) {
            this.context.mergeSignature(mixinTargetContext.getSignature());
        }
    }

    protected void applyInterfaces(MixinTargetContext mixinTargetContext) {
        for (String string : mixinTargetContext.getInterfaces()) {
            if (this.targetClass.interfaces.contains(string)) continue;
            this.targetClass.interfaces.add(string);
            mixinTargetContext.getTargetClassInfo().addInterface(string);
        }
    }

    protected void applyAttributes(MixinTargetContext mixinTargetContext) {
        if (mixinTargetContext.shouldSetSourceFile()) {
            this.targetClass.sourceFile = mixinTargetContext.getSourceFile();
        }
        this.targetClass.version = Math.max(this.targetClass.version, mixinTargetContext.getMinRequiredClassVersion());
    }

    protected void applyAnnotations(MixinTargetContext mixinTargetContext) {
        ClassNode classNode = mixinTargetContext.getClassNode();
        Bytecode.mergeAnnotations(classNode, this.targetClass);
    }

    protected void applyFields(MixinTargetContext mixinTargetContext) {
        this.mergeShadowFields(mixinTargetContext);
        this.mergeNewFields(mixinTargetContext);
    }

    protected void mergeShadowFields(MixinTargetContext mixinTargetContext) {
        for (Map.Entry<FieldNode, ClassInfo.Field> entry : mixinTargetContext.getShadowFields()) {
            FieldNode fieldNode = entry.getKey();
            FieldNode fieldNode2 = this.findTargetField(fieldNode);
            if (fieldNode2 == null) continue;
            Bytecode.mergeAnnotations(fieldNode, fieldNode2);
            if (!entry.getValue().isDecoratedMutable() || Bytecode.hasFlag(fieldNode2, 2)) continue;
            fieldNode2.access &= 0xFFFFFFEF;
        }
    }

    protected void mergeNewFields(MixinTargetContext mixinTargetContext) {
        for (FieldNode fieldNode : mixinTargetContext.getFields()) {
            FieldNode fieldNode2 = this.findTargetField(fieldNode);
            if (fieldNode2 != null) continue;
            this.targetClass.fields.add(fieldNode);
            if (fieldNode.signature == null) continue;
            if (this.mergeSignatures) {
                SignatureVisitor signatureVisitor = mixinTargetContext.getSignature().getRemapper();
                new SignatureReader(fieldNode.signature).accept(signatureVisitor);
                fieldNode.signature = signatureVisitor.toString();
                continue;
            }
            fieldNode.signature = null;
        }
    }

    protected void applyMethods(MixinTargetContext mixinTargetContext) {
        for (MethodNode methodNode : mixinTargetContext.getShadowMethods()) {
            this.applyShadowMethod(mixinTargetContext, methodNode);
        }
        for (MethodNode methodNode : mixinTargetContext.getMethods()) {
            this.applyNormalMethod(mixinTargetContext, methodNode);
        }
    }

    protected void applyShadowMethod(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        MethodNode methodNode2 = this.findTargetMethod(methodNode);
        if (methodNode2 != null) {
            Bytecode.mergeAnnotations(methodNode, methodNode2);
        }
    }

    protected void applyNormalMethod(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        mixinTargetContext.transformMethod(methodNode);
        if (!methodNode.name.startsWith("<")) {
            this.checkMethodVisibility(mixinTargetContext, methodNode);
            this.checkMethodConstraints(mixinTargetContext, methodNode);
            this.mergeMethod(mixinTargetContext, methodNode);
        } else if ("<clinit>".equals(methodNode.name)) {
            this.appendInsns(mixinTargetContext, methodNode);
        }
    }

    protected void mergeMethod(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        Object object;
        boolean bl = Annotations.getVisible(methodNode, Overwrite.class) != null;
        MethodNode methodNode2 = this.findTargetMethod(methodNode);
        if (methodNode2 != null) {
            if (this.isAlreadyMerged(mixinTargetContext, methodNode, bl, methodNode2)) {
                return;
            }
            object = Annotations.getInvisible(methodNode, Intrinsic.class);
            if (object != null) {
                if (this.mergeIntrinsic(mixinTargetContext, methodNode, bl, methodNode2, (AnnotationNode)object)) {
                    mixinTargetContext.getTarget().methodMerged(methodNode);
                    return;
                }
            } else {
                if (mixinTargetContext.requireOverwriteAnnotations() && !bl) {
                    throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("%s%s in %s cannot overwrite method in %s because @Overwrite is required by the parent configuration", methodNode.name, methodNode.desc, mixinTargetContext, mixinTargetContext.getTarget().getClassName()));
                }
                this.targetClass.methods.remove(methodNode2);
            }
        } else if (bl) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("Overwrite target \"%s\" was not located in target class %s", methodNode.name, mixinTargetContext.getTargetClassRef()));
        }
        this.targetClass.methods.add(methodNode);
        mixinTargetContext.methodMerged(methodNode);
        if (methodNode.signature != null) {
            if (this.mergeSignatures) {
                object = mixinTargetContext.getSignature().getRemapper();
                new SignatureReader(methodNode.signature).accept((SignatureVisitor)object);
                methodNode.signature = object.toString();
            } else {
                methodNode.signature = null;
            }
        }
    }

    protected boolean isAlreadyMerged(MixinTargetContext mixinTargetContext, MethodNode methodNode, boolean bl, MethodNode methodNode2) {
        AnnotationNode annotationNode = Annotations.getVisible(methodNode2, MixinMerged.class);
        if (annotationNode == null) {
            if (Annotations.getVisible(methodNode2, Final.class) != null) {
                this.logger.warn("Overwrite prohibited for @Final method {} in {}. Skipping method.", new Object[]{methodNode.name, mixinTargetContext});
                return true;
            }
            return false;
        }
        String string = (String)Annotations.getValue(annotationNode, "sessionId");
        if (!this.context.getSessionId().equals(string)) {
            throw new ClassFormatError("Invalid @MixinMerged annotation found in" + mixinTargetContext + " at " + methodNode.name + " in " + this.targetClass.name);
        }
        if (Bytecode.hasFlag(methodNode2, 4160) && Bytecode.hasFlag(methodNode, 4160)) {
            if (mixinTargetContext.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                this.logger.warn("Synthetic bridge method clash for {} in {}", new Object[]{methodNode.name, mixinTargetContext});
            }
            return true;
        }
        String string2 = (String)Annotations.getValue(annotationNode, "mixin");
        int n2 = (Integer)Annotations.getValue(annotationNode, "priority");
        if (n2 >= mixinTargetContext.getPriority() && !string2.equals(mixinTargetContext.getClassName())) {
            this.logger.warn("Method overwrite conflict for {} in {}, previously written by {}. Skipping method.", new Object[]{methodNode.name, mixinTargetContext, string2});
            return true;
        }
        if (Annotations.getVisible(methodNode2, Final.class) != null) {
            this.logger.warn("Method overwrite conflict for @Final method {} in {} declared by {}. Skipping method.", new Object[]{methodNode.name, mixinTargetContext, string2});
            return true;
        }
        return false;
    }

    protected boolean mergeIntrinsic(MixinTargetContext mixinTargetContext, MethodNode methodNode, boolean bl, MethodNode methodNode2, AnnotationNode annotationNode) {
        AnnotationNode annotationNode2;
        if (bl) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, "@Intrinsic is not compatible with @Overwrite, remove one of these annotations on " + methodNode.name + " in " + mixinTargetContext);
        }
        String string = methodNode.name + methodNode.desc;
        if (Bytecode.hasFlag(methodNode, 8)) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, "@Intrinsic method cannot be static, found " + string + " in " + mixinTargetContext);
        }
        if (!(Bytecode.hasFlag(methodNode, 4096) || (annotationNode2 = Annotations.getVisible(methodNode, MixinRenamed.class)) != null && Annotations.getValue(annotationNode2, "isInterfaceMember", Boolean.FALSE).booleanValue())) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, "@Intrinsic method must be prefixed interface method, no rename encountered on " + string + " in " + mixinTargetContext);
        }
        if (!Annotations.getValue(annotationNode, "displace", Boolean.FALSE).booleanValue()) {
            this.logger.log(mixinTargetContext.getLoggingLevel(), "Skipping Intrinsic mixin method {} for {}", new Object[]{string, mixinTargetContext.getTargetClassRef()});
            return true;
        }
        this.displaceIntrinsic(mixinTargetContext, methodNode, methodNode2);
        return false;
    }

    protected void displaceIntrinsic(MixinTargetContext mixinTargetContext, MethodNode methodNode, MethodNode methodNode2) {
        String string = "proxy+" + methodNode2.name;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof MethodInsnNode) || abstractInsnNode.getOpcode() == 184) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!methodInsnNode.owner.equals(this.targetClass.name) || !methodInsnNode.name.equals(methodNode2.name) || !methodInsnNode.desc.equals(methodNode2.desc)) continue;
            methodInsnNode.name = string;
        }
        methodNode2.name = string;
    }

    protected final void appendInsns(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        if (Type.getReturnType(methodNode.desc) != Type.VOID_TYPE) {
            throw new IllegalArgumentException("Attempted to merge insns from a method which does not return void");
        }
        MethodNode methodNode2 = this.findTargetMethod(methodNode);
        if (methodNode2 != null) {
            AbstractInsnNode abstractInsnNode = Bytecode.findInsn(methodNode2, 177);
            if (abstractInsnNode != null) {
                ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
                while (listIterator.hasNext()) {
                    AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode)listIterator.next();
                    if (abstractInsnNode2 instanceof LineNumberNode || abstractInsnNode2.getOpcode() == 177) continue;
                    methodNode2.instructions.insertBefore(abstractInsnNode, abstractInsnNode2);
                }
                methodNode2.maxLocals = Math.max(methodNode2.maxLocals, methodNode.maxLocals);
                methodNode2.maxStack = Math.max(methodNode2.maxStack, methodNode.maxStack);
            }
            return;
        }
        this.targetClass.methods.add(methodNode);
    }

    protected void applyInitialisers(MixinTargetContext mixinTargetContext) {
        MethodNode methodNode = this.getConstructor(mixinTargetContext);
        if (methodNode == null) {
            return;
        }
        Deque<AbstractInsnNode> deque = this.getInitialiser(mixinTargetContext, methodNode);
        if (deque == null || deque.size() == 0) {
            return;
        }
        for (MethodNode methodNode2 : this.targetClass.methods) {
            if (!"<init>".equals(methodNode2.name)) continue;
            methodNode2.maxStack = Math.max(methodNode2.maxStack, methodNode.maxStack);
            this.injectInitialiser(mixinTargetContext, methodNode2, deque);
        }
    }

    protected MethodNode getConstructor(MixinTargetContext mixinTargetContext) {
        MethodNode methodNode = null;
        for (MethodNode methodNode2 : mixinTargetContext.getMethods()) {
            if (!"<init>".equals(methodNode2.name) || !Bytecode.methodHasLineNumbers(methodNode2)) continue;
            if (methodNode == null) {
                methodNode = methodNode2;
                continue;
            }
            this.logger.warn(String.format("Mixin %s has multiple constructors, %s was selected\n", mixinTargetContext, methodNode.desc));
        }
        return methodNode;
    }

    private Range getConstructorRange(MethodNode methodNode) {
        boolean bl = false;
        AbstractInsnNode abstractInsnNode = null;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = -1;
        Object object = methodNode.instructions.iterator();
        while (object.hasNext()) {
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode)object.next();
            if (abstractInsnNode2 instanceof LineNumberNode) {
                n2 = ((LineNumberNode)abstractInsnNode2).line;
                bl = true;
                continue;
            }
            if (abstractInsnNode2 instanceof MethodInsnNode) {
                if (abstractInsnNode2.getOpcode() != 183 || !"<init>".equals(((MethodInsnNode)abstractInsnNode2).name) || n5 != -1) continue;
                n5 = methodNode.instructions.indexOf(abstractInsnNode2);
                n3 = n2;
                continue;
            }
            if (abstractInsnNode2.getOpcode() == 181) {
                bl = false;
                continue;
            }
            if (abstractInsnNode2.getOpcode() != 177) continue;
            if (bl) {
                n4 = n2;
                continue;
            }
            n4 = n3;
            abstractInsnNode = abstractInsnNode2;
        }
        if (abstractInsnNode != null) {
            object = new LabelNode(new Label());
            methodNode.instructions.insertBefore(abstractInsnNode, (AbstractInsnNode)object);
            methodNode.instructions.insertBefore(abstractInsnNode, new LineNumberNode(n3, (LabelNode)object));
        }
        return new Range(n3, n4, n5);
    }

    protected final Deque<AbstractInsnNode> getInitialiser(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        Range range = this.getConstructorRange(methodNode);
        if (!range.isValid()) {
            return null;
        }
        int n2 = 0;
        ArrayDeque<AbstractInsnNode> arrayDeque = new ArrayDeque<AbstractInsnNode>();
        boolean bl = false;
        int n3 = -1;
        LabelNode labelNode = null;
        Object object = methodNode.instructions.iterator(range.marker);
        while (object.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)object.next();
            if (abstractInsnNode instanceof LineNumberNode) {
                n2 = ((LineNumberNode)abstractInsnNode).line;
                AbstractInsnNode abstractInsnNode2 = methodNode.instructions.get(methodNode.instructions.indexOf(abstractInsnNode) + 1);
                if (n2 == range.end && abstractInsnNode2.getOpcode() != 177) {
                    bl = true;
                    n3 = 177;
                    continue;
                }
                bl = range.excludes(n2);
                n3 = -1;
                continue;
            }
            if (!bl) continue;
            if (labelNode != null) {
                arrayDeque.add(labelNode);
                labelNode = null;
            }
            if (abstractInsnNode instanceof LabelNode) {
                labelNode = (LabelNode)abstractInsnNode;
                continue;
            }
            int n4 = abstractInsnNode.getOpcode();
            if (n4 == n3) {
                n3 = -1;
                continue;
            }
            for (int n5 : INITIALISER_OPCODE_BLACKLIST) {
                if (n4 != n5) continue;
                throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Cannot handle " + Bytecode.getOpcodeName(n4) + " opcode (0x" + Integer.toHexString(n4).toUpperCase() + ") in class initialiser");
            }
            arrayDeque.add(abstractInsnNode);
        }
        object = (AbstractInsnNode)arrayDeque.peekLast();
        if (object != null && ((AbstractInsnNode)object).getOpcode() != 181) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Could not parse initialiser, expected 0xB5, found 0x" + Integer.toHexString(((AbstractInsnNode)object).getOpcode()) + " in " + mixinTargetContext);
        }
        return arrayDeque;
    }

    protected final void injectInitialiser(MixinTargetContext mixinTargetContext, MethodNode methodNode, Deque<AbstractInsnNode> deque) {
        Map<LabelNode, LabelNode> map = Bytecode.cloneLabels(methodNode.instructions);
        AbstractInsnNode abstractInsnNode = this.findInitialiserInjectionPoint(mixinTargetContext, methodNode, deque);
        if (abstractInsnNode == null) {
            this.logger.warn("Failed to locate initialiser injection point in <init>{}, initialiser was not mixed in.", new Object[]{methodNode.desc});
            return;
        }
        for (AbstractInsnNode abstractInsnNode2 : deque) {
            if (abstractInsnNode2 instanceof LabelNode) continue;
            if (abstractInsnNode2 instanceof JumpInsnNode) {
                throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Unsupported JUMP opcode in initialiser in " + mixinTargetContext);
            }
            AbstractInsnNode abstractInsnNode3 = abstractInsnNode2.clone(map);
            methodNode.instructions.insert(abstractInsnNode, abstractInsnNode3);
            abstractInsnNode = abstractInsnNode3;
        }
    }

    protected AbstractInsnNode findInitialiserInjectionPoint(MixinTargetContext mixinTargetContext, MethodNode methodNode, Deque<AbstractInsnNode> deque) {
        HashSet<String> hashSet = new HashSet<String>();
        for (AbstractInsnNode object2 : deque) {
            if (object2.getOpcode() != 181) continue;
            hashSet.add(MixinApplicatorStandard.fieldKey((FieldInsnNode)object2));
        }
        Object object3 = this.getInitialiserInjectionMode(mixinTargetContext.getEnvironment());
        String string = mixinTargetContext.getTargetClassInfo().getName();
        String string2 = mixinTargetContext.getTargetClassInfo().getSuperName();
        AbstractInsnNode abstractInsnNode = null;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            String string3;
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode2.getOpcode() == 183 && "<init>".equals(((MethodInsnNode)abstractInsnNode2).name)) {
                string3 = ((MethodInsnNode)abstractInsnNode2).owner;
                if (!string3.equals(string) && !string3.equals(string2)) continue;
                abstractInsnNode = abstractInsnNode2;
                if (object3 != InitialiserInjectionMode.SAFE) continue;
                break;
            }
            if (abstractInsnNode2.getOpcode() != 181 || object3 != InitialiserInjectionMode.DEFAULT || !hashSet.contains(string3 = MixinApplicatorStandard.fieldKey((FieldInsnNode)abstractInsnNode2))) continue;
            abstractInsnNode = abstractInsnNode2;
        }
        return abstractInsnNode;
    }

    private InitialiserInjectionMode getInitialiserInjectionMode(MixinEnvironment mixinEnvironment) {
        String string = mixinEnvironment.getOptionValue(MixinEnvironment.Option.INITIALISER_INJECTION_MODE);
        if (string == null) {
            return InitialiserInjectionMode.DEFAULT;
        }
        try {
            return InitialiserInjectionMode.valueOf(string.toUpperCase());
        }
        catch (Exception exception) {
            this.logger.warn("Could not parse unexpected value \"{}\" for mixin.initialiserInjectionMode, reverting to DEFAULT", new Object[]{string});
            return InitialiserInjectionMode.DEFAULT;
        }
    }

    private static String fieldKey(FieldInsnNode fieldInsnNode) {
        return String.format("%s:%s", fieldInsnNode.desc, fieldInsnNode.name);
    }

    protected void prepareInjections(MixinTargetContext mixinTargetContext) {
        mixinTargetContext.prepareInjections();
    }

    protected void applyInjections(MixinTargetContext mixinTargetContext) {
        mixinTargetContext.applyInjections();
    }

    protected void applyAccessors(MixinTargetContext mixinTargetContext) {
        List<MethodNode> list = mixinTargetContext.generateAccessors();
        for (MethodNode methodNode : list) {
            if (methodNode.name.startsWith("<")) continue;
            this.mergeMethod(mixinTargetContext, methodNode);
        }
    }

    protected void checkMethodVisibility(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        if (Bytecode.hasFlag(methodNode, 8) && !Bytecode.hasFlag(methodNode, 2) && !Bytecode.hasFlag(methodNode, 4096) && Annotations.getVisible(methodNode, Overwrite.class) == null) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("Mixin %s contains non-private static method %s", mixinTargetContext, methodNode));
        }
    }

    protected void applySourceMap(TargetClassContext targetClassContext) {
        this.targetClass.sourceDebug = targetClassContext.getSourceMap().toString();
    }

    protected void checkMethodConstraints(MixinTargetContext mixinTargetContext, MethodNode methodNode) {
        for (Class<? extends Annotation> class_ : CONSTRAINED_ANNOTATIONS) {
            AnnotationNode annotationNode = Annotations.getVisible(methodNode, class_);
            if (annotationNode == null) continue;
            this.checkConstraints(mixinTargetContext, methodNode, annotationNode);
        }
    }

    protected final void checkConstraints(MixinTargetContext mixinTargetContext, MethodNode methodNode, AnnotationNode annotationNode) {
        try {
            ConstraintParser.Constraint constraint = ConstraintParser.parse(annotationNode);
            try {
                constraint.check(mixinTargetContext.getEnvironment());
            }
            catch (ConstraintViolationException constraintViolationException) {
                String string = String.format("Constraint violation: %s on %s in %s", constraintViolationException.getMessage(), methodNode, mixinTargetContext);
                this.logger.warn(string);
                if (!mixinTargetContext.getEnvironment().getOption(MixinEnvironment.Option.IGNORE_CONSTRAINTS)) {
                    throw new InvalidMixinException(mixinTargetContext, string, (Throwable)constraintViolationException);
                }
            }
        }
        catch (InvalidConstraintException invalidConstraintException) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, invalidConstraintException.getMessage());
        }
    }

    protected final MethodNode findTargetMethod(MethodNode methodNode) {
        for (MethodNode methodNode2 : this.targetClass.methods) {
            if (!methodNode2.name.equals(methodNode.name) || !methodNode2.desc.equals(methodNode.desc)) continue;
            return methodNode2;
        }
        return null;
    }

    protected final FieldNode findTargetField(FieldNode fieldNode) {
        for (FieldNode fieldNode2 : this.targetClass.fields) {
            if (!fieldNode2.name.equals(fieldNode.name)) continue;
            return fieldNode2;
        }
        return null;
    }

    class Range {
        final int start;
        final int end;
        final int marker;

        Range(int n2, int n3, int n4) {
            this.start = n2;
            this.end = n3;
            this.marker = n4;
        }

        boolean isValid() {
            return this.start != 0 && this.end != 0 && this.end >= this.start;
        }

        boolean contains(int n2) {
            return n2 >= this.start && n2 <= this.end;
        }

        boolean excludes(int n2) {
            return n2 < this.start || n2 > this.end;
        }

        public String toString() {
            return String.format("Range[%d-%d,%d,valid=%s)", this.start, this.end, this.marker, this.isValid());
        }
    }

    static enum InitialiserInjectionMode {
        DEFAULT,
        SAFE;

    }

    static enum ApplicatorPass {
        MAIN,
        PREINJECT,
        INJECT;

    }
}

