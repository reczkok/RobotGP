package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class LoopNode implements Node{
    private Node condition;
    private List<Node> body;
    private List<Node> children;
    private Node parent;
    private ControlStructures controlStructure;
    private int depth;

    public LoopNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.LOOP;
        this.body = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public LoopNode(Node parent, Node condition){
        this.parent = parent;
        this.controlStructure = ControlStructures.LOOP;
        this.condition = condition;
        this.children = new ArrayList<>();
        this.body = new ArrayList<>();
        this.addChild(condition);
    }

    public void addToBody(Node node){
        this.body.add(node);
        this.addChild(node);
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
        this.condition = conditionNode;
        this.addChild(conditionNode);
        int bodySize = (int) (Math.random() * NodeParameters.maxDefinitionChildren) + 1;
        for (int i = 0; i < bodySize; i++) {
            if (maxDepth <= 3) {
                int random = (int) (Math.random() * 2);
                switch (random) {
                    case 0:
                        AssignmentNode assignmentNode = new AssignmentNode(this);
                        assignmentNode.initializeRandom(maxDepth - 1);
                        this.addChild(assignmentNode);
                        this.body.add(assignmentNode);
                        break;
                    case 1:
                        OutputNode outputNode = new OutputNode(this);
                        outputNode.initializeRandom(maxDepth - 1);
                        this.addChild(outputNode);
                        this.body.add(outputNode);
                        break;
                }
                return;
            }
            int random = (int) (Math.random() * 4);
            switch (random) {
                case 0:
                    IfNode ifNode = new IfNode(this);
                    ifNode.initializeRandom(maxDepth - 1);
                    this.addChild(ifNode);
                    this.body.add(ifNode);
                    break;
                case 1:
                    LoopNode loopNode = new LoopNode(this);
                    loopNode.initializeRandom(maxDepth - 1);
                    this.addChild(loopNode);
                    this.body.add(loopNode);
                    break;
                case 2:
                    AssignmentNode assignmentNode = new AssignmentNode(this);
                    assignmentNode.initializeRandom(maxDepth - 1);
                    this.addChild(assignmentNode);
                    this.body.add(assignmentNode);
                    break;
                case 3:
                    OutputNode outputNode = new OutputNode(this);
                    outputNode.initializeRandom(maxDepth - 1);
                    this.addChild(outputNode);
                    this.body.add(outputNode);
                    break;
            }
        }
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
        System.out.print("while(");
        this.condition.printAtIndent(indent);
        System.out.println("){");
        for(Node child : this.body){
            child.printAtIndent(indent + 1);
        }
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println("}");
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
        LoopNode loopNode = new LoopNode(parent);
        Node conditionCopy = this.condition.copy(loopNode);
        loopNode.addChild(conditionCopy);
        loopNode.condition = conditionCopy;
        if(!this.body.isEmpty()){
            for(Node child : this.body){
                Node childCopy = child.copy(loopNode);
                loopNode.addChild(childCopy);
                loopNode.body.add(childCopy);
            }
        }
        return loopNode;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.condition == oldChild){
            this.condition = newChild;
        }else if(this.body.contains(oldChild)){
            this.body.remove(oldChild);
            this.body.add(newChild);
        }else {
            throw new RuntimeException("Invalid child");
        }
        this.children.remove(oldChild);
        this.addChild(newChild);
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        if(this.condition == child){
            return Arrays.asList(ControlStructures.CONDITION);
        }else if(this.body.contains(child)){
            if(depth>=4) return Arrays.asList(ControlStructures.IF, ControlStructures.LOOP, ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
            else return Arrays.asList(ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
        }else {
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        printWriter.print("while(");
        this.condition.printAtIndent(i, printWriter);
        printWriter.println("){");
        for(Node child : this.body){
            child.printAtIndent(i + 1, printWriter);
        }
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        printWriter.println("}");
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("while(");
        this.condition.printAtIndent(i, stringBuilder);
        stringBuilder.append("){");
        for(Node child : this.body){
            child.printAtIndent(i + 1, stringBuilder);
        }
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("}");
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    private Node getRandomCondition(int maxDepth) {
        if(maxDepth <= 2){
            ConditionNode conditionNode = new ConditionNode(this);
            conditionNode.initializeRandom(maxDepth - 1);
            return conditionNode;
        }else {
            int random = (int) (Math.random() * 4);
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
                case 3:
                    NotNode notNode = new NotNode(this);
                    notNode.initializeRandom(maxDepth - 1);
                    return notNode;
            }
            throw new RuntimeException("Invalid random number");
        }
    }
}
