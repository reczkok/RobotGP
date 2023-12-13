package Nodes;

public class LoopNode extends Node{
    private Node condition;
    private Node body;

    public LoopNode(Node parent) {
        super(parent, ControlStructures.LOOP);
    }
}
