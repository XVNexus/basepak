package dev.xveon.basepak.base.datatype;

import dev.xveon.basepak.base.Datatype;

public class I08 extends Datatype {
    @Override
    public Object convertFrom(Primitive type, Object value) {
        switch (type) {
            case BOL -> { return ((boolean) value) ? ((byte) 1) : ((byte) 0); }
            case I08, I16, I32, I64, F32, F64, CHR -> { return (byte) value; }
            case STR -> { return (byte) ((String) value).length(); }
            case VEC, MAP -> { return (byte) 0; }
        }
        return value;
    }

    @Override
    public Primitive getPrimitive() {
        return Primitive.I08;
    }
}
