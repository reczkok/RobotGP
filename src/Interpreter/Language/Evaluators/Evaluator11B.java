package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;

public class Evaluator11B extends Evaluator11A {
    public Evaluator11B() {
        super();
        this.target = 789;
    }

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11B();
    }
}
