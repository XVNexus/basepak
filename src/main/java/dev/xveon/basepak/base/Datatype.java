package dev.xveon.basepak.base;

import dev.xveon.basepak.base.datatype.Primitive;

public abstract class Datatype {
    private Datatype[] subtypes;

    public abstract Object convertFrom(Primitive type, Object value);

    public abstract Primitive getPrimitive();
}
