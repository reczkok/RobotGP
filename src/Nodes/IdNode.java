package Nodes;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IdNode implements Node{
    public int varIndex;
    private Node parent;
    private final ControlStructures controlStructure;
    private int depth;

    public IdNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.ID;
    }

    public IdNode(Node parent, String varIndex) {
        this.parent = parent;
        this.controlStructure = ControlStructures.ID;
        this.varIndex = Integer.parseInt(varIndex.substring(1));
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
        this.varIndex = (int) (Math.random() * NodeParameters.varAmount);
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
        System.out.print("X" + this.varIndex);
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
        IdNode idNode = new IdNode(parent);
        idNode.varIndex = this.varIndex;
        return idNode;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        throw new UnsupportedOperationException("IdNode does not support replaceChild");
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child) {
        throw new UnsupportedOperationException("IdNode does not support getLegalAlternatives");
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.print("X" + this.varIndex);
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
