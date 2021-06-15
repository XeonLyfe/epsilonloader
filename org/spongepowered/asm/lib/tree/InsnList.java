/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class InsnList {
    private int size;
    private AbstractInsnNode first;
    private AbstractInsnNode last;
    AbstractInsnNode[] cache;

    public int size() {
        return this.size;
    }

    public AbstractInsnNode getFirst() {
        return this.first;
    }

    public AbstractInsnNode getLast() {
        return this.last;
    }

    public AbstractInsnNode get(int n2) {
        if (n2 < 0 || n2 >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.cache == null) {
            this.cache = this.toArray();
        }
        return this.cache[n2];
    }

    public boolean contains(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode abstractInsnNode2 = this.first;
        while (abstractInsnNode2 != null && abstractInsnNode2 != abstractInsnNode) {
            abstractInsnNode2 = abstractInsnNode2.next;
        }
        return abstractInsnNode2 != null;
    }

    public int indexOf(AbstractInsnNode abstractInsnNode) {
        if (this.cache == null) {
            this.cache = this.toArray();
        }
        return abstractInsnNode.index;
    }

    public void accept(MethodVisitor methodVisitor) {
        AbstractInsnNode abstractInsnNode = this.first;
        while (abstractInsnNode != null) {
            abstractInsnNode.accept(methodVisitor);
            abstractInsnNode = abstractInsnNode.next;
        }
    }

    public ListIterator<AbstractInsnNode> iterator() {
        return this.iterator(0);
    }

    public ListIterator<AbstractInsnNode> iterator(int n2) {
        return new InsnListIterator(n2);
    }

    public AbstractInsnNode[] toArray() {
        int n2 = 0;
        AbstractInsnNode abstractInsnNode = this.first;
        AbstractInsnNode[] arrabstractInsnNode = new AbstractInsnNode[this.size];
        while (abstractInsnNode != null) {
            arrabstractInsnNode[n2] = abstractInsnNode;
            abstractInsnNode.index = n2++;
            abstractInsnNode = abstractInsnNode.next;
        }
        return arrabstractInsnNode;
    }

    public void set(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        AbstractInsnNode abstractInsnNode3;
        AbstractInsnNode abstractInsnNode4;
        abstractInsnNode2.next = abstractInsnNode4 = abstractInsnNode.next;
        if (abstractInsnNode4 != null) {
            abstractInsnNode4.prev = abstractInsnNode2;
        } else {
            this.last = abstractInsnNode2;
        }
        abstractInsnNode2.prev = abstractInsnNode3 = abstractInsnNode.prev;
        if (abstractInsnNode3 != null) {
            abstractInsnNode3.next = abstractInsnNode2;
        } else {
            this.first = abstractInsnNode2;
        }
        if (this.cache != null) {
            int n2 = abstractInsnNode.index;
            this.cache[n2] = abstractInsnNode2;
            abstractInsnNode2.index = n2;
        } else {
            abstractInsnNode2.index = 0;
        }
        abstractInsnNode.index = -1;
        abstractInsnNode.prev = null;
        abstractInsnNode.next = null;
    }

    public void add(AbstractInsnNode abstractInsnNode) {
        ++this.size;
        if (this.last == null) {
            this.first = abstractInsnNode;
            this.last = abstractInsnNode;
        } else {
            this.last.next = abstractInsnNode;
            abstractInsnNode.prev = this.last;
        }
        this.last = abstractInsnNode;
        this.cache = null;
        abstractInsnNode.index = 0;
    }

    public void add(InsnList insnList) {
        if (insnList.size == 0) {
            return;
        }
        this.size += insnList.size;
        if (this.last == null) {
            this.first = insnList.first;
            this.last = insnList.last;
        } else {
            AbstractInsnNode abstractInsnNode;
            this.last.next = abstractInsnNode = insnList.first;
            abstractInsnNode.prev = this.last;
            this.last = insnList.last;
        }
        this.cache = null;
        insnList.removeAll(false);
    }

    public void insert(AbstractInsnNode abstractInsnNode) {
        ++this.size;
        if (this.first == null) {
            this.first = abstractInsnNode;
            this.last = abstractInsnNode;
        } else {
            this.first.prev = abstractInsnNode;
            abstractInsnNode.next = this.first;
        }
        this.first = abstractInsnNode;
        this.cache = null;
        abstractInsnNode.index = 0;
    }

    public void insert(InsnList insnList) {
        if (insnList.size == 0) {
            return;
        }
        this.size += insnList.size;
        if (this.first == null) {
            this.first = insnList.first;
            this.last = insnList.last;
        } else {
            AbstractInsnNode abstractInsnNode;
            this.first.prev = abstractInsnNode = insnList.last;
            abstractInsnNode.next = this.first;
            this.first = insnList.first;
        }
        this.cache = null;
        insnList.removeAll(false);
    }

    public void insert(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        ++this.size;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.next;
        if (abstractInsnNode3 == null) {
            this.last = abstractInsnNode2;
        } else {
            abstractInsnNode3.prev = abstractInsnNode2;
        }
        abstractInsnNode.next = abstractInsnNode2;
        abstractInsnNode2.next = abstractInsnNode3;
        abstractInsnNode2.prev = abstractInsnNode;
        this.cache = null;
        abstractInsnNode2.index = 0;
    }

    public void insert(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        if (insnList.size == 0) {
            return;
        }
        this.size += insnList.size;
        AbstractInsnNode abstractInsnNode2 = insnList.first;
        AbstractInsnNode abstractInsnNode3 = insnList.last;
        AbstractInsnNode abstractInsnNode4 = abstractInsnNode.next;
        if (abstractInsnNode4 == null) {
            this.last = abstractInsnNode3;
        } else {
            abstractInsnNode4.prev = abstractInsnNode3;
        }
        abstractInsnNode.next = abstractInsnNode2;
        abstractInsnNode3.next = abstractInsnNode4;
        abstractInsnNode2.prev = abstractInsnNode;
        this.cache = null;
        insnList.removeAll(false);
    }

    public void insertBefore(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        ++this.size;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.prev;
        if (abstractInsnNode3 == null) {
            this.first = abstractInsnNode2;
        } else {
            abstractInsnNode3.next = abstractInsnNode2;
        }
        abstractInsnNode.prev = abstractInsnNode2;
        abstractInsnNode2.next = abstractInsnNode;
        abstractInsnNode2.prev = abstractInsnNode3;
        this.cache = null;
        abstractInsnNode2.index = 0;
    }

    public void insertBefore(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        if (insnList.size == 0) {
            return;
        }
        this.size += insnList.size;
        AbstractInsnNode abstractInsnNode2 = insnList.first;
        AbstractInsnNode abstractInsnNode3 = insnList.last;
        AbstractInsnNode abstractInsnNode4 = abstractInsnNode.prev;
        if (abstractInsnNode4 == null) {
            this.first = abstractInsnNode2;
        } else {
            abstractInsnNode4.next = abstractInsnNode2;
        }
        abstractInsnNode.prev = abstractInsnNode3;
        abstractInsnNode3.next = abstractInsnNode;
        abstractInsnNode2.prev = abstractInsnNode4;
        this.cache = null;
        insnList.removeAll(false);
    }

    public void remove(AbstractInsnNode abstractInsnNode) {
        --this.size;
        AbstractInsnNode abstractInsnNode2 = abstractInsnNode.next;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.prev;
        if (abstractInsnNode2 == null) {
            if (abstractInsnNode3 == null) {
                this.first = null;
                this.last = null;
            } else {
                abstractInsnNode3.next = null;
                this.last = abstractInsnNode3;
            }
        } else if (abstractInsnNode3 == null) {
            this.first = abstractInsnNode2;
            abstractInsnNode2.prev = null;
        } else {
            abstractInsnNode3.next = abstractInsnNode2;
            abstractInsnNode2.prev = abstractInsnNode3;
        }
        this.cache = null;
        abstractInsnNode.index = -1;
        abstractInsnNode.prev = null;
        abstractInsnNode.next = null;
    }

    void removeAll(boolean bl) {
        if (bl) {
            AbstractInsnNode abstractInsnNode = this.first;
            while (abstractInsnNode != null) {
                AbstractInsnNode abstractInsnNode2 = abstractInsnNode.next;
                abstractInsnNode.index = -1;
                abstractInsnNode.prev = null;
                abstractInsnNode.next = null;
                abstractInsnNode = abstractInsnNode2;
            }
        }
        this.size = 0;
        this.first = null;
        this.last = null;
        this.cache = null;
    }

    public void clear() {
        this.removeAll(false);
    }

    public void resetLabels() {
        AbstractInsnNode abstractInsnNode = this.first;
        while (abstractInsnNode != null) {
            if (abstractInsnNode instanceof LabelNode) {
                ((LabelNode)abstractInsnNode).resetLabel();
            }
            abstractInsnNode = abstractInsnNode.next;
        }
    }

    private final class InsnListIterator
    implements ListIterator {
        AbstractInsnNode next;
        AbstractInsnNode prev;
        AbstractInsnNode remove;

        InsnListIterator(int n2) {
            if (n2 == InsnList.this.size()) {
                this.next = null;
                this.prev = InsnList.this.getLast();
            } else {
                this.next = InsnList.this.get(n2);
                this.prev = this.next.prev;
            }
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Object next() {
            AbstractInsnNode abstractInsnNode;
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            this.prev = abstractInsnNode = this.next;
            this.next = abstractInsnNode.next;
            this.remove = abstractInsnNode;
            return abstractInsnNode;
        }

        public void remove() {
            if (this.remove != null) {
                if (this.remove == this.next) {
                    this.next = this.next.next;
                } else {
                    this.prev = this.prev.prev;
                }
            } else {
                throw new IllegalStateException();
            }
            InsnList.this.remove(this.remove);
            this.remove = null;
        }

        public boolean hasPrevious() {
            return this.prev != null;
        }

        public Object previous() {
            AbstractInsnNode abstractInsnNode;
            this.next = abstractInsnNode = this.prev;
            this.prev = abstractInsnNode.prev;
            this.remove = abstractInsnNode;
            return abstractInsnNode;
        }

        public int nextIndex() {
            if (this.next == null) {
                return InsnList.this.size();
            }
            if (InsnList.this.cache == null) {
                InsnList.this.cache = InsnList.this.toArray();
            }
            return this.next.index;
        }

        public int previousIndex() {
            if (this.prev == null) {
                return -1;
            }
            if (InsnList.this.cache == null) {
                InsnList.this.cache = InsnList.this.toArray();
            }
            return this.prev.index;
        }

        public void add(Object object) {
            if (this.next != null) {
                InsnList.this.insertBefore(this.next, (AbstractInsnNode)object);
            } else if (this.prev != null) {
                InsnList.this.insert(this.prev, (AbstractInsnNode)object);
            } else {
                InsnList.this.add((AbstractInsnNode)object);
            }
            this.prev = (AbstractInsnNode)object;
            this.remove = null;
        }

        public void set(Object object) {
            if (this.remove != null) {
                InsnList.this.set(this.remove, (AbstractInsnNode)object);
                if (this.remove == this.prev) {
                    this.prev = (AbstractInsnNode)object;
                } else {
                    this.next = (AbstractInsnNode)object;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
}

