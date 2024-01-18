package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.List;

public class Evaluator11F extends Evaluator11A {
    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11F();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        if(results.size() != 1){
            return Double.POSITIVE_INFINITY;
        }
        double closest = Double.POSITIVE_INFINITY;
        for(ExpressionResult result : results){
            if(result.getType() == ExpressionFlags.Integer){
                double value = Math.pow(Math.abs(target - result.getIntValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(this.readTooMuch && closest != Double.POSITIVE_INFINITY){
            closest += 1000;
            this.readTooMuch = false;
        }
        return closest;
    }
}
