package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.List;

public class Evaluator14B extends Evaluator14A {
    boolean expectsNoOutput = false;
    @Override
    public Evaluator getEvaluator() {
        return new Evaluator14B();
    }
    @Override
    public boolean requiresNext() {
        excessiveReads = 0;
        readTooMuch = false;
        inputs.clear();
        expectsNoOutput = false;
        int x = (int)(Math.random() * 100);
        int sum = 0;
        for (int i = 0; i < x; i++) {
            int a = (int)(Math.random() * 199) - 99;
            sum += a;
            inputs.add(new ExpressionResult(a));
        }
        if(x == 0){
            target = 0;
            expectsNoOutput = true;
        } else target = sum / x;


        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }

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
            } else if(result.getType() == ExpressionFlags.Float){
                double value = Math.pow(Math.abs(target - result.getFloatValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(inputs.size() > 1 && closest != Float.POSITIVE_INFINITY){
            closest += 1000 * inputs.size();
        }
        if(results.size() > 1 && closest != Float.POSITIVE_INFINITY){
            closest += 1000 * results.size();
        }
        if(readTooMuch && closest != Float.POSITIVE_INFINITY){
            closest += 5000 * excessiveReads;
            readTooMuch = false;
        }
        return closest;
    }
}
