package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Argument;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class Move extends Instruction {
    @Override
    public void Execute(Context context, Argument... arguments) {
        String regFrom = (String) arguments[0].getEnforce(Datatype.STR, getName(), "reg_from");
        String regTo = (String) arguments[1].getEnforce(Datatype.STR, getName(), "reg_to");

        context.getRegisterTable().put(regTo, context.getRegisterTable().get(regFrom));
    }

    @Override
    public String getName() {
        return "move";
    }
}
