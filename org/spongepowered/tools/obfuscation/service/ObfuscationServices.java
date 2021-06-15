/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.service.IObfuscationService;
import org.spongepowered.tools.obfuscation.service.ObfuscationTypeDescriptor;

public final class ObfuscationServices {
    private static ObfuscationServices instance;
    private final ServiceLoader<IObfuscationService> serviceLoader;
    private final Set<IObfuscationService> services = new HashSet<IObfuscationService>();

    private ObfuscationServices() {
        this.serviceLoader = ServiceLoader.load(IObfuscationService.class, this.getClass().getClassLoader());
    }

    public static ObfuscationServices getInstance() {
        if (instance == null) {
            instance = new ObfuscationServices();
        }
        return instance;
    }

    public void initProviders(IMixinAnnotationProcessor iMixinAnnotationProcessor) {
        try {
            for (IObfuscationService iObfuscationService : this.serviceLoader) {
                if (this.services.contains(iObfuscationService)) continue;
                this.services.add(iObfuscationService);
                String string = iObfuscationService.getClass().getSimpleName();
                Collection<ObfuscationTypeDescriptor> collection = iObfuscationService.getObfuscationTypes();
                if (collection == null) continue;
                for (ObfuscationTypeDescriptor obfuscationTypeDescriptor : collection) {
                    try {
                        ObfuscationType obfuscationType = ObfuscationType.create(obfuscationTypeDescriptor, iMixinAnnotationProcessor);
                        iMixinAnnotationProcessor.printMessage(Diagnostic.Kind.NOTE, string + " supports type: \"" + obfuscationType + "\"");
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        catch (ServiceConfigurationError serviceConfigurationError) {
            iMixinAnnotationProcessor.printMessage(Diagnostic.Kind.ERROR, serviceConfigurationError.getClass().getSimpleName() + ": " + serviceConfigurationError.getMessage());
            serviceConfigurationError.printStackTrace();
        }
    }

    public Set<String> getSupportedOptions() {
        HashSet<String> hashSet = new HashSet<String>();
        for (IObfuscationService iObfuscationService : this.serviceLoader) {
            Set<String> set = iObfuscationService.getSupportedOptions();
            if (set == null) continue;
            hashSet.addAll(set);
        }
        return hashSet;
    }

    public IObfuscationService getService(Class<? extends IObfuscationService> class_) {
        for (IObfuscationService iObfuscationService : this.serviceLoader) {
            if (!class_.getName().equals(iObfuscationService.getClass().getName())) continue;
            return iObfuscationService;
        }
        return null;
    }
}

