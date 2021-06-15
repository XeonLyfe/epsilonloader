/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.signature.SignatureReader;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import org.spongepowered.asm.lib.signature.SignatureWriter;
import org.spongepowered.asm.lib.tree.ClassNode;

public class ClassSignature {
    protected static final String OBJECT = "java/lang/Object";
    private final Map<TypeVar, TokenHandle> types = new LinkedHashMap<TypeVar, TokenHandle>();
    private Token superClass = new Token("java/lang/Object");
    private final List<Token> interfaces = new ArrayList<Token>();
    private final Deque<String> rawInterfaces = new LinkedList<String>();

    ClassSignature() {
    }

    private ClassSignature read(String string) {
        if (string != null) {
            try {
                new SignatureReader(string).accept(new SignatureParser());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return this;
    }

    protected TypeVar getTypeVar(String string) {
        for (TypeVar typeVar : this.types.keySet()) {
            if (!typeVar.matches(string)) continue;
            return typeVar;
        }
        return null;
    }

    protected TokenHandle getType(String string) {
        for (TypeVar typeVar : this.types.keySet()) {
            if (!typeVar.matches(string)) continue;
            return this.types.get(typeVar);
        }
        TokenHandle tokenHandle = new TokenHandle();
        this.types.put(new TypeVar(string), tokenHandle);
        return tokenHandle;
    }

    protected String getTypeVar(TokenHandle tokenHandle) {
        for (Map.Entry<TypeVar, TokenHandle> entry : this.types.entrySet()) {
            TypeVar typeVar = entry.getKey();
            TokenHandle tokenHandle2 = entry.getValue();
            if (tokenHandle != tokenHandle2 && tokenHandle.asToken() != tokenHandle2.asToken()) continue;
            return "T" + typeVar + ";";
        }
        return tokenHandle.token.asType();
    }

    protected void addTypeVar(TypeVar typeVar, TokenHandle tokenHandle) throws IllegalArgumentException {
        if (this.types.containsKey(typeVar)) {
            throw new IllegalArgumentException("TypeVar " + typeVar + " is already present on " + this);
        }
        this.types.put(typeVar, tokenHandle);
    }

    protected void setSuperClass(Token token) {
        this.superClass = token;
    }

    public String getSuperClass() {
        return this.superClass.asType(true);
    }

    protected void addInterface(Token token) {
        if (!token.isRaw()) {
            String string = token.asType(true);
            ListIterator<Token> listIterator = this.interfaces.listIterator();
            while (listIterator.hasNext()) {
                Token token2 = listIterator.next();
                if (!token2.isRaw() || !token2.asType(true).equals(string)) continue;
                listIterator.set(token);
                return;
            }
        }
        this.interfaces.add(token);
    }

    public void addInterface(String string) {
        this.rawInterfaces.add(string);
    }

    protected void addRawInterface(String string) {
        Token token = new Token(string);
        String string2 = token.asType(true);
        for (Token token2 : this.interfaces) {
            if (!token2.asType(true).equals(string2)) continue;
            return;
        }
        this.interfaces.add(token);
    }

    public void merge(ClassSignature classSignature) {
        try {
            Iterator<Token> iterator2 = new HashSet<String>();
            for (TypeVar typeVar : this.types.keySet()) {
                iterator2.add((Token)((Object)typeVar.toString()));
            }
            classSignature.conform((Set<String>)((Object)iterator2));
        }
        catch (IllegalStateException illegalStateException) {
            illegalStateException.printStackTrace();
            return;
        }
        for (Map.Entry entry : classSignature.types.entrySet()) {
            this.addTypeVar((TypeVar)entry.getKey(), (TokenHandle)entry.getValue());
        }
        for (Token token : classSignature.interfaces) {
            this.addInterface(token);
        }
    }

    private void conform(Set<String> set) {
        for (TypeVar typeVar : this.types.keySet()) {
            String string = this.findUniqueName(typeVar.getOriginalName(), set);
            typeVar.rename(string);
            set.add(string);
        }
    }

    private String findUniqueName(String string, Set<String> set) {
        String string2;
        if (!set.contains(string)) {
            return string;
        }
        if (string.length() == 1 && (string2 = this.findOffsetName(string.charAt(0), set)) != null) {
            return string2;
        }
        string2 = this.findOffsetName('T', set, "", string);
        if (string2 != null) {
            return string2;
        }
        string2 = this.findOffsetName('T', set, string, "");
        if (string2 != null) {
            return string2;
        }
        string2 = this.findOffsetName('T', set, "T", string);
        if (string2 != null) {
            return string2;
        }
        string2 = this.findOffsetName('T', set, "", string + "Type");
        if (string2 != null) {
            return string2;
        }
        throw new IllegalStateException("Failed to conform type var: " + string);
    }

    private String findOffsetName(char c2, Set<String> set) {
        return this.findOffsetName(c2, set, "", "");
    }

    private String findOffsetName(char c2, Set<String> set, String string, String string2) {
        String string3 = String.format("%s%s%s", string, Character.valueOf(c2), string2);
        if (!set.contains(string3)) {
            return string3;
        }
        if (c2 > '@' && c2 < '[') {
            int n2 = c2 - 64;
            while (n2 + 65 != c2) {
                string3 = String.format("%s%s%s", string, Character.valueOf((char)(n2 + 65)), string2);
                if (!set.contains(string3)) {
                    return string3;
                }
                ++n2;
                n2 %= 26;
            }
        }
        return null;
    }

    public SignatureVisitor getRemapper() {
        return new SignatureRemapper();
    }

    public String toString() {
        while (this.rawInterfaces.size() > 0) {
            this.addRawInterface(this.rawInterfaces.remove());
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.types.size() > 0) {
            boolean bl = false;
            StringBuilder object = new StringBuilder();
            for (Map.Entry<TypeVar, TokenHandle> entry : this.types.entrySet()) {
                String string = entry.getValue().asBound();
                if (string.isEmpty()) continue;
                object.append(entry.getKey()).append(':').append(string);
                bl = true;
            }
            if (bl) {
                stringBuilder.append('<').append((CharSequence)object).append('>');
            }
        }
        stringBuilder.append(this.superClass.asType());
        for (Token token : this.interfaces) {
            stringBuilder.append(token.asType());
        }
        return stringBuilder.toString();
    }

    public ClassSignature wake() {
        return this;
    }

    public static ClassSignature of(String string) {
        return new ClassSignature().read(string);
    }

    public static ClassSignature of(ClassNode classNode) {
        if (classNode.signature != null) {
            return ClassSignature.of(classNode.signature);
        }
        return ClassSignature.generate(classNode);
    }

    public static ClassSignature ofLazy(ClassNode classNode) {
        if (classNode.signature != null) {
            return new Lazy(classNode.signature);
        }
        return ClassSignature.generate(classNode);
    }

    private static ClassSignature generate(ClassNode classNode) {
        ClassSignature classSignature = new ClassSignature();
        classSignature.setSuperClass(new Token(classNode.superName != null ? classNode.superName : OBJECT));
        for (String string : classNode.interfaces) {
            classSignature.addInterface(new Token(string));
        }
        return classSignature;
    }

    class SignatureRemapper
    extends SignatureWriter {
        private final Set<String> localTypeVars = new HashSet<String>();

        SignatureRemapper() {
        }

        @Override
        public void visitFormalTypeParameter(String string) {
            this.localTypeVars.add(string);
            super.visitFormalTypeParameter(string);
        }

        @Override
        public void visitTypeVariable(String string) {
            TypeVar typeVar;
            if (!this.localTypeVars.contains(string) && (typeVar = ClassSignature.this.getTypeVar(string)) != null) {
                super.visitTypeVariable(typeVar.toString());
                return;
            }
            super.visitTypeVariable(string);
        }
    }

    class SignatureParser
    extends SignatureVisitor {
        private FormalParamElement param;

        SignatureParser() {
            super(327680);
        }

        @Override
        public void visitFormalTypeParameter(String string) {
            this.param = new FormalParamElement(string);
        }

        @Override
        public SignatureVisitor visitClassBound() {
            return this.param.visitClassBound();
        }

        @Override
        public SignatureVisitor visitInterfaceBound() {
            return this.param.visitInterfaceBound();
        }

        @Override
        public SignatureVisitor visitSuperclass() {
            return new SuperClassElement();
        }

        @Override
        public SignatureVisitor visitInterface() {
            return new InterfaceElement();
        }

        class InterfaceElement
        extends TokenElement {
            InterfaceElement() {
            }

            @Override
            public void visitEnd() {
                ClassSignature.this.addInterface(this.token);
            }
        }

        class SuperClassElement
        extends TokenElement {
            SuperClassElement() {
            }

            @Override
            public void visitEnd() {
                ClassSignature.this.setSuperClass(this.token);
            }
        }

        class BoundElement
        extends TokenElement {
            private final TokenElement type;
            private final boolean classBound;

            BoundElement(TokenElement tokenElement, boolean bl) {
                this.type = tokenElement;
                this.classBound = bl;
            }

            @Override
            public void visitClassType(String string) {
                this.token = this.type.token.addBound(string, this.classBound);
            }

            @Override
            public void visitTypeArgument() {
                this.token.addTypeArgument('*');
            }

            @Override
            public SignatureVisitor visitTypeArgument(char c2) {
                return new TypeArgElement(this, c2);
            }
        }

        class TypeArgElement
        extends TokenElement {
            private final TokenElement type;
            private final char wildcard;

            TypeArgElement(TokenElement tokenElement, char c2) {
                this.type = tokenElement;
                this.wildcard = c2;
            }

            @Override
            public SignatureVisitor visitArrayType() {
                this.type.setArray();
                return this;
            }

            @Override
            public void visitBaseType(char c2) {
                this.token = this.type.addTypeArgument(c2).asToken();
            }

            @Override
            public void visitTypeVariable(String string) {
                TokenHandle tokenHandle = ClassSignature.this.getType(string);
                this.token = this.type.addTypeArgument(tokenHandle).setWildcard(this.wildcard).asToken();
            }

            @Override
            public void visitClassType(String string) {
                this.token = this.type.addTypeArgument(string).setWildcard(this.wildcard).asToken();
            }

            @Override
            public void visitTypeArgument() {
                this.token.addTypeArgument('*');
            }

            @Override
            public SignatureVisitor visitTypeArgument(char c2) {
                return new TypeArgElement(this, c2);
            }

            @Override
            public void visitEnd() {
            }
        }

        class FormalParamElement
        extends TokenElement {
            private final TokenHandle handle;

            FormalParamElement(String string) {
                this.handle = ClassSignature.this.getType(string);
                this.token = this.handle.asToken();
            }
        }

        abstract class TokenElement
        extends SignatureElement {
            protected Token token;
            private boolean array;

            TokenElement() {
            }

            public Token getToken() {
                if (this.token == null) {
                    this.token = new Token();
                }
                return this.token;
            }

            protected void setArray() {
                this.array = true;
            }

            private boolean getArray() {
                boolean bl = this.array;
                this.array = false;
                return bl;
            }

            @Override
            public void visitClassType(String string) {
                this.getToken().setType(string);
            }

            @Override
            public SignatureVisitor visitClassBound() {
                this.getToken();
                return new BoundElement(this, true);
            }

            @Override
            public SignatureVisitor visitInterfaceBound() {
                this.getToken();
                return new BoundElement(this, false);
            }

            @Override
            public void visitInnerClassType(String string) {
                this.token.addInnerClass(string);
            }

            @Override
            public SignatureVisitor visitArrayType() {
                this.setArray();
                return this;
            }

            @Override
            public SignatureVisitor visitTypeArgument(char c2) {
                return new TypeArgElement(this, c2);
            }

            Token addTypeArgument() {
                return this.token.addTypeArgument('*').asToken();
            }

            IToken addTypeArgument(char c2) {
                return this.token.addTypeArgument(c2).setArray(this.getArray());
            }

            IToken addTypeArgument(String string) {
                return this.token.addTypeArgument(string).setArray(this.getArray());
            }

            IToken addTypeArgument(Token token) {
                return this.token.addTypeArgument(token).setArray(this.getArray());
            }

            IToken addTypeArgument(TokenHandle tokenHandle) {
                return this.token.addTypeArgument(tokenHandle).setArray(this.getArray());
            }
        }

        abstract class SignatureElement
        extends SignatureVisitor {
            public SignatureElement() {
                super(327680);
            }
        }
    }

    class TokenHandle
    implements IToken {
        final Token token;
        boolean array;
        char wildcard;

        TokenHandle() {
            this(new Token());
        }

        TokenHandle(Token token) {
            this.token = token;
        }

        @Override
        public IToken setArray(boolean bl) {
            this.array |= bl;
            return this;
        }

        @Override
        public IToken setWildcard(char c2) {
            if ("+-".indexOf(c2) > -1) {
                this.wildcard = c2;
            }
            return this;
        }

        @Override
        public String asBound() {
            return this.token.asBound();
        }

        @Override
        public String asType() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.wildcard > '\u0000') {
                stringBuilder.append(this.wildcard);
            }
            if (this.array) {
                stringBuilder.append('[');
            }
            return stringBuilder.append(ClassSignature.this.getTypeVar(this)).toString();
        }

        @Override
        public Token asToken() {
            return this.token;
        }

        public String toString() {
            return this.token.toString();
        }

        public TokenHandle clone() {
            return new TokenHandle(this.token);
        }
    }

    static class Token
    implements IToken {
        static final String SYMBOLS = "+-*";
        private final boolean inner;
        private boolean array;
        private char symbol = '\u0000';
        private String type;
        private List<Token> classBound;
        private List<Token> ifaceBound;
        private List<IToken> signature;
        private List<IToken> suffix;
        private Token tail;

        Token() {
            this(false);
        }

        Token(String string) {
            this(string, false);
        }

        Token(char c2) {
            this();
            this.symbol = c2;
        }

        Token(boolean bl) {
            this(null, bl);
        }

        Token(String string, boolean bl) {
            this.inner = bl;
            this.type = string;
        }

        Token setSymbol(char c2) {
            if (this.symbol == '\u0000' && SYMBOLS.indexOf(c2) > -1) {
                this.symbol = c2;
            }
            return this;
        }

        Token setType(String string) {
            if (this.type == null) {
                this.type = string;
            }
            return this;
        }

        boolean hasClassBound() {
            return this.classBound != null;
        }

        boolean hasInterfaceBound() {
            return this.ifaceBound != null;
        }

        @Override
        public IToken setArray(boolean bl) {
            this.array |= bl;
            return this;
        }

        @Override
        public IToken setWildcard(char c2) {
            if ("+-".indexOf(c2) == -1) {
                return this;
            }
            return this.setSymbol(c2);
        }

        private List<Token> getClassBound() {
            if (this.classBound == null) {
                this.classBound = new ArrayList<Token>();
            }
            return this.classBound;
        }

        private List<Token> getIfaceBound() {
            if (this.ifaceBound == null) {
                this.ifaceBound = new ArrayList<Token>();
            }
            return this.ifaceBound;
        }

        private List<IToken> getSignature() {
            if (this.signature == null) {
                this.signature = new ArrayList<IToken>();
            }
            return this.signature;
        }

        private List<IToken> getSuffix() {
            if (this.suffix == null) {
                this.suffix = new ArrayList<IToken>();
            }
            return this.suffix;
        }

        IToken addTypeArgument(char c2) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(c2);
            }
            Token token = new Token(c2);
            this.getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(String string) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(string);
            }
            Token token = new Token(string);
            this.getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(Token token) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(token);
            }
            this.getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(TokenHandle tokenHandle) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(tokenHandle);
            }
            TokenHandle tokenHandle2 = tokenHandle.clone();
            this.getSignature().add(tokenHandle2);
            return tokenHandle2;
        }

        Token addBound(String string, boolean bl) {
            if (bl) {
                return this.addClassBound(string);
            }
            return this.addInterfaceBound(string);
        }

        Token addClassBound(String string) {
            Token token = new Token(string);
            this.getClassBound().add(token);
            return token;
        }

        Token addInterfaceBound(String string) {
            Token token = new Token(string);
            this.getIfaceBound().add(token);
            return token;
        }

        Token addInnerClass(String string) {
            this.tail = new Token(string, true);
            this.getSuffix().add(this.tail);
            return this.tail;
        }

        public String toString() {
            return this.asType();
        }

        @Override
        public String asBound() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.type != null) {
                stringBuilder.append(this.type);
            }
            if (this.classBound != null) {
                for (Token token : this.classBound) {
                    stringBuilder.append(token.asType());
                }
            }
            if (this.ifaceBound != null) {
                for (Token token : this.ifaceBound) {
                    stringBuilder.append(':').append(token.asType());
                }
            }
            return stringBuilder.toString();
        }

        @Override
        public String asType() {
            return this.asType(false);
        }

        public String asType(boolean bl) {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.array) {
                stringBuilder.append('[');
            }
            if (this.symbol != '\u0000') {
                stringBuilder.append(this.symbol);
            }
            if (this.type == null) {
                return stringBuilder.toString();
            }
            if (!this.inner) {
                stringBuilder.append('L');
            }
            stringBuilder.append(this.type);
            if (!bl) {
                if (this.signature != null) {
                    stringBuilder.append('<');
                    for (IToken iToken : this.signature) {
                        stringBuilder.append(iToken.asType());
                    }
                    stringBuilder.append('>');
                }
                if (this.suffix != null) {
                    for (IToken iToken : this.suffix) {
                        stringBuilder.append('.').append(iToken.asType());
                    }
                }
            }
            if (!this.inner) {
                stringBuilder.append(';');
            }
            return stringBuilder.toString();
        }

        boolean isRaw() {
            return this.signature == null;
        }

        String getClassType() {
            return this.type != null ? this.type : ClassSignature.OBJECT;
        }

        @Override
        public Token asToken() {
            return this;
        }
    }

    static interface IToken {
        public static final String WILDCARDS = "+-";

        public String asType();

        public String asBound();

        public Token asToken();

        public IToken setArray(boolean var1);

        public IToken setWildcard(char var1);
    }

    static class TypeVar
    implements Comparable<TypeVar> {
        private final String originalName;
        private String currentName;

        TypeVar(String string) {
            this.currentName = this.originalName = string;
        }

        @Override
        public int compareTo(TypeVar typeVar) {
            return this.currentName.compareTo(typeVar.currentName);
        }

        public String toString() {
            return this.currentName;
        }

        String getOriginalName() {
            return this.originalName;
        }

        void rename(String string) {
            this.currentName = string;
        }

        public boolean matches(String string) {
            return this.originalName.equals(string);
        }

        public boolean equals(Object object) {
            return this.currentName.equals(object);
        }

        public int hashCode() {
            return this.currentName.hashCode();
        }
    }

    static class Lazy
    extends ClassSignature {
        private final String sig;
        private ClassSignature generated;

        Lazy(String string) {
            this.sig = string;
        }

        @Override
        public ClassSignature wake() {
            if (this.generated == null) {
                this.generated = ClassSignature.of(this.sig);
            }
            return this.generated;
        }
    }
}

