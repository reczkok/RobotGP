package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionResult;

public class Evaluator12D extends Evaluator12B{
    @Override
    public boolean requiresNext() {
        inputs.clear();
        int a = (int)(Math.random() * 19999) - 9999;
        int b = (int)(Math.random() * 19999) - 9999;
        target = a - b;
        inputs.add(new ExpressionResult(a));
        inputs.add(new ExpressionResult(b));

        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator12D();
    }
}
