/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.tools.agent;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.mixin.MixinEnvironment;

class MixinAgentClassLoader
extends ClassLoader {
    private static final Logger logger = LogManager.getLogger((String)"mixin.agent");
    private Map<Class<?>, byte[]> mixins = new HashMap();
    private Map<String, byte[]> targets = new HashMap<String, byte[]>();

    MixinAgentClassLoader() {
    }

    void addMixinClass(String string) {
        logger.debug("Mixin class {} added to class loader", new Object[]{string});
        try {
            byte[] arrby = this.materialise(string);
            Class<?> class_ = this.defineClass(string, arrby, 0, arrby.length);
            class_.newInstance();
            this.mixins.put(class_, arrby);
        }
        catch (Throwable throwable) {
            logger.catching(throwable);
        }
    }

    void addTargetClass(String string, byte[] arrby) {
        this.targets.put(string, arrby);
    }

    byte[] getFakeMixinBytecode(Class<?> class_) {
        return this.mixins.get(class_);
    }

    byte[] getOriginalTargetBytecode(String string) {
        return this.targets.get(string);
    }

    private byte[] materialise(String string) {
        ClassWriter classWriter = new ClassWriter(3);
        classWriter.visit(MixinEnvironment.getCompatibilityLevel().classVersion(), 1, string.replace('.', '/'), null, Type.getInternalName(Object.class), null);
        MethodVisitor methodVisitor = classWriter.visitMethod(1, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitMethodInsn(183, Type.getInternalName(Object.class), "<init>", "()V", false);
        methodVisitor.visitInsn(177);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}

