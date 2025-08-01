package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

import java.util.ArrayList;

public class Call extends Instruction {
    @Override
    @SuppressWarnings("unchecked")
    public void Execute(Context context, Arglist arglist) {
        String funName = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "fun_name");
        ArrayList<Argument> argList = (ArrayList<Argument>) arglist.get(1).getValueEnforced(Datatype.VEC, getName(), "arg_list");

        Instruction[] instructions = context.getFunctionTable().get(funName).getInstructions();
        for (int i = 0; i < instructions.length; i++) {
            context.getInstructionQueue().add(i, instructions[i]);
        }
    }

    @Override
    public String getName() {
        return "call";
    }
}
