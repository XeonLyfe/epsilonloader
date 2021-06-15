/*
 * Decompiled with CFR 0.150.
 */
package com.formdev.flatlaf.json;

import com.formdev.flatlaf.json.JsonHandler;
import com.formdev.flatlaf.json.JsonParser;
import com.formdev.flatlaf.json.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static Object parse(Reader reader) throws IOException, ParseException {
        DefaultHandler defaultHandler = new DefaultHandler();
        new JsonParser(defaultHandler).parse(reader);
        return defaultHandler.getValue();
    }

    static class DefaultHandler
    extends JsonHandler<List<Object>, Map<String, Object>> {
        private Object value;

        DefaultHandler() {
        }

        @Override
        public List<Object> startArray() {
            return new ArrayList<Object>();
        }

        @Override
        public Map<String, Object> startObject() {
            return new LinkedHashMap<String, Object>();
        }

        @Override
        public void endNull() {
            this.value = "null";
        }

        @Override
        public void endBoolean(boolean bl) {
            this.value = bl ? "true" : "false";
        }

        @Override
        public void endString(String string) {
            this.value = string;
        }

        @Override
        public void endNumber(String string) {
            this.value = string;
        }

        @Override
        public void endArray(List<Object> list) {
            this.value = list;
        }

        @Override
        public void endObject(Map<String, Object> map) {
            this.value = map;
        }

        @Override
        public void endArrayValue(List<Object> list) {
            list.add(this.value);
        }

        @Override
        public void endObjectValue(Map<String, Object> map, String string) {
            map.put(string, this.value);
        }

        Object getValue() {
            return this.value;
        }
    }
}

