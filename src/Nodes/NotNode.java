package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class NotNode implements Node{
    private Node parent;
    private ControlStructures controlStructure;
    private Node child;
    private int depth;

    public NotNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.NOT;
    }

    public NotNode(Node parent, Node child){
        this.parent = parent;
        this.controlStructure = ControlStructures.NOT;
        this.child = child;
        this.addChild(child);
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
        return false;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        Node conditionNode = this.getRandomCondition(maxDepth);
        this.child = conditionNode;
        this.addChild(conditionNode);
    }

    @Override
    public void addChild(Node child) {
        this.child = child;
    }

    @Override
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(this.child);
        return children;
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.print("NOT(");
        this.child.printAtIndent(indent + 1);
        System.out.print(")");
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
        NotNode notNode = new NotNode(parent);
        notNode.addChild(this.child.copy(notNode));
        return notNode;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.child == oldChild){
            this.child = newChild;
        }else{
            throw new RuntimeException("Invalid old child");
        }
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child) {
        if(this.child == child){
            return Arrays.asList(ControlStructures.AND, ControlStructures.OR, ControlStructures.NOT, ControlStructures.CONDITION);
        }else{
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.print("NOT(");
        this.child.printAtIndent(i + 1, printWriter);
        printWriter.print(")");
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    private Node getRandomCondition(int maxDepth) {
        if(maxDepth <= 0){
            ConditionNode conditionNode = new ConditionNode(this);
            conditionNode.initializeRandom(maxDepth - 1);
            return conditionNode;
        }else {
            int random = (int) (Math.random() * 3);
            switch (random) {
                case 0:
                    ConditionNode conditionNode = new ConditionNode(this);
                    conditionNode.initializeRandom(maxDepth - 1);
                    return conditionNode;
                case 1:
                    AndNode andNode = new AndNode(this);
                    andNode.initializeRandom(maxDepth - 1);
                    return andNode;
                case 2:
                    OrNode orNode = new OrNode(this);
                    orNode.initializeRandom(maxDepth - 1);
                    return orNode;
            }
            throw new RuntimeException("Invalid random number");
        }
    }
}
