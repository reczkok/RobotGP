package Nodes;

public class PlusNode extends Node{
    private Node left;
    private Node right;

    public PlusNode(Node parent) {
        super(parent, ControlStructures.PLUS);
    }

    public PlusNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.PLUS);
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
