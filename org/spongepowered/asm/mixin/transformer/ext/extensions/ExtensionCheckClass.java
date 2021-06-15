/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.transformer.ext.extensions;

import org.spongepowered.asm.lib.util.CheckClassAdapter;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.transformers.MixinClassWriter;

public class ExtensionCheckClass
implements IExtension {
    @Override
    public boolean checkActive(MixinEnvironment mixinEnvironment) {
        return mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_VERIFY);
    }

    @Override
    public void preApply(ITargetClassContext iTargetClassContext) {
    }

    @Override
    public void postApply(ITargetClassContext iTargetClassContext) {
        try {
            iTargetClassContext.getClassNode().accept(new CheckClassAdapter(new MixinClassWriter(2)));
        }
        catch (RuntimeException runtimeException) {
            throw new ValidationFailedException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public void export(MixinEnvironment mixinEnvironment, String string, boolean bl, byte[] arrby) {
    }

    public static class ValidationFailedException
    extends MixinException {
        private static final long serialVersionUID = 1L;

        public ValidationFailedException(String string, Throwable throwable) {
            super(string, throwable);
        }

        public ValidationFailedException(String string) {
            super(string);
        }

        public ValidationFailedException(Throwable throwable) {
            super(throwable);
        }
    }
}

