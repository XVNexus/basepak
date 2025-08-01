package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class Chr extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? '1' : '0'; }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (char) value; }
            case STR -> { return ((String) value).charAt(0); }
            case VEC, MAP -> { return (char) 0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.CHR;
    }
}
