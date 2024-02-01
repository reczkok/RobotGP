import GP.Evolver;
import GP.Program;
import Interpreter.Language.Evaluator;
import Interpreter.Language.Evaluators.*;
import Interpreter.Language.Evaluators.Benchmarks.*;


public class RobotGP {
    public static void main(String[] args) {
        BooleanInputs.generateInputs(10);
        BooleanInputs.printBoolTable();
        Evaluator evaluator = new BooleanF(10, BooleanInputs.inputs);
        Evolver evolver = new Evolver(evaluator);
        System.out.println("Expected values:");
        BooleanInputs.printBoolTable();
    }
}
