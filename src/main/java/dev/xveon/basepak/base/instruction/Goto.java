package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Argument;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class Goto extends Instruction {
    @Override
    public void Execute(Context context, Argument... arguments) {
        String funName = (String) arguments[0].getEnforce(Datatype.STR, getName(), "fun_name");
        int insIndex = (int) arguments[1].getEnforce(Datatype.I32, getName(), "ins_index");

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
