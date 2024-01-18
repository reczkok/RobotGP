package Nodes;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BoolNode implements Node{
    public boolean val;
    private Node parent;
    private final ControlStructures controlStructure;
    private int depth;

    public BoolNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.BOOL;
    }

    public BoolNode(Node parent, boolean val) {
        this.parent = parent;
        this.controlStructure = ControlStructures.BOOL;
        this.val = val;
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
        this.val = Math.random() < 0.5;
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
        if (this.val) {
            System.out.print("true");
        } else {
            System.out.print("false");
        }
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
        BoolNode copy = new BoolNode(parent);
        copy.val = this.val;
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        throw new UnsupportedOperationException("Cannot replace child of BoolNode");
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        throw new UnsupportedOperationException("IdNode does not support getLegalAlternatives");
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        if (this.val) {
            printWriter.print("true");
        } else {
            printWriter.print("false");
        }
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        if (this.val) {
            stringBuilder.append("true");
        } else {
            stringBuilder.append("false");
        }
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
