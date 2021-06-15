/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.commons.ClassRemapper;
import org.spongepowered.asm.lib.commons.Remapper;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.transformers.MixinClassWriter;

final class InnerClassGenerator
implements IClassGenerator {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final Map<String, String> innerClassNames = new HashMap<String, String>();
    private final Map<String, InnerClassInfo> innerClasses = new HashMap<String, InnerClassInfo>();

    InnerClassGenerator() {
    }

    public String registerInnerClass(MixinInfo mixinInfo, String string, MixinTargetContext mixinTargetContext) {
        String string2 = String.format("%s%s", string, mixinTargetContext);
        String string3 = this.innerClassNames.get(string2);
        if (string3 == null) {
            string3 = InnerClassGenerator.getUniqueReference(string, mixinTargetContext);
            this.innerClassNames.put(string2, string3);
            this.innerClasses.put(string3, new InnerClassInfo(string3, string, mixinInfo, mixinTargetContext));
            logger.debug("Inner class {} in {} on {} gets unique name {}", new Object[]{string, mixinInfo.getClassRef(), mixinTargetContext.getTargetClassRef(), string3});
        }
        return string3;
    }

    @Override
    public byte[] generate(String string) {
        String string2 = string.replace('.', '/');
        InnerClassInfo innerClassInfo = this.innerClasses.get(string2);
        if (innerClassInfo != null) {
            return this.generate(innerClassInfo);
        }
        return null;
    }

    private byte[] generate(InnerClassInfo innerClassInfo) {
        try {
            logger.debug("Generating mapped inner class {} (originally {})", new Object[]{innerClassInfo.getName(), innerClassInfo.getOriginalName()});
            ClassReader classReader = new ClassReader(innerClassInfo.getClassBytes());
            MixinClassWriter mixinClassWriter = new MixinClassWriter(classReader, 0);
            classReader.accept(new InnerClassAdapter((ClassVisitor)mixinClassWriter, innerClassInfo), 8);
            return mixinClassWriter.toByteArray();
        }
        catch (InvalidMixinException invalidMixinException) {
            throw invalidMixinException;
        }
        catch (Exception exception) {
            logger.catching((Throwable)exception);
            return null;
        }
    }

    private static String getUniqueReference(String string, MixinTargetContext mixinTargetContext) {
        String string2 = string.substring(string.lastIndexOf(36) + 1);
        if (string2.matches("^[0-9]+$")) {
            string2 = "Anonymous";
        }
        return String.format("%s$%s$%s", mixinTargetContext.getTargetClassRef(), string2, UUID.randomUUID().toString().replace("-", ""));
    }

    static class InnerClassAdapter
    extends ClassRemapper {
        private final InnerClassInfo info;

        public InnerClassAdapter(ClassVisitor classVisitor, InnerClassInfo innerClassInfo) {
            super(327680, classVisitor, innerClassInfo);
            this.info = innerClassInfo;
        }

        @Override
        public void visitSource(String string, String string2) {
            super.visitSource(string, string2);
            AnnotationVisitor annotationVisitor = this.cv.visitAnnotation("Lorg/spongepowered/asm/mixin/transformer/meta/MixinInner;", false);
            annotationVisitor.visit("mixin", this.info.getOwner().toString());
            annotationVisitor.visit("name", this.info.getOriginalName().substring(this.info.getOriginalName().lastIndexOf(47) + 1));
            annotationVisitor.visitEnd();
        }

        @Override
        public void visitInnerClass(String string, String string2, String string3, int n2) {
            if (string.startsWith(this.info.getOriginalName() + "$")) {
                throw new InvalidMixinException((IMixinInfo)this.info.getOwner(), "Found unsupported nested inner class " + string + " in " + this.info.getOriginalName());
            }
            super.visitInnerClass(string, string2, string3, n2);
        }
    }

    static class InnerClassInfo
    extends Remapper {
        private final String name;
        private final String originalName;
        private final MixinInfo owner;
        private final MixinTargetContext target;
        private final String ownerName;
        private final String targetName;

        InnerClassInfo(String string, String string2, MixinInfo mixinInfo, MixinTargetContext mixinTargetContext) {
            this.name = string;
            this.originalName = string2;
            this.owner = mixinInfo;
            this.ownerName = mixinInfo.getClassRef();
            this.target = mixinTargetContext;
            this.targetName = mixinTargetContext.getTargetClassRef();
        }

        String getName() {
            return this.name;
        }

        String getOriginalName() {
            return this.originalName;
        }

        MixinInfo getOwner() {
            return this.owner;
        }

        MixinTargetContext getTarget() {
            return this.target;
        }

        String getOwnerName() {
            return this.ownerName;
        }

        String getTargetName() {
            return this.targetName;
        }

        byte[] getClassBytes() throws ClassNotFoundException, IOException {
            return MixinService.getService().getBytecodeProvider().getClassBytes(this.originalName, true);
        }

        @Override
        public String mapMethodName(String string, String string2, String string3) {
            ClassInfo.Method method;
            if (this.ownerName.equalsIgnoreCase(string) && (method = this.owner.getClassInfo().findMethod(string2, string3, 10)) != null) {
                return method.getName();
            }
            return super.mapMethodName(string, string2, string3);
        }

        @Override
        public String map(String string) {
            if (this.originalName.equals(string)) {
                return this.name;
            }
            if (this.ownerName.equals(string)) {
                return this.targetName;
            }
            return string;
        }

        public String toString() {
            return this.name;
        }
    }
}

