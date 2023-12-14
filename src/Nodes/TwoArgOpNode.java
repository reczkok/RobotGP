package Nodes;

import java.util.*;

public abstract class TwoArgOpNode implements Node{
    private Node parent;
    private ControlStructures controlStructure;
    protected Node left;
    protected Node right;
    private Set<ControlStructures> childrenControlStructures;
    private int depth;

    public TwoArgOpNode(Node parent, ControlStructures controlStructure) {
        this.parent = parent;
        this.controlStructure = controlStructure;
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
        if (this.childrenControlStructures.contains(controlStructure)) {
            if(this.left.getControlStructure() == controlStructure){
                List<Node> children = new ArrayList<>();
                children.add(this.left);
                return children;
            } else if(this.right.getControlStructure() == controlStructure){
                List<Node> children = new ArrayList<>();
                children.add(this.right);
                return children;
            }
        }
        return null;
    }

    @Override
    public boolean isLiteral() {
        return false;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        if(maxDepth <= 0){
            int random = (int) (Math.random() * 3);
            switch (random){
                case 0:
                    IdNode idNode = new IdNode(this);
                    idNode.initializeRandom(maxDepth - 1);
                    this.addChild(idNode);
                    this.left = idNode;
                    break;
                case 1:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    this.addChild(floatNode);
                    this.left = floatNode;
                    break;
                case 2:
                    LookNode lookNode = new LookNode(this);
                    lookNode.initializeRandom(maxDepth - 1);
                    this.addChild(lookNode);
                    this.left = lookNode;
                    break;
            }
            random = (int) (Math.random() * 3);
            switch (random){
                case 0:
                    IdNode idNode = new IdNode(this);
                    idNode.initializeRandom(maxDepth - 1);
                    this.addChild(idNode);
                    this.right = idNode;
                    break;
                case 1:
                    FloatNode floatNode = new FloatNode(this);
                    floatNode.initializeRandom(maxDepth - 1);
                    this.addChild(floatNode);
                    this.right = floatNode;
                    break;
                case 2:
                    LookNode lookNode = new LookNode(this);
                    lookNode.initializeRandom(maxDepth - 1);
                    this.addChild(lookNode);
                    this.right = lookNode;
                    break;
            }
        } else {
            Node leftNode = this.getRandomNode(maxDepth);
            this.addChild(leftNode);
            this.left = leftNode;
            Node rightNode = this.getRandomNode(maxDepth);
            this.addChild(rightNode);
            this.right = rightNode;
        }
    }

    private Node getRandomNode(int maxDepth) {
        int random = (int) (Math.random() * 11);
        switch (random) {
            case 0:
                IdNode idNode1 = new IdNode(this);
                idNode1.initializeRandom(maxDepth - 1);
                return idNode1;
            case 1:
                LookNode lookNode = new LookNode(this);
                lookNode.initializeRandom(maxDepth - 1);
                return lookNode;
            case 2:
                FloatNode floatNode = new FloatNode(this);
                floatNode.initializeRandom(maxDepth - 1);
                return floatNode;
            case 3:
                AndNode andNode = new AndNode(this);
                andNode.initializeRandom(maxDepth - 1);
                return andNode;
            case 4:
                OrNode orNode = new OrNode(this);
                orNode.initializeRandom(maxDepth - 1);
                return orNode;
            case 5:
                NotNode notNode = new NotNode(this);
                notNode.initializeRandom(maxDepth - 1);
                return notNode;
            case 6:
                PlusNode plusNode = new PlusNode(this);
                plusNode.initializeRandom(maxDepth - 1);
                return plusNode;
            case 7:
                MinusNode minusNode = new MinusNode(this);
                minusNode.initializeRandom(maxDepth - 1);
                return minusNode;
            case 8:
                DivisionNode divisionNode = new DivisionNode(this);
                divisionNode.initializeRandom(maxDepth - 1);
                return divisionNode;
            case 9:
                MultNode multNode = new MultNode(this);
                multNode.initializeRandom(maxDepth - 1);
                return multNode;
            case 10:
                ModuloNode moduloNode = new ModuloNode(this);
                moduloNode.initializeRandom(maxDepth - 1);
                return moduloNode;
            default:
                throw new RuntimeException("Invalid random number somehow");
        }
    }

    @Override
    public void addChild(Node child) {
        ControlStructures childControlStructure = child.getControlStructure();
        this.childrenControlStructures.add(childControlStructure);
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

    public void replaceChild(Node oldChild, Node newChild){
        if(this.left == oldChild){
            this.left = newChild;
        } else if(this.right == oldChild){
            this.right = newChild;
        } else {
            throw new RuntimeException("Invalid child");
        }
    }
}
