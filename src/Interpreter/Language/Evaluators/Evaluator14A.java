package Interpreter.Language.Evaluators;
import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.List;

public class Evaluator14A extends Evaluator{

    List<ExpressionResult> inputs = new ArrayList<>();
    int target;
    boolean readTooMuch = false;
    int excessiveReads = 0;
    int iterations = 50;

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator14A();
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
            } else if(result.getType() == ExpressionFlags.Float){
                double value = Math.pow(Math.abs(target - result.getFloatValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(results.size() > 1 && closest != Float.POSITIVE_INFINITY){
            closest += 100 * results.size();
        }
        if(readTooMuch && closest != Float.POSITIVE_INFINITY){
            closest += 1000 * excessiveReads;
        }
        return closest;
    }

    @Override
    public ExpressionResult getNextInput() {
        if(inputs.isEmpty()){
            readTooMuch = true;
            excessiveReads++;
            return new ExpressionResult();
        }
        return inputs.remove(0);
    }

    @Override
    public boolean requiresNext() {
        readTooMuch = false;
        excessiveReads = 0;
        inputs.clear();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int a = (int)(Math.random() * 199) - 99;
            sum += a;
            inputs.add(new ExpressionResult(a));
        }
        target = sum / 10;


        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }
}
