package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;

public class Evaluator11E extends Evaluator11D {
    public Evaluator11E() {
        super();
        this.target = 789;
    }

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11E();
    }
}
