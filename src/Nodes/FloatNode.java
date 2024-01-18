package Nodes;

import GP.Parameters;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FloatNode implements Node{
    public float value;
    private Node parent;
    private final ControlStructures controlStructure;
    private int depth;

    public FloatNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.FLOAT;
    }

    public FloatNode(Node parent, String value) {
        this.parent = parent;
        this.controlStructure = ControlStructures.FLOAT;
        this.value = Float.parseFloat(value);
    }

    @Override
    public Node getParent() {
        return this.parent;
    }

    @Override
    public ControlStructures getControlStructure() {
        return this.controlStructure;
    }
    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        this.value = (float) (Math.random() * NodeParameters.floatAmount);
        if (Math.random() < 0.5) {
            this.value *= -1;
        }
    }

    @Override
    public void addChild(Node child) {
        throw new UnsupportedOperationException("IdNode does not support addChild");
    }

    @Override
    public List<Node> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print(this.value);
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public Node copy(Node parent) {
        FloatNode copy = new FloatNode(parent);
        copy.value = this.value;
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        throw new UnsupportedOperationException("Cannot replace child of IdNode");
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        throw new UnsupportedOperationException("IdNode does not support getLegalAlternatives");
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.print(String.format("%.2f", this.value));
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%.2f", this.value));
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
