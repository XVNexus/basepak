package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

import java.util.List;

public class CallInstruction extends Instruction {
    @Override
    public void Execute(Arglist arglist, Function function, Context context) {
        String funName = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "fun_name");

        for (int i = 1; i < arglist.getSize(); i++) {
            context.setRegister("loc.arg" + (i - 1), arglist.get(i));
        }
        context.pushInstructions(List.of(context.getFunction(funName).getInstructions()));
    }

    @Override
    public String getName() {
        return "call";
    }
}
