package Nodes;

public class ConditionNode extends Node{
    private ControlStructures comparisonOperator;
    Node left;
    Node right;

    public ConditionNode(Node parent) {
        super(parent, ControlStructures.CONDITION);
    }
}
