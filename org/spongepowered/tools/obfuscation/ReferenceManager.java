/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationEnvironment;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IReferenceManager;

public class ReferenceManager
implements IReferenceManager {
    private final IMixinAnnotationProcessor ap;
    private final String outRefMapFileName;
    private final List<ObfuscationEnvironment> environments;
    private final ReferenceMapper refMapper = new ReferenceMapper();
    private boolean allowConflicts;

    public ReferenceManager(IMixinAnnotationProcessor iMixinAnnotationProcessor, List<ObfuscationEnvironment> list) {
        this.ap = iMixinAnnotationProcessor;
        this.environments = list;
        this.outRefMapFileName = this.ap.getOption("outRefMapFile");
    }

    @Override
    public boolean getAllowConflicts() {
        return this.allowConflicts;
    }

    @Override
    public void setAllowConflicts(boolean bl) {
        this.allowConflicts = bl;
    }

    @Override
    public void write() {
        if (this.outRefMapFileName == null) {
            return;
        }
        PrintWriter printWriter = null;
        try {
            printWriter = this.newWriter(this.outRefMapFileName, "refmap");
            this.refMapper.write(printWriter);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                }
                catch (Exception exception) {}
            }
        }
    }

    private PrintWriter newWriter(String string, String string2) throws IOException {
        if (string.matches("^.*[\\\\/:].*$")) {
            File file = new File(string);
            file.getParentFile().mkdirs();
            this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + string2 + " to " + file.getAbsolutePath());
            return new PrintWriter(file);
        }
        FileObject fileObject = this.ap.getProcessingEnvironment().getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", string, new Element[0]);
        this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + string2 + " to " + new File(fileObject.toUri()).getAbsolutePath());
        return new PrintWriter(fileObject.openWriter());
    }

    @Override
    public ReferenceMapper getMapper() {
        return this.refMapper;
    }

    @Override
    public void addMethodMapping(String string, String string2, ObfuscationData<MappingMethod> obfuscationData) {
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingMethod mappingMethod = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod == null) continue;
            MemberInfo memberInfo = new MemberInfo(mappingMethod);
            this.addMapping(obfuscationEnvironment.getType(), string, string2, memberInfo.toString());
        }
    }

    @Override
    public void addMethodMapping(String string, String string2, MemberInfo memberInfo, ObfuscationData<MappingMethod> obfuscationData) {
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingMethod mappingMethod = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod == null) continue;
            MemberInfo memberInfo2 = memberInfo.remapUsing(mappingMethod, true);
            this.addMapping(obfuscationEnvironment.getType(), string, string2, memberInfo2.toString());
        }
    }

    @Override
    public void addFieldMapping(String string, String string2, MemberInfo memberInfo, ObfuscationData<MappingField> obfuscationData) {
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingField mappingField = obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingField == null) continue;
            MemberInfo memberInfo2 = MemberInfo.fromMapping(mappingField.transform(obfuscationEnvironment.remapDescriptor(memberInfo.desc)));
            this.addMapping(obfuscationEnvironment.getType(), string, string2, memberInfo2.toString());
        }
    }

    @Override
    public void addClassMapping(String string, String string2, ObfuscationData<String> obfuscationData) {
        for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            String string3 = obfuscationData.get(obfuscationEnvironment.getType());
            if (string3 == null) continue;
            this.addMapping(obfuscationEnvironment.getType(), string, string2, string3);
        }
    }

    protected void addMapping(ObfuscationType obfuscationType, String string, String string2, String string3) {
        String string4 = this.refMapper.addMapping(obfuscationType.getKey(), string, string2, string3);
        if (obfuscationType.isDefault()) {
            this.refMapper.addMapping(null, string, string2, string3);
        }
        if (!this.allowConflicts && string4 != null && !string4.equals(string3)) {
            throw new ReferenceConflictException(string4, string3);
        }
    }

    public static class ReferenceConflictException
    extends RuntimeException {
        private static final long serialVersionUID = 1L;
        private final String oldReference;
        private final String newReference;

        public ReferenceConflictException(String string, String string2) {
            this.oldReference = string;
            this.newReference = string2;
        }

        public String getOld() {
            return this.oldReference;
        }

        public String getNew() {
            return this.newReference;
        }
    }
}

