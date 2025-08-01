package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class Move extends Instruction {
    @Override
    public void Execute(Context context, Arglist arglist) {
        String regFrom = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "reg_from");
        String regTo = (String) arglist.get(1).getValueEnforced(Datatype.STR, getName(), "reg_to");

        if (!regFrom.isEmpty()) {
            context.getRegisterTable().put(regTo, context.getRegisterTable().get(regFrom));
        } else {
            context.getRegisterTable().remove(regTo);
        }
    }

    @Override
    public String getName() {
        return "move";
    }
}
