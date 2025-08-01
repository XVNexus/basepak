package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class Bol extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return value; }
            case I08 -> { return ((byte) value) != 0; }
            case I16 -> { return ((short) value) != 0; }
            case I32 -> { return ((int) value) != 0; }
            case I64 -> { return ((long) value) != 0; }
            case F32 -> { return ((float) value) != 0.0; }
            case F64 -> { return ((double) value) != 0.0; }
            case CHR -> { return ((char) value) != 0; }
            case STR -> { return !((String) value).isEmpty(); }
            case VEC, MAP -> { return false; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.BOL;
    }
}
