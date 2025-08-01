package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class Grab extends Instruction {
    @Override
    public void Execute(Context context, Arglist arglist) {
        String bspPath = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "bsp_path");

        context.grab(bspPath);
    }

    @Override
    public String getName() {
        return "grab";
    }
}
