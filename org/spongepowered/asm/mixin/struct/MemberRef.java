/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.util.Bytecode;

public abstract class MemberRef {
    private static final int[] H_OPCODES = new int[]{0, 180, 178, 181, 179, 182, 184, 183, 183, 185};

    public abstract boolean isField();

    public abstract int getOpcode();

    public abstract void setOpcode(int var1);

    public abstract String getOwner();

    public abstract void setOwner(String var1);

    public abstract String getName();

    public abstract void setName(String var1);

    public abstract String getDesc();

    public abstract void setDesc(String var1);

    public String toString() {
        String string = Bytecode.getOpcodeName(this.getOpcode());
        return String.format("%s for %s.%s%s%s", string, this.getOwner(), this.getName(), this.isField() ? ":" : "", this.getDesc());
    }

    public boolean equals(Object object) {
        if (!(object instanceof MemberRef)) {
            return false;
        }
        MemberRef memberRef = (MemberRef)object;
        return this.getOpcode() == memberRef.getOpcode() && this.getOwner().equals(memberRef.getOwner()) && this.getName().equals(memberRef.getName()) && this.getDesc().equals(memberRef.getDesc());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    static int opcodeFromTag(int n2) {
        return n2 >= 0 && n2 < H_OPCODES.length ? H_OPCODES[n2] : 0;
    }

    static int tagFromOpcode(int n2) {
        for (int i2 = 1; i2 < H_OPCODES.length; ++i2) {
            if (H_OPCODES[i2] != n2) continue;
            return i2;
        }
        return 0;
    }

    public static final class Handle
    extends MemberRef {
        private org.spongepowered.asm.lib.Handle handle;

        public Handle(org.spongepowered.asm.lib.Handle handle) {
            this.handle = handle;
        }

        public org.spongepowered.asm.lib.Handle getMethodHandle() {
            return this.handle;
        }

        @Override
        public boolean isField() {
            switch (this.handle.getTag()) {
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: {
                    return false;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    return true;
                }
            }
            throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
        }

        @Override
        public int getOpcode() {
            int n2 = MemberRef.opcodeFromTag(this.handle.getTag());
            if (n2 == 0) {
                throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
            }
            return n2;
        }

        @Override
        public void setOpcode(int n2) {
            int n3 = MemberRef.tagFromOpcode(n2);
            if (n3 == 0) {
                throw new MixinTransformerError("Invalid opcode " + Bytecode.getOpcodeName(n2) + " for method handle " + this.handle + ".");
            }
            boolean bl = n3 == 9;
            this.handle = new org.spongepowered.asm.lib.Handle(n3, this.handle.getOwner(), this.handle.getName(), this.handle.getDesc(), bl);
        }

        @Override
        public String getOwner() {
            return this.handle.getOwner();
        }

        @Override
        public void setOwner(String string) {
            boolean bl = this.handle.getTag() == 9;
            this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), string, this.handle.getName(), this.handle.getDesc(), bl);
        }

        @Override
        public String getName() {
            return this.handle.getName();
        }

        @Override
        public void setName(String string) {
            boolean bl = this.handle.getTag() == 9;
            this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), this.handle.getOwner(), string, this.handle.getDesc(), bl);
        }

        @Override
        public String getDesc() {
            return this.handle.getDesc();
        }

        @Override
        public void setDesc(String string) {
            boolean bl = this.handle.getTag() == 9;
            this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), this.handle.getOwner(), this.handle.getName(), string, bl);
        }
    }

    public static final class Field
    extends MemberRef {
        private static final int OPCODES = 183;
        public final FieldInsnNode insn;

        public Field(FieldInsnNode fieldInsnNode) {
            this.insn = fieldInsnNode;
        }

        @Override
        public boolean isField() {
            return true;
        }

        @Override
        public int getOpcode() {
            return this.insn.getOpcode();
        }

        @Override
        public void setOpcode(int n2) {
            if ((n2 & 0xB7) == 0) {
                throw new IllegalArgumentException("Invalid opcode for field instruction: 0x" + Integer.toHexString(n2));
            }
            this.insn.setOpcode(n2);
        }

        @Override
        public String getOwner() {
            return this.insn.owner;
        }

        @Override
        public void setOwner(String string) {
            this.insn.owner = string;
        }

        @Override
        public String getName() {
            return this.insn.name;
        }

        @Override
        public void setName(String string) {
            this.insn.name = string;
        }

        @Override
        public String getDesc() {
            return this.insn.desc;
        }

        @Override
        public void setDesc(String string) {
            this.insn.desc = string;
        }
    }

    public static final class Method
    extends MemberRef {
        private static final int OPCODES = 191;
        public final MethodInsnNode insn;

        public Method(MethodInsnNode methodInsnNode) {
            this.insn = methodInsnNode;
        }

        @Override
        public boolean isField() {
            return false;
        }

        @Override
        public int getOpcode() {
            return this.insn.getOpcode();
        }

        @Override
        public void setOpcode(int n2) {
            if ((n2 & 0xBF) == 0) {
                throw new IllegalArgumentException("Invalid opcode for method instruction: 0x" + Integer.toHexString(n2));
            }
            this.insn.setOpcode(n2);
        }

        @Override
        public String getOwner() {
            return this.insn.owner;
        }

        @Override
        public void setOwner(String string) {
            this.insn.owner = string;
        }

        @Override
        public String getName() {
            return this.insn.name;
        }

        @Override
        public void setName(String string) {
            this.insn.name = string;
        }

        @Override
        public String getDesc() {
            return this.insn.desc;
        }

        @Override
        public void setDesc(String string) {
            this.insn.desc = string;
        }
    }
}

