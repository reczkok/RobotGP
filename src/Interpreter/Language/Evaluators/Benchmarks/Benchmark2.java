package Interpreter.Language.Evaluators.Benchmarks;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.List;

public class Benchmark2 extends Evaluator{
    boolean readTooMuch = false;
    List<ExpressionResult> inputs = new ArrayList<>();
    int iterations = 50;
    int target = 0;
    @Override
    public Evaluator getEvaluator() {
        return new Benchmark2();
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
            if(result.getType() == ExpressionFlags.Float){
                double value = Math.pow(Math.abs(target - result.getFloatValue()), 2);
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(results.size() > 1 && closest != Double.POSITIVE_INFINITY){
            closest += 100;
        }
        if(readTooMuch && closest != Double.POSITIVE_INFINITY){
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
        readTooMuch = false;
        int a = (int) (Math.random() * 10) + 1;
        for(int i = 0; i <= a; i++){
            target += i * i;
        }
        inputs.add(new ExpressionResult(a));
        if (iterations > 0) {
            iterations--;
            return true;
        }
        return false;
    }

}
