package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class GotoInstruction extends Instruction {
    public GotoInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        int insIndex = (int) arglist.get(0).getValueEnforced(Datatype.I32, getName(), "ins_index");
        String regCondition = (String) arglist.get(1).getValueEnforced(Datatype.STR, "", getName(), "reg_condition");
        if (regCondition.isEmpty() || (boolean) context.getRegister(regCondition).toDatatype(Datatype.BOL).getValue()) {
            context.setPointer(insIndex);
        }
    }

    @Override
    public String getName() {
        return "goto";
    }
}
