import Nodes.ControlStructures;
import Nodes.IfNode;
import Nodes.MainNode;
import Nodes.Node;

import java.util.*;

public class Program {
    private List<Node> nodes;
    private MainNode root;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private Set<ControlStructures> childrenControlStructures;
    private double fitness;
    private int depth;

    public Program(MainNode root) {
        this.root = root;
        this.nodes = new ArrayList<>();
        this.nodes.add(root);
        this.nodesByControlStructure = new HashMap<>();
        this.childrenControlStructures = new HashSet<>();
        this.fitness = 0;
        this.depth = 0;
    }

    public void initializeRandom(int maxDepth){
        this.root.initializeRandom(maxDepth);
        this.updateTreeInfo();
    }

    public void print(){
        this.root.printAtIndent(0);
    }

    private void registerNode(Node node, int depth){
        ControlStructures childControlStructure = node.getControlStructure();
        node.setDepth(depth);
        if(this.childrenControlStructures.contains(childControlStructure)){
            this.nodesByControlStructure.get(childControlStructure).add(node);
        }else{
            List<Node> children = new ArrayList<>();
            children.add(node);
            this.nodesByControlStructure.put(childControlStructure, children);
            this.childrenControlStructures.add(childControlStructure);
        }
        this.nodes.add(node);
    }

    public void updateTreeInfo(){
        List<Node> currentNodes = new ArrayList<>();
        currentNodes.add(this.root);
        int depth = 0;
        while(!currentNodes.isEmpty()){
            List<Node> nextNodes = new ArrayList<>();
            for(Node node : currentNodes){
                this.registerNode(node, depth);
                List<Node> children = node.getChildren();
                nextNodes.addAll(children);
            }
            currentNodes = nextNodes;
            depth++;
        }
        this.depth = depth;
    }
}
