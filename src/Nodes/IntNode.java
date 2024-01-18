package Nodes;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IntNode implements Node{
    public int varIndex;
    private Node parent;
    private final ControlStructures controlStructure;
    private int depth;

    public IntNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.INT;
    }

    public IntNode(Node parent, String value) {
        this.parent = parent;
        this.controlStructure = ControlStructures.INT;
        this.varIndex = Integer.parseInt(value);
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
        this.varIndex = (int) (Math.random() * NodeParameters.intAmount);
        if (Math.random() < 0.5) {
            this.varIndex *= -1;
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
        System.out.print(this.varIndex);
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
        IntNode copy = new IntNode(parent);
        copy.varIndex = this.varIndex;
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        throw new UnsupportedOperationException("IdNode does not support replaceChild");
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        throw new UnsupportedOperationException("IdNode does not support getLegalAlternatives");
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.print(this.varIndex);
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        stringBuilder.append(this.varIndex);
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
