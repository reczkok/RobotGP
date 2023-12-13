package Nodes;

import java.util.List;

public class IfNode extends Node{
    private Node condition;
    private List<Node> ifBody;
    private List<Node> elseBody;

    public IfNode(Node parent) {
        super(parent, ControlStructures.IF);
    }
}
