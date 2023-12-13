package Nodes;

import java.util.ArrayList;
import java.util.List;

public class Node {
    Node parent = null;
    List<Node> children = null;
    ControlStructures controlStructure = null;

    public Node(Node parent, List<Node> children, ControlStructures controlStructure) {
        this.parent = parent;
        this.children = children;
        this.controlStructure = controlStructure;
    }

    public Node(Node parent, ControlStructures controlStructure) {
        this.parent = parent;
        this.controlStructure = controlStructure;
        this.children = new ArrayList<>();
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setChildren(List<Node> children){
        this.children = children;
    }
    public List<Node> getChildren(){
        return children;
    }
    public void addChild(Node child){
        children.add(child);
    }
    public ControlStructures getControlStructure(){
        return controlStructure;
    }
    public List<Node> getChildrenByControlStructure(ControlStructures controlStructure){
        List<Node> childrenByControlStructure = new ArrayList<>();
        for(Node child: children){
            if(child.getControlStructure() == controlStructure)
                childrenByControlStructure.add(child);
        }
        return childrenByControlStructure;
    }
    public boolean isLiteral(){
        return controlStructure == ControlStructures.INT || controlStructure == ControlStructures.FLOAT || controlStructure == ControlStructures.ID;
    }
}
