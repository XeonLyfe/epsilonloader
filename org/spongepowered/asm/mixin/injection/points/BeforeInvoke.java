/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

@InjectionPoint.AtCode(value="INVOKE")
public class BeforeInvoke
extends InjectionPoint {
    protected final MemberInfo target;
    protected final boolean allowPermissive;
    protected final int ordinal;
    protected final String className;
    protected final IMixinContext context;
    protected final Logger logger = LogManager.getLogger((String)"mixin");
    private boolean log = false;

    public BeforeInvoke(InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.target = injectionPointData.getTarget();
        this.ordinal = injectionPointData.getOrdinal();
        this.log = injectionPointData.get("log", false);
        this.className = this.getClassName();
        this.context = injectionPointData.getContext();
        this.allowPermissive = this.context.getOption(MixinEnvironment.Option.REFMAP_REMAP) && this.context.getOption(MixinEnvironment.Option.REFMAP_REMAP_ALLOW_PERMISSIVE) && !this.context.getReferenceMapper().isDefault();
    }

    private String getClassName() {
        InjectionPoint.AtCode atCode = this.getClass().getAnnotation(InjectionPoint.AtCode.class);
        return String.format("@At(%s)", atCode != null ? atCode.value() : this.getClass().getSimpleName().toUpperCase());
    }

    public BeforeInvoke setLogging(boolean bl) {
        this.log = bl;
        return this;
    }

    @Override
    public boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection) {
        this.log("{} is searching for an injection point in method with descriptor {}", this.className, string);
        if (!this.find(string, insnList, collection, this.target, SearchType.STRICT) && this.target.desc != null && this.allowPermissive) {
            this.logger.warn("STRICT match for {} using \"{}\" in {} returned 0 results, attempting permissive search. To inhibit permissive search set mixin.env.allowPermissiveMatch=false", new Object[]{this.className, this.target, this.context});
            return this.find(string, insnList, collection, this.target, SearchType.PERMISSIVE);
        }
        return true;
    }

    protected boolean find(String string, InsnList insnList, Collection<AbstractInsnNode> collection, MemberInfo memberInfo, SearchType searchType) {
        if (memberInfo == null) {
            return false;
        }
        MemberInfo memberInfo2 = searchType == SearchType.PERMISSIVE ? memberInfo.transform(null) : memberInfo;
        int n2 = 0;
        int n3 = 0;
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = listIterator.next();
            if (this.matchesInsn(abstractInsnNode)) {
                MemberInfo memberInfo3 = new MemberInfo(abstractInsnNode);
                this.log("{} is considering insn {}", this.className, memberInfo3);
                if (memberInfo2.matches(memberInfo3.owner, memberInfo3.name, memberInfo3.desc)) {
                    this.log("{} > found a matching insn, checking preconditions...", this.className);
                    if (this.matchesInsn(memberInfo3, n2)) {
                        this.log("{} > > > found a matching insn at ordinal {}", this.className, n2);
                        if (this.addInsn(insnList, collection, abstractInsnNode)) {
                            ++n3;
                        }
                        if (this.ordinal == n2) break;
                    }
                    ++n2;
                }
            }
            this.inspectInsn(string, insnList, abstractInsnNode);
        }
        if (searchType == SearchType.PERMISSIVE && n3 > 1) {
            this.logger.warn("A permissive match for {} using \"{}\" in {} matched {} instructions, this may cause unexpected behaviour. To inhibit permissive search set mixin.env.allowPermissiveMatch=false", new Object[]{this.className, memberInfo, this.context, n3});
        }
        return n3 > 0;
    }

    protected boolean addInsn(InsnList insnList, Collection<AbstractInsnNode> collection, AbstractInsnNode abstractInsnNode) {
        collection.add(abstractInsnNode);
        return true;
    }

    protected boolean matchesInsn(AbstractInsnNode abstractInsnNode) {
        return abstractInsnNode instanceof MethodInsnNode;
    }

    protected void inspectInsn(String string, InsnList insnList, AbstractInsnNode abstractInsnNode) {
    }

    protected boolean matchesInsn(MemberInfo memberInfo, int n2) {
        this.log("{} > > comparing target ordinal {} with current ordinal {}", this.className, this.ordinal, n2);
        return this.ordinal == -1 || this.ordinal == n2;
    }

    protected void log(String string, Object ... arrobject) {
        if (this.log) {
            this.logger.info(string, arrobject);
        }
    }

    public static enum SearchType {
        STRICT,
        PERMISSIVE;

    }
}

