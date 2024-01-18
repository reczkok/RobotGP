package Interpreter.Language;

import java.util.List;

public abstract class Evaluator {
    public abstract Evaluator getEvaluator();
    public abstract double evaluate(List<ExpressionResult> results);
    public abstract ExpressionResult getNextInput();
    public abstract boolean requiresNext();
}
