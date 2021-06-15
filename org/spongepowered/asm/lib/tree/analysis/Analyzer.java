/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.IincInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LookupSwitchInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TableSwitchInsnNode;
import org.spongepowered.asm.lib.tree.TryCatchBlockNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.Frame;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;
import org.spongepowered.asm.lib.tree.analysis.Subroutine;
import org.spongepowered.asm.lib.tree.analysis.Value;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Analyzer<V extends Value>
implements Opcodes {
    private final Interpreter<V> interpreter;
    private int n;
    private InsnList insns;
    private List<TryCatchBlockNode>[] handlers;
    private Frame<V>[] frames;
    private Subroutine[] subroutines;
    private boolean[] queued;
    private int[] queue;
    private int top;

    public Analyzer(Interpreter<V> interpreter) {
        this.interpreter = interpreter;
    }

    public Frame<V>[] analyze(String string, MethodNode methodNode) throws AnalyzerException {
        int n2;
        Type[] arrtype;
        Object object;
        if ((methodNode.access & 0x500) != 0) {
            this.frames = new Frame[0];
            return this.frames;
        }
        this.n = methodNode.instructions.size();
        this.insns = methodNode.instructions;
        this.handlers = new List[this.n];
        this.frames = new Frame[this.n];
        this.subroutines = new Subroutine[this.n];
        this.queued = new boolean[this.n];
        this.queue = new int[this.n];
        this.top = 0;
        for (int i2 = 0; i2 < methodNode.tryCatchBlocks.size(); ++i2) {
            object = methodNode.tryCatchBlocks.get(i2);
            int n3 = this.insns.indexOf(((TryCatchBlockNode)object).start);
            int n4 = this.insns.indexOf(((TryCatchBlockNode)object).end);
            for (int i3 = n3; i3 < n4; ++i3) {
                arrtype = this.handlers[i3];
                if (arrtype == null) {
                    arrtype = new ArrayList<TryCatchBlockNode>();
                    this.handlers[i3] = arrtype;
                }
                arrtype.add((TryCatchBlockNode)object);
            }
        }
        Subroutine subroutine = new Subroutine(null, methodNode.maxLocals, null);
        object = new ArrayList();
        HashMap<LabelNode, Subroutine> hashMap = new HashMap<LabelNode, Subroutine>();
        this.findSubroutine(0, subroutine, (List<AbstractInsnNode>)object);
        while (!object.isEmpty()) {
            JumpInsnNode jumpInsnNode = (JumpInsnNode)object.remove(0);
            Subroutine subroutine2 = (Subroutine)hashMap.get(jumpInsnNode.label);
            if (subroutine2 == null) {
                subroutine2 = new Subroutine(jumpInsnNode.label, methodNode.maxLocals, jumpInsnNode);
                hashMap.put(jumpInsnNode.label, subroutine2);
                this.findSubroutine(this.insns.indexOf(jumpInsnNode.label), subroutine2, (List<AbstractInsnNode>)object);
                continue;
            }
            subroutine2.callers.add(jumpInsnNode);
        }
        for (int i4 = 0; i4 < this.n; ++i4) {
            if (this.subroutines[i4] == null || this.subroutines[i4].start != null) continue;
            this.subroutines[i4] = null;
        }
        Frame<V> frame = this.newFrame(methodNode.maxLocals, methodNode.maxStack);
        Frame<V> frame2 = this.newFrame(methodNode.maxLocals, methodNode.maxStack);
        frame.setReturn(this.interpreter.newValue(Type.getReturnType(methodNode.desc)));
        arrtype = Type.getArgumentTypes(methodNode.desc);
        int n5 = 0;
        if ((methodNode.access & 8) == 0) {
            Type type = Type.getObjectType(string);
            frame.setLocal(n5++, this.interpreter.newValue(type));
        }
        for (n2 = 0; n2 < arrtype.length; ++n2) {
            frame.setLocal(n5++, this.interpreter.newValue(arrtype[n2]));
            if (arrtype[n2].getSize() != 2) continue;
            frame.setLocal(n5++, this.interpreter.newValue(null));
        }
        while (n5 < methodNode.maxLocals) {
            frame.setLocal(n5++, this.interpreter.newValue(null));
        }
        this.merge(0, frame, null);
        this.init(string, methodNode);
        while (this.top > 0) {
            n2 = this.queue[--this.top];
            Frame<V> frame3 = this.frames[n2];
            Subroutine subroutine3 = this.subroutines[n2];
            this.queued[n2] = false;
            AbstractInsnNode abstractInsnNode = null;
            try {
                Object object2;
                int n6;
                Object object3;
                abstractInsnNode = methodNode.instructions.get(n2);
                int n7 = abstractInsnNode.getOpcode();
                int n8 = abstractInsnNode.getType();
                if (n8 == 8 || n8 == 15 || n8 == 14) {
                    this.merge(n2 + 1, frame3, subroutine3);
                    this.newControlFlowEdge(n2, n2 + 1);
                } else {
                    int n9;
                    frame.init(frame3).execute(abstractInsnNode, this.interpreter);
                    Subroutine subroutine4 = subroutine3 = subroutine3 == null ? null : subroutine3.copy();
                    if (abstractInsnNode instanceof JumpInsnNode) {
                        object3 = (JumpInsnNode)abstractInsnNode;
                        if (n7 != 167 && n7 != 168) {
                            this.merge(n2 + 1, frame, subroutine3);
                            this.newControlFlowEdge(n2, n2 + 1);
                        }
                        n6 = this.insns.indexOf(((JumpInsnNode)object3).label);
                        if (n7 == 168) {
                            this.merge(n6, frame, new Subroutine(((JumpInsnNode)object3).label, methodNode.maxLocals, (JumpInsnNode)object3));
                        } else {
                            this.merge(n6, frame, subroutine3);
                        }
                        this.newControlFlowEdge(n2, n6);
                    } else if (abstractInsnNode instanceof LookupSwitchInsnNode) {
                        object3 = (LookupSwitchInsnNode)abstractInsnNode;
                        n6 = this.insns.indexOf(((LookupSwitchInsnNode)object3).dflt);
                        this.merge(n6, frame, subroutine3);
                        this.newControlFlowEdge(n2, n6);
                        for (n9 = 0; n9 < ((LookupSwitchInsnNode)object3).labels.size(); ++n9) {
                            object2 = ((LookupSwitchInsnNode)object3).labels.get(n9);
                            n6 = this.insns.indexOf((AbstractInsnNode)object2);
                            this.merge(n6, frame, subroutine3);
                            this.newControlFlowEdge(n2, n6);
                        }
                    } else if (abstractInsnNode instanceof TableSwitchInsnNode) {
                        object3 = (TableSwitchInsnNode)abstractInsnNode;
                        n6 = this.insns.indexOf(((TableSwitchInsnNode)object3).dflt);
                        this.merge(n6, frame, subroutine3);
                        this.newControlFlowEdge(n2, n6);
                        for (n9 = 0; n9 < ((TableSwitchInsnNode)object3).labels.size(); ++n9) {
                            object2 = ((TableSwitchInsnNode)object3).labels.get(n9);
                            n6 = this.insns.indexOf((AbstractInsnNode)object2);
                            this.merge(n6, frame, subroutine3);
                            this.newControlFlowEdge(n2, n6);
                        }
                    } else if (n7 == 169) {
                        if (subroutine3 == null) {
                            throw new AnalyzerException(abstractInsnNode, "RET instruction outside of a sub routine");
                        }
                        for (int i5 = 0; i5 < subroutine3.callers.size(); ++i5) {
                            JumpInsnNode jumpInsnNode = subroutine3.callers.get(i5);
                            n9 = this.insns.indexOf(jumpInsnNode);
                            if (this.frames[n9] == null) continue;
                            this.merge(n9 + 1, this.frames[n9], frame, this.subroutines[n9], subroutine3.access);
                            this.newControlFlowEdge(n2, n9 + 1);
                        }
                    } else if (n7 != 191 && (n7 < 172 || n7 > 177)) {
                        if (subroutine3 != null) {
                            if (abstractInsnNode instanceof VarInsnNode) {
                                int n10 = ((VarInsnNode)abstractInsnNode).var;
                                subroutine3.access[n10] = true;
                                if (n7 == 22 || n7 == 24 || n7 == 55 || n7 == 57) {
                                    subroutine3.access[n10 + 1] = true;
                                }
                            } else if (abstractInsnNode instanceof IincInsnNode) {
                                int n11 = ((IincInsnNode)abstractInsnNode).var;
                                subroutine3.access[n11] = true;
                            }
                        }
                        this.merge(n2 + 1, frame, subroutine3);
                        this.newControlFlowEdge(n2, n2 + 1);
                    }
                }
                if ((object3 = this.handlers[n2]) == null) continue;
                for (n6 = 0; n6 < object3.size(); ++n6) {
                    TryCatchBlockNode tryCatchBlockNode = (TryCatchBlockNode)object3.get(n6);
                    object2 = tryCatchBlockNode.type == null ? Type.getObjectType("java/lang/Throwable") : Type.getObjectType(tryCatchBlockNode.type);
                    int n12 = this.insns.indexOf(tryCatchBlockNode.handler);
                    if (!this.newControlFlowExceptionEdge(n2, tryCatchBlockNode)) continue;
                    frame2.init(frame3);
                    frame2.clearStack();
                    frame2.push(this.interpreter.newValue((Type)object2));
                    this.merge(n12, frame2, subroutine3);
                }
            }
            catch (AnalyzerException analyzerException) {
                throw new AnalyzerException(analyzerException.node, "Error at instruction " + n2 + ": " + analyzerException.getMessage(), analyzerException);
            }
            catch (Exception exception) {
                throw new AnalyzerException(abstractInsnNode, "Error at instruction " + n2 + ": " + exception.getMessage(), exception);
            }
        }
        return this.frames;
    }

    private void findSubroutine(int n2, Subroutine subroutine, List<AbstractInsnNode> list) throws AnalyzerException {
        while (true) {
            Object object;
            int n3;
            Object object2;
            if (n2 < 0 || n2 >= this.n) {
                throw new AnalyzerException(null, "Execution can fall off end of the code");
            }
            if (this.subroutines[n2] != null) {
                return;
            }
            this.subroutines[n2] = subroutine.copy();
            AbstractInsnNode abstractInsnNode = this.insns.get(n2);
            if (abstractInsnNode instanceof JumpInsnNode) {
                if (abstractInsnNode.getOpcode() == 168) {
                    list.add(abstractInsnNode);
                } else {
                    object2 = (JumpInsnNode)abstractInsnNode;
                    this.findSubroutine(this.insns.indexOf(((JumpInsnNode)object2).label), subroutine, list);
                }
            } else if (abstractInsnNode instanceof TableSwitchInsnNode) {
                object2 = (TableSwitchInsnNode)abstractInsnNode;
                this.findSubroutine(this.insns.indexOf(((TableSwitchInsnNode)object2).dflt), subroutine, list);
                for (n3 = ((TableSwitchInsnNode)object2).labels.size() - 1; n3 >= 0; --n3) {
                    object = ((TableSwitchInsnNode)object2).labels.get(n3);
                    this.findSubroutine(this.insns.indexOf((AbstractInsnNode)object), subroutine, list);
                }
            } else if (abstractInsnNode instanceof LookupSwitchInsnNode) {
                object2 = (LookupSwitchInsnNode)abstractInsnNode;
                this.findSubroutine(this.insns.indexOf(((LookupSwitchInsnNode)object2).dflt), subroutine, list);
                for (n3 = ((LookupSwitchInsnNode)object2).labels.size() - 1; n3 >= 0; --n3) {
                    object = ((LookupSwitchInsnNode)object2).labels.get(n3);
                    this.findSubroutine(this.insns.indexOf((AbstractInsnNode)object), subroutine, list);
                }
            }
            object2 = this.handlers[n2];
            if (object2 != null) {
                for (n3 = 0; n3 < object2.size(); ++n3) {
                    object = (TryCatchBlockNode)object2.get(n3);
                    this.findSubroutine(this.insns.indexOf(((TryCatchBlockNode)object).handler), subroutine, list);
                }
            }
            switch (abstractInsnNode.getOpcode()) {
                case 167: 
                case 169: 
                case 170: 
                case 171: 
                case 172: 
                case 173: 
                case 174: 
                case 175: 
                case 176: 
                case 177: 
                case 191: {
                    return;
                }
            }
            ++n2;
        }
    }

    public Frame<V>[] getFrames() {
        return this.frames;
    }

    public List<TryCatchBlockNode> getHandlers(int n2) {
        return this.handlers[n2];
    }

    protected void init(String string, MethodNode methodNode) throws AnalyzerException {
    }

    protected Frame<V> newFrame(int n2, int n3) {
        return new Frame(n2, n3);
    }

    protected Frame<V> newFrame(Frame<? extends V> frame) {
        return new Frame<V>(frame);
    }

    protected void newControlFlowEdge(int n2, int n3) {
    }

    protected boolean newControlFlowExceptionEdge(int n2, int n3) {
        return true;
    }

    protected boolean newControlFlowExceptionEdge(int n2, TryCatchBlockNode tryCatchBlockNode) {
        return this.newControlFlowExceptionEdge(n2, this.insns.indexOf(tryCatchBlockNode.handler));
    }

    private void merge(int n2, Frame<V> frame, Subroutine subroutine) throws AnalyzerException {
        boolean bl;
        Frame<V> frame2 = this.frames[n2];
        Subroutine subroutine2 = this.subroutines[n2];
        if (frame2 == null) {
            this.frames[n2] = this.newFrame(frame);
            bl = true;
        } else {
            bl = frame2.merge(frame, this.interpreter);
        }
        if (subroutine2 == null) {
            if (subroutine != null) {
                this.subroutines[n2] = subroutine.copy();
                bl = true;
            }
        } else if (subroutine != null) {
            bl |= subroutine2.merge(subroutine);
        }
        if (bl && !this.queued[n2]) {
            this.queued[n2] = true;
            this.queue[this.top++] = n2;
        }
    }

    private void merge(int n2, Frame<V> frame, Frame<V> frame2, Subroutine subroutine, boolean[] arrbl) throws AnalyzerException {
        boolean bl;
        Frame<V> frame3 = this.frames[n2];
        Subroutine subroutine2 = this.subroutines[n2];
        frame2.merge(frame, arrbl);
        if (frame3 == null) {
            this.frames[n2] = this.newFrame(frame2);
            bl = true;
        } else {
            bl = frame3.merge(frame2, this.interpreter);
        }
        if (subroutine2 != null && subroutine != null) {
            bl |= subroutine2.merge(subroutine);
        }
        if (bl && !this.queued[n2]) {
            this.queued[n2] = true;
            this.queue[this.top++] = n2;
        }
    }
}

