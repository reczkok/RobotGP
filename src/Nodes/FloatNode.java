package Nodes;

import java.util.ArrayList;
import java.util.List;

public class FloatNode implements Node{
    public int varIndex;
    private Node parent;
    private final ControlStructures controlStructure;
    private int depth;

    public FloatNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.FLOAT;
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
    public List<Node> getChildrenByControlStructure(ControlStructures controlStructure) {
        return null;
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        this.varIndex = (int) (Math.random() * NodeParameters.floatAmount);
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
        System.out.print("float:" + this.varIndex);
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }
}
