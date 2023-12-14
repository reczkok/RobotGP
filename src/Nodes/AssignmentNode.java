package Nodes;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AssignmentNode implements Node{
    private Node varaiableNode;
    private Node exprNode;
    private Node parent;
    private List<Node> children;
    private ControlStructures controlStructure;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;


    public AssignmentNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.ASSIGNMENT;
        this.childrenControlStructures = new HashSet<>();
        this.nodesByControlStructure = new HashMap<>();
        this.children = new ArrayList<>();
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
            return this.nodesByControlStructure.get(controlStructure);
        }
        return null;
    }

    @Override
    public boolean isLiteral() {
        return false;
    }

    @Override
    public void initializeRandom(int maxDepth) {
        IdNode idNode = new IdNode(this);
        idNode.initializeRandom(maxDepth - 1);
        this.varaiableNode = idNode;
        this.addChild(idNode);
        int random = (int) (Math.random() * 11);
        switch (random) {
            case 0:
                IdNode idNode1 = new IdNode(this);
                idNode1.initializeRandom(maxDepth - 1);
                this.exprNode = idNode1;
                this.addChild(idNode1);
                break;
            case 1:
                FloatNode floatNode = new FloatNode(this);
                floatNode.initializeRandom(maxDepth - 1);
                this.exprNode = floatNode;
                this.addChild(floatNode);
                break;
            case 2:
                AndNode andNode = new AndNode(this);
                andNode.initializeRandom(maxDepth - 1);
                this.exprNode = andNode;
                this.addChild(andNode);
                break;
            case 3:
                OrNode orNode = new OrNode(this);
                orNode.initializeRandom(maxDepth - 1);
                this.exprNode = orNode;
                this.addChild(orNode);
                break;
            case 4:
                NotNode notNode = new NotNode(this);
                notNode.initializeRandom(maxDepth - 1);
                this.exprNode = notNode;
                this.addChild(notNode);
                break;
            case 5:
                PlusNode plusNode = new PlusNode(this);
                plusNode.initializeRandom(maxDepth - 1);
                this.exprNode = plusNode;
                this.addChild(plusNode);
                break;
            case 6:
                MinusNode minusNode = new MinusNode(this);
                minusNode.initializeRandom(maxDepth - 1);
                this.exprNode = minusNode;
                this.addChild(minusNode);
                break;
            case 7:
                DivisionNode divisionNode = new DivisionNode(this);
                divisionNode.initializeRandom(maxDepth - 1);
                this.exprNode = divisionNode;
                this.addChild(divisionNode);
                break;
            case 8:
                MultNode multNode = new MultNode(this);
                multNode.initializeRandom(maxDepth - 1);
                this.exprNode = multNode;
                this.addChild(multNode);
                break;
            case 9:
                ModuloNode moduloNode = new ModuloNode(this);
                moduloNode.initializeRandom(maxDepth - 1);
                this.exprNode = moduloNode;
                this.addChild(moduloNode);
                break;
            case 10:
                LookNode lookNode = new LookNode(this);
                lookNode.initializeRandom(maxDepth - 1);
                this.exprNode = lookNode;
                this.addChild(lookNode);
                break;
        }
    }

    @Override
    public void addChild(Node child) {
        ControlStructures childControlStructure = child.getControlStructure();
        if(this.childrenControlStructures.contains(childControlStructure)){
            this.nodesByControlStructure.get(childControlStructure).add(child);
        }else{
            List<Node> children = new ArrayList<>();
            children.add(child);
            this.nodesByControlStructure.put(childControlStructure, children);
            this.childrenControlStructures.add(childControlStructure);
        }
        this.children.add(child);
    }

    @Override
    public List<Node> getChildren() {
        return this.children;
    }

    @Override
    public void printAtIndent(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        this.varaiableNode.printAtIndent(indent);
        System.out.print(" = ");
        this.exprNode.printAtIndent(indent);
        System.out.println(";");
    }
}
