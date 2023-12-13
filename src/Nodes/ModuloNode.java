package Nodes;

public class ModuloNode extends Node {
    private Node left;
    private Node right;

    public ModuloNode(Node parent) {
        super(parent, ControlStructures.MODULO);
    }

    public ModuloNode(Node parent, Node left, Node right) {
        super(parent, ControlStructures.MODULO);
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
