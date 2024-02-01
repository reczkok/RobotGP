package Nodes;

import java.util.*;

public abstract class LogicalComparison implements Node {
    private Node parent;
    private ControlStructures controlStructure;
    protected Node left;
    protected Node right;
    private int depth;

    public LogicalComparison(Node parent, ControlStructures controlStructure) {
        this.parent = parent;
        this.controlStructure = controlStructure;
    }

    public LogicalComparison(Node parent, ControlStructures controlStructure, Node left, Node right){
        this.parent = parent;
        this.controlStructure = controlStructure;
        this.left = left;
        this.right = right;
        this.addChild(left);
        this.addChild(right);
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
    public void initializeRandom(int maxDepth){
        if (maxDepth <= 2){
            ConditionNode conditionNode = new ConditionNode(this);
            conditionNode.initializeRandom(maxDepth - 1);
            this.addChild(conditionNode);
            this.left = conditionNode;

            ConditionNode conditionNode2 = new ConditionNode(this);
            conditionNode2.initializeRandom(maxDepth - 1);
            this.addChild(conditionNode2);
            this.right = conditionNode2;
        } else {
            Node left = getRandomNode(maxDepth);
            this.addChild(left);
            this.left = left;
            Node right = getRandomNode(maxDepth);
            this.addChild(right);
            this.right = right;
        }
    }

    private Node getRandomNode(int maxDepth){
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                AndNode andNode = new AndNode(this);
                andNode.initializeRandom(maxDepth - 1);
                return andNode;
            case 1:
                OrNode orNode = new OrNode(this);
                orNode.initializeRandom(maxDepth - 1);
                return orNode;
            case 2:
                ConditionNode conditionNode = new ConditionNode(this);
                conditionNode.initializeRandom(maxDepth - 1);
                return conditionNode;
            default:
                throw new RuntimeException("Invalid random number somehow");
        }
    }

    @Override
    public void addChild(Node child) {
    }

    @Override
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(this.left);
        children.add(this.right);
        return children;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    public void replaceChild(Node oldChild, Node newChild) {
        if (this.left == oldChild) {
            this.left = newChild;
        } else if (this.right == oldChild) {
            this.right = newChild;
        } else {
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        if(child == this.left || child == this.right){
            if(depth > 4)return Arrays.asList(ControlStructures.AND, ControlStructures.OR, ControlStructures.CONDITION);
            else return List.of(ControlStructures.CONDITION);
        }else{
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
