package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class Str extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return null; }
            case I08 -> { return null; }
            case I16 -> { return null; }
            case I32 -> { return null; }
            case I64 -> { return null; }
            case F32 -> { return null; }
            case F64 -> { return null; }
            case CHR -> { return null; }
            case STR -> { return null; }
            case VEC, MAP -> { return null; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.STR;
    }
}
