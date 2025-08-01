package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class I32 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((int) 1) : ((int) 0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (int) value; }
            case STR -> { return (int) ((String) value).length(); }
            case VEC, MAP -> { return (int) 0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.I32;
    }
}
