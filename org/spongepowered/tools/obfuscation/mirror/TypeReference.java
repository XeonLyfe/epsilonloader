/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mirror;

import java.io.Serializable;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public class TypeReference
implements Serializable,
Comparable<TypeReference> {
    private static final long serialVersionUID = 1L;
    private final String name;
    private transient TypeHandle handle;

    public TypeReference(TypeHandle typeHandle) {
        this.name = typeHandle.getName();
        this.handle = typeHandle;
    }

    public TypeReference(String string) {
        this.name = string;
    }

    public String getName() {
        return this.name;
    }

    public String getClassName() {
        return this.name.replace('/', '.');
    }

    public TypeHandle getHandle(ProcessingEnvironment processingEnvironment) {
        if (this.handle == null) {
            TypeElement typeElement = processingEnvironment.getElementUtils().getTypeElement(this.getClassName());
            try {
                this.handle = new TypeHandle(typeElement);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return this.handle;
    }

    public String toString() {
        return String.format("TypeReference[%s]", this.name);
    }

    @Override
    public int compareTo(TypeReference typeReference) {
        return typeReference == null ? -1 : this.name.compareTo(typeReference.name);
    }

    public boolean equals(Object object) {
        return object instanceof TypeReference && this.compareTo((TypeReference)object) == 0;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}

