package GP;

import Nodes.ControlStructures;
import Nodes.MainNode;
import Nodes.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        updateTreeInfo();
    }

    public void initializeRandom(int maxDepth, int minNodes){
        while (this.depth < maxDepth || this.nodes.size() < minNodes){
            this.root.initializeRandom(maxDepth);
            this.updateTreeInfo();
        }
    }

    public void print(){
        this.root.printAtIndent(0);
    }

    public void printToFile(String fileName){
        File file = new File(fileName);
        try {
            boolean created = file.createNewFile();
            if(!created){
                file.delete();
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            this.root.printAtIndent(0, printWriter);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerNode(Node node, int depth){
        ControlStructures childControlStructure = node.getControlStructure();
        node.setDepth(depth);
        if(childControlStructure == ControlStructures.MAIN){
            this.nodes.add(node);
            return;
        }
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
        this.nodes.clear();
        this.nodesByControlStructure.clear();
        this.childrenControlStructures.clear();
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

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    public double getFitness(){
        return this.fitness;
    }

    public Node getRandomNode(){
        int random = (int) (Math.random() * this.nodes.size());
        return this.nodes.get(random);
    }

    public boolean hasNodeOfType(ControlStructures controlStructure){
        return this.childrenControlStructures.contains(controlStructure);
    }

    public List<Node> getNodesOfType(ControlStructures controlStructure){
        return this.nodesByControlStructure.get(controlStructure);
    }

    public Node getRandomNodeOfType(ControlStructures controlStructure){
        List<Node> nodes = this.nodesByControlStructure.get(controlStructure);
        int random = (int) (Math.random() * nodes.size());
        return nodes.get(random);
    }

    public Node mutateNode(Node node){
        Node parent = node.getParent();
        List<ControlStructures> possibleControlStructures = parent.getLegalAlternatives(node);
        int random = (int) (Math.random() * possibleControlStructures.size());
        ControlStructures newControlStructure = possibleControlStructures.get(random);
        Node newNode = NodeFactory.getNodeOfControlStructure(newControlStructure, parent);
        newNode.initializeRandom(this.depth - newNode.getDepth());
        parent.replaceChild(node, newNode);
        this.updateTreeInfo();
        return newNode;
    }

    public Set<ControlStructures> getChildrenControlStructures() {
        return this.childrenControlStructures;
    }

    public Program copy(){
        MainNode rootCopy = (MainNode) this.root.copy(null);
        Program programCopy = new Program(rootCopy);
        programCopy.updateTreeInfo();
        return programCopy;
    }

    public MainNode getRoot() {
        return this.root;
    }
}
