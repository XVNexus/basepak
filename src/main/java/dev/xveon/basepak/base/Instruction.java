package dev.xveon.basepak.base;

public abstract class Instruction {
    public abstract void Execute(Arglist arglist, Function function, Context context);
    public abstract String getName();
}
