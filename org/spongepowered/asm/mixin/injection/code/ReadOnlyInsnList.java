/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.code;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;

class ReadOnlyInsnList
extends InsnList {
    private InsnList insnList;

    public ReadOnlyInsnList(InsnList insnList) {
        this.insnList = insnList;
    }

    void dispose() {
        this.insnList = null;
    }

    @Override
    public final void set(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void add(AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void add(InsnList insnList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insert(AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insert(InsnList insnList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insert(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insert(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insertBefore(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void insertBefore(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void remove(AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AbstractInsnNode[] toArray() {
        return this.insnList.toArray();
    }

    @Override
    public int size() {
        return this.insnList.size();
    }

    @Override
    public AbstractInsnNode getFirst() {
        return this.insnList.getFirst();
    }

    @Override
    public AbstractInsnNode getLast() {
        return this.insnList.getLast();
    }

    @Override
    public AbstractInsnNode get(int n2) {
        return this.insnList.get(n2);
    }

    @Override
    public boolean contains(AbstractInsnNode abstractInsnNode) {
        return this.insnList.contains(abstractInsnNode);
    }

    @Override
    public int indexOf(AbstractInsnNode abstractInsnNode) {
        return this.insnList.indexOf(abstractInsnNode);
    }

    @Override
    public ListIterator<AbstractInsnNode> iterator() {
        return this.insnList.iterator();
    }

    @Override
    public ListIterator<AbstractInsnNode> iterator(int n2) {
        return this.insnList.iterator(n2);
    }

    @Override
    public final void resetLabels() {
        this.insnList.resetLabels();
    }
}

