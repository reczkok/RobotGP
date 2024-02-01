package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class OutputNode implements Node{
    private Node exprNode;
    private Node parent;
    private ControlStructures controlStructure;
    private int depth;


    public OutputNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.OUTPUT;
    }

    public OutputNode(Node parent, Node expression){
        this.parent = parent;
        this.controlStructure = ControlStructures.OUTPUT;
        this.exprNode = expression;
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
        if(maxDepth <= 1){
            int random = (int) (Math.random() * 4);
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
                    BoolNode boolNode = new BoolNode(this);
                    boolNode.initializeRandom(maxDepth - 1);
                    this.exprNode = boolNode;
                    this.addChild(boolNode);
                    break;
                case 3:
                    IntNode intNode = new IntNode(this);
                    intNode.initializeRandom(maxDepth - 1);
                    this.exprNode = intNode;
                    this.addChild(intNode);
                    break;
            }
            return;
        }
        int random = (int) (Math.random() * 9);
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
                BoolNode boolNode = new BoolNode(this);
                boolNode.initializeRandom(maxDepth - 1);
                this.exprNode = boolNode;
                this.addChild(boolNode);
                break;
            case 3:
                PlusNode plusNode = new PlusNode(this);
                plusNode.initializeRandom(maxDepth - 1);
                this.exprNode = plusNode;
                this.addChild(plusNode);
                break;
            case 4:
                MinusNode minusNode = new MinusNode(this);
                minusNode.initializeRandom(maxDepth - 1);
                this.exprNode = minusNode;
                this.addChild(minusNode);
                break;
            case 5:
                DivisionNode divisionNode = new DivisionNode(this);
                divisionNode.initializeRandom(maxDepth - 1);
                this.exprNode = divisionNode;
                this.addChild(divisionNode);
                break;
            case 6:
                MultNode multNode = new MultNode(this);
                multNode.initializeRandom(maxDepth - 1);
                this.exprNode = multNode;
                this.addChild(multNode);
                break;
            case 7:
                ModuloNode moduloNode = new ModuloNode(this);
                moduloNode.initializeRandom(maxDepth - 1);
                this.exprNode = moduloNode;
                this.addChild(moduloNode);
                break;
            case 8:
                IntNode intNode = new IntNode(this);
                intNode.initializeRandom(maxDepth - 1);
                this.exprNode = intNode;
                this.addChild(intNode);
                break;
        }
    }

    @Override
    public void addChild(Node child) {
        this.exprNode = child;
    }

    @Override
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        children.add(this.exprNode);
        return children;
    }

    @Override
    public void printAtIndent(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.print("out(");
        this.exprNode.printAtIndent(indent);
        System.out.println(");");
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
        OutputNode copy = new OutputNode(parent);
        Node exprNodeCopy = this.exprNode.copy(copy);
        copy.addChild(exprNodeCopy);
        copy.exprNode = exprNodeCopy;
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.exprNode == oldChild){
            this.exprNode = newChild;
        }else{
            throw new RuntimeException("Invalid old child");
        }
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        if(depth > 2)return Arrays.asList(ControlStructures.ID, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.PLUS, ControlStructures.MINUS, ControlStructures.DIVISION, ControlStructures.MULTIPLY, ControlStructures.MODULO, ControlStructures.INT);
        else return Arrays.asList(ControlStructures.ID, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.INT);
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        printWriter.print("out(");
        this.exprNode.printAtIndent(i, printWriter);
        printWriter.println(");");
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("out(");
        this.exprNode.printAtIndent(i, stringBuilder);
        stringBuilder.append(");");
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
