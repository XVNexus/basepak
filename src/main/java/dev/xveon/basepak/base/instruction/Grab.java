package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Argument;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class Grab extends Instruction {
    @Override
    public void Execute(Context context, Argument... arguments) {
        String bspPath = (String) arguments[0].getEnforce(Datatype.STR, getName(), "bsp_path");

        context.grab(bspPath);
    }

    @Override
    public String getName() {
        return "grab";
    }
}
