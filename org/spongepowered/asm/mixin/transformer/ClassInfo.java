/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.transformer.MethodMapper;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.ClassSignature;
import org.spongepowered.asm.util.perf.Profiler;

public final class ClassInfo {
    public static final int INCLUDE_PRIVATE = 2;
    public static final int INCLUDE_STATIC = 8;
    public static final int INCLUDE_ALL = 10;
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static final Profiler profiler = MixinEnvironment.getProfiler();
    private static final String JAVA_LANG_OBJECT = "java/lang/Object";
    private static final Map<String, ClassInfo> cache = new HashMap<String, ClassInfo>();
    private static final ClassInfo OBJECT = new ClassInfo();
    private final String name;
    private final String superName;
    private final String outerName;
    private final boolean isProbablyStatic;
    private final Set<String> interfaces;
    private final Set<Method> methods;
    private final Set<Field> fields;
    private final Set<MixinInfo> mixins = new HashSet<MixinInfo>();
    private final Map<ClassInfo, ClassInfo> correspondingTypes = new HashMap<ClassInfo, ClassInfo>();
    private final MixinInfo mixin;
    private final MethodMapper methodMapper;
    private final boolean isMixin;
    private final boolean isInterface;
    private final int access;
    private ClassInfo superClass;
    private ClassInfo outerClass;
    private ClassSignature signature;

    private ClassInfo() {
        this.name = JAVA_LANG_OBJECT;
        this.superName = null;
        this.outerName = null;
        this.isProbablyStatic = true;
        this.methods = ImmutableSet.of(new Method("getClass", "()Ljava/lang/Class;"), new Method("hashCode", "()I"), new Method("equals", "(Ljava/lang/Object;)Z"), new Method("clone", "()Ljava/lang/Object;"), new Method("toString", "()Ljava/lang/String;"), new Method("notify", "()V"), new Method("notifyAll", "()V"), new Method("wait", "(J)V"), new Method("wait", "(JI)V"), new Method("wait", "()V"), new Method("finalize", "()V"));
        this.fields = Collections.emptySet();
        this.isInterface = false;
        this.interfaces = Collections.emptySet();
        this.access = 1;
        this.isMixin = false;
        this.mixin = null;
        this.methodMapper = null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    private ClassInfo(ClassNode classNode) {
        Profiler.Section section = profiler.begin(1, "class.meta");
        try {
            void var4_7;
            this.name = classNode.name;
            this.superName = classNode.superName != null ? classNode.superName : JAVA_LANG_OBJECT;
            this.methods = new HashSet<Method>();
            this.fields = new HashSet<Field>();
            this.isInterface = (classNode.access & 0x200) != 0;
            this.interfaces = new HashSet<String>();
            this.access = classNode.access;
            this.isMixin = classNode instanceof MixinInfo.MixinClassNode;
            this.mixin = this.isMixin ? ((MixinInfo.MixinClassNode)classNode).getMixin() : null;
            this.interfaces.addAll(classNode.interfaces);
            for (MethodNode object2 : classNode.methods) {
                this.addMethod(object2, this.isMixin);
            }
            boolean bl = true;
            String string = classNode.outerClass;
            for (FieldNode fieldNode : classNode.fields) {
                if ((fieldNode.access & 0x1000) != 0 && fieldNode.name.startsWith("this$")) {
                    String string2;
                    bl = false;
                    if (var4_7 == null && (string2 = fieldNode.desc) != null && string2.startsWith("L")) {
                        String string3 = string2.substring(1, string2.length() - 1);
                    }
                }
                this.fields.add(new Field(fieldNode, this.isMixin));
            }
            this.isProbablyStatic = bl;
            this.outerName = var4_7;
            this.methodMapper = new MethodMapper(MixinEnvironment.getCurrentEnvironment(), this);
            this.signature = ClassSignature.ofLazy(classNode);
        }
        finally {
            section.end();
        }
    }

    void addInterface(String string) {
        this.interfaces.add(string);
        this.getSignature().addInterface(string);
    }

    void addMethod(MethodNode methodNode) {
        this.addMethod(methodNode, true);
    }

    private void addMethod(MethodNode methodNode, boolean bl) {
        if (!methodNode.name.startsWith("<")) {
            this.methods.add(new Method(methodNode, bl));
        }
    }

    void addMixin(MixinInfo mixinInfo) {
        if (this.isMixin) {
            throw new IllegalArgumentException("Cannot add target " + this.name + " for " + mixinInfo.getClassName() + " because the target is a mixin");
        }
        this.mixins.add(mixinInfo);
    }

    public Set<MixinInfo> getMixins() {
        return Collections.unmodifiableSet(this.mixins);
    }

    public boolean isMixin() {
        return this.isMixin;
    }

    public boolean isPublic() {
        return (this.access & 1) != 0;
    }

    public boolean isAbstract() {
        return (this.access & 0x400) != 0;
    }

    public boolean isSynthetic() {
        return (this.access & 0x1000) != 0;
    }

    public boolean isProbablyStatic() {
        return this.isProbablyStatic;
    }

    public boolean isInner() {
        return this.outerName != null;
    }

    public boolean isInterface() {
        return this.isInterface;
    }

    public Set<String> getInterfaces() {
        return Collections.unmodifiableSet(this.interfaces);
    }

    public String toString() {
        return this.name;
    }

    public MethodMapper getMethodMapper() {
        return this.methodMapper;
    }

    public int getAccess() {
        return this.access;
    }

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return this.name.replace('/', '.');
    }

    public String getSuperName() {
        return this.superName;
    }

    public ClassInfo getSuperClass() {
        if (this.superClass == null && this.superName != null) {
            this.superClass = ClassInfo.forName(this.superName);
        }
        return this.superClass;
    }

    public String getOuterName() {
        return this.outerName;
    }

    public ClassInfo getOuterClass() {
        if (this.outerClass == null && this.outerName != null) {
            this.outerClass = ClassInfo.forName(this.outerName);
        }
        return this.outerClass;
    }

    public ClassSignature getSignature() {
        return this.signature.wake();
    }

    List<ClassInfo> getTargets() {
        if (this.mixin != null) {
            ArrayList<ClassInfo> arrayList = new ArrayList<ClassInfo>();
            arrayList.add(this);
            arrayList.addAll(this.mixin.getTargets());
            return arrayList;
        }
        return ImmutableList.of(this);
    }

    public Set<Method> getMethods() {
        return Collections.unmodifiableSet(this.methods);
    }

    public Set<Method> getInterfaceMethods(boolean bl) {
        HashSet<Method> hashSet = new HashSet<Method>();
        if (!this.isInterface) {
            for (ClassInfo classInfo = this.addMethodsRecursive(hashSet, bl); classInfo != null && classInfo != OBJECT; classInfo = classInfo.addMethodsRecursive(hashSet, bl)) {
            }
        }
        Iterator iterator2 = hashSet.iterator();
        while (iterator2.hasNext()) {
            if (((Method)iterator2.next()).isAbstract()) continue;
            iterator2.remove();
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private ClassInfo addMethodsRecursive(Set<Method> set, boolean bl) {
        if (this.isInterface) {
            for (Method object : this.methods) {
                if (!object.isAbstract()) {
                    set.remove(object);
                }
                set.add(object);
            }
        } else if (!this.isMixin && bl) {
            for (MixinInfo mixinInfo : this.mixins) {
                mixinInfo.getClassInfo().addMethodsRecursive(set, bl);
            }
        }
        for (String string : this.interfaces) {
            ClassInfo.forName(string).addMethodsRecursive(set, bl);
        }
        return this.getSuperClass();
    }

    public boolean hasSuperClass(String string) {
        return this.hasSuperClass(string, Traversal.NONE);
    }

    public boolean hasSuperClass(String string, Traversal traversal) {
        if (JAVA_LANG_OBJECT.equals(string)) {
            return true;
        }
        return this.findSuperClass(string, traversal) != null;
    }

    public boolean hasSuperClass(ClassInfo classInfo) {
        return this.hasSuperClass(classInfo, Traversal.NONE, false);
    }

    public boolean hasSuperClass(ClassInfo classInfo, Traversal traversal) {
        return this.hasSuperClass(classInfo, traversal, false);
    }

    public boolean hasSuperClass(ClassInfo classInfo, Traversal traversal, boolean bl) {
        if (OBJECT == classInfo) {
            return true;
        }
        return this.findSuperClass(classInfo.name, traversal, bl) != null;
    }

    public ClassInfo findSuperClass(String string) {
        return this.findSuperClass(string, Traversal.NONE);
    }

    public ClassInfo findSuperClass(String string, Traversal traversal) {
        return this.findSuperClass(string, traversal, false, new HashSet<String>());
    }

    public ClassInfo findSuperClass(String string, Traversal traversal, boolean bl) {
        if (ClassInfo.OBJECT.name.equals(string)) {
            return null;
        }
        return this.findSuperClass(string, traversal, bl, new HashSet<String>());
    }

    private ClassInfo findSuperClass(String string, Traversal traversal, boolean bl, Set<String> set) {
        Object object;
        Iterator<MixinInfo> iterator2;
        ClassInfo classInfo = this.getSuperClass();
        if (classInfo != null) {
            iterator2 = classInfo.getTargets().iterator();
            while (iterator2.hasNext()) {
                ClassInfo object2 = (ClassInfo)iterator2.next();
                if (string.equals(object2.getName())) {
                    return classInfo;
                }
                object = object2.findSuperClass(string, traversal.next(), bl, set);
                if (object == null) continue;
                return object;
            }
        }
        if (bl && (iterator2 = this.findInterface(string)) != null) {
            return iterator2;
        }
        if (traversal.canTraverse()) {
            for (MixinInfo mixinInfo : this.mixins) {
                object = mixinInfo.getClassName();
                if (set.contains(object)) continue;
                set.add((String)object);
                ClassInfo classInfo2 = mixinInfo.getClassInfo();
                if (string.equals(classInfo2.getName())) {
                    return classInfo2;
                }
                ClassInfo classInfo3 = classInfo2.findSuperClass(string, Traversal.ALL, bl, set);
                if (classInfo3 == null) continue;
                return classInfo3;
            }
        }
        return null;
    }

    private ClassInfo findInterface(String string) {
        for (String string2 : this.getInterfaces()) {
            ClassInfo classInfo = ClassInfo.forName(string2);
            if (string.equals(string2)) {
                return classInfo;
            }
            ClassInfo classInfo2 = classInfo.findInterface(string);
            if (classInfo2 == null) continue;
            return classInfo2;
        }
        return null;
    }

    ClassInfo findCorrespondingType(ClassInfo classInfo) {
        if (classInfo == null || !classInfo.isMixin || this.isMixin) {
            return null;
        }
        ClassInfo classInfo2 = this.correspondingTypes.get(classInfo);
        if (classInfo2 == null) {
            classInfo2 = this.findSuperTypeForMixin(classInfo);
            this.correspondingTypes.put(classInfo, classInfo2);
        }
        return classInfo2;
    }

    private ClassInfo findSuperTypeForMixin(ClassInfo classInfo) {
        for (ClassInfo classInfo2 = this; classInfo2 != null && classInfo2 != OBJECT; classInfo2 = classInfo2.getSuperClass()) {
            for (MixinInfo mixinInfo : classInfo2.mixins) {
                if (!mixinInfo.getClassInfo().equals(classInfo)) continue;
                return classInfo2;
            }
        }
        return null;
    }

    public boolean hasMixinInHierarchy() {
        if (!this.isMixin) {
            return false;
        }
        for (ClassInfo classInfo = this.getSuperClass(); classInfo != null && classInfo != OBJECT; classInfo = classInfo.getSuperClass()) {
            if (!classInfo.isMixin) continue;
            return true;
        }
        return false;
    }

    public boolean hasMixinTargetInHierarchy() {
        if (this.isMixin) {
            return false;
        }
        for (ClassInfo classInfo = this.getSuperClass(); classInfo != null && classInfo != OBJECT; classInfo = classInfo.getSuperClass()) {
            if (classInfo.mixins.size() <= 0) continue;
            return true;
        }
        return false;
    }

    public Method findMethodInHierarchy(MethodNode methodNode, SearchType searchType) {
        return this.findMethodInHierarchy(methodNode.name, methodNode.desc, searchType, Traversal.NONE);
    }

    public Method findMethodInHierarchy(MethodNode methodNode, SearchType searchType, int n2) {
        return this.findMethodInHierarchy(methodNode.name, methodNode.desc, searchType, Traversal.NONE, n2);
    }

    public Method findMethodInHierarchy(MethodInsnNode methodInsnNode, SearchType searchType) {
        return this.findMethodInHierarchy(methodInsnNode.name, methodInsnNode.desc, searchType, Traversal.NONE);
    }

    public Method findMethodInHierarchy(MethodInsnNode methodInsnNode, SearchType searchType, int n2) {
        return this.findMethodInHierarchy(methodInsnNode.name, methodInsnNode.desc, searchType, Traversal.NONE, n2);
    }

    public Method findMethodInHierarchy(String string, String string2, SearchType searchType) {
        return this.findMethodInHierarchy(string, string2, searchType, Traversal.NONE);
    }

    public Method findMethodInHierarchy(String string, String string2, SearchType searchType, Traversal traversal) {
        return this.findMethodInHierarchy(string, string2, searchType, traversal, 0);
    }

    public Method findMethodInHierarchy(String string, String string2, SearchType searchType, Traversal traversal, int n2) {
        return (Method)this.findInHierarchy(string, string2, searchType, traversal, n2, Member.Type.METHOD);
    }

    public Field findFieldInHierarchy(FieldNode fieldNode, SearchType searchType) {
        return this.findFieldInHierarchy(fieldNode.name, fieldNode.desc, searchType, Traversal.NONE);
    }

    public Field findFieldInHierarchy(FieldNode fieldNode, SearchType searchType, int n2) {
        return this.findFieldInHierarchy(fieldNode.name, fieldNode.desc, searchType, Traversal.NONE, n2);
    }

    public Field findFieldInHierarchy(FieldInsnNode fieldInsnNode, SearchType searchType) {
        return this.findFieldInHierarchy(fieldInsnNode.name, fieldInsnNode.desc, searchType, Traversal.NONE);
    }

    public Field findFieldInHierarchy(FieldInsnNode fieldInsnNode, SearchType searchType, int n2) {
        return this.findFieldInHierarchy(fieldInsnNode.name, fieldInsnNode.desc, searchType, Traversal.NONE, n2);
    }

    public Field findFieldInHierarchy(String string, String string2, SearchType searchType) {
        return this.findFieldInHierarchy(string, string2, searchType, Traversal.NONE);
    }

    public Field findFieldInHierarchy(String string, String string2, SearchType searchType, Traversal traversal) {
        return this.findFieldInHierarchy(string, string2, searchType, traversal, 0);
    }

    public Field findFieldInHierarchy(String string, String string2, SearchType searchType, Traversal traversal, int n2) {
        return (Field)this.findInHierarchy(string, string2, searchType, traversal, n2, Member.Type.FIELD);
    }

    private <M extends Member> M findInHierarchy(String string, String string2, SearchType searchType, Traversal traversal, int n2, Member.Type type) {
        ClassInfo classInfo;
        ClassInfo classInfo2;
        if (searchType == SearchType.ALL_CLASSES) {
            classInfo2 = this.findMember(string, string2, n2, type);
            if (classInfo2 != null) {
                return (M)classInfo2;
            }
            if (traversal.canTraverse()) {
                for (MixinInfo object : this.mixins) {
                    classInfo = object.getClassInfo().findMember(string, string2, n2, type);
                    if (classInfo == null) continue;
                    return (M)this.cloneMember((M)((Object)classInfo));
                }
            }
        }
        if ((classInfo2 = this.getSuperClass()) != null) {
            for (ClassInfo classInfo3 : classInfo2.getTargets()) {
                classInfo = classInfo3.findInHierarchy(string, string2, SearchType.ALL_CLASSES, traversal.next(), n2 & 0xFFFFFFFD, type);
                if (classInfo == null) continue;
                return (M)classInfo;
            }
        }
        if (type == Member.Type.METHOD && (this.isInterface || MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces())) {
            for (String string3 : this.interfaces) {
                classInfo = ClassInfo.forName(string3);
                if (classInfo == null) {
                    logger.debug("Failed to resolve declared interface {} on {}", new Object[]{string3, this.name});
                    continue;
                }
                M m2 = classInfo.findInHierarchy(string, string2, SearchType.ALL_CLASSES, traversal.next(), n2 & 0xFFFFFFFD, type);
                if (m2 == null) continue;
                return (M)(this.isInterface ? m2 : new InterfaceMethod((Member)m2));
            }
        }
        return null;
    }

    private <M extends Member> M cloneMember(M m2) {
        if (m2 instanceof Method) {
            return (M)new Method(m2);
        }
        return (M)new Field(m2);
    }

    public Method findMethod(MethodNode methodNode) {
        return this.findMethod(methodNode.name, methodNode.desc, methodNode.access);
    }

    public Method findMethod(MethodNode methodNode, int n2) {
        return this.findMethod(methodNode.name, methodNode.desc, n2);
    }

    public Method findMethod(MethodInsnNode methodInsnNode) {
        return this.findMethod(methodInsnNode.name, methodInsnNode.desc, 0);
    }

    public Method findMethod(MethodInsnNode methodInsnNode, int n2) {
        return this.findMethod(methodInsnNode.name, methodInsnNode.desc, n2);
    }

    public Method findMethod(String string, String string2, int n2) {
        return (Method)this.findMember(string, string2, n2, Member.Type.METHOD);
    }

    public Field findField(FieldNode fieldNode) {
        return this.findField(fieldNode.name, fieldNode.desc, fieldNode.access);
    }

    public Field findField(FieldInsnNode fieldInsnNode, int n2) {
        return this.findField(fieldInsnNode.name, fieldInsnNode.desc, n2);
    }

    public Field findField(String string, String string2, int n2) {
        return (Field)this.findMember(string, string2, n2, Member.Type.FIELD);
    }

    private <M extends Member> M findMember(String string, String string2, int n2, Member.Type type) {
        Set<Member> set = type == Member.Type.METHOD ? this.methods : this.fields;
        for (Member member : set) {
            if (!member.equals(string, string2) || !member.matchesFlags(n2)) continue;
            return (M)member;
        }
        return null;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ClassInfo)) {
            return false;
        }
        return ((ClassInfo)object).name.equals(this.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    static ClassInfo fromClassNode(ClassNode classNode) {
        ClassInfo classInfo = cache.get(classNode.name);
        if (classInfo == null) {
            classInfo = new ClassInfo(classNode);
            cache.put(classNode.name, classInfo);
        }
        return classInfo;
    }

    public static ClassInfo forName(String string) {
        ClassInfo classInfo = cache.get(string = string.replace('.', '/'));
        if (classInfo == null) {
            try {
                ClassNode classNode = MixinService.getService().getBytecodeProvider().getClassNode(string);
                classInfo = new ClassInfo(classNode);
            }
            catch (Exception exception) {
                logger.catching(Level.TRACE, (Throwable)exception);
                logger.warn("Error loading class: {} ({}: {})", new Object[]{string, exception.getClass().getName(), exception.getMessage()});
            }
            cache.put(string, classInfo);
            logger.trace("Added class metadata for {} to metadata cache", new Object[]{string});
        }
        return classInfo;
    }

    public static ClassInfo forType(Type type) {
        if (type.getSort() == 9) {
            return ClassInfo.forType(type.getElementType());
        }
        if (type.getSort() < 9) {
            return null;
        }
        return ClassInfo.forName(type.getClassName().replace('.', '/'));
    }

    public static ClassInfo getCommonSuperClass(String string, String string2) {
        if (string == null || string2 == null) {
            return OBJECT;
        }
        return ClassInfo.getCommonSuperClass(ClassInfo.forName(string), ClassInfo.forName(string2));
    }

    public static ClassInfo getCommonSuperClass(Type type, Type type2) {
        if (type == null || type2 == null || type.getSort() != 10 || type2.getSort() != 10) {
            return OBJECT;
        }
        return ClassInfo.getCommonSuperClass(ClassInfo.forType(type), ClassInfo.forType(type2));
    }

    private static ClassInfo getCommonSuperClass(ClassInfo classInfo, ClassInfo classInfo2) {
        return ClassInfo.getCommonSuperClass(classInfo, classInfo2, false);
    }

    public static ClassInfo getCommonSuperClassOrInterface(String string, String string2) {
        if (string == null || string2 == null) {
            return OBJECT;
        }
        return ClassInfo.getCommonSuperClassOrInterface(ClassInfo.forName(string), ClassInfo.forName(string2));
    }

    public static ClassInfo getCommonSuperClassOrInterface(Type type, Type type2) {
        if (type == null || type2 == null || type.getSort() != 10 || type2.getSort() != 10) {
            return OBJECT;
        }
        return ClassInfo.getCommonSuperClassOrInterface(ClassInfo.forType(type), ClassInfo.forType(type2));
    }

    public static ClassInfo getCommonSuperClassOrInterface(ClassInfo classInfo, ClassInfo classInfo2) {
        return ClassInfo.getCommonSuperClass(classInfo, classInfo2, true);
    }

    private static ClassInfo getCommonSuperClass(ClassInfo classInfo, ClassInfo classInfo2, boolean bl) {
        if (classInfo.hasSuperClass(classInfo2, Traversal.NONE, bl)) {
            return classInfo2;
        }
        if (classInfo2.hasSuperClass(classInfo, Traversal.NONE, bl)) {
            return classInfo;
        }
        if (classInfo.isInterface() || classInfo2.isInterface()) {
            return OBJECT;
        }
        do {
            if ((classInfo = classInfo.getSuperClass()) != null) continue;
            return OBJECT;
        } while (!classInfo2.hasSuperClass(classInfo, Traversal.NONE, bl));
        return classInfo;
    }

    static {
        cache.put(JAVA_LANG_OBJECT, OBJECT);
    }

    class Field
    extends Member {
        public Field(Member member) {
            super(member);
        }

        public Field(FieldNode fieldNode) {
            this(fieldNode, false);
        }

        public Field(FieldNode fieldNode, boolean bl) {
            super(Member.Type.FIELD, fieldNode.name, fieldNode.desc, fieldNode.access, bl);
            this.setUnique(Annotations.getVisible(fieldNode, Unique.class) != null);
            if (Annotations.getVisible(fieldNode, Shadow.class) != null) {
                boolean bl2 = Annotations.getVisible(fieldNode, Final.class) != null;
                boolean bl3 = Annotations.getVisible(fieldNode, Mutable.class) != null;
                this.setDecoratedFinal(bl2, bl3);
            }
        }

        public Field(String string, String string2, int n2) {
            super(Member.Type.FIELD, string, string2, n2, false);
        }

        public Field(String string, String string2, int n2, boolean bl) {
            super(Member.Type.FIELD, string, string2, n2, bl);
        }

        @Override
        public ClassInfo getOwner() {
            return ClassInfo.this;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof Field)) {
                return false;
            }
            return super.equals(object);
        }

        @Override
        protected String getDisplayFormat() {
            return "%s:%s";
        }
    }

    public class InterfaceMethod
    extends Method {
        private final ClassInfo owner;

        public InterfaceMethod(Member member) {
            super(member);
            this.owner = member.getOwner();
        }

        @Override
        public ClassInfo getOwner() {
            return this.owner;
        }

        @Override
        public ClassInfo getImplementor() {
            return ClassInfo.this;
        }
    }

    public class Method
    extends Member {
        private final List<FrameData> frames;
        private boolean isAccessor;

        public Method(Member member) {
            super(member);
            this.frames = member instanceof Method ? ((Method)member).frames : null;
        }

        public Method(MethodNode methodNode) {
            this(methodNode, false);
            this.setUnique(Annotations.getVisible(methodNode, Unique.class) != null);
            this.isAccessor = Annotations.getSingleVisible(methodNode, Accessor.class, Invoker.class) != null;
        }

        public Method(MethodNode methodNode, boolean bl) {
            super(Member.Type.METHOD, methodNode.name, methodNode.desc, methodNode.access, bl);
            this.frames = this.gatherFrames(methodNode);
            this.setUnique(Annotations.getVisible(methodNode, Unique.class) != null);
            this.isAccessor = Annotations.getSingleVisible(methodNode, Accessor.class, Invoker.class) != null;
        }

        public Method(String string, String string2) {
            super(Member.Type.METHOD, string, string2, 1, false);
            this.frames = null;
        }

        public Method(String string, String string2, int n2) {
            super(Member.Type.METHOD, string, string2, n2, false);
            this.frames = null;
        }

        public Method(String string, String string2, int n2, boolean bl) {
            super(Member.Type.METHOD, string, string2, n2, bl);
            this.frames = null;
        }

        private List<FrameData> gatherFrames(MethodNode methodNode) {
            ArrayList<FrameData> arrayList = new ArrayList<FrameData>();
            ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
            while (listIterator.hasNext()) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
                if (!(abstractInsnNode instanceof FrameNode)) continue;
                arrayList.add(new FrameData(methodNode.instructions.indexOf(abstractInsnNode), (FrameNode)abstractInsnNode));
            }
            return arrayList;
        }

        public List<FrameData> getFrames() {
            return this.frames;
        }

        @Override
        public ClassInfo getOwner() {
            return ClassInfo.this;
        }

        public boolean isAccessor() {
            return this.isAccessor;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof Method)) {
                return false;
            }
            return super.equals(object);
        }
    }

    static abstract class Member {
        private final Type type;
        private final String memberName;
        private final String memberDesc;
        private final boolean isInjected;
        private final int modifiers;
        private String currentName;
        private String currentDesc;
        private boolean decoratedFinal;
        private boolean decoratedMutable;
        private boolean unique;

        protected Member(Member member) {
            this(member.type, member.memberName, member.memberDesc, member.modifiers, member.isInjected);
            this.currentName = member.currentName;
            this.currentDesc = member.currentDesc;
            this.unique = member.unique;
        }

        protected Member(Type type, String string, String string2, int n2) {
            this(type, string, string2, n2, false);
        }

        protected Member(Type type, String string, String string2, int n2, boolean bl) {
            this.type = type;
            this.memberName = string;
            this.memberDesc = string2;
            this.isInjected = bl;
            this.currentName = string;
            this.currentDesc = string2;
            this.modifiers = n2;
        }

        public String getOriginalName() {
            return this.memberName;
        }

        public String getName() {
            return this.currentName;
        }

        public String getOriginalDesc() {
            return this.memberDesc;
        }

        public String getDesc() {
            return this.currentDesc;
        }

        public boolean isInjected() {
            return this.isInjected;
        }

        public boolean isRenamed() {
            return !this.currentName.equals(this.memberName);
        }

        public boolean isRemapped() {
            return !this.currentDesc.equals(this.memberDesc);
        }

        public boolean isPrivate() {
            return (this.modifiers & 2) != 0;
        }

        public boolean isStatic() {
            return (this.modifiers & 8) != 0;
        }

        public boolean isAbstract() {
            return (this.modifiers & 0x400) != 0;
        }

        public boolean isFinal() {
            return (this.modifiers & 0x10) != 0;
        }

        public boolean isSynthetic() {
            return (this.modifiers & 0x1000) != 0;
        }

        public boolean isUnique() {
            return this.unique;
        }

        public void setUnique(boolean bl) {
            this.unique = bl;
        }

        public boolean isDecoratedFinal() {
            return this.decoratedFinal;
        }

        public boolean isDecoratedMutable() {
            return this.decoratedMutable;
        }

        public void setDecoratedFinal(boolean bl, boolean bl2) {
            this.decoratedFinal = bl;
            this.decoratedMutable = bl2;
        }

        public boolean matchesFlags(int n2) {
            return ((~this.modifiers | n2 & 2) & 2) != 0 && ((~this.modifiers | n2 & 8) & 8) != 0;
        }

        public abstract ClassInfo getOwner();

        public ClassInfo getImplementor() {
            return this.getOwner();
        }

        public int getAccess() {
            return this.modifiers;
        }

        public String renameTo(String string) {
            this.currentName = string;
            return string;
        }

        public String remapTo(String string) {
            this.currentDesc = string;
            return string;
        }

        public boolean equals(String string, String string2) {
            return !(!this.memberName.equals(string) && !this.currentName.equals(string) || !this.memberDesc.equals(string2) && !this.currentDesc.equals(string2));
        }

        public boolean equals(Object object) {
            if (!(object instanceof Member)) {
                return false;
            }
            Member member = (Member)object;
            return !(!member.memberName.equals(this.memberName) && !member.currentName.equals(this.currentName) || !member.memberDesc.equals(this.memberDesc) && !member.currentDesc.equals(this.currentDesc));
        }

        public int hashCode() {
            return this.toString().hashCode();
        }

        public String toString() {
            return String.format(this.getDisplayFormat(), this.memberName, this.memberDesc);
        }

        protected String getDisplayFormat() {
            return "%s%s";
        }

        static enum Type {
            METHOD,
            FIELD;

        }
    }

    public static class FrameData {
        private static final String[] FRAMETYPES = new String[]{"NEW", "FULL", "APPEND", "CHOP", "SAME", "SAME1"};
        public final int index;
        public final int type;
        public final int locals;

        FrameData(int n2, int n3, int n4) {
            this.index = n2;
            this.type = n3;
            this.locals = n4;
        }

        FrameData(int n2, FrameNode frameNode) {
            this.index = n2;
            this.type = frameNode.type;
            this.locals = frameNode.local != null ? frameNode.local.size() : 0;
        }

        public String toString() {
            return String.format("FrameData[index=%d, type=%s, locals=%d]", this.index, FRAMETYPES[this.type + 1], this.locals);
        }
    }

    public static enum Traversal {
        NONE(null, false, SearchType.SUPER_CLASSES_ONLY),
        ALL(null, true, SearchType.ALL_CLASSES),
        IMMEDIATE(NONE, true, SearchType.SUPER_CLASSES_ONLY),
        SUPER(ALL, false, SearchType.SUPER_CLASSES_ONLY);

        private final Traversal next;
        private final boolean traverse;
        private final SearchType searchType;

        private Traversal(Traversal traversal, boolean bl, SearchType searchType) {
            this.next = traversal != null ? traversal : this;
            this.traverse = bl;
            this.searchType = searchType;
        }

        public Traversal next() {
            return this.next;
        }

        public boolean canTraverse() {
            return this.traverse;
        }

        public SearchType getSearchType() {
            return this.searchType;
        }
    }

    public static enum SearchType {
        ALL_CLASSES,
        SUPER_CLASSES_ONLY;

    }
}

