/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.TypeAnnotationNode;

public class LocalVariableAnnotationNode
extends TypeAnnotationNode {
    public List<LabelNode> start;
    public List<LabelNode> end;
    public List<Integer> index;

    public LocalVariableAnnotationNode(int n2, TypePath typePath, LabelNode[] arrlabelNode, LabelNode[] arrlabelNode2, int[] arrn, String string) {
        this(327680, n2, typePath, arrlabelNode, arrlabelNode2, arrn, string);
    }

    public LocalVariableAnnotationNode(int n2, int n3, TypePath typePath, LabelNode[] arrlabelNode, LabelNode[] arrlabelNode2, int[] arrn, String string) {
        super(n2, n3, typePath, string);
        this.start = new ArrayList<LabelNode>(arrlabelNode.length);
        this.start.addAll(Arrays.asList(arrlabelNode));
        this.end = new ArrayList<LabelNode>(arrlabelNode2.length);
        this.end.addAll(Arrays.asList(arrlabelNode2));
        this.index = new ArrayList<Integer>(arrn.length);
        for (int n4 : arrn) {
            this.index.add(n4);
        }
    }

    public void accept(MethodVisitor methodVisitor, boolean bl) {
        Label[] arrlabel = new Label[this.start.size()];
        Label[] arrlabel2 = new Label[this.end.size()];
        int[] arrn = new int[this.index.size()];
        for (int i2 = 0; i2 < arrlabel.length; ++i2) {
            arrlabel[i2] = this.start.get(i2).getLabel();
            arrlabel2[i2] = this.end.get(i2).getLabel();
            arrn[i2] = this.index.get(i2);
        }
        this.accept(methodVisitor.visitLocalVariableAnnotation(this.typeRef, this.typePath, arrlabel, arrlabel2, arrn, this.desc, true));
    }
}

