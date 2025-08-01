package dev.xveon.basepak.base;

public abstract class Instruction {
    public abstract void Execute(Context context, Arglist arglist);
    public abstract String getName();
}
