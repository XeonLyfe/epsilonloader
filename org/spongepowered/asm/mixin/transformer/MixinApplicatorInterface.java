/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.Map;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinApplicatorStandard;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidInterfaceMixinException;

class MixinApplicatorInterface
extends MixinApplicatorStandard {
    MixinApplicatorInterface(TargetClassContext targetClassContext) {
        super(targetClassContext);
    }

    @Override
    protected void applyInterfaces(MixinTargetContext mixinTargetContext) {
        for (String string : mixinTargetContext.getInterfaces()) {
            if (this.targetClass.name.equals(string) || this.targetClass.interfaces.contains(string)) continue;
            this.targetClass.interfaces.add(string);
            mixinTargetContext.getTargetClassInfo().addInterface(string);
        }
    }

    @Override
    protected void applyFields(MixinTargetContext mixinTargetContext) {
        for (Map.Entry<FieldNode, ClassInfo.Field> entry : mixinTargetContext.getShadowFields()) {
            FieldNode fieldNode = entry.getKey();
            this.logger.error("Ignoring redundant @Shadow field {}:{} in {}", new Object[]{fieldNode.name, fieldNode.desc, mixinTargetContext});
        }
        this.mergeNewFields(mixinTargetContext);
    }

    @Override
    protected void applyInitialisers(MixinTargetContext mixinTargetContext) {
    }

    @Override
    protected void prepareInjections(MixinTargetContext mixinTargetContext) {
        for (MethodNode methodNode : this.targetClass.methods) {
            try {
                InjectionInfo injectionInfo = InjectionInfo.parse(mixinTargetContext, methodNode);
                if (injectionInfo == null) continue;
                throw new InvalidInterfaceMixinException((IMixinContext)mixinTargetContext, injectionInfo + " is not supported on interface mixin method " + methodNode.name);
            }
            catch (InvalidInjectionException invalidInjectionException) {
                String string = invalidInjectionException.getInjectionInfo() != null ? invalidInjectionException.getInjectionInfo().toString() : "Injection";
                throw new InvalidInterfaceMixinException((IMixinContext)mixinTargetContext, string + " is not supported in interface mixin");
            }
        }
    }

    @Override
    protected void applyInjections(MixinTargetContext mixinTargetContext) {
    }
}

