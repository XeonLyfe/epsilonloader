/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.mixin.injection.modify.InvalidImplicitDiscriminatorException;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class LocalVariableDiscriminator {
    private final boolean argsOnly;
    private final int ordinal;
    private final int index;
    private final Set<String> names;
    private final boolean print;

    public LocalVariableDiscriminator(boolean bl, int n2, int n3, Set<String> set, boolean bl2) {
        this.argsOnly = bl;
        this.ordinal = n2;
        this.index = n3;
        this.names = Collections.unmodifiableSet(set);
        this.print = bl2;
    }

    public boolean isArgsOnly() {
        return this.argsOnly;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public int getIndex() {
        return this.index;
    }

    public Set<String> getNames() {
        return this.names;
    }

    public boolean hasNames() {
        return !this.names.isEmpty();
    }

    public boolean printLVT() {
        return this.print;
    }

    protected boolean isImplicit(Context context) {
        return this.ordinal < 0 && this.index < context.baseArgIndex && this.names.isEmpty();
    }

    public int findLocal(Type type, boolean bl, Target target, AbstractInsnNode abstractInsnNode) {
        try {
            return this.findLocal(new Context(type, bl, target, abstractInsnNode));
        }
        catch (InvalidImplicitDiscriminatorException invalidImplicitDiscriminatorException) {
            return -2;
        }
    }

    public int findLocal(Context context) {
        if (this.isImplicit(context)) {
            return this.findImplicitLocal(context);
        }
        return this.findExplicitLocal(context);
    }

    private int findImplicitLocal(Context context) {
        int n2 = 0;
        int n3 = 0;
        for (int i2 = context.baseArgIndex; i2 < context.locals.length; ++i2) {
            Context.Local local = context.locals[i2];
            if (local == null || !local.type.equals(context.returnType)) continue;
            ++n3;
            n2 = i2;
        }
        if (n3 == 1) {
            return n2;
        }
        throw new InvalidImplicitDiscriminatorException("Found " + n3 + " candidate variables but exactly 1 is required.");
    }

    private int findExplicitLocal(Context context) {
        for (int i2 = context.baseArgIndex; i2 < context.locals.length; ++i2) {
            Context.Local local = context.locals[i2];
            if (local == null || !local.type.equals(context.returnType) || !(this.ordinal > -1 ? this.ordinal == local.ord : (this.index >= context.baseArgIndex ? this.index == i2 : this.names.contains(local.name)))) continue;
            return i2;
        }
        return -1;
    }

    public static LocalVariableDiscriminator parse(AnnotationNode annotationNode) {
        boolean bl = Annotations.getValue(annotationNode, "argsOnly", Boolean.FALSE);
        int n2 = Annotations.getValue(annotationNode, "ordinal", -1);
        int n3 = Annotations.getValue(annotationNode, "index", -1);
        boolean bl2 = Annotations.getValue(annotationNode, "print", Boolean.FALSE);
        HashSet<String> hashSet = new HashSet<String>();
        List list = Annotations.getValue(annotationNode, "name", (List)null);
        if (list != null) {
            hashSet.addAll(list);
        }
        return new LocalVariableDiscriminator(bl, n2, n3, hashSet, bl2);
    }

    public static class Context
    implements PrettyPrinter.IPrettyPrintable {
        final Target target;
        final Type returnType;
        final AbstractInsnNode node;
        final int baseArgIndex;
        final Local[] locals;
        private final boolean isStatic;

        public Context(Type type, boolean bl, Target target, AbstractInsnNode abstractInsnNode) {
            this.isStatic = Bytecode.methodIsStatic(target.method);
            this.returnType = type;
            this.target = target;
            this.node = abstractInsnNode;
            this.baseArgIndex = this.isStatic ? 0 : 1;
            this.locals = this.initLocals(target, bl, abstractInsnNode);
            this.initOrdinals();
        }

        private Local[] initLocals(Target target, boolean bl, AbstractInsnNode abstractInsnNode) {
            Object[] arrobject;
            if (!bl && (arrobject = Locals.getLocalsAt(target.classNode, target.method, abstractInsnNode)) != null) {
                Local[] arrlocal = new Local[arrobject.length];
                for (int i2 = 0; i2 < arrobject.length; ++i2) {
                    if (arrobject[i2] == null) continue;
                    arrlocal[i2] = new Local(((LocalVariableNode)arrobject[i2]).name, Type.getType(((LocalVariableNode)arrobject[i2]).desc));
                }
                return arrlocal;
            }
            arrobject = new Local[this.baseArgIndex + target.arguments.length];
            if (!this.isStatic) {
                arrobject[0] = new Local("this", Type.getType(target.classNode.name));
            }
            for (int i3 = this.baseArgIndex; i3 < arrobject.length; ++i3) {
                Type type = target.arguments[i3 - this.baseArgIndex];
                arrobject[i3] = new Local("arg" + i3, type);
            }
            return arrobject;
        }

        private void initOrdinals() {
            HashMap<Type, Integer> hashMap = new HashMap<Type, Integer>();
            for (int i2 = 0; i2 < this.locals.length; ++i2) {
                Integer n2 = 0;
                if (this.locals[i2] == null) continue;
                n2 = (Integer)hashMap.get(this.locals[i2].type);
                n2 = n2 == null ? 0 : n2 + 1;
                hashMap.put(this.locals[i2].type, n2);
                this.locals[i2].ord = n2;
            }
        }

        @Override
        public void print(PrettyPrinter prettyPrinter) {
            prettyPrinter.add("%5s  %7s  %30s  %-50s  %s", "INDEX", "ORDINAL", "TYPE", "NAME", "CANDIDATE");
            for (int i2 = this.baseArgIndex; i2 < this.locals.length; ++i2) {
                Object object;
                Local local = this.locals[i2];
                if (local != null) {
                    object = local.type;
                    String string = local.name;
                    int n2 = local.ord;
                    String string2 = this.returnType.equals(object) ? "YES" : "-";
                    prettyPrinter.add("[%3d]    [%3d]  %30s  %-50s  %s", i2, n2, SignaturePrinter.getTypeName((Type)object, false), string, string2);
                    continue;
                }
                if (i2 <= 0) continue;
                object = this.locals[i2 - 1];
                boolean bl = object != null && ((Local)object).type != null && ((Local)object).type.getSize() > 1;
                prettyPrinter.add("[%3d]           %30s", i2, bl ? "<top>" : "-");
            }
        }

        public class Local {
            int ord = 0;
            String name;
            Type type;

            public Local(String string, Type type) {
                this.name = string;
                this.type = type;
            }

            public String toString() {
                return String.format("Local[ordinal=%d, name=%s, type=%s]", this.ord, this.name, this.type);
            }
        }
    }
}

