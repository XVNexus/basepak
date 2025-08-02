package dev.xveon.basepak.base;

public class Arglist {
    private final Argument[] arguments;

    public Arglist(Argument[] arguments) {
        this.arguments = arguments;
    }

    public Argument get(int index) {
        return index < arguments.length ? arguments[index] : new Argument(null);
    }

    public int getSize() {
        return arguments.length;
    }
}
