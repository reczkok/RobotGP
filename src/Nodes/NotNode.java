package Nodes;

import java.util.*;

public class NotNode implements Node{
    private Node parent;
    private ControlStructures controlStructure;
    private Node child;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;
    private int depth;

    public NotNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.NOT;
        nodesByControlStructure = new HashMap<>();
        childrenControlStructures = new HashSet<>();
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
        return false;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        if (maxDepth == 0) {
            int random = (int) (Math.random() * 3);
            switch (random) {
                case 0:
                    IdNode idNode = new IdNode(this);
                    idNode.initializeRandom(maxDepth - 1);
                    this.addChild(idNode);
                    this.child = idNode;
                    break;
                case 1:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    this.addChild(floatNode);
                    this.child = floatNode;
                    break;
                case 2:
                    LookNode lookNode = new LookNode(this);
                    lookNode.initializeRandom(maxDepth - 1);
                    this.addChild(lookNode);
                    this.child = lookNode;
                    break;
            }

        } else {
            int random = (int) (Math.random() * 11);
            switch (random) {
                case 0:
                    IdNode idNode1 = new IdNode(this);
                    idNode1.initializeRandom(maxDepth - 1);
                    this.child = idNode1;
                    this.addChild(idNode1);
                    break;
                case 1:
                    LookNode lookNode = new LookNode(this);
                    lookNode.initializeRandom(maxDepth - 1);
                    this.child = lookNode;
                    this.addChild(lookNode);
                    break;
                case 2:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    this.child = floatNode;
                    this.addChild(floatNode);
                    break;
                case 3:
                    AndNode andNode = new AndNode(this);
                    andNode.initializeRandom(maxDepth - 1);
                    this.child = andNode;
                    this.addChild(andNode);
                    break;
                case 4:
                    OrNode orNode = new OrNode(this);
                    orNode.initializeRandom(maxDepth - 1);
                    this.child = orNode;
                    this.addChild(orNode);
                    break;
                case 5:
                    NotNode notNode = new NotNode(this);
                    notNode.initializeRandom(maxDepth - 1);
                    this.child = notNode;
                    this.addChild(notNode);
                    break;
                case 6:
                    PlusNode plusNode = new PlusNode(this);
                    plusNode.initializeRandom(maxDepth - 1);
                    this.child = plusNode;
                    this.addChild(plusNode);
                    break;
                case 7:
                    MinusNode minusNode = new MinusNode(this);
                    minusNode.initializeRandom(maxDepth - 1);
                    this.child = minusNode;
                    this.addChild(minusNode);
                    break;
                case 8:
                    DivisionNode divisionNode = new DivisionNode(this);
                    divisionNode.initializeRandom(maxDepth - 1);
                    this.child = divisionNode;
                    this.addChild(divisionNode);
                    break;
                case 9:
                    MultNode multNode = new MultNode(this);
                    multNode.initializeRandom(maxDepth - 1);
                    this.child = multNode;
                    this.addChild(multNode);
                    break;
                case 10:
                    ModuloNode moduloNode = new ModuloNode(this);
                    moduloNode.initializeRandom(maxDepth - 1);
                    this.child = moduloNode;
                    this.addChild(moduloNode);
                    break;
            }
        }

    }

    @Override
    public void addChild(Node child) {
        this.child = child;
        this.childrenControlStructures.add(child.getControlStructure());
        if (this.nodesByControlStructure.containsKey(child.getControlStructure())) {
            this.nodesByControlStructure.get(child.getControlStructure()).add(child);
        } else {
            List<Node> nodes = new ArrayList<>();
            nodes.add(child);
            this.nodesByControlStructure.put(child.getControlStructure(), nodes);
        }
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
}
