package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class Goto extends Instruction {
    @Override
    public void Execute(Context context, Arglist arglist) {
        String funName = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "fun_name");
        int insIndex = (int) arglist.get(1).getValueEnforced(Datatype.I32, getName(), "ins_index");

        context.getInstructionQueue().clear();
        Instruction[] instructions = context.getFunctionTable().get(funName).getInstructions();
        for (int i = insIndex; i < instructions.length; i++) {
            context.getInstructionQueue().add(instructions[i]);
        }
    }

    @Override
    public String getName() {
        return "goto";
    }
}
