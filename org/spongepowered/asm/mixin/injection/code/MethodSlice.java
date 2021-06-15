/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.injection.code;

import com.google.common.base.Strings;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.IInjectionPointContext;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.injection.code.ReadOnlyInsnList;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidSliceException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public final class MethodSlice {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final ISliceContext owner;
    private final String id;
    private final InjectionPoint from;
    private final InjectionPoint to;
    private final String name;

    private MethodSlice(ISliceContext iSliceContext, String string, InjectionPoint injectionPoint, InjectionPoint injectionPoint2) {
        if (injectionPoint == null && injectionPoint2 == null) {
            throw new InvalidSliceException(iSliceContext, String.format("%s is redundant. No 'from' or 'to' value specified", this));
        }
        this.owner = iSliceContext;
        this.id = Strings.nullToEmpty(string);
        this.from = injectionPoint;
        this.to = injectionPoint2;
        this.name = MethodSlice.getSliceName(string);
    }

    public String getId() {
        return this.id;
    }

    public ReadOnlyInsnList getSlice(MethodNode methodNode) {
        int n2;
        int n3 = methodNode.instructions.size() - 1;
        int n4 = this.find(methodNode, this.from, 0, 0, this.name + "(from)");
        if (n4 > (n2 = this.find(methodNode, this.to, n3, n4, this.name + "(to)"))) {
            throw new InvalidSliceException(this.owner, String.format("%s is negative size. Range(%d -> %d)", this.describe(), n4, n2));
        }
        if (n4 < 0 || n2 < 0 || n4 > n3 || n2 > n3) {
            throw new InjectionError("Unexpected critical error in " + this + ": out of bounds start=" + n4 + " end=" + n2 + " lim=" + n3);
        }
        if (n4 == 0 && n2 == n3) {
            return new ReadOnlyInsnList(methodNode.instructions);
        }
        return new InsnListSlice(methodNode.instructions, n4, n2);
    }

    private int find(MethodNode methodNode, InjectionPoint injectionPoint, int n2, int n3, String string) {
        if (injectionPoint == null) {
            return n2;
        }
        LinkedList<AbstractInsnNode> linkedList = new LinkedList<AbstractInsnNode>();
        ReadOnlyInsnList readOnlyInsnList = new ReadOnlyInsnList(methodNode.instructions);
        boolean bl = injectionPoint.find(methodNode.desc, readOnlyInsnList, linkedList);
        InjectionPoint.Selector selector = injectionPoint.getSelector();
        if (linkedList.size() != 1 && selector == InjectionPoint.Selector.ONE) {
            throw new InvalidSliceException(this.owner, String.format("%s requires 1 result but found %d", this.describe(string), linkedList.size()));
        }
        if (!bl) {
            if (this.owner.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                logger.warn("{} did not match any instructions", new Object[]{this.describe(string)});
            }
            return n3;
        }
        return methodNode.instructions.indexOf(selector == InjectionPoint.Selector.FIRST ? (AbstractInsnNode)linkedList.getFirst() : (AbstractInsnNode)linkedList.getLast());
    }

    public String toString() {
        return this.describe();
    }

    private String describe() {
        return this.describe(this.name);
    }

    private String describe(String string) {
        return MethodSlice.describeSlice(string, this.owner);
    }

    private static String describeSlice(String string, ISliceContext iSliceContext) {
        String string2 = Bytecode.getSimpleName(iSliceContext.getAnnotation());
        MethodNode methodNode = iSliceContext.getMethod();
        return String.format("%s->%s(%s)::%s%s", iSliceContext.getContext(), string2, string, methodNode.name, methodNode.desc);
    }

    private static String getSliceName(String string) {
        return String.format("@Slice[%s]", Strings.nullToEmpty(string));
    }

    public static MethodSlice parse(ISliceContext iSliceContext, Slice slice) {
        String string = slice.id();
        At at = slice.from();
        At at2 = slice.to();
        InjectionPoint injectionPoint = at != null ? InjectionPoint.parse((IInjectionPointContext)iSliceContext, at) : null;
        InjectionPoint injectionPoint2 = at2 != null ? InjectionPoint.parse((IInjectionPointContext)iSliceContext, at2) : null;
        return new MethodSlice(iSliceContext, string, injectionPoint, injectionPoint2);
    }

    public static MethodSlice parse(ISliceContext iSliceContext, AnnotationNode annotationNode) {
        String string = (String)Annotations.getValue(annotationNode, "id");
        AnnotationNode annotationNode2 = (AnnotationNode)Annotations.getValue(annotationNode, "from");
        AnnotationNode annotationNode3 = (AnnotationNode)Annotations.getValue(annotationNode, "to");
        InjectionPoint injectionPoint = annotationNode2 != null ? InjectionPoint.parse((IInjectionPointContext)iSliceContext, annotationNode2) : null;
        InjectionPoint injectionPoint2 = annotationNode3 != null ? InjectionPoint.parse((IInjectionPointContext)iSliceContext, annotationNode3) : null;
        return new MethodSlice(iSliceContext, string, injectionPoint, injectionPoint2);
    }

    static final class InsnListSlice
    extends ReadOnlyInsnList {
        private final int start;
        private final int end;

        protected InsnListSlice(InsnList insnList, int n2, int n3) {
            super(insnList);
            this.start = n2;
            this.end = n3;
        }

        @Override
        public ListIterator<AbstractInsnNode> iterator() {
            return this.iterator(0);
        }

        @Override
        public ListIterator<AbstractInsnNode> iterator(int n2) {
            return new SliceIterator(super.iterator(this.start + n2), this.start, this.end, this.start + n2);
        }

        @Override
        public AbstractInsnNode[] toArray() {
            AbstractInsnNode[] arrabstractInsnNode = super.toArray();
            AbstractInsnNode[] arrabstractInsnNode2 = new AbstractInsnNode[this.size()];
            System.arraycopy(arrabstractInsnNode, this.start, arrabstractInsnNode2, 0, arrabstractInsnNode2.length);
            return arrabstractInsnNode2;
        }

        @Override
        public int size() {
            return this.end - this.start + 1;
        }

        @Override
        public AbstractInsnNode getFirst() {
            return super.get(this.start);
        }

        @Override
        public AbstractInsnNode getLast() {
            return super.get(this.end);
        }

        @Override
        public AbstractInsnNode get(int n2) {
            return super.get(this.start + n2);
        }

        @Override
        public boolean contains(AbstractInsnNode abstractInsnNode) {
            for (AbstractInsnNode abstractInsnNode2 : this.toArray()) {
                if (abstractInsnNode2 != abstractInsnNode) continue;
                return true;
            }
            return false;
        }

        @Override
        public int indexOf(AbstractInsnNode abstractInsnNode) {
            int n2 = super.indexOf(abstractInsnNode);
            return n2 >= this.start && n2 <= this.end ? n2 - this.start : -1;
        }

        public int realIndexOf(AbstractInsnNode abstractInsnNode) {
            return super.indexOf(abstractInsnNode);
        }

        static class SliceIterator
        implements ListIterator<AbstractInsnNode> {
            private final ListIterator<AbstractInsnNode> iter;
            private int start;
            private int end;
            private int index;

            public SliceIterator(ListIterator<AbstractInsnNode> listIterator, int n2, int n3, int n4) {
                this.iter = listIterator;
                this.start = n2;
                this.end = n3;
                this.index = n4;
            }

            @Override
            public boolean hasNext() {
                return this.index <= this.end && this.iter.hasNext();
            }

            @Override
            public AbstractInsnNode next() {
                if (this.index > this.end) {
                    throw new NoSuchElementException();
                }
                ++this.index;
                return this.iter.next();
            }

            @Override
            public boolean hasPrevious() {
                return this.index > this.start;
            }

            @Override
            public AbstractInsnNode previous() {
                if (this.index <= this.start) {
                    throw new NoSuchElementException();
                }
                --this.index;
                return this.iter.previous();
            }

            @Override
            public int nextIndex() {
                return this.index - this.start;
            }

            @Override
            public int previousIndex() {
                return this.index - this.start - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove insn from slice");
            }

            @Override
            public void set(AbstractInsnNode abstractInsnNode) {
                throw new UnsupportedOperationException("Cannot set insn using slice");
            }

            @Override
            public void add(AbstractInsnNode abstractInsnNode) {
                throw new UnsupportedOperationException("Cannot add insn using slice");
            }
        }
    }
}

