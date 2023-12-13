package Nodes;

public class LookNode extends Node{
    private int varIndex;
    private int value;

    public LookNode(Node parent) {
        super(parent, ControlStructures.LOOK);
    }

    public LookNode(Node parent, int varIndex, int value) {
        super(parent, ControlStructures.LOOK);
        this.varIndex = varIndex;
        this.value = value;
    }

    public int getVarIndex() {
        return varIndex;
    }

    public int getValue() {
        return value;
    }
}
