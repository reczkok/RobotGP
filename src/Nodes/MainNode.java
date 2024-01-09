package Nodes;

import java.io.PrintWriter;
import java.util.*;

public class MainNode implements Node{
    private List<Node> children;
    private Node parent;
    private ControlStructures controlStructure;
    private int depth;

    public MainNode(Node parent) {
        this.children = new ArrayList<>();
        this.parent = parent;
        this.controlStructure = ControlStructures.MAIN;
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    @Override
    public List<Node> getChildren() {
        return this.children;
    }

    @Override
    public void printAtIndent(int indent) {
        System.out.println("main(){");
        for(Node child : this.children){
            child.printAtIndent(indent + 1);
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
        MainNode mainNode = new MainNode(parent);
        for(Node child : this.children){
            Node childCopy = child.copy(mainNode);
            mainNode.addChild(childCopy);
        }
        return mainNode;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(this.children.contains(oldChild)){
            this.children.remove(oldChild);
            this.children.add(newChild);
        } else {
            throw new UnsupportedOperationException("MainNode does not contain oldChild");
        }
    }

    @Override
    public List<ControlStructures> getLegalAlternatives(Node child) {
        if(this.children.contains(child)){
            return Arrays.asList(ControlStructures.IF, ControlStructures.LOOP, ControlStructures.ASSIGNMENT, ControlStructures.OUTPUT);
        } else {
            throw new UnsupportedOperationException("MainNode does not contain child");
        }
    }

    @Override
    public void printAtIndent(int i, PrintWriter printWriter) {
        printWriter.println("main(){");
        for(Node child : this.children){
            child.printAtIndent(i + 1, printWriter);
        }
        printWriter.println("}");
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void initializeRandom(int maxDepth) {
        if (maxDepth == 0) {
            return;
        }
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                IfNode ifNode = new IfNode(this);
                ifNode.initializeRandom(maxDepth - 1);
                this.addChild(ifNode);
                break;
            case 1:
                LoopNode loopNode = new LoopNode(this);
                loopNode.initializeRandom(maxDepth - 1);
                this.addChild(loopNode);
                break;
            case 2:
                AssignmentNode assignmentNode = new AssignmentNode(this);
                assignmentNode.initializeRandom(maxDepth - 1);
                this.addChild(assignmentNode);
                break;
            case 3:
                OutputNode outputNode = new OutputNode(this);
                outputNode.initializeRandom(maxDepth - 1);
                this.addChild(outputNode);
                break;
        }
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
}
