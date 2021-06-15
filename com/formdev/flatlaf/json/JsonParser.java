/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.JsonHandler;
import com.formdev.flatlaf.json.Location;
import com.formdev.flatlaf.json.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

class JsonParser {
    private static final int MAX_NESTING_LEVEL = 1000;
    private static final int MIN_BUFFER_SIZE = 10;
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private final JsonHandler<Object, Object> handler;
    private Reader reader;
    private char[] buffer;
    private int bufferOffset;
    private int index;
    private int fill;
    private int line;
    private int lineOffset;
    private int current;
    private StringBuilder captureBuffer;
    private int captureStart;
    private int nestingLevel;

    public JsonParser(JsonHandler<?, ?> jsonHandler) {
        if (jsonHandler == null) {
            throw new NullPointerException("handler is null");
        }
        this.handler = jsonHandler;
        jsonHandler.parser = this;
    }

    public void parse(String string) {
        if (string == null) {
            throw new NullPointerException("string is null");
        }
        int n2 = Math.max((int)((long)1314917523 ^ (long)1314917526) << 1, Math.min(((int)-1215684341L ^ 0xB78A210A) << 10, string.length()));
        try {
            this.parse(new StringReader(string), n2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void parse(Reader reader) throws IOException {
        this.parse(reader, (int)((long)-255994588 ^ (long)-255994587) << 10);
    }

    public void parse(Reader reader, int n2) throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader is null");
        }
        if (n2 <= 0) {
            throw new IllegalArgumentException("buffersize is zero or negative");
        }
        this.reader = reader;
        this.buffer = new char[n2];
        this.bufferOffset = (int)-86499362L ^ 0xFAD81FDE;
        this.index = (int)((long)-34313368 ^ (long)-34313368);
        this.fill = (int)-410864097L ^ 0xE782B61F;
        this.line = (int)((long)633562102 ^ (long)633562103);
        this.lineOffset = (int)((long)-80249815 ^ (long)-80249815);
        this.current = (int)((long)-334433993 ^ (long)-334433993);
        this.captureStart = (int)-252147880L ^ 0xF0778A7;
        this.read();
        this.skipWhiteSpace();
        this.readValue();
        this.skipWhiteSpace();
        if (!this.isEndOfText()) {
            throw this.error("Unexpected character");
        }
    }

    private void readValue() throws IOException {
        switch (this.current) {
            case 110: {
                this.readNull();
                break;
            }
            case 116: {
                this.readTrue();
                break;
            }
            case 102: {
                this.readFalse();
                break;
            }
            case 34: {
                this.readString();
                break;
            }
            case 91: {
                this.readArray();
                break;
            }
            case 123: {
                this.readObject();
                break;
            }
            case 45: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                this.readNumber();
                break;
            }
            default: {
                throw this.expected("value");
            }
        }
    }

    private void readArray() throws IOException {
        Object object = this.handler.startArray();
        this.read();
        if ((this.nestingLevel += (int)-2044677922L ^ 0x8620B0DF) > (int)((long)578841947 ^ (long)578841894) << 3) {
            throw this.error("Nesting too deep");
        }
        this.skipWhiteSpace();
        if (this.readChar((char)((int)2102270681L ^ 0x7D4E1A84))) {
            this.nestingLevel -= (int)((long)-1975068633 ^ (long)-1975068634);
            this.handler.endArray(object);
            return;
        }
        do {
            this.skipWhiteSpace();
            this.handler.startArrayValue(object);
            this.readValue();
            this.handler.endArrayValue(object);
            this.skipWhiteSpace();
        } while (this.readChar((int)((long)1246664474 ^ (long)1246664465) << 2));
        if (!this.readChar((char)((int)1470731144L ^ 0x57A993D5))) {
            throw this.expected("',' or ']'");
        }
        this.nestingLevel -= (int)31285700L ^ 0x1DD61C5;
        this.handler.endArray(object);
    }

    private void readObject() throws IOException {
        Object object = this.handler.startObject();
        this.read();
        if ((this.nestingLevel += (int)((long)-159658049 ^ (long)-159658050)) > (int)((long)932889840 ^ (long)932889741) << 3) {
            throw this.error("Nesting too deep");
        }
        this.skipWhiteSpace();
        if (this.readChar((char)((int)-1712006593L ^ 0x99F4DA42))) {
            this.nestingLevel -= (int)((long)1479171394 ^ (long)1479171395);
            this.handler.endObject(object);
            return;
        }
        do {
            this.skipWhiteSpace();
            this.handler.startObjectName(object);
            String string = this.readName();
            this.handler.endObjectName(object, string);
            this.skipWhiteSpace();
            if (!this.readChar((int)((long)-178715316 ^ (long)-178715311) << 1)) {
                throw this.expected("':'");
            }
            this.skipWhiteSpace();
            this.handler.startObjectValue(object, string);
            this.readValue();
            this.handler.endObjectValue(object, string);
            this.skipWhiteSpace();
        } while (this.readChar(((int)1310692111L ^ 0x4E1F9304) << 2));
        if (!this.readChar((char)((int)1998377445L ^ 0x771CD198))) {
            throw this.expected("',' or '}'");
        }
        this.nestingLevel -= (int)((long)-489435562 ^ (long)-489435561);
        this.handler.endObject(object);
    }

    private String readName() throws IOException {
        if (this.current != (int)((long)144282314 ^ (long)144282331) << 1) {
            throw this.expected("name");
        }
        return this.readStringInternal();
    }

    private void readNull() throws IOException {
        this.handler.startNull();
        this.read();
        this.readRequiredChar((char)((int)-693998223L ^ 0xD6A26D04));
        this.readRequiredChar(((int)-2125442328L ^ 0x815052F3) << 2);
        this.readRequiredChar((int)((long)713323774 ^ (long)713323749) << 2);
        this.handler.endNull();
    }

    private void readTrue() throws IOException {
        this.handler.startBoolean();
        this.read();
        this.readRequiredChar(((int)-1307573101L ^ 0xB21004AA) << 1);
        this.readRequiredChar((char)((int)1092515299L ^ 0x411E7596));
        this.readRequiredChar((char)((int)-2142720898L ^ 0x8048AC1B));
        this.handler.endBoolean(((int)-1738361017L ^ 0x9862B746) != 0);
    }

    private void readFalse() throws IOException {
        this.handler.startBoolean();
        this.read();
        this.readRequiredChar((char)((int)-411603372L ^ 0xE7776E35));
        this.readRequiredChar((int)((long)1700593890 ^ (long)1700593913) << 2);
        this.readRequiredChar((char)((long)1690546084 ^ (long)1690546135));
        this.readRequiredChar((char)((int)-800512228L ^ 0xD0492779));
        this.handler.endBoolean((boolean)((long)1803705259 ^ (long)1803705259));
    }

    private void readRequiredChar(char c2) throws IOException {
        if (!this.readChar(c2)) {
            throw this.expected("'" + c2 + "'");
        }
    }

    private void readString() throws IOException {
        this.handler.startString();
        this.handler.endString(this.readStringInternal());
    }

    private String readStringInternal() throws IOException {
        this.read();
        this.startCapture();
        while (this.current != ((int)-161631349L ^ 0xF65DB39A) << 1) {
            if (this.current == (int)((long)850932990 ^ (long)850932969) << 2) {
                this.pauseCapture();
                this.readEscape();
                this.startCapture();
                continue;
            }
            if (this.current < ((int)-96005363L ^ 0xFA47130C) << 5) {
                throw this.expected("valid string character");
            }
            this.read();
        }
        String string = this.endCapture();
        this.read();
        return string;
    }

    private void readEscape() throws IOException {
        this.read();
        switch (this.current) {
            case 34: 
            case 47: 
            case 92: {
                this.captureBuffer.append((char)this.current);
                break;
            }
            case 98: {
                this.captureBuffer.append(((int)1251102227L ^ 0x4A924E12) << 3);
                break;
            }
            case 102: {
                this.captureBuffer.append(((int)37191978L ^ 0x2378129) << 2);
                break;
            }
            case 110: {
                this.captureBuffer.append(((int)-982477035L ^ 0xC5709710) << 1);
                break;
            }
            case 114: {
                this.captureBuffer.append((char)((long)-1407046645 ^ (long)-1407046650));
                break;
            }
            case 116: {
                this.captureBuffer.append((char)((long)-528462172 ^ (long)-528462163));
                break;
            }
            case 117: {
                char[] arrc = new char[(int)((long)969341659 ^ (long)969341658) << 2];
                for (int i2 = (int)((long)866228807 ^ (long)866228807); i2 < ((int)-2024449507L ^ 0x87555A1C) << 2; ++i2) {
                    this.read();
                    if (!this.isHexDigit()) {
                        throw this.expected("hexadecimal digit");
                    }
                    arrc[i2] = (char)this.current;
                }
                this.captureBuffer.append((char)Integer.parseInt(new String(arrc), (int)((long)-519497004 ^ (long)-519497003) << 4));
                break;
            }
            default: {
                throw this.expected("valid escape sequence");
            }
        }
        this.read();
    }

    private void readNumber() throws IOException {
        this.handler.startNumber();
        this.startCapture();
        this.readChar((char)((int)124865785L ^ 0x7714CD4));
        int n2 = this.current;
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        if (n2 != (int)((long)1712582953 ^ (long)1712582954) << 4) {
            while (this.readDigit()) {
            }
        }
        this.readFraction();
        this.readExponent();
        this.handler.endNumber(this.endCapture());
    }

    private boolean readFraction() throws IOException {
        if (!this.readChar(((int)1774541344L ^ 0x69C55A37) << 1)) {
            return (int)((long)-866053451 ^ (long)-866053451) != 0;
        }
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        while (this.readDigit()) {
        }
        return ((int)-76694294L ^ 0xFB6DBCEB) != 0;
    }

    private boolean readExponent() throws IOException {
        if (!this.readChar((char)((long)-1843987427 ^ (long)-1843987336)) && !this.readChar((char)((long)1404299943 ^ (long)1404300002))) {
            return (int)((long)-630279445 ^ (long)-630279445) != 0;
        }
        if (!this.readChar((char)((long)-85434663 ^ (long)-85434638))) {
            this.readChar((char)((int)-29772578L ^ 0xFE39B4F3));
        }
        if (!this.readDigit()) {
            throw this.expected("digit");
        }
        while (this.readDigit()) {
        }
        return ((int)352865665L ^ 0x15084D80) != 0;
    }

    private boolean readChar(char c2) throws IOException {
        if (this.current != c2) {
            return (int)((long)-1193072262 ^ (long)-1193072262) != 0;
        }
        this.read();
        return ((int)-1509882814L ^ 0xA6010443) != 0;
    }

    private boolean readDigit() throws IOException {
        if (!this.isDigit()) {
            return ((int)-1107420236L ^ 0xBDFE1BB4) != 0;
        }
        this.read();
        return (int)((long)1402189392 ^ (long)1402189393) != 0;
    }

    private void skipWhiteSpace() throws IOException {
        while (this.isWhiteSpace()) {
            this.read();
        }
    }

    private void read() throws IOException {
        if (this.index == this.fill) {
            if (this.captureStart != (int)((long)1916482453 ^ (long)-1916482454)) {
                this.captureBuffer.append(this.buffer, this.captureStart, this.fill - this.captureStart);
                this.captureStart = (int)1576118549L ^ 0x5DF1A915;
            }
            this.bufferOffset += this.fill;
            this.fill = this.reader.read(this.buffer, (int)-269632102L ^ 0xEFEDBD9A, this.buffer.length);
            this.index = (int)((long)814753655 ^ (long)814753655);
            if (this.fill == (int)((long)1719013295 ^ (long)-1719013296)) {
                this.current = (int)((long)-145058548 ^ (long)145058547);
                this.index += (int)((long)220560283 ^ (long)220560282);
                return;
            }
        }
        if (this.current == ((int)-1491208273L ^ 0xA71DF7AA) << 1) {
            this.line += (int)((long)-1430513481 ^ (long)-1430513482);
            this.lineOffset = this.bufferOffset + this.index;
        }
        int n2 = this.index;
        this.index = n2 + (int)((long)356911849 ^ (long)356911848);
        this.current = this.buffer[n2];
    }

    private void startCapture() {
        if (this.captureBuffer == null) {
            this.captureBuffer = new StringBuilder();
        }
        this.captureStart = this.index - (int)((long)395840426 ^ (long)395840427);
    }

    private void pauseCapture() {
        int n2 = this.current == ((int)306448803L ^ 0xEDBBF65C) ? this.index : this.index - (int)((long)1631953815 ^ (long)1631953814);
        this.captureBuffer.append(this.buffer, this.captureStart, n2 - this.captureStart);
        this.captureStart = (int)((long)-391629354 ^ (long)391629353);
    }

    private String endCapture() {
        int n2 = this.captureStart;
        int n3 = this.index - (int)((long)1062928921 ^ (long)1062928920);
        this.captureStart = (int)((long)-2097039277 ^ (long)2097039276);
        if (this.captureBuffer.length() > 0) {
            this.captureBuffer.append(this.buffer, n2, n3 - n2);
            String string = this.captureBuffer.toString();
            this.captureBuffer.setLength((int)1734690910L ^ 0x6765485E);
            return string;
        }
        return new String(this.buffer, n2, n3 - n2);
    }

    Location getLocation() {
        int n2 = this.bufferOffset + this.index - ((int)1927029209L ^ 0x72DC21D8);
        int n3 = n2 - this.lineOffset + ((int)-1949806936L ^ 0x8BC84EA9);
        return new Location(n2, this.line, n3);
    }

    private ParseException expected(String string) {
        if (this.isEndOfText()) {
            return this.error("Unexpected end of input");
        }
        return this.error("Expected " + string);
    }

    private ParseException error(String string) {
        return new ParseException(string, this.getLocation());
    }

    private boolean isWhiteSpace() {
        return (this.current == ((int)22427691L ^ 0x156382A) << 5 || this.current == (int)((long)1103305216 ^ (long)1103305225) || this.current == ((int)1487532538L ^ 0x58A9F1FF) << 1 || this.current == ((int)2137199901L ^ 0x7F631510) ? (int)-1271894478L ^ 0xB4306E33 : (int)1673642324L ^ 0x63C1C154) != 0;
    }

    private boolean isDigit() {
        return (this.current >= ((int)-226483433L ^ 0xF2802314) << 4 && this.current <= ((int)-991774016L ^ 0xC4E2BAF9) ? (int)((long)-88013653 ^ (long)-88013654) : (int)((long)1249580098 ^ (long)1249580098)) != 0;
    }

    private boolean isHexDigit() {
        return (this.current >= ((int)100347333L ^ 0x5FB2DC6) << 4 && this.current <= (int)((long)1414098470 ^ (long)1414098463) || this.current >= ((int)456519850L ^ 0x1B35F0CB) && this.current <= ((int)-26625929L ^ 0xFE69B844) << 1 || this.current >= (int)((long)-1173309093 ^ (long)-1173309158) && this.current <= ((int)-237034600L ^ 0xF1DF23BB) << 1 ? (int)((long)-1611917659 ^ (long)-1611917660) : (int)1811771996L ^ 0x6BFD725C) != 0;
    }

    private boolean isEndOfText() {
        return (this.current == ((int)-2000238489L ^ 0x77393798) ? (int)((long)-1887428859 ^ (long)-1887428860) : (int)((long)61568620 ^ (long)61568620)) != 0;
    }
}

