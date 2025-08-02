package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class GotoInstruction extends Instruction {
    public GotoInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        int insIndex = (int) arglist.get(0).getValueEnforced(Datatype.I32, getName(), "ins_index");
        context.setPointer(insIndex);
    }

    @Override
    public String getName() {
        return "goto";
    }
}
