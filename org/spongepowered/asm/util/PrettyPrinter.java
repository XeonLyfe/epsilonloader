/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.util;

import com.google.common.base.Strings;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrettyPrinter {
    private final HorizontalRule horizontalRule = new HorizontalRule('*');
    private final List<Object> lines = new ArrayList<Object>();
    private Table table;
    private boolean recalcWidth = false;
    protected int width = 100;
    protected int wrapWidth = 80;
    protected int kvKeyWidth = 10;
    protected String kvFormat = PrettyPrinter.makeKvFormat(this.kvKeyWidth);

    public PrettyPrinter() {
        this(100);
    }

    public PrettyPrinter(int n2) {
        this.width = n2;
    }

    public PrettyPrinter wrapTo(int n2) {
        this.wrapWidth = n2;
        return this;
    }

    public int wrapTo() {
        return this.wrapWidth;
    }

    public PrettyPrinter table() {
        this.table = new Table();
        return this;
    }

    public PrettyPrinter table(String ... arrstring) {
        this.table = new Table();
        for (String string : arrstring) {
            this.table.addColumn(string);
        }
        return this;
    }

    public PrettyPrinter table(Object ... arrobject) {
        this.table = new Table();
        Column column = null;
        for (Object object : arrobject) {
            if (object instanceof String) {
                column = this.table.addColumn((String)object);
                continue;
            }
            if (object instanceof Integer && column != null) {
                int n2 = (Integer)object;
                if (n2 > 0) {
                    column.setWidth(n2);
                    continue;
                }
                if (n2 >= 0) continue;
                column.setMaxWidth(-n2);
                continue;
            }
            if (object instanceof Alignment && column != null) {
                column.setAlignment((Alignment)((Object)object));
                continue;
            }
            if (object == null) continue;
            column = this.table.addColumn(object.toString());
        }
        return this;
    }

    public PrettyPrinter spacing(int n2) {
        if (this.table == null) {
            this.table = new Table();
        }
        this.table.setColSpacing(n2);
        return this;
    }

    public PrettyPrinter th() {
        return this.th(false);
    }

    private PrettyPrinter th(boolean bl) {
        if (this.table == null) {
            this.table = new Table();
        }
        if (!bl || this.table.addHeader) {
            this.table.headerAdded();
            this.addLine(this.table);
        }
        return this;
    }

    public PrettyPrinter tr(Object ... arrobject) {
        this.th(true);
        this.addLine(this.table.addRow(arrobject));
        this.recalcWidth = true;
        return this;
    }

    public PrettyPrinter add() {
        this.addLine("");
        return this;
    }

    public PrettyPrinter add(String string) {
        this.addLine(string);
        this.width = Math.max(this.width, string.length());
        return this;
    }

    public PrettyPrinter add(String string, Object ... arrobject) {
        String string2 = String.format(string, arrobject);
        this.addLine(string2);
        this.width = Math.max(this.width, string2.length());
        return this;
    }

    public PrettyPrinter add(Object[] arrobject) {
        return this.add(arrobject, "%s");
    }

    public PrettyPrinter add(Object[] arrobject, String string) {
        for (Object object : arrobject) {
            this.add(string, object);
        }
        return this;
    }

    public PrettyPrinter addIndexed(Object[] arrobject) {
        int n2 = String.valueOf(arrobject.length - 1).length();
        String string = "[%" + n2 + "d] %s";
        for (int i2 = 0; i2 < arrobject.length; ++i2) {
            this.add(string, i2, arrobject[i2]);
        }
        return this;
    }

    public PrettyPrinter addWithIndices(Collection<?> collection) {
        return this.addIndexed(collection.toArray());
    }

    public PrettyPrinter add(IPrettyPrintable iPrettyPrintable) {
        if (iPrettyPrintable != null) {
            iPrettyPrintable.print(this);
        }
        return this;
    }

    public PrettyPrinter add(Throwable throwable) {
        return this.add(throwable, 4);
    }

    public PrettyPrinter add(Throwable throwable, int n2) {
        while (throwable != null) {
            this.add("%s: %s", throwable.getClass().getName(), throwable.getMessage());
            this.add(throwable.getStackTrace(), n2);
            throwable = throwable.getCause();
        }
        return this;
    }

    public PrettyPrinter add(StackTraceElement[] arrstackTraceElement, int n2) {
        String string = Strings.repeat(" ", n2);
        for (StackTraceElement stackTraceElement : arrstackTraceElement) {
            this.add("%s%s", string, stackTraceElement);
        }
        return this;
    }

    public PrettyPrinter add(Object object) {
        return this.add(object, 0);
    }

    public PrettyPrinter add(Object object, int n2) {
        String string = Strings.repeat(" ", n2);
        return this.append(object, n2, string);
    }

    private PrettyPrinter append(Object object, int n2, String string) {
        if (object instanceof String) {
            return this.add("%s%s", string, object);
        }
        if (object instanceof Iterable) {
            for (Object t2 : (Iterable)object) {
                this.append(t2, n2, string);
            }
            return this;
        }
        if (object instanceof Map) {
            this.kvWidth(n2);
            return this.add((Map)object);
        }
        if (object instanceof IPrettyPrintable) {
            return this.add((IPrettyPrintable)object);
        }
        if (object instanceof Throwable) {
            return this.add((Throwable)object, n2);
        }
        if (object.getClass().isArray()) {
            return this.add((Object[])object, n2 + "%s");
        }
        return this.add("%s%s", string, object);
    }

    public PrettyPrinter addWrapped(String string, Object ... arrobject) {
        return this.addWrapped(this.wrapWidth, string, arrobject);
    }

    public PrettyPrinter addWrapped(int n2, String string, Object ... arrobject) {
        String string2 = "";
        String string3 = String.format(string, arrobject).replace("\t", "    ");
        Matcher matcher = Pattern.compile("^(\\s+)(.*)$").matcher(string3);
        if (matcher.matches()) {
            string2 = matcher.group(1);
        }
        try {
            for (String string4 : this.getWrapped(n2, string3, string2)) {
                this.addLine(string4);
            }
        }
        catch (Exception exception) {
            this.add(string3);
        }
        return this;
    }

    private List<String> getWrapped(int n2, String string, String string2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        while (string.length() > n2) {
            int n3 = string.lastIndexOf(32, n2);
            if (n3 < 10) {
                n3 = n2;
            }
            String string3 = string.substring(0, n3);
            arrayList.add(string3);
            string = string2 + string.substring(n3 + 1);
        }
        if (string.length() > 0) {
            arrayList.add(string);
        }
        return arrayList;
    }

    public PrettyPrinter kv(String string, String string2, Object ... arrobject) {
        return this.kv(string, String.format(string2, arrobject));
    }

    public PrettyPrinter kv(String string, Object object) {
        this.addLine(new KeyValue(string, object));
        return this.kvWidth(string.length());
    }

    public PrettyPrinter kvWidth(int n2) {
        if (n2 > this.kvKeyWidth) {
            this.kvKeyWidth = n2;
            this.kvFormat = PrettyPrinter.makeKvFormat(n2);
        }
        this.recalcWidth = true;
        return this;
    }

    public PrettyPrinter add(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String string = entry.getKey() == null ? "null" : entry.getKey().toString();
            this.kv(string, entry.getValue());
        }
        return this;
    }

    public PrettyPrinter hr() {
        return this.hr('*');
    }

    public PrettyPrinter hr(char c2) {
        this.addLine(new HorizontalRule(c2));
        return this;
    }

    public PrettyPrinter centre() {
        Object object;
        if (!this.lines.isEmpty() && (object = this.lines.get(this.lines.size() - 1)) instanceof String) {
            this.addLine(new CentredText(this.lines.remove(this.lines.size() - 1)));
        }
        return this;
    }

    private void addLine(Object object) {
        if (object == null) {
            return;
        }
        this.lines.add(object);
        this.recalcWidth |= object instanceof IVariableWidthEntry;
    }

    public PrettyPrinter trace() {
        return this.trace(PrettyPrinter.getDefaultLoggerName());
    }

    public PrettyPrinter trace(Level level) {
        return this.trace(PrettyPrinter.getDefaultLoggerName(), level);
    }

    public PrettyPrinter trace(String string) {
        return this.trace(System.err, LogManager.getLogger((String)string));
    }

    public PrettyPrinter trace(String string, Level level) {
        return this.trace(System.err, LogManager.getLogger((String)string), level);
    }

    public PrettyPrinter trace(Logger logger) {
        return this.trace(System.err, logger);
    }

    public PrettyPrinter trace(Logger logger, Level level) {
        return this.trace(System.err, logger, level);
    }

    public PrettyPrinter trace(PrintStream printStream) {
        return this.trace(printStream, PrettyPrinter.getDefaultLoggerName());
    }

    public PrettyPrinter trace(PrintStream printStream, Level level) {
        return this.trace(printStream, PrettyPrinter.getDefaultLoggerName(), level);
    }

    public PrettyPrinter trace(PrintStream printStream, String string) {
        return this.trace(printStream, LogManager.getLogger((String)string));
    }

    public PrettyPrinter trace(PrintStream printStream, String string, Level level) {
        return this.trace(printStream, LogManager.getLogger((String)string), level);
    }

    public PrettyPrinter trace(PrintStream printStream, Logger logger) {
        return this.trace(printStream, logger, Level.DEBUG);
    }

    public PrettyPrinter trace(PrintStream printStream, Logger logger, Level level) {
        this.log(logger, level);
        this.print(printStream);
        return this;
    }

    public PrettyPrinter print() {
        return this.print(System.err);
    }

    public PrettyPrinter print(PrintStream printStream) {
        this.updateWidth();
        this.printSpecial(printStream, this.horizontalRule);
        for (Object object : this.lines) {
            if (object instanceof ISpecialEntry) {
                this.printSpecial(printStream, (ISpecialEntry)object);
                continue;
            }
            this.printString(printStream, object.toString());
        }
        this.printSpecial(printStream, this.horizontalRule);
        return this;
    }

    private void printSpecial(PrintStream printStream, ISpecialEntry iSpecialEntry) {
        printStream.printf("/*%s*/\n", iSpecialEntry.toString());
    }

    private void printString(PrintStream printStream, String string) {
        if (string != null) {
            printStream.printf("/* %-" + this.width + "s */\n", string);
        }
    }

    public PrettyPrinter log(Logger logger) {
        return this.log(logger, Level.INFO);
    }

    public PrettyPrinter log(Logger logger, Level level) {
        this.updateWidth();
        this.logSpecial(logger, level, this.horizontalRule);
        for (Object object : this.lines) {
            if (object instanceof ISpecialEntry) {
                this.logSpecial(logger, level, (ISpecialEntry)object);
                continue;
            }
            this.logString(logger, level, object.toString());
        }
        this.logSpecial(logger, level, this.horizontalRule);
        return this;
    }

    private void logSpecial(Logger logger, Level level, ISpecialEntry iSpecialEntry) {
        logger.log(level, "/*{}*/", new Object[]{iSpecialEntry.toString()});
    }

    private void logString(Logger logger, Level level, String string) {
        if (string != null) {
            logger.log(level, String.format("/* %-" + this.width + "s */", string));
        }
    }

    private void updateWidth() {
        if (this.recalcWidth) {
            this.recalcWidth = false;
            for (Object object : this.lines) {
                if (!(object instanceof IVariableWidthEntry)) continue;
                this.width = Math.min(4096, Math.max(this.width, ((IVariableWidthEntry)object).getWidth()));
            }
        }
    }

    private static String makeKvFormat(int n2) {
        return String.format("%%%ds : %%s", n2);
    }

    private static String getDefaultLoggerName() {
        String string = new Throwable().getStackTrace()[2].getClassName();
        int n2 = string.lastIndexOf(46);
        return n2 == -1 ? string : string.substring(n2 + 1);
    }

    public static void dumpStack() {
        new PrettyPrinter().add(new Exception("Stack trace")).print(System.err);
    }

    public static void print(Throwable throwable) {
        new PrettyPrinter().add(throwable).print(System.err);
    }

    static class Row
    implements IVariableWidthEntry {
        final Table table;
        final String[] args;

        public Row(Table table, Object ... arrobject) {
            this.table = table.grow(arrobject.length);
            this.args = new String[arrobject.length];
            for (int i2 = 0; i2 < arrobject.length; ++i2) {
                this.args[i2] = arrobject[i2].toString();
                this.table.columns.get(i2).setMinWidth(this.args[i2].length());
            }
        }

        public String toString() {
            Object[] arrobject = new Object[this.table.columns.size()];
            for (int i2 = 0; i2 < arrobject.length; ++i2) {
                Column column = this.table.columns.get(i2);
                arrobject[i2] = i2 >= this.args.length ? "" : (this.args[i2].length() > column.getMaxWidth() ? this.args[i2].substring(0, column.getMaxWidth()) : this.args[i2]);
            }
            return String.format(this.table.format, arrobject);
        }

        @Override
        public int getWidth() {
            return this.toString().length();
        }
    }

    static class Column {
        private final Table table;
        private Alignment align = Alignment.LEFT;
        private int minWidth = 1;
        private int maxWidth = Integer.MAX_VALUE;
        private int size = 0;
        private String title = "";
        private String format = "%s";

        Column(Table table) {
            this.table = table;
        }

        Column(Table table, String string) {
            this(table);
            this.title = string;
            this.minWidth = string.length();
            this.updateFormat();
        }

        Column(Table table, Alignment alignment, int n2, String string) {
            this(table, string);
            this.align = alignment;
            this.size = n2;
        }

        void setAlignment(Alignment alignment) {
            this.align = alignment;
            this.updateFormat();
        }

        void setWidth(int n2) {
            if (n2 > this.size) {
                this.size = n2;
                this.updateFormat();
            }
        }

        void setMinWidth(int n2) {
            if (n2 > this.minWidth) {
                this.minWidth = n2;
                this.updateFormat();
            }
        }

        void setMaxWidth(int n2) {
            this.size = Math.min(this.size, this.maxWidth);
            this.maxWidth = Math.max(1, n2);
            this.updateFormat();
        }

        void setTitle(String string) {
            this.title = string;
            this.setWidth(string.length());
        }

        private void updateFormat() {
            int n2 = Math.min(this.maxWidth, this.size == 0 ? this.minWidth : this.size);
            this.format = "%" + (this.align == Alignment.RIGHT ? "" : "-") + n2 + "s";
            this.table.updateFormat();
        }

        int getMaxWidth() {
            return this.maxWidth;
        }

        String getTitle() {
            return this.title;
        }

        String getFormat() {
            return this.format;
        }

        public String toString() {
            if (this.title.length() > this.maxWidth) {
                return this.title.substring(0, this.maxWidth);
            }
            return this.title;
        }
    }

    static class Table
    implements IVariableWidthEntry {
        final List<Column> columns = new ArrayList<Column>();
        final List<Row> rows = new ArrayList<Row>();
        String format = "%s";
        int colSpacing = 2;
        boolean addHeader = true;

        Table() {
        }

        void headerAdded() {
            this.addHeader = false;
        }

        void setColSpacing(int n2) {
            this.colSpacing = Math.max(0, n2);
            this.updateFormat();
        }

        Table grow(int n2) {
            while (this.columns.size() < n2) {
                this.columns.add(new Column(this));
            }
            this.updateFormat();
            return this;
        }

        Column add(Column column) {
            this.columns.add(column);
            return column;
        }

        Row add(Row row) {
            this.rows.add(row);
            return row;
        }

        Column addColumn(String string) {
            return this.add(new Column(this, string));
        }

        Column addColumn(Alignment alignment, int n2, String string) {
            return this.add(new Column(this, alignment, n2, string));
        }

        Row addRow(Object ... arrobject) {
            return this.add(new Row(this, arrobject));
        }

        void updateFormat() {
            String string = Strings.repeat(" ", this.colSpacing);
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = false;
            for (Column column : this.columns) {
                if (bl) {
                    stringBuilder.append(string);
                }
                bl = true;
                stringBuilder.append(column.getFormat());
            }
            this.format = stringBuilder.toString();
        }

        String getFormat() {
            return this.format;
        }

        Object[] getTitles() {
            ArrayList<String> arrayList = new ArrayList<String>();
            for (Column column : this.columns) {
                arrayList.add(column.getTitle());
            }
            return arrayList.toArray();
        }

        public String toString() {
            boolean bl = false;
            String[] arrstring = new String[this.columns.size()];
            for (int i2 = 0; i2 < this.columns.size(); ++i2) {
                arrstring[i2] = this.columns.get(i2).toString();
                bl |= !arrstring[i2].isEmpty();
            }
            return bl ? String.format(this.format, arrstring) : null;
        }

        @Override
        public int getWidth() {
            String string = this.toString();
            return string != null ? string.length() : 0;
        }
    }

    public static enum Alignment {
        LEFT,
        RIGHT;

    }

    class CentredText {
        private final Object centred;

        public CentredText(Object object) {
            this.centred = object;
        }

        public String toString() {
            String string = this.centred.toString();
            return String.format("%" + ((PrettyPrinter.this.width - string.length()) / 2 + string.length()) + "s", string);
        }
    }

    class HorizontalRule
    implements ISpecialEntry {
        private final char[] hrChars;

        public HorizontalRule(char ... arrc) {
            this.hrChars = arrc;
        }

        public String toString() {
            return Strings.repeat(new String(this.hrChars), PrettyPrinter.this.width + 2);
        }
    }

    class KeyValue
    implements IVariableWidthEntry {
        private final String key;
        private final Object value;

        public KeyValue(String string, Object object) {
            this.key = string;
            this.value = object;
        }

        public String toString() {
            return String.format(PrettyPrinter.this.kvFormat, this.key, this.value);
        }

        @Override
        public int getWidth() {
            return this.toString().length();
        }
    }

    static interface ISpecialEntry {
    }

    static interface IVariableWidthEntry {
        public int getWidth();
    }

    public static interface IPrettyPrintable {
        public void print(PrettyPrinter var1);
    }
}

