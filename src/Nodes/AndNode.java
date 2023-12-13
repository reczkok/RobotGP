package Nodes;

public class AndNode extends Node{
    private Node left;
    private Node right;

    public AndNode(Node parent) {
        super(parent, ControlStructures.AND);
    }

    public AndNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.AND);
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
