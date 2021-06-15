/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.mixin.struct;

import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.util.Bytecode;

public class SourceMap {
    private static final String DEFAULT_STRATUM = "Mixin";
    private static final String NEWLINE = "\n";
    private final String sourceFile;
    private final Map<String, Stratum> strata = new LinkedHashMap<String, Stratum>();
    private int nextLineOffset = 1;
    private String defaultStratum = "Mixin";

    public SourceMap(String string) {
        this.sourceFile = string;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public String getPseudoGeneratedSourceFile() {
        return this.sourceFile.replace(".java", "$mixin.java");
    }

    public File addFile(ClassNode classNode) {
        return this.addFile(this.defaultStratum, classNode);
    }

    public File addFile(String string, ClassNode classNode) {
        return this.addFile(string, classNode.sourceFile, classNode.name + ".java", Bytecode.getMaxLineNumber(classNode, 500, 50));
    }

    public File addFile(String string, String string2, int n2) {
        return this.addFile(this.defaultStratum, string, string2, n2);
    }

    public File addFile(String string, String string2, String string3, int n2) {
        Stratum stratum = this.strata.get(string);
        if (stratum == null) {
            stratum = new Stratum(string);
            this.strata.put(string, stratum);
        }
        File file = stratum.addFile(this.nextLineOffset, n2, string2, string3);
        this.nextLineOffset += n2;
        return file;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.appendTo(stringBuilder);
        return stringBuilder.toString();
    }

    private void appendTo(StringBuilder stringBuilder) {
        stringBuilder.append("SMAP").append(NEWLINE);
        stringBuilder.append(this.getSourceFile()).append(NEWLINE);
        stringBuilder.append(this.defaultStratum).append(NEWLINE);
        for (Stratum stratum : this.strata.values()) {
            stratum.appendTo(stringBuilder);
        }
        stringBuilder.append("*E").append(NEWLINE);
    }

    static class Stratum {
        private static final String STRATUM_MARK = "*S";
        private static final String FILE_MARK = "*F";
        private static final String LINES_MARK = "*L";
        public final String name;
        private final Map<String, File> files = new LinkedHashMap<String, File>();

        public Stratum(String string) {
            this.name = string;
        }

        public File addFile(int n2, int n3, String string, String string2) {
            File file = this.files.get(string2);
            if (file == null) {
                file = new File(this.files.size() + 1, n2, n3, string, string2);
                this.files.put(string2, file);
            }
            return file;
        }

        void appendTo(StringBuilder stringBuilder) {
            stringBuilder.append(STRATUM_MARK).append(" ").append(this.name).append(SourceMap.NEWLINE);
            stringBuilder.append(FILE_MARK).append(SourceMap.NEWLINE);
            for (File file : this.files.values()) {
                file.appendFile(stringBuilder);
            }
            stringBuilder.append(LINES_MARK).append(SourceMap.NEWLINE);
            for (File file : this.files.values()) {
                file.appendLines(stringBuilder);
            }
        }
    }

    public static class File {
        public final int id;
        public final int lineOffset;
        public final int size;
        public final String sourceFileName;
        public final String sourceFilePath;

        public File(int n2, int n3, int n4, String string) {
            this(n2, n3, n4, string, null);
        }

        public File(int n2, int n3, int n4, String string, String string2) {
            this.id = n2;
            this.lineOffset = n3;
            this.size = n4;
            this.sourceFileName = string;
            this.sourceFilePath = string2;
        }

        public void applyOffset(ClassNode classNode) {
            for (MethodNode methodNode : classNode.methods) {
                this.applyOffset(methodNode);
            }
        }

        public void applyOffset(MethodNode methodNode) {
            ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
            while (listIterator.hasNext()) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
                if (!(abstractInsnNode instanceof LineNumberNode)) continue;
                ((LineNumberNode)abstractInsnNode).line += this.lineOffset - 1;
            }
        }

        void appendFile(StringBuilder stringBuilder) {
            if (this.sourceFilePath != null) {
                stringBuilder.append("+ ").append(this.id).append(" ").append(this.sourceFileName).append(SourceMap.NEWLINE);
                stringBuilder.append(this.sourceFilePath).append(SourceMap.NEWLINE);
            } else {
                stringBuilder.append(this.id).append(" ").append(this.sourceFileName).append(SourceMap.NEWLINE);
            }
        }

        public void appendLines(StringBuilder stringBuilder) {
            stringBuilder.append("1#").append(this.id).append(",").append(this.size).append(":").append(this.lineOffset).append(SourceMap.NEWLINE);
        }
    }
}

