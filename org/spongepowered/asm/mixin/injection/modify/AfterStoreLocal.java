/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.modify.BeforeLoadLocal;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@InjectionPoint.AtCode(value="STORE")
public class AfterStoreLocal
extends BeforeLoadLocal {
    public AfterStoreLocal(InjectionPointData injectionPointData) {
        super(injectionPointData, 54, true);
    }
}

