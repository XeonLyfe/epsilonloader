/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.ext;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;

public final class Extensions {
    private final MixinTransformer transformer;
    private final List<IExtension> extensions = new ArrayList<IExtension>();
    private final Map<Class<? extends IExtension>, IExtension> extensionMap = new HashMap<Class<? extends IExtension>, IExtension>();
    private final List<IClassGenerator> generators = new ArrayList<IClassGenerator>();
    private final List<IClassGenerator> generatorsView = Collections.unmodifiableList(this.generators);
    private final Map<Class<? extends IClassGenerator>, IClassGenerator> generatorMap = new HashMap<Class<? extends IClassGenerator>, IClassGenerator>();
    private List<IExtension> activeExtensions = Collections.emptyList();

    public Extensions(MixinTransformer mixinTransformer) {
        this.transformer = mixinTransformer;
    }

    public MixinTransformer getTransformer() {
        return this.transformer;
    }

    public void add(IExtension iExtension) {
        this.extensions.add(iExtension);
        this.extensionMap.put(iExtension.getClass(), iExtension);
    }

    public List<IExtension> getExtensions() {
        return Collections.unmodifiableList(this.extensions);
    }

    public List<IExtension> getActiveExtensions() {
        return this.activeExtensions;
    }

    public <T extends IExtension> T getExtension(Class<? extends IExtension> class_) {
        return (T)Extensions.lookup(class_, this.extensionMap, this.extensions);
    }

    public void select(MixinEnvironment mixinEnvironment) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (IExtension iExtension : this.extensions) {
            if (!iExtension.checkActive(mixinEnvironment)) continue;
            builder.add(iExtension);
        }
        this.activeExtensions = builder.build();
    }

    public void preApply(ITargetClassContext iTargetClassContext) {
        for (IExtension iExtension : this.activeExtensions) {
            iExtension.preApply(iTargetClassContext);
        }
    }

    public void postApply(ITargetClassContext iTargetClassContext) {
        for (IExtension iExtension : this.activeExtensions) {
            iExtension.postApply(iTargetClassContext);
        }
    }

    public void export(MixinEnvironment mixinEnvironment, String string, boolean bl, byte[] arrby) {
        for (IExtension iExtension : this.activeExtensions) {
            iExtension.export(mixinEnvironment, string, bl, arrby);
        }
    }

    public void add(IClassGenerator iClassGenerator) {
        this.generators.add(iClassGenerator);
        this.generatorMap.put(iClassGenerator.getClass(), iClassGenerator);
    }

    public List<IClassGenerator> getGenerators() {
        return this.generatorsView;
    }

    public <T extends IClassGenerator> T getGenerator(Class<? extends IClassGenerator> class_) {
        return (T)Extensions.lookup(class_, this.generatorMap, this.generators);
    }

    private static <T> T lookup(Class<? extends T> class_, Map<Class<? extends T>, T> map, List<T> list) {
        T t2 = map.get(class_);
        if (t2 == null) {
            for (T t3 : list) {
                if (!class_.isAssignableFrom(t3.getClass())) continue;
                t2 = t3;
                break;
            }
            if (t2 == null) {
                throw new IllegalArgumentException("Extension for <" + class_.getName() + "> could not be found");
            }
            map.put(class_, t2);
        }
        return t2;
    }
}

