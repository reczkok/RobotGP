package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.List;

public class Evaluator13A extends Evaluator {
    List<ExpressionResult> inputs = new ArrayList<>();
    int target;
    boolean readTooMuch = false;
    int iterations = 50;


    @Override
    public Evaluator getEvaluator() {
        return new Evaluator13A();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        double closest = Double.POSITIVE_INFINITY;
        if(results.size() > 1){
            return closest;
        }
        for(ExpressionResult result : results){
            if(result.getType() == ExpressionFlags.Integer){
                if(result.getIntValue() == target) {
                    closest = 0;
                }else{
                    double value = Math.pow(Math.abs(target - result.getIntValue()), 2) + 1000;
                    if(value < closest){
                        closest = value;
                    }
                }
            }
        }
        if(results.size() > 1 && closest != Float.POSITIVE_INFINITY){
            closest += 5000;
        }
        if(readTooMuch && closest != Float.POSITIVE_INFINITY){
            closest += 1000;
            readTooMuch = false;
        }
        if(!inputs.isEmpty() && closest != Float.POSITIVE_INFINITY){
            closest += 1000;
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
        int a = (int)(Math.random() * 10);
        int b = (int)(Math.random() * 10);
        target = Math.max(a, b);
        inputs.add(new ExpressionResult(a));
        inputs.add(new ExpressionResult(b));

        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }
}
