package Nodes;

public class MultNode extends Node{
    private Node left;
    private Node right;

    public MultNode(Node parent) {
        super(parent, ControlStructures.MULTIPLY);
    }

    public MultNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.MULTIPLY);
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
