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
public class TableSwitchInsnNode
extends AbstractInsnNode {
    public int min;
    public int max;
    public LabelNode dflt;
    public List<LabelNode> labels;

    public TableSwitchInsnNode(int n2, int n3, LabelNode labelNode, LabelNode ... arrlabelNode) {
        super(170);
        this.min = n2;
        this.max = n3;
        this.dflt = labelNode;
        this.labels = new ArrayList<LabelNode>();
        if (arrlabelNode != null) {
            this.labels.addAll(Arrays.asList(arrlabelNode));
        }
    }

    @Override
    public int getType() {
        return 11;
    }

    @Override
    public void accept(MethodVisitor methodVisitor) {
        Label[] arrlabel = new Label[this.labels.size()];
        for (int i2 = 0; i2 < arrlabel.length; ++i2) {
            arrlabel[i2] = this.labels.get(i2).getLabel();
        }
        methodVisitor.visitTableSwitchInsn(this.min, this.max, this.dflt.getLabel(), arrlabel);
        this.acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> map) {
        return new TableSwitchInsnNode(this.min, this.max, TableSwitchInsnNode.clone(this.dflt, map), TableSwitchInsnNode.clone(this.labels, map)).cloneAnnotations(this);
    }
}

