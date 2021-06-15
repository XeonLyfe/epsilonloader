/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.code;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.injection.code.MethodSlice;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidSliceException;
import org.spongepowered.asm.util.Annotations;

public final class MethodSlices {
    private final InjectionInfo info;
    private final Map<String, MethodSlice> slices = new HashMap<String, MethodSlice>(4);

    private MethodSlices(InjectionInfo injectionInfo) {
        this.info = injectionInfo;
    }

    private void add(MethodSlice methodSlice) {
        String string = this.info.getSliceId(methodSlice.getId());
        if (this.slices.containsKey(string)) {
            throw new InvalidSliceException((ISliceContext)this.info, methodSlice + " has a duplicate id, '" + string + "' was already defined");
        }
        this.slices.put(string, methodSlice);
    }

    public MethodSlice get(String string) {
        return this.slices.get(string);
    }

    public String toString() {
        return String.format("MethodSlices%s", this.slices.keySet());
    }

    public static MethodSlices parse(InjectionInfo injectionInfo) {
        MethodSlices methodSlices = new MethodSlices(injectionInfo);
        AnnotationNode annotationNode = injectionInfo.getAnnotation();
        if (annotationNode != null) {
            for (AnnotationNode annotationNode2 : Annotations.getValue(annotationNode, "slice", true)) {
                MethodSlice methodSlice = MethodSlice.parse((ISliceContext)injectionInfo, annotationNode2);
                methodSlices.add(methodSlice);
            }
        }
        return methodSlices;
    }
}

