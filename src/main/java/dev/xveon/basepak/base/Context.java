package dev.xveon.basepak.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: NUL KEYWORD

public class Context {
    private Map<String, Argument> registerTable = new HashMap<>();
    private HashMap<String, Function> functionTable = new HashMap<>();
    private ArrayList<Function> functionQueue = new ArrayList<>();
    private ArrayList<Instruction> instructionQueue = new ArrayList<>();
    private ArrayList<Arglist> arglistQueue = new ArrayList<>();
    private ArrayList<Integer> waitTicks = new ArrayList<>();
    private ArrayList<List<Instruction>> waitBuffer = new ArrayList<>();
    private long tickTime = 0;

    public Context(Function[] functions) {
        for (Function function : functions) {
            this.functionTable.put(function.getName(), function);
        }
    }

    public void loadFile(String bspPath) {
        // TODO: GRAB ALL FUNCTIONS FROM SCRIPT FILE
    }

    public void tick() {
        while (!instructionQueue.isEmpty()) {
            instructionQueue.remove(0).Execute(arglistQueue.remove(0), functionQueue.remove(0), this);
        }
        tickTime++;
        // TODO: UPDATE WAITS
    }

    public Argument getRegister(String name) {
        return registerTable.getOrDefault(name, new Argument(Datatype.NUL));
    }

    public void setRegister(String name, Argument value) {
        registerTable.put(name, value);
    }

    public void removeRegister(String name) {
        registerTable.remove(name);
    }

    public Function getFunction(String name) {
        return functionTable.get(name);
    }

    public void enqueueInstructions(List<Instruction> instructions, List<Arglist> arglists, Function function) {
        instructionQueue.addAll(instructions);
        arglistQueue.addAll(arglists);
        for (int i = 0; i < instructions.size(); i++) {
            functionQueue.add(function);
        }
    }

    public void enqueueInstructions(int delay, List<Instruction> instructions) {
        waitTicks.add(delay);
        waitBuffer.add(instructions);
    }

    public void pushInstructions(List<Instruction> instructions) {
        instructionQueue.addAll(0, instructions);
    }

    public void clearInstructions() {
        instructionQueue.clear();
    }

    public long getTickTime() {
        return tickTime;
    }
}
