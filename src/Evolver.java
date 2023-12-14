import Nodes.ControlStructures;
import Nodes.Node;
import Nodes.TwoArgOpNode;

import java.util.*;

public class Evolver {
    List<Program> programs;

    public Evolver() {
        this.programs = new ArrayList<>();
        Program p1test = ProgramFactory.generateRandomProgram();
        Program p2test = ProgramFactory.generateRandomProgram();
        List<Program> out = this.crossOver(p1test, p2test);
        System.out.println("-----------------P1-----------------");
        p1test.print();
        out.get(0).print();
        System.out.println("-----------------P2-----------------");
        p2test.print();
        out.get(1).print();
    }

    public List<Program> doCrossOver(Program p1, Program p2, Set<ControlStructures> common){
        Program p1Copy = ProgramFactory.copyProgram(p1);
        Program p2Copy = ProgramFactory.copyProgram(p2);
        ControlStructures crossoverPoint = common.iterator().next();
        List<Node> p1Nodes = p1Copy.getNodesOfType(crossoverPoint);
        List<Node> p2Nodes = p2Copy.getNodesOfType(crossoverPoint);
        Node p1Node = p1Nodes.get(0);
        Node p2Node = p2Nodes.get(0);
        Node p1Parent = p1Node.getParent();
        Node p2Parent = p2Node.getParent();
        swapHelper(p1Parent, p1Node, p2Node);
        swapHelper(p2Parent, p2Node, p1Node);
        List<Program> children = new ArrayList<>();
        children.add(p1Copy);
        children.add(p2Copy);
        return children;
    }

    private void swapHelper(Node parent, Node oldChild, Node newChild){
        ControlStructures parentControlStructure = parent.getControlStructure();
        System.out.println(parentControlStructure);
        switch (parentControlStructure){
            case AND, PLUS, MINUS, OR, DIVISION, MULTIPLY, MODULO, CONDITION:
                System.out.print("Swapped ");
                oldChild.printAtIndent(0);
                System.out.print(" with ");
                newChild.printAtIndent(0);
                System.out.println();
                ((TwoArgOpNode) parent).replaceChild(oldChild, newChild);
                break;
        }
    }

    public List<Program> crossOver(Program p1, Program p2){
        Set<ControlStructures> p1ControlStructures = p1.getChildrenControlStructures();
        Set<ControlStructures> p2ControlStructures = p2.getChildrenControlStructures();
        Set<ControlStructures> p1Copy = new HashSet<>(p1ControlStructures);
        p1Copy.retainAll(p2ControlStructures);
        if(!p1Copy.isEmpty()) {
            return this.doCrossOver(p1, p2, p1Copy);
        }
        return null;
    }

    private void generateInitialPopulation() {
        for(int i = 0; i < Parameters.populationSize; i++) {
            programs.add(ProgramFactory.generateRandomProgram());
        }
    }
}
