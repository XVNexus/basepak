package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class I16 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((short) 1) : ((short) 0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (short) value; }
            case STR -> { return (short) ((String) value).length(); }
            case VEC, MAP -> { return (short) 0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.I16;
    }
}
