package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.List;

public class Evaluator11A extends Evaluator {
    boolean readTooMuch = false;
    int target = 1;
    int iterations = 1;

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11A();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        if(results.isEmpty()){
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

    @Override
    public ExpressionResult getNextInput() {
        readTooMuch = true;
        return new ExpressionResult();
    }

    @Override
    public boolean requiresNext() {
        iterations--;
        return iterations >= 0;
    }
}
