package GP;

import Nodes.ControlStructures;
import Nodes.MainNode;
import Nodes.Node;
import Nodes.NotNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
        while (this.depth <= maxDepth || this.nodes.size() < minNodes){
            this.root.initializeRandom(maxDepth);
            this.updateTreeInfo();
        }
    }

    public int getSize(){
        return this.nodes.size();
    }

    public List<Node> getNodes() {
        return this.nodes;
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

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        this.root.printAtIndent(0, stringBuilder);
        return stringBuilder.toString();
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
        if(node.getParent() == null){
            return node;
        }
        Node parent = node.getParent();
        int desiredDepth = this.depth - node.getDepth() - 1;
        if (desiredDepth < 0) {
            desiredDepth = 0;
        }
        List<ControlStructures> possibleControlStructures = parent.getLegalAlternatives(node, desiredDepth);
        int random = (int) (Math.random() * possibleControlStructures.size());
        ControlStructures newControlStructure = possibleControlStructures.get(random);
        Node newNode = NodeFactory.getNodeOfControlStructure(newControlStructure, parent);
        newNode.initializeRandom(desiredDepth);
        parent.replaceChild(node, newNode);
        this.updateTreeInfo();
        return newNode;
    }

    public Node sizeFair(int desiredSize, Set<ControlStructures> desiredControlStructures){
        List<Node> legalNodes = new ArrayList<>();
        if(desiredControlStructures.isEmpty()){
            return null;
        }
        for(ControlStructures controlStructure : desiredControlStructures){
            legalNodes.addAll(this.nodesByControlStructure.get(controlStructure));
        }
        if(legalNodes.isEmpty()) return null;
        List<Node> smaller = new ArrayList<>();
        List<Node> larger = new ArrayList<>();
        List<Node> equal = new ArrayList<>();
        double smallerAvgSize = 0;
        double largerAvgSize = 0;
        for(Node node : legalNodes){
            int size = this.getNodeSize(node);
            if(size < desiredSize){
                smaller.add(node);
                smallerAvgSize += size;
            }else if(size > desiredSize){
                larger.add(node);
                largerAvgSize += size;
            }else{
                equal.add(node);
            }
        }
        if(smaller.isEmpty() || larger.isEmpty()){
            if(equal.isEmpty()) return null;
            int random = (int) (Math.random() * equal.size());
            return legalNodes.get(random);
        }
        smallerAvgSize /= smaller.size();
        largerAvgSize /= larger.size();

        double p0 = 1.0/desiredSize;
        if(equal.isEmpty()) p0 = 0;
        double pPlus = (1.0 - p0) / (larger.size()*(1.0+(largerAvgSize/smallerAvgSize)));
        //double pMinus = (1.0 - p0) / (smaller.size()*(1.0+(smallerAvgSize/largerAvgSize)));
        double random = Math.random();
        if(random < p0){
            int randomIndex = (int) (Math.random() * equal.size());
            return equal.get(randomIndex);
        }else if(random < p0 + pPlus){
            int randomIndex = (int) (Math.random() * larger.size());
            return larger.get(randomIndex);
        }else{
            int randomIndex = (int) (Math.random() * smaller.size());
            return smaller.get(randomIndex);
        }
    }

    public int getNodeSize(Node node){
        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        int size = 0;
        while(!nodes.isEmpty()){
            Node currentNode = nodes.remove(0);
            size++;
            nodes.addAll(currentNode.getChildren());
        }
        return size;
    }

    public Set<ControlStructures> getChildrenControlStructures() {
        return this.childrenControlStructures;
    }

    public Program copy(){
        MainNode rootCopy = (MainNode) this.root.copy(null);
        return new Program(rootCopy);
    }

    public MainNode getRoot() {
        return this.root;
    }
}
