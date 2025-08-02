package dev.xveon.basepak;

import dev.xveon.basepak.base.Argument;
import dev.xveon.basepak.base.Datatype;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testDefaultConversion();
    }

    public static void testDefaultConversion() {
        var types = Arrays.asList(Datatype.NUL, Datatype.BOL, Datatype.I32, Datatype.I64,
                Datatype.F32, Datatype.F64,Datatype.CHR, Datatype.STR, Datatype.VEC, Datatype.MAP);
        for (var i = 0; i < types.size(); i++) {
            for (var j = 0; j < types.size(); j++) {
                var fromVal = new Argument(types.get(i));
                var toVal = fromVal.toDatatype(types.get(j));
                System.out.println(fromVal + " => " + toVal);
            }
        }
    }
}
