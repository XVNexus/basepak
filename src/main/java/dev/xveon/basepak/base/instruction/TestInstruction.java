package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

import java.util.Objects;

public class TestInstruction extends Instruction {
    @Override
    public void Execute(Arglist arglist, Function function, Context context) {
        String condition = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "condition");
        Argument regAArgument = arglist.get(1);
        Argument regBArgument = arglist.get(2).to(regAArgument.getDatatype());

        Boolean result = null;
        switch (regAArgument.getDatatype()) {
            case NUL -> result = true;
            case BOL -> result = switch (condition) {
                case "==" -> (boolean) regAArgument.getValue() == (boolean) regBArgument.getValue();
                case "!=" -> (boolean) regAArgument.getValue() != (boolean) regBArgument.getValue();
                default -> false;
            };
            case I32 -> result = switch (condition) {
                case "==" -> (int) regAArgument.getValue() == (int) regBArgument.getValue();
                case "!=" -> (int) regAArgument.getValue() != (int) regBArgument.getValue();
                case "<" -> (int) regAArgument.getValue() < (int) regBArgument.getValue();
                case ">" -> (int) regAArgument.getValue() > (int) regBArgument.getValue();
                case "<=" -> (int) regAArgument.getValue() <= (int) regBArgument.getValue();
                case ">=" -> (int) regAArgument.getValue() >= (int) regBArgument.getValue();
                default -> false;
            };
            case I64 -> result = switch (condition) {
                case "==" -> (long) regAArgument.getValue() == (long) regBArgument.getValue();
                case "!=" -> (long) regAArgument.getValue() != (long) regBArgument.getValue();
                case "<" -> (long) regAArgument.getValue() < (long) regBArgument.getValue();
                case ">" -> (long) regAArgument.getValue() > (long) regBArgument.getValue();
                case "<=" -> (long) regAArgument.getValue() <= (long) regBArgument.getValue();
                case ">=" -> (long) regAArgument.getValue() >= (long) regBArgument.getValue();
                default -> false;
            };
            case F32 -> result = switch (condition) {
                case "==" -> (float) regAArgument.getValue() == (float) regBArgument.getValue();
                case "!=" -> (float) regAArgument.getValue() != (float) regBArgument.getValue();
                case "<" -> (float) regAArgument.getValue() < (float) regBArgument.getValue();
                case ">" -> (float) regAArgument.getValue() > (float) regBArgument.getValue();
                case "<=" -> (float) regAArgument.getValue() <= (float) regBArgument.getValue();
                case ">=" -> (float) regAArgument.getValue() >= (float) regBArgument.getValue();
                default -> false;
            };
            case F64 -> result = switch (condition) {
                case "==" -> (double) regAArgument.getValue() == (double) regBArgument.getValue();
                case "!=" -> (double) regAArgument.getValue() != (double) regBArgument.getValue();
                case "<" -> (double) regAArgument.getValue() < (double) regBArgument.getValue();
                case ">" -> (double) regAArgument.getValue() > (double) regBArgument.getValue();
                case "<=" -> (double) regAArgument.getValue() <= (double) regBArgument.getValue();
                case ">=" -> (double) regAArgument.getValue() >= (double) regBArgument.getValue();
                default -> false;
            };
            case CHR -> result = switch (condition) {
                case "==" -> (char) regAArgument.getValue() == (char) regBArgument.getValue();
                case "!=" -> (char) regAArgument.getValue() != (char) regBArgument.getValue();
                case "<" -> (char) regAArgument.getValue() < (char) regBArgument.getValue();
                case ">" -> (char) regAArgument.getValue() > (char) regBArgument.getValue();
                case "<=" -> (char) regAArgument.getValue() <= (char) regBArgument.getValue();
                case ">=" -> (char) regAArgument.getValue() >= (char) regBArgument.getValue();
                default -> false;
            };
            case STR, VEC, MAP -> result = switch (condition) {
                case "==" -> Objects.equals(regAArgument.getValue(), regBArgument.getValue());
                case "!=" -> !Objects.equals(regAArgument.getValue(), regBArgument.getValue());
                default -> false;
            };
        }
        context.setRegister("loc.test", new Argument(result));
    }

    @Override
    public String getName() {
        return "test";
    }
}
