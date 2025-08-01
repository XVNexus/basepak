package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class I64 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((long) 1) : ((long) 0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (long) value; }
            case STR -> { return (long) ((String) value).length(); }
            case VEC, MAP -> { return (long) 0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.I64;
    }
}
