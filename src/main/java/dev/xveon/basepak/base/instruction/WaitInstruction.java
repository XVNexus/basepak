package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Arglist;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class WaitInstruction extends Instruction {
    public WaitInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        int delay = (int) arglist.get(0).getValueEnforced(Datatype.I32, getName(), "delay");
        context.emitEvent(delay, context.getPointer() + 1);
        context.resetPointer();
    }

    @Override
    public String getName() {
        return "wait";
    }
}
