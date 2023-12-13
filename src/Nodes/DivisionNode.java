package Nodes;

public class DivisionNode extends Node{
    private Node left;
    private Node right;

    public DivisionNode(Node parent) {
        super(parent, ControlStructures.DIVISION);
    }

    public DivisionNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.DIVISION);
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
