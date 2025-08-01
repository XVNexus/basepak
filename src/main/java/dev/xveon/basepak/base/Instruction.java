package dev.xveon.basepak.base;

public abstract class Instruction {
    public abstract void Execute(Context context, Argument... arguments);
    public abstract String getName();
}
