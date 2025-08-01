package dev.xveon.basepak.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: NUL KEYWORD

public class Context {
    private Map<String, Argument> registerTable = new HashMap<>();
    private HashMap<String, Function> functionTable = new HashMap<>();
    private ArrayList<Instruction> instructionQueue = new ArrayList<>();

    public Context(Function[] functions) {
        for (Function function : functions) {
            this.functionTable.put(function.getName(), function);
        }
    }

    public void grab(String bspPath) {
        // TODO: GRAB ALL FUNCTIONS FROM SCRIPT FILE
    }

    public Map<String, Argument> getRegisterTable() {
        return registerTable;
    }

    public HashMap<String, Function> getFunctionTable() {
        return functionTable;
    }

    public ArrayList<Instruction> getInstructionQueue() {
        return instructionQueue;
    }
}
