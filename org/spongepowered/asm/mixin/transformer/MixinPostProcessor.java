/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinConfig;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.transformers.MixinClassWriter;
import org.spongepowered.asm.transformers.TreeTransformer;
import org.spongepowered.asm.util.Bytecode;

class MixinPostProcessor
extends TreeTransformer
implements MixinConfig.IListener {
    private final Set<String> syntheticInnerClasses = new HashSet<String>();
    private final Map<String, MixinInfo> accessorMixins = new HashMap<String, MixinInfo>();
    private final Set<String> loadable = new HashSet<String>();

    MixinPostProcessor() {
    }

    @Override
    public void onInit(MixinInfo mixinInfo) {
        for (String string : mixinInfo.getSyntheticInnerClasses()) {
            this.registerSyntheticInner(string.replace('/', '.'));
        }
    }

    @Override
    public void onPrepare(MixinInfo mixinInfo) {
        String string = mixinInfo.getClassName();
        if (mixinInfo.isLoadable()) {
            this.registerLoadable(string);
        }
        if (mixinInfo.isAccessor()) {
            this.registerAccessor(mixinInfo);
        }
    }

    void registerSyntheticInner(String string) {
        this.syntheticInnerClasses.add(string);
    }

    void registerLoadable(String string) {
        this.loadable.add(string);
    }

    void registerAccessor(MixinInfo mixinInfo) {
        this.registerLoadable(mixinInfo.getClassName());
        this.accessorMixins.put(mixinInfo.getClassName(), mixinInfo);
    }

    boolean canTransform(String string) {
        return this.syntheticInnerClasses.contains(string) || this.loadable.contains(string);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isDelegationExcluded() {
        return true;
    }

    @Override
    public byte[] transformClassBytes(String string, String string2, byte[] arrby) {
        if (this.syntheticInnerClasses.contains(string2)) {
            return this.processSyntheticInner(arrby);
        }
        if (this.accessorMixins.containsKey(string2)) {
            MixinInfo mixinInfo = this.accessorMixins.get(string2);
            return this.processAccessor(arrby, mixinInfo);
        }
        return arrby;
    }

    private byte[] processSyntheticInner(byte[] arrby) {
        ClassReader classReader = new ClassReader(arrby);
        MixinClassWriter mixinClassWriter = new MixinClassWriter(classReader, 0);
        ClassVisitor classVisitor = new ClassVisitor(327680, mixinClassWriter){

            @Override
            public void visit(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
                super.visit(n2, n3 | 1, string, string2, string3, arrstring);
            }

            @Override
            public FieldVisitor visitField(int n2, String string, String string2, String string3, Object object) {
                if ((n2 & 6) == 0) {
                    n2 |= 1;
                }
                return super.visitField(n2, string, string2, string3, object);
            }

            @Override
            public MethodVisitor visitMethod(int n2, String string, String string2, String string3, String[] arrstring) {
                if ((n2 & 6) == 0) {
                    n2 |= 1;
                }
                return super.visitMethod(n2, string, string2, string3, arrstring);
            }
        };
        classReader.accept(classVisitor, 8);
        return mixinClassWriter.toByteArray();
    }

    private byte[] processAccessor(byte[] arrby, MixinInfo mixinInfo) {
        if (!MixinEnvironment.getCompatibilityLevel().isAtLeast(MixinEnvironment.CompatibilityLevel.JAVA_8)) {
            return arrby;
        }
        boolean bl = false;
        MixinInfo.MixinClassNode mixinClassNode = mixinInfo.getClassNode(0);
        ClassInfo classInfo = mixinInfo.getTargets().get(0);
        for (MixinInfo.MixinMethodNode mixinMethodNode : mixinClassNode.mixinMethods) {
            if (!Bytecode.hasFlag(mixinMethodNode, 8)) continue;
            AnnotationNode annotationNode = mixinMethodNode.getVisibleAnnotation(Accessor.class);
            AnnotationNode annotationNode2 = mixinMethodNode.getVisibleAnnotation(Invoker.class);
            if (annotationNode == null && annotationNode2 == null) continue;
            ClassInfo.Method method = MixinPostProcessor.getAccessorMethod(mixinInfo, mixinMethodNode, classInfo);
            MixinPostProcessor.createProxy(mixinMethodNode, classInfo, method);
            bl = true;
        }
        if (bl) {
            return this.writeClass(mixinClassNode);
        }
        return arrby;
    }

    private static ClassInfo.Method getAccessorMethod(MixinInfo mixinInfo, MethodNode methodNode, ClassInfo classInfo) throws MixinTransformerError {
        ClassInfo.Method method = mixinInfo.getClassInfo().findMethod(methodNode, 10);
        if (!method.isRenamed()) {
            throw new MixinTransformerError("Unexpected state: " + mixinInfo + " loaded before " + classInfo + " was conformed");
        }
        return method;
    }

    private static void createProxy(MethodNode methodNode, ClassInfo classInfo, ClassInfo.Method method) {
        methodNode.instructions.clear();
        Type[] arrtype = Type.getArgumentTypes(methodNode.desc);
        Type type = Type.getReturnType(methodNode.desc);
        Bytecode.loadArgs(arrtype, methodNode.instructions, 0);
        methodNode.instructions.add(new MethodInsnNode(184, classInfo.getName(), method.getName(), methodNode.desc, false));
        methodNode.instructions.add(new InsnNode(type.getOpcode(172)));
        methodNode.maxStack = Bytecode.getFirstNonArgLocalIndex(arrtype, false);
        methodNode.maxLocals = 0;
    }
}

