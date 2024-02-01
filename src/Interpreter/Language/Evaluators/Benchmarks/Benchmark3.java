package Interpreter.Language.Evaluators.Benchmarks;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Benchmark3 extends Evaluator {
    boolean readTooMuch = false;
    List<ExpressionResult> inputs = new ArrayList<>();
    int iterations = 50;
    int target = 0;
    @Override
    public Evaluator getEvaluator() {
        return new Benchmark3();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        if(results.isEmpty()){
            return Double.POSITIVE_INFINITY;
        }
        double closest = Double.POSITIVE_INFINITY;
        for(ExpressionResult result : results){
            if(result.getType() == ExpressionFlags.Integer){
                double value = Math.abs(target - result.getIntValue());
                if(value < closest){
                    closest = value;
                }
            }
            if(result.getType() == ExpressionFlags.Float){
                double value = Math.abs(target - result.getFloatValue());
                if(value < closest){
                    closest = value;
                }
            }
        }
        if(results.size() > 1 && closest != Double.POSITIVE_INFINITY){
            closest += 5000;
        }
        if(readTooMuch && closest != Double.POSITIVE_INFINITY){
            closest += 10000;
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
        int a = (int) (Math.random() * 1999) - 999;
        int b = (int) (Math.random() * 1999) - 999;
        int c = (int) (Math.random() * 1999) - 999;
        List<Integer> list = Arrays.asList(a, b, c);
        list.sort(Integer::compareTo);
        target = list.get(1);
        inputs.add(new ExpressionResult(a));
        inputs.add(new ExpressionResult(b));
        inputs.add(new ExpressionResult(c));
        if(iterations > 0){
            iterations--;
            return true;
        }
        return false;
    }
}
