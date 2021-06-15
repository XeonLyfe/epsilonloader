/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.InvokeDynamicInsnNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.struct.MemberRef;
import org.spongepowered.asm.mixin.struct.SourceMap;
import org.spongepowered.asm.mixin.transformer.ClassContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.InnerClassGenerator;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.obfuscation.RemapperChain;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.ClassSignature;

public class MixinTargetContext
extends ClassContext
implements IMixinContext {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final MixinInfo mixin;
    private final ClassNode classNode;
    private final TargetClassContext targetClass;
    private final String sessionId;
    private final ClassInfo targetClassInfo;
    private final BiMap<String, String> innerClasses = HashBiMap.create();
    private final List<MethodNode> shadowMethods = new ArrayList<MethodNode>();
    private final Map<FieldNode, ClassInfo.Field> shadowFields = new LinkedHashMap<FieldNode, ClassInfo.Field>();
    private final List<MethodNode> mergedMethods = new ArrayList<MethodNode>();
    private final InjectorGroupInfo.Map injectorGroups = new InjectorGroupInfo.Map();
    private final List<InjectionInfo> injectors = new ArrayList<InjectionInfo>();
    private final List<AccessorInfo> accessors = new ArrayList<AccessorInfo>();
    private final boolean inheritsFromMixin;
    private final boolean detachedSuper;
    private final SourceMap.File stratum;
    private int minRequiredClassVersion = MixinEnvironment.CompatibilityLevel.JAVA_6.classVersion();

    MixinTargetContext(MixinInfo mixinInfo, ClassNode classNode, TargetClassContext targetClassContext) {
        this.mixin = mixinInfo;
        this.classNode = classNode;
        this.targetClass = targetClassContext;
        this.targetClassInfo = ClassInfo.forName(this.getTarget().getClassRef());
        this.stratum = targetClassContext.getSourceMap().addFile(this.classNode);
        this.inheritsFromMixin = mixinInfo.getClassInfo().hasMixinInHierarchy() || this.targetClassInfo.hasMixinTargetInHierarchy();
        this.detachedSuper = !this.classNode.superName.equals(this.getTarget().getClassNode().superName);
        this.sessionId = targetClassContext.getSessionId();
        this.requireVersion(classNode.version);
        InnerClassGenerator innerClassGenerator = (InnerClassGenerator)targetClassContext.getExtensions().getGenerator(InnerClassGenerator.class);
        for (String string : this.mixin.getInnerClasses()) {
            this.innerClasses.put(string, innerClassGenerator.registerInnerClass(this.mixin, string, this));
        }
    }

    void addShadowMethod(MethodNode methodNode) {
        this.shadowMethods.add(methodNode);
    }

    void addShadowField(FieldNode fieldNode, ClassInfo.Field field) {
        this.shadowFields.put(fieldNode, field);
    }

    void addAccessorMethod(MethodNode methodNode, Class<? extends Annotation> class_) {
        this.accessors.add(AccessorInfo.of(this, methodNode, class_));
    }

    void addMixinMethod(MethodNode methodNode) {
        Annotations.setVisible(methodNode, MixinMerged.class, "mixin", this.getClassName());
        this.getTarget().addMixinMethod(methodNode);
    }

    void methodMerged(MethodNode methodNode) {
        this.mergedMethods.add(methodNode);
        this.targetClassInfo.addMethod(methodNode);
        this.getTarget().methodMerged(methodNode);
        Annotations.setVisible(methodNode, MixinMerged.class, "mixin", this.getClassName(), "priority", this.getPriority(), "sessionId", this.sessionId);
    }

    public String toString() {
        return this.mixin.toString();
    }

    public MixinEnvironment getEnvironment() {
        return this.mixin.getParent().getEnvironment();
    }

    @Override
    public boolean getOption(MixinEnvironment.Option option) {
        return this.getEnvironment().getOption(option);
    }

    @Override
    public ClassNode getClassNode() {
        return this.classNode;
    }

    @Override
    public String getClassName() {
        return this.mixin.getClassName();
    }

    @Override
    public String getClassRef() {
        return this.mixin.getClassRef();
    }

    public TargetClassContext getTarget() {
        return this.targetClass;
    }

    @Override
    public String getTargetClassRef() {
        return this.getTarget().getClassRef();
    }

    public ClassNode getTargetClassNode() {
        return this.getTarget().getClassNode();
    }

    public ClassInfo getTargetClassInfo() {
        return this.targetClassInfo;
    }

    @Override
    protected ClassInfo getClassInfo() {
        return this.mixin.getClassInfo();
    }

    public ClassSignature getSignature() {
        return this.getClassInfo().getSignature();
    }

    public SourceMap.File getStratum() {
        return this.stratum;
    }

    public int getMinRequiredClassVersion() {
        return this.minRequiredClassVersion;
    }

    public int getDefaultRequiredInjections() {
        return this.mixin.getParent().getDefaultRequiredInjections();
    }

    public String getDefaultInjectorGroup() {
        return this.mixin.getParent().getDefaultInjectorGroup();
    }

    public int getMaxShiftByValue() {
        return this.mixin.getParent().getMaxShiftByValue();
    }

    public InjectorGroupInfo.Map getInjectorGroups() {
        return this.injectorGroups;
    }

    public boolean requireOverwriteAnnotations() {
        return this.mixin.getParent().requireOverwriteAnnotations();
    }

    public ClassInfo findRealType(ClassInfo classInfo) {
        if (classInfo == this.getClassInfo()) {
            return this.targetClassInfo;
        }
        ClassInfo classInfo2 = this.targetClassInfo.findCorrespondingType(classInfo);
        if (classInfo2 == null) {
            throw new InvalidMixinException((IMixinContext)this, "Resolution error: unable to find corresponding type for " + classInfo + " in hierarchy of " + this.targetClassInfo);
        }
        return classInfo2;
    }

    public void transformMethod(MethodNode methodNode) {
        this.validateMethod(methodNode);
        this.transformDescriptor(methodNode);
        this.transformLVT(methodNode);
        this.stratum.applyOffset(methodNode);
        AbstractInsnNode abstractInsnNode = null;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode)listIterator.next();
            if (abstractInsnNode2 instanceof MethodInsnNode) {
                this.transformMethodRef(methodNode, listIterator, new MemberRef.Method((MethodInsnNode)abstractInsnNode2));
            } else if (abstractInsnNode2 instanceof FieldInsnNode) {
                this.transformFieldRef(methodNode, listIterator, new MemberRef.Field((FieldInsnNode)abstractInsnNode2));
                this.checkFinal(methodNode, listIterator, (FieldInsnNode)abstractInsnNode2);
            } else if (abstractInsnNode2 instanceof TypeInsnNode) {
                this.transformTypeNode(methodNode, listIterator, (TypeInsnNode)abstractInsnNode2, abstractInsnNode);
            } else if (abstractInsnNode2 instanceof LdcInsnNode) {
                this.transformConstantNode(methodNode, listIterator, (LdcInsnNode)abstractInsnNode2);
            } else if (abstractInsnNode2 instanceof InvokeDynamicInsnNode) {
                this.transformInvokeDynamicNode(methodNode, listIterator, (InvokeDynamicInsnNode)abstractInsnNode2);
            }
            abstractInsnNode = abstractInsnNode2;
        }
    }

    private void validateMethod(MethodNode methodNode) {
        ClassInfo.Method method;
        if (!(Annotations.getInvisible(methodNode, SoftOverride.class) == null || (method = this.targetClassInfo.findMethodInHierarchy(methodNode.name, methodNode.desc, ClassInfo.SearchType.SUPER_CLASSES_ONLY, ClassInfo.Traversal.SUPER)) != null && method.isInjected())) {
            throw new InvalidMixinException((IMixinContext)this, "Mixin method " + methodNode.name + methodNode.desc + " is tagged with @SoftOverride but no valid method was found in superclasses of " + this.getTarget().getClassName());
        }
    }

    private void transformLVT(MethodNode methodNode) {
        if (methodNode.localVariables == null) {
            return;
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            if (localVariableNode == null || localVariableNode.desc == null) continue;
            localVariableNode.desc = this.transformSingleDescriptor(Type.getType(localVariableNode.desc));
        }
    }

    private void transformMethodRef(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, MemberRef memberRef) {
        this.transformDescriptor(memberRef);
        if (memberRef.getOwner().equals(this.getClassRef())) {
            memberRef.setOwner(this.getTarget().getClassRef());
            ClassInfo.Method method = this.getClassInfo().findMethod(memberRef.getName(), memberRef.getDesc(), 10);
            if (method != null && method.isRenamed() && method.getOriginalName().equals(memberRef.getName()) && method.isSynthetic()) {
                memberRef.setName(method.getName());
            }
            this.upgradeMethodRef(methodNode, memberRef, method);
        } else if (this.innerClasses.containsKey(memberRef.getOwner())) {
            memberRef.setOwner((String)this.innerClasses.get(memberRef.getOwner()));
            memberRef.setDesc(this.transformMethodDescriptor(memberRef.getDesc()));
        } else if (this.detachedSuper || this.inheritsFromMixin) {
            if (memberRef.getOpcode() == 183) {
                this.updateStaticBinding(methodNode, memberRef);
            } else if (memberRef.getOpcode() == 182 && ClassInfo.forName(memberRef.getOwner()).isMixin()) {
                this.updateDynamicBinding(methodNode, memberRef);
            }
        }
    }

    private void transformFieldRef(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, MemberRef memberRef) {
        if ("super$".equals(memberRef.getName())) {
            if (memberRef instanceof MemberRef.Field) {
                this.processImaginarySuper(methodNode, ((MemberRef.Field)memberRef).insn);
                iterator2.remove();
            } else {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "Cannot call imaginary super from method handle.");
            }
        }
        this.transformDescriptor(memberRef);
        if (memberRef.getOwner().equals(this.getClassRef())) {
            memberRef.setOwner(this.getTarget().getClassRef());
            ClassInfo.Field field = this.getClassInfo().findField(memberRef.getName(), memberRef.getDesc(), 10);
            if (field != null && field.isRenamed() && field.getOriginalName().equals(memberRef.getName()) && field.isStatic()) {
                memberRef.setName(field.getName());
            }
        } else {
            ClassInfo classInfo = ClassInfo.forName(memberRef.getOwner());
            if (classInfo.isMixin()) {
                ClassInfo classInfo2 = this.targetClassInfo.findCorrespondingType(classInfo);
                memberRef.setOwner(classInfo2 != null ? classInfo2.getName() : this.getTarget().getClassRef());
            }
        }
    }

    private void checkFinal(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, FieldInsnNode fieldInsnNode) {
        if (!fieldInsnNode.owner.equals(this.getTarget().getClassRef())) {
            return;
        }
        int n2 = fieldInsnNode.getOpcode();
        if (n2 == 180 || n2 == 178) {
            return;
        }
        for (Map.Entry<FieldNode, ClassInfo.Field> entry : this.shadowFields.entrySet()) {
            FieldNode fieldNode = entry.getKey();
            if (!fieldNode.desc.equals(fieldInsnNode.desc) || !fieldNode.name.equals(fieldInsnNode.name)) continue;
            ClassInfo.Field field = entry.getValue();
            if (field.isDecoratedFinal()) {
                if (field.isDecoratedMutable()) {
                    if (this.mixin.getParent().getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                        logger.warn("Write access to @Mutable @Final field {} in {}::{}", new Object[]{field, this.mixin, methodNode.name});
                    }
                } else if ("<init>".equals(methodNode.name) || "<clinit>".equals(methodNode.name)) {
                    logger.warn("@Final field {} in {} should be final", new Object[]{field, this.mixin});
                } else {
                    logger.error("Write access detected to @Final field {} in {}::{}", new Object[]{field, this.mixin, methodNode.name});
                    if (this.mixin.getParent().getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERIFY)) {
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "Write access detected to @Final field " + field + " in " + this.mixin + "::" + methodNode.name);
                    }
                }
            }
            return;
        }
    }

    private void transformTypeNode(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, TypeInsnNode typeInsnNode, AbstractInsnNode abstractInsnNode) {
        if (typeInsnNode.getOpcode() == 192 && typeInsnNode.desc.equals(this.getTarget().getClassRef()) && abstractInsnNode.getOpcode() == 25 && ((VarInsnNode)abstractInsnNode).var == 0) {
            iterator2.remove();
            return;
        }
        if (typeInsnNode.desc.equals(this.getClassRef())) {
            typeInsnNode.desc = this.getTarget().getClassRef();
        } else {
            String string = (String)this.innerClasses.get(typeInsnNode.desc);
            if (string != null) {
                typeInsnNode.desc = string;
            }
        }
        this.transformDescriptor(typeInsnNode);
    }

    private void transformConstantNode(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, LdcInsnNode ldcInsnNode) {
        ldcInsnNode.cst = this.transformConstant(methodNode, iterator2, ldcInsnNode.cst);
    }

    private void transformInvokeDynamicNode(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, InvokeDynamicInsnNode invokeDynamicInsnNode) {
        this.requireVersion(51);
        invokeDynamicInsnNode.desc = this.transformMethodDescriptor(invokeDynamicInsnNode.desc);
        invokeDynamicInsnNode.bsm = this.transformHandle(methodNode, iterator2, invokeDynamicInsnNode.bsm);
        for (int i2 = 0; i2 < invokeDynamicInsnNode.bsmArgs.length; ++i2) {
            invokeDynamicInsnNode.bsmArgs[i2] = this.transformConstant(methodNode, iterator2, invokeDynamicInsnNode.bsmArgs[i2]);
        }
    }

    private Object transformConstant(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, Object object) {
        if (object instanceof Type) {
            Type type = (Type)object;
            String string = this.transformDescriptor(type);
            if (!type.toString().equals(string)) {
                return Type.getType(string);
            }
            return object;
        }
        if (object instanceof Handle) {
            return this.transformHandle(methodNode, iterator2, (Handle)object);
        }
        return object;
    }

    private Handle transformHandle(MethodNode methodNode, Iterator<AbstractInsnNode> iterator2, Handle handle) {
        MemberRef.Handle handle2 = new MemberRef.Handle(handle);
        if (handle2.isField()) {
            this.transformFieldRef(methodNode, iterator2, handle2);
        } else {
            this.transformMethodRef(methodNode, iterator2, handle2);
        }
        return handle2.getMethodHandle();
    }

    private void processImaginarySuper(MethodNode methodNode, FieldInsnNode fieldInsnNode) {
        if (fieldInsnNode.getOpcode() != 180) {
            if ("<init>".equals(methodNode.name)) {
                throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super declaration: field " + fieldInsnNode.name + " must not specify an initialiser");
            }
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: found " + Bytecode.getOpcodeName(fieldInsnNode.getOpcode()) + " opcode in " + methodNode.name + methodNode.desc);
        }
        if ((methodNode.access & 2) != 0 || (methodNode.access & 8) != 0) {
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: method " + methodNode.name + methodNode.desc + " is private or static");
        }
        if (Annotations.getInvisible(methodNode, SoftOverride.class) == null) {
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: method " + methodNode.name + methodNode.desc + " is not decorated with @SoftOverride");
        }
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator(methodNode.instructions.indexOf(fieldInsnNode));
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            if (!(abstractInsnNode instanceof MethodInsnNode)) continue;
            MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
            if (!methodInsnNode.owner.equals(this.getClassRef()) || !methodInsnNode.name.equals(methodNode.name) || !methodInsnNode.desc.equals(methodNode.desc)) continue;
            methodInsnNode.setOpcode(183);
            this.updateStaticBinding(methodNode, new MemberRef.Method(methodInsnNode));
            return;
        }
        throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: could not find INVOKE for " + methodNode.name + methodNode.desc);
    }

    private void updateStaticBinding(MethodNode methodNode, MemberRef memberRef) {
        this.updateBinding(methodNode, memberRef, ClassInfo.Traversal.SUPER);
    }

    private void updateDynamicBinding(MethodNode methodNode, MemberRef memberRef) {
        this.updateBinding(methodNode, memberRef, ClassInfo.Traversal.ALL);
    }

    private void updateBinding(MethodNode methodNode, MemberRef memberRef, ClassInfo.Traversal traversal) {
        if ("<init>".equals(methodNode.name) || memberRef.getOwner().equals(this.getTarget().getClassRef()) || this.getTarget().getClassRef().startsWith("<")) {
            return;
        }
        ClassInfo.Method method = this.targetClassInfo.findMethodInHierarchy(memberRef.getName(), memberRef.getDesc(), traversal.getSearchType(), traversal);
        if (method != null) {
            if (method.getOwner().isMixin()) {
                throw new InvalidMixinException((IMixinContext)this, "Invalid " + memberRef + " in " + this + " resolved " + method.getOwner() + " but is mixin.");
            }
            memberRef.setOwner(method.getImplementor().getName());
        } else if (ClassInfo.forName(memberRef.getOwner()).isMixin()) {
            throw new MixinTransformerError("Error resolving " + memberRef + " in " + this);
        }
    }

    public void transformDescriptor(FieldNode fieldNode) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        fieldNode.desc = this.transformSingleDescriptor(fieldNode.desc, false);
    }

    public void transformDescriptor(MethodNode methodNode) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        methodNode.desc = this.transformMethodDescriptor(methodNode.desc);
    }

    public void transformDescriptor(MemberRef memberRef) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        if (memberRef.isField()) {
            memberRef.setDesc(this.transformSingleDescriptor(memberRef.getDesc(), false));
        } else {
            memberRef.setDesc(this.transformMethodDescriptor(memberRef.getDesc()));
        }
    }

    public void transformDescriptor(TypeInsnNode typeInsnNode) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        typeInsnNode.desc = this.transformSingleDescriptor(typeInsnNode.desc, true);
    }

    private String transformDescriptor(Type type) {
        if (type.getSort() == 11) {
            return this.transformMethodDescriptor(type.getDescriptor());
        }
        return this.transformSingleDescriptor(type);
    }

    private String transformSingleDescriptor(Type type) {
        if (type.getSort() < 9) {
            return type.toString();
        }
        return this.transformSingleDescriptor(type.toString(), false);
    }

    private String transformSingleDescriptor(String string, boolean bl) {
        String string2 = string;
        while (string2.startsWith("[") || string2.startsWith("L")) {
            if (string2.startsWith("[")) {
                string2 = string2.substring(1);
                continue;
            }
            string2 = string2.substring(1, string2.indexOf(";"));
            bl = true;
        }
        if (!bl) {
            return string;
        }
        String string3 = (String)this.innerClasses.get(string2);
        if (string3 != null) {
            return string.replace(string2, string3);
        }
        if (this.innerClasses.inverse().containsKey(string2)) {
            return string;
        }
        ClassInfo classInfo = ClassInfo.forName(string2);
        if (!classInfo.isMixin()) {
            return string;
        }
        return string.replace(string2, this.findRealType(classInfo).toString());
    }

    private String transformMethodDescriptor(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (Type type : Type.getArgumentTypes(string)) {
            stringBuilder.append(this.transformSingleDescriptor(type));
        }
        return stringBuilder.append(')').append(this.transformSingleDescriptor(Type.getReturnType(string))).toString();
    }

    @Override
    public Target getTargetMethod(MethodNode methodNode) {
        return this.getTarget().getTargetMethod(methodNode);
    }

    MethodNode findMethod(MethodNode methodNode, AnnotationNode annotationNode) {
        List list;
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add(methodNode.name);
        if (annotationNode != null && (list = (List)Annotations.getValue(annotationNode, "aliases")) != null) {
            linkedList.addAll(list);
        }
        return this.getTarget().findMethod(linkedList, methodNode.desc);
    }

    MethodNode findRemappedMethod(MethodNode methodNode) {
        RemapperChain remapperChain = this.getEnvironment().getRemappers();
        String string = remapperChain.mapMethodName(this.getTarget().getClassRef(), methodNode.name, methodNode.desc);
        if (string.equals(methodNode.name)) {
            return null;
        }
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add(string);
        return this.getTarget().findAliasedMethod(linkedList, methodNode.desc);
    }

    FieldNode findField(FieldNode fieldNode, AnnotationNode annotationNode) {
        List list;
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add(fieldNode.name);
        if (annotationNode != null && (list = (List)Annotations.getValue(annotationNode, "aliases")) != null) {
            linkedList.addAll(list);
        }
        return this.getTarget().findAliasedField(linkedList, fieldNode.desc);
    }

    FieldNode findRemappedField(FieldNode fieldNode) {
        RemapperChain remapperChain = this.getEnvironment().getRemappers();
        String string = remapperChain.mapFieldName(this.getTarget().getClassRef(), fieldNode.name, fieldNode.desc);
        if (string.equals(fieldNode.name)) {
            return null;
        }
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add(string);
        return this.getTarget().findAliasedField(linkedList, fieldNode.desc);
    }

    protected void requireVersion(int n2) {
        this.minRequiredClassVersion = Math.max(this.minRequiredClassVersion, n2);
        if (n2 > MixinEnvironment.getCompatibilityLevel().classVersion()) {
            throw new InvalidMixinException((IMixinContext)this, "Unsupported mixin class version " + n2);
        }
    }

    @Override
    public Extensions getExtensions() {
        return this.targetClass.getExtensions();
    }

    @Override
    public IMixinInfo getMixin() {
        return this.mixin;
    }

    MixinInfo getInfo() {
        return this.mixin;
    }

    @Override
    public int getPriority() {
        return this.mixin.getPriority();
    }

    public Set<String> getInterfaces() {
        return this.mixin.getInterfaces();
    }

    public Collection<MethodNode> getShadowMethods() {
        return this.shadowMethods;
    }

    public List<MethodNode> getMethods() {
        return this.classNode.methods;
    }

    public Set<Map.Entry<FieldNode, ClassInfo.Field>> getShadowFields() {
        return this.shadowFields.entrySet();
    }

    public List<FieldNode> getFields() {
        return this.classNode.fields;
    }

    public Level getLoggingLevel() {
        return this.mixin.getLoggingLevel();
    }

    public boolean shouldSetSourceFile() {
        return this.mixin.getParent().shouldSetSourceFile();
    }

    public String getSourceFile() {
        return this.classNode.sourceFile;
    }

    @Override
    public IReferenceMapper getReferenceMapper() {
        return this.mixin.getParent().getReferenceMapper();
    }

    public void preApply(String string, ClassNode classNode) {
        this.mixin.preApply(string, classNode);
    }

    public void postApply(String string, ClassNode classNode) {
        try {
            this.injectorGroups.validateAll();
        }
        catch (InjectionValidationException injectionValidationException) {
            InjectorGroupInfo injectorGroupInfo = injectionValidationException.getGroup();
            throw new InjectionError(String.format("Critical injection failure: Callback group %s in %s failed injection check: %s", injectorGroupInfo, this.mixin, injectionValidationException.getMessage()));
        }
        this.mixin.postApply(string, classNode);
    }

    public String getUniqueName(MethodNode methodNode, boolean bl) {
        return this.getTarget().getUniqueName(methodNode, bl);
    }

    public String getUniqueName(FieldNode fieldNode) {
        return this.getTarget().getUniqueName(fieldNode);
    }

    public void prepareInjections() {
        this.injectors.clear();
        for (MethodNode methodNode : this.mergedMethods) {
            InjectionInfo injectionInfo = InjectionInfo.parse(this, methodNode);
            if (injectionInfo == null) continue;
            if (injectionInfo.isValid()) {
                injectionInfo.prepare();
                this.injectors.add(injectionInfo);
            }
            methodNode.visibleAnnotations.remove(injectionInfo.getAnnotation());
        }
    }

    public void applyInjections() {
        for (InjectionInfo injectionInfo : this.injectors) {
            injectionInfo.inject();
        }
        for (InjectionInfo injectionInfo : this.injectors) {
            injectionInfo.postInject();
        }
        this.injectors.clear();
    }

    public List<MethodNode> generateAccessors() {
        for (AccessorInfo object : this.accessors) {
            object.locate();
        }
        ArrayList arrayList = new ArrayList();
        for (AccessorInfo accessorInfo : this.accessors) {
            MethodNode methodNode = accessorInfo.generate();
            this.getTarget().addMixinMethod(methodNode);
            arrayList.add(methodNode);
        }
        return arrayList;
    }
}

