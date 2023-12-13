package Nodes;

public class OrNode extends Node{
    private Node left;
    private Node right;

    public OrNode(Node parent) {
        super(parent, ControlStructures.OR);
    }

    public OrNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.OR);
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
