/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.tools.obfuscation.mapping.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.spongepowered.tools.obfuscation.mapping.IMappingWriter;

public abstract class MappingWriter
implements IMappingWriter {
    private final Messager messager;
    private final Filer filer;

    public MappingWriter(Messager messager, Filer filer) {
        this.messager = messager;
        this.filer = filer;
    }

    protected PrintWriter openFileWriter(String string, String string2) throws IOException {
        if (string.matches("^.*[\\\\/:].*$")) {
            File file = new File(string);
            file.getParentFile().mkdirs();
            this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + string2 + " to " + file.getAbsolutePath());
            return new PrintWriter(file);
        }
        FileObject fileObject = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", string, new Element[0]);
        this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + string2 + " to " + new File(fileObject.toUri()).getAbsolutePath());
        return new PrintWriter(fileObject.openWriter());
    }
}

