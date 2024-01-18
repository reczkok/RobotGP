package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.List;

public class Evaluator12B extends Evaluator {

    List<ExpressionResult> inputs = new ArrayList<>();
    int target;
    boolean readTooMuch = false;
    int iterations = 10;


    @Override
    public Evaluator getEvaluator() {
        return new Evaluator12B();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        double closest = Double.POSITIVE_INFINITY;
        for(ExpressionResult result : results){
            if(result.getType() == ExpressionFlags.Float){
                double value = Math.pow(Math.abs(target - result.getFloatValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
            if(result.getType() == ExpressionFlags.Integer){
                double value = Math.pow(Math.abs(target - result.getIntValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(results.size() > 1 && closest != Float.POSITIVE_INFINITY){
            closest += 100;
        }
        if(readTooMuch && closest != Float.POSITIVE_INFINITY){
            closest += 1000;
            readTooMuch = false;
        }
        return closest;
    }

    @Override
    public ExpressionResult getNextInput() {
        if(inputs.isEmpty()){
            readTooMuch = true;
            return new ExpressionResult();
        }
        return inputs.remove(0);
    }

    @Override
    public boolean requiresNext() {
        inputs.clear();
        int a = (int)(Math.random() * 19) - 9;
        int b = (int)(Math.random() * 19) - 9;
        target = a + b;
        inputs.add(new ExpressionResult(a));
        inputs.add(new ExpressionResult(b));

        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }
}
