package dev.xveon.basepak.base;

public class Function {
    private final String name;
    private final Instruction[] instructions;

    public Function(String name, Instruction[] instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }
}
