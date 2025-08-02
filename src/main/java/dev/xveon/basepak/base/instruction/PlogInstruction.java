package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class PlogInstruction extends Instruction {
    public PlogInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        int level = (int) arglist.get(0).getValueEnforced(Datatype.I32, getName(), "level");
        String message = (String) arglist.get(1).getValueEnforced(Datatype.STR, getName(), "message");
        context.printLog(switch (level) {
            case 0 -> Severity.INFO;
            case 1 -> Severity.WARNING;
            default -> Severity.ERROR;
        }, message);
    }

    @Override
    public String getName() {
        return "plog";
    }
}
