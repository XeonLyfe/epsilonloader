/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LookupSwitchInsnNode
extends AbstractInsnNode {
    public LabelNode dflt;
    public List<Integer> keys;
    public List<LabelNode> labels;

    public LookupSwitchInsnNode(LabelNode labelNode, int[] arrn, LabelNode[] arrlabelNode) {
        super(171);
        this.dflt = labelNode;
        this.keys = new ArrayList<Integer>(arrn == null ? 0 : arrn.length);
        this.labels = new ArrayList<LabelNode>(arrlabelNode == null ? 0 : arrlabelNode.length);
        if (arrn != null) {
            for (int i2 = 0; i2 < arrn.length; ++i2) {
                this.keys.add(arrn[i2]);
            }
        }
        if (arrlabelNode != null) {
            this.labels.addAll(Arrays.asList(arrlabelNode));
        }
    }

    @Override
    public int getType() {
        return 12;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        int[] arrn = new int[this.keys.size()];
        for (int i2 = 0; i2 < arrn.length; ++i2) {
            arrn[i2] = this.keys.get(i2);
        }
        Label[] arrlabel = new Label[this.labels.size()];
        for (int i3 = 0; i3 < arrlabel.length; ++i3) {
            arrlabel[i3] = this.labels.get(i3).getLabel();
        }
        methodVisitor.visitLookupSwitchInsn(this.dflt.getLabel(), arrn, arrlabel);
        this.acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> map) {
        LookupSwitchInsnNode lookupSwitchInsnNode = new LookupSwitchInsnNode(LookupSwitchInsnNode.clone(this.dflt, map), null, LookupSwitchInsnNode.clone(this.labels, map));
        lookupSwitchInsnNode.keys.addAll(this.keys);
        return lookupSwitchInsnNode.cloneAnnotations(this);
    }
}

