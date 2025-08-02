package dev.xveon.basepak.base;

import java.util.ArrayList;
import java.util.HashMap;

public class Context {
    public static final int POINTER_DEFAULT = -2;

    private HashMap<String, Argument> registerTable = new HashMap<>();
    private HashMap<String, Integer> labelTable = new HashMap<>();
    private ArrayList<Instruction> instructionTable = new ArrayList<>();
    private int pointer = POINTER_DEFAULT;
    private ArrayList<Integer> pointerStack = new ArrayList<>();
    private long tickTime = 0;
    private ArrayList<Integer> eventQueue = new ArrayList<>();
    private ArrayList<Integer> delayQueue = new ArrayList<>();
    private int eventIndex = 0;

    public void loadFile(String path) {
        // TODO: LOAD INSTRUCTIONS AND INDEX TABLE FROM FILE
    }

    public void doTick() {
        for (eventIndex = eventQueue.size() - 1; eventIndex >= 0; eventIndex--) {
            if (delayQueue.get(eventIndex) == 0) {
                delayQueue.remove(eventIndex);
                pointer = eventQueue.remove(eventIndex);
                runThread();
            } else {
                delayQueue.set(eventIndex, delayQueue.get(eventIndex) - 1);
            }
        }
        tickTime++;
    }

    public void runThread() {
        this.pointer--;
        while (pointer > POINTER_DEFAULT) {
            instructionTable.get(++this.pointer).execute(this);
        }
    }

    public void emitEvent(int delay, int pointer) {
        eventQueue.add(0, pointer);
        delayQueue.add(0, delay);
        eventIndex++;
    }

    public void emitEvent(int pointer) {
        emitEvent(0, pointer);
    }

    public void emitEvent(int delay, String label) {
        emitEvent(delay, labelTable.get(label));
    }

    public void emitEvent(String label) {
        emitEvent(0, label);
    }

    public Argument getRegister(String name) {
        return registerTable.getOrDefault(name, new Argument(null));
    }

    public void setRegister(String name, Argument value) {
        registerTable.put(name, value);
    }

    public void removeRegister(String name) {
        registerTable.remove(name);
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public void resetPointer() {
        setPointer(POINTER_DEFAULT);
    }

    public void setPointer(String label) {
        setPointer(labelTable.get(label));
    }

    public void pushPointer() {
        pointerStack.add(0, pointer);
    }

    public void pullPointer() {
        pointer = pointerStack.remove(0);
    }

    public long getTickTime() {
        return tickTime;
    }

    public void printLog(Severity severity, String message) {
        // TODO: PRINT TO GAME LOGS AND CHAT
    }
}
