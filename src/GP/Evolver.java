package GP;

import Interpreter.Language.Deserializer;
import Nodes.*;

import java.util.*;

public class Evolver {
    List<Program> programs;
    int[] integers = new int[NodeParameters.intAmount];
    float[] floats = new float[NodeParameters.floatAmount];
    ProgramVariable[] variables = new ProgramVariable[NodeParameters.varAmount];

    public Evolver() {
        this.programs = new ArrayList<>();
        Program p1 = ProgramFactory.generateRandomProgram();
        p1.printToFile("p1.txt");
        Program p2 = deserializeProgram("p1.txt");
        p1.print();
        System.out.println("----------------------------");
        p2.print();
    }

    private void initializeVariables() {
        for(int i = 0; i < NodeParameters.varAmount; i++) {
            variables[i] = new ProgramVariable();
        }
    }

    private Program deserializeProgram(String fileName) {
        MainNode root = (MainNode) Deserializer.deserialize(fileName);
        return new Program(root);
    }

    private void testCrossover(){
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

    private void testMutate(){
        Program p1test = ProgramFactory.generateRandomProgram();
        Program out = this.mutate(p1test);
        System.out.println("-----------------P1-----------------");
        p1test.print();
        System.out.println("-----------------P2-----------------");
        out.print();
    }

    public List<Program> doCrossOver(Program p1, Program p2, Set<ControlStructures> common){
        Program p1Copy = ProgramFactory.copyProgram(p1);
        Program p2Copy = ProgramFactory.copyProgram(p2);
        ControlStructures crossoverPoint = common.iterator().next();
        Node p1Node = p1Copy.getRandomNodeOfType(crossoverPoint);
        Node p2Node = p2Copy.getRandomNodeOfType(crossoverPoint);
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
        System.out.println("Parent Control Structure: " + parentControlStructure);
        System.out.print("Swapped ");
        oldChild.printAtIndent(0);
        System.out.println("\nwith: ");
        newChild.printAtIndent(0);
        System.out.println("\n");
        parent.replaceChild(oldChild, newChild);
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

    public Program mutate(Program p){
        Program pCopy = ProgramFactory.copyProgram(p);
        Node randomNode = pCopy.getRandomNode();
        Node mutatedNode = pCopy.mutateNode(randomNode);
        System.out.println("Mutated: ");
        randomNode.printAtIndent(0);
        System.out.println("\ninto: ");
        mutatedNode.printAtIndent(0);
        System.out.println("\n");
        return pCopy;
    }

    public Program tournamentSelection(List<Program> programs, int tournamentSize){
        List<Program> tournament = new ArrayList<>();
        for(int i = 0; i < tournamentSize; i++){
            int random = (int) (Math.random() * programs.size());
            tournament.add(programs.get(random));
        }
        Program best = tournament.get(0);
        for(Program program : tournament){
            if(program.getFitness() > best.getFitness()){
                best = program;
            }
        }
        return best;
    }

    public Program negativeTournamentSelection(List<Program> programs, int tournamentSize){
        List<Program> tournament = new ArrayList<>();
        for(int i = 0; i < tournamentSize; i++){
            int random = (int) (Math.random() * programs.size());
            tournament.add(programs.get(random));
        }
        Program worst = tournament.get(0);
        for(Program program : tournament){
            if(program.getFitness() < worst.getFitness()){
                worst = program;
            }
        }
        return worst;
    }

    private void generateInitialPopulation() {
        for(int i = 0; i < Parameters.populationSize; i++) {
            programs.add(ProgramFactory.generateRandomProgram());
        }
    }
}
