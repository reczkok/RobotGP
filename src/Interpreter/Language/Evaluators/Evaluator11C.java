package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;

public class Evaluator11C extends Evaluator11B{
    public Evaluator11C() {
        super();
        this.target = 31415;
    }

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11C();
    }
}
