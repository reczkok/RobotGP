import GP.Evolver;
import GP.Program;
import Interpreter.Language.Evaluator;
import Interpreter.Language.Evaluators.*;


public class RobotGP {
    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator14B();
        Evolver evolver = new Evolver(evaluator);
    }
}
