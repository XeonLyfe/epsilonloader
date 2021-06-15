/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AnnotationNode
extends AnnotationVisitor {
    public String desc;
    public List<Object> values;

    public AnnotationNode(String string) {
        this(327680, string);
        if (this.getClass() != AnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    public AnnotationNode(int n2, String string) {
        super(n2);
        this.desc = string;
    }

    AnnotationNode(List<Object> list) {
        super(327680);
        this.values = list;
    }

    @Override
    public void visit(String string, Object object) {
        if (this.values == null) {
            this.values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        if (object instanceof byte[]) {
            byte[] arrby = (byte[])object;
            ArrayList<Byte> arrayList = new ArrayList<Byte>(arrby.length);
            for (byte by : arrby) {
                arrayList.add(by);
            }
            this.values.add(arrayList);
        } else if (object instanceof boolean[]) {
            boolean[] arrbl = (boolean[])object;
            ArrayList<Boolean> arrayList = new ArrayList<Boolean>(arrbl.length);
            for (boolean bl : arrbl) {
                arrayList.add(bl);
            }
            this.values.add(arrayList);
        } else if (object instanceof short[]) {
            short[] arrs = (short[])object;
            ArrayList<Short> arrayList = new ArrayList<Short>(arrs.length);
            for (short s2 : arrs) {
                arrayList.add(s2);
            }
            this.values.add(arrayList);
        } else if (object instanceof char[]) {
            char[] arrc = (char[])object;
            ArrayList<Character> arrayList = new ArrayList<Character>(arrc.length);
            for (char c2 : arrc) {
                arrayList.add(Character.valueOf(c2));
            }
            this.values.add(arrayList);
        } else if (object instanceof int[]) {
            int[] arrn = (int[])object;
            ArrayList<Integer> arrayList = new ArrayList<Integer>(arrn.length);
            for (int n2 : arrn) {
                arrayList.add(n2);
            }
            this.values.add(arrayList);
        } else if (object instanceof long[]) {
            long[] arrl = (long[])object;
            ArrayList<Long> arrayList = new ArrayList<Long>(arrl.length);
            for (long l2 : arrl) {
                arrayList.add(l2);
            }
            this.values.add(arrayList);
        } else if (object instanceof float[]) {
            float[] arrf = (float[])object;
            ArrayList<Float> arrayList = new ArrayList<Float>(arrf.length);
            for (float f2 : arrf) {
                arrayList.add(Float.valueOf(f2));
            }
            this.values.add(arrayList);
        } else if (object instanceof double[]) {
            double[] arrd = (double[])object;
            ArrayList<Double> arrayList = new ArrayList<Double>(arrd.length);
            for (double d2 : arrd) {
                arrayList.add(d2);
            }
            this.values.add(arrayList);
        } else {
            this.values.add(object);
        }
    }

    @Override
    public void visitEnum(String string, String string2, String string3) {
        if (this.values == null) {
            this.values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        this.values.add(new String[]{string2, string3});
    }

    @Override
    public AnnotationVisitor visitAnnotation(String string, String string2) {
        if (this.values == null) {
            this.values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        AnnotationNode annotationNode = new AnnotationNode(string2);
        this.values.add(annotationNode);
        return annotationNode;
    }

    @Override
    public AnnotationVisitor visitArray(String string) {
        if (this.values == null) {
            this.values = new ArrayList<Object>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            this.values.add(string);
        }
        ArrayList<Object> arrayList = new ArrayList<Object>();
        this.values.add(arrayList);
        return new AnnotationNode(arrayList);
    }

    @Override
    public void visitEnd() {
    }

    public void check(int n2) {
    }

    public void accept(AnnotationVisitor annotationVisitor) {
        if (annotationVisitor != null) {
            if (this.values != null) {
                for (int i2 = 0; i2 < this.values.size(); i2 += 2) {
                    String string = (String)this.values.get(i2);
                    Object object = this.values.get(i2 + 1);
                    AnnotationNode.accept(annotationVisitor, string, object);
                }
            }
            annotationVisitor.visitEnd();
        }
    }

    static void accept(AnnotationVisitor annotationVisitor, String string, Object object) {
        if (annotationVisitor != null) {
            if (object instanceof String[]) {
                String[] arrstring = (String[])object;
                annotationVisitor.visitEnum(string, arrstring[0], arrstring[1]);
            } else if (object instanceof AnnotationNode) {
                AnnotationNode annotationNode = (AnnotationNode)object;
                annotationNode.accept(annotationVisitor.visitAnnotation(string, annotationNode.desc));
            } else if (object instanceof List) {
                AnnotationVisitor annotationVisitor2 = annotationVisitor.visitArray(string);
                if (annotationVisitor2 != null) {
                    List list = (List)object;
                    for (int i2 = 0; i2 < list.size(); ++i2) {
                        AnnotationNode.accept(annotationVisitor2, null, list.get(i2));
                    }
                    annotationVisitor2.visitEnd();
                }
            } else {
                annotationVisitor.visit(string, object);
            }
        }
    }
}

