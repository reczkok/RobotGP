package Nodes;

public class MoveNode extends Node{
    private int varIndex;
    private int value;

    public MoveNode(Node parent) {
        super(parent, ControlStructures.MOVE);
    }

    public MoveNode(Node parent, int varIndex, int value) {
        super(parent, ControlStructures.MOVE);
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
