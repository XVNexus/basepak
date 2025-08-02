package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

import java.util.List;

public class GotoInstruction extends Instruction {
    @Override
    public void Execute(Arglist arglist, Function function, Context context) {
        int insIndex = (int) arglist.get(0).getValueEnforced(Datatype.I32, 0, getName(), "ins_index");
        String funName = (String) arglist.get(1).getValueEnforced(Datatype.STR, function.getName(), getName(), "fun_name");

        context.clearInstructions();
        Instruction[] instructions = context.getFunction(funName).getInstructions();
        for (int i = insIndex; i < instructions.length; i++) {
            context.enqueueInstructions(List.of(instructions).subList(insIndex, instructions.length));
        }
    }

    @Override
    public String getName() {
        return "goto";
    }
}
