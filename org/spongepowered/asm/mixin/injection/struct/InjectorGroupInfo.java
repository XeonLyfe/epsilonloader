/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 */
package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import org.spongepowered.asm.util.Annotations;

public class InjectorGroupInfo {
    private final String name;
    private final List<InjectionInfo> members = new ArrayList<InjectionInfo>();
    private final boolean isDefault;
    private int minCallbackCount = -1;
    private int maxCallbackCount = Integer.MAX_VALUE;

    public InjectorGroupInfo(String string) {
        this(string, false);
    }

    InjectorGroupInfo(String string, boolean bl) {
        this.name = string;
        this.isDefault = bl;
    }

    public String toString() {
        return String.format("@Group(name=%s, min=%d, max=%d)", this.getName(), this.getMinRequired(), this.getMaxAllowed());
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public String getName() {
        return this.name;
    }

    public int getMinRequired() {
        return Math.max(this.minCallbackCount, 1);
    }

    public int getMaxAllowed() {
        return Math.min(this.maxCallbackCount, Integer.MAX_VALUE);
    }

    public Collection<InjectionInfo> getMembers() {
        return Collections.unmodifiableCollection(this.members);
    }

    public void setMinRequired(int n2) {
        if (n2 < 1) {
            throw new IllegalArgumentException("Cannot set zero or negative value for injector group min count. Attempted to set min=" + n2 + " on " + this);
        }
        if (this.minCallbackCount > 0 && this.minCallbackCount != n2) {
            LogManager.getLogger((String)"mixin").warn("Conflicting min value '{}' on @Group({}), previously specified {}", new Object[]{n2, this.name, this.minCallbackCount});
        }
        this.minCallbackCount = Math.max(this.minCallbackCount, n2);
    }

    public void setMaxAllowed(int n2) {
        if (n2 < 1) {
            throw new IllegalArgumentException("Cannot set zero or negative value for injector group max count. Attempted to set max=" + n2 + " on " + this);
        }
        if (this.maxCallbackCount < Integer.MAX_VALUE && this.maxCallbackCount != n2) {
            LogManager.getLogger((String)"mixin").warn("Conflicting max value '{}' on @Group({}), previously specified {}", new Object[]{n2, this.name, this.maxCallbackCount});
        }
        this.maxCallbackCount = Math.min(this.maxCallbackCount, n2);
    }

    public InjectorGroupInfo add(InjectionInfo injectionInfo) {
        this.members.add(injectionInfo);
        return this;
    }

    public InjectorGroupInfo validate() throws InjectionValidationException {
        if (this.members.size() == 0) {
            return this;
        }
        int n2 = 0;
        for (InjectionInfo injectionInfo : this.members) {
            n2 += injectionInfo.getInjectedCallbackCount();
        }
        int n3 = this.getMinRequired();
        int n4 = this.getMaxAllowed();
        if (n2 < n3) {
            throw new InjectionValidationException(this, String.format("expected %d invocation(s) but only %d succeeded", n3, n2));
        }
        if (n2 > n4) {
            throw new InjectionValidationException(this, String.format("maximum of %d invocation(s) allowed but %d succeeded", n4, n2));
        }
        return this;
    }

    public static final class Map
    extends HashMap<String, InjectorGroupInfo> {
        private static final long serialVersionUID = 1L;
        private static final InjectorGroupInfo NO_GROUP = new InjectorGroupInfo("NONE", true);

        @Override
        public InjectorGroupInfo get(Object object) {
            return this.forName(object.toString());
        }

        public InjectorGroupInfo forName(String string) {
            InjectorGroupInfo injectorGroupInfo = (InjectorGroupInfo)super.get(string);
            if (injectorGroupInfo == null) {
                injectorGroupInfo = new InjectorGroupInfo(string);
                this.put(string, injectorGroupInfo);
            }
            return injectorGroupInfo;
        }

        public InjectorGroupInfo parseGroup(MethodNode methodNode, String string) {
            return this.parseGroup(Annotations.getInvisible(methodNode, Group.class), string);
        }

        public InjectorGroupInfo parseGroup(AnnotationNode annotationNode, String string) {
            Integer n2;
            if (annotationNode == null) {
                return NO_GROUP;
            }
            String string2 = (String)Annotations.getValue(annotationNode, "name");
            if (string2 == null || string2.isEmpty()) {
                string2 = string;
            }
            InjectorGroupInfo injectorGroupInfo = this.forName(string2);
            Integer n3 = (Integer)Annotations.getValue(annotationNode, "min");
            if (n3 != null && n3 != -1) {
                injectorGroupInfo.setMinRequired(n3);
            }
            if ((n2 = (Integer)Annotations.getValue(annotationNode, "max")) != null && n2 != -1) {
                injectorGroupInfo.setMaxAllowed(n2);
            }
            return injectorGroupInfo;
        }

        public void validateAll() throws InjectionValidationException {
            for (InjectorGroupInfo injectorGroupInfo : this.values()) {
                injectorGroupInfo.validate();
            }
        }
    }
}

