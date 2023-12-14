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
    }

    public void print(){
        this.root.printAtIndent(0);
    }

    public void updateTreeInfo(){

    }
}
