/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util.perf;

import com.google.common.base.Joiner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import org.spongepowered.asm.util.PrettyPrinter;

public final class Profiler {
    public static final int ROOT = 1;
    public static final int FINE = 2;
    private final Map<String, Section> sections = new TreeMap<String, Section>();
    private final List<String> phases = new ArrayList<String>();
    private final Deque<Section> stack = new LinkedList<Section>();
    private boolean active;

    public Profiler() {
        this.phases.add("Initial");
    }

    public void setActive(boolean bl) {
        if (!this.active && bl || !bl) {
            this.reset();
        }
        this.active = bl;
    }

    public void reset() {
        for (Section section : this.sections.values()) {
            section.invalidate();
        }
        this.sections.clear();
        this.phases.clear();
        this.phases.add("Initial");
        this.stack.clear();
    }

    public Section get(String string) {
        Section section = this.sections.get(string);
        if (section == null) {
            section = this.active ? new LiveSection(string, this.phases.size() - 1) : new Section(string);
            this.sections.put(string, section);
        }
        return section;
    }

    private Section getSubSection(String string, String string2, Section section) {
        Section section2 = this.sections.get(string);
        if (section2 == null) {
            section2 = new SubSection(string, this.phases.size() - 1, string2, section);
            this.sections.put(string, section2);
        }
        return section2;
    }

    boolean isHead(Section section) {
        return this.stack.peek() == section;
    }

    public Section begin(String ... arrstring) {
        return this.begin(0, arrstring);
    }

    public Section begin(int n2, String ... arrstring) {
        return this.begin(n2, Joiner.on('.').join(arrstring));
    }

    public Section begin(String string) {
        return this.begin(0, string);
    }

    public Section begin(int n2, String string) {
        boolean bl = (n2 & 1) != 0;
        boolean bl2 = (n2 & 2) != 0;
        String string2 = string;
        Section section = this.stack.peek();
        if (section != null) {
            string2 = section.getName() + (bl ? " -> " : ".") + string2;
            if (section.isRoot() && !bl) {
                int n3 = section.getName().lastIndexOf(" -> ");
                string = (n3 > -1 ? section.getName().substring(n3 + 4) : section.getName()) + "." + string;
                bl = true;
            }
        }
        Section section2 = this.get(bl ? string : string2);
        if (bl && section != null && this.active) {
            section2 = this.getSubSection(string2, section.getName(), section2);
        }
        section2.setFine(bl2).setRoot(bl);
        this.stack.push(section2);
        return section2.start();
    }

    void end(Section section) {
        block5: {
            try {
                Section section2;
                Section section3 = section2 = this.stack.pop();
                while (section3 != section) {
                    if (section3 == null && this.active) {
                        if (section2 == null) {
                            throw new IllegalStateException("Attempted to pop " + section + " but the stack is empty");
                        }
                        throw new IllegalStateException("Attempted to pop " + section + " which was not in the stack, head was " + section2);
                    }
                    section3 = this.stack.pop();
                }
            }
            catch (NoSuchElementException noSuchElementException) {
                if (!this.active) break block5;
                throw new IllegalStateException("Attempted to pop " + section + " but the stack is empty");
            }
        }
    }

    public void mark(String string) {
        long l2 = 0L;
        for (Section section : this.sections.values()) {
            l2 += section.getTime();
        }
        if (l2 == 0L) {
            int n2 = this.phases.size();
            this.phases.set(n2 - 1, string);
            return;
        }
        this.phases.add(string);
        for (Section section : this.sections.values()) {
            section.mark();
        }
    }

    public Collection<Section> getSections() {
        return Collections.unmodifiableCollection(this.sections.values());
    }

    public PrettyPrinter printer(boolean bl, boolean bl2) {
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        int n2 = this.phases.size() + 4;
        int[] arrn = new int[]{0, 1, 2, n2 - 2, n2 - 1};
        Object[] arrobject = new Object[n2 * 2];
        int n3 = 0;
        int n4 = 0;
        while (n3 < n2) {
            arrobject[n4 + 1] = PrettyPrinter.Alignment.RIGHT;
            if (n3 == arrn[0]) {
                arrobject[n4] = (bl2 ? "" : "  ") + "Section";
                arrobject[n4 + 1] = PrettyPrinter.Alignment.LEFT;
            } else {
                arrobject[n4] = n3 == arrn[1] ? "    TOTAL" : (n3 == arrn[3] ? "    Count" : (n3 == arrn[4] ? "Avg. " : (n3 - arrn[2] < this.phases.size() ? this.phases.get(n3 - arrn[2]) : "")));
            }
            n4 = ++n3 * 2;
        }
        prettyPrinter.table(arrobject).th().hr().add();
        for (Section section : this.sections.values()) {
            if (section.isFine() && !bl || bl2 && section.getDelegate() != section) continue;
            this.printSectionRow(prettyPrinter, n2, arrn, section, bl2);
            if (!bl2) continue;
            for (Section section2 : this.sections.values()) {
                Section section3 = section2.getDelegate();
                if (section2.isFine() && !bl || section3 != section || section3 == section2) continue;
                this.printSectionRow(prettyPrinter, n2, arrn, section2, bl2);
            }
        }
        return prettyPrinter.add();
    }

    private void printSectionRow(PrettyPrinter prettyPrinter, int n2, int[] arrn, Section section, boolean bl) {
        long[] arrl;
        boolean bl2 = section.getDelegate() != section;
        Object[] arrobject = new Object[n2];
        int n3 = 1;
        arrobject[0] = bl ? (bl2 ? "  > " + section.getBaseName() : section.getName()) : (bl2 ? "+ " : "  ") + section.getName();
        for (long l2 : arrl = section.getTimes()) {
            if (n3 == arrn[1]) {
                arrobject[n3++] = section.getTotalTime() + " ms";
            }
            if (n3 < arrn[2] || n3 >= arrobject.length) continue;
            arrobject[n3++] = l2 + " ms";
        }
        arrobject[arrn[3]] = section.getTotalCount();
        arrobject[arrn[4]] = new DecimalFormat("   ###0.000 ms").format(section.getTotalAverageTime());
        for (int i2 = 0; i2 < arrobject.length; ++i2) {
            if (arrobject[i2] != null) continue;
            arrobject[i2] = "-";
        }
        prettyPrinter.tr(arrobject);
    }

    class SubSection
    extends LiveSection {
        private final String baseName;
        private final Section root;

        SubSection(String string, int n2, String string2, Section section) {
            super(string, n2);
            this.baseName = string2;
            this.root = section;
        }

        @Override
        Section invalidate() {
            this.root.invalidate();
            return super.invalidate();
        }

        @Override
        public String getBaseName() {
            return this.baseName;
        }

        @Override
        public void setInfo(String string) {
            this.root.setInfo(string);
            super.setInfo(string);
        }

        @Override
        Section getDelegate() {
            return this.root;
        }

        @Override
        Section start() {
            this.root.start();
            return super.start();
        }

        @Override
        public Section end() {
            this.root.stop();
            return super.end();
        }

        @Override
        public Section next(String string) {
            super.stop();
            return this.root.next(string);
        }
    }

    class LiveSection
    extends Section {
        private int cursor;
        private long[] times;
        private long start;
        private long time;
        private long markedTime;
        private int count;
        private int markedCount;

        LiveSection(String string, int n2) {
            super(string);
            this.cursor = 0;
            this.times = new long[0];
            this.start = 0L;
            this.cursor = n2;
        }

        @Override
        Section start() {
            this.start = System.currentTimeMillis();
            return this;
        }

        @Override
        protected Section stop() {
            if (this.start > 0L) {
                this.time += System.currentTimeMillis() - this.start;
            }
            this.start = 0L;
            ++this.count;
            return this;
        }

        @Override
        public Section end() {
            this.stop();
            if (!this.invalidated) {
                Profiler.this.end(this);
            }
            return this;
        }

        @Override
        void mark() {
            if (this.cursor >= this.times.length) {
                this.times = Arrays.copyOf(this.times, this.cursor + 4);
            }
            this.times[this.cursor] = this.time;
            this.markedTime += this.time;
            this.markedCount += this.count;
            this.time = 0L;
            this.count = 0;
            ++this.cursor;
        }

        @Override
        public long getTime() {
            return this.time;
        }

        @Override
        public long getTotalTime() {
            return this.time + this.markedTime;
        }

        @Override
        public double getSeconds() {
            return (double)this.time * 0.001;
        }

        @Override
        public double getTotalSeconds() {
            return (double)(this.time + this.markedTime) * 0.001;
        }

        @Override
        public long[] getTimes() {
            long[] arrl = new long[this.cursor + 1];
            System.arraycopy(this.times, 0, arrl, 0, Math.min(this.times.length, this.cursor));
            arrl[this.cursor] = this.time;
            return arrl;
        }

        @Override
        public int getCount() {
            return this.count;
        }

        @Override
        public int getTotalCount() {
            return this.count + this.markedCount;
        }

        @Override
        public double getAverageTime() {
            return this.count > 0 ? (double)this.time / (double)this.count : 0.0;
        }

        @Override
        public double getTotalAverageTime() {
            return this.count > 0 ? (double)(this.time + this.markedTime) / (double)(this.count + this.markedCount) : 0.0;
        }
    }

    public class Section {
        static final String SEPARATOR_ROOT = " -> ";
        static final String SEPARATOR_CHILD = ".";
        private final String name;
        private boolean root;
        private boolean fine;
        protected boolean invalidated;
        private String info;

        Section(String string) {
            this.name = string;
            this.info = string;
        }

        Section getDelegate() {
            return this;
        }

        Section invalidate() {
            this.invalidated = true;
            return this;
        }

        Section setRoot(boolean bl) {
            this.root = bl;
            return this;
        }

        public boolean isRoot() {
            return this.root;
        }

        Section setFine(boolean bl) {
            this.fine = bl;
            return this;
        }

        public boolean isFine() {
            return this.fine;
        }

        public String getName() {
            return this.name;
        }

        public String getBaseName() {
            return this.name;
        }

        public void setInfo(String string) {
            this.info = string;
        }

        public String getInfo() {
            return this.info;
        }

        Section start() {
            return this;
        }

        protected Section stop() {
            return this;
        }

        public Section end() {
            if (!this.invalidated) {
                Profiler.this.end(this);
            }
            return this;
        }

        public Section next(String string) {
            this.end();
            return Profiler.this.begin(string);
        }

        void mark() {
        }

        public long getTime() {
            return 0L;
        }

        public long getTotalTime() {
            return 0L;
        }

        public double getSeconds() {
            return 0.0;
        }

        public double getTotalSeconds() {
            return 0.0;
        }

        public long[] getTimes() {
            return new long[1];
        }

        public int getCount() {
            return 0;
        }

        public int getTotalCount() {
            return 0;
        }

        public double getAverageTime() {
            return 0.0;
        }

        public double getTotalAverageTime() {
            return 0.0;
        }

        public final String toString() {
            return this.name;
        }
    }
}

