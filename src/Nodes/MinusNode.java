package Nodes;

public class MinusNode extends Node{
    private Node left;
    private Node right;

    public MinusNode(Node parent) {
        super(parent, ControlStructures.MINUS);
    }

    public MinusNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.MINUS);
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
