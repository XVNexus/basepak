package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class F32 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((float) 1.0) : ((float) 0.0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (float) value; }
            case STR -> { return (float) ((String) value).length(); }
            case VEC, MAP -> { return (float) 0.0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.F32;
    }
}
