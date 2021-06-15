/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.mixin.transformer.ext.extensions;

import com.google.common.base.Charsets;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.util.Constants;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class ExtensionCheckInterfaces
implements IExtension {
    private static final String AUDIT_DIR = "audit";
    private static final String IMPL_REPORT_FILENAME = "mixin_implementation_report";
    private static final String IMPL_REPORT_CSV_FILENAME = "mixin_implementation_report.csv";
    private static final String IMPL_REPORT_TXT_FILENAME = "mixin_implementation_report.txt";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final File csv;
    private final File report;
    private final Multimap<ClassInfo, ClassInfo.Method> interfaceMethods = HashMultimap.create();
    private boolean strict;

    public ExtensionCheckInterfaces() {
        File file = new File(Constants.DEBUG_OUTPUT_DIR, AUDIT_DIR);
        file.mkdirs();
        this.csv = new File(file, IMPL_REPORT_CSV_FILENAME);
        this.report = new File(file, IMPL_REPORT_TXT_FILENAME);
        try {
            Files.write("Class,Method,Signature,Interface\n", this.csv, Charsets.ISO_8859_1);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        try {
            String string = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Files.write("Mixin Implementation Report generated on " + string + "\n", this.report, Charsets.ISO_8859_1);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public boolean checkActive(MixinEnvironment mixinEnvironment) {
        this.strict = mixinEnvironment.getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS_STRICT);
        return mixinEnvironment.getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS);
    }

    @Override
    public void preApply(ITargetClassContext iTargetClassContext) {
        ClassInfo classInfo = iTargetClassContext.getClassInfo();
        for (ClassInfo.Method method : classInfo.getInterfaceMethods(false)) {
            this.interfaceMethods.put(classInfo, method);
        }
    }

    @Override
    public void postApply(ITargetClassContext iTargetClassContext) {
        ClassInfo classInfo = iTargetClassContext.getClassInfo();
        if (classInfo.isAbstract() && !this.strict) {
            logger.info("{} is skipping abstract target {}", new Object[]{this.getClass().getSimpleName(), iTargetClassContext});
            return;
        }
        String string = classInfo.getName().replace('/', '.');
        int n2 = 0;
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        prettyPrinter.add("Class: %s", string).hr();
        prettyPrinter.add("%-32s %-47s  %s", "Return Type", "Missing Method", "From Interface").hr();
        Set<ClassInfo.Method> set = classInfo.getInterfaceMethods(true);
        HashSet<ClassInfo.Method> hashSet = new HashSet<ClassInfo.Method>(classInfo.getSuperClass().getInterfaceMethods(true));
        hashSet.addAll(this.interfaceMethods.removeAll(classInfo));
        for (ClassInfo.Method method : set) {
            ClassInfo.Method method2 = classInfo.findMethodInHierarchy(method.getName(), method.getDesc(), ClassInfo.SearchType.ALL_CLASSES, ClassInfo.Traversal.ALL);
            if (method2 != null && !method2.isAbstract() || hashSet.contains(method)) continue;
            if (n2 > 0) {
                prettyPrinter.add();
            }
            SignaturePrinter signaturePrinter = new SignaturePrinter(method.getName(), method.getDesc()).setModifiers("");
            String string2 = method.getOwner().getName().replace('/', '.');
            ++n2;
            prettyPrinter.add("%-32s%s", signaturePrinter.getReturnType(), signaturePrinter);
            prettyPrinter.add("%-80s  %s", "", string2);
            this.appendToCSVReport(string, method, string2);
        }
        if (n2 > 0) {
            prettyPrinter.hr().add("%82s%s: %d", "", "Total unimplemented", n2);
            prettyPrinter.print(System.err);
            this.appendToTextReport(prettyPrinter);
        }
    }

    @Override
    public void export(MixinEnvironment mixinEnvironment, String string, boolean bl, byte[] arrby) {
    }

    private void appendToCSVReport(String string, ClassInfo.Method method, String string2) {
        try {
            Files.append(String.format("%s,%s,%s,%s\n", string, method.getName(), method.getDesc(), string2), this.csv, Charsets.ISO_8859_1);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void appendToTextReport(PrettyPrinter prettyPrinter) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(this.report, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print("\n");
            prettyPrinter.print(printStream);
        }
        catch (Exception exception) {
            IOUtils.closeQuietly(fileOutputStream);
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(fileOutputStream);
            throw throwable;
        }
        IOUtils.closeQuietly((OutputStream)fileOutputStream);
    }
}

