package dev.xveon.basepak.base;

public abstract class Instruction {
    protected Arglist arglist;

    public Instruction(Arglist arglist) {
        this.arglist = arglist;
    }

    public abstract void execute(Context context);

    public abstract String getName();
}
