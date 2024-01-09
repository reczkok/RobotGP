package Nodes;

import java.util.*;

public abstract class TwoArgOpNode implements Node {
    private Node parent;
    private ControlStructures controlStructure;
    protected Node left;
    protected Node right;
    private int depth;

    public TwoArgOpNode(Node parent, ControlStructures controlStructure) {
        this.parent = parent;
        this.controlStructure = controlStructure;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
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
        Node leftNode = this.getRandomNode(maxDepth);
        this.left = leftNode;
        this.addChild(leftNode);
        Node rightNode = this.getRandomNode(maxDepth);
        this.right = rightNode;
        this.addChild(rightNode);
    }

    private Node getRandomNode(int maxDepth) {
        if(maxDepth <= 0){
            int random = (int) (Math.random() * 5);
            switch (random) {
                case 0:
                    IdNode idNode = new IdNode(this);
                    idNode.initializeRandom(maxDepth - 1);
                    return idNode;
                case 1:
                    IntNode intNode = new IntNode(this);
                    intNode.initializeRandom(maxDepth - 1);
                    return intNode;
                case 2:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    return floatNode;
                case 3:
                    BoolNode boolNode = new BoolNode(this);
                    boolNode.initializeRandom(maxDepth - 1);
                    return boolNode;
                case 4:
                    InputNode inputNode = new InputNode(this);
                    inputNode.initializeRandom(maxDepth - 1);
                    return inputNode;
                default:
                    throw new RuntimeException("Invalid random number somehow");
            }
        }else {
            int random = (int) (Math.random() * 10);
            switch (random) {
                case 0:
                    IdNode idNode1 = new IdNode(this);
                    idNode1.initializeRandom(maxDepth - 1);
                    return idNode1;
                case 1:
                    IntNode intNode = new IntNode(this);
                    intNode.initializeRandom(maxDepth - 1);
                    return intNode;
                case 2:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    return floatNode;
                case 3:
                    BoolNode boolNode = new BoolNode(this);
                    boolNode.initializeRandom(maxDepth - 1);
                    return boolNode;
                case 4:
                    PlusNode plusNode = new PlusNode(this);
                    plusNode.initializeRandom(maxDepth - 1);
                    return plusNode;
                case 5:
                    MinusNode minusNode = new MinusNode(this);
                    minusNode.initializeRandom(maxDepth - 1);
                    return minusNode;
                case 6:
                    DivisionNode divisionNode = new DivisionNode(this);
                    divisionNode.initializeRandom(maxDepth - 1);
                    return divisionNode;
                case 7:
                    MultNode multNode = new MultNode(this);
                    multNode.initializeRandom(maxDepth - 1);
                    return multNode;
                case 8:
                    ModuloNode moduloNode = new ModuloNode(this);
                    moduloNode.initializeRandom(maxDepth - 1);
                    return moduloNode;
                case 9:
                    InputNode inputNode = new InputNode(this);
                    inputNode.initializeRandom(maxDepth - 1);
                    return inputNode;
                default:
                    throw new RuntimeException("Invalid random number somehow");
            }
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
    public List<ControlStructures> getLegalAlternatives(Node child) {
        if (left == child || right == child) {
            return Arrays.asList(ControlStructures.ID, ControlStructures.INT, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.PLUS, ControlStructures.MINUS, ControlStructures.DIVISION, ControlStructures.MULTIPLY, ControlStructures.MODULO, ControlStructures.INPUT);
        } else {
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
