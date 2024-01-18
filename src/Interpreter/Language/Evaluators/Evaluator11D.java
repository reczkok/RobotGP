package Interpreter.Language.Evaluators;

import Interpreter.Language.Evaluator;
import Interpreter.Language.ExpressionFlags;
import Interpreter.Language.ExpressionResult;

import java.util.List;

public class Evaluator11D extends Evaluator11A {
    public Evaluator11D() {
        super();
        this.target = 1;
    }

    @Override
    public Evaluator getEvaluator() {
        return new Evaluator11D();
    }

    @Override
    public double evaluate(List<ExpressionResult> results) {
        if(results.isEmpty()){
            return Double.POSITIVE_INFINITY;
        }
        ExpressionResult first = results.get(0);
        double closest = Double.POSITIVE_INFINITY;
        if(first.getType() == ExpressionFlags.Integer){
            closest = Math.pow(Math.abs(target - first.getIntValue()), 2);
        }else {
            return Double.POSITIVE_INFINITY;
        }
        if(this.readTooMuch && closest != Double.POSITIVE_INFINITY){
            closest += 1000;
            this.readTooMuch = false;
        }
        return closest;
    }
}
