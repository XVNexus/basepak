package dev.xveon.basepak.base.instruction;

import dev.xveon.basepak.base.*;

public class CalcInstruction extends Instruction {
    public CalcInstruction(Arglist arglist) {
        super(arglist);
    }

    @Override
    public void execute(Context context) {
        String operation = (String) arglist.get(0).getValueEnforced(Datatype.STR, getName(), "operation");
        String regA = (String) arglist.get(1).getValueEnforced(Datatype.STR, getName(), "reg_a");
        String regB = (String) arglist.get(2).getValueEnforced(Datatype.STR, "", getName(), "reg_b");
        Argument argA = context.getRegister(regA);
        Argument argB = context.getRegister(regB);
        context.setRegister(regA, Argument.fromValue(switch (operation) {
            case "equ" -> argA.getValue().equals(argB.getValue());
            case "neq" -> !argA.getValue().equals(argB.getValue());
            case "ltn" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() < (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() < (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() < (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() < (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() < (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() < (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "gtn" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() > (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() > (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() > (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() > (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() > (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() > (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "lte" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() <= (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() <= (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() <= (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() <= (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() <= (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() <= (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "gte" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() >= (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() >= (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() >= (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() >= (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() >= (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() >= (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "buf" -> switch (argA.getDatatype()) {
                case BOL -> (boolean) argA.getValue();
                case I32 -> (int) argA.getValue();
                case I64 -> (long) argA.getValue();
                case CHR -> (char) argA.getValue();
                default -> (int) argA.toDatatype(Datatype.BOL).getValue();
            };
            case "and" -> switch (argA.getDatatype()) {
                case BOL -> (boolean) argA.getValue() && (boolean) argB.toDatatype(Datatype.BOL).getValue();
                case I32 -> (int) argA.getValue() & (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() & (long) argB.toDatatype(Datatype.I64).getValue();
                case CHR -> (char) argA.getValue() & (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.BOL).getValue() & (int) argB.toDatatype(Datatype.BOL).getValue();
            };
            case "lor" -> switch (argA.getDatatype()) {
                case BOL -> (boolean) argA.getValue() || (boolean) argB.toDatatype(Datatype.BOL).getValue();
                case I32 -> (int) argA.getValue() | (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() | (long) argB.toDatatype(Datatype.I64).getValue();
                case CHR -> (char) argA.getValue() | (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.BOL).getValue() | (int) argB.toDatatype(Datatype.BOL).getValue();
            };
            case "xor" -> switch (argA.getDatatype()) {
                case BOL -> (boolean) argA.getValue() ^ (boolean) argB.toDatatype(Datatype.BOL).getValue();
                case I32 -> (int) argA.getValue() ^ (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() ^ (long) argB.toDatatype(Datatype.I64).getValue();
                case CHR -> (char) argA.getValue() ^ (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.BOL).getValue() ^ (int) argB.toDatatype(Datatype.BOL).getValue();
            };
            case "not" -> switch (argA.getDatatype()) {
                case BOL -> !((boolean) argA.getValue());
                case I32 -> ~((int) argA.getValue());
                case I64 -> ~((long) argA.getValue());
                case CHR -> ~((char) argA.getValue());
                default -> ~((int) argA.toDatatype(Datatype.BOL).getValue());
            };
            case "nan" -> switch (argA.getDatatype()) {
                case BOL -> !((boolean) argA.getValue() && (boolean) argB.toDatatype(Datatype.BOL).getValue());
                case I32 -> ~((int) argA.getValue() & (int) argB.toDatatype(Datatype.I32).getValue());
                case I64 -> ~((long) argA.getValue() & (long) argB.toDatatype(Datatype.I64).getValue());
                case CHR -> ~((char) argA.getValue() & (char) argB.toDatatype(Datatype.CHR).getValue());
                default -> ~((int) argA.toDatatype(Datatype.BOL).getValue() & (int) argB.toDatatype(Datatype.BOL).getValue());
            };
            case "nor" -> switch (argA.getDatatype()) {
                case BOL -> !((boolean) argA.getValue() || (boolean) argB.toDatatype(Datatype.BOL).getValue());
                case I32 -> ~((int) argA.getValue() | (int) argB.toDatatype(Datatype.I32).getValue());
                case I64 -> ~((long) argA.getValue() | (long) argB.toDatatype(Datatype.I64).getValue());
                case CHR -> ~((char) argA.getValue() | (char) argB.toDatatype(Datatype.CHR).getValue());
                default -> ~((int) argA.toDatatype(Datatype.BOL).getValue() | (int) argB.toDatatype(Datatype.BOL).getValue());
            };
            case "nxo" -> switch (argA.getDatatype()) {
                case BOL -> (boolean) argA.getValue() == (boolean) argB.toDatatype(Datatype.BOL).getValue();
                case I32 -> ~((int) argA.getValue() ^ (int) argB.toDatatype(Datatype.I32).getValue());
                case I64 -> ~((long) argA.getValue() ^ (long) argB.toDatatype(Datatype.I64).getValue());
                case CHR -> ~((char) argA.getValue() ^ (char) argB.toDatatype(Datatype.CHR).getValue());
                default -> ~((int) argA.toDatatype(Datatype.BOL).getValue() ^ (int) argB.toDatatype(Datatype.BOL).getValue());
            };
            case "add" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() + (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() + (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() + (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() + (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() + (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() + (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "sub" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() - (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() - (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() - (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() - (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() - (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() - (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "mul" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() * (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() * (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() * (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() * (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() * (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() * (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "div" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() / (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() / (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() / (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() / (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() / (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() / (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "mod" -> switch (argA.getDatatype()) {
                case I32 -> (int) argA.getValue() % (int) argB.toDatatype(Datatype.I32).getValue();
                case I64 -> (long) argA.getValue() % (long) argB.toDatatype(Datatype.I64).getValue();
                case F32 -> (float) argA.getValue() % (float) argB.toDatatype(Datatype.F32).getValue();
                case F64 -> (double) argA.getValue() % (double) argB.toDatatype(Datatype.F64).getValue();
                case CHR -> (char) argA.getValue() % (char) argB.toDatatype(Datatype.CHR).getValue();
                default -> (int) argA.toDatatype(Datatype.F32).getValue() % (int) argB.toDatatype(Datatype.F32).getValue();
            };
            case "pow" -> switch (argA.getDatatype()) {
                case I32 -> Math.pow((int) argA.getValue(), (int) argB.toDatatype(Datatype.I32).getValue());
                case I64 -> Math.pow((long) argA.getValue(), (long) argB.toDatatype(Datatype.I64).getValue());
                case F32 -> Math.pow((float) argA.getValue(), (float) argB.toDatatype(Datatype.F32).getValue());
                case F64 -> Math.pow((double) argA.getValue(), (double) argB.toDatatype(Datatype.F64).getValue());
                case CHR -> Math.pow((char) argA.getValue(), (char) argB.toDatatype(Datatype.CHR).getValue());
                default -> Math.pow((int) argA.toDatatype(Datatype.F32).getValue(), (int) argB.toDatatype(Datatype.F32).getValue());
            };
            default -> throw new IllegalArgumentException("'" + operation + "' is not a valid operation.");
        }));
    }

    @Override
    public String getName() {
        return "calc";
    }
}
