package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class MoveInstruction extends Instruction {
    @Override
    public void Execute(Arglist arglist, Function function, Context context) {
        String regFrom = (String) arglist.get(0).getValueEnforced(Datatype.STR, "", getName(), "reg_from");
        String regTo = (String) arglist.get(1).getValueEnforced(Datatype.STR, getName(), "reg_to");

        if (!regFrom.isEmpty()) {
            context.setRegister(regTo, context.getRegister(regFrom));
        } else {
            context.removeRegister(regTo);
        }
    }

    @Override
    public String getName() {
        return "move";
    }
}
