/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package org.spongepowered.asm.mixin;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

public class EnvironmentStateTweaker
implements ITweaker {
    public void acceptOptions(List<String> list, File file, File file2, String string) {
    }

    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        MixinBootstrap.getPlatform().inject();
    }

    public String getLaunchTarget() {
        return "";
    }

    public String[] getLaunchArguments() {
        MixinEnvironment.gotoPhase(MixinEnvironment.Phase.DEFAULT);
        return new String[0];
    }
}

