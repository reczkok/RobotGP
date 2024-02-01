package GP;

import Interpreter.Language.Deserializer;
import Interpreter.Language.Evaluator;
import Interpreter.Language.Evaluators.Evaluator11A;
import Interpreter.Language.Evaluators.Evaluator12B;
import Interpreter.Language.Interpreter;
import Interpreter.Language.ProgramOutput;
import Nodes.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Evolver {
    List<Program> programs;
    double bestFitnessGlobal;
    Evaluator evaluator;
    ProgramInfo programInfo;

    public Evolver(Evaluator evaluator) {
        this.programs = new ArrayList<>();
        this.bestFitnessGlobal = Double.POSITIVE_INFINITY;
        this.evaluator = evaluator;
        this.programInfo = new ProgramInfo();
        evolve();
        this.programInfo.toCSV("programInfo.csv");
    }

    public void evolve(){
        this.generateInitialPopulation();
        for(int i = 0; i < Parameters.maxGenerations; i++){
            if(bestFitnessGlobal < Parameters.fitnessThreshold){
                System.out.println("Threshold reached");
                printBestPop();
                printBestPopOutput();
                return;
            }
//            for(Program program : this.programs){
//                double fitness = evaluateProgram(program);
//                program.setFitness(fitness);
//                if(fitness < bestFitnessGlobal){
//                    bestFitnessGlobal = fitness;
//                }
//            }
            System.out.println("Generation: " + i);
            printStats();
            System.out.println("----------------------------");
            for(int j = 0; j < Parameters.populationSize; j++) {
                float random = (float) Math.random();
                Program candidate = null;
                if (random < Parameters.crossoverProbability) {
                    Program p1 = tournamentSelection(this.programs, Parameters.tournamentSize);
                    Program p2 = tournamentSelection(this.programs, Parameters.tournamentSize);
                    List<Program> children = this.crossOver(p1, p2);
                    int attempts = 0;
                    while (children == null){
                        if(attempts > 50){
                            break;
                        }
                        if(attempts % 10 == 0){
                            p1 = tournamentSelection(this.programs, Parameters.tournamentSize);
                            p2 = tournamentSelection(this.programs, Parameters.tournamentSize);
                        }
                        children = this.crossOver(p1, p2);
                        attempts++;
                    }
                    if (children != null) {
                        Program child1 = children.get(0);
                        Program child2 = children.get(1);
                        int child1Size = child1.getSize();
                        int child2Size = child2.getSize();
                        double child1Fitness = evaluateProgram(child1);
                        double child2Fitness = evaluateProgram(child2);
                        if (child1Fitness < child2Fitness) {
                            child1.setFitness(child1Fitness);
                            candidate = child1;
                        } else {
                            child2.setFitness(child2Fitness);
                            candidate = child2;
                        }
                    }
                } else {
                    Program p1 = tournamentSelection(this.programs, Parameters.tournamentSize);
                    int p1Size = p1.getSize();
                    candidate = this.mutate(p1);
                    int candidateSize = candidate.getSize();
                    double candidateFitness = evaluateProgram(candidate);
                    candidate.setFitness(candidateFitness);
                    if (candidateSize > p1Size * 1.5) {
                        //System.out.println("Size diff = " + (candidateSize - p1Size));
                        candidate = null;
                    }
                }
                if (candidate != null) {
                    Program toReplace = negativeTournamentSelection(this.programs, Parameters.tournamentSize);
                    this.programs.remove(toReplace);
                    this.programs.add(candidate);
                    if (candidate.getFitness() < bestFitnessGlobal) {
                        bestFitnessGlobal = candidate.getFitness();
                    }
                }
            }
        }
        System.out.println("Max generations reached");
        printBestPop();
        printBestPopOutput();
    }

    private void printBestPopOutput() {
        double bestFitness = Double.POSITIVE_INFINITY;
        Program bestProgram = null;
        for(Program program : this.programs){
            double fitness = program.getFitness();
            if(fitness < bestFitness){
                bestFitness = fitness;
                bestProgram = program;
            }
        }
        if (bestProgram != null) {
            ProgramOutput programOutput = Interpreter.getProgramOutputs(bestProgram, Parameters.maxEvaluationIterations, evaluator.getEvaluator());
            System.out.println("Best Program Output: ");
            System.out.println(programOutput);
        }
    }

    private void printStats(){
        double bestFitness = Double.POSITIVE_INFINITY;
        double averageFitness = 0;
        double averageSize = 0;
        int infiniteFitness = 0;
        for(Program program : this.programs){
            double fitness = program.getFitness();
            averageSize += program.getSize();
            if(fitness == Double.POSITIVE_INFINITY){
                infiniteFitness++;
                continue;
            }
            if(fitness < bestFitness){
                bestFitness = fitness;
            }
            averageFitness += fitness;
        }
        averageSize /= this.programs.size();
        averageFitness /= (this.programs.size() - infiniteFitness);
        System.out.println("Best Fitness: " + bestFitness);
        System.out.println("Average Fitness: " + averageFitness);
        System.out.println("Infinite Fitness: " + infiniteFitness);
        System.out.println("Average Size: " + averageSize);
        this.programInfo.addInfo(bestFitness, averageFitness, averageSize);
        printBestPop();

    }

    private void printBestPop(){
        double bestFitness = Double.POSITIVE_INFINITY;
        Program bestProgram = null;
        for(Program program : this.programs){
            double fitness = program.getFitness();
            if(fitness < bestFitness){
                bestFitness = fitness;
                bestProgram = program;
            }
        }
        if (bestProgram != null) {
            System.out.println("Best Program: ");
            bestProgram.print();
        }
    }

    private Program deserializeProgram(String fileName) {
        MainNode root = (MainNode) Deserializer.deserialize(fileName);
        return new Program(root);
    }

    private double evaluateProgram(Program p){
        return Interpreter.runProgram(p, Parameters.maxEvaluationIterations, evaluator.getEvaluator());
    }

    private void testSerialization(){
        Program p1 = ProgramFactory.generateRandomProgram();
        p1.printToFile("p1.txt");
        Program p2 = deserializeProgram("p1.txt");
        p1.print();
        System.out.println("----------------------------");
        p2.print();
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

    public List<Program> doCrossOver(Program p1, Program p2, ControlStructures p1ControlStructure, Set<ControlStructures> p2ControlStructures){
        Program p1Copy = ProgramFactory.copyProgram(p1);
        Program p2Copy = ProgramFactory.copyProgram(p2);
        Node p1Node = p1Copy.getRandomNodeOfType(p1ControlStructure);
        int p1NodeSize = p1.getNodeSize(p1Node);
        Node p2Node = p2Copy.sizeFair(p1NodeSize, p2ControlStructures);
        if(p2Node == null){
            return null;
        }
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
        parent.replaceChild(oldChild, newChild);
    }

    public List<Program> crossOver(Program p1, Program p2){
        p1.updateTreeInfo();
        p2.updateTreeInfo();
        Set<ControlStructures> p1ControlStructures = p1.getChildrenControlStructures();
        ControlStructures randomControlStructure = p1ControlStructures.iterator().next();
        Set<ControlStructures> allAllowedControlStructures = getLegalCrossOverStructures(randomControlStructure);
        Set<ControlStructures> p2ControlStructures = p2.getChildrenControlStructures();
        Set<ControlStructures> common = new HashSet<>(allAllowedControlStructures);
        common.retainAll(p2ControlStructures);
        if(!common.isEmpty()) {
            return this.doCrossOver(p1, p2, randomControlStructure, common);
        }
        return null;
    }

    private Set<ControlStructures> getLegalCrossOverStructures(ControlStructures randomControlStructure) {
        switch (randomControlStructure){
            case MAIN:
                return new HashSet<>(List.of(ControlStructures.MAIN));
            case IF, LOOP, OUTPUT, ASSIGNMENT:
                return new HashSet<>(Arrays.asList(ControlStructures.IF, ControlStructures.LOOP, ControlStructures.OUTPUT, ControlStructures.ASSIGNMENT));
            case PLUS, MINUS, MODULO, MULTIPLY, DIVISION, INT, FLOAT, BOOL, INPUT:
                return new HashSet<>(Arrays.asList(ControlStructures.PLUS, ControlStructures.MINUS, ControlStructures.MODULO, ControlStructures.MULTIPLY, ControlStructures.DIVISION, ControlStructures.INT, ControlStructures.FLOAT, ControlStructures.BOOL, ControlStructures.INPUT));
            case AND, OR, NOT, CONDITION:
                return new HashSet<>(Arrays.asList(ControlStructures.AND, ControlStructures.OR, ControlStructures.NOT, ControlStructures.CONDITION));
            case ID:
                return new HashSet<>(List.of(ControlStructures.ID));
            default:
                return new HashSet<>();
        }
    }

    public Program mutate(Program p){
        Program pCopy = ProgramFactory.copyProgram(p);
        int nodeCount = pCopy.getSize();
        int nodesToMutate = 1;
        for(int i = 0; i < nodesToMutate; i++){
            Node node = pCopy.getRandomNode();
            pCopy.mutateNode(node);
        }
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
            if(program.getFitness() < best.getFitness()){
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
            if(program.getFitness() > worst.getFitness()){
                worst = program;
            }
        }
        return worst;
    }

    private void generateInitialPopulation() {
        for(int i = 0; i < Parameters.populationSize; i++) {
            Program p = ProgramFactory.generateRandomProgram();
            double fitness = evaluateProgram(p);
            if(fitness < bestFitnessGlobal){
                bestFitnessGlobal = fitness;
            }
            p.setFitness(fitness);
            this.programs.add(p);
        }
    }
}

class ProgramInfo{
    private List<Double> bestFitness;
    private List<Double> averageFitness;
    private List<Double> averageSize;

    public ProgramInfo() {
        this.bestFitness = new ArrayList<>();
        this.averageFitness = new ArrayList<>();
        this.averageSize = new ArrayList<>();
    }

    public void addBestFitness(double fitness){
        this.bestFitness.add(fitness);
    }

    public void addAverageFitness(double fitness){
        this.averageFitness.add(fitness);
    }

    public void addAverageSize(double size){
        this.averageSize.add(size);
    }

    public void addInfo(double bestFitness, double averageFitness, double averageSize){
        this.addBestFitness(bestFitness);
        this.addAverageFitness(averageFitness);
        this.addAverageSize(averageSize);
    }

    public void toCSV(String filename){
        File file = new File(filename);
        boolean exists = file.exists();
        if (!exists) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write("Best Fitness, Average Fitness, Average Size\n");
            for(int i = 0; i < this.bestFitness.size(); i++){
                writer.write(this.bestFitness.get(i) + "," + this.averageFitness.get(i) + "," + this.averageSize.get(i) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
