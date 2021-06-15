/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.throwables;

import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;

public class SyntheticBridgeException
extends MixinException {
    private static final long serialVersionUID = 1L;
    private final Problem problem;
    private final String name;
    private final String desc;
    private final int index;
    private final AbstractInsnNode a;
    private final AbstractInsnNode b;

    public SyntheticBridgeException(Problem problem, String string, String string2, int n2, AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        super(problem.getMessage(string, string2, n2, abstractInsnNode, abstractInsnNode2));
        this.problem = problem;
        this.name = string;
        this.desc = string2;
        this.index = n2;
        this.a = abstractInsnNode;
        this.b = abstractInsnNode2;
    }

    public void printAnalysis(IMixinContext iMixinContext, MethodNode methodNode, MethodNode methodNode2) {
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        prettyPrinter.addWrapped(100, this.getMessage(), new Object[0]).hr();
        prettyPrinter.add().kv("Method", this.name + this.desc).kv("Problem Type", (Object)this.problem).add().hr();
        String string = (String)Annotations.getValue(Annotations.getVisible(methodNode, MixinMerged.class), "mixin");
        String string2 = string != null ? string : iMixinContext.getTargetClassRef().replace('/', '.');
        this.printMethod(prettyPrinter.add("Existing method").add().kv("Owner", string2).add(), methodNode).hr();
        this.printMethod(prettyPrinter.add("Incoming method").add().kv("Owner", iMixinContext.getClassRef().replace('/', '.')).add(), methodNode2).hr();
        this.printProblem(prettyPrinter, iMixinContext, methodNode, methodNode2).print(System.err);
    }

    private PrettyPrinter printMethod(PrettyPrinter prettyPrinter, MethodNode methodNode) {
        int n2 = 0;
        ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
        while (listIterator.hasNext()) {
            prettyPrinter.kv(n2 == this.index ? ">>>>" : "", Bytecode.describeNode((AbstractInsnNode)listIterator.next()));
            ++n2;
        }
        return prettyPrinter.add();
    }

    private PrettyPrinter printProblem(PrettyPrinter prettyPrinter, IMixinContext iMixinContext, MethodNode methodNode, MethodNode methodNode2) {
        Type type = Type.getObjectType(iMixinContext.getTargetClassRef());
        prettyPrinter.add("Analysis").add();
        switch (this.problem) {
            case BAD_INSN: {
                prettyPrinter.add("The bridge methods are not compatible because they contain incompatible opcodes");
                prettyPrinter.add("at index " + this.index + ":").add();
                prettyPrinter.kv("Existing opcode: %s", Bytecode.getOpcodeName(this.a));
                prettyPrinter.kv("Incoming opcode: %s", Bytecode.getOpcodeName(this.b)).add();
                prettyPrinter.add("This implies that the bridge methods are from different interfaces. This problem");
                prettyPrinter.add("may not be resolvable without changing the base interfaces.").add();
                break;
            }
            case BAD_LOAD: {
                prettyPrinter.add("The bridge methods are not compatible because they contain different variables at");
                prettyPrinter.add("opcode index " + this.index + ".").add();
                ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
                ListIterator<AbstractInsnNode> listIterator2 = methodNode2.instructions.iterator();
                Type[] arrtype = Type.getArgumentTypes(methodNode.desc);
                Type[] arrtype2 = Type.getArgumentTypes(methodNode2.desc);
                int n2 = 0;
                while (listIterator.hasNext() && listIterator2.hasNext()) {
                    AbstractInsnNode abstractInsnNode = listIterator.next();
                    AbstractInsnNode abstractInsnNode2 = listIterator2.next();
                    if (abstractInsnNode instanceof VarInsnNode && abstractInsnNode2 instanceof VarInsnNode) {
                        VarInsnNode varInsnNode = (VarInsnNode)abstractInsnNode;
                        VarInsnNode varInsnNode2 = (VarInsnNode)abstractInsnNode2;
                        Type type2 = varInsnNode.var > 0 ? arrtype[varInsnNode.var - 1] : type;
                        Type type3 = varInsnNode2.var > 0 ? arrtype2[varInsnNode2.var - 1] : type;
                        prettyPrinter.kv("Target " + n2, "%8s %-2d %s", Bytecode.getOpcodeName(varInsnNode), varInsnNode.var, type2);
                        prettyPrinter.kv("Incoming " + n2, "%8s %-2d %s", Bytecode.getOpcodeName(varInsnNode2), varInsnNode2.var, type3);
                        if (type2.equals(type3)) {
                            prettyPrinter.kv("", "Types match: %s", type2);
                        } else if (type2.getSort() != type3.getSort()) {
                            prettyPrinter.kv("", "Types are incompatible");
                        } else if (type2.getSort() == 10) {
                            ClassInfo classInfo = ClassInfo.getCommonSuperClassOrInterface(type2, type3);
                            prettyPrinter.kv("", "Common supertype: %s", classInfo);
                        }
                        prettyPrinter.add();
                    }
                    ++n2;
                }
                prettyPrinter.add("Since this probably means that the methods come from different interfaces, you");
                prettyPrinter.add("may have a \"multiple inheritance\" problem, it may not be possible to implement");
                prettyPrinter.add("both root interfaces");
                break;
            }
            case BAD_CAST: {
                prettyPrinter.add("Incompatible CHECKCAST encountered at opcode " + this.index + ", this could indicate that the bridge");
                prettyPrinter.add("is casting down for contravariant generic types. It may be possible to coalesce the");
                prettyPrinter.add("bridges by adjusting the types in the target method.").add();
                Type type4 = Type.getObjectType(((TypeInsnNode)this.a).desc);
                Type type5 = Type.getObjectType(((TypeInsnNode)this.b).desc);
                prettyPrinter.kv("Target type", type4);
                prettyPrinter.kv("Incoming type", type5);
                prettyPrinter.kv("Common supertype", ClassInfo.getCommonSuperClassOrInterface(type4, type5)).add();
                break;
            }
            case BAD_INVOKE_NAME: {
                prettyPrinter.add("Incompatible invocation targets in synthetic bridge. This is extremely unusual");
                prettyPrinter.add("and implies that a remapping transformer has incorrectly remapped a method. This");
                prettyPrinter.add("is an unrecoverable error.");
                break;
            }
            case BAD_INVOKE_DESC: {
                MethodInsnNode methodInsnNode = (MethodInsnNode)this.a;
                MethodInsnNode methodInsnNode2 = (MethodInsnNode)this.b;
                Type[] arrtype = Type.getArgumentTypes(methodInsnNode.desc);
                Type[] arrtype3 = Type.getArgumentTypes(methodInsnNode2.desc);
                if (arrtype.length != arrtype3.length) {
                    int n3 = Type.getArgumentTypes(methodNode.desc).length;
                    String string = arrtype.length == n3 ? "The TARGET" : (arrtype3.length == n3 ? " The INCOMING" : "NEITHER");
                    prettyPrinter.add("Mismatched invocation descriptors in synthetic bridge implies that a remapping");
                    prettyPrinter.add("transformer has incorrectly coalesced a bridge method with a conflicting name.");
                    prettyPrinter.add("Overlapping bridge methods should always have the same number of arguments, yet");
                    prettyPrinter.add("the target method has %d arguments, the incoming method has %d. This is an", arrtype.length, arrtype3.length);
                    prettyPrinter.add("unrecoverable error. %s method has the expected arg count of %d", string, n3);
                    break;
                }
                Type type6 = Type.getReturnType(methodInsnNode.desc);
                Type type7 = Type.getReturnType(methodInsnNode2.desc);
                prettyPrinter.add("Incompatible invocation descriptors in synthetic bridge implies that generified");
                prettyPrinter.add("types are incompatible over one or more generic superclasses or interfaces. It may");
                prettyPrinter.add("be possible to adjust the generic types on implemented members to rectify this");
                prettyPrinter.add("problem by coalescing the appropriate generic types.").add();
                this.printTypeComparison(prettyPrinter, "return type", type6, type7);
                for (int i2 = 0; i2 < arrtype.length; ++i2) {
                    this.printTypeComparison(prettyPrinter, "arg " + i2, arrtype[i2], arrtype3[i2]);
                }
                break;
            }
            case BAD_LENGTH: {
                prettyPrinter.add("Mismatched bridge method length implies the bridge methods are incompatible");
                prettyPrinter.add("and may originate from different superinterfaces. This is an unrecoverable");
                prettyPrinter.add("error.").add();
                break;
            }
        }
        return prettyPrinter;
    }

    private PrettyPrinter printTypeComparison(PrettyPrinter prettyPrinter, String string, Type type, Type type2) {
        prettyPrinter.kv("Target " + string, "%s", type);
        prettyPrinter.kv("Incoming " + string, "%s", type2);
        if (type.equals(type2)) {
            prettyPrinter.kv("Analysis", "Types match: %s", type);
        } else if (type.getSort() != type2.getSort()) {
            prettyPrinter.kv("Analysis", "Types are incompatible");
        } else if (type.getSort() == 10) {
            ClassInfo classInfo = ClassInfo.getCommonSuperClassOrInterface(type, type2);
            prettyPrinter.kv("Analysis", "Common supertype: L%s;", classInfo);
        }
        return prettyPrinter.add();
    }

    public static enum Problem {
        BAD_INSN("Conflicting opcodes %4$s and %5$s at offset %3$d in synthetic bridge method %1$s%2$s"),
        BAD_LOAD("Conflicting variable access at offset %3$d in synthetic bridge method %1$s%2$s"),
        BAD_CAST("Conflicting type cast at offset %3$d in synthetic bridge method %1$s%2$s"),
        BAD_INVOKE_NAME("Conflicting synthetic bridge target method name in synthetic bridge method %1$s%2$s Existing:%6$s Incoming:%7$s"),
        BAD_INVOKE_DESC("Conflicting synthetic bridge target method descriptor in synthetic bridge method %1$s%2$s Existing:%8$s Incoming:%9$s"),
        BAD_LENGTH("Mismatched bridge method length for synthetic bridge method %1$s%2$s unexpected extra opcode at offset %3$d");

        private final String message;

        private Problem(String string2) {
            this.message = string2;
        }

        String getMessage(String string, String string2, int n2, AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
            return String.format(this.message, string, string2, n2, Bytecode.getOpcodeName(abstractInsnNode), Bytecode.getOpcodeName(abstractInsnNode), Problem.getInsnName(abstractInsnNode), Problem.getInsnName(abstractInsnNode2), Problem.getInsnDesc(abstractInsnNode), Problem.getInsnDesc(abstractInsnNode2));
        }

        private static String getInsnName(AbstractInsnNode abstractInsnNode) {
            return abstractInsnNode instanceof MethodInsnNode ? ((MethodInsnNode)abstractInsnNode).name : "";
        }

        private static String getInsnDesc(AbstractInsnNode abstractInsnNode) {
            return abstractInsnNode instanceof MethodInsnNode ? ((MethodInsnNode)abstractInsnNode).desc : "";
        }
    }
}

