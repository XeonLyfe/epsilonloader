/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.util;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.analysis.Analyzer;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.Frame;
import org.spongepowered.asm.lib.tree.analysis.SimpleVerifier;
import org.spongepowered.asm.lib.util.CheckAnnotationAdapter;
import org.spongepowered.asm.lib.util.CheckFieldAdapter;
import org.spongepowered.asm.lib.util.CheckMethodAdapter;
import org.spongepowered.asm.lib.util.Textifier;
import org.spongepowered.asm.lib.util.TraceMethodVisitor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CheckClassAdapter
extends ClassVisitor {
    private int version;
    private boolean start;
    private boolean source;
    private boolean outer;
    private boolean end;
    private Map<Label, Integer> labels = new HashMap<Label, Integer>();
    private boolean checkDataFlow;

    public static void main(String[] arrstring) throws Exception {
        if (arrstring.length != 1) {
            System.err.println("Verifies the given class.");
            System.err.println("Usage: CheckClassAdapter <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader = arrstring[0].endsWith(".class") ? new ClassReader(new FileInputStream(arrstring[0])) : new ClassReader(arrstring[0]);
        CheckClassAdapter.verify(classReader, false, new PrintWriter(System.err));
    }

    public static void verify(ClassReader classReader, ClassLoader classLoader, boolean bl, PrintWriter printWriter) {
        ClassNode classNode = new ClassNode();
        classReader.accept(new CheckClassAdapter(classNode, false), 2);
        Type type = classNode.superName == null ? null : Type.getObjectType(classNode.superName);
        List<MethodNode> list = classNode.methods;
        ArrayList<Type> arrayList = new ArrayList<Type>();
        Iterator<String> iterator2 = classNode.interfaces.iterator();
        while (iterator2.hasNext()) {
            arrayList.add(Type.getObjectType(iterator2.next()));
        }
        for (int i2 = 0; i2 < list.size(); ++i2) {
            MethodNode methodNode = list.get(i2);
            SimpleVerifier simpleVerifier = new SimpleVerifier(Type.getObjectType(classNode.name), type, arrayList, (classNode.access & 0x200) != 0);
            Analyzer<BasicValue> analyzer = new Analyzer<BasicValue>(simpleVerifier);
            if (classLoader != null) {
                simpleVerifier.setClassLoader(classLoader);
            }
            try {
                analyzer.analyze(classNode.name, methodNode);
                if (!bl) {
                    continue;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace(printWriter);
            }
            CheckClassAdapter.printAnalyzerResult(methodNode, analyzer, printWriter);
        }
        printWriter.flush();
    }

    public static void verify(ClassReader classReader, boolean bl, PrintWriter printWriter) {
        CheckClassAdapter.verify(classReader, null, bl, printWriter);
    }

    static void printAnalyzerResult(MethodNode methodNode, Analyzer<BasicValue> analyzer, PrintWriter printWriter) {
        int n2;
        Frame<BasicValue>[] arrframe = analyzer.getFrames();
        Textifier textifier = new Textifier();
        TraceMethodVisitor traceMethodVisitor = new TraceMethodVisitor(textifier);
        printWriter.println(methodNode.name + methodNode.desc);
        for (n2 = 0; n2 < methodNode.instructions.size(); ++n2) {
            methodNode.instructions.get(n2).accept(traceMethodVisitor);
            StringBuilder stringBuilder = new StringBuilder();
            Frame<BasicValue> frame = arrframe[n2];
            if (frame == null) {
                stringBuilder.append('?');
            } else {
                int n3;
                for (n3 = 0; n3 < frame.getLocals(); ++n3) {
                    stringBuilder.append(CheckClassAdapter.getShortName(frame.getLocal(n3).toString())).append(' ');
                }
                stringBuilder.append(" : ");
                for (n3 = 0; n3 < frame.getStackSize(); ++n3) {
                    stringBuilder.append(CheckClassAdapter.getShortName(frame.getStack(n3).toString())).append(' ');
                }
            }
            while (stringBuilder.length() < methodNode.maxStack + methodNode.maxLocals + 1) {
                stringBuilder.append(' ');
            }
            printWriter.print(Integer.toString(n2 + 100000).substring(1));
            printWriter.print(" " + stringBuilder + " : " + textifier.text.get(textifier.text.size() - 1));
        }
        for (n2 = 0; n2 < methodNode.tryCatchBlocks.size(); ++n2) {
            methodNode.tryCatchBlocks.get(n2).accept(traceMethodVisitor);
            printWriter.print(" " + textifier.text.get(textifier.text.size() - 1));
        }
        printWriter.println();
    }

    private static String getShortName(String string) {
        int n2 = string.lastIndexOf(47);
        int n3 = string.length();
        if (string.charAt(n3 - 1) == ';') {
            --n3;
        }
        return n2 == -1 ? string : string.substring(n2 + 1, n3);
    }

    public CheckClassAdapter(ClassVisitor classVisitor) {
        this(classVisitor, true);
    }

    public CheckClassAdapter(ClassVisitor classVisitor, boolean bl) {
        this(327680, classVisitor, bl);
        if (this.getClass() != CheckClassAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected CheckClassAdapter(int n2, ClassVisitor classVisitor, boolean bl) {
        super(n2, classVisitor);
        this.checkDataFlow = bl;
    }

    @Override
    public void visit(int n2, int n3, String string, String string2, String string3, String[] arrstring) {
        if (this.start) {
            throw new IllegalStateException("visit must be called only once");
        }
        this.start = true;
        this.checkState();
        CheckClassAdapter.checkAccess(n3, 423473);
        if (string == null || !string.endsWith("package-info")) {
            CheckMethodAdapter.checkInternalName(string, "class name");
        }
        if ("java/lang/Object".equals(string)) {
            if (string3 != null) {
                throw new IllegalArgumentException("The super class name of the Object class must be 'null'");
            }
        } else {
            CheckMethodAdapter.checkInternalName(string3, "super class name");
        }
        if (string2 != null) {
            CheckClassAdapter.checkClassSignature(string2);
        }
        if ((n3 & 0x200) != 0 && !"java/lang/Object".equals(string3)) {
            throw new IllegalArgumentException("The super class name of interfaces must be 'java/lang/Object'");
        }
        if (arrstring != null) {
            for (int i2 = 0; i2 < arrstring.length; ++i2) {
                CheckMethodAdapter.checkInternalName(arrstring[i2], "interface name at index " + i2);
            }
        }
        this.version = n2;
        super.visit(n2, n3, string, string2, string3, arrstring);
    }

    @Override
    public void visitSource(String string, String string2) {
        this.checkState();
        if (this.source) {
            throw new IllegalStateException("visitSource can be called only once.");
        }
        this.source = true;
        super.visitSource(string, string2);
    }

    @Override
    public void visitOuterClass(String string, String string2, String string3) {
        this.checkState();
        if (this.outer) {
            throw new IllegalStateException("visitOuterClass can be called only once.");
        }
        this.outer = true;
        if (string == null) {
            throw new IllegalArgumentException("Illegal outer class owner");
        }
        if (string3 != null) {
            CheckMethodAdapter.checkMethodDesc(string3);
        }
        super.visitOuterClass(string, string2, string3);
    }

    @Override
    public void visitInnerClass(String string, String string2, String string3, int n2) {
        this.checkState();
        CheckMethodAdapter.checkInternalName(string, "class name");
        if (string2 != null) {
            CheckMethodAdapter.checkInternalName(string2, "outer class name");
        }
        if (string3 != null) {
            int n3;
            for (n3 = 0; n3 < string3.length() && Character.isDigit(string3.charAt(n3)); ++n3) {
            }
            if (n3 == 0 || n3 < string3.length()) {
                CheckMethodAdapter.checkIdentifier(string3, n3, -1, "inner class name");
            }
        }
        CheckClassAdapter.checkAccess(n2, 30239);
        super.visitInnerClass(string, string2, string3, n2);
    }

    @Override
    public FieldVisitor visitField(int n2, String string, String string2, String string3, Object object) {
        this.checkState();
        CheckClassAdapter.checkAccess(n2, 413919);
        CheckMethodAdapter.checkUnqualifiedName(this.version, string, "field name");
        CheckMethodAdapter.checkDesc(string2, false);
        if (string3 != null) {
            CheckClassAdapter.checkFieldSignature(string3);
        }
        if (object != null) {
            CheckMethodAdapter.checkConstant(object);
        }
        FieldVisitor fieldVisitor = super.visitField(n2, string, string2, string3, object);
        return new CheckFieldAdapter(fieldVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int n2, String string, String string2, String string3, String[] arrstring) {
        this.checkState();
        CheckClassAdapter.checkAccess(n2, 400895);
        if (!"<init>".equals(string) && !"<clinit>".equals(string)) {
            CheckMethodAdapter.checkMethodIdentifier(this.version, string, "method name");
        }
        CheckMethodAdapter.checkMethodDesc(string2);
        if (string3 != null) {
            CheckClassAdapter.checkMethodSignature(string3);
        }
        if (arrstring != null) {
            for (int i2 = 0; i2 < arrstring.length; ++i2) {
                CheckMethodAdapter.checkInternalName(arrstring[i2], "exception name at index " + i2);
            }
        }
        CheckMethodAdapter checkMethodAdapter = this.checkDataFlow ? new CheckMethodAdapter(n2, string, string2, super.visitMethod(n2, string, string2, string3, arrstring), this.labels) : new CheckMethodAdapter(super.visitMethod(n2, string, string2, string3, arrstring), this.labels);
        checkMethodAdapter.version = this.version;
        return checkMethodAdapter;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String string, boolean bl) {
        this.checkState();
        CheckMethodAdapter.checkDesc(string, false);
        return new CheckAnnotationAdapter(super.visitAnnotation(string, bl));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int n2, TypePath typePath, String string, boolean bl) {
        this.checkState();
        int n3 = n2 >>> 24;
        if (n3 != 0 && n3 != 17 && n3 != 16) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(n3));
        }
        CheckClassAdapter.checkTypeRefAndPath(n2, typePath);
        CheckMethodAdapter.checkDesc(string, false);
        return new CheckAnnotationAdapter(super.visitTypeAnnotation(n2, typePath, string, bl));
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        this.checkState();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        super.visitAttribute(attribute);
    }

    @Override
    public void visitEnd() {
        this.checkState();
        this.end = true;
        super.visitEnd();
    }

    private void checkState() {
        if (!this.start) {
            throw new IllegalStateException("Cannot visit member before visit has been called.");
        }
        if (this.end) {
            throw new IllegalStateException("Cannot visit member after visitEnd has been called.");
        }
    }

    static void checkAccess(int n2, int n3) {
        int n4;
        int n5;
        if ((n2 & ~n3) != 0) {
            throw new IllegalArgumentException("Invalid access flags: " + n2);
        }
        int n6 = (n2 & 1) == 0 ? 0 : 1;
        int n7 = (n2 & 2) == 0 ? 0 : 1;
        int n8 = n5 = (n2 & 4) == 0 ? 0 : 1;
        if (n6 + n7 + n5 > 1) {
            throw new IllegalArgumentException("public private and protected are mutually exclusive: " + n2);
        }
        int n9 = (n2 & 0x10) == 0 ? 0 : 1;
        int n10 = n4 = (n2 & 0x400) == 0 ? 0 : 1;
        if (n9 + n4 > 1) {
            throw new IllegalArgumentException("final and abstract are mutually exclusive: " + n2);
        }
    }

    public static void checkClassSignature(String string) {
        int n2 = 0;
        if (CheckClassAdapter.getChar(string, 0) == '<') {
            n2 = CheckClassAdapter.checkFormalTypeParameters(string, n2);
        }
        n2 = CheckClassAdapter.checkClassTypeSignature(string, n2);
        while (CheckClassAdapter.getChar(string, n2) == 'L') {
            n2 = CheckClassAdapter.checkClassTypeSignature(string, n2);
        }
        if (n2 != string.length()) {
            throw new IllegalArgumentException(string + ": error at index " + n2);
        }
    }

    public static void checkMethodSignature(String string) {
        int n2 = 0;
        if (CheckClassAdapter.getChar(string, 0) == '<') {
            n2 = CheckClassAdapter.checkFormalTypeParameters(string, n2);
        }
        n2 = CheckClassAdapter.checkChar('(', string, n2);
        while ("ZCBSIFJDL[T".indexOf(CheckClassAdapter.getChar(string, n2)) != -1) {
            n2 = CheckClassAdapter.checkTypeSignature(string, n2);
        }
        n2 = CheckClassAdapter.getChar(string, n2 = CheckClassAdapter.checkChar(')', string, n2)) == 'V' ? ++n2 : CheckClassAdapter.checkTypeSignature(string, n2);
        while (CheckClassAdapter.getChar(string, n2) == '^') {
            if (CheckClassAdapter.getChar(string, ++n2) == 'L') {
                n2 = CheckClassAdapter.checkClassTypeSignature(string, n2);
                continue;
            }
            n2 = CheckClassAdapter.checkTypeVariableSignature(string, n2);
        }
        if (n2 != string.length()) {
            throw new IllegalArgumentException(string + ": error at index " + n2);
        }
    }

    public static void checkFieldSignature(String string) {
        int n2 = CheckClassAdapter.checkFieldTypeSignature(string, 0);
        if (n2 != string.length()) {
            throw new IllegalArgumentException(string + ": error at index " + n2);
        }
    }

    static void checkTypeRefAndPath(int n2, TypePath typePath) {
        int n3 = 0;
        switch (n2 >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                n3 = -65536;
                break;
            }
            case 19: 
            case 20: 
            case 21: 
            case 64: 
            case 65: 
            case 67: 
            case 68: 
            case 69: 
            case 70: {
                n3 = -16777216;
                break;
            }
            case 16: 
            case 17: 
            case 18: 
            case 23: 
            case 66: {
                n3 = -256;
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                n3 = -16776961;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(n2 >>> 24));
            }
        }
        if ((n2 & ~n3) != 0) {
            throw new IllegalArgumentException("Invalid type reference 0x" + Integer.toHexString(n2));
        }
        if (typePath != null) {
            for (int i2 = 0; i2 < typePath.getLength(); ++i2) {
                int n4 = typePath.getStep(i2);
                if (n4 != 0 && n4 != 1 && n4 != 3 && n4 != 2) {
                    throw new IllegalArgumentException("Invalid type path step " + i2 + " in " + typePath);
                }
                if (n4 == 3 || typePath.getStepArgument(i2) == 0) continue;
                throw new IllegalArgumentException("Invalid type path step argument for step " + i2 + " in " + typePath);
            }
        }
    }

    private static int checkFormalTypeParameters(String string, int n2) {
        n2 = CheckClassAdapter.checkChar('<', string, n2);
        n2 = CheckClassAdapter.checkFormalTypeParameter(string, n2);
        while (CheckClassAdapter.getChar(string, n2) != '>') {
            n2 = CheckClassAdapter.checkFormalTypeParameter(string, n2);
        }
        return n2 + 1;
    }

    private static int checkFormalTypeParameter(String string, int n2) {
        n2 = CheckClassAdapter.checkIdentifier(string, n2);
        if ("L[T".indexOf(CheckClassAdapter.getChar(string, n2 = CheckClassAdapter.checkChar(':', string, n2))) != -1) {
            n2 = CheckClassAdapter.checkFieldTypeSignature(string, n2);
        }
        while (CheckClassAdapter.getChar(string, n2) == ':') {
            n2 = CheckClassAdapter.checkFieldTypeSignature(string, n2 + 1);
        }
        return n2;
    }

    private static int checkFieldTypeSignature(String string, int n2) {
        switch (CheckClassAdapter.getChar(string, n2)) {
            case 'L': {
                return CheckClassAdapter.checkClassTypeSignature(string, n2);
            }
            case '[': {
                return CheckClassAdapter.checkTypeSignature(string, n2 + 1);
            }
        }
        return CheckClassAdapter.checkTypeVariableSignature(string, n2);
    }

    private static int checkClassTypeSignature(String string, int n2) {
        n2 = CheckClassAdapter.checkChar('L', string, n2);
        n2 = CheckClassAdapter.checkIdentifier(string, n2);
        while (CheckClassAdapter.getChar(string, n2) == '/') {
            n2 = CheckClassAdapter.checkIdentifier(string, n2 + 1);
        }
        if (CheckClassAdapter.getChar(string, n2) == '<') {
            n2 = CheckClassAdapter.checkTypeArguments(string, n2);
        }
        while (CheckClassAdapter.getChar(string, n2) == '.') {
            if (CheckClassAdapter.getChar(string, n2 = CheckClassAdapter.checkIdentifier(string, n2 + 1)) != '<') continue;
            n2 = CheckClassAdapter.checkTypeArguments(string, n2);
        }
        return CheckClassAdapter.checkChar(';', string, n2);
    }

    private static int checkTypeArguments(String string, int n2) {
        n2 = CheckClassAdapter.checkChar('<', string, n2);
        n2 = CheckClassAdapter.checkTypeArgument(string, n2);
        while (CheckClassAdapter.getChar(string, n2) != '>') {
            n2 = CheckClassAdapter.checkTypeArgument(string, n2);
        }
        return n2 + 1;
    }

    private static int checkTypeArgument(String string, int n2) {
        char c2 = CheckClassAdapter.getChar(string, n2);
        if (c2 == '*') {
            return n2 + 1;
        }
        if (c2 == '+' || c2 == '-') {
            ++n2;
        }
        return CheckClassAdapter.checkFieldTypeSignature(string, n2);
    }

    private static int checkTypeVariableSignature(String string, int n2) {
        n2 = CheckClassAdapter.checkChar('T', string, n2);
        n2 = CheckClassAdapter.checkIdentifier(string, n2);
        return CheckClassAdapter.checkChar(';', string, n2);
    }

    private static int checkTypeSignature(String string, int n2) {
        switch (CheckClassAdapter.getChar(string, n2)) {
            case 'B': 
            case 'C': 
            case 'D': 
            case 'F': 
            case 'I': 
            case 'J': 
            case 'S': 
            case 'Z': {
                return n2 + 1;
            }
        }
        return CheckClassAdapter.checkFieldTypeSignature(string, n2);
    }

    private static int checkIdentifier(String string, int n2) {
        if (!Character.isJavaIdentifierStart(CheckClassAdapter.getChar(string, n2))) {
            throw new IllegalArgumentException(string + ": identifier expected at index " + n2);
        }
        ++n2;
        while (Character.isJavaIdentifierPart(CheckClassAdapter.getChar(string, n2))) {
            ++n2;
        }
        return n2;
    }

    private static int checkChar(char c2, String string, int n2) {
        if (CheckClassAdapter.getChar(string, n2) == c2) {
            return n2 + 1;
        }
        throw new IllegalArgumentException(string + ": '" + c2 + "' expected at index " + n2);
    }

    private static char getChar(String string, int n2) {
        return n2 < string.length() ? string.charAt(n2) : (char)'\u0000';
    }
}

