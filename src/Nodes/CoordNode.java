package Nodes;

public class CoordNode extends Node{
    private int varIndex;
    private int value;

    public CoordNode(Node parent) {
        super(parent, ControlStructures.GET_COORDINATE);
    }

    public CoordNode(Node parent, int varIndex, int value) {
        super(parent, ControlStructures.GET_COORDINATE);
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
