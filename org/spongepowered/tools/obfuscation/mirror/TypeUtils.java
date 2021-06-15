/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import org.spongepowered.asm.util.SignaturePrinter;
import org.spongepowered.tools.obfuscation.mirror.Visibility;

public abstract class TypeUtils {
    private static final int MAX_GENERIC_RECURSION_DEPTH = 5;
    private static final String OBJECT_SIG = "java.lang.Object";
    private static final String OBJECT_REF = "java/lang/Object";

    private TypeUtils() {
    }

    public static PackageElement getPackage(TypeMirror typeMirror) {
        if (!(typeMirror instanceof DeclaredType)) {
            return null;
        }
        return TypeUtils.getPackage((TypeElement)((DeclaredType)typeMirror).asElement());
    }

    public static PackageElement getPackage(TypeElement typeElement) {
        Element element;
        for (element = typeElement.getEnclosingElement(); element != null && !(element instanceof PackageElement); element = element.getEnclosingElement()) {
        }
        return (PackageElement)element;
    }

    public static String getElementType(Element element) {
        if (element instanceof TypeElement) {
            return "TypeElement";
        }
        if (element instanceof ExecutableElement) {
            return "ExecutableElement";
        }
        if (element instanceof VariableElement) {
            return "VariableElement";
        }
        if (element instanceof PackageElement) {
            return "PackageElement";
        }
        if (element instanceof TypeParameterElement) {
            return "TypeParameterElement";
        }
        return element.getClass().getSimpleName();
    }

    public static String stripGenerics(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2 = string.charAt(i2);
            if (c2 == '<') {
                ++n2;
            }
            if (n2 == 0) {
                stringBuilder.append(c2);
                continue;
            }
            if (c2 != '>') continue;
            --n2;
        }
        return stringBuilder.toString();
    }

    public static String getName(VariableElement variableElement) {
        return variableElement != null ? variableElement.getSimpleName().toString() : null;
    }

    public static String getName(ExecutableElement executableElement) {
        return executableElement != null ? executableElement.getSimpleName().toString() : null;
    }

    public static String getJavaSignature(Element element) {
        if (element instanceof ExecutableElement) {
            ExecutableElement executableElement = (ExecutableElement)element;
            StringBuilder stringBuilder = new StringBuilder().append("(");
            boolean bl = false;
            for (VariableElement variableElement : executableElement.getParameters()) {
                if (bl) {
                    stringBuilder.append(',');
                }
                stringBuilder.append(TypeUtils.getTypeName(variableElement.asType()));
                bl = true;
            }
            stringBuilder.append(')').append(TypeUtils.getTypeName(executableElement.getReturnType()));
            return stringBuilder.toString();
        }
        return TypeUtils.getTypeName(element.asType());
    }

    public static String getJavaSignature(String string) {
        return new SignaturePrinter("", string).setFullyQualified(true).toDescriptor();
    }

    public static String getTypeName(TypeMirror typeMirror) {
        switch (typeMirror.getKind()) {
            case ARRAY: {
                return TypeUtils.getTypeName(((ArrayType)typeMirror).getComponentType()) + "[]";
            }
            case DECLARED: {
                return TypeUtils.getTypeName((DeclaredType)typeMirror);
            }
            case TYPEVAR: {
                return TypeUtils.getTypeName(TypeUtils.getUpperBound(typeMirror));
            }
            case ERROR: {
                return OBJECT_SIG;
            }
        }
        return typeMirror.toString();
    }

    public static String getTypeName(DeclaredType declaredType) {
        if (declaredType == null) {
            return OBJECT_SIG;
        }
        return TypeUtils.getInternalName((TypeElement)declaredType.asElement()).replace('/', '.');
    }

    public static String getDescriptor(Element element) {
        if (element instanceof ExecutableElement) {
            return TypeUtils.getDescriptor((ExecutableElement)element);
        }
        if (element instanceof VariableElement) {
            return TypeUtils.getInternalName((VariableElement)element);
        }
        return TypeUtils.getInternalName(element.asType());
    }

    public static String getDescriptor(ExecutableElement executableElement) {
        if (executableElement == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (VariableElement variableElement : executableElement.getParameters()) {
            stringBuilder.append(TypeUtils.getInternalName(variableElement));
        }
        String string = TypeUtils.getInternalName(executableElement.getReturnType());
        return String.format("(%s)%s", stringBuilder, string);
    }

    public static String getInternalName(VariableElement variableElement) {
        if (variableElement == null) {
            return null;
        }
        return TypeUtils.getInternalName(variableElement.asType());
    }

    public static String getInternalName(TypeMirror typeMirror) {
        switch (typeMirror.getKind()) {
            case ARRAY: {
                return "[" + TypeUtils.getInternalName(((ArrayType)typeMirror).getComponentType());
            }
            case DECLARED: {
                return "L" + TypeUtils.getInternalName((DeclaredType)typeMirror) + ";";
            }
            case TYPEVAR: {
                return "L" + TypeUtils.getInternalName(TypeUtils.getUpperBound(typeMirror)) + ";";
            }
            case BOOLEAN: {
                return "Z";
            }
            case BYTE: {
                return "B";
            }
            case CHAR: {
                return "C";
            }
            case DOUBLE: {
                return "D";
            }
            case FLOAT: {
                return "F";
            }
            case INT: {
                return "I";
            }
            case LONG: {
                return "J";
            }
            case SHORT: {
                return "S";
            }
            case VOID: {
                return "V";
            }
            case ERROR: {
                return "Ljava/lang/Object;";
            }
        }
        throw new IllegalArgumentException("Unable to parse type symbol " + typeMirror + " with " + (Object)((Object)typeMirror.getKind()) + " to equivalent bytecode type");
    }

    public static String getInternalName(DeclaredType declaredType) {
        if (declaredType == null) {
            return OBJECT_REF;
        }
        return TypeUtils.getInternalName((TypeElement)declaredType.asElement());
    }

    public static String getInternalName(TypeElement typeElement) {
        if (typeElement == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(typeElement.getSimpleName());
        for (Element element = typeElement.getEnclosingElement(); element != null; element = element.getEnclosingElement()) {
            if (element instanceof TypeElement) {
                stringBuilder.insert(0, "$").insert(0, element.getSimpleName());
                continue;
            }
            if (!(element instanceof PackageElement)) continue;
            stringBuilder.insert(0, "/").insert(0, ((PackageElement)element).getQualifiedName().toString().replace('.', '/'));
        }
        return stringBuilder.toString();
    }

    private static DeclaredType getUpperBound(TypeMirror typeMirror) {
        try {
            return TypeUtils.getUpperBound0(typeMirror, 5);
        }
        catch (IllegalStateException illegalStateException) {
            throw new IllegalArgumentException("Type symbol \"" + typeMirror + "\" is too complex", illegalStateException);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Unable to compute upper bound of type symbol " + typeMirror, illegalArgumentException);
        }
    }

    private static DeclaredType getUpperBound0(TypeMirror typeMirror, int n2) {
        if (n2 == 0) {
            throw new IllegalStateException("Generic symbol \"" + typeMirror + "\" is too complex, exceeded " + 5 + " iterations attempting to determine upper bound");
        }
        if (typeMirror instanceof DeclaredType) {
            return (DeclaredType)typeMirror;
        }
        if (typeMirror instanceof TypeVariable) {
            try {
                TypeMirror typeMirror2 = ((TypeVariable)typeMirror).getUpperBound();
                return TypeUtils.getUpperBound0(typeMirror2, --n2);
            }
            catch (IllegalStateException illegalStateException) {
                throw illegalStateException;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                throw illegalArgumentException;
            }
            catch (Exception exception) {
                throw new IllegalArgumentException("Unable to compute upper bound of type symbol " + typeMirror);
            }
        }
        return null;
    }

    public static boolean isAssignable(ProcessingEnvironment processingEnvironment, TypeMirror typeMirror, TypeMirror typeMirror2) {
        boolean bl = processingEnvironment.getTypeUtils().isAssignable(typeMirror, typeMirror2);
        if (!bl && typeMirror instanceof DeclaredType && typeMirror2 instanceof DeclaredType) {
            TypeMirror typeMirror3 = TypeUtils.toRawType(processingEnvironment, (DeclaredType)typeMirror);
            TypeMirror typeMirror4 = TypeUtils.toRawType(processingEnvironment, (DeclaredType)typeMirror2);
            return processingEnvironment.getTypeUtils().isAssignable(typeMirror3, typeMirror4);
        }
        return bl;
    }

    private static TypeMirror toRawType(ProcessingEnvironment processingEnvironment, DeclaredType declaredType) {
        return processingEnvironment.getElementUtils().getTypeElement(((TypeElement)declaredType.asElement()).getQualifiedName()).asType();
    }

    public static Visibility getVisibility(Element element) {
        if (element == null) {
            return null;
        }
        for (Modifier modifier : element.getModifiers()) {
            switch (modifier) {
                case PUBLIC: {
                    return Visibility.PUBLIC;
                }
                case PROTECTED: {
                    return Visibility.PROTECTED;
                }
                case PRIVATE: {
                    return Visibility.PRIVATE;
                }
            }
        }
        return Visibility.PACKAGE;
    }
}

