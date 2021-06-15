/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionNumber
implements Comparable<VersionNumber>,
Serializable {
    private static final long serialVersionUID = 1L;
    public static final VersionNumber NONE = new VersionNumber();
    private static final Pattern PATTERN = Pattern.compile("^(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5}))?)?)?(-[a-zA-Z0-9_\\-]+)?$");
    private final long value;
    private final String suffix;

    private VersionNumber() {
        this.value = 0L;
        this.suffix = "";
    }

    private VersionNumber(short[] arrs) {
        this(arrs, null);
    }

    private VersionNumber(short[] arrs, String string) {
        this.value = VersionNumber.pack(arrs);
        this.suffix = string != null ? string : "";
    }

    private VersionNumber(short s2, short s3, short s4, short s5) {
        this(s2, s3, s4, s5, null);
    }

    private VersionNumber(short s2, short s3, short s4, short s5, String string) {
        this.value = VersionNumber.pack(s2, s3, s4, s5);
        this.suffix = string != null ? string : "";
    }

    public String toString() {
        short[] arrs = VersionNumber.unpack(this.value);
        Object[] arrobject = new Object[5];
        arrobject[0] = arrs[0];
        arrobject[1] = arrs[1];
        Object object = (this.value & Integer.MAX_VALUE) > 0L ? String.format(".%d", arrs[2]) : (arrobject[2] = "");
        arrobject[3] = (this.value & 0x7FFFL) > 0L ? String.format(".%d", arrs[3]) : "";
        arrobject[4] = this.suffix;
        return String.format("%d.%d%3$s%4$s%5$s", arrobject);
    }

    @Override
    public int compareTo(VersionNumber versionNumber) {
        if (versionNumber == null) {
            return 1;
        }
        long l2 = this.value - versionNumber.value;
        return l2 > 0L ? 1 : (l2 < 0L ? -1 : 0);
    }

    public boolean equals(Object object) {
        if (!(object instanceof VersionNumber)) {
            return false;
        }
        return ((VersionNumber)object).value == this.value;
    }

    public int hashCode() {
        return (int)(this.value >> 32) ^ (int)(this.value & 0xFFFFFFFFL);
    }

    private static long pack(short ... arrs) {
        return (long)arrs[0] << 48 | (long)arrs[1] << 32 | (long)(arrs[2] << 16) | (long)arrs[3];
    }

    private static short[] unpack(long l2) {
        return new short[]{(short)(l2 >> 48), (short)(l2 >> 32 & 0x7FFFL), (short)(l2 >> 16 & 0x7FFFL), (short)(l2 & 0x7FFFL)};
    }

    public static VersionNumber parse(String string) {
        return VersionNumber.parse(string, NONE);
    }

    public static VersionNumber parse(String string, String string2) {
        return VersionNumber.parse(string, VersionNumber.parse(string2));
    }

    private static VersionNumber parse(String string, VersionNumber versionNumber) {
        if (string == null) {
            return versionNumber;
        }
        Matcher matcher = PATTERN.matcher(string);
        if (!matcher.matches()) {
            return versionNumber;
        }
        short[] arrs = new short[4];
        for (int i2 = 0; i2 < 4; ++i2) {
            String string2 = matcher.group(i2 + 1);
            if (string2 == null) continue;
            int n2 = Integer.parseInt(string2);
            if (n2 > 32767) {
                throw new IllegalArgumentException("Version parts cannot exceed 32767, found " + n2);
            }
            arrs[i2] = (short)n2;
        }
        return new VersionNumber(arrs, matcher.group(5));
    }
}

