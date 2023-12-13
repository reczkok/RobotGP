import Nodes.ControlStructures;
import Nodes.IfNode;
import Nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    private List<Node> nodes;
    private Node root;
    private Map<ControlStructures, List<Node>> nodesByControlStructure;
    private double fitness;
    private int depth;

    public Program(Node root) {
        this.root = root;
        this.nodes = new ArrayList<>();
        this.nodes.add(root);
        this.nodesByControlStructure = new HashMap<>();
        this.fitness = 0;
        this.depth = 0;
    }


}
