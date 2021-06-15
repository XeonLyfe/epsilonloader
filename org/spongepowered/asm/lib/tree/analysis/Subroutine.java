/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;

class Subroutine {
    LabelNode start;
    boolean[] access;
    List<JumpInsnNode> callers;

    private Subroutine() {
    }

    Subroutine(LabelNode labelNode, int n2, JumpInsnNode jumpInsnNode) {
        this.start = labelNode;
        this.access = new boolean[n2];
        this.callers = new ArrayList<JumpInsnNode>();
        this.callers.add(jumpInsnNode);
    }

    public Subroutine copy() {
        Subroutine subroutine = new Subroutine();
        subroutine.start = this.start;
        subroutine.access = new boolean[this.access.length];
        System.arraycopy(this.access, 0, subroutine.access, 0, this.access.length);
        subroutine.callers = new ArrayList<JumpInsnNode>(this.callers);
        return subroutine;
    }

    public boolean merge(Subroutine subroutine) throws AnalyzerException {
        int n2;
        boolean bl = false;
        for (n2 = 0; n2 < this.access.length; ++n2) {
            if (!subroutine.access[n2] || this.access[n2]) continue;
            this.access[n2] = true;
            bl = true;
        }
        if (subroutine.start == this.start) {
            for (n2 = 0; n2 < subroutine.callers.size(); ++n2) {
                JumpInsnNode jumpInsnNode = subroutine.callers.get(n2);
                if (this.callers.contains(jumpInsnNode)) continue;
                this.callers.add(jumpInsnNode);
                bl = true;
            }
        }
        return bl;
    }
}

