package Nodes;

import java.util.*;

public class MainNode implements Node{
    private List<Node> children;
    private Node parent;
    private ControlStructures controlStructure;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;
    private int depth;

    public MainNode(Node parent) {
        this.children = new ArrayList<>();
        this.nodesByControlStructure = new HashMap<>();
        this.parent = parent;
        this.controlStructure = ControlStructures.MAIN;
        this.childrenControlStructures = new HashSet<>();
    }

    public void addChild(Node child){
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
                MoveNode moveNode = new MoveNode(this);
                moveNode.initializeRandom(maxDepth - 1);
                this.addChild(moveNode);
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
    public List<Node> getChildrenByControlStructure(ControlStructures controlStructure) {
        if(this.childrenControlStructures.contains(controlStructure)){
            return this.nodesByControlStructure.get(controlStructure);
        }
        return null;
    }

    @Override
    public boolean isLiteral() {
        return false;
    }
}
