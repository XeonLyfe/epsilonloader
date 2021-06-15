/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;

public final class Annotations {
    private Annotations() {
    }

    public static void setVisible(FieldNode fieldNode, Class<? extends Annotation> class_, Object ... arrobject) {
        AnnotationNode annotationNode = Annotations.createNode(Type.getDescriptor(class_), arrobject);
        fieldNode.visibleAnnotations = Annotations.add(fieldNode.visibleAnnotations, annotationNode);
    }

    public static void setInvisible(FieldNode fieldNode, Class<? extends Annotation> class_, Object ... arrobject) {
        AnnotationNode annotationNode = Annotations.createNode(Type.getDescriptor(class_), arrobject);
        fieldNode.invisibleAnnotations = Annotations.add(fieldNode.invisibleAnnotations, annotationNode);
    }

    public static void setVisible(MethodNode methodNode, Class<? extends Annotation> class_, Object ... arrobject) {
        AnnotationNode annotationNode = Annotations.createNode(Type.getDescriptor(class_), arrobject);
        methodNode.visibleAnnotations = Annotations.add(methodNode.visibleAnnotations, annotationNode);
    }

    public static void setInvisible(MethodNode methodNode, Class<? extends Annotation> class_, Object ... arrobject) {
        AnnotationNode annotationNode = Annotations.createNode(Type.getDescriptor(class_), arrobject);
        methodNode.invisibleAnnotations = Annotations.add(methodNode.invisibleAnnotations, annotationNode);
    }

    private static AnnotationNode createNode(String string, Object ... arrobject) {
        AnnotationNode annotationNode = new AnnotationNode(string);
        for (int i2 = 0; i2 < arrobject.length - 1; i2 += 2) {
            if (!(arrobject[i2] instanceof String)) {
                throw new IllegalArgumentException("Annotation keys must be strings, found " + arrobject[i2].getClass().getSimpleName() + " with " + arrobject[i2].toString() + " at index " + i2 + " creating " + string);
            }
            annotationNode.visit((String)arrobject[i2], arrobject[i2 + 1]);
        }
        return annotationNode;
    }

    private static List<AnnotationNode> add(List<AnnotationNode> list, AnnotationNode annotationNode) {
        if (list == null) {
            list = new ArrayList<AnnotationNode>(1);
        } else {
            list.remove(Annotations.get(list, annotationNode.desc));
        }
        list.add(annotationNode);
        return list;
    }

    public static AnnotationNode getVisible(FieldNode fieldNode, Class<? extends Annotation> class_) {
        return Annotations.get(fieldNode.visibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getInvisible(FieldNode fieldNode, Class<? extends Annotation> class_) {
        return Annotations.get(fieldNode.invisibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getVisible(MethodNode methodNode, Class<? extends Annotation> class_) {
        return Annotations.get(methodNode.visibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getInvisible(MethodNode methodNode, Class<? extends Annotation> class_) {
        return Annotations.get(methodNode.invisibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getSingleVisible(MethodNode methodNode, Class<? extends Annotation> ... arrclass) {
        return Annotations.getSingle(methodNode.visibleAnnotations, arrclass);
    }

    public static AnnotationNode getSingleInvisible(MethodNode methodNode, Class<? extends Annotation> ... arrclass) {
        return Annotations.getSingle(methodNode.invisibleAnnotations, arrclass);
    }

    public static AnnotationNode getVisible(ClassNode classNode, Class<? extends Annotation> class_) {
        return Annotations.get(classNode.visibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getInvisible(ClassNode classNode, Class<? extends Annotation> class_) {
        return Annotations.get(classNode.invisibleAnnotations, Type.getDescriptor(class_));
    }

    public static AnnotationNode getVisibleParameter(MethodNode methodNode, Class<? extends Annotation> class_, int n2) {
        return Annotations.getParameter(methodNode.visibleParameterAnnotations, Type.getDescriptor(class_), n2);
    }

    public static AnnotationNode getInvisibleParameter(MethodNode methodNode, Class<? extends Annotation> class_, int n2) {
        return Annotations.getParameter(methodNode.invisibleParameterAnnotations, Type.getDescriptor(class_), n2);
    }

    public static AnnotationNode getParameter(List<AnnotationNode>[] arrlist, String string, int n2) {
        if (arrlist == null || n2 < 0 || n2 >= arrlist.length) {
            return null;
        }
        return Annotations.get(arrlist[n2], string);
    }

    public static AnnotationNode get(List<AnnotationNode> list, String string) {
        if (list == null) {
            return null;
        }
        for (AnnotationNode annotationNode : list) {
            if (!string.equals(annotationNode.desc)) continue;
            return annotationNode;
        }
        return null;
    }

    private static AnnotationNode getSingle(List<AnnotationNode> list, Class<? extends Annotation>[] arrclass) {
        ArrayList<AnnotationNode> arrayList = new ArrayList<AnnotationNode>();
        for (Class<? extends Annotation> class_ : arrclass) {
            AnnotationNode annotationNode = Annotations.get(list, Type.getDescriptor(class_));
            if (annotationNode == null) continue;
            arrayList.add(annotationNode);
        }
        int n2 = arrayList.size();
        if (n2 > 1) {
            throw new IllegalArgumentException("Conflicting annotations found: " + Lists.transform(arrayList, new Function<AnnotationNode, String>(){

                @Override
                public String apply(AnnotationNode annotationNode) {
                    return annotationNode.desc;
                }
            }));
        }
        return n2 == 0 ? null : (AnnotationNode)arrayList.get(0);
    }

    public static <T> T getValue(AnnotationNode annotationNode) {
        return Annotations.getValue(annotationNode, "value");
    }

    public static <T> T getValue(AnnotationNode annotationNode, String string, T t2) {
        T t3 = Annotations.getValue(annotationNode, string);
        return t3 != null ? t3 : t2;
    }

    public static <T> T getValue(AnnotationNode annotationNode, String string, Class<?> class_) {
        Preconditions.checkNotNull(class_, "annotationClass cannot be null");
        Object object = Annotations.getValue(annotationNode, string);
        if (object == null) {
            try {
                object = class_.getDeclaredMethod(string, new Class[0]).getDefaultValue();
            }
            catch (NoSuchMethodException noSuchMethodException) {
                // empty catch block
            }
        }
        return object;
    }

    public static <T> T getValue(AnnotationNode annotationNode, String string) {
        boolean bl = false;
        if (annotationNode == null || annotationNode.values == null) {
            return null;
        }
        for (Object object : annotationNode.values) {
            if (bl) {
                return (T)object;
            }
            if (!object.equals(string)) continue;
            bl = true;
        }
        return null;
    }

    public static <T extends Enum<T>> T getValue(AnnotationNode annotationNode, String string, Class<T> class_, T t2) {
        String[] arrstring = (String[])Annotations.getValue(annotationNode, string);
        if (arrstring == null) {
            return t2;
        }
        return Annotations.toEnumValue(class_, arrstring);
    }

    public static <T> List<T> getValue(AnnotationNode annotationNode, String string, boolean bl) {
        T t2 = Annotations.getValue(annotationNode, string);
        if (t2 instanceof List) {
            return (List)t2;
        }
        if (t2 != null) {
            ArrayList<T> arrayList = new ArrayList<T>();
            arrayList.add(t2);
            return arrayList;
        }
        return Collections.emptyList();
    }

    public static <T extends Enum<T>> List<T> getValue(AnnotationNode annotationNode, String string, boolean bl, Class<T> class_) {
        T t2 = Annotations.getValue(annotationNode, string);
        if (t2 instanceof List) {
            ListIterator<T> listIterator = ((List)t2).listIterator();
            while (listIterator.hasNext()) {
                listIterator.set(Annotations.toEnumValue(class_, (String[])listIterator.next()));
            }
            return (List)t2;
        }
        if (t2 instanceof String[]) {
            ArrayList<T> arrayList = new ArrayList<T>();
            arrayList.add(Annotations.toEnumValue(class_, (String[])t2));
            return arrayList;
        }
        return Collections.emptyList();
    }

    private static <T extends Enum<T>> T toEnumValue(Class<T> class_, String[] arrstring) {
        if (!class_.getName().equals(Type.getType(arrstring[0]).getClassName())) {
            throw new IllegalArgumentException("The supplied enum class does not match the stored enum value");
        }
        return Enum.valueOf(class_, arrstring[1]);
    }
}

