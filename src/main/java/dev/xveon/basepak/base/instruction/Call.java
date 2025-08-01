package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Argument;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

import java.util.ArrayList;

public class Call extends Instruction {
    @Override
    @SuppressWarnings("unchecked")
    public void Execute(Context context, Argument... arguments) {
        String funName = (String) arguments[0].getEnforce(Datatype.STR, getName(), "fun_name");
        ArrayList<Argument> argList = (ArrayList<Argument>) arguments[1].getEnforce(Datatype.VEC, getName(), "arg_list");

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
