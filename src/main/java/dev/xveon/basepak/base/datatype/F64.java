package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class F64 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((double) 1.0) : ((double) 0.0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (double) value; }
            case STR -> { return (double) ((String) value).length(); }
            case VEC, MAP -> { return (double) 0.0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.F64;
    }
}
