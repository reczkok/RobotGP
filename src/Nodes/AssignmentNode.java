package Nodes;

import java.io.PrintWriter;
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
    private int depth;


    public AssignmentNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.ASSIGNMENT;
        this.children = new ArrayList<>();
    }

    public AssignmentNode(Node parent, Node variable, Node expression){
        this.parent = parent;
        this.controlStructure = ControlStructures.ASSIGNMENT;
        this.children = new ArrayList<>();
        this.varaiableNode = variable;
        this.exprNode = expression;
        this.addChild(variable);
        this.addChild(expression);
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
        IdNode idNode = new IdNode(this);
        idNode.initializeRandom(maxDepth - 1);
        this.varaiableNode = idNode;
        this.addChild(idNode);
        Node exprNode = this.getRandomNode(maxDepth - 1);
        this.exprNode = exprNode;
        this.addChild(exprNode);
    }

    @Override
    public void addChild(Node child) {
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
        AssignmentNode copy = new AssignmentNode(parent);
        Node variableNodeCopy = this.varaiableNode.copy(copy);
        Node exprNodeCopy = this.exprNode.copy(copy);
        copy.addChild(variableNodeCopy);
        copy.addChild(exprNodeCopy);
        copy.exprNode = exprNodeCopy;
        copy.varaiableNode = variableNodeCopy;
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.varaiableNode == oldChild){
            this.varaiableNode = newChild;
        }
        if(this.exprNode == oldChild){
            this.exprNode = newChild;
        }
        this.children.remove(oldChild);
        this.addChild(newChild);
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        if (child == this.varaiableNode) {
            return Arrays.asList(ControlStructures.ID);
        } else if (child == this.exprNode) {
            if(depth > 1) return Arrays.asList(ControlStructures.ID, ControlStructures.INT, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.PLUS, ControlStructures.MINUS, ControlStructures.DIVISION, ControlStructures.MULTIPLY, ControlStructures.MODULO, ControlStructures.INPUT);
            else return Arrays.asList(ControlStructures.ID, ControlStructures.INT, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.INPUT);
        } else {
            throw new UnsupportedOperationException("AssignmentNode does not contain child");
        }
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        this.varaiableNode.printAtIndent(i, printWriter);
        printWriter.print(" = ");
        this.exprNode.printAtIndent(i, printWriter);
        printWriter.println(";");
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        this.varaiableNode.printAtIndent(i, stringBuilder);
        stringBuilder.append(" = ");
        this.exprNode.printAtIndent(i, stringBuilder);
        stringBuilder.append(";");
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    private Node getRandomNode(int maxDepth) {
        if(maxDepth <= 1){
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
}
