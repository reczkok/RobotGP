package Nodes;

import java.util.*;

public class IfNode implements Node{
    private Node condition;
    private List<Node> children;
    private List<Node> ifBody;
    private List<Node> elseBody;
    private Node parent;
    private ControlStructures controlStructure;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;
    private int depth;

    public IfNode(Node parent) {
        this.parent = parent;
        this.controlStructure = ControlStructures.IF;
        this.ifBody = new ArrayList<>();
        this.elseBody = new ArrayList<>();
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
                MoveNode moveNode = new MoveNode(this);
                moveNode.initializeRandom(maxDepth - 1);
                this.addChild(moveNode);
                this.ifBody.add(moveNode);
                break;
        }
        random = (int) (Math.random() * 2);
        if (random == 0){
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
                    MoveNode moveNode = new MoveNode(this);
                    moveNode.initializeRandom(maxDepth - 1);
                    this.addChild(moveNode);
                    this.elseBody.add(moveNode);
                    break;
            }
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
}
