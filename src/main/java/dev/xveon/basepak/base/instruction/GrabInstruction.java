package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class GrabInstruction extends Instruction {
    @Override
    public void Execute(Arglist arglist, Function function, Context context) {
        String bspPath = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "bsp_path");

        context.loadFile(bspPath);
    }

    @Override
    public String getName() {
        return "grab";
    }
}
