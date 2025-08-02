package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Arglist;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class CallInstruction extends Instruction {
    public CallInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        int insIndex = (int) arglist.get(0).getValueEnforced(Datatype.I32, getName(), "ins_index");
        for (int i = 1; i < arglist.getSize(); i++) {
            context.setRegister("loc.arg" + (i - 1), arglist.get(i));
        }
        context.pushPointer();
        context.setPointer(insIndex);
    }

    @Override
    public String getName() {
        return "call";
    }
}
