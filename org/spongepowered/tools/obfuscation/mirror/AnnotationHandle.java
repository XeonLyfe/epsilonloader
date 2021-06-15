/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public final class AnnotationHandle {
    public static final AnnotationHandle MISSING = new AnnotationHandle(null);
    private final AnnotationMirror annotation;

    private AnnotationHandle(AnnotationMirror annotationMirror) {
        this.annotation = annotationMirror;
    }

    public AnnotationMirror asMirror() {
        return this.annotation;
    }

    public boolean exists() {
        return this.annotation != null;
    }

    public String toString() {
        if (this.annotation == null) {
            return "@{UnknownAnnotation}";
        }
        return "@" + this.annotation.getAnnotationType().asElement().getSimpleName();
    }

    public <T> T getValue(String string, T t2) {
        if (this.annotation == null) {
            return t2;
        }
        AnnotationValue annotationValue = this.getAnnotationValue(string);
        if (t2 instanceof Enum && annotationValue != null) {
            VariableElement variableElement = (VariableElement)annotationValue.getValue();
            if (variableElement == null) {
                return t2;
            }
            return (T)Enum.valueOf(t2.getClass(), variableElement.getSimpleName().toString());
        }
        return (T)(annotationValue != null ? annotationValue.getValue() : t2);
    }

    public <T> T getValue() {
        return this.getValue("value", null);
    }

    public <T> T getValue(String string) {
        return this.getValue(string, null);
    }

    public boolean getBoolean(String string, boolean bl) {
        return this.getValue(string, bl);
    }

    public AnnotationHandle getAnnotation(String string) {
        Object object;
        Object t2 = this.getValue(string);
        if (t2 instanceof AnnotationMirror) {
            return AnnotationHandle.of((AnnotationMirror)t2);
        }
        if (t2 instanceof AnnotationValue && (object = ((AnnotationValue)t2).getValue()) instanceof AnnotationMirror) {
            return AnnotationHandle.of((AnnotationMirror)object);
        }
        return null;
    }

    public <T> List<T> getList() {
        return this.getList("value");
    }

    public <T> List<T> getList(String string) {
        List<AnnotationValue> list = this.getValue(string, Collections.emptyList());
        return AnnotationHandle.unwrapAnnotationValueList(list);
    }

    public List<AnnotationHandle> getAnnotationList(String string) {
        Object var2_2 = this.getValue(string, null);
        if (var2_2 == null) {
            return Collections.emptyList();
        }
        if (var2_2 instanceof AnnotationMirror) {
            return ImmutableList.of(AnnotationHandle.of(var2_2));
        }
        List list = var2_2;
        ArrayList<AnnotationHandle> arrayList = new ArrayList<AnnotationHandle>(list.size());
        for (AnnotationValue annotationValue : list) {
            arrayList.add(new AnnotationHandle((AnnotationMirror)annotationValue.getValue()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    protected AnnotationValue getAnnotationValue(String string) {
        for (ExecutableElement executableElement : this.annotation.getElementValues().keySet()) {
            if (!executableElement.getSimpleName().contentEquals(string)) continue;
            return this.annotation.getElementValues().get(executableElement);
        }
        return null;
    }

    protected static <T> List<T> unwrapAnnotationValueList(List<AnnotationValue> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList<Object> arrayList = new ArrayList<Object>(list.size());
        for (AnnotationValue annotationValue : list) {
            arrayList.add(annotationValue.getValue());
        }
        return arrayList;
    }

    protected static AnnotationMirror getAnnotation(Element element, Class<? extends Annotation> class_) {
        if (element == null) {
            return null;
        }
        List<? extends AnnotationMirror> list = element.getAnnotationMirrors();
        if (list == null) {
            return null;
        }
        for (AnnotationMirror annotationMirror : list) {
            TypeElement typeElement;
            Element element2 = annotationMirror.getAnnotationType().asElement();
            if (!(element2 instanceof TypeElement) || !(typeElement = (TypeElement)element2).getQualifiedName().contentEquals(class_.getName())) continue;
            return annotationMirror;
        }
        return null;
    }

    public static AnnotationHandle of(AnnotationMirror annotationMirror) {
        return new AnnotationHandle(annotationMirror);
    }

    public static AnnotationHandle of(Element element, Class<? extends Annotation> class_) {
        return new AnnotationHandle(AnnotationHandle.getAnnotation(element, class_));
    }
}

