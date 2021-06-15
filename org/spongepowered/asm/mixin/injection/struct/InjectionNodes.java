/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.util.Bytecode;

public class InjectionNodes
extends ArrayList<InjectionNode> {
    private static final long serialVersionUID = 1L;

    public InjectionNode add(AbstractInsnNode abstractInsnNode) {
        InjectionNode injectionNode = this.get(abstractInsnNode);
        if (injectionNode == null) {
            injectionNode = new InjectionNode(abstractInsnNode);
            this.add(injectionNode);
        }
        return injectionNode;
    }

    public InjectionNode get(AbstractInsnNode abstractInsnNode) {
        for (InjectionNode injectionNode : this) {
            if (!injectionNode.matches(abstractInsnNode)) continue;
            return injectionNode;
        }
        return null;
    }

    public boolean contains(AbstractInsnNode abstractInsnNode) {
        return this.get(abstractInsnNode) != null;
    }

    public void replace(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        InjectionNode injectionNode = this.get(abstractInsnNode);
        if (injectionNode != null) {
            injectionNode.replace(abstractInsnNode2);
        }
    }

    public void remove(AbstractInsnNode abstractInsnNode) {
        InjectionNode injectionNode = this.get(abstractInsnNode);
        if (injectionNode != null) {
            injectionNode.remove();
        }
    }

    public static class InjectionNode
    implements Comparable<InjectionNode> {
        private static int nextId = 0;
        private final int id;
        private final AbstractInsnNode originalTarget;
        private AbstractInsnNode currentTarget;
        private Map<String, Object> decorations;

        public InjectionNode(AbstractInsnNode abstractInsnNode) {
            this.currentTarget = this.originalTarget = abstractInsnNode;
            this.id = nextId++;
        }

        public int getId() {
            return this.id;
        }

        public AbstractInsnNode getOriginalTarget() {
            return this.originalTarget;
        }

        public AbstractInsnNode getCurrentTarget() {
            return this.currentTarget;
        }

        public InjectionNode replace(AbstractInsnNode abstractInsnNode) {
            this.currentTarget = abstractInsnNode;
            return this;
        }

        public InjectionNode remove() {
            this.currentTarget = null;
            return this;
        }

        public boolean matches(AbstractInsnNode abstractInsnNode) {
            return this.originalTarget == abstractInsnNode || this.currentTarget == abstractInsnNode;
        }

        public boolean isReplaced() {
            return this.originalTarget != this.currentTarget;
        }

        public boolean isRemoved() {
            return this.currentTarget == null;
        }

        public <V> InjectionNode decorate(String string, V v2) {
            if (this.decorations == null) {
                this.decorations = new HashMap<String, Object>();
            }
            this.decorations.put(string, v2);
            return this;
        }

        public boolean hasDecoration(String string) {
            return this.decorations != null && this.decorations.get(string) != null;
        }

        public <V> V getDecoration(String string) {
            return (V)(this.decorations == null ? null : this.decorations.get(string));
        }

        @Override
        public int compareTo(InjectionNode injectionNode) {
            return injectionNode == null ? Integer.MAX_VALUE : this.hashCode() - injectionNode.hashCode();
        }

        public String toString() {
            return String.format("InjectionNode[%s]", Bytecode.describeNode(this.currentTarget).replaceAll("\\s+", " "));
        }
    }
}

