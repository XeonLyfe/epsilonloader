/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib.tree.analysis;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.analysis.AnalyzerException;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.BasicVerifier;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimpleVerifier
extends BasicVerifier {
    private final Type currentClass;
    private final Type currentSuperClass;
    private final List<Type> currentClassInterfaces;
    private final boolean isInterface;
    private ClassLoader loader = this.getClass().getClassLoader();

    public SimpleVerifier() {
        this(null, null, false);
    }

    public SimpleVerifier(Type type, Type type2, boolean bl) {
        this(type, type2, null, bl);
    }

    public SimpleVerifier(Type type, Type type2, List<Type> list, boolean bl) {
        this(327680, type, type2, list, bl);
    }

    protected SimpleVerifier(int n2, Type type, Type type2, List<Type> list, boolean bl) {
        super(n2);
        this.currentClass = type;
        this.currentSuperClass = type2;
        this.currentClassInterfaces = list;
        this.isInterface = bl;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    @Override
    public BasicValue newValue(Type type) {
        BasicValue basicValue;
        boolean bl;
        if (type == null) {
            return BasicValue.UNINITIALIZED_VALUE;
        }
        boolean bl2 = bl = type.getSort() == 9;
        if (bl) {
            switch (type.getElementType().getSort()) {
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    return new BasicValue(type);
                }
            }
        }
        if (BasicValue.REFERENCE_VALUE.equals(basicValue = super.newValue(type))) {
            if (bl) {
                basicValue = this.newValue(type.getElementType());
                String string = basicValue.getType().getDescriptor();
                for (int i2 = 0; i2 < type.getDimensions(); ++i2) {
                    string = '[' + string;
                }
                basicValue = new BasicValue(Type.getType(string));
            } else {
                basicValue = new BasicValue(type);
            }
        }
        return basicValue;
    }

    @Override
    protected boolean isArrayValue(BasicValue basicValue) {
        Type type = basicValue.getType();
        return type != null && ("Lnull;".equals(type.getDescriptor()) || type.getSort() == 9);
    }

    @Override
    protected BasicValue getElementValue(BasicValue basicValue) throws AnalyzerException {
        Type type = basicValue.getType();
        if (type != null) {
            if (type.getSort() == 9) {
                return this.newValue(Type.getType(type.getDescriptor().substring(1)));
            }
            if ("Lnull;".equals(type.getDescriptor())) {
                return basicValue;
            }
        }
        throw new Error("Internal error");
    }

    @Override
    protected boolean isSubTypeOf(BasicValue basicValue, BasicValue basicValue2) {
        Type type = basicValue2.getType();
        Type type2 = basicValue.getType();
        switch (type.getSort()) {
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                return type2.equals(type);
            }
            case 9: 
            case 10: {
                if ("Lnull;".equals(type2.getDescriptor())) {
                    return true;
                }
                if (type2.getSort() == 10 || type2.getSort() == 9) {
                    return this.isAssignableFrom(type, type2);
                }
                return false;
            }
        }
        throw new Error("Internal error");
    }

    @Override
    public BasicValue merge(BasicValue basicValue, BasicValue basicValue2) {
        if (!basicValue.equals(basicValue2)) {
            Type type = basicValue.getType();
            Type type2 = basicValue2.getType();
            if (!(type == null || type.getSort() != 10 && type.getSort() != 9 || type2 == null || type2.getSort() != 10 && type2.getSort() != 9)) {
                if ("Lnull;".equals(type.getDescriptor())) {
                    return basicValue2;
                }
                if ("Lnull;".equals(type2.getDescriptor())) {
                    return basicValue;
                }
                if (this.isAssignableFrom(type, type2)) {
                    return basicValue;
                }
                if (this.isAssignableFrom(type2, type)) {
                    return basicValue2;
                }
                do {
                    if (type != null && !this.isInterface(type)) continue;
                    return BasicValue.REFERENCE_VALUE;
                } while (!this.isAssignableFrom(type = this.getSuperClass(type), type2));
                return this.newValue(type);
            }
            return BasicValue.UNINITIALIZED_VALUE;
        }
        return basicValue;
    }

    protected boolean isInterface(Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.isInterface;
        }
        return this.getClass(type).isInterface();
    }

    protected Type getSuperClass(Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.currentSuperClass;
        }
        Class<?> class_ = this.getClass(type).getSuperclass();
        return class_ == null ? null : Type.getType(class_);
    }

    protected boolean isAssignableFrom(Type type, Type type2) {
        if (type.equals(type2)) {
            return true;
        }
        if (this.currentClass != null && type.equals(this.currentClass)) {
            if (this.getSuperClass(type2) == null) {
                return false;
            }
            if (this.isInterface) {
                return type2.getSort() == 10 || type2.getSort() == 9;
            }
            return this.isAssignableFrom(type, this.getSuperClass(type2));
        }
        if (this.currentClass != null && type2.equals(this.currentClass)) {
            if (this.isAssignableFrom(type, this.currentSuperClass)) {
                return true;
            }
            if (this.currentClassInterfaces != null) {
                for (int i2 = 0; i2 < this.currentClassInterfaces.size(); ++i2) {
                    Type type3 = this.currentClassInterfaces.get(i2);
                    if (!this.isAssignableFrom(type, type3)) continue;
                    return true;
                }
            }
            return false;
        }
        Class<Object> class_ = this.getClass(type);
        if (class_.isInterface()) {
            class_ = Object.class;
        }
        return class_.isAssignableFrom(this.getClass(type2));
    }

    protected Class<?> getClass(Type type) {
        try {
            if (type.getSort() == 9) {
                return Class.forName(type.getDescriptor().replace('/', '.'), false, this.loader);
            }
            return Class.forName(type.getClassName(), false, this.loader);
        }
        catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException(classNotFoundException.toString());
        }
    }
}

