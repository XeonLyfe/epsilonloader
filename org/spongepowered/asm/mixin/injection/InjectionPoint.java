/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 */
package org.spongepowered.asm.mixin.injection;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.IInjectionPointContext;
import org.spongepowered.asm.mixin.injection.modify.AfterStoreLocal;
import org.spongepowered.asm.mixin.injection.modify.BeforeLoadLocal;
import org.spongepowered.asm.mixin.injection.points.AfterInvoke;
import org.spongepowered.asm.mixin.injection.points.BeforeConstant;
import org.spongepowered.asm.mixin.injection.points.BeforeFieldAccess;
import org.spongepowered.asm.mixin.injection.points.BeforeFinalReturn;
import org.spongepowered.asm.mixin.injection.points.BeforeInvoke;
import org.spongepowered.asm.mixin.injection.points.BeforeNew;
import org.spongepowered.asm.mixin.injection.points.BeforeReturn;
import org.spongepowered.asm.mixin.injection.points.BeforeStringInvoke;
import org.spongepowered.asm.mixin.injection.points.JumpInsnPoint;
import org.spongepowered.asm.mixin.injection.points.MethodHead;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public abstract class InjectionPoint {
    public static final int DEFAULT_ALLOWED_SHIFT_BY = 0;
    public static final int MAX_ALLOWED_SHIFT_BY = 5;
    private static Map<String, Class<? extends InjectionPoint>> types = new HashMap<String, Class<? extends InjectionPoint>>();
    private final String slice;
    private final Selector selector;
    private final String id;

    protected InjectionPoint() {
        this("", Selector.DEFAULT, null);
    }

    protected InjectionPoint(InjectionPointData injectionPointData) {
        this(injectionPointData.getSlice(), injectionPointData.getSelector(), injectionPointData.getId());
    }

    public InjectionPoint(String string, Selector selector, String string2) {
        this.slice = string;
        this.selector = selector;
        this.id = string2;
    }

    public String getSlice() {
        return this.slice;
    }

    public Selector getSelector() {
        return this.selector;
    }

    public String getId() {
        return this.id;
    }

    public boolean checkPriority(int n2, int n3) {
        return n2 < n3;
    }

    public abstract boolean find(String var1, InsnList var2, Collection<AbstractInsnNode> var3);

    public String toString() {
        return String.format("@At(\"%s\")", this.getAtCode());
    }

    protected static AbstractInsnNode nextNode(InsnList insnList, AbstractInsnNode abstractInsnNode) {
        int n2 = insnList.indexOf(abstractInsnNode) + 1;
        if (n2 > 0 && n2 < insnList.size()) {
            return insnList.get(n2);
        }
        return abstractInsnNode;
    }

    public static InjectionPoint and(InjectionPoint ... arrinjectionPoint) {
        return new Intersection(arrinjectionPoint);
    }

    public static InjectionPoint or(InjectionPoint ... arrinjectionPoint) {
        return new Union(arrinjectionPoint);
    }

    public static InjectionPoint after(InjectionPoint injectionPoint) {
        return new Shift(injectionPoint, 1);
    }

    public static InjectionPoint before(InjectionPoint injectionPoint) {
        return new Shift(injectionPoint, -1);
    }

    public static InjectionPoint shift(InjectionPoint injectionPoint, int n2) {
        return new Shift(injectionPoint, n2);
    }

    public static List<InjectionPoint> parse(IInjectionPointContext iInjectionPointContext, List<AnnotationNode> list) {
        return InjectionPoint.parse(iInjectionPointContext.getContext(), iInjectionPointContext.getMethod(), iInjectionPointContext.getAnnotation(), list);
    }

    public static List<InjectionPoint> parse(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, List<AnnotationNode> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (AnnotationNode annotationNode2 : list) {
            InjectionPoint injectionPoint = InjectionPoint.parse(iMixinContext, methodNode, annotationNode, annotationNode2);
            if (injectionPoint == null) continue;
            builder.add(injectionPoint);
        }
        return builder.build();
    }

    public static InjectionPoint parse(IInjectionPointContext iInjectionPointContext, At at) {
        return InjectionPoint.parse(iInjectionPointContext.getContext(), iInjectionPointContext.getMethod(), iInjectionPointContext.getAnnotation(), at.value(), at.shift(), at.by(), Arrays.asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode(), at.id());
    }

    public static InjectionPoint parse(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, At at) {
        return InjectionPoint.parse(iMixinContext, methodNode, annotationNode, at.value(), at.shift(), at.by(), Arrays.asList(at.args()), at.target(), at.slice(), at.ordinal(), at.opcode(), at.id());
    }

    public static InjectionPoint parse(IInjectionPointContext iInjectionPointContext, AnnotationNode annotationNode) {
        return InjectionPoint.parse(iInjectionPointContext.getContext(), iInjectionPointContext.getMethod(), iInjectionPointContext.getAnnotation(), annotationNode);
    }

    public static InjectionPoint parse(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, AnnotationNode annotationNode2) {
        String string = (String)Annotations.getValue(annotationNode2, "value");
        ImmutableList<String> immutableList = (ImmutableList<String>)Annotations.getValue(annotationNode2, "args");
        String string2 = Annotations.getValue(annotationNode2, "target", "");
        String string3 = Annotations.getValue(annotationNode2, "slice", "");
        At.Shift shift = Annotations.getValue(annotationNode2, "shift", At.Shift.class, At.Shift.NONE);
        int n2 = Annotations.getValue(annotationNode2, "by", 0);
        int n3 = Annotations.getValue(annotationNode2, "ordinal", -1);
        int n4 = Annotations.getValue(annotationNode2, "opcode", 0);
        String string4 = (String)Annotations.getValue(annotationNode2, "id");
        if (immutableList == null) {
            immutableList = ImmutableList.of();
        }
        return InjectionPoint.parse(iMixinContext, methodNode, annotationNode, string, shift, n2, immutableList, string2, string3, n3, n4, string4);
    }

    public static InjectionPoint parse(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, String string, At.Shift shift, int n2, List<String> list, String string2, String string3, int n3, int n4, String string4) {
        InjectionPointData injectionPointData = new InjectionPointData(iMixinContext, methodNode, annotationNode, string, list, string2, string3, n3, n4, string4);
        Class<? extends InjectionPoint> class_ = InjectionPoint.findClass(iMixinContext, injectionPointData);
        InjectionPoint injectionPoint = InjectionPoint.create(iMixinContext, injectionPointData, class_);
        return InjectionPoint.shift(iMixinContext, methodNode, annotationNode, injectionPoint, shift, n2);
    }

    private static Class<? extends InjectionPoint> findClass(IMixinContext iMixinContext, InjectionPointData injectionPointData) {
        String string = injectionPointData.getType();
        Class<InjectionPoint> class_ = types.get(string);
        if (class_ == null) {
            if (string.matches("^([A-Za-z_][A-Za-z0-9_]*\\.)+[A-Za-z_][A-Za-z0-9_]*$")) {
                try {
                    class_ = Class.forName(string);
                    types.put(string, class_);
                }
                catch (Exception exception) {
                    throw new InvalidInjectionException(iMixinContext, injectionPointData + " could not be loaded or is not a valid InjectionPoint", (Throwable)exception);
                }
            } else {
                throw new InvalidInjectionException(iMixinContext, injectionPointData + " is not a valid injection point specifier");
            }
        }
        return class_;
    }

    private static InjectionPoint create(IMixinContext iMixinContext, InjectionPointData injectionPointData, Class<? extends InjectionPoint> class_) {
        Constructor<? extends InjectionPoint> constructor = null;
        try {
            constructor = class_.getDeclaredConstructor(InjectionPointData.class);
            constructor.setAccessible(true);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            throw new InvalidInjectionException(iMixinContext, class_.getName() + " must contain a constructor which accepts an InjectionPointData", (Throwable)noSuchMethodException);
        }
        InjectionPoint injectionPoint = null;
        try {
            injectionPoint = constructor.newInstance(injectionPointData);
        }
        catch (Exception exception) {
            throw new InvalidInjectionException(iMixinContext, "Error whilst instancing injection point " + class_.getName() + " for " + injectionPointData.getAt(), (Throwable)exception);
        }
        return injectionPoint;
    }

    private static InjectionPoint shift(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, InjectionPoint injectionPoint, At.Shift shift, int n2) {
        if (injectionPoint != null) {
            if (shift == At.Shift.BEFORE) {
                return InjectionPoint.before(injectionPoint);
            }
            if (shift == At.Shift.AFTER) {
                return InjectionPoint.after(injectionPoint);
            }
            if (shift == At.Shift.BY) {
                InjectionPoint.validateByValue(iMixinContext, methodNode, annotationNode, injectionPoint, n2);
                return InjectionPoint.shift(injectionPoint, n2);
            }
        }
        return injectionPoint;
    }

    private static void validateByValue(IMixinContext iMixinContext, MethodNode methodNode, AnnotationNode annotationNode, InjectionPoint injectionPoint, int n2) {
        MixinEnvironment mixinEnvironment = iMixinContext.getMixin().getConfig().getEnvironment();
        ShiftByViolationBehaviour shiftByViolationBehaviour = mixinEnvironment.getOption(MixinEnvironment.Option.SHIFT_BY_VIOLATION_BEHAVIOUR, ShiftByViolationBehaviour.WARN);
        if (shiftByViolationBehaviour == ShiftByViolationBehaviour.IGNORE) {
            return;
        }
        String string = "the maximum allowed value: ";
        String string2 = "Increase the value of maxShiftBy to suppress this warning.";
        int n3 = 0;
        if (iMixinContext instanceof MixinTargetContext) {
            n3 = ((MixinTargetContext)iMixinContext).getMaxShiftByValue();
        }
        if (n2 <= n3) {
            return;
        }
        if (n2 > 5) {
            string = "MAX_ALLOWED_SHIFT_BY=";
            string2 = "You must use an alternate query or a custom injection point.";
            n3 = 5;
        }
        String string3 = String.format("@%s(%s) Shift.BY=%d on %s::%s exceeds %s%d. %s", Bytecode.getSimpleName(annotationNode), injectionPoint, n2, iMixinContext, methodNode.name, string, n3, string2);
        if (shiftByViolationBehaviour == ShiftByViolationBehaviour.WARN && n3 < 5) {
            LogManager.getLogger((String)"mixin").warn(string3);
            return;
        }
        throw new InvalidInjectionException(iMixinContext, string3);
    }

    protected String getAtCode() {
        AtCode atCode = this.getClass().getAnnotation(AtCode.class);
        return atCode == null ? this.getClass().getName() : atCode.value();
    }

    public static void register(Class<? extends InjectionPoint> class_) {
        AtCode atCode = class_.getAnnotation(AtCode.class);
        if (atCode == null) {
            throw new IllegalArgumentException("Injection point class " + class_ + " is not annotated with @AtCode");
        }
        Class<? extends InjectionPoint> class_2 = types.get(atCode.value());
        if (class_2 != null && !class_2.equals(class_)) {
            LogManager.getLogger((String)"mixin").debug("Overriding InjectionPoint {} with {} (previously {})", new Object[]{atCode.value(), class_.getName(), class_2.getName()});
        }
        types.put(atCode.value(), class_);
    }

    static {
        InjectionPoint.register(BeforeFieldAccess.class);
        InjectionPoint.register(BeforeInvoke.class);
        InjectionPoint.register(BeforeNew.class);
        InjectionPoint.register(BeforeReturn.class);
        InjectionPoint.register(BeforeStringInvoke.class);
        InjectionPoint.register(JumpInsnPoint.class);
        InjectionPoint.register(MethodHead.class);
        InjectionPoint.register(AfterInvoke.class);
        InjectionPoint.register(BeforeLoadLocal.class);
        InjectionPoint.register(AfterStoreLocal.class);
        InjectionPoint.register(BeforeFinalReturn.class);
        InjectionPoint.register(BeforeConstant.class);
    }

    static final class Shift
    extends InjectionPoint {
        private final InjectionPoint input;
        private final int shift;

        public Shift(InjectionPoint injectionPoint, int n2) {
            if (injectionPoint == null) {
                throw new IllegalArgumentException("Must supply an input injection point for SHIFT");
            }
            this.input = injectionPoint;
            this.shift = n2;
        }

        @Override
        public String toString() {
            return "InjectionPoint(" + this.getClass().getSimpleName() + ")[" + this.input + "]";
        }

        @Override
        public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
            List<Object> list = collection instanceof List ? (List<Object>)collection : new ArrayList<AbstractInsnNode>(collection);
            this.input.find(string, insnList, collection);
            for (int i2 = 0; i2 < list.size(); ++i2) {
                list.set(i2, insnList.get(insnList.indexOf((AbstractInsnNode)list.get(i2)) + this.shift));
            }
            if (collection != list) {
                collection.clear();
                collection.addAll((Collection<AbstractInsnNode>)list);
            }
            return collection.size() > 0;
        }
    }

    static final class Union
    extends CompositeInjectionPoint {
        public Union(InjectionPoint ... arrinjectionPoint) {
            super(arrinjectionPoint);
        }

        @Override
        public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
            LinkedHashSet<AbstractInsnNode> linkedHashSet = new LinkedHashSet<AbstractInsnNode>();
            for (int i2 = 0; i2 < this.components.length; ++i2) {
                this.components[i2].find(string, insnList, linkedHashSet);
            }
            collection.addAll(linkedHashSet);
            return linkedHashSet.size() > 0;
        }
    }

    static final class Intersection
    extends CompositeInjectionPoint {
        public Intersection(InjectionPoint ... arrinjectionPoint) {
            super(arrinjectionPoint);
        }

        @Override
        public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
            boolean bl = false;
            ArrayList[] arrarrayList = (ArrayList[])Array.newInstance(ArrayList.class, this.components.length);
            for (int i2 = 0; i2 < this.components.length; ++i2) {
                arrarrayList[i2] = new ArrayList();
                this.components[i2].find(string, insnList, arrarrayList[i2]);
            }
            ArrayList arrayList = arrarrayList[0];
            for (int i3 = 0; i3 < arrayList.size(); ++i3) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode)arrayList.get(i3);
                boolean bl2 = true;
                for (int i4 = 1; i4 < arrarrayList.length && arrarrayList[i4].contains(abstractInsnNode); ++i4) {
                }
                if (!bl2) continue;
                collection.add(abstractInsnNode);
                bl = true;
            }
            return bl;
        }
    }

    static abstract class CompositeInjectionPoint
    extends InjectionPoint {
        protected final InjectionPoint[] components;

        protected CompositeInjectionPoint(InjectionPoint ... arrinjectionPoint) {
            if (arrinjectionPoint == null || arrinjectionPoint.length < 2) {
                throw new IllegalArgumentException("Must supply two or more component injection points for composite point!");
            }
            this.components = arrinjectionPoint;
        }

        @Override
        public String toString() {
            return "CompositeInjectionPoint(" + this.getClass().getSimpleName() + ")[" + Joiner.on(',').join(this.components) + "]";
        }
    }

    static enum ShiftByViolationBehaviour {
        IGNORE,
        WARN,
        ERROR;

    }

    public static enum Selector {
        FIRST,
        LAST,
        ONE;

        public static final Selector DEFAULT;

        static {
            DEFAULT = FIRST;
        }
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.TYPE})
    public static @interface AtCode {
        public String value();
    }
}

