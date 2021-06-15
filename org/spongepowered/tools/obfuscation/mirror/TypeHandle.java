/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeReference;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.mapping.ResolvableMappingMethod;

public class TypeHandle {
    private final String name;
    private final PackageElement pkg;
    private final TypeElement element;
    private TypeReference reference;

    public TypeHandle(PackageElement packageElement, String string) {
        this.name = string.replace('.', '/');
        this.pkg = packageElement;
        this.element = null;
    }

    public TypeHandle(TypeElement typeElement) {
        this.pkg = TypeUtils.getPackage(typeElement);
        this.name = TypeUtils.getInternalName(typeElement);
        this.element = typeElement;
    }

    public TypeHandle(DeclaredType declaredType) {
        this((TypeElement)declaredType.asElement());
    }

    public final String toString() {
        return this.name.replace('/', '.');
    }

    public final String getName() {
        return this.name;
    }

    public final PackageElement getPackage() {
        return this.pkg;
    }

    public final TypeElement getElement() {
        return this.element;
    }

    protected TypeElement getTargetElement() {
        return this.element;
    }

    public AnnotationHandle getAnnotation(Class<? extends Annotation> class_) {
        return AnnotationHandle.of(this.getTargetElement(), class_);
    }

    public final List<? extends Element> getEnclosedElements() {
        return TypeHandle.getEnclosedElements(this.getTargetElement());
    }

    public <T extends Element> List<T> getEnclosedElements(ElementKind ... arrelementKind) {
        return TypeHandle.getEnclosedElements(this.getTargetElement(), arrelementKind);
    }

    public TypeMirror getType() {
        return this.getTargetElement() != null ? this.getTargetElement().asType() : null;
    }

    public TypeHandle getSuperclass() {
        TypeElement typeElement = this.getTargetElement();
        if (typeElement == null) {
            return null;
        }
        TypeMirror typeMirror = typeElement.getSuperclass();
        if (typeMirror == null || typeMirror.getKind() == TypeKind.NONE) {
            return null;
        }
        return new TypeHandle((DeclaredType)typeMirror);
    }

    public List<TypeHandle> getInterfaces() {
        if (this.getTargetElement() == null) {
            return Collections.emptyList();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (TypeMirror typeMirror : this.getTargetElement().getInterfaces()) {
            builder.add(new TypeHandle((DeclaredType)typeMirror));
        }
        return builder.build();
    }

    public boolean isPublic() {
        return this.getTargetElement() != null && this.getTargetElement().getModifiers().contains((Object)Modifier.PUBLIC);
    }

    public boolean isImaginary() {
        return this.getTargetElement() == null;
    }

    public boolean isSimulated() {
        return false;
    }

    public final TypeReference getReference() {
        if (this.reference == null) {
            this.reference = new TypeReference(this);
        }
        return this.reference;
    }

    public MappingMethod getMappingMethod(String string, String string2) {
        return new ResolvableMappingMethod(this, string, string2);
    }

    public String findDescriptor(MemberInfo memberInfo) {
        String string = memberInfo.desc;
        if (string == null) {
            for (ExecutableElement executableElement : this.getEnclosedElements(ElementKind.METHOD)) {
                if (!executableElement.getSimpleName().toString().equals(memberInfo.name)) continue;
                string = TypeUtils.getDescriptor(executableElement);
                break;
            }
        }
        return string;
    }

    public final FieldHandle findField(VariableElement variableElement) {
        return this.findField(variableElement, true);
    }

    public final FieldHandle findField(VariableElement variableElement, boolean bl) {
        return this.findField(variableElement.getSimpleName().toString(), TypeUtils.getTypeName(variableElement.asType()), bl);
    }

    public final FieldHandle findField(String string, String string2) {
        return this.findField(string, string2, true);
    }

    public FieldHandle findField(String string, String string2, boolean bl) {
        String string3 = TypeUtils.stripGenerics(string2);
        for (VariableElement variableElement : this.getEnclosedElements(ElementKind.FIELD)) {
            if (TypeHandle.compareElement(variableElement, string, string2, bl)) {
                return new FieldHandle(this.getTargetElement(), variableElement);
            }
            if (!TypeHandle.compareElement(variableElement, string, string3, bl)) continue;
            return new FieldHandle(this.getTargetElement(), variableElement, true);
        }
        return null;
    }

    public final MethodHandle findMethod(ExecutableElement executableElement) {
        return this.findMethod(executableElement, true);
    }

    public final MethodHandle findMethod(ExecutableElement executableElement, boolean bl) {
        return this.findMethod(executableElement.getSimpleName().toString(), TypeUtils.getJavaSignature(executableElement), bl);
    }

    public final MethodHandle findMethod(String string, String string2) {
        return this.findMethod(string, string2, true);
    }

    public MethodHandle findMethod(String string, String string2, boolean bl) {
        String string3 = TypeUtils.stripGenerics(string2);
        return TypeHandle.findMethod(this, string, string2, string3, bl);
    }

    protected static MethodHandle findMethod(TypeHandle typeHandle, String string, String string2, String string3, boolean bl) {
        for (ExecutableElement executableElement : TypeHandle.getEnclosedElements(typeHandle.getTargetElement(), ElementKind.CONSTRUCTOR, ElementKind.METHOD)) {
            if (!TypeHandle.compareElement(executableElement, string, string2, bl) && !TypeHandle.compareElement(executableElement, string, string3, bl)) continue;
            return new MethodHandle(typeHandle, executableElement);
        }
        return null;
    }

    protected static boolean compareElement(Element element, String string, String string2, boolean bl) {
        try {
            String string3 = element.getSimpleName().toString();
            String string4 = TypeUtils.getJavaSignature(element);
            String string5 = TypeUtils.stripGenerics(string4);
            boolean bl2 = bl ? string.equals(string3) : string.equalsIgnoreCase(string3);
            return bl2 && (string2.length() == 0 || string2.equals(string4) || string2.equals(string5));
        }
        catch (NullPointerException nullPointerException) {
            return false;
        }
    }

    protected static <T extends Element> List<T> getEnclosedElements(TypeElement typeElement, ElementKind ... arrelementKind) {
        if (arrelementKind == null || arrelementKind.length < 1) {
            return TypeHandle.getEnclosedElements(typeElement);
        }
        if (typeElement == null) {
            return Collections.emptyList();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        block0: for (Element element : typeElement.getEnclosedElements()) {
            for (ElementKind elementKind : arrelementKind) {
                if (element.getKind() != elementKind) continue;
                builder.add(element);
                continue block0;
            }
        }
        return builder.build();
    }

    protected static List<? extends Element> getEnclosedElements(TypeElement typeElement) {
        return typeElement != null ? typeElement.getEnclosedElements() : Collections.emptyList();
    }
}

