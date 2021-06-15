/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.analysis.SmallSet;
import org.spongepowered.asm.lib.tree.analysis.Value;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SourceValue
implements Value {
    public final int size;
    public final Set<AbstractInsnNode> insns;

    public SourceValue(int n2) {
        this(n2, SmallSet.emptySet());
    }

    public SourceValue(int n2, AbstractInsnNode abstractInsnNode) {
        this.size = n2;
        this.insns = new SmallSet<Object>(abstractInsnNode, null);
    }

    public SourceValue(int n2, Set<AbstractInsnNode> set) {
        this.size = n2;
        this.insns = set;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public boolean equals(Object object) {
        if (!(object instanceof SourceValue)) {
            return false;
        }
        SourceValue sourceValue = (SourceValue)object;
        return this.size == sourceValue.size && this.insns.equals(sourceValue.insns);
    }

    public int hashCode() {
        return this.insns.hashCode();
    }
}

