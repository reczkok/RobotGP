package Nodes;

import java.util.*;

public class LoopNode implements Node{
    private Node condition;
    private List<Node> body;
    private List<Node> children;
    private Node parent;
    private ControlStructures controlStructure;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;
    private int depth;

    public LoopNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.LOOP;
        this.body = new ArrayList<>();
        this.children = new ArrayList<>();
        this.nodesByControlStructure = new HashMap<>();
        this.childrenControlStructures = new HashSet<>();
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
        ConditionNode conditionNode = new ConditionNode(this);
        conditionNode.initializeRandom(maxDepth - 1);
        this.condition = conditionNode;
        this.addChild(conditionNode);
        if (maxDepth <= 0) {
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
                MoveNode moveNode = new MoveNode(this);
                moveNode.initializeRandom(maxDepth - 1);
                this.addChild(moveNode);
                this.body.add(moveNode);
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
}
