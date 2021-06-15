/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.ModifyConstantInjector;
import org.spongepowered.asm.mixin.injection.points.BeforeConstant;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

public class ModifyConstantInjectionInfo
extends InjectionInfo {
    private static final String CONSTANT_ANNOTATION_CLASS = Constant.class.getName().replace('.', '/');

    public ModifyConstantInjectionInfo(MixinTargetContext mixinTargetContext, MethodNode methodNode, AnnotationNode annotationNode) {
        super(mixinTargetContext, methodNode, annotationNode, "constant");
    }

    @Override
    protected List<AnnotationNode> readInjectionPoints(String string) {
        List<AnnotationNode> list = super.readInjectionPoints(string);
        if (list.isEmpty()) {
            AnnotationNode annotationNode = new AnnotationNode(CONSTANT_ANNOTATION_CLASS);
            annotationNode.visit("log", Boolean.TRUE);
            list = ImmutableList.of(annotationNode);
        }
        return list;
    }

    @Override
    protected void parseInjectionPoints(List<AnnotationNode> list) {
        Type type = Type.getReturnType(this.method.desc);
        for (AnnotationNode annotationNode : list) {
            this.injectionPoints.add(new BeforeConstant(this.getContext(), annotationNode, type.getDescriptor()));
        }
    }

    @Override
    protected Injector parseInjector(AnnotationNode annotationNode) {
        return new ModifyConstantInjector(this);
    }

    @Override
    protected String getDescription() {
        return "Constant modifier method";
    }

    @Override
    public String getSliceId(String string) {
        return Strings.nullToEmpty(string);
    }
}

