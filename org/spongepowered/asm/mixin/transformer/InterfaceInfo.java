/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.HashSet;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.meta.MixinRenamed;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;

public final class InterfaceInfo {
    private final MixinInfo mixin;
    private final String prefix;
    private final Type iface;
    private final boolean unique;
    private Set<String> methods;

    private InterfaceInfo(MixinInfo mixinInfo, String string, Type type, boolean bl) {
        if (string == null || string.length() < 2 || !string.endsWith("$")) {
            throw new InvalidMixinException((IMixinInfo)mixinInfo, String.format("Prefix %s for iface %s is not valid", string, type.toString()));
        }
        this.mixin = mixinInfo;
        this.prefix = string;
        this.iface = type;
        this.unique = bl;
    }

    private void initMethods() {
        this.methods = new HashSet<String>();
        this.readInterface(this.iface.getInternalName());
    }

    private void readInterface(String string) {
        ClassInfo classInfo = ClassInfo.forName(string);
        for (ClassInfo.Method object : classInfo.getMethods()) {
            this.methods.add(object.toString());
        }
        for (String string2 : classInfo.getInterfaces()) {
            this.readInterface(string2);
        }
    }

    public String getPrefix() {
        return this.prefix;
    }

    public Type getIface() {
        return this.iface;
    }

    public String getName() {
        return this.iface.getClassName();
    }

    public String getInternalName() {
        return this.iface.getInternalName();
    }

    public boolean isUnique() {
        return this.unique;
    }

    public boolean renameMethod(MethodNode methodNode) {
        if (this.methods == null) {
            this.initMethods();
        }
        if (!methodNode.name.startsWith(this.prefix)) {
            if (this.methods.contains(methodNode.name + methodNode.desc)) {
                this.decorateUniqueMethod(methodNode);
            }
            return false;
        }
        String string = methodNode.name.substring(this.prefix.length());
        String string2 = string + methodNode.desc;
        if (!this.methods.contains(string2)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s does not exist in target interface %s", string, this.getName()));
        }
        if ((methodNode.access & 1) == 0) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s cannot implement %s because it is not visible", string, this.getName()));
        }
        Annotations.setVisible(methodNode, MixinRenamed.class, "originalName", methodNode.name, "isInterfaceMember", true);
        this.decorateUniqueMethod(methodNode);
        methodNode.name = string;
        return true;
    }

    private void decorateUniqueMethod(MethodNode methodNode) {
        if (!this.unique) {
            return;
        }
        if (Annotations.getVisible(methodNode, Unique.class) == null) {
            Annotations.setVisible(methodNode, Unique.class, new Object[0]);
            this.mixin.getClassInfo().findMethod(methodNode).setUnique(true);
        }
    }

    static InterfaceInfo fromAnnotation(MixinInfo mixinInfo, AnnotationNode annotationNode) {
        String string = (String)Annotations.getValue(annotationNode, "prefix");
        Type type = (Type)Annotations.getValue(annotationNode, "iface");
        Boolean bl = (Boolean)Annotations.getValue(annotationNode, "unique");
        if (string == null || type == null) {
            throw new InvalidMixinException((IMixinInfo)mixinInfo, String.format("@Interface annotation on %s is missing a required parameter", mixinInfo));
        }
        return new InterfaceInfo(mixinInfo, string, type, bl != null && bl != false);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        InterfaceInfo interfaceInfo = (InterfaceInfo)object;
        return this.mixin.equals(interfaceInfo.mixin) && this.prefix.equals(interfaceInfo.prefix) && this.iface.equals(interfaceInfo.iface);
    }

    public int hashCode() {
        int n2 = this.mixin.hashCode();
        n2 = 31 * n2 + this.prefix.hashCode();
        n2 = 31 * n2 + this.iface.hashCode();
        return n2;
    }
}

