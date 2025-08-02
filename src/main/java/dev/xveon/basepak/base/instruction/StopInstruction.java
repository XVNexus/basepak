package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.Arglist;
import dev.xveon.basepak.base.Context;
import dev.xveon.basepak.base.Datatype;
import dev.xveon.basepak.base.Instruction;

public class StopInstruction extends Instruction {
    public StopInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        String scope = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "scope");
        switch (scope) {
            case "fun" -> {
                if (!context.pullPointer()) {
                    context.resetPointer();
                }
            }
            case "eve" -> context.resetPointer();
            case "pak" -> {
                // TODO: TERMINATE CONTEXT
            }
        }
    }

    @Override
    public String getName() {
        return "stop";
    }
}
