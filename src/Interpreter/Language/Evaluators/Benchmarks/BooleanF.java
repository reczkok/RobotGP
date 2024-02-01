package Interpreter.Language.Evaluators.Benchmarks;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.List;
import java.util.ArrayList;

public class BooleanF extends Evaluator {
    int k;
    int iterations;
    List<List<ExpressionResult>> inputs;
    List<ExpressionResult> currentInputsCopy;
    int currentIteration = 0;
    boolean readTooMuch = false;
    boolean target = false;
    public BooleanF(int k, List<List<ExpressionResult>> inputs){
        this.k = k;
        iterations = (int)Math.pow(2, k);
        this.inputs = inputs;
    }

    @Override
    public Evaluator getEvaluator() {
        return new BooleanF(k, inputs);
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        double closest = 10;
        if(results.isEmpty()){
            return Double.POSITIVE_INFINITY;
        }
        if(results.size() != 1){
            closest = 10000;
        }
        ExpressionResult result = results.get(0);
        if(result.getType() == ExpressionFlags.Boolean){
            if(result.getBoolValue() == target){
                closest = 0;
            }
        }else if(result.getType() == ExpressionFlags.Integer){
            if(result.getIntValue() == (target ? 1 : 0)){
                closest = 0;
            }
        }else if(result.getType() == ExpressionFlags.Float){
            if(result.getFloatValue() == (target ? 1 : 0)){
                closest = 0;
            }
        }
        if(readTooMuch){
            closest += 10;
            readTooMuch = false;
        }
        return closest;
    }

    @Override
    public ExpressionResult getNextInput() {
        if(currentInputsCopy.isEmpty()){
            readTooMuch = true;
            return new ExpressionResult();
        }
        return currentInputsCopy.remove(0);
    }

    @Override
    public boolean requiresNext() {
        if(currentIteration == iterations){
            return false;
        }
        currentInputsCopy = new ArrayList<>();
        currentInputsCopy.addAll(inputs.get(currentIteration));
        target = currentInputsCopy.remove(currentInputsCopy.size() - 1).getBoolValue();
        currentIteration++;
        return true;
    }
}
