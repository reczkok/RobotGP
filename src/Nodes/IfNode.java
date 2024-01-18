package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class IfNode implements Node{
    private Node condition;
    private List<Node> children;
    private List<Node> ifBody;
    private List<Node> elseBody;
    private Node parent;
    private ControlStructures controlStructure;
    private int depth;

    public IfNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.IF;
        this.ifBody = new ArrayList<>();
        this.elseBody = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public IfNode(Node parent, Node condition){
        this.parent = parent;
        this.controlStructure = ControlStructures.IF;
        this.condition = condition;
        this.ifBody = new ArrayList<>();
        this.elseBody = new ArrayList<>();
        this.children = new ArrayList<>();
        this.addChild(condition);
    }

    public void addToBody(Node node){
        this.ifBody.add(node);
        this.addChild(node);
    }

    public void addToElseBody(Node node){
        this.elseBody.add(node);
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
                        this.ifBody.add(assignmentNode);
                        break;
                    case 1:
                        OutputNode outputNode = new OutputNode(this);
                        outputNode.initializeRandom(maxDepth - 1);
                        this.addChild(outputNode);
                        this.ifBody.add(outputNode);
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
                    this.ifBody.add(ifNode);
                    break;
                case 1:
                    LoopNode loopNode = new LoopNode(this);
                    loopNode.initializeRandom(maxDepth - 1);
                    this.addChild(loopNode);
                    this.ifBody.add(loopNode);
                    break;
                case 2:
                    AssignmentNode assignmentNode = new AssignmentNode(this);
                    assignmentNode.initializeRandom(maxDepth - 1);
                    this.addChild(assignmentNode);
                    this.ifBody.add(assignmentNode);
                    break;
                case 3:
                    OutputNode outputNode = new OutputNode(this);
                    outputNode.initializeRandom(maxDepth - 1);
                    this.addChild(outputNode);
                    this.ifBody.add(outputNode);
                    break;
            }
        }
        int random = (int) (Math.random() * 2);
        if (random == 0){
            bodySize = (int) (Math.random() * NodeParameters.maxDefinitionChildren) + 1;
            for (int i = 0; i < bodySize; i++) {
                random = (int) (Math.random() * 4);
                switch (random) {
                    case 0:
                        IfNode ifNode = new IfNode(this);
                        ifNode.initializeRandom(maxDepth - 1);
                        this.addChild(ifNode);
                        this.elseBody.add(ifNode);
                        break;
                    case 1:
                        LoopNode loopNode = new LoopNode(this);
                        loopNode.initializeRandom(maxDepth - 1);
                        this.addChild(loopNode);
                        this.elseBody.add(loopNode);
                        break;
                    case 2:
                        AssignmentNode assignmentNode = new AssignmentNode(this);
                        assignmentNode.initializeRandom(maxDepth - 1);
                        this.addChild(assignmentNode);
                        this.elseBody.add(assignmentNode);
                        break;
                    case 3:
                        OutputNode outputNode = new OutputNode(this);
                        outputNode.initializeRandom(maxDepth - 1);
                        this.addChild(outputNode);
                        this.elseBody.add(outputNode);
                        break;
                }
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
        System.out.print("if(");
        this.condition.printAtIndent(indent + 1);
        System.out.println("){");
        for(Node child : this.ifBody){
            child.printAtIndent(indent + 1);
        }
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        if(!this.elseBody.isEmpty()){
            System.out.println("}else{");
            for(Node child : this.elseBody){
                child.printAtIndent(indent + 1);
            }
            for (int i = 0; i < indent; i++) {
                System.out.print("\t");
            }
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
        IfNode copy = new IfNode(parent);
        Node conditionCopy = this.condition.copy(copy);
        copy.addChild(conditionCopy);
        copy.condition = conditionCopy;
        if(!this.ifBody.isEmpty()){
            for(Node child : this.ifBody){
                Node childCopy = child.copy(copy);
                copy.addChild(childCopy);
                copy.ifBody.add(childCopy);
            }
        }
        if(!this.elseBody.isEmpty()) {
            for (Node child : this.elseBody) {
                Node childCopy = child.copy(copy);
                copy.addChild(childCopy);
                copy.elseBody.add(childCopy);
            }
        }
        return copy;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.condition == oldChild){
            this.condition = newChild;
        } else if(this.ifBody.contains(oldChild)){
            this.ifBody.remove(oldChild);
            this.ifBody.add(newChild);
        } else if(this.elseBody.contains(oldChild)){
            this.elseBody.remove(oldChild);
            this.elseBody.add(newChild);
        } else {
            throw new RuntimeException("Invalid child");
        }
        this.children.remove(oldChild);
        this.addChild(newChild);
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child, int depth) {
        if(this.condition == child){
            return Arrays.asList(ControlStructures.CONDITION);
        } else if(this.ifBody.contains(child)){
            if(depth>=4) return Arrays.asList(ControlStructures.IF, ControlStructures.LOOP, ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
            else return Arrays.asList(ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
        } else if(this.elseBody.contains(child)){
            if(depth>=4) return Arrays.asList(ControlStructures.IF, ControlStructures.LOOP, ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
            else return Arrays.asList(ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
        } else {
            throw new RuntimeException("Invalid child");
        }
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        printWriter.print("if(");
        this.condition.printAtIndent(i + 1, printWriter);
        printWriter.println("){");
        for(Node child : this.ifBody){
            child.printAtIndent(i + 1, printWriter);
        }
        for (int j = 0; j < i; j++) {
            printWriter.print("\t");
        }
        if(!this.elseBody.isEmpty()){
            printWriter.println("}else{");
            for(Node child : this.elseBody){
                child.printAtIndent(i + 1, printWriter);
            }
            for (int j = 0; j < i; j++) {
                printWriter.print("\t");
            }
        }
        printWriter.println("}");
    }

    @Override
    public void printAtIndent(int i, StringBuilder stringBuilder) {
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        stringBuilder.append("if(");
        this.condition.printAtIndent(i + 1, stringBuilder);
        stringBuilder.append("){");
        for(Node child : this.ifBody){
            child.printAtIndent(i + 1, stringBuilder);
        }
        for (int j = 0; j < i; j++) {
            stringBuilder.append("\t");
        }
        if(!this.elseBody.isEmpty()){
            stringBuilder.append("}else{");
            for(Node child : this.elseBody){
                child.printAtIndent(i + 1, stringBuilder);
            }
            for (int j = 0; j < i; j++) {
                stringBuilder.append("\t");
            }
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
