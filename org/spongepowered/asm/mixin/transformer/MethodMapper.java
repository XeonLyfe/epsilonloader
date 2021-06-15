/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.util.Counter;

public class MethodMapper {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static final List<String> classes = new ArrayList<String>();
    private static final Map<String, Counter> methods = new HashMap<String, Counter>();
    private final ClassInfo info;

    public MethodMapper(MixinEnvironment mixinEnvironment, ClassInfo classInfo) {
        this.info = classInfo;
    }

    public ClassInfo getClassInfo() {
        return this.info;
    }

    public void remapHandlerMethod(MixinInfo mixinInfo, MethodNode methodNode, ClassInfo.Method method) {
        if (!(methodNode instanceof MixinInfo.MixinMethodNode) || !((MixinInfo.MixinMethodNode)methodNode).isInjector()) {
            return;
        }
        if (method.isUnique()) {
            logger.warn("Redundant @Unique on injector method {} in {}. Injectors are implicitly unique", new Object[]{method, mixinInfo});
        }
        if (method.isRenamed()) {
            methodNode.name = method.getName();
            return;
        }
        String string = this.getHandlerName((MixinInfo.MixinMethodNode)methodNode);
        methodNode.name = method.renameTo(string);
    }

    public String getHandlerName(MixinInfo.MixinMethodNode mixinMethodNode) {
        String string = InjectionInfo.getInjectorPrefix(mixinMethodNode.getInjectorAnnotation());
        String string2 = MethodMapper.getClassUID(mixinMethodNode.getOwner().getClassRef());
        String string3 = MethodMapper.getMethodUID(mixinMethodNode.name, mixinMethodNode.desc, !mixinMethodNode.isSurrogate());
        return String.format("%s$%s$%s%s", string, mixinMethodNode.name, string2, string3);
    }

    private static String getClassUID(String string) {
        int n2 = classes.indexOf(string);
        if (n2 < 0) {
            n2 = classes.size();
            classes.add(string);
        }
        return MethodMapper.finagle(n2);
    }

    private static String getMethodUID(String string, String string2, boolean bl) {
        String string3 = String.format("%s%s", string, string2);
        Counter counter = methods.get(string3);
        if (counter == null) {
            counter = new Counter();
            methods.put(string3, counter);
        } else if (bl) {
            ++counter.value;
        }
        return String.format("%03x", counter.value);
    }

    private static String finagle(int n2) {
        String string = Integer.toHexString(n2);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2;
            c2 = (char)(c2 + ((c2 = string.charAt(i2)) < ':' ? 49 : 10));
            stringBuilder.append(c2);
        }
        return Strings.padStart(stringBuilder.toString(), 3, 'z');
    }
}

